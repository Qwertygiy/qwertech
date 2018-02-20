package com.kbi.qwertech.items.stats;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import gregapi.util.ST;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class QT_Tool_Mattock
  extends ToolStats
{
	@Override
  public int getToolDamagePerBlockBreak()
  {
    return 50;
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
    return 200;
  }
  
	@Override
  public int getBaseQuality()
  {
    return 0;
  }
  
	@Override
  public float getBaseDamage()
  {
    return 1.75F;
  }
  
	@Override
  public float getSpeedMultiplier()
  {
    return 1.0F;
  }
  
	@Override
  public float getMaxDurabilityMultiplier()
  {
    return 1.5F;
  }
  
	@Override
  public boolean canCollect()
  {
    return false;
  }
  
	@Override
  public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent)
  {
    if (((aBlock.getMaterial() == Material.ice) || (aBlock.getMaterial() == Material.packedIce)) && (aDrops.isEmpty()))
    {
      aDrops.add(ST.make(aBlock, 1L, aMetaData));
      aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
      aEvent.dropChance = 1.0F;
      return 0;
    }
    return 0;
  }
  
	@Override
  public boolean isMinableBlock(Block aBlock, byte aMetaData)
  {
    String tTool = aBlock.getHarvestTool(aMetaData);
    return ((tTool != null) && ((tTool.equalsIgnoreCase("axe")) || (tTool.equalsIgnoreCase("saw")))) || (aBlock.getMaterial() == Material.leaves) || (aBlock.getMaterial() == Material.vine) || (aBlock.getMaterial() == Material.plants) || (aBlock.getMaterial() == Material.gourd) || (aBlock.getMaterial() == Material.wood) || (aBlock.getMaterial() == Material.cactus) || (aBlock.getMaterial() == Material.ice) || (aBlock.getMaterial() == Material.packedIce) || (aBlock.getMaterial() == Material.ground) || (aBlock.getMaterial() == Material.grass);
  }
  
	@Override
  public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ)
  {
    if (!aBlock.isWood(aPlayer.worldObj, aX, aY, aZ))
    {
      if (!OP.log.contains(new ItemStack[] { ST.make(aBlock, 1L, aMetaData) })) {}
    }
    else {
      return aDefault * 2.0F;
    }
    if (aBlock.getMaterial() != Material.wood)
    {
      if (!OP.plank.contains(new ItemStack[] { ST.make(aBlock, 1L, aMetaData) })) {}
    }
    else {
      return aDefault / 2.0F;
    }
    if (aBlock.getMaterial() != Material.ground && aBlock.getMaterial() != Material.grass)
    {
    	if (!OP.dirt.contains(new ItemStack[] { ST.make(aBlock, 1L, aMetaData) })) {}
    }
    else {
    	return aDefault * 2.0F;
    }
    return (aBlock.getMaterial() == Material.vine) || (aBlock.getMaterial() == Material.plants) || (aBlock.getMaterial() == Material.gourd) ? aDefault / 4.0F : aDefault;
  }
  
	@Override
  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
  {
    return aIsToolHead ? (IIconContainer)MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(gregapi.oredict.OreDictPrefix.get("toolHeadMattock").mIconIndexItem) : (IIconContainer)MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
  }
  
	@Override
  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
  {
    return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
  }
  
	@Override
  public void onStatsAddedToTool(MultiItemTool aItem, int aID)
  {
	  aItem.addItemBehavior(aID, new Behavior_Tool("hoe", null, 100L, !canBlock()));
  }
  
	@Override
  public String getDeathMessage()
  {
    return "[KILLER] got rid of [VICTIM]... automattockally";
  }
}
