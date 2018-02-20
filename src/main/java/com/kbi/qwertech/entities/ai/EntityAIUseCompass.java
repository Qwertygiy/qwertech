package com.kbi.qwertech.entities.ai;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class EntityAIUseCompass extends EntityAITarget {
	
	int counter = 0;
	List followers = new ArrayList();
	
    public EntityAIUseCompass(EntityCreature p_i1663_1_,boolean p_i1663_4_)
    {
        this(p_i1663_1_, p_i1663_4_, false);
    }

    public EntityAIUseCompass(EntityCreature p_i1665_1_, boolean p_i1665_4_, boolean p_i1665_5_)
    {
        super(p_i1665_1_, p_i1665_4_, p_i1665_5_);
        this.setMutexBits(8);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        if (this.taskOwner.getHeldItem() != null && Item.getIdFromItem(this.taskOwner.getHeldItem().getItem()) == Item.getIdFromItem(Items.compass))
        {
           	this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0D);
        	return true;
        }
        this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.taskOwner.worldObj.getClosestPlayerToEntity(this.taskOwner, 128));
    }
    
    @Override
    public boolean continueExecuting()
    {
    	this.counter += 1;
    	if (counter >= 20)
    	{
    		counter = 0;
    		this.taskOwner.setAttackTarget(this.taskOwner.worldObj.getClosestPlayerToEntity(this.taskOwner, 128));
    		List list = this.taskOwner.worldObj.selectEntitiesWithinAABB(EntityLiving.class, this.taskOwner.boundingBox.expand(16, 4.0D, 16), null);
    		for (int q = 0; q < list.size(); q++)
    		{
    			EntityLiving follower = (EntityLiving)list.get(q);
    			if (follower instanceof EntityZombie || follower instanceof EntitySkeleton || follower instanceof EntityWitch)
    			{
    				follower.setAttackTarget(this.taskOwner.getAttackTarget());
    				follower.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0D);
    			}
    		}
    		for (int q = 0; q < followers.size(); q++)
    		{
    			EntityLiving oldFollower = (EntityLiving)followers.get(q);
    			if (list.contains(oldFollower)) continue;
    			oldFollower.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
    			oldFollower.setAttackTarget(null);
    		}
    		followers = list;
    	}
    	return true;
    }

}
