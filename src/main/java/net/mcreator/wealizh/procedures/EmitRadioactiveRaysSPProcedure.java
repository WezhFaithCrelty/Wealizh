package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class EmitRadioactiveRaysSPProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, boolean DS, double Rad_num, double x2, double y2, double z2) {
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double DC = 0;
		double ax = 0;
		double ay = 0;
		double az = 0;
		double Rad_num_UT = 0;
		DC = DistanceCalculationProcedure.execute(x, y, z, x2, y2, z2);
		Rad_num_UT = Rad_num;
		dx = (x2 - x) / DC;
		dy = (y2 - y) / DC;
		dz = (z2 - z) / DC;
		ax = x;
		ay = y;
		az = z;
		for (int index0 = 0; index0 < (int) DC; index0++) {
			ax = dx + ax;
			ay = dy + ay;
			az = dz + az;
			Rad_num_UT = Rad_num_UT - (DS ? 0.05 : BlockRadiationAbsorptionAmountAttributeProcedure.execute(world.getBlockState(BlockPos.containing(ax, ay, az))));
		}
		return 0 < Rad_num_UT ? Rad_num_UT : 0;
	}
}