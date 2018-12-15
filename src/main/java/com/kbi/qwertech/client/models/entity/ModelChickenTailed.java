package com.kbi.qwertech.client.models.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelLeghorn - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelChickenTailed extends ModelChickenCrested {
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer a2tail;
    public ModelRenderer a3tail;
    public ModelRenderer a2tail2;
    public ModelRenderer a3tail2;
    public ModelRenderer a2tail3;

    public ModelChickenTailed() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.a2tail2 = new ModelRenderer(this, 40, 14);
        this.a2tail2.setRotationPoint(0.0F, 4.0F, 0.5F);
        this.a2tail2.addBox(-3.0F, 0.0F, -1.0F, 6, 3, 1, 0.0F);
        this.setRotateAngle(a2tail2, -0.8726646259971648F, 0.0F, 0.0F);
        this.a3tail2 = new ModelRenderer(this, 40, 26);
        this.a3tail2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.a3tail2.addBox(-3.0F, 0.0F, -1.0F, 6, 2, 1, 0.0F);
        this.setRotateAngle(a3tail2, -0.8726646259971648F, 0.0F, 0.0F);
        this.a2tail3 = new ModelRenderer(this, 40, 18);
        this.a2tail3.setRotationPoint(0.0F, 3.0F, 0.5F);
        this.a2tail3.addBox(-3.0F, 0.0F, -1.0F, 6, 3, 1, 0.0F);
        this.setRotateAngle(a2tail3, -0.8726646259971648F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 26, 8);
        this.tail.setRotationPoint(0.0F, 13.5F, 3.5F);
        this.tail.addBox(-3.01F, 0.0F, -0.5F, 6, 4, 1, 0.0F);
        this.setRotateAngle(tail, 2.6179938779914944F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 40, 0);
        this.tail2.setRotationPoint(0.0F, 15.0F, 3.5F);
        this.tail2.addBox(-3.01F, 0.0F, -0.5F, 6, 4, 1, 0.0F);
        this.setRotateAngle(tail2, 2.2689280275926285F, 0.0F, 0.0F);
        this.tail3 = new ModelRenderer(this, 40, 5);
        this.tail3.setRotationPoint(0.0F, 16.5F, 3.5F);
        this.tail3.addBox(-3.01F, 0.0F, -0.5F, 6, 3, 1, 0.0F);
        this.setRotateAngle(tail3, 2.0943951023931953F, 0.0F, 0.0F);
        this.a2tail = new ModelRenderer(this, 40, 9);
        this.a2tail.setRotationPoint(0.0F, 4.0F, 0.5F);
        this.a2tail.addBox(-3.0F, 0.0F, -1.0F, 6, 4, 1, 0.0F);
        this.setRotateAngle(a2tail, -1.0471975511965976F, 0.0F, 0.0F);
        this.a3tail = new ModelRenderer(this, 40, 22);
        this.a3tail.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.a3tail.addBox(-3.0F, 0.0F, -1.0F, 6, 3, 1, 0.0F);
        this.setRotateAngle(a3tail, -0.8726646259971648F, 0.0F, 0.0F);
        this.tail2.addChild(this.a2tail2);
        this.a2tail2.addChild(this.a3tail2);
        this.tail3.addChild(this.a2tail3);
        this.tail.addChild(this.a2tail);
        this.a2tail.addChild(this.a3tail);
        this.chin.setRotationPoint(0.0F, 13.75F, -2.25F);
        this.head.setRotationPoint(0.0F, 13.75F, -2.25F);
        this.bill.setRotationPoint(0.0F, 13.75F, -2.25F);
        this.crest.setRotationPoint(0.0F, 13.75F, -2.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * f5, 2.0F * f5);
            this.head.render(f5);
            this.bill.render(f5);
            this.chin.render(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            this.body.render(f5);
            this.rightLeg.render(f5);
            this.leftLeg.render(f5);
            this.rightWing.render(f5);
            this.leftWing.render(f5);
            GL11.glPopMatrix();
        }
        else {
            this.body.render(f5);
            this.tail.render(f5);
            this.tail2.render(f5);
            this.tail3.render(f5);
            this.chin.render(f5);
            this.head.render(f5);
            this.rightLeg.render(f5);
            this.bill.render(f5);
            this.leftWing.render(f5);
            this.leftLeg.render(f5);
            this.rightWing.render(f5);
            this.crest.render(f5);
        }
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
