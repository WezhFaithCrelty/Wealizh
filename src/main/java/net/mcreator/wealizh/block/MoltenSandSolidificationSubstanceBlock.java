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

public class MoltenSandSolidificationSubstanceBlock extends FallingBlock {
	public static final MapCodec<MoltenSandSolidificationSubstanceBlock> CODEC = simpleCodec(MoltenSandSolidificationSubstanceBlock::new);

	@Override
	public MapCodec<MoltenSandSolidificationSubstanceBlock> codec() {
		return CODEC;
	}

	@Override
	public int getDustColor(BlockState blockstate, BlockGetter world, BlockPos pos) {
		return blockstate.getMapColor(world, pos).col;
	}

	public MoltenSandSolidificationSubstanceBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_GRAY).sound(SoundType.GRAVEL).strength(1f).instrument(NoteBlockInstrument.SNARE));
	}
}