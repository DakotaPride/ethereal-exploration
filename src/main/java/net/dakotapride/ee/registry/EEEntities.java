package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.*;
import net.dakotapride.ee.utils.EERegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEEntities {

    public static RegistryObject<EntityType<OutburstEntity>> OUTBURST;
    public static RegistryObject<EntityType<FumeEntity>> FUME;
    public static RegistryObject<EntityType<AquadineEntity>> AQUADINE;
    public static RegistryObject<EntityType<DephelingusEntity>> DEPHELINGUS;

    public static RegistryObject<EntityType<FumeGasEntity>> FUME_GAS;

    public static void init() {
        OUTBURST = EERegistry.ENTITIES.register("outburst",
                () -> EntityType.Builder.of(OutburstEntity::new, MobCategory.MONSTER)
                        .sized(0.65f, 1.6f)
                        .build(new ResourceLocation(EtherealExploration.MOD_ID, "outburst").toString()));
        FUME = EERegistry.ENTITIES.register("fume",
                () -> EntityType.Builder.of(FumeEntity::new, MobCategory.MONSTER)
                        .sized(0.65f, 1.65f)
                        .build(new ResourceLocation(EtherealExploration.MOD_ID, "fume").toString()));
        AQUADINE = EERegistry.ENTITIES.register("aquadine",
                () -> EntityType.Builder.of(AquadineEntity::new, MobCategory.WATER_CREATURE)
                        .sized(0.65f, 0.6f)
                        .build(new ResourceLocation(EtherealExploration.MOD_ID, "aquadine").toString()));
        DEPHELINGUS = EERegistry.ENTITIES.register("dephelingus",
                () -> EntityType.Builder.of(DephelingusEntity::new, MobCategory.WATER_CREATURE)
                        .sized(0.35f, 0.35f)
                        .build(new ResourceLocation(EtherealExploration.MOD_ID, "dephelingus").toString()));

        FUME_GAS = EERegistry.ENTITIES.register("fume_gas",
                () -> EntityType.Builder.<FumeGasEntity>of(FumeGasEntity::new, MobCategory.MISC)
                        .sized(0.4f, 0.4f)
                        .build(new ResourceLocation(EtherealExploration.MOD_ID, "fume_gas").toString()));
    }
}
