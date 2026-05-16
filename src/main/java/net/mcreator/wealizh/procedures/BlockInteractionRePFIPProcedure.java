package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.ProblemReporter;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.jei_recipes.InteractionJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.List;

@EventBusSubscriber
public class BlockInteractionRePFIPProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean place_block = false;
		if (!entity.isShiftKeyDown()) {
			{
				List<InteractionJEIRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = WealizhModRecipeTypes.recipes.byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (InteractionJEIRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()))))
						continue;
					if (!ingredients.get(1).test((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)))
						continue;
					if (!ingredients.get(2).test((Blocks.AIR.asItem() == (new Object() {
						public ItemStack getResult() {
							List<InteractionJEIRecipe> recipes = null;
							if (world instanceof ServerLevel _lvl) {
								recipes = _lvl.recipeAccess().recipeMap().byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
							} else {
								recipes = WealizhModRecipeTypes.recipes.byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
							}
							for (InteractionJEIRecipe recipe : recipes) {
								List<Ingredient> ingredients = recipe.getIngredients();
								if (!ingredients.get(0).test((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()))))
									continue;
								if (!ingredients.get(1).test((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)))
									continue;
								if (!ingredients.get(2).test((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)))
									continue;
								return recipe.getResultItems().get(0).copy();
							}
							return ItemStack.EMPTY;
						}
					}.getResult()).getItem() ? new ItemStack(Blocks.AIR) : (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY))))
						continue;
					List<ItemStack> reciperesult = recipe.getResultItems();
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId((world.getBlockState(BlockPos.containing(x, y, z)))));
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if ((itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()).getBlock() == Blocks.AIR || place_block) {
							if (!(entity instanceof Player _plr8 && _plr8.gameMode() == GameType.CREATIVE)) {
								if (world instanceof ServerLevel _level) {
									(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
									});
								}
								if (entity instanceof Player _player) {
									ItemStack _setstack = itemstackiterator.copy();
									_setstack.setCount(1);
									ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
								}
							}
						} else {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								for (Property<?> _propertyOld : _bso.getProperties()) {
									Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
									if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
										try {
											_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
										} catch (Exception e) {
										}
								}
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
										} catch (Exception ignored) {
										}
									}
								}
							}
							place_block = true;
						}
					}
					break;
				}
			}
			place_block = false;
			{
				List<InteractionJEIRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = WealizhModRecipeTypes.recipes.byType(InteractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (InteractionJEIRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()))))
						continue;
					if (!ingredients.get(1).test(new ItemStack(WealizhModItems.HAND_INTERACTION.get())))
						continue;
					if (!ingredients.get(2).test((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)))
						continue;
					List<ItemStack> reciperesult = recipe.getResultItems();
					if (entity instanceof LivingEntity _entity)
						_entity.swing(InteractionHand.MAIN_HAND, true);
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId((world.getBlockState(BlockPos.containing(x, y, z)))));
					if (!(entity instanceof Player _plr31 && _plr31.gameMode() == GameType.CREATIVE)) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
						}
					}
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if ((itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()).getBlock() == Blocks.AIR || place_block) {
							if (!(entity instanceof Player _plr38 && _plr38.gameMode() == GameType.CREATIVE)) {
								if (world instanceof ServerLevel _level) {
									(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak(1, _level, null, _stkprov -> {
									});
								}
								if (entity instanceof Player _player) {
									ItemStack _setstack = itemstackiterator.copy();
									_setstack.setCount(1);
									ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
								}
							}
						} else {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								for (Property<?> _propertyOld : _bso.getProperties()) {
									Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
									if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
										try {
											_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
										} catch (Exception e) {
										}
								}
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
										} catch (Exception ignored) {
										}
									}
								}
							}
							place_block = true;
						}
					}
					break;
				}
			}
		}
	}
}