package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelIngot - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelIngot extends ModelBaseTool {
    public ModelRenderer ingot;

    public ModelIngot() {
        this.textureWidth = 40;
        this.textureHeight = 40;
        this.ingot = new ModelRenderer(this, 0, 0);
        this.ingot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ingot.addBox(-6.0F, -12.0F, -6.0F, 8, 24, 12, 0.0F);
        this.setRotateAngle(ingot, 2.356194490192345F, 0.0F, 1.5707963267948966F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.ingot.offsetX, this.ingot.offsetY, this.ingot.offsetZ);
        GL11.glTranslatef(this.ingot.rotationPointX * f5, this.ingot.rotationPointY * f5, this.ingot.rotationPointZ * f5);
        GL11.glScaled(0.66D, 0.66D, 0.66D);
        GL11.glTranslatef(-this.ingot.offsetX, -this.ingot.offsetY, -this.ingot.offsetZ);
        GL11.glTranslatef(-this.ingot.rotationPointX * f5, -this.ingot.rotationPointY * f5, -this.ingot.rotationPointZ * f5);
        this.ingot.render(f5);
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
