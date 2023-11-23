package net.dakotapride.ee.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.dakotapride.ee.entity.NonDestructivePrimedTntEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class NonDescructivePrimedTntRenderer extends EntityRenderer<NonDestructivePrimedTntEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public NonDescructivePrimedTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(NonDestructivePrimedTntEntity entity, float l, float m, PoseStack pose, @NotNull MultiBufferSource source, int n) {
        pose.pushPose();
        pose.translate(0.0F, 0.5F, 0.0F);
        int i = entity.getFuse();
        if ((float)i - m + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - m + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            pose.scale(f1, f1, f1);
        }

        pose.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pose.translate(-0.5F, -0.5F, 0.5F);
        pose.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, Blocks.TNT.defaultBlockState(), pose, source, n, i / 5 % 2 == 0);
        pose.popPose();
        super.render(entity, l, m, pose, source, n);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull NonDestructivePrimedTntEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}
