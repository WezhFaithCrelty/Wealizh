package net.mcreator.wealizh.procedures;

import net.minecraft.world.entity.Entity;

public class FlashMaskIndexReturnProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return entity.getPersistentData().getDoubleOr("Flash_Mask_i", 0);
	}
}