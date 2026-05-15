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

import net.mcreator.wealizh.world.inventory.KilnGUIMenu;
import net.mcreator.wealizh.procedures.KilnIsBurnBoolReturnProcedure;
import net.mcreator.wealizh.procedures.GUIProgressBarIndexReturnProcedure;
import net.mcreator.wealizh.init.WealizhModScreens;

public class KilnGUIScreen extends AbstractContainerScreen<KilnGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/kiln_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/cao_lian_jie_tiao_kou_-shang_.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("wealizh:textures/screens/cao_lian_jie_tiao_kou_-shang_.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("wealizh:textures/screens/cao_lian_jie_tiao_kou_-shang_.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("wealizh:textures/screens/cao_lian_jie_tiao_kou_-xia_.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("wealizh:textures/screens/cao_lian_jie_tiao_kou_-xia_.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("wealizh:textures/screens/cao_lian_jie_tiao_kou_-xia_.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("wealizh:textures/screens/cao_gai_kuai_.png");
	private static final ResourceLocation IMAGE_9 = ResourceLocation.parse("wealizh:textures/screens/wei_jing_gao_ti_shi_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/chang_jian_tou_jin_du_tiao_.png");

	public KilnGUIScreen(KilnGUIMenu container, Inventory inventory, Component text) {
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
		if (KilnIsBurnBoolReturnProcedure.execute(world, x, y, z))
			if (mouseX > leftPos + -21 && mouseX < leftPos + 3 && mouseY > topPos + -1 && mouseY < topPos + 23) {
				guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.wealizh.kiln_gui.tooltip_not_exposed_to_combustion"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 21, this.topPos + 31, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 129, this.topPos + 31, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 62, this.topPos + 47, 0, 0, 6, 5, 6, 5);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 80, this.topPos + 47, 0, 0, 6, 5, 6, 5);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 98, this.topPos + 47, 0, 0, 6, 5, 6, 5);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 62, this.topPos + 37, 0, 0, 6, 5, 6, 5);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 80, this.topPos + 37, 0, 0, 6, 5, 6, 5);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 98, this.topPos + 37, 0, 0, 6, 5, 6, 5);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + 25, this.topPos + 59, 0, 0, 18, 18, 18, 18);
		if (KilnIsBurnBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, this.leftPos + -18, this.topPos + 2, 0, 0, 18, 18, 18, 18);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 56, this.topPos + 38, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 14, 0, 896), 64, 14, 64, 910);
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