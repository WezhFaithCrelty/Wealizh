package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class MoltenHighTemperatureSolidifiedSubstanceSurfaceGenerationDeterminationProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		for (int index0 = 0; index0 < 4; index0++) {
			if (!(world.getBlockState(BlockPos.containing(x, y + index0 + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x, y, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y + index0 + 1, z)))) {
				return true;
			}
		}
		return false;
	}
}