package net.mcreator.wealizh.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.wealizh.procedures.ItemsALabelTextProcedure;
import net.mcreator.wealizh.procedures.ItemEnchantmentRemoveMendingPProcedure;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.WealizhMod;

import javax.annotation.Nullable;

import java.util.function.Consumer;

public class QuantitativeTransformationSubstanceNucleusItem extends Item {
	public QuantitativeTransformationSubstanceNucleusItem(Item.Properties properties) {
		super(properties.durability(86));
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemstack) {
		return new ItemStack(WealizhModItems.EMPTY_SUBSTANCE_NUCLEUS.get());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		Entity entity = itemstack.getEntityRepresentation() != null ? itemstack.getEntityRepresentation() : WealizhMod.clientPlayer();
		String hoverText = ItemsALabelTextProcedure.execute(itemstack);
		if (hoverText != null) {
			for (String line : hoverText.split("\n")) {
				componentConsumer.accept(Component.literal(line));
			}
		}
	}

	@Override
	public void inventoryTick(ItemStack itemstack, ServerLevel world, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
		super.inventoryTick(itemstack, world, entity, equipmentSlot);
		ItemEnchantmentRemoveMendingPProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
	}
}