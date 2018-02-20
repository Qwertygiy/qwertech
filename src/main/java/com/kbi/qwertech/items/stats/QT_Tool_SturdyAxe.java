package com.kbi.qwertech.items.stats;

import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.items.tools.early.GT_Tool_Axe;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class QT_Tool_SturdyAxe extends GT_Tool_Axe {

	@Override
	public float getMaxDurabilityMultiplier()
	{
		return 1.1F;
	}
	
	@Override
	public int getToolDamagePerDropConversion()
	{
	    return 20;
	}
	
	@Override
	public boolean isCrowbar()
	{
	    return true;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData)
	  {
	    if (((aBlock instanceof BlockRailBase)) || (aBlock.getMaterial() == Material.circuits) || (IL.TG_Ore_Cluster_1.equal(aBlock)) || (IL.TG_Ore_Cluster_2.equal(aBlock))) {
	      return true;
	    }
	    String tTool = aBlock.getHarvestTool(aMetaData);
	    if (UT.Code.stringValid(tTool)) {
	      return "crowbar".equalsIgnoreCase(tTool) || super.isMinableBlock(aBlock, aMetaData);
	    }
	    return super.isMinableBlock(aBlock, aMetaData);
	  }
	
	public long checkWood(Block block, int x, int y, int z, long increment, long durability, World world, boolean breakIt)
	{
		Block aBlock = world.getBlock(x, y, z);
		List<ItemStack> drops = aBlock.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		if (increment >= durability) return 0;
		if (aBlock != block) {
			if (!aBlock.isWood(world, x, y, z) && !OP.log.contains(ST.make(aBlock, 1, world.getBlockMetadata(x, y, z))))
			{
				if (!OP.log.contains(drops.toArray(new ItemStack[drops.size()])))
				{
					return 0;
				}
			}
		}
		//System.out.println("It's a match at " + x + " " + y + " " + z + " with durability " + durability);
		durability = durability - increment;
		long returnable = 0L;
		if (breakIt) {
			world.func_147480_a(x, y, z, true);
			returnable = checkWood(block, x + 1, y, z, increment, durability, world, breakIt) + checkWood(block, x - 1, y, z, increment, durability, world, breakIt) + checkWood(block, x + 1, y, z + 1, increment, durability, world, breakIt) + checkWood(block, x + 1, y, z - 1, increment, durability, world, breakIt) + checkWood(block, x - 1, y, z + 1, increment, durability, world, breakIt) + checkWood(block, x - 1, y, z - 1, increment, durability, world, breakIt) + checkWood(block, x, y, z + 1, increment, durability, world, breakIt) + checkWood(block, x, y, z - 1, increment, durability, world, breakIt);
		}
		if (y < world.getHeight())
		{
			y = y + 1;
			returnable = returnable + checkWood(block, x, y, z, increment, durability, world, breakIt) + checkWood(block, x + 1, y, z, increment, durability, world, breakIt) + checkWood(block, x - 1, y, z, increment, durability, world, breakIt) + checkWood(block, x + 1, y, z + 1, increment, durability, world, breakIt) + checkWood(block, x + 1, y, z - 1, increment, durability, world, breakIt) + checkWood(block, x - 1, y, z + 1, increment, durability, world, breakIt) + checkWood(block, x - 1, y, z - 1, increment, durability, world, breakIt) + checkWood(block, x, y, z + 1, increment, durability, world, breakIt) + checkWood(block, x, y, z - 1, increment, durability, world, breakIt);
		}
		if (breakIt && returnable <= 0) {WD.leafdecay(world, x, y, z, block); } //trigger leaf decay at the end of a branch
		return increment + returnable;
	}
	
	boolean LOCK = false;
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent)
	  {
		if (LOCK) return 0;
		LOCK = true;
	    int rAmount = 0;
	    if ((!aPlayer.worldObj.isRemote) && (!aPlayer.isSneaking())) {
	      if (aBlock.isWood(aPlayer.worldObj, aX, aY + 1, aZ) || OP.log.contains(aDrops.toArray(new ItemStack[aDrops.size()])))
	      {
	        try
	        {
	          long tIncrement = 1L;
	          rAmount = (int)checkWood(aBlock, aX, aY + 1, aZ, tIncrement, aAvailableDurability, aPlayer.worldObj, true);
	        }
	        catch (Throwable e) {
	        	e.printStackTrace();
	        }
	      }
	    }
	    LOCK = false;
	    return rAmount;
	  }
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID)
	{
	    aItem.addItemBehavior(aID, new Behavior_Tool("axe", "dig.wood", getToolDamagePerContainerCraft(), true));
	    aItem.addItemBehavior(aID, new Behavior_Tool("crowbar", "random.break", 100L, !canBlock()));
	}
	
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
	  {
	    return aIsToolHead ? MT.Redstone.mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	  }
	
}
