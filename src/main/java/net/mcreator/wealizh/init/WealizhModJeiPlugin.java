package net.mcreator.wealizh.init;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.wealizh.jei_recipes.*;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.List;

@JeiPlugin
public class WealizhModJeiPlugin implements IModPlugin {
	public static IRecipeType<RapidQualitativeChangeMachineJEIRecipe> RapidQualitativeChangeMachineJEI_Type = IRecipeType.create(RapidQualitativeChangeMachineJEIRecipeCategory.UID, RapidQualitativeChangeMachineJEIRecipe.class);
	public static IRecipeType<QualitativeChangeJEIRecipe> QualitativeChangeJEI_Type = IRecipeType.create(QualitativeChangeJEIRecipeCategory.UID, QualitativeChangeJEIRecipe.class);
	public static IRecipeType<QuantitativeTransformationReconstructorJEIRecipe> QuantitativeTransformationReconstructorJEI_Type = IRecipeType.create(QuantitativeTransformationReconstructorJEIRecipeCategory.UID,
			QuantitativeTransformationReconstructorJEIRecipe.class);
	public static IRecipeType<AssemblyJEIRecipe> AssemblyJEI_Type = IRecipeType.create(AssemblyJEIRecipeCategory.UID, AssemblyJEIRecipe.class);
	public static IRecipeType<SoakingJEIRecipe> SoakingJEI_Type = IRecipeType.create(SoakingJEIRecipeCategory.UID, SoakingJEIRecipe.class);
	public static IRecipeType<MixedSmeltingJEIRecipe> MixedSmeltingJEI_Type = IRecipeType.create(MixedSmeltingJEIRecipeCategory.UID, MixedSmeltingJEIRecipe.class);
	public static IRecipeType<InteractionJEIRecipe> InteractionJEI_Type = IRecipeType.create(InteractionJEIRecipeCategory.UID, InteractionJEIRecipe.class);
	public static IRecipeType<CrushJEIRecipe> CrushJEI_Type = IRecipeType.create(CrushJEIRecipeCategory.UID, CrushJEIRecipe.class);
	public static IRecipeType<ForgingJEIRecipe> ForgingJEI_Type = IRecipeType.create(ForgingJEIRecipeCategory.UID, ForgingJEIRecipe.class);
	public static IRecipeType<DecayJEIRecipe> DecayJEI_Type = IRecipeType.create(DecayJEIRecipeCategory.UID, DecayJEIRecipe.class);
	public static IRecipeType<CompressJEIRecipe> CompressJEI_Type = IRecipeType.create(CompressJEIRecipeCategory.UID, CompressJEIRecipe.class);
	public static IRecipeType<CentrifugalExtractionJEIRecipe> CentrifugalExtractionJEI_Type = IRecipeType.create(CentrifugalExtractionJEIRecipeCategory.UID, CentrifugalExtractionJEIRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("wealizh:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new RapidQualitativeChangeMachineJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new QualitativeChangeJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new QuantitativeTransformationReconstructorJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new AssemblyJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new SoakingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new MixedSmeltingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new InteractionJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CrushJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new ForgingJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DecayJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CompressJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new CentrifugalExtractionJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<RapidQualitativeChangeMachineJEIRecipe> RapidQualitativeChangeMachineJEIRecipes = WealizhModRecipeTypes.recipes.byType(RapidQualitativeChangeMachineJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(RapidQualitativeChangeMachineJEI_Type, RapidQualitativeChangeMachineJEIRecipes);
		List<QualitativeChangeJEIRecipe> QualitativeChangeJEIRecipes = WealizhModRecipeTypes.recipes.byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(QualitativeChangeJEI_Type, QualitativeChangeJEIRecipes);
		List<QuantitativeTransformationReconstructorJEIRecipe> QuantitativeTransformationReconstructorJEIRecipes = WealizhModRecipeTypes.recipes.byType(QuantitativeTransformationReconstructorJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value)
				.collect(Collectors.toList());
		registration.addRecipes(QuantitativeTransformationReconstructorJEI_Type, QuantitativeTransformationReconstructorJEIRecipes);
		List<AssemblyJEIRecipe> AssemblyJEIRecipes = WealizhModRecipeTypes.recipes.byType(AssemblyJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(AssemblyJEI_Type, AssemblyJEIRecipes);
		List<SoakingJEIRecipe> SoakingJEIRecipes = WealizhModRecipeTypes.recipes.byType(SoakingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(SoakingJEI_Type, SoakingJEIRecipes);
		List<MixedSmeltingJEIRecipe> MixedSmeltingJEIRecipes = WealizhModRecipeTypes.recipes.byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(MixedSmeltingJEI_Type, MixedSmeltingJEIRecipes);
		List<InteractionJEIRecipe> InteractionJEIRecipes = WealizhModRecipeTypes.recipes.byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(InteractionJEI_Type, InteractionJEIRecipes);
		List<CrushJEIRecipe> CrushJEIRecipes = WealizhModRecipeTypes.recipes.byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CrushJEI_Type, CrushJEIRecipes);
		List<ForgingJEIRecipe> ForgingJEIRecipes = WealizhModRecipeTypes.recipes.byType(ForgingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(ForgingJEI_Type, ForgingJEIRecipes);
		List<DecayJEIRecipe> DecayJEIRecipes = WealizhModRecipeTypes.recipes.byType(DecayJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(DecayJEI_Type, DecayJEIRecipes);
		List<CompressJEIRecipe> CompressJEIRecipes = WealizhModRecipeTypes.recipes.byType(CompressJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CompressJEI_Type, CompressJEIRecipes);
		List<CentrifugalExtractionJEIRecipe> CentrifugalExtractionJEIRecipes = WealizhModRecipeTypes.recipes.byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(CentrifugalExtractionJEI_Type, CentrifugalExtractionJEIRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addCraftingStations(RapidQualitativeChangeMachineJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.RAPID_QUALITATIVE_CHANGE_MACHINE.get().asItem())));
		registration.addCraftingStations(QuantitativeTransformationReconstructorJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR.get().asItem())));
		registration.addCraftingStations(AssemblyJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.ASSEMBLY_TYPE_CONTROL_CONSOLE.get().asItem())));
		registration.addCraftingStations(SoakingJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.STEEL_BOX.get().asItem())));
		registration.addCraftingStations(MixedSmeltingJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.KILN.get().asItem())));
		registration.addCraftingStations(CrushJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.CRUSHER.get().asItem())));
		registration.addCraftingStations(ForgingJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.PRESSING_MACHINE.get().asItem())));
		registration.addCraftingStations(CompressJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.COMPRESSOR.get().asItem())));
		registration.addCraftingStations(CentrifugalExtractionJEI_Type, VanillaTypes.ITEM_STACK, List.of(new ItemStack(WealizhModBlocks.CENTRIFUGE.get().asItem())));
	}
}