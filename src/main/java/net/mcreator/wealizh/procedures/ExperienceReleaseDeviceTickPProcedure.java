package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

public class ExperienceReleaseDeviceTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double XP_num = 0;
		if (world instanceof Level _level0 && _level0.hasNeighborSignal(BlockPos.containing(x, y, z))
				&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:solid_experience")))) {
			if (WealizhModBlocks.EXPERIENCE_BLOCK.get().asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()) {
				XP_num = Math.pow(9, 5);
			} else if (WealizhModItems.SOLID_EXPERIENCE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()) {
				XP_num = Math.pow(9, 4);
			} else if (WealizhModItems.EXPERIENCE_SOLID_CUBE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()) {
				XP_num = Math.pow(9, 3);
			} else if (WealizhModItems.EXPERIENCE_CUBE_NUGGET.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()) {
				XP_num = Math.pow(9, 2);
			} else if (WealizhModItems.EXPERIENCE_NUGGET.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()) {
				XP_num = Math.pow(9, 1);
			} else {
				XP_num = Math.pow(9, 0);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.amethyst_block.place")), SoundSource.BLOCKS, (float) 0.5, 1);
				} else {
					_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.amethyst_block.place")), SoundSource.BLOCKS, (float) 0.5, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.WHITE_SMOKE, (x + 0.5), (y + 0.5), (z + 0.5), 5, 0.2, 0.2, 0.2, 0);
			if (world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, (x + 0.5), (y + 0.5), (z + 0.5), (int) (XP_num * itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount())));
			for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.45), (x + 0.45), (x + 0.45), (x + 0.55), (y + 0.55), (z + 0.55)))) {
				if (("minecraft:experience_orb").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString())) {
					entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
				}
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
				_itemHandlerModifiable.setStackInSlot(0, ItemStack.EMPTY);
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