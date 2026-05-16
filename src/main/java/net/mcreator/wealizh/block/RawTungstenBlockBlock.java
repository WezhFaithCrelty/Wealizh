package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class RawTungstenBlockBlock extends Block {
	public RawTungstenBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_CYAN).strength(8f, 12f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}