package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.api.client.models.IModelAnimateable;
import com.kbi.qwertech.api.client.models.ModelRendererDefaults;
import com.kbi.qwertech.api.client.registry.AnimationHelper;
import com.kbi.qwertech.api.client.registry.AnimationsRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

public class ModelChicken extends net.minecraft.client.model.ModelChicken implements IModelAnimateable {

    private HashMap<String, ModelRendererDefaults> boxes = new HashMap<>();

    public ModelChicken()
    {
        byte b0 = 16;
        this.head = new ModelRendererDefaults(this, 0, 0);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.head.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
        this.bill = new ModelRendererDefaults(this, 14, 0);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
        this.bill.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
        this.chin = new ModelRendererDefaults(this, 14, 4);
        this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
        this.chin.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
        this.body = new ModelRendererDefaults(this, 0, 9);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
        this.body.setRotationPoint(0.0F, (float)b0, 0.0F);
        this.body.rotateAngleX = 1.5708F;
        this.rightLeg = new ModelRendererDefaults(this, 26, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.rightLeg.setRotationPoint(-2.0F, (float)(3 + b0), 1.0F);
        this.leftLeg = new ModelRendererDefaults(this, 26, 0);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.leftLeg.setRotationPoint(1.0F, (float)(3 + b0), 1.0F);
        this.rightWing = new ModelRendererDefaults(this, 24, 13);
        this.rightWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        this.rightWing.setRotationPoint(-3.0F, (float)(-3 + b0), 0.0F);
        this.leftWing = new ModelRendererDefaults(this, 24, 13);
        this.leftWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing.setRotationPoint(3.0F, (float)(-3 + b0), 0.0F);
        addBox((ModelRendererDefaults)this.head, "head");
        addBox((ModelRendererDefaults)this.bill, "bill");
        addBox((ModelRendererDefaults)this.chin, "chin");
        addBox((ModelRendererDefaults)this.body, "body");
        addBox((ModelRendererDefaults)this.rightLeg, "rightLeg");
        addBox((ModelRendererDefaults)this.leftLeg, "leftLeg");
        addBox((ModelRendererDefaults)this.rightWing, "rightWing");
        addBox((ModelRendererDefaults)this.leftWing, "leftWing");
        AnimationHelper.setAsDefault(this);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        AnimationHelper.reset(this);
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        AnimationsRegistry.setAnimations(p_78088_1_);

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
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity)
    {
        EntityLiving elb = (EntityLiving)entity;

        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.bill.rotateAngleX = this.head.rotateAngleX;
        this.bill.rotateAngleY = this.head.rotateAngleY;
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
        /*
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.rightWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F) * 1.4F * p_78087_2_;
        if (this.rightWing.rotateAngleZ < 0) this.rightWing.rotateAngleZ *= -1F;
        this.leftWing.rotateAngleZ = MathHelper.cos(p_78087_1_ * 0.666F + (float)Math.PI) * 1.4F * p_78087_2_;
        if (this.leftWing.rotateAngleZ > 0) this.leftWing.rotateAngleZ *= -1F;
        */
        if (!AnimationsRegistry.hasAnimation(entity, "breathe"))
        {
            AnimationsRegistry.addAnimation(entity, this, "breathe", 0, (short)50, true, false);
        }

        if (entity.onGround && (elb.limbSwingAmount > 0.1F))
        {
            //System.out.println("Trying to add walking");
            double speed = elb.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() - 0.25;
            speed = speed * 20;
            AnimationsRegistry.addAnimation(entity, this, "walk", 1, (short)(10 - Math.floor(speed)), true, false);
            AnimationsRegistry.addAnimation(entity, this, "chickenheadbop", 1, (short)(10 - Math.floor(speed)), true, false);
        } else if (entity.prevPosX == entity.posX && entity.prevPosZ == entity.posZ)
        {
            AnimationsRegistry.removeAnimation(entity, "walk", true);
            AnimationsRegistry.removeAnimation(entity, "chickenheadbop", true);
        }

        if (!entity.onGround && entity.fallDistance != 0)
        {
            AnimationsRegistry.addAnimation(entity, this, "fly", 1, (short)5, true, false);
        } else {
            AnimationsRegistry.removeAnimation(entity, "fly", true);
        }

        if (elb.hurtTime > 0)
        {
            AnimationsRegistry.addAnimation(entity, this, "chickenhurt", 10, (short)10, false, false);
        }
    }

    @Override
    public Map<String, ModelRendererDefaults> getBoxes() {
        return boxes;
    }

    @Override
    public void addBox(ModelRendererDefaults box, String name) {
        boxes.put(name, box);
    }

    @Override
    public ModelRendererDefaults getBox(String name) {
        return boxes.get(name);
    }

    @Override
    public float getVariable(Entity entity, String ID) {
        switch(ID)
        {
            case "walk": return ((EntityLiving)entity).limbSwingAmount;
            case "fly": return Math.min(2.0F, entity.fallDistance);
            default: return 1F;
        }
    }
}
