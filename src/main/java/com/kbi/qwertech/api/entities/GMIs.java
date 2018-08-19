package com.kbi.qwertech.api.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

/**
 * GMIs - Genetic Mob Interfaces
 * These should be implemented by Entities that implement IGeneticMob.
 * They allow easy access for external features (like items and blocks)
 * to behaviors or features common to multiple types of Genetic Mob,
 * which are not easily accessed through the base Entity classes or
 * other interfaces (IShearable).
 */
public class GMIs {

    /**
     * IEggLayer
     * Should be implemented for every Genetic Mob that has the chance to lay eggs.
     * Should NOT be implemented if the mob periodically drops something other than an egg.
     */
    public interface IEggLayer {
        /**
         * canLayEgg
         * Whether this mob can currently lay an egg.
         * @param geneticMob the mob being checked
         * @return true if egglaying is possible
         */
        boolean canLayEgg(IGeneticMob geneticMob);

        /**
         * getEggItem
         * The ItemStack that this mob would currently drop as an egg.
         * @param geneticMob the mob being checked
         * @return the egg
         */
        ItemStack getEggItem(IGeneticMob geneticMob);

        /**
         * willEggHatch
         * Whether an egg laid right now by this mob is fertilized and will hatch into a baby mob.
         * @param geneticMob the mob being checked
         * @return true if it will eventually hatch
         */
        boolean willEggHatch(IGeneticMob geneticMob);
    }

    /**
     * IHitAggro
     * Should be implemented if the mob has the chance to strike back at an attacker.
     */
    public interface IHitAggro {
        /**
         * shouldAggroOnHit
         * The chance (where 1.0F = 100%) that this mob would be aggroed if struck by this entity
         * Should NOT trigger the aggro itself! That should only occur when actually hit!
         * @param geneticMob the mob being checked
         * @param attacker the entity that could attack
         * @return the chance of aggro (1.0F = 100%, 0.0F = 0%)
         */
        float shouldAggroOnHit(IGeneticMob geneticMob, EntityLiving attacker);

        /**
         * aggroHitTimer
         * How long this mob would stay angry at this attacker if aggroed via hit
         * @param geneticMob the mob being checked
         * @param attacker the entity that could attack
         * @return the length, in ticks, of the timer
         */
        int aggroHitTimer(IGeneticMob geneticMob, EntityLiving attacker);
    }
}
