package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEEffects {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EtherealExploration.MOD_ID);

    public static RegistryObject<MobEffect> PESTILENT = EFFECTS.register("pestilent", PestilantEffect::new);
    public static RegistryObject<MobEffect> TOXIC_VISION = EFFECTS.register("toxic_vision", ToxicVisionEffect::new);
    public static RegistryObject<MobEffect> AVOIDANCE = EFFECTS.register("avoidance", AvoidanceEffect::new);

    public static void init(IEventBus bus) {
        EFFECTS.register(bus);
    }

    public static class Potions {
        public static final DeferredRegister<Potion> POTIONS
                = DeferredRegister.create(ForgeRegistries.POTIONS, EtherealExploration.MOD_ID);

        public static final RegistryObject<Potion> TOXIC_VISION = POTIONS.register("toxic_vision",
                () -> new Potion(new MobEffectInstance(EEEffects.TOXIC_VISION.get(), 3600, 0)));
        public static final RegistryObject<Potion> LONG_TOXIC_VISION = POTIONS.register("long_toxic_vision",
                () -> new Potion(new MobEffectInstance(EEEffects.TOXIC_VISION.get(), 9600, 0)));

        public static final RegistryObject<Potion> AVOIDANCE = POTIONS.register("avoidance",
                () -> new Potion(new MobEffectInstance(EEEffects.AVOIDANCE.get(), 1200, 0)));

        public static void init(IEventBus eventBus) {
            POTIONS.register(eventBus);
        }
    }
}
