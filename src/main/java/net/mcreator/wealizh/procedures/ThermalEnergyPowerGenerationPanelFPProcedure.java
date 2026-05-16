package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class ThermalEnergyPowerGenerationPanelFPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("Eu", 1);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (WealizhModBlocks.CASING_THERMAL_ENERGY_POWER_GENERATION_PANEL.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("E_d", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("E_d", false);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("fx", 0);
				_blockEntity.getPersistentData().putDouble("fy", 0);
				_blockEntity.getPersistentData().putDouble("fz", 0);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fy", (-1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (Direction.UP == (getDirectionFromBlockState(blockstate))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fy", 1);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (Direction.NORTH == (getDirectionFromBlockState(blockstate))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fz", (-1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (Direction.SOUTH == (getDirectionFromBlockState(blockstate))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fz", 1);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (Direction.WEST == (getDirectionFromBlockState(blockstate))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fx", (-1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (Direction.EAST == (getDirectionFromBlockState(blockstate))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fx", 1);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E_Acc") >= 1 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Eu")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("E", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E")));
					_blockEntity.getPersistentData().putDouble("E_Acc", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") >= 1 && getBlockNBTNumber(world,
				BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")),
				"E") < getBlockNBTNumber(world,
						BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")),
						"Eu")) {
			if (getBlockNBTLogic(world,
					BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")), "E_d")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"),
							z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz"));
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("E", (1 + getBlockNBTNumber(world,
								BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")),
								"E")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
		if (getBlockNBTNumber(world,
				BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")),
				"E") < getBlockNBTNumber(world,
						BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")),
						"Eu")) {
			if ((world.getBlockState(
					BlockPos.containing(x - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"), z - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz"))))
					.is(BlockTags.create(ResourceLocation.parse("wealizh:can_give_cv"))) && Direction.UP == (getDirectionFromBlockState(blockstate))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("E_Acc",
								(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E_Acc") + getBlockNBTNumber(world,
										BlockPos.containing(x - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"),
												z - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")),
										"pow")
										/ (getPropertyByName((world.getBlockState(BlockPos.containing(x - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"),
												z - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")))), "function_soul") instanceof BooleanProperty _getbp86
												&& (world.getBlockState(BlockPos.containing(x - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fx"), y - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fy"),
														z - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fz")))).getValue(_getbp86) ? 500 : 1000)));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
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

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}