package com.kbi.qwertech.api.entities;

import net.minecraft.entity.EntityLivingBase;

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
        float shouldAggroOnHit(IGeneticMob geneticMob, EntityLivingBase attacker);

        /**
         * aggroHitTimer
         * How long this mob would stay angry at this attacker if aggroed via hit
         * @param geneticMob the mob being checked
         * @param attacker the entity that could attack
         * @return the length, in ticks, of the timer
         */
        int aggroHitTimer(IGeneticMob geneticMob, EntityLivingBase attacker);
    }

    public interface IAutoAggro {
        /**
         * shouldAggroTowardsMob
         * Whether this mob should go attack that mob by seeing it
         * @param geneticMob the mob being checked
         * @param otherEntity the entity that could be chased
         * @return whether the mob would go chase
         */
        boolean shouldAutoAggro(IGeneticMob geneticMob, EntityLivingBase otherEntity);
    }
}
