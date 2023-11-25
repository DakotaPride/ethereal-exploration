package net.dakotapride.ee.block;

import net.dakotapride.ee.registry.EETags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WastesOreBlock extends Block {
    public WastesOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader world, RandomSource randomSource, BlockPos pos, int fortune, int silktouch) {
        if (state.is(EETags.CAN_DROP_EXPERIENCE_ORES)) {
            return silktouch == 0 ? 1 + randomSource.nextInt(5) : 0;
        } else {
            return super.getExpDrop(state, world, randomSource, pos, fortune, silktouch);
        }
    }
}
