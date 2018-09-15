package com.kbi.qwertech.api.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

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

    public interface IAutoAggro {
        /**
         * shouldAggroTowardsMob
         * Whether this mob should go attack that mob by seeing it
         * @param geneticMob the mob being checked
         * @param otherEntity the entity that could be chased
         * @return whether the mob would go chase
         */
        boolean shouldAutoAggro(IGeneticMob geneticMob, EntityLiving otherEntity);
    }

    public interface IEatStuffOnTheGround {

        /**
         * shouldEatOffTheGround
         * The desirability of this item to this mob
         * @param geneticMob the mob that may be hungry
         * @param itemEntity the item that may be food
         * @return how likely this mob will go after this item, from 0 to 1
         */
        float shouldEatOffTheGround(IGeneticMob geneticMob, EntityItem itemEntity);
    }

    public interface INesting {
        /**
         * getHomeCoords
         * Returns the current coordinates of this mob's home, if they exist
         * @param geneticMob the mob to check
         * @return Vec3 of the coordinates
         */
        Vec3 getHomeCoords(IGeneticMob geneticMob);

        /**
         * isValidHome
         * whether the block at the given coords is a valid home for this mob
         * @param geneticMob the mob to check
         * @param coords Vec3 of the coordinates
         * @return true if the mob can set it as home
         */
        boolean isValidHome(IGeneticMob geneticMob, Vec3 coords);

        /**
         * wouldBeValidHome
         * whether the block at the given coords would be a valid home for this mob if the given block was placed there
         * @param geneticMob the mob to check
         * @param coords Vec3 of the coordinates
         * @param block the Block that would be placed
         * @param metadata the metadata of the block that would be placed
         * @param te the potential TileEntity
         * @return true if the mob can set it as home
         */
        boolean wouldBeValidHome(IGeneticMob geneticMob, Vec3 coords, Block block, int metadata, TileEntity te);

        /**
         * setHome
         * Try to set the home of this mob to the specified coords
         * @param geneticMob the mob to set home
         * @param coords Vec3 of the coordinates
         * @return true if successful
         */
        boolean setHome(IGeneticMob geneticMob, Vec3 coords);

        /**
         * removeHome
         * Attempt to remove the current home coordinates of this mob without setting a new home
         * @param geneticMob the mob to evict
         * @return true if successful (you monster)
         */
        boolean removeHome(IGeneticMob geneticMob);

        /**
         * goHome
         * Try to send this mob to its current home coordinates
         * @param geneticMob the mob to send to its room
         * @return true if successful
         */
        boolean goHome(IGeneticMob geneticMob);

        /**
         * isHome
         * Checks to see if this mob is close enough to its home to count as being at home
         * @param geneticMob the mob to check
         * @return true if anyone's home
         */
        boolean isHome(IGeneticMob geneticMob);
    }


}
