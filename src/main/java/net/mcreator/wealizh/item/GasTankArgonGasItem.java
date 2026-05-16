package net.mcreator.wealizh.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.wealizh.init.WealizhModItems;

public class GasTankArgonGasItem extends Item {
	public GasTankArgonGasItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemstack) {
		return new ItemStack(WealizhModItems.GAS_TANK_EMPTY.get());
	}
}