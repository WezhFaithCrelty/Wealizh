package net.mcreator.wealizh.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.item.ItemEntity;
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

import net.mcreator.wealizh.jei_recipes.QualitativeChangeJEIRecipe;
import net.mcreator.wealizh.init.WealizhModRecipeTypes;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class BlocksQualitativeChangesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (getPropertyByName(blockstate, "qc_bool") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			{
				List<QualitativeChangeJEIRecipe> recipes = null;
				if (world instanceof ServerLevel _lvl) {
					recipes = _lvl.recipeAccess().recipeMap().byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				} else {
					recipes = WealizhModRecipeTypes.recipes.byType(QualitativeChangeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
				}
				for (QualitativeChangeJEIRecipe recipe : recipes) {
					List<Ingredient> ingredients = recipe.getIngredients();
					if (!ingredients.get(0).test((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()))))
						continue;
					List<ItemStack> reciperesult = recipe.getResultItems();
					for (ItemStack itemstack : reciperesult) {
						ItemStack itemstackiterator = itemstack.copy();
						if (Mth.nextInt(RandomSource.create(), 1, (int) ItemQualitativeChangeAttributeProcedure.execute(new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()))) == 1) {
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
										AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("wealizh:qualitative_change"));
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