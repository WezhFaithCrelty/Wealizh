package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;

public class RemoteStarterLabelTextProcedure {
	public static String execute(ItemStack itemstack) {
		return "\u00A7bCDT:\u00A7e" + ("" + Math.floor(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("x", 0))).replace(".0", "") + " "
				+ ("" + Math.floor(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("y", 0))).replace(".0", "") + " "
				+ ("" + Math.floor(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("z", 0))).replace(".0", "");
	}
}