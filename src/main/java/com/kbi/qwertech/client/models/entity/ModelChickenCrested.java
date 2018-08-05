package com.kbi.qwertech.client.models.entity;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRhodeIslandRed - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelChickenCrested extends ModelChicken {
    public ModelRenderer crest;
    public ModelRenderer tail;

    public ModelChickenCrested() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.bill = new ModelRenderer(this, 14, 0);
        this.bill.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
        this.tail = new ModelRenderer(this, 26, 8);
        this.tail.setRotationPoint(0.0F, 13.5F, 3.5F);
        this.tail.addBox(-3.01F, 0.0F, -0.5F, 6, 3, 1, 0.0F);
        this.setRotateAngle(tail, 2.0943951023931953F, 0.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.setRotationPoint(-4.0F, 13.0F, 0.0F);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(body, 1.5707963705062866F, 0.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.setRotationPoint(4.0F, 13.0F, 0.0F);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.leftLeg = new ModelRenderer(this, 26, 0);
        this.leftLeg.setRotationPoint(1.0F, 19.0F, 1.0F);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(leftLeg, -1.401298464324817E-45F, 0.0F, 0.0F);
        this.crest = new ModelRenderer(this, 14, 23);
        this.crest.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.crest.addBox(-1.0F, -7.5F, -2.01F, 2, 2, 3, 0.0F);
        this.rightLeg = new ModelRenderer(this, 26, 0);
        this.rightLeg.setRotationPoint(-2.0F, 19.0F, 1.0F);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(rightLeg, 1.401298464324817E-45F, 0.0F, 0.0F);
        this.chin = new ModelRenderer(this, 18, 8);
        this.chin.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.chin.addBox(-1.0F, -5.01F, -3.0F, 2, 5, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bill.render(f5);
        this.tail.render(f5);
        this.rightWing.render(f5);
        this.body.render(f5);
        this.leftWing.render(f5);
        this.head.render(f5);
        this.leftLeg.render(f5);
        this.crest.render(f5);
        this.rightLeg.render(f5);
        this.chin.render(f5);
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
