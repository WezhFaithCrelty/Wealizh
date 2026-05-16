package net.mcreator.wealizh.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class PipeTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double give_num2 = 0;
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
				_blockEntity.getPersistentData().putDouble("LO_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo") < 1 ? 1000 : getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo")));
				_blockEntity.getPersistentData().putDouble("co_num", 0);
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
		if (!(getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_Lock") && world instanceof Level _level16 && _level16.hasNeighborSignal(BlockPos.containing(x, y, z)))) {
			if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp21 && blockstate.getValue(_getbp21)) {
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
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp24 && blockstate.getValue(_getbp24)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("ya2", (y + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.UP == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp30 && blockstate.getValue(_getbp30)) {
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
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp33 && blockstate.getValue(_getbp33)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("ya2", (y - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.NORTH == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp39 && blockstate.getValue(_getbp39)) {
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
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp42 && blockstate.getValue(_getbp42)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("za2", (z + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.SOUTH == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp48 && blockstate.getValue(_getbp48)) {
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
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp51 && blockstate.getValue(_getbp51)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("za2", (z - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.WEST == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp57 && blockstate.getValue(_getbp57)) {
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
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp60 && blockstate.getValue(_getbp60)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("xa2", (x + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (Direction.EAST == (getDirectionFromBlockState(blockstate))) {
				if (getPropertyByName(blockstate, "ac") instanceof BooleanProperty _getbp66 && blockstate.getValue(_getbp66)) {
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
				if (getPropertyByName(blockstate, "bc") instanceof BooleanProperty _getbp69 && blockstate.getValue(_getbp69)) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("xa2", (x - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (1 <= getBlockNBTNumber(world,
					BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
					("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					&& getBlockNBTLogic(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
							("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					|| 1 <= getBlockNBTNumber(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
							("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							&& getBlockNBTLogic(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
									("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, x, y, z);
				if (!(getBlockNBTLogic(world,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")), "L_o")
						&& !getBlockNBTLogic(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")), "L_d")
						&& getBlockNBTLogic(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")), "L_o")
						&& !getBlockNBTLogic(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
								"L_d"))) {
					if (getBlockNBTLogic(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")), "L_o")
							&& !getBlockNBTLogic(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
									"L_d")) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
								_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2")));
								_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2")));
								_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
					if (getBlockNBTLogic(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")), "L_o")
							&& !getBlockNBTLogic(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
									"L_d")) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
								_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2")));
								_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2")));
								_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				}
			}
			if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "CP")) {
				if (2 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")) {
					WCNAddSPProcedure.execute(world, x, y, z, blockstate, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"),
							getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za"));
					WCNAddSPProcedure.execute(world, x, y, z, blockstate, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"),
							getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2"));
					give_num = Math.floor(getBlockNBTNumber(world,
							BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
							("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "LO_num")
							* (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1) <= getBlockNBTNumber(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
									("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
											? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "LO_num")
											: (getBlockNBTNumber(world,
													BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"),
															getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
													("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) / (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1))
													/ getBlockNBTNumber(world, BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"),
															getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))));
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za"));
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
									(getBlockNBTNumber(world,
											BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
											("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					give_num2 = Math
							.floor(getBlockNBTNumber(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
									("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "LO_num")
									* (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1) <= getBlockNBTNumber(world,
											BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"),
													getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
											("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
													? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "LO_num")
													: (getBlockNBTNumber(world,
															BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"),
																	getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
															("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) / (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1))
															/ getBlockNBTNumber(world, BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"),
																	getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))));
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2"));
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")), (getBlockNBTNumber(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
									("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					i = 1;
					for (int index0 = 0; index0 < (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num"); index0++) {
						if (1 <= getBlockNBTNumber(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
								("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
								&& getBlockNBTLogic(world,
										BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za")),
										("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
							ELGiveTransmissionSPProcedure.execute(world, x, y, z, blockstate, give_num, i, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya"),
									getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za"));
						}
						if (1 <= getBlockNBTNumber(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
								("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
								&& getBlockNBTLogic(world,
										BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2")),
										("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
							ELGiveTransmissionSPProcedure.execute(world, x, y, z, blockstate, give_num2, i, getBlockNBTNumber(world, BlockPos.containing(x, y, z), "xa2"), getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ya2"),
									getBlockNBTNumber(world, BlockPos.containing(x, y, z), "za2"));
						}
						i = 1 + i;
					}
				}
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("CP", true);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
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