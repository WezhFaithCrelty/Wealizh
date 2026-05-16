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

import net.mcreator.wealizh.jei_recipes.ForgingJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

import java.util.stream.Collectors;
import java.util.List;

public class PressingStorageRoomGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("E_d", true);
				_blockEntity.getPersistentData().putDouble("Eu", 450);
				_blockEntity.getPersistentData().putDouble("zhiliang", (5 < getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "FD_time") ? 0 : getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "FD_time") * (34d / 5)));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 4, "");
		BlockPullRodDChangePProcedure.execute(world, x, y, z);
		if (34 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zhiliang") && 34 > getBlockNBTNumber(world, BlockPos.containing(x, y, z), "damage_l")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("damage_l", (0.1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "damage_l")));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (WealizhModItems.STEEL_BILLETSMATERIAL.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 5).copy()).getItem() && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "damage_l") >= 10) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				int _slotid = 5;
				ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
				_stk.shrink(1);
				_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("damage_l", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "damage_l") - 20));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:loading_and_unloading")), SoundSource.BLOCKS, (float) 0.8, (float) 0.5);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:loading_and_unloading")), SoundSource.BLOCKS, (float) 0.8, (float) 0.5, false);
				}
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") == 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.05));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			PressingMachinePiLinFangKuaiGengXinShiProcedure.execute(world, x, y + 1, z);
		}
		{
			List<ForgingJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(ForgingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(ForgingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (ForgingJEIRecipe recipe : recipes) {
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
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if (WealizhModBlocks.PRESSING_MACHINE.get() == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()
							&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == itemstackiterator.getItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Blocks.AIR.asItem())
							&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount() && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0
							&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "damage_l") < 34 && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON")) {
						PressingMachinePiLinFangKuaiGengXinShiProcedure.execute(world, x, y + 1, z);
						if (getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), "FD_time") == 5) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("time", (32 / getBlockNBTNumber(world, BlockPos.containing(x, y, z), "damage_l") + Math.floor(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time"))));
									_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					} else {
						if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.05));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						PressingMachinePiLinFangKuaiGengXinShiProcedure.execute(world, x, y + 1, z);
					}
					if (11 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
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
						if (!(WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem())) {
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								int _slotid = 1;
								ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
								_stk.shrink(1);
								_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
							}
						}
						if (!(WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy()).getItem())) {
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								int _slotid = 2;
								ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
								_stk.shrink(1);
								_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
							}
						}
						if (!(WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy()).getItem())) {
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								int _slotid = 3;
								ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
								_stk.shrink(1);
								_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
							}
						}
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							ItemStack _setstack = itemstackiterator.copy();
							_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() + itemstackiterator.getCount());
							_itemHandlerModifiable.setStackInSlot(0, _setstack);
						}
						if (world instanceof Level _level)
							_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
					}
				}
				break;
			}
		}
		if (WealizhModBlocks.PRESSING_MACHINE.get() == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()) {
			i = 1;
			for (int index0 = 0; index0 < 3; index0++) {
				BlockItemsUpwardEntrySPProcedure.execute(world, x, y, z, i);
				i = 1 + i;
			}
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "damage_l");
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		i = 1;
		for (int index1 = 0; index1 < 3; index1++) {
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

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}