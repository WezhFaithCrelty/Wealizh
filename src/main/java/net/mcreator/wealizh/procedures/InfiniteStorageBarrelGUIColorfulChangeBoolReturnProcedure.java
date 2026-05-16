package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class InfiniteStorageBarrelGUIColorfulChangeBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return WealizhModBlocks.INFINITE_STORAGE_BARREL.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock();
	}
}