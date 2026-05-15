package net.mcreator.wealizh.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.wealizh.world.inventory.RapidQualitativeChangeMachineGUIMenu;
import net.mcreator.wealizh.procedures.SubstanceNucleusLedNumReturnProcedure;
import net.mcreator.wealizh.procedures.GUIVibrationAmplitudeBarIndexReturnProcedure;
import net.mcreator.wealizh.procedures.GUIValueNumBarIndexReturnProcedure;
import net.mcreator.wealizh.procedures.GUIProgressBarIndexReturnProcedure;
import net.mcreator.wealizh.init.WealizhModScreens;

public class RapidQualitativeChangeMachineGUIScreen extends AbstractContainerScreen<RapidQualitativeChangeMachineGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/rapid_qualitative_change_machine_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/ding_cui_fa_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/jian_duan_dang_ban_.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/he_cao_.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/he_bing_jin_du_tiao_.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/zhi_liang_tiao_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/dou_liang_tiao_.png");

	public RapidQualitativeChangeMachineGUIScreen(RapidQualitativeChangeMachineGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 48, this.topPos + 34, 0, 0, 16, 20, 16, 20);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 30, this.topPos + 12, 0, 0, 15, 64, 15, 64);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 118, this.topPos + 33, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 47, this.topPos + 15, 0, Mth.clamp((int) SubstanceNucleusLedNumReturnProcedure.execute(world, x, y, z) * 18, 0, 72), 18, 18, 18, 90);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 67, this.topPos + 28, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 37, 0, 1776), 48, 37, 48, 1813);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 90, this.topPos + 68, 0, Mth.clamp((int) GUIValueNumBarIndexReturnProcedure.execute(world, x, y, z) * 5, 0, 170), 36, 5, 36, 175);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 90, this.topPos + 18, 0, Mth.clamp((int) GUIVibrationAmplitudeBarIndexReturnProcedure.execute(world, x, y, z) * 5, 0, 170), 36, 5, 36, 175);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
	}
}