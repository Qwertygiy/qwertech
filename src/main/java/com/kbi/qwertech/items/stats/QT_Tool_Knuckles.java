package com.kbi.qwertech.items.stats;

import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import com.kbi.qwertech.QwerTech;

public class QT_Tool_Knuckles
  extends ToolStats
{
	@Override
  public int getToolDamagePerBlockBreak()
  {
    return 0;
  }
  
	@Override
  public int getToolDamagePerDropConversion()
  {
    return 100;
  }
  
	@Override
  public int getToolDamagePerContainerCraft()
  {
    return 100;
  }
  
	@Override
  public int getToolDamagePerEntityAttack()
  {
    return 50;
  }
  
	@Override
  public int getBaseQuality()
  {
    return 0;
  }
  
	@Override
  public float getBaseDamage()
  {
    return 3F;
  }
	
  @Override
  public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity)
  {
	  if (!(aEntity instanceof EntityGolem))
	  {
		  return aOriginalHurtResistance / 2;
	  } else {
		  return aOriginalHurtResistance;
	  }
  }
  
	@Override
  public float getSpeedMultiplier()
  {
    return 2.0F;
  }
  
	@Override
  public float getMaxDurabilityMultiplier()
  {
    return 1.2F;
  }
  
	@Override
  public boolean canCollect()
  {
    return false;
  }
	
	@Override
	public boolean isWeapon()
	{
		return true;
	}
  
  
	@Override
  public boolean isMinableBlock(Block aBlock, byte aMetaData)
  {
     return true; /* it can punch anything, but at great cost */
  }
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent)
	{
		if (!((aBlock instanceof BlockTorch) || (aBlock instanceof BlockButton) || (aBlock instanceof BlockPressurePlate) || (aBlock instanceof BlockRedstoneWire) || (aBlock instanceof BlockTripWire) || (aBlock.getMaterial() == Material.glass) || (aBlock.getMaterial() == Material.leaves) || (aBlock.getMaterial() == Material.plants) || (aBlock.getMaterial() == Material.vine) || (aBlock.getMaterial() == Material.cloth) || (aBlock.getMaterial() == Material.snow)))
		{
			if (aBlock.getMaterial() == Material.sand || aBlock.getMaterial() == Material.ground || aBlock.getMaterial() == Material.grass || aBlock.getMaterial() == Material.clay)
			{
				return 5;
			} else {
				return 20;
			}
		}
		return 0;
	}

  
	@Override
  public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ)
  {
		return Math.min(5, aDefault * 2);
  }
  
	@Override
  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
  {
    return MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(QwerTech.knucklesTexID);
  }
  
	@Override
  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
  {
    return MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
  }
  
	@Override
  public void onStatsAddedToTool(MultiItemTool aItem, int aID)
  {
	  //aItem.addItemBehavior(aID, new Behavior_Tool("hoe", null, 100L, !canBlock()));
  }
  
	@Override
  public String getDeathMessage()
  {
    return "[KILLER] introduced [VICTIM] to Ol' Righty";
  }
}
