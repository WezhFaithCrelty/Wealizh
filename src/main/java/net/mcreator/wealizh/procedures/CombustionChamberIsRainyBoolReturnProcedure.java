package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CombustionChamberIsRainyBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getLevelData().isRaining() && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y + 1, z));
	}
}