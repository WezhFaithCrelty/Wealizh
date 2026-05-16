package net.mcreator.wealizh.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.wealizh.procedures.GeigerCounterYouJiKongQiShiShiTiDeWeiZhiProcedure;
import net.mcreator.wealizh.procedures.GeigerCounterWuPinZaiWuPinLanShiMeiKeFaShengProcedure;

import javax.annotation.Nullable;

public class GeigerCounterItem extends Item {
	public GeigerCounterItem(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		GeigerCounterYouJiKongQiShiShiTiDeWeiZhiProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, ServerLevel world, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
		super.inventoryTick(itemstack, world, entity, equipmentSlot);
		GeigerCounterWuPinZaiWuPinLanShiMeiKeFaShengProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
	}
}