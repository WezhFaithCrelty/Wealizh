/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.wealizh.world.inventory.*;
import net.mcreator.wealizh.network.MenuStateUpdateMessage;
import net.mcreator.wealizh.WealizhMod;

import java.util.Map;

public class WealizhModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, WealizhMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<RapidQualitativeChangeMachineGUIMenu>> RAPID_QUALITATIVE_CHANGE_MACHINE_GUI = REGISTRY.register("rapid_qualitative_change_machine_gui",
			() -> IMenuTypeExtension.create(RapidQualitativeChangeMachineGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<QuantitativeTransformationReconstructorGUIMenu>> QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR_GUI = REGISTRY.register("quantitative_transformation_reconstructor_gui",
			() -> IMenuTypeExtension.create(QuantitativeTransformationReconstructorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CombustionChamberGUIMenu>> COMBUSTION_CHAMBER_GUI = REGISTRY.register("combustion_chamber_gui", () -> IMenuTypeExtension.create(CombustionChamberGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<StorageBatteryGUIMenu>> STORAGE_BATTERY_GUI = REGISTRY.register("storage_battery_gui", () -> IMenuTypeExtension.create(StorageBatteryGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AssemblyMachineGUIMenu>> ASSEMBLY_MACHINE_GUI = REGISTRY.register("assembly_machine_gui", () -> IMenuTypeExtension.create(AssemblyMachineGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<KilnGUIMenu>> KILN_GUI = REGISTRY.register("kiln_gui", () -> IMenuTypeExtension.create(KilnGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CrusherGUIMenu>> CRUSHER_GUI = REGISTRY.register("crusher_gui", () -> IMenuTypeExtension.create(CrusherGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PressingStorageRoomGUIMenu>> PRESSING_STORAGE_ROOM_GUI = REGISTRY.register("pressing_storage_room_gui", () -> IMenuTypeExtension.create(PressingStorageRoomGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CompressorGUIMenu>> COMPRESSOR_GUI = REGISTRY.register("compressor_gui", () -> IMenuTypeExtension.create(CompressorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CentrifugeGUIMenu>> CENTRIFUGE_GUI = REGISTRY.register("centrifuge_gui", () -> IMenuTypeExtension.create(CentrifugeGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<StorageBarrelGUIMenu>> STORAGE_BARREL_GUI = REGISTRY.register("storage_barrel_gui", () -> IMenuTypeExtension.create(StorageBarrelGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<SteelBoxGUIMenu>> STEEL_BOX_GUI = REGISTRY.register("steel_box_gui", () -> IMenuTypeExtension.create(SteelBoxGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PlanningPaperSONGUIMenu>> PLANNING_PAPER_SON_GUI = REGISTRY.register("planning_paper_son_gui", () -> IMenuTypeExtension.create(PlanningPaperSONGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CreativePlanningPaperNBTGUIMenu>> CREATIVE_PLANNING_PAPER_NBT_GUI = REGISTRY.register("creative_planning_paper_nbt_gui",
			() -> IMenuTypeExtension.create(CreativePlanningPaperNBTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PlanningPaperConcentratedAssignPrioritiesGUIMenu>> PLANNING_PAPER_CONCENTRATED_ASSIGN_PRIORITIES_GUI = REGISTRY.register("planning_paper_concentrated_assign_priorities_gui",
			() -> IMenuTypeExtension.create(PlanningPaperConcentratedAssignPrioritiesGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PlanningPaperTrVoGUIMenu>> PLANNING_PAPER_TR_VO_GUI = REGISTRY.register("planning_paper_tr_vo_gui", () -> IMenuTypeExtension.create(PlanningPaperTrVoGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<LogisticsPipeGUIMenu>> LOGISTICS_PIPE_GUI = REGISTRY.register("logistics_pipe_gui", () -> IMenuTypeExtension.create(LogisticsPipeGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AirSeparatorGUIMenu>> AIR_SEPARATOR_GUI = REGISTRY.register("air_separator_gui", () -> IMenuTypeExtension.create(AirSeparatorGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof WealizhModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}