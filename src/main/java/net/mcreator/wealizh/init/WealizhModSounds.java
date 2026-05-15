/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.wealizh.WealizhMod;

public class WealizhModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, WealizhMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> LOADING_AND_UNLOADING = REGISTRY.register("loading_and_unloading", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "loading_and_unloading")));
	public static final DeferredHolder<SoundEvent, SoundEvent> EMPTY_DISCHARGE = REGISTRY.register("empty_discharge", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "empty_discharge")));
	public static final DeferredHolder<SoundEvent, SoundEvent> COMBUSTION_CHAMBER_TYPE_MECHANICAL_COMBUSTION = REGISTRY.register("combustion_chamber_type_mechanical_combustion",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "combustion_chamber_type_mechanical_combustion")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MECHANICAL_CLICK = REGISTRY.register("mechanical_click", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "mechanical_click")));
	public static final DeferredHolder<SoundEvent, SoundEvent> POWER_OUTAGE = REGISTRY.register("power_outage", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "power_outage")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ASSEMBLY = REGISTRY.register("assembly", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "assembly")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CRUSHER_OPERATION = REGISTRY.register("crusher_operation", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "crusher_operation")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PRESSING_MACHINE_FORGING = REGISTRY.register("pressing_machine_forging",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "pressing_machine_forging")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PISTON_GROUP_DRIVE_MECHANISM = REGISTRY.register("piston_group_drive_mechanism",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "piston_group_drive_mechanism")));
	public static final DeferredHolder<SoundEvent, SoundEvent> BUZZER_BEEP = REGISTRY.register("buzzer_beep", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "buzzer_beep")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER_COUNTER_TICK = REGISTRY.register("geiger_counter_tick", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "geiger_counter_tick")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER_COUNTER_TICK2 = REGISTRY.register("geiger_counter_tick2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "geiger_counter_tick2")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER_COUNTER_TICK3 = REGISTRY.register("geiger_counter_tick3", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "geiger_counter_tick3")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER_COUNTER_TICK4 = REGISTRY.register("geiger_counter_tick4", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "geiger_counter_tick4")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER_COUNTER_TICK5 = REGISTRY.register("geiger_counter_tick5", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "geiger_counter_tick5")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GEIGER_COUNTER_TICK6 = REGISTRY.register("geiger_counter_tick6", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "geiger_counter_tick6")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PULL_THE_SLIDE_CATCH = REGISTRY.register("pull_the_slide_catch", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "pull_the_slide_catch")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CENTRIFUGE_FUNCTION = REGISTRY.register("centrifuge_function", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "centrifuge_function")));
	public static final DeferredHolder<SoundEvent, SoundEvent> STORAGE_BARREL_OPEN = REGISTRY.register("storage_barrel_open", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "storage_barrel_open")));
	public static final DeferredHolder<SoundEvent, SoundEvent> STORAGE_BARREL_CLOSE = REGISTRY.register("storage_barrel_close", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "storage_barrel_close")));
	public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_COLLISION = REGISTRY.register("steel_collision", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "steel_collision")));
	public static final DeferredHolder<SoundEvent, SoundEvent> TINNITUS = REGISTRY.register("tinnitus", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "tinnitus")));
	public static final DeferredHolder<SoundEvent, SoundEvent> LARGE_SCALE_EXPLOSION = REGISTRY.register("large_scale_explosion", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("wealizh", "large_scale_explosion")));
}