package net.dakotapride.ee.entity;

import net.dakotapride.ee.registry.EEEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class NonDestructivePrimedTntEntity extends PrimedTnt {
    public NonDestructivePrimedTntEntity(EntityType<? extends PrimedTnt> entity, Level level) {
        super(entity, level);
    }

    public NonDestructivePrimedTntEntity(Level level, double x, double y, double z) {
        this(EEEntities.NONDESTRUCTIVE_TNT.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void explode() {
        float f = 4.0F;
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Level.ExplosionInteraction.NONE);
    }
}
