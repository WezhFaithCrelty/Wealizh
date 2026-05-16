package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class TungstenBlockBlock extends Block {
	public TungstenBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_CYAN).sound(SoundType.IRON).strength(8f, 12f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}
}