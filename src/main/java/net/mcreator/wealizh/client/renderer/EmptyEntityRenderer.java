package net.mcreator.wealizh.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.wealizh.entity.EmptyEntityEntity;
import net.mcreator.wealizh.client.model.ModelCustomModel;

public class EmptyEntityRenderer extends MobRenderer<EmptyEntityEntity, LivingEntityRenderState, ModelCustomModel> {
	private EmptyEntityEntity entity = null;
	private final ResourceLocation entityTexture = ResourceLocation.parse("wealizh:textures/entities/kong_.png");

	public EmptyEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelCustomModel(context.bakeLayer(ModelCustomModel.LAYER_LOCATION)), 1f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(EmptyEntityEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}