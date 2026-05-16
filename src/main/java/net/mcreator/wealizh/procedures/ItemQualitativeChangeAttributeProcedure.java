package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;

import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

public class ItemQualitativeChangeAttributeProcedure {
	public static double execute(ItemStack itemstack) {
		if (WealizhModItems.FUNBICOBALT.get() == itemstack.getItem()) {
			return 2400;
		}
		if (WealizhModItems.FUNBICOBALT_POWDER.get() == itemstack.getItem()) {
			return 2000;
		}
		if (WealizhModBlocks.FUNBICOBALT_BLOCK.get().asItem() == itemstack.getItem()) {
			return 24600;
		}
		if (WealizhModItems.FUNBELITY.get() == itemstack.getItem()) {
			return 2000;
		}
		if (WealizhModItems.FUNBELITY_POWDER.get() == itemstack.getItem()) {
			return 1800;
		}
		if (WealizhModBlocks.FUNBELITY_BLOCK.get().asItem() == itemstack.getItem()) {
			return 18000;
		}
		if (WealizhModItems.COMPRESSED_FUNBELITY.get() == itemstack.getItem()) {
			return 20000;
		}
		if (WealizhModItems.COMPRESSED_FUNBICOBALT.get() == itemstack.getItem()) {
			return 26400;
		}
		if (WealizhModItems.FUNBIELUMITUO.get() == itemstack.getItem()) {
			return 1200;
		}
		if (WealizhModItems.A_PINCH_FUNBELITY_POWDER.get() == itemstack.getItem()) {
			return 200;
		}
		return 0;
	}
}