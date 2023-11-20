package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EtherealExploration.MOD_ID);

    public static RegistryObject<SimpleParticleType> FUMES = PARTICLES.register("fumes", () -> new SimpleParticleType(true));
    public static RegistryObject<SimpleParticleType> SMALL_FUMES = PARTICLES.register("small_fumes", () -> new SimpleParticleType(true));

    public static void init(IEventBus bus) {
        PARTICLES.register(bus);
    }

}
