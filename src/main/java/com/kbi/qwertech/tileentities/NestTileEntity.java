package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.network.packets.PacketInventorySync;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase05Inventories;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static gregapi.data.CS.*;

public class NestTileEntity extends TileEntityBase05Inventories implements IMultiTileEntity.IMTE_GetBlocksMovement, IMultiTileEntity.IMTE_AddCollisionBoxesToList, IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState, IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool, IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool, ISidedInventory {

    protected boolean mUpdatedGrid = true;

    @Override
    public void onTick(long aTimer, boolean aIsServerSide) {
        super.onTick(aTimer, aIsServerSide);
        if (!hasWorldObj()) return;
        if (aIsServerSide) {
            if (mUpdatedGrid) {
                sendDisplays();
                mUpdatedGrid = F;
            } else if (aTimer % (200 + (xCoord % 10) + (zCoord % 10)) == 0)
            {
                sendDisplays();
                for (int q = 0; q < this.invsize(); q++)
                {
                    if (slotHas(q))
                    {
                        ItemStack stack = slot(q);
                        NBTTagCompound tagCompound = UT.NBT.getOrCreate(stack);
                        long timer = tagCompound.getLong("Timer");
                        if (timer > 0 && worldObj.getTotalWorldTime() > timer)
                        {
                            worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, stack));
                            slot(q, null);
                        }
                    }
                }
            }
        } else {
            if (mUpdatedGrid)
            {
                this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                mUpdatedGrid = false;
            }
        }
    }

    @Override public void setInventorySlotContents(int aSlot, ItemStack aStack)		{if (!ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContents(aSlot, aStack);}
    @Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack)	{if (!ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContentsGUI(aSlot, aStack);}
    @Override public ItemStack decrStackSize(int aSlot, int aDecrement)				{if (aDecrement > 0) mUpdatedGrid = T; return super.decrStackSize(aSlot, aDecrement);}
    @Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement)			{if (aDecrement > 0) mUpdatedGrid = T; return super.decrStackSizeGUI(aSlot, aDecrement);}

    @Override public boolean addStackToSlot(int aSlot, ItemStack aStack) {if (!ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; return super.addStackToSlot(aSlot, aStack); }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public int getInventoryStackLimitGUI(int aSlot) {
        return 1;
    }

    public void sendDisplays()
    {
        for (int q = 0; q < this.invsize(); q++)
        {
            QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(slot(q), this.xCoord, this.yCoord, this.zCoord, q), this.worldObj, this.xCoord, this.zCoord);
        }
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
        if (this.getClass() != NestTileEntity.class)
        {
            return super.getDrops(aFortune, aSilkTouch);
        }
        if (aSilkTouch) return super.getDrops(aFortune, aSilkTouch);
        ArrayListNoNulls<ItemStack> returnable = new ArrayListNoNulls<>();
        returnable.add(IL.Grass_Moldy.get(2 + this.getRandomNumber(aFortune + 1)));
        return returnable;
    }

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
    protected EntityLiving chosenEntity;

    @Override
    public void readFromNBT2(NBTTagCompound aNBT) {
        super.readFromNBT2(aNBT);
        String entityID = aNBT.getString("ENT");
        if (worldObj != null && entityID != null && entityID.length() > 0)
        {
            UUID uuid = UUID.fromString(entityID);
            if (chosenEntity == null || chosenEntity.getUniqueID() != uuid)
            {
                chosenEntity = null;
                List entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, this.box().expand(32, 16, 32));
                for (Object entity : entities) {
                    EntityLiving eL = (EntityLiving)entity;
                    if (eL.getUniqueID() == uuid)
                    {
                        chosenEntity = eL;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void writeToNBT2(NBTTagCompound aNBT) {
        super.writeToNBT2(aNBT);
        if (chosenEntity != null)
        {
            aNBT.setString("ENT", chosenEntity.getPersistentID().toString());
        } else {
            aNBT.setString("ENT", "");
        }
    }

    /**
     * Attempts to set the passed entity as the owner of the nest.
     * @param entityLiving The entity to nest here.
     * @return true if the nest is unoccupied by a living nearby creature and the passed entity is an adult.
     */
    public boolean setNestingEntity(EntityLiving entityLiving)
    {
        if (entityLiving == chosenEntity)
        {
            return true;
        }
        if (chosenEntity != null && !chosenEntity.isDead && chosenEntity.getDistance(xCoord, yCoord, zCoord) < 32)
        {
            return false;
        }
        if (entityLiving.isChild())
        {
            return false;
        }
        chosenEntity = entityLiving;
        return true;
    }

    public EntityLiving getNestingEntity()
    {
        if (chosenEntity.isDead || chosenEntity.getDistance(xCoord, yCoord, zCoord) > 32)
        {
            //System.out.println("Too far away: " + chosenEntity.getDistance(xCoord, yCoord, zCoord));
            chosenEntity = null;
        }
        return chosenEntity;
    }

    @Override
    public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
        ItemStack stack = aPlayer.getHeldItem();
        if (stack != null && OM.is("magnifyingglass", stack))
        {
            List<String> chats = new ArrayList<String>();
            if (getNestingEntity() == null)
            {
                chats.add("It seems this nest has been abandoned by its owner.");
            } else {
                chats.add("This nest belongs to a " + getNestingEntity().getCommandSenderName());
            }
        }
        return super.onBlockActivated2(aPlayer, aSide, aHitX, aHitY, aHitZ);
    }

    @Override
    public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        if (aRenderPass > 4)
        {
            ItemStack stack = slot(aRenderPass - 5);
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
        }
        if (ourcolor == null)
        {
            ourcolor = new short[]{(short)(210 + Math.abs(this.xCoord % 16)), (short)(245 + Math.abs(this.zCoord % 8)), (short)(200 + Math.abs(this.zCoord % 16) - Math.abs(this.xCoord % 12)), 255};
        }
        return BlockTextureDefault.get(nest, ourcolor);
    }

    @Override
    public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
        switch(aRenderPass) {
            case 9:
                //egg
                aBlock.setBlockBounds(PX_P[10], PX_P[2], PX_P[9], PX_P[13], PX_P[6], PX_P[12]);
                break;
            case 8:
                //egg
                aBlock.setBlockBounds(PX_P[9], PX_P[2], PX_P[4], PX_P[12], PX_P[6], PX_P[7]);
                break;
            case 7:
                //egg
                aBlock.setBlockBounds(PX_P[4], PX_P[2], PX_P[9], PX_P[7], PX_P[6], PX_P[12]);
                break;
            case 6:
                //egg
                aBlock.setBlockBounds(PX_P[4], PX_P[2], PX_P[6], PX_P[7], PX_P[6], PX_P[9]);
                break;
            case 5:
                //egg
                aBlock.setBlockBounds(PX_P[7], PX_P[2], PX_P[7], PX_P[10], PX_P[6], PX_P[10]);
                break;
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
        if (xCoord == 0 && yCoord == 0 && zCoord == 0) return 5; //skip check altogether in inventory
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
            return 5;
        } else {
            return 5 + invsize();
        }
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

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return false;
    }
}
