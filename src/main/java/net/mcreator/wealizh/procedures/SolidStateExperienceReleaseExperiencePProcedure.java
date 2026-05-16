package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;

public class SolidStateExperienceReleaseExperiencePProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double xi = 0;
		double yi = 0;
		double zi = 0;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.amethyst_cluster.break")), SoundSource.BLOCKS, (float) 0.8, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.amethyst_cluster.break")), SoundSource.BLOCKS, (float) 0.8, 1, false);
			}
		}
		xi = x + 0.5;
		yi = y + 1.2;
		zi = z + 0.5;
		if (WealizhModItems.EXPERIENCE_PARTICLE.get() == itemstack.getItem()) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, xi, yi, zi, (int) Math.pow(9, 0)));
		}
		if (WealizhModItems.EXPERIENCE_NUGGET.get() == itemstack.getItem()) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, xi, yi, zi, (int) Math.pow(9, 1)));
		}
		if (WealizhModItems.EXPERIENCE_CUBE_NUGGET.get() == itemstack.getItem()) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, xi, yi, zi, (int) Math.pow(9, 2)));
		}
		if (WealizhModItems.EXPERIENCE_SOLID_CUBE.get() == itemstack.getItem()) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, xi, yi, zi, (int) Math.pow(9, 3)));
		}
		if (WealizhModItems.SOLID_EXPERIENCE.get() == itemstack.getItem()) {
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, xi, yi, zi, (int) Math.pow(9, 4)));
		}
		if (!(entity instanceof Player _plr16 && _plr16.gameMode() == GameType.CREATIVE)) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = itemstack;
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
		}
	}
}