package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;

public class FluidInputLToBlockPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double slot, String L) {
		if (L == null)
			return;
		double Su = 0;
		Su = 1000;
		if ((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).contains("fluid_tank_")
				|| (BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).contains("gas_tank_"))
				&& !(BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).contains("_empty")
				&& (((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).contains("fluid_tank_")
						? WealizhModItems.FLUID_TANK_EMPTY.get()
						: WealizhModItems.GAS_TANK_EMPTY.get()) == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 2)).copy()).getItem()
						|| Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 2)).copy()).getItem())
				|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
						&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("consumption_pattern", 0) == 0)
				&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 2)).getCount() < 64
				&& ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
						? true
						: getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L" + L)) <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Lu" + L)) - Su)
				&& (((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
						? !(BuiltInRegistries.ITEM
								.getValue(ResourceLocation.parse((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).replace("fluid_tank_", "")))
										.toLowerCase(java.util.Locale.ENGLISH))) == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem()
								|| BuiltInRegistries.ITEM
										.getValue(ResourceLocation.parse((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).replace("gas_tank_", "")))
												.toLowerCase(java.util.Locale.ENGLISH))) == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem())
						: BuiltInRegistries.ITEM
								.getValue(ResourceLocation.parse((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).replace("fluid_tank_", "")))
										.toLowerCase(java.util.Locale.ENGLISH))) == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem()
								|| BuiltInRegistries.ITEM
										.getValue(ResourceLocation.parse((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).replace("gas_tank_", "")))
												.toLowerCase(java.util.Locale.ENGLISH))) == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem()
								|| Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem())
						|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
								&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("consumption_pattern", 0) == 0)) {
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit")))) {
				if (!(Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem())) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble(("L" + L), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Lu" + L))));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("L" + L), (Su + getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L" + L))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if ((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).contains("fluid_tank_")) {
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.getValue(ResourceLocation.parse(
								(((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).replace("fluid_tank_", ""))).toLowerCase(java.util.Locale.ENGLISH))))
								.copy();
						_setstack.setCount(1);
						_itemHandlerModifiable.setStackInSlot((int) slot, _setstack);
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
								? itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()
								: new ItemStack(WealizhModItems.FLUID_TANK_EMPTY.get())).copy();
						_setstack.setCount(1 + itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 2)).getCount());
						_itemHandlerModifiable.setStackInSlot((int) (slot + 2), _setstack);
					}
				} else {
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.getValue(ResourceLocation
								.parse((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()).getItem()).toString()).replace("gas_tank_", ""))).toLowerCase(java.util.Locale.ENGLISH))))
								.copy();
						_setstack.setCount(1);
						_itemHandlerModifiable.setStackInSlot((int) slot, _setstack);
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
								? itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 1)).copy()
								: new ItemStack(WealizhModItems.GAS_TANK_EMPTY.get())).copy();
						_setstack.setCount(1 + itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) (slot + 2)).getCount());
						_itemHandlerModifiable.setStackInSlot((int) (slot + 2), _setstack);
					}
				}
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = (int) (slot + 1);
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.shrink(1);
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
			}
		}
		if (0 >= getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L" + L))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(("L" + L), 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
				_itemHandlerModifiable.setStackInSlot((int) slot, ItemStack.EMPTY);
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Lu" + L)) < getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("L" + L))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(("L" + L), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Lu" + L))));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}