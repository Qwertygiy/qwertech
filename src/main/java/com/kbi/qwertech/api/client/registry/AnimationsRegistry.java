package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.IModelAnimateable;
import com.kbi.qwertech.api.client.models.ModelAnimation;
import com.kbi.qwertech.api.client.registry.AnimationEntry.PrioritySort;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AnimationsRegistry {
    private static final HashMap<Entity, List<AnimationEntry>> registry = new HashMap<>();
    /**
     * The instance of PrioritySort that is used to sort animations by priority.
     */
    public static final PrioritySort sort = new PrioritySort();
    private static final ModelAnimation[] animationList = new ModelAnimation[Short.MAX_VALUE];
    private static final HashMap<String, Short> animationNames = new HashMap<>();

    /**
     * Registers an animation without a name key, using a hash instead. Not recommended.
     * @param anim The animation to register.
     * @param ID The ID of the animation.
     * @return True if it didn't overwrite anything.
     */
    public boolean registerAnimation(ModelAnimation anim, int ID)
    {
        return registerAnimation(anim, ID, "#" + anim.hashCode());
    }

    /**
     * Registers an animation with both ID and name key. Recommended method.
     * @param anim The animation to register.
     * @param ID The ID of the animation.
     * @param name The name of the animation, which must be unique.
     * @return True if it didn't overwrite anything.
     */
    public boolean registerAnimation(ModelAnimation anim, int ID, String name)
    {
        if (animationNames.containsKey(name) || animationList[ID] != null)
        {
            animationList[ID] = anim;
            animationNames.put(name, (short)ID);
            anim.setID(ID);
            anim.setName(name);
            return false;
        } else {
            animationList[ID] = anim;
            animationNames.put(name, (short)ID);
            return anim.setID(ID) && anim.setName(name);
        }
    }

    /**
     * returns the animation at the given ID.
     * @param ID The ID, below Short.MAX_VALUE
     * @return the animation, if it exists
     */
    public ModelAnimation getAnimation(int ID)
    {
        return animationList[ID];
    }

    /**
     * returns the animation with the given name
     * @param name The key the animation was registered with
     * @return The animation, if it exists
     */
    public ModelAnimation getAnimation(String name)
    {
        if (animationNames.containsKey(name))
        {
            return getAnimation(animationNames.get(name));
        }
        return null;
    }

    /**
     * Triggers the beginning of a single-pass low-priority animation for the given entity, if it is not already playing.
     * @param entity The entity we are animating
     * @param mod The model we are rendering
     * @param anim The animation we are using
     * @param dur How long, in ticks, the animation should run
     * @return True if the entity exists to be animated.
     */
    public boolean addAnimation(Entity entity, IModelAnimateable mod, ModelAnimation anim, short dur)
    {
        return addAnimation(entity, mod, anim, 1, dur, false, false);
    }

    /**
     * Triggers the beginning of a single-pass animation for the given entity, if it is not already playing.
     * @param entity The entity we are animating
     * @param mod The model we are rendering
     * @param anim The animation we are using
     * @param pri Higher priority animations override lower priority animations
     * @param dur How long, in ticks, the animation should run
     * @return True if the entity exists to be animated.
     */
    public boolean addAnimation(Entity entity, IModelAnimateable mod, ModelAnimation anim, int pri, short dur)
    {
        return addAnimation(entity, mod, anim, pri, dur, false, false);
    }

    /**
     * Triggers the beginning of an animation for the given entity, if it is not already playing.
     * @param entity The entity we are animating
     * @param mod The model we are rendering
     * @param anim The animation we are using
     * @param pri Higher priority animations override lower priority animations
     * @param dur How long, in ticks, the animation should run
     * @param loop If the animation should begin anew when it finishes
     * @return True if the entity exists to be animated.
     */
    public boolean addAnimation(Entity entity, IModelAnimateable mod, ModelAnimation anim, int pri, short dur, boolean loop)
    {
        return addAnimation(entity, mod, anim, pri, dur, loop, false);
    }

    /**
     * Triggers the beginning of an animation for the given entity.
     * @param entity The entity we are animating
     * @param mod The model we are rendering
     * @param anim The animation we are using
     * @param pri Higher priority animations override lower priority animations
     * @param dur How long, in ticks, the animation should run
     * @param loop If the animation should begin anew when it finishes.
     * @param overwrite If the animation should still be added if another of the same animation is already playing.
     * @return True if the entity exists to be animated.
     */
    public boolean addAnimation(Entity entity, IModelAnimateable mod, ModelAnimation anim, int pri, short dur, boolean loop, boolean overwrite)
    {
        if (dur < 2) return false; //no point in adding an animation too short to animate
        if (entity == null || entity.worldObj == null) return false;
        if (overwrite)
        {
            removeAnimation(entity, anim);
        } else {
            if (hasAnimation(entity, anim))
            {
                return false;
            }
        }
        List<AnimationEntry> listen = registry.get(entity);
        if (listen == null)
        {
            listen = new ArrayList<>();
            registry.put(entity, listen);
        }
        listen.add(new AnimationEntry(mod, anim, pri, dur, entity.worldObj.getTotalWorldTime(), loop));
        Collections.sort(listen, sort);
        return true;
    }

    /**
     * Removes any ongoing instances of the given animation from the given entity.
     * @param entity The entity to check.
     * @param ID The ID of the animation to search for.
     * @return True if removed.
     */
    public boolean removeAnimation(Entity entity, int ID)
    {
        return removeAnimation(entity, animationList[ID]);
    }

    /**
     * Removes any ongoing instances of the given animation from the given entity.
     * @param entity The entity to check.
     * @param name The name of the animation to search for.
     * @return True if removed.
     */
    public boolean removeAnimation(Entity entity, String name)
    {
        return removeAnimation(entity, animationNames.get(name));
    }

    /**
     * Removes any ongoing instances of the given animation from the given entity.
     * @param entity The entity to check.
     * @param anim The animation to search for.
     * @return True if removed.
     */
    public boolean removeAnimation(Entity entity, ModelAnimation anim)
    {
        if (anim == null) return false;
        if (entity == null || entity.worldObj == null) return false;
        List<AnimationEntry> listen = registry.get(entity);
        if (listen == null || listen.size() == 0) return false;
        boolean didWe = false;
        for (int q = 0; q < listen.size(); q++)
        {
            AnimationEntry ae = listen.get(q);
            if (ae.animation == anim && ae.startTime <= entity.worldObj.getTotalWorldTime())
            {
                listen.remove(q);
                q = q - 1;
                didWe = true;
            }
        }
        return didWe;
    }

    /**
     * Returns true if the entity is currently in the process of the given animation.
     * @param entity The entity to check.
     * @param ID The ID of the animation to check.
     * @return True if found.
     */
    public boolean hasAnimation(Entity entity, int ID)
    {
        return hasAnimation(entity, animationList[ID]);
    }

    /**
     * Returns true if the entity is currently in the process of the given animation.
     * @param entity The entity to check.
     * @param name The name of the animation to check.
     * @return True if found.
     */
    public boolean hasAnimation(Entity entity, String name)
    {
        return hasAnimation(entity, animationNames.get(name));
    }

    /**
     * Returns true if the entity is currently in the process of the given animation.
     * @param entity The entity to check.
     * @param anim The animation to check.
     * @return True if found.
     */
    public boolean hasAnimation(Entity entity, ModelAnimation anim)
    {
        if (anim == null) return false;
        if (entity == null || entity.worldObj == null) return false;
        List<AnimationEntry> listen = registry.get(entity);
        if (listen == null || listen.size() == 0) return false;
        for (AnimationEntry ae : listen)
        {
            if (ae.animation == anim && ae.startTime <= entity.worldObj.getTotalWorldTime())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates and applies all active animations for the given entity.
     * @param entity The entity to animate.
     */
    public void setAnimations(Entity entity)
    {
        List<AnimationEntry> listen = registry.get(entity);
        if (listen == null) return;
        if (entity == null || entity.worldObj == null)
        {
            registry.remove(entity);
            return;
        }

        for (int q = 0; q < listen.size(); q++)
        {
            AnimationEntry ae = listen.get(q);
            short timePassed = (short)(entity.worldObj.getTotalWorldTime() - ae.startTime);

            if (timePassed < 0) continue; //it hasn't started yet

            float percentage = (float)timePassed/(float)ae.duration;
            if (percentage < 1.0F) {
                ae.animation.apply(ae.model, percentage);
            } else {
                if (ae.looping)
                {
                    ae.startTime = entity.worldObj.getTotalWorldTime();
                } else {
                    ae.animation.restore(ae.model);
                    listen.remove(q);
                    q = q - 1;
                }
            }
        }
    }
}
