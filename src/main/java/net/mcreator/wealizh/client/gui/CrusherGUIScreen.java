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

import net.mcreator.wealizh.world.inventory.CrusherGUIMenu;
import net.mcreator.wealizh.procedures.*;
import net.mcreator.wealizh.network.CrusherGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class CrusherGUIScreen extends AbstractContainerScreen<CrusherGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_tong_dao_kai_guan;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/crusher_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/lou_dou__da__you__tiao_lian_jie_.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("wealizh:textures/screens/lou_dou_xing_dang_ban_.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_dao_pian_cao_.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_dao_pian_cao_.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_dao_pian_cao_.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_dao_pian_cao_.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_dao_pian_cao_.png");
	private static final ResourceLocation IMAGE_9 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_dao_pian_cao_.png");
	private static final ResourceLocation IMAGE_10 = ResourceLocation.parse("wealizh:textures/screens/tong_dao_xian_.png");
	private static final ResourceLocation IMAGE_11 = ResourceLocation.parse("wealizh:textures/screens/dian_chi_.png");
	private static final ResourceLocation IMAGE_12 = ResourceLocation.parse("wealizh:textures/screens/cun_ru_.png");
	private static final ResourceLocation IMAGE_13 = ResourceLocation.parse("wealizh:textures/screens/wei_jing_gao_ti_shi_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/chang_jian_tou_jin_du_tiao_.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/tong_dao_cao_.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_suo_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/dian_liang_zhi_.png");

	public CrusherGUIScreen(CrusherGUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 7 && mouseX < leftPos + 31 && mouseY > topPos + 18 && mouseY < topPos + 42) {
			String hoverText = GUITTextEReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (CrusherGUIIsMissingGearBladesBoolReturnProcedure.execute(world, x, y, z))
			if (mouseX > leftPos + -21 && mouseX < leftPos + 3 && mouseY > topPos + -1 && mouseY < topPos + 23) {
				guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.wealizh.crusher_gui.tooltip_missing_gear_blades"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 31, this.topPos + 13, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 34, this.topPos + 38, 0, 0, 20, 10, 20, 10);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 123, this.topPos + 32, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 127, this.topPos + 59, 0, 0, 18, 9, 18, 9);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 57, this.topPos + 21, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 75, this.topPos + 21, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 93, this.topPos + 21, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 57, this.topPos + 52, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + 75, this.topPos + 52, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, this.leftPos + 93, this.topPos + 52, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, this.leftPos + 12, this.topPos + 36, 0, 0, 14, 16, 14, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11, this.leftPos + 11, this.topPos + 53, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_12, this.leftPos + 29, this.topPos + 53, 0, 0, 16, 16, 16, 16);
		if (CrusherGUIIsMissingGearBladesBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_13, this.leftPos + -18, this.topPos + 2, 0, 0, 18, 18, 18, 18);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 54, this.topPos + 39, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 14, 0, 896), 64, 14, 64, 910);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 128, this.topPos + 37, 0, Mth.clamp((int) GUIPassageTrueOrFalseIndexReturnProcedure.execute(world, x, y, z) * 16, 0, 16), 16, 16, 16, 32);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 154, this.topPos + 35, 0, Mth.clamp((int) GUIRedstoneActivateIndexReturnProcedure.execute(world, x, y, z) * 20, 0, 20), 12, 20, 12, 40);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 10, this.topPos + 13, 0, Mth.clamp((int) GUIEBarReturnProcedure.execute(world, x, y, z) * 34, 0, 1088), 18, 34, 18, 1122);
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
		imagebutton_tong_dao_kai_guan = new ImageButton(this.leftPos + 127, this.topPos + 12, 18, 18,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/tong_dao_kai_guan_.png"), ResourceLocation.parse("wealizh:textures/screens/tong_dao_kai_guan__liang_.png")), e -> {
					int x = CrusherGUIScreen.this.x;
					int y = CrusherGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new CrusherGUIButtonMessage(0, x, y, z));
						CrusherGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tong_dao_kai_guan);
	}
}