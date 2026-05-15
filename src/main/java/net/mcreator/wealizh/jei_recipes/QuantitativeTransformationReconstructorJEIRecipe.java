package net.mcreator.wealizh.jei_recipes;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;

import java.util.List;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

public record QuantitativeTransformationReconstructorJEIRecipe(List<ItemStack> outputs, List<Ingredient> recipeItems) implements Recipe<RecipeInput> {
	public QuantitativeTransformationReconstructorJEIRecipe(List<ItemStack> outputs, List<Ingredient> recipeItems) {
		this.outputs = outputs;
		this.recipeItems = recipeItems;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeBookCategories.CRAFTING_MISC;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.create(this.recipeItems);
	}

	@Override
	public boolean matches(RecipeInput pContainer, Level pLevel) {
		if (pLevel.isClientSide()) {
			return false;
		}
		return false;
	}

	public List<Ingredient> getIngredients() {
		return recipeItems;
	}

	@Override
	public ItemStack assemble(RecipeInput input, HolderLookup.Provider holder) {
		return ItemStack.EMPTY;
	}

	public List<ItemStack> getResultItems() {
		return outputs;
	}

	@Override
	public RecipeType<? extends Recipe<RecipeInput>> getType() {
		return Type.INSTANCE;
	}

	@Override
	public RecipeSerializer<? extends Recipe<RecipeInput>> getSerializer() {
		return Serializer.INSTANCE;
	}

	public static class Type implements RecipeType<QuantitativeTransformationReconstructorJEIRecipe> {
		private Type() {
		}

		public static final RecipeType<QuantitativeTransformationReconstructorJEIRecipe> INSTANCE = new Type();
	}

	public static class Serializer implements RecipeSerializer<QuantitativeTransformationReconstructorJEIRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		private static final MapCodec<QuantitativeTransformationReconstructorJEIRecipe> CODEC = RecordCodecBuilder
				.mapCodec(builder -> builder.group(ItemStack.OPTIONAL_CODEC.listOf().fieldOf("outputs").forGetter(QuantitativeTransformationReconstructorJEIRecipe::outputs),
						Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(QuantitativeTransformationReconstructorJEIRecipe::recipeItems)).apply(builder, QuantitativeTransformationReconstructorJEIRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, QuantitativeTransformationReconstructorJEIRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

		@Override
		public MapCodec<QuantitativeTransformationReconstructorJEIRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, QuantitativeTransformationReconstructorJEIRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static QuantitativeTransformationReconstructorJEIRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
			List<Ingredient> inputs = NonNullList.withSize(buf.readVarInt(), EmptyIngredient.create());
			inputs.replaceAll(ingredients -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
			List<ItemStack> outputs = NonNullList.withSize(buf.readVarInt(), ItemStack.EMPTY);
			outputs.replaceAll(results -> ItemStack.STREAM_CODEC.decode(buf));
			return new QuantitativeTransformationReconstructorJEIRecipe(outputs, inputs);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buf, QuantitativeTransformationReconstructorJEIRecipe recipe) {
			buf.writeVarInt(recipe.getIngredients().size());
			for (Ingredient ing : recipe.getIngredients()) {
				if (ing.items().findFirst().get().value() == Items.AIR)
					Ingredient.CONTENTS_STREAM_CODEC.encode(buf, EmptyIngredient.create());
				else
					Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
			}
			buf.writeVarInt(recipe.getResultItems().size());
			for (ItemStack itemstack : recipe.getResultItems()) {
				ItemStack.STREAM_CODEC.encode(buf, itemstack);
			}
		}
	}
}