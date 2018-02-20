package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelHammer - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelHammer extends ModelBaseTool {
    public ModelRenderer handle;
    public ModelRenderer smasher;

    public ModelHammer() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(12.0F, 5.5F, 0.0F);
        this.handle.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(handle, 0.0F, 0.0F, -1.9198621771937625F);
        this.smasher = new ModelRenderer(this, 0, 12);
        this.smasher.setRotationPoint(-2.0F, 13.8F, -3.0F);
        this.smasher.addBox(-2.0F, 0.0F, 0.0F, 10, 6, 6, 0.0F);
        this.setRotateAngle(smasher, 0.0F, 0.0F, -1.9198621771937625F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	applyColorSecondary();
    	this.handle.render(f5);
        applyColorPrimary();
        this.smasher.render(f5);
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
