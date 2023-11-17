package net.dakotapride.ee.entity.model;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.OutburstEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@SuppressWarnings("unused")
public class OutburstModel extends GeoModel<OutburstEntity> {

	@Override
	public ResourceLocation getModelResource(OutburstEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "geo/outburst.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(OutburstEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/outburst.png");
	}

	@Override
	public ResourceLocation getAnimationResource(OutburstEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "animations/outburst.animation.json");
	}

	@Override
	public void setCustomAnimations(OutburstEntity animatable, long instanceId, AnimationState<OutburstEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}