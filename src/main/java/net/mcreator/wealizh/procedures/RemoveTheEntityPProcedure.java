package net.mcreator.wealizh.procedures;

import net.minecraft.world.entity.Entity;

public class RemoveTheEntityPProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!entity.level().isClientSide())
			entity.discard();
	}
}