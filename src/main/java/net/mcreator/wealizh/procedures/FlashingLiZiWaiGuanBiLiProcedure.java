package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.wealizh.entity.TemporaryEntityEntity;

import java.util.Comparator;

public class FlashingLiZiWaiGuanBiLiProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		Entity TE = null;
		TE = findEntityInWorldRange(world, TemporaryEntityEntity.class, x, y, z, 1);
		return TE != null ? TE.getPersistentData().getDoubleOr("P_IV", 0) : 50;
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}