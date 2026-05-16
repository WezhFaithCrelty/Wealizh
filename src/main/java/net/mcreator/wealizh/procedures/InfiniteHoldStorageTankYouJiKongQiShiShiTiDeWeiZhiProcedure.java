package net.mcreator.wealizh.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;

import net.mcreator.wealizh.init.WealizhModItems;

public class InfiniteHoldStorageTankYouJiKongQiShiShiTiDeWeiZhiProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("consumption_pattern", 0) == 1) {
			{
				final String _tagName = "consumption_pattern";
				final double _tagValue = 0;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(
						Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u6D88\u8017\u6A21\u5F0F\u5DF2\u7981\u7528" : "\u00A7cThe Consumption Pattern Is Disable")),
						true);
		} else {
			{
				final String _tagName = "consumption_pattern";
				final double _tagValue = 1;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(
						Component.literal(((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7a\u6D88\u8017\u6A21\u5F0F\u5DF2\u542F\u7528" : "\u00A7aThe Consumption Pattern Is Enable")),
						true);
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}