package net.dakotapride.ee.entity.model;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.DeviantEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@SuppressWarnings("unused")
public class DeviantModel extends GeoModel<DeviantEntity> {

	@Override
	public ResourceLocation getModelResource(DeviantEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "geo/deviant.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DeviantEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/deviant.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DeviantEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "animations/deviant.animation.json");
	}

	@Override
	public void setCustomAnimations(DeviantEntity animatable, long instanceId, AnimationState<DeviantEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}