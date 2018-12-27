package com.kbi.qwertech.entities.projectile;

import com.kbi.qwertech.QwerTech;
import gregapi.data.IL;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.UT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class EntityBall extends EntityRock {
	
	public EntityBall(World world) {
		super(world);
	}
	
	public EntityBall(World world, EntityLivingBase eb2)
	{
		super(world, eb2);
	}
	
	public EntityBall(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
	
	 public EntityBall(World p_i1755_1_, EntityLivingBase p_i1755_2_, float p_i1755_4_, OreDictMaterial mt, short rockType)
	    {
	        super(p_i1755_1_, p_i1755_2_, p_i1755_4_, mt, rockType);
		 	//System.out.println("Recieved a" + rockType);
		 	//System.out.println("We are now a " + this.getType());
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
	}
	
	@Override
	public int getDamage()
	{
		/*
		short condition = this.getType();
		switch (condition)
		{
			case 0:
				return 0;
			case 1:
				return 0;
			default:
				return 0;
		}*/
		return 0;
	}
	
	
	@Override
	public void onUpdate()
    {
        super.onUpdate();
        Entity entity = null;
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;
        EntityLivingBase entitylivingbase = this.getThrower();

        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        for (int j = 0; j < list.size(); ++j)
        {
            Entity entity1 = (Entity)list.get(j);

            if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksExisted >= 5))
            {
                float f = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                if (movingobjectposition1 != null)
                {
                    double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
            	int type = this.getType();
            	if (type == 5) QwerTech.achievementHandler.issueAchievement((EntityPlayer)entity, "mudSplat");
            	if (this.worldObj.isRemote && entity == Minecraft.getMinecraft().thePlayer)
            	{
            		switch (type)
            		{
	            		case 4:
	            		{
	            			com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{100, 255, 100, 175});
	            			break;
	            		}
	            		case 5:
	            		{
	            			com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{30, 10, 0, 255});
	            			break;
	            		}
	            		case 6:
	            		{
	            			com.kbi.qwertech.client.QT_GUIHandler.addNewSplat(new short[]{222, 0, 0, 222});
	            			break;
	            		}
            		}
            	}
            }
        }
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
        	UT.Sounds.send(this.worldObj, "mob.slime.small", 0.5F, 0.8F + new Random().nextFloat(), (int)this.posX, (int)this.posY, (int)this.posZ);
        } else {
        	UT.Sounds.send(this.worldObj, "mob.slime.small", 0.5F, 0.8F + new Random().nextFloat(), (int)this.posY, (int)this.posY, (int)this.posZ);
        }
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		//if (!(this.worldObj.isRemote)) {
			boolean bounce = false;
			if (mop.typeOfHit == MovingObjectType.ENTITY && (mop.entityHit != this.getThrower() || this.ticksExisted > 20)) {
				if (!(this.worldObj.isRemote))
				{
					if (this.getType() == 4){
						mop.entityHit.attackEntityFrom((new EntityDamageSourceIndirect("thrownSlime", this, this.getThrower())).setProjectile(), this.getDamage());
					} else if (this.getType() == 5){
						mop.entityHit.attackEntityFrom((new EntityDamageSourceIndirect("thrownMud", this, this.getThrower())).setProjectile(), this.getDamage());
					}
					
					OreDictMaterial mat = this.getMaterial();
					if (mat.contains(TD.Properties.FLAMMABLE) || this.isBurning())
					{
						this.setFire(2);
						mop.entityHit.setFire(1);
					}
					
					if (mat.contains(TD.Properties.EXPLOSIVE) && (new Random().nextFloat() > 0.5 || this.isBurning()))
					{
						this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1, true);
						this.setDead();
					}
				}
				
				bounce = true; //bounce off mob
			} else if (mop.typeOfHit == MovingObjectType.BLOCK) {
				if (this.worldObj.isSideSolid(mop.blockX, mop.blockY, mop.blockZ, ForgeDirection.getOrientation(mop.sideHit))) {
					bounce = true; //bounce off side
				} /*else if ((Math.abs(this.motionY) < 2) && (Math.abs(this.motionX) + Math.abs(this.motionZ) > 0.5) && this.worldObj.isAnyLiquid(AxisAlignedBB.getBoundingBox(mop.blockX, mop.blockY, mop.blockZ, mop.blockX, mop.blockY, mop.blockZ))) {
					bounce = true; //skip on water; doesn't work though
				}*/
			}
			if (bounce) {
				if (!this.worldObj.isRemote) {
					//Random rand = new Random();
					switch (this.getType())
					{
						case 4:
						{
							this.entityDropItem(new ItemStack(Items.slime_ball), 1);
							break;
						}
						case 5:
						{
							this.entityDropItem(IL.Mud_Ball.get(1, (Object[])null), 1);
							break;
						}
						case 6:
						{
							//this.entityDropItem(IL.Food_Tomato.get(1, (Object[])null), 1);
						}
					}
					this.setDead();
				}
			}
		//}
	}

}
