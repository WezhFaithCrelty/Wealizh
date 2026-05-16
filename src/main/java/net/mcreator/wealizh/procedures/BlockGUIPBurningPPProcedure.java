package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

public class BlockGUIPBurningPPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double bur_fs, double bur_slot) {
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("bur", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") - 13 / getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur_f")));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") < 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("bur", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if ((world instanceof Level _levelFV7 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) bur_slot).copy()).getBurnTime(null, _levelFV7.fuelValues()) : 0) > 0
				&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") == 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("bur_f",
							((world instanceof Level _levelFV10 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) bur_slot).copy()).getBurnTime(null, _levelFV10.fuelValues()) : 0) / bur_fs));
					_blockEntity.getPersistentData().putDouble("bur", 13);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (Items.LAVA_BUCKET == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) bur_slot).copy()).getItem()) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
					_setstack.setCount(1);
					_itemHandlerModifiable.setStackInSlot((int) bur_slot, _setstack);
				}
			} else {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = 0;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
			}
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
}