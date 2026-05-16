package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class PollutionSourceNBTNumSetPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (WealizhModBlocks.TEMPORARY_BLOCK.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			entity.getPersistentData().putDouble("Rad_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_num")));
			entity.getPersistentData().putDouble("Rad_sc", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_sc")));
			entity.getPersistentData().putDouble("Rad_r", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_r")));
			entity.getPersistentData().putDouble("CoD_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_num")));
			entity.getPersistentData().putDouble("CoD_sc", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_sc")));
			entity.getPersistentData().putDouble("Tox_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_num")));
			entity.getPersistentData().putDouble("Tox_sc", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_sc")));
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}