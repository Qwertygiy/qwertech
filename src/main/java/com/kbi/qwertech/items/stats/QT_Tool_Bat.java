package com.kbi.qwertech.items.stats;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.items.behavior.Behavior_Scrape;
import com.kbi.qwertech.items.behavior.Behavior_Swing;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class QT_Tool_Bat extends ToolStats {
	@Override
	public int getToolDamagePerContainerCraft()
	{
		return 999999999;
	}

	@Override
	public boolean isMinableBlock(Block arg0, byte arg1) {
		return arg0.getMaterial() == Material.glass;
	}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		OreDictMaterial secondary = MultiItemTool.getSecondaryMaterial(aStack);
		if (secondary != MT.NULL && secondary != MT.Empty && secondary != MT.Butter)
		{
			return aDefault * 5;
		}
		return aDefault;
	}
	
	@Override
	public boolean isWeapon()
	{
		return true;
	}
	
	@Override
	public boolean isMiningTool()
	{
		return false;
	}
	
	@Override
	public float getBaseDamage()
	{
		return 2.5F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier()
	{
	    return 0.8F;
	}
	
	@Override
	  public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity)
	  {
		  return aOriginalHurtResistance * 3 / 2;
	  }
	
	@Override
	public int getToolDamagePerEntityAttack()
	{
	    return 100;
	}
	
	@Override
	public ItemStack getBrokenItem(ItemStack aStack) {
		return null;
	}
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		OreDictMaterial mat = MultiItemTool.getSecondaryMaterial(aStack);
		if (mat != MT.NULL && mat != MT.Empty && mat != MT.Butter)
		{ //if we've added any spikes, behavior is a little more... destructive
			return (aOriginalDamage - MultiItemTool.getPrimaryMaterial(aStack).mToolQuality + mat.mToolQuality) * 1.5F;
		}
		return aOriginalDamage;
	}
	
		@Override
	  public void onStatsAddedToTool(MultiItemTool aItem, int aID)
	  {
		  aItem.addItemBehavior(aID, new Behavior_Scrape(1F));
		  aItem.addItemBehavior(aID, new Behavior_Swing(2));
	  }
		
		@Override
		public Enchantment[] getEnchantments(ItemStack aStack, OreDictMaterial aMaterial) {
			if (aMaterial.mToolQuality > 1)
			{
				return new Enchantment[]{Enchantment.knockback};
			}
			return new Enchantment[]{};
		}
		
		@Override
		public int[] getEnchantmentLevels(ItemStack aStack) {
			return new int[]{MultiItemTool.getPrimaryMaterial(aStack).mToolQuality - 2};
		}
	
		@Override
		  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
		  {
			OreDictMaterial secondary = MultiItemTool.getSecondaryMaterial(aStack, MT.NULL);
			if (secondary == MT.Empty || secondary == MT.Butter || secondary == MT.NULL)
			{
				return MultiItemTool.getPrimaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(QwerTech.batTexID);
			}
		    return aIsToolHead ? MultiItemTool.getSecondaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(QwerTech.batSpikeTexID) : MultiItemTool.getPrimaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(QwerTech.batTexID);
		  }
		  
			@Override
		  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
		  {			
				OreDictMaterial secondary = MultiItemTool.getSecondaryMaterial(aStack, MT.NULL);
				if (secondary == MT.Empty || secondary == MT.Butter || secondary == MT.NULL)
				{
					return MultiItemTool.getPrimaryMaterial(aStack, MT.Wood).mRGBaSolid;
				}
				return aIsToolHead ? MultiItemTool.getSecondaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getPrimaryMaterial(aStack, MT.Wood).mRGBaSolid;
		  }
	  
		@Override
	  public String getDeathMessage()
	  {
	    return "<[KILLER]>: Strike three, yer out, [VICTIM].";
	  }
		
		
}