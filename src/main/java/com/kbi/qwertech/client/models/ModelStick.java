package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelStick - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelStick extends ModelBaseTool {
    public ModelRenderer handle;

    public ModelStick() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(-0.33F, 0.0F, 0.0F);
        this.handle.addBox(-1.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.7853981633974483F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        applyColorPrimary();
    	this.handle.render(f5);
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
