package com.kbi.qwertech.entities.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityAIHydrationLimit extends EntityAIBase {

	private final EntityLivingBase theEntity;
	private float minTemp;
	private float maxTemp;
	private int mode;
	private int damage;
	
	public EntityAIHydrationLimit(EntityLivingBase entity, float limit, int mode)
	{
		this(entity, limit, limit, mode);
	}
	
	public EntityAIHydrationLimit(EntityLivingBase entity, float max)
	{
		this(entity, max, max, 1);
	}
	
	public EntityAIHydrationLimit(EntityLivingBase entity, float min, float max)
	{
		this(entity, min, max, 0);
	}
	
	/**
	 * Sets the range of wetness at which this entity can survive. Touching water is 1. Completely dry is 0.
	 * @param entity The entity this applies to.
	 * @param min The minimum wetness.
	 * @param max The maximum wetness.
	 * @param mode -1: dry limit only. 0: both limited. 1: wet limit only.
	 */
	public EntityAIHydrationLimit(EntityLivingBase entity, float min, float max, int mode) {
		this.theEntity = entity;
		this.setMinTemp(min);
		this.setMaxTemp(max);
		this.setMode(mode);
		this.setDamage(1);
		this.setMutexBits(0);
	}
	
	public EntityAIHydrationLimit setDamage(int amount)
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
			BiomeGenBase biome = theEntity.worldObj.getBiomeGenForCoords((int)theEntity.posX,(int)theEntity.posZ);
			if (biome.rainfall < this.minTemp && this.mode < 1 && !this.theEntity.isInWater() && !(this.theEntity.worldObj.isRaining() && biome.rainfall > 0))
			{
				this.theEntity.moveForward = 0;
				this.theEntity.moveStrafing = 0;
				this.theEntity.attackEntityFrom(new DamageSource("dry").setDamageBypassesArmor().setFireDamage(), this.damage);
			} else if ((biome.rainfall > this.maxTemp || (this.maxTemp >= 1 && (this.theEntity.isInWater() || (this.theEntity.worldObj.isRaining() && biome.rainfall > 0)))) && this.mode > -1)
			{
				this.theEntity.moveForward = 0;
				this.theEntity.moveStrafing = 0;
				this.theEntity.attackEntityFrom(new DamageSource("water").setDamageBypassesArmor(), this.damage);
			}
		}
		return false;
	}

}