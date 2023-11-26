package net.dakotapride.ee.block;

import net.dakotapride.ee.registry.EEBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class StairsBlock extends StairBlock {
    public StairsBlock(Supplier<BlockState> state) {
        super(state, BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).requiresCorrectToolForDrops());
    }

    public static class BleakStone extends StairsBlock {
        public BleakStone() {
            super(() -> EEBlocks.BLEAK_STONE.get().defaultBlockState());
        }
    }

    public static class PolishedBleakStone extends StairsBlock {
        public PolishedBleakStone() {
            super(() -> EEBlocks.POLISHED_BLEAK_STONE.get().defaultBlockState());
        }
    }

    public static class BleakStoneBricks extends StairsBlock {
        public BleakStoneBricks() {
            super(() -> EEBlocks.BLEAK_STONE_BRICKS.get().defaultBlockState());
        }
    }

    public static class DendriticStone extends StairsBlock {
        public DendriticStone() {
            super(() -> EEBlocks.DENDRITIC_STONE.get().defaultBlockState());
        }
    }

    public static class PolishedDendriticStone extends StairsBlock {
        public PolishedDendriticStone() {
            super(() -> EEBlocks.POLISHED_DENDRITIC_STONE.get().defaultBlockState());
        }
    }

    public static class DendriticStoneBricks extends StairsBlock {
        public DendriticStoneBricks() {
            super(() -> EEBlocks.DENDRITIC_STONE_BRICKS.get().defaultBlockState());
        }
    }

    public static class CelesteStone extends StairsBlock {
        public CelesteStone() {
            super(() -> EEBlocks.CELESTE_STONE.get().defaultBlockState());
        }
    }

    public static class PolishedCelesteStone extends StairsBlock {
        public PolishedCelesteStone() {
            super(() -> EEBlocks.POLISHED_CELESTE_STONE.get().defaultBlockState());
        }
    }

    public static class CelesteStoneBricks extends StairsBlock {
        public CelesteStoneBricks() {
            super(() -> EEBlocks.CELESTE_STONE_BRICKS.get().defaultBlockState());
        }
    }

}
