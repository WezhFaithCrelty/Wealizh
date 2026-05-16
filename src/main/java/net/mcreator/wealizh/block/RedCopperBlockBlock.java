package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class RedCopperBlockBlock extends Block {
	public RedCopperBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.TERRACOTTA_ORANGE).sound(SoundType.COPPER).strength(5f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.COW_BELL));
	}
}