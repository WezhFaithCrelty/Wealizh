package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.wealizh.jei_recipes.DecayJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;
import net.mcreator.wealizh.init.WealizhModEntities;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class BlocksRadiationsProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double RadA = 0;
		RadA = ItemRadioactivityAttributeProcedure.execute(new ItemStack(blockstate.getBlock()));
		if (RadA >= 0.1) {
			AllRInPProcedure.execute(world, x + 0.5, y + 0.5, z + 0.5, world instanceof Level _level ? WealizhModEntities.EMPTY_ENTITY.get().create(_level, EntitySpawnReason.EVENT) : null, false, true,
					getBlockNBTNumber(world, BlockPos.containing(x, y, z), "t") < 1, 0, 0, RadA / 20, RadA * 10, 0, 0);
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "t") < 1) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("t", 50);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			}
			if (Mth.nextInt(RandomSource.create(), 1, (int) (RadA * 100)) == 1 && RadA >= 2.5) {
				BsRadiationsPProcedure.execute(world, x, y, z, 0, 10, RadA);
			}
			{
				List<DecayJEIRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(DecayJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = WealizhModRecipeTypes.recipes.byType(DecayJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (DecayJEIRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()))))
						continue;
					List<ItemStack> reciperesult = recipe.getResultItems();
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if (Mth.nextInt(RandomSource.create(), 1, (int) Math.ceil(20130110 / RadA)) == 1) {
							if (!((itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()).getBlock() == Blocks.AIR)
									&& (itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()).canSurvive(world, BlockPos.containing(x, y, z))) {
								{
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockState _bs = (itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState());
									BlockState _bso = world.getBlockState(_bp);
									for (Property<?> _propertyOld : _bso.getProperties()) {
										Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
										if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
											try {
												_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
											} catch (Exception e) {
											}
									}
									BlockEntity _be = world.getBlockEntity(_bp);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata(world.registryAccess());
										_be.setRemoved();
									}
									world.setBlock(_bp, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(_bp);
										if (_be != null) {
											try {
												_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
											} catch (Exception ignored) {
											}
										}
									}
								}
							} else {
								world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
								if (world instanceof ServerLevel _level) {
									ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), itemstackiterator);
									entityToSpawn.setPickUpDelay(10);
									_level.addFreshEntity(entityToSpawn);
								}
								for (Entity entityiterator : world.getEntities(null, new AABB((x + 0.49), (y + 0.49), (z + 0.49), (x + 0.51), (y + 0.51), (z + 0.51)))) {
									entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
								}
							}
							{
								final Vec3 _center = new Vec3((x + 0.5), (y + 0.5), (z + 0.5));
								for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(25 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
										.toList()) {
									if (entityiterator instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
										AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:radioactive_decay"));
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
					}
					break;
				}
			}
		}
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "t") > 0) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("t", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "t") - 1));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}