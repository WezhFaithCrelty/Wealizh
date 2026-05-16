package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class DecayedLogRandomTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double nx = 0;
		double ny = 0;
		double nz = 0;
		nx = x - 1;
		ny = y - 1;
		nz = z - 1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				for (int index2 = 0; index2 < 3; index2++) {
					if ((world.getBlockState(BlockPos.containing(nx, ny, nz))).is(BlockTags.create(ResourceLocation.parse("minecraft:logs"))) && Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
						{
							BlockPos _bp = BlockPos.containing(nx, ny, nz);
							BlockState _bs = WealizhModBlocks.DECAYED_LOG.get().defaultBlockState();
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
					}
					nx = 1 + nx;
				}
				nx = x - 1;
				nz = 1 + nz;
			}
			nz = z - 1;
			ny = 1 + ny;
		}
	}
}