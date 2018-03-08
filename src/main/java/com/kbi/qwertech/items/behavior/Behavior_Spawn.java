package com.kbi.qwertech.items.behavior;

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Behavior_Spawn extends AbstractBehaviorDefault {

	Class spawn;
	
	public <E extends Entity> Behavior_Spawn(Class<E> Spawnable) {
		this.spawn = Spawnable;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ)
	{
		if (aWorld.isRemote) return true;
		try {
			Entity entity = (Entity)spawn.getConstructor(World.class).newInstance(new Object[] {aWorld});
			NBTTagCompound tagger = UT.NBT.getNBT(aStack);
			entity.readFromNBT(tagger);
			entity.setPosition(aX + hitX + entity.width, aY + hitY + entity.height, aZ + hitZ + entity.width);
			aWorld.spawnEntityInWorld(entity);
			aStack.stackSize = aStack.stackSize - 1;
			if (aItem.hasContainerItem(aStack))
			{
				if (!aPlayer.inventory.addItemStackToInventory(aItem.getContainerItem(aStack)))
				{
					aPlayer.entityDropItem(aItem.getContainerItem(aStack), 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
