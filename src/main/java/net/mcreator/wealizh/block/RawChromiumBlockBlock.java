package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class RawChromiumBlockBlock extends Block {
	public RawChromiumBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.GLOW_LICHEN).strength(9f, 14f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}