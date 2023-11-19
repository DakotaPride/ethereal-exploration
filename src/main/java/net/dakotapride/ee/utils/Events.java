package net.dakotapride.ee.utils;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.AquadineEntity;
import net.dakotapride.ee.entity.DephelingusEntity;
import net.dakotapride.ee.entity.FumeEntity;
import net.dakotapride.ee.entity.OutburstEntity;
import net.dakotapride.ee.particle.FumesParticle;
import net.dakotapride.ee.registry.EEEntities;
import net.dakotapride.ee.registry.EEParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EtherealExploration.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EEEntities.OUTBURST.get(), OutburstEntity.createAttributes());
        event.put(EEEntities.FUME.get(), FumeEntity.createAttributes());
        event.put(EEEntities.AQUADINE.get(), AquadineEntity.createAttributes().build());
        event.put(EEEntities.DEPHELINGUS.get(), DephelingusEntity.createAttributes().build());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(EEParticles.FUMES.get(), FumesParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(EEParticles.SMALL_FUMES.get(), FumesParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        SpawnPlacements.register(EEEntities.FUME.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
        SpawnPlacements.register(EEEntities.AQUADINE.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.OCEAN_FLOOR, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        SpawnPlacements.register(EEEntities.DEPHELINGUS.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.OCEAN_FLOOR, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
    }
}
