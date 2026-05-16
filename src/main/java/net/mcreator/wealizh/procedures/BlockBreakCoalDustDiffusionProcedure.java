package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.wealizh.init.WealizhModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BlockBreakCoalDustDiffusionProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double Block_CoD_Num = 0;
		Block_CoD_Num = ItemCoalDustAttributeProcedure.execute(new ItemStack(blockstate.getBlock()));
		if (blockstate.getBlock() == WealizhModBlocks.LIGNITE_ORE.get()) {
			Block_CoD_Num = 0.04;
		}
		if (blockstate.getBlock() == WealizhModBlocks.DEEPSLATE_LIGNITE_ORE.get()) {
			Block_CoD_Num = 0.04;
		}
		if (blockstate.getBlock() == Blocks.COAL_ORE) {
			Block_CoD_Num = 0.02;
		}
		if (blockstate.getBlock() == Blocks.DEEPSLATE_COAL_ORE) {
			Block_CoD_Num = 0.02;
		}
		if (0 < Block_CoD_Num && !(entity instanceof Player _plr10 && _plr10.gameMode() == GameType.CREATIVE)) {
			BlockBreakCoalDustDiffusionQusProcedure.execute(world, x, y, z, Block_CoD_Num);
		}
	}
}