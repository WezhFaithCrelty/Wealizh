package net.mcreator.wealizh.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class SugarCaneResidueItem extends Item {
	public SugarCaneResidueItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(0).saturationModifier(0.2f).build(), Consumables.defaultFood().consumeSeconds(12F).build()));
	}
}