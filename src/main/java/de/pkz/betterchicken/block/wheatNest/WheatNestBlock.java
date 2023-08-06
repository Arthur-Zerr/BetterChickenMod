package de.pkz.betterchicken.block.wheatNest;

import de.pkz.betterchicken.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WheatNestBlock extends Block implements EntityBlock {
    public static final String BLOCK_ID = "wheat_nest";
    public static final IntegerProperty EGG_AMOUNT = IntegerProperty.create("egg_amount", 0, 3);

    public WheatNestBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(EGG_AMOUNT, 0));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegister.WHEAT_NEST_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EGG_AMOUNT);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof WheatNestBlockEntity blockEntity) {
                int counter = blockEntity.getEggCount();
                player.sendSystemMessage(Component.literal("Wheat Nest has %d eggs.".formatted(counter)));
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return super.use(state, level, pos, player, hand, hitResult);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return makeShape();
    }

    public static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.125, 0.75, 0.1875, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.1875, 0.25, 0.1875, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.8125, 0.75, 0.1875, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.25, 0.875, 0.1875, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.25, 0.1875, 0.1875, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.75, 0.25, 0.1875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.0625, 0.75, 0.8125, 0.1875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.0625, 0.1875, 0.8125, 0.1875, 0.25), BooleanOp.OR);

        return shape;
    }
}
