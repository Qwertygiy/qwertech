package com.kbi.qwertech.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import java.util.List;

public class EntityAIEatFoodOffTheGround extends EntityAIBase {
    private EntityLiving ourEntity;
    private float minLost;
    private double maxDist;
    private float heal;
    private double xPos;
    private double yPos;
    private double zPos;
    public EntityItem target;
    private int math;

    /**
     * Initiates basic food-eating protocol. Within 16 blocks, if health is below max, half a heart per non-food breeding item
     * @param entity The animal to be hungry
     */
    public EntityAIEatFoodOffTheGround(EntityLiving entity)
    {
        this(entity, 1F);
    }

    /**
     * Creates food-eating protocol that allows the animal to ignore ground food until low on health
     * @param entity The animal to be hungry
     * @param minHealth How much health must be lost before they are hungry
     */
    public EntityAIEatFoodOffTheGround(EntityLiving entity, float minHealth)
    {
        this(entity, minHealth, 16);
    }

    /**
     * Creates food-eating protocol that allows the animal to ignore ground food until low on health, and set how far they "smell"
     * @param entity The animal to be hungry
     * @param minHealth How much health must be lost before they are hungry
     * @param maxDistance How far away they will track down food
     */
    public EntityAIEatFoodOffTheGround(EntityLiving entity, float minHealth, double maxDistance)
    {
        this(entity, minHealth, maxDistance, 1F);
    }

    /**
     * Creates fully custom food-eating protocol
     * @param entity The animal to be hungry
     * @param minHealth How much health must be lost before they are hungry
     * @param maxDistance How far away they will track down food
     * @param heals How much any inedible item (e.g. seeds) will heal them
     */
    public EntityAIEatFoodOffTheGround(EntityLiving entity, float minHealth, double maxDistance, float heals)
    {
        ourEntity = entity;
        minLost = minHealth;
        maxDist = maxDistance;
        heal = heals;
        math = ourEntity.getRNG().nextInt(40);
    }

    @Override
    public boolean shouldExecute() {
        //we have a random integer as our timer so they don't all notice the food at once
        if (ourEntity.worldObj.getTotalWorldTime() % 30 != math) return false;

        //hunger overrides the normal reasons an entity wouldn't care for food
        if (!ourEntity.isPotionActive(Potion.hunger)) {
            //such as being poisoned or nauseous
            if (ourEntity.isPotionActive(Potion.poison) || ourEntity.isPotionActive(Potion.confusion)) return false;
            //or chasing after someone
            if (ourEntity.getAttackTarget() != null) return false;
            //or being full on health, *unless* they are ready to breed
            if (ourEntity.getHealth() + minLost >= ourEntity.getMaxHealth())
            {
                if (ourEntity instanceof EntityAnimal)
                {
                    if (((EntityAnimal)ourEntity).getGrowingAge() > 0 || ((EntityAnimal)ourEntity).isInLove())
                    {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        List<EntityItem> entities = ourEntity.worldObj.getEntitiesWithinAABB(EntityItem.class, ourEntity.boundingBox.expand(maxDist, maxDist * 0.25, maxDist));
        for (EntityItem ei : entities)
        {
            if (ourEntity instanceof EntityAnimal)
            {
                if (((EntityAnimal)ourEntity).isBreedingItem(ei.getEntityItem()))
                {
                    target = ei;
                    xPos = Math.floor(ei.posX);
                    yPos = Math.floor(ei.posY);
                    zPos = Math.floor(ei.posZ);
                    return true;
                }
            } else {
                if (ei.getEntityItem().getItem() instanceof ItemFood)
                {
                    target = ei;
                    xPos = Math.floor(ei.posX);
                    yPos = Math.floor(ei.posY);
                    zPos = Math.floor(ei.posZ);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void startExecuting()
    {
        this.ourEntity.getNavigator().tryMoveToXYZ(this.xPos, this.yPos, this.zPos, 1);
        //System.out.println("We're trying... moving at 1");
    }

    @Override
    public boolean continueExecuting() {
        if (target == null || target.worldObj != ourEntity.worldObj) return false;
        if (ourEntity.getNavigator().noPath()) return false;
        if (Math.floor(target.posX) != xPos || Math.floor(target.posZ) != zPos)
        {
            xPos = Math.floor(target.posX);
            yPos = Math.floor(target.posY);
            zPos = Math.floor(target.posZ);
        }
        if (Math.floor(ourEntity.posX) == this.xPos && Math.floor(ourEntity.posZ) == this.zPos && ourEntity.posY - this.yPos > -0.1 && ourEntity.posY - this.yPos < 0.5) {
            float amount = heal;
            ItemStack stack = target.getEntityItem();
            if (stack.getItem() instanceof ItemFood)
            {
                amount = ((ItemFood)stack.getItem()).func_150905_g(stack);
            }
            ourEntity.heal(amount);
            if (ourEntity instanceof EntityAnimal)
            {
                EntityAnimal ea = (EntityAnimal)ourEntity;
                if (ea.getGrowingAge() == 0) {
                    ea.func_146082_f(null);
                } else if (ea.getGrowingAge() < 0)
                {
                    ea.addGrowth((int)(Math.floor(ea.getGrowingAge() * 0.1)));
                }
            }
            if (stack.stackSize > 1)
            {
                stack.stackSize = stack.stackSize - 1;
                target.setEntityItemStack(stack);
            } else {
                target.setDead();
            }
            return false;
        }
        return true;
    }
}
