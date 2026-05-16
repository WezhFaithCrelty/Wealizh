package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BlockPullRodDChangePProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level0 && _level0.hasNeighborSignal(BlockPos.containing(x, y, z)) && !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_hhd")) {
			if (!getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON")) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.lever.click")), SoundSource.BLOCKS, (float) 0.5, 1);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.lever.click")), SoundSource.BLOCKS, (float) 0.5, 1, false);
					}
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("ON", true);
					_blockEntity.getPersistentData().putBoolean("RS_hhd", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (!(world instanceof Level _level6 && _level6.hasNeighborSignal(BlockPos.containing(x, y, z))) && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RS_hhd")) {
			if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "ON")) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.lever.click")), SoundSource.BLOCKS, (float) 0.5, 1);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.lever.click")), SoundSource.BLOCKS, (float) 0.5, 1, false);
					}
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("ON", false);
					_blockEntity.getPersistentData().putBoolean("RS_hhd", false);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}