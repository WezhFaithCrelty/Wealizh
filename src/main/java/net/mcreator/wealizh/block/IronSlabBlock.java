package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class IronSlabBlock extends SlabBlock {
	public IronSlabBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.METAL).sound(SoundType.IRON).strength(5f, 6f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}
}