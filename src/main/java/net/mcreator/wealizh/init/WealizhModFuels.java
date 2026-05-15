/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;

@EventBusSubscriber
public class WealizhModFuels {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		ItemStack itemstack = event.getItemStack();
		if (itemstack.getItem() == WealizhModItems.COAL_POWDER.get())
			event.setBurnTime(1200);
		else if (itemstack.getItem() == WealizhModItems.CHARCOAL_POWDER.get())
			event.setBurnTime(1200);
		else if (itemstack.getItem() == WealizhModItems.LIGNITE.get())
			event.setBurnTime(1400);
		else if (itemstack.getItem() == WealizhModItems.LIGNITE_POWDER.get())
			event.setBurnTime(1000);
		else if (itemstack.getItem() == WealizhModBlocks.LIGNITE_BLOCK.get().asItem())
			event.setBurnTime(14000);
		else if (itemstack.getItem() == WealizhModBlocks.APPLE_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.GOLDEN_APPLE_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.POTATO_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.POISONOUS_POTATO_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.CARROT_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.GOLDEN_CARROT_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.BEETROOT_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.GLISTERING_MELON_SLICE_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.SWEET_BERRIES_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.GLOW_BERRIES_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.SUGAR_CANE_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.EGG_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == Blocks.SHORT_GRASS.asItem())
			event.setBurnTime(50);
		else if (itemstack.getItem() == Blocks.TALL_GRASS.asItem())
			event.setBurnTime(50);
		else if (itemstack.getItem() == Blocks.FERN.asItem())
			event.setBurnTime(50);
		else if (itemstack.getItem() == Blocks.LARGE_FERN.asItem())
			event.setBurnTime(50);
		else if (itemstack.getItem() == WealizhModItems.PEAT.get())
			event.setBurnTime(1200);
		else if (itemstack.getItem() == WealizhModBlocks.PEAT_BLOCK.get().asItem())
			event.setBurnTime(12000);
		else if (itemstack.getItem() == WealizhModBlocks.DECAYED_LOG.get().asItem())
			event.setBurnTime(320);
		else if (itemstack.getItem() == WealizhModBlocks.DIED_SHORT_GRASS.get().asItem())
			event.setBurnTime(50);
		else if (itemstack.getItem() == WealizhModBlocks.DIED_TALL_GRASS.get().asItem())
			event.setBurnTime(50);
		else if (itemstack.getItem() == WealizhModBlocks.HUMUS_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.ENCHANTED_GOLDEN_APPLE_WAREHOUSE_BUCKET.get().asItem())
			event.setBurnTime(180);
		else if (itemstack.getItem() == WealizhModBlocks.CHARCOALIZED_LOG.get().asItem())
			event.setBurnTime(800);
		else if (itemstack.getItem() == WealizhModBlocks.CHARCOALIZED_PLANKS.get().asItem())
			event.setBurnTime(600);
		else if (itemstack.getItem() == WealizhModBlocks.CHARCOALIZED_PLANKS_SLAB.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == WealizhModBlocks.CHARCOALIZED_PLANKS_STAIR.get().asItem())
			event.setBurnTime(500);
		else if (itemstack.getItem() == WealizhModBlocks.CHARCOALIZED_PLANKS_FENCE.get().asItem())
			event.setBurnTime(400);
		else if (itemstack.getItem() == WealizhModBlocks.CHARCOALIZED_PLANKS_FENCE_GATE.get().asItem())
			event.setBurnTime(400);
		else if (itemstack.getItem() == WealizhModItems.ANEMENROUNZE.get())
			event.setBurnTime(2147483647);
	}
}