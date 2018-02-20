package com.kbi.qwertech.entities.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IThrowableEntity;

public class EntityEgg extends net.minecraft.entity.projectile.EntityEgg implements IThrowableEntity {

	private EntityAgeable spawnable;
	private EntityLivingBase theThrower;
	
	public EntityEgg(World p_i1779_1_)
    {
        super(p_i1779_1_);
        this.spawnable = new EntityChicken(p_i1779_1_);
    }

    public EntityEgg(World p_i1780_1_, EntityLivingBase p_i1780_2_)
    {
        super(p_i1780_1_, p_i1780_2_);
       	this.theThrower = p_i1780_2_;
       	this.spawnable = new EntityChicken(p_i1780_1_);
    }

    public EntityEgg(World p_i1781_1_, double p_i1781_2_, double p_i1781_4_, double p_i1781_6_)
    {
        super(p_i1781_1_, p_i1781_2_, p_i1781_4_, p_i1781_6_);
        this.spawnable = new EntityChicken(p_i1781_1_);
    }

	@Override
	public EntityLivingBase getThrower() {
		return theThrower;
	}

	@Override
	public void setThrower(Entity entity) {
		if (entity instanceof EntityLivingBase)
		{
			this.theThrower = (EntityLivingBase)entity;
		}
	}
	
	public EntityAgeable getAnimalType()
	{
		return this.spawnable;
	}
	
	public void setAnimalType(EntityAgeable typeAnimal)
	{
		this.spawnable = typeAnimal;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_)
    {
        if (p_70184_1_.entityHit != null)
        {
            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0)
        {
            byte b0 = 1;

            if (this.rand.nextInt(32) == 0)
            {
                b0 = 4;
            }

            for (int i = 0; i < b0; ++i)
            {
            	try {
	                EntityAgeable entitychicken = spawnable.createChild(spawnable);
	                entitychicken.setGrowingAge(-24000);
	                entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
	                this.worldObj.spawnEntityInWorld(entitychicken);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        } else if (this.worldObj.isRemote) {
        	if (p_70184_1_.entityHit == Minecraft.getMinecraft().thePlayer)
        	{
        		com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{222, 222, 222, 200});
        		com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{222, 222, 222, 200});
        		com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{222, 222, 222, 200});
        		com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{222, 111, 0, 250});
        	}
        }

        for (int j = 0; j < 8; ++j)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }

}
