package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelKnuckles - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */   
public class ModelMace extends ModelBaseTool {
        public ModelRenderer handle;
        public ModelRenderer smasher;
        public ModelRenderer spike1;
        public ModelRenderer spike2;
        public ModelRenderer spike3;
        public ModelRenderer spike4;
        public ModelRenderer spike5;
        public ModelRenderer spike6;
        public ModelRenderer spike7;
        public ModelRenderer spike8;
        public ModelRenderer cube1;
        public ModelRenderer cube2;
        public ModelRenderer cube3;
        public ModelRenderer spike9;
        public ModelRenderer spike10;
        public ModelRenderer spike11;
        public ModelRenderer spike12;
        public ModelRenderer spike13;

        public ModelMace() {
            this.textureWidth = 32;
            this.textureHeight = 32;
            this.spike1 = new ModelRenderer(this, 8, 0);
            this.spike1.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.spike1.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike1, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
            this.smasher = new ModelRenderer(this, 8, 0);
            this.smasher.setRotationPoint(-2.0F, 13.8F, -3.0F);
            this.smasher.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
            this.setRotateAngle(smasher, 0.0F, 0.0F, -1.9198621771937625F);
            this.spike2 = new ModelRenderer(this, 8, 0);
            this.spike2.setRotationPoint(0.0F, 0.0F, 6.0F);
            this.spike2.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike2, -0.7853981633974483F, -0.7853981633974483F, 0.0F);
            this.cube3 = new ModelRenderer(this, 0, 12);
            this.cube3.setRotationPoint(3.0F, 3.0F, 3.0F);
            this.cube3.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
            this.setRotateAngle(cube3, 0.0F, 0.0F, 0.7853981633974483F);
            this.spike7 = new ModelRenderer(this, 8, 0);
            this.spike7.setRotationPoint(6.0F, 6.0F, 0.0F);
            this.spike7.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike7, -0.7853981633974483F, -0.7853981633974483F, 0.0F);
            this.spike9 = new ModelRenderer(this, 8, 0);
            this.spike9.setRotationPoint(3.0F, -1.0F, 3.0F);
            this.spike9.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.spike4 = new ModelRenderer(this, 8, 0);
            this.spike4.setRotationPoint(6.0F, 0.0F, 6.0F);
            this.spike4.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike4, -2.356194490192345F, -2.356194490192345F, 0.0F);
            this.cube2 = new ModelRenderer(this, 0, 12);
            this.cube2.setRotationPoint(3.0F, 3.0F, 3.0F);
            this.cube2.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
            this.setRotateAngle(cube2, 0.7853981633974483F, 0.0F, 0.0F);
            this.spike10 = new ModelRenderer(this, 8, 0);
            this.spike10.setRotationPoint(-1.0F, 3.0F, 3.0F);
            this.spike10.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike10, 0.0F, 0.0F, 1.5707963267948966F);
            this.spike6 = new ModelRenderer(this, 8, 0);
            this.spike6.setRotationPoint(0.0F, 6.0F, 0.0F);
            this.spike6.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike6, -2.356194490192345F, -2.356194490192345F, 0.0F);
            this.spike12 = new ModelRenderer(this, 8, 0);
            this.spike12.setRotationPoint(3.0F, 3.0F, -1.0F);
            this.spike12.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike12, 1.5707963267948966F, 0.0F, 0.0F);
            this.handle = new ModelRenderer(this, 0, 0);
            this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
            this.handle.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
            this.setRotateAngle(handle, 0.0F, 0.0F, -1.9198621771937625F);
            this.spike3 = new ModelRenderer(this, 8, 0);
            this.spike3.setRotationPoint(6.0F, 0.0F, 0.0F);
            this.spike3.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike3, 2.356194490192345F, 2.356194490192345F, 0.0F);
            this.spike11 = new ModelRenderer(this, 8, 0);
            this.spike11.setRotationPoint(7.0F, 3.0F, 3.0F);
            this.spike11.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike11, 0.0F, 0.0F, -1.5707963267948966F);
            this.cube1 = new ModelRenderer(this, 0, 12);
            this.cube1.setRotationPoint(3.0F, 3.0F, 3.0F);
            this.cube1.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
            this.setRotateAngle(cube1, 0.0F, 0.7853981633974483F, 0.0F);
            this.spike8 = new ModelRenderer(this, 8, 0);
            this.spike8.setRotationPoint(0.0F, 6.0F, 6.0F);
            this.spike8.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike8, 2.356194490192345F, 2.356194490192345F, 0.0F);
            this.spike13 = new ModelRenderer(this, 8, 0);
            this.spike13.setRotationPoint(3.0F, 3.0F, 7.0F);
            this.spike13.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike13, -1.5707963267948966F, 0.0F, 0.0F);
            this.spike5 = new ModelRenderer(this, 8, 0);
            this.spike5.setRotationPoint(6.0F, 6.0F, 6.0F);
            this.spike5.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(spike5, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
            this.smasher.addChild(this.spike1);
            this.smasher.addChild(this.spike2);
            this.smasher.addChild(this.cube3);
            this.smasher.addChild(this.spike7);
            this.smasher.addChild(this.spike9);
            this.smasher.addChild(this.spike4);
            this.smasher.addChild(this.cube2);
            this.smasher.addChild(this.spike10);
            this.smasher.addChild(this.spike6);
            this.smasher.addChild(this.spike12);
            this.smasher.addChild(this.spike3);
            this.smasher.addChild(this.spike11);
            this.smasher.addChild(this.cube1);
            this.smasher.addChild(this.spike8);
            this.smasher.addChild(this.spike13);
            this.smasher.addChild(this.spike5);
        }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorSecondary();
    	this.handle.render(f5);
        applyColorPrimary();
        this.smasher.render(f5);
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
