package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.jei_recipes.ForgingJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.init.WealizhModBlocks;

import java.util.stream.Collectors;
import java.util.List;

public class PressingMachinePiLinFangKuaiGengXinShiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("function") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
		}
		{
			List<ForgingJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(ForgingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(ForgingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (ForgingJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 1).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 1).copy())))
					continue;
				if (!ingredients.get(1)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 2).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 2).copy())))
					continue;
				if (!ingredients.get(2)
						.test((WealizhModItems.SLOT_OCCUPANCY_PLATE.get() == (itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 3).copy()).getItem()
								? new ItemStack(Blocks.AIR)
								: itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 3).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == WealizhModBlocks.PRESSING_STORAGE_ROOM.get()
							&& ((itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 0).copy()).getItem() == itemstackiterator.getItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 0).copy()).getItem() == Blocks.AIR.asItem())
							&& itemFromBlockInventory(world, BlockPos.containing(x, y - 1, z), 0).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount() && getBlockNBTNumber(world, BlockPos.containing(x, y - 1, z), "E") > 0
							&& getBlockNBTNumber(world, BlockPos.containing(x, y - 1, z), "damage_l") < 34 && getBlockNBTLogic(world, BlockPos.containing(x, y - 1, z), "ON")) {
						{
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("function") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
					}
				}
				break;
			}
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			IItemHandler itemHandler = ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.getStackInSlot(slot);
		}
		return ItemStack.EMPTY;
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