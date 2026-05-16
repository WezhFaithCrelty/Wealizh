package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class SmoothIronWallBlock extends WallBlock {
	public SmoothIronWallBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.METAL).sound(SoundType.IRON).strength(5f, 6f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.IRON_XYLOPHONE).forceSolidOn());
	}
}