package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModParticleTypes;

public class DiedGrassBlockRandomTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double nx = 0;
		double ny = 0;
		double nz = 0;
		DiedGrassBlocksDesertDustTurnPProcedure.execute(world, x, y, z);
		if (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.DIRT.defaultBlockState(), 3);
		}
		if (Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.DUST_PARTICLES.get()), (x + 0.5), (y + 1), (z + 0.5), Mth.nextInt(RandomSource.create(), 1, 3), 1, 0, 1, 0.01);
		}
		if (Mth.nextInt(RandomSource.create(), 1, 256) == 1) {
			nx = x - 1;
			ny = y - 1;
			nz = z - 1;
			for (int index0 = 0; index0 < 3; index0++) {
				for (int index1 = 0; index1 < 3; index1++) {
					for (int index2 = 0; index2 < 3; index2++) {
						if (Blocks.GRASS_BLOCK == (world.getBlockState(BlockPos.containing(nx, ny, nz))).getBlock()) {
							world.setBlock(BlockPos.containing(x, y, z), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
						}
						nx = 1 + nx;
					}
					nx = x - 1;
					nz = 1 + nz;
				}
				nz = z - 1;
				ny = 1 + ny;
			}
		}
	}
}