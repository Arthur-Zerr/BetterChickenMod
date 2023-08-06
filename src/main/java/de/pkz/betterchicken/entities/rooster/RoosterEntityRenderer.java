package de.pkz.betterchicken.entities.rooster;

import de.pkz.betterchicken.BetterChicken;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RoosterEntityRenderer extends MobRenderer<RoosterEntity, RoosterEntityModel<RoosterEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BetterChicken.MODID, "textures/rooster.png");

    public RoosterEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RoosterEntityModel<>(ctx.bakeLayer(RoosterEntityModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(RoosterEntity entity) {
        return TEXTURE;
    }
}
