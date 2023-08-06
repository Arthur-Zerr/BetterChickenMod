package de.pkz.betterchicken.entities.chicken;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.entities.chicken.ChickenEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ChickenEntityModel<T extends ChickenEntity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BetterChicken.MODID, ChickenEntity.ENTITY_ID), "main");

    private final ModelParts parts;

    public ChickenEntityModel(ModelPart root) {
        ModelPart body = root.getChild("main");
        ModelPart head = body.getChild("Head");
        ModelPart leftFoot = body.getChild("left_food");
        ModelPart rightFoot = body.getChild("right_food");

        this.parts = new ModelParts(body, head, leftFoot, rightFoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = main.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-0.5F, -5.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 18).addBox(-1.7F, -2.0F, -4.0F, 3.5F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -3.0F));

        PartDefinition left_wing = main.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(14, 14).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -9.0F, 0.0F));

        PartDefinition right_wing = main.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -9.0F, 0.0F));

        PartDefinition bottom = main.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(8, 14).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 4.0F));

        PartDefinition left_food = main.addOrReplaceChild("left_food", CubeListBuilder.create().texOffs(4, 24).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 18).addBox(-0.5F, 3.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 16).addBox(-1.5F, 3.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -4.0F, 0.5F));

        PartDefinition right_food = main.addOrReplaceChild("right_food", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-0.5F, 3.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 14).addBox(-1.5F, 3.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -4.0F, 0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.parts.head().yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.parts.head().xRot = netHeadYaw * Mth.DEG_TO_RAD;

        this.parts.leftFoot().xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.parts.rightFoot().xRot = Mth.cos(limbSwing * 0.6662f + (float) Math.PI) * 1.4f * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.parts.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private record ModelParts(ModelPart body, ModelPart head, ModelPart leftFoot, ModelPart rightFoot) {
    }
}