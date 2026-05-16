package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class AluminumStorageBatteryGUIColorfulChangeBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return WealizhModBlocks.ALUMINUM_STORAGE_BATTERY.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock();
	}
}