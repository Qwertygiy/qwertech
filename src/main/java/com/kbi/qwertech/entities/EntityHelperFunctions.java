package com.kbi.qwertech.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityHelperFunctions {
	
	public static MovingObjectPosition getEntityLookTrace(World world, Entity entity, boolean par3, double range)
	  {
	    float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch);
	    float f2 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw);
	    double d0 = entity.prevPosX + (entity.posX - entity.prevPosX);
	    double d1 = entity.prevPosY + (entity.posY - entity.prevPosY);
	    if (!world.isRemote && entity instanceof EntityPlayer) {
	      d1 += 1.62D;
	    }
	    double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ);
	    Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
	    float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
	    float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
	    float f5 = -MathHelper.cos(-f1 * 0.01745329F);
	    float f6 = MathHelper.sin(-f1 * 0.01745329F);
	    float f7 = f4 * f5;
	    float f8 = f3 * f5;
	    double d3 = range;
	    if (entity instanceof EntityPlayerMP) {
	      d3 = ((EntityPlayerMP)entity).theItemInWorldManager.getBlockReachDistance();
	    }
	    Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
	    return world.func_147447_a(vec3, vec31, par3, !par3, par3);
	  }
	
}
