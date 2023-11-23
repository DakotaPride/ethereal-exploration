package net.dakotapride.ee.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class NonDestructivePrimedTntEntity extends PrimedTnt {
    public NonDestructivePrimedTntEntity(EntityType<? extends PrimedTnt> entity, Level level) {
        super(entity, level);
    }

    @Override
    protected void explode() {
        float f = 4.0F;
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Level.ExplosionInteraction.NONE);
    }
}
