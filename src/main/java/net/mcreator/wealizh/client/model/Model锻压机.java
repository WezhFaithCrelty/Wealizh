package net.mcreator.wealizh.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Model锻压机 extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("wealizh", "model_duan_ya_ji"), "main");
	public final ModelPart kuang_jia;
	public final ModelPart duan_ya_bang;

	public Model锻压机(ModelPart root) {
		super(root);
		this.kuang_jia = root.getChild("kuang_jia");
		this.duan_ya_bang = root.getChild("duan_ya_bang");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition kuang_jia = partdefinition.addOrReplaceChild("kuang_jia",
				CubeListBuilder.create().texOffs(26, 14).addBox(-7.0F, -2.0F, -7.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(26, 30).addBox(6.0F, -2.0F, -7.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(16, 46)
						.addBox(-6.0F, -1.0F, 4.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-6.0F, -15.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(48, 0)
						.addBox(-7.0F, -14.0F, 3.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 49).addBox(6.0F, -14.0F, 3.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition cube_r1 = kuang_jia.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-7.0F, -14.0F, -6.0F, 1.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition duan_ya_bang = partdefinition.addOrReplaceChild("duan_ya_bang",
				CubeListBuilder.create().texOffs(44, 46).addBox(-3.0F, -13.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 40).addBox(-2.0F, -27.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

	}

}