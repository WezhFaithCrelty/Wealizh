package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class LargeScaleExplosionDamageDGPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double dx, double dy, double dz, double pow) {
		if (entity == null)
			return;
		BlockState block = Blocks.AIR.defaultBlockState();
		double nx = 0;
		double ny = 0;
		double nz = 0;
		double pow_c = 0;
		double Block_ER = 0;
		String Skey = "";
		nx = x;
		ny = y;
		nz = z;
		pow_c = pow;
		while (0 <= pow_c) {
			if (-64 > ny || 319 < ny) {
				return;
			}
			if (!world.isEmptyBlock(BlockPos.containing(nx, ny, nz))) {
				block = (world.getBlockState(BlockPos.containing(nx, ny, nz)));
				Block_ER = block.getBlock() instanceof LiquidBlock ? 0.2 : block.getBlock().getExplosionResistance();
				if (pow_c >= Block_ER) {
					Skey = Math.floor(nx) + "" + Math.floor(ny) + Math.floor(nz);
					if (!entity.getPersistentData().getBooleanOr(("CB" + Skey), false)) {
						entity.getPersistentData().putString(("B" + Skey), ("" + block));
						entity.getPersistentData().putBoolean(("CB" + Skey), true);
					}
				} else {
					return;
				}
			}
			pow_c = pow_c - (Mth.nextDouble(RandomSource.create(), 0, 20 / pow) + (1 >= Block_ER / 2 ? 1 : Block_ER / 2));
			nx = dx + nx;
			ny = dy + ny;
			nz = dz + nz;
		}
	}
}