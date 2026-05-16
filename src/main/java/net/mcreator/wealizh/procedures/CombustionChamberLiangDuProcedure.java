package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;

public class CombustionChamberLiangDuProcedure {
	public static double execute(BlockState blockstate) {
		if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			if (getPropertyByName(blockstate, "function_soul") instanceof BooleanProperty _getbp3 && blockstate.getValue(_getbp3)) {
				return 8;
			} else {
				return 12;
			}
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