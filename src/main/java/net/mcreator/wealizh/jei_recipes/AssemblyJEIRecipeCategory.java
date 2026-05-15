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

public class AssemblyJEIRecipeCategory implements IRecipeCategory<AssemblyJEIRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("wealizh:assembly_jei");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("wealizh:textures/screens/zhuang_pei_jei.png");
	private final IDrawable background;
	private final IDrawable icon;

	private final Minecraft mc = Minecraft.getInstance();

	public AssemblyJEIRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 106, 106);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(WealizhModBlocks.ASSEMBLY_TYPE_CONTROL_CONSOLE.get().asItem()));
	}

	@Override
	public IRecipeType<AssemblyJEIRecipe> getRecipeType() {
		return WealizhModJeiPlugin.AssemblyJEI_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("装配|Assembly");
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
	public void draw(AssemblyJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, AssemblyJEIRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(1, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 45, 89).add(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 45).add(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 45, 1).add(recipe.getIngredients().get(2));
		builder.addSlot(RecipeIngredientRole.INPUT, 89, 45).add(recipe.getIngredients().get(3));
		builder.addSlot(RecipeIngredientRole.INPUT, 27, 89).add(recipe.getIngredients().get(4));
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 63).add(recipe.getIngredients().get(5));
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 27).add(recipe.getIngredients().get(6));
		builder.addSlot(RecipeIngredientRole.INPUT, 27, 1).add(recipe.getIngredients().get(7));
		builder.addSlot(RecipeIngredientRole.INPUT, 63, 1).add(recipe.getIngredients().get(8));
		builder.addSlot(RecipeIngredientRole.INPUT, 89, 27).add(recipe.getIngredients().get(9));
		builder.addSlot(RecipeIngredientRole.INPUT, 89, 63).add(recipe.getIngredients().get(10));
		builder.addSlot(RecipeIngredientRole.INPUT, 63, 89).add(recipe.getIngredients().get(11));
		builder.addSlot(RecipeIngredientRole.INPUT, 7, 83).add(recipe.getIngredients().get(12));
		builder.addSlot(RecipeIngredientRole.INPUT, 7, 7).add(recipe.getIngredients().get(13));
		builder.addSlot(RecipeIngredientRole.INPUT, 83, 7).add(recipe.getIngredients().get(14));
		builder.addSlot(RecipeIngredientRole.INPUT, 83, 83).add(recipe.getIngredients().get(15));
		builder.addSlot(RecipeIngredientRole.INPUT, 26, 64).add(recipe.getIngredients().get(16));
		builder.addSlot(RecipeIngredientRole.INPUT, 26, 26).add(recipe.getIngredients().get(17));
		builder.addSlot(RecipeIngredientRole.INPUT, 64, 26).add(recipe.getIngredients().get(18));
		builder.addSlot(RecipeIngredientRole.INPUT, 64, 64).add(recipe.getIngredients().get(19));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 45, 45).add(actualOutputs.get(0));
	}
}