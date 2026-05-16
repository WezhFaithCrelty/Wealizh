package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BelowIsTheSolidBlockBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getBlockState(BlockPos.containing(x, y - 1, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z)) || world.getBlockState(BlockPos.containing(x, y, z)).canOcclude();
	}
}