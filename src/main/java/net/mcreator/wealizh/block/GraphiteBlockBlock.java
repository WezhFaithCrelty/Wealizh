package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class GraphiteBlockBlock extends Block {
	public GraphiteBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_BLACK).sound(SoundType.DEEPSLATE_BRICKS).strength(4f, 5f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.XYLOPHONE));
	}
}