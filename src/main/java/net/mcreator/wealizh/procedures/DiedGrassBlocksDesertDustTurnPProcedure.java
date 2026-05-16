package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class DiedGrassBlocksDesertDustTurnPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (WealizhModBlocks.DIED_GRASS_BLOCK.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			if (world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("wealizh:desolate_land"))) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("desert_dust") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
				}
			} else {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("desert_dust") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
		} else if (WealizhModBlocks.DIED_SHORT_GRASS.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() || WealizhModBlocks.DIED_SHORT_GRASS_DESERT_DUST.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			if (world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("wealizh:desolate_land"))) {
				world.setBlock(BlockPos.containing(x, y, z), WealizhModBlocks.DIED_SHORT_GRASS_DESERT_DUST.get().defaultBlockState(), 3);
			} else {
				world.setBlock(BlockPos.containing(x, y, z), WealizhModBlocks.DIED_SHORT_GRASS.get().defaultBlockState(), 3);
			}
		} else if (WealizhModBlocks.DIED_TALL_GRASS.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() || WealizhModBlocks.DIED_TALL_GRASS_DESERT_DUST.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()) {
			if (world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("wealizh:desolate_land"))) {
				world.setBlock(BlockPos.containing(x, y, z), WealizhModBlocks.DIED_TALL_GRASS_DESERT_DUST.get().defaultBlockState(), 3);
			} else {
				world.setBlock(BlockPos.containing(x, y, z), WealizhModBlocks.DIED_TALL_GRASS.get().defaultBlockState(), 3);
			}
		}
	}
}