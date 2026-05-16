package net.mcreator.wealizh.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.mcreator.wealizh.procedures.SchrodingerYouJiFangKuaiShiFangKuaiDeWeiZhiProcedure;

public class SchrodingerItem extends Item {
	public SchrodingerItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		SchrodingerYouJiFangKuaiShiFangKuaiDeWeiZhiProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getPlayer());
		return InteractionResult.SUCCESS;
	}
}