package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

import com.mojang.serialization.MapCodec;

public class CobaltizedSandBlock extends FallingBlock {
	public static final MapCodec<CobaltizedSandBlock> CODEC = simpleCodec(CobaltizedSandBlock::new);

	@Override
	public MapCodec<CobaltizedSandBlock> codec() {
		return CODEC;
	}

	@Override
	public int getDustColor(BlockState blockstate, BlockGetter world, BlockPos pos) {
		return blockstate.getMapColor(world, pos).col;
	}

	public CobaltizedSandBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.ICE).sound(SoundType.SAND).strength(0.5f, 0.8f).instrument(NoteBlockInstrument.SNARE));
	}
}