package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class ConcentratedWirePipeTickSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, double xa, double ya, double za) {
		double give_num = 0;
		double i = 0;
		if (1 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num")) {
			WCNAddSPProcedure.execute(world, x, y, z, blockstate, xa, ya, za);
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1 == 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("co_num", 2);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			give_num = blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
					? Math.floor(((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "EO_num") < 1
							? getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("E" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
							: getBlockNBTNumber(world, BlockPos.containing(x, y, z), "EO_num")) / (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1))
							/ getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))))
					: Math.floor(getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) * getBlockNBTNumber(world, BlockPos.containing(x, y, z), "LO_num")
							* (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1) <= getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
									? getBlockNBTNumber(world, BlockPos.containing(x, y, z), "LO_num")
									: (getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) / (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num") - 1))
											/ getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))));
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(xa, ya, za);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
							(getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ("WCN" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			i = 1;
			for (int index0 = 0; index0 < (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "co_num"); index0++) {
				if (1 <= getBlockNBTNumber(world, BlockPos.containing(xa, ya, za), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
						&& getBlockNBTLogic(world, BlockPos.containing(xa, ya, za), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E_o" : "L_o") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))) {
					ELGiveTransmissionSPProcedure.execute(world, x, y, z, blockstate, give_num, i, xa, ya, za);
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

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}