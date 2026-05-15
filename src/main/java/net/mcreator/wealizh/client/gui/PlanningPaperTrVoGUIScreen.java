package net.mcreator.wealizh.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.wealizh.world.inventory.PlanningPaperTrVoGUIMenu;
import net.mcreator.wealizh.network.PlanningPaperTrVoGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

public class PlanningPaperTrVoGUIScreen extends AbstractContainerScreen<PlanningPaperTrVoGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox TrVo;
	private Button button_empty;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/planning_paper_tr_vo_gui.png");

	public PlanningPaperTrVoGUIScreen(PlanningPaperTrVoGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 156;
		this.imageHeight = 51;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("TrVo"))
				TrVo.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		TrVo.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (TrVo.isFocused())
			return TrVo.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String TrVoValue = TrVo.getValue();
		super.resize(minecraft, width, height);
		TrVo.setValue(TrVoValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_tr_vo_gui.label_trvo"), 5, 9, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		TrVo = new EditBox(this.font, this.leftPos + 33, this.topPos + 5, 118, 18, Component.translatable("gui.wealizh.planning_paper_tr_vo_gui.TrVo"));
		TrVo.setMaxLength(8192);
		TrVo.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "TrVo", content, false);
		});
		TrVo.setHint(Component.translatable("gui.wealizh.planning_paper_tr_vo_gui.TrVo"));
		this.addWidget(this.TrVo);
		button_empty = Button.builder(Component.translatable("gui.wealizh.planning_paper_tr_vo_gui.button_empty"), e -> {
			int x = PlanningPaperTrVoGUIScreen.this.x;
			int y = PlanningPaperTrVoGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PlanningPaperTrVoGUIButtonMessage(0, x, y, z));
				PlanningPaperTrVoGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 64, this.topPos + 26, 25, 20).build();
		this.addRenderableWidget(button_empty);
	}
}