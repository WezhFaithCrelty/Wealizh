package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.wealizh.jei_recipes.CentrifugalExtractionJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

import java.util.stream.Collectors;
import java.util.List;

public class CentrifugeGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double oi = 0;
		double io_bool = 0;
		double ioi_bool = 0;
		double oL_d = 0;
		double i = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("E_d", true);
				_blockEntity.getPersistentData().putBoolean("L_d", true);
				_blockEntity.getPersistentData().putBoolean("L_o2", true);
				_blockEntity.getPersistentData().putBoolean("L_o3", true);
				_blockEntity.getPersistentData().putDouble("np", 1);
				_blockEntity.getPersistentData().putDouble("np2", 5);
				_blockEntity.getPersistentData().putDouble("np3", 8);
				_blockEntity.getPersistentData().putDouble("L_slot", 0);
				_blockEntity.getPersistentData().putDouble("L_slot2", 4);
				_blockEntity.getPersistentData().putDouble("L_slot3", 7);
				_blockEntity.getPersistentData().putDouble("AP_num", 0);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		for (int index0 = 0; index0 < 24; index0++) {
			i = 1 + i;
			if (WealizhModBlocks.CENTRIFUGE_AROUND_PIPE.get() == (world.getBlockState(BlockPos.containing(x, y + i, z))).getBlock()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("AP_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else {
				break;
			}
		}
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("Eu", (800 + 50 * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
				_blockEntity.getPersistentData().putDouble("Lu", (8000 * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
				_blockEntity.getPersistentData().putDouble("Lu2", (4000 * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
				_blockEntity.getPersistentData().putDouble("Lu3", (4000 * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 12, "");
		FluidInputLToBlockPProcedure.execute(world, x, y, z, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot"), "");
		FluidOutputLToBlockPProcedure.execute(world, x, y, z, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot"), "");
		FluidOutputLToBlockPProcedure.execute(world, x, y, z, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot2"), "2");
		FluidOutputLToBlockPProcedure.execute(world, x, y, z, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot3"), "3");
		{
			List<CentrifugalExtractionJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (CentrifugalExtractionJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot")).copy())))
					continue;
				if (!ingredients.get(1)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					oi = 1 + oi;
					if (2 >= oi && !(itemstackiterator.getItem() == Blocks.AIR.asItem() || itemstackiterator.getItem() == WealizhModItems.EMPTY.get())) {
						oL_d = 1 + oL_d;
					}
				}
				break;
			}
		}
		{
			List<CentrifugalExtractionJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (CentrifugalExtractionJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot")).copy())))
					continue;
				if (!ingredients.get(1)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					ioi_bool = 1 + ioi_bool;
					if (2 >= ioi_bool
							&& (((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L_slot" + ("" + (ioi_bool + 1)).replace(".0", "")))).copy()).getItem() == Blocks.AIR.asItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L_slot" + ("" + (ioi_bool + 1)).replace(".0", "")))).copy())
											.getItem() == itemstackiterator.getItem())
									&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L" + ("" + (ioi_bool + 1)).replace(".0", ""))) <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Lu" + ("" + (ioi_bool + 1)).replace(".0", "")))
											- (1000 / oL_d) * itemstackiterator.getCount()
									&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L") >= 1000
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot")).copy()).getItem() == Blocks.AIR.asItem())) {
						io_bool = 1 + io_bool;
					}
					if (2 < ioi_bool && (((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (ioi_bool + 7)).copy()).getItem() == Blocks.AIR.asItem()
							|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (ioi_bool + 7)).copy()).getItem() == itemstackiterator.getItem())
							&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (ioi_bool + 7)).getCount() < itemstackiterator.getMaxStackSize() - itemstackiterator.getCount()
							|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == Blocks.AIR.asItem())) {
						io_bool = 1 + io_bool;
					}
				}
				break;
			}
		}
		oi = 1;
		{
			List<CentrifugalExtractionJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(CentrifugalExtractionJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (CentrifugalExtractionJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot")).copy())))
					continue;
				if (!ingredients.get(1)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
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
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if (2 >= oi && !(WealizhModItems.EMPTY.get() == itemstackiterator.getItem())
								&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L_slot" + ("" + (oi + 1)).replace(".0", "")))).copy()).getItem() == Blocks.AIR.asItem()
										|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L_slot" + ("" + (oi + 1)).replace(".0", "")))).copy())
												.getItem() == itemstackiterator.getItem())) {
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								ItemStack _setstack = itemstackiterator.copy();
								_setstack.setCount(1);
								_itemHandlerModifiable.setStackInSlot((int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L_slot" + ("" + (oi + 1)).replace(".0", ""))), _setstack);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble(("L" + ("" + (oi + 1)).replace(".0", "")), (1000 / oL_d + getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L" + ("" + (oi + 1)).replace(".0", "")))));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if (2 < oi && !((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == Blocks.AIR.asItem())) {
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								ItemStack _setstack = itemstackiterator.copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (oi + 7)).getCount() + itemstackiterator.getCount());
								_itemHandlerModifiable.setStackInSlot((int) (oi + 7), _setstack);
							}
						}
						oi = 1 + oi;
					}
					if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot")).copy()).getItem() == Blocks.AIR.asItem())) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("L", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L") - 1000));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (0 >= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L")) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("L", 0);
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
								_itemHandlerModifiable.setStackInSlot((int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot"), ItemStack.EMPTY);
						}
					}
					if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == Blocks.AIR.asItem()
							|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem() == WealizhModItems.SLOT_OCCUPANCY_PLATE.get())) {
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							int _slotid = 3;
							ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
							_stk.shrink(1);
							_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
						}
					}
				}
				if (world instanceof Level _level113 && _level113.hasNeighborSignal(BlockPos.containing(x, y, z)) && io_bool == ioi_bool && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("time", (0.01 * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num") + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi") <= 0) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("ERi", (60 / getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
								_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("ERi", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi") - 1));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0) {
						if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") <= 0) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:centrifuge_function")), SoundSource.BLOCKS, 1, 1);
								} else {
									_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:centrifuge_function")), SoundSource.BLOCKS, 1, 1, false);
								}
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("sound_i", 120);
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
									_blockEntity.getPersistentData().putDouble("sound_i", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") - 1));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				} else {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("sound_i", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.005 * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "AP_num")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				}
				break;
			}
		}
		if (ioi_bool == 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("sound_i", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((x + 0.5), (y + 0.5), (z + 0.5)), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"stopsound @a[distance=..16] block wealizh:centrifuge_function");
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot"));
		ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, 3);
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}
}