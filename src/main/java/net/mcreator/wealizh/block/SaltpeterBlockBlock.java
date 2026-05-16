package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class SaltpeterBlockBlock extends Block {
	public SaltpeterBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.SNOW).strength(2f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}
}