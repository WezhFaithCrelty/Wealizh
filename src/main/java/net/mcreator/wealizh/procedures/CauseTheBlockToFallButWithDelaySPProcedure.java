package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.WealizhMod;

public class CauseTheBlockToFallButWithDelaySPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, double dn) {
		WealizhMod.queueServerWork((int) dn, () -> {
			if (world instanceof ServerLevel _level)
				FallingBlockEntity.fall(_level, BlockPos.containing(x, y, z), blockstate);
			world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		});
	}
}