package net.mcreator.wealizh.procedures;

import net.minecraft.world.entity.Entity;

public class TemporaryMarkTickPProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("TC", (1 + entity.getPersistentData().getDoubleOr("TC", 0)));
		if (2 < entity.getPersistentData().getDoubleOr("TC", 0)) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}