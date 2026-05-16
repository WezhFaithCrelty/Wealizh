package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.DoorBlock;

public class WaxedStudioDoorBlock extends DoorBlock {
	public WaxedStudioDoorBlock(BlockBehaviour.Properties properties) {
		super(BlockSetType.STONE, properties.mapColor(MapColor.TERRACOTTA_CYAN).sound(SoundType.IRON).strength(4f, 5f).requiresCorrectToolForDrops().noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false)
				.instrument(NoteBlockInstrument.IRON_XYLOPHONE));
	}
}