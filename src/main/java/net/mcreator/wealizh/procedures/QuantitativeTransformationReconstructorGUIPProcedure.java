package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.jei_recipes.QuantitativeTransformationReconstructorJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import java.util.stream.Collectors;
import java.util.List;

public class QuantitativeTransformationReconstructorGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		ItemStack it = ItemStack.EMPTY;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("slot_he", 2);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!(WealizhModItems.QUANTITATIVE_TRANSFORMATION_SUBSTANCE_NUCLEUS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy()).getItem())) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.4));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:substance_nucleus")))) {
			if (WealizhModItems.EMPTY_SUBSTANCE_NUCLEUS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("substance_nucleus_led", 4);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (WealizhModItems.QUANTITATIVE_TRANSFORMATION_SUBSTANCE_NUCLEUS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy()).getItem()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("substance_nucleus_led", 2);
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
						_blockEntity.getPersistentData().putDouble("substance_nucleus_led", 1);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("substance_nucleus_led", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("time", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("douliang", (Mth.nextInt(RandomSource.create(), 0, 34)));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			QuantitativeTransformationReconstructorOperationPiLinFangKuaiGengXinShiProcedure.execute(world, x, y, z);
		} else if (getPropertyByName(blockstate, "operation") instanceof BooleanProperty _getbp26 && blockstate.getValue(_getbp26)) {
			QuantitativeTransformationReconstructorOperationPiLinFangKuaiGengXinShiProcedure.execute(world, x, y, z);
		}
		if (0 >= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("douliang", 0);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		GUIZhiLiangBarIndexYSPProcedure.execute(world, x, y, z);
		{
			List<QuantitativeTransformationReconstructorJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(QuantitativeTransformationReconstructorJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(QuantitativeTransformationReconstructorJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (QuantitativeTransformationReconstructorJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
					continue;
				if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount()
							&& WealizhModItems.QUANTITATIVE_TRANSFORMATION_SUBSTANCE_NUCLEUS.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy())
									.getItem()
							&& (itemstackiterator.getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem()
									|| Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem())) {
						if (48 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("time", 0);
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
									&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								int _slotid = (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he");
								ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
								_stk.hurtAndBreak(1, _serverLevel, null, _stkprov -> {
								});
								_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
							}
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								int _slotid = 0;
								ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
								_stk.shrink(1);
								_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
							}
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								ItemStack _setstack = itemstackiterator.copy();
								_setstack.setCount(itemstackiterator.getCount() + itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount());
								_itemHandlerModifiable.setStackInSlot(1, _setstack);
							}
						}
						if (Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he")).copy()).getItem()) {
							if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								ItemStack _setstack = new ItemStack(WealizhModItems.EMPTY_SUBSTANCE_NUCLEUS.get()).copy();
								_setstack.setCount(1);
								_itemHandlerModifiable.setStackInSlot((int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "slot_he"), _setstack);
							}
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("time",
										((1 / ItemQualitativeChangeAttributeProcedure.execute(itemstackiterator)) * 100
												+ getBlockNBTNumber(world, BlockPos.containing(x, y, z), "douliang") / (ItemQualitativeChangeAttributeProcedure.execute(itemstackiterator) / 20)
												+ getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				}
				break;
			}
		}
		ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, 0);
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}