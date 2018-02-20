package com.kbi.qwertech.api.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelArmorBasic - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelArmorBasic extends ModelBiped {
    public ModelRenderer bipedLeftFoot;
    public ModelRenderer bipedRightFoot;
    public ModelRenderer bipedLeftHand;
    public ModelRenderer bipedLeftShoulder;
    public ModelRenderer bipedRightHand;
    public ModelRenderer bipedRightShoulder;
    public ModelRenderer bipedBelt;
    
    public ModelArmorBasic() {
    	this(0.0F);
    }

    public ModelArmorBasic(float scale) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        this.bipedLeftFoot = new ModelRenderer(this, 0, 32);
        this.bipedLeftFoot.mirror = true;
        this.bipedLeftFoot.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftFoot.addBox(-2.5F, 5.0F, -2.5F, 5, 7, 5, scale);
        this.bipedRightFoot = new ModelRenderer(this, 0, 32);
        this.bipedRightFoot.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightFoot.addBox(-2.5F, 5.0F, -2.5F, 5, 7, 5, scale);
        this.bipedLeftShoulder = new ModelRenderer(this, 20, 32);
        this.bipedLeftShoulder.mirror = true;
        this.bipedLeftShoulder.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftShoulder.addBox(-1.5F, -2.3F, -2.5F, 5, 4, 5, scale);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale + 0.5F);
        this.bipedRightShoulder = new ModelRenderer(this, 20, 32);
        this.bipedRightShoulder.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightShoulder.addBox(-3.5F, -2.3F, -2.5F, 5, 4, 5, scale);
        this.bipedEars = new ModelRenderer(this, 24, 0);
        this.bipedEars.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, scale);
        this.bipedBelt = new ModelRenderer(this, 16, 41);
        this.bipedBelt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBelt.addBox(-4.5F, 9.0F, -2.5F, 9, 3, 5, scale);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
        this.bipedLeftHand = new ModelRenderer(this, 40, 32);
        this.bipedLeftHand.mirror = true;
        this.bipedLeftHand.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftHand.addBox(-1.5F, 3.3F, -2.5F, 5, 7, 5, scale);
        this.bipedRightHand = new ModelRenderer(this, 40, 32);
        this.bipedRightHand.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightHand.addBox(-3.5F, 3.3F, -2.5F, 5, 7, 5, scale);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scale);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scale);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	
    	this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedBody.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedHeadwear.render(f5);
        this.bipedHead.render(f5);
        
        copyAngleTo(bipedBody, bipedBelt);
        copyAngleTo(bipedLeftLeg, bipedLeftFoot);
        copyAngleTo(bipedLeftArm, bipedLeftShoulder);
        copyAngleTo(bipedLeftArm, bipedLeftHand);
        copyAngleTo(bipedRightLeg, bipedRightFoot);
        copyAngleTo(bipedRightArm, bipedRightHand);
        copyAngleTo(bipedRightArm, bipedRightShoulder);
        
        this.bipedBelt.render(f5);
        this.bipedLeftFoot.render(f5);
        this.bipedLeftShoulder.render(f5);
        this.bipedLeftHand.render(f5);
        this.bipedRightFoot.render(f5);
        this.bipedRightShoulder.render(f5);
        this.bipedRightHand.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void copyAngleTo(ModelRenderer hasAngles, ModelRenderer needsAngles)
    {
    	setRotateAngle(needsAngles, hasAngles.rotateAngleX, hasAngles.rotateAngleY, hasAngles.rotateAngleZ);
    }
}
