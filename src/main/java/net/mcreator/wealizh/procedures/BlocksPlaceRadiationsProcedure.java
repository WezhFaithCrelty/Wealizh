package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;

public class BlocksPlaceRadiationsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double DV, double GS, double pow) {
		BsRadiationsPProcedure.execute(world, x + 0.5, y + 0.5, z + 0.5, DV, GS, pow);
	}
}