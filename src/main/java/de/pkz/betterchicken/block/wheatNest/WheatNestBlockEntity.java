package de.pkz.betterchicken.block.wheatNest;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WheatNestBlockEntity extends BlockEntity {

    private int eggCounter;

    public WheatNestBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.WHEAT_NEST_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag betterChickenData = nbt.getCompound(BetterChicken.MODID);
        this.eggCounter = betterChickenData.getInt("eggCounter");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        var betterChickenData = new CompoundTag();
        betterChickenData.putInt("eggCounter", this.eggCounter);
        nbt.put(BetterChicken.MODID, betterChickenData);
    }

    public int getEggCount() {
        return ++this.eggCounter;
    }
}
