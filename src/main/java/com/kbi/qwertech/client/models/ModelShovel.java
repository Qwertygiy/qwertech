package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelShovel - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelShovel extends ModelBaseTool {
    public ModelRenderer head;
    public ModelRenderer side;
    public ModelRenderer side_1;
    public ModelRenderer center;
    public ModelRenderer center_1;
    public ModelRenderer head_1;
    public ModelRenderer head_2;
    public ModelRenderer handle;
    public ModelRenderer side_2;
    public ModelRenderer side_3;

    public ModelShovel() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.head_1 = new ModelRenderer(this, 8, 0);
        this.head_1.setRotationPoint(0.0F, -3.15F, -1.0F);
        this.head_1.addBox(-1.0F, -15.0F, -0.5F, 1, 3, 3, 0.0F);
        this.center = new ModelRenderer(this, 20, 11);
        this.center.setRotationPoint(0.12F, -17.0F, -0.18F);
        this.center.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(center, 0.0F, 0.19198621771937624F, 0.0F);
        this.side_2 = new ModelRenderer(this, 20, 0);
        this.side_2.setRotationPoint(-0.1F, -12.5F, -1.0F);
        this.side_2.addBox(-0.5F, -2.5F, -1.5F, 1, 5, 2, 0.0F);
        this.setRotateAngle(side_2, 0.0F, 0.3490658503988659F, 0.0F);
        this.head = new ModelRenderer(this, 8, 0);
        this.head.setRotationPoint(0.0F, -1.0F, -1.0F);
        this.head.addBox(-0.5F, -15.0F, -1.0F, 1, 5, 4, 0.0F);
        this.side_1 = new ModelRenderer(this, 20, 7);
        this.side_1.setRotationPoint(-0.12F, -16.0F, 2.08F);
        this.side_1.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(side_1, 0.7853981633974483F, -0.3490658503988659F, 0.0F);
        this.center_1 = new ModelRenderer(this, 20, 11);
        this.center_1.setRotationPoint(-0.12F, -17.0F, 2.08F);
        this.center_1.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(center_1, 0.0F, -0.19198621771937624F, 0.0F);
        this.head_2 = new ModelRenderer(this, 8, 0);
        this.head_2.setRotationPoint(0.25F, -3.15F, 0.0F);
        this.head_2.addBox(-0.5F, -15.0F, -0.5F, 1, 3, 1, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(-0.33F, 0.0F, 0.0F);
        this.handle.addBox(-1.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.7853981633974483F, 0.0F);
        this.side_3 = new ModelRenderer(this, 20, 0);
        this.side_3.mirror = true;
        this.side_3.setRotationPoint(-0.1F, -12.5F, 3.0F);
        this.side_3.addBox(-0.5F, -2.5F, -0.5F, 1, 5, 2, 0.0F);
        this.setRotateAngle(side_3, 0.0F, -0.3490658503988659F, 0.0F);
        this.side = new ModelRenderer(this, 20, 7);
        this.side.setRotationPoint(-0.12F, -16.0F, -2.08F);
        this.side.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(side, 0.7853981633974483F, 0.3490658503988659F, 0.0F);
        this.head.addChild(this.side_2);
        this.head.addChild(this.side_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glPushMatrix();
        GL11.glRotatef(180, 0, 1, 0);
    	applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.head_1.offsetX, this.head_1.offsetY, this.head_1.offsetZ);
        GL11.glTranslatef(this.head_1.rotationPointX * f5, this.head_1.rotationPointY * f5, this.head_1.rotationPointZ * f5);
        GL11.glScaled(0.5D, 1.0D, 1.0D);
        GL11.glTranslatef(-this.head_1.offsetX, -this.head_1.offsetY, -this.head_1.offsetZ);
        GL11.glTranslatef(-this.head_1.rotationPointX * f5, -this.head_1.rotationPointY * f5, -this.head_1.rotationPointZ * f5);
        this.head_1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.center.offsetX, this.center.offsetY, this.center.offsetZ);
        GL11.glTranslatef(this.center.rotationPointX * f5, this.center.rotationPointY * f5, this.center.rotationPointZ * f5);
        GL11.glScaled(0.95D, 1.15D, 1.9D);
        GL11.glTranslatef(-this.center.offsetX, -this.center.offsetY, -this.center.offsetZ);
        GL11.glTranslatef(-this.center.rotationPointX * f5, -this.center.rotationPointY * f5, -this.center.rotationPointZ * f5);
        this.center.render(f5);
        GL11.glPopMatrix();
        this.head.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.side_1.offsetX, this.side_1.offsetY, this.side_1.offsetZ);
        GL11.glTranslatef(this.side_1.rotationPointX * f5, this.side_1.rotationPointY * f5, this.side_1.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.5D, 1.0D);
        GL11.glTranslatef(-this.side_1.offsetX, -this.side_1.offsetY, -this.side_1.offsetZ);
        GL11.glTranslatef(-this.side_1.rotationPointX * f5, -this.side_1.rotationPointY * f5, -this.side_1.rotationPointZ * f5);
        this.side_1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.center_1.offsetX, this.center_1.offsetY, this.center_1.offsetZ);
        GL11.glTranslatef(this.center_1.rotationPointX * f5, this.center_1.rotationPointY * f5, this.center_1.rotationPointZ * f5);
        GL11.glScaled(0.95D, 1.15D, 1.9D);
        GL11.glTranslatef(-this.center_1.offsetX, -this.center_1.offsetY, -this.center_1.offsetZ);
        GL11.glTranslatef(-this.center_1.rotationPointX * f5, -this.center_1.rotationPointY * f5, -this.center_1.rotationPointZ * f5);
        this.center_1.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.head_2.offsetX, this.head_2.offsetY, this.head_2.offsetZ);
        GL11.glTranslatef(this.head_2.rotationPointX * f5, this.head_2.rotationPointY * f5, this.head_2.rotationPointZ * f5);
        GL11.glScaled(0.55D, 1.0D, 1.3D);
        GL11.glTranslatef(-this.head_2.offsetX, -this.head_2.offsetY, -this.head_2.offsetZ);
        GL11.glTranslatef(-this.head_2.rotationPointX * f5, -this.head_2.rotationPointY * f5, -this.head_2.rotationPointZ * f5);
        this.head_2.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.side.offsetX, this.side.offsetY, this.side.offsetZ);
        GL11.glTranslatef(this.side.rotationPointX * f5, this.side.rotationPointY * f5, this.side.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.5D, 1.0D);
        GL11.glTranslatef(-this.side.offsetX, -this.side.offsetY, -this.side.offsetZ);
        GL11.glTranslatef(-this.side.rotationPointX * f5, -this.side.rotationPointY * f5, -this.side.rotationPointZ * f5);
        this.side.render(f5);
        GL11.glPopMatrix();
        
        applyColorSecondary();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.handle.offsetX, this.handle.offsetY, this.handle.offsetZ);
        GL11.glTranslatef(this.handle.rotationPointX * f5, this.handle.rotationPointY * f5, this.handle.rotationPointZ * f5);
        GL11.glScaled(0.75D, 1.0D, 1.0D);
        GL11.glTranslatef(-this.handle.offsetX, -this.handle.offsetY, -this.handle.offsetZ);
        GL11.glTranslatef(-this.handle.rotationPointX * f5, -this.handle.rotationPointY * f5, -this.handle.rotationPointZ * f5);
        this.handle.render(f5);
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
