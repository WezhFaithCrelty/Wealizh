package net.mcreator.wealizh.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class ConcentratedPipeTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
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
				_blockEntity.getPersistentData().putDouble("co_num", 0);
				_blockEntity.getPersistentData().putDouble("LO_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo") < 1 ? 1000 : getBlockNBTNumber(world, BlockPos.containing(x, y, z), "TrVo")));
				_blockEntity.getPersistentData().putBoolean("HD", false);
				_blockEntity.getPersistentData().putBoolean("HBA", true);
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
		if (!(getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_Lock") && world instanceof Level _level17 && _level17.hasNeighborSignal(BlockPos.containing(x, y, z)))) {
			if (1 <= getBlockNBTNumber(world, BlockPos.containing(x + 1, y, z), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					&& getBlockNBTLogic(world, BlockPos.containing(x + 1, y, z), ("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					|| 1 <= getBlockNBTNumber(world, BlockPos.containing(x - 1, y, z), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							&& getBlockNBTLogic(world, BlockPos.containing(x - 1, y, z), ("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					|| 1 <= getBlockNBTNumber(world, BlockPos.containing(x, y + 1, z), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							&& getBlockNBTLogic(world, BlockPos.containing(x, y + 1, z), ("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					|| 1 <= getBlockNBTNumber(world, BlockPos.containing(x, y - 1, z), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							&& getBlockNBTLogic(world, BlockPos.containing(x, y - 1, z), ("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					|| 1 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z + 1), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							&& getBlockNBTLogic(world, BlockPos.containing(x, y, z + 1), ("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
					|| 1 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z - 1), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							&& getBlockNBTLogic(world, BlockPos.containing(x, y, z - 1), ("L_o" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, x, y, z);
			}
			for (int index0 = 0; index0 < 6; index0++) {
				i = 1 + i;
				if (i == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "p_x")) {
					ConcentratedWirePipeTickSPProcedure.execute(world, x, y, z, blockstate, x + 1, y, z);
				}
				if (i == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "p_nx")) {
					ConcentratedWirePipeTickSPProcedure.execute(world, x, y, z, blockstate, x - 1, y, z);
				}
				if (i == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "p_y")) {
					ConcentratedWirePipeTickSPProcedure.execute(world, x, y, z, blockstate, x, y + 1, z);
				}
				if (i == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "p_ny")) {
					ConcentratedWirePipeTickSPProcedure.execute(world, x, y, z, blockstate, x, y - 1, z);
				}
				if (i == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "p_z")) {
					ConcentratedWirePipeTickSPProcedure.execute(world, x, y, z, blockstate, x, y, z + 1);
				}
				if (i == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "p_nz")) {
					ConcentratedWirePipeTickSPProcedure.execute(world, x, y, z, blockstate, x, y, z - 1);
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
}