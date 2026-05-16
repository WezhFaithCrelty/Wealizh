package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.procedures.StudioDoorRandomTickPProcedure;

public class StudioDoorBlock extends DoorBlock {
	public StudioDoorBlock(BlockBehaviour.Properties properties) {
		super(BlockSetType.STONE, properties.mapColor(MapColor.TERRACOTTA_CYAN).sound(SoundType.IRON).strength(4f, 5f).requiresCorrectToolForDrops().noOcclusion().randomTicks().pushReaction(PushReaction.DESTROY)
				.isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		StudioDoorRandomTickPProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}