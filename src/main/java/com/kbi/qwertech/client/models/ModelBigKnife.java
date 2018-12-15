package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelMace - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelBigKnife extends ModelBaseTool {
    public ModelRenderer handle;
    public ModelRenderer blade;
    public ModelRenderer blade_1;
    public ModelRenderer handle_1;
    public ModelRenderer handle_2;

    public ModelBigKnife() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.blade = new ModelRenderer(this, 8, 0);
        this.blade.setRotationPoint(3.7F, 10.9F, 0.0F);
        this.blade.addBox(-4.4F, -5.0F, -1.0F, 6, 10, 2, 0.0F);
        this.setRotateAngle(blade, 0.0F, 0.0F, -2.0943951023931953F);
        this.handle_1 = new ModelRenderer(this, 0, 0);
        this.handle_1.setRotationPoint(-1.45F, 1.0F, 0.0F);
        this.handle_1.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(handle_1, 0.0F, 0.0F, 0.7853981633974483F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
        this.handle.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.0F, -2.0943951023931953F);
        this.handle_2 = new ModelRenderer(this, 0, 0);
        this.handle_2.setRotationPoint(-1.45F, -4.0F, 0.0F);
        this.handle_2.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(handle_2, 0.0F, 0.0F, 0.7853981633974483F);
        this.blade_1 = new ModelRenderer(this, 0, 7);
        this.blade_1.setRotationPoint(2.0F, 18.1F, 0.0F);
        this.blade_1.addBox(-0.5F, 0.0F, -0.5F, 2, 7, 2, 0.0F);
        this.setRotateAngle(blade_1, 0.0F, 0.7853981633974483F, -2.0594885173533086F);
        this.handle.addChild(this.handle_1);
        this.handle.addChild(this.handle_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.handle.render(f5);
        applyColorPrimary();
    	GL11.glPushMatrix();
        GL11.glTranslatef(this.blade.offsetX, this.blade.offsetY, this.blade.offsetZ);
        GL11.glTranslatef(this.blade.rotationPointX * f5, this.blade.rotationPointY * f5, this.blade.rotationPointZ * f5);
        GL11.glScaled(1.0D, 1.0D, 0.3D);
        GL11.glTranslatef(-this.blade.offsetX, -this.blade.offsetY, -this.blade.offsetZ);
        GL11.glTranslatef(-this.blade.rotationPointX * f5, -this.blade.rotationPointY * f5, -this.blade.rotationPointZ * f5);
        this.blade.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.blade_1.offsetX, this.blade_1.offsetY, this.blade_1.offsetZ);
        GL11.glTranslatef(this.blade_1.rotationPointX * f5, this.blade_1.rotationPointY * f5, this.blade_1.rotationPointZ * f5);
        GL11.glScaled(1.4D, 1.5D, 0.22D);
        GL11.glTranslatef(-this.blade_1.offsetX, -this.blade_1.offsetY, -this.blade_1.offsetZ);
        GL11.glTranslatef(-this.blade_1.rotationPointX * f5, -this.blade_1.rotationPointY * f5, -this.blade_1.rotationPointZ * f5);
        this.blade_1.render(f5);
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
