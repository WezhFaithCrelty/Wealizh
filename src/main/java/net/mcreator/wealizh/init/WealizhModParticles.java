/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.wealizh.client.particle.*;

@EventBusSubscriber(Dist.CLIENT)
public class WealizhModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(WealizhModParticleTypes.BLUE_LIGHT.get(), BlueLightParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.EXPLOSION_SMOKE.get(), ExplosionSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.SPARK.get(), SparkParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.BIG_EXPLOSION_SMOKE.get(), BigExplosionSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.DUST_PARTICLES.get(), DustParticlesParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.HUGE_EXPLOSION_SMOKE.get(), HugeExplosionSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.MUSHROOM_CLOUD.get(), MushroomCloudParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.HUGE_SMOKE.get(), HugeSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.GIANT_CLOUD_OF_SMOKE.get(), GiantCloudOfSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.HIGH_TEMPERATURE_SMOKE.get(), HighTemperatureSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.HUGE_PERMANENCE_SMOKE.get(), HugePermanenceSmokeParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.DESOLATE_DUST.get(), DesolateDustParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.MUSHROOM_CLOUD_STEM.get(), MushroomCloudStemParticle::provider);
		event.registerSpriteSet(WealizhModParticleTypes.SMOKE.get(), SmokeParticle::provider);
	}
}