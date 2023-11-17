package net.dakotapride.ee.registry;

import net.dakotapride.ee.utils.EERegistry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEParticles {

    public static RegistryObject<SimpleParticleType> FUMES;
    public static RegistryObject<SimpleParticleType> SMALL_FUMES;

    public static void init() {
        FUMES = EERegistry.PARTICLES.register("fumes", () -> new SimpleParticleType(true));
        SMALL_FUMES = EERegistry.PARTICLES.register("small_fumes", () -> new SimpleParticleType(true));
    }
}
