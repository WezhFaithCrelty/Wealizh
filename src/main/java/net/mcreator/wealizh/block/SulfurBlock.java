package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class SulfurBlock extends Block {
	public SulfurBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.GOLD).sound(SoundType.TUFF).strength(1.5f, 6f).requiresCorrectToolForDrops().ignitedByLava().instrument(NoteBlockInstrument.BASEDRUM));
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 20;
	}
}