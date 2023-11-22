package net.dakotapride.ee.fluid.type;

import net.dakotapride.ee.registry.EEFluids;
import org.joml.Vector3f;

public class SludgeFluidType extends BaseFluidType {
    public SludgeFluidType(Properties properties) {
        super(EEFluids.Types.SLUDGE_STILL_LOCATION, EEFluids.Types.SLUDGE_FLOWING_LOCATION, EEFluids.Types.SLUDGE_OVERLAY_LOCATION,
                0xA055CD6D, new Vector3f(107F / 255F, 1.0F,114F / 255F), properties);
    }
}
