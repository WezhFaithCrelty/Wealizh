package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.ItemStack;

import net.mcreator.wealizh.init.WealizhModItems;

public class UnstableEntropyLabelTextProcedure {
	public static String execute() {
		return (new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c[\u4E0D\u7A33\u5B9A\u71B5]" : "\u00A7c[Unstable Entropy]";
	}
}