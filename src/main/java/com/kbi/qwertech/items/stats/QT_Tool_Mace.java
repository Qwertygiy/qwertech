package com.kbi.qwertech.items.stats;

import com.kbi.qwertech.QwerTech;
import gregapi.data.ANY;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class QT_Tool_Mace
  extends ToolStats
{
	@Override
  public int getToolDamagePerBlockBreak()
  {
    return 100;
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
    return 5F;
  }
  
	@Override
  public float getSpeedMultiplier()
  {
    return 0.6F;
  }
	
  @Override
  public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity)
  {
	  return aOriginalHurtResistance * 3 / 2;
  }
  
  @Override
  public float getMaxDurabilityMultiplier()
  {
    return 1.0F;
  }
  
	@Override
  public boolean canCollect()
  {
    return false;
  }
  
	@Override
  public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent)
  {
    if (aBlock == Blocks.stone || aBlock == Blocks.cobblestone)
    {
    	aDrops.clear();
	    aDrops.add(OP.rockGt.mat(MT.Stone, 3 + aFortune));
	    aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
	    aEvent.dropChance = 1.0F;
	    QwerTech.achievementHandler.issueAchievement(aPlayer, "useMace");
	    return 0;
    } else {
    	for (int q = 0; q < aDrops.size(); q++) {
    		if (ANY.Stone.contains(aDrops.get(q)) && !(OP.dust.contains(aDrops.get(q)) || OP.dustImpure.contains(aDrops.get(q)) || OP.dustSmall.contains(aDrops.get(q)) || OP.dustTiny.contains(aDrops.get(q)))) {
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreAndesite.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Andesite, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreBasalt.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Basalt, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreBlackgranite.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.GraniteBlack, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreDiorite.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Diorite, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreEnd.contains(aDrops.get(q)) || OP.oreEndstone.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Endstone, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreLimestone.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Limestone, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreMarble.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Marble, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreMars.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.MarsRock, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreMoon.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.MoonRock, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreNether.contains(aDrops.get(q)) || OP.oreNetherrack.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Netherrack, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreRedgranite.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.GraniteRed, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreSandstone.contains(aDrops.get(q))) {
    			aDrops.add(new ItemStack(Blocks.sand, 1));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.oreSpace.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.SpaceRock, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		} else if (OP.ore.contains(aDrops.get(q))) {
    			aDrops.add(OP.rockGt.mat(MT.Stone, 1 + aFortune));
    			aDrops.add(OP.rockGt.mat(OM.data(aDrops.get(q)).mMaterial.mMaterial, 4 + aFortune));
    			aDrops.remove(q);
    			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
    		    aEvent.dropChance = 1.0F;
    		}
    	}
    }
    return 0;
  }
  
	@Override
  public boolean isMinableBlock(Block aBlock, byte aMetaData)
  {
    return true; //mace will smash anything
  }
	
  public boolean isWeapon()
  {
	  return true;
  }
  
  public boolean isMiningTool()
  {
	  return true;
  }
  
	@Override
  public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ)
  {
    if (aBlock.getMaterial() == Material.rock || aBlock.getMaterial() == Material.ground || aBlock.getMaterial() == Material.grass) {
    	return aDefault * 4.0F;
    } else if (aBlock.getMaterial() == Material.glass) {
    	return aDefault * 10.0F;
    } else if (aBlock.getMaterial() == Material.iron || aBlock.getMaterial() == Material.anvil) {
    	return aDefault / 3.0F;
    } else return aDefault;
  }
  
	@Override
  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
  {
    return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(gregapi.oredict.OreDictPrefix.get("toolHeadMace").mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
  }
  
	@Override
  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
  {
    return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
  }
  
	@Override
  public void onStatsAddedToTool(MultiItemTool aItem, int aID)
  {
	  //aItem.addItemBehavior(aID, new Behavior_Tool("hoe", null, 100L, !canBlock()));
  }
  
	@Override
  public String getDeathMessage()
  {
    return "[VICTIM] was maced by [KILLER], old-school";
  }
}
