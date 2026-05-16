package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class LargeScaleExplosionTPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState block = Blocks.AIR.defaultBlockState();
		String Skey = "";
		String block_ID = "";
		boolean CB = false;
		double nx = 0;
		double ny = 0;
		double nz = 0;
		double Stage = 0;
		double DC = 0;
		double pow = 0;
		double latitude = 0;
		double i = 0;
		double step = 0;
		double j = 0;
		double cos_la = 0;
		double longitude = 0;
		double circumference = 0;
		double angle = 0;
		double JNT = 0;
		double dx = 0;
		double dz = 0;
		double melting_point_num = 0;
		pow = entity.getPersistentData().getDoubleOr("pow", 0);
		if (!entity.getPersistentData().getBooleanOr("SD", false)) {
			step = 1 > pow ? 360 : 180 / (Math.PI * pow);
			JNT = Math.ceil(180 / step);
			for (int index0 = 0; index0 < (int) Math.ceil((360 / step) * 2); index0++) {
				longitude = Math.toRadians(i * step);
				for (int index1 = 0; index1 < (int) (JNT - (0 == i ? 0 : 1)); index1++) {
					latitude = Math.toRadians(j * step - 90);
					cos_la = Math.cos(latitude);
					LargeScaleExplosionDamageDGPProcedure.execute(world, x, y, z, entity, cos_la * Math.cos(longitude), Math.sin(latitude), cos_la * Math.sin(longitude), pow);
					j = 1 + j;
				}
				i = 0.5 + i;
				j = 1;
			}
			entity.getPersistentData().putBoolean("SD", true);
		}
		circumference = Math.floor(0.25 * Math.PI * entity.getPersistentData().getDoubleOr("cr", 0));
		if (0 == entity.getPersistentData().getDoubleOr("cr", 0)) {
			circumference = 1;
		} else if (8 >= circumference) {
			circumference = 8;
		}
		angle = (2 * Math.PI * entity.getPersistentData().getDoubleOr("ci", 0)) / circumference;
		dx = Math.floor((Math.floor(x / 16) * 16 + 7.5 + Math.cos(angle) * entity.getPersistentData().getDoubleOr("cr", 0)) / 16) * 16;
		dz = Math.floor((Math.floor(z / 16) * 16 + 7.5 + Math.sin(angle) * entity.getPersistentData().getDoubleOr("cr", 0)) / 16) * 16;
		CB = 50 <= pow;
		if (!entity.getPersistentData().getBooleanOr(("cl" + dx + dz), false)) {
			entity.getPersistentData().putBoolean(("cl" + dx + dz), true);
			ny = 319;
			for (int index2 = 0; index2 < 384; index2++) {
				nx = dx;
				for (int index3 = 0; index3 < 16; index3++) {
					nz = dz;
					for (int index4 = 0; index4 < 16; index4++) {
						if (!world.isEmptyBlock(BlockPos.containing(nx, ny, nz))) {
							block = (world.getBlockState(BlockPos.containing(nx, ny, nz)));
							Skey = nx + "" + ny + nz;
							DC = DistanceCalculationProcedure.execute(x, y, z, nx + 0.5, ny + 0.5, nz + 0.5);
							if (pow * 0.24 >= DC) {
								Stage = 5;
							} else if (pow * 0.48 >= DC) {
								Stage = 4;
							} else if (pow * 0.72 >= DC) {
								Stage = 3;
							} else if (pow * 0.96 >= DC) {
								Stage = 2;
							} else {
								Stage = 1;
							}
							if (entity.getPersistentData().getBooleanOr(("CB" + Skey), false) && (entity.getPersistentData().getStringOr(("B" + Skey), "")).equals("" + block)
									|| 0.1 * Stage > block.getBlock().getExplosionResistance() && 2 * pow >= DC) {
								world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.AIR.defaultBlockState(), 3);
							}
						}
						nz = nz + 1;
					}
					nx = nx + 1;
				}
				nx = dx;
				for (int index5 = 0; index5 < 16; index5++) {
					nz = dz;
					for (int index6 = 0; index6 < 16; index6++) {
						DC = DistanceCalculationProcedure.execute(x, y, z, nx + 0.5, ny + 0.5, nz + 0.5);
						if (world.isEmptyBlock(BlockPos.containing(nx, ny, nz))) {
							if (2 * pow >= DC) {
								BlockPos checkPos = new BlockPos((int) nx, (int) (ny - 1), (int) nz);
								if (0 < world.getBlockState(checkPos).getFlammability(world, checkPos, Direction.UP)) {
									world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.FIRE.defaultBlockState(), 3);
								}
							}
						} else {
							block = (world.getBlockState(BlockPos.containing(nx, ny, nz)));
							Skey = nx + "" + ny + nz;
							if (pow * 0.24 >= DC) {
								Stage = 5;
							} else if (pow * 0.48 >= DC) {
								Stage = 4;
							} else if (pow * 0.72 >= DC) {
								Stage = 3;
							} else if (pow * 0.96 >= DC) {
								Stage = 2;
							} else {
								Stage = 1;
							}
							if (CB) {
								if (1.2 * pow >= DC) {
									if ((block.is(BlockTags.create(ResourceLocation.parse("wealizh:cb_lar_melting")))
											|| (BuiltInRegistries.BLOCK.getKey(block.getBlock()).toString()).contains("_ore") && !(BuiltInRegistries.BLOCK.getKey(block.getBlock()).toString()).contains("deepslate_"))
											&& MoltenHighTemperatureSolidifiedSubstanceSurfaceGenerationDeterminationProcedure.execute(world, nx, ny, nz)) {
										if (Blocks.BEDROCK == block.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.CHARRED_BEDROCK.get().defaultBlockState(), 3);
										} else if (Blocks.SAND == block.getBlock() || Blocks.RED_SAND == block.getBlock() || Blocks.SUSPICIOUS_SAND == block.getBlock() || Blocks.GRAVEL == block.getBlock()
												|| Blocks.SUSPICIOUS_GRAVEL == block.getBlock()) {
											if (Mth.nextInt(RandomSource.create(), 1, (int) (1 + 4 / Stage)) == 1) {
												world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.TRINITITE.get().defaultBlockState(), 3);
											} else {
												world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.MOLTEN_SAND_SOLIDIFICATION_SUBSTANCE.get().defaultBlockState(), 3);
											}
										} else {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.MOLTEN_HIGH_TEMPERATURE_SOLIDIFIED_SUBSTANCE.get().defaultBlockState(), 3);
										}
										melting_point_num = (getPropertyByName(block, "melting_point") instanceof IntegerProperty _getip32 ? block.getValue(_getip32) : -1) + Stage + 1;
										{
											int _value = (int) (5 < melting_point_num ? 5 : melting_point_num);
											BlockPos _pos = BlockPos.containing(nx, ny, nz);
											BlockState _bs = world.getBlockState(_pos);
											if (_bs.getBlock().getStateDefinition().getProperty("melting_point") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
												world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
										}
									} else if (BlocksExposureToJudgmentProcedure.execute(world, nx, ny, nz, entity)) {
										if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:stone")))) {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.CHARRED_STONE.get().defaultBlockState(), 3);
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:containing_dirt")))) {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.MOLTEN_HIGH_TEMPERATURE_SOLIDIFIED_SUBSTANCE.get().defaultBlockState(), 3);
											melting_point_num = (getPropertyByName(block, "melting_point") instanceof IntegerProperty _getip38 ? block.getValue(_getip38) : -1) + Stage + 1;
											{
												int _value = (int) (5 < melting_point_num ? 5 : melting_point_num);
												BlockPos _pos = BlockPos.containing(nx, ny, nz);
												BlockState _bs = world.getBlockState(_pos);
												if (_bs.getBlock().getStateDefinition().getProperty("melting_point") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
													world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:leaves")))) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.AIR.defaultBlockState(), 3);
										} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:logs"))) && !block.is(BlockTags.create(ResourceLocation.parse("mincraft:stem")))
												|| WealizhModBlocks.DECAYED_LOG.get() == block.getBlock()) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARCOALIZED_LOG.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (Blocks.CLAY == block.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.TERRACOTTA.defaultBlockState(), 3);
										} else if ((Blocks.COAL_ORE == block.getBlock() || Blocks.DEEPSLATE_COAL_ORE == block.getBlock() || WealizhModBlocks.LIGNITE_ORE.get() == block.getBlock()
												|| WealizhModBlocks.DEEPSLATE_LIGNITE_ORE.get() == block.getBlock() || WealizhModBlocks.GRAPHITE_ORE.get() == block.getBlock() || WealizhModBlocks.DEEPSLATE_GRAPHITE_ORE.get() == block.getBlock())
												&& Mth.nextInt(RandomSource.create(), 1, (int) (1 + 6 / Stage)) == 1) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState(), 3);
										} else if (Blocks.WATER == block.getBlock() || Blocks.BUBBLE_COLUMN == block.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.AIR.defaultBlockState(), 3);
										} else if (Blocks.SNOW == block.getBlock() || Blocks.SNOW_BLOCK == block.getBlock() || Blocks.POWDER_SNOW == block.getBlock() || Blocks.ICE == block.getBlock() || Blocks.PACKED_ICE == block.getBlock()
												|| Blocks.FROSTED_ICE == block.getBlock() || Blocks.BLUE_ICE == block.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.AIR.defaultBlockState(), 3);
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:stone_slab")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARRED_STONE_SLAB.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:stone_stair")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARRED_STONE_STAIR.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:stone_wall")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARRED_STONE_WALL.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:planks")))) {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.CHARCOALIZED_PLANKS.get().defaultBlockState(), 3);
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:planks_slab")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARCOALIZED_PLANKS_SLAB.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:planks_stair")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARCOALIZED_PLANKS_STAIR.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:planks_fence")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARCOALIZED_PLANKS_FENCE.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:planks_fence_gate")))) {
											{
												BlockPos _bp = BlockPos.containing(nx, ny, nz);
												BlockState _bs = WealizhModBlocks.CHARCOALIZED_PLANKS_FENCE_GATE.get().defaultBlockState();
												BlockState _bso = world.getBlockState(_bp);
												for (Property<?> _propertyOld : _bso.getProperties()) {
													Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
													if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
														try {
															_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
														} catch (Exception e) {
														}
												}
												world.setBlock(_bp, _bs, 3);
											}
										} else if (Blocks.WET_SPONGE == block.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.SPONGE.defaultBlockState(), 3);
										} else if (Blocks.WATER_CAULDRON == block.getBlock() || Blocks.POWDER_SNOW_CAULDRON == block.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.CAULDRON.defaultBlockState(), 3);
										}
										block_ID = BuiltInRegistries.BLOCK.getKey(block.getBlock()).toString();
										if (block_ID.contains("_ore") && !block_ID.contains("deepslate_")) {
											world.setBlock(BlockPos.containing(nx, ny, nz), BuiltInRegistries.BLOCK.getValue(ResourceLocation.parse(((block_ID.replace(":", ":deepslate_"))).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState(),
													3);
											if (world.isEmptyBlock(BlockPos.containing(nx, ny, nz))) {
												world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.CHARRED_STONE.get().defaultBlockState(), 3);
											}
										}
									}
									block = (world.getBlockState(BlockPos.containing(nx, ny, nz)));
									if (pow * 20 >= block.getBlock().getExplosionResistance() && pow >= world.getBlockState(BlockPos.containing(nx, ny, nz)).getDestroySpeed(world, BlockPos.containing(nx, ny, nz))
											&& BlocksThereIsSupportBelowJudgmentProcedure.execute(world, nx, ny, nz, entity) && 4 > BlocksAttachedToTheSurroundingBlockNumReturnProcedure.execute(world, nx, ny, nz) && !block.hasBlockEntity()) {
										CauseTheBlockToFallButWithDelaySPProcedure.execute(world, nx, ny, nz, block, 1);
									}
									if (world.isEmptyBlock(BlockPos.containing(nx, ny + 1, nz))) {
										BlockPos checkPos = new BlockPos((int) nx, (int) ny, (int) nz);
										if (0 < world.getBlockState(checkPos).getFlammability(world, checkPos, Direction.UP)) {
											world.setBlock(BlockPos.containing(nx, ny + 1, nz), Blocks.FIRE.defaultBlockState(), 3);
										}
									}
								} else if (2 * pow >= DC) {
									if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:containing_dirt"))) && !(Blocks.DIRT == block.getBlock() || Blocks.COARSE_DIRT == block.getBlock())) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.COARSE_DIRT.defaultBlockState(), 3);
									} else if (Blocks.SNOW == block.getBlock() || Blocks.SNOW_BLOCK == block.getBlock() || Blocks.POWDER_SNOW == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.AIR.defaultBlockState(), 3);
									} else if (Blocks.ICE == block.getBlock() || Blocks.PACKED_ICE == block.getBlock() || Blocks.FROSTED_ICE == block.getBlock() || Blocks.BLUE_ICE == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.WATER.defaultBlockState(), 3);
									} else if (Blocks.POWDER_SNOW_CAULDRON == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.CAULDRON.defaultBlockState(), 3);
									}
								}
							}
						}
						nz = nz + 1;
					}
					nx = nx + 1;
				}
				ny = ny - 1;
			}
		}
		entity.getPersistentData().putDouble("ci", (0.8 + entity.getPersistentData().getDoubleOr("ci", 0)));
		if (circumference <= entity.getPersistentData().getDoubleOr("ci", 0)) {
			entity.getPersistentData().putDouble("ci", 0);
			entity.getPersistentData().putDouble("cr", (8 + entity.getPersistentData().getDoubleOr("cr", 0)));
		}
		if (pow * 2 + 24 < entity.getPersistentData().getDoubleOr("cr", 0)) {
			LargeScaleExplosionAreaDestructionPProcedure.execute(world, x, y, z, pow * 2);
			if (!entity.level().isClientSide())
				entity.discard();
			return;
		}
		if (Mth.nextInt(RandomSource.create(), 1, 20) != 1) {
			LargeScaleExplosionTPProcedure.execute(world, x, y, z, entity);
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