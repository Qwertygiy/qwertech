package com.kbi.qwertech.entities.projectile;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.UT;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.QTConfigs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityRock extends EntityThrowable {
	
	public Entity shootingEntity;
	public OreDictMaterial material = MT.Stone;
	/** 0 = rock, 1 = chunk **/
	public float strength = 1F;
	public short objectType = 0;
	public int timeGrounded = 0;
    //private double damage = 2.0D;
    /** The amount of knockback an arrow applies when it hits a mob. */
    private int knockbackStrength = 1;
	
	public EntityRock(World world) {
		super(world);
	}
	
	public EntityRock(World world, EntityLivingBase eb2)
	{
		super(world, eb2);
	}
	
	public EntityRock(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
	
	 public EntityRock(World p_i1755_1_, EntityLivingBase p_i1755_2_, float p_i1755_4_, OreDictMaterial mt, short rockType)
	    {
	        super(p_i1755_1_, p_i1755_2_);
	        this.renderDistanceWeight = 10.0D;
	        this.shootingEntity = p_i1755_2_;
	        this.setMaterial(mt);
	        this.setType(rockType);
	        this.strength = p_i1755_4_;
	        
	        this.setSize(0.25F, 0.25F);
	        this.setLocationAndAngles(p_i1755_2_.posX, p_i1755_2_.posY + p_i1755_2_.getEyeHeight(), p_i1755_2_.posZ, p_i1755_2_.rotationYaw, p_i1755_2_.rotationPitch);
	        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
	        this.posY -= 0.10000000149011612D;
	        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
	        this.setPosition(this.posX, this.posY, this.posZ);
	        this.yOffset = 0.0F;
	        float f = Math.max(0.2F, p_i1755_4_);
	        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f;
	        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f;
	        this.motionY = -MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * f;
	        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);

	        //this.setSize(0.5F, 0.5F);
	    }

	@Override
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_,
			double p_70186_5_, float p_70186_7_, float p_70186_8_) {
		super.setThrowableHeading(p_70186_1_, p_70186_3_, p_70186_5_, p_70186_7_, p_70186_8_);	
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		super.entityInit();
		this.dataWatcher.addObject(13, "");
		this.dataWatcher.addObject(12, (short)0);
	}
	
	public int getDamage()
	{
		int bySpeed = Math.round(this.strength * 2);
		int returnable = 1;
		if (this.material.mToolQuality != 0) {
			returnable = this.material.mToolQuality + this.objectType + 1 + bySpeed;
		} else {
			returnable = this.objectType + 2 + bySpeed;
		}
		if (this.isBurning()) returnable = returnable + 1;
		return returnable;
	}
	
	public void setMaterial(OreDictMaterial od)
	{
		this.material = od;
	    this.dataWatcher.updateObject(13, od.mNameInternal);
	}
	
	public OreDictMaterial getMaterial()
	{
		String DW = this.dataWatcher.getWatchableObjectString(13);
		if (DW != null) {
			return OreDictMaterial.get(DW);
		} else {
			return this.material;
		}
	}
	
	public short getType()
	{
		short t = this.dataWatcher.getWatchableObjectShort(12);
		return t;
	}
	
	public void setType(short t)
	{
		this.objectType = t;
		this.dataWatcher.updateObject(12, t);
	}
	
	@Override
	public void onUpdate()
    {
        super.onUpdate();
        if (this.ticksExisted > 1000 && this.ticksExisted < 1100)
        {
        	this.motionX = 0;
        	this.motionY = 0;
        	this.motionZ = 0;
        	//this.setVelocity(0, 0, 0);
        } else if (this.ticksExisted > 1100)
        {
        	this.setDead();
        }
    }
	

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		this.setMaterial(OreDictMaterial.get(tag.getString("material")));
		this.objectType = tag.getShort("type");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setString("material", this.getMaterial().mNameInternal);
		tag.setShort("type", this.objectType);
	}
	
	@Override
	protected boolean canTriggerWalking()
    {
        return false;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }
	
	@Override
	protected float func_70182_d()
    {
        return 1.0F;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z)
	{
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}
	
	public void bounce(double x, double y, double z, double strength)
	{
		x *= strength;
		y *= strength;
		z *= strength;
		this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        //this.setVelocity(this.motionX, this.motionY, this.motionZ);
        float f3 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, f3) * 180.0D / Math.PI);
        if (this.getType() == 0)
        {
        	UT.Sounds.send(this.worldObj, Block.soundTypeStone.getStepResourcePath(), 0.5F, 0.8F + new Random().nextFloat(), (int)this.posX, (int)this.posY, (int)this.posZ);
        } else {
        	UT.Sounds.send(this.worldObj, Block.soundTypeMetal.getStepResourcePath(), 0.5F, 0.8F + new Random().nextFloat(), (int)this.posY, (int)this.posY, (int)this.posZ);
        }
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		//if (!(this.worldObj.isRemote)) {
			boolean bounce = false;
			if (mop.typeOfHit == MovingObjectType.ENTITY && (mop.entityHit != this.getThrower() || this.ticksExisted > 20)) {
				if (!(this.worldObj.isRemote))
				{
					
					mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.getDamage());
					
					if (mop.entityHit instanceof EntityLivingBase) {
						UT.Enchantments.applyBullshitA((EntityLivingBase)mop.entityHit, this.getThrower() == null ? this : this.getThrower(), OP.arrowGtWood.mat(this.getMaterial(), 1));
						UT.Enchantments.applyBullshitB((this.getThrower() instanceof EntityLivingBase) ? (EntityLivingBase)this.getThrower() : null, mop.entityHit, OP.arrowGtWood.mat(this.getMaterial(), 1));
					}
					
					if (this.getMaterial().contains(TD.Properties.FLAMMABLE) || this.isBurning())
					{
						this.setFire(1);
						mop.entityHit.setFire(2);
					}
					
					if (this.getMaterial().contains(TD.Properties.EXPLOSIVE) && QTConfigs.slingshotExplode && (new Random().nextFloat() > 0.5 || this.isBurning()))
					{
						this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2, true);
						this.setDead();
					}
				}
				
				bounce = true; //bounce off mob
			} else if (mop.typeOfHit == MovingObjectType.BLOCK) {
				if (this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.glass && QTConfigs.slingshotGlass && (!(this.getThrower() instanceof EntityPlayerMP) || this.worldObj.canMineBlock((EntityPlayer)this.getThrower(), mop.blockX, mop.blockY, mop.blockZ))) {
					double speed = Math.abs(this.motionX) + Math.abs(this.motionY) + Math.abs(this.motionZ);
					Block block = this.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
					if (!(this.worldObj.isRemote) && ((block.getBlockBoundsMaxX() == 1 && block.getBlockBoundsMaxZ() == 1) && speed > 2) || speed > 1) {
						UT.Sounds.send(this.worldObj, Block.soundTypeGlass.getBreakSound(), 1, 1, mop.blockX, mop.blockY, mop.blockZ);
						this.worldObj.setBlockToAir(mop.blockX, mop.blockY, mop.blockZ);
						this.bounce(this.motionX, this.motionY, this.motionZ, 0.6);
						QwerTech.achievementHandler.issueAchievement((EntityPlayerMP)this.getThrower(), "brokenWindow");
					} else {
						bounce = true; //bounce off glass
					}
				}
				if (this.worldObj.isSideSolid(mop.blockX, mop.blockY, mop.blockZ, ForgeDirection.getOrientation(mop.sideHit))) {
					bounce = true; //bounce off side
				} /*else if ((Math.abs(this.motionY) < 2) && (Math.abs(this.motionX) + Math.abs(this.motionZ) > 0.5) && this.worldObj.isAnyLiquid(AxisAlignedBB.getBoundingBox(mop.blockX, mop.blockY, mop.blockZ, mop.blockX, mop.blockY, mop.blockZ))) {
					bounce = true; //skip on water; doesn't work though
				}*/
			}
			if (bounce) {
				switch (mop.sideHit) {
					case 0: {
						this.posY = this.posY - 0.1;
						this.bounce(this.motionX, -this.motionY, this.motionZ, 0.6);
						break;
					}
					case 1: {
						this.posY = this.posY + 0.1;
						this.bounce(this.motionX, -this.motionY, this.motionZ, 0.6);
						break;
					}
					case 2: {
						this.posZ = this.posZ + 0.1;
						this.bounce(this.motionX, this.motionY, -this.motionZ, 0.6);
						break;
					}
					case 3: {
						this.posZ = this.posZ - 0.1;
						this.bounce(this.motionX, this.motionY, -this.motionZ, 0.6);
						break;
					}
					case 4: {
						this.posX = this.posX - 0.1;
						this.bounce(-this.motionX, this.motionY, this.motionZ, 0.6);
						break;
					}
					case 5: {
						this.posX = this.posX + 0.1;
						this.bounce(-this.motionX, this.motionY, this.motionZ, 0.6);
						break;
					}
					default: {
						this.bounce(-this.motionX, -this.motionY, -this.motionZ, 0.6);
						break;
					}
				}
				if (!this.worldObj.isRemote && ((Math.abs(this.motionX) + Math.abs(this.motionY) + Math.abs(this.motionZ)) < 0.1)) {
					Random rand = new Random();
					if (this.getMaterial().contains(TD.Properties.EXPLOSIVE) && QTConfigs.slingshotExplode && rand.nextFloat() > 0.5)
					{
						this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2, true);
						this.setDead();
					} else if (this.getMaterial() != null && this.getMaterial() != MT.NULL) {
						if (this.objectType == 0) {
							if (rand.nextFloat() < 0.75) {
								this.entityDropItem(OP.rockGt.mat(this.getMaterial(), 1), 1);
							} else {
								this.entityDropItem(OP.dustTiny.mat(this.getMaterial(), 1), 1);
							}
						} else {
							if (rand.nextFloat() < 0.9) {
								this.entityDropItem(OP.chunkGt.mat(this.getMaterial(), 1), 1);
							} else {
								this.entityDropItem(OP.nugget.mat(this.getMaterial(), 1), 1);
							}
						}
					}
					this.setDead();
				}
			}
		//}
	}

}
