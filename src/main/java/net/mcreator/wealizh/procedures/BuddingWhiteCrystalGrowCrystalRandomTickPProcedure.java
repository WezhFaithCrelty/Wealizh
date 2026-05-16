package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;

public class BuddingWhiteCrystalGrowCrystalRandomTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure.execute(world, x, y, z, 1, 0, 0);
		BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure.execute(world, x, y, z, -1, 0, 0);
		BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure.execute(world, x, y, z, 0, 1, 0);
		BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure.execute(world, x, y, z, 0, -1, 0);
		BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure.execute(world, x, y, z, 0, 0, 1);
		BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure.execute(world, x, y, z, 0, 0, -1);
	}
}