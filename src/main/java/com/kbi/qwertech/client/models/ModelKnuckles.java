package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelKnuckles - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelKnuckles extends ModelBaseTool {
    public ModelRenderer knuckles;

    public ModelKnuckles() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.knuckles = new ModelRenderer(this, 0, 0);
        this.knuckles.setRotationPoint(12.0F, 3.5F, 2.5F);
        this.knuckles.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(knuckles, -1.5707963267948966F, 0.0F, 0.22689280275926282F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	this.knuckles.render(f5);
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
