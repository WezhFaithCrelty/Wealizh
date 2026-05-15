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

import net.mcreator.wealizh.world.inventory.PressingStorageRoomGUIMenu;
import net.mcreator.wealizh.procedures.*;
import net.mcreator.wealizh.network.PressingStorageRoomGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

public class PressingStorageRoomGUIScreen extends AbstractContainerScreen<PressingStorageRoomGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_la_gan_kai_guan_bian_kuang;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/pressing_storage_room_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("wealizh:textures/screens/shuang_quan_zhi_wu_cao_.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("wealizh:textures/screens/duan_ya_ji__i.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_.png");
	private static final ResourceLocation IMAGE_3 = ResourceLocation.parse("wealizh:textures/screens/pei_liao_cao_.png");
	private static final ResourceLocation IMAGE_4 = ResourceLocation.parse("wealizh:textures/screens/jian_tou_.png");
	private static final ResourceLocation IMAGE_5 = ResourceLocation.parse("wealizh:textures/screens/da_cao_.png");
	private static final ResourceLocation IMAGE_6 = ResourceLocation.parse("wealizh:textures/screens/lou_dou_xing_dang_ban_.png");
	private static final ResourceLocation IMAGE_7 = ResourceLocation.parse("wealizh:textures/screens/tong_dao_xian_.png");
	private static final ResourceLocation IMAGE_8 = ResourceLocation.parse("wealizh:textures/screens/dian_chi_.png");
	private static final ResourceLocation IMAGE_9 = ResourceLocation.parse("wealizh:textures/screens/wei_jing_gao_ti_shi_.png");
	private static final ResourceLocation IMAGE_10 = ResourceLocation.parse("wealizh:textures/screens/jian_duan_dang_ban_.png");
	private static final ResourceLocation IMAGE_11 = ResourceLocation.parse("wealizh:textures/screens/shuang_quan_zhi_wu_cao_.png");
	private static final ResourceLocation IMAGE_12 = ResourceLocation.parse("wealizh:textures/screens/chi_lun_.png");
	private static final ResourceLocation SPRITE_0 = ResourceLocation.parse("wealizh:textures/screens/sun_pi_tiao_.png");
	private static final ResourceLocation SPRITE_1 = ResourceLocation.parse("wealizh:textures/screens/zhi_liang_tiao_.png");
	private static final ResourceLocation SPRITE_2 = ResourceLocation.parse("wealizh:textures/screens/dian_liang_zhi_.png");
	private static final ResourceLocation SPRITE_3 = ResourceLocation.parse("wealizh:textures/screens/duan_ya_.png");
	private static final ResourceLocation SPRITE_4 = ResourceLocation.parse("wealizh:textures/screens/la_gan_kai_guan_.png");

	public PressingStorageRoomGUIScreen(PressingStorageRoomGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 180;
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
		if (GUITSKTextIsAMachineIsDamagedProcedure.execute(world, x, y, z))
			if (mouseX > leftPos + -21 && mouseX < leftPos + 3 && mouseY > topPos + -1 && mouseY < topPos + 23) {
				guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.wealizh.pressing_storage_room_gui.tooltip_press_machine_is_damaged"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (mouseX > leftPos + 13 && mouseX < leftPos + 37 && mouseY > topPos + 38 && mouseY < topPos + 62) {
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 152, this.topPos + 17, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 152, this.topPos + 17, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 98, this.topPos + 20, 0, 0, 14, 10, 14, 10);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 80, this.topPos + 16, 0, 0, 18, 18, 18, 18);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 113, this.topPos + 74, 0, 0, 14, 10, 14, 10);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 129, this.topPos + 62, 0, 0, 26, 26, 26, 26);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 133, this.topPos + 49, 0, 0, 18, 9, 18, 9);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 18, this.topPos + 54, 0, 0, 14, 16, 14, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + 17, this.topPos + 71, 0, 0, 16, 16, 16, 16);
		if (GUITSKTextIsAMachineIsDamagedProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, this.leftPos + -18, this.topPos + 2, 0, 0, 18, 18, 18, 18);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, this.leftPos + 39, this.topPos + 24, 0, 0, 15, 64, 15, 64);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11, this.leftPos + 17, this.topPos + 11, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_12, this.leftPos + 17, this.topPos + 11, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_0, this.leftPos + 113, this.topPos + 17, 0, Mth.clamp((int) GUIDamageBarIndexReturnProcedure.execute(world, x, y, z) * 5, 0, 170), 36, 5, 36, 175);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_1, this.leftPos + 113, this.topPos + 28, 0, Mth.clamp((int) GUIValueNumBarIndexReturnProcedure.execute(world, x, y, z) * 5, 0, 170), 36, 5, 36, 175);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_2, this.leftPos + 16, this.topPos + 33, 0, Mth.clamp((int) GUIEBarReturnProcedure.execute(world, x, y, z) * 34, 0, 1088), 18, 34, 18, 1122);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_3, this.leftPos + 59, this.topPos + 45, 0, Mth.clamp((int) GUIProgressBarIndexReturnProcedure.execute(world, x, y, z) * 22, 0, 242), 48, 22, 48, 264);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SPRITE_4, this.leftPos + 60, this.topPos + 15, 0, Mth.clamp((int) ONAndOFFSwitchIndexReturnProcedure.execute(world, x, y, z) * 20, 0, 20), 14, 20, 14, 40);
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
		imagebutton_la_gan_kai_guan_bian_kuang = new ImageButton(this.leftPos + 60, this.topPos + 15, 14, 20,
				new WidgetSprites(ResourceLocation.parse("wealizh:textures/screens/la_gan_kai_guan__bian_kuang_.png"), ResourceLocation.parse("wealizh:textures/screens/la_gan_kai_guan__bian_kuang_.png")), e -> {
					int x = PressingStorageRoomGUIScreen.this.x;
					int y = PressingStorageRoomGUIScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new PressingStorageRoomGUIButtonMessage(0, x, y, z));
						PressingStorageRoomGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_la_gan_kai_guan_bian_kuang);
	}
}