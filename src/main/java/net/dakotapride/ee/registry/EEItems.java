package net.dakotapride.ee.registry;

import net.dakotapride.ee.utils.EERegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEItems {

    // public static RegistryObject<Item> TOXIN_PROTECTION_HELMET;
    public static RegistryObject<Item> AQUADINE;
    public static RegistryObject<Item> BUCKET_OF_AQUADINE;
    public static RegistryObject<Item> DEPHELINGUS;
    public static RegistryObject<Item> BUCKET_OF_DEPHELINGUS;

    public static void init() {
        // TOXIN_PROTECTION_HELMET = EERegistry.ITEMS.register("toxin_helmet", () -> new Item(new Item.Properties().stacksTo(1).fireResistant()));
        AQUADINE = EERegistry.ITEMS.register("aquadine", () -> new Item(new Item.Properties().food(EEFoods.RAW_AQUADINE.properties)));
        BUCKET_OF_AQUADINE = EERegistry.ITEMS.register("aquadine_bucket", () -> new MobBucketItem(() -> EEEntities.AQUADINE.get(),
                () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
        DEPHELINGUS = EERegistry.ITEMS.register("dephelingus", () -> new Item(new Item.Properties().food(EEFoods.RAW_DEPHELINGUS.properties)));
        BUCKET_OF_DEPHELINGUS = EERegistry.ITEMS.register("dephelingus_bucket", () -> new MobBucketItem(() -> EEEntities.DEPHELINGUS.get(),
                () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
    }

}
