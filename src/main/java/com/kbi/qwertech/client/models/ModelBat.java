package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * ModelBat - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelBat extends ModelBaseTool {
    public ModelRenderer spike1;
    public ModelRenderer spike2;
    public ModelRenderer spike3;
    public ModelRenderer spike4;
    public ModelRenderer bat;
    public ModelRenderer bat_1;
    public ModelRenderer bat_2;
    public ModelRenderer bat_3;
    public ModelRenderer handle;
    public ModelRenderer center;
    public ModelRenderer handle_1;
    public short[] defaultHandleColor = this.handleColor;

    public ModelBat() {
        this.textureWidth = 17;
        this.textureHeight = 18;
        this.spike1 = new ModelRenderer(this, 9, 0);
        this.spike1.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.spike1.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(spike1, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
        this.spike3 = new ModelRenderer(this, 9, 0);
        this.spike3.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.spike3.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(spike3, 0.7853981633974483F, 0.0F, 0.7853981633974483F);
        this.bat = new ModelRenderer(this, 1, 0);
        this.bat.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.bat.addBox(-1.0F, -12.5F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(bat, 0.0F, 0.7853981633974483F, 0.05759586531581287F);
        this.bat_3 = new ModelRenderer(this, 0, 0);
        this.bat_3.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.bat_3.addBox(-1.0F, -12.5F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(bat_3, 0.08726646259971647F, 0.7853981633974483F, 0.05759586531581287F);
        this.bat_2 = new ModelRenderer(this, 1, 0);
        this.bat_2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.bat_2.addBox(-1.0F, -12.5F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(bat_2, -0.08726646259971647F, 0.7853981633974483F, -0.05759586531581287F);
        this.handle_1 = new ModelRenderer(this, 9, 4);
        this.handle_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle_1.addBox(-1.0F, -1.0F, -1.0F, 2, 1, 2, 0.0F);
        this.setRotateAngle(handle_1, 0.0F, 0.7853981633974483F, 0.0F);
        this.handle = new ModelRenderer(this, 0, 12);
        this.handle.setRotationPoint(0.0F, -13.5F, 0.0F);
        this.handle.addBox(-2.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
        this.setRotateAngle(handle, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.bat_1 = new ModelRenderer(this, 0, 0);
        this.bat_1.setRotationPoint(0.0F, -0.9999999999999999F, 0.0F);
        this.bat_1.addBox(-1.0F, -12.5F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(bat_1, 0.0F, 0.7853981633974483F, -0.05759586531581287F);
        this.center = new ModelRenderer(this, 0, 11);
        this.center.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.center.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.spike2 = new ModelRenderer(this, 9, 0);
        this.spike2.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.spike2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(spike2, 0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.spike4 = new ModelRenderer(this, 9, 0);
        this.spike4.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.spike4.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(spike4, 0.7853981633974483F, 0.0F, -0.7853981633974483F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
        this.bat.render(f5);
        this.bat_3.render(f5);
        this.bat_2.render(f5);
        this.handle_1.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.handle.offsetX, this.handle.offsetY, this.handle.offsetZ);
        GL11.glTranslatef(this.handle.rotationPointX * f5, this.handle.rotationPointY * f5, this.handle.rotationPointZ * f5);
        GL11.glScaled(0.75D, 0.5D, 0.75D);
        GL11.glTranslatef(-this.handle.offsetX, -this.handle.offsetY, -this.handle.offsetZ);
        GL11.glTranslatef(-this.handle.rotationPointX * f5, -this.handle.rotationPointY * f5, -this.handle.rotationPointZ * f5);
        this.handle.render(f5);
        GL11.glPopMatrix();
        this.bat_1.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.center.offsetX, this.center.offsetY, this.center.offsetZ);
        GL11.glTranslatef(this.center.rotationPointX * f5, this.center.rotationPointY * f5, this.center.rotationPointZ * f5);
        GL11.glScaled(1.33D, 1.0D, 1.33D);
        GL11.glTranslatef(-this.center.offsetX, -this.center.offsetY, -this.center.offsetZ);
        GL11.glTranslatef(-this.center.rotationPointX * f5, -this.center.rotationPointY * f5, -this.center.rotationPointZ * f5);
        this.center.render(f5);
        GL11.glPopMatrix();
        
        if (this.handleColor != this.defaultHandleColor)
        {
	        applyColorSecondary();
	        GL11.glPushMatrix();
	        GL11.glTranslatef(this.spike1.offsetX, this.spike1.offsetY, this.spike1.offsetZ);
	        GL11.glTranslatef(this.spike1.rotationPointX * f5, this.spike1.rotationPointY * f5, this.spike1.rotationPointZ * f5);
	        GL11.glScaled(2.5D, 0.33D, 0.33D);
	        GL11.glTranslatef(-this.spike1.offsetX, -this.spike1.offsetY, -this.spike1.offsetZ);
	        GL11.glTranslatef(-this.spike1.rotationPointX * f5, -this.spike1.rotationPointY * f5, -this.spike1.rotationPointZ * f5);
	        this.spike1.render(f5);
	        GL11.glPopMatrix();
	        GL11.glPushMatrix();
	        GL11.glTranslatef(this.spike3.offsetX, this.spike3.offsetY, this.spike3.offsetZ);
	        GL11.glTranslatef(this.spike3.rotationPointX * f5, this.spike3.rotationPointY * f5, this.spike3.rotationPointZ * f5);
	        GL11.glScaled(2.5D, 0.33D, 0.33D);
	        GL11.glTranslatef(-this.spike3.offsetX, -this.spike3.offsetY, -this.spike3.offsetZ);
	        GL11.glTranslatef(-this.spike3.rotationPointX * f5, -this.spike3.rotationPointY * f5, -this.spike3.rotationPointZ * f5);
	        this.spike3.render(f5);
	        GL11.glPopMatrix();
	        GL11.glPushMatrix();
	        GL11.glTranslatef(this.spike2.offsetX, this.spike2.offsetY, this.spike2.offsetZ);
	        GL11.glTranslatef(this.spike2.rotationPointX * f5, this.spike2.rotationPointY * f5, this.spike2.rotationPointZ * f5);
	        GL11.glScaled(0.33D, 0.33D, 2.5D);
	        GL11.glTranslatef(-this.spike2.offsetX, -this.spike2.offsetY, -this.spike2.offsetZ);
	        GL11.glTranslatef(-this.spike2.rotationPointX * f5, -this.spike2.rotationPointY * f5, -this.spike2.rotationPointZ * f5);
	        this.spike2.render(f5);
	        GL11.glPopMatrix();
	        GL11.glPushMatrix();
	        GL11.glTranslatef(this.spike4.offsetX, this.spike4.offsetY, this.spike4.offsetZ);
	        GL11.glTranslatef(this.spike4.rotationPointX * f5, this.spike4.rotationPointY * f5, this.spike4.rotationPointZ * f5);
	        GL11.glScaled(0.33D, 0.33D, 2.5D);
	        GL11.glTranslatef(-this.spike4.offsetX, -this.spike4.offsetY, -this.spike4.offsetZ);
	        GL11.glTranslatef(-this.spike4.rotationPointX * f5, -this.spike4.rotationPointY * f5, -this.spike4.rotationPointZ * f5);
	        this.spike4.render(f5);
	        GL11.glPopMatrix();
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
