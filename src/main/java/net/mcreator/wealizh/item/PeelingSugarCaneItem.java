package net.mcreator.wealizh.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class PeelingSugarCaneItem extends Item {
	public PeelingSugarCaneItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(1).saturationModifier(1.2f).build(), Consumables.defaultFood().consumeSeconds(5F).build()));
	}
}