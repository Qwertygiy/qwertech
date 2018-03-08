package com.kbi.qwertech.client.models;

import gregapi.oredict.OreDictMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

/**
 * ModelSlingshot - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelSlingshot extends ModelBase {
    public ModelRenderer rock;
    public ModelRenderer rockOverlay;
    public ModelRenderer handle;
    public ModelRenderer String;
    public ModelRenderer handle1;
    public ModelRenderer handle2;
    public ModelRenderer handle3;
    public ModelRenderer handle3_1;
    public ModelRenderer StringRight;
    public ModelRenderer StringLeft;
    
    public short[] primaryMaterial = new short[]{255, 255, 255, 255};
    public short[] rockMaterial = new short[]{255, 255, 255, 255};
    public float[] defaultString = new float[]{2.3F, 8.2F, 0.0F, 0.0F, 0.0F, -1.920F, -0.8F, 0.0F, -2.1F, 0.0F, 0.436F, 0.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F, -0.349F};
    public float[] maximumString = new float[]{2.3F, 6.6F, 0.0F, 0.0F, 0.0F, -1.571F, -1.2F, 0.0F, -1.7F, 0.0F, 0.785F, 0.0F, 3.0F, 8.2F, 0.0F, 0.0F, 0.0F, 0.0F};
    
    public ModelFoil foilModel;
    public ModelBall ballModel;
    public boolean isLaunching = false;
    public Random rand;
    
    public ModelSlingshot() {
    	this.rand = new Random();
    	this.foilModel = new ModelFoil();
    	this.ballModel = new ModelBall();
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.handle = new ModelRenderer(this, 0, 7);
        this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
        this.handle.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.0F, -1.9198621771937625F);
        this.String = new ModelRenderer(this, 0, 0);
        this.String.setRotationPoint(2.3F, 8.2F, 0.0F);
        this.String.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(String, 0.0F, 0.0F, -1.9198621771937625F);
        this.handle3_1 = new ModelRenderer(this, 24, 6);
        this.handle3_1.setRotationPoint(0.0F, -9.0F, 2.57F);
        this.handle3_1.addBox(-1.0F, -2.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(handle3_1, -0.17453292519943295F, 0.0F, 0.0F);
        this.rockOverlay = new ModelRenderer(this, 3, 8);
        this.rockOverlay.setRotationPoint(3.0F, 10.0F, 0.0F);
        this.rockOverlay.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotateAngle(rockOverlay, 0.0F, 0.0F, -0.3490658503988659F);
        this.rock = new ModelRenderer(this, 6, 0);
        this.rock.setRotationPoint(3.0F, 10.0F, 0.0F);
        this.rock.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotateAngle(rock, 0.0F, 0.0F, -0.3490658503988659F);
        this.StringLeft = new ModelRenderer(this, 0, 0);
        this.StringLeft.setRotationPoint(-0.8F, 0.0F, -2.1F);
        this.StringLeft.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(StringLeft, 0.0F, 0.4363323129985824F, 0.0F);
        this.handle2 = new ModelRenderer(this, 24, 10);
        this.handle2.setRotationPoint(0.0F, -5.0F, 1.0F);
        this.handle2.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(handle2, -0.6981317007977318F, 0.0F, 0.0F);
        this.handle1 = new ModelRenderer(this, 24, 10);
        this.handle1.setRotationPoint(0.0F, -5.0F, -1.0F);
        this.handle1.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(handle1, 0.6981317007977318F, 0.0F, 0.0F);
        this.handle3 = new ModelRenderer(this, 24, 6);
        this.handle3.setRotationPoint(0.0F, -9.0F, -2.57F);
        this.handle3.addBox(-1.0F, -2.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(handle3, 0.17453292519943295F, 0.0F, 0.0F);
        this.StringRight = new ModelRenderer(this, 0, 0);
        this.StringRight.setRotationPoint(-0.8F, 0.0F, 2.1F);
        this.StringRight.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(StringRight, 0.0F, -0.4363323129985824F, 0.0F);
        this.handle.addChild(this.handle3_1);
        this.String.addChild(this.StringLeft);
        this.handle.addChild(this.handle2);
        this.handle.addChild(this.handle1);
        this.handle.addChild(this.handle3);
        this.String.addChild(this.StringRight);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.setStringPosition(f1);
    	/*if (this.rand.nextInt(10) == 3)
        {
        	UT.Sounds.send(entity.worldObj, Block.soundTypeLadder.getStepResourcePath(), 0.5F, Math.max(0.5F, 2F - (72000 - f1)/100), (int)entity.posX, (int)entity.posY, (int)entity.posZ);
        }*/
    	
    	float red = (this.primaryMaterial[0]) / 255.0F;
        float green = (this.primaryMaterial[1]) / 255.0F;
        float blue = (this.primaryMaterial[2]) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
    	this.handle.render(f5);
    	GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.String.offsetX, this.String.offsetY, this.String.offsetZ);
        GL11.glTranslatef(this.String.rotationPointX * f5, this.String.rotationPointY * f5, this.String.rotationPointZ * f5);
        GL11.glScaled(0.75D, 0.75D, 0.75D);
        GL11.glTranslatef(-this.String.offsetX, -this.String.offsetY, -this.String.offsetZ);
        GL11.glTranslatef(-this.String.rotationPointX * f5, -this.String.rotationPointY * f5, -this.String.rotationPointZ * f5);
        
        this.String.render(f5);
        GL11.glPopMatrix();
        
        if (f1 > 0)
        {
        	this.isLaunching = true;
        	if (f <= 1)
	        {
		        GL11.glPushMatrix();
		        GL11.glTranslatef(this.rock.offsetX, this.rock.offsetY, this.rock.offsetZ);
		        GL11.glTranslatef(this.rock.rotationPointX * f5, this.rock.rotationPointY * f5, this.rock.rotationPointZ * f5);
		        GL11.glScaled(0.6D, 0.6D, 0.6D);
		        GL11.glTranslatef(-this.rock.offsetX, -this.rock.offsetY, -this.rock.offsetZ);
		        GL11.glTranslatef(-this.rock.rotationPointX * f5, -this.rock.rotationPointY * f5, -this.rock.rotationPointZ * f5);
		        red = (this.rockMaterial[0]) / 255.0F;
		        green = (this.rockMaterial[1]) / 255.0F;
		        blue = (this.rockMaterial[2]) / 255.0F;
		        GL11.glColor4f(red, green, blue, 1.0F);
		        this.rock.render(f5);
		        GL11.glColor4f(1F, 1F, 1F, 1F);
		        GL11.glPopMatrix();
	        }
	        if (f == 1)
	        {
		        GL11.glPushMatrix();
		        GL11.glTranslatef(this.rockOverlay.offsetX, this.rockOverlay.offsetY, this.rockOverlay.offsetZ);
		        GL11.glTranslatef(this.rockOverlay.rotationPointX * f5, this.rockOverlay.rotationPointY * f5, this.rockOverlay.rotationPointZ * f5);
		        GL11.glScaled(0.61D, 0.61D, 0.61D);
		        GL11.glTranslatef(-this.rockOverlay.offsetX, -this.rockOverlay.offsetY, -this.rockOverlay.offsetZ);
		        GL11.glTranslatef(-this.rockOverlay.rotationPointX * f5, -this.rockOverlay.rotationPointY * f5, -this.rockOverlay.rotationPointZ * f5);
		        this.rockOverlay.render(f5);
		        GL11.glPopMatrix();
	        } else if (f == 2) {
	        	GL11.glPushMatrix();
	        	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("qwertech:textures/entity/rock/base.png"));
	        	//GL11.glTranslatef(this.rock.offsetX + this.rock.rotationPointX, this.rock.offsetY + this.rock.rotationPointY, this.rock.offsetZ + this.rock.rotationPointZ);
	        	GL11.glTranslatef(this.rock.offsetX, this.rock.offsetY, this.rock.offsetZ);
	        	GL11.glTranslatef(this.foilModel.rocky.rotationPointX * f5, this.foilModel.rocky.rotationPointY * f5, this.foilModel.rocky.rotationPointZ * f5);
	        	GL11.glScaled(0.65D, 0.65D, 0.65D);
	        	GL11.glTranslatef(-this.rock.offsetX, -this.rock.offsetY, -this.rock.offsetZ);
	        	GL11.glTranslatef(-this.foilModel.rocky.rotationPointX * f5, -this.foilModel.rocky.rotationPointY * f5, -this.foilModel.rocky.rotationPointZ * f5);
	        	GL11.glTranslatef(this.rock.offsetX, this.rock.offsetY, this.rock.offsetZ);
	        	red = (this.rockMaterial[0]) / 255.0F;
		        green = (this.rockMaterial[1]) / 255.0F;
		        blue = (this.rockMaterial[2]) / 255.0F;
		        GL11.glColor4f(red + ((entity.ticksExisted%60) / 600), green + ((entity.ticksExisted%50) / 500), blue + ((entity.ticksExisted%40) / 400), 1.0F);
	        	this.foilModel.rocky.render(f5);
	        	GL11.glColor4f(red + ((entity.ticksExisted%40) / 400), green, blue + ((entity.ticksExisted%60) / 600), 1.0F);
	        	this.foilModel.rocky_1.render(f5);
	        	GL11.glColor4f(red + ((entity.ticksExisted%50) / 500), green + ((entity.ticksExisted%40) / 400), blue, 1.0F);
	        	this.foilModel.rocky_2.render(f5);
	        	GL11.glColor4f(red, green + ((entity.ticksExisted%60) / 600), blue + ((entity.ticksExisted%50) / 500), 1.0F);
	        	this.foilModel.rocky_3.render(f5);
	        	GL11.glColor4f(1F, 1F, 1F, 1F);
	        	GL11.glPopMatrix();
	        } else if (f >= 3) {
	        	GL11.glPushMatrix();
	        	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("qwertech:textures/entity/rock/ball.png"));
	        	GL11.glTranslatef(this.rock.offsetX, this.rock.offsetY, this.rock.offsetZ);
	        	GL11.glTranslatef(this.ballModel.rocky.rotationPointX * f5, this.ballModel.rocky.rotationPointY * f5, this.ballModel.rocky.rotationPointZ * f5);
	        	GL11.glScaled(0.55D, 0.55D, 0.55D);
	        	GL11.glTranslatef(-this.rock.offsetX, -this.rock.offsetY, -this.rock.offsetZ);
	        	GL11.glTranslatef(-this.ballModel.rocky.rotationPointX * f5, -this.ballModel.rocky.rotationPointY * f5, -this.ballModel.rocky.rotationPointZ * f5);
	        	GL11.glTranslatef((this.rock.rotationPointX * f5) + (2.5F * f5), (this.rock.rotationPointY * f5) + (7.5F * f5), (this.rock.rotationPointZ * f5));
	        	this.ballModel.render(entity, f, f1, f2, f3, f4, f5);
	        	GL11.glColor4f(1F, 1F, 1F, 1F);
	        	GL11.glPopMatrix();
	        }
        } else if (this.isLaunching) {
        	this.rand.setSeed(this.rand.nextLong());
        	this.foilModel = new ModelFoil(this.rand);
        	this.isLaunching = false;
        }
    }
    
    public void setStringPosition(float percent)
    {
    	if (percent != 0) percent = 72000 - percent;
    	percent = percent * 2;
    	float launchdist = Math.min(100, percent);
    	//if (launchdist > 90) launchdist += (new Random().nextInt(10) - 7);
        float[] angles = defaultString.clone();
        for (int q = 0; q < angles.length; q++)
        {
        	angles[q] = defaultString[q] + (((maximumString[q] - defaultString[q])/100) * launchdist);
        }
        this.String.setRotationPoint(angles[0], angles[1], angles[2]);
        this.setRotateAngle(this.String, angles[3], angles[4], angles[5]);
        this.StringLeft.setRotationPoint(angles[6], angles[7], angles[8]);
        this.setRotateAngle(this.StringLeft, angles[9], angles[10], angles[11]);
        this.StringRight.setRotationPoint(angles[6], -angles[7], -angles[8]);
        this.setRotateAngle(this.StringRight, -angles[9], -angles[10], -angles[11]);
        for (ModelRenderer mr : new ModelRenderer[] {this.rock, this.rockOverlay, this.foilModel.rocky, this.foilModel.rocky_1, this.foilModel.rocky_2, this.foilModel.rocky_3})
        {
        	mr.setRotationPoint(angles[12], angles[13], angles[14]);
        }
        this.setRotateAngle(this.rock, angles[15], angles[16], angles[17]);
        this.setRotateAngle(this.rockOverlay, angles[15], angles[16], angles[17]);
    }
    
    public void setHandleMaterial(OreDictMaterial material)
    {
    	this.primaryMaterial = material.mRGBaSolid;
    }
    
    public void setRockMaterial(OreDictMaterial material)
    {
    	this.rockMaterial = material.mRGBaSolid;
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
