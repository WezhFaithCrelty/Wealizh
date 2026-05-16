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

public class CreativePlanningPaperNBTGUIOKButtonClickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (("logic").equals((entity instanceof Player _entity0 && _entity0.containerMenu instanceof WealizhModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "Type", "") : "")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean(((entity instanceof Player _entity1 && _entity1.containerMenu instanceof WealizhModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "NBT", "") : ""),
							(("true").equals((entity instanceof Player _entity2 && _entity2.containerMenu instanceof WealizhModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(0, "Value", "") : "")
									|| ("1").equals((entity instanceof Player _entity3 && _entity3.containerMenu instanceof WealizhModMenus.MenuAccessor _menu3) ? _menu3.getMenuState(0, "Value", "") : "") ? true : false));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (("text").equals((entity instanceof Player _entity5 && _entity5.containerMenu instanceof WealizhModMenus.MenuAccessor _menu5) ? _menu5.getMenuState(0, "Type", "") : "")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putString(((entity instanceof Player _entity6 && _entity6.containerMenu instanceof WealizhModMenus.MenuAccessor _menu6) ? _menu6.getMenuState(0, "NBT", "") : ""),
							((entity instanceof Player _entity7 && _entity7.containerMenu instanceof WealizhModMenus.MenuAccessor _menu7) ? _menu7.getMenuState(0, "Value", "") : ""));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(((entity instanceof Player _entity9 && _entity9.containerMenu instanceof WealizhModMenus.MenuAccessor _menu9) ? _menu9.getMenuState(0, "NBT", "") : ""),
							parseDouble((entity instanceof Player _entity10 && _entity10.containerMenu instanceof WealizhModMenus.MenuAccessor _menu10) ? _menu10.getMenuState(0, "Value", "") : ""));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.WHITE_SMOKE, (x + 0.5), (y + 0.5), (z + 0.5), (int) Mth.nextDouble(RandomSource.create(), 5, 10), 0.5, 0.5, 0.5, 0.01);
	}

	private static double parseDouble(String s) {
		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			return 0;
		}
	}
}