package com.kbi.qwertech.items.behavior;

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.item.multiitem.behaviors.IBehavior.Behaviour_None;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Slingshot extends AbstractBehaviorDefault{
	
	public final String mSoundName;
	public final long mDamage;

	public Behavior_Slingshot(String aSoundName, long aDamage)
	{
		this.mSoundName = aSoundName;
		this.mDamage = aDamage;
	}
	//nothing I can think of ATM
	
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer)
	{
		aPlayer.setItemInUse(aStack, 72000);
			/*if (aPlayer.getItemInUseDuration() <= 71900) { //useless for now
				ItemStack ammo = null;
				short type = 0;
				int slot = -1;
				ItemStack[] inventory = aPlayer.inventory.mainInventory;
				for (int q = 0; q < inventory.length; q++)
				{
					if (inventory[q] != null) {
						if (OP.rockGt.contains(inventory[q]))
						{
							ammo = inventory[q];
							slot = q;
							type = 0;
							break;
						} else if (OP.chunkGt.contains(inventory[q])) {
							ammo = inventory[q];
							slot = q;
							type = 1;
							break;
						} else if ((inventory[q]).getItem() == Items.egg) {
							ammo = inventory[q];
							slot = q;
							type = 10;
							break;
						} else {
							//do nothing, keep going
						}
					}
				}
				if (ammo != null)
				{
					if ((this.mDamage > 0L) && ((aPlayer == null) || (!UT.Entities.hasInfiniteItems(aPlayer)))) {
						((MultiItemTool)aItem).doDamage(aStack, UT.Code.units(this.mDamage, 10000L, this.mDamage, true), aPlayer);
						ammo.stackSize = ammo.stackSize - 1;
						if (ammo.stackSize < 1) {
							aPlayer.inventory.setInventorySlotContents(slot, null);
						} else {
							aPlayer.inventory.setInventorySlotContents(slot, ammo);
						}
					}
					if (this.mSoundName != null) {
						UT.Sounds.send(aWorld, this.mSoundName, 1.0F, 1.0F, (int)aPlayer.posX, (int)aPlayer.posY, (int)aPlayer.posZ);
					}
					if (!(aWorld.isRemote)) {
						if (type == (short)10) {
							aWorld.spawnEntityInWorld(new EntityEgg(aWorld, aPlayer));
						} else {
							aWorld.spawnEntityInWorld(new EntityRock(aWorld, aPlayer, 1, OM.anydata(ammo).mMaterial.mMaterial, type));
						}
					}
				}
			}*/
		return aStack;
	}
}
