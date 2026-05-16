package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.core.BlockPos;

public class TrinititePaneBlock extends IronBarsBlock {
	public TrinititePaneBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.PLANT).sound(SoundType.GLASS).strength(0.6f, 1f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.HAT));
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 2;
	}
}