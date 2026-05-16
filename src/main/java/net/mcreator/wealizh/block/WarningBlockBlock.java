package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class WarningBlockBlock extends Block {
	public WarningBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_YELLOW).strength(2f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.XYLOPHONE));
	}
}