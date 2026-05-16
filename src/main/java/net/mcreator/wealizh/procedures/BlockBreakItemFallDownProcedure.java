package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BlockBreakItemFallDownProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		double i = 0;
		double n = 0;
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "np") > 0) {
			n = 1;
			for (int index0 = 0; index0 < getBlockInventorySlotCount(world, BlockPos.containing(x, y, z)); index0++) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("np" + (n == 1 ? "" : ("" + n).replace(".0", "")))) == i + 1) {
					n = 1 + n;
				} else if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem() == Blocks.AIR.asItem())) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).copy()));
						entityToSpawn.setPickUpDelay(0);
						_level.addFreshEntity(entityToSpawn);
					}
				}
				i = 1 + i;
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static int getBlockInventorySlotCount(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getSlots();
		}
		return 0;
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