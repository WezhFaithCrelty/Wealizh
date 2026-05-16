package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class RawZhegncopperBlockBlock extends Block {
	public RawZhegncopperBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.WOOD).strength(5f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}