package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class BlocksExposureToJudgmentProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		return !((world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude() || world.getBlockState(BlockPos.containing(x + 1, y, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z)))
				&& (world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude() || world.getBlockState(BlockPos.containing(x - 1, y, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z)))
				&& (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || world.getBlockState(BlockPos.containing(x, y + 1, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z)))
				&& (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude() || world.getBlockState(BlockPos.containing(x, y - 1, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z)))
				&& (world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude() || world.getBlockState(BlockPos.containing(x, y, z + 1)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z)))
				&& (world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude() || world.getBlockState(BlockPos.containing(x, y, z - 1)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z))))
				|| entity.getPersistentData().getBooleanOr(("CB" + (x + 1) + y + z), false) || entity.getPersistentData().getBooleanOr(("CB" + (x - 1) + y + z), false) || entity.getPersistentData().getBooleanOr(("CB" + x + (y + 1) + z), false)
				|| entity.getPersistentData().getBooleanOr(("CB" + x + (y - 1) + z), false) || entity.getPersistentData().getBooleanOr(("CB" + x + y + (z + 1)), false) || entity.getPersistentData().getBooleanOr(("CB" + x + y + (z - 1)), false);
	}
}