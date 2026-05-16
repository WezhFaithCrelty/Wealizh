package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

public class MeowTitaniumIngotWuPinZaiWuPinLanShiMeiKeFaShengProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (0.005 >= entity.getPersistentData().getDoubleOr("Rad", 0)) {
			entity.getPersistentData().putDouble("Rad", 0);
		} else {
			entity.getPersistentData().putDouble("Rad", ((0.75 / itemstack.getCount()) * entity.getPersistentData().getDoubleOr("Rad", 0)));
		}
	}
}