package net.mcreator.wealizh.client.renderer.block;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.AnimationDefinition;

import net.mcreator.wealizh.procedures.HandCrankedGeneratorHuiFangTiaoJianProcedure;
import net.mcreator.wealizh.init.WealizhModBlockEntities;
import net.mcreator.wealizh.client.model.animations.手摇发电机_Converted_ConvertedAnimation;
import net.mcreator.wealizh.client.model.Model手摇发电机_Converted_Converted;
import net.mcreator.wealizh.block.entity.HandCrankedGeneratorBlockEntity;
import net.mcreator.wealizh.block.HandCrankedGeneratorBlock;

import java.util.Map;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

@EventBusSubscriber(Dist.CLIENT)
public class HandCrankedGeneratorRenderer implements BlockEntityRenderer<HandCrankedGeneratorBlockEntity> {
	private final CustomHierarchicalModel model;
	private final ResourceLocation texture;
	private final LivingEntityRenderState renderState;

	HandCrankedGeneratorRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new CustomHierarchicalModel(context.bakeLayer(Model手摇发电机_Converted_Converted.LAYER_LOCATION));
		this.texture = ResourceLocation.parse("wealizh:textures/block/shou_yao_fa_dian_ji_.png");
		this.renderState = new LivingEntityRenderState();
	}

	private void updateRenderState(HandCrankedGeneratorBlockEntity blockEntity, float partialTick) {
		int tickCount = (int) blockEntity.getLevel().getGameTime();
		renderState.ageInTicks = tickCount + partialTick;
		blockEntity.animationState0.animateWhen(HandCrankedGeneratorHuiFangTiaoJianProcedure.execute(blockEntity.getBlockState()), tickCount);
	}

	@Override
	public void render(HandCrankedGeneratorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource renderer, int light, int overlayLight, Vec3 cameraPos) {
		updateRenderState(blockEntity, partialTick);
		poseStack.pushPose();
		poseStack.scale(-1, -1, 1);
		poseStack.translate(-0.5, -0.5, 0.5);
		BlockState state = blockEntity.getBlockState();
		Direction facing = state.getValue(HandCrankedGeneratorBlock.FACING);
		switch (facing) {
			case NORTH -> {
			}
			case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(90));
			case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90));
			case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));
			case UP -> poseStack.mulPose(Axis.XN.rotationDegrees(90));
			case DOWN -> poseStack.mulPose(Axis.XN.rotationDegrees(-90));
		}
		poseStack.translate(0, -1, 0);
		VertexConsumer builder = renderer.getBuffer(RenderType.entityCutout(texture));
		model.setupBlockEntityAnim(blockEntity, renderState);
		model.renderToBuffer(poseStack, builder, light, overlayLight);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(WealizhModBlockEntities.HAND_CRANKED_GENERATOR.get(), HandCrankedGeneratorRenderer::new);
	}

	private static final class CustomHierarchicalModel extends Model手摇发电机_Converted_Converted {
		private final KeyframeAnimation keyframeAnimation0;

		public CustomHierarchicalModel(ModelPart root) {
			super(root);
			this.keyframeAnimation0 = safeBake(手摇发电机_Converted_ConvertedAnimation.animation);
		}

		private KeyframeAnimation safeBake(AnimationDefinition source) {
			try {
				return source.bake(root);
			} catch (IllegalArgumentException e) {
				return new AnimationDefinition(0, false, Map.of()).bake(root);
			}
		}

		public void setupBlockEntityAnim(HandCrankedGeneratorBlockEntity blockEntity, LivingEntityRenderState state) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
			this.keyframeAnimation0.apply(blockEntity.animationState0, state.ageInTicks, 1f);
			super.setupAnim(state);
		}
	}
}