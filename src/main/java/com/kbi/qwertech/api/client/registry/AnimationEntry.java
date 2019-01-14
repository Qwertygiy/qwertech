package com.kbi.qwertech.api.client.registry;

import com.kbi.qwertech.api.client.models.IModelAnimateable;
import com.kbi.qwertech.api.client.models.ModelAnimation;

import java.util.Comparator;

public class AnimationEntry {
    public short duration;
    public long startTime;
    public int priority;
    public ModelAnimation animation;
    public IModelAnimateable model;
    public boolean looping;

    /**
     * AnimationEntry to be used in the AnimationsRegistry map.
     * @param mod The model that is the subject of the animation.
     * @param anim The animation that is being entered.
     * @param pri Priority of animation. Higher = more important.
     * @param dur How long in ticks the animation should run.
     * @param start The total world time at the start of the animation.
     * @param loop Whether the animation should start again upon finishing.
     */
    public AnimationEntry(IModelAnimateable mod, ModelAnimation anim, int pri, short dur, long start, boolean loop)
    {
        model = mod;
        animation = anim;
        priority = pri;
        duration = dur;
        startTime = start;
        looping = loop;
    }

    /**
     * Sorts animation entries by priority. When reversed, higher priority comes first.
     */
    public static class PrioritySort implements Comparator<AnimationEntry> {

        boolean reversed = false;
        public PrioritySort reverse()
        {
            reversed = true;
            return this;
        }

        @Override
        public int compare(AnimationEntry o1, AnimationEntry o2) {
            if (reversed) return o2.priority - o1.priority;
            return o1.priority - o2.priority;
        }

        @Override
        public boolean equals(Object obj) {
            return obj.getClass() == this.getClass() && ((PrioritySort)obj).reversed == this.reversed;
        }
    }
}
