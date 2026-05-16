package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class LogisticsPipeTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("xa", x);
				_blockEntity.getPersistentData().putDouble("xa2", x);
				_blockEntity.getPersistentData().putDouble("xs", x);
				_blockEntity.getPersistentData().putDouble("ya", y);
				_blockEntity.getPersistentData().putDouble("ya2", y);
				_blockEntity.getPersistentData().putDouble("ys", y);
				_blockEntity.getPersistentData().putDouble("za", z);
				_blockEntity.getPersistentData().putDouble("za2", z);
				_blockEntity.getPersistentData().putDouble("zs", z);
				_blockEntity.getPersistentData().putDouble("GO_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo") < 0 ? 64 : getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo")));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!(getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_Lock") && world instanceof Level _level13 && _level13.hasNeighborSignal(BlockPos.containing(x, y, z)))) {
			if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp18 && blockstate.getValue(_getbp18)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("ya", (y - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp21 && blockstate.getValue(_getbp21)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("ys", (y + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.UP == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp27 && blockstate.getValue(_getbp27)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("ya", (y + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp30 && blockstate.getValue(_getbp30)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("ys", (y - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.NORTH == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp36 && blockstate.getValue(_getbp36)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("za", (z - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp39 && blockstate.getValue(_getbp39)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("zs", (z + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp45 && blockstate.getValue(_getbp45)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("za", (z + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp48 && blockstate.getValue(_getbp48)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("zs", (z - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.WEST == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp54 && blockstate.getValue(_getbp54)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("xa", (x - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp57 && blockstate.getValue(_getbp57)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("xs", (x + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.EAST == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp63 && blockstate.getValue(_getbp63)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("xa", (x + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp66 && blockstate.getValue(_getbp66)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("xs", (x - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			SlotItemGetSPProcedure.execute(world, x, y, z, getBlockNBTLogic(world, BlockPos.containing(x, y, z), "GN_ME"), getBlockNBTLogic(world, BlockPos.containing(x, y, z), "SO_ME"), getBlockNBTLogic(world, BlockPos.containing(x, y, z), "SS_ME"),
					getBlockNBTNumber(world, BlockPos.containing(x, y, z), "GO_num"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"),
					getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "GSO"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "GSS"));
		}
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