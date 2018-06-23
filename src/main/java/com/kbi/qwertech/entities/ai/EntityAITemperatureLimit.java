package com.kbi.qwertech.entities.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityAITemperatureLimit extends EntityAIBase {

	private final EntityLivingBase theEntity;
	private float minTemp;
	private float maxTemp;
	private int mode;
	private int damage;
	private boolean tooHot = false;
	private boolean tooCold = false;
	
	public EntityAITemperatureLimit(EntityLivingBase entity, float limit, int mode)
	{
		this(entity, limit, limit, mode);
	}
	
	public EntityAITemperatureLimit(EntityLivingBase entity, float max)
	{
		this(entity, max, max, 1);
	}
	
	public EntityAITemperatureLimit(EntityLivingBase entity, float min, float max)
	{
		this(entity, min, max, 0);
	}
	
	/**
	 * Sets the range of temperatures at which this entity can survive.
	 * @param entity The entity this applies to.
	 * @param min The minimum temperature limit.
	 * @param max The maximum temperature limit.
	 * @param mode -1: cold limit only. 0: both limited. 1: hot limit only.
	 */
	public EntityAITemperatureLimit(EntityLivingBase entity, float min, float max, int mode) {
		this.theEntity = entity;
		this.setMinTemp(min);
		this.setMaxTemp(max);
		this.setMode(mode);
		this.setDamage(1);
		this.setMutexBits(0);
	}
	
	public EntityAITemperatureLimit setDamage(int amount)
	{
		this.damage = amount;
		return this;
	}
	
	public int getDamage()
	{
		return this.damage;
	}
	
	public void setMinTemp(float min)
	{
		this.minTemp = min;
	}
	
	public void setMaxTemp(float max)
	{
		this.maxTemp = max;
	}
	
	public float getMinTemp()
	{
		return this.minTemp;
	}
	
	public float getMaxTemp()
	{
		return this.maxTemp;
	}
	
	public void setMode(int modal)
	{
		this.mode = modal;
	}
	
	public int getMode()
	{
		return this.mode;
	}

	@Override
	public boolean shouldExecute() {
		if (theEntity.worldObj.rand.nextInt(100) == 0)
		{
			tooCold = false;
			tooHot = false;
			BiomeGenBase biome = theEntity.worldObj.getBiomeGenForCoords((int)theEntity.posX,(int)theEntity.posZ);
			float temp = biome.getFloatTemperature((int)theEntity.posX, (int)theEntity.posY, (int)theEntity.posZ);
			if (temp < this.minTemp && this.mode < 1)
			{
				this.theEntity.moveForward = 0;
				this.theEntity.moveStrafing = 0;
				this.theEntity.attackEntityFrom(new DamageSource("cold").setDamageBypassesArmor().setFireDamage(), this.damage);
				tooCold = true;
			} else if (temp > this.maxTemp && this.mode > -1)
			{
				this.theEntity.moveForward = 0;
				this.theEntity.moveStrafing = 0;
				this.theEntity.attackEntityFrom(new DamageSource("heat").setDamageBypassesArmor().setFireDamage(), this.damage);
				tooHot = true;
			}
		}
		return false;
	}

}
