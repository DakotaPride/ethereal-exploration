package net.dakotapride.ee.registry;

import net.dakotapride.ee.block.BleakOreBlock;
import net.dakotapride.ee.block.SludgeBlock;
import net.dakotapride.ee.block.SludgeCoveredGrassBlock;
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
    public static RegistryObject<Block> SLUDGE_COVERED_GRASS_BLOCK;
    public static RegistryObject<Block> DINGY_DIRT;
    public static RegistryObject<Block> BLEAK_IRON_ORE;
    public static RegistryObject<Block> BLEAK_GOLD_ORE;
    public static RegistryObject<Block> BLEAK_COAL_ORE;

    public static void init() {
        HARDENED_SLUDGE_BLOCK = EERegistry.block("hardened_sludge", () -> new SludgeBlock(4.0F,
                BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 5).destroyTime(0F)));
        INDUSTRIAL_IRON_BLOCK = EERegistry.block("industrial_iron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
        BLEAK_STONE = EERegistry.block("bleak_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
        SLUDGE_COVERED_GRASS_BLOCK = EERegistry.block("sludge_covered_grass_block", () -> new SludgeCoveredGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
        DINGY_DIRT = EERegistry.block("dingy_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
        BLEAK_IRON_ORE = EERegistry.block("bleak_iron_ore", () -> new BleakOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
        BLEAK_GOLD_ORE = EERegistry.block("bleak_gold_ore", () -> new BleakOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));
        BLEAK_COAL_ORE = EERegistry.block("bleak_coal_ore", () -> new BleakOreBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)));
    }

}
