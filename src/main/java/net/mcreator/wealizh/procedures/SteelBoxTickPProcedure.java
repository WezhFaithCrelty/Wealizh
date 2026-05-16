package net.mcreator.wealizh.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.jei_recipes.SoakingJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModItems;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class SteelBoxTickPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double oi = 0;
		double io_bool = 0;
		double ioi_bool = 0;
		if (getPropertyByName(blockstate, "waterlogged") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1) && Mth.nextInt(RandomSource.create(), 1, 20) == 1) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.DRIPPING_WATER, (x + Mth.nextDouble(RandomSource.create(), -0.1, 1.1)), (y + Mth.nextDouble(RandomSource.create(), -0.1, 0.9)), (z + Mth.nextDouble(RandomSource.create(), -0.1, 1.1)), 1, 0, 0, 0, 1);
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		{
			List<SoakingJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(SoakingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(SoakingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (SoakingJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
					continue;
				if (!ingredients.get(1).test(new ItemStack(WealizhModItems.WATER.get())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					ioi_bool = 1 + ioi_bool;
					if (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) ioi_bool).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount()
							&& (itemstackiterator.getItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) ioi_bool).copy()).getItem()
									|| Blocks.AIR.asItem() == (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) ioi_bool).copy()).getItem())) {
						io_bool = 1 + io_bool;
					}
				}
				break;
			}
		}
		oi = 1;
		{
			List<SoakingJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(SoakingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(SoakingJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (SoakingJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
					continue;
				if (!ingredients.get(1).test(new ItemStack(WealizhModItems.WATER.get())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				if (34 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
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
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							ItemStack _setstack = itemstackiterator.copy();
							_setstack.setCount(itemstackiterator.getCount() + itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) oi).getCount());
							_itemHandlerModifiable.setStackInSlot((int) oi, _setstack);
						}
						oi = 1 + oi;
					}
					if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
						int _slotid = 0;
						ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
						_stk.shrink(1);
						_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(9 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
							if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
								AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:collectsediment"));
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
				if (getPropertyByName(blockstate, "waterlogged") instanceof BooleanProperty _getbp34 && blockstate.getValue(_getbp34) && io_bool == ioi_bool) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("time", (0.17 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") && Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.BUBBLE, (x + Mth.nextDouble(RandomSource.create(), -0.1, 1.1)), (y + Mth.nextDouble(RandomSource.create(), -0.1, 1)), (z + Mth.nextDouble(RandomSource.create(), -0.1, 1.1)), 1, 0, 0, 0,
									1);
					}
					if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") && Mth.nextInt(RandomSource.create(), 1, 20) == 1) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.bubble_column.bubble_pop")), SoundSource.BLOCKS, 2, 1);
							} else {
								_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.bubble_column.bubble_pop")), SoundSource.BLOCKS, 2, 1, false);
							}
						}
					}
				} else {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 2));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				break;
			}
		}
		ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, 0);
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}