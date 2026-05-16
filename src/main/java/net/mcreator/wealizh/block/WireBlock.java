package net.mcreator.wealizh.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.wealizh.procedures.WireTickPProcedure;
import net.mcreator.wealizh.procedures.WireConnectionPProcedure;
import net.mcreator.wealizh.block.entity.WireBlockEntity;

import javax.annotation.Nullable;

import java.util.function.Function;

public class WireBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty AC = BooleanProperty.create("ac");
	public static final BooleanProperty BC = BooleanProperty.create("bc");
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public WireBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.COLOR_BLACK).sound(SoundType.WOOL).strength(2f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AC, false).setValue(BC, false).setValue(WATERLOGGED, false));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			if (state.getValue(AC) == true && state.getValue(BC) == false) {
				return switch (state.getValue(FACING)) {
					default -> box(6, 6, 6, 10, 10, 22);
					case NORTH -> box(6, 6, -6, 10, 10, 10);
					case EAST -> box(6, 6, 6, 22, 10, 10);
					case WEST -> box(-6, 6, 6, 10, 10, 10);
					case UP -> box(6, 6, 6, 10, 22, 10);
					case DOWN -> box(6, -6, 6, 10, 10, 10);
				};
			} else if (state.getValue(AC) == false && state.getValue(BC) == true) {
				return switch (state.getValue(FACING)) {
					default -> box(6, 6, -6, 10, 10, 10);
					case NORTH -> box(6, 6, 6, 10, 10, 22);
					case EAST -> box(-6, 6, 6, 10, 10, 10);
					case WEST -> box(6, 6, 6, 22, 10, 10);
					case UP -> box(6, -6, 6, 10, 10, 10);
					case DOWN -> box(6, 6, 6, 10, 22, 10);
				};
			} else if (state.getValue(AC) == true && state.getValue(BC) == true) {
				return switch (state.getValue(FACING)) {
					default -> box(6, 6, -6, 10, 10, 22);
					case NORTH -> box(6, 6, -6, 10, 10, 22);
					case EAST -> box(-6, 6, 6, 22, 10, 10);
					case WEST -> box(-6, 6, 6, 22, 10, 10);
					case UP -> box(6, -6, 6, 10, 22, 10);
					case DOWN -> box(6, -6, 6, 10, 22, 10);
				};
			}
			return switch (state.getValue(FACING)) {
				default -> box(6, 6, 6, 10, 10, 10);
				case NORTH -> box(6, 6, 6, 10, 10, 10);
				case EAST -> box(6, 6, 6, 10, 10, 10);
				case WEST -> box(6, 6, 6, 10, 10, 10);
				case UP -> box(6, 6, 6, 10, 10, 10);
				case DOWN -> box(6, 6, 6, 10, 10, 10);
			};
		}, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state) {
		return propagatesSkylightDown(state) ? 0 : 1;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, AC, BC, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace()).setValue(AC, false).setValue(BC, false).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess scheduledTickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			scheduledTickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, world, scheduledTickAccess, currentPos, facing, facingPos, facingState, random);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
		WireConnectionPProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		WireConnectionPProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		WireTickPProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WireBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}
}