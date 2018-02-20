package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * Shuriken - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelShuriken extends ModelBaseTool {
    public ModelRenderer vertical;
    public ModelRenderer horizontal;

    public ModelShuriken() {
        this.textureWidth = 8;
        this.textureHeight = 8;
        this.vertical = new ModelRenderer(this, 0, 0);
        this.vertical.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vertical.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(vertical, 0.0F, 0.0F, 0.7853981633974483F);
        this.horizontal = new ModelRenderer(this, 0, 4);
        this.horizontal.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.horizontal.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(horizontal, 0.0F, 0.0F, 0.7853981633974483F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.vertical.offsetX, this.vertical.offsetY, this.vertical.offsetZ);
        GL11.glTranslatef(this.vertical.rotationPointX * f5, this.vertical.rotationPointY * f5, this.vertical.rotationPointZ * f5);
        GL11.glScaled(0.5D, 2.5D, 0.26D);
        GL11.glTranslatef(-this.vertical.offsetX, -this.vertical.offsetY, -this.vertical.offsetZ);
        GL11.glTranslatef(-this.vertical.rotationPointX * f5, -this.vertical.rotationPointY * f5, -this.vertical.rotationPointZ * f5);
        this.vertical.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.horizontal.offsetX, this.horizontal.offsetY, this.horizontal.offsetZ);
        GL11.glTranslatef(this.horizontal.rotationPointX * f5, this.horizontal.rotationPointY * f5, this.horizontal.rotationPointZ * f5);
        GL11.glScaled(2.5D, 0.5D, 0.25D);
        GL11.glTranslatef(-this.horizontal.offsetX, -this.horizontal.offsetY, -this.horizontal.offsetZ);
        GL11.glTranslatef(-this.horizontal.rotationPointX * f5, -this.horizontal.rotationPointY * f5, -this.horizontal.rotationPointZ * f5);
        this.horizontal.render(f5);
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
