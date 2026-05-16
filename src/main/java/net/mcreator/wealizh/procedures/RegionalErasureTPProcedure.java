package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class RegionalErasureTPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double latitude = 0;
		double i = 0;
		double j = 0;
		double nx = 0;
		double ny = 0;
		double nz = 0;
		double ex = 0;
		double ey = 0;
		double ez = 0;
		double step = 0;
		double longitude = 0;
		double cos_la = 0;
		double r = 0;
		double JNT = 0;
		double nx_f = 0;
		double ny_f = 0;
		double nz_f = 0;
		String SKey = "";
		ex = Math.floor(x) + 0.5;
		ey = Math.floor(y) + 0.5;
		ez = Math.floor(z) + 0.5;
		r = entity.getPersistentData().getDoubleOr("r", 0);
		step = 1 > r ? 360 : 30 / r;
		JNT = Math.ceil(180 / step);
		for (int index0 = 0; index0 < (int) Math.ceil(360 / step); index0++) {
			longitude = Math.toRadians(i * step);
			for (int index1 = 0; index1 < (int) (JNT - (0 == i ? 0 : 1)); index1++) {
				latitude = Math.toRadians(j * step - 90);
				ny = ey + Math.sin(latitude) * r;
				if (-64 <= ny && 319 >= ny) {
					cos_la = Math.cos(latitude);
					nx = ex + cos_la * Math.cos(longitude) * r;
					nz = ez + cos_la * Math.sin(longitude) * r;
					nx_f = Math.floor(nx);
					ny_f = Math.floor(ny);
					nz_f = Math.floor(nz);
					SKey = "S" + nx_f + ny_f + nz_f;
					if (!entity.getPersistentData().getBooleanOr(SKey, false)) {
						entity.getPersistentData().putBoolean(SKey, true);
						for (Entity entityiterator : world.getEntities(entity, new AABB(nx_f, ny_f, nz_f, (nx_f + 1), (ny_f + 1), (nz_f + 1)))) {
							if (!entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("wealizh:invincible")))
									&& (("minecraft:player").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString())
											? entityiterator instanceof Player _plr5 && _plr5.gameMode() == GameType.SURVIVAL || entityiterator instanceof Player _plr6 && _plr6.gameMode() == GameType.SURVIVAL
											: true)) {
								entityiterator.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:divide_by_zero")))), 2147483647);
								if (!entityiterator.level().isClientSide())
									entityiterator.discard();
							}
						}
						if (!(world.isEmptyBlock(BlockPos.containing(nx, ny, nz)) || -1 == world.getBlockState(BlockPos.containing(nx, ny, nz)).getDestroySpeed(world, BlockPos.containing(nx, ny, nz)))) {
							world.setBlock(BlockPos.containing(nx, ny, nz), Blocks.AIR.defaultBlockState(), 3);
						}
					}
				}
				j = 1 + j;
			}
			i = 1 + i;
			j = 1;
		}
		entity.getPersistentData().putDouble("r", (1 + r));
		if (r == entity.getPersistentData().getDoubleOr("pow", 0)) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}