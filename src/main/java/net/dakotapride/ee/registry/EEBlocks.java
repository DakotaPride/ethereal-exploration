package net.dakotapride.ee.registry;

import net.dakotapride.ee.block.SludgeBlock;
import net.dakotapride.ee.utils.EERegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEBlocks {

    public static RegistryObject<Block> HARDENED_SLUDGE_BLOCK;
    public static RegistryObject<Block> INDUSTRIAL_IRON_BLOCK;
    public static RegistryObject<Block> BLEAK_STONE;

    public static void init() {
        HARDENED_SLUDGE_BLOCK = EERegistry.block("hardened_sludge", () -> new SludgeBlock(4.0F,
                BlockBehaviour.Properties.of().lightLevel((state) -> 5).strength(50.0F, 1200.0F)));
        INDUSTRIAL_IRON_BLOCK = EERegistry.block("industrial_iron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
        BLEAK_STONE = EERegistry.block("bleak_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    }

}
