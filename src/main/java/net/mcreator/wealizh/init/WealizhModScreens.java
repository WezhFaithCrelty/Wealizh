/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.wealizh.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
public class WealizhModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(WealizhModMenus.RAPID_QUALITATIVE_CHANGE_MACHINE_GUI.get(), RapidQualitativeChangeMachineGUIScreen::new);
		event.register(WealizhModMenus.QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR_GUI.get(), QuantitativeTransformationReconstructorGUIScreen::new);
		event.register(WealizhModMenus.COMBUSTION_CHAMBER_GUI.get(), CombustionChamberGUIScreen::new);
		event.register(WealizhModMenus.STORAGE_BATTERY_GUI.get(), StorageBatteryGUIScreen::new);
		event.register(WealizhModMenus.ASSEMBLY_MACHINE_GUI.get(), AssemblyMachineGUIScreen::new);
		event.register(WealizhModMenus.KILN_GUI.get(), KilnGUIScreen::new);
		event.register(WealizhModMenus.CRUSHER_GUI.get(), CrusherGUIScreen::new);
		event.register(WealizhModMenus.PRESSING_STORAGE_ROOM_GUI.get(), PressingStorageRoomGUIScreen::new);
		event.register(WealizhModMenus.COMPRESSOR_GUI.get(), CompressorGUIScreen::new);
		event.register(WealizhModMenus.CENTRIFUGE_GUI.get(), CentrifugeGUIScreen::new);
		event.register(WealizhModMenus.STORAGE_BARREL_GUI.get(), StorageBarrelGUIScreen::new);
		event.register(WealizhModMenus.STEEL_BOX_GUI.get(), SteelBoxGUIScreen::new);
		event.register(WealizhModMenus.PLANNING_PAPER_SON_GUI.get(), PlanningPaperSONGUIScreen::new);
		event.register(WealizhModMenus.CREATIVE_PLANNING_PAPER_NBT_GUI.get(), CreativePlanningPaperNBTGUIScreen::new);
		event.register(WealizhModMenus.PLANNING_PAPER_CONCENTRATED_ASSIGN_PRIORITIES_GUI.get(), PlanningPaperConcentratedAssignPrioritiesGUIScreen::new);
		event.register(WealizhModMenus.PLANNING_PAPER_TR_VO_GUI.get(), PlanningPaperTrVoGUIScreen::new);
		event.register(WealizhModMenus.LOGISTICS_PIPE_GUI.get(), LogisticsPipeGUIScreen::new);
		event.register(WealizhModMenus.AIR_SEPARATOR_GUI.get(), AirSeparatorGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}