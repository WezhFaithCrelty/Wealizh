package net.mcreator.wealizh.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class WealizhLconItem extends Item {
	public WealizhLconItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC));
	}
}