package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.util.TriState;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.procedures.DiedGrassBlockRandomTickPProcedure;

public class DiedGrassBlockBlock extends Block {
	public static final BooleanProperty DESERT_DUST = BooleanProperty.create("desert_dust");

	public DiedGrassBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.WOOD).sound(SoundType.GRASS).strength(0.6f).randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(DESERT_DUST, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(DESERT_DUST);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(DESERT_DUST, false);
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction direction, BlockState plant) {
		return TriState.TRUE;
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		DiedGrassBlockRandomTickPProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}