package net.mcreator.wealizh.block;

import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.procedures.DetonatorBlockExplosionTriggerProcedure;

import javax.annotation.Nullable;

public class DetonatorBlockBlock extends Block {
	public DetonatorBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.STONE).sound(SoundType.AZALEA_LEAVES).strength(0.5f, 0f).instrument(NoteBlockInstrument.SNARE));
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			DetonatorBlockExplosionTriggerProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public void wasExploded(ServerLevel world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		DetonatorBlockExplosionTriggerProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}