package net.dakotapride.ee.entity.render;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.FumeEntity;
import net.dakotapride.ee.entity.OutburstEntity;
import net.dakotapride.ee.entity.model.FumeModel;
import net.dakotapride.ee.entity.model.OutburstModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@SuppressWarnings("unused")
public class FumeRenderer extends GeoEntityRenderer<FumeEntity> {
    public FumeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FumeModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FumeEntity animatable) {
        return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/fume.png");
    }
}