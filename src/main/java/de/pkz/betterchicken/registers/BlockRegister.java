package de.pkz.betterchicken.registers;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.block.wheatNest.WheatNestBlock;
import de.pkz.betterchicken.block.woodFeeder.WoodFeederBlock;
import de.pkz.betterchicken.block.woodNest.WoodNestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterChicken.MODID);

    public static final RegistryObject<WheatNestBlock> WHEAT_NEST_BLOCK = BLOCKS.register(WheatNestBlock.BLOCK_ID,
            () -> new WheatNestBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(5.f, 17.f)));

    public static final RegistryObject<WoodNestBlock> WOOD_NEST_BLOCK = BLOCKS.register(WoodNestBlock.BLOCK_ID,
            () -> new WoodNestBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(5.f, 17.f)));

    public static final RegistryObject<WoodFeederBlock> WOOD_FEEDER_BLOCK = BLOCKS.register(WoodFeederBlock.BLOCK_ID,
            () -> new WoodFeederBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(5.f, 17.f)));
}
