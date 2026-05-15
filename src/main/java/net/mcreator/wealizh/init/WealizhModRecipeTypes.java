package net.mcreator.wealizh.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.wealizh.jei_recipes.*;

@EventBusSubscriber
public class WealizhModRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "wealizh");
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "wealizh");
	public static RecipeMap recipes = null;

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		IEventBus bus = ModList.get().getModContainerById("wealizh").get().getEventBus();
		event.enqueueWork(() -> {
			RECIPE_TYPES.register(bus);
			SERIALIZERS.register(bus);
			RECIPE_TYPES.register("rapid_qualitative_change_machine_jei", () -> RapidQualitativeChangeMachineJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("rapid_qualitative_change_machine_jei", () -> RapidQualitativeChangeMachineJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("qualitative_change_jei", () -> QualitativeChangeJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("qualitative_change_jei", () -> QualitativeChangeJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("quantitative_transformation_jei", () -> QuantitativeTransformationReconstructorJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("quantitative_transformation_jei", () -> QuantitativeTransformationReconstructorJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("assembly_jei", () -> AssemblyJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("assembly_jei", () -> AssemblyJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("soaking_jei", () -> SoakingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("soaking_jei", () -> SoakingJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("mixed_smelting_jei", () -> MixedSmeltingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("mixed_smelting_jei", () -> MixedSmeltingJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("interaction_jei", () -> InteractionJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("interaction_jei", () -> InteractionJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("crush_jei", () -> CrushJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("crush_jei", () -> CrushJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("forging_jei", () -> ForgingJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("forging_jei", () -> ForgingJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("decay_jei", () -> DecayJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("decay_jei", () -> DecayJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("compress_jei", () -> CompressJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("compress_jei", () -> CompressJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("centrifugal_extraction_jei", () -> CentrifugalExtractionJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("centrifugal_extraction_jei", () -> CentrifugalExtractionJEIRecipe.Serializer.INSTANCE);
		});
	}

	@SubscribeEvent
	public static void syncRecipes(OnDatapackSyncEvent event) {
		event.sendRecipes(RapidQualitativeChangeMachineJEIRecipe.Type.INSTANCE);
		event.sendRecipes(QualitativeChangeJEIRecipe.Type.INSTANCE);
		event.sendRecipes(QuantitativeTransformationReconstructorJEIRecipe.Type.INSTANCE);
		event.sendRecipes(AssemblyJEIRecipe.Type.INSTANCE);
		event.sendRecipes(SoakingJEIRecipe.Type.INSTANCE);
		event.sendRecipes(MixedSmeltingJEIRecipe.Type.INSTANCE);
		event.sendRecipes(InteractionJEIRecipe.Type.INSTANCE);
		event.sendRecipes(CrushJEIRecipe.Type.INSTANCE);
		event.sendRecipes(ForgingJEIRecipe.Type.INSTANCE);
		event.sendRecipes(DecayJEIRecipe.Type.INSTANCE);
		event.sendRecipes(CompressJEIRecipe.Type.INSTANCE);
		event.sendRecipes(CentrifugalExtractionJEIRecipe.Type.INSTANCE);
	}

	@EventBusSubscriber(value = Dist.CLIENT)
	public static class RecipeReceiver {
		@SubscribeEvent
		public static void receiveRecipes(RecipesReceivedEvent event) {
			recipes = event.getRecipeMap();
		}

		@SubscribeEvent
		public static void clearRecipes(ClientPlayerNetworkEvent.LoggingOut event) {
			recipes = null;
		}
	}
}