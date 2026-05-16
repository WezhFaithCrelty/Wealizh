package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BlockItemsUpwardEntrySPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double slot) {
		for (Entity entityiterator : world.getEntities(null, new AABB(x, (y + 0.5), z, (x + 1), (y + 1.5), (z + 1)))) {
			if (("minecraft:item").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()) && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount()
					+ (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount() <= (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getMaxStackSize()
					&& ((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).copy()).getItem() == ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).copy()).getItem()
					|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem() == Blocks.AIR.asItem())) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = ((entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).copy()).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount() + (entityiterator instanceof ItemEntity _itemEnt ? _itemEnt.getItem() : ItemStack.EMPTY).getCount());
					_itemHandlerModifiable.setStackInSlot((int) slot, _setstack);
				}
				if (!entityiterator.level().isClientSide())
					entityiterator.discard();
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