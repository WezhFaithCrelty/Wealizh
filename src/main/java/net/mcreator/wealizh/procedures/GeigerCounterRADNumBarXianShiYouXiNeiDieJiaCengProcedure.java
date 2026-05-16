package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.wealizh.init.WealizhModItems;

public class GeigerCounterRADNumBarXianShiYouXiNeiDieJiaCengProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return hasEntityInInventory(entity, new ItemStack(WealizhModItems.GEIGER_COUNTER.get()));
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}