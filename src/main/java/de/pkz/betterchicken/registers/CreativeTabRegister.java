package de.pkz.betterchicken.registers;

import de.pkz.betterchicken.BetterChicken;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTabRegister {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterChicken.MODID);

    public static final List<Supplier<? extends ItemLike>> TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> CREATIVE_MODE_TAB_REGISTRY_OBJECT = TABS.register("better_chicken_creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.betterchicken.creative_tab"))
            .icon(ItemRegisters.WOOD_NEST_BLOCK_ITEM.get()::getDefaultInstance)
            .displayItems((displayParameters, output) -> {
                ItemRegisters.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
            })
            .build());

}
