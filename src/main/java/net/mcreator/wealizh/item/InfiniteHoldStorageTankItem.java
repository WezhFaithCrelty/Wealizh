package net.mcreator.wealizh.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.client.multiplayer.ClientLevel;

import net.mcreator.wealizh.procedures.InfiniteHoldStorageTankYouJiKongQiShiShiTiDeWeiZhiProcedure;
import net.mcreator.wealizh.procedures.InfiniteHoldStorageTankShuXingZhiTiGongQiProcedure;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

public class InfiniteHoldStorageTankItem extends Item {
	public InfiniteHoldStorageTankItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		InfiniteHoldStorageTankYouJiKongQiShiShiTiDeWeiZhiProcedure.execute(entity, entity.getItemInHand(hand));
		return ar;
	}

	public record ConsumptionPatternProperty() implements RangeSelectItemModelProperty {
		public static final MapCodec<ConsumptionPatternProperty> MAP_CODEC = MapCodec.unit(new ConsumptionPatternProperty());

		@Override
		public float get(ItemStack itemStackToRender, @Nullable ClientLevel clientWorld, @Nullable LivingEntity entity, int seed) {
			return (float) InfiniteHoldStorageTankShuXingZhiTiGongQiProcedure.execute(itemStackToRender);
		}

		@Override
		public MapCodec<ConsumptionPatternProperty> type() {
			return MAP_CODEC;
		}
	}
}