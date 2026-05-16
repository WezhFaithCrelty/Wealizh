package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

public class BatteryAttributeEpSetProcedure {
	public static void execute(ItemStack itemstack) {
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit")))) {
			{
				final String _tagName = "Ep";
				final double _tagValue = Double.POSITIVE_INFINITY;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:battery/button_battery")))) {
			{
				final String _tagName = "Ep";
				final double _tagValue = 1;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:battery/battery")))) {
			{
				final String _tagName = "Ep";
				final double _tagValue = 2;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:battery/copper_core_battery")))) {
			{
				final String _tagName = "Ep";
				final double _tagValue = 3;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:battery/aluminum_copper_alloy_battery")))) {
			{
				final String _tagName = "Ep";
				final double _tagValue = 4;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
		}
	}
}