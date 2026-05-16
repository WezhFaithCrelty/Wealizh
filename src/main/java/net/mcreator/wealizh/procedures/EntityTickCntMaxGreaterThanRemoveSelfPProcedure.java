package net.mcreator.wealizh.procedures;

import net.minecraft.world.entity.Entity;

public class EntityTickCntMaxGreaterThanRemoveSelfPProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("tick_cnt", (1 + entity.getPersistentData().getDoubleOr("tick_cnt", 0)));
		if (2 < entity.getPersistentData().getDoubleOr("tick_cnt", 0)) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}