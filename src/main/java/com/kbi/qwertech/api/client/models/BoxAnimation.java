package com.kbi.qwertech.api.client.models;

import java.util.*;

public class BoxAnimation {

    protected Map<Float, Float> originX;
    protected Map<Float, Float> originY;
    protected Map<Float, Float> originZ;
    protected Map<Float, Float> offsetX;
    protected Map<Float, Float> offsetY;
    protected Map<Float, Float> offsetZ;
    protected Map<Float, Float> rotateX;
    protected Map<Float, Float> rotateY;
    protected Map<Float, Float> rotateZ;
    protected Map<Float, Boolean> visible;
    protected List<Float> activeTimes;
    private ModelRendererDefaults box;

    private boolean cleanedUp = false;

    public BoxAnimation()
    {
        originX = new HashMap<>();
        originY = new HashMap<>();
        originZ = new HashMap<>();
        offsetX = new HashMap<>();
        offsetY = new HashMap<>();
        offsetZ = new HashMap<>();
        rotateX = new HashMap<>();
        rotateY = new HashMap<>();
        rotateZ = new HashMap<>();
        visible = new HashMap<>();

        setOrigin(0.0F, 0.0F, 0.0F, 0.0F);
        setOffset(0.0F, 0.0F, 0.0F, 0.0F);
        setRotate(0.0F, 0, 0, 0);
        setVisible(0.0F, true);
        setOrigin(1.0F, 0.0F, 0.0F, 0.0F);
        setOffset(1.0F, 0.0F, 0.0F, 0.0F);
        setRotate(1.0F, 0, 0, 0);
        setVisible(1.0F, true);
    }

    /**
     * Not necessary, but if you intend to lock this animation to a specific instance of a model, this will do so.
     * @param aBox The box to be locked in.
     */
    public void setBox(ModelRendererDefaults aBox)
    {
        box = aBox;
    }

    /**
     * If this animation has been locked to a model, it will return the box attached.
     * @return the box this animation is locked to, if it exists
     */
    public ModelRendererDefaults getBox()
    {
        return box;
    }

    /**
     * The backbone of the whole system. Returns interpolated values for any time value.
     * @param time From 0.0F at "start" to 1.0F at "end".
     * @param map The map containing values which is to be queried.
     * @return The value interpolated between the values of the map closest to the given time.
     */
    private Object get(float time, Map map)
    {
        if (time < 0.0F || time > 1.0F)
        {
            time = time % 1.0F;
            if (time < 0.0F)
            {
                time = 1.0F - time;
            }
        }
        try {
            if (map.containsKey(time)) {
                return map.get(time);
            } else {
                int previous = -1;
                int next = -1;
                int closest = -1;
                int spot = -1;
                if (activeTimes.contains(time)) {
                    spot = activeTimes.indexOf(time);
                } else {
                    for (int q = 0; q < activeTimes.size(); q++)
                    {
                        if (activeTimes.get(q) > time)
                        {
                            if (map.containsKey(activeTimes.get(q - 1))) {
                                previous = q - 1;
                            }
                            closest = q - 1;
                            spot = q - 1;
                            break;
                        }
                    }
                }
                while (previous == -1 && spot > -1)
                {
                    spot = spot - 1;
                    float lastCheck = activeTimes.get(spot);
                    if (map.containsKey(lastCheck))
                    {
                        previous = spot;
                        break;
                    }
                }
                spot = closest;
                while (spot < activeTimes.size())
                {
                    spot = spot + 1;
                    float lastCheck = activeTimes.get(spot);
                    if (map.containsKey(lastCheck))
                    {
                        next = spot;
                        break;
                    }
                }
                float pastTime = activeTimes.get(previous);
                float futureTime = activeTimes.get(next);
                Object pastValue = map.get(pastTime);
                Object futureValue = map.get(futureTime);
                float percentagePassed = (time - pastTime) / (futureTime - pastTime);
                if (pastValue instanceof Boolean)
                {
                    return pastValue;
                } else if (pastValue instanceof Float)
                {
                    return ((float)futureValue) - (((float)pastValue) * (1 - percentagePassed));
                } else if (pastValue instanceof Integer)
                {
                    return Math.round(((float)futureValue) - (((float)pastValue) * (1 - percentagePassed)));
                }
            }
        } catch (Exception e)
        {
            System.out.println("Error in accessing animation");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds the given value to the given map at the set time, and also adds the time to the list of frames, if it doesn't already exist.
     * @param time 0.0F at "start", 1.0F at "end".
     * @param map The map containing the value type to use.
     * @param value The value to be added at the given time.
     * @return True if successful.
     */
    private boolean set(float time, Map map, Object value)
    {
        if (time < 0.0F || time > 1.0F)
        {
            return false;
        }
        try {
            map.put(time, value);
            if (!activeTimes.contains(time)) {
                activeTimes.add(time);
                Collections.sort(activeTimes);
            }
            return true;
        } catch(Exception e)
        {
            System.out.println("Error in setting animation;");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes any unchanged parameters. If one never changes, there is no need to calculate it.
     * @param map The map of values to clean up, if the values contained are all identical.
     */
    private void cleanUp(Map<Float, ?> map)
    {
        Object firstly = map.get(0.0F);
        List<Float> canRemove = new ArrayList<>();
        for (float key : map.keySet())
        {
            if (map.get(key) != firstly)
            {
                return; //there's a different value, we won't remove anything
            }
            if (key != 0.0F && key != 1.0F) canRemove.add(key); //but if none are found, we'll stick it in there
        }
        for (float keys : canRemove)
        {
            map.remove(keys);
        }
    }

    /**
     * Clean ALL the things!
     */
    private void cleanUp()
    {
        cleanUp(rotateX);
        cleanUp(rotateY);
        cleanUp(rotateZ);
        cleanUp(originX);
        cleanUp(originY);
        cleanUp(originZ);
        cleanUp(offsetX);
        cleanUp(offsetY);
        cleanUp(offsetZ);
    }

    /**
     * Checks whether we should bother calculating this stat or not.
     * @param map The map containing the stat to check.
     * @return True if we should, false if it's empty.
     */
    private boolean should(Map<Float, ?> map)
    {
        if (!cleanedUp)
        {
            cleanUp();
            cleanedUp = true;
        }
        return !(map.size() == 2 && map.get(0.0F) == map.get(1.0F));
    }

    public boolean getVisible(float time)
    {
        return (boolean)get(time, visible);
    }

    public float getOriginX(float time)
    {
        return (float)get(time, originX);
    }

    public float getOriginY(float time)
    {
        return (float)get(time, originY);
    }

    public float getOriginZ(float time)
    {
        return (float)get(time, originZ);
    }

    public float getOffsetX(float time)
    {
        return (float)get(time, offsetX);
    }

    public float getOffsetY(float time)
    {
        return (float)get(time, offsetY);
    }

    public float getOffsetZ(float time)
    {
        return (float)get(time, offsetZ);
    }

    public float getRotateX(float time)
    {
        return (float)get(time, rotateX);
    }

    public float getRotateY(float time)
    {
        return (float)get(time, rotateY);
    }

    public float getRotateZ(float time)
    {
        return (float)get(time, rotateZ);
    }

    public boolean setVisible(float time, boolean visibility)
    {
        return set(time, visible, visibility);
    }

    public boolean setOriginX(float time, float amount)
    {
        return set(time, originX, amount);
    }

    public boolean setOriginY(float time, float amount)
    {
        return set(time, originY, amount);
    }

    public boolean setOriginZ(float time, float amount)
    {
        return set(time, originZ, amount);
    }

    public boolean setOrigin(float time, float x, float y, float z)
    {
        return setOriginX(time, x) && setOriginY(time, y) && setOriginZ(time, z);
    }

    public boolean setOffsetX(float time, float amount)
    {
        return set(time, offsetX, amount);
    }

    public boolean setOffsetY(float time, float amount)
    {
        return set(time, offsetY, amount);
    }

    public boolean setOffsetZ(float time, float amount)
    {
        return set(time, offsetZ, amount);
    }

    public boolean setOffset(float time, float x, float y, float z)
    {
        return setOffsetX(time, x) && setOffsetY(time, y) && setOffsetZ(time, z);
    }

    public boolean setRotateX(float time, float amount)
    {
        return set(time, rotateX, amount);
    }

    public boolean setRotateY(float time, float amount)
    {
        return set(time, rotateY, amount);
    }

    public boolean setRotateZ(float time, float amount)
    {
        return set(time, rotateZ, amount);
    }

    public boolean setRotate(float time, float x, float y, float z)
    {
        return setRotateX(time, x) && setRotateY(time, y) && setRotateZ(time, z);
    }

    /**
     * Calculates and applies the rotation and positioning for the given time upon the given box.
     * @param box The model box to animate.
     * @param time The time of the animation, from 0.0F at start to 1.0F at end.
     */
    public void apply(ModelRendererDefaults box, float time)
    {
        if (should(originX)) box.rotationPointX = box.defaultRotateX + getRotateX(time);
        if (should(originY)) box.rotationPointY = box.defaultRotateY + getRotateY(time);
        if (should(originZ)) box.rotationPointZ = box.defaultRotateZ + getRotateZ(time);
        if (should(offsetX)) box.offsetX = box.defaultOffsetX + getOffsetX(time);
        if (should(offsetY)) box.offsetY = box.defaultOffsetY + getOffsetY(time);
        if (should(offsetZ)) box.offsetZ = box.defaultOffsetZ + getOffsetZ(time);
        if (should(rotateX)) box.rotateAngleX = box.defaultRotateX + getRotateX(time);
        if (should(rotateY)) box.rotateAngleY = box.defaultRotateY + getRotateY(time);
        if (should(rotateZ)) box.rotateAngleZ = box.defaultRotateZ + getRotateZ(time);
        if (should(visible)) box.isHidden = !getVisible(time);
    }

    /**
     * If this animation has been locked to a model, animate that model for the given time.
     * @param time The time of the animation, from 0.0F at start to 1.0F at end.
     * @return True if successful, false if unlocked.
     */
    public boolean apply(float time)
    {
        if (box == null) return false;
        if (should(originX)) box.rotationPointX = box.defaultRotateX + getRotateX(time);
        if (should(originY)) box.rotationPointY = box.defaultRotateY + getRotateY(time);
        if (should(originZ)) box.rotationPointZ = box.defaultRotateZ + getRotateZ(time);
        if (should(offsetX)) box.offsetX = box.defaultOffsetX + getOffsetX(time);
        if (should(offsetY)) box.offsetY = box.defaultOffsetY + getOffsetY(time);
        if (should(offsetZ)) box.offsetZ = box.defaultOffsetZ + getOffsetZ(time);
        if (should(rotateX)) box.rotateAngleX = box.defaultRotateX + getRotateX(time);
        if (should(rotateY)) box.rotateAngleY = box.defaultRotateY + getRotateY(time);
        if (should(rotateZ)) box.rotateAngleZ = box.defaultRotateZ + getRotateZ(time);
        if (should(visible)) box.isHidden = !getVisible(time);
        return true;
    }

    /**
     * Restore the box to defaults, if we could have changed anything there
     * @param box The box to restore
     */
    public void restore(ModelRendererDefaults box)
    {
        if (should(originX)) box.rotationPointX = box.defaultRotateX;
        if (should(originY)) box.rotationPointY = box.defaultRotateY;
        if (should(originZ)) box.rotationPointZ = box.defaultRotateZ;
        if (should(offsetX)) box.offsetX = box.defaultOffsetX;
        if (should(offsetY)) box.offsetY = box.defaultOffsetY;
        if (should(offsetZ)) box.offsetZ = box.defaultOffsetZ;
        if (should(rotateX)) box.rotateAngleX = box.defaultRotateX;
        if (should(rotateY)) box.rotateAngleY = box.defaultRotateY;
        if (should(rotateZ)) box.rotateAngleZ = box.defaultRotateZ;
        if (should(visible)) box.isHidden = box.defaultHidden;
    }

    /**
     * If locked to a box, restore anything we changed about that box.
     * @return true if locked to a box.
     */
    public boolean restore()
    {
        if (box == null) return false;
        if (should(originX)) box.rotationPointX = box.defaultRotateX;
        if (should(originY)) box.rotationPointY = box.defaultRotateY;
        if (should(originZ)) box.rotationPointZ = box.defaultRotateZ;
        if (should(offsetX)) box.offsetX = box.defaultOffsetX;
        if (should(offsetY)) box.offsetY = box.defaultOffsetY;
        if (should(offsetZ)) box.offsetZ = box.defaultOffsetZ;
        if (should(rotateX)) box.rotateAngleX = box.defaultRotateX;
        if (should(rotateY)) box.rotateAngleY = box.defaultRotateY;
        if (should(rotateZ)) box.rotateAngleZ = box.defaultRotateZ;
        if (should(visible)) box.isHidden = box.defaultHidden;
        return true;
    }
}
