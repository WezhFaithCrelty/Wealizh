package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class StorageBatteryGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (WealizhModBlocks.INFINITE_STORAGE_BATTERY.get() == blockstate.getBlock()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("Eu", Double.POSITIVE_INFINITY);
					_blockEntity.getPersistentData().putDouble("E", Double.POSITIVE_INFINITY);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (WealizhModBlocks.ALUMINUM_COPPER_ALLOY_STORAGE_BATTERY.get() == blockstate.getBlock()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("Eu", 2500);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (WealizhModBlocks.STORAGE_BATTERY.get() == blockstate.getBlock()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("Eu", 800);
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
					_blockEntity.getPersistentData().putDouble("Eu", 120);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (3 == (world instanceof Level _level11 && _level11.hasNeighborSignal(BlockPos.containing(x, y, z)) ? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "RMode") : getBlockNBTNumber(world, BlockPos.containing(x, y, z), "SMode"))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("E_d", false);
					_blockEntity.getPersistentData().putBoolean("E_o", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (2 == (world instanceof Level _level16 && _level16.hasNeighborSignal(BlockPos.containing(x, y, z))
				? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "RMode")
				: getBlockNBTNumber(world, BlockPos.containing(x, y, z), "SMode"))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("E_d", true);
					_blockEntity.getPersistentData().putBoolean("E_o", false);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (1 == (world instanceof Level _level21 && _level21.hasNeighborSignal(BlockPos.containing(x, y, z))
				? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "RMode")
				: getBlockNBTNumber(world, BlockPos.containing(x, y, z), "SMode"))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("E_d", true);
					_blockEntity.getPersistentData().putBoolean("E_o", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (0 == (world instanceof Level _level26 && _level26.hasNeighborSignal(BlockPos.containing(x, y, z))
				? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "RMode")
				: getBlockNBTNumber(world, BlockPos.containing(x, y, z), "SMode"))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("E_d", false);
					_blockEntity.getPersistentData().putBoolean("E_o", false);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 0, "");
		BatteryOutputEToBlockPProcedure.execute(world, x, y, z, blockstate, 1, "");
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}