package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorSpring - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorSpring extends ModelBiped {
    public ModelRenderer bipedSpringLeft;
    public ModelRenderer bipedSpringRight;

    public ModelArmorSpring() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.bipedSpringRight = new ModelRenderer(this, 0, 0);
        this.bipedSpringRight.mirror = true;
        this.bipedSpringRight.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedSpringRight.addBox(-1.5F, 12.0F, -1.5F, 3, 5, 3, 0.0F);
        this.bipedSpringLeft = new ModelRenderer(this, 0, 0);
        this.bipedSpringLeft.mirror = true;
        this.bipedSpringLeft.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedSpringLeft.addBox(-1.5F, 12.0F, -1.5F, 3, 5, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        if (!entity.onGround)
        {
        	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        	setRotateAngle(bipedSpringRight, bipedRightLeg.rotateAngleX, bipedRightLeg.rotateAngleY, bipedRightLeg.rotateAngleZ);
        	setRotateAngle(bipedSpringLeft, bipedLeftLeg.rotateAngleX, bipedLeftLeg.rotateAngleY, bipedLeftLeg.rotateAngleZ);
        	this.bipedSpringRight.render(f5);
        	this.bipedSpringLeft.render(f5);
        }
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
