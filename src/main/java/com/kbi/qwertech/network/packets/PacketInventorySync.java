package com.kbi.qwertech.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.tileentity.base.TileEntityBase05Inventories;
import gregapi.util.ST;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import java.io.IOException;

import static gregapi.data.CS.ERR;

public class PacketInventorySync implements IPacket {
	private ItemStack mStack;
	private int slot;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	
	public PacketInventorySync() {/**/}
	
	public PacketInventorySync(ItemStack aStack, int aX, int aY, int aZ, int aSlot) {
		mStack = aStack;
		slot = aSlot;
		xCoord = aX;
		yCoord = aY;
		zCoord = aZ;
	}
	
	@Override
	public byte getPacketID() {
		return 0;
	}
	
	@Override
	public ByteArrayDataOutput encode() {
        ByteArrayDataOutput aData = ByteStreams.newDataOutput();
		aData.writeInt(slot);
		aData.writeInt(xCoord);
		aData.writeInt(yCoord);
		aData.writeInt(zCoord);
		if (mStack == null)
		{
			aData.writeShort(-1);
		} else {
	        aData.writeShort(ST.id(mStack));
			aData.writeByte(mStack.stackSize);
			aData.writeShort(ST.meta(mStack));
	        NBTTagCompound tNBT = mStack.getTagCompound();
	        if (tNBT == null) aData.writeShort(-1); else {
	        	try {
	        		byte[] tData = CompressedStreamTools.compress(tNBT);
	                aData.writeShort(tData.length);
	                aData.write(tData);
	        	} catch (IOException e) {e.printStackTrace(ERR);}
	        }
		}
		return aData;
	}
	
	@Override
	public IPacket decode(ByteArrayDataInput aData) {
		int tZ = aData.readInt();
		int tSlot = aData.readInt();
		int tX = aData.readInt();
		int tY = aData.readInt();
		int itemId = aData.readShort();
		ItemStack possible = null;
		if (itemId > 0)
		{
			possible = ST.make(Item.getItemById(itemId), aData.readByte(), aData.readShort(), readNBTTagCompoundFromBuffer(aData));
		}
		return new PacketInventorySync(possible, tSlot, tX, tY, tZ);
	}
	
    public NBTTagCompound readNBTTagCompoundFromBuffer(ByteArrayDataInput aData) {
        short tLength = aData.readShort();
        if (tLength <= 0) return null;
        byte[] tData = new byte[tLength];
        aData.readFully(tData);
        try {return CompressedStreamTools.func_152457_a(tData, new NBTSizeTracker(2097152L));} catch (IOException e) {e.printStackTrace(ERR);}
        return null;
    }
    
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		//System.out.println("Recieved inventory packet for slot " + slot + " at " + xCoord + "x " + yCoord + "y " + zCoord  + "z");
		TileEntity te = aWorld.getTileEntity(xCoord, yCoord, zCoord);
		if (te != null && te instanceof TileEntityBase05Inventories)
		{
			//System.out.println("Successful");
			TileEntityBase05Inventories tb = (TileEntityBase05Inventories)te;
			tb.setInventorySlotContents(slot, mStack);
			tb.setInventorySlotContentsGUI(slot, mStack);
		}
	}
}