package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class GUIPassageTrueOrFalseIndexReturnProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "passage") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp1)) {
			return 1;
		}
		return 0;
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