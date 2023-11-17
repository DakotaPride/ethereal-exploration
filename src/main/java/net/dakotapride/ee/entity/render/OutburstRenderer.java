package net.dakotapride.ee.entity.render;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.OutburstEntity;
import net.dakotapride.ee.entity.model.OutburstModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@SuppressWarnings("unused")
public class OutburstRenderer extends GeoEntityRenderer<OutburstEntity> {
    public OutburstRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OutburstModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull OutburstEntity animatable) {
        return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/outburst.png");
    }
}