package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class PollutionSourceTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double RDP_num = 0;
		double RDP_sc = 0;
		double tx = 0;
		double ty = 0;
		double tz = 0;
		double td = 0;
		double tr = 0;
		double random_t = 0;
		if (entity.getPersistentData().getDoubleOr("Rad_num", 0) < 0.1 && entity.getPersistentData().getDoubleOr("CoD_num", 0) < 0.1 && entity.getPersistentData().getDoubleOr("Tox_num", 0) < 0.1) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
		if (Mth.nextInt(RandomSource.create(), 1, (int) (entity.getPersistentData().getDoubleOr("Rad_num", 0) * 2000)) == 1) {
			BsRadiationsPProcedure.execute(world, x, y, z, 6 <= entity.getPersistentData().getDoubleOr("Rad_num", 0) ? 0.25 : 0, 8, entity.getPersistentData().getDoubleOr("Rad_num", 0) * 20);
		}
		if (entity.getPersistentData().getDoubleOr("Rad_num", 0) > 0.1 || entity.getPersistentData().getDoubleOr("CoD_num", 0) > 0.1 || entity.getPersistentData().getDoubleOr("Tox_num", 0) > 0.1) {
			AllRInPProcedure.execute(world, x, y, z, entity, true, false, entity.getPersistentData().getDoubleOr("t", 0) < 1, entity.getPersistentData().getDoubleOr("CoD_num", 0),
					entity.getPersistentData().getDoubleOr("CoD_num", 0) * entity.getPersistentData().getDoubleOr("CoD_sc", 0), entity.getPersistentData().getDoubleOr("Rad_num", 0), entity.getPersistentData().getDoubleOr("Rad_num", 0) * 200,
					entity.getPersistentData().getDoubleOr("Tox_num", 0), entity.getPersistentData().getDoubleOr("Tox_num", 0) * entity.getPersistentData().getDoubleOr("Tox_sc", 0));
			if (entity.getPersistentData().getDoubleOr("t", 0) < 1) {
				entity.getPersistentData().putDouble("t", 50);
			}
		}
		if (entity.getPersistentData().getDoubleOr("Rad_num", 0) > 6 && Mth.nextInt(RandomSource.create(), 1, 20) == 1) {
			tr = entity.getPersistentData().getDoubleOr("Rad_num", 0) * 20;
			for (int index0 = 0; index0 < (int) (tr > 25000 ? 25000 : tr); index0++) {
				tx = x + Mth.nextInt(RandomSource.create(), (int) (0 - tr * 0.8), (int) (tr * 0.8));
				ty = 319;
				tz = z + Mth.nextInt(RandomSource.create(), (int) (0 - tr * 0.8), (int) (tr * 0.8));
				td = DistanceCalculationProcedure.execute(x, y, z, tx, y, tz);
				random_t = Mth.nextInt(RandomSource.create(), 1, (int) (1 + Math.floor(td / (tr / 10))));
				while (-64 <= ty) {
					if (td > tr * 0.8 || random_t != 1 || world.getBlockState(BlockPos.containing(tx, ty, tz)).canOcclude()) {
						ty = -100;
					}
					if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get().defaultBlockState().canSurvive(world, BlockPos.containing(tx, ty, tz)) && !((world.getBlockState(BlockPos.containing(tx, ty, tz))).getBlock() instanceof LiquidBlock)) {
						if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get() == (world.getBlockState(BlockPos.containing(tx, ty, tz))).getBlock()
								&& 15 > (getPropertyByName((world.getBlockState(BlockPos.containing(tx, ty, tz))), "cumt") instanceof IntegerProperty _getip35 ? (world.getBlockState(BlockPos.containing(tx, ty, tz))).getValue(_getip35) : -1)) {
							{
								int _value = 1 + (getPropertyByName((world.getBlockState(BlockPos.containing(tx, ty, tz))), "cumt") instanceof IntegerProperty _getip37 ? (world.getBlockState(BlockPos.containing(tx, ty, tz))).getValue(_getip37) : -1);
								BlockPos _pos = BlockPos.containing(tx, ty, tz);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("cumt") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
									world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(tx, ty, tz))).is(BlockTags.create(ResourceLocation.parse("minecraft:replaceable")))) {
							world.setBlock(BlockPos.containing(tx, ty, tz), WealizhModBlocks.RADIOACTIVE_FALLOUT.get().defaultBlockState(), 3);
						}
						ty = -100;
					}
					ty = ty - 1;
				}
			}
		}
		entity.getPersistentData().putDouble("Rad_num", (entity.getPersistentData().getDoubleOr("Rad_num", 0) * 0.999999));
		entity.getPersistentData().putDouble("CoD_num", (entity.getPersistentData().getDoubleOr("CoD_num", 0) * 0.9999));
		entity.getPersistentData().putDouble("Tox_num", (entity.getPersistentData().getDoubleOr("Tox_num", 0) * 0.99999));
		if (entity.getPersistentData().getDoubleOr("t", 0) > 0) {
			entity.getPersistentData().putDouble("t", (entity.getPersistentData().getDoubleOr("t", 0) - 1));
		}
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