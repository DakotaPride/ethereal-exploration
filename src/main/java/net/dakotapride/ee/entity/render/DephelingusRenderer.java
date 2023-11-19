package net.dakotapride.ee.entity.render;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.AquadineEntity;
import net.dakotapride.ee.entity.DephelingusEntity;
import net.dakotapride.ee.entity.model.AquadineModel;
import net.dakotapride.ee.entity.model.DephelingusModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@SuppressWarnings("unused")
public class DephelingusRenderer extends GeoEntityRenderer<DephelingusEntity> {
    public DephelingusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DephelingusModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DephelingusEntity animatable) {
        return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/dephelingus.png");
    }
}