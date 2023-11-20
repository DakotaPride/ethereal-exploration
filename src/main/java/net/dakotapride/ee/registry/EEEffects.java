package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.effect.ToxinEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEEffects {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EtherealExploration.MOD_ID);

    public static RegistryObject<MobEffect> TOXIN = EFFECTS.register("toxin", ToxinEffect::new);

    public static void init(IEventBus bus) {
        EFFECTS.register(bus);
    }
}
