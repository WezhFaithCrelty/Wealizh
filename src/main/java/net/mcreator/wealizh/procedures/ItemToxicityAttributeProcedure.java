package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;

import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

public class ItemToxicityAttributeProcedure {
	public static double execute(ItemStack itemstack) {
		if (WealizhModItems.DECAYED_FUNBICOBALT.get() == itemstack.getItem()) {
			return 0.08;
		}
		if (WealizhModItems.DECAYED_FUNBICOBALT_POWDER.get() == itemstack.getItem()) {
			return 0.12;
		}
		if (WealizhModBlocks.DECAYED_FUNBICOBALT_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.72;
		}
		if (WealizhModItems.LEAD_INGOT.get() == itemstack.getItem()) {
			return 0.2;
		}
		if (WealizhModItems.LEAD_POWDER.get() == itemstack.getItem()) {
			return 0.42;
		}
		if (WealizhModItems.RAW_LEAD.get() == itemstack.getItem()) {
			return 0.08;
		}
		if (WealizhModBlocks.LEAD_BLOCK.get().asItem() == itemstack.getItem()) {
			return 1.6;
		}
		if (WealizhModBlocks.RAW_LEAD_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.7;
		}
		if (WealizhModItems.FUNBELITY_CYTOSPLASM.get() == itemstack.getItem()) {
			return 3.4;
		}
		if (WealizhModItems.COMPRESSED_DECAYED_FUNBICOBALT.get() == itemstack.getItem()) {
			return 0.8;
		}
		if (WealizhModItems.FUNBIELUCURTUO.get() == itemstack.getItem()) {
			return 6.2;
		}
		if (WealizhModItems.A_PINCH_FUNBELITY_CYTOSPLASM.get() == itemstack.getItem()) {
			return 0.7;
		}
		if (WealizhModItems.LUMP_POTENT_SULFUR.get() == itemstack.getItem()) {
			return 0.5;
		}
		if (WealizhModItems.POTENT_SULFUR_POWDER.get() == itemstack.getItem()) {
			return 1.2;
		}
		if (WealizhModBlocks.POTENT_SULFUR.get().asItem() == itemstack.getItem()) {
			return 4.5;
		}
		if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get().asItem() == itemstack.getItem()) {
			return 0.56;
		}
		return 0;
	}
}