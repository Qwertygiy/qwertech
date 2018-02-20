package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * ModelPick - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelPick extends ModelBaseTool {
    public ModelRenderer handle;
    public ModelRenderer topmetal;
    public ModelRenderer mainBar;
    public ModelRenderer front;
    public ModelRenderer back;
    public ModelRenderer front_1;
    public ModelRenderer back_1;

    public ModelPick() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.topmetal = new ModelRenderer(this, 8, 0);
        this.topmetal.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.topmetal.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.addBox(-1.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.7853981633974483F, 0.0F);
        this.back = new ModelRenderer(this, 16, 10);
        this.back.setRotationPoint(-5.0F, -11.7F, 0.0F);
        this.back.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(back, 0.0F, 0.0F, -1.3613568165555772F);
        this.front_1 = new ModelRenderer(this, 8, 10);
        this.front_1.setRotationPoint(2.7F, 0.0F, 0.0F);
        this.front_1.addBox(-0.3F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(front_1, 0.0F, 0.0F, 0.5759586531581287F);
        this.mainBar = new ModelRenderer(this, 8, 6);
        this.mainBar.setRotationPoint(0.0F, -13.2F, 0.0F);
        this.mainBar.addBox(-3.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.front = new ModelRenderer(this, 16, 10);
        this.front.setRotationPoint(5.0F, -11.7F, 0.0F);
        this.front.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(front, 0.0F, 0.0F, 1.3613568165555772F);
        this.back_1 = new ModelRenderer(this, 8, 10);
        this.back_1.mirror = true;
        this.back_1.setRotationPoint(-2.7F, 0.0F, 0.0F);
        this.back_1.addBox(-2.7F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(back_1, 0.0F, 0.0F, -0.5759586531581287F);
        this.mainBar.addChild(this.front_1);
        this.mainBar.addChild(this.back_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorSecondary();
    	this.handle.render(f5);
    	applyColorPrimary();
    	this.topmetal.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.back.offsetX, this.back.offsetY, this.back.offsetZ);
        GL11.glTranslatef(this.back.rotationPointX * f5, this.back.rotationPointY * f5, this.back.rotationPointZ * f5);
        GL11.glScaled(0.75D, 0.75D, 1.0D);
        GL11.glTranslatef(-this.back.offsetX, -this.back.offsetY, -this.back.offsetZ);
        GL11.glTranslatef(-this.back.rotationPointX * f5, -this.back.rotationPointY * f5, -this.back.rotationPointZ * f5);
        this.back.render(f5);
        GL11.glPopMatrix();
        this.mainBar.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.front.offsetX, this.front.offsetY, this.front.offsetZ);
        GL11.glTranslatef(this.front.rotationPointX * f5, this.front.rotationPointY * f5, this.front.rotationPointZ * f5);
        GL11.glScaled(0.75D, 0.75D, 1.0D);
        GL11.glTranslatef(-this.front.offsetX, -this.front.offsetY, -this.front.offsetZ);
        GL11.glTranslatef(-this.front.rotationPointX * f5, -this.front.rotationPointY * f5, -this.front.rotationPointZ * f5);
        this.front.render(f5);
        GL11.glPopMatrix();
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
