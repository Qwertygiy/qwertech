package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelPokeyStick - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelPokeyStick extends ModelBaseTool {
    public ModelRenderer spike;
    public ModelRenderer handle;

    public ModelPokeyStick() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.spike = new ModelRenderer(this, 4, 8);
        this.spike.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.spike.addBox(-1.0F, -2.0F, -2.0F, 2, 4, 4, 0.0F);
        this.setRotateAngle(spike, 0.7853981633974483F, 0.0F, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.spike.offsetX, this.spike.offsetY, this.spike.offsetZ);
        GL11.glTranslatef(this.spike.rotationPointX * f5, this.spike.rotationPointY * f5, this.spike.rotationPointZ * f5);
        GL11.glScaled(1D, 1D, 0.35D);
        GL11.glTranslatef(-this.spike.offsetX, -this.spike.offsetY, -this.spike.offsetZ);
        GL11.glTranslatef(-this.spike.rotationPointX * f5, -this.spike.rotationPointY * f5, -this.spike.rotationPointZ * f5);
        this.spike.render(f5);
        GL11.glPopMatrix();
        this.handle.render(f5);
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
