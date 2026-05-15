package net.mcreator.wealizh;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.mcreator.wealizh.procedures.ItemsALabelTextProcedure;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT)
public class OriginalVersionItemStackALabelText {
    private static final List<Item> ITEMS_WITH_TOOLTIPS = new ArrayList<>();
    
    static {
    	
        ITEMS_WITH_TOOLTIPS.add(Items.WHITE_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.ORANGE_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.MAGENTA_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.LIGHT_BLUE_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.YELLOW_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.LIME_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.PINK_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.GRAY_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.LIGHT_GRAY_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.CYAN_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.PURPLE_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.BLUE_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.BROWN_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.GREEN_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.RED_CONCRETE);
        ITEMS_WITH_TOOLTIPS.add(Items.BLACK_CONCRETE);
    }
    
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (ITEMS_WITH_TOOLTIPS.contains(itemStack.getItem())) {
            String hoverText = ItemsALabelTextProcedure.execute(itemStack);
    		if (hoverText != null && !hoverText.isEmpty()) {
    			var tooltip = event.getToolTip();
        		for (String line : hoverText.split("\n")) {
            		if (tooltip.size() > 0) {
                    	tooltip.add(1, Component.literal(line));
                	} else {
                    	tooltip.add(Component.literal(line));
                	}
        		}
        	}
    	}
    }
}