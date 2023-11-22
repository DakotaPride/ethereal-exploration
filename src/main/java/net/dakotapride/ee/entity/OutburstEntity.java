package net.dakotapride.ee.entity;

import net.dakotapride.ee.registry.EEEffects;
import net.minecraft.advancements.Advancement;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Collection;

@SuppressWarnings("unused")
public class OutburstEntity extends Monster implements PowerableMob, GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    // boolean isExploding;

    public OutburstEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 50;
    }

    public static AttributeSupplier createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
        return 1.55F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        // this.goalSelector.addGoal(2, new SwellGoal(this));
        // this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        // this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().then("animation.outburst.walking", Animation.LoopType.LOOP));
        }

        // else if (this.isExploding) {
        //            OutburstEntity.this.getNavigation().stop();
        //            state.getController().setAnimation(RawAnimation.begin().then("animation.outburst.explosion", Animation.LoopType.PLAY_ONCE));
        //        }

        else {
            state.getController().setAnimation(RawAnimation.begin().then("animation.outburst.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        LivingEntity entity = this.getTarget();

        // if (entity instanceof Player player && this.distanceToSqr(entity) < 3.0D) {
        //            isExploding = true;
        //        } else {
        //            isExploding = false;
        //        }

        if (entity instanceof Player player && this.distanceToSqr(entity) < 9.0D && !(player.getAbilities().instabuild)
                && !(player.hasEffect(EEEffects.AVOIDANCE.get()))) {
            this.provideExplosion();
        }
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        } else {
            if (entity instanceof Player player && this.distanceToSqr(entity) < 9.0D && !(player.getAbilities().instabuild)
                    && player.hasEffect(EEEffects.AVOIDANCE.get())) {
                ((LivingEntity)entity).addEffect(new MobEffectInstance(EEEffects.PESTILENT.get(), 200), this);
            }

            return true;
        }
    }

    // @Override
    //    public void tick() {
    //        super.tick();
    //        LivingEntity entity = this.getTarget();
    //
    //        if (entity instanceof Player player && this.distanceToSqr(entity) < 3.0D) {
    //            isExploding = true;
    //        } else {
    //            isExploding = false;
    //        }
    //
    //        if (entity instanceof Player player && this.distanceToSqr(entity) < 9.0D && !(player.getAbilities().instabuild) && isExploding) {
    //            this.provideExplosion();
    //        }
    //    }

    private void provideExplosion() {
        if (!this.level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)3 * f, Level.ExplosionInteraction.MOB);
            this.spawnLingeringCloud();
        }

    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

            for(MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }

            this.level().addFreshEntity(areaeffectcloud);
        }

    }

    @Override
    public boolean isPowered() {
        return true;
    }
}
