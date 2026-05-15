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

import net.mcreator.wealizh.world.inventory.StorageBatteryGUIMenu;
import net.mcreator.wealizh.procedures.*;
import net.mcreator.wealizh.network.StorageBatteryGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class StorageBatteryGUIScreen extends AbstractContainerScreen<StorageBatteryGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_kong_cao;
	private ImageButton imagebutton_tou_ming_cao;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/storage_battery_gui.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/tian_lan_gui.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/lu_tong_he_jin_gui.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/gai_nian_ti_gui.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/tong_dao_xian_.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("wealizh:textures/screens/tong_dao_xian_.png");
	private static final ResourceLocation SPRITE_5 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_dao_xian_.png");
	private static final ResourceLocation SPRITE_6 = ResourceLocation.parse("wealizh:textures/screens/dian_liang_zhi_-chang_.png");
	private static final ResourceLocation SPRITE_7 = ResourceLocation.parse("wealizh:textures/screens/kong_cao_.png");
	private static final ResourceLocation SPRITE_8 = ResourceLocation.parse("wealizh:textures/screens/kong_cao_.png");
	private static final ResourceLocation SPRITE_9 = ResourceLocation.parse("wealizh:textures/screens/cun_ru_.png");
	private static final ResourceLocation SPRITE_10 = ResourceLocation.parse("wealizh:textures/screens/qu_chu_.png");
	private static final ResourceLocation SPRITE_11 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_-shang_.png");
	private static final ResourceLocation SPRITE_12 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_-xia_.png");
	private static final ResourceLocation SPRITE_13 = ResourceLocation.parse("wealizh:textures/screens/dian_chi_.png");
	private static final ResourceLocation SPRITE_14 = ResourceLocation.parse("wealizh:textures/screens/dian_chi_.png");
	private static final ResourceLocation SPRITE_15 = ResourceLocation.parse("wealizh:textures/screens/mo_shi_an_niu_.png");
	private static final ResourceLocation SPRITE_16 = ResourceLocation.parse("wealizh:textures/screens/mo_shi_an_niu_.png");
	private static final ResourceLocation SPRITE_17 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_ji_huo_.png");
	private static final ResourceLocation SPRITE_18 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_hong_fa_.png");
	private static final ResourceLocation SPRITE_19 = ResourceLocation.parse("wealizh:textures/screens/bao_chi_hong_fa_.png");
	private static final ResourceLocation SPRITE_20 = ResourceLocation.parse("wealizh:textures/screens/wu_xian_dian_liang_tiao__chang_.png");

	public StorageBatteryGUIScreen(StorageBatteryGUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 64 && mouseX < leftPos + 88 && mouseY > topPos + 17 && mouseY < topPos + 41) {
			String hoverText = GUITTextEReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 88 && mouseX < leftPos + 112 && mouseY > topPos + 17 && mouseY < topPos + 41) {
			String hoverText = GUITTextEReturnProcedure.execute(world, x, y, z);
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
		if (AluminumStorageBatteryGUIColorfulChangeBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (AluminumCopperAlloyStorageBatteryGUIColorfulChangeBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (InfiniteStorageBatteryGUIColorfulChangeBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 99, this.topPos + 42, 0, 0, 14, 16, 14, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_4, this.leftPos + 63, this.topPos + 42, 0, 0, 14, 16, 14, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_5, this.leftPos + 81, this.topPos + 45, 0, 0, 14, 16, 14, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_6, this.leftPos + 61, this.topPos + 12, 0, Mth.clamp((int) GUIEBarReturnProcedure.execute(world, x, y, z) * 34, 0, 1088), 54, 34, 54, 1122);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_7, this.leftPos + 61, this.topPos + 55, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_8, this.leftPos + 97, this.topPos + 55, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_9, this.leftPos + 44, this.topPos + 56, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_10, this.leftPos + 116, this.topPos + 56, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_11, this.leftPos + 47, this.topPos + 23, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_12, this.leftPos + 119, this.topPos + 23, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_13, this.leftPos + 62, this.topPos + 56, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_14, this.leftPos + 98, this.topPos + 56, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_15, this.leftPos + 133, this.topPos + 28, 0, Mth.clamp((int) GUIModeButtonIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 54), 18, 18, 18, 72);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_16, this.leftPos + 25, this.topPos + 28, 0, Mth.clamp((int) GUIRModeButtonIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 54), 18, 18, 18, 72);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_17, this.leftPos + 79, this.topPos + 55, 0, Mth.clamp((int) GUIRedstoneActivateIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 18), 18, 18, 18, 36);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_18, this.leftPos + 26, this.topPos + 47, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_19, this.leftPos + 134, this.topPos + 47, 0, 0, 16, 16, 16, 16);
		if (GUIInfiniteEBarBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_20, this.leftPos + 61, this.topPos + 12, 0, 0, 54, 34, 54, 34);
		}
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
		imagebutton_kong_cao = new ImageButton(this.leftPos + 133, this.topPos + 28, 18, 18,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png"), ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png")), e -> {
					int x = StorageBatteryGUIScreen.this.x;
					int y = StorageBatteryGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new StorageBatteryGUIButtonMessage(0, x, y, z));
						StorageBatteryGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_kong_cao);
		imagebutton_tou_ming_cao = new ImageButton(this.leftPos + 25, this.topPos + 28, 18, 18,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png"), ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png")), e -> {
					int x = StorageBatteryGUIScreen.this.x;
					int y = StorageBatteryGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new StorageBatteryGUIButtonMessage(1, x, y, z));
						StorageBatteryGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tou_ming_cao);
	}
}