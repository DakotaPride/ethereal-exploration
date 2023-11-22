package net.dakotapride.ee.registry;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.fluid.type.SludgeFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class EEFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, EtherealExploration.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_SLUDGE = FLUIDS.register("source_sludge",
            () -> new ForgeFlowingFluid.Source(EEFluids.SLUDGE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SLUDGE = FLUIDS.register("flowing_sludge",
            () -> new ForgeFlowingFluid.Flowing(EEFluids.SLUDGE_PROPERTIES));


    public static final ForgeFlowingFluid.Properties SLUDGE_PROPERTIES = new ForgeFlowingFluid.Properties(
            Types.SLUDGE_FLUID_TYPE, SOURCE_SLUDGE, FLOWING_SLUDGE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(EEBlocks.SLUDGE_FLUID)
            .bucket(EEItems.BUCKET_OF_SLUDGE);


    public static void init(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    @SuppressWarnings("unused")
    public static class Types {
        public static final ResourceLocation WATER_FLOWING_LOCATION = new ResourceLocation("block/water_flow");
        public static final ResourceLocation WATER_STILL_LOCATION = new ResourceLocation("block/water_still");
        public static final ResourceLocation SLUDGE_STILL_LOCATION = new ResourceLocation(EtherealExploration.MOD_ID, "block/sludge_still");
        public static final ResourceLocation SLUDGE_FLOWING_LOCATION = new ResourceLocation(EtherealExploration.MOD_ID, "block/sludge_flowing");
        public static final ResourceLocation SLUDGE_OVERLAY_LOCATION = new ResourceLocation(EtherealExploration.MOD_ID, "overlay/sludge");

        public static final DeferredRegister<FluidType> FLUID_TYPES =
                DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, EtherealExploration.MOD_ID);

        public static final RegistryObject<FluidType> SLUDGE_FLUID_TYPE = FLUID_TYPES.register("sludge",
                () -> new SludgeFluidType(FluidType.Properties.create().lightLevel(2).density(15).viscosity(5)));

        public static void init(IEventBus bus) {
            FLUID_TYPES.register(bus);
        }
    }

}
