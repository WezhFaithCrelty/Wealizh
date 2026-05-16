package net.mcreator.wealizh.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class SaltItem extends Item {
	public SaltItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(0).saturationModifier(0.3f).alwaysEdible().build(), Consumables.defaultFood().consumeSeconds(0.6F).build()));
	}
}