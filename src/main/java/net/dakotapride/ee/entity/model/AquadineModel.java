package net.dakotapride.ee.entity.model;

import net.dakotapride.ee.EtherealExploration;
import net.dakotapride.ee.entity.AquadineEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@SuppressWarnings("unused")
public class AquadineModel extends GeoModel<AquadineEntity> {

	@Override
	public ResourceLocation getModelResource(AquadineEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "geo/aquadine.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AquadineEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "textures/entity/aquadine.png");
	}

	@Override
	public ResourceLocation getAnimationResource(AquadineEntity animatable) {
		return new ResourceLocation(EtherealExploration.MOD_ID, "animations/aquadine.animation.json");
	}

	@Override
	public void setCustomAnimations(AquadineEntity animatable, long instanceId, AnimationState<AquadineEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}