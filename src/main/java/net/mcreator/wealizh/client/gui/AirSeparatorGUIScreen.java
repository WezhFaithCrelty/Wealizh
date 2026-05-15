package net.mcreator.wealizh.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.wealizh.world.inventory.AirSeparatorGUIMenu;
import net.mcreator.wealizh.procedures.*;
import net.mcreator.wealizh.network.AirSeparatorGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class AirSeparatorGUIScreen extends AbstractContainerScreen<AirSeparatorGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_zhuan_huan_an_niu;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/air_separator_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_-xia_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/dian_chi_.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/tong_dao_xian_.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("wealizh:textures/screens/kong_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("wealizh:textures/screens/man_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("wealizh:textures/screens/jian_duan_dang_ban_.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("wealizh:textures/screens/jian_duan_dang_ban__fan_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/ye_guan_.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/lian_jie_kou__ce_.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/you_xia_guai_jiao_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/dian_liang_zhi_.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_jin_du_tiao_.png");
	private static final ResourceLocation SPRITE_5 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_ji_huo_.png");

	public AirSeparatorGUIScreen(AirSeparatorGUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 35 && mouseX < leftPos + 59 && mouseY > topPos + 21 && mouseY < topPos + 45) {
			String hoverText = GUITTextEReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 99 && mouseX < leftPos + 123 && mouseY > topPos + 19 && mouseY < topPos + 43) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 99 && mouseX < leftPos + 123 && mouseY > topPos + 43 && mouseY < topPos + 67) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 124, this.topPos + 36, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 39, this.topPos + 53, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 40, this.topPos + 36, 0, 0, 14, 16, 14, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 124, this.topPos + 18, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 124, this.topPos + 54, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 20, this.topPos + 9, 0, 0, 15, 64, 15, 64);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 140, this.topPos + 9, 0, 0, 15, 64, 15, 64);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 102, this.topPos + 16, 0, Mth.clamp((int) GUILiquidBarIndexReturnProcedure.execute(world, x, y, z) * 54, 0, 2808), 18, 54, 18, 2862);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 119, this.topPos + 23, 0, 0, 2, 4, 2, 4);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 84, this.topPos + 34, 0, 0, 19, 19, 19, 19);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 38, this.topPos + 16, 0, Mth.clamp((int) GUIEBarReturnProcedure.execute(world, x, y, z) * 34, 0, 1088), 18, 34, 18, 1122);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_4, this.leftPos + 78, this.topPos + 17, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 17, 0, 374), 22, 17, 22, 391);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_5, this.leftPos + 58, this.topPos + 52, 0, Mth.clamp((int) GUIRedstoneActivateIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 18), 18, 18, 18, 36);
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
		imagebutton_zhuan_huan_an_niu = new ImageButton(this.leftPos + 58, this.topPos + 34, 18, 18,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/zhuan_huan_an_niu_.png"), ResourceLocation.parse("wealizh:textures/screens/zhuan_huan_an_niu_.png")), e -> {
					int x = AirSeparatorGUIScreen.this.x;
					int y = AirSeparatorGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new AirSeparatorGUIButtonMessage(0, x, y, z));
						AirSeparatorGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_zhuan_huan_an_niu);
	}
}