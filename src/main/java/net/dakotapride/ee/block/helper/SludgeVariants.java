package net.dakotapride.ee.block.helper;

import net.dakotapride.ee.registry.EEParticles;
import net.minecraft.core.particles.SimpleParticleType;

public enum SludgeVariants {
    NORMAL(EEParticles.FUMES.get()),
    CRYSTALLINE_INFUSED(),
    LIMPID();

    SimpleParticleType particleType;

    SludgeVariants(SimpleParticleType particle) {
        this.particleType = particle;
    }

    SludgeVariants() {}

    public SimpleParticleType getParticles() {
        return particleType;
    }

    public void setParticles(SimpleParticleType particleType) {
        this.particleType = particleType;
    }
}
