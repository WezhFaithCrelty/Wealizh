package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.wealizh.jei_recipes.InteractionJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.List;

@EventBusSubscriber
public class EatingLeftoversRePProcedure {
	@SubscribeEvent
	public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plr0 && _plr0.gameMode() == GameType.CREATIVE)) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = (new Object() {
					public ItemStack getResult() {
						List<InteractionJEIRecipe> recipes = null;
						if (world instanceof ServerLevel _lvl) {
							recipes = _lvl.recipeAccess().recipeMap().byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						} else {
							recipes = WealizhModRecipeTypes.recipes.byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						}
						for (InteractionJEIRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test(new ItemStack(WealizhModItems.EATING.get())))
								continue;
							if (!ingredients.get(1).test(new ItemStack(Blocks.AIR)))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
						return ItemStack.EMPTY;
					}
				}.getResult()).copy();
				_setstack.setCount((new Object() {
					public ItemStack getResult() {
						List<InteractionJEIRecipe> recipes = null;
						if (world instanceof ServerLevel _lvl) {
							recipes = _lvl.recipeAccess().recipeMap().byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						} else {
							recipes = WealizhModRecipeTypes.recipes.byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						}
						for (InteractionJEIRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test(new ItemStack(WealizhModItems.EATING.get())))
								continue;
							if (!ingredients.get(1).test(new ItemStack(Blocks.AIR)))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
						return ItemStack.EMPTY;
					}
				}.getResult()).getCount());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}