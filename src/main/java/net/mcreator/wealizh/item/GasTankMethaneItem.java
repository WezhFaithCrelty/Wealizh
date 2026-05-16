package net.mcreator.wealizh.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.wealizh.init.WealizhModItems;

public class GasTankMethaneItem extends Item {
	public GasTankMethaneItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemstack) {
		return new ItemStack(WealizhModItems.GAS_TANK_EMPTY.get());
	}
}