package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ZhegncopperBlockBlock extends Block {
	public ZhegncopperBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.WOOD).sound(SoundType.COPPER).strength(5f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}
}