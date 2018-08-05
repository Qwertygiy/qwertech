package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.entities.neutral.EntityTurkey;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * ModelTurkey - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelTurkey extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Tail;
    public ModelRenderer LeftWing;
    public ModelRenderer RightWing;
    public ModelRenderer RightTail;
    public ModelRenderer LeftTail;
    public ModelRenderer Beak;
    public ModelRenderer Snood;
    public ModelRenderer Chest;

    public ModelTurkey() {
    	this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftTail = new ModelRenderer(this, 0, 34);
        this.LeftTail.setRotationPoint(3.33F, 0.0F, 0.0F);
        this.LeftTail.addBox(-4.0F, -2.5F, -1.7F, 8, 2, 11, 0.0F);
        this.setRotateAngle(LeftTail, -0.21F, 0.576F, -0.262F);
        this.RightWing = new ModelRenderer(this, 10, 11);
        this.RightWing.mirror = true;
        this.RightWing.setRotationPoint(-5.0F, -2.0F, 2.0F);
        this.RightWing.addBox(-6.0F, -3.0F, -1.0F, 6, 6, 2, 0.0F);
        this.setRotateAngle(RightWing, 0.0F, -1.047F, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 19);
        this.Tail.setRotationPoint(0.0F, 5.5F, -0.5F);
        this.Tail.addBox(-5.5F, -1.0F, -2.0F, 11, 2, 12, 0.0F);
        this.setRotateAngle(Tail, -1.396F, 0.0F, 0.0F);
        this.Chest = new ModelRenderer(this, 32, 34);
        this.Chest.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Chest.addBox(-5.5F, -2.5F, -2.5F, 11, 5, 5, 0.0F);
        this.setRotateAngle(Chest, 0.785F, 0.0F, 0.0F);
        this.RightTail = new ModelRenderer(this, 0, 34);
        this.RightTail.mirror = true;
        this.RightTail.setRotationPoint(-3.33F, 0.0F, 0.0F);
        this.RightTail.addBox(-4.0F, -2.5F, -1.7F, 8, 2, 11, 0.0F);
        this.setRotateAngle(RightTail, -0.209F, -0.576F, 0.262F);
        this.Beak = new ModelRenderer(this, 14, 0);
        this.Beak.setRotationPoint(0.0F, -3.5F, -1.5F);
        this.Beak.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Beak, 0.785F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 26, 0);
        this.Body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Body.addBox(-6.0F, -6.0F, -3.5F, 12, 12, 7, 0.0F);
        this.setRotateAngle(Body, 2.00F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.Head.addBox(-2.0F, -6.0F, -2.0F, 4, 7, 3, 0.0F);
        this.setRotateAngle(Head, -0.175F, 0.0F, 0.0F);
        this.Snood = new ModelRenderer(this, 0, 10);
        this.Snood.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.Snood.addBox(-1.0F, -3.5F, -1.5F, 2, 5, 2, 0.0F);
        this.setRotateAngle(Snood, -0.175F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 20);
        this.RightLeg.mirror = true;
        this.RightLeg.setRotationPoint(-2.0F, 19.0F, 1.0F);
        this.RightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 20);
        this.LeftLeg.setRotationPoint(2.0F, 19.0F, 1.0F);
        this.LeftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.LeftWing = new ModelRenderer(this, 10, 11);
        this.LeftWing.setRotationPoint(5.0F, -2.0F, 2.0F);
        this.LeftWing.addBox(0.0F, -3.0F, -1.0F, 6, 6, 2, 0.0F);
        this.setRotateAngle(LeftWing, 0.0F, 1.047F, 0.0F);
        this.Tail.addChild(this.LeftTail);
        this.Body.addChild(this.RightWing);
        this.Body.addChild(this.Tail);
        this.Body.addChild(this.Chest);
        this.Tail.addChild(this.RightTail);
        this.Head.addChild(this.Beak);
        this.Head.addChild(this.Snood);
        this.Body.addChild(this.LeftWing);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	if (this.isChild) 
    	{
    		GL11.glPushMatrix();
    		GL11.glTranslatef(0.0F, 4.5F * f5, 2.0F * f5);
    		this.Snood.isHidden = true;
	        this.Head.render(f5);
	        GL11.glTranslatef(0.0F, 7.5F * f5, -1.0F * f5);
	        GL11.glScalef(0.5F, 0.5F, 0.5F);
	        this.LeftLeg.render(f5);
	        this.RightLeg.render(f5);
	    	this.Body.render(f5);
	    	GL11.glPopMatrix();
    	} else {
    		this.Snood.isHidden = false;
    		this.Head.render(f5);
    		this.LeftLeg.render(f5);
	        this.RightLeg.render(f5);
	    	this.Body.render(f5);
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
    
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
    	float headangle = -0.175F;
    	if (((EntityTurkey)p_78087_7_).isAngry()) headangle = 0.175F;
        this.Head.rotateAngleX = (p_78087_5_ / (180F / (float)Math.PI)) + headangle;
        this.Head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        //this.Body.rotateAngleX = ((float)Math.PI / 2F);
        this.RightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.LeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        if (((EntityTurkey)p_78087_7_).isAngry())
        {
        	this.RightWing.rotateAngleY = p_78087_3_;
        	this.LeftWing.rotateAngleY = -p_78087_3_;
        	this.Tail.rotateAngleX = -0.33F;
		} else {
			this.Tail.rotateAngleX = -1.396F;
			this.RightWing.rotateAngleY = -1.047F;
			this.LeftWing.rotateAngleY = 1.047F;
		}
        //this.RightWing.rotateAngleZ = p_78087_3_;
        //this.LeftWing.rotateAngleZ = -p_78087_3_;
    }
}
