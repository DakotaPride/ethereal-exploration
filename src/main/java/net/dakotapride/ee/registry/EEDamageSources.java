package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

@SuppressWarnings("unused")
public class EEDamageSources {
    public static final ResourceKey<DamageType> FUME_GAS = create("fume_gas");
    public static final ResourceKey<DamageType> TOXIN = create("toxin");
    public static final ResourceKey<DamageType> ACID = create("acid");

    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EtherealExploration.MOD_ID, name));
    }
}
