package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class GeigerCounterWuPinZaiWuPinLanShiMeiKeFaShengProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (0 >= entity.getPersistentData().getDoubleOr("GGJ_S", 0)) {
			if (100 <= entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick6")), SoundSource.PLAYERS, 3, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick6")), SoundSource.PLAYERS, 3, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 2);
			} else if (50 <= entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick6")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick6")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 4);
			} else if (25 <= entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick5")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick5")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 3);
			} else if (10 <= entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick4")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick4")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 3);
			} else if (5 <= entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick3")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick3")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 4);
			} else if (1 <= entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick2")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick2")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 10);
			} else if (0 < entity.getPersistentData().getDoubleOr("Rad_NR", 0)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:geiger_counter_tick")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.getPersistentData().putDouble("GGJ_S", 20);
			}
		} else {
			entity.getPersistentData().putDouble("GGJ_S", (entity.getPersistentData().getDoubleOr("GGJ_S", 0) - 1));
		}
	}
}