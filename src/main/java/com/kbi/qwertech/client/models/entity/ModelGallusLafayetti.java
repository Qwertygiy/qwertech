package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.api.client.registry.AnimationHelper;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGallusLafayetti - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelGallusLafayetti extends ModelWildChicken {

    public ModelGallusLafayetti() {
        super();
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.setRotateAngle(tail, 1.9198621771937625F, 0.0F, 0.0F);
        this.setRotateAngle(tail2, -0.3490658503988659F, 0.0F, 0.0F);
        this.setRotateAngle(neck, 0.3490658503988659F, 0.0F, 0.0F);
        this.setRotateAngle(head, -0.3490658503988659F, 0.0F, 0.0F);

        this.wattle.setTextureOffset(18, 9);
        this.wattle.setRotationPoint(0.0F, 1.7F, -1.0F);
        this.wattle.cubeList.clear();
        this.wattle.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(wattle, -0.3490658503988659F, 0.0F, 0.0F);

        this.crest.setTextureOffset(17, 19);
        this.crest.setRotationPoint(0.0F, -0.6F, -0.5F);
        this.crest.cubeList.clear();
        this.crest.addBox(-0.5F, -2.0F, -2.0F, 1, 2, 5, 0.0F);
        this.setRotateAngle(crest, 0.7853981633974483F, 0.0F, 0.0F);

        this.head.childModels.clear();
        this.head.addChild(this.crest);
        this.head.addChild(this.wattle);
        this.head.addChild(this.beak);
        AnimationHelper.setAsDefault(this);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        super.render(entity, f, f1, f2, f3, f4, f5);
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
