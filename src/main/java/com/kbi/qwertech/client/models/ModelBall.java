package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelRock - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelBall extends ModelBase {
    public ModelRenderer rocky;
    public ModelRenderer rockyX;
    public ModelRenderer rockyY;
    public ModelRenderer rockyZ;
    public ModelRenderer rockyX2;
    public ModelRenderer rockyY2;
    public ModelRenderer rockyZ2;

    public ModelBall() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.rockyZ2 = new ModelRenderer(this, 0, 18);
        this.rockyZ2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyZ2.addBox(-3.5F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(rockyZ2, 0.0F, 1.5707963267948966F, 0.0F);
        this.rockyX = new ModelRenderer(this, 0, 10);
        this.rockyX.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyX.addBox(-3.0F, -2.0F, -2.0F, 6, 4, 4, 0.0F);
        this.rockyX2 = new ModelRenderer(this, 0, 18);
        this.rockyX2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyX2.addBox(-3.5F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.rockyY2 = new ModelRenderer(this, 0, 18);
        this.rockyY2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyY2.addBox(-3.5F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(rockyY2, 0.0F, 0.0F, 1.5707963267948966F);
        this.rockyY = new ModelRenderer(this, 0, 10);
        this.rockyY.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyY.addBox(-3.0F, -2.0F, -2.0F, 6, 4, 4, 0.0F);
        this.setRotateAngle(rockyY, 0.0F, 0.0F, 1.5707963267948966F);
        this.rocky = new ModelRenderer(this, 0, 0);
        this.rocky.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rocky.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(rocky, 0.7853981633974483F, 0.7853981633974483F, 0.7853981633974483F);
        this.rockyZ = new ModelRenderer(this, 0, 10);
        this.rockyZ.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyZ.addBox(-3.0F, -2.0F, -2.0F, 6, 4, 4, 0.0F);
        this.setRotateAngle(rockyZ, 0.0F, 1.5707963267948966F, 0.0F);
        this.rocky.addChild(this.rockyZ2);
        this.rocky.addChild(this.rockyX);
        this.rocky.addChild(this.rockyX2);
        this.rocky.addChild(this.rockyY2);
        this.rocky.addChild(this.rockyY);
        this.rocky.addChild(this.rockyZ);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	short[] l = new short[]{255, 255, 255, 255};
    	boolean blend = false;
    	switch((int)f)
    	{
	    	case 3:
	    	{
	    		l = new short[]{255, 245, 230, 255};
	    		break;
	    	}
	    	case 4:
	    	{
	    		l = new short[]{100, 250, 100, 50};
	    		blend = GL11.glIsEnabled(GL11.GL_BLEND);
	    		GL11.glEnable(GL11.GL_BLEND);
	    		break;
	    	}
	    	case 5:
	    	{
	    		l = new short[]{30, 10, 0, 255};
	    		break;
	    	}
	    	case 6:
	    	{
	    		l = new short[]{222, 0, 0, 255};
	    		break;
	    	}
    	}
    	//System.out.println("Render Material is " + entity.getMaterial().mNameLocal);
        float g5 = (l[0]) / 255.0F;
        float g6 = (l[1]) / 255.0F;
        float g7 = (l[2]) / 255.0F;
        float g8 = (l[3]) / 255.0F;
        GL11.glColor4f(g5, g6, g7, g8);
    	this.rocky.render(f5);
    	if (f == 4)
    	{
    		if (!blend)
    		{
    			GL11.glDisable(GL11.GL_BLEND);
    		}
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
