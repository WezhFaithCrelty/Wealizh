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
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.jei_recipes.CompressJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

import java.util.stream.Collectors;
import java.util.List;

public class CompressorGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("E_d", true);
				_blockEntity.getPersistentData().putDouble("Eu", 600);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 10, "");
		BlockPullRodDChangePProcedure.execute(world, x, y, z);
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") <= 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
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
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") > 0) {
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
			}
		}
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
			if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				int _slotid = 11;
				ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
				_stk.hurtAndBreak(1, _serverLevel, null, _stkprov -> {
				});
				_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
			}
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0) {
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
			List<CompressJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(CompressJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(CompressJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (CompressJEIRecipe recipe : recipes) {
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
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if (12 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
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
						for (int index0 = 0; index0 < 9; index0++) {
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
							_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() + itemstackiterator.getCount());
							_itemHandlerModifiable.setStackInSlot(0, _setstack);
						}
					}
					if (WealizhModBlocks.COMPRESSION_CHAMBER.get() == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()
							&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == itemstackiterator.getItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Blocks.AIR.asItem())
							&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount() && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0
							&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 11).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:piston_group"))) && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON")) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("time", (0.05 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
							if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") <= 0) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:piston_group_drive_mechanism")), SoundSource.BLOCKS, 1, 1);
									} else {
										_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:piston_group_drive_mechanism")), SoundSource.BLOCKS, 1, 1, false);
									}
								}
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null) {
										_blockEntity.getPersistentData().putDouble("sound_i", 2);
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
					} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
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
				}
				break;
			}
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "damage_l");
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		i = 1;
		for (int index1 = 0; index1 < 9; index1++) {
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