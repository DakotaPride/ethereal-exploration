package net.dakotapride.ee.effect;

import net.dakotapride.ee.registry.EEDamageSources;
import net.dakotapride.ee.registry.EEEffects;
import net.dakotapride.ee.registry.EEItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ToxinEffect extends MobEffect {
    public ToxinEffect() {
        super(MobEffectCategory.HARMFUL, 0x6BFF72);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int k) {
        if (this == EEEffects.TOXIN.get()) {
            entity.hurt(entity.damageSources().source(EEDamageSources.TOXIN), 4.0F);
        }

        super.applyEffectTick(entity, k);
    }

    @Override
    public boolean isDurationEffectTick(int k, int l) {
        if (this == EEEffects.TOXIN.get()) {
            int j = 25 >> l;
            if (j > 0) {
                return k % j == 0;
            } else {
                return true;
            }
        }

        return super.isDurationEffectTick(k, l);
    }
}
