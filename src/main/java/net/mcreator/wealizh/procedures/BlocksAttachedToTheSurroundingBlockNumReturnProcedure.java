package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlocksAttachedToTheSurroundingBlockNumReturnProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double ASN = 0;
		if (world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude() || world.getBlockState(BlockPos.containing(x + 1, y, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x + 1, y, z))) {
			ASN = 1 + ASN;
		}
		if (world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude() || world.getBlockState(BlockPos.containing(x - 1, y, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x - 1, y, z))) {
			ASN = 1 + ASN;
		}
		if (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || world.getBlockState(BlockPos.containing(x, y + 1, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y + 1, z))) {
			ASN = 1 + ASN;
		}
		if (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude() || world.getBlockState(BlockPos.containing(x, y - 1, z)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y - 1, z))) {
			ASN = 1 + ASN;
		}
		if (world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude() || world.getBlockState(BlockPos.containing(x, y, z + 1)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y, z + 1))) {
			ASN = 1 + ASN;
		}
		if (world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude() || world.getBlockState(BlockPos.containing(x, y, z - 1)).isCollisionShapeFullBlock(world, BlockPos.containing(x, y, z - 1))) {
			ASN = 1 + ASN;
		}
		return ASN;
	}
}