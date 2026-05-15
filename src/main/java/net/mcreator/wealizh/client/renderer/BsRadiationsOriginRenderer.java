package net.mcreator.wealizh.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.wealizh.entity.BsRadiationsOriginEntity;
import net.mcreator.wealizh.client.model.ModelCustomModel;

public class BsRadiationsOriginRenderer extends MobRenderer<BsRadiationsOriginEntity, LivingEntityRenderState, ModelCustomModel> {
	private BsRadiationsOriginEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("wealizh:textures/entities/kong_.png");

	public BsRadiationsOriginRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelCustomModel(context.bakeLayer(ModelCustomModel.LAYER_LOCATION)), 0f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(BsRadiationsOriginEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}