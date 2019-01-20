package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.IModelAnimateable;
import com.kbi.qwertech.api.client.models.ModelRendererDefaults;

import java.util.Map;

public class AnimationHelper {

    /**
     * Sets the model's current position as its default state.
     * @param model The model to set.
     */
    public static void setAsDefault(IModelAnimateable model)
    {
        Map<String, ModelRendererDefaults> boxes = model.getBoxes();
        for (ModelRendererDefaults box : boxes.values())
        {
            setAsDefault(box);
        }
    }

    /**
     * Sets the box's current position as its default state.
     * @param box The box to set.
     */
    public static void setAsDefault(ModelRendererDefaults box)
    {
        box.setDefaults(box.rotationPointX, box.rotationPointY, box.rotationPointZ, box.offsetX, box.offsetY, box.offsetZ, box.rotateAngleX, box.rotateAngleY, box.rotateAngleZ);
    }

    public static void reset(IModelAnimateable model)
    {
        Map<String, ModelRendererDefaults>boxes = model.getBoxes();
        for (ModelRendererDefaults box : boxes.values())
        {
            box.restore();
        }
    }
}
