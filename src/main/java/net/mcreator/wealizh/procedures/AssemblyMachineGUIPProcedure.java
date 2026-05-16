package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.jei_recipes.AssemblyJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import java.util.stream.Collectors;
import java.util.List;

public class AssemblyMachineGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("E_d", true);
				_blockEntity.getPersistentData().putDouble("Eu", 450);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		if (AssemblyMachineITFProcedure.execute(world, x, y, z, blockstate)) {
			BlockONOFFRDChangePProcedure.execute(world, x, y, z);
			BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 21, "");
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi") <= 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("ERi", 20);
						_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0 && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON")) {
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
			{
				List<AssemblyJEIRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(AssemblyJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = WealizhModRecipeTypes.recipes.byType(AssemblyJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (AssemblyJEIRecipe recipe : recipes) {
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
					if (!ingredients.get(8)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 9).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 9).copy())))
						continue;
					if (!ingredients.get(9)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 10).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 10).copy())))
						continue;
					if (!ingredients.get(10)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 11).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 11).copy())))
						continue;
					if (!ingredients.get(11)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 12).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 12).copy())))
						continue;
					if (!ingredients.get(12)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 13).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 13).copy())))
						continue;
					if (!ingredients.get(13)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 14).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 14).copy())))
						continue;
					if (!ingredients.get(14)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 15).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 15).copy())))
						continue;
					if (!ingredients.get(15)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 16).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 16).copy())))
						continue;
					if (!ingredients.get(16)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 17).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 17).copy())))
						continue;
					if (!ingredients.get(17)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 18).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 18).copy())))
						continue;
					if (!ingredients.get(18)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 19).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 19).copy())))
						continue;
					if (!ingredients.get(19)
							.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 20).copy()).getItem()
									? new ItemStack(Blocks.AIR)
									: itemFromBlockInventory(world, BlockPos.containing(x, y, z), 20).copy())))
						continue;
					List<ItemStack> reciperesult = recipe.getResultItems();
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if (24 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
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
							for (int index0 = 0; index0 < 20; index0++) {
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
						if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON") && 0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E")
								&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount()
								&& (itemstackiterator.getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()
										|| Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem())) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("time", (0.25 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0 && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON")) {
								if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") <= 0) {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:assembly")), SoundSource.BLOCKS, 1, 1);
										} else {
											_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:assembly")), SoundSource.BLOCKS, 1, 1, false);
										}
									}
									if (!world.isClientSide()) {
										BlockPos _bp = BlockPos.containing(x, y, z);
										BlockEntity _blockEntity = world.getBlockEntity(_bp);
										BlockState _bs = world.getBlockState(_bp);
										if (_blockEntity != null) {
											_blockEntity.getPersistentData().putDouble("sound_i", 15);
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
									_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.5));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
					break;
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
						_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.25));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
		i = 1;
		for (int index1 = 0; index1 < 20; index1++) {
			ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, i);
			i = 1 + i;
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
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