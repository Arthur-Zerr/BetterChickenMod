package de.pkz.betterchicken.entities.chicken;

import de.pkz.betterchicken.BetterChicken;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChickenEntityRenderer extends MobRenderer<ChickenEntity, ChickenEntityModel<ChickenEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BetterChicken.MODID, "textures/entities/chicken_0.png");

    public ChickenEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ChickenEntityModel<>(ctx.bakeLayer(ChickenEntityModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(ChickenEntity entity) {
        return TEXTURE;
    }
}
