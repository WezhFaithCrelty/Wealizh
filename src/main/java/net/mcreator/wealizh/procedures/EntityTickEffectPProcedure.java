package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EntityTickEffectPProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double xs = 0;
		double ys = 0;
		double zs = 0;
		boolean have_radiation_resistance_item = false;
		if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("wealizh:netrad")))) {
			if (world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("wealizh:desolate_land")) && world.getLevelData().isRaining()) {
				if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
					AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:the_cold_winter"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
			if (("minecraft:player").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString())
					? entity instanceof Player _plr5 && _plr5.gameMode() == GameType.SURVIVAL || entity instanceof Player _plr6 && _plr6.gameMode() == GameType.ADVENTURE
					: true) {
				xs = entity.getDeltaMovement().x();
				ys = entity.getDeltaMovement().y();
				zs = entity.getDeltaMovement().z();
				if (6000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:radioactivity_h"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 0, false, false));
					if (7000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 1, false, false));
					}
					if (8000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 250, 0, false, false));
					}
					if (9000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 1, false, false));
					}
					if (10000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 2, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 2, false, false));
					}
					if (11000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 250, 0, false, false));
					}
					if (12000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 3, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 250, 0, false, false));
					}
					if (13000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 3, false, false));
						if (Mth.nextInt(RandomSource.create(), 1, 75) == 1) {
							world.levelEvent(2001, BlockPos.containing(x, y + Mth.nextInt(RandomSource.create(), 0, (int) entity.getBbHeight()), z), Block.getId(Blocks.REDSTONE_BLOCK.defaultBlockState()));
						}
					}
					if (15000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
						entity.getPersistentData().putDouble("Rad_HB", (1 + entity.getPersistentData().getDoubleOr("Rad_HB", 0)));
					} else {
						entity.getPersistentData().putDouble("Rad_HB", 0);
					}
					if (200 <= entity.getPersistentData().getDoubleOr("Rad_HB", 0)) {
						if (20000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
							entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:radiation_injury")))), 25);
							entity.setDeltaMovement(new Vec3(xs, ys, zs));
							if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
								AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:radioactivity_can_be_fatal"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						} else if (16000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
							entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:radiation_injury")))), 5);
							entity.setDeltaMovement(new Vec3(xs, ys, zs));
							if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
								AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:radioactivity_can_be_fatal"));
								if (_adv != null) {
									AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
									if (!_ap.isDone()) {
										for (String criteria : _ap.getRemainingCriteria())
											_player.getAdvancements().award(_adv, criteria);
									}
								}
							}
						} else if (15000 <= entity.getPersistentData().getDoubleOr("Rad", 0)) {
							entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:radiation_injury")))), 1);
							entity.setDeltaMovement(new Vec3(xs, ys, zs));
							if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
								AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:radioactivity_can_be_fatal"));
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
				if (2000 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:toxic_reaction"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 0, false, false));
					if (2250 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 1, false, false));
					}
					if (2500 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 250, 0, false, false));
					}
					if (2750 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 1, false, false));
					}
					if (3000 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 2, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 2, false, false));
					}
					if (3250 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 250, 0, false, false));
					}
					if (3500 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 3, false, false));
					}
					if (3750 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 3, false, false));
					}
					if (4500 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:poison")))), 25);
						entity.setDeltaMovement(new Vec3(xs, ys, zs));
					} else if (4250 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:poison")))), 5);
						entity.setDeltaMovement(new Vec3(xs, ys, zs));
					} else if (4000 <= entity.getPersistentData().getDoubleOr("Tox", 0)) {
						entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:poison")))), 1);
						entity.setDeltaMovement(new Vec3(xs, ys, zs));
					}
				}
				if (2000 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
						AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:chronic_lung_disease"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 0, false, false));
					if (2250 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 1, false, false));
					}
					if (2500 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 250, 0, false, false));
					}
					if (2750 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 1, false, false));
					}
					if (3000 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 2, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 2, false, false));
					}
					if (3250 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 250, 0, false, false));
					}
					if (3500 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 250, 3, false, false));
					}
					if (3500 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 250, 0, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 250, 3, false, false));
					}
					if (3800 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:coal_dust")))), 25);
						entity.setDeltaMovement(new Vec3(xs, ys, zs));
					} else if (3700 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:coal_dust")))), 5);
						entity.setDeltaMovement(new Vec3(xs, ys, zs));
					} else if (3600 <= entity.getPersistentData().getDoubleOr("CoD", 0)) {
						entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("wealizh:coal_dust")))), 1);
						entity.setDeltaMovement(new Vec3(xs, ys, zs));
					}
				}
			}
			if (0 < entity.getPersistentData().getDoubleOr("Tox", 0)) {
				entity.getPersistentData().putDouble("Tox", (entity.getPersistentData().getDoubleOr("Tox", 0) - 0.02));
			} else {
				entity.getPersistentData().putDouble("Tox", 0);
			}
			if (0 < entity.getPersistentData().getDoubleOr("CoD", 0)) {
				entity.getPersistentData().putDouble("CoD", (entity.getPersistentData().getDoubleOr("CoD", 0) - 0.001));
			} else {
				entity.getPersistentData().putDouble("CoD", 0);
			}
			if (0 < entity.getPersistentData().getDoubleOr("Rad", 0)) {
				entity.getPersistentData().putDouble("Rad", (entity.getPersistentData().getDoubleOr("Rad", 0) - 0.001));
			} else {
				entity.getPersistentData().putDouble("Rad", 0);
			}
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
				for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
					ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
					if (itemstackiterator.is(ItemTags.create(ResourceLocation.parse("wealizh:can_radiation_resistance")))) {
						have_radiation_resistance_item = true;
					}
				}
			}
			if (!have_radiation_resistance_item) {
				entity.getPersistentData().putDouble("Rad", (entity.getPersistentData().getDoubleOr("Rad", 0) + entity.getPersistentData().getDoubleOr("Rad_N", 0)));
				entity.getPersistentData().putDouble("Rad", (entity.getPersistentData().getDoubleOr("Rad", 0) + entity.getPersistentData().getDoubleOr("Rad_I", 0)));
			}
			entity.getPersistentData().putDouble("Rad_NR", (entity.getPersistentData().getDoubleOr("Rad_N", 0)));
			entity.getPersistentData().putDouble("Rad_IR", (entity.getPersistentData().getDoubleOr("Rad_I", 0)));
			entity.getPersistentData().putDouble("Rad_N", 0);
			entity.getPersistentData().putDouble("Rad_I", 0);
			entity.getPersistentData().putDouble("CoD", (entity.getPersistentData().getDoubleOr("CoD", 0) + entity.getPersistentData().getDoubleOr("CoD_N", 0)));
			entity.getPersistentData().putDouble("CoD", (entity.getPersistentData().getDoubleOr("CoD", 0) + entity.getPersistentData().getDoubleOr("CoD_I", 0)));
			entity.getPersistentData().putDouble("CoD_NR", (entity.getPersistentData().getDoubleOr("CoD_N", 0)));
			entity.getPersistentData().putDouble("CoD_IR", (entity.getPersistentData().getDoubleOr("CoD_I", 0)));
			entity.getPersistentData().putDouble("CoD_N", 0);
			entity.getPersistentData().putDouble("CoD_I", 0);
			entity.getPersistentData().putDouble("Tox", (entity.getPersistentData().getDoubleOr("Tox", 0) + entity.getPersistentData().getDoubleOr("Tox_N", 0)));
			entity.getPersistentData().putDouble("Tox", (entity.getPersistentData().getDoubleOr("Tox", 0) + entity.getPersistentData().getDoubleOr("Tox_I", 0)));
			entity.getPersistentData().putDouble("Tox_NR", (entity.getPersistentData().getDoubleOr("Tox_N", 0)));
			entity.getPersistentData().putDouble("Tox_IR", (entity.getPersistentData().getDoubleOr("Tox_I", 0)));
			entity.getPersistentData().putDouble("Tox_N", 0);
			entity.getPersistentData().putDouble("Tox_I", 0);
		}
	}
}