package de.pkz.betterchicken.block.woodFeeder;

import de.pkz.betterchicken.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WoodFeederBlockEntity extends BlockEntity {

    public WoodFeederBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.WOOD_FEEDER_BLOCK_ENTITY.get(), pos, state);
    }

}
