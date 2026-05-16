package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BlockItemsBelowIsTheOutputSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double slot) {
		if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == Blocks.AIR.asItem())) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y - 0.5), (z + 0.5), ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).copy()));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
			for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.45), (y - 0.55), (x + 0.45), (x + 0.55), (y - 0.45), (z + 0.55)))) {
				if (("minecraft:item").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString())
						&& ((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).copy()).getItem() == ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).copy()).getItem()) {
					entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
				}
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
				_itemHandlerModifiable.setStackInSlot((int) slot, ItemStack.EMPTY);
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