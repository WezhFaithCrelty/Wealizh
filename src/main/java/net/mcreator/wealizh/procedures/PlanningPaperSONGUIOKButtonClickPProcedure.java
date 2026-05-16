package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModMenus;

public class PlanningPaperSONGUIOKButtonClickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putString("sbn", ((entity instanceof Player _entity0 && _entity0.containerMenu instanceof WealizhModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "SN", "") : ""));
				_blockEntity.getPersistentData().putString("obn", ((entity instanceof Player _entity2 && _entity2.containerMenu instanceof WealizhModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(0, "ON", "") : ""));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) Mth.nextDouble(RandomSource.create(), 5, 10), 0.5, 0.5, 0.5, 0.01);
	}
}