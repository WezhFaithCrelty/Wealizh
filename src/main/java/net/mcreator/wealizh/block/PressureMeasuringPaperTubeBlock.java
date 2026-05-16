package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.EndRodBlock;

public class PressureMeasuringPaperTubeBlock extends EndRodBlock {
	public PressureMeasuringPaperTubeBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.SNOW).sound(SoundType.CHERRY_LEAVES).instabreak().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.GUITAR).forceSolidOff());
	}
}