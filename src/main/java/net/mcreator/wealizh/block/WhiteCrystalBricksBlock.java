package net.mcreator.wealizh.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class WhiteCrystalBricksBlock extends Block {
	public WhiteCrystalBricksBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.QUARTZ).sound(SoundType.AMETHYST).strength(1.5f).requiresCorrectToolForDrops());
	}
}