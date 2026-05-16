package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class GUILiquid2BarIndexReturnProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		return (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "L2") / getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Lu2")) * 52;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}