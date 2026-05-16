package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class GUIPassageButtonPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "passage") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp1)) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("passage") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			}
		} else {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("passage") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}