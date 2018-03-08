package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorFeather - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorFeather extends ModelBiped {
    public ModelRenderer bipedFeathermount;
    public ModelRenderer bipedFeatherStem;
    public ModelRenderer bipedFeatherStem2;
    public ModelRenderer bipedFeatherStem3;
    public ModelRenderer bipedFeather2;
    public ModelRenderer bipedFeather1;
    public ModelRenderer bipedFeatherStem4;
    public ModelRenderer bipedFeather3;
    public ModelRenderer bipedFeather4;

    public ModelArmorFeather() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.bipedFeatherStem4 = new ModelRenderer(this, 11, 7);
        this.bipedFeatherStem4.setRotationPoint(0.0F, -2.0F, 1.1F);
        this.bipedFeatherStem4.addBox(0.0F, 0.1F, 0.4F, 1, 2, 1, 0.0F);
        this.setRotateAngle(bipedFeatherStem4, -0.175F, 0.0F, 0.0F);
        this.bipedFeatherStem2 = new ModelRenderer(this, 4, 4);
        this.bipedFeatherStem2.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.bipedFeatherStem2.addBox(-0.5F, -1.8F, -0.45F, 1, 2, 1, 0.0F);
        this.setRotateAngle(bipedFeatherStem2, -0.35F, 0.0F, 0.0F);
        this.bipedFeathermount = new ModelRenderer(this, 0, 0);
        this.bipedFeathermount.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedFeathermount.addBox(-1.5F, -9.0F, -1.5F, 3, 1, 3, 0.0F);
        this.bipedFeather2 = new ModelRenderer(this, 0, 11);
        this.bipedFeather2.setRotationPoint(-0.5F, -3.0F, -0.45F);
        this.bipedFeather2.addBox(0.0F, 0.25F, 0.8F, 1, 1, 4, 0.0F);
        this.setRotateAngle(bipedFeather2, 0.35F, 0.0F, 0.0F);
        this.bipedFeather3 = new ModelRenderer(this, 0, 7);
        this.bipedFeather3.setRotationPoint(0.0F, 0.0F, 1.1F);
        this.bipedFeather3.addBox(0.0F, 0.66F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(bipedFeather3, 0.611F, 0.0F, 0.0F);
        this.bipedFeather1 = new ModelRenderer(this, 6, 11);
        this.bipedFeather1.setRotationPoint(-0.5F, -1.5F, -0.45F);
        this.bipedFeather1.addBox(0.0F, 0.5F, 0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(bipedFeather1, 0.35F, 0.0F, 0.0F);
        this.bipedFeatherStem3 = new ModelRenderer(this, 12, 2);
        this.bipedFeatherStem3.setRotationPoint(-0.5F, -6.0F, -0.45F);
        this.bipedFeatherStem3.addBox(0.0F, 0.1F, 1.1F, 1, 4, 1, 0.0F);
        this.setRotateAngle(bipedFeatherStem3, -0.262F, 0.0F, 0.0F);
        this.bipedFeather4 = new ModelRenderer(this, 6, 7);
        this.bipedFeather4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedFeather4.addBox(0.0F, 0.66F, 0.33F, 1, 1, 3, 0.0F);
        this.setRotateAngle(bipedFeather4, 0.785F, 0.0F, 0.0F);
        this.bipedFeatherStem = new ModelRenderer(this, 0, 4);
        this.bipedFeatherStem.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedFeatherStem.addBox(-0.5F, -11.0F, -0.5F, 1, 2, 1, 0.0F);
        this.bipedFeatherStem3.addChild(this.bipedFeatherStem4);
        this.bipedFeatherStem.addChild(this.bipedFeatherStem2);
        this.bipedFeatherStem2.addChild(this.bipedFeather2);
        this.bipedFeatherStem3.addChild(this.bipedFeather3);
        this.bipedFeatherStem2.addChild(this.bipedFeather1);
        this.bipedFeatherStem2.addChild(this.bipedFeatherStem3);
        this.bipedFeatherStem4.addChild(this.bipedFeather4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        setRotateAngle(bipedFeathermount, bipedHead.rotateAngleX, bipedHead.rotateAngleY, bipedHead.rotateAngleZ);
        setRotateAngle(bipedFeatherStem, bipedHead.rotateAngleX, bipedHead.rotateAngleY, bipedHead.rotateAngleZ);
        bipedFeatherStem2.rotateAngleX = -0.35F + (float)entity.motionY/10;
        bipedFeatherStem3.rotateAngleX = -0.262F + (float)entity.motionY/15;
        bipedFeatherStem4.rotateAngleX = -0.175F + (float)entity.motionY/20;
    	this.bipedFeathermount.render(f5);
        this.bipedFeatherStem.render(f5);
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
