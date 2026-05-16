package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ItemSthYPProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean have_radiation_resistance_item = false;
		if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("wealizh:netrad")))) {
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
				for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
					ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
					if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("wealizh:can_radiation_resistance")))) {
						have_radiation_resistance_item = true;
					}
				}
			}
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
				for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
					ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
					if (!have_radiation_resistance_item) {
						entity.getPersistentData().putDouble("Rad_I", ((ItemRadioactivityAttributeProcedure.execute(itemstackiterator) / 20) * itemstackiterator.getCount() + entity.getPersistentData().getDoubleOr("Rad_I", 0)));
					}
					entity.getPersistentData().putDouble("CoD_I", ((ItemCoalDustAttributeProcedure.execute(itemstackiterator) / 20) * itemstackiterator.getCount() + entity.getPersistentData().getDoubleOr("CoD_I", 0)));
					entity.getPersistentData().putDouble("Tox_I", ((ItemToxicityAttributeProcedure.execute(itemstackiterator) / 20) * itemstackiterator.getCount() + entity.getPersistentData().getDoubleOr("Tox_I", 0)));
				}
			}
		}
	}
}