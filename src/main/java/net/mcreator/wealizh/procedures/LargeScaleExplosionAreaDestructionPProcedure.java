package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import java.util.Comparator;

public class LargeScaleExplosionAreaDestructionPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double pow) {
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((1 + pow * 4) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (!entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("wealizh:invincible")))) {
					EmitHurtRaysSPProcedure.execute(world, x, y, z, entityiterator, pow, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ());
				}
			}
		}
	}
}