package com.kbi.qwertech.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelUpgradeStation - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelUpgradeStation extends ModelBaseTool {
    public ModelRenderer Boxbase;
    public ModelRenderer HandleLeft;
    public ModelRenderer HandleRight;
    public ModelRenderer HandleLeft2;
    public ModelRenderer PincerLeftFront;
    public ModelRenderer PincerLeftRear;
    public ModelRenderer HandleRight2;
    public ModelRenderer PincerRightFront;
    public ModelRenderer PincerRightRear;

    public ModelUpgradeStation() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.PincerLeftRear = new ModelRenderer(this, 34, 23);
        this.PincerLeftRear.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.PincerLeftRear.addBox(3.0F, -0.5F, -1.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(PincerLeftRear, 0.0F, -0.3490658503988659F, 0.0F);
        this.HandleLeft = new ModelRenderer(this, 0, 20);
        this.HandleLeft.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.HandleLeft.addBox(1.0F, 1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(HandleLeft, 0.0F, 0.0F, -0.4363323129985824F);
        this.HandleRight2 = new ModelRenderer(this, 20, 20);
        this.HandleRight2.mirror = true;
        this.HandleRight2.setRotationPoint(-7.5F, 1.5F, 0.0F);
        this.HandleRight2.addBox(-3.9F, -0.5F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(HandleRight2, 0.0F, 0.0F, 1.7453292519943295F);
        this.Boxbase = new ModelRenderer(this, 0, 0);
        this.Boxbase.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Boxbase.addBox(-8.0F, 0.0F, -8.0F, 16, 4, 16, 0.0F);
        this.HandleLeft2 = new ModelRenderer(this, 20, 20);
        this.HandleLeft2.setRotationPoint(7.5F, 1.5F, 0.0F);
        this.HandleLeft2.addBox(-0.25F, -0.5F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(HandleLeft2, 0.0F, 0.0F, -1.9198621771937625F);
        this.PincerLeftFront = new ModelRenderer(this, 34, 20);
        this.PincerLeftFront.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.PincerLeftFront.addBox(3.0F, -0.5F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(PincerLeftFront, 0.0F, 0.3490658503988659F, 0.0F);
        this.PincerRightFront = new ModelRenderer(this, 34, 23);
        this.PincerRightFront.mirror = true;
        this.PincerRightFront.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.PincerRightFront.addBox(-2.5F, -0.5F, -1.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(PincerRightFront, 0.0F, -0.3490658503988659F, 0.0F);
        this.PincerRightRear = new ModelRenderer(this, 34, 20);
        this.PincerRightRear.mirror = true;
        this.PincerRightRear.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.PincerRightRear.addBox(-2.5F, -0.5F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(PincerRightRear, 0.0F, 0.3490658503988659F, 0.0F);
        this.HandleRight = new ModelRenderer(this, 0, 20);
        this.HandleRight.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.HandleRight.addBox(-9.0F, 1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(HandleRight, 0.0F, 0.0F, 0.5759586531581287F);
        this.HandleLeft2.addChild(this.PincerLeftRear);
        this.HandleRight.addChild(this.HandleRight2);
        this.HandleLeft.addChild(this.HandleLeft2);
        this.HandleLeft2.addChild(this.PincerLeftFront);
        this.HandleRight2.addChild(this.PincerRightFront);
        this.HandleRight2.addChild(this.PincerRightRear);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glColor4f(1, 1, 1, 1);
    	GL11.glScalef(1, 1, 0.5F);
        this.HandleLeft.render(f5);
        this.HandleRight.render(f5);
        GL11.glScalef(1, 1, 2);
        this.applyColorPrimary();
        this.Boxbase.render(f5);
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
