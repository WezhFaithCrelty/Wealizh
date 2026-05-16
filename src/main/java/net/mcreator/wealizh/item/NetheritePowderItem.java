package net.mcreator.wealizh.item;

import net.minecraft.world.item.Item;

public class NetheritePowderItem extends Item {
	public NetheritePowderItem(Item.Properties properties) {
		super(properties.fireResistant());
	}
}