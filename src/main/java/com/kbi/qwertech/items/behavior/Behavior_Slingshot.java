package com.kbi.qwertech.items.behavior;

import gregapi.data.CS;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class Behavior_Slingshot extends AbstractBehaviorDefault{
	
	public final String mSoundName;
	public final long mDamage;
	public final int finishTime;

	public Behavior_Slingshot(String aSoundName, long aDamage)
	{
		this(aSoundName, aDamage, 72000);
	}

	public Behavior_Slingshot(String aSoundName, long aDamage, int aFinishTime)
	{
		this.mSoundName = aSoundName;
		this.mDamage = aDamage;
		this.finishTime = aFinishTime;
	}

	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (aStack.getItemDamage() == CS.ToolsGT.WRENCH && aItem == CS.ToolsGT.sMetaTool)
		{
			aList.add(2, "Hold Rightclick to dismantle undamaged tool on left");
		}
		return super.getAdditionalToolTips(aItem, aList, aStack);
	}

	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer)
	{
		aPlayer.setItemInUse(aStack, finishTime);
		return aStack;
	}
}
