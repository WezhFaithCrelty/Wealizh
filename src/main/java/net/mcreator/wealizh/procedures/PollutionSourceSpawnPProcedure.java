package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.ProblemReporter;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModEntities;
import net.mcreator.wealizh.init.WealizhModBlocks;

public class PollutionSourceSpawnPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double CoD_num, double Rad_num, double Tox_num) {
		BlockState block = Blocks.AIR.defaultBlockState();
		block = (world.getBlockState(BlockPos.containing(x, y, z)));
		{
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockState _bs = WealizhModBlocks.TEMPORARY_BLOCK.get().defaultBlockState();
			BlockEntity _be = world.getBlockEntity(_bp);
			CompoundTag _bnbt = null;
			if (_be != null) {
				_bnbt = _be.saveWithFullMetadata(world.registryAccess());
				_be.setRemoved();
			}
			world.setBlock(_bp, _bs, 3);
			if (_bnbt != null) {
				_be = world.getBlockEntity(_bp);
				if (_be != null) {
					try {
						_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
					} catch (Exception ignored) {
					}
				}
			}
		}
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("Rad_num", Rad_num);
				_blockEntity.getPersistentData().putDouble("CoD_num", CoD_num);
				_blockEntity.getPersistentData().putDouble("CoD_sc", 100);
				_blockEntity.getPersistentData().putDouble("Tox_num", Tox_num);
				_blockEntity.getPersistentData().putDouble("Tox_sc", 100);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (world instanceof ServerLevel _level) {
			Entity entityToSpawn = WealizhModEntities.POLLUTION_SOURCE.get().spawn(_level, BlockPos.containing(Math.floor(x) + 0.5, Math.floor(y) + 0.5, Math.floor(z) + 0.5), EntitySpawnReason.MOB_SUMMONED);
			if (entityToSpawn != null) {
				entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
			}
		}
		{
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockState _bs = block;
			BlockEntity _be = world.getBlockEntity(_bp);
			CompoundTag _bnbt = null;
			if (_be != null) {
				_bnbt = _be.saveWithFullMetadata(world.registryAccess());
				_be.setRemoved();
			}
			world.setBlock(_bp, _bs, 3);
			if (_bnbt != null) {
				_be = world.getBlockEntity(_bp);
				if (_be != null) {
					try {
						_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
					} catch (Exception ignored) {
					}
				}
			}
		}
	}
}