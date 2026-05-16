package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChromiumOreBlock extends Block {
	public ChromiumOreBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.STONE).strength(3.5f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}