package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class ItIsSelfConnectionBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double dx, double dy, double dz, double ox, double oy, double oz) {
		return (((getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "xa") == ox + dx || getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "xa2") == ox + dx) && ox != 0
				|| (getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "ya") == oy + dy || getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "ya2") == oy + dy) && oy != 0
				|| (getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "za") == oz + dz || getBlockNBTNumber(world, BlockPos.containing(dx, dy, dz), "za2") == oz + dz) && oz != 0)
				&& !(world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
				|| WealizhModBlocks.CONCENTRATED_WIRE.get() == (world.getBlockState(BlockPos.containing(dx, dy, dz))).getBlock()
						&& (!(world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
								|| WealizhModBlocks.CONCENTRATED_WIRE.get() == (world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).getBlock()
								|| (world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
										&& getBlockNBTNumber(world, BlockPos.containing(dx + ox, dy + oy, dz + oz), "xs") == dx && getBlockNBTNumber(world, BlockPos.containing(dx + ox, dy + oy, dz + oz), "ys") == dy
										&& getBlockNBTNumber(world, BlockPos.containing(dx + ox, dy + oy, dz + oz), "zs") == dz)
				|| WealizhModBlocks.CONCENTRATED_PIPE.get() == (world.getBlockState(BlockPos.containing(dx, dy, dz))).getBlock()
						&& (!(world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).is(BlockTags.create(ResourceLocation.parse("wealizh:pipe")))
								|| WealizhModBlocks.CONCENTRATED_PIPE.get() == (world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).getBlock()
								|| (world.getBlockState(BlockPos.containing(dx + ox, dy + oy, dz + oz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
										&& getBlockNBTNumber(world, BlockPos.containing(dx + ox, dy + oy, dz + oz), "xs") == dx && getBlockNBTNumber(world, BlockPos.containing(dx + ox, dy + oy, dz + oz), "ys") == dy
										&& getBlockNBTNumber(world, BlockPos.containing(dx + ox, dy + oy, dz + oz), "zs") == dz))
				&& !(getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz), "RS_Lcok") && world instanceof Level _level31 && _level31.hasNeighborSignal(BlockPos.containing(dx, dy, dz)));
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