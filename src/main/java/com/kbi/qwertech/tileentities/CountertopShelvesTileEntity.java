package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.recipe.CountertopRecipe;
import com.kbi.qwertech.network.packets.PacketInventorySync;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.metatype.BlockStones;
import gregapi.data.CS;
import gregapi.data.TD;
import gregapi.gui.ContainerClientDefault;
import gregapi.gui.ContainerCommonDefault;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.PX_P;

public class CountertopShelvesTileEntity extends CuttingBoardTileEntity {
    @Override
    public String getTileEntityName() {
        return "qt.cooking.countertop.storage";
    }

    @SideOnly(Side.CLIENT)
    @Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {
        if (aGUIID == 0) {
            return new GUIClientCuttingBoard(aPlayer.inventory, this);
        } else {
            return new ContainerClientDefault(new ContainerCommonDefault(aPlayer.inventory, this, 9, 18));
        }
    }
    @Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer)
    {
        if (aGUIID == 0) {
            return new GUICommonCuttingBoard(aPlayer.inventory, this);
        } else {
            return new ContainerCommonDefault(aPlayer.inventory, this, 9, 18);
        }
    }

    @Override
    public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
        if (aSide == this.getFacing() && aHitY < 0.8F && openGUI(aPlayer, 1)) return true;
        if (aSide > 1 && openGUI(aPlayer, 0)) return true;
        int aSlot = -1;
        short sided = this.getFacing();
        //System.out.println("Facing " + sided);
        switch (sided)
        {
            case CS.SIDE_Z_NEG:
            {
                aHitX = 1 - aHitX;
                break;
            }
            case CS.SIDE_X_POS:
            {
                aHitZ = 1 - aHitZ;
            }
            case CS.SIDE_X_NEG:
            {
                float aTemp = aHitX;
                aHitX = aHitZ;
                aHitZ = aTemp;
                break;
            }
            case CS.SIDE_Z_POS:
            {
                break; //default
            }
        }
        if (aHitX < 0.33)
        {
            if (aHitZ > 0.66)
            {
                aSlot = 0;
            } else if (aHitZ < 0.33)
            {
                aSlot = 2;
            } else {
                aSlot = 1;
            }
        } else if (aHitX > 0.66)
        {
            if (aHitZ > 0.66)
            {
                aSlot = 5;
            } else if (aHitZ < 0.33)
            {
                aSlot = 7;
            } else {
                aSlot = 6;
            }
        } else {
            if (aHitZ > 0.66)
            {
                aSlot = 3;
            } else if (aHitZ < 0.33)
            {
                aSlot = 4;
            }
        }
        if (aSlot > -1)
        {
            ItemStack item = getStackInSlot(aSlot);
            ItemStack held = aPlayer.getHeldItem();
            if (ST.equal(item, held))
            {
                while (item.stackSize < item.getMaxStackSize() && held.stackSize > 0)
                {
                    item.stackSize = item.stackSize + 1;
                    held.stackSize = held.stackSize - 1;
                }
                if (held.stackSize < 1) {
                    aPlayer.setCurrentItemOrArmor(0, null);
                }
                QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(item, this.xCoord, this.yCoord, this.zCoord, aSlot), this.worldObj, this.xCoord, this.zCoord);
                return true;
            } else if (held == null && item != null)
            {
                aPlayer.setCurrentItemOrArmor(0, item);
                setInventorySlotContents(aSlot, null);
                QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(null, this.xCoord, this.yCoord, this.zCoord, aSlot), this.worldObj, this.xCoord, this.zCoord);
                return true;
            } else if (held != null && item == null)
            {
                aPlayer.setCurrentItemOrArmor(0, null);
                setInventorySlotContents(aSlot, held);
                QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(held, this.xCoord, this.yCoord, this.zCoord, aSlot), this.worldObj, this.xCoord, this.zCoord);
                return true;
            }
        }
        return aPlayer.getHeldItem() == null && openGUI(aPlayer, 0);
    }

    @Override
    public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
        super.addToolTips(aList, aStack, aF3_H);
        aList.add(3, "Drawer size: 18 stacks");
    }

    @Override
    public boolean canDrop(int i) {
        return i != 8;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered)
    {
        return 4;
    }

    @Override
    public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        if (aRenderPass >= 3)
        {
            return BlockTextureDefault.get(icons1[0], UT.Code.getRGBInt(150, 150, 150));
        }
        if (mMaterial.contains(TD.Properties.STONE)) {
            return BlockTextureDefault.get(((BlockStones) CS.BlocksGT.stones[mTexture]).mIcons[mMetafy], UT.Code.getRGBInt(255, 255, 255));
        } else {
            IIconContainer returnable = aRenderPass == 0 ? icons1[aSide < 2 ? 0 : 1] : icons2[aSide < 2 ? 0 : 1];
            return BlockTextureDefault.get(returnable, mRGBa);
        }
    }



    @Override
    public byte getDefaultSide() {return CS.SIDE_FRONT;}

    @SideOnly(Side.CLIENT)
    @Override
    public boolean setBlockBounds2(Block block, int aRenderPass, boolean[] aShouldSideBeRendered) {
        short xneg = 1, xpos = 15, zneg = 1, zpos = 15;
        if (this.getOpacityAtSide(CS.SIDE_X_NEG) || (this.getAdjacentTileEntity(CS.SIDE_X_NEG).exists() && this.getAdjacentTileEntity(CS.SIDE_X_NEG).mTileEntity instanceof CuttingBoardTileEntity))
        {
            if (this.mFacing != CS.SIDE_X_NEG) {
                xneg = 0;
            }
        }
        if (this.getOpacityAtSide(CS.SIDE_X_POS) || (this.getAdjacentTileEntity(CS.SIDE_X_POS).exists() && this.getAdjacentTileEntity(CS.SIDE_X_POS).mTileEntity instanceof CuttingBoardTileEntity))
        {
            if (this.mFacing != CS.SIDE_X_POS) {
                xpos = 16;
            }
        }
        if (this.getOpacityAtSide(CS.SIDE_Z_NEG) || (this.getAdjacentTileEntity(CS.SIDE_Z_NEG).exists() && this.getAdjacentTileEntity(CS.SIDE_Z_NEG).mTileEntity instanceof CuttingBoardTileEntity))
        {
            if (this.mFacing != CS.SIDE_Z_NEG) {
                zneg = 0;
            }
        }
        if (this.getOpacityAtSide(CS.SIDE_Z_POS) || (this.getAdjacentTileEntity(CS.SIDE_Z_POS).exists() && this.getAdjacentTileEntity(CS.SIDE_Z_POS).mTileEntity instanceof CuttingBoardTileEntity))
        {
            if (this.mFacing != CS.SIDE_Z_POS) {
                zpos = 16;
            }
        }
        switch (aRenderPass) {
            case 0:
                block.setBlockBounds(PX_P[0], PX_P[10], PX_P[0], PX_P[16], PX_P[16], PX_P[16]);
                break;
            case 1:
                block.setBlockBounds(PX_P[xneg - (this.mFacing == CS.SIDE_X_NEG ? 1 : 0)], PX_P[0], PX_P[zneg - (this.mFacing == CS.SIDE_Z_NEG ? 1 : 0)], PX_P[xpos - (this.mFacing == CS.SIDE_X_POS ? 1 : 0)], PX_P[10], PX_P[zpos - (this.mFacing == CS.SIDE_Z_POS ? 1 : 0)]);
                break;
            case 2:
                block.setBlockBounds(PX_P[this.mFacing == CS.SIDE_Z_POS || this.mFacing == CS.SIDE_Z_NEG ? 2 : this.mFacing == CS.SIDE_X_NEG ? xneg - 1 : xpos - 1], PX_P[1], PX_P[this.mFacing == CS.SIDE_X_POS || this.mFacing == CS.SIDE_X_NEG ? 2 : this.mFacing == CS.SIDE_Z_NEG ? zneg - 1 : zpos - 1], PX_P[this.mFacing == CS.SIDE_Z_POS || this.mFacing == CS.SIDE_Z_NEG ? 14 : this.mFacing == CS.SIDE_X_NEG ? xneg : xpos], PX_P[8], PX_P[this.mFacing == CS.SIDE_X_POS || this.mFacing == CS.SIDE_X_NEG ? 14 : this.mFacing == CS.SIDE_Z_NEG ? zneg : zpos]);
                break;
            case 3:
                block.setBlockBounds(PX_P[this.mFacing == CS.SIDE_Z_POS || this.mFacing == CS.SIDE_Z_NEG ? 7 : this.mFacing == CS.SIDE_X_NEG ? xneg: xpos], PX_P[4], PX_P[this.mFacing == CS.SIDE_X_POS || this.mFacing == CS.SIDE_X_NEG ? 7 : this.mFacing == CS.SIDE_Z_NEG ? zneg: zpos], PX_P[this.mFacing == CS.SIDE_Z_POS || this.mFacing == CS.SIDE_Z_NEG ? 9 : this.mFacing == CS.SIDE_X_NEG ? xneg + 1 : xpos + 1], PX_P[7], PX_P[this.mFacing == CS.SIDE_X_POS || this.mFacing == CS.SIDE_X_NEG ? 9 : this.mFacing == CS.SIDE_Z_NEG ? zneg + 1: zpos + 1]);
                break;
        }
        return true;
    }
}
