package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.procedures.BuddingWhiteCrystalGrowCrystalRandomTickPProcedure;

public class BuddingWhiteCrystalBlock extends Block {
	public BuddingWhiteCrystalBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.QUARTZ).sound(SoundType.AMETHYST).strength(1.5f).requiresCorrectToolForDrops().randomTicks().pushReaction(PushReaction.DESTROY));
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		BuddingWhiteCrystalGrowCrystalRandomTickPProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}