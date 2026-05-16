package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.world.inventory.*;
import net.mcreator.wealizh.init.WealizhModMenus;
import net.mcreator.wealizh.init.WealizhModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BlockGUIAchievementCJPProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof RapidQualitativeChangeMachineGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:fall_in_love_with_qualitative_change"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr4 && _plr4.containerMenu instanceof QuantitativeTransformationReconstructorGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu5 ? _menu5.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:well_it_is_still_that_same_flavo"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr8 && _plr8.containerMenu instanceof CombustionChamberGUIMenu
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu9 ? _menu9.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == WealizhModItems.FUEL_TANK_SOUL_FIRE_CATALYST
						.get()) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:the_sky_blue_flames_are_inside"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr12 && _plr12.containerMenu instanceof AssemblyMachineGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu13 ? _menu13.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:the_initial_form_of_the_factory"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr16 && _plr16.containerMenu instanceof KilnGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu17 ? _menu17.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:ancient_casting_method"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr20 && _plr20.containerMenu instanceof CrusherGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu21 ? _menu21.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:shining_debris"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr24 && _plr24.containerMenu instanceof PressingStorageRoomGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu25 ? _menu25.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:under_a_lot_of_pressure"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr28 && _plr28.containerMenu instanceof CompressorGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu29 ? _menu29.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:very_small_small_but_also_very_cute"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr32 && _plr32.containerMenu instanceof CentrifugeGUIMenu
				&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu33 ? _menu33.getSlots().get(4).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()
						&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu35 ? _menu35.getSlots().get(7).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()
						&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu37 ? _menu37.getSlots().get(8).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:before_the_order_came_into_being"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
		if (entity instanceof Player _plr40 && _plr40.containerMenu instanceof CrusherGUIMenu
				&& (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof WealizhModMenus.MenuAccessor _menu41 ? _menu41.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == WealizhModItems.SUGAR_CANE_RESIDUE.get()) {
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:get_eat"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
		}
	}
}