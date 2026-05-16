package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class DeepslateSaltpeterOreBlock extends Block {
	public DeepslateSaltpeterOreBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.DEEPSLATE).sound(SoundType.DEEPSLATE).strength(3f, 4.5f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}