package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorMonocle - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorMonocle extends ModelBiped {
    public ModelRenderer bipedMonocle;
    public ModelRenderer bipedMonocle2;

    public ModelArmorMonocle() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.bipedMonocle = new ModelRenderer(this, 0, 0);
        this.bipedMonocle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedMonocle.addBox(-5F, -6.0F, -4.75F, 6, 5, 1, -1.25F);
        this.bipedMonocle2 = new ModelRenderer(this, 0, 6);
        this.bipedMonocle2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedMonocle2.addBox(-4.5F, -6.5F, -4.75F, 5, 6, 1, -1.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	setRotateAngle(this.bipedMonocle, this.bipedHead.rotateAngleX, this.bipedHead.rotateAngleY, this.bipedHead.rotateAngleZ);
    	setRotateAngle(this.bipedMonocle2, this.bipedHead.rotateAngleX, this.bipedHead.rotateAngleY, this.bipedHead.rotateAngleZ);
    	this.bipedMonocle.render(f5);
    	this.bipedMonocle2.render(f5);
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
