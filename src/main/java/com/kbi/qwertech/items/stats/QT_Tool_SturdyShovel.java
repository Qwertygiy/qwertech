package com.kbi.qwertech.items.stats;

import com.kbi.qwertech.entities.EntityHelperFunctions;
import gregtech.items.tools.early.GT_Tool_Shovel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class QT_Tool_SturdyShovel extends GT_Tool_Shovel {
	
boolean LOCK = false;
	
	public MovingObjectPosition MOP;
	
		public float breakCheck(EntityPlayer player, World world, int x, int y, int z, boolean doBreak)
		{
			Block aBlock = world.getBlock(x, y, z);
			int aMetadata = world.getBlockMetadata(x, y, z);
			if (aBlock.canHarvestBlock(player, aMetadata) && this.isMinableBlock(aBlock, (byte)aMetadata) && aBlock.getPlayerRelativeBlockHardness(player, world, x, y, z) > 0)
			{
				if (doBreak)
				{
					world.func_147480_a(x, y, z, true);
					return 1;
				}
				return super.getMiningSpeed(aBlock, (byte)aMetadata);
			}
			return 0;
		}
		
		@Override
		public float getMaxDurabilityMultiplier()
		{
		    return 2F;
		}
		
		@Override
		public int getToolDamagePerBlockBreak()
		{
		    return 75;
		}
		
		@Override
		public int getToolDamagePerDropConversion()
		{
		    return 75;
		}
		
		public void calculateSides(boolean[] pos, boolean[] loc)
		{
			if (MOP != null && MOP.typeOfHit == MovingObjectType.BLOCK)
			{
				switch (MOP.sideHit)
				{
					case 0:
					case 1:
					{
						pos = new boolean[]{false, true, true};
						break;
					}
					case 2:
					case 3:
					{
						pos = new boolean[]{true, false, true};
						break;
					}
					case 4:
					case 5:
					{
						pos = new boolean[]{true, true, false};
						break;
					}
					default:
					{
						pos = new boolean[]{false, false, false};
						break;
					}
				}
				if (MOP.hitVec.yCoord > MOP.blockY + 0.5)
				{
					loc[0] = true;
				}
				if (MOP.hitVec.zCoord > MOP.blockZ + 0.5)
				{
					loc[1] = true;
				}
				if (MOP.hitVec.xCoord > MOP.blockX + 0.5)
				{
					loc[2] = true;
				}
			}
		}
		
		public float checkBlocks(EntityPlayer aPlayer, int aX, int aY, int aZ, boolean chop)
		{
			boolean[] checkPos = new boolean[]{false, false, false};
			boolean[] checkLoc = new boolean[]{false, false, false};
			
			calculateSides(checkPos, checkLoc);
			
			float returnable = 0;
			int x = aX;
			int y = aY;
			int z = aZ;
			if (checkPos[0])
			{
				if (checkLoc[0]) {y += 1;} else {y -= 1;}
				returnable = returnable + breakCheck(aPlayer, aPlayer.worldObj, x, y, z, chop);
				if (checkPos[1])
				{
					if (checkLoc[1]) {z += 1;} else {z -= 1;}
					returnable = returnable + breakCheck(aPlayer, aPlayer.worldObj, x, y, z, chop);
				} else if (checkPos[2])
				{
					if (checkLoc[2]) {x += 1;} else {x -= 1;}
					returnable = returnable + breakCheck(aPlayer, aPlayer.worldObj, x, y, z, chop);
				}
				
			}
			x = aX;
			y = aY;
			z = aZ;
			if (checkPos[1])
			{
				if (checkLoc[1]) {z += 1;} else {z -= 1;}
				returnable = returnable + breakCheck(aPlayer, aPlayer.worldObj, x, y, z, chop);
				if (checkPos[2])
				{
					if (checkLoc[2]) {x += 1;} else {x -= 1;}
					returnable = returnable + breakCheck(aPlayer, aPlayer.worldObj, x, y, z, chop);
				}
				
			}
			x = aX;
			y = aY;
			z = aZ;
			if (checkPos[2])
			{
				if (checkLoc[2]) {x += 1;} else {x -= 1;}
				returnable = returnable + breakCheck(aPlayer, aPlayer.worldObj, x, y, z, chop);
			}
			return returnable;
		}

		@Override
		public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent)
		{
			if (LOCK) return 0;
			LOCK = true;
			int returnable = (int)checkBlocks(aPlayer, aX, aY, aZ, true);
			LOCK = false;
			return returnable;
		}
		
		@Override
		public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ)
		{
			MOP = EntityHelperFunctions.getEntityLookTrace(aWorld, aPlayer, false, 5D);
			float returnable = super.getMiningSpeed(aBlock, aMetaData, aDefault, aPlayer, aWorld, aX, aY, aZ);
			returnable = returnable + checkBlocks(aPlayer, aX, aY, aZ, false);
			return returnable/5;
		}
}
