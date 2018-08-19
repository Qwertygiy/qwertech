package com.kbi.qwertech.client.models.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelChicken extends net.minecraft.client.model.ModelChicken {

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
            this.head.render(p_78088_7_);
            this.bill.render(p_78088_7_);
            this.chin.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            this.rightLeg.render(p_78088_7_);
            this.leftLeg.render(p_78088_7_);
            this.rightWing.render(p_78088_7_);
            this.leftWing.render(p_78088_7_);
            GL11.glPopMatrix();
        }
        else
        {
            this.head.render(p_78088_7_);
            this.bill.render(p_78088_7_);
            this.chin.render(p_78088_7_);
            this.body.render(p_78088_7_);
            this.rightLeg.render(p_78088_7_);
            this.leftLeg.render(p_78088_7_);
            this.rightWing.render(p_78088_7_);
            this.leftWing.render(p_78088_7_);
        }
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bill.rotateAngleX = this.head.rotateAngleX;
        this.bill.rotateAngleY = this.head.rotateAngleY;
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.rightWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F) * 1.4F * p_78087_2_;
        if (this.rightWing.rotateAngleZ < 0) this.rightWing.rotateAngleZ *= -1F;
        this.leftWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F + (float)Math.PI) * 1.4F * p_78087_2_;
        if (this.leftWing.rotateAngleZ > 0) this.leftWing.rotateAngleZ *= -1F;
    }
}
