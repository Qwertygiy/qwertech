package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.BoxAnimation;
import com.kbi.qwertech.api.client.models.BoxAnimationVariable;
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
        BoxAnimationVariable left = new BoxAnimationVariable();
        left.setRotateX(0.25F, 0.0F, 0.25F);
        left.setRotateX(0.50F, 0.0F, 0.0F);
        left.setRotateX(0.75F, 0.0F, -0.25F);
        BoxAnimationVariable right = new BoxAnimationVariable();
        right.setRotateX(0.25F, 0.0F, -0.25F);
        right.setRotateX(0.50F, 0.0F, 0.0F);
        right.setRotateX(0.75F, 0.0F, 0.25F);
        ModelAnimation walk = new ModelAnimation();
        walk.addPart("leftLeg", left);
        walk.addPart("rightLeg", right);
        AnimationsRegistry.registerAnimation(walk, 0, "walk");

        //fly
        left = new BoxAnimationVariable();
        left.setRotateZ(0.0F, -0.5F);
        left.setRotateZ(0.25F, -2F);
        left.setRotateZ(0.5F, -0.5F);
        left.setRotateZ(0.75F, -2F);
        left.setRotateZ(1.0F, -0.5F);
        right = new BoxAnimationVariable();
        right.setRotateZ(0.0F, 0.5F);
        right.setRotateZ(0.25F, 2F);
        right.setRotateZ(0.5F, 0.5F);
        right.setRotateZ(0.75F, 2F);
        right.setRotateZ(1.0F, 0.5F);
        ModelAnimation fly = new ModelAnimation();
        fly.addPart("leftWing", left);
        fly.addPart("rightWing", right);
        AnimationsRegistry.registerAnimation(fly, 1, "fly");

        //idle
        BoxAnimation body = new BoxAnimation();
        body.setOriginY(0.5F, -0.1F);
        BoxAnimation head = new BoxAnimation();
        head.setOriginY(0.4F, -0.05F);
        BoxAnimation bill = new BoxAnimation();
        bill.setOriginY(0.4F, -0.05F);
        BoxAnimation chin = new BoxAnimation();
        chin.setOriginY(0.4F, -0.05F);
        BoxAnimation crest = new BoxAnimation();
        crest.setOriginY(0.4F, -0.05F);
        ModelAnimation breathe = new ModelAnimation();
        breathe.addPart("body", body);
        breathe.addPart("head", head);
        breathe.addPart("bill", bill);
        breathe.addPart("chin", chin);
        breathe.addPart("crest", crest);
        AnimationsRegistry.registerAnimation(breathe, 2, "breathe");
    }
}
