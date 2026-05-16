package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;

public class DistanceCalculationProcedure {
	public static double execute(double x, double y, double z, double x2, double y2, double z2) {
		return (new Vec3(x, y, z)).distanceTo((new Vec3(x2, y2, z2)));
	}
}