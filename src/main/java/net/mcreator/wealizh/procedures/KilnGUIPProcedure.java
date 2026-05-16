package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.jei_recipes.MixedSmeltingJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import java.util.stream.Collectors;
import java.util.List;

public class KilnGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("np", 9);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (Blocks.FIRE == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()
				|| Blocks.CAMPFIRE == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() && getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "lit") instanceof BooleanProperty _getbp6
						&& (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getbp6)
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(ResourceLocation.parse("wealizh:can_give_cv")))
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "function") instanceof BooleanProperty _getbp10 && (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getbp10)
						&& !(getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "function_soul") instanceof BooleanProperty _getbp12 && (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getbp12))) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.BURN.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(8, _setstack);
			}
		} else if (Blocks.SOUL_FIRE == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()
				|| Blocks.SOUL_CAMPFIRE == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() && getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "lit") instanceof BooleanProperty _getbp19
						&& (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getbp19)
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(ResourceLocation.parse("wealizh:can_give_cv")))
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y - 1, z))), "function_soul") instanceof BooleanProperty _getbp23 && (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getbp23)) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.SOUL_BURN.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(8, _setstack);
			}
		} else {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
				_itemHandlerModifiable.setStackInSlot(8, ItemStack.EMPTY);
		}
		if ((!(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:burn"))) && getPropertyByName(blockstate, "open") instanceof BooleanProperty _getbp29
				&& blockstate.getValue(_getbp29) || (new Object() {
					public ItemStack getResult() {
						List<MixedSmeltingJEIRecipe> recipes = null;
						if (world instanceof ServerLevel _lvl) {
							recipes = _lvl.recipeAccess().recipeMap().byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						} else {
							recipes = WealizhModRecipeTypes.recipes.byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
						}
						for (MixedSmeltingJEIRecipe recipe : recipes) {
							List<Ingredient> ingredients = recipe.getIngredients();
							if (!ingredients.get(0)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
								continue;
							if (!ingredients.get(1)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
								continue;
							if (!ingredients.get(2)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
								continue;
							if (!ingredients.get(3)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy())))
								continue;
							if (!ingredients.get(4)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy())))
								continue;
							if (!ingredients.get(5)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy())))
								continue;
							if (!ingredients.get(6)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 7).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 7).copy())))
								continue;
							if (!ingredients.get(7)
									.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy()).getItem()
											? new ItemStack(Blocks.AIR)
											: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy())))
								continue;
							return recipe.getResultItems().get(0).copy();
						}
						return ItemStack.EMPTY;
					}
				}.getResult()).getItem() == Blocks.AIR.asItem()) && 0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.15));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		BlockOpenCloseRDChangePProcedure.execute(world, x, y, z, blockstate);
		{
			List<MixedSmeltingJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (MixedSmeltingJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
					continue;
				if (!ingredients.get(1)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
					continue;
				if (!ingredients.get(2)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
					continue;
				if (!ingredients.get(3)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy())))
					continue;
				if (!ingredients.get(4)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy())))
					continue;
				if (!ingredients.get(5)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy())))
					continue;
				if (!ingredients.get(6)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 7).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 7).copy())))
					continue;
				if (!ingredients.get(7)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if (64 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("time", 0);
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						for (int index0 = 0; index0 < 7; index0++) {
							i = 1 + i;
							if (!(WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem())) {
								if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
									int _slotid = (int) i;
									ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
									_stk.shrink(1);
									_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
								}
							}
						}
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							ItemStack _setstack = itemstackiterator.copy();
							_setstack.setCount(itemstackiterator.getCount() + itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount());
							_itemHandlerModifiable.setStackInSlot(0, _setstack);
						}
					}
					if (!(getPropertyByName(blockstate, "open") instanceof BooleanProperty _getbp71 && blockstate.getValue(_getbp71))
							&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount()
							&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == itemstackiterator.getItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Blocks.AIR.asItem())) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("time", ((WealizhModItems.SOUL_BURN.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy()).getItem() && !((new Object() {
									public ItemStack getResult() {
										List<MixedSmeltingJEIRecipe> recipes = null;
										if (world instanceof ServerLevel _lvl) {
											recipes = _lvl.recipeAccess().recipeMap().byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
										} else {
											recipes = WealizhModRecipeTypes.recipes.byType(MixedSmeltingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
										}
										for (MixedSmeltingJEIRecipe recipe : recipes) {
											List<Ingredient> ingredients = recipe.getIngredients();
											if (!ingredients.get(0)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy())))
												continue;
											if (!ingredients.get(1)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy())))
												continue;
											if (!ingredients.get(2)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
												continue;
											if (!ingredients.get(3)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy())))
												continue;
											if (!ingredients.get(4)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy())))
												continue;
											if (!ingredients.get(5)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 6).copy())))
												continue;
											if (!ingredients.get(6)
													.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 7).copy()).getItem()
															? new ItemStack(Blocks.AIR)
															: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 7).copy())))
												continue;
											if (!ingredients.get(7).test(new ItemStack(WealizhModItems.BURN.get())))
												continue;
											return recipe.getResultItems().get(0).copy();
										}
										return ItemStack.EMPTY;
									}
								}.getResult()).getItem() == Blocks.AIR.asItem()) ? 0.2 : 0.1) + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.15));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				}
				break;
			}
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		i = 1;
		for (int index1 = 0; index1 < 7; index1++) {
			ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, i);
			i = 1 + i;
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}