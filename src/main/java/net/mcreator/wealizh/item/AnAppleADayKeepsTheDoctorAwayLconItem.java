package net.mcreator.wealizh.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class AnAppleADayKeepsTheDoctorAwayLconItem extends Item {
	public AnAppleADayKeepsTheDoctorAwayLconItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(666).saturationModifier(666f).alwaysEdible().build(), Consumables.defaultFood().animation(ItemUseAnimation.NONE).consumeSeconds(0F).build()));
	}
}