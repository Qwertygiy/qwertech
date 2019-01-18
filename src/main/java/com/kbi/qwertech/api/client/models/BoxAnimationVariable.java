package com.kbi.qwertech.api.client.models;

import java.util.HashMap;
import java.util.Map;

public class BoxAnimationVariable extends BoxAnimation {
    protected Map<Float, Float> vOriginX;
    protected Map<Float, Float> vOriginY;
    protected Map<Float, Float> vOriginZ;
    protected Map<Float, Float> vOffsetX;
    protected Map<Float, Float> vOffsetY;
    protected Map<Float, Float> vOffsetZ;
    protected Map<Float, Float> vRotateX;
    protected Map<Float, Float> vRotateY;
    protected Map<Float, Float> vRotateZ;

    public BoxAnimationVariable() {
        super();
        vOriginX = new HashMap<>();
        vOriginY = new HashMap<>();
        vOriginZ = new HashMap<>();
        vOffsetX = new HashMap<>();
        vOffsetY = new HashMap<>();
        vOffsetZ = new HashMap<>();
        vRotateX = new HashMap<>();
        vRotateY = new HashMap<>();
        vRotateZ = new HashMap<>();

        setOrigin(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setOffset(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotate(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setOrigin(1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setOffset(1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        setRotate(1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    protected void cleanUp(Map<Float, ?> map1, Map<Float, ?> map2)
    {
        Object firstly = map1.get(0.0F);
        for (float key : map1.keySet())
        {
            Object blob = map1.get(key);
            if (!blob.equals(firstly))
            {
                return; //there's a different value, we won't remove anything
            }
        }
        Object secondly = map2.get(0.0F);
        for (float key : map2.keySet())
        {
            Object blob = map2.get(key);
            if (!blob.equals(secondly))
            {
                return; //there's a different value, we won't remove anything
            }
        }
        map1.clear();
        map2.clear();
    }

    @Override
    protected void cleanUp() {
        cleanUp(rotateX, vRotateX);
        cleanUp(rotateY, vRotateY);
        cleanUp(rotateZ, vRotateZ);
        cleanUp(originX, vOriginX);
        cleanUp(originY, vOriginY);
        cleanUp(originZ, vOriginZ);
        cleanUp(offsetX, vOffsetX);
        cleanUp(offsetY, vOffsetY);
        cleanUp(offsetZ, vOffsetZ);
    }

    public float getOriginX(float time, float variable)
    {
        return (float)get(time, originX) + (variable * (float)get(time, vOriginX));
    }

    public float getOriginY(float time, float variable)
    {
        return (float)get(time, originY) + (variable * (float)get(time, vOriginY));
    }

    public float getOriginZ(float time, float variable)
    {
        return (float)get(time, originZ) + (variable * (float)get(time, vOriginZ));
    }

    public float getOffsetX(float time, float variable)
    {
        return (float)get(time, offsetX) + (variable * (float)get(time, vOffsetX));
    }

    public float getOffsetY(float time, float variable)
    {
        return (float)get(time, offsetY) + (variable * (float)get(time, vOffsetY));
    }

    public float getOffsetZ(float time, float variable)
    {
        return (float)get(time, offsetZ) + (variable * (float)get(time, vOffsetZ));
    }

    public float getRotateX(float time, float variable)
    {
        return (float)get(time, rotateX) + (variable * (float)get(time, vRotateX));
    }

    public float getRotateY(float time, float variable)
    {
        return (float)get(time, rotateY) + (variable * (float)get(time, vRotateY));
    }

    public float getRotateZ(float time, float variable)
    {
        return (float)get(time, rotateZ) + (variable * (float)get(time, vRotateZ));
    }

    public boolean setOriginX(float time, float amount, float variable)
    {
        return set(time, originX, amount) && set(time, vOriginX, variable);
    }

    public boolean setOriginY(float time, float amount, float variable)
    {
        return set(time, originY, amount) && set(time, vOriginY, variable);
    }

    public boolean setOriginZ(float time, float amount, float variable)
    {
        return set(time, originZ, amount) && set(time, vOriginZ, variable);
    }

    public boolean setOrigin(float time, float x, float y, float z, float variable)
    {
        return setOriginX(time, x, variable) && setOriginY(time, y, variable) && setOriginZ(time, z, variable);
    }

    public boolean setOffsetX(float time, float amount, float variable)
    {
        return set(time, offsetX, amount) && set(time, vOffsetX, variable);
    }

    public boolean setOffsetY(float time, float amount, float variable)
    {
        return set(time, offsetY, amount) && set(time, vOffsetY, variable);
    }

    public boolean setOffsetZ(float time, float amount, float variable)
    {
        return set(time, offsetZ, amount) && set(time, vOffsetZ, variable);
    }

    public boolean setOffset(float time, float x, float y, float z, float variable)
    {
        return setOffsetX(time, x, variable) && setOffsetY(time, y, variable) && setOffsetZ(time, z, variable);
    }

    public boolean setRotateX(float time, float amount, float variable)
    {
        return set(time, rotateX, amount) && set(time, vRotateX, variable);
    }

    public boolean setRotateY(float time, float amount, float variable)
    {
        return set(time, rotateY, amount) && set(time, vRotateY, variable);
    }

    public boolean setRotateZ(float time, float amount, float variable)
    {
        return set(time, rotateZ, amount) && set(time, vRotateZ, variable);
    }

    public boolean setRotate(float time, float x, float y, float z, float variable)
    {
        return setRotateX(time, x, variable) && setRotateY(time, y, variable) && setRotateZ(time, z, variable);
    }

    /**
     * Calculates and applies the rotation and positioning for the given time upon the given box.
     * @param box The model box to animate.
     * @param time The time of the animation, from 0.0F at start to 1.0F at end.
     * @param variable A variable passed on from the entity
     */
    @Override
    public void apply(ModelRendererDefaults box, float time, float variable)
    {
        if (should(originX)) box.rotationPointX = box.defaultRotateX + getRotateX(time, variable);
        if (should(originY)) box.rotationPointY = box.defaultRotateY + getRotateY(time, variable);
        if (should(originZ)) box.rotationPointZ = box.defaultRotateZ + getRotateZ(time, variable);
        if (should(offsetX)) box.offsetX = box.defaultOffsetX + getOffsetX(time, variable);
        if (should(offsetY)) box.offsetY = box.defaultOffsetY + getOffsetY(time, variable);
        if (should(offsetZ)) box.offsetZ = box.defaultOffsetZ + getOffsetZ(time, variable);
        if (should(rotateX)) box.rotateAngleX = box.defaultRotateX + getRotateX(time, variable);
        if (should(rotateY)) box.rotateAngleY = box.defaultRotateY + getRotateY(time, variable);
        if (should(rotateZ)) box.rotateAngleZ = box.defaultRotateZ + getRotateZ(time, variable);
        if (should(visible)) box.isHidden = !getVisible(time);
    }

    /**
     * If this animation has been locked to a model, animate that model for the given time.
     * @param time The time of the animation, from 0.0F at start to 1.0F at end.
     * @param variable A variable passed on from the entity
     * @return True if successful, false if unlocked.
     */
    @Override
    public boolean apply(float time, float variable)
    {
        if (box == null) return false;
        if (should(originX)) box.rotationPointX = box.defaultRotateX + getRotateX(time, variable);
        if (should(originY)) box.rotationPointY = box.defaultRotateY + getRotateY(time, variable);
        if (should(originZ)) box.rotationPointZ = box.defaultRotateZ + getRotateZ(time, variable);
        if (should(offsetX)) box.offsetX = box.defaultOffsetX + getOffsetX(time, variable);
        if (should(offsetY)) box.offsetY = box.defaultOffsetY + getOffsetY(time, variable);
        if (should(offsetZ)) box.offsetZ = box.defaultOffsetZ + getOffsetZ(time, variable);
        if (should(rotateX)) box.rotateAngleX = box.defaultRotateX + getRotateX(time, variable);
        if (should(rotateY)) box.rotateAngleY = box.defaultRotateY + getRotateY(time, variable);
        if (should(rotateZ)) box.rotateAngleZ = box.defaultRotateZ + getRotateZ(time, variable);
        if (should(visible)) box.isHidden = !getVisible(time);
        return true;
    }
}
