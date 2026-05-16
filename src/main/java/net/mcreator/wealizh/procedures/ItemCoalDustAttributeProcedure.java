package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;

import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

public class ItemCoalDustAttributeProcedure {
	public static double execute(ItemStack itemstack) {
		if (WealizhModItems.COAL_POWDER.get() == itemstack.getItem()) {
			return 0.15;
		}
		if (WealizhModItems.CHARCOAL_POWDER.get() == itemstack.getItem()) {
			return 0.18;
		}
		if (WealizhModItems.LIGNITE.get() == itemstack.getItem()) {
			return 0.02;
		}
		if (WealizhModItems.LIGNITE_POWDER.get() == itemstack.getItem()) {
			return 0.22;
		}
		if (WealizhModBlocks.LIGNITE_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.08;
		}
		return 0;
	}
}