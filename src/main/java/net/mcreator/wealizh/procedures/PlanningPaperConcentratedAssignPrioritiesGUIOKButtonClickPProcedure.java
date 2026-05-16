package net.mcreator.wealizh.procedures;

import org.checkerframework.checker.units.qual.s;

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

public class PlanningPaperConcentratedAssignPrioritiesGUIOKButtonClickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("p_x", parseDouble((entity instanceof Player _entity0 && _entity0.containerMenu instanceof WealizhModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "px", "") : ""));
				_blockEntity.getPersistentData().putDouble("p_nx", parseDouble((entity instanceof Player _entity2 && _entity2.containerMenu instanceof WealizhModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(0, "nx", "") : ""));
				_blockEntity.getPersistentData().putDouble("p_y", parseDouble((entity instanceof Player _entity4 && _entity4.containerMenu instanceof WealizhModMenus.MenuAccessor _menu4) ? _menu4.getMenuState(0, "py", "") : ""));
				_blockEntity.getPersistentData().putDouble("p_ny", parseDouble((entity instanceof Player _entity6 && _entity6.containerMenu instanceof WealizhModMenus.MenuAccessor _menu6) ? _menu6.getMenuState(0, "ny", "") : ""));
				_blockEntity.getPersistentData().putDouble("p_z", parseDouble((entity instanceof Player _entity8 && _entity8.containerMenu instanceof WealizhModMenus.MenuAccessor _menu8) ? _menu8.getMenuState(0, "pz", "") : ""));
				_blockEntity.getPersistentData().putDouble("p_nz", parseDouble((entity instanceof Player _entity10 && _entity10.containerMenu instanceof WealizhModMenus.MenuAccessor _menu10) ? _menu10.getMenuState(0, "nz", "") : ""));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) Mth.nextDouble(RandomSource.create(), 5, 10), 0.5, 0.5, 0.5, 0.01);
	}

	private static double parseDouble(String s) {
		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			return 0;
		}
	}
}