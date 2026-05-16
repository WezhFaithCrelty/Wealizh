package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;

import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

public class ItemRadioactivityAttributeProcedure {
	public static double execute(ItemStack itemstack) {
		if (WealizhModItems.ROCK_CRYSTAL.get() == itemstack.getItem()) {
			return 0.02;
		}
		if (WealizhModItems.ROCK_CRYSTAL_POWDER.get() == itemstack.getItem()) {
			return 0.04;
		}
		if (WealizhModBlocks.DEEPSLATE_ROCK_CRYSTAL_ORE.get().asItem() == itemstack.getItem()) {
			return 0.01;
		}
		if (WealizhModBlocks.ROCK_CRYSTAL_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.18;
		}
		if (WealizhModBlocks.THERMAL_ENERGY_POWER_GENERATION_PANEL.get().asItem() == itemstack.getItem()) {
			return 0.06;
		}
		if (WealizhModBlocks.CASING_THERMAL_ENERGY_POWER_GENERATION_PANEL.get().asItem() == itemstack.getItem()) {
			return 0.05;
		}
		if (WealizhModItems.URANIUM_INGOT.get() == itemstack.getItem()) {
			return 0.08;
		}
		if (WealizhModItems.RAW_URANIUM.get() == itemstack.getItem()) {
			return 0.06;
		}
		if (WealizhModItems.URANIUM_POWDER.get() == itemstack.getItem()) {
			return 0.12;
		}
		if (WealizhModBlocks.URANIUM_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.72;
		}
		if (WealizhModBlocks.RAW_URANIUM_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.54;
		}
		if (WealizhModBlocks.URANIUM_ORE.get().asItem() == itemstack.getItem()) {
			return 0.02;
		}
		if (WealizhModBlocks.DEEPSLATE_URANIUM_ORE.get().asItem() == itemstack.getItem()) {
			return 0.02;
		}
		if (WealizhModItems.YELLOW_CAKE.get() == itemstack.getItem()) {
			return 0.16;
		}
		if (WealizhModItems.RAW_REDSTONE_CRYSTAL.get() == itemstack.getItem()) {
			return 0.008;
		}
		if (WealizhModItems.RAW_REDSTONE.get() == itemstack.getItem()) {
			return 0.01;
		}
		if (WealizhModBlocks.RAW_REDSTONE_CRYSTAL_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.072;
		}
		if (WealizhModBlocks.RAW_REDSTONE_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.1;
		}
		if (WealizhModBlocks.MIU_POLYMER_BLOCK.get().asItem() == itemstack.getItem()) {
			return 86.437;
		}
		if (WealizhModItems.URANIUM_235_INGOT.get() == itemstack.getItem()) {
			return 0.35;
		}
		if (WealizhModItems.URANIUM_235_NUGGET.get() == itemstack.getItem()) {
			return 0.03;
		}
		if (WealizhModItems.URANIUM_235_POWDER.get() == itemstack.getItem()) {
			return 0.42;
		}
		if (WealizhModItems.URANIUM_235_BILLETSMATERIAL.get() == itemstack.getItem()) {
			return 0.58;
		}
		if (WealizhModBlocks.URANIUM_235_BLOCK.get().asItem() == itemstack.getItem()) {
			return 3.2;
		}
		if (WealizhModItems.LITYTIUM_INGOT.get() == itemstack.getItem()) {
			return 0.006;
		}
		if (WealizhModItems.LITYTIUM_POWDER.get() == itemstack.getItem()) {
			return 0.008;
		}
		if (WealizhModBlocks.LITYTIUM_BLOCK.get().asItem() == itemstack.getItem()) {
			return 0.054;
		}
		if (WealizhModBlocks.RADIOACTIVE_FALLOUT.get().asItem() == itemstack.getItem()) {
			return 0.02;
		}
		return 0;
	}
}