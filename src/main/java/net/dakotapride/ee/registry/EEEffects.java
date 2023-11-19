package net.dakotapride.ee.registry;

import net.dakotapride.ee.effect.ToxinEffect;
import net.dakotapride.ee.utils.EERegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEEffects {

    public static RegistryObject<MobEffect> TOXIN;

    public static void init() {
        TOXIN = EERegistry.EFFECTS.register("toxin", ToxinEffect::new);
    }

}
