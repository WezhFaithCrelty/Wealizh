package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class WCNAddSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, double ox, double oy, double oz) {
		if (getBlockNBTLogic(world, BlockPos.containing(ox, oy, oz), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E_o" : "L_o") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
				&& getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) <= 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(ox, oy, oz);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")), 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (ItIsConnectionBoolReturnProcedure.execute(world, ox, oy, oz, ox + 1, oy, oz)
					&& (world.getBlockState(BlockPos.containing(ox + 1, oy, oz))).is(BlockTags.create(ResourceLocation.parse(
							(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))).toLowerCase(java.util.Locale.ENGLISH))))
					&& (getBlockNBTString(world, BlockPos.containing(ox + 1, oy, oz), "sbn")).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ox, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(1 + getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (ItIsConnectionBoolReturnProcedure.execute(world, ox, oy, oz, ox - 1, oy, oz)
					&& (world.getBlockState(BlockPos.containing(ox - 1, oy, oz))).is(BlockTags.create(ResourceLocation.parse(
							(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))).toLowerCase(java.util.Locale.ENGLISH))))
					&& (getBlockNBTString(world, BlockPos.containing(ox - 1, oy, oz), "sbn")).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ox, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(1 + getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (ItIsConnectionBoolReturnProcedure.execute(world, ox, oy, oz, ox, oy + 1, oz)
					&& (world.getBlockState(BlockPos.containing(ox, oy + 1, oz))).is(BlockTags.create(ResourceLocation.parse(
							(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))).toLowerCase(java.util.Locale.ENGLISH))))
					&& (getBlockNBTString(world, BlockPos.containing(ox, oy + 1, oz), "sbn")).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ox, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(1 + getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (ItIsConnectionBoolReturnProcedure.execute(world, ox, oy, oz, ox, oy - 1, oz)
					&& (world.getBlockState(BlockPos.containing(ox, oy - 1, oz))).is(BlockTags.create(ResourceLocation.parse(
							(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))).toLowerCase(java.util.Locale.ENGLISH))))
					&& (getBlockNBTString(world, BlockPos.containing(ox, oy - 1, oz), "sbn")).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(oy, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(1 + getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (ItIsConnectionBoolReturnProcedure.execute(world, ox, oy, oz, ox, oy, oz + 1)
					&& (world.getBlockState(BlockPos.containing(ox, oy, oz + 1))).is(BlockTags.create(ResourceLocation.parse(
							(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))).toLowerCase(java.util.Locale.ENGLISH))))
					&& (getBlockNBTString(world, BlockPos.containing(ox, oy, oz + 1), "sbn")).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ox, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(1 + getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (ItIsConnectionBoolReturnProcedure.execute(world, ox, oy, oz, ox, oy, oz - 1)
					&& (world.getBlockState(BlockPos.containing(ox, oy, oz - 1))).is(BlockTags.create(ResourceLocation.parse(
							(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "wealizh:wire" : "wealizh:pipe") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))).toLowerCase(java.util.Locale.ENGLISH))))
					&& (getBlockNBTString(world, BlockPos.containing(ox, oy, oz - 1), "sbn")).equals(getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ox, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(1 + getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}