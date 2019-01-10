package com.kbi.qwertech.tileentities;

import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.awt.*;
import java.util.List;

import static gregapi.data.CS.*;

public class NestBoxTileEntity extends NestTileEntity implements IMultiTileEntity.IMTE_GetSubItems {

    protected OreDictMaterial mMaterial = MT.NULL;

    @Override
    public String getTileEntityName() {
        return super.getTileEntityName() + ".manmade";
    }

    @Override
    public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem,
                               CreativeTabs aTab, List aList, short aID) {
        return SHOW_HIDDEN_MATERIALS || !mMaterial.mHidden;
    }

    @Override
    public void readFromNBT2(NBTTagCompound aNBT) {
        super.readFromNBT2(aNBT);
        if (aNBT.hasKey(NBT_MATERIAL)) mMaterial = OreDictMaterial.get(aNBT.getString(NBT_MATERIAL));
    }

    public static IIconContainer wood = new Textures.BlockIcons.CustomIcon("qwertech:nestbox/wood");
    @Override
    public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        if (aRenderPass > 9)
        {
            ItemStack stack = slot(aRenderPass - 10);
            short[] colors = new short[4];
            if (stack != null)
            {
                NBTTagCompound nbt = UT.NBT.getOrCreate(stack);
                int color = nbt.getInteger("itemColor");
                Color oColor = Color.getColor(null, color);
                colors[0] = (short)oColor.getRed();
                colors[1] = (short)oColor.getBlue();
                colors[2] = (short)oColor.getGreen();
                colors[3] = (short)oColor.getAlpha();
                return BlockTextureDefault.get(MT.Bone.mTextureSetsBlock.get(OP.blockDust.mIconIndexBlock), colors);
                //return BlockTextureDefault.get(nest, colors);
            }
            return null;
        } else if (aRenderPass < 5) {
            if (ourcolor == null) {
                ourcolor = new short[]{(short) (210 + Math.abs(this.xCoord % 16)), (short) (245 + Math.abs(this.zCoord % 8)), (short) (200 + Math.abs(this.zCoord % 16) - Math.abs(this.xCoord % 12)), 255};
            }
            return BlockTextureDefault.get(nest, ourcolor);
        } else {
            return BlockTextureDefault.get(wood, this.mMaterial.fRGBaSolid);
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool() {
        return this.box(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[7], PX_P[16]);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool() {
        return this.box(0, 0, 0, 1, 0.5, 1);
    }

    @Override
    public void setBlockBoundsBasedOnState(Block aBlock) {
        aBlock.setBlockBounds(PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[7], PX_P[16]);
    }

    @Override
    public void addCollisionBoxesToList(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
        this.box(aAABB, aList, PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[3], PX_P[16]);
        this.box(aAABB, aList, PX_P[0], PX_P[0], PX_P[0], PX_P[16], PX_P[7], PX_P[1]);
        this.box(aAABB, aList, PX_P[0], PX_P[0], PX_P[15], PX_P[16], PX_P[7], PX_P[16]);
        this.box(aAABB, aList, PX_P[0], PX_P[0], PX_P[0], PX_P[1], PX_P[7], PX_P[16]);
        this.box(aAABB, aList, PX_P[15], PX_P[0], PX_P[0], PX_P[16], PX_P[7], PX_P[16]);
    }

    @Override
    public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
        return super.getRenderPasses(aBlock, aShouldSideBeRendered) + 5;
    }

    @Override
    public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
        switch(aRenderPass) {
            case 14:
                //egg
                aBlock.setBlockBounds(PX_P[7], PX_P[2], PX_P[9], PX_P[10], PX_P[6], PX_P[12]);
                break;
            case 13:
                //egg
                aBlock.setBlockBounds(PX_P[9], PX_P[2], PX_P[5], PX_P[12], PX_P[6], PX_P[8]);
                break;
            case 12:
                //egg
                aBlock.setBlockBounds(PX_P[5], PX_P[2], PX_P[7], PX_P[8], PX_P[6], PX_P[11]);
                break;
            case 11:
                //egg
                aBlock.setBlockBounds(PX_P[5], PX_P[2], PX_P[6], PX_P[8], PX_P[6], PX_P[9]);
                break;
            case 10:
                //egg
                aBlock.setBlockBounds(PX_P[7], PX_P[2], PX_P[7], PX_P[10], PX_P[6], PX_P[10]);
                break;
            case 9:
                aBlock.setBlockBounds(PX_P[2], PX_P[0], PX_P[14], PX_P[14], PX_P[7], PX_P[16]);
                break;
            case 8:
                aBlock.setBlockBounds(PX_P[2], PX_P[0], PX_P[0], PX_P[14], PX_P[7], PX_P[2]);
                break;
            case 7:
                aBlock.setBlockBounds(PX_P[14], PX_P[0], PX_P[0], PX_P[16], PX_P[7], PX_P[16]);
                break;
            case 6:
                aBlock.setBlockBounds(PX_P[0], PX_P[0], PX_P[0], PX_P[2], PX_P[7], PX_P[16]);
                break;
            case 5:
                aBlock.setBlockBounds(PX_P[1], PX_P[0], PX_P[1], PX_P[15], PX_P[1], PX_P[15]);
                break;
            case 4:
                aBlock.setBlockBounds(PX_P[1], PX_P[2], PX_P[13], PX_P[15], PX_P[4], PX_P[15]);
                break;
            case 3:
                aBlock.setBlockBounds(PX_P[1], PX_P[2], PX_P[1], PX_P[15], PX_P[4], PX_P[3]);
                break;
            case 2:
                aBlock.setBlockBounds(PX_P[13], PX_P[2], PX_P[1], PX_P[15], PX_P[4], PX_P[15]);
                break;
            case 1:
                aBlock.setBlockBounds(PX_P[1], PX_P[2], PX_P[1], PX_P[3], PX_P[4], PX_P[15]);
                break;
            case 0:
                aBlock.setBlockBounds(PX_P[1], PX_P[1], PX_P[1], PX_P[15], PX_P[2], PX_P[15]);
        }
        return true;
    }

}
