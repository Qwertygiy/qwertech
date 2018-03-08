package com.kbi.qwertech.entities.neutral;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.entities.ai.EntityAITemptAdvanced;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictManager;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class EntityTurkey extends EntityAnimal {
	
	public int timeUntilNextEgg;

	public EntityTurkey(World p_i1681_1_) {
		super(p_i1681_1_);
		this.setSize(0.6F, 0.8F);
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITemptAdvanced(this, 1, false));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1,new EntityAIHurtByTarget(this, true));
	}
	
	@Override
	public boolean isAIEnabled()
    {
        return true;
    }

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable parent) {
		return new EntityTurkey(parent.worldObj);
	}
	
	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, (byte) 0);
	}

	@Override
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && !this.isChild() && --this.timeUntilNextEgg <= 0)
        {
            this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.entityDropItem(QTI.turkeyEgg.get(1), 1);
            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        }
    }
	
	@Override
	protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        return super.getExperiencePoints(p_70693_1_);
    }
	
	@Override
	public boolean isBreedingItem(ItemStack p_70877_1_)
    {
		if (p_70877_1_ == null) return false;
		if (OreDictManager.isItemStackInstanceOf(p_70877_1_, "cropCorn"))
		{
			this.setAttackTarget(null);
			return true;
		} else if(!OreDictionary.doesOreNameExist("cropCorn") && OreDictManager.isItemStackInstanceOf(p_70877_1_, "listAllseed"))
		{
			this.setAttackTarget(null);
			return true;
		}
		return false;
    }
	
	@Override
	protected String getLivingSound()
    {
        return this.isAngry() ? "qwertech:mob.turkey.hurt" : "qwertech:mob.turkey.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
	@Override
    protected String getHurtSound()
    {
        return "qwertech:mob.turkey.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "qwertech:mob.turkey.death";
    }

    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
    	float v = 0.15F;
    	if (this.isAngry())
    	{
    		v = 0.33F;
    	}
        this.playSound("mob.chicken.step", v, 1.0F);
    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);

        if (this.isBurning())
        {
            this.entityDropItem(QTI.turkeyWholeCooked.get(1), 1);
            this.entityDropItem(OP.dustTiny.mat(MT.Ash, j), 1);
        }
        else
        {
        	this.entityDropItem(QTI.turkeyFeather.get(j), 1);
            this.entityDropItem(QTI.turkeyWholeRaw.get(1), 1);
        }
    }
    
    @Override
    public void setAttackTarget(EntityLivingBase p_70624_1_)
    {
        super.setAttackTarget(p_70624_1_);

        if (p_70624_1_ == null)
        {
            this.setAngry(false);
        }
        else
        {
            this.setAngry(true);
        }
    }
    
    public boolean isAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean p_70916_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70916_1_)
        {
            this.dataWatcher.updateObject(16, (byte) (b0 | 2));
        }
        else
        {
            this.dataWatcher.updateObject(16, (byte) (b0 & -3));
        }
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("Angry", this.isAngry());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.setAngry(p_70037_1_.getBoolean("Angry"));
    }
    
    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
    	if (this.rand.nextInt(3) == 0) this.setAttackTarget(null);
        int i = 1;
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }
}
