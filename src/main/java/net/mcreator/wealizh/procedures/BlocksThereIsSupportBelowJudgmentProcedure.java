package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class BlocksThereIsSupportBelowJudgmentProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		for (int index0 = 0; index0 < 4; index0++) {
			if ((world.isEmptyBlock(BlockPos.containing(x, y - (index0 + 1), z)) || entity.getPersistentData().getBooleanOr(("CB" + x + (y - (index0 + 1)) + z), false))
					&& !(Blocks.VOID_AIR == (world.getBlockState(BlockPos.containing(x, y - (index0 + 1), z))).getBlock())) {
				return true;
			}
		}
		return false;
	}
}