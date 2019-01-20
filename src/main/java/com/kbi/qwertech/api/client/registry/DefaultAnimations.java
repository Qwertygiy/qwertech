package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.BoxAnimationVariable;
import com.kbi.qwertech.api.client.models.ModelAnimation;

public class DefaultAnimations {

    public static DefaultAnimations instance;
    private BoxAnimationVariable head;
    private BoxAnimationVariable body;
    private BoxAnimationVariable left;
    private BoxAnimationVariable right;

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
        left = new BoxAnimationVariable();
        left.setRotateX(0.25F, 0.0F, 1F);
        left.setRotateX(0.50F, 0.0F, 0.0F);
        left.setRotateX(0.75F, 0.0F, -1F);
        right = new BoxAnimationVariable();
        right.setRotateX(0.25F, 0.0F, -1F);
        right.setRotateX(0.50F, 0.0F, 0.0F);
        right.setRotateX(0.75F, 0.0F, 1F);
        ModelAnimation walk = new ModelAnimation();
        walk.addPart("leftLeg", left);
        walk.addPart("rightLeg", right);
        AnimationsRegistry.registerAnimation(walk, 0, "walk");

        //fly
        left = new BoxAnimationVariable();
        left.setRotateZ(0.0F, -0.1F, -0.5F);
        left.setRotateZ(0.25F, -0.25F, -1F);
        left.setRotateZ(0.5F, -0.1F, -0.5F);
        left.setRotateZ(0.75F, -0.25F, -1F);
        left.setRotateZ(1.0F, -0.1F, -0.5F);
        right = new BoxAnimationVariable();
        right.setRotateZ(0.0F, 0.1F, 0.5F);
        right.setRotateZ(0.25F, 0.25F, 1F);
        right.setRotateZ(0.5F, 0.1F, 0.5F);
        right.setRotateZ(0.75F, 0.25F, 1F);
        right.setRotateZ(1.0F, 0.1F, 0.5F);
        ModelAnimation fly = new ModelAnimation();
        fly.addPart("leftWing", left);
        fly.addPart("rightWing", right);
        AnimationsRegistry.registerAnimation(fly, 1, "fly");

        //idle
        body = new BoxAnimationVariable();
        body.setOriginY(0.5F, -0.2F);
        head = new BoxAnimationVariable();
        head.setOriginY(0.4F, -0.2F);
        left = new BoxAnimationVariable();
        left.setRotateZ(0.6F, -0.1F);
        left.setOriginY(0.5F, -0.2F);
        right = new BoxAnimationVariable();
        right.setRotateZ(0.6F, 0.1F);
        right.setOriginY(0.5F, -0.2F);
        ModelAnimation breathe = new ModelAnimation();
        breathe.addPart("body", body);
        breathe.addPart("head", head);
        breathe.addPart("bill", head);
        breathe.addPart("chin", head);
        breathe.addPart("crest", head);
        breathe.addPart("leftWing", left);
        breathe.addPart("rightWing", right);
        breathe.addPart("leftArm", left);
        breathe.addPart("rightArm", right);
        AnimationsRegistry.registerAnimation(breathe, 2, "breathe");

        //chickenbop
        head = new BoxAnimationVariable();
        head.setOriginZ(0.35F, 2F);
        head.setOriginZ(0.5F, 0F);
        head.setOriginZ(0.85F, 2F);
        ModelAnimation chickenBop = new ModelAnimation();
        chickenBop.addPart("head", head);
        chickenBop.addPart("bill", head);
        chickenBop.addPart("chin", head);
        chickenBop.addPart("crest", head);
        AnimationsRegistry.registerAnimation(chickenBop, 3, "chickenheadbop");
    }
}
