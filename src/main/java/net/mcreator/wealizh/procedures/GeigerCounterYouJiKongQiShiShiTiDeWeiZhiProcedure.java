package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;

public class GeigerCounterYouJiKongQiShiShiTiDeWeiZhiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:pull_the_slide_catch")), SoundSource.PLAYERS, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:pull_the_slide_catch")), SoundSource.PLAYERS, 1, 1, false);
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A76\u2193   \u2622" + new ItemStack(WealizhModItems.GEIGER_COUNTER.get()).getDisplayName().getString() + "\u2622   \u2193")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7e\u73AF\u5883\u8F90\u5C04\uFF1A" : "\u00A7eEnvironmental-Radiation:") + "\n"
					+ TextColourDARReturnProcedure.execute(100, entity.getPersistentData().getDoubleOr("Rad_NR", 0)) + (entity.getPersistentData().getDoubleOr("Rad_NR", 0) < 0 ? 0 : entity.getPersistentData().getDoubleOr("Rad_NR", 0) * 20)
					+ " Rad/s")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7b\u7269\u54C1\u680F\u8F90\u5C04\uFF1A" : "\u00A7bInventory-Radiation:")
					+ "\n" + TextColourDARReturnProcedure.execute(100, entity.getPersistentData().getDoubleOr("Rad_IR", 0)) + (entity.getPersistentData().getDoubleOr("Rad_IR", 0) < 0 ? 0 : entity.getPersistentData().getDoubleOr("Rad_IR", 0) * 20)
					+ " Rad/s")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7a\u7EFC\u5408\u8F90\u5C04\uFF1A" : "\u00A7aComprehensive Radiation:") + "\n"
					+ TextColourDARReturnProcedure.execute(120, entity.getPersistentData().getDoubleOr("Rad_NR", 0) + entity.getPersistentData().getDoubleOr("Rad_IR", 0))
					+ (entity.getPersistentData().getDoubleOr("Rad_NR", 0) + entity.getPersistentData().getDoubleOr("Rad_IR", 0) < 0
							? 0
							: (entity.getPersistentData().getDoubleOr("Rad_NR", 0) + entity.getPersistentData().getDoubleOr("Rad_IR", 0)) * 20)
					+ " Rad/s")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(
					Component.literal((((new ItemStack(WealizhModItems.LANGUAGE_TESTER.get()).getDisplayName().getString()).equals("[\u6587]") ? "\u00A7c\u81EA\u8EAB\u8F90\u5C04\u7D2F\u8BA1\uFF1A" : "\u00A7cCumulative Self-Radiation:") + "\n"
							+ TextColourDARReturnProcedure.execute(15000, entity.getPersistentData().getDoubleOr("Rad", 0)) + (entity.getPersistentData().getDoubleOr("Rad", 0) < 0 ? 0 : entity.getPersistentData().getDoubleOr("Rad", 0)) + " Rad")),
					false);
	}
}