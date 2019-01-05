package com.kbi.qwertech.tileentities;

import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase05Inventories;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.PX_P;

public class NestTileEntity extends TileEntityBase05Inventories implements IMultiTileEntity.IMTE_GetBlocksMovement, IMultiTileEntity.IMTE_AddCollisionBoxesToList, IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState, IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool, IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool {
    @Override
    public boolean canDrop(int aSlot) {
        return true;
    }

    @Override
    public String getTileEntityName() {
        return "qt.birdnest";
    }

    public static IIconContainer nest = new Textures.BlockIcons.CustomIcon("qwertech:soil/drystraw");
    public short[] ourcolor = null;


    @Override
    public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        if (ourcolor == null)
        {
            ourcolor = new short[]{(short)(210 + Math.abs(this.xCoord % 16)), (short)(245 + Math.abs(this.zCoord % 8)), (short)(200 + Math.abs(this.zCoord % 16) - Math.abs(this.xCoord % 12)), 255};
        }
        return BlockTextureDefault.get(nest, ourcolor);
    }

    @Override
    public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
        switch(aRenderPass) {
            case 4:
                aBlock.setBlockBounds(PX_P[0], PX_P[1], PX_P[14], PX_P[16], PX_P[3], PX_P[16]);
                break;
            case 3:
                aBlock.setBlockBounds(PX_P[0], PX_P[1], PX_P[0], PX_P[16], PX_P[3], PX_P[2]);
                break;
            case 2:
                aBlock.setBlockBounds(PX_P[14], PX_P[1], PX_P[0], PX_P[16], PX_P[3], PX_P[16]);
                break;
            case 1:
                aBlock.setBlockBounds(PX_P[0], PX_P[1], PX_P[0], PX_P[2], PX_P[3], PX_P[16]);
                break;
            default:
                aBlock.setBlockBounds(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[1], PX_P[16]);
        }
        return true;
    }

    @Override
    public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
        return 5;
    }

    @Override
    public boolean getBlocksMovement() {
        return true;
    }



    @Override
    public void addCollisionBoxesToList(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
        this.box(aAABB, aList, PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[1], PX_P[16]);
    }

    @Override
    public void setBlockBoundsBasedOnState(Block aBlock) {
        boolean empty = true;
        for (int q = 0; q < this.getSizeInventory(); q++)
        {
            if (slotHas(q))
            {
                empty = false;
                break;
            }
        }
        if (empty) {
            aBlock.setBlockBounds(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[3], PX_P[16]);
        } else {
            aBlock.setBlockBounds(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[5], PX_P[16]);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool() {
        return this.box(0, 0, 0, 1, 0.1, 1);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool() {
        boolean empty = true;
        for (int q = 0; q < this.getSizeInventory(); q++)
        {
            if (slotHas(q))
            {
                empty = false;
                break;
            }
        }
        if (empty) {
            return this.box(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[3], PX_P[16]);
        } else {
            return this.box(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[5], PX_P[16]);
        }
    }
}
