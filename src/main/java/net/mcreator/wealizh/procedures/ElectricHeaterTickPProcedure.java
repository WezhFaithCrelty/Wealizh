package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

public class ElectricHeaterTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("Eu", 1);
				_blockEntity.getPersistentData().putBoolean("E_d", true);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp3 && blockstate.getValue(_getbp3)) {
			for (Entity entityiterator : world.getEntities(null, new AABB(x, y, z, (x + 1), (y + 1.2), (z + 1)))) {
				if (entityiterator instanceof Player _plr4 && _plr4.gameMode() == GameType.SURVIVAL || entityiterator instanceof Player _plr5 && _plr5.gameMode() == GameType.ADVENTURE) {
					if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:underfloor_heating"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
				if (getPropertyByName(blockstate, "function_soul") instanceof BooleanProperty _getbp8 && blockstate.getValue(_getbp8)) {
					if (!(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()).equals("minecraft:item")) {
						entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_FIRE)), 2);
					}
					entityiterator.igniteForSeconds(Mth.nextInt(RandomSource.create(), 2, 20));
				} else if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp15 && blockstate.getValue(_getbp15)) {
					if (!(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()).equals("minecraft:item")) {
						entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_FIRE)), 1);
					}
					entityiterator.igniteForSeconds(Mth.nextInt(RandomSource.create(), 1, 15));
				}
			}
		}
		if (Blocks.POWDER_SNOW == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() && Mth.nextInt(RandomSource.create(), 1, (int) (42 - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow"))) == 1) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.WATER.defaultBlockState(), 3);
		}
		if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E")) {
			if (5 > getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("pow", (0.25 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("pow", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") - 0.1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (2 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("function") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
		} else if (1.5 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") && 2 > getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow")) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("function") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			}
		}
		if (0 == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("ERi", 100);
					_blockEntity.getPersistentData().putDouble("E", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("ERi", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}