package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class SteckedStoneAlloyBlockBlock extends Block {
	public SteckedStoneAlloyBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.METAL).sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.XYLOPHONE));
	}
}