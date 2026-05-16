package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.LevelReader;
import net.minecraft.util.ARGB;
import net.minecraft.core.BlockPos;

public class TemperedGlassPaneBlock extends IronBarsBlock {
	public TemperedGlassPaneBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_CYAN).sound(SoundType.GLASS).strength(1f, 10f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.HAT));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return ARGB.opaque(-7758690);
	}
}