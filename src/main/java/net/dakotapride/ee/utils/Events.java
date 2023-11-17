package net.dakotapride.ee.utils;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.FumeEntity;
import net.dakotapride.ee.entity.OutburstEntity;
import net.dakotapride.ee.particle.FumesParticle;
import net.dakotapride.ee.registry.EEEntities;
import net.dakotapride.ee.registry.EEParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EtherealExploration.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EEEntities.OUTBURST.get(), OutburstEntity.createAttributes());
        event.put(EEEntities.FUME.get(), FumeEntity.createAttributes());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(EEParticles.FUMES.get(), FumesParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(EEParticles.SMALL_FUMES.get(), FumesParticle.Provider::new);
    }
}
