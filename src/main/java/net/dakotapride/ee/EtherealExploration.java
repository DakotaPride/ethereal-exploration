package net.dakotapride.ee;

import com.mojang.logging.LogUtils;
import net.dakotapride.ee.registry.*;
import net.dakotapride.ee.utils.EERegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EtherealExploration.MOD_ID)
public class EtherealExploration {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "etherealexploration";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public EtherealExploration() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        bus.addListener(this::commonSetup);

        EEItems.init();
        EERegistry.ITEMS.register(bus);
        EEBlocks.init();
        EERegistry.BLOCKS.register(bus);
        EEEffects.init();
        EERegistry.EFFECTS.register(bus);
        EEEntities.init();
        EERegistry.ENTITIES.register(bus);
        EERegistry.CREATIVE_MODE_TABS.register(bus);
        EEParticles.init();
        EERegistry.PARTICLES.register(bus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
        }
    }
}
