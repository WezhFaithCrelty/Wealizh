package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class BFSCDetectionTargetPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, double dx, double dy, double dz) {
		if (!getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz), "HD")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(dx, dy, dz);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("HD", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if ((ItIsConnectionBoolReturnProcedure.execute(world, dx, dy, dz, dx + 1, dy, dz) || !((world.getBlockState(BlockPos.containing(dx + 1, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
					|| (world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))) && ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 1, 0, 0))
					&& (world.getBlockState(BlockPos.containing(dx + 1, dy, dz))).is(BlockTags.create(
							ResourceLocation.parse((((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe")).toLowerCase(java.util.Locale.ENGLISH))))
					&& !getBlockNBTLogic(world, BlockPos.containing(dx + 1, dy, dz), "HD")) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, dx + 1, dy, dz);
			} else if (getBlockNBTLogic(world, BlockPos.containing(dx + 1, dy, dz),
					((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
							? "E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")
							: "L_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
					&& ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 1, 0, 0)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
						_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (dx + 1));
						_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dy);
						_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dz);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if ((ItIsConnectionBoolReturnProcedure.execute(world, dx, dy, dz, dx - 1, dy, dz) || !((world.getBlockState(BlockPos.containing(dx - 1, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
					|| (world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))) && ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, -1, 0, 0))
					&& (world.getBlockState(BlockPos.containing(dx - 1, dy, dz))).is(BlockTags.create(
							ResourceLocation.parse((((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe")).toLowerCase(java.util.Locale.ENGLISH))))
					&& !getBlockNBTLogic(world, BlockPos.containing(dx - 1, dy, dz), "HD")) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, dx - 1, dy, dz);
			} else if (getBlockNBTLogic(world, BlockPos.containing(dx - 1, dy, dz),
					((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
							? "E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")
							: "L_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
					&& ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, -1, 0, 0)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
						_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (dx - 1));
						_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dy);
						_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dz);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if ((ItIsConnectionBoolReturnProcedure.execute(world, dx, dy, dz, dx, dy + 1, dz) || !((world.getBlockState(BlockPos.containing(dx, dy + 1, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
					|| (world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))) && ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, 1, 0))
					&& (world.getBlockState(BlockPos.containing(dx, dy + 1, dz))).is(BlockTags.create(
							ResourceLocation.parse((((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe")).toLowerCase(java.util.Locale.ENGLISH))))
					&& !getBlockNBTLogic(world, BlockPos.containing(dx, dy + 1, dz), "HD")) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, dx, dy + 1, dz);
			} else if (getBlockNBTLogic(world, BlockPos.containing(dx, dy + 1, dz),
					((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
							? "E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")
							: "L_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
					&& ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, 1, 0)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
						_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dx);
						_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (dy + 1));
						_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dz);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if ((ItIsConnectionBoolReturnProcedure.execute(world, dx, dy, dz, dx, dy - 1, dz) || !((world.getBlockState(BlockPos.containing(dx, dy - 1, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
					|| (world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))) && ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, -1, 0))
					&& (world.getBlockState(BlockPos.containing(dx, dy - 1, dz))).is(BlockTags.create(
							ResourceLocation.parse((((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe")).toLowerCase(java.util.Locale.ENGLISH))))
					&& !getBlockNBTLogic(world, BlockPos.containing(dx, dy - 1, dz), "HD")) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, dx, dy - 1, dz);
			} else if (getBlockNBTLogic(world, BlockPos.containing(dx, dy - 1, dz),
					((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
							? "E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")
							: "L_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
					&& ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, -1, 0)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
						_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dx);
						_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (dy - 1));
						_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dz);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if ((ItIsConnectionBoolReturnProcedure.execute(world, dx, dy, dz, dx, dy, dz + 1) || !((world.getBlockState(BlockPos.containing(dx, dy, dz + 1))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
					|| (world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))) && ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, 0, 1))
					&& (world.getBlockState(BlockPos.containing(dx, dy, dz + 1))).is(BlockTags.create(
							ResourceLocation.parse((((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe")).toLowerCase(java.util.Locale.ENGLISH))))
					&& !getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz + 1), "HD")) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, dx, dy, dz + 1);
			} else if (getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz + 1),
					((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
							? "E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")
							: "L_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
					&& ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, 0, 1)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
						_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dx);
						_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dy);
						_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (dz + 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if ((ItIsConnectionBoolReturnProcedure.execute(world, dx, dy, dz, dx, dy, dz - 1) || !((world.getBlockState(BlockPos.containing(dx, dy, dz - 1))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))
					|| (world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:unidirectional_linear_valve")))) && ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, 0, -1))
					&& (world.getBlockState(BlockPos.containing(dx, dy, dz - 1))).is(BlockTags.create(
							ResourceLocation.parse((((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe")).toLowerCase(java.util.Locale.ENGLISH))))
					&& !getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz - 1), "HD")) {
				BFSCDetectionTargetPProcedure.execute(world, x, y, z, blockstate, dx, dy, dz - 1);
			} else if (getBlockNBTLogic(world, BlockPos.containing(dx, dy, dz - 1),
					((world.getBlockState(BlockPos.containing(dx, dy, dz))).is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
							? "E_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")
							: "L_d" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
					&& ItIsSelfConnectionBoolReturnProcedure.execute(world, dx, dy, dz, 0, 0, -1)) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")));
						_blockEntity.getPersistentData().putDouble(("tx" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dx);
						_blockEntity.getPersistentData().putDouble(("ty" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), dy);
						_blockEntity.getPersistentData().putDouble(("tz" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")), (dz - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}