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

public class SlotItemGetSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, boolean GN_ME, boolean SO_ME, boolean SS_ME, double get_num, double ox, double oy, double oz, double slot_o, double slot_s) {
		double get_num_U = 0;
		double can_get_num = 0;
		double can_give_num = 0;
		double slot_s_U = 0;
		double slot_o_U = 0;
		double i = 0;
		double j = 0;
		if (!(ox == x && oy == y && oz == z)) {
			get_num_U = get_num;
			slot_s_U = slot_s;
			slot_o_U = slot_o;
			if (!(SS_ME && SO_ME)) {
				for (int index0 = 0; index0 < getBlockInventorySlotCount(world, BlockPos.containing(x, y, z)); index0++) {
					for (int index1 = 0; index1 < getBlockInventorySlotCount(world, BlockPos.containing(ox, oy, oz)); index1++) {
						if (!GN_ME) {
							if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).getCount() + get_num > itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) j).getCount()) {
								can_get_num = getBlockInventorySlotStackLimit(world, BlockPos.containing(x, y, z), (int) i) - itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).getCount();
							}
							if (itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) j).getCount() - get_num < 0) {
								can_give_num = itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) j).getCount();
							}
							if (can_get_num > can_give_num) {
								get_num_U = can_give_num;
							} else {
								get_num_U = can_get_num;
							}
						}
						if (!SO_ME && world instanceof Level _level && _level.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(ox, oy, oz), null) instanceof IItemHandler _ih
								&& !_ih.extractItem((int) j, (int) get_num_U, true).isEmpty()) {
							slot_o_U = j;
						}
						if (!SS_ME && canInsertInBlockInventory(world, BlockPos.containing(x, y, z), (int) i, (int) get_num_U, ((itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) slot_o_U).copy()).copy()))
								&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U).copy()).getItem() == Blocks.AIR.asItem() || ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U).copy()).copy())
										.getItem() == ((itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) slot_o_U).copy()).copy()).getItem())) {
							slot_s_U = i;
						}
						j = 1 + j;
					}
					j = 0;
					i = 1 + i;
				}
			}
			if (canInsertInBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U, (int) get_num_U, ((itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) slot_o_U).copy()).copy())) && world instanceof Level _level
					&& _level.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(ox, oy, oz), null) instanceof IItemHandler _ih && !_ih.extractItem((int) slot_o_U, (int) get_num_U, true).isEmpty()
					&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U).copy()).getItem() == Blocks.AIR.asItem()
							|| ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U).copy()).copy()).getItem() == ((itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) slot_o_U).copy()).copy()).getItem())
					&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U).getCount() + get_num_U <= getBlockInventorySlotStackLimit(world, BlockPos.containing(x, y, z), (int) slot_o_U)) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = ((itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) slot_o_U).copy()).copy()).copy();
					_setstack.setCount((int) (get_num_U + itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot_s_U).getCount()));
					_itemHandlerModifiable.setStackInSlot((int) slot_s_U, _setstack);
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(ox, oy, oz), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = (int) slot_o_U;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink((int) get_num_U);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
			}
		}
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

	private static int getBlockInventorySlotStackLimit(LevelAccessor world, BlockPos pos, int slotId) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.getSlots())
				return itemHandler.getSlotLimit(slotId);
		}
		return 0;
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