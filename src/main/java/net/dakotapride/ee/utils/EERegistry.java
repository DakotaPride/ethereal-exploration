package net.dakotapride.ee.utils;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.registry.EEBlocks;
import net.dakotapride.ee.registry.EEItems;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class EERegistry {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EtherealExploration.MOD_ID);
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EtherealExploration.MOD_ID);
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EtherealExploration.MOD_ID);
    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EtherealExploration.MOD_ID);
    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EtherealExploration.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EtherealExploration.MOD_ID);

    public static <T extends Block> RegistryObject<T> block(String name, Supplier<T> block) {
        RegistryObject<T> value = BLOCKS.register(name, block);
        blockItem(name, value);
        return value;
    }

    public static <T extends Block> RegistryObject<Item> blockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // public static final RegistryObject<CreativeModeTab> TESTING_TAB = CREATIVE_MODE_TABS.register("testing_tab",
    //            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EEItems.TESTING_ITEM.get()))
    //                    .title(Component.translatable("ee.tab.testing"))
    //                    .displayItems(new DisplayItems())
    //                    .build());

    public static class DisplayItems implements CreativeModeTab.DisplayItemsGenerator {

        @Override
        public void accept(CreativeModeTab.@NotNull ItemDisplayParameters parameters, CreativeModeTab.@NotNull Output output) {
            // output.accept(EEItems.TESTING_ITEM.get().getDefaultInstance());
            // output.accept(EEBlocks.TESTING_BLOCK.get().asItem().getDefaultInstance());
        }
    }

}
