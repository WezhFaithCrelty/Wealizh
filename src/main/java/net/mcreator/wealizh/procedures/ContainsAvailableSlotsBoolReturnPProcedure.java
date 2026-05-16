package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

public class ContainsAvailableSlotsBoolReturnPProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double i = 0;
		boolean clear_slot = false;
		for (int index0 = 0; index0 < getBlockInventorySlotCount(world, BlockPos.containing(x, y, z)); index0++) {
			if (Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem()) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = new ItemStack(Blocks.STRUCTURE_VOID).copy();
					_setstack.setCount(1);
					_itemHandlerModifiable.setStackInSlot((int) i, _setstack);
				}
				clear_slot = true;
			}
			if (canInsertInBlockInventory(world, BlockPos.containing(x, y, z), (int) i, 1, ItemStack.EMPTY)
					|| world instanceof Level _level && _level.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandler _ih && !_ih.extractItem((int) i, 1, true).isEmpty()) {
				if (clear_slot) {
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
						_itemHandlerModifiable.setStackInSlot((int) i, ItemStack.EMPTY);
				}
				return true;
			}
			if (clear_slot) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
					_itemHandlerModifiable.setStackInSlot((int) i, ItemStack.EMPTY);
			}
			i = 1 + i;
		}
		return false;
	}

	private static int getBlockInventorySlotCount(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getSlots();
		}
		return 0;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	public static boolean canInsertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.getSlots()) {
				itemstack.setCount(amount);
				return itemHandler.isItemValid(slotId, itemstack);
			}
		}
		return false;
	}
}