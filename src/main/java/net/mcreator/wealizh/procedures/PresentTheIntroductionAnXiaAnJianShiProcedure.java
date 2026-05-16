package net.mcreator.wealizh.procedures;

import net.minecraft.world.entity.Entity;

public class PresentTheIntroductionAnXiaAnJianShiProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putBoolean("PTIn", true);
	}
}