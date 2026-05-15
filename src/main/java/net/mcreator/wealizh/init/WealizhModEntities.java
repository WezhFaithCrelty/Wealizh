/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.wealizh.entity.*;
import net.mcreator.wealizh.WealizhMod;

@EventBusSubscriber
public class WealizhModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, WealizhMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<PollutionSourceEntity>> POLLUTION_SOURCE = register("pollution_source",
			EntityType.Builder.<PollutionSourceEntity>of(PollutionSourceEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(0).setUpdateInterval(3).fireImmune()

					.sized(0f, 0f));
	public static final DeferredHolder<EntityType<?>, EntityType<EmptyEntityEntity>> EMPTY_ENTITY = register("empty_entity",
			EntityType.Builder.<EmptyEntityEntity>of(EmptyEntityEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(0).setUpdateInterval(3)

					.sized(1f, 1f));
	public static final DeferredHolder<EntityType<?>, EntityType<LargeScaleExplosionOriginEntity>> LARGE_SCALE_EXPLOSION_ORIGIN = register("large_scale_explosion_origin",
			EntityType.Builder.<LargeScaleExplosionOriginEntity>of(LargeScaleExplosionOriginEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(0).setUpdateInterval(3).fireImmune()

					.sized(0f, 0f));
	public static final DeferredHolder<EntityType<?>, EntityType<RegionalErasureOriginEntity>> REGIONAL_ERASURE_ORIGIN = register("regional_erasure_origin",
			EntityType.Builder.<RegionalErasureOriginEntity>of(RegionalErasureOriginEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(0).setUpdateInterval(3).fireImmune()

					.sized(0f, 0f));
	public static final DeferredHolder<EntityType<?>, EntityType<BsRadiationsOriginEntity>> BS_RADIATIONS_ORIGIN = register("bs_radiations_origin",
			EntityType.Builder.<BsRadiationsOriginEntity>of(BsRadiationsOriginEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(0).setUpdateInterval(3).fireImmune()

					.sized(0f, 0f));
	public static final DeferredHolder<EntityType<?>, EntityType<TemporaryEntityEntity>> TEMPORARY_ENTITY = register("temporary_entity",
			EntityType.Builder.<TemporaryEntityEntity>of(TemporaryEntityEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(0).setUpdateInterval(3).fireImmune()

					.sized(0.1f, 0.1f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(WealizhMod.MODID, registryname))));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		PollutionSourceEntity.init(event);
		EmptyEntityEntity.init(event);
		LargeScaleExplosionOriginEntity.init(event);
		RegionalErasureOriginEntity.init(event);
		BsRadiationsOriginEntity.init(event);
		TemporaryEntityEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(POLLUTION_SOURCE.get(), PollutionSourceEntity.createAttributes().build());
		event.put(EMPTY_ENTITY.get(), EmptyEntityEntity.createAttributes().build());
		event.put(LARGE_SCALE_EXPLOSION_ORIGIN.get(), LargeScaleExplosionOriginEntity.createAttributes().build());
		event.put(REGIONAL_ERASURE_ORIGIN.get(), RegionalErasureOriginEntity.createAttributes().build());
		event.put(BS_RADIATIONS_ORIGIN.get(), BsRadiationsOriginEntity.createAttributes().build());
		event.put(TEMPORARY_ENTITY.get(), TemporaryEntityEntity.createAttributes().build());
	}
}