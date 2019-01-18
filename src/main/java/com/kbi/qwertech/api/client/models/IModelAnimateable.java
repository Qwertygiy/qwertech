package com.kbi.qwertech.api.client.models;

import net.minecraft.entity.Entity;

import java.util.Map;

public interface IModelAnimateable {
    /**
     * @return the list of animateable boxes in this model.
     */
    Map<String, ModelRendererDefaults> getBoxes();

    /**
     * Adds an animateable box to this model.
     * @param box The box to be added.
     * @param name The name key of this box. Should not be duplicated within this model.
     */
    void addBox(ModelRendererDefaults box, String name);

    /**
     * Gets the box with a certain key.
     * @param name The key to use
     * @return The box with that key
     */
    ModelRendererDefaults getBox(String name);

    float getVariable(Entity entity, String ID);
}
