package com.kbi.qwertech.items;

import com.kbi.qwertech.items.behavior.Behavior_AOE;
import com.mojang.authlib.GameProfile;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MultiItemTool_QT extends MultiItemTool {

	public MultiItemTool_QT(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
	}

	public boolean isLeft = true;
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack)
	{
		if (entity instanceof EntityPlayer && ((EntityPlayer)entity).openContainer != null)
		{
			if (((EntityPlayer)entity).openContainer.getClass() != ContainerPlayer.class || !isLeft)
			{
				isLeft = true;
				return false;
			}
		}
		boolean isAOE = false;
		ArrayList<IBehavior<MultiItem>> behaver = mItemBehaviors.get((short)getDamage(stack));
		if (behaver != null) for (IBehavior<MultiItem> tBehavior : behaver) try {
			if (tBehavior instanceof Behavior_AOE)
			{
				isAOE = true;
			}
		} catch (Exception ignored) {
			
		}
		if (isAOE) {
			Vec3 vec31 = entity.getLookVec();
	        float f1 = 1F;
	        List list = entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.addCoord(vec31.xCoord * 2, vec31.yCoord * 2, vec31.zCoord * 2).expand(f1, f1, f1).offset(vec31.xCoord, vec31.yCoord, vec31.zCoord));
	        for (int q = 0; q < list.size(); q++)
	        {
	        	if ((list.get(q) instanceof EntityLivingBase && ((EntityLivingBase)list.get(q)).hurtTime > 0))
	        	{
	        		//this means entity will be attacked anyway
	        	} else {
		        	if (entity instanceof EntityPlayer)
		        	{
		        		this.onSwipeEntity(stack, ((EntityPlayer)entity), (Entity)list.get(q));
		        	} else if (!entity.worldObj.isRemote) {
		        		FakePlayer player = FakePlayerFactory.get((WorldServer)entity.worldObj, new GameProfile(new UUID('0', '0'), entity.getCommandSenderName()));
		        		player.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
		        		player.rotationYawHead = entity.getRotationYawHead();
		        		this.onSwipeEntity(stack, player, (Entity)list.get(q));
		        		player = null;
		        	}
	        	}
	        }
		}
		return false;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		isLeft = true;
		ArrayList<IBehavior<MultiItem>> behaver = mItemBehaviors.get((short)getDamage(aStack));
		if (behaver != null) for (IBehavior<MultiItem> tBehavior : behaver) try {
			if (tBehavior instanceof Behavior_AOE)
			{
				return true;
			}
		} catch (Exception ignored) {
			
		}
		return super.onLeftClickEntity(aStack, aPlayer, aEntity);
	}
	
	public boolean onSwipeEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		return super.onLeftClickEntity(aStack, aPlayer, aEntity);
	}

	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		isLeft = false;
		return super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
	}
}
