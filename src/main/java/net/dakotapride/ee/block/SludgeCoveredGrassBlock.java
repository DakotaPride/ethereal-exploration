package net.dakotapride.ee.block;

import net.dakotapride.ee.registry.EEDamageSources;
import net.dakotapride.ee.registry.EETags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SludgeCoveredGrassBlock extends Block {
    public SludgeCoveredGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity living && !(living.getType().is(EETags.IMMUNE_TO_TOXIN_DAMAGE))) {
                if (!living.isInvulnerableTo(level.damageSources().source(EEDamageSources.TOXIN))) {
                    living.hurt(level.damageSources().source(EEDamageSources.TOXIN), 4.0F);
                }
            }

        }
    }
}
