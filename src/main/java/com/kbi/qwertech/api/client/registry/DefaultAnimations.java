package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.BoxAnimation;
import com.kbi.qwertech.api.client.models.ModelAnimation;

public class DefaultAnimations {

    public static DefaultAnimations instance;

    public DefaultAnimations()
    {
        if (instance == null)
        {
            instance = this;
            initialize();
        }
    }

    private void initialize()
    {
        //walky
        BoxAnimation left = new BoxAnimation();
        left.setRotateX(0.25F, 0.25F);
        left.setRotateX(0.5F, 0F);
        left.setRotateX(0.75F, -0.25F);
        BoxAnimation right = new BoxAnimation();
        right.setRotateX(0.25F, -0.25F);
        right.setRotateX(0.5F, 0F);
        right.setRotateX(0.75F, 0.25F);
        ModelAnimation walk = new ModelAnimation();
        walk.addPart("leftLeg", left);
        walk.addPart("rightLeg", right);
        AnimationsRegistry.registerAnimation(walk, 0, "walk");

        //fly
        left = new BoxAnimation();
        left.setRotateZ(0.0F, -0.5F);
        left.setRotateZ(0.5F, -1F);
        left.setRotateZ(1.0F, -0.5F);
        right = new BoxAnimation();
        right.setRotateZ(0.0F, 0.5F);
        right.setRotateZ(0.5F, 1F);
        right.setRotateZ(1.0F, 0.5F);
        ModelAnimation fly = new ModelAnimation();
        fly.addPart("leftWing", left);
        fly.addPart("rightWing", right);
        AnimationsRegistry.registerAnimation(fly, 1, "fly");
    }
}
