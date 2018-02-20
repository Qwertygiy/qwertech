package com.kbi.qwertech.client.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRock - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelFoil extends ModelBase {
    public ModelRenderer rocky;
    public ModelRenderer rocky_1;
    public ModelRenderer rocky_2;
    public ModelRenderer rocky_3;
    public Random rand;
    
    public ModelFoil() {
    	this(new Random());
    }

    public ModelFoil(Random randy) {
    	this.rand = randy;
        this.textureWidth = 32;
        this.textureHeight = 8;
        this.rocky_2 = new ModelRenderer(this, 0, 0);
        this.rocky_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rocky_2.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(rocky_2, rand.nextFloat()/5 - 0.1F, 0.7853981633974483F, rand.nextFloat()/5 - 0.1F);
        this.rocky = new ModelRenderer(this, 0, 0);
        this.rocky.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rocky.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(rocky, 0.0F, rand.nextFloat()/5 - 0.1F, rand.nextFloat()/5 - 0.1F);
        this.rocky_1 = new ModelRenderer(this, 0, 0);
        this.rocky_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rocky_1.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(rocky_1, rand.nextFloat()/5 - 0.1F, rand.nextFloat()/5 - 0.1F, 0.7853981633974483F);
        this.rocky_3 = new ModelRenderer(this, 0, 0);
        this.rocky_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rocky_3.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4, 0.0F);
        this.setRotateAngle(rocky_3, 0.7853981633974483F, rand.nextFloat()/5 - 0.1F, rand.nextFloat()/5 - 0.1F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rocky_2.render(f5);
        this.rocky.render(f5);
        this.rocky_1.render(f5);
        this.rocky_3.render(f5);
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
