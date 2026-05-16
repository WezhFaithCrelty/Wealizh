package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;

public class AirSeparatorGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("Eu", 400);
				_blockEntity.getPersistentData().putDouble("Lu", 2000);
				_blockEntity.getPersistentData().putDouble("np", 1);
				_blockEntity.getPersistentData().putDouble("np2", 5);
				_blockEntity.getPersistentData().putDouble("conv_num_max", 11);
				_blockEntity.getPersistentData().putBoolean("E_d", true);
				_blockEntity.getPersistentData().putBoolean("L_o", true);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 3, "");
		FluidOutputLToBlockPProcedure.execute(world, x, y, z, 0, "");
		if (0 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.NITROGEN.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.NITROGEN.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (1 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.OXYGEN.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.OXYGEN.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (2 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.ARGON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.ARGON_GAS.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (3 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.WATER.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.WATER.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (4 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.CARBON_DIOXIDE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.CARBON_DIOXIDE.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (5 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.NEON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.NEON_GAS.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (6 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.HELIUM_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.HELIUM_GAS.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (7 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.METHANE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.METHANE.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (8 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.KRYPTON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.KRYPTON_GAS.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (9 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.DINITROGEN_MONOXIDE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.DINITROGEN_MONOXIDE.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (10 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.XENON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.XENON_GAS.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		} else if (11 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "conv_num") && !(WealizhModItems.OZONE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem())) {
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				ItemStack _setstack = new ItemStack(WealizhModItems.OZONE.get()).copy();
				_setstack.setCount(1);
				_itemHandlerModifiable.setStackInSlot(4, _setstack);
			}
		}
		if (world instanceof Level _level55 && _level55.hasNeighborSignal(BlockPos.containing(x, y, z)) && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() && 0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E")
				&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Lu") && (Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()
						|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem())) {
			if (22 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
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
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).copy();
					_setstack.setCount(1);
					_itemHandlerModifiable.setStackInSlot(0, _setstack);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("L", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (0 >= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("ERi", 100);
						_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (WealizhModItems.NITROGEN.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (780.9 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.OXYGEN.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (209.5 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.ARGON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (9.3 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.WATER.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (world.getLevelData().isRaining()) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("time", (240 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if (world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("desert")) || world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("savanna"))
						|| world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("savanna_plateau"))) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("time", (0.002 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if (!(Level.NETHER == (world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD)))) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("time", (4 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if (WealizhModItems.CARBON_DIOXIDE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.41 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.NEON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.0182 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.HELIUM_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.00524 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.METHANE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.0017 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.KRYPTON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.00114 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.DINITROGEN_MONOXIDE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.00033 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.XENON_GAS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.000087 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.OZONE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 4).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", (0.00007 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
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
		} else if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
			if (22 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("time", 22);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.2));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, 4);
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