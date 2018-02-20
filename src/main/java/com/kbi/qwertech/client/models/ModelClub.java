package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelClub - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelClub extends ModelBaseTool {
    public ModelRenderer handle;
    public ModelRenderer smasher;
    public ModelRenderer smasherZ;
    public ModelRenderer smasherX;
    public ModelRenderer smasherY;
    public ModelRenderer smasherX2;
    public ModelRenderer smasherZ2;

    public ModelClub() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.smasherY = new ModelRenderer(this, 2, 12);
        this.smasherY.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.smasherY.addBox(-1.5F, -10.0F, -1.5F, 3, 17, 3, 0.0F);
        this.smasherX2 = new ModelRenderer(this, 0, 19);
        this.smasherX2.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.smasherX2.addBox(-1.0F, -4.0F, -3.5F, 2, 3, 7, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
        this.handle.addBox(-1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.0F, -1.9198621771937625F);
        this.smasherZ2 = new ModelRenderer(this, 0, 23);
        this.smasherZ2.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.smasherZ2.addBox(-3.5F, -4.0F, -1.0F, 7, 3, 2, 0.0F);
        this.smasherX = new ModelRenderer(this, 0, 12);
        this.smasherX.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.smasherX.addBox(-1.5F, -6.5F, -3.0F, 3, 8, 6, 0.0F);
        this.smasher = new ModelRenderer(this, 8, 0);
        this.smasher.setRotationPoint(3.3F, 8.7F, 0.0F);
        this.smasher.addBox(-2.5F, -9.0F, -2.5F, 5, 13, 5, 0.0F);
        this.setRotateAngle(smasher, 0.0F, 0.0F, -1.9198621771937625F);
        this.smasherZ = new ModelRenderer(this, 0, 15);
        this.smasherZ.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.smasherZ.addBox(-3.0F, -6.5F, -1.5F, 6, 8, 3, 0.0F);
        this.smasher.addChild(this.smasherY);
        this.smasher.addChild(this.smasherX2);
        this.smasher.addChild(this.smasherZ2);
        this.smasher.addChild(this.smasherX);
        this.smasher.addChild(this.smasherZ);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorSecondary();
    	this.handle.render(f5);
    	applyColorPrimary();
        this.smasher.render(f5);
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
