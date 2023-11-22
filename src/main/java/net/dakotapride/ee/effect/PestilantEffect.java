package net.dakotapride.ee.effect;

import net.dakotapride.ee.registry.EEDamageSources;
import net.dakotapride.ee.registry.EEEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class PestilantEffect extends MobEffect {
    public PestilantEffect() {
        super(MobEffectCategory.HARMFUL, 0x6BFF72);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int k) {
        if (this == EEEffects.PESTILENT.get()) {
            entity.hurt(entity.damageSources().source(EEDamageSources.TOXIN), 4.0F);
        }

        super.applyEffectTick(entity, k);
    }

    @Override
    public boolean isDurationEffectTick(int k, int l) {
        if (this == EEEffects.PESTILENT.get()) {
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
