package com.kbi.qwertech.entities.ai;

import gregapi.oredict.OreDictManager;
import gregapi.util.ST;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.kbi.qwertech.api.registry.MobBreedRegistry;

public class EntityAITemptAdvanced extends EntityAIBase
{
    /** The entity using this AI that is tempted by the player. */
    private EntityAnimal temptedEntity;
    private double field_75282_b;
    /** X position of player tempting this mob */
    private double targetX;
    /** Y position of player tempting this mob */
    private double targetY;
    /** Z position of player tempting this mob */
    private double targetZ;
    private double field_75278_f;
    private double field_75279_g;
    /** The player that is tempting the entity that is using this AI. */
    private EntityPlayer temptingPlayer;
    /**
     * A counter that is decremented each time the shouldExecute method is called. The shouldExecute method will always
     * return false if delayTemptCounter is greater than 0.
     */
    private int delayTemptCounter;
    /** True if this EntityAITempt task is running */
    private boolean isRunning;
    /** Whether the entity using this AI will be scared by the tempter's sudden movement. */
    private boolean scaredByPlayerMovement;
    private boolean field_75286_m;
    private List<Object> replacements;

    public EntityAITemptAdvanced(EntityAnimal p_i45316_1_, double p_i45316_2_, boolean p_i45316_5_)
    {
        this.temptedEntity = p_i45316_1_;
        this.field_75282_b = p_i45316_2_;
        this.scaredByPlayerMovement = p_i45316_5_;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        if (this.delayTemptCounter > 0)
        {
            --this.delayTemptCounter;
            return false;
        }
        else
        {
            this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

            if (this.temptingPlayer == null)
            {
                return false;
            }
            else
            {
                ItemStack itemstack = this.temptingPlayer.getCurrentEquippedItem();
                if (itemstack == null) return false;
                if (this.temptedEntity.isBreedingItem(itemstack)) return true;
                if (this.replacements == null)
                {
                	HashMap<Item, List<Object>> hash = MobBreedRegistry.getMap();
                	Iterator<Item> begin = hash.keySet().iterator();
                	while (begin.hasNext())
                	{
                		Item toCheck = begin.next();
                		if (this.temptedEntity.isBreedingItem(ST.make(toCheck, 1, 0)))
                		{
                			this.replacements = hash.get(toCheck);
                			break;
                		}
                	}
                }
                if (this.replacements != null && this.replacements.size() > 0)
                {
                	for (int q = 0; q < replacements.size(); q++)
                	{
                		Object toCheck = replacements.get(q);
                		if (toCheck instanceof String)
                		{
                			if (OreDictManager.isItemStackInstanceOf(itemstack, toCheck))
                			{
                				return true;
                			}
                		} else if (toCheck instanceof ItemStack)
                		{
                			if (ST.equal((ItemStack)toCheck, itemstack))
                			{
                				return true;
                			}
                		}
                	}
                }
                return false;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        if (this.scaredByPlayerMovement)
        {
            if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D)
            {
                if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
                {
                    return false;
                }

                if (Math.abs(this.temptingPlayer.rotationPitch - this.field_75278_f) > 5.0D || Math.abs(this.temptingPlayer.rotationYaw - this.field_75279_g) > 5.0D)
                {
                    return false;
                }
            }
            else
            {
                this.targetX = this.temptingPlayer.posX;
                this.targetY = this.temptingPlayer.posY;
                this.targetZ = this.temptingPlayer.posZ;
            }

            this.field_75278_f = this.temptingPlayer.rotationPitch;
            this.field_75279_g = this.temptingPlayer.rotationYaw;
        }

        return this.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.isRunning = true;
        this.field_75286_m = this.temptedEntity.getNavigator().getAvoidsWater();
        this.temptedEntity.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask()
    {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.isRunning = false;
        this.temptedEntity.getNavigator().setAvoidsWater(this.field_75286_m);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, this.temptedEntity.getVerticalFaceSpeed());

        if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
        {
            this.temptedEntity.getNavigator().clearPathEntity();
        }
        else
        {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.field_75282_b);
        }
    }

    /**
     * @see #isRunning
     */
    public boolean isRunning()
    {
        return this.isRunning;
    }
}