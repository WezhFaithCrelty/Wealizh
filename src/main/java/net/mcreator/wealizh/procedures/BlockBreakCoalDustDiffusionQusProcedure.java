package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.wealizh.WealizhMod;

public class BlockBreakCoalDustDiffusionQusProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double CoD) {
		double Block_CoD_Num = 0;
		WealizhMod.queueServerWork(1, () -> {
			PollutionSourceSpawnPProcedure.execute(world, x + 0.5, y + 0.5, z + 0.5, CoD, 0, 0);
		});
	}
}