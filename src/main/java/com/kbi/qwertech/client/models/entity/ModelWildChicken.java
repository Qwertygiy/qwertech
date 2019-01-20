package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.api.client.models.IModelAnimateable;
import com.kbi.qwertech.api.client.models.ModelRendererDefaults;
import com.kbi.qwertech.api.client.registry.AnimationHelper;
import com.kbi.qwertech.api.client.registry.AnimationsRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelGallusGallusMurghi - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelWildChicken extends ModelBase implements IModelAnimateable {

    private HashMap<String, ModelRendererDefaults> boxes = new HashMap<>();

    public ModelRendererDefaults neck;
    public ModelRendererDefaults rightLeg;
    public ModelRendererDefaults leftLeg;
    public ModelRendererDefaults body;
    public ModelRendererDefaults leftWing;
    public ModelRendererDefaults rightWing;
    public ModelRendererDefaults tail;
    public ModelRendererDefaults head;
    public ModelRendererDefaults beak;
    public ModelRendererDefaults crest;
    public ModelRendererDefaults wattle;
    public ModelRendererDefaults tail2;

    public ModelWildChicken() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.beak = new ModelRendererDefaults(this, 14, 5);
        this.beak.setRotationPoint(0.0F, 0.5F, -2.0F);
        this.beak.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(beak, 0.7853981633974483F, 0.0F, 0.0F);
        this.tail = new ModelRendererDefaults(this, 34, 6);
        this.tail.setRotationPoint(0.0F, 14.4F, 3.9F);
        this.tail.addBox(-3.01F, -3.0F, -3.5F, 6, 7, 4, 0.0F);
        this.setRotateAngle(tail, 2.0943951023931953F, 0.0F, 0.0F);
        this.rightWing = new ModelRendererDefaults(this, 24, 13);
        this.rightWing.setRotationPoint(-4.0F, 13.25F, 1.0F);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(rightWing, -0.2617993877991494F, 0.0F, 0.0F);
        this.leftLeg = new ModelRendererDefaults(this, 26, 0);
        this.leftLeg.setRotationPoint(1.0F, 17.0F, 1.5F);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
        this.neck = new ModelRendererDefaults(this, 0, 0);
        this.neck.setRotationPoint(0.0F, 15.0F, -2.25F);
        this.neck.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.setRotateAngle(neck, 0.3490658503988659F, 0.0F, 0.0F);
        this.leftWing = new ModelRendererDefaults(this, 24, 13);
        this.leftWing.setRotationPoint(4.0F, 13.25F, 1.0F);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(leftWing, -0.2617993877991494F, 0.0F, -0.0F);
        this.tail2 = new ModelRendererDefaults(this, 38, 0);
        this.tail2.setRotationPoint(0.0F, 4.0F, 0.5F);
        this.tail2.addBox(-3.0F, 0.0F, -2.0F, 6, 3, 2, 0.0F);
        this.setRotateAngle(tail2, -0.5235987755982988F, 0.0F, 0.0F);
        this.body = new ModelRendererDefaults(this, 0, 9);
        this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 5, 0.0F);
        this.setRotateAngle(body, 1.3089969389957472F, 0.0F, 0.0F);
        this.head = new ModelRendererDefaults(this, 14, 0);
        this.head.setRotationPoint(0.0F, -5.0F, -0.5F);
        this.head.addBox(-1.5F, -1.0F, -2.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(head, -0.3490658503988659F, 0.0F, 0.0F);
        this.wattle = new ModelRendererDefaults(this, 18, 9);
        this.wattle.setRotationPoint(0.0F, 1.4F, -1.5F);
        this.wattle.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.rightLeg = new ModelRendererDefaults(this, 26, 0);
        this.rightLeg.setRotationPoint(-2.0F, 17.0F, 1.5F);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 7, 3, 0.0F);
        this.crest = new ModelRendererDefaults(this, 18, 19);
        this.crest.setRotationPoint(0.0F, -0.6F, -1.3F);
        this.crest.addBox(-0.5F, -2.0F, -1.5F, 1, 2, 4, 0.0F);
        this.setRotateAngle(crest, 0.3490658503988659F, 0.0F, 0.0F);
        this.head.addChild(this.beak);
        this.tail.addChild(this.tail2);
        this.neck.addChild(this.head);
        this.head.addChild(this.wattle);
        this.head.addChild(this.crest);
        this.addBox(this.head, "head1-2");
        this.addBox(this.leftWing, "leftWing");
        this.addBox(this.rightWing, "rightWing");
        this.addBox(this.neck, "head");
        this.addBox(this.body, "body");
        this.addBox(this.tail, "tail");
        this.addBox(this.beak, "head-beak");
        this.addBox(this.crest, "head-crest");
        this.addBox(this.wattle, "head-wattle");
        this.addBox(this.leftLeg, "leftLeg");
        this.addBox(this.rightLeg, "rightLeg");
        this.addBox(this.tail2, "tail2");
        AnimationHelper.setAsDefault(this);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        AnimationHelper.reset(this);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        AnimationsRegistry.setAnimations(entity);

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
    public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity)
    {
        EntityLiving elb = (EntityLiving)entity;

        this.neck.rotateAngleX = (f5 / (180F / (float)Math.PI)) + 0.35F;
        this.neck.rotateAngleY = f4 / (180F / (float)Math.PI);
        this.setRotateAngle(this.head, -0.35F, 0, 0);

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
