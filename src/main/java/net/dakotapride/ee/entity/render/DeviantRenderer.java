package net.dakotapride.ee.entity.render;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.DeviantEntity;
import net.dakotapride.ee.entity.model.DeviantModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@SuppressWarnings("unused")
public class DeviantRenderer extends GeoEntityRenderer<DeviantEntity> {
    public DeviantRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DeviantModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DeviantEntity animatable) {
        return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/deviant.png");
    }
}