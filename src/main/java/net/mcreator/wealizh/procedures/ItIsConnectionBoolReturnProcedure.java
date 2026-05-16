package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class ItIsConnectionBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double dx, double dy, double dz, double jx, double jy, double jz) {
		boolean Is_UWLV = false;
		Is_UWLV = (world.getBlockState(BlockPos.containing(jx, jy, jz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")));
		return (getBlockNBTNumber(world, BlockPos.containing(jx, jy, jz), (Is_UWLV ? "xs" : "xa")) == dx && getBlockNBTNumber(world, BlockPos.containing(jx, jy, jz), (Is_UWLV ? "ys" : "ya")) == dy
				&& getBlockNBTNumber(world, BlockPos.containing(jx, jy, jz), (Is_UWLV ? "zs" : "za")) == dz
				|| !Is_UWLV && getBlockNBTNumber(world, BlockPos.containing(jx, jy, jz), "xa2") == dx && getBlockNBTNumber(world, BlockPos.containing(jx, jy, jz), "ya2") == dy && getBlockNBTNumber(world, BlockPos.containing(jx, jy, jz), "za2") == dz
				|| (WealizhModBlocks.CONCENTRATED_WIRE.get() == (world.getBlockState(BlockPos.containing(jx, jy, jz))).getBlock()
						? WealizhModBlocks.CONCENTRATED_WIRE.get() == (world.getBlockState(BlockPos.containing(dx, dy, dz))).getBlock()
								|| getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "xs") == jx && getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "ys") == jy && getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "zs") == jz
						: WealizhModBlocks.CONCENTRATED_PIPE.get() == (world.getBlockState(BlockPos.containing(jx, jy, jz))).getBlock()
								&& (WealizhModBlocks.CONCENTRATED_PIPE.get() == (world.getBlockState(BlockPos.containing(dx, dy, dz))).getBlock() || getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "xs") == jx
										&& getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "ys") == jy && getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "zs") == jz)))
				&& !(getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz), "RS_Lock") && world instanceof Level _level23 && _level23.hasNeighborSignal(BlockPos.containing(dx, dy, dz)));
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}