package net.dakotapride.ee.entity;

import net.dakotapride.ee.registry.EEDamageSources;
import net.dakotapride.ee.registry.EEEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class FumeGasEntity extends Fireball {
    public FumeGasEntity(EntityType<? extends FumeGasEntity> entity, Level level) {
        super(entity, level);
    }

    public FumeGasEntity(EntityType<? extends FumeGasEntity> entity, Level level, LivingEntity livingEntity, double f, double g, double h) {
        super(entity, livingEntity, f, g, h, level);
    }

    public FumeGasEntity(EntityType<? extends FumeGasEntity> entity, Level level, double c, double d, double e, double f, double g, double h) {
        super(entity, c, d, e, f, g, h, level);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            Entity entity = result.getEntity();
            Entity entity1 = this.getOwner();

            entity.hurt(entity1.level().damageSources().source(EEDamageSources.FUME_GAS), 2.0F);

            if (entity instanceof LivingEntity entity2) {
                entity2.addEffect(new MobEffectInstance(MobEffects.POISON, 120, 1));
            }

        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.discard();
        }

    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float v) {
        return false;
    }
}
