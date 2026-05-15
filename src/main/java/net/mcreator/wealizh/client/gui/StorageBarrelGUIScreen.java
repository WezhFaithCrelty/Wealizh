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

import net.mcreator.wealizh.world.inventory.StorageBarrelGUIMenu;
import net.mcreator.wealizh.procedures.*;
import net.mcreator.wealizh.network.StorageBarrelGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class StorageBarrelGUIScreen extends AbstractContainerScreen<StorageBarrelGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_ban_tou_ming_cao_bai;
	private ImageButton imagebutton_tou_ming_cao;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/storage_barrel_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/gai_nian_ti_gui.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_-xia_.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/kong_cao_.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("wealizh:textures/screens/kong_cao_.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("wealizh:textures/screens/kong_cao_.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("wealizh:textures/screens/kong_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("wealizh:textures/screens/man_ye_ti_guan_.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_hong_fa_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/ye_guan_-chang_.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_suo_.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/you_xia_guai_jiao_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/mo_shi_an_niu_.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("wealizh:textures/screens/hong_shi_ji_huo_.png");
	private static final ResourceLocation SPRITE_5 = ResourceLocation.parse("wealizh:textures/screens/mo_shi_an_niu_.png");
	private static final ResourceLocation SPRITE_6 = ResourceLocation.parse("wealizh:textures/screens/bao_chi_hong_fa_.png");
	private static final ResourceLocation SPRITE_7 = ResourceLocation.parse("wealizh:textures/screens/wu_xian_ye_guan__chang_.png");

	public StorageBarrelGUIScreen(StorageBarrelGUIMenu container, Inventory inventory, Component text) {
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
		if (mouseX > leftPos + 64 && mouseX < leftPos + 88 && mouseY > topPos + 20 && mouseY < topPos + 44) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 88 && mouseX < leftPos + 112 && mouseY > topPos + 20 && mouseY < topPos + 44) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 64 && mouseX < leftPos + 88 && mouseY > topPos + 43 && mouseY < topPos + 67) {
			String hoverText = GUITTextLiquidNumReturnProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 88 && mouseX < leftPos + 112 && mouseY > topPos + 43 && mouseY < topPos + 67) {
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
		if (InfiniteStorageBarrelGUIColorfulChangeBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 119, this.topPos + 36, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 43, this.topPos + 52, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 115, this.topPos + 16, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 115, this.topPos + 52, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 119, this.topPos + 54, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 119, this.topPos + 18, 0, 0, 10, 14, 10, 14);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 17, this.topPos + 53, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 61, this.topPos + 16, 0, Mth.clamp((int) GUILiquidBarIndexReturnProcedure.execute(world, x, y, z) * 54, 0, 2808), 54, 54, 54, 2862);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 46, this.topPos + 17, 0, Mth.clamp((int) GUIRedstoneActivateIndexReturnProcedure.execute(world, x, y, z) * 20, 0, 20), 12, 20, 12, 40);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 43, this.topPos + 34, 0, 0, 19, 19, 19, 19);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 16, this.topPos + 34, 0, Mth.clamp((int) GUIRModeButtonIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 54), 18, 18, 18, 72);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_4, this.leftPos + 16, this.topPos + 16, 0, Mth.clamp((int) GUIRedstoneActivateIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 18), 18, 18, 18, 36);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_5, this.leftPos + 142, this.topPos + 27, 0, Mth.clamp((int) GUIModeButtonIndexReturnProcedure.execute(world, x, y, z) * 18, 0, 54), 18, 18, 18, 72);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_6, this.leftPos + 143, this.topPos + 47, 0, 0, 16, 16, 16, 16);
		if (InfiniteStorageBarrelGUIColorfulChangeBoolReturnProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_7, this.leftPos + 61, this.topPos + 16, 0, 0, 54, 54, 54, 54);
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
		imagebutton_ban_tou_ming_cao_bai = new ImageButton(this.leftPos + 16, this.topPos + 34, 18, 18,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png"), ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png")), e -> {
					int x = StorageBarrelGUIScreen.this.x;
					int y = StorageBarrelGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new StorageBarrelGUIButtonMessage(0, x, y, z));
						StorageBarrelGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_ban_tou_ming_cao_bai);
		imagebutton_tou_ming_cao = new ImageButton(this.leftPos + 142, this.topPos + 27, 18, 18,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png"), ResourceLocation.parse("wealizh:textures/screens/tou_ming_cao_.png")), e -> {
					int x = StorageBarrelGUIScreen.this.x;
					int y = StorageBarrelGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new StorageBarrelGUIButtonMessage(1, x, y, z));
						StorageBarrelGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
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