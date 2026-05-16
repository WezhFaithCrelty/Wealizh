package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.wealizh.init.WealizhModParticleTypes;

public class MushroomCloudTPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double r_Explosion_wave = 0;
		double r_max_Explosion_wave = 0;
		double C_r_Explosion_wave = 0;
		double angle = 0;
		double pow_EW = 0;
		double nx = 0;
		double ny = 0;
		double nz = 0;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double nyit = 0;
		if (r_Explosion_wave <= r_max_Explosion_wave) {
			r_max_Explosion_wave = 5 * entity.getPersistentData().getDoubleOr("pow", 0);
			r_Explosion_wave = entity.getPersistentData().getDoubleOr("r_EW", 0);
			C_r_Explosion_wave = Math.PI * r_Explosion_wave;
			if (1 > C_r_Explosion_wave) {
				C_r_Explosion_wave = 1;
			}
			pow_EW = r_max_Explosion_wave - r_Explosion_wave;
			for (int index0 = 0; index0 < (int) C_r_Explosion_wave; index0++) {
				angle = (2 * Math.PI * index0) / C_r_Explosion_wave;
				dx = x + Math.cos(angle) * r_Explosion_wave;
				dz = z + Math.sin(angle) * r_Explosion_wave;
				ny = -100;
				for (int index1 = 0; index1 < (int) pow_EW; index1++) {
					if (320 < y + index1 && -63 > y - index1) {
						break;
					}
					nyit = y + index1;
					if (!(world.getBlockState(BlockPos.containing(dx, nyit, dz)).canOcclude() || world.getBlockState(BlockPos.containing(dx, nyit, dz)).isCollisionShapeFullBlock(world, BlockPos.containing(dx, nyit, dz)))
							&& (world.getBlockState(BlockPos.containing(dx, nyit - 1, dz)).canOcclude() || world.getBlockState(BlockPos.containing(dx, nyit - 1, dz)).isCollisionShapeFullBlock(world, BlockPos.containing(dx, nyit - 1, dz)))) {
						ny = nyit;
						break;
					}
					nyit = y - index1;
					if (!(world.getBlockState(BlockPos.containing(dx, nyit, dz)).canOcclude() || world.getBlockState(BlockPos.containing(dx, nyit, dz)).isCollisionShapeFullBlock(world, BlockPos.containing(dx, nyit, dz)))
							&& (world.getBlockState(BlockPos.containing(dx, nyit - 1, dz)).canOcclude() || world.getBlockState(BlockPos.containing(dx, nyit - 1, dz)).isCollisionShapeFullBlock(world, BlockPos.containing(dx, nyit - 1, dz)))) {
						ny = nyit;
						break;
					}
				}
				if (320 >= ny && -64 <= ny) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.GIANT_CLOUD_OF_SMOKE.get()), true, false, dx, ny, dz, 0, 0, 0, 0, 0);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("playsound wealizh:large_scale_explosion voice @a[distance=" + r_Explosion_wave + ".." + (r_Explosion_wave + 1) + "] ~ ~ ~ 100000000"));
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("camerashake @a[distance=" + r_Explosion_wave + ".." + (r_Explosion_wave + 1) + "] 10 10"));
		}
		if (r_Explosion_wave >= r_max_Explosion_wave) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
		entity.getPersistentData().putDouble("r_EW", (1 + r_Explosion_wave));
	}
}