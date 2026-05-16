package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModParticleTypes;
import net.mcreator.wealizh.init.WealizhModBlocks;
import net.mcreator.wealizh.WealizhMod;

public class UraniumFilledBombExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.4), (y + 0.4), (z + 0.4), (x + 0.6), (y + 0.6), (z + 0.6)))) {
			if (WealizhModBlocks.URANIUM_FILLED_BOMB.get().asItem() == (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()) {
				if (!entityiterator.level().isClientSide())
					entityiterator.discard();
			}
		}
		WealizhMod.queueServerWork(1, () -> {
			PollutionSourceSpawnPProcedure.execute(world, x, y, z, 0, 0.64, 0);
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, (x + 0.5), (y + 0.5), (z + 0.5), 10, Level.ExplosionInteraction.TNT);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.HUGE_EXPLOSION_SMOKE.get()), true, false, x + 0.5, y + 0.5, z + 0.5, Mth.nextInt(RandomSource.create(), 50, 100), 6, 6, 6, 0);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.BIG_EXPLOSION_SMOKE.get()), true, false, x + 0.5, y + 0.5, z + 0.5, Mth.nextInt(RandomSource.create(), 120, 240), 10, 10, 10, 0);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.EXPLOSION_SMOKE.get()), true, false, x + 0.5, y + 0.5, z + 0.5, Mth.nextInt(RandomSource.create(), 240, 320), 0, 0, 0, 0.5);
		});
	}
}