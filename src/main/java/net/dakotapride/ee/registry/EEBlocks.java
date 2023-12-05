package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class EEBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EtherealExploration.MOD_ID);

    public static RegistryObject<Block> ABNORMAL_TABLE = block("abnormal_table", () -> new AbnormalTableBlock(BlockBehaviour.Properties.copy(Blocks.CARTOGRAPHY_TABLE)));

    public static RegistryObject<Block> BLEAK_STONE = block("bleak_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_STONE_SLAB = block("bleak_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_STONE_STAIRS = block("bleak_stone_stairs", StairsBlock.BleakStone::new);
    public static RegistryObject<Block> BLEAK_STONE_WALL = block("bleak_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_BLEAK_STONE = block("polished_bleak_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_BLEAK_STONE_SLAB = block("polished_bleak_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_BLEAK_STONE_STAIRS = block("polished_bleak_stone_stairs", StairsBlock.PolishedBleakStone::new);
    public static RegistryObject<Block> POLISHED_BLEAK_STONE_WALL = block("polished_bleak_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_STONE_BRICKS = block("bleak_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_STONE_BRICK_SLAB = block("bleak_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_STONE_BRICK_STAIRS = block("bleak_stone_brick_stairs", StairsBlock.BleakStoneBricks::new);
    public static RegistryObject<Block> BLEAK_STONE_BRICK_WALL = block("bleak_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));

    public static RegistryObject<Block> DENDRITIC_STONE = block("dendritic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_STONE_SLAB = block("dendritic_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_STONE_STAIRS = block("dendritic_stone_stairs", StairsBlock.DendriticStone::new);
    public static RegistryObject<Block> DENDRITIC_STONE_WALL = block("dendritic_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_DENDRITIC_STONE = block("polished_dendritic_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_DENDRITIC_STONE_SLAB = block("polished_dendritic_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_DENDRITIC_STONE_STAIRS = block("polished_dendritic_stone_stairs", StairsBlock.PolishedDendriticStone::new);
    public static RegistryObject<Block> POLISHED_DENDRITIC_STONE_WALL = block("polished_dendritic_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_STONE_BRICKS = block("dendritic_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_STONE_BRICK_SLAB = block("dendritic_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_STONE_BRICK_STAIRS = block("dendritic_stone_brick_stairs", StairsBlock.DendriticStoneBricks::new);
    public static RegistryObject<Block> DENDRITIC_STONE_BRICK_WALL = block("dendritic_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));

    public static RegistryObject<Block> CELESTE_STONE = block("celeste_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_STONE_SLAB = block("celeste_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_STONE_STAIRS = block("celeste_stone_stairs", StairsBlock.DendriticStone::new);
    public static RegistryObject<Block> CELESTE_STONE_WALL = block("celeste_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_CELESTE_STONE = block("polished_celeste_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_CELESTE_STONE_SLAB = block("polished_celeste_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> POLISHED_CELESTE_STONE_STAIRS = block("polished_celeste_stone_stairs", StairsBlock.PolishedDendriticStone::new);
    public static RegistryObject<Block> POLISHED_CELESTE_STONE_WALL = block("polished_celeste_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_STONE_BRICKS = block("celeste_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_STONE_BRICK_SLAB = block("celeste_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_STONE_BRICK_STAIRS = block("celeste_stone_brick_stairs", StairsBlock.DendriticStoneBricks::new);
    public static RegistryObject<Block> CELESTE_STONE_BRICK_WALL = block("celeste_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).requiresCorrectToolForDrops()));

    public static RegistryObject<Block> VOID_STONE = block("void_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));

    public static RegistryObject<Block> HARDENED_SLUDGE_BLOCK = block("hardened_sludge", () -> new SludgeBlock(4.0F,
            BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 5).destroyTime(0F)));
    public static RegistryObject<Block> INDUSTRIAL_IRON_BLOCK = block("industrial_iron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> SLUDGE_COVERED_GRASS_BLOCK = block("sludge_covered_grass_block", () -> new SludgeCoveredGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static RegistryObject<Block> DINGY_DIRT = block("dingy_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static RegistryObject<Block> BLEAK_IRON_ORE = block("bleak_iron_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_GOLD_ORE = block("bleak_gold_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_COAL_ORE = block("bleak_coal_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> BLEAK_SULFUR_ORE = block("bleak_sulfur_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_IRON_ORE = block("dendritic_iron_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_GOLD_ORE = block("dendritic_gold_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_COAL_ORE = block("dendritic_coal_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> DENDRITIC_SULFUR_ORE = block("dendritic_sulfur_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_IRON_ORE = block("celeste_iron_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_GOLD_ORE = block("celeste_gold_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_COAL_ORE = block("celeste_coal_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> CELESTE_SULFUR_ORE = block("celeste_sulfur_ore", () -> new WastesOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops()));
    public static RegistryObject<LiquidBlock> SLUDGE_FLUID = BLOCKS.register("sludge", () -> new SludgeBlock.Liquid(EEFluids.SOURCE_SLUDGE,
            BlockBehaviour.Properties.copy(Blocks.WATER).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel((state) -> 15)));

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
    }

    public static <T extends Block> RegistryObject<T> block(String name, Supplier<T> block) {
        RegistryObject<T> value = BLOCKS.register(name, block);
        blockItem(name, value);
        return value;
    }

    public static <T extends Block> RegistryObject<Item> blockItem(String name, RegistryObject<T> block) {
        return EEItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
