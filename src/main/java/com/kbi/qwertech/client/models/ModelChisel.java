package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelScrewdriver - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelChisel extends ModelBaseTool {
    public ModelRenderer handle1;
    public ModelRenderer blade;
    public ModelRenderer flat;
    public ModelRenderer blade_1;
    public ModelRenderer rotated;
    public ModelRenderer handle1Child;

    public ModelChisel() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.rotated = new ModelRenderer(this, 8, 6);
        this.rotated.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.rotated.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(rotated, 0.7853981633974483F, 0.0F, 0.0F);
        this.flat = new ModelRenderer(this, 8, 10);
        this.flat.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.flat.addBox(-0.5F, -2.0F, -1.5F, 1, 2, 3, 0.0F);
        this.handle1 = new ModelRenderer(this, 0, 0);
        this.handle1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle1.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.blade_1 = new ModelRenderer(this, 0, 12);
        this.blade_1.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.blade_1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(blade_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.handle1Child = new ModelRenderer(this, 0, 6);
        this.handle1Child.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle1Child.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(handle1Child, 0.0F, 0.7853981852531433F, 0.0F);
        this.blade = new ModelRenderer(this, 8, 0);
        this.blade.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.blade.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.handle1.addChild(this.handle1Child);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorSecondary();
    	this.handle1.render(f5);
    	applyColorPrimary();
        this.rotated.render(f5);
        this.flat.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade_1.offsetX, this.blade_1.offsetY, this.blade_1.offsetZ);
        GL11.glTranslatef(this.blade_1.rotationPointX * f5, this.blade_1.rotationPointY * f5, this.blade_1.rotationPointZ * f5);
        GL11.glScaled(0.7D, 2.0D, 1.0D);
        GL11.glTranslatef(-this.blade_1.offsetX, -this.blade_1.offsetY, -this.blade_1.offsetZ);
        GL11.glTranslatef(-this.blade_1.rotationPointX * f5, -this.blade_1.rotationPointY * f5, -this.blade_1.rotationPointZ * f5);
        this.blade_1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade.offsetX, this.blade.offsetY, this.blade.offsetZ);
        GL11.glTranslatef(this.blade.rotationPointX * f5, this.blade.rotationPointY * f5, this.blade.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.0D, 1.3D);
        GL11.glTranslatef(-this.blade.offsetX, -this.blade.offsetY, -this.blade.offsetZ);
        GL11.glTranslatef(-this.blade.rotationPointX * f5, -this.blade.rotationPointY * f5, -this.blade.rotationPointZ * f5);
        this.blade.render(f5);
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
