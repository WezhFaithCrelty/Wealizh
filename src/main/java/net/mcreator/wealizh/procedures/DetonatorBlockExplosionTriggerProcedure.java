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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.init.WealizhModParticleTypes;
import net.mcreator.wealizh.init.WealizhModBlocks;
import net.mcreator.wealizh.WealizhMod;

public class DetonatorBlockExplosionTriggerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
		for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.4), (y + 0.4), (z + 0.4), (x + 0.6), (y + 0.6), (z + 0.6)))) {
			if (WealizhModBlocks.DETONATOR_BLOCK.get().asItem() == (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getItem()) {
				if (!entityiterator.level().isClientSide())
					entityiterator.discard();
			}
		}
		WealizhMod.queueServerWork(1, () -> {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.EXPLOSION_SMOKE.get()), true, false, x + 0.5, y + 0.5, z + 0.5, Mth.nextInt(RandomSource.create(), 24, 36), 0, 0, 0, 0.04);
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, (x + 0.5), (y + 0.5), (z + 0.5), 1, Level.ExplosionInteraction.TNT);
			for (Entity entityiterator : world.getEntities(null, new AABB((x - 10), (y - 10), (z - 10), (x + 10), (y + 10), (z + 10)))) {
				if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
					AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:place_activate_detonate"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		});
	}
}