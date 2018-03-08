package com.kbi.qwertech.items.behavior;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.registry.MobScrapeRegistry;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Behavior_Scrape extends AbstractBehaviorDefault{
	
	public final float mChances;
	public final Random rand;

	public Behavior_Scrape(float aChance)
	{
		this.mChances = aChance;
		this.rand = new Random();
	}
	
	@Override public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) 
	{
		IToolStats tStats = ((MultiItemTool)aItem).getToolStats(aStack);
		OreDictMaterial secondary = MultiItemTool.getSecondaryMaterial(aStack, MT.NULL);
		if (tStats != null && secondary != MT.NULL && secondary != MT.Empty) 
		{
			float damage = tStats.getBaseDamage() + secondary.mToolQuality;
			for (int q = 0; q < aList.size(); q++)
			{
				if (aList.get(q).contains("Attack Damage"))
				{
					aList.set(q, LH.Chat.WHITE + "Attack Damage: " + LH.Chat.BLUE + "+" + damage + LH.Chat.RED + " (= " + ((damage+1)/2) + " Hearts)" + LH.Chat.GRAY);
				}
			}
		}
		return aList;
	}
	
	@Override 
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity)
	{
		//System.out.println("Smacked a " + aEntity.getClass().getName());
		OreDictMaterial mat = MultiItemTool.getSecondaryMaterial(aStack);
		if (mat != MT.NULL && mat != MT.Empty && mat != MT.Butter)
		{ //if we've added any spikes, behavior is a little more... destructive
			if (aEntity instanceof EntityThrowable)
			{
				if (aEntity instanceof EntityPotion)
				{//smacking a potion just applies it to the smacker
					for (int j = 0; j < 8; ++j)
			        {
			            aEntity.worldObj.spawnParticle("bubble", aEntity.posX, aEntity.posY, aEntity.posZ, 0.0D, 0.0D, 0.0D);
			        }
					if (!aEntity.worldObj.isRemote)
			        {
			            List list = Items.potionitem.getEffects(((EntityPotion)aEntity).getPotionDamage());

			            if (list != null && !list.isEmpty())
			            {
			            	Iterator iterator1 = list.iterator();

			                while (iterator1.hasNext())
			                {
			                	PotionEffect potioneffect = (PotionEffect)iterator1.next();
			                    int i = potioneffect.getPotionID();
			                    if (Potion.potionTypes[i].isInstant())
			                    {
			                    	Potion.potionTypes[i].affectEntity(((EntityPotion)aEntity).getThrower(), aPlayer, potioneffect.getAmplifier(), 1);
			                    } else {
			                        aPlayer.addPotionEffect(new PotionEffect(i, potioneffect.getDuration(), potioneffect.getAmplifier()));
			                    }
			                }
			            }

			            aEntity.worldObj.playAuxSFX(2002, (int)Math.round(aEntity.posX), (int)Math.round(aEntity.posY), (int)Math.round(aEntity.posZ), ((EntityPotion)aEntity).getPotionDamage());
			            aEntity.setDead();
			        }
				} else {//this is egg, snowball, etc. just destroy
					for (int j = 0; j < 8; ++j)
			        {
			            aEntity.worldObj.spawnParticle("snowballpoof", aEntity.posX, aEntity.posY, aEntity.posZ, 0.0D, 0.0D, 0.0D);
			        }
					if (!aEntity.worldObj.isRemote)
			        {
			            aEntity.setDead();
			        }
				}
			} else if (aEntity instanceof EntityFireball && !(aEntity instanceof EntityWitherSkull))
			{//smack it to dustereens
				for (int j = 0; j < 8; ++j)
		        {
		            aEntity.worldObj.spawnParticle("smoke", aEntity.posX, aEntity.posY, aEntity.posZ, 0.0D, 0.0D, 0.0D);
		        }
				if (!aEntity.worldObj.isRemote)
		        {
					aEntity.entityDropItem(OP.dustSmall.mat(MT.Ash, 1), 0);
		            aEntity.setDead();
		        }
			} else if (aEntity instanceof IProjectile)
			{//anything else: arrows, bullets, rocks, whatever
				//meh
			} else if (aEntity.worldObj.isRemote) {
				return false;
			} else if (rand.nextFloat() > mChances) {
				return false;
			} 
			
			if (MobScrapeRegistry.isRegistered(aEntity.getClass()))
			{
				ItemStack droppable = MobScrapeRegistry.getRandomItem(MobScrapeRegistry.getKey(aEntity.getClass()));
				if (droppable != null)
				{
					aEntity.entityDropItem(droppable, 1);
				}
			} else if (aEntity instanceof EntityPlayer && QTConfigs.canScrapePlayers) {
				int randomChoice = aEntity.worldObj.rand.nextInt(((EntityPlayer)aEntity).inventory.getSizeInventory());
				ItemStack randomSlot = ((EntityPlayer)aEntity).inventory.getStackInSlot(randomChoice);
				if (randomSlot != null)
				{//smack away a single item (not a single stack!)
					((EntityPlayer)aEntity).dropPlayerItemWithRandomChoice(((EntityPlayer)aEntity).inventory.decrStackSize(randomChoice, 1), false);
				}
			} else if (aEntity instanceof EntityAnimal && QTConfigs.doMobScrapesDrop) {
				aEntity.entityDropItem(OP.scrapGt.mat(MT.MeatRaw, 1), 1);
			} else if (aEntity instanceof EntityMob && ((EntityMob)aEntity).isEntityUndead() && QTConfigs.doMobScrapesDrop) {
				aEntity.entityDropItem(OP.scrapGt.mat(MT.MeatRotten, 1), 1);
			}
		} else {
			//code handled by Behavior_Swing
		}
		return false;
	}
	
}