package com.kbi.qwertech.entities.ai;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.items.behavior.Behavior_Swing;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;

public class EntityAISwing extends EntityAIBase {
	
	private EntityCreature thisEntity;
	
	public EntityAISwing(EntityCreature entity)
	{
		thisEntity = entity;
		this.setMutexBits(8);
	}

	@Override
	public boolean shouldExecute() {
		if (!this.thisEntity.isSwingInProgress)
		{
			ItemStack is = this.thisEntity.getHeldItem();
			if (is != null && is.getItem() == QTI.qwerTool.getItem())
			{
				ArrayList<IBehavior<MultiItem>> behaviors = ((MultiItemTool)QTI.qwerTool.getItem()).mItemBehaviors.get((short) QTI.qwerTool.getItem().getDamage(is));
				if (behaviors != null)
				{
					for (int q = 0; q < behaviors.size(); q++)
					{
						if (behaviors.get(q) instanceof Behavior_Swing)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void startExecuting()
	{
        boolean shouldSwing = false;
        Vec3 vec31 = thisEntity.getLookVec();
        float f1 = 1F;
        List entities = thisEntity.worldObj.getEntitiesWithinAABBExcludingEntity(thisEntity, thisEntity.boundingBox.addCoord(vec31.xCoord * 2, vec31.yCoord * 2, vec31.zCoord * 2).expand(f1, f1, f1).offset(vec31.xCoord, vec31.yCoord, vec31.zCoord));
        for (int q = 0; q < entities.size(); q++)
        {
        	Entity check = (Entity)entities.get(q);
        	//System.out.println("Batzombie found an " + check.getClass().getName());
			if (check instanceof IProjectile)
			{
				shouldSwing = true;
				thisEntity.getLookHelper().setLookPositionWithEntity(check, 10, thisEntity.getVerticalFaceSpeed());
			}
		}
		if (shouldSwing)
		{
			System.out.println("Is swing!");
			this.thisEntity.swingItem();
		}
	}
	
	public boolean continueExecuting()
	{
		return false;
	}

}
