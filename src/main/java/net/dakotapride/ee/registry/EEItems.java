package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
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
            () -> Fluids.LAVA, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
    public static RegistryObject<Item> DEPHELINGUS = ITEMS.register("dephelingus", () -> new Item(new Item.Properties().food(EEFoods.RAW_DEPHELINGUS.properties)));
    public static RegistryObject<Item> COOKED_DEPHELINGUS = ITEMS.register("cooked_dephelingus", () -> new Item(new Item.Properties().food(EEFoods.COOKED_DEPHELINGUS.properties)));
    public static RegistryObject<Item> BUCKET_OF_DEPHELINGUS = ITEMS.register("dephelingus_bucket", () -> new MobBucketItem(() -> EEEntities.DEPHELINGUS.get(),
            () -> Fluids.LAVA, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
    public static RegistryObject<Item> BUCKET_OF_SLUDGE = ITEMS.register("sludge_bucket", () -> new BucketItem(EEFluids.SOURCE_SLUDGE, new Item.Properties()
            .craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static class Tab {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EtherealExploration.MOD_ID);

        // public static final RegistryObject<CreativeModeTab> TESTING_TAB = CREATIVE_MODE_TABS.register("testing_tab",
        //            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EEItems.TESTING_ITEM.get()))
        //                    .title(Component.translatable("ee.tab.testing"))
        //                    .displayItems(new DisplayItems())
        //                    .build());

        public static class DisplayItems implements CreativeModeTab.DisplayItemsGenerator {

            @Override
            public void accept(CreativeModeTab.@NotNull ItemDisplayParameters parameters, CreativeModeTab.@NotNull Output output) {
                // output.accept(EEItems.TESTING_ITEM.get().getDefaultInstance());
                // output.accept(EEBlocks.TESTING_BLOCK.get().asItem().getDefaultInstance());
            }
        }

        public static void init(IEventBus bus) {
            CREATIVE_MODE_TABS.register(bus);
        }
    }

}
