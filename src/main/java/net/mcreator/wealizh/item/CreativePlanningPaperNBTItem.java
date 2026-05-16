package net.mcreator.wealizh.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CreativePlanningPaperNBTItem extends Item {
	public CreativePlanningPaperNBTItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).stacksTo(1));
	}
}