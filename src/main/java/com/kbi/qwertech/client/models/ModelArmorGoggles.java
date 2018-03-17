package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorGoggles - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorGoggles extends ModelBiped {
    public ModelRenderer bipedLeftGlass;
    public ModelRenderer bipedRightGlass;
    public ModelRenderer bipedGlassBridge;

    public ModelArmorGoggles() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.bipedLeftGlass = new ModelRenderer(this, 0, 0);
        this.bipedLeftGlass.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftGlass.addBox(-5.5F, -6.5F, -4.75F, 6, 5, 1, -1.25F);
        this.bipedRightGlass = new ModelRenderer(this, 0, 6);
        this.bipedRightGlass.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedRightGlass.addBox(-0.5F, -6.5F, -4.75F, 6, 5, 1, -1.25F);
        this.bipedGlassBridge = new ModelRenderer(this, 0, 12);
        this.bipedGlassBridge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedGlassBridge.addBox(-2.0F, -5.5F, -4.85F, 4, 3, 1, -1.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        setRotateAngle(this.bipedLeftGlass, this.bipedHead.rotateAngleX, this.bipedHead.rotateAngleY, this.bipedHead.rotateAngleZ);
        setRotateAngle(this.bipedRightGlass, this.bipedHead.rotateAngleX, this.bipedHead.rotateAngleY, this.bipedHead.rotateAngleZ);
        setRotateAngle(this.bipedGlassBridge, this.bipedHead.rotateAngleX, this.bipedHead.rotateAngleY, this.bipedHead.rotateAngleZ);
        this.bipedLeftGlass.render(f5);
        this.bipedRightGlass.render(f5);
        this.bipedGlassBridge.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
