package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class NuclearBombExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		MushroomCloudSpawnPProcedure.execute(world, x + 0.5, y + 0.5, z + 0.5, 50);
	}
}