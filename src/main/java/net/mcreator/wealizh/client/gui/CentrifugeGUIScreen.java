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

import net.mcreator.wealizh.world.inventory.CentrifugeGUIMenu;
import net.mcreator.wealizh.procedures.*;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class CentrifugeGUIScreen extends AbstractContainerScreen<CentrifugeGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/centrifuge_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_-xia_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/man_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/kong_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("wealizh:textures/screens/wu_pin_.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("wealizh:textures/screens/fu_chan_pin_.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("wealizh:textures/screens/kong_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("wealizh:textures/screens/man_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("wealizh:textures/screens/chi_xin_ji_zhuan_guan__3d.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("wealizh:textures/screens/dian_chi_.png");
	private static final ResourceLocation IMAGE_9 = ResourceLocation.parse("wealizh:textures/screens/kong_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_10 = ResourceLocation.parse("wealizh:textures/screens/man_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_11 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou_.png");
	private static final ResourceLocation IMAGE_12 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/ye_guan_.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/ye_guan_.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/dian_liang_zhi_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/chang_jian_tou_jin_du_tiao_.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("wealizh:textures/screens/chang_jian_tou_jin_du_tiao_.png");
	private static final ResourceLocation SPRITE_5 = ResourceLocation.parse("wealizh:textures/screens/chang_jian_tou_jin_du_tiao_.png");
	private static final ResourceLocation SPRITE_6 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou_.png");
	private static final ResourceLocation SPRITE_7 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou_.png");
	private static final ResourceLocation SPRITE_8 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_ji_huo_.png");
	private static final ResourceLocation SPRITE_9 = ResourceLocation.parse("wealizh:textures/screens/you_xia_guai_jiao_.png");
	private static final ResourceLocation SPRITE_10 = ResourceLocation.parse("wealizh:textures/screens/ye_guan_.png");
	private static final ResourceLocation SPRITE_11 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou__ce_.png");
	private static final ResourceLocation SPRITE_12 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou_.png");
	private static final ResourceLocation SPRITE_13 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou__ce_.png");
	private static final ResourceLocation SPRITE_14 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou__ce_.png");
	private static final ResourceLocation SPRITE_15 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou_.png");

	public CentrifugeGUIScreen(CentrifugeGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 182;
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
		if (mouseX > leftPos + 170 && mouseX < leftPos + 194 && mouseY > topPos + 40 && mouseY < topPos + 64) {
			String hoverText = GUITTextEReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 170 && mouseX < leftPos + 194 && mouseY > topPos + 12 && mouseY < topPos + 36) {
			String hoverText = GUITTextAPNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 23 && mouseX < leftPos + 47 && mouseY > topPos + 42 && mouseY < topPos + 66) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 23 && mouseX < leftPos + 47 && mouseY > topPos + 18 && mouseY < topPos + 42) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 109 && mouseX < leftPos + 133 && mouseY > topPos + 42 && mouseY < topPos + 66) {
			String hoverText = GUITTextLiquid2NumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 109 && mouseX < leftPos + 133 && mouseY > topPos + 18 && mouseY < topPos + 42) {
			String hoverText = GUITTextLiquid2NumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 145 && mouseX < leftPos + 169 && mouseY > topPos + 42 && mouseY < topPos + 66) {
			String hoverText = GUITTextLiquid3NumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 145 && mouseX < leftPos + 169 && mouseY > topPos + 18 && mouseY < topPos + 42) {
			String hoverText = GUITTextLiquid3NumReturnProcedure.execute(world, x, y, z);
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 12, this.topPos + 53, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 12, this.topPos + 34, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 12, this.topPos + 71, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 76, this.topPos + 70, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 76, this.topPos + 16, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 134, this.topPos + 17, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 134, this.topPos + 35, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 174, this.topPos + 16, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + 174, this.topPos + 70, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, this.leftPos + 134, this.topPos + 53, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, this.leftPos + 134, this.topPos + 71, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11, this.leftPos + 137, this.topPos + 32, 0, 0, 4, 2, 4, 2);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_12, this.leftPos + 137, this.topPos + 68, 0, 0, 4, 2, 4, 2);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 26, this.topPos + 15, 0, Mth.clamp((int) GUILiquidBarIndexReturnProcedure.execute(world, x, y, z) * 54, 0, 2808), 18, 54, 18, 2862);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 112, this.topPos + 15, 0, Mth.clamp((int) GUILiquid2BarIndexReturnProcedure.execute(world, x, y, z) * 54, 0, 2808), 18, 54, 18, 2862);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 173, this.topPos + 35, 0, Mth.clamp((int) GUIEBarReturnProcedure.execute(world, x, y, z) * 34, 0, 1088), 18, 34, 18, 1122);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 46, this.topPos + 34, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 14, 0, 896), 64, 14, 64, 910);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_4, this.leftPos + 46, this.topPos + 44, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 14, 0, 896), 64, 14, 64, 910);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_5, this.leftPos + 46, this.topPos + 54, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 14, 0, 896), 64, 14, 64, 910);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_6, this.leftPos + 33, this.topPos + 68, 0, 0, 4, 2, 4, 2);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_7, this.leftPos + 120, this.topPos + 68, 0, 0, 4, 2, 4, 2);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_8, this.leftPos + 44, this.topPos + 15, 0, Mth.clamp((int) GUIRedstoneActivateIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 18), 18, 18, 18, 36);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_9, this.leftPos + 8, this.topPos + 14, 0, 0, 19, 19, 19, 19);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_10, this.leftPos + 148, this.topPos + 15, 0, Mth.clamp((int) GUILiquid3BarIndexReturnProcedure.execute(world, x, y, z) * 54, 0, 2808), 18, 54, 18, 2862);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_11, this.leftPos + 129, this.topPos + 22, 0, 0, 2, 4, 2, 4);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_12, this.leftPos + 155, this.topPos + 68, 0, 0, 4, 2, 4, 2);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_13, this.leftPos + 147, this.topPos + 58, 0, 0, 2, 4, 2, 4);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_14, this.leftPos + 129, this.topPos + 22, 0, 0, 2, 4, 2, 4);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_15, this.leftPos + 180, this.topPos + 68, 0, 0, 4, 2, 4, 2);
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