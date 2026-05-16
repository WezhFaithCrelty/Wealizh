package net.mcreator.wealizh.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.init.WealizhModBlocks;

public class AssemblyMachineITFProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double ix = 0;
		double iy = 0;
		double iz = 0;
		if (Direction.DOWN == (getDirectionFromBlockState(blockstate))) {
			iy = 0;
			for (int index0 = 0; index0 < 3; index0++) {
				ix = -1;
				for (int index1 = 0; index1 < 3; index1++) {
					iz = -1;
					for (int index2 = 0; index2 < 3; index2++) {
						if (!(((new ItemStack((world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock())).is(ItemTags.create(ResourceLocation.parse("wealizh:iron_block")))
								|| 0 == ix && 1 == iy && 0 == iz && WealizhModBlocks.ASSEMBLY_ROOM.get() == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock() || 0 == ix && 0 == iy && 0 == iz
								|| -1 == ix && 0 == iy && 0 == iz || 1 == ix && 0 == iy && 0 == iz || 0 == ix && 0 == iy && -1 == iz || 0 == ix && 0 == iy && 1 == iz)
								&& !(Blocks.AIR == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock()))) {
							return false;
						}
						iz = iz + 1;
					}
					ix = ix + 1;
				}
				iy = iy + 1;
			}
		}
		if (Direction.UP == (getDirectionFromBlockState(blockstate))) {
			iy = -2;
			for (int index3 = 0; index3 < 3; index3++) {
				ix = -1;
				for (int index4 = 0; index4 < 3; index4++) {
					iz = -1;
					for (int index5 = 0; index5 < 3; index5++) {
						if (!(((new ItemStack((world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock())).is(ItemTags.create(ResourceLocation.parse("wealizh:iron_block")))
								|| 0 == ix && -1 == iy && 0 == iz && WealizhModBlocks.ASSEMBLY_ROOM.get() == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock() || 0 == ix && 0 == iy && 0 == iz
								|| -1 == ix && 0 == iy && 0 == iz || 1 == ix && 0 == iy && 0 == iz || 0 == ix && 0 == iy && -1 == iz || 0 == ix && 0 == iy && 1 == iz)
								&& !(Blocks.AIR == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock()))) {
							return false;
						}
						iz = iz + 1;
					}
					ix = ix + 1;
				}
				iy = iy + 1;
			}
		}
		if (Direction.NORTH == (getDirectionFromBlockState(blockstate))) {
			iy = -1;
			for (int index6 = 0; index6 < 3; index6++) {
				ix = -1;
				for (int index7 = 0; index7 < 3; index7++) {
					iz = 0;
					for (int index8 = 0; index8 < 3; index8++) {
						if (!(((new ItemStack((world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock())).is(ItemTags.create(ResourceLocation.parse("wealizh:iron_block")))
								|| 0 == ix && 0 == iy && 1 == iz && WealizhModBlocks.ASSEMBLY_ROOM.get() == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock() || 0 == ix && 0 == iy && 0 == iz
								|| -1 == ix && 0 == iy && 0 == iz || 1 == ix && 0 == iy && 0 == iz || 0 == ix && 1 == iy && 0 == iz || 0 == ix && -1 == iy && 0 == iz)
								&& !(Blocks.AIR == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock()))) {
							return false;
						}
						iz = iz + 1;
					}
					ix = ix + 1;
				}
				iy = iy + 1;
			}
		}
		if (Direction.SOUTH == (getDirectionFromBlockState(blockstate))) {
			iy = -1;
			for (int index9 = 0; index9 < 3; index9++) {
				ix = -1;
				for (int index10 = 0; index10 < 3; index10++) {
					iz = -2;
					for (int index11 = 0; index11 < 3; index11++) {
						if (!(((new ItemStack((world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock())).is(ItemTags.create(ResourceLocation.parse("wealizh:iron_block")))
								|| 0 == ix && 0 == iy && -1 == iz && WealizhModBlocks.ASSEMBLY_ROOM.get() == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock() || 0 == ix && 0 == iy && 0 == iz
								|| -1 == ix && 0 == iy && 0 == iz || 1 == ix && 0 == iy && 0 == iz || 0 == ix && 1 == iy && 0 == iz || 0 == ix && -1 == iy && 0 == iz)
								&& !(Blocks.AIR == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock()))) {
							return false;
						}
						iz = iz + 1;
					}
					ix = ix + 1;
				}
				iy = iy + 1;
			}
		}
		if (Direction.WEST == (getDirectionFromBlockState(blockstate))) {
			iy = -1;
			for (int index12 = 0; index12 < 3; index12++) {
				ix = 0;
				for (int index13 = 0; index13 < 3; index13++) {
					iz = -1;
					for (int index14 = 0; index14 < 3; index14++) {
						if (!(((new ItemStack((world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock())).is(ItemTags.create(ResourceLocation.parse("wealizh:iron_block")))
								|| 1 == ix && 0 == iy && 0 == iz && WealizhModBlocks.ASSEMBLY_ROOM.get() == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock() || 0 == ix && 0 == iy && 0 == iz
								|| 0 == ix && 0 == iy && 1 == iz || 0 == ix && 0 == iy && -1 == iz || 0 == ix && 1 == iy && 0 == iz || 0 == ix && -1 == iy && 0 == iz)
								&& !(Blocks.AIR == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock()))) {
							return false;
						}
						iz = iz + 1;
					}
					ix = ix + 1;
				}
				iy = iy + 1;
			}
		}
		if (Direction.EAST == (getDirectionFromBlockState(blockstate))) {
			iy = -1;
			for (int index15 = 0; index15 < 3; index15++) {
				ix = -2;
				for (int index16 = 0; index16 < 3; index16++) {
					iz = -1;
					for (int index17 = 0; index17 < 3; index17++) {
						if (!(((new ItemStack((world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock())).is(ItemTags.create(ResourceLocation.parse("wealizh:iron_block")))
								|| -1 == ix && 0 == iy && 0 == iz && WealizhModBlocks.ASSEMBLY_ROOM.get() == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock() || 0 == ix && 0 == iy && 0 == iz
								|| 0 == ix && 0 == iy && 1 == iz || 0 == ix && 0 == iy && -1 == iz || 0 == ix && 1 == iy && 0 == iz || 0 == ix && -1 == iy && 0 == iz)
								&& !(Blocks.AIR == (world.getBlockState(BlockPos.containing(x + ix, y + iy, z + iz))).getBlock()))) {
							return false;
						}
						iz = iz + 1;
					}
					ix = ix + 1;
				}
				iy = iy + 1;
			}
		}
		return true;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
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