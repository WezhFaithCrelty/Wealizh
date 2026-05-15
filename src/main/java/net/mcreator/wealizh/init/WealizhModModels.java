/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.wealizh.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.wealizh.client.model.Model锻压机;
import net.mcreator.wealizh.client.model.Model经验吸收器;
import net.mcreator.wealizh.client.model.Model手摇发电机_Converted_Converted;
import net.mcreator.wealizh.client.model.ModelCustomModel;

@EventBusSubscriber(Dist.CLIENT)
public class WealizhModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Model锻压机.LAYER_LOCATION, Model锻压机::createBodyLayer);
		event.registerLayerDefinition(Model经验吸收器.LAYER_LOCATION, Model经验吸收器::createBodyLayer);
		event.registerLayerDefinition(Model手摇发电机_Converted_Converted.LAYER_LOCATION, Model手摇发电机_Converted_Converted::createBodyLayer);
		event.registerLayerDefinition(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
	}
}