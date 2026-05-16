package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;

import net.mcreator.wealizh.init.WealizhModItems;

public class ItemsALabelTextProcedure {
	public static String execute(ItemStack itemstack) {
		String Display_Text = "";
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:battery")))) {
			if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("Ep", 0) == 0) {
				BatteryAttributeEpSetProcedure.execute(itemstack);
			}
			Display_Text = "\u00A7eE:" + TextEOrganizationValueReturnProcedure.execute(itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit"))) ? Double.POSITIVE_INFINITY : itemstack.getMaxDamage() - itemstack.getDamageValue())
					+ " / \u00A7e"
					+ TextEOrganizationValueReturnProcedure.execute(itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
							? Double.POSITIVE_INFINITY
							: (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:battery/empty")))
									? new ItemStack(BuiltInRegistries.ITEM.getValue(ResourceLocation.parse((((BuiltInRegistries.ITEM.getKey(itemstack.getItem()).toString()).replace("empty_", ""))).toLowerCase(java.util.Locale.ENGLISH))))
									: itemstack).getMaxDamage())
					+ "\n" + "\u00A7bP:" + TextEOrganizationValueReturnProcedure.execute(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("Ep", 0));
		}
		if (0.1 != BlockRadiationAbsorptionAmountAttributeProcedure.execute(itemstack.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState())
				&& 0.02 != BlockRadiationAbsorptionAmountAttributeProcedure.execute(itemstack.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState())) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A7eARBM: \u00A7r"
					+ BlockRadiationAbsorptionAmountAttributeProcedure.execute(itemstack.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()) + " \u00A77(-0.1)";
		}
		if ((BuiltInRegistries.ITEM.getKey(itemstack.getItem()).toString()).contains("fuel_tank_")) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A7eFRQ:" + (itemstack.getMaxDamage() - itemstack.getDamageValue()) + " / " + itemstack.getMaxDamage();
		}
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:gear_blade")))) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A7eDRB: " + (itemstack.getMaxDamage() - itemstack.getDamageValue()) + " / " + itemstack.getMaxDamage();
		}
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("wealizh:substance_nucleus")))) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A7eTAOM: " + (itemstack.getMaxDamage() - itemstack.getDamageValue()) + " / " + itemstack.getMaxDamage();
		}
		if (0 < ItemRadioactivityAttributeProcedure.execute(itemstack)) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A7a" + new ItemStack(WealizhModItems.RADIOACTIVITY.get()).getDisplayName().getString() + " " + ItemRadioactivityAttributeProcedure.execute(itemstack)
					+ " Rad/s";
		}
		if (0 < ItemCoalDustAttributeProcedure.execute(itemstack)) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A78" + new ItemStack(WealizhModItems.COAL_DUST_LCON.get()).getDisplayName().getString() + " " + ItemCoalDustAttributeProcedure.execute(itemstack)
					+ " CoD/s";
		}
		if (0 < ItemToxicityAttributeProcedure.execute(itemstack)) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A7a" + new ItemStack(WealizhModItems.TOXIC.get()).getDisplayName().getString() + " " + ItemToxicityAttributeProcedure.execute(itemstack) + " Tox/s";
		}
		if (0 < ItemQualitativeChangeAttributeProcedure.execute(itemstack)) {
			Display_Text = Display_Text + "" + ((Display_Text).equals("") ? "" : "\n") + "\u00A72" + new ItemStack(WealizhModItems.QUALITATIVE_CHANGE.get()).getDisplayName().getString() + " \u00B9/"
					+ ("" + ItemQualitativeChangeAttributeProcedure.execute(itemstack)).replace(".0", "");
		}
		return Display_Text;
	}
}