package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class SchrodingerBlockSODPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String block = "";
		double xi = 0;
		double yi = 0;
		double zi = 0;
		if (Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
			if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BuiltInRegistries.BLOCK.getValue(ResourceLocation.parse(((getBlockNBTString(world, BlockPos.containing(x, y, z), "block"))).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
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
			} else {
				world.setBlock(BlockPos.containing(x, y, z), WealizhModBlocks.NAN_SUBSTANCE.get().defaultBlockState(), 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Sch_num") > 0) {
			xi = x + Mth.nextInt(RandomSource.create(), -1, 1);
			yi = y + Mth.nextInt(RandomSource.create(), -1, 1);
			zi = z + Mth.nextInt(RandomSource.create(), -1, 1);
			if (!world.isEmptyBlock(BlockPos.containing(xi, yi, zi))) {
				block = BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(xi, yi, zi))).getBlock()).toString();
				{
					BlockPos _bp = BlockPos.containing(xi, yi, zi);
					BlockState _bs = WealizhModBlocks.SCHRODINGER_BLOCK.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
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
					BlockPos _bp = BlockPos.containing(xi, yi, zi);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("Sch_num", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Sch_num") - 1));
						_blockEntity.getPersistentData().putString("block", block);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}