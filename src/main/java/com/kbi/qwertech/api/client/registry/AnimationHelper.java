package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.BoxAnimation;
import com.kbi.qwertech.api.client.models.IModelAnimateable;
import com.kbi.qwertech.api.client.models.ModelAnimation;
import com.kbi.qwertech.api.client.models.ModelRendererDefaults;

import java.util.Map;

public class AnimationHelper {

    /**
     * Sets the model's current position as its default state.
     * @param model The model to set.
     */
    public void setAsDefault(IModelAnimateable model)
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
    public void setAsDefault(ModelRendererDefaults box)
    {
        box.setDefaults(box.rotationPointX, box.rotationPointY, box.rotationPointZ, box.offsetX, box.offsetY, box.offsetZ, box.rotateAngleX, box.rotateAngleY, box.rotateAngleZ);
    }
}
