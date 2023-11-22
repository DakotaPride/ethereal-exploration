package net.dakotapride.ee.entity.render;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.AquadineEntity;
import net.dakotapride.ee.entity.model.AquadineModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@SuppressWarnings("unused")
public class AquadineRenderer extends GeoEntityRenderer<AquadineEntity> {
    public AquadineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AquadineModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AquadineEntity entity) {
        return entity.hasCustomName() &&

                // Gobber2 Easter Egg Goober
                "goober".equalsIgnoreCase(entity.getName().getString()) ?
                new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/goober.png")

                // Dejojo Animal Crossing Stream Easter Egg ORNG
                : "orng".equalsIgnoreCase(entity.getName().getString()) ?
                new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/orng.png")

                : new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/aquadine.png");
    }
}