/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.wealizh.block.entity.*;
import net.mcreator.wealizh.WealizhMod;

@EventBusSubscriber
public class WealizhModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, WealizhMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RapidQualitativeChangeMachineBlockEntity>> RAPID_QUALITATIVE_CHANGE_MACHINE = register("rapid_qualitative_change_machine", WealizhModBlocks.RAPID_QUALITATIVE_CHANGE_MACHINE,
			RapidQualitativeChangeMachineBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuantitativeTransformationReconstructorBlockEntity>> QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR = register("quantitative_transformation_reconstructor",
			WealizhModBlocks.QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR, QuantitativeTransformationReconstructorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CombustionChamberBlockEntity>> COMBUSTION_CHAMBER = register("combustion_chamber", WealizhModBlocks.COMBUSTION_CHAMBER, CombustionChamberBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RockCrystalBlockBlockEntity>> ROCK_CRYSTAL_BLOCK = register("rock_crystal_block", WealizhModBlocks.ROCK_CRYSTAL_BLOCK, RockCrystalBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ThermalEnergyPowerGenerationPanelBlockEntity>> THERMAL_ENERGY_POWER_GENERATION_PANEL = register("thermal_energy_power_generation_panel",
			WealizhModBlocks.THERMAL_ENERGY_POWER_GENERATION_PANEL, ThermalEnergyPowerGenerationPanelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AluminumStorageBatteryBlockEntity>> ALUMINUM_STORAGE_BATTERY = register("aluminum_storage_battery", WealizhModBlocks.ALUMINUM_STORAGE_BATTERY,
			AluminumStorageBatteryBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CasingThermalEnergyPowerGenerationPanelBlockEntity>> CASING_THERMAL_ENERGY_POWER_GENERATION_PANEL = register("casing_thermal_energy_power_generation_panel",
			WealizhModBlocks.CASING_THERMAL_ENERGY_POWER_GENERATION_PANEL, CasingThermalEnergyPowerGenerationPanelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PyriteCubeBlockEntity>> PYRITE_CUBE = register("pyrite_cube", WealizhModBlocks.PYRITE_CUBE, PyriteCubeBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StorageBatteryBlockEntity>> STORAGE_BATTERY = register("storage_battery", WealizhModBlocks.STORAGE_BATTERY, StorageBatteryBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MiuPolymerBlockBlockEntity>> MIU_POLYMER_BLOCK = register("miu_polymer_block", WealizhModBlocks.MIU_POLYMER_BLOCK, MiuPolymerBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfiniteStorageBatteryBlockEntity>> INFINITE_STORAGE_BATTERY = register("infinite_storage_battery", WealizhModBlocks.INFINITE_STORAGE_BATTERY,
			InfiniteStorageBatteryBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UraniumBlockBlockEntity>> URANIUM_BLOCK = register("uranium_block", WealizhModBlocks.URANIUM_BLOCK, UraniumBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RawUraniumBlockBlockEntity>> RAW_URANIUM_BLOCK = register("raw_uranium_block", WealizhModBlocks.RAW_URANIUM_BLOCK, RawUraniumBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AluminumCopperAlloyStorageBatteryBlockEntity>> ALUMINUM_COPPER_ALLOY_STORAGE_BATTERY = register("aluminum_copper_alloy_storage_battery",
			WealizhModBlocks.ALUMINUM_COPPER_ALLOY_STORAGE_BATTERY, AluminumCopperAlloyStorageBatteryBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HandCrankedGeneratorBlockEntity>> HAND_CRANKED_GENERATOR = register("hand_cranked_generator", WealizhModBlocks.HAND_CRANKED_GENERATOR, HandCrankedGeneratorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AssemblyTypeControlConsoleBlockEntity>> ASSEMBLY_TYPE_CONTROL_CONSOLE = register("assembly_type_control_console", WealizhModBlocks.ASSEMBLY_TYPE_CONTROL_CONSOLE,
			AssemblyTypeControlConsoleBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SteelBoxBlockEntity>> STEEL_BOX = register("steel_box", WealizhModBlocks.STEEL_BOX, SteelBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KilnBlockEntity>> KILN = register("kiln", WealizhModBlocks.KILN, KilnBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SchrodingerBlockBlockEntity>> SCHRODINGER_BLOCK = register("schrodinger_block", WealizhModBlocks.SCHRODINGER_BLOCK, SchrodingerBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrusherBlockEntity>> CRUSHER = register("crusher", WealizhModBlocks.CRUSHER, CrusherBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RawRedstoneCrystalBlockBlockEntity>> RAW_REDSTONE_CRYSTAL_BLOCK = register("raw_redstone_crystal_block", WealizhModBlocks.RAW_REDSTONE_CRYSTAL_BLOCK,
			RawRedstoneCrystalBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PressingMachineBlockEntity>> PRESSING_MACHINE = register("pressing_machine", WealizhModBlocks.PRESSING_MACHINE, PressingMachineBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PressingStorageRoomBlockEntity>> PRESSING_STORAGE_ROOM = register("pressing_storage_room", WealizhModBlocks.PRESSING_STORAGE_ROOM, PressingStorageRoomBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RawRedstoneBlockBlockEntity>> RAW_REDSTONE_BLOCK = register("raw_redstone_block", WealizhModBlocks.RAW_REDSTONE_BLOCK, RawRedstoneBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Uranium235BlockBlockEntity>> URANIUM_235_BLOCK = register("uranium_235_block", WealizhModBlocks.URANIUM_235_BLOCK, Uranium235BlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CompressorBlockEntity>> COMPRESSOR = register("compressor", WealizhModBlocks.COMPRESSOR, CompressorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PlacerBlockEntity>> PLACER = register("placer", WealizhModBlocks.PLACER, PlacerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DischargeBoxBlockEntity>> DISCHARGE_BOX = register("discharge_box", WealizhModBlocks.DISCHARGE_BOX, DischargeBoxBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExperienceAbsorberBlockEntity>> EXPERIENCE_ABSORBER = register("experience_absorber", WealizhModBlocks.EXPERIENCE_ABSORBER, ExperienceAbsorberBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExperienceReleaseDeviceBlockEntity>> EXPERIENCE_RELEASE_DEVICE = register("experience_release_device", WealizhModBlocks.EXPERIENCE_RELEASE_DEVICE,
			ExperienceReleaseDeviceBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CentrifugeBlockEntity>> CENTRIFUGE = register("centrifuge", WealizhModBlocks.CENTRIFUGE, CentrifugeBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FunbicobaltBlockBlockEntity>> FUNBICOBALT_BLOCK = register("funbicobalt_block", WealizhModBlocks.FUNBICOBALT_BLOCK, FunbicobaltBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DecayedFunbicobaltBlockBlockEntity>> DECAYED_FUNBICOBALT_BLOCK = register("decayed_funbicobalt_block", WealizhModBlocks.DECAYED_FUNBICOBALT_BLOCK,
			DecayedFunbicobaltBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LitytiumBlockBlockEntity>> LITYTIUM_BLOCK = register("litytium_block", WealizhModBlocks.LITYTIUM_BLOCK, LitytiumBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopperStorageBarrelBlockEntity>> COPPER_STORAGE_BARREL = register("copper_storage_barrel", WealizhModBlocks.COPPER_STORAGE_BARREL, CopperStorageBarrelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IronStorageBarrelBlockEntity>> IRON_STORAGE_BARREL = register("iron_storage_barrel", WealizhModBlocks.IRON_STORAGE_BARREL, IronStorageBarrelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WireBlockEntity>> WIRE = register("wire", WealizhModBlocks.WIRE, WireBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PipeBlockEntity>> PIPE = register("pipe", WealizhModBlocks.PIPE, PipeBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfiniteStorageBarrelBlockEntity>> INFINITE_STORAGE_BARREL = register("infinite_storage_barrel", WealizhModBlocks.INFINITE_STORAGE_BARREL,
			InfiniteStorageBarrelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcentratedWireBlockEntity>> CONCENTRATED_WIRE = register("concentrated_wire", WealizhModBlocks.CONCENTRATED_WIRE, ConcentratedWireBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcentratedPipeBlockEntity>> CONCENTRATED_PIPE = register("concentrated_pipe", WealizhModBlocks.CONCENTRATED_PIPE, ConcentratedPipeBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UnidirectionalWireValveBlockEntity>> UNIDIRECTIONAL_WIRE_VALVE = register("unidirectional_wire_valve", WealizhModBlocks.UNIDIRECTIONAL_WIRE_VALVE,
			UnidirectionalWireValveBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UnidirectionalPipeValveBlockEntity>> UNIDIRECTIONAL_PIPE_VALVE = register("unidirectional_pipe_valve", WealizhModBlocks.UNIDIRECTIONAL_PIPE_VALVE,
			UnidirectionalPipeValveBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TitaniumStorageBarrelBlockEntity>> TITANIUM_STORAGE_BARREL = register("titanium_storage_barrel", WealizhModBlocks.TITANIUM_STORAGE_BARREL,
			TitaniumStorageBarrelBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectricLampBlockEntity>> ELECTRIC_LAMP = register("electric_lamp", WealizhModBlocks.ELECTRIC_LAMP, ElectricLampBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LogisticsPipeBlockEntity>> LOGISTICS_PIPE = register("logistics_pipe", WealizhModBlocks.LOGISTICS_PIPE, LogisticsPipeBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConcentratedLogisticsPipeBlockEntity>> CONCENTRATED_LOGISTICS_PIPE = register("concentrated_logistics_pipe", WealizhModBlocks.CONCENTRATED_LOGISTICS_PIPE,
			ConcentratedLogisticsPipeBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TemporaryBlockBlockEntity>> TEMPORARY_BLOCK = register("temporary_block", WealizhModBlocks.TEMPORARY_BLOCK, TemporaryBlockBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectricHeaterBlockEntity>> ELECTRIC_HEATER = register("electric_heater", WealizhModBlocks.ELECTRIC_HEATER, ElectricHeaterBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AirSeparatorBlockEntity>> AIR_SEPARATOR = register("air_separator", WealizhModBlocks.AIR_SEPARATOR, AirSeparatorBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RAPID_QUALITATIVE_CHANGE_MACHINE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, QUANTITATIVE_TRANSFORMATION_RECONSTRUCTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COMBUSTION_CHAMBER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ROCK_CRYSTAL_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, THERMAL_ENERGY_POWER_GENERATION_PANEL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ALUMINUM_STORAGE_BATTERY.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CASING_THERMAL_ENERGY_POWER_GENERATION_PANEL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PYRITE_CUBE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, STORAGE_BATTERY.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MIU_POLYMER_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, INFINITE_STORAGE_BATTERY.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, URANIUM_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RAW_URANIUM_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ALUMINUM_COPPER_ALLOY_STORAGE_BATTERY.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, HAND_CRANKED_GENERATOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ASSEMBLY_TYPE_CONTROL_CONSOLE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, STEEL_BOX.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, KILN.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SCHRODINGER_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CRUSHER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RAW_REDSTONE_CRYSTAL_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PRESSING_MACHINE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PRESSING_STORAGE_ROOM.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RAW_REDSTONE_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, URANIUM_235_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COMPRESSOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PLACER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DISCHARGE_BOX.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EXPERIENCE_ABSORBER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, EXPERIENCE_RELEASE_DEVICE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CENTRIFUGE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, FUNBICOBALT_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DECAYED_FUNBICOBALT_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, LITYTIUM_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, COPPER_STORAGE_BARREL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, IRON_STORAGE_BARREL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, WIRE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PIPE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, INFINITE_STORAGE_BARREL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCENTRATED_WIRE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCENTRATED_PIPE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UNIDIRECTIONAL_WIRE_VALVE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UNIDIRECTIONAL_PIPE_VALVE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, TITANIUM_STORAGE_BARREL.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIC_LAMP.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, LOGISTICS_PIPE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CONCENTRATED_LOGISTICS_PIPE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, TEMPORARY_BLOCK.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ELECTRIC_HEATER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, AIR_SEPARATOR.get(), SidedInvWrapper::new);
	}
}