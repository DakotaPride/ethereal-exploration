package net.dakotapride.ee.utils;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.effect.BrewingRecipe;
import net.dakotapride.ee.entity.*;
import net.dakotapride.ee.particle.FumesParticle;
import net.dakotapride.ee.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EtherealExploration.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {
    @SubscribeEvent
    public static void commonSetupEvent(FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD, EEItems.AQUADINE.get(), EEEffects.Potions.TOXIC_VISION.get()));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(EEEffects.Potions.TOXIC_VISION.get(), Items.REDSTONE, EEEffects.Potions.LONG_TOXIC_VISION.get()));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.MUNDANE, EEItems.SULFURIC_DUST.get(), EEEffects.Potions.AVOIDANCE.get()));
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EEEntities.OUTBURST.get(), OutburstEntity.createAttributes());
        event.put(EEEntities.FUME.get(), FumeEntity.createAttributes());
        event.put(EEEntities.AQUADINE.get(), AquadineEntity.createAttributes().build());
        event.put(EEEntities.DEPHELINGUS.get(), DephelingusEntity.createAttributes().build());
        event.put(EEEntities.DEVIANT.get(), DeviantEntity.createAttributes());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(EEParticles.FUMES.get(), FumesParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(EEParticles.SMALL_FUMES.get(), FumesParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        SpawnPlacements.Type sludgeSpawnPlacements = SpawnPlacements.Type.create("in_sludge",
                (levelReader, blockPos, entityType) -> !levelReader.getFluidState(blockPos).isEmpty()
                        && levelReader.getFluidState(blockPos).getFluidType() == EEFluids.Types.SLUDGE_FLUID_TYPE.get());

        SpawnPlacements.register(EEEntities.FUME.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FumeEntity::spawnConditions);
        SpawnPlacements.register(EEEntities.AQUADINE.get(), sludgeSpawnPlacements,
                Heightmap.Types.OCEAN_FLOOR, AquadineEntity::checkSpawnConditionsForAcidicEntity);
        SpawnPlacements.register(EEEntities.DEPHELINGUS.get(), sludgeSpawnPlacements,
                Heightmap.Types.OCEAN_FLOOR, DephelingusEntity::checkSpawnConditionsForAcidicEntity);
        SpawnPlacements.register(EEEntities.DEVIANT.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DeviantEntity::spawnConditions);
    }

}
