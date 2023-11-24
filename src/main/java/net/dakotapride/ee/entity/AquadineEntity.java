package net.dakotapride.ee.entity;

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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
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
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class AquadineEntity extends WaterAnimal implements GeoEntity, Bucketable, IAcidicUtils {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(AquadineEntity.class, EntityDataSerializers.BOOLEAN);
    private float landProgress;
    private float fishPitch = 0;
    private boolean wasJustInAcceptableFluid = false;

    public AquadineEntity(EntityType<? extends WaterAnimal> entity, Level level) {
        super(entity, level);
        this.xpReward = 10;
        this.moveControl = new AquadineMoveControl();
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
                this.nodeEvaluator = new AcidSwimNodeEvaluator(true);
                return new PathFinder(this.nodeEvaluator, p_26598_);
            }

            @Override
            public boolean isInLiquid() {
                return isInAcidicFluid(AquadineEntity.this);
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

    public static boolean checkSpawnConditionsForAcidicEntity(EntityType<? extends AquadineEntity> entity, LevelAccessor level,
                                                              MobSpawnType type, BlockPos pos, RandomSource random) {
        return !level.getFluidState(pos).isEmpty() && level.getFluidState(pos).getFluidType() == EEFluids.Types.SLUDGE_FLUID_TYPE.get();
    }

    @Override
    public void tick() {
        super.tick();
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
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 8.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new AquadineSwimmingGoal());
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.2F, true));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Axolotl.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, DephelingusEntity.class, true));
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
        return 0.4F;
    }

    protected @NotNull SoundEvent getFlopSound() {
        // Temp Sounds
        return SoundEvents.COD_FLOP;
    }

    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {

            return PlayState.CONTINUE;
        } else {
            state.getController().setAnimation(RawAnimation.begin().then("animation.aquadine.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public @NotNull ItemStack getBucketItemStack() {
        return EEItems.BUCKET_OF_AQUADINE.get().getDefaultInstance();
    }

    protected boolean canRandomSwim() {
        return true;
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
    class AquadineMoveControl extends MoveControl implements IAcidicUtils {

        public AquadineMoveControl() {
            super(AquadineEntity.this);
        }

        @Override
        public void tick() {
            // if (this.operation == MoveControl.Operation.MOVE_TO && isInAcidicFluid(AquadineEntity.this)) {
            //                final Vec3 vector3d = new Vec3(this.wantedX - AquadineEntity.this.getX(), this.wantedY - AquadineEntity.this.getY(), this.wantedZ - AquadineEntity.this.getZ());
            //                final double d5 = vector3d.length();
            //                if (d5 < AquadineEntity.this.getBoundingBox().getSize()) {
            //                    this.operation = MoveControl.Operation.WAIT;
            //                    AquadineEntity.this.setDeltaMovement(AquadineEntity.this.getDeltaMovement().scale(0.5D));
            //                } else {
            //                    AquadineEntity.this.setDeltaMovement(AquadineEntity.this.getDeltaMovement().add(vector3d.scale(this.speedModifier * 0.06D / d5)));
            //                    final Vec3 vector3d1 = AquadineEntity.this.getDeltaMovement();
            //                    float f = -((float) Mth.atan2(vector3d1.x, vector3d1.z)) * 180.0F / (float) Math.PI;
            //                    AquadineEntity.this.setYRot(Mth.approachDegrees(AquadineEntity.this.getYRot(), f, 20));
            //                    AquadineEntity.this.yBodyRot = AquadineEntity.this.getYRot();
            //                }
            //            }

            if (AquadineEntity.this.isInFluidType(EEFluids.Types.SLUDGE_FLUID_TYPE.get())) {
                AquadineEntity.this.setDeltaMovement(AquadineEntity.this.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !AquadineEntity.this.getNavigation().isDone()) {
                float f = (float)(this.speedModifier * AquadineEntity.this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                AquadineEntity.this.setSpeed(Mth.lerp(0.125F, AquadineEntity.this.getSpeed(), f));
                double d0 = this.wantedX - AquadineEntity.this.getX();
                double d1 = this.wantedY - AquadineEntity.this.getY();
                double d2 = this.wantedZ - AquadineEntity.this.getZ();
                if (d1 != 0.0D) {
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    AquadineEntity.this.setDeltaMovement(AquadineEntity.this.getDeltaMovement().add(0.0D, (double)AquadineEntity.this.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    AquadineEntity.this.setYRot(this.rotlerp(AquadineEntity.this.getYRot(), f1, 90.0F));
                    AquadineEntity.this.yBodyRot = AquadineEntity.this.getYRot();
                }

            } else {
                AquadineEntity.this.setSpeed(0.0F);
            }
        }
    }

    @SuppressWarnings("unused")
    class AquadineSwimmingGoal extends RandomSwimmingGoal {
        public AquadineSwimmingGoal() {
            super(AquadineEntity.this, 1.0D, 40);
        }

        public boolean canUse() {
            return AquadineEntity.this.canRandomSwim() && super.canUse();
        }
    }
}
