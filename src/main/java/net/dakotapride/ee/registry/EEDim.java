package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

@SuppressWarnings("unused")
public class EEDim {
    public static final ResourceKey<Level> CRYSTALLINE = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(EtherealExploration.MOD_ID, "crystalline"));
    public static final ResourceKey<DimensionType> CRYSTALLINE_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, CRYSTALLINE.registry());

    public static void init() {}
}
