package net.mcreator.wealizh.client.gui;

import org.objectweb.asm.tree.analysis.Value;

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

import net.mcreator.wealizh.world.inventory.CreativePlanningPaperNBTGUIMenu;
import net.mcreator.wealizh.network.CreativePlanningPaperNBTGUIButtonMessage;
import net.mcreator.wealizh.init.WealizhModScreens;

public class CreativePlanningPaperNBTGUIScreen extends AbstractContainerScreen<CreativePlanningPaperNBTGUIMenu> implements WealizhModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox Type;
	private EditBox NBT;
	private EditBox Value;
	private Button button_empty;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("wealizh:textures/screens/creative_planning_paper_nbt_gui.png");

	public CreativePlanningPaperNBTGUIScreen(CreativePlanningPaperNBTGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 160;
		this.imageHeight = 90;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("Type"))
				Type.setValue(stringState);
			else if (name.equals("NBT"))
				NBT.setValue(stringState);
			else if (name.equals("Value"))
				Value.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		Type.render(guiGraphics, mouseX, mouseY, partialTicks);
		NBT.render(guiGraphics, mouseX, mouseY, partialTicks);
		Value.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (Type.isFocused())
			return Type.keyPressed(key, b, c);
		if (NBT.isFocused())
			return NBT.keyPressed(key, b, c);
		if (Value.isFocused())
			return Value.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String TypeValue = Type.getValue();
		String NBTValue = NBT.getValue();
		String ValueValue = Value.getValue();
		super.resize(minecraft, width, height);
		Type.setValue(TypeValue);
		NBT.setValue(NBTValue);
		Value.setValue(ValueValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.label_type"), 7, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.label_nbt"), 10, 28, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.label_value"), 4, 48, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		Type = new EditBox(this.font, this.leftPos + 37, this.topPos + 4, 118, 18, Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.Type"));
		Type.setMaxLength(8192);
		Type.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "Type", content, false);
		});
		Type.setHint(Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.Type"));
		this.addWidget(this.Type);
		NBT = new EditBox(this.font, this.leftPos + 37, this.topPos + 24, 118, 18, Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.NBT"));
		NBT.setMaxLength(8192);
		NBT.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "NBT", content, false);
		});
		this.addWidget(this.NBT);
		Value = new EditBox(this.font, this.leftPos + 37, this.topPos + 44, 118, 18, Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.Value"));
		Value.setMaxLength(8192);
		Value.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "Value", content, false);
		});
		this.addWidget(this.Value);
		button_empty = Button.builder(Component.translatable("gui.wealizh.creative_planning_paper_nbt_gui.button_empty"), e -> {
			int x = CreativePlanningPaperNBTGUIScreen.this.x;
			int y = CreativePlanningPaperNBTGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new CreativePlanningPaperNBTGUIButtonMessage(0, x, y, z));
				CreativePlanningPaperNBTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 66, this.topPos + 65, 25, 20).build();
		this.addRenderableWidget(button_empty);
	}
}