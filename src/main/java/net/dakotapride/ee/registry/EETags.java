package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class EETags {
    public static TagKey<Block> CAN_DROP_EXPERIENCE_ORES = tag("can_drop_experience/ores", Registries.BLOCK);
    public static TagKey<EntityType<?>> IMMUNE_TO_TOXIN = tag("immune/toxin", Registries.ENTITY_TYPE);


    private static <F> TagKey<F> tag(String name, ResourceKey<Registry<F>> key) {
        return TagKey.create(key, new ResourceLocation(EtherealExploration.MOD_ID, name));
    }

    private static <F> TagKey<F> forgeTag(String name, ResourceKey<Registry<F>> key) {
        return TagKey.create(key, new ResourceLocation("forge", name));
    }

    public static void init() {}
}
