package net.dakotapride.ee.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.FumeGasEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class FumeGasRenderer extends EntityRenderer<FumeGasEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/projectile/fume_gas.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);
    public FumeGasRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected int getBlockLightLevel(@NotNull FumeGasEntity entity, @NotNull BlockPos pos) {
        return 0;
    }

    @Override
    public void render(@NotNull FumeGasEntity entity, float j, float k, PoseStack poseStack, MultiBufferSource multiBufferSource, int v) {
        poseStack.pushPose();
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = poseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
        vertex(vertexconsumer, matrix4f, matrix3f, v, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, v, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, v, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, v, 0.0F, 1, 0, 0);
        poseStack.popPose();
        super.render(entity, j, k, poseStack, multiBufferSource, v);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, int g, float v, int j, int k, int l) {
        vertexConsumer.vertex(matrix4f, v - 0.5F, (float)j - 0.25F, 0.0F).color(255, 255, 255,
                255).uv((float)k, (float)l).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(g)
                .normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FumeGasEntity entity) {
        return TEXTURE_LOCATION;
    }
}
