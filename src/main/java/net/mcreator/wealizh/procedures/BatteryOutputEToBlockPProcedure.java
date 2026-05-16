package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

public class BatteryOutputEToBlockPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, double slot, String E) {
		if (E == null)
			return;
		double IP_E = 0;
		double IP_E_a = 0;
		IP_E_a = getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E)) < (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag()
				.getDoubleOr("Ep", 0)
						? getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E))
						: (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("Ep", 0);
		IP_E = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getDamageValue() < IP_E_a
				&& !((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:battery/empty")))
						|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit"))))
								? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getDamageValue()
								: IP_E_a;
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:battery"))) && 0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E))) {
			if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).isDamaged()
					|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:infinite_unit")))) {
				if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
						&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = (int) slot;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.hurtAndBreak((int) (0 - IP_E), _serverLevel, null, _stkprov -> {
					});
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("E" + E), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E)) - (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit"))) ? 0 : IP_E)));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:battery/empty")))) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.getValue(ResourceLocation
							.parse((((BuiltInRegistries.ITEM.getKey((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getItem()).toString()).replace("empty_", ""))).toLowerCase(java.util.Locale.ENGLISH)))).copy();
					_setstack.setCount(1);
					_itemHandlerModifiable.setStackInSlot((int) slot, _setstack);
				}
				if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
						&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = (int) slot;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.hurtAndBreak((int) ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getMaxDamage()
							- (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDoubleOr("Ep", 0)), _serverLevel, null, _stkprov -> {
							});
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("E" + E), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E)) - IP_E));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
		if (0 > getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(("E" + E), 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Eu" + E)) < getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("E" + E))) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(("E" + E), (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("Eu" + E))));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
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