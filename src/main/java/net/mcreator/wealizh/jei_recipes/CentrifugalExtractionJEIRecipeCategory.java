package net.mcreator.wealizh.jei_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.wealizh.init.WealizhModJeiPlugin;
import net.mcreator.wealizh.init.WealizhModBlocks;

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

public class CentrifugalExtractionJEIRecipeCategory implements IRecipeCategory<CentrifugalExtractionJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("wealizh:centrifugal_extraction_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("wealizh:textures/screens/chi_xin_cui_qu_jei.png");
	private final IDrawable background;
	private final IDrawable icon;

	private final Minecraft mc = Minecraft.getInstance();

	public CentrifugalExtractionJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 18);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(WealizhModBlocks.CENTRIFUGE.get().asItem()));
	}

	@Override
	public IRecipeType<CentrifugalExtractionJEIRecipe> getRecipeType() {
		return WealizhModJeiPlugin.CentrifugalExtractionJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("离心萃取|Centrifugal Extraction");
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
	public void draw(CentrifugalExtractionJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CentrifugalExtractionJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(4, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).add(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 19, 1).add(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 1).add(actualOutputs.get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 123, 1).add(actualOutputs.get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 141, 1).add(actualOutputs.get(2));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 159, 1).add(actualOutputs.get(3));
	}
}