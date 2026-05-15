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

public class QuantitativeTransformationReconstructorJEIRecipeCategory implements IRecipeCategory<QuantitativeTransformationReconstructorJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("wealizh:quantitative_transformation_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("wealizh:textures/screens/zhi_bian_huan_yuan_jei.png");
	private final IDrawable background;
	private final IDrawable icon;

	private final Minecraft mc = Minecraft.getInstance();

	public QuantitativeTransformationReconstructorJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 97, 58);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(WealizhModBlocks.QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR.get().asItem()));
	}

	@Override
	public IRecipeType<QuantitativeTransformationReconstructorJEIRecipe> getRecipeType() {
		return WealizhModJeiPlugin.QuantitativeTransformationReconstructorJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("质变还原|Quantitative Transformation");
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
	public void draw(QuantitativeTransformationReconstructorJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, QuantitativeTransformationReconstructorJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 41).add(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).add(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 76, 22).add(actualOutputs.get(0));
	}
}