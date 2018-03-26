package com.kbi.qwertech.client.models;

import com.kbi.qwertech.entities.passive.EntityFrog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Iterator;

/**
 * ModelFrog - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelFrog extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Back;
    public ModelRenderer LeftEye;
    public ModelRenderer RightEye;
    public ModelRenderer UpperLeftRear;
    public ModelRenderer UpperRightRear;
    public ModelRenderer Arms;
    public ModelRenderer Head;
    public ModelRenderer MiddleRightRear;
    public ModelRenderer LowerRightRear;
    public ModelRenderer LowerRightFoot;
    public ModelRenderer MiddleRightRear_1;
    public ModelRenderer LowerRightRear_1;
    public ModelRenderer LowerRightFoot_1;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;
    public ModelRenderer Brim;
    public ModelRenderer Hat;
    public ModelRenderer Cane;
    
    public HashMap<ModelRenderer, float[]> emptyAngles = new HashMap();
    public HashMap<ModelRenderer, float[]> fullAngles = new HashMap();

    public ModelFrog() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.LowerRightRear_1 = new ModelRenderer(this, 12, 0);
        this.LowerRightRear_1.mirror = true;
        this.LowerRightRear_1.setRotationPoint(0.5F, 2.0F, 1.5F);
        this.LowerRightRear_1.addBox(-0.5F, -3.5F, -0.0F, 1, 4, 1, 0.0F);
        this.UpperRightRear = new ModelRenderer(this, 0, 22);
        this.UpperRightRear.mirror = true;
        this.UpperRightRear.setRotationPoint(1.9F, 20.5F, -3.0F);
        this.UpperRightRear.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(UpperRightRear, 1.0471975511965976F, 0.2617993877991494F, 0.0F);
        this.UpperLeftRear = new ModelRenderer(this, 0, 22);
        this.UpperLeftRear.setRotationPoint(-1.9F, 20.5F, -3.0F);
        this.UpperLeftRear.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(UpperLeftRear, 1.0471975511965976F, -0.2617993877991494F, 0.0F);
        this.Back = new ModelRenderer(this, 0, 18);
        this.Back.setRotationPoint(0.0F, 20.5F, -2.5F);
        this.Back.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(Back, 0.7853981633974483F, 0.0F, 0.0F);
        this.LowerRightFoot = new ModelRenderer(this, 9, 5);
        this.LowerRightFoot.mirror = true;
        this.LowerRightFoot.setRotationPoint(1.0F, -3.0F, 0.5F);
        this.LowerRightFoot.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 3, 0.0F);
        this.setRotateAngle(LowerRightFoot, 2.0943951023931953F, 0.0F, 0.0F);
        this.LowerRightFoot_1 = new ModelRenderer(this, 9, 5);
        this.LowerRightFoot_1.mirror = true;
        this.LowerRightFoot_1.setRotationPoint(0.0F, -3.0F, 0.5F);
        this.LowerRightFoot_1.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 3, 0.0F);
        this.setRotateAngle(LowerRightFoot_1, 2.0943951023931953F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 14, 9);
        this.LeftArm.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.LeftArm.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(LeftArm, 0.5235987755982988F, 0.0F, 0.0F);
        this.LowerRightRear = new ModelRenderer(this, 12, 0);
        this.LowerRightRear.mirror = true;
        this.LowerRightRear.setRotationPoint(-0.5F, 2.0F, 1.5F);
        this.LowerRightRear.addBox(-0.5F, -3.5F, -0.0F, 1, 4, 1, 0.0F);
        this.MiddleRightRear = new ModelRenderer(this, 4, 22);
        this.MiddleRightRear.mirror = true;
        this.MiddleRightRear.setRotationPoint(-0.4F, 2.0F, 0.0F);
        this.MiddleRightRear.addBox(-1.0F, -0.5F, -0.5F, 1, 3, 2, 0.0F);
        this.setRotateAngle(MiddleRightRear, 3.141592653589793F, 0.11623892818282235F, -0.2617993877991494F);
        this.Head = new ModelRenderer(this, 12, 16);
        this.Head.setRotationPoint(0.0F, 20.5F, 3.5F);
        this.Head.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(Head, 0.7853981633974483F, 0.0F, 0.0F);
        this.LeftEye = new ModelRenderer(this, 0, 0);
        this.LeftEye.setRotationPoint(-1.0F, 19.0F, 3.5F);
        this.LeftEye.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(LeftEye, 0.0F, 0.3490658503988659F, 0.0F);
        this.Arms = new ModelRenderer(this, 0, 6);
        this.Arms.setRotationPoint(0.0F, 22.0F, 2.5F);
        this.Arms.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(Arms, 1.5707963267948966F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 9);
        this.Body.setRotationPoint(0.0F, 20.5F, 0.0F);
        this.Body.addBox(-2.0F, -2.5F, -1.5F, 4, 6, 3, 0.0F);
        this.setRotateAngle(Body, 1.5707963267948966F, 0.0F, 0.0F);
        this.RightEye = new ModelRenderer(this, 0, 0);
        this.RightEye.mirror = true;
        this.RightEye.setRotationPoint(1.0F, 19.0F, 3.5F);
        this.RightEye.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(RightEye, 0.0F, -0.3490658503988659F, 0.0F);
        this.RightArm = new ModelRenderer(this, 14, 9);
        this.RightArm.mirror = true;
        this.RightArm.setRotationPoint(2.0F, 0.0F, 0.0F);
        this.RightArm.addBox(0.5F, -0.5F, -2.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(RightArm, 0.5235987755982988F, 0.0F, 0.0F);
        this.MiddleRightRear_1 = new ModelRenderer(this, 4, 22);
        this.MiddleRightRear_1.mirror = true;
        this.MiddleRightRear_1.setRotationPoint(0.4F, 2.0F, 0.0F);
        this.MiddleRightRear_1.addBox(0.0F, -0.5F, -0.5F, 1, 3, 2, 0.0F);
        this.setRotateAngle(MiddleRightRear_1, 3.141592653589793F, -0.11623892818282235F, 0.2617993877991494F);
        this.MiddleRightRear_1.addChild(this.LowerRightRear_1);
        this.LowerRightRear.addChild(this.LowerRightFoot);
        this.LowerRightRear_1.addChild(this.LowerRightFoot_1);
        this.Arms.addChild(this.LeftArm);
        this.MiddleRightRear.addChild(this.LowerRightRear);
        this.UpperLeftRear.addChild(this.MiddleRightRear);
        this.Arms.addChild(this.RightArm);
        this.UpperRightRear.addChild(this.MiddleRightRear_1);
        this.Brim = new ModelRenderer(this, 22, 7);
        this.Brim.setRotationPoint(0.0F, 18.25F, -2.5F);
        this.Brim.addBox(-2.0F, -2.0F, -0.5F, 4, 4, 1, 0.0F);
        this.setRotateAngle(Brim, -1.8325957145940461F, 0.0F, 0.0F);
        this.Hat = new ModelRenderer(this, 16, 0);
        this.Hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 4, 0.0F);
        this.Cane = new ModelRenderer(this, 10, 20);
        this.Cane.setRotationPoint(0.6F, 1.0F, 0.0F);
        this.Cane.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
        this.setRotateAngle(Cane, -0.5759586531581287F, 0.0F, 0.0F);
        this.LeftArm.addChild(this.Cane);
        this.Brim.addChild(this.Hat);
        
        this.setRotateAngleFull(Back, 0.7853981633974483F, 0.0F, 0.0F);
        this.setRotateAngleFull(UpperLeftRear, -0.8726646259971648F, 0.0F, 0.0F);
        this.setRotateAngleFull(LowerRightRear, -2.6179938779914944F, 0.0F, 0.0F);
        this.setRotateAngleFull(MiddleRightRear, -0.5235987755982988F, 0.05811946409141117F, -0.13962634015954636F);
        this.setRotateAngleFull(LowerRightRear_1, -2.6179938779914944F, 0.0F, 0.0F);
        this.setRotateAngleFull(RightEye, 0.0F, -0.3490658503988659F, 0.0F);
        this.setRotateAngleFull(MiddleRightRear_1, -0.5235987755982988F, -0.05811946409141117F, 0.13962634015954636F);
        this.setRotateAngleFull(LeftEye, 0.0F, 0.3490658503988659F, 0.0F);
        this.setRotateAngleFull(Head, 0.7853981633974483F, 0.0F, 0.0F);
        this.setRotateAngleFull(LeftArm, -0.5235987755982988F, 0.0F, 0.0F);
        this.setRotateAngleFull(LowerRightFoot_1, 2.0943951023931953F, 0.0F, 0.0F);
        this.setRotateAngleFull(RightArm, -0.5235987755982988F, 0.0F, 0.0F);
        this.setRotateAngleFull(LowerRightFoot, 2.0943951023931953F, 0.0F, 0.0F);
        this.setRotateAngleFull(Arms, 1.5707963267948966F, 0.0F, 0.0F);
        this.setRotateAngleFull(Body, 1.5707963267948966F, 0.0F, 0.0F);
        this.setRotateAngleFull(UpperRightRear, -0.8726646259971648F, 0.0F, 0.0F);
        this.setRotateAngleFull(Cane, -0.6F, 0.0F, 0.0F);
        this.setRotateAngleFull(Brim, -2F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity instanceof EntityFrog && ((EntityFrog)entity).getCustomNameTag().contains("Michigan"))
        {
            this.Brim.render(f5);
            this.Cane.showModel = true;
        } else {
            this.Cane.showModel = false;
        }

        GL11.glPushMatrix();
        GL11.glScalef(1, 1, -1);
    	this.UpperRightRear.render(f5);
        this.UpperLeftRear.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.Back.offsetX, this.Back.offsetY, this.Back.offsetZ);
        GL11.glTranslatef(this.Back.rotationPointX * f5, this.Back.rotationPointY * f5, this.Back.rotationPointZ * f5);
        GL11.glScaled(0.99D, 1.05D, 1.0D);
        GL11.glTranslatef(-this.Back.offsetX, -this.Back.offsetY, -this.Back.offsetZ);
        GL11.glTranslatef(-this.Back.rotationPointX * f5, -this.Back.rotationPointY * f5, -this.Back.rotationPointZ * f5);
        this.Back.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GL11.glTranslatef(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GL11.glScaled(0.99D, 1.05D, 1.5D);
        GL11.glTranslatef(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GL11.glTranslatef(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.LeftEye.offsetX, this.LeftEye.offsetY, this.LeftEye.offsetZ);
        GL11.glTranslatef(this.LeftEye.rotationPointX * f5, this.LeftEye.rotationPointY * f5, this.LeftEye.rotationPointZ * f5);
        GL11.glScaled(0.4D, 0.4D, 0.4D);
        GL11.glTranslatef(-this.LeftEye.offsetX, -this.LeftEye.offsetY, -this.LeftEye.offsetZ);
        GL11.glTranslatef(-this.LeftEye.rotationPointX * f5, -this.LeftEye.rotationPointY * f5, -this.LeftEye.rotationPointZ * f5);
        this.LeftEye.render(f5);
        GL11.glPopMatrix();
        this.Arms.render(f5);
        this.Body.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.RightEye.offsetX, this.RightEye.offsetY, this.RightEye.offsetZ);
        GL11.glTranslatef(this.RightEye.rotationPointX * f5, this.RightEye.rotationPointY * f5, this.RightEye.rotationPointZ * f5);
        GL11.glScaled(0.4D, 0.4D, 0.4D);
        GL11.glTranslatef(-this.RightEye.offsetX, -this.RightEye.offsetY, -this.RightEye.offsetZ);
        GL11.glTranslatef(-this.RightEye.rotationPointX * f5, -this.RightEye.rotationPointY * f5, -this.RightEye.rotationPointZ * f5);
        this.RightEye.render(f5);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void doTheRotate(ModelRenderer modelRenderer, float x, float y, float z)
    {
    	modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;	
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        emptyAngles.put(modelRenderer, new float[]{x, y, z});
    }
    
    public void setRotateAngleFull(ModelRenderer modelRenderer, float x, float y, float z) {
    	fullAngles.put(modelRenderer, new float[]{x, y, z});
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	Iterator<ModelRenderer> iterate = emptyAngles.keySet().iterator();
    	while (iterate.hasNext())
    	{
    		ModelRenderer box = iterate.next();
    		float[] emptyVec = emptyAngles.get(box);
    		float[] fullVec = fullAngles.get(box);
    		float swingamount = 1 - Math.min(entity.fallDistance, 1);
    		if (swingamount >= 1) 
    		{
    			this.doTheRotate(box, emptyVec[0], emptyVec[1], emptyVec[2]);
    		} else if (swingamount <= 0) {
    			this.doTheRotate(box, fullVec[0], fullVec[1], fullVec[2]);
    		} else {
	    		double x = emptyVec[0] + ((fullVec[0] - emptyVec[0])/swingamount);
	    		double y = emptyVec[1] + ((fullVec[1] - emptyVec[1])/swingamount);
	    		double z = emptyVec[2] + ((fullVec[2] - emptyVec[2])/swingamount);
	    		this.doTheRotate(box, (float)x, (float)y, (float)z);
    		}
    	}
    }
}
