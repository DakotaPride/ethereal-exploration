package net.dakotapride.ee.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public enum EEFoods {
    RAW_AQUADINE(new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.2F)
            .effect(() -> new MobEffectInstance(EEEffects.TOXIN.get(), 200, 0), 0.9F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 120, 0), 0.4F).build()),
    COOKED_AQUADINE(new FoodProperties.Builder().build()),
    RAW_DEPHELINGUS(new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.2F)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 240, 0), 0.85F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 120, 0), 0.4F).build()),
    COOKED_DEPHELINGUS(new FoodProperties.Builder().build());

    final FoodProperties properties;

    EEFoods(FoodProperties properties) {
        this.properties = properties;
    }
}
