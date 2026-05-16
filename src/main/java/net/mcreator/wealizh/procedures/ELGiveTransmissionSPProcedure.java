package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class ELGiveTransmissionSPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, double give_num, double i, double ox, double oy, double oz) {
		double give_num_i = 0;
		double give_num_o = 0;
		if (1 <= getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
				&& getBlockNBTLogic(world,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
						((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E_d" : "L_d") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
				&& ((world.getBlockState(
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i)))))
						.is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))
								? true
								: getBlockNBTNumber(world,
										BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
												getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
										((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn"))) < getBlockNBTNumber(world,
												BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
														getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
												((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "Eu" : "Lu") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn"))))
				&& (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire")))
						? true
						: ((itemFromBlockInventory(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
										getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
								(int) getBlockNBTNumber(world,
										BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
												getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
										("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn"))))
								.copy()).getItem() == Blocks.AIR.asItem()
								|| (itemFromBlockInventory(world,
										BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
												getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
										(int) getBlockNBTNumber(world,
												BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
														getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
												("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn"))))
										.copy())
										.getItem() == (itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz),
												(int) getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))).copy()).getItem())
								&& !((itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))).copy())
										.getItem() == Blocks.AIR.asItem()))
				&& !(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)) == ox && getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)) == oy && getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i)) == oz)
				&& getBlockNBTLogic(world,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
						((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E_d" : "L_d") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))) {
			if ((world.getBlockState(
					BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i)))))
					.is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))) {
				give_num_i = give_num;
			} else {
				if (getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) < give_num) {
					give_num_o = getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")));
				} else {
					give_num_o = give_num;
				}
				give_num_i = getBlockNBTNumber(world,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
						((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
						+ give_num_o > getBlockNBTNumber(world,
								BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
										getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
								((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "Eu" : "Lu") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
										? getBlockNBTNumber(world,
												BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
														getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
												((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "Eu" : "Lu") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
												- getBlockNBTNumber(world,
														BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
																getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
														((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))
										: give_num_o;
			}
			if (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:pipe"))) && (itemFromBlockInventory(world,
					BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
					(int) getBlockNBTNumber(world, BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
							getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))), ("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn"))))
					.copy()).getItem() == Blocks.AIR.asItem()) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK,
						BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
						null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					ItemStack _setstack = (itemFromBlockInventory(world, BlockPos.containing(ox, oy, oz), (int) getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))))
							.copy()).copy();
					_setstack.setCount(1);
					_itemHandlerModifiable.setStackInSlot(0, _setstack);
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
						getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i)));
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")),
							(give_num_i + getBlockNBTNumber(world,
									BlockPos.containing(getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tx" + i)), getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("ty" + i)),
											getBlockNBTNumber(world, BlockPos.containing(x, y, z), ("tz" + i))),
									((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "obn")))));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (!(world.getBlockState(BlockPos.containing(ox, oy, oz))).is(BlockTags.create(ResourceLocation.parse("wealizh:infinite_unit")))) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(ox, oy, oz);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")),
								(getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")))
										- Math.floor(give_num_i)));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) < 0) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(ox, oy, oz);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble(((blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:wire"))) ? "E" : "L") + "" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn")), 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
			if (blockstate.is(BlockTags.create(ResourceLocation.parse("wealizh:pipe"))) && getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("L" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))) <= 0) {
				if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(ox, oy, oz), null) instanceof IItemHandlerModifiable _itemHandlerModifiable)
					_itemHandlerModifiable.setStackInSlot((int) getBlockNBTNumber(world, BlockPos.containing(ox, oy, oz), ("L_slot" + getBlockNBTString(world, BlockPos.containing(x, y, z), "sbn"))), ItemStack.EMPTY);
			}
		}
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

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
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