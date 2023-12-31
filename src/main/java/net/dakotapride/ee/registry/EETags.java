package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("unused")
public class EETags {
    public static TagKey<Block> CAN_DROP_EXPERIENCE_ORES = tag("can_drop_experience/ores", Registries.BLOCK);
    public static TagKey<Block> IS_SAFE_TO_SPAWN_PLAYER_ON = tag("spawnable/is_safe/player", Registries.BLOCK);
    public static TagKey<EntityType<?>> IMMUNE_TO_TOXIN_DAMAGE = tag("immune/toxin", Registries.ENTITY_TYPE);
    public static TagKey<EntityType<?>> IMMUNE_TO_ACIDIC_DAMAGE = tag("immune/acidic", Registries.ENTITY_TYPE);
    public static TagKey<EntityType<?>> IMMUNE_TO_DRYING_OUT = tag("immune/drying_out", Registries.ENTITY_TYPE);
    public static TagKey<Fluid> SLUDGE_FLUID = tag("sludge", Registries.FLUID);


    private static <F> TagKey<F> tag(String name, ResourceKey<Registry<F>> key) {
        return TagKey.create(key, new ResourceLocation(EtherealExploration.MOD_ID, name));
    }

    private static <F> TagKey<F> forgeTag(String name, ResourceKey<Registry<F>> key) {
        return TagKey.create(key, new ResourceLocation("forge", name));
    }

    public static void init() {}
}
