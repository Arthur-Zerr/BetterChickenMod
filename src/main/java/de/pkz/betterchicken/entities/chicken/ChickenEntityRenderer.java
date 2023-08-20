package de.pkz.betterchicken.entities.chicken;

import de.pkz.betterchicken.BetterChicken;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ChickenEntityRenderer extends MobRenderer<ChickenEntity, ChickenEntityModel<ChickenEntity>> {

    private static final ResourceLocation CHICKEN_BROWN_VARIANT = new ResourceLocation(BetterChicken.MODID, "textures/entities/chicken_0.png");
    private static final ResourceLocation CHICKEN_WHITE_VARIANT = new ResourceLocation(BetterChicken.MODID, "textures/entities/chicken_1.png");
    private static final ResourceLocation CHICKEN_WHITE_BROWN_VARIANT = new ResourceLocation(BetterChicken.MODID, "textures/entities/chicken_2.png");


    public ChickenEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ChickenEntityModel<>(ctx.bakeLayer(ChickenEntityModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ChickenEntity entity) {
        if (entity.getVariant() == EChickenVariant.BROWN)
            return CHICKEN_BROWN_VARIANT;
        if (entity.getVariant() == EChickenVariant.WHITE)
            return CHICKEN_WHITE_VARIANT;
        if (entity.getVariant() == EChickenVariant.WHITE_BROWN)
            return CHICKEN_WHITE_BROWN_VARIANT;

        return CHICKEN_BROWN_VARIANT;
    }
}
