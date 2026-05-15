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

import net.mcreator.wealizh.world.inventory.PlanningPaperSONGUIMenu;
import net.mcreator.wealizh.network.PlanningPaperSONGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

public class PlanningPaperSONGUIScreen extends AbstractContainerScreen<PlanningPaperSONGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox SN;
	private EditBox ON;
	private Button button_empty;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/planning_paper_son_gui.png");

	public PlanningPaperSONGUIScreen(PlanningPaperSONGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 148;
		this.imageHeight = 70;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("SN"))
				SN.setValue(stringState);
			else if (name.equals("ON"))
				ON.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		SN.render(guiGraphics, mouseX, mouseY, partialTicks);
		ON.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (SN.isFocused())
			return SN.keyPressed(key, b, c);
		if (ON.isFocused())
			return ON.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String SNValue = SN.getValue();
		String ONValue = ON.getValue();
		super.resize(minecraft, width, height);
		SN.setValue(SNValue);
		ON.setValue(ONValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_son_gui.label_sn"), 6, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_son_gui.label_on"), 6, 29, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		SN = new EditBox(this.font, this.leftPos + 25, this.topPos + 5, 118, 18, Component.translatable("gui.wealizh.planning_paper_son_gui.SN"));
		SN.setMaxLength(8192);
		SN.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "SN", content, false);
		});
		SN.setHint(Component.translatable("gui.wealizh.planning_paper_son_gui.SN"));
		this.addWidget(this.SN);
		ON = new EditBox(this.font, this.leftPos + 25, this.topPos + 25, 118, 18, Component.translatable("gui.wealizh.planning_paper_son_gui.ON"));
		ON.setMaxLength(8192);
		ON.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "ON", content, false);
		});
		ON.setHint(Component.translatable("gui.wealizh.planning_paper_son_gui.ON"));
		this.addWidget(this.ON);
		button_empty = Button.builder(Component.translatable("gui.wealizh.planning_paper_son_gui.button_empty"), e -> {
			int x = PlanningPaperSONGUIScreen.this.x;
			int y = PlanningPaperSONGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PlanningPaperSONGUIButtonMessage(0, x, y, z));
				PlanningPaperSONGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 60, this.topPos + 46, 25, 20).build();
		this.addRenderableWidget(button_empty);
	}
}