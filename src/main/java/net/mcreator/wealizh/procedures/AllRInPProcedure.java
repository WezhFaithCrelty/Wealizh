package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import java.util.UUID;
import java.util.Comparator;

public class AllRInPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, boolean DS, boolean IB, boolean sc, double CoD_num, double CoD_sc, double Rad_num, double Rad_sc, double Tox_num, double Tox_sc) {
		if (entity == null)
			return;
		Entity TEN = null;
		double IC_num = 0;
		double DisC = 0;
		double TN = 0;
		double i = 0;
		if (sc) {
			if (CoD_sc <= Rad_sc && Tox_sc <= Rad_sc) {
				IC_num = Rad_sc;
			} else if (Rad_sc <= CoD_sc && Tox_sc <= CoD_sc) {
				IC_num = CoD_sc;
			} else {
				IC_num = Tox_sc;
			}
			if (IB) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("Rad_SN", 0);
						_blockEntity.getPersistentData().putDouble("CoD_SN", 0);
						_blockEntity.getPersistentData().putDouble("Tox_SN", 0);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else {
				entity.getPersistentData().putDouble("Rad_SN", 0);
				entity.getPersistentData().putDouble("CoD_SN", 0);
				entity.getPersistentData().putDouble("Tox_SN", 0);
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((IC_num * 2 + 1) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
						.toList()) {
					if (!entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("wealizh:netrad")))) {
						DisC = DistanceCalculationProcedure.execute(x, y, z, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ());
						if (1 > DisC) {
							DisC = 1;
						}
						if (DisC <= Rad_sc) {
							entityiterator.getPersistentData().putDouble("Rad_N",
									(EmitRadioactiveRaysSPProcedure.execute(world, x, y, z, DS, Rad_num, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()) + entityiterator.getPersistentData().getDoubleOr("Rad_N", 0)));
							if (IB) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null) {
										_blockEntity.getPersistentData().putString(("Rad_SE" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN")), (entityiterator.getStringUUID()));
										_blockEntity.getPersistentData().putDouble("Rad_SN", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN")));
									}
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							} else {
								entity.getPersistentData().putString(("Rad_SE" + entity.getPersistentData().getDoubleOr("Rad_SN", 0)), (entityiterator.getStringUUID()));
								entity.getPersistentData().putDouble("Rad_SN", (1 + entity.getPersistentData().getDoubleOr("Rad_SN", 0)));
							}
						}
						if (DisC <= CoD_sc) {
							entityiterator.getPersistentData().putDouble("CoD_N", (CoD_num / (1 > DisC / 5 ? 1 : DisC / 5) + entityiterator.getPersistentData().getDoubleOr("CoD_N", 0)));
							if (IB) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null) {
										_blockEntity.getPersistentData().putString(("CoD_SE" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN")), (entityiterator.getStringUUID()));
										_blockEntity.getPersistentData().putDouble("CoD_SN", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN")));
									}
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							} else {
								entity.getPersistentData().putString(("CoD_SE" + entity.getPersistentData().getDoubleOr("CoD_SN", 0)), (entityiterator.getStringUUID()));
								entity.getPersistentData().putDouble("CoD_SN", (1 + entity.getPersistentData().getDoubleOr("CoD_SN", 0)));
							}
						}
						if (DisC <= Tox_sc) {
							entityiterator.getPersistentData().putDouble("Tox_N", (Tox_num / (1 > DisC / 5 ? 1 : DisC / 5) + entityiterator.getPersistentData().getDoubleOr("Tox_N", 0)));
							if (IB) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null) {
										_blockEntity.getPersistentData().putString(("Tox_SE" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_SN")), (entityiterator.getStringUUID()));
										_blockEntity.getPersistentData().putDouble("Tox_SN", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_SN")));
									}
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
							} else {
								entity.getPersistentData().putString(("Tox_SE" + entity.getPersistentData().getDoubleOr("Tox_SN", 0)), (entityiterator.getStringUUID()));
								entity.getPersistentData().putDouble("Tox_SN", (1 + entity.getPersistentData().getDoubleOr("Tox_SN", 0)));
							}
						}
					}
				}
			}
		} else {
			if (IB) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN") <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN")
						&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_SN") <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN")) {
					IC_num = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN");
				} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN") <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN")
						&& getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_SN") <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN")) {
					IC_num = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN");
				} else {
					IC_num = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_SN");
				}
			} else {
				if (entity.getPersistentData().getDoubleOr("CoD_SN", 0) <= entity.getPersistentData().getDoubleOr("Rad_SN", 0) && entity.getPersistentData().getDoubleOr("Tox_SN", 0) <= entity.getPersistentData().getDoubleOr("Rad_SN", 0)) {
					IC_num = entity.getPersistentData().getDoubleOr("Rad_SN", 0);
				} else if (entity.getPersistentData().getDoubleOr("Rad_SN", 0) <= entity.getPersistentData().getDoubleOr("CoD_SN", 0) && entity.getPersistentData().getDoubleOr("Tox_SN", 0) <= entity.getPersistentData().getDoubleOr("CoD_SN", 0)) {
					IC_num = entity.getPersistentData().getDoubleOr("CoD_SN", 0);
				} else {
					IC_num = entity.getPersistentData().getDoubleOr("Tox_SN", 0);
				}
			}
			for (int index0 = 0; index0 < (int) IC_num; index0++) {
				if (IB) {
					if (i <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Rad_SN")) {
						TEN = world instanceof ServerLevel _level74 ? getEntityFromUUID(_level74, (getBlockNBTString(world, BlockPos.containing(x, y, z), ("Rad_SE" + i)))) : null;
						if (!(TEN == null)) {
							DisC = DistanceCalculationProcedure.execute(x, y, z, TEN.getX(), TEN.getY(), TEN.getZ());
							if (1 > DisC) {
								DisC = 1;
							}
							if (DisC <= Rad_sc) {
								TEN.getPersistentData().putDouble("Rad_N", (EmitRadioactiveRaysSPProcedure.execute(world, x, y, z, DS, Rad_num, TEN.getX(), TEN.getY(), TEN.getZ()) + TEN.getPersistentData().getDoubleOr("Rad_N", 0)));
							}
						}
					}
					if (i <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "CoD_SN")) {
						TEN = world instanceof ServerLevel _level86 ? getEntityFromUUID(_level86, (getBlockNBTString(world, BlockPos.containing(x, y, z), ("CoD_SE" + i)))) : null;
						if (!(TEN == null)) {
							DisC = DistanceCalculationProcedure.execute(x, y, z, TEN.getX(), TEN.getY(), TEN.getZ());
							if (1 > DisC) {
								DisC = 1;
							}
							if (DisC <= CoD_sc) {
								TEN.getPersistentData().putDouble("CoD_N", (CoD_num / (1 > DisC / 5 ? 1 : DisC / 5) + TEN.getPersistentData().getDoubleOr("CoD_N", 0)));
							}
						}
					}
					if (i <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Tox_SN")) {
						TEN = world instanceof ServerLevel _level95 ? getEntityFromUUID(_level95, (getBlockNBTString(world, BlockPos.containing(x, y, z), ("Tox_SE" + i)))) : null;
						if (!(TEN == null)) {
							DisC = DistanceCalculationProcedure.execute(x, y, z, TEN.getX(), TEN.getY(), TEN.getZ());
							if (1 > DisC) {
								DisC = 1;
							}
							if (DisC <= Tox_sc) {
								TEN.getPersistentData().putDouble("Tox_N", (Tox_num / (1 > DisC / 5 ? 1 : DisC / 5) + TEN.getPersistentData().getDoubleOr("Tox_N", 0)));
							}
						}
					}
				} else {
					if (i <= entity.getPersistentData().getDoubleOr("Rad_SN", 0)) {
						TEN = world instanceof ServerLevel _level104 ? getEntityFromUUID(_level104, (entity.getPersistentData().getStringOr(("Rad_SE" + i), ""))) : null;
						if (!(TEN == null)) {
							DisC = DistanceCalculationProcedure.execute(x, y, z, TEN.getX(), TEN.getY(), TEN.getZ());
							if (1 > DisC) {
								DisC = 1;
							}
							if (DisC <= Rad_sc) {
								TEN.getPersistentData().putDouble("Rad_N", (EmitRadioactiveRaysSPProcedure.execute(world, x, y, z, DS, Rad_num, TEN.getX(), TEN.getY(), TEN.getZ()) + TEN.getPersistentData().getDoubleOr("Rad_N", 0)));
							}
						}
					}
					if (i <= entity.getPersistentData().getDoubleOr("CoD_SN", 0)) {
						TEN = world instanceof ServerLevel _level116 ? getEntityFromUUID(_level116, (entity.getPersistentData().getStringOr(("CoD_SE" + i), ""))) : null;
						if (!(TEN == null)) {
							DisC = DistanceCalculationProcedure.execute(x, y, z, TEN.getX(), TEN.getY(), TEN.getZ());
							if (1 > DisC) {
								DisC = 1;
							}
							if (DisC <= CoD_sc) {
								TEN.getPersistentData().putDouble("CoD_N", (CoD_num / (1 > DisC / 5 ? 1 : DisC / 5) + TEN.getPersistentData().getDoubleOr("CoD_N", 0)));
							}
						}
					}
					if (i <= entity.getPersistentData().getDoubleOr("Tox_SN", 0)) {
						TEN = world instanceof ServerLevel _level125 ? getEntityFromUUID(_level125, (entity.getPersistentData().getStringOr(("Tox_SE" + i), ""))) : null;
						if (!(TEN == null)) {
							DisC = DistanceCalculationProcedure.execute(x, y, z, TEN.getX(), TEN.getY(), TEN.getZ());
							if (1 > DisC) {
								DisC = 1;
							}
							if (DisC <= Tox_sc) {
								TEN.getPersistentData().putDouble("Tox_N", (Tox_num / (1 > DisC / 5 ? 1 : DisC / 5) + TEN.getPersistentData().getDoubleOr("Tox_N", 0)));
							}
						}
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

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}

	private static Entity getEntityFromUUID(ServerLevel level, String uuid) {
		try {
			return level.getEntity(UUID.fromString(uuid));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}