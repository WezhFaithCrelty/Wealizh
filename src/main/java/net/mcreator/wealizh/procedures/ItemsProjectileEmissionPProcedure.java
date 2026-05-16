package net.mcreator.wealizh.procedures;

import org.checkerframework.checker.units.qual.t;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.wealizh.WealizhMod;

public class ItemsProjectileEmissionPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack item, double TC, double r, double t, double xs, double ys, double zs) {
		WealizhMod.queueServerWork((int) t, () -> {
			for (int index0 = 0; index0 < (int) r; index0++) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), item);
					entityToSpawn.setPickUpDelay((int) TC);
					_level.addFreshEntity(entityToSpawn);
				}
				for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.45), (y + 0.45), (z + 0.45), (x + 0.55), (y + 0.55), (z + 0.55)))) {
					if (("minecraft:item").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString())
							&& ((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).copy()).getItem() == (item.copy()).getItem()) {
						entityiterator.setDeltaMovement(new Vec3(xs, ys, zs));
					}
				}
			}
		});
	}
}