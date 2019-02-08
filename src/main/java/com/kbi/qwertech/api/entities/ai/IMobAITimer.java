package com.kbi.qwertech.api.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public interface IMobAITimer {
    void setStartTime(boolean isResetting);
    long getStartTime();
    void setMaxTime(int ticksToWait);
    int getMaxTime();

    abstract class EntityAITimed extends EntityAIBase implements IMobAITimer {
        public EntityLiving livingEntity;
        public long startTime = -1;
        public int maxTime = -1;

        public EntityAITimed(EntityLiving entity)
        {
            livingEntity = entity;
        }

        @Override
        public void setStartTime(boolean isResetting)
        {
            if (isResetting)
            {
                startTime = -1;
            } else {
                startTime = livingEntity.worldObj.getTotalWorldTime();
            }
        }

        @Override
        public long getStartTime()
        {
            return startTime;
        }

        @Override
        public void setMaxTime(int ticksToWait) {
            maxTime = ticksToWait;
        }

        @Override
        public int getMaxTime() {
            return maxTime;
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            setStartTime(false);
        }

        @Override
        public void resetTask() {
            super.resetTask();
            setStartTime(true);
        }

        @Override
        public boolean continueExecuting() {
            if (livingEntity.worldObj == null || (livingEntity.worldObj.getTotalWorldTime() - getStartTime() > getMaxTime() && getMaxTime() > 0))
            {
                return false;
            }
            return super.continueExecuting();
        }
    }
}