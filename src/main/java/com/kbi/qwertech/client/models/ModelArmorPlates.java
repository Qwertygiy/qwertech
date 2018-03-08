package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorPlates - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorPlates extends ModelBiped {
    public ModelRenderer bipedHeadPlate;
    public ModelRenderer bipedBodyPlate;
    public ModelRenderer bipedLegPlate;
    public ModelRenderer bipedRightBootPlate;
    public ModelRenderer bipedLeftBootPlate;

    public ModelArmorPlates() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.bipedBodyPlate = new ModelRenderer(this, 0, 4);
        this.bipedBodyPlate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBodyPlate.addBox(-3.0F, 1.5F, -3.0F, 6, 7, 1, 0.0F);
        this.bipedRightBootPlate = new ModelRenderer(this, 14, 0);
        this.bipedRightBootPlate.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightBootPlate.addBox(-1.5F, 7.0F, -3.5F, 3, 2, 1, 0.0F);
        this.bipedLeftBootPlate = new ModelRenderer(this, 14, 0);
        this.bipedLeftBootPlate.mirror = true;
        this.bipedLeftBootPlate.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftBootPlate.addBox(-1.5F, 7.0F, -3.5F, 3, 2, 1, 0.0F);
        this.bipedHeadPlate = new ModelRenderer(this, 0, 0);
        this.bipedHeadPlate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadPlate.addBox(-3.0F, -7.5F, -5.0F, 6, 3, 1, 0.0F);
        this.bipedLegPlate = new ModelRenderer(this, 0, 12);
        this.bipedLegPlate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLegPlate.addBox(-3.0F, 8.5F, -3.5F, 6, 3, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	
    	setRotateAngle(bipedLeftBootPlate, bipedLeftLeg.rotateAngleX, bipedLeftLeg.rotateAngleY, bipedLeftLeg.rotateAngleZ);
    	setRotateAngle(bipedRightBootPlate, bipedRightLeg.rotateAngleX, bipedRightLeg.rotateAngleY, bipedRightLeg.rotateAngleZ);
    	setRotateAngle(bipedBodyPlate, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);
    	setRotateAngle(bipedLegPlate, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);
    	setRotateAngle(bipedHeadPlate, bipedHead.rotateAngleX, bipedHead.rotateAngleY, bipedHead.rotateAngleZ);
    	
    	this.bipedBodyPlate.render(f5);
        this.bipedRightBootPlate.render(f5);
        this.bipedLeftBootPlate.render(f5);
        this.bipedHeadPlate.render(f5);
        this.bipedLegPlate.render(f5);
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
