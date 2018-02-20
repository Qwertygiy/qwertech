package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * ModelStick - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelStickLong extends ModelBaseTool {
    public ModelRenderer handle;

    public ModelStickLong() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(-0.33F, 0.0F, 0.0F);
        this.handle.addBox(-1.0F, -14.0F, -1.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.7853981633974483F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.handle.offsetX, this.handle.offsetY, this.handle.offsetZ);
        GL11.glTranslatef(this.handle.rotationPointX * f5, this.handle.rotationPointY * f5, this.handle.rotationPointZ * f5);
        GL11.glScaled(0.8D, 0.8D, 0.8D);
        GL11.glTranslatef(-this.handle.offsetX, -this.handle.offsetY, -this.handle.offsetZ);
        GL11.glTranslatef(-this.handle.rotationPointX * f5, -this.handle.rotationPointY * f5, -this.handle.rotationPointZ * f5);
        this.handle.render(f5);
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
