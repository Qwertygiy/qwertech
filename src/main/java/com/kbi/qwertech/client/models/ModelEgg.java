package com.kbi.qwertech.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRock - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelEgg extends ModelBaseTool {
    public ModelRenderer rocky;
    public ModelRenderer rockyX;
    public ModelRenderer rockyY;
    public ModelRenderer rockyZ;
    public ModelRenderer rockyY2;

    public ModelEgg() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.rockyY = new ModelRenderer(this, 0, 20);
        this.rockyY.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyY.addBox(-5.0F, -2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(rockyY, 0.0F, 0.0F, 1.5707963267948966F);
        this.rocky = new ModelRenderer(this, 0, 0);
        this.rocky.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rocky.addBox(-2.5F, -4.5F, -2.5F, 5, 7, 5, 0.0F);
        this.rockyZ = new ModelRenderer(this, 0, 12);
        this.rockyZ.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyZ.addBox(-3.0F, -3.33F, -1.5F, 6, 5, 3, 0.0F);
        this.setRotateAngle(rockyZ, 0.0F, 1.5707963267948966F, 0.0F);
        this.rockyY2 = new ModelRenderer(this, 0, 28);
        this.rockyY2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyY2.addBox(-5.5F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(rockyY2, 0.0F, 0.0F, 1.5707963267948966F);
        this.rockyX = new ModelRenderer(this, 0, 12);
        this.rockyX.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rockyX.addBox(-3.0F, -3.33F, -1.5F, 6, 5, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rockyY.render(f5);
        this.rocky.render(f5);
        this.rockyZ.render(f5);
        this.rockyY2.render(f5);
        this.rockyX.render(f5);
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
