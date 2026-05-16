package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class OriginInitialsPowAssignmentPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double phi = 0;
		double Rays_num = 0;
		double pow = 0;
		double theta = 0;
		double sin_phi = 0;
		boolean CB = false;
		if (WealizhModBlocks.TEMPORARY_BLOCK.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			entity.getPersistentData().putDouble("pow", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "A_pow")));
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}