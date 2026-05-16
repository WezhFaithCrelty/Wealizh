package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.init.WealizhModParticleTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import java.util.Comparator;

public class CombustionChamberGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("sound_i", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") - 1));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp3 && blockstate.getValue(_getbp3) || getPropertyByName(blockstate, "function_soul") instanceof BooleanProperty _getbp5 && blockstate.getValue(_getbp5)) {
			for (Entity entityiterator : world.getEntities(null, new AABB(x, y, z, (x + 1), (y + 1.2), (z + 1)))) {
				if (entityiterator instanceof Player _plr6 && _plr6.gameMode() == GameType.SURVIVAL || entityiterator instanceof Player _plr7 && _plr7.gameMode() == GameType.ADVENTURE) {
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
				if (getPropertyByName(blockstate, "function_soul") instanceof BooleanProperty _getbp10 && blockstate.getValue(_getbp10)) {
					if (!(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()).equals("minecraft:item")) {
						entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_FIRE)), 2);
					}
					entityiterator.igniteForSeconds(Mth.nextInt(RandomSource.create(), 2, 20));
				} else if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp17 && blockstate.getValue(_getbp17)) {
					if (!(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString()).equals("minecraft:item")) {
						entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_FIRE)), 1);
					}
					entityiterator.igniteForSeconds(Mth.nextInt(RandomSource.create(), 1, 15));
				}
			}
		}
		if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp25 && blockstate.getValue(_getbp25)) {
			if (getPropertyByName(blockstate, "function_soul") instanceof BooleanProperty _getbp27 && blockstate.getValue(_getbp27)) {
				if (Mth.nextInt(RandomSource.create(), 1, 50) == 1) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, (x + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (y + 1.1), (z + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), 1, 0, 0, 0, 0);
				}
			} else if (Mth.nextInt(RandomSource.create(), 1, 50) == 1) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.FLAME, (x + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (y + 1.1), (z + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), 1, 0, 0, 0, 0);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") > 0) {
			if (CombustionChamberWaterUnderBoolReturnProcedure.execute(world, x, y, z)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("pow", (4 >= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") ? 0 : getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") - 4));
						_blockEntity.getPersistentData().putDouble("bur", 0);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.fire.extinguish")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.fire.extinguish")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				for (int index0 = 0; index0 < Mth.nextInt(RandomSource.create(), 10, 15); index0++) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.SMOKE.get()), (x + Mth.nextDouble(RandomSource.create(), 0, 1)), (y + Mth.nextDouble(RandomSource.create(), 1, 1.5)),
								(z + Mth.nextDouble(RandomSource.create(), 0, 1)), 1, 0, 0, 0, 0.02);
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(9 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
						if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
							AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:quickly_shut_off_the_engine"));
							if (_adv != null) {
								AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
								if (!_ap.isDone()) {
									for (String criteria : _ap.getRemainingCriteria())
										_player.getAdvancements().award(_adv, criteria);
								}
							}
						}
						if (WealizhModItems.FUEL_TANK_SOUL_FIRE_CATALYST.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()) {
							if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
								AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:quickly_shut_off_the_engine_but_it_is_the_soul_version"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						}
					}
				}
			}
			if (WealizhModItems.FUEL_TANK_SOUL_FIRE_CATALYST.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") < 12) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("pow", (0.005 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getMaxDamage() - (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getDamageValue() <= 1) {
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						ItemStack _setstack = new ItemStack(WealizhModItems.FUEL_TANK_EMPTY.get()).copy();
						_setstack.setCount(1);
						_itemHandlerModifiable.setStackInSlot(1, _setstack);
					}
				} else {
					if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
							&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						int _slotid = 1;
						ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
						_stk.hurtAndBreak(1, _serverLevel, null, _stkprov -> {
						});
						_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
					}
				}
			} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") < 6) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("pow", (0.002 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow")));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") <= 1) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:combustion_chamber_type_mechanical_combustion")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:combustion_chamber_type_mechanical_combustion")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("sound_i", 140);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			CombustionChamberPiLinFangKuaiGengXinShiProcedure.execute(world, x, y, z);
		} else if ((world instanceof Level _levelFV70 ? (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getBurnTime(null, _levelFV70.fuelValues()) : 0) == 0
				&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") > 0) {
			if (getPropertyByName(blockstate, "function") instanceof BooleanProperty _getbp73 && blockstate.getValue(_getbp73) || getPropertyByName(blockstate, "function_soul") instanceof BooleanProperty _getbp75 && blockstate.getValue(_getbp75)) {
				CombustionChamberPiLinFangKuaiGengXinShiProcedure.execute(world, x, y, z);
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("pow", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") - 0.024));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (CombustionChamberIsRainyBoolReturnProcedure.execute(world, x, y, z)) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("pow", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") - (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") > 0 ? 0.001 : 0.02)));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (Mth.nextInt(RandomSource.create(), 1, 30) == 1) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.SMOKE, (x + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (y + 1.1), (z + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), 1, 0, 0, 0, 0);
			}
		}
		if (Blocks.POWDER_SNOW == (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() && Mth.nextInt(RandomSource.create(), 1, (int) (40 - getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow"))) == 1) {
			world.setBlock(BlockPos.containing(x, y, z), Blocks.WATER.defaultBlockState(), 3);
		}
		if (!CombustionChamberWaterUnderBoolReturnProcedure.execute(world, x, y, z)) {
			BlockGUIPBurningPPProcedure.execute(world, x, y, z, 10, 0);
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "pow") > 12) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("pow", 12);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "bur") <= 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("sound_i", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((x + 0.5), (y + 0.5), (z + 0.5)), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"stopsound @a[distance=..16] block wealizh:combustion_chamber_type_mechanical_combustion");
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "pow");
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "bur");
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
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