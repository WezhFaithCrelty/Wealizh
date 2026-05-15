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

import net.mcreator.wealizh.world.inventory.PlanningPaperConcentratedAssignPrioritiesGUIMenu;
import net.mcreator.wealizh.network.PlanningPaperConcentratedAssignPrioritiesGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

public class PlanningPaperConcentratedAssignPrioritiesGUIScreen extends AbstractContainerScreen<PlanningPaperConcentratedAssignPrioritiesGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox px;
	private EditBox nx;
	private EditBox py;
	private EditBox ny;
	private EditBox pz;
	private EditBox nz;
	private Button button_empty;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/planning_paper_concentrated_assign_priorities_gui.png");

	public PlanningPaperConcentratedAssignPrioritiesGUIScreen(PlanningPaperConcentratedAssignPrioritiesGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 148;
		this.imageHeight = 150;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("px"))
				px.setValue(stringState);
			else if (name.equals("nx"))
				nx.setValue(stringState);
			else if (name.equals("py"))
				py.setValue(stringState);
			else if (name.equals("ny"))
				ny.setValue(stringState);
			else if (name.equals("pz"))
				pz.setValue(stringState);
			else if (name.equals("nz"))
				nz.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		px.render(guiGraphics, mouseX, mouseY, partialTicks);
		nx.render(guiGraphics, mouseX, mouseY, partialTicks);
		py.render(guiGraphics, mouseX, mouseY, partialTicks);
		ny.render(guiGraphics, mouseX, mouseY, partialTicks);
		pz.render(guiGraphics, mouseX, mouseY, partialTicks);
		nz.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (px.isFocused())
			return px.keyPressed(key, b, c);
		if (nx.isFocused())
			return nx.keyPressed(key, b, c);
		if (py.isFocused())
			return py.keyPressed(key, b, c);
		if (ny.isFocused())
			return ny.keyPressed(key, b, c);
		if (pz.isFocused())
			return pz.keyPressed(key, b, c);
		if (nz.isFocused())
			return nz.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String pxValue = px.getValue();
		String nxValue = nx.getValue();
		String pyValue = py.getValue();
		String nyValue = ny.getValue();
		String pzValue = pz.getValue();
		String nzValue = nz.getValue();
		super.resize(minecraft, width, height);
		px.setValue(pxValue);
		nx.setValue(nxValue);
		py.setValue(pyValue);
		ny.setValue(nyValue);
		pz.setValue(pzValue);
		nz.setValue(nzValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.label_x"), 10, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.label_x1"), 5, 29, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.label_y"), 10, 49, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.label_y1"), 5, 69, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.label_z"), 10, 89, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.label_z1"), 5, 109, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		px = new EditBox(this.font, this.leftPos + 25, this.topPos + 5, 118, 18, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.px"));
		px.setMaxLength(8192);
		px.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "px", content, false);
		});
		px.setHint(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.px"));
		this.addWidget(this.px);
		nx = new EditBox(this.font, this.leftPos + 25, this.topPos + 25, 118, 18, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.nx"));
		nx.setMaxLength(8192);
		nx.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "nx", content, false);
		});
		nx.setHint(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.nx"));
		this.addWidget(this.nx);
		py = new EditBox(this.font, this.leftPos + 25, this.topPos + 45, 118, 18, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.py"));
		py.setMaxLength(8192);
		py.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "py", content, false);
		});
		py.setHint(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.py"));
		this.addWidget(this.py);
		ny = new EditBox(this.font, this.leftPos + 25, this.topPos + 65, 118, 18, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.ny"));
		ny.setMaxLength(8192);
		ny.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "ny", content, false);
		});
		ny.setHint(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.ny"));
		this.addWidget(this.ny);
		pz = new EditBox(this.font, this.leftPos + 25, this.topPos + 85, 118, 18, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.pz"));
		pz.setMaxLength(8192);
		pz.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "pz", content, false);
		});
		pz.setHint(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.pz"));
		this.addWidget(this.pz);
		nz = new EditBox(this.font, this.leftPos + 25, this.topPos + 105, 118, 18, Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.nz"));
		nz.setMaxLength(8192);
		nz.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "nz", content, false);
		});
		nz.setHint(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.nz"));
		this.addWidget(this.nz);
		button_empty = Button.builder(Component.translatable("gui.wealizh.planning_paper_concentrated_assign_priorities_gui.button_empty"), e -> {
			int x = PlanningPaperConcentratedAssignPrioritiesGUIScreen.this.x;
			int y = PlanningPaperConcentratedAssignPrioritiesGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PlanningPaperConcentratedAssignPrioritiesGUIButtonMessage(0, x, y, z));
				PlanningPaperConcentratedAssignPrioritiesGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 59, this.topPos + 126, 25, 20).build();
		this.addRenderableWidget(button_empty);
	}
}