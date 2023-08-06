package de.pkz.betterchicken.events;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.entities.chicken.ChickenEntityModel;
import de.pkz.betterchicken.entities.chicken.ChickenEntityRenderer;
import de.pkz.betterchicken.entities.rooster.RoosterEntityModel;
import de.pkz.betterchicken.entities.rooster.RoosterEntityRenderer;
import de.pkz.betterchicken.registers.EntityRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterChicken.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    
    @SubscribeEvent
    public static void registersRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.ROOSTER_ENTITY.get(), RoosterEntityRenderer::new);
        event.registerEntityRenderer(EntityRegister.CHICKEN_ENTITY.get(), ChickenEntityRenderer::new);

    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RoosterEntityModel.LAYER_LOCATION, RoosterEntityModel::createBodyLayer);
        event.registerLayerDefinition(ChickenEntityModel.LAYER_LOCATION, ChickenEntityModel::createBodyLayer);
    }
}
