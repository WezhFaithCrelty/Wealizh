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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.jei_recipes.CrushJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModParticleTypes;
import net.mcreator.wealizh.init.WealizhModItems;
import net.mcreator.wealizh.WealizhMod;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class CrusherGUIPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double i = 0;
		double Gear_blade_num = 0;
		double x_ran = 0;
		double y_ran = 0;
		double z_ran = 0;
		double j = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("E_d", true);
				_blockEntity.getPersistentData().putDouble("Eu", 450);
				_blockEntity.getPersistentData().putDouble("i", 3);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		for (int index0 = 0; index0 < 6; index0++) {
			if (!(Blocks.AIR.asItem() == (new Object() {
				public ItemStack getResult() {
					List<CrushJEIRecipe> recipes = null;
					if (world instanceof ServerLevel _lvl) {
						recipes = _lvl.recipeAccess().recipeMap().byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					} else {
						recipes = WealizhModRecipeTypes.recipes.byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
					}
					for (CrushJEIRecipe recipe : recipes) {
						List<Ingredient> ingredients = recipe.getIngredients();
						if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
							continue;
						if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) getBlockNBTNumber(world, BlockPos.containing(x, y, z), "i")).copy())))
							continue;
						return recipe.getResultItems().get(0).copy();
					}
					return ItemStack.EMPTY;
				}
			}.getResult()).getItem())) {
				Gear_blade_num = 1 + Gear_blade_num;
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("i", (1 + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "i")));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi") <= 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("ERi", 40);
					_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("ERi", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "ERi") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") <= 0 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") > 0) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("sound_i", 0);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
		}
		BatteryInputEToBlockPProcedure.execute(world, x, y, z, blockstate, 2, "");
		if (Gear_blade_num >= 6 && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0 && Mth.nextInt(RandomSource.create(), 1, 10) == 1
				&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:gear_blade")))) {
			i = 3;
			if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
				int _slotid = 0;
				ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
				_stk.hurtAndBreak(Mth.nextInt(RandomSource.create(), 1, 2), _serverLevel, null, _stkprov -> {
				});
				_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
			}
			for (int index1 = 0; index1 < 6; index1++) {
				if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
						&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
					int _slotid = (int) i;
					ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
					_stk.hurtAndBreak(Mth.nextInt(RandomSource.create(), 1, 2), _serverLevel, null, _stkprov -> {
					});
					_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:steel_collision")), SoundSource.BLOCKS,
								(float) Mth.nextDouble(RandomSource.create(), 0.5, 1), (float) Mth.nextDouble(RandomSource.create(), 1.2, 2.4));
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:steel_collision")), SoundSource.BLOCKS, (float) Mth.nextDouble(RandomSource.create(), 0.5, 1),
								(float) Mth.nextDouble(RandomSource.create(), 1.2, 2.4), false);
					}
				}
				if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("E", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") - 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy()).getItem() == Blocks.AIR.asItem()) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.EXPLOSION_SMOKE.get()), (x + 0.5), (y + 0.5), (z + 0.5), Mth.nextInt(RandomSource.create(), 32, 64), 0, 0, 0, 0.06);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.SPARK.get()), (x + 0.5), (y + 0.5), (z + 0.5), Mth.nextInt(RandomSource.create(), 48, 86), 0, 0, 0, 0.25);
					for (int index2 = 0; index2 < 9; index2++) {
						if (Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
							ItemsProjectileEmissionPProcedure.execute(world, x, y, z, (itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) j).copy()).copy(), Mth.nextInt(RandomSource.create(), 100, 500), 1, 1,
									Mth.nextDouble(RandomSource.create(), -0.6, 0.6), Mth.nextDouble(RandomSource.create(), 0.6, 2.4), Mth.nextDouble(RandomSource.create(), -0.6, 0.6));
						}
						j = 1 + j;
					}
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof Level _level && !_level.isClientSide())
						_level.explode(null, (x + 0.5), (y + 0.8), (z + 0.5), 1, Level.ExplosionInteraction.BLOCK);
					WealizhMod.queueServerWork(1, () -> {
						for (int index3 = 0; index3 < Mth.nextInt(RandomSource.create(), 0, 8); index3++) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(Items.IRON_NUGGET));
								entityToSpawn.setPickUpDelay(Mth.nextInt(RandomSource.create(), 100, 500));
								_level.addFreshEntity(entityToSpawn);
							}
						}
						for (int index4 = 0; index4 < Mth.nextInt(RandomSource.create(), 0, 4); index4++) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(Items.IRON_INGOT));
								entityToSpawn.setPickUpDelay(Mth.nextInt(RandomSource.create(), 100, 500));
								_level.addFreshEntity(entityToSpawn);
							}
						}
						for (int index5 = 0; index5 < Mth.nextInt(RandomSource.create(), 0, 8); index5++) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(WealizhModItems.STEEL_NUGGET.get()));
								entityToSpawn.setPickUpDelay(Mth.nextInt(RandomSource.create(), 100, 500));
								_level.addFreshEntity(entityToSpawn);
							}
						}
						for (int index6 = 0; index6 < Mth.nextInt(RandomSource.create(), 0, 4); index6++) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(WealizhModItems.STEEL_INGOT.get()));
								entityToSpawn.setPickUpDelay(Mth.nextInt(RandomSource.create(), 100, 500));
								_level.addFreshEntity(entityToSpawn);
							}
						}
						for (int index7 = 0; index7 < Mth.nextInt(RandomSource.create(), 0, 4); index7++) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(WealizhModItems.STEEL_SHAFT.get()));
								entityToSpawn.setPickUpDelay(Mth.nextInt(RandomSource.create(), 100, 500));
								_level.addFreshEntity(entityToSpawn);
							}
						}
						for (int index8 = 0; index8 < Mth.nextInt(RandomSource.create(), 0, 4); index8++) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(WealizhModItems.REBAR.get()));
								entityToSpawn.setPickUpDelay(Mth.nextInt(RandomSource.create(), 100, 500));
								_level.addFreshEntity(entityToSpawn);
							}
						}
						for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.45), (y + 0.45), (z + 0.45), (x + 0.55), (y + 0.55), (z + 0.55)))) {
							if (("minecraft:item").equals(BuiltInRegistries.ENTITY_TYPE.getKey(entityiterator.getType()).toString())) {
								entityiterator.setDeltaMovement(new Vec3((Mth.nextDouble(RandomSource.create(), -0.5, 0.5)), (Mth.nextDouble(RandomSource.create(), 0.5, 2)), (Mth.nextDouble(RandomSource.create(), -0.5, 0.5))));
							}
						}
					});
					{
						final Vec3 _center = new Vec3(x, y, z);
						for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(51 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
							if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
								AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:powdered_bran_and_millet_grains"));
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
				i = 1 + i;
			}
			x_ran = Mth.nextDouble(RandomSource.create(), -3, 3);
			y_ran = Mth.nextDouble(RandomSource.create(), 8, 12);
			z_ran = Mth.nextDouble(RandomSource.create(), -3, 3);
			for (int index9 = 0; index9 < Mth.nextInt(RandomSource.create(), 6, 16); index9++) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.SPARK.get()), (x + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (y + 1), (z + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), 0,
							(x_ran + Mth.nextDouble(RandomSource.create(), -1, 1)), (y_ran + Mth.nextDouble(RandomSource.create(), -2, 2)), (z_ran + Mth.nextDouble(RandomSource.create(), -1, 1)), 0.1);
			}
			for (int index10 = 0; index10 < Mth.nextInt(RandomSource.create(), 4, 12); index10++) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (WealizhModParticleTypes.SPARK.get()), (x + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), (y - 0.1), (z + Mth.nextDouble(RandomSource.create(), 0.2, 0.8)), 1, 0, 0, 0, 0.08);
			}
		}
		{
			List<CrushJEIRecipe> recipes = null;
			if (world instanceof ServerLevel _lvl) {
				recipes = _lvl.recipeAccess().recipeMap().byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			} else {
				recipes = WealizhModRecipeTypes.recipes.byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
			}
			for (CrushJEIRecipe recipe : recipes) {
				List<Ingredient> ingredients = recipe.getIngredients();
				if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
					continue;
				if (!ingredients.get(1).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 3).copy())))
					continue;
				List<ItemStack> reciperesult = recipe.getResultItems();
				for (ItemStack itemstack : reciperesult) {
					ItemStack itemstackiterator = itemstack.copy();
					if (64 <= getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
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
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							int _slotid = 0;
							ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
							_stk.shrink(1);
							_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
						}
						if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
							ItemStack _setstack = itemstackiterator.copy();
							_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() + itemstackiterator.getCount());
							_itemHandlerModifiable.setStackInSlot(1, _setstack);
						}
						i = 3;
						for (int index11 = 0; index11 < 6; index11++) {
							if (world instanceof ILevelExtension _ext && world instanceof ServerLevel _serverLevel
									&& _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
								int _slotid = (int) i;
								ItemStack _stk = _itemHandlerModifiable.getStackInSlot(_slotid).copy();
								_stk.hurtAndBreak(1, _serverLevel, null, _stkprov -> {
								});
								_itemHandlerModifiable.setStackInSlot(_slotid, _stk);
							}
							i = 1 + i;
						}
					}
					if (Gear_blade_num >= 6
							&& ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == itemstackiterator.getItem()
									|| (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == Blocks.AIR.asItem())
							&& itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).getCount() <= itemstackiterator.getMaxStackSize() - itemstackiterator.getCount() && getBlockNBTNumber(world, BlockPos.containing(x, y, z), "E") > 0) {
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:gear_blade"))) && Mth.nextInt(RandomSource.create(), 1, 3) == 1) {
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("time", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time") - 0.25));
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
									_blockEntity.getPersistentData().putDouble("time",
											(((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 8).copy()).is(ItemTags.create(ResourceLocation.parse("wealizh:gear_blade/level2"))) && !((new Object() {
												public ItemStack getResult() {
													List<CrushJEIRecipe> recipes = null;
													if (world instanceof ServerLevel _lvl) {
														recipes = _lvl.recipeAccess().recipeMap().byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
													} else {
														recipes = WealizhModRecipeTypes.recipes.byType(CrushJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
													}
													for (CrushJEIRecipe recipe : recipes) {
														List<Ingredient> ingredients = recipe.getIngredients();
														if (!ingredients.get(0).test((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy())))
															continue;
														if (!ingredients.get(1).test(new ItemStack(WealizhModItems.IRON_GEAR_BLADE.get())))
															continue;
														return recipe.getResultItems().get(0).copy();
													}
													return ItemStack.EMPTY;
												}
											}.getResult()).getItem() == Blocks.AIR.asItem()) ? 0.75 : 0.5) + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") <= 0 && 0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "time")) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:crusher_operation")), SoundSource.BLOCKS, 1, 1);
								} else {
									_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("wealizh:crusher_operation")), SoundSource.BLOCKS, 1, 1, false);
								}
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
									_blockEntity.getPersistentData().putDouble("sound_i", 23);
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
					}
				}
				break;
			}
		}
		if (0 < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("sound_i", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "sound_i") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (!(world instanceof Level _level138 && _level138.hasNeighborSignal(BlockPos.containing(x, y, z))) && getPropertyByName(blockstate, "passage") instanceof BooleanProperty _getbp140 && blockstate.getValue(_getbp140)) {
			BlockItemsBelowIsTheOutputSPProcedure.execute(world, x, y, z, 1);
		}
		BlockNumNBTSetZeroSPProcedure.execute(world, x, y, z, "time");
		BlockItemsUpwardEntrySPProcedure.execute(world, x, y, z, 0);
		ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, 0);
		i = 3;
		for (int index12 = 0; index12 < 6; index12++) {
			ItemChangeTimeSetZeroSPProcedure.execute(world, x, y, z, i);
			i = 1 + i;
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

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}