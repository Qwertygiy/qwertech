package com.kbi.qwertech.api.client.models;

import net.minecraft.client.model.ModelBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ModelAnimation {

    private int id = -1;
    private String name;
    private HashMap<String, BoxAnimation> boxes = new HashMap<>();
    private IModelAnimateable model;

    /**
     * ModelAnimation is created as blank slate.
     */
    public ModelAnimation()
    {
    }

    /**
     * Not recommended, but if you decide to lock this animation to an instance of a model, this will do so.
     * @param aModel The model to be locked to.
     */
    public void setModel(IModelAnimateable aModel)
    {
        model = aModel;
    }

    /**
     * If this animation has been locked to an instance of a model, returns that model.
     * @return The model locked to.
     */
    public IModelAnimateable getModel()
    {
        return model;
    }

    /**
     * Adds a box timeline to this animation.
     * @param key The ID of the IModelAnimateable's box that the animation should apply to.
     * @param box The animation to be applied.
     */
    public void addPart(String key, BoxAnimation box)
    {
        boxes.put(key, box);
    }

    /**
     * Retrieves the animation for a given key.
     * @param key The ID of the IModelAnimateable's box that the animation being queried will affect.
     * @return The animation.
     */
    public BoxAnimation getPart(String key)
    {
        return boxes.get(key);
    }

    /**
     * Retrieves the list of all animation timelines included in this model animation.
     * @return The HashMap containing box IDs and animations.
     */
    public HashMap<String, BoxAnimation> getBoxes()
    {
        return boxes;
    }

    /**
     * The ID of this animation in the AnimationRegistry.
     * @return The ID. -1 if not registered.
     */
    public int getID()
    {
        return id;
    }

    /**
     * Sets the internal ID of this animation. DO NOT CALL! Use AnimationRegistry.registerAnimation instead.
     * @param ID The ID to be applied.
     * @return True if not already set.
     */
    public boolean setID(int ID)
    {
        if (id == -1)
        {
            id = ID;
            return true;
        }
        return false;
    }

    /**
     * The key of this animation in the AnimationRegistry.
     * @return The key. Null if not registered.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the internal name of this animation. DO NOT CALL! Use AnimationRegistry.registerAnimation instead.
     * @param NAME The name to be applied.
     * @return True if not already set.
     */
    public boolean setName(String NAME)
    {
        if (name == null)
        {
            name = NAME;
            return true;
        }
        return false;
    }

    /**
     * Applies all applicable BoxAnimations to the given model for the given time.
     * @param model The model to animate.
     * @param time The time, where 0.0f is start and 1.0f is end.
     */
    public void apply(IModelAnimateable model, float time)
    {
        for (String key : boxes.keySet())
        {
            ModelRendererDefaults box = model.getBox(key);
            if (box != null)
            {
                BoxAnimation toBox = boxes.get(key);
                toBox.apply(box, time);
            }
        }
    }

    /**
     * If locked onto a model, applies all applicable BoxAnimations to it for the given time.
     * @param time The time, where 0.0f is start and 1.0f is end.
     * @return True if locked to a model.
     */
    public boolean apply(float time)
    {
        if (model == null) return false;
        for (String key : boxes.keySet())
        {
            ModelRendererDefaults box = model.getBox(key);
            if (box != null)
            {
                BoxAnimation toBox = boxes.get(key);
                toBox.apply(box, time);
            }
        }
        return true;
    }

    /**
     * Returns all boxes of the given model to their defaults.
     * @param model The model to return to default.
     */
    public void restore(IModelAnimateable model)
    {
        for (String key : boxes.keySet())
        {
            ModelRendererDefaults box = model.getBox(key);
            if (box != null)
            {
                box.restore();
            }
        }
    }

    /**
     * If locked onto a model, restores all of its boxes to default.
     * @return True if locked.
     */
    public boolean restore()
    {
        if (model == null) return false;
        for (String key : boxes.keySet())
        {
            ModelRendererDefaults box = model.getBox(key);
            if (box != null)
            {
                box.restore();
            }
        }
        return true;
    }
}
