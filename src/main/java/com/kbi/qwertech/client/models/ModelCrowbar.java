package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * ModelCrowbar - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelCrowbar extends ModelBaseTool {
    public ModelRenderer handle;
    public ModelRenderer cover;
    public ModelRenderer seg1;
    public ModelRenderer seg5;
    public ModelRenderer seg2;
    public ModelRenderer seg3;
    public ModelRenderer seg4;
    public ModelRenderer seg2_1;
    public ModelRenderer seg3_1;
    public ModelRenderer seg4_1;

    public ModelCrowbar() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.seg5 = new ModelRenderer(this, 10, 12);
        this.seg5.setRotationPoint(-0.52F, 9.44F, 0.0F);
        this.seg5.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(seg5, 3.141592653589793F, 3.141592653589793F, 0.6981317007977318F);
        this.seg3 = new ModelRenderer(this, 6, 13);
        this.seg3.setRotationPoint(0.52F, -1.32F, -0.7F);
        this.seg3.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(seg3, 0.2617993877991494F, 0.0F, 0.6981317007977318F);
        this.cover = new ModelRenderer(this, 6, 0);
        this.cover.setRotationPoint(12.0F, 5.0F, 0.0F);
        this.cover.addBox(-1.0F, -2.5F, -1.5F, 2, 9, 3, -0.2F);
        this.setRotateAngle(cover, 0.0F, 3.141592653589793F, 1.1519173063162573F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.0F, 0.0F);
        this.handle.addBox(-0.5F, -5.0F, -1.0F, 1, 14, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 3.141592653589793F, 1.1519173063162573F);
        this.seg3_1 = new ModelRenderer(this, 6, 13);
        this.seg3_1.setRotationPoint(0.52F, -1.32F, -0.7F);
        this.seg3_1.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(seg3_1, 0.2617993877991494F, 0.0F, 0.6981317007977318F);
        this.seg4_1 = new ModelRenderer(this, 6, 13);
        this.seg4_1.setRotationPoint(0.52F, -1.32F, 0.7F);
        this.seg4_1.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(seg4_1, -0.2617993877991494F, 0.0F, 0.6981317007977318F);
        this.seg2_1 = new ModelRenderer(this, 10, 12);
        this.seg2_1.setRotationPoint(0.45F, -1.51F, 0.0F);
        this.seg2_1.addBox(-0.4F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(seg2_1, 0.0F, 0.0F, 0.6981317007977318F);
        this.seg1 = new ModelRenderer(this, 10, 12);
        this.seg1.setRotationPoint(0.52F, -5.44F, 0.0F);
        this.seg1.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(seg1, 0.0F, 0.0F, 0.6981317007977318F);
        this.seg2 = new ModelRenderer(this, 10, 12);
        this.seg2.setRotationPoint(0.45F, -1.51F, 0.0F);
        this.seg2.addBox(-0.4F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(seg2, 0.0F, 0.0F, 0.6981317007977318F);
        this.seg4 = new ModelRenderer(this, 6, 13);
        this.seg4.setRotationPoint(0.52F, -1.32F, 0.7F);
        this.seg4.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(seg4, -0.2617993877991494F, 0.0F, 0.6981317007977318F);
        this.handle.addChild(this.seg5);
        this.seg2.addChild(this.seg3);
        this.seg2_1.addChild(this.seg3_1);
        this.seg2_1.addChild(this.seg4_1);
        this.seg5.addChild(this.seg2_1);
        this.handle.addChild(this.seg1);
        this.seg1.addChild(this.seg2);
        this.seg2.addChild(this.seg4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.cover.render(f5);
        applyColorPrimary();
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
