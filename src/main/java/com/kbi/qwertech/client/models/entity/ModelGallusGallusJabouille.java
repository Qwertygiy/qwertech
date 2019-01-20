package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.api.client.models.ModelRendererDefaults;
import com.kbi.qwertech.api.client.registry.AnimationHelper;
import net.minecraft.entity.Entity;

/**
 * ModelGallusGallusJabouille - Qwertygiy
 * Created using Tabula 4.1.1
 */
public class ModelGallusGallusJabouille extends ModelWildChicken {

    public ModelGallusGallusJabouille() {
        super();
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.crest = new ModelRendererDefaults(this, 18, 23);
        this.crest.setRotationPoint(0.0F, -0.6F, -1.3F);
        this.crest.addBox(-0.5F, -2.0F, -1.5F, 1, 2, 4, 0.0F);
        this.setRotateAngle(crest, 0.17453292519943295F, 0.0F, 0.0F);
        this.neck = new ModelRendererDefaults(this, 0, 0);
        this.neck.setRotationPoint(0.0F, 15.0F, -2.25F);
        this.neck.addBox(-2.0F, -5.0F, -2.0F, 4, 5, 3, 0.0F);
        this.setRotateAngle(neck, 0.17453292519943295F, 0.0F, 0.0F);
        this.head.setRotationPoint(0.0F, -4.0F, -0.5F);
        this.setRotateAngle(head, -0.17453292519943295F, 0.0F, 0.0F);
        this.body = new ModelRendererDefaults(this, 0, 9);
        this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -4.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(body, 1.3089969389957472F, 0.0F, 0.0F);
        this.head.addChild(this.crest);
        this.neck.addChild(this.head);
        addBox(this.body, "body");
        addBox(this.neck, "head");
        addBox(this.crest, "head-crest");
        AnimationHelper.setAsDefault(this);
    }

    @Override
    public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity)
    {
        super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
        this.neck.rotateAngleX = (f5 / (180F / (float)Math.PI)) + 0.175F;
        this.neck.rotateAngleY = f4 / (180F / (float)Math.PI);
    }
}
