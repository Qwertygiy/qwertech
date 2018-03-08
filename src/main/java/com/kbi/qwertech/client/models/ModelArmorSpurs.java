package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorSpurs - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorSpurs extends ModelBiped {
    public ModelRenderer bipedSpikeLeft;
    public ModelRenderer bipedSpikeRight;
    public ModelRenderer bipedSpikeLeft_1;
    public ModelRenderer bipedSpikeRight_1;

    public ModelArmorSpurs() {
        this.textureWidth = 8;
        this.textureHeight = 8;
        this.bipedSpikeRight = new ModelRenderer(this, 0, 0);
        this.bipedSpikeRight.mirror = true;
        this.bipedSpikeRight.setRotationPoint(-2.4F, 12.0F, 0.0F);
        this.bipedSpikeRight.addBox(-3.0F, 9.0F, 0.5F, 1, 1, 3, 0.0F);
        this.bipedSpikeRight_1 = new ModelRenderer(this, 0, 0);
        this.bipedSpikeRight_1.mirror = true;
        this.bipedSpikeRight_1.setRotationPoint(-2.5F, 9.5F, 2.0F);
        this.bipedSpikeRight_1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(bipedSpikeRight_1, 1.5707963267948966F, 0.0F, 0.0F);
        this.bipedSpikeLeft = new ModelRenderer(this, 0, 0);
        this.bipedSpikeLeft.mirror = true;
        this.bipedSpikeLeft.setRotationPoint(2.4F, 12.0F, 0.0F);
        this.bipedSpikeLeft.addBox(2.0F, 9.0F, 0.5F, 1, 1, 3, 0.0F);
        this.bipedSpikeLeft_1 = new ModelRenderer(this, 0, 0);
        this.bipedSpikeLeft_1.mirror = true;
        this.bipedSpikeLeft_1.setRotationPoint(2.5F, 9.5F, 2.0F);
        this.bipedSpikeLeft_1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(bipedSpikeLeft_1, 1.5707963267948966F, 0.0F, 0.0F);
        this.bipedSpikeRight.addChild(this.bipedSpikeRight_1);
        this.bipedSpikeLeft.addChild(this.bipedSpikeLeft_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	setRotateAngle(bipedSpikeRight, bipedRightLeg.rotateAngleX, bipedRightLeg.rotateAngleY, bipedRightLeg.rotateAngleZ);
    	setRotateAngle(bipedSpikeLeft, bipedLeftLeg.rotateAngleX, bipedLeftLeg.rotateAngleY, bipedLeftLeg.rotateAngleZ);
        this.bipedSpikeRight.render(f5);
        this.bipedSpikeLeft.render(f5);
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
