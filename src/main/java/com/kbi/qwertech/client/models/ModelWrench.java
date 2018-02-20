package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * ModelWrench - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelWrench extends ModelBaseTool {
    public double[] modelScale = new double[] { 0.9D, 0.9D, 0.9D };
    public ModelRenderer mainbody;
    public ModelRenderer bottomcurvetop;
    public ModelRenderer bottomcurvebottom;
    public ModelRenderer left1;
    public ModelRenderer right1;
    public ModelRenderer left2;
    public ModelRenderer left3;
    public ModelRenderer right2;
    public ModelRenderer right3;

    public ModelWrench() {
    	this.textureWidth = 16;
        this.textureHeight = 16;
        this.left3 = new ModelRenderer(this, 0, 0);
        this.left3.setRotationPoint(0.0F, -1.9F, 0.08F);
        this.left3.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
        this.setRotateAngle(left3, -0.4363323129985824F, 0.0F, 0.0F);
        this.bottomcurvebottom = new ModelRenderer(this, 0, 0);
        this.bottomcurvebottom.setRotationPoint(11.9F, 6.63F, 0.0F);
        this.bottomcurvebottom.addBox(-1.0F, -1.5F, -1.5F, 2, 3, 3, -0.4F);
        this.setRotateAngle(bottomcurvebottom, 0.7853981633974483F, 3.141592653589793F, 1.2217304763960306F);
        this.right3 = new ModelRenderer(this, 0, 0);
        this.right3.setRotationPoint(0.0F, -1.9F, 0.08F);
        this.right3.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
        this.setRotateAngle(right3, -0.4363323129985824F, 0.0F, 0.0F);
        this.mainbody = new ModelRenderer(this, 6, 2);
        this.mainbody.setRotationPoint(7.0F, 8.0F, 0.0F);
        this.mainbody.addBox(-1.0F, -5.0F, -1.5F, 2, 10, 3, 0.0F);
        this.setRotateAngle(mainbody, 0.0F, 3.141592653589793F, 1.2217304763960306F);
        this.bottomcurvetop = new ModelRenderer(this, 0, 0);
        this.bottomcurvetop.setRotationPoint(11.63F, 5.9F, 0.0F);
        this.bottomcurvetop.addBox(-1.0F, -1.5F, -1.5F, 2, 3, 3, -0.4F);
        this.setRotateAngle(bottomcurvetop, 0.7853981633974483F, 3.141592653589793F, 1.2217304763960306F);
        this.right1 = new ModelRenderer(this, 0, 0);
        this.right1.setRotationPoint(0.0F, 4.6F, 2.6F);
        this.right1.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(right1, 1.0471975511965976F, 3.141592653589793F, 3.141592653589793F);
        this.left1 = new ModelRenderer(this, 0, 0);
        this.left1.setRotationPoint(0.0F, 4.6F, -2.6F);
        this.left1.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(left1, 1.0471975511965976F, 0.0F, 3.141592653589793F);
        this.left2 = new ModelRenderer(this, 0, 0);
        this.left2.setRotationPoint(0.0F, -1.1F, 1.2F);
        this.left2.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(left2, -1.0471975511965976F, 0.0F, 0.0F);
        this.right2 = new ModelRenderer(this, 0, 0);
        this.right2.setRotationPoint(0.0F, -1.1F, 1.2F);
        this.right2.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(right2, -1.0471975511965976F, 0.0F, 0.0F);
        this.left2.addChild(this.left3);
        this.right2.addChild(this.right3);
        this.mainbody.addChild(this.right1);
        this.mainbody.addChild(this.left1);
        this.left1.addChild(this.left2);
        this.right1.addChild(this.right2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glPushMatrix();
        GL11.glScaled(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        applyColorPrimary();
        this.bottomcurvebottom.render(f5);
        this.bottomcurvetop.render(f5);
        this.mainbody.render(f5);
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
