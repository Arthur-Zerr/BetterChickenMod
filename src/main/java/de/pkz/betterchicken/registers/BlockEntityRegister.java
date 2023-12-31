package de.pkz.betterchicken.registers;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.block.wheatNest.WheatNestBlock;
import de.pkz.betterchicken.block.wheatNest.WheatNestBlockEntity;
import de.pkz.betterchicken.block.woodFeeder.WoodFeederBlock;
import de.pkz.betterchicken.block.woodFeeder.WoodFeederBlockEntity;
import de.pkz.betterchicken.block.woodNest.WoodNestBlock;
import de.pkz.betterchicken.block.woodNest.WoodNestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterChicken.MODID);

    public static final RegistryObject<BlockEntityType<WheatNestBlockEntity>> WHEAT_NEST_BLOCK_ENTITY = BLOCK_ENTITIES.register(WheatNestBlock.BLOCK_ID,
            () -> BlockEntityType.Builder.of(WheatNestBlockEntity::new, BlockRegister.WHEAT_NEST_BLOCK.get())
                    .build(null));

    public static final RegistryObject<BlockEntityType<WoodNestBlockEntity>> WOOD_NEST_BLOCK_ENTITY = BLOCK_ENTITIES.register("wood_nest",
            () -> BlockEntityType.Builder.of(WoodNestBlockEntity::new, BlockRegister.WOOD_NEST_BLOCK.get())
                    .build(null));

    public static final RegistryObject<BlockEntityType<WoodFeederBlockEntity>> WOOD_FEEDER_BLOCK_ENTITY = BLOCK_ENTITIES.register(WoodFeederBlock.BLOCK_ID,
            () -> BlockEntityType.Builder.of(WoodFeederBlockEntity::new, BlockRegister.WOOD_FEEDER_BLOCK.get())
                    .build(null));
}
