package com.kbi.qwertech.client.models.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * ModelGallusGallusJabouille - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelGallusGallusJabouille extends ModelWildChicken {

    public ModelGallusGallusJabouille() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.crest = new ModelRenderer(this, 18, 23);
        this.crest.setRotationPoint(0.0F, -0.6F, -1.3F);
        this.crest.addBox(-0.5F, -2.0F, -1.5F, 1, 2, 4, 0.0F);
        this.setRotateAngle(crest, 0.17453292519943295F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 0);
        this.neck.setRotationPoint(0.0F, 15.0F, -2.25F);
        this.neck.addBox(-2.0F, -5.0F, -2.0F, 4, 5, 3, 0.0F);
        this.setRotateAngle(neck, 0.17453292519943295F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 14, 0);
        this.head.setRotationPoint(0.0F, -4.0F, -0.5F);
        this.head.addBox(-1.5F, -1.0F, -2.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(head, -0.17453292519943295F, 0.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.setRotationPoint(-4.0F, 13.25F, 1.0F);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(rightWing, -0.2617993877991494F, 0.0F, 0.0F);
        this.rightLeg = new ModelRenderer(this, 26, 0);
        this.rightLeg.setRotationPoint(-2.0F, 17.0F, 1.5F);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
        this.leftLeg = new ModelRenderer(this, 26, 0);
        this.leftLeg.setRotationPoint(1.0F, 17.0F, 1.5F);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
        this.beak = new ModelRenderer(this, 14, 5);
        this.beak.setRotationPoint(0.0F, 0.5F, -2.0F);
        this.beak.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(beak, 0.7853981633974483F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -4.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(body, 1.3089969389957472F, 0.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.setRotationPoint(4.0F, 13.25F, 1.0F);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(leftWing, -0.2617993877991494F, 0.0F, -0.0F);
        this.tail = new ModelRenderer(this, 34, 6);
        this.tail.setRotationPoint(0.0F, 14.4F, 3.9F);
        this.tail.addBox(-3.01F, -3.0F, -3.5F, 6, 7, 4, 0.0F);
        this.setRotateAngle(tail, 2.0943951023931953F, 0.0F, 0.0F);
        this.wattle = new ModelRenderer(this, 18, 9);
        this.wattle.setRotationPoint(0.0F, 1.4F, -1.5F);
        this.wattle.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.tail2 = new ModelRenderer(this, 38, 0);
        this.tail2.setRotationPoint(0.0F, 4.0F, 0.5F);
        this.tail2.addBox(-3.0F, 0.0F, -2.0F, 6, 3, 2, 0.0F);
        this.setRotateAngle(tail2, -0.5235987755982988F, 0.0F, 0.0F);
        this.head.addChild(this.crest);
        this.neck.addChild(this.head);
        this.head.addChild(this.beak);
        this.head.addChild(this.wattle);
        this.tail.addChild(this.tail2);
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.neck.rotateAngleX = (p_78087_5_ / (180F / (float)Math.PI)) + 0.175F;
        this.neck.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        //this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.rightWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F) * 1.4F * p_78087_2_;
        if (this.rightWing.rotateAngleZ < 0) this.rightWing.rotateAngleZ *= -1F;
        this.leftWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F + (float)Math.PI) * 1.4F * p_78087_2_;
        if (this.leftWing.rotateAngleZ > 0) this.leftWing.rotateAngleZ *= -1F;
    }
}
