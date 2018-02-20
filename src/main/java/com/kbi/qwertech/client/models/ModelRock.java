package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRock - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelRock extends ModelBase {
    public ModelRenderer rocky;

    public ModelRock() {
        this.textureWidth = 32;
        this.textureHeight = 8;
        this.rocky = new ModelRenderer(this, 0, 0);
        this.rocky.setRotationPoint(0F, 0F, 0F);
        this.rocky.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotateAngle(rocky, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rocky.render(f5);
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