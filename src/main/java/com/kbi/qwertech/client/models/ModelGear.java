package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGear - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelGear extends ModelBaseTool {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;

    public ModelGear() {
        this.textureWidth = 16;
        this.textureHeight = 8;
        this.shape1_5 = new ModelRenderer(this, 0, 0);
        this.shape1_5.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.shape1_5.addBox(-1.0F, -1.0F, -0.5F, 6, 2, 1, 0.0F);
        this.setRotateAngle(shape1_5, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1_6 = new ModelRenderer(this, 0, 0);
        this.shape1_6.setRotationPoint(-2.0F, -3.0F, 0.0F);
        this.shape1_6.addBox(-1.0F, -1.0F, -0.5F, 6, 2, 1, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 0, 0);
        this.shape1_7.setRotationPoint(-2.0F, 1.0F, 0.0F);
        this.shape1_7.addBox(-1.0F, -1.0F, -0.5F, 6, 2, 1, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 0);
        this.shape1_4.setRotationPoint(3.0F, -1.0F, 0.0F);
        this.shape1_4.addBox(-1.0F, -1.0F, -0.5F, 2, 6, 1, 0.0F);
        this.setRotateAngle(shape1_4, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.shape1.addBox(-1.0F, -1.0F, -0.5F, 2, 6, 1, 0.0F);
        this.setRotateAngle(shape1, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1_3 = new ModelRenderer(this, 0, 0);
        this.shape1_3.setRotationPoint(-2.0F, -3.0F, 0.0F);
        this.shape1_3.addBox(-1.0F, -1.0F, -0.5F, 2, 6, 1, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(-3.0F, -1.0F, 0.0F);
        this.shape1_1.addBox(-1.0F, -1.0F, -0.5F, 6, 2, 1, 0.0F);
        this.setRotateAngle(shape1_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape1_2 = new ModelRenderer(this, 0, 0);
        this.shape1_2.setRotationPoint(2.0F, -3.0F, 0.0F);
        this.shape1_2.addBox(-1.0F, -1.0F, -0.5F, 2, 6, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	this.shape1_5.render(f5);
        this.shape1_6.render(f5);
        this.shape1_7.render(f5);
        this.shape1_4.render(f5);
        this.shape1.render(f5);
        this.shape1_3.render(f5);
        this.shape1_1.render(f5);
        this.shape1_2.render(f5);
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
