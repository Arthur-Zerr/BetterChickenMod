package de.pkz.betterchicken.registers;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.block.wheatNest.WheatNestBlock;
import de.pkz.betterchicken.block.woodFeeder.WoodFeederBlock;
import de.pkz.betterchicken.block.woodNest.WoodNestBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegisters {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterChicken.MODID);

    // Items

    public static final RegistryObject<ForgeSpawnEggItem> ROOSTER_SPAWN_EGG = ITEMS.register("rooster_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegister.ROOSTER_ENTITY, 0x292b45, 0x991d2e, new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> CHICKEN_SPAWN_EGG = ITEMS.register("chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegister.CHICKEN_ENTITY, 0xb54b00, 0x991d2e, new Item.Properties()));

    // Blocks
    public static final RegistryObject<BlockItem> WHEAT_NEST_BLOCK_ITEM = ITEMS.register(WheatNestBlock.BLOCK_ID,
            () -> new BlockItem(BlockRegister.WHEAT_NEST_BLOCK.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> WOOD_NEST_BLOCK_ITEM = ITEMS.register(WoodNestBlock.BLOCK_ID,
            () -> new BlockItem(BlockRegister.WOOD_NEST_BLOCK.get(),
                    new Item.Properties()));

    public static final RegistryObject<BlockItem> WOOD_FEEDER_BLOCK_ITEM = ITEMS.register(WoodFeederBlock.BLOCK_ID,
            () -> new BlockItem(BlockRegister.WOOD_FEEDER_BLOCK.get(),
                    new Item.Properties()));

}
