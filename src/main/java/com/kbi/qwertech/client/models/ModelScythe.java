package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelMace - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelScythe extends ModelBaseTool {

    public ModelRenderer handle;
    public ModelRenderer blade;
    public ModelRenderer blade2;
    public ModelRenderer centerTip;
    public ModelRenderer topTip;

    public ModelScythe() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.centerTip = new ModelRenderer(this, 16, 10);
        this.centerTip.setRotationPoint(0.0F, 1.0F, -5.5F);
        this.centerTip.addBox(-1.0F, -0.6F, -2.6F, 2, 1, 6, 0.0F);
        this.setRotateAngle(centerTip, 0.2617993877991494F, 0.0F, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
        this.handle.addBox(-1.0F, -13.0F, -1.0F, 2, 13, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 2.1816615649929116F, -1.9198621771937625F);
        this.topTip = new ModelRenderer(this, 14, 9);
        this.topTip.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topTip.addBox(-1.0F, 0.0F, -2.6F, 2, 1, 6, 0.0F);
        this.setRotateAngle(topTip, -0.08726646259971647F, 0.0F, 0.0F);
        this.blade2 = new ModelRenderer(this, 8, 8);
        this.blade2.setRotationPoint(0.0F, -0.1F, -4.8F);
        this.blade2.addBox(-1.0F, -0.5F, -2.5F, 2, 2, 10, 0.0F);
        this.setRotateAngle(blade2, 0.17453292519943295F, 0.0F, 0.0F);
        this.blade = new ModelRenderer(this, 8, 0);
        this.blade.setRotationPoint(1.9F, 9.0F, -0.1F);
        this.blade.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 5, 0.0F);
        this.setRotateAngle(blade, -0.4363323129985824F, 2.007128639793479F, -2.2689280275926285F);
        this.blade2.addChild(this.centerTip);
        this.centerTip.addChild(this.topTip);
        this.blade.addChild(this.blade2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	applyColorSecondary();
    	this.handle.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade.offsetX, this.blade.offsetY, this.blade.offsetZ);
        GL11.glTranslatef(this.blade.rotationPointX * f5, this.blade.rotationPointY * f5, this.blade.rotationPointZ * f5);
        GL11.glScaled(1.1D, 0.5D, 1.0D);
        GL11.glTranslatef(-this.blade.offsetX, -this.blade.offsetY, -this.blade.offsetZ);
        GL11.glTranslatef(-this.blade.rotationPointX * f5, -this.blade.rotationPointY * f5, -this.blade.rotationPointZ * f5);
        
        applyColorPrimary();
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
