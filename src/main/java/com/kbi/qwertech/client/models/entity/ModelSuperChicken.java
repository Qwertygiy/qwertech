package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.api.client.models.ModelRendererDefaults;
import com.kbi.qwertech.api.client.registry.AnimationHelper;
import com.kbi.qwertech.api.client.registry.AnimationsRegistry;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelSuperChicken - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelSuperChicken extends ModelChicken {
    public ModelRenderer cape;
    public ModelRenderer cape2;

    public ModelSuperChicken() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rightWing = new ModelRendererDefaults(this, 24, 13);
        this.rightWing.setRotationPoint(-4.0F, 14.0F, 0.0F);
        this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(rightWing, -0.2617993877991494F, 0.0F, 0.0F);
        this.bill = new ModelRendererDefaults(this, 14, 0);
        this.bill.setRotationPoint(0.0F, 15.0F, -2.5F);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
        this.cape2 = new ModelRendererDefaults(this, 40, 9);
        this.cape2.setRotationPoint(0.0F, 7.9F, 0.0F);
        this.cape2.addBox(-3.0F, 0.0F, -0.5F, 6, 4, 1, 0.0F);
        this.setRotateAngle(cape2, -0.17453292519943295F, 0.0F, 0.0F);
        this.chin = new ModelRendererDefaults(this, 18, 8);
        this.chin.setRotationPoint(0.0F, 15.0F, -2.5F);
        this.chin.addBox(-1.0F, -5.01F, -3.0F, 2, 5, 2, 0.0F);
        this.leftLeg = new ModelRendererDefaults(this, 26, 0);
        this.leftLeg.setRotationPoint(1.0F, 19.0F, 1.0F);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(leftLeg, -1.401298464324817E-45F, 0.0F, 0.0F);
        this.rightLeg = new ModelRendererDefaults(this, 26, 0);
        this.rightLeg.setRotationPoint(-2.0F, 19.0F, 1.0F);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(rightLeg, 1.401298464324817E-45F, 0.0F, 0.0F);
        this.body = new ModelRendererDefaults(this, 0, 9);
        this.body.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(body, 1.3089969389957472F, 0.0F, 0.0F);
        this.head = new ModelRendererDefaults(this, 0, 0);
        this.head.setRotationPoint(0.0F, 15.0F, -2.5F);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        this.leftWing = new ModelRendererDefaults(this, 24, 13);
        this.leftWing.setRotationPoint(4.0F, 14.0F, 0.0F);
        this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F);
        this.setRotateAngle(leftWing, -0.2617993877991494F, 0.0F, -0.0F);
        this.cape = new ModelRendererDefaults(this, 40, 0);
        this.cape.setRotationPoint(0.0F, 13.0F, -2.0F);
        this.cape.addBox(-3.0F, 0.0F, -0.5F, 6, 8, 1, 0.0F);
        this.setRotateAngle(cape, 1.3962634015954636F, 0.0F, 0.0F);
        this.cape.addChild(this.cape2);
        addBox((ModelRendererDefaults)this.cape, "cape");
        addBox((ModelRendererDefaults)this.cape2, "cape1-2");
        addBox((ModelRendererDefaults)this.body, "body");
        addBox((ModelRendererDefaults)this.leftLeg, "leftLeg");
        addBox((ModelRendererDefaults)this.rightLeg, "rightLeg");
        addBox((ModelRendererDefaults)this.leftWing, "leftWing");
        addBox((ModelRendererDefaults)this.rightWing, "rightWing");
        addBox((ModelRendererDefaults)this.head, "head");
        addBox((ModelRendererDefaults)this.bill, "bill");
        addBox((ModelRendererDefaults)this.chin, "chin");
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
            this.head.render(f5);
            this.bill.render(f5);
            this.chin.render(f5);
            this.cape.render(f5);
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
            this.rightWing.render(f5);
            this.bill.render(f5);
            this.chin.render(f5);
            this.leftLeg.render(f5);
            this.rightLeg.render(f5);
            this.body.render(f5);
            this.head.render(f5);
            this.leftWing.render(f5);
            this.cape.render(f5);
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
