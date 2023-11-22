package net.dakotapride.ee.block;

import net.dakotapride.ee.registry.EEDamageSources;
import net.dakotapride.ee.registry.EEParticles;
import net.dakotapride.ee.registry.EETags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SludgeBlock extends Block {
    float steppingDamage;
    // SimpleParticleType particles;

    public SludgeBlock(float variantDamage, Properties properties) {
        super(properties);
        this.steppingDamage = variantDamage;
        // this.particles = variantParticles;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        level.addAlwaysVisibleParticle(EEParticles.FUMES.get(), true, (double)pos.getX() + 0.5D + random.nextDouble() / 3.0D *
                (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ()
                + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof Player player) {
            player.hurt(level.damageSources().generic(), steppingDamage);
        }

        super.stepOn(level, pos, state, entity);
    }

    @SuppressWarnings("unused")
    public enum SludgeVariants {
        NORMAL(EEParticles.FUMES.get()),
        CRYSTALLINE_INFUSED(),
        LIMPID();

        SimpleParticleType particleType;

        SludgeVariants(SimpleParticleType particle) {
            this.particleType = particle;
        }

        SludgeVariants() {}

        public SimpleParticleType getParticles() {
            return particleType;
        }

        public void setParticles(SimpleParticleType particleType) {
            this.particleType = particleType;
        }
    }

    public static class Liquid extends LiquidBlock {
        public Liquid(Supplier<? extends FlowingFluid> fluid, Properties properties) {
            super(fluid, properties);
        }

        @Override
        public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
            super.entityInside(state, level, pos, entity);

            if (!(entity.getType().is(EETags.IMMUNE_TO_ACIDIC_DAMAGE))) {
                entity.hurt(entity.damageSources().source(EEDamageSources.ACID), 4.0F);
            }
        }
    }
}
