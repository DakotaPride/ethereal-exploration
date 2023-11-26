package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class EEItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EtherealExploration.MOD_ID);

    // public static RegistryObject<Item> TOXIN_PROTECTION_HELMET = ITEMS.register("toxin_helmet", () -> new Item(new Item.Properties().stacksTo(1).fireResistant()));
    public static RegistryObject<Item> AQUADINE = ITEMS.register("aquadine", () -> new Item(new Item.Properties().food(EEFoods.RAW_AQUADINE.properties)));
    public static RegistryObject<Item> COOKED_AQUADINE = ITEMS.register("cooked_aquadine", () -> new Item(new Item.Properties().food(EEFoods.COOKED_AQUADINE.properties)));
    public static RegistryObject<Item> BUCKET_OF_AQUADINE = ITEMS.register("aquadine_bucket", () -> new MobBucketItem(() -> EEEntities.AQUADINE.get(),
            EEFluids.SOURCE_SLUDGE, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
    public static RegistryObject<Item> DEPHELINGUS = ITEMS.register("dephelingus", () -> new Item(new Item.Properties().food(EEFoods.RAW_DEPHELINGUS.properties)));
    public static RegistryObject<Item> COOKED_DEPHELINGUS = ITEMS.register("cooked_dephelingus", () -> new Item(new Item.Properties().food(EEFoods.COOKED_DEPHELINGUS.properties)));
    public static RegistryObject<Item> BUCKET_OF_DEPHELINGUS = ITEMS.register("dephelingus_bucket", () -> new MobBucketItem(() -> EEEntities.DEPHELINGUS.get(),
            EEFluids.SOURCE_SLUDGE, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
    public static RegistryObject<Item> BUCKET_OF_SLUDGE = ITEMS.register("sludge_bucket", () -> new BucketItem(EEFluids.SOURCE_SLUDGE, new Item.Properties()
            .craftRemainder(Items.BUCKET).stacksTo(1)));
    public static RegistryObject<Item> SULFURIC_DUST = ITEMS.register("sulfur_dust", () -> new Item(new Item.Properties().fireResistant()));

    public static RegistryObject<Item> AQUADINE_SPAWN_EGG = ITEMS.register("aquadine_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> EEEntities.AQUADINE.get(), 0x2D601A, 0xDCFF84, new Item.Properties()));
    public static RegistryObject<Item> DEPHELINGUS_SPAWN_EGG = ITEMS.register("dephelingus_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> EEEntities.DEPHELINGUS.get(), 0x668C7B, 0xDCFF84, new Item.Properties()));
    public static RegistryObject<Item> FUME_SPAWN_EGG = ITEMS.register("fume_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> EEEntities.FUME.get(), 0x32513B, 0xF5FFFE, new Item.Properties()));
    public static RegistryObject<Item> DEVIANT_SPAWN_EGG = ITEMS.register("deviant_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> EEEntities.DEVIANT.get(), 0xA7CCC1, 0x223A2A, new Item.Properties()));
    public static RegistryObject<Item> OUTBURST_SPAWN_EGG = ITEMS.register("outburst_spawn_egg",
            () -> new ForgeSpawnEggItem(() -> EEEntities.OUTBURST.get(), 0x7AF276, 0x0F5723, new Item.Properties()));

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static class Tab {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EtherealExploration.MOD_ID);

        public static final RegistryObject<CreativeModeTab> WASTES = CREATIVE_MODE_TABS.register("wastes",
                () -> CreativeModeTab.builder().icon(() -> new ItemStack(EEItems.BUCKET_OF_AQUADINE.get()))
                        .title(Component.translatable("etherealexploration.tab.wastes"))
                        .displayItems(new Module.Wastes())
                        .build());

        public static void init(IEventBus bus) {
            CREATIVE_MODE_TABS.register(bus);
        }

        public static class Module {

            public static class Wastes extends Module implements CreativeModeTab.DisplayItemsGenerator {

                public void spawnEggs(CreativeModeTab.@NotNull Output output) {
                    output.accept(EEItems.OUTBURST_SPAWN_EGG.get());
                    output.accept(EEItems.FUME_SPAWN_EGG.get());
                    output.accept(EEItems.DEVIANT_SPAWN_EGG.get());
                    output.accept(EEItems.AQUADINE_SPAWN_EGG.get());
                    output.accept(EEItems.DEPHELINGUS_SPAWN_EGG.get());
                }

                public void blocks(CreativeModeTab.@NotNull Output output) {
                    output.accept(EEBlocks.SLUDGE_COVERED_GRASS_BLOCK.get().asItem());
                    output.accept(EEBlocks.DINGY_DIRT.get().asItem());

                    output.accept(EEBlocks.BLEAK_STONE.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_SLAB.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_STAIRS.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_WALL.get().asItem());
                    output.accept(EEBlocks.POLISHED_BLEAK_STONE.get().asItem());
                    output.accept(EEBlocks.POLISHED_BLEAK_STONE_SLAB.get().asItem());
                    output.accept(EEBlocks.POLISHED_BLEAK_STONE_STAIRS.get().asItem());
                    output.accept(EEBlocks.POLISHED_BLEAK_STONE_WALL.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_BRICKS.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_BRICK_SLAB.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_BRICK_STAIRS.get().asItem());
                    output.accept(EEBlocks.BLEAK_STONE_BRICK_WALL.get().asItem());
                    output.accept(EEBlocks.BLEAK_IRON_ORE.get().asItem());
                    output.accept(EEBlocks.BLEAK_GOLD_ORE.get().asItem());
                    output.accept(EEBlocks.BLEAK_COAL_ORE.get().asItem());
                    output.accept(EEBlocks.BLEAK_SULFUR_ORE.get().asItem());

                    output.accept(EEBlocks.DENDRITIC_STONE.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_SLAB.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_STAIRS.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_WALL.get().asItem());
                    output.accept(EEBlocks.POLISHED_DENDRITIC_STONE.get().asItem());
                    output.accept(EEBlocks.POLISHED_DENDRITIC_STONE_SLAB.get().asItem());
                    output.accept(EEBlocks.POLISHED_DENDRITIC_STONE_STAIRS.get().asItem());
                    output.accept(EEBlocks.POLISHED_DENDRITIC_STONE_WALL.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_BRICKS.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_BRICK_SLAB.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_BRICK_STAIRS.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_STONE_BRICK_WALL.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_IRON_ORE.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_GOLD_ORE.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_COAL_ORE.get().asItem());
                    output.accept(EEBlocks.DENDRITIC_SULFUR_ORE.get().asItem());

                    output.accept(EEBlocks.CELESTE_STONE.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_SLAB.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_STAIRS.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_WALL.get().asItem());
                    output.accept(EEBlocks.POLISHED_CELESTE_STONE.get().asItem());
                    output.accept(EEBlocks.POLISHED_CELESTE_STONE_SLAB.get().asItem());
                    output.accept(EEBlocks.POLISHED_CELESTE_STONE_STAIRS.get().asItem());
                    output.accept(EEBlocks.POLISHED_CELESTE_STONE_WALL.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_BRICKS.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_BRICK_SLAB.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_BRICK_STAIRS.get().asItem());
                    output.accept(EEBlocks.CELESTE_STONE_BRICK_WALL.get().asItem());
                    output.accept(EEBlocks.CELESTE_IRON_ORE.get().asItem());
                    output.accept(EEBlocks.CELESTE_GOLD_ORE.get().asItem());
                    output.accept(EEBlocks.CELESTE_COAL_ORE.get().asItem());
                    output.accept(EEBlocks.CELESTE_SULFUR_ORE.get().asItem());

                    output.accept(EEBlocks.INDUSTRIAL_IRON_BLOCK.get().asItem());
                    output.accept(EEBlocks.HARDENED_SLUDGE_BLOCK.get().asItem());
                }

                public void items(CreativeModeTab.@NotNull Output output) {
                    output.accept(EEItems.AQUADINE.get());
                    output.accept(EEItems.COOKED_AQUADINE.get());
                    output.accept(EEItems.BUCKET_OF_AQUADINE.get());

                    output.accept(EEItems.DEPHELINGUS.get());
                    output.accept(EEItems.COOKED_DEPHELINGUS.get());
                    output.accept(EEItems.BUCKET_OF_DEPHELINGUS.get());

                    output.accept(EEItems.BUCKET_OF_SLUDGE.get());
                    output.accept(EEItems.SULFURIC_DUST.get());
                }

                public void displayItems(CreativeModeTab.@NotNull Output output) {
                    items(output);
                    blocks(output);
                    spawnEggs(output);
                }

                @Override
                public void accept(CreativeModeTab.@NotNull ItemDisplayParameters parameters, CreativeModeTab.@NotNull Output output) {
                    displayItems(output);
                }
            }
        }
    }

}
