package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class NickelChromiumAlloyBlockBlock extends Block {
	public NickelChromiumAlloyBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.CLAY).sound(SoundType.IRON).strength(6.5f, 10f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}
}