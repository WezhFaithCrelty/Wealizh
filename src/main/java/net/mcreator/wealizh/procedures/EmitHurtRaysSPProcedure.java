package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

public class EmitHurtRaysSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double IY, double x2, double y2, double z2) {
		if (entity == null)
			return;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double DC = 0;
		double ax = 0;
		double ay = 0;
		double az = 0;
		double Injury_UT = 0;
		double Block_ER = 0;
		DC = DistanceCalculationProcedure.execute(x, y, z, x2, y2, z2);
		Injury_UT = IY;
		dx = (x2 - x) / DC;
		dy = (y2 - y) / DC;
		dz = (z2 - z) / DC;
		ax = x;
		ay = y;
		az = z;
		for (int index0 = 0; index0 < (int) DC; index0++) {
			ax = dx + ax;
			ay = dy + ay;
			az = dz + az;
			Block_ER = world.isEmptyBlock(BlockPos.containing(ax, ay, az)) ? 1 : world.getBlockState(BlockPos.containing(ax, ay, az)).getBlock().getExplosionResistance();
			Injury_UT = Injury_UT - (1 > Block_ER ? 0.5 : Block_ER / 2);
		}
		if (1 <= Injury_UT) {
			entity.hurt((100 <= Injury_UT ? new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:sudden_high_temperature")))) : new DamageSource(world.holderOrThrow(DamageTypes.GENERIC))),
					(float) Injury_UT);
			if (100 <= Injury_UT && ("minecraft:player").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString())
					&& (entity instanceof Player _plr5 && _plr5.gameMode() == GameType.SURVIVAL || entity instanceof Player _plr6 && _plr6.gameMode() == GameType.ADVENTURE)) {
				if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
					AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:dawn_the_true_dawn"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
			entity.igniteForSeconds((int) (Injury_UT / 5));
			entity.push((dx * Injury_UT), (dy * Injury_UT), (dz * Injury_UT));
		}
	}
}