package com.kbi.qwertech.api.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererDefaults extends ModelRenderer {

    public float defaultOriginX;
    public float defaultOriginY;
    public float defaultOriginZ;
    public float defaultOffsetX;
    public float defaultOffsetY;
    public float defaultOffsetZ;
    public float defaultRotateX;
    public float defaultRotateY;
    public float defaultRotateZ;

    public ModelRendererDefaults(ModelBase p_i1172_1_, String p_i1172_2_) {
        super(p_i1172_1_, p_i1172_2_);
    }

    public ModelRendererDefaults(ModelBase p_i1173_1_) {
        super(p_i1173_1_);
    }

    public ModelRendererDefaults(ModelBase p_i1174_1_, int p_i1174_2_, int p_i1174_3_) {
        super(p_i1174_1_, p_i1174_2_, p_i1174_3_);
    }

    /**
     * Restore this box back to its default position, such as when an animation ends
     */
    public void restore()
    {
        this.rotationPointX = defaultOriginX;
        this.rotationPointY = defaultOriginY;
        this.rotationPointZ = defaultOriginZ;
        this.offsetX = defaultOffsetX;
        this.offsetY = defaultOffsetY;
        this.offsetZ = defaultOffsetZ;
        this.rotateAngleX = defaultRotateX;
        this.rotateAngleY = defaultRotateY;
        this.rotateAngleZ = defaultRotateZ;
    }

    /**
     * Sets the default rotation point for the box (top right front corner if no offset)
     */
    public void setDefaultOrigin(float x, float y, float z)
    {
        defaultOriginX = x;
        defaultOriginY = y;
        defaultOriginZ = z;
    }

    /**
     * Sets the default offset from rotation point for the box.
     */
    public void setDefaultOffset(float x, float y, float z)
    {
        defaultOffsetX = x;
        defaultOffsetY = y;
        defaultOffsetZ = z;
    }

    /**
     * Sets the default rotation values of the box.
     */
    public void setDefaultRotation(float x, float y, float z)
    {
        defaultRotateX = x;
        defaultRotateY = y;
        defaultRotateZ = z;
    }

    /**
     * Sets all default parameters of the box.
     */
    public void setDefaults(float originX, float originY, float originZ, float offsetX, float offsetY, float offsetZ, float rotateX, float rotateY, float rotateZ)
    {
        setDefaultOrigin(originX, originY, originZ);
        setDefaultOffset(offsetX, offsetY, offsetZ);
        setDefaultRotation(rotateX, rotateY, rotateZ);
    }
}
