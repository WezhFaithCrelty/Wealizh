package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class DiedGrassRandomTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double nx = 0;
		double ny = 0;
		double nz = 0;
		DiedGrassBlocksDesertDustTurnPProcedure.execute(world, x, y, z);
		if (Mth.nextInt(RandomSource.create(), 1, 24) == 1) {
			if (Blocks.GRASS_BLOCK == (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()) {
				if (WealizhModBlocks.DIED_SHORT_GRASS.get() == blockstate.getBlock()) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.SHORT_GRASS.defaultBlockState(), 3);
				} else {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.TALL_GRASS.defaultBlockState(), 3);
				}
			}
		}
	}
}