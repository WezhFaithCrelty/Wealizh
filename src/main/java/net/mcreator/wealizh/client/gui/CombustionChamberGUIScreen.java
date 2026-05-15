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

import net.mcreator.wealizh.world.inventory.CombustionChamberGUIMenu;
import net.mcreator.wealizh.procedures.GUIPowerHunNumReturnProcedure;
import net.mcreator.wealizh.procedures.GUIPowerBarIndexReturnProcedure;
import net.mcreator.wealizh.procedures.GUIBurningBarIndexReturnProcedure;
import net.mcreator.wealizh.procedures.CombustionChamberWaterUnderBoolReturnProcedure;
import net.mcreator.wealizh.procedures.CombustionChamberIsRainyBoolReturnProcedure;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class CombustionChamberGUIScreen extends AbstractContainerScreen<CombustionChamberGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/combustion_chamber_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/wei_jing_gao_ti_shi_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/jing_gao_ti_shi_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/hui_hei_gui.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/tu_kuang__64x64.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/ran_shao_zhi_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/gong_lu_zhi_-yuan_.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation SPRITE_5 = ResourceLocation.parse("wealizh:textures/screens/wang_ge_cao_.png");
	private static final ResourceLocation SPRITE_6 = ResourceLocation.parse("wealizh:textures/screens/ran_liao_ping_.png");

	public CombustionChamberGUIScreen(CombustionChamberGUIMenu container, Inventory inventory, Component text) {
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
		boolean customTooltipShown = false;
		if (CombustionChamberIsRainyBoolReturnProcedure.execute(world, x, y, z))
			if (mouseX > leftPos + -21 && mouseX < leftPos + 3 && mouseY > topPos + 0 && mouseY < topPos + 24) {
				guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.wealizh.combustion_chamber_gui.tooltip_this_machine_will_have_a_reduced"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (CombustionChamberWaterUnderBoolReturnProcedure.execute(world, x, y, z))
			if (mouseX > leftPos + -21 && mouseX < leftPos + 3 && mouseY > topPos + 18 && mouseY < topPos + 42) {
				guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.wealizh.combustion_chamber_gui.tooltip_the_burner_at_the_top_of_this_ma"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (mouseX > leftPos + 91 && mouseX < leftPos + 115 && mouseY > topPos + 22 && mouseY < topPos + 46) {
			String hoverText = GUIPowerHunNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		if (CombustionChamberIsRainyBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + -18, this.topPos + 3, 0, 0, 18, 18, 18, 18);
		}
		if (CombustionChamberWaterUnderBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + -18, this.topPos + 21, 0, 0, 18, 18, 18, 18);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 56, this.topPos + 11, 0, 0, 64, 64, 64, 64);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 68, this.topPos + 53, 0, Mth.clamp((int) GUIBurningBarIndexReturnProcedure.execute(world, x, y, z) * 14, 0, 182), 14, 14, 14, 196);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 94, this.topPos + 25, 0, Mth.clamp((int) GUIPowerBarIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 216), 18, 18, 18, 234);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_4, this.leftPos + 63, this.topPos + 21, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_5, this.leftPos + 94, this.topPos + 51, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_6, this.leftPos + 95, this.topPos + 52, 0, 0, 16, 16, 16, 16);
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