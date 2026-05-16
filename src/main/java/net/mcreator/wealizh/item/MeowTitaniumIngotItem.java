package net.mcreator.wealizh.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.wealizh.procedures.MeowTitaniumIngotWuPinZaiWuPinLanShiMeiKeFaShengProcedure;

import javax.annotation.Nullable;

import java.util.function.Consumer;

public class MeowTitaniumIngotItem extends Item {
	public MeowTitaniumIngotItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.wealizh.meow_titanium_ingot.description_0"));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, ServerLevel world, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
		super.inventoryTick(itemstack, world, entity, equipmentSlot);
		MeowTitaniumIngotWuPinZaiWuPinLanShiMeiKeFaShengProcedure.execute(entity, itemstack);
	}
}