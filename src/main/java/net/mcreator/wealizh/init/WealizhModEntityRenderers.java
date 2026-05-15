/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.wealizh.client.renderer.*;

@EventBusSubscriber(Dist.CLIENT)
public class WealizhModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(WealizhModEntities.POLLUTION_SOURCE.get(), PollutionSourceRenderer::new);
		event.registerEntityRenderer(WealizhModEntities.EMPTY_ENTITY.get(), EmptyEntityRenderer::new);
		event.registerEntityRenderer(WealizhModEntities.LARGE_SCALE_EXPLOSION_ORIGIN.get(), LargeScaleExplosionOriginRenderer::new);
		event.registerEntityRenderer(WealizhModEntities.REGIONAL_ERASURE_ORIGIN.get(), RegionalErasureOriginRenderer::new);
		event.registerEntityRenderer(WealizhModEntities.BS_RADIATIONS_ORIGIN.get(), BsRadiationsOriginRenderer::new);
		event.registerEntityRenderer(WealizhModEntities.TEMPORARY_ENTITY.get(), TemporaryEntityRenderer::new);
	}
}