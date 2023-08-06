package de.pkz.betterchicken.registers;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.entities.chicken.ChickenEntity;
import de.pkz.betterchicken.entities.rooster.RoosterEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BetterChicken.MODID);

    public static final RegistryObject<EntityType<RoosterEntity>> ROOSTER_ENTITY = ENTITIES.register(RoosterEntity.ENTITY_ID,
            () -> EntityType.Builder.<RoosterEntity>of(RoosterEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f).build(new ResourceLocation(BetterChicken.MODID, RoosterEntity.ENTITY_ID).toString()));

    public static final RegistryObject<EntityType<ChickenEntity>> CHICKEN_ENTITY = ENTITIES.register(ChickenEntity.ENTITY_ID,
            () -> EntityType.Builder.<ChickenEntity>of(ChickenEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f).build(new ResourceLocation(BetterChicken.MODID, ChickenEntity.ENTITY_ID).toString()));

}
