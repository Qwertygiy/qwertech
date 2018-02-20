package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * Screwdriver - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelScrewdriver extends ModelBaseTool {
    public ModelRenderer handle1;
    public ModelRenderer blade;
    public ModelRenderer blade2;
    public ModelRenderer blade3;
    public ModelRenderer handle2;

    public ModelScrewdriver() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.blade2 = new ModelRenderer(this, 8, 9);
        this.blade2.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.blade2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(blade2, 0.0F, 0.0F, 0.7853981633974483F);
        this.handle2 = new ModelRenderer(this, 0, 6);
        this.handle2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle2.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(handle2, 0.0F, 0.7853981633974483F, 0.0F);
        this.blade = new ModelRenderer(this, 8, 0);
        this.blade.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.blade.addBox(-0.5F, -5.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(blade, 0.0F, 0.7853981633974483F, 0.0F);
        this.blade3 = new ModelRenderer(this, 8, 9);
        this.blade3.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.blade3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(blade3, -0.7853981633974483F, 0.0F, 0.0F);
        this.handle1 = new ModelRenderer(this, 0, 0);
        this.handle1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle1.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.handle1.addChild(this.handle2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.blade2.offsetX, this.blade2.offsetY, this.blade2.offsetZ);
        GL11.glTranslatef(this.blade2.rotationPointX * f5, this.blade2.rotationPointY * f5, this.blade2.rotationPointZ * f5);
        GL11.glScaled(0.8D, 3.0D, 0.25D);
        GL11.glTranslatef(-this.blade2.offsetX, -this.blade2.offsetY, -this.blade2.offsetZ);
        GL11.glTranslatef(-this.blade2.rotationPointX * f5, -this.blade2.rotationPointY * f5, -this.blade2.rotationPointZ * f5);
        this.blade2.render(f5);
        GL11.glPopMatrix();
        this.blade.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade3.offsetX, this.blade3.offsetY, this.blade3.offsetZ);
        GL11.glTranslatef(this.blade3.rotationPointX * f5, this.blade3.rotationPointY * f5, this.blade3.rotationPointZ * f5);
        GL11.glScaled(0.25D, 3.0D, 0.8D);
        GL11.glTranslatef(-this.blade3.offsetX, -this.blade3.offsetY, -this.blade3.offsetZ);
        GL11.glTranslatef(-this.blade3.rotationPointX * f5, -this.blade3.rotationPointY * f5, -this.blade3.rotationPointZ * f5);
        this.blade3.render(f5);
        GL11.glPopMatrix();
        applyColorSecondary();
        this.handle1.render(f5);
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
