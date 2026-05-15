package net.mcreator.wealizh.jei_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.wealizh.init.WealizhModJeiPlugin;
import net.mcreator.wealizh.init.WealizhModItems;

import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

import java.util.List;

public class SoakingJEIRecipeCategory implements IRecipeCategory<SoakingJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("wealizh:soaking_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("wealizh:textures/screens/jin_pao_jei.png");
	private final IDrawable background;
	private final IDrawable icon;

	private final Minecraft mc = Minecraft.getInstance();

	public SoakingJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 138, 30);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(WealizhModItems.SOAKING.get()));
	}

	@Override
	public IRecipeType<SoakingJEIRecipe> getRecipeType() {
		return WealizhModJeiPlugin.SoakingJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("浸泡|Soaking");
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return this.background.getWidth();
	}

	@Override
	public int getHeight() {
		return this.background.getHeight();
	}

	@Override
	public void draw(SoakingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, SoakingJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(5, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 7, 7).add(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 121, 7).add(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 31, 7).add(actualOutputs.get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 49, 7).add(actualOutputs.get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 67, 7).add(actualOutputs.get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 7).add(actualOutputs.get(3));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 7).add(actualOutputs.get(4));
	}
}