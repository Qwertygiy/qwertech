package com.kbi.qwertech.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.Vec3;

public class EntityAIMoveTowardsSimpleTarget extends EntityAIBase
{
    private EntityCreature theEntity;
    private Entity targetEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private double speed;
    /** If the distance to the target entity is further than this, this AI task will not run. */
    private float maxTargetDistance;
    private static final String __OBFID = "CL_00001599";

    public EntityAIMoveTowardsSimpleTarget(EntityCreature p_i1640_1_, double p_i1640_2_, float p_i1640_4_)
    {
        this.theEntity = p_i1640_1_;
        this.speed = p_i1640_2_;
        this.maxTargetDistance = p_i1640_4_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        this.targetEntity = this.theEntity.getEntityToAttack();

        if (this.targetEntity == null || this.targetEntity.worldObj != this.theEntity.worldObj)
        {
            return false;
        }
        else if (this.targetEntity.getDistanceSqToEntity(this.theEntity) > (double)(this.maxTargetDistance * this.maxTargetDistance))
        {
            return false;
        }
        else if (!(this.targetEntity instanceof EntityItem))
        {
            return false;
        }
        else
        {
            /*Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, Vec3.createVectorHelper(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.movePosX = vec3.xCoord;
                this.movePosY = vec3.yCoord;
                this.movePosZ = vec3.zCoord;
                return true;
            }*/
            this.movePosX = this.targetEntity.posX;
            this.movePosY = this.targetEntity.posY;
            this.movePosZ = this.targetEntity.posZ;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.theEntity.getNavigator().noPath() && this.targetEntity != null && this.targetEntity.worldObj == this.theEntity.worldObj && this.targetEntity.getDistanceSqToEntity(this.theEntity) < (double)(this.maxTargetDistance * this.maxTargetDistance);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.targetEntity = null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.theEntity.getAIMoveSpeed());
    }
}