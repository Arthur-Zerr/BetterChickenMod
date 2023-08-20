package de.pkz.betterchicken.block.woodFeeder;

import de.pkz.betterchicken.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodFeederBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final String BLOCK_ID = "wood_feeder";

    public static final BooleanProperty HAS_FOOD = BooleanProperty.create("has_food");

    public WoodFeederBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HAS_FOOD, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegister.WOOD_FEEDER_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(HAS_FOOD);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH)
            shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.1875, 0.9375, 0.1875, 0.8125), BooleanOp.OR);
        else
            shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.0625, 0.8125, 0.1875, 0.9375), BooleanOp.OR);
        return shape;
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide)
            return InteractionResult.FAIL;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof WoodFeederBlockEntity blockEntity))
            return InteractionResult.FAIL;

        ItemStackHandler inventory = blockEntity.getInventory();
        ItemStack stack = player.getItemInHand(hand);

        if (stack.isEmpty()) {
            if (inventory.getStackInSlot(0).isEmpty())
                return InteractionResult.SUCCESS;

            ItemStack extractedItem = inventory.extractItem(0, player.isCrouching() ? inventory.getSlotLimit(0) : 1, false);
            player.addItem(extractedItem);
        } else {
            ItemStack toInsert = stack.copy();
            inventory.insertItem(0, toInsert, false);
            player.setItemInHand(hand, ItemStack.EMPTY);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (level.isClientSide)
            return;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof WoodFeederBlockEntity blockEntity))
            return;

        ItemStackHandler inventory = blockEntity.getInventory();
        for (int index = 0; index < inventory.getSlots(); index++) {
            var entity = new ItemEntity(level, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, inventory.getStackInSlot(index));
            level.addFreshEntity(entity);
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
