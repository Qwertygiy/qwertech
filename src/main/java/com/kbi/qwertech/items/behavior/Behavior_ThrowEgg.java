package com.kbi.qwertech.items.behavior;

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.behaviors.IBehavior.Behaviour_None;
import gregapi.util.UT;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.kbi.qwertech.entities.neutral.EntityTurkey;
import com.kbi.qwertech.entities.projectile.EntityEgg;

public class Behavior_ThrowEgg extends gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault implements IBehavior<MultiItem> {
	
	public Behavior_ThrowEgg() {
	}

	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld,
			EntityPlayer aPlayer) {
		if (!aWorld.isRemote)
		{
			System.out.println("TRYING TO THROW A TURKEY");
			EntityEgg eggSpawn = new EntityEgg(aWorld, aPlayer);
			eggSpawn.setAnimalType(new EntityTurkey(aWorld));
			if (!UT.Entities.hasInfiniteItems(aPlayer)) --aStack.stackSize;
			aWorld.spawnEntityInWorld(eggSpawn);
		}
		return aStack;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList,
			ItemStack aStack) {
		//aList.add("Spawns a " + this.spawner.);
		return aList;
	}
}
