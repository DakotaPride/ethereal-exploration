package net.dakotapride.ee.entity;

import com.mojang.datafixers.DataFixUtils;
import net.dakotapride.ee.registry.EEBlocks;
import net.dakotapride.ee.registry.EEFluids;
import net.dakotapride.ee.registry.EEItems;
import net.dakotapride.ee.registry.EETags;
import net.dakotapride.ee.utils.IAcidicUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class DephelingusEntity extends WaterAnimal implements GeoEntity, Bucketable, IAcidicUtils {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(DephelingusEntity.class, EntityDataSerializers.BOOLEAN);
    private float landProgress;
    private float fishPitch = 0;
    private boolean wasJustInAcceptableFluid = false;

    @Nullable
    private DephelingusEntity leader;
    private int schoolSize = 1;

    public DephelingusEntity(EntityType<? extends WaterAnimal> entity, Level level) {
        super(entity, level);
        this.moveControl = new DephelingusMoveControl();
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new WaterBoundPathNavigation(this, level) {

            @Override
            protected @NotNull PathFinder createPathFinder(int p_26598_) {
                this.nodeEvaluator = new DephelingusEntity.AcidSwimNodeEvaluator(true);
                return new PathFinder(this.nodeEvaluator, p_26598_);
            }

            @Override
            public boolean isInLiquid() {
                return isInAcidicFluid(DephelingusEntity.this);
            }
        };
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
    public boolean removeWhenFarAway(double dist) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean sit) {
        this.entityData.set(FROM_BUCKET, sit);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("FromBucket", this.fromBucket());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }

    @Override
    protected int calculateFallDamage(float f, float f1) {
        return super.calculateFallDamage(f, f1) - 5;
    }

    public static boolean checkSpawnConditionsForAcidicEntity(EntityType<? extends DephelingusEntity> entity, LevelAccessor level,
                                                              MobSpawnType type, BlockPos pos, RandomSource random) {
        return !level.getFluidState(pos).isEmpty() && level.getFluidState(pos).getFluidType() == EEFluids.Types.SLUDGE_FLUID_TYPE.get();
    }

    @Override
    protected void handleAirSupply(int prevAir) {
        if (this.isAlive() && !isInAcidicFluid(this)) {
            this.setAirSupply(prevAir - 1);
            if (this.getAirSupply() == -20 && !(this.getType().is(EETags.IMMUNE_TO_DRYING_OUT))) {
                this.setAirSupply(0);
                this.hurt(damageSources().dryOut(), 2.0F);
            }
        } else {
            this.setAirSupply(500);
        }
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float f1 = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float f2 = Math.min(f1 * 10.0F, 1.0F);
        this.walkAnimation.update(f2, 0.4F);

    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);
        if (!type.consumesAction()) {
            if (itemstack.getItem() == EEItems.BUCKET_OF_SLUDGE.get() && this.isAlive()) {
                this.playSound(this.getPickupSound(), 1.0F, 1.0F);
                ItemStack itemstack1 = this.getBucketItemStack();
                this.saveToBucketTag(itemstack1);
                ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack1, false);
                player.setItemInHand(hand, itemstack2);
                if (!level().isClientSide) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack1);
                }
                this.discard();
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
        return type;
    }

    @Override
    public void saveToBucketTag(@Nonnull ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        CompoundTag platTag = new CompoundTag();
        this.addAdditionalSaveData(platTag);
        CompoundTag compound = bucket.getOrCreateTag();
        compound.put("FishBucketTag", platTag);
    }

    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        if (compound.contains("FishBucketTag")) {
            this.readAdditionalSaveData(compound.getCompound("FishBucketTag"));
        }
        this.setAirSupply(2000);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowDephelingusLeaderGoal());
        this.goalSelector.addGoal(4, new DephelingusSwimmingGoal());
    }

    class FollowDephelingusLeaderGoal extends Goal {
        private static final int INTERVAL_TICKS = 200;
        private int timeToRecalcPath;
        private int nextStartTick;

        public FollowDephelingusLeaderGoal() {}

        protected int nextStartTick(DephelingusEntity dephelingus) {
            return reducedTickDelay(200 + dephelingus.getRandom().nextInt(200) % 20);
        }

        @Override
        public boolean canUse() {
            if (DephelingusEntity.this.hasFollowers()) {
                return false;
            } else if (DephelingusEntity.this.isFollower()) {
                return true;
            } else if (this.nextStartTick > 0) {
                --this.nextStartTick;
                return false;
            } else {
                this.nextStartTick = this.nextStartTick(DephelingusEntity.this);
                Predicate<DephelingusEntity> predicate = (dephelingus) -> dephelingus.canBeFollowed() || !dephelingus.isFollower();
                List<? extends DephelingusEntity> list = DephelingusEntity.this.level().getEntitiesOfClass(DephelingusEntity.this.getClass(), DephelingusEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
                DephelingusEntity dephelingus = DataFixUtils.orElse(list.stream().filter(DephelingusEntity::canBeFollowed).findAny(), DephelingusEntity.this);
                dephelingus.addFollowers(list.stream().filter((follower) -> !follower.isFollower()));
                return DephelingusEntity.this.isFollower();
            }
        }

        @Override
        public boolean canContinueToUse() {
            return DephelingusEntity.this.isFollower() && DephelingusEntity.this.inRangeOfLeader();
        }

        @Override
        public void start() {
            this.timeToRecalcPath = 0;
        }

        @Override
        public void stop() {
            DephelingusEntity.this.stopFollowing();
        }

        @Override
        public void tick() {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                DephelingusEntity.this.pathToLeader();
            }
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    protected boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public DephelingusEntity startFollowing(DephelingusEntity entity) {
        this.leader = entity;
        entity.addFollower();
        return entity;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends DephelingusEntity> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }

        float prevLandProgress = landProgress;
        float prevFishPitch = fishPitch;
        boolean grounded = this.onGround() && !isInAcidicFluid(this);
        if (grounded && landProgress < 5F) {
            landProgress++;
        }
        if (!grounded && landProgress > 0F) {
            landProgress--;
        }
        fishPitch = Mth.clamp((float) this.getDeltaMovement().y * 1.8F, -1.0F, 1.0F) * -(float) (180F / (float) Math.PI);
        boolean inAcid = isInAcidicFluid(this);
        if (inAcid != wasJustInAcceptableFluid) {
            wasJustInAcceptableFluid = inAcid;
        }
        if (!isInAcidicFluid(this) && this.isAlive()) {
            if (this.onGround() && random.nextFloat() < 0.1F) {
                this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F, 0.5D, (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F));
                this.setYRot(this.random.nextFloat() * 360.0F);
                this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
            }
        }

    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }

    }

    public void addFollowers(Stream<? extends DephelingusEntity> entity) {
        entity.limit(this.getMaxSchoolSize() - this.schoolSize).filter((dephelingus) -> dephelingus != this).forEach((dephelingus) -> dephelingus.startFollowing(this));
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        super.finalizeSpawn(level, difficulty, spawnType, data, tag);
        if (data == null) {
            data = new DephelingusEntity.SchoolSpawnGroupData(this);
        } else {
            this.startFollowing(((DephelingusEntity.SchoolSpawnGroupData)data).leader);
        }

        return data;
    }

    public static class SchoolSpawnGroupData implements SpawnGroupData {
        public final DephelingusEntity leader;

        public SchoolSpawnGroupData(DephelingusEntity entity) {
            this.leader = entity;
        }
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
        return 0.2F;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return EEItems.BUCKET_OF_DEPHELINGUS.get().getDefaultInstance();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {

            return PlayState.CONTINUE;
        } else {
            state.getController().setAnimation(RawAnimation.begin().then("animation.dephelingus.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @SuppressWarnings("unused")
    public static class AcidSwimNodeEvaluator extends SwimNodeEvaluator {

        public AcidSwimNodeEvaluator(boolean breach) {
            super(breach);
        }

        @Override
        public @NotNull BlockPathTypes getBlockPathType(@NotNull BlockGetter getter, int x, int y, int z, @NotNull Mob mob) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int i = x; i < x + entityWidth; ++i) {
                for (int j = y; j < y + entityHeight; ++j) {
                    for (int k = z; k < z + entityDepth; ++k) {
                        FluidState fluidstate = getter.getFluidState(blockpos$mutableblockpos.set(i, j, k));
                        BlockState blockstate = getter.getBlockState(blockpos$mutableblockpos.set(i, j, k));
                        if (fluidstate.isEmpty() && blockstate.isPathfindable(getter, blockpos$mutableblockpos.below(), PathComputationType.WATER) && blockstate.isAir()) {
                            return BlockPathTypes.BREACH;
                        }

                        //works in water and acid, not lava
                        if (fluidstate.is(FluidTags.LAVA)) {
                            return BlockPathTypes.BLOCKED;
                        }
                    }
                }
            }

            BlockState blockstate1 = getter.getBlockState(blockpos$mutableblockpos);
            return blockstate1.isPathfindable(getter, blockpos$mutableblockpos, PathComputationType.WATER) ? BlockPathTypes.WATER : BlockPathTypes.BLOCKED;
        }
    }

    @SuppressWarnings("unused")
    class DephelingusMoveControl extends MoveControl implements IAcidicUtils {

        public DephelingusMoveControl() {
            super(DephelingusEntity.this);
        }

        @Override
        public void tick() {
            if (DephelingusEntity.this.isInFluidType(EEFluids.Types.SLUDGE_FLUID_TYPE.get())) {
                DephelingusEntity.this.setDeltaMovement(DephelingusEntity.this.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !DephelingusEntity.this.getNavigation().isDone()) {
                float f = (float)(this.speedModifier * DephelingusEntity.this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                DephelingusEntity.this.setSpeed(Mth.lerp(0.125F, DephelingusEntity.this.getSpeed(), f));
                double d0 = this.wantedX - DephelingusEntity.this.getX();
                double d1 = this.wantedY - DephelingusEntity.this.getY();
                double d2 = this.wantedZ - DephelingusEntity.this.getZ();
                if (d1 != 0.0D) {
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    DephelingusEntity.this.setDeltaMovement(DephelingusEntity.this.getDeltaMovement().add(0.0D, (double)DephelingusEntity.this.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    DephelingusEntity.this.setYRot(this.rotlerp(DephelingusEntity.this.getYRot(), f1, 90.0F));
                    DephelingusEntity.this.yBodyRot = DephelingusEntity.this.getYRot();
                }

            } else {
                DephelingusEntity.this.setSpeed(0.0F);
            }
        }
    }

    @SuppressWarnings("unused")
    class DephelingusSwimmingGoal extends RandomSwimmingGoal {
        public DephelingusSwimmingGoal() {
            super(DephelingusEntity.this, 1.0D, 40);
        }

        public boolean canUse() {
            return DephelingusEntity.this.canRandomSwim() && super.canUse() && !(DephelingusEntity.this.isFollower());
        }
    }
}
