package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelKnife - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelKnife extends ModelBaseTool {
	
    public double[] modelScale = new double[] { 0.8D, 0.8D, 0.8D };
    public ModelRenderer shape28;
    public ModelRenderer blade;
    public ModelRenderer shape28_1;
    public ModelRenderer shape28_2;
    public ModelRenderer handle;
    public ModelRenderer blade_1;
    public ModelRenderer blade_2;
    public ModelRenderer blade_3;
    public ModelRenderer handle_1;
    public ModelRenderer handle_2;

    public ModelKnife() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.handle_1 = new ModelRenderer(this, 0, 0);
        this.handle_1.setRotationPoint(-1.45F, 1.0F, 0.0F);
        this.handle_1.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(handle_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.blade_2 = new ModelRenderer(this, 0, 7);
        this.blade_2.setRotationPoint(3.3F, 14.6F, 0.0F);
        this.blade_2.addBox(-0.5F, 0.0F, -0.5F, 2, 7, 2, 0.0F);
        this.setRotateAngle(blade_2, 0.0F, 0.7853981633974483F, -1.8500490071139892F);
        this.blade = new ModelRenderer(this, 8, 0);
        this.blade.setRotationPoint(5.0F, 12.05F, 0.0F);
        this.blade.addBox(0.4F, -4.3F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(blade, 0.0F, 0.0F, -2.0943951023931953F);
        this.shape28_2 = new ModelRenderer(this, 8, 9);
        this.shape28_2.setRotationPoint(2.6F, 13.8F, -0.1F);
        this.shape28_2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(shape28_2, 0.7853981633974483F, -0.3490658503988659F, 0.2792526803190927F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
        this.handle.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.0F, -2.0943951023931953F);
        this.shape28 = new ModelRenderer(this, 8, 9);
        this.shape28.setRotationPoint(2.6F, 13.8F, -0.0F);
        this.shape28.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(shape28, 0.7853981633974483F, 0.0F, 0.2792526803190927F);
        this.shape28_1 = new ModelRenderer(this, 8, 9);
        this.shape28_1.setRotationPoint(2.6F, 13.8F, 0.1F);
        this.shape28_1.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(shape28_1, 0.7853981633974483F, 0.3490658503988659F, 0.2792526803190927F);
        this.blade_3 = new ModelRenderer(this, 8, 0);
        this.blade_3.setRotationPoint(5.0F, 12.3F, 0.0F);
        this.blade_3.addBox(-0.7F, -4.2F, -1.0F, 1, 9, 2, 0.0F);
        this.setRotateAngle(blade_3, 0.0F, 0.0F, -1.9198621771937625F);
        this.blade_1 = new ModelRenderer(this, 8, 0);
        this.blade_1.setRotationPoint(5.0F, 12.3F, 0.0F);
        this.blade_1.addBox(-0.4F, -2.2F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(blade_1, 0.0F, 0.0F, -2.0943951023931953F);
        this.handle_2 = new ModelRenderer(this, 0, 0);
        this.handle_2.setRotationPoint(-1.45F, -4.0F, 0.0F);
        this.handle_2.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(handle_2, 0.0F, 0.0F, 0.7853981633974483F);
        this.handle.addChild(this.handle_1);
        this.handle.addChild(this.handle_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.handle.render(f5);
    	
    	applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glScaled(modelScale[0] / 1D, modelScale[1] / 1D, modelScale[2] / 1D);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade_2.offsetX, this.blade_2.offsetY, this.blade_2.offsetZ);
        GL11.glTranslatef(this.blade_2.rotationPointX * f5, this.blade_2.rotationPointY * f5, this.blade_2.rotationPointZ * f5);
        GL11.glScaled(1D, 1.3D, 0.35D);
        GL11.glTranslatef(-this.blade_2.offsetX, -this.blade_2.offsetY, -this.blade_2.offsetZ);
        GL11.glTranslatef(-this.blade_2.rotationPointX * f5, -this.blade_2.rotationPointY * f5, -this.blade_2.rotationPointZ * f5);
        this.blade_2.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade.offsetX, this.blade.offsetY, this.blade.offsetZ);
        GL11.glTranslatef(this.blade.rotationPointX * f5, this.blade.rotationPointY * f5, this.blade.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.0D, 0.5D);
        GL11.glTranslatef(-this.blade.offsetX, -this.blade.offsetY, -this.blade.offsetZ);
        GL11.glTranslatef(-this.blade.rotationPointX * f5, -this.blade.rotationPointY * f5, -this.blade.rotationPointZ * f5);
        this.blade.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape28_2.offsetX, this.shape28_2.offsetY, this.shape28_2.offsetZ);
        GL11.glTranslatef(this.shape28_2.rotationPointX * f5, this.shape28_2.rotationPointY * f5, this.shape28_2.rotationPointZ * f5);
        GL11.glScaled(1.1D, 1.7D, 0.45D);
        GL11.glTranslatef(-this.shape28_2.offsetX, -this.shape28_2.offsetY, -this.shape28_2.offsetZ);
        GL11.glTranslatef(-this.shape28_2.rotationPointX * f5, -this.shape28_2.rotationPointY * f5, -this.shape28_2.rotationPointZ * f5);
        this.shape28_2.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape28.offsetX, this.shape28.offsetY, this.shape28.offsetZ);
        GL11.glTranslatef(this.shape28.rotationPointX * f5, this.shape28.rotationPointY * f5, this.shape28.rotationPointZ * f5);
        GL11.glScaled(1.1D, 1.7D, 0.45D);
        GL11.glTranslatef(-this.shape28.offsetX, -this.shape28.offsetY, -this.shape28.offsetZ);
        GL11.glTranslatef(-this.shape28.rotationPointX * f5, -this.shape28.rotationPointY * f5, -this.shape28.rotationPointZ * f5);
        this.shape28.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.shape28_1.offsetX, this.shape28_1.offsetY, this.shape28_1.offsetZ);
        GL11.glTranslatef(this.shape28_1.rotationPointX * f5, this.shape28_1.rotationPointY * f5, this.shape28_1.rotationPointZ * f5);
        GL11.glScaled(1.1D, 1.7D, 0.45D);
        GL11.glTranslatef(-this.shape28_1.offsetX, -this.shape28_1.offsetY, -this.shape28_1.offsetZ);
        GL11.glTranslatef(-this.shape28_1.rotationPointX * f5, -this.shape28_1.rotationPointY * f5, -this.shape28_1.rotationPointZ * f5);
        this.shape28_1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade_3.offsetX, this.blade_3.offsetY, this.blade_3.offsetZ);
        GL11.glTranslatef(this.blade_3.rotationPointX * f5, this.blade_3.rotationPointY * f5, this.blade_3.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.0D, 0.5D);
        GL11.glTranslatef(-this.blade_3.offsetX, -this.blade_3.offsetY, -this.blade_3.offsetZ);
        GL11.glTranslatef(-this.blade_3.rotationPointX * f5, -this.blade_3.rotationPointY * f5, -this.blade_3.rotationPointZ * f5);
        this.blade_3.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade_1.offsetX, this.blade_1.offsetY, this.blade_1.offsetZ);
        GL11.glTranslatef(this.blade_1.rotationPointX * f5, this.blade_1.rotationPointY * f5, this.blade_1.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.0D, 0.5D);
        GL11.glTranslatef(-this.blade_1.offsetX, -this.blade_1.offsetY, -this.blade_1.offsetZ);
        GL11.glTranslatef(-this.blade_1.rotationPointX * f5, -this.blade_1.rotationPointY * f5, -this.blade_1.rotationPointZ * f5);
        this.blade_1.render(f5);
        GL11.glPopMatrix();
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
