package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModItems;

public class CombustionChamberPiLinFangKuaiGengXinShiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") > 0) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("function") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
			if (WealizhModItems.FUEL_TANK_SOUL_FIRE_CATALYST.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("function_soul") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
				}
			} else {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("function_soul") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
		} else if ((world instanceof Level _levelFV7 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getBurnTime(null, _levelFV7.fuelValues()) : 0) == 0
				|| Blocks.WATER == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() || Blocks.BUBBLE_COLUMN == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("function") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
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