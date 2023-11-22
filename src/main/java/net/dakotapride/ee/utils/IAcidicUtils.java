package net.dakotapride.ee.utils;


import net.dakotapride.ee.registry.EEFluids;
import net.minecraft.world.entity.Entity;

public interface IAcidicUtils {

    default boolean isInAcidicFluid(Entity entity) {
        return entity.isInFluidType(EEFluids.Types.SLUDGE_FLUID_TYPE.get());
    }

}
