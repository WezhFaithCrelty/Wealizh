package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class IsItAttachedBlocksBoolReturnProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double dx = 0;
		double dy = 0;
		double dz = 0;
		if (Blocks.CAVE_AIR == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			return false;
		}
		if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
			dy = 1;
		} else if (Direction.UP == (getDirectionFromBlockState(blockstate))) {
			dy = -1;
		} else if (Direction.NORTH == (getDirectionFromBlockState(blockstate))) {
			dz = 1;
		} else if (Direction.SOUTH == (getDirectionFromBlockState(blockstate))) {
			dz = -1;
		} else if (Direction.WEST == (getDirectionFromBlockState(blockstate))) {
			dx = 1;
		} else {
			dx = -1;
		}
		return world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz)).isCollisionShapeFullBlock(world, BlockPos.containing(x + dx, y + dy, z + dz));
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}