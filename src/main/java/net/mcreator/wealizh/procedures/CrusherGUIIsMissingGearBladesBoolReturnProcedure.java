package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class CrusherGUIIsMissingGearBladesBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double i = 0;
		i = 3;
		for (int index0 = 0; index0 < 6; index0++) {
			if (!(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:gear_blade")))) {
				return true;
			}
			i = 1 + i;
		}
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