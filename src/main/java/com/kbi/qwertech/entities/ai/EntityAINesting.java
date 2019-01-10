package com.kbi.qwertech.entities.ai;

import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.blocks.BlockSoil;
import com.kbi.qwertech.entities.EntityHelperFunctions;
import com.kbi.qwertech.loaders.RegisterSpecies;
import com.kbi.qwertech.tileentities.NestTileEntity;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.lang.reflect.Constructor;

public class EntityAINesting extends EntityAIBase
{
    private EntityLiving entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private ItemStack makeANest;
    private NestTileEntity ourNest;
    private boolean laidTonight = false;

    public EntityAINesting(EntityLiving p1, ItemStack nestStack)
    {
        this.entity = p1;
        this.makeANest = nestStack;
        this.setMutexBits(1);
    }

    public static Vec3 findSuitableNest(EntityLiving el, int range, boolean existing)
    {
        range = range + 1;
        int x1 = 0;
        int y1 = 4;
        int z1 = 0;
        while (x1 < range && z1 < range)
        {
            for (int x2 = -x1; x2 <= x1; x2++)
            {
                for (int y2 = -y1; y2 <= y1; y2++)
                {
                    for (int z2 = -z1; z2 <= z1; z2++)
                    {
                        int x = (int)Math.floor(el.posX) + x2;
                        int y = (int)Math.floor(el.posY) + y2;
                        int z = (int)Math.floor(el.posZ) + z2;
                        if (!el.worldObj.getBlock(x, y + 1, z).canBeReplacedByLeaves(el.worldObj, x, y + 1, z) || !el.worldObj.getBlock(x, y + 2, z).canBeReplacedByLeaves(el.worldObj, x, y + 2, z))
                        {
                            continue;
                        }
                        if (existing)
                        {
                            TileEntity TE = el.worldObj.getTileEntity(x, y, z);
                            if (TE instanceof NestTileEntity)
                            {
                                NestTileEntity nte = (NestTileEntity)TE;
                                if (nte.setNestingEntity(el)) {
                                    if (el.getNavigator().getPathToXYZ(x, y, z) != null) {
                                        return Vec3.createVectorHelper(x, y, z);
                                    }
                                }
                            }
                        } else {
                            Block block = el.worldObj.getBlock(x, y, z);
                            if (block instanceof BlockDirt || block instanceof BlockGrass || block instanceof BlockFarmland || block instanceof BlockMycelium || block instanceof BlockSoil || block.canSustainPlant(el.worldObj, x, y, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling))
                            {
                                Block[] checkList = new Block[4];
                                checkList[0] = el.worldObj.getBlock(x - 1, y + 1, z);
                                checkList[1] = el.worldObj.getBlock(x + 1, y + 1, z);
                                checkList[2] = el.worldObj.getBlock(x, y + 1, z - 1);
                                checkList[3] = el.worldObj.getBlock(x, y + 1, z - 1);
                                for (int q = 0; q < 4; q++)
                                {
                                    Block check = checkList[q];
                                    if (check instanceof BlockTallGrass || check instanceof BlockFlower || check instanceof BlockBush || check instanceof BlockCrops || check instanceof BlockReed)
                                    {
                                        if (el.getNavigator().getPathToXYZ(x, y, z) != null) {
                                            return Vec3.createVectorHelper(x, y, z);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            x1++;
            z1++;
        }
        return null;
    }

    private ItemStack setEggData()
    {
        Species species = ((IGeneticMob)entity).getSpecies();
        Subtype subtype = ((IGeneticMob)entity).getSubtype();
        ItemStack toReturn = null;
        if (subtype.hasTag(RegisterSpecies.ITEM_EGG))
        {
            toReturn = ((ItemStack)subtype.getTag(RegisterSpecies.ITEM_EGG)).copy();
        } else if (species.hasTag(RegisterSpecies.ITEM_EGG))
        {
            toReturn = ((ItemStack)species.getTag(RegisterSpecies.ITEM_EGG)).copy();
        }
        if (toReturn == null) return null;
        NBTTagCompound tag = UT.NBT.getOrCreate(toReturn);
        if (subtype.hasTag(RegisterSpecies.COLOR_EGG))
        {
            tag.setInteger("itemColor", (Integer)subtype.getTag(RegisterSpecies.COLOR_EGG));
        } else if (species.hasTag(RegisterSpecies.COLOR_EGG))
        {
            tag.setInteger("itemColor", (Integer)species.getTag(RegisterSpecies.COLOR_EGG));
        }
        toReturn.setTagCompound(tag);
        return toReturn;
    }

    public void successfulNesting() {
        if (!laidTonight && !entity.isDead && ourNest != null && entity instanceof IGeneticMob)
        {
            IGeneticMob igm = (IGeneticMob)entity;
            ItemStack egg = setEggData();
            if (egg == null) return;
            NBTTagCompound nbt = UT.NBT.getOrCreate(egg);
            if (igm.isFertilized())
            {
                igm.setFertilized(false);
                EntityLivingBase returnable;
                try {
                    Constructor constructor = entity.getClass().getConstructor(World.class);
                    returnable = (EntityLivingBase)constructor.newInstance(entity.worldObj);
                    ((IGeneticMob)returnable).setSpeciesID((igm).getSpeciesID());
                    ((IGeneticMob)returnable).setSubtypeID((igm).getSubtypeID());
                    EntityLivingBase fakeParent = (EntityLivingBase)constructor.newInstance(entity.worldObj);
                    fakeParent.readEntityFromNBT((igm).getMateNBT());
                    EntityHelperFunctions.createOffspring((IGeneticMob)returnable, igm, (IGeneticMob)fakeParent);
                    returnable.writeEntityToNBT(nbt);
                    fakeParent.setDead();
                    returnable.setDead();
                } catch (Exception e) {
                    System.out.println("Error in resolving mate:");
                    e.printStackTrace();
                    entity.writeEntityToNBT(nbt);
                }
                nbt.setLong("Timer", entity.worldObj.getTotalWorldTime() + (Short.MAX_VALUE - (igm).getMaturity()));
                egg.setTagCompound(nbt);
            }
            int randy = entity.worldObj.rand.nextInt(16000 + igm.getFertility());
            if (randy > 26000)
            {
                egg.stackSize = 3;
            } else if (randy > 16000)
            {
                egg.stackSize = 2;
            } else {
                egg.stackSize = 1;
            }
            for (int q = 0; q < ourNest.invsize(); q++)
            {
                if (ourNest.addStackToSlot(q, egg))
                {
                    laidTonight = true;
                    break;
                }
            }
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        if (this.entity.worldObj.isDaytime())
        {
            laidTonight = false;
            return false;
        } else if (this.entity.getAge() >= 100)
        {
            return false;
        }
        else if (this.entity.getRNG().nextInt(20) != 0)
        {
            return false;
        }
        else
        {
            if (entity.worldObj.getTileEntity((int)Math.floor(entity.posX), (int)Math.floor(entity.posY), (int)Math.floor(entity.posZ)) instanceof NestTileEntity)
            {
                //System.out.println("We're in a nest already, hmph");
                return false;
            } else if (entity.worldObj.getTileEntity((int)Math.floor(entity.posX), (int)Math.floor(entity.posY - 1), (int)Math.floor(entity.posZ)) instanceof NestTileEntity)
            {
                return false;
            }
            //System.out.println("We're lookin' for a nest here at " + entity.posX + ", " + entity.posZ);
            Vec3 vec3;
            if (ourNest != null && ourNest.getWorld() == entity.worldObj && entity.worldObj.getTileEntity(ourNest.xCoord, ourNest.yCoord, ourNest.zCoord) == ourNest)
            {
                vec3 = Vec3.createVectorHelper(ourNest.xCoord, ourNest.yCoord, ourNest.zCoord);
            } else {
                ourNest = null;
                vec3 = findSuitableNest(entity, 16, true);
            }

            if (vec3 == null)
            {
                vec3 = findSuitableNest(entity, 8, false);
            }
            if (vec3 == null)
            {
                //System.out.println("No nesting spot found");
                return false;
            }
            else
            {
                //System.out.println("Found a nesting spot at " + vec3);
                this.xPosition = vec3.xCoord;
                this.yPosition = vec3.yCoord;
                this.zPosition = vec3.zCoord;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        if (Math.floor(this.entity.posX) == this.xPosition && Math.floor(this.entity.posZ) == this.zPosition && this.entity.posY - this.yPosition > -0.1 && this.entity.posY - this.yPosition < 1.1)
        {
            int x = (int)Math.floor(xPosition);
            int y = (int)Math.floor(yPosition);
            int z = (int)Math.floor(zPosition);
            World world = this.entity.worldObj;
            TileEntity te = world.getTileEntity(x, y, z);
            if (!(te instanceof NestTileEntity))
            {
                //System.out.println("We found the spot but no nest here");
                Block block = world.getBlock((int)Math.floor(xPosition), (int)Math.floor(yPosition), (int)Math.floor(zPosition));
                //we check it again to make sure that it hasn't somehow reverted while the mob was trying to pathfind there
                if ((world.getBlock(x, y + 1, z).canBeReplacedByLeaves(world, x, y + 1, z) && world.getBlock(x, y + 2, z).canBeReplacedByLeaves(world, x, y + 2, z)) && (block instanceof BlockDirt || block instanceof BlockGrass || block instanceof BlockFarmland || block instanceof BlockMycelium || block instanceof BlockSoil || block.canSustainPlant(world, x, y, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling)))
                {
                    //System.out.println("We can go ahead and place here");
                    if (!world.isRemote)
                    {
                        WD.set(world, x, y + 1, z, makeANest);
                        WD.air(world, x, y + 2, z);
                        te = world.getTileEntity(x, y + 1, z);
                        ((NestTileEntity)te).setNestingEntity(this.entity);
                        ourNest = (NestTileEntity)te;
                        //System.out.println("There should be a nest here now");
                    }
                    successfulNesting();
                    return false;

                } else {
                    //System.out.println("We reached the spot but changed our mind");
                    //if it's not a good nesting spot anymore, we try again.
                    if (this.shouldExecute())
                    {
                        startExecuting();
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                //System.out.println("We reached our existing nest");
                ourNest = (NestTileEntity)te;
                successfulNesting();
                return false;
            }
        }
        //System.out.println("We're trying to get to " + this.xPosition + ", " + this.yPosition + ", " + this.zPosition + " but we're at " + Math.floor(entity.posX) + ", " + Math.floor(entity.posY) + ", " + Math.floor(entity.posZ));
        if (this.entity.getNavigator().noPath())
        {
            this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1);
        }
        return true;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1);
        //System.out.println("We're trying... moving at 1");
    }
}