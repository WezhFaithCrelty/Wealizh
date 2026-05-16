package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;

public class ExperienceAbsorberTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level0 && _level0.hasNeighborSignal(BlockPos.containing(x, y, z)) && itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() < 64
				&& (Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()
						|| WealizhModItems.EXPERIENCE_PARTICLE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem())) {
			for (Entity entityiterator : world.getEntities(null, new AABB((x - 5), (y - 5), (z - 5), (x + 5), (y + 5), (z + 5)))) {
				if (("minecraft:experience_orb").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()) && 0 < ((ExperienceOrb) entityiterator).getValue()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.experience_orb.pickup")), SoundSource.BLOCKS, (float) 0.5, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.experience_orb.pickup")), SoundSource.BLOCKS, (float) 0.5, 1, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.WHITE_SMOKE, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), Mth.nextInt(RandomSource.create(), 1, 5), 0.2, 0.2, 0.2, 0.01);
					if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() + ((ExperienceOrb) entityiterator).getValue() > 64) {
						if (world instanceof ServerLevel _level)
							_level.addFreshEntity(new ExperienceOrb(_level, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()),
									(int) (((ExperienceOrb) entityiterator).getValue() - (64 - itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount()))));
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = new ItemStack(WealizhModItems.EXPERIENCE_PARTICLE.get()).copy();
						_setstack.setCount((int) ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() + ((ExperienceOrb) entityiterator).getValue() <= 64
								? ((ExperienceOrb) entityiterator).getValue()
								: 64 - itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount()) + itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount()));
						_itemHandlerModifiable.setStackInSlot(0, _setstack);
					}
					if (!entityiterator.level().isClientSide())
						entityiterator.discard();
				}
			}
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}
}