package com.kbi.qwertech.entities.passive;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.entities.ai.EntityAIHydrationLimit;
import com.kbi.qwertech.entities.ai.EntityAITemperatureLimit;
import com.kbi.qwertech.entities.ai.EntityAITemptAdvanced;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class EntityFrog extends EntityAnimal {

	//private boolean isRaggy = ((new Date()).getMonth() == 3 && (new Date()).getDate() <= 3);
	private boolean isRaggy = true;

	public EntityFrog(World p_i1681_1_) {
		super(p_i1681_1_);
		this.setSize(0.5F, 0.4F);
		this.tasks.addTask(0, new EntityAISwimming(this));
	    this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
	    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITemptAdvanced(this, 1, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAITemperatureLimit(this, 0.2F, 2F, -1));
        this.tasks.addTask(8, new EntityAIHydrationLimit(this, 0.4F, -1));
        this.getNavigator().setAvoidSun(true);
        this.getNavigator().setAvoidsWater(false);
        this.jumpMovementFactor = this.jumpMovementFactor * 3;
	}

	@Override
	public float getShadowSize() {
		return super.getShadowSize() * 0.5F;
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
    }
	
	@Override
	protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        return super.getExperiencePoints(p_70693_1_);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable parent) {
		return new EntityFrog(parent.worldObj);
	}
	
	@Override
	protected void fall(float p_70069_1_) {}
	
	@Override
    protected Item getDropItem()
    {
        return null;
    }
	
	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}
	
	public ItemStack getEggItem()
	{
		return QTI.frogEggs.get(1);
	}
	
	@Override
	public boolean hitByEntity(Entity entity)
	{
		if (entity.worldObj.isRemote) return super.hitByEntity(entity);
		if (entity instanceof EntityLivingBase)
		{
			ItemStack held = ((EntityLivingBase)entity).getHeldItem();
			if (held != null && Item.getIdFromItem(held.getItem()) == Item.getIdFromItem(Items.glass_bottle))
			{
				held.stackSize = held.stackSize - 1;
				NBTTagCompound frogData = UT.NBT.make();
				this.writeToNBT(frogData);
				ItemStack newFrog = QTI.jarFrog.get(1);
				UT.NBT.set(newFrog, frogData);
				if (entity instanceof EntityPlayer)
				{
					InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
					if (!inventory.addItemStackToInventory(newFrog))
					{
						this.entityDropItem(newFrog, 1);
					}
				}
				this.setDead();
				return true;
			}
		}
		this.getJumpHelper().setJumping();
		return super.hitByEntity(entity);
	}
	
	@Override
	public boolean interact(EntityPlayer player)
	{
		if (player.worldObj.isRemote) return super.interact(player);
		ItemStack held = player.getHeldItem();
		if (held != null && Item.getIdFromItem(held.getItem()) == Item.getIdFromItem(Items.glass_bottle))
		{
			held.stackSize = held.stackSize - 1;
			NBTTagCompound frogData = UT.NBT.make();
			this.writeToNBT(frogData);
			ItemStack newFrog = QTI.jarFrog.get(1);
			UT.NBT.set(newFrog, frogData);
			player.inventory.addItemStackToInventory(newFrog);
			//player.setCurrentItemOrArmor(0, held);
			this.setDead();
			return true;
		}
		return super.interact(player);
	}
	
	@Override
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (!this.getNavigator().noPath() && !this.isJumping)
        {
        	if (this.onGround || this.inWater)
        	{
	        	this.getJumpHelper().setJumping();
        	}
        }
        if (!this.worldObj.isRemote && !this.isChild() && this.inWater && this.rand.nextInt(500) == 0)
        {
        	List frogs = this.worldObj.getEntitiesWithinAABB(EntityFrog.class, this.boundingBox.expand(16, 8, 16));
        	if (frogs.size() > 1 && frogs.size() < 6)
        	{
        		this.entityDropItem(this.getEggItem(), 1);
        	}
        }
    }
	
	@Override
	public int getTalkInterval()
    {

    	return 30;
    }
	
	@Override
	protected String getLivingSound()
    {
		if (isRaggy) return "qwertech:mob.bullfrog.spring";
    	return "qwertech:mob.bullfrog.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
	@Override
    protected String getHurtSound()
    {
		if (isRaggy) return "qwertech:mob.bullfrog.spring";
    	return "qwertech:mob.bullfrog.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
		if (isRaggy) return "qwertech:mob.bullfrog.spring";
    	return "qwertech:mob.bullfrog.death";
    }

	@Override
	public String getCustomNameTag() {
    	if (isRaggy) {
    		return "Michigan J.";
		}
		return super.getCustomNameTag();
	}

	/**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = 2;
        if (p_70628_2_ > 0) j += this.rand.nextInt(p_70628_2_);

        if (this.isBurning())
        {
            this.entityDropItem(QTI.frogLegCooked.get(j), 1);
        }
        else
        {
        	this.entityDropItem(QTI.frogLegRaw.get(j), 1);
        }
    }
}
