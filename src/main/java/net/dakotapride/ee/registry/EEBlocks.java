package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.block.BleakOreBlock;
import net.dakotapride.ee.block.SludgeBlock;
import net.dakotapride.ee.block.SludgeCoveredGrassBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
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

    public static RegistryObject<Block> HARDENED_SLUDGE_BLOCK = block("hardened_sludge", () -> new SludgeBlock(4.0F,
            BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 5).destroyTime(0F)));
    public static RegistryObject<Block> INDUSTRIAL_IRON_BLOCK = block("industrial_iron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static RegistryObject<Block> BLEAK_STONE = block("bleak_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static RegistryObject<Block> SLUDGE_COVERED_GRASS_BLOCK = block("sludge_covered_grass_block", () -> new SludgeCoveredGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static RegistryObject<Block> DINGY_DIRT = block("dingy_dirt", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static RegistryObject<Block> BLEAK_IRON_ORE = block("bleak_iron_ore", () -> new BleakOreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static RegistryObject<Block> BLEAK_GOLD_ORE = block("bleak_gold_ore", () -> new BleakOreBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));
    public static RegistryObject<Block> BLEAK_COAL_ORE = block("bleak_coal_ore", () -> new BleakOreBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)));
    public static RegistryObject<LiquidBlock> SLUDGE_FLUID = BLOCKS.register("sludge", () -> new LiquidBlock(EEFluids.SOURCE_SLUDGE,
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
