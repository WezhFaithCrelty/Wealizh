package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class NaNSubstanceBlock extends Block {
	public NaNSubstanceBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_MAGENTA).instabreak());
	}
}