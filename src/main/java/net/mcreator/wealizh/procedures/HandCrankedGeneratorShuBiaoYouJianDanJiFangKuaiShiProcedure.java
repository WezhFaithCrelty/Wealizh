package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.init.WealizhModBlocks;
import net.mcreator.wealizh.WealizhMod;

import javax.annotation.Nullable;

@EventBusSubscriber
public class HandCrankedGeneratorShuBiaoYouJianDanJiFangKuaiShiProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getLevel().getBlockState(event.getPos()), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() && !(getPropertyByName(blockstate, "rotation") instanceof BooleanProperty _getbp3 && blockstate.getValue(_getbp3))
				&& entity.isShiftKeyDown() && Blocks.AIR.asItem() == (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()) {
			{
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("rotation") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
			}
			if ((getDirectionFromBlockState(blockstate)) == Direction.DOWN) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("iy", 1);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.UP) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("iy", (-1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.NORTH) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("iz", 1);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.SOUTH) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("iz", (-1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.WEST) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("ix", (-1));
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
						_blockEntity.getPersistentData().putDouble("ix", 1);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
				AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:mechanical_power"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 120, 2, false, false));
			if (0 == (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0)) {
				if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
					AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:turn_the_crank_until_you_get_tired_then_just_keep_on_enduring_it"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
			WealizhMod.queueServerWork(50, () -> {
				if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "rotation") instanceof BooleanProperty _getbp37 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp37)) {
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("rotation") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("E_Acc", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E_Acc")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (10 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E_Acc")) {
						if (getBlockNBTLogic(world,
								BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ix"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "iy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "iz")),
								"E_d")) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ix"), y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "iy"),
										z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "iz"));
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("E", (1 + getBlockNBTNumber(world, BlockPos.containing(x + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ix"),
											y + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "iy"), z + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "iz")), "E")));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("E_Acc", 0);
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				}
			});
			if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() && getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "rotation") instanceof BooleanProperty _getbp59
					&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp59)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
			WealizhMod.queueServerWork(10, () -> {
				if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "rotation") instanceof BooleanProperty _getbp64 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp64)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
				if (0 == (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:turn_the_crank_until_you_get_tired_then_just_keep_on_enduring_it"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			});
			WealizhMod.queueServerWork(20, () -> {
				if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "rotation") instanceof BooleanProperty _getbp72 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp72)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
				if (0 == (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:turn_the_crank_until_you_get_tired_then_just_keep_on_enduring_it"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			});
			WealizhMod.queueServerWork(30, () -> {
				if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "rotation") instanceof BooleanProperty _getbp80 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp80)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
				if (0 == (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:turn_the_crank_until_you_get_tired_then_just_keep_on_enduring_it"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			});
			WealizhMod.queueServerWork(40, () -> {
				if (WealizhModBlocks.HAND_CRANKED_GENERATOR.get() == (world.getBlockState(BlockPos.containing(x, y, z))).getBlock()
						&& getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "rotation") instanceof BooleanProperty _getbp88 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp88)) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:mechanical_click")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
				if (0 == (entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:turn_the_crank_until_you_get_tired_then_just_keep_on_enduring_it"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			});
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

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}