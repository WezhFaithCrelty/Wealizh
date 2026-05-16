package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.jei_recipes.QualitativeChangeJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

@EventBusSubscriber
public class ItemQualitativeChangeProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
			for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
				ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
				if (Mth.nextInt(RandomSource.create(), 1, (int) (ItemQualitativeChangeAttributeProcedure.execute(itemstackiterator) / itemstackiterator.getCount())) == 1 && !((new Object() {
					public ItemStack getResult() {
						List<QualitativeChangeJEIRecipe> recipes = null;
						if (world instanceof ServerLevel _lvl) {
							recipes = _lvl.recipeAccess().recipeMap().byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						} else {
							recipes = WealizhModRecipeTypes.recipes.byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						}
						for (QualitativeChangeJEIRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0).test(itemstackiterator))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
						return ItemStack.EMPTY;
					}
				}.getResult()).getItem() == Blocks.AIR.asItem())) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:qualitative_change"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = itemstackiterator;
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = (new Object() {
							public ItemStack getResult() {
								List<QualitativeChangeJEIRecipe> recipes = null;
								if (world instanceof ServerLevel _lvl) {
									recipes = _lvl.recipeAccess().recipeMap().byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								} else {
									recipes = WealizhModRecipeTypes.recipes.byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								}
								for (QualitativeChangeJEIRecipe recipe : recipes) {
									List<Ingredient> ingredients = recipe.getIngredients();
									if (!ingredients.get(0).test(itemstackiterator))
										continue;
									return recipe.getResultItems().get(0).copy();
								}
								return ItemStack.EMPTY;
							}
						}.getResult()).copy();
						_setstack.setCount((new Object() {
							public ItemStack getResult() {
								List<QualitativeChangeJEIRecipe> recipes = null;
								if (world instanceof ServerLevel _lvl) {
									recipes = _lvl.recipeAccess().recipeMap().byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								} else {
									recipes = WealizhModRecipeTypes.recipes.byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
								}
								for (QualitativeChangeJEIRecipe recipe : recipes) {
									List<Ingredient> ingredients = recipe.getIngredients();
									if (!ingredients.get(0).test(itemstackiterator))
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
		{
			List<QualitativeChangeJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (QualitativeChangeJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY)))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if (Mth.nextInt(RandomSource.create(), 1, (int) (ItemQualitativeChangeAttributeProcedure.execute(entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY)
							/ (entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount())) == 1) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, itemstackiterator);
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
						(entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).shrink(1);
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, (entity instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
						for (Entity entityiterator : world.getEntities(null, new AABB((x - 0.1), (y - 0.1), (z - 0.1), (x + 0.1), (y + 0.1), (z + 0.1)))) {
							if (("minecraft:item").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString())) {
								entityiterator.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), (entity.getDeltaMovement().y()), (entity.getDeltaMovement().z())));
							}
						}
						if (!entity.level().isClientSide())
							entity.discard();
						{
							final Vec3 _center = new Vec3(x, y, z);
							for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(25 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
								if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
									AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:qualitative_change"));
									if (_adv != null) {
										AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
										if (!_ap.isDone()) {
											for (String criteria : _ap.getRemainingCriteria())
												_player.getAdvancements().award(_adv, criteria);
										}
									}
								}
							}
						}
					}
				}
				break;
			}
		}
	}
}