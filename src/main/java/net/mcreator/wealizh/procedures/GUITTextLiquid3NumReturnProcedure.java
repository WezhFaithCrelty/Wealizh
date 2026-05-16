package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

public class GUITTextLiquid3NumReturnProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot3")).copy()).getItem() == Blocks.AIR.asItem()
				? "[]"
				: (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L_slot3")).copy()).getDisplayName().getString()) + "\n"
				+ ("" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L3")).replace(".0", "") + " / " + ("" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Lu3")).replace(".0", "") + " (mL)";
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