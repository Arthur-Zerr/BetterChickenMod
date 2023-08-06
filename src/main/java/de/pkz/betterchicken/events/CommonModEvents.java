package de.pkz.betterchicken.events;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.entities.chicken.ChickenEntity;
import de.pkz.betterchicken.entities.rooster.RoosterEntity;
import de.pkz.betterchicken.registers.EntityRegister;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterChicken.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegister.ROOSTER_ENTITY.get(), RoosterEntity.createAttributes().build());
        event.put(EntityRegister.CHICKEN_ENTITY.get(), RoosterEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(EntityRegister.ROOSTER_ENTITY.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                RoosterEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);

        event.register(EntityRegister.CHICKEN_ENTITY.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                ChickenEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
    }
}
