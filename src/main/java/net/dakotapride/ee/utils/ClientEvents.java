package net.dakotapride.ee.utils;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.FumeGasEntity;
import net.dakotapride.ee.entity.render.*;
import net.dakotapride.ee.registry.EEEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EtherealExploration.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EEEntities.OUTBURST.get(), OutburstRenderer::new);
        EntityRenderers.register(EEEntities.FUME.get(), FumeRenderer::new);
        // Predator
        EntityRenderers.register(EEEntities.AQUADINE.get(), AquadineRenderer::new);
        // Prey for the Aquadine mob
        EntityRenderers.register(EEEntities.DEPHELINGUS.get(), DephelingusRenderer::new);

        EntityRenderers.register(EEEntities.FUME_GAS.get(), FumeGasRenderer::new);
    }
}
