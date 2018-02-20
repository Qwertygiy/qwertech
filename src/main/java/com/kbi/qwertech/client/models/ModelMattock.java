package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelAxe - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelMattock extends ModelBaseTool {
    public ModelRenderer handle;
    public ModelRenderer head;
    public ModelRenderer handle_1;
    public ModelRenderer LowerRight;
    public ModelRenderer UpperStraight;
    public ModelRenderer UpperBend;
    public ModelRenderer LowerStraight;
    public ModelRenderer UpperStraight_1;
    public ModelRenderer bladeLRB;
    public ModelRenderer bladeLRT;
    public ModelRenderer bladeLRB_1;
    public ModelRenderer bladeLRT_1;

    public ModelMattock() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.UpperStraight = new ModelRenderer(this, 12, 4);
        this.UpperStraight.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.UpperStraight.addBox(-0.5F, -2.0F, -2.0F, 1, 2, 6, 0.0F);
        this.bladeLRB_1 = new ModelRenderer(this, 16, 12);
        this.bladeLRB_1.setRotationPoint(5.9F, 14.5F, 0.25F);
        this.bladeLRB_1.addBox(-0.5F, -2.6F, -1.5F, 1, 5, 3, -0.4F);
        this.setRotateAngle(bladeLRB_1, 0.0F, -1.7453292519943295F, -2.443460952792061F);
        this.UpperBend = new ModelRenderer(this, 12, 4);
        this.UpperBend.setRotationPoint(0.0F, 1.1F, 3.8F);
        this.UpperBend.addBox(-0.5F, -2.0F, -2.0F, 1, 2, 5, 0.0F);
        this.setRotateAngle(UpperBend, 0.4363323129985824F, 0.0F, 0.0F);
        this.LowerStraight = new ModelRenderer(this, 12, 4);
        this.LowerStraight.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.LowerStraight.addBox(-0.5F, -2.25F, -2.4F, 1, 3, 5, 0.0F);
        this.head = new ModelRenderer(this, 10, 2);
        this.head.setRotationPoint(1.5F, 9.2F, 0.0F);
        this.head.addBox(-0.5F, -2.0F, -4.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(head, 0.0F, 1.5707963267948966F, -1.9198621771937625F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.3F, 0.0F);
        this.handle.addBox(-1.0F, -14.0F, -1.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.0F, -1.9198621771937625F);
        this.bladeLRT_1 = new ModelRenderer(this, 16, 12);
        this.bladeLRT_1.setRotationPoint(5.9F, 14.5F, -0.25F);
        this.bladeLRT_1.addBox(-0.5F, -2.6F, -1.5F, 1, 5, 3, -0.4F);
        this.setRotateAngle(bladeLRT_1, 0.0F, -1.3962634015954636F, -2.443460952792061F);
        this.LowerRight = new ModelRenderer(this, 12, 4);
        this.LowerRight.setRotationPoint(0.0F, 0.8F, -4.0F);
        this.LowerRight.addBox(-0.5F, -2.0F, -2.0F, 1, 4, 4, 0.0F);
        this.setRotateAngle(LowerRight, 0.5759586531581287F, 0.0F, 0.0F);
        this.UpperStraight_1 = new ModelRenderer(this, 15, 7);
        this.UpperStraight_1.setRotationPoint(0.0F, -0.66F, 4.1F);
        this.UpperStraight_1.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(UpperStraight_1, 0.7853981633974483F, 0.0F, 0.0F);
        this.handle_1 = new ModelRenderer(this, 18, 0);
        this.handle_1.setRotationPoint(1.0F, 9.4F, 0.0F);
        this.handle_1.addBox(-1.5F, -2.0F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(handle_1, 0.0F, 0.7853981633974483F, -1.9198621771937625F);
        this.bladeLRB = new ModelRenderer(this, 16, 12);
        this.bladeLRB.setRotationPoint(3.9F, 16.15F, 0.25F);
        this.bladeLRB.addBox(-0.5F, -2.5F, -1.4F, 1, 4, 3, -0.4F);
        this.setRotateAngle(bladeLRB, 0.0F, -1.7453292519943295F, -1.9198621771937625F);
        this.bladeLRT = new ModelRenderer(this, 16, 12);
        this.bladeLRT.setRotationPoint(3.9F, 16.15F, -0.25F);
        this.bladeLRT.addBox(-0.5F, -2.5F, -1.4F, 1, 4, 3, -0.4F);
        this.setRotateAngle(bladeLRT, 0.0F, -1.3962634015954636F, -1.9198621771937625F);
        this.head.addChild(this.UpperStraight);
        this.head.addChild(this.UpperBend);
        this.head.addChild(this.LowerStraight);
        this.head.addChild(this.LowerRight);
        this.UpperStraight.addChild(this.UpperStraight_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorSecondary();
    	this.handle.render(f5);
    	applyColorPrimary();
        this.handle_1.render(f5);
    	this.head.render(f5);
        this.bladeLRB.render(f5);
        this.bladeLRT.render(f5);
        this.bladeLRT_1.render(f5);
        this.bladeLRB_1.render(f5);
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
