package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.QTMT;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CompostBinTileEntity extends TileEntityBase09FacingSingle {

    protected byte fillLevel = -128;
    protected byte lastFillLevel = -127;

    @Override
    public void onTick2(long aTimer, boolean aIsServerSide) {
        super.onTick2(aTimer, aIsServerSide);
        if (aIsServerSide)
        {
            if (worldObj.rand.nextFloat() > 0.995F)
            {
                int randy = worldObj.rand.nextInt(6) + 4;
                ItemStack compCheck = getStackInSlot(randy);
                if (ST.valid(compCheck) && fillLevel < 127)
                {
                    if (compCheck.stackSize < 2) {
                        //System.out.println("Found an " + compCheck);
                        slot(randy, null);
                    } else {
                        compCheck.stackSize = compCheck.stackSize - 1;
                        slot(randy, compCheck);
                    }
                    //System.out.println("WE SHOULD BE GETTING RID OF SLOT " + randy);
                    ItemStack dust = getStackInSlot(1);
                    if (ST.invalid(dust))
                    {
                        setInventorySlotContents(1, OP.dust.mat(QTMT.CompostRaw, 1));
                    } else {
                        dust.stackSize = dust.stackSize + 1;
                        setInventorySlotContents(1, dust);
                        if (dust.stackSize >= 9)
                        {
                            dust.stackSize = dust.stackSize - 9;
                            ItemStack block = getStackInSlot(0);
                            if (ST.invalid(block))
                            {
                                setInventorySlotContents(0, OP.blockDust.mat(QTMT.CompostRaw, 1));
                            } else {
                                block.stackSize = block.stackSize + 1;
                                setInventorySlotContents(0, block);
                            }
                            if (dust.stackSize < 1)
                            {
                                setInventorySlotContents(1, null);
                            }
                        }
                    }
                }
                //calculateFill();
            }
        }
    }

    @Override
    public boolean onTickCheck(long aTimer) {
        return super.onTickCheck(aTimer) || this.fillLevel != this.lastFillLevel;
    }

    @Override
    public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
        super.onTickResetChecks(aTimer, aIsServerSide);
        this.lastFillLevel = fillLevel;
        calculateFill();
    }

    public void calculateFill()
    {
        if (this.isClientSide()) return;
        fillLevel = -127;
        ItemStack block = getStackInSlot(0);
        if (ST.valid(block))
        {
            fillLevel =( byte)(fillLevel + 255);
        }
        ItemStack dust = getStackInSlot(1);
        if (ST.valid(dust)) {
            fillLevel = (byte) (fillLevel + (dust.stackSize * 28));
        }
        ItemStack smallDust = getStackInSlot(2);
        if (ST.valid(smallDust)) {
            fillLevel = (byte) (fillLevel + (dust.stackSize * 7));
        }
        ItemStack tinyDust = getStackInSlot(3);
        if (ST.valid(tinyDust)) {
            fillLevel = (byte) (fillLevel + (dust.stackSize * 3));
        }
        //System.out.println(fillLevel);
    }

    @Override
    public void setInventorySlotContents(int aSlot, ItemStack aStack) {
        super.setInventorySlotContents(aSlot, aStack);
        calculateFill();
    }

    @Override
    public byte getVisualData() {
        return fillLevel;
    }

    @Override
    public void setVisualData(byte aData) {
        fillLevel = aData;
    }

    @Override
    public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
        if (aSide != CS.SIDE_Y_POS && aSide != this.getFacing()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
        if (aTool == "shovel") {
            if (aPlayer instanceof EntityPlayer) {
                ((EntityPlayer) aPlayer).inventory.addItemStackToInventory(getStackInSlot(0));
                ((EntityPlayer) aPlayer).inventory.addItemStackToInventory(getStackInSlot(1));
                ((EntityPlayer) aPlayer).inventory.addItemStackToInventory(getStackInSlot(2));
                ((EntityPlayer) aPlayer).inventory.addItemStackToInventory(getStackInSlot(3));
            } else {
                ST.drop(worldObj, xCoord, yCoord + 1, zCoord, getStackInSlot(0));
                ST.drop(worldObj, xCoord, yCoord + 1, zCoord, getStackInSlot(1));
                ST.drop(worldObj, xCoord, yCoord + 1, zCoord, getStackInSlot(2));
                ST.drop(worldObj, xCoord, yCoord + 1, zCoord, getStackInSlot(3));
            }
            slot(0, null);
            slot(1, null);
            slot(2, null);
            slot(3, null);
            return 1000;
        }

        return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
    }

    @Override
    public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
        if (aSide != CS.SIDE_FRONT && aSide != CS.SIDE_TOP) return false;
        ItemStack stack = aPlayer.getHeldItem();
        if (ST.valid(stack))
        {
            if (OP.tree.contains(stack) || OP.plant.contains(stack) || OM.materialcontained(stack, MT.Wheat, MT.Rice, MT.Barley, MT.Oat, MT.Cocoa, MT.Potato, MT.Tofu) || OM.is( "itemPlantRemains", stack) || stack.getItemUseAction() == EnumAction.eat) {
                for (int q = 4; q < 12; q++) {
                    ItemStack stackel = getStackInSlot(q);
                    if (ST.invalid(stackel)) {
                        slot(q, stack);
                        aPlayer.setCurrentItemOrArmor(0, null);
                        break;
                    }
                }
            }
        } else {
            ItemStack block = getStackInSlot(0);
            if (block != null && block.stackSize > 1)
            {
                aPlayer.setCurrentItemOrArmor(0, block);
                this.setInventorySlotContents(0, null);
            } else {
                ItemStack dust = getStackInSlot(1);
                if (dust != null && dust.stackSize > 1)
                {
                    aPlayer.setCurrentItemOrArmor(0, dust);
                    this.setInventorySlotContents(1, null);
                } else {
                    ItemStack smallDust = getStackInSlot(2);
                    if (smallDust != null && smallDust.stackSize > 1)
                    {
                        aPlayer.setCurrentItemOrArmor(0, smallDust);
                        this.setInventorySlotContents(2, null);
                    } else {
                        ItemStack tinyDust = getStackInSlot(3);
                        if (tinyDust != null && tinyDust.stackSize > 1)
                        {
                            aPlayer.setCurrentItemOrArmor(0, tinyDust);
                            this.setInventorySlotContents(3, null);
                        }
                    }
                }
            }
        }
        return true;
    }


    public static String texture_Dir(){
        return "qwertech:compostbin/";
    }

    public static IIconContainer sColoreds[] = new IIconContainer[] {
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "top"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "bottom"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "side"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "large"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "small"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "side2")
    };

    @Override
    public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        if (aRenderPass == 11)
        {
            return BlockTextureCopied.get(QwerTech.soilBlock, 0, 3);
        }
        short aIndex;
        if(aRenderPass != 3)
        {
            aIndex = 0;
        } else {
            aIndex = 4;
        }
        if (aSide == CS.SIDE_Y_POS)
        {
            aIndex = 0;
        } else if (aSide == CS.SIDE_Y_NEG)
        {
            aIndex = 1;
        } else if (aSide == CS.SIDE_X_POS) {
            if (mFacing == CS.SIDE_Z_NEG) {
                aIndex = 2;
            } else if (mFacing == CS.SIDE_Z_POS) {
                aIndex = 5;
            }
        } else if (aSide == CS.SIDE_X_NEG) {
            if (mFacing == CS.SIDE_Z_POS) {
                aIndex = 2;
            } else if (mFacing == CS.SIDE_Z_NEG) {
                aIndex = 5;
            }
        } else if (aSide == CS.SIDE_Z_POS) {
            if (mFacing == CS.SIDE_X_POS) {
                aIndex = 2;
            } else if (mFacing == CS.SIDE_X_NEG) {
                aIndex = 5;
            }
        } else if (aSide == CS.SIDE_Z_NEG) {
            if (mFacing == CS.SIDE_X_NEG) {
                aIndex = 2;
            } else if (mFacing == CS.SIDE_X_POS) {
                aIndex = 5;
            }
        } else {
            aIndex = 3;
        }
        return BlockTextureDefault.get(sColoreds[aIndex], mRGBa);
    }

    @Override
    public boolean canDrop(int aSlot) {
        return true;
    }

    @Override
    public String getTileEntityName() {
        return "qt.compostbin";
    }

    @Override
    public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
        return 12;
    }



    public boolean setBlockBoundsOnRotation(Block aBlock, float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
    {
        switch (this.getFacing())
        {
            case CS.SIDE_Z_POS:
                aBlock.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
                return true;
            case CS.SIDE_Z_NEG:
                aBlock.setBlockBounds(minX, minY, 1 - maxZ, maxX, maxY, 1 - minZ);
                return true;
            case CS.SIDE_X_POS:
                aBlock.setBlockBounds(minZ, minY, minX, maxZ, maxY, maxX);
                return true;
            case CS.SIDE_X_NEG:
                aBlock.setBlockBounds(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX);
                return true;
            default:
                aBlock.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
                return true;
        }
    }

    @Override
    public void setFacing(short aFacing) {
        if (aFacing != CS.SIDE_Y_NEG && aFacing != CS.SIDE_Y_POS) {
            super.setFacing(aFacing);
        } else {
            super.setFacing(CS.SIDE_X_POS);
        }
    }



    @Override
    public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
        switch (aRenderPass)
        {
            case 0:
                setBlockBoundsOnRotation(aBlock, CS.PX_P[1], 0F, CS.PX_P[1], CS.PX_N[1], CS.PX_P[2], CS.PX_N[1]);
                break;
            case 1:
                setBlockBoundsOnRotation(aBlock,0F, 0F, CS.PX_P[1], CS.PX_P[1], CS.PX_P[5], CS.PX_N[1]);
                break;
            case 2:
                setBlockBoundsOnRotation(aBlock, CS.PX_N[1], 0F, CS.PX_P[1], 1F, CS.PX_P[5], CS.PX_N[1]);
                break;
            case 3:
                setBlockBoundsOnRotation(aBlock, 0F, 0F, CS.PX_N[1], 1F, CS.PX_P[5], 1F);
                break;
            case 4:
                setBlockBoundsOnRotation(aBlock, 0F, 0F, 0F, 1F, 1F, CS.PX_P[1]);
                break;
            case 5:
                setBlockBoundsOnRotation(aBlock, 0, CS.PX_P[5], CS.PX_P[1], CS.PX_P[1], CS.PX_P[9], CS.PX_P[12]);
                break;
            case 6:
                setBlockBoundsOnRotation(aBlock, 0, CS.PX_P[9], CS.PX_P[1], CS.PX_P[1], CS.PX_P[13], CS.PX_P[8]);
                break;
            case 7:
                setBlockBoundsOnRotation(aBlock, 0, CS.PX_P[13], CS.PX_P[1], CS.PX_P[1], 1F, CS.PX_P[4]);
                break;
            case 8:
                setBlockBoundsOnRotation(aBlock, CS.PX_N[1], CS.PX_P[5], CS.PX_P[1], 1F, CS.PX_P[9], CS.PX_P[12]);
                break;
            case 9:
                setBlockBoundsOnRotation(aBlock, CS.PX_N[1], CS.PX_P[9], CS.PX_P[1], 1F, CS.PX_P[13], CS.PX_P[8]);
                break;
            case 10:
                setBlockBoundsOnRotation(aBlock, CS.PX_N[1], CS.PX_P[13], CS.PX_P[1], 1F, 1F, CS.PX_P[4]);
                break;
            case 11:
                setBlockBoundsOnRotation(aBlock, CS.PX_P[1], CS.PX_P[2], CS.PX_P[1], CS.PX_N[1], (fillLevel + 128)/255F, CS.PX_N[1]);
                break;
        }
        return true;
    }
}
