package com.kbi.qwertech.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * ModelGallusGallusMurghi - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelWildChicken extends ModelBase {
    public ModelRenderer neck;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;
    public ModelRenderer body;
    public ModelRenderer leftWing;
    public ModelRenderer rightWing;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer beak;
    public ModelRenderer crest;
    public ModelRenderer wattle;
    public ModelRenderer tail2;

    public ModelWildChicken() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.beak = new ModelRenderer(this, 14, 5);
        this.beak.setRotationPoint(0.0F, 0.5F, -2.0F);
        this.beak.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(beak, 0.7853981633974483F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 34, 6);
        this.tail.setRotationPoint(0.0F, 14.4F, 3.9F);
        this.tail.addBox(-3.01F, -3.0F, -3.5F, 6, 7, 4, 0.0F);
        this.setRotateAngle(tail, 2.0943951023931953F, 0.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.setRotationPoint(-4.0F, 13.25F, 1.0F);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(rightWing, -0.2617993877991494F, 0.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 26, 0);
        this.leftLeg.setRotationPoint(1.0F, 17.0F, 1.5F);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
        this.neck = new ModelRenderer(this, 0, 0);
        this.neck.setRotationPoint(0.0F, 15.0F, -2.25F);
        this.neck.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.setRotateAngle(neck, 0.3490658503988659F, 0.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.setRotationPoint(4.0F, 13.25F, 1.0F);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(leftWing, -0.2617993877991494F, 0.0F, -0.0F);
        this.tail2 = new ModelRenderer(this, 38, 0);
        this.tail2.setRotationPoint(0.0F, 4.0F, 0.5F);
        this.tail2.addBox(-3.0F, 0.0F, -2.0F, 6, 3, 2, 0.0F);
        this.setRotateAngle(tail2, -0.5235987755982988F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 5, 0.0F);
        this.setRotateAngle(body, 1.3089969389957472F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 14, 0);
        this.head.setRotationPoint(0.0F, -5.0F, -0.5F);
        this.head.addBox(-1.5F, -1.0F, -2.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(head, -0.3490658503988659F, 0.0F, 0.0F);
        this.wattle = new ModelRenderer(this, 18, 9);
        this.wattle.setRotationPoint(0.0F, 1.4F, -1.5F);
        this.wattle.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.rightLeg = new ModelRenderer(this, 26, 0);
        this.rightLeg.setRotationPoint(-2.0F, 17.0F, 1.5F);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
        this.crest = new ModelRenderer(this, 18, 19);
        this.crest.setRotationPoint(0.0F, -0.6F, -1.3F);
        this.crest.addBox(-0.5F, -2.0F, -1.5F, 1, 2, 4, 0.0F);
        this.setRotateAngle(crest, 0.3490658503988659F, 0.0F, 0.0F);
        this.head.addChild(this.beak);
        this.tail.addChild(this.tail2);
        this.neck.addChild(this.head);
        this.head.addChild(this.wattle);
        this.head.addChild(this.crest);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * f5, 2.0F * f5);
            this.neck.render(f5);
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
        else
        {
            this.tail.render(f5);
            this.rightWing.render(f5);
            this.leftLeg.render(f5);
            this.neck.render(f5);
            this.leftWing.render(f5);
            this.body.render(f5);
            this.rightLeg.render(f5);
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

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.neck.rotateAngleX = (p_78087_5_ / (180F / (float)Math.PI)) + 0.35F;
        this.neck.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.setRotateAngle(this.head, -0.35F, 0, 0);
        //this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.rightWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F) * 1.4F * p_78087_2_;
        if (this.rightWing.rotateAngleZ < 0) this.rightWing.rotateAngleZ *= -1F;
        this.leftWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F + (float)Math.PI) * 1.4F * p_78087_2_;
        if (this.leftWing.rotateAngleZ > 0) this.leftWing.rotateAngleZ *= -1F;
    }
}
