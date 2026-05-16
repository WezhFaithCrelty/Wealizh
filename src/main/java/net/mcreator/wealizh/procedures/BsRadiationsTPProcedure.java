package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class BsRadiationsTPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState block = Blocks.AIR.defaultBlockState();
		BlockState block2 = Blocks.AIR.defaultBlockState();
		double latitude = 0;
		double i = 0;
		double j = 0;
		double nx = 0;
		double ny = 0;
		double nz = 0;
		double dx = 0;
		double dz = 0;
		double circumference = 0;
		double pow = 0;
		double angle = 0;
		double Stage = 0;
		double step = 0;
		double longitude = 0;
		double cos_la = 0;
		double DL_sc = 0;
		double DC = 0;
		double GS = 0;
		double JNT = 0;
		double JNT_o = 0;
		boolean in_desert_dust = false;
		pow = entity.getPersistentData().getDoubleOr("pow", 0);
		GS = entity.getPersistentData().getDoubleOr("GS", 0);
		circumference = Math.floor(0.25 * Math.PI * entity.getPersistentData().getDoubleOr("cr", 0));
		if (0 == entity.getPersistentData().getDoubleOr("cr", 0)) {
			circumference = 1;
		} else if (8 >= circumference) {
			circumference = 8;
		}
		angle = (2 * Math.PI * entity.getPersistentData().getDoubleOr("ci", 0)) / circumference;
		dx = Math.floor((Math.floor(x / 16) * 16 + 7.5 + Math.cos(angle) * entity.getPersistentData().getDoubleOr("cr", 0)) / 16) * 16;
		dz = Math.floor((Math.floor(z / 16) * 16 + 7.5 + Math.sin(angle) * entity.getPersistentData().getDoubleOr("cr", 0)) / 16) * 16;
		DL_sc = Math.floor(pow * entity.getPersistentData().getDoubleOr("DV", 0));
		if (!entity.getPersistentData().getBooleanOr(("cl" + dx + dz), false)) {
			entity.getPersistentData().putBoolean(("cl" + dx + dz), true);
			ny = 319;
			for (int index0 = 0; index0 < 384; index0++) {
				nx = dx;
				for (int index1 = 0; index1 < 16; index1++) {
					nz = dz;
					for (int index2 = 0; index2 < 16; index2++) {
						DC = DistanceCalculationProcedure.execute(x, y, z, nx, ny, nz);
						if (DC <= pow && Mth.nextInt(RandomSource.create(), 1, (int) Math.ceil(Math.pow(pow, 2))) <= Math.ceil(Math.pow((pow / DC) * pow, 2) / (GS / 2))) {
							in_desert_dust = DC <= DL_sc;
							if (in_desert_dust && ny == Math.floor(y) && !world.getBiome(BlockPos.containing(nx, ny, nz)).is(ResourceLocation.parse("wealizh:desolate_land"))) {
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(nx, 0, nz), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											"fillbiome ~ -64 ~ ~ 319 ~ wealizh:desolate_land");
							}
							if (!world.isEmptyBlock(BlockPos.containing(nx, ny, nz))) {
								block = (world.getBlockState(BlockPos.containing(nx, ny, nz)));
								if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:rad_can_change")))) {
									block2 = (world.getBlockState(BlockPos.containing(nx, ny + 1, nz)));
									if (Blocks.GRASS_BLOCK == block.getBlock() || Blocks.MYCELIUM == block.getBlock() || Blocks.PODZOL == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_GRASS_BLOCK.get().defaultBlockState(), 3);
										if (in_desert_dust) {
											{
												BlockPos _pos = BlockPos.containing(nx, ny, nz);
												BlockState _bs = world.getBlockState(_pos);
												if (_bs.getBlock().getStateDefinition().getProperty("desert_dust") instanceof BooleanProperty _booleanProp)
													world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
											}
										}
										if (Blocks.SHORT_GRASS == block2.getBlock() || Blocks.FERN == block2.getBlock() || Blocks.BUSH == block2.getBlock()) {
											if (in_desert_dust) {
												world.setBlock(BlockPos.containing(nx, ny + 1, nz), WealizhModBlocks.DIED_SHORT_GRASS_DESERT_DUST.get().defaultBlockState(), 3);
											} else {
												world.setBlock(BlockPos.containing(nx, ny + 1, nz), WealizhModBlocks.DIED_SHORT_GRASS.get().defaultBlockState(), 3);
											}
										}
										if (Blocks.TALL_GRASS == block2.getBlock() || Blocks.LARGE_FERN == block2.getBlock() || Blocks.FIREFLY_BUSH == block2.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny + 2, nz), Blocks.AIR.defaultBlockState(), 3);
											if (in_desert_dust) {
												world.setBlock(BlockPos.containing(nx, ny + 1, nz), WealizhModBlocks.DIED_TALL_GRASS_DESERT_DUST.get().defaultBlockState(), 3);
											} else {
												world.setBlock(BlockPos.containing(nx, ny + 1, nz), WealizhModBlocks.DIED_TALL_GRASS.get().defaultBlockState(), 3);
											}
										}
										if (block2.is(BlockTags.create(ResourceLocation.parse("minecraft:flowers")))) {
											world.destroyBlock(BlockPos.containing(nx, ny + 1, nz), false);
										}
										if (block2.is(BlockTags.create(ResourceLocation.parse("minecraft:saplings")))) {
											world.setBlock(BlockPos.containing(nx, ny + 1, nz), Blocks.DEAD_BUSH.defaultBlockState(), 3);
										}
										if (Blocks.PINK_PETALS == block2.getBlock()) {
											world.destroyBlock(BlockPos.containing(nx, ny + 1, nz), false);
										}
										if (Blocks.WILDFLOWERS == block2.getBlock()) {
											world.destroyBlock(BlockPos.containing(nx, ny + 1, nz), false);
										}
									} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:logs"))) && !block.is(BlockTags.create(ResourceLocation.parse("mincraft:stem")))) {
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
											world.setBlock(_bp, _bs, 3);
										}
									} else if (Blocks.SHORT_GRASS == block.getBlock() || Blocks.FERN == block.getBlock() || Blocks.BUSH == block.getBlock()) {
										if (in_desert_dust) {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_SHORT_GRASS_DESERT_DUST.get().defaultBlockState(), 3);
										} else {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_SHORT_GRASS.get().defaultBlockState(), 3);
										}
									} else if ((Blocks.TALL_GRASS == block.getBlock() || Blocks.LARGE_FERN == block.getBlock()) && (Blocks.TALL_GRASS == block2.getBlock() || Blocks.LARGE_FERN == block2.getBlock())
											|| Blocks.FIREFLY_BUSH == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny + 1, nz), Blocks.AIR.defaultBlockState(), 3);
										if (in_desert_dust) {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_TALL_GRASS_DESERT_DUST.get().defaultBlockState(), 3);
										} else {
											world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_TALL_GRASS.get().defaultBlockState(), 3);
										}
									} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:leaves")))) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:flowers")))) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.PINK_PETALS == block.getBlock() || Blocks.WILDFLOWERS == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.KELP == block.getBlock() || Blocks.KELP_PLANT == block.getBlock() || Blocks.SEAGRASS == block.getBlock() || Blocks.TALL_SEAGRASS == block.getBlock() || Blocks.SEA_PICKLE == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.BAMBOO_SAPLING == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.BAMBOO == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.SUGAR_CANE == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:saplings")))) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_BUSH.defaultBlockState(), 3);
									} else if (Blocks.MOSS_BLOCK == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.PALE_MOSS_BLOCK.defaultBlockState(), 3);
										if (Blocks.MOSS_CARPET == block2.getBlock()) {
											world.setBlock(BlockPos.containing(nx, ny + 1, nz), Blocks.PALE_MOSS_CARPET.defaultBlockState(), 3);
										}
										if (Blocks.AZALEA == block2.getBlock() || Blocks.FLOWERING_AZALEA == block2.getBlock() || Blocks.BIG_DRIPLEAF_STEM == block2.getBlock() || Blocks.BIG_DRIPLEAF == block2.getBlock()
												|| Blocks.SMALL_DRIPLEAF == block2.getBlock()) {
											world.destroyBlock(BlockPos.containing(nx, ny + 1, nz), false);
										}
									} else if (Blocks.MOSS_CARPET == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.PALE_MOSS_CARPET.defaultBlockState(), 3);
									} else if (Blocks.GLOW_LICHEN == block.getBlock() || Blocks.VINE == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.AZALEA == block.getBlock() || Blocks.FLOWERING_AZALEA == block.getBlock() || Blocks.BIG_DRIPLEAF_STEM == block.getBlock() || Blocks.BIG_DRIPLEAF == block.getBlock()
											|| Blocks.SMALL_DRIPLEAF == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (Blocks.PUMPKIN == block.getBlock() || Blocks.MELON == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.HUMUS.get().defaultBlockState(), 3);
									} else if (Blocks.TUBE_CORAL_BLOCK == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_TUBE_CORAL_BLOCK.defaultBlockState(), 3);
									} else if (Blocks.BRAIN_CORAL_BLOCK == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState(), 3);
									} else if (Blocks.BUBBLE_CORAL_BLOCK == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState(), 3);
									} else if (Blocks.FIRE_CORAL_BLOCK == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState(), 3);
									} else if (Blocks.HORN_CORAL_BLOCK == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_HORN_CORAL_BLOCK.defaultBlockState(), 3);
									} else if (Blocks.TUBE_CORAL == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_TUBE_CORAL.defaultBlockState(), 3);
									} else if (Blocks.BRAIN_CORAL == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_BRAIN_CORAL.defaultBlockState(), 3);
									} else if (Blocks.BUBBLE_CORAL == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_BUBBLE_CORAL.defaultBlockState(), 3);
									} else if (Blocks.FIRE_CORAL == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_FIRE_CORAL.defaultBlockState(), 3);
									} else if (Blocks.HORN_CORAL == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.DEAD_HORN_CORAL.defaultBlockState(), 3);
									} else if (Blocks.TUBE_CORAL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_TUBE_CORAL_FAN.defaultBlockState();
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
									} else if (Blocks.BRAIN_CORAL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_BRAIN_CORAL_FAN.defaultBlockState();
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
									} else if (Blocks.FIRE_CORAL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_BUBBLE_CORAL_FAN.defaultBlockState();
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
									} else if (Blocks.HORN_CORAL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_HORN_CORAL_FAN.defaultBlockState();
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
									} else if (Blocks.TUBE_CORAL_WALL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_TUBE_CORAL_WALL_FAN.defaultBlockState();
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
									} else if (Blocks.BRAIN_CORAL_WALL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_BRAIN_CORAL_WALL_FAN.defaultBlockState();
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
									} else if (Blocks.BUBBLE_CORAL_WALL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_BUBBLE_CORAL_WALL_FAN.defaultBlockState();
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
									} else if (Blocks.FIRE_CORAL_WALL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_FIRE_CORAL_WALL_FAN.defaultBlockState();
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
									} else if (Blocks.HORN_CORAL_WALL_FAN == block.getBlock()) {
										{
											BlockPos _bp = BlockPos.containing(nx, ny, nz);
											BlockState _bs = Blocks.DEAD_HORN_CORAL_WALL_FAN.defaultBlockState();
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
									} else if (block.is(BlockTags.create(ResourceLocation.parse("wealizh:not_empty_warehouse_bucket"))) && !(WealizhModBlocks.HUMUS_WAREHOUSE_BUCKET.get() == block.getBlock())) {
										world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.HUMUS_WAREHOUSE_BUCKET.get().defaultBlockState(), 3);
									} else if (Blocks.CAVE_VINES_PLANT == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.PALE_HANGING_MOSS.defaultBlockState(), 3);
									} else if (Blocks.CACTUS == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.HUMUS.get().defaultBlockState(), 3);
									} else if (Blocks.CACTUS_FLOWER == block.getBlock()) {
										world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_SHORT_GRASS.get().defaultBlockState(), 3);
									} else if (Blocks.CAVE_VINES == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
									} else if (block.is(BlockTags.create(ResourceLocation.parse("minecraft:crops")))) {
										world.setBlock(BlockPos.containing(nx, ny, nz), WealizhModBlocks.DIED_SHORT_GRASS.get().defaultBlockState(), 3);
									} else if (Blocks.SPORE_BLOSSOM == block.getBlock()) {
										world.destroyBlock(BlockPos.containing(nx, ny, nz), false);
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
		entity.getPersistentData().putDouble("ci", (1 + entity.getPersistentData().getDoubleOr("ci", 0)));
		if (circumference <= entity.getPersistentData().getDoubleOr("ci", 0)) {
			entity.getPersistentData().putDouble("ci", 0);
			entity.getPersistentData().putDouble("cr", (8 + entity.getPersistentData().getDoubleOr("cr", 0)));
		}
		if (pow + 24 < entity.getPersistentData().getDoubleOr("cr", 0)) {
			if (!entity.level().isClientSide())
				entity.discard();
			return;
		}
		if (Mth.nextInt(RandomSource.create(), 1, 20) != 1) {
			BsRadiationsTPProcedure.execute(world, x, y, z, entity);
		}
	}
}