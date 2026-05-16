package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class BlockRadiationAbsorptionAmountAttributeProcedure {
	public static double execute(BlockState blockstate) {
		if (Blocks.AIR == blockstate.getBlock() || Blocks.VOID_AIR == blockstate.getBlock() || Blocks.CAVE_AIR == blockstate.getBlock()) {
			return 0.02;
		}
		if (WealizhModBlocks.LEAD_BLOCK.get() == blockstate.getBlock()) {
			return 2.4;
		}
		if (WealizhModBlocks.GRAPHITELEAD_BLOCK.get() == blockstate.getBlock()) {
			return 2;
		}
		if (WealizhModBlocks.RAW_LEAD_BLOCK.get() == blockstate.getBlock()) {
			return 2.2;
		}
		if (WealizhModBlocks.LEAD_ORE.get() == blockstate.getBlock()) {
			return 0.14;
		}
		if (WealizhModBlocks.DEEPSLATE_LEAD_ORE.get() == blockstate.getBlock()) {
			return 0.14;
		}
		if (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:concrete")))) {
			return 1.2;
		}
		return 0.1;
	}
}