package net.mcreator.wealizh.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.procedures.RadioactiveFalloutFangKuaiBeiWanJiaPoPiShiProcedure;
import net.mcreator.wealizh.procedures.ItemsALabelTextProcedure;
import net.mcreator.wealizh.procedures.BelowIsTheSolidBlockBoolReturnProcedure;
import net.mcreator.wealizh.init.WealizhModBlocks;
import net.mcreator.wealizh.WealizhMod;

import java.util.function.Function;
import java.util.function.Consumer;

public class RadioactiveFalloutBlock extends Block {
	public static final IntegerProperty CUMT = IntegerProperty.create("cumt", 0, 15);
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public RadioactiveFalloutBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_GRAY).sound(SoundType.SAND).strength(0.1f, 0f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.SNARE));
		this.registerDefaultState(this.stateDefinition.any().setValue(CUMT, 0));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(CUMT) == 0) {
				return box(0, 0, 0, 16, 1, 16);
			} else if (state.getValue(CUMT) == 1) {
				return box(0, 0, 0, 16, 2, 16);
			} else if (state.getValue(CUMT) == 2) {
				return box(0, 0, 0, 16, 3, 16);
			} else if (state.getValue(CUMT) == 3) {
				return box(0, 0, 0, 16, 4, 16);
			} else if (state.getValue(CUMT) == 4) {
				return box(0, 0, 0, 16, 5, 16);
			} else if (state.getValue(CUMT) == 5) {
				return box(0, 0, 0, 16, 6, 16);
			} else if (state.getValue(CUMT) == 6) {
				return box(0, 0, 0, 16, 7, 16);
			} else if (state.getValue(CUMT) == 7) {
				return box(0, 0, 0, 16, 8, 16);
			} else if (state.getValue(CUMT) == 8) {
				return box(0, 0, 0, 16, 9, 16);
			} else if (state.getValue(CUMT) == 9) {
				return box(0, 0, 0, 16, 10, 16);
			} else if (state.getValue(CUMT) == 10) {
				return box(0, 0, 0, 16, 11, 16);
			} else if (state.getValue(CUMT) == 11) {
				return box(0, 0, 0, 16, 12, 16);
			} else if (state.getValue(CUMT) == 12) {
				return box(0, 0, 0, 16, 13, 16);
			} else if (state.getValue(CUMT) == 13) {
				return box(0, 0, 0, 16, 14, 16);
			} else if (state.getValue(CUMT) == 14) {
				return box(0, 0, 0, 16, 15, 16);
			}
			return box(0, 0, 0, 16, 16, 16);
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(CUMT);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(CUMT, 0);
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return BelowIsTheSolidBlockBoolReturnProcedure.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, world, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		RadioactiveFalloutFangKuaiBeiWanJiaPoPiShiProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate, entity);
		return retval;
	}

	public static class Item extends BlockItem {
		public Item(Item.Properties properties) {
			super(WealizhModBlocks.RADIOACTIVE_FALLOUT.get(), properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
			Entity entity = itemstack.getEntityRepresentation() != null ? itemstack.getEntityRepresentation() : WealizhMod.clientPlayer();
			String hoverText = ItemsALabelTextProcedure.execute(itemstack);
			if (hoverText != null) {
				for (String line : hoverText.split("\n")) {
					componentConsumer.accept(Component.literal(line));
				}
			}
		}
	}
}