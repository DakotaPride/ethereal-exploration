package net.dakotapride.ee.entity.model;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.FumeEntity;
import net.dakotapride.ee.entity.OutburstEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@SuppressWarnings("unused")
public class FumeModel extends GeoModel<FumeEntity> {

	@Override
	public ResourceLocation getModelResource(FumeEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "geo/fume.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(FumeEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/fume.png");
	}

	@Override
	public ResourceLocation getAnimationResource(FumeEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "animations/fume.animation.json");
	}

	@Override
	public void setCustomAnimations(FumeEntity animatable, long instanceId, AnimationState<FumeEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}