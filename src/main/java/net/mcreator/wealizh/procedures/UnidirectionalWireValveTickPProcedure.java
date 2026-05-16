package net.mcreator.wealizh.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class UnidirectionalWireValveTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double give_num = 0;
		double i = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("xa", x);
				_blockEntity.getPersistentData().putDouble("xa2", x);
				_blockEntity.getPersistentData().putDouble("ya", y);
				_blockEntity.getPersistentData().putDouble("ya2", y);
				_blockEntity.getPersistentData().putDouble("za", z);
				_blockEntity.getPersistentData().putDouble("za2", z);
				_blockEntity.getPersistentData().putDouble("xs", x);
				_blockEntity.getPersistentData().putDouble("ys", y);
				_blockEntity.getPersistentData().putDouble("zs", z);
				_blockEntity.getPersistentData().putDouble("co_num", 0);
				_blockEntity.getPersistentData().putDouble("EO_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo")));
				_blockEntity.getPersistentData().putBoolean("HD", false);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (parseDouble(getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")) <= 1) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putString("obn", "");
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (parseDouble(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")) <= 1) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putString("sbn", "");
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (!(getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_Lock") && world instanceof Level _level18 && _level18.hasNeighborSignal(BlockPos.containing(x, y, z)))) {
			if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("ya", (y - 1));
						_blockEntity.getPersistentData().putDouble("ys", (y + 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (Direction.UP == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("ya", (y + 1));
						_blockEntity.getPersistentData().putDouble("ys", (y - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (Direction.NORTH == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("za", (z - 1));
						_blockEntity.getPersistentData().putDouble("zs", (z + 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (Direction.SOUTH == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("za", (z + 1));
						_blockEntity.getPersistentData().putDouble("zs", (z - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (Direction.WEST == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("xa", (x - 1));
						_blockEntity.getPersistentData().putDouble("xs", (x + 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (Direction.EAST == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("xa", (x + 1));
						_blockEntity.getPersistentData().putDouble("xs", (x - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (1 <= getBlockNBTNumber(world,
					BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
					("E" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					&& getBlockNBTLogic(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
							("E_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, x, y, z);
				if (!(getBlockNBTLogic(world,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
						("E_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
						&& !getBlockNBTLogic(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
								("E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))))) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
							_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs")));
							_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys")));
							_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (1 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")) {
				WCNAddSPProcedure.execute(world, x, y, z, blockstate, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"),
						getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs"));
				if (1 <= getBlockNBTNumber(world,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
						("E" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
						&& getBlockNBTLogic(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
								("E_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
					if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1 == 0) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("co_num", 2);
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					if (getBlockNBTNumber(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
							("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) == 0) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs"));
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")), 1);
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					give_num = Math.floor(((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "EO_num") < 1
							? getBlockNBTNumber(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
									("E" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							: getBlockNBTNumber(world, BlockPos.containing(x, y, z), "EO_num")) / (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1))
							/ getBlockNBTNumber(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
									("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))));
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs"));
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
									(getBlockNBTNumber(world,
											BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs")),
											("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					i = 1;
					for (int index0 = 0; index0 < (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num"); index0++) {
						ELGiveTransmissionSPProcedure.execute(world, x, y, z, blockstate, give_num, i, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xs"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ys"),
								getBlockNBTNumber(world, BlockPos.containing(x, y, z), "zs"));
						i = 1 + i;
					}
				}
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static double parseDouble(String s) {
		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			return 0;
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
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