package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class NetherHonesilverOreBlock extends Block {
	public NetherHonesilverOreBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.NETHER).sound(SoundType.NETHERRACK).strength(3f).lightLevel(blockstate -> 1).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
				.instrument(NoteBlockInstrument.BASEDRUM));
	}
}