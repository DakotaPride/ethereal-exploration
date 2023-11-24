package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEEntities {
    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EtherealExploration.MOD_ID);

    public static RegistryObject<EntityType<OutburstEntity>> OUTBURST = ENTITIES.register("outburst",
            () -> EntityType.Builder.of(OutburstEntity::new, MobCategory.MONSTER)
                    .sized(0.65f, 1.6f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "outburst").toString()));
    public static RegistryObject<EntityType<FumeEntity>> FUME = ENTITIES.register("fume",
            () -> EntityType.Builder.of(FumeEntity::new, MobCategory.MONSTER)
                    .sized(0.65f, 1.65f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "fume").toString()));
    public static RegistryObject<EntityType<AquadineEntity>> AQUADINE = ENTITIES.register("aquadine",
            () -> EntityType.Builder.of(AquadineEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.65f, 0.6f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "aquadine").toString()));
    public static RegistryObject<EntityType<DephelingusEntity>> DEPHELINGUS = ENTITIES.register("dephelingus",
            () -> EntityType.Builder.of(DephelingusEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.35f, 0.35f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "dephelingus").toString()));
    public static RegistryObject<EntityType<DeviantEntity>> DEVIANT = ENTITIES.register("deviant",
            () -> EntityType.Builder.of(DeviantEntity::new, MobCategory.MONSTER)
                    .sized(0.9f, 0.4f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "deviant").toString()));

    public static RegistryObject<EntityType<FumeGasEntity>> FUME_GAS = ENTITIES.register("fume_gas",
            () -> EntityType.Builder.<FumeGasEntity>of(FumeGasEntity::new, MobCategory.MISC)
                    .sized(0.4f, 0.4f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "fume_gas").toString()));
    public static RegistryObject<EntityType<NonDestructivePrimedTntEntity>> NONDESTRUCTIVE_TNT = ENTITIES.register("nondestructive_tnt",
            () -> EntityType.Builder.<NonDestructivePrimedTntEntity>of(NonDestructivePrimedTntEntity::new, MobCategory.MISC)
                    .sized(0.9f, 0.9f)
                    .build(new ResourceLocation(EtherealExploration.MOD_ID, "nondestructive_tnt").toString()));

    public static void init(IEventBus bus) {
        ENTITIES.register(bus);
    }

}
