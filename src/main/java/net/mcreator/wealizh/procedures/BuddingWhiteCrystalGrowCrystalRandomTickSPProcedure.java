package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class BuddingWhiteCrystalGrowCrystalRandomTickSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double dx, double dy, double dz) {
		Direction direction = Direction.NORTH;
		if (dx > 0) {
			direction = Direction.EAST;
		} else if (dx < 0) {
			direction = Direction.WEST;
		} else if (dy > 0) {
			direction = Direction.UP;
		} else if (dy < 0) {
			direction = Direction.DOWN;
		} else if (dz > 0) {
			direction = Direction.SOUTH;
		} else {
			direction = Direction.NORTH;
		}
		if (Mth.nextInt(RandomSource.create(), 1, 12) == 1) {
			if (world.isEmptyBlock(BlockPos.containing(x + dx, y + dy, z + dz))) {
				world.setBlock(BlockPos.containing(x + dx, y + dy, z + dz), WealizhModBlocks.SMALL_WHITE_CRYSTAL_BUD.get().defaultBlockState(), 3);
				{
					Direction _dir = direction;
					BlockPos _pos = BlockPos.containing(x + dx, y + dy, z + dz);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("facing") instanceof EnumProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else if (_bs.getBlock().getStateDefinition().getProperty("axis") instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis())) {
						world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			}
			if (direction == (getDirectionFromBlockState((world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz)))))) {
				if (WealizhModBlocks.SMALL_WHITE_CRYSTAL_BUD.get() == (world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getBlock()) {
					{
						BlockPos _bp = BlockPos.containing(x + dx, y + dy, z + dz);
						BlockState _bs = WealizhModBlocks.MEDIUM_WHITE_CRYSTAL_BUD.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				} else if (WealizhModBlocks.MEDIUM_WHITE_CRYSTAL_BUD.get() == (world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getBlock()) {
					{
						BlockPos _bp = BlockPos.containing(x + dx, y + dy, z + dz);
						BlockState _bs = WealizhModBlocks.LARGE_WHITE_CRYSTAL_BUD.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				} else if (WealizhModBlocks.LARGE_WHITE_CRYSTAL_BUD.get() == (world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getBlock()) {
					{
						BlockPos _bp = BlockPos.containing(x + dx, y + dy, z + dz);
						BlockState _bs = WealizhModBlocks.WHITE_CRYSTAL_CLUSTER.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
		}
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