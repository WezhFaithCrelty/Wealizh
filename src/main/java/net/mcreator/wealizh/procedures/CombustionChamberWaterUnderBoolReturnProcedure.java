package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CombustionChamberWaterUnderBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return Blocks.WATER == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() || Blocks.BUBBLE_COLUMN == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock();
	}
}