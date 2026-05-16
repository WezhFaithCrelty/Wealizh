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

public class CobaltizedGlassPaneBlock extends IronBarsBlock {
	public CobaltizedGlassPaneBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.ICE).sound(SoundType.GLASS).strength(0.3f, 0.5f).instrument(NoteBlockInstrument.HAT));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return ARGB.opaque(-16763905);
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 2;
	}
}