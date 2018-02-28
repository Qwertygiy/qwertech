package com.kbi.qwertech.tileentities;

import static gregapi.data.CS.F;
import static gregapi.data.CS.NW_API;
import static gregapi.data.CS.T;
import static gregapi.data.CS.W;
import gregapi.block.prefixblock.PrefixBlockTileEntity;
import gregapi.network.INetworkHandler;
import gregapi.network.packets.data.PacketSyncDataName;
import gregapi.network.packets.ids.PacketSyncDataIDs;
import gregapi.render.ITexture;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import com.kbi.qwertech.blocks.ChiselableBlock;

public class ChiselableTileEntity extends PrefixBlockTileEntity {
	public short mDesign = W;

	public ChiselableTileEntity() {
		super();
	}
	
	@Override
	public void onScheduledUpdate() {
		if (!(mBlocked = WD.visOcc(worldObj, xCoord, yCoord, zCoord, F, T))) {
			NW_API.sendToAllPlayersInRange(new PacketSyncDataIDs(getCoords(), mMetaData, mDesign), worldObj, getCoords());
			if (mItemNBT != null && mItemNBT.hasKey("display")) NW_API.sendToAllPlayersInRange(new PacketSyncDataName(getCoords(), mItemNBT.getCompoundTag("display").getString("Name")), worldObj, getCoords());
		}
	}
	
	@Override
	public void onAdjacentBlockChange(int aX, int aY, int aZ) {
		if (!worldObj.isRemote && mBlocked) {
			mBlocked = F;
			NW_API.sendToAllPlayersInRange(new PacketSyncDataIDs(getCoords(), mMetaData, mDesign), worldObj, getCoords());
			if (mItemNBT != null && mItemNBT.hasKey("display")) NW_API.sendToAllPlayersInRange(new PacketSyncDataName(getCoords(), mItemNBT.getCompoundTag("display").getString("Name")), worldObj, getCoords());
		}
	}
	
	@Override
	public void sendUpdateToPlayer(EntityPlayerMP aPlayer) {
		if (!(mBlocked = WD.visOcc(worldObj, xCoord, yCoord, zCoord, T, T))) {
			NW_API.sendToPlayer(new PacketSyncDataIDs(getCoords(), mMetaData, mDesign), aPlayer);
			if (mItemNBT != null && mItemNBT.hasKey("display")) NW_API.sendToPlayer(new PacketSyncDataName(getCoords(), mItemNBT.getCompoundTag("display").getString("Name")), aPlayer);
		}
	}
	
	private ITexture mTexture;
	
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		if (mTexture == null) mTexture = (aBlock instanceof ChiselableBlock ? ((ChiselableBlock)aBlock).getTexture(mMetaData, mDesign, worldObj != null) : null);
		return mTexture;
	}
	
	@Override public boolean renderItem(Block aBlock, RenderBlocks aRenderer) {return F;}
	@Override public boolean renderBlock(Block aBlock, RenderBlocks aRenderer) {return F;}
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public void readFromNBT(NBTTagCompound aNBT) {
		super.readFromNBT(aNBT); 
		mDesign = aNBT.getShort("d"); 
		if (mDesign >= 32767 || mDesign < 0) 
		{
			System.out.println("Invalid design: " + mDesign);
			mDesign = 0;
		} 
		aNBT.setShort("d", mDesign); 
		mItemNBT = aNBT;
	}
	@Override public void writeToNBT(NBTTagCompound aNBT) {super.writeToNBT(aNBT); aNBT.setShort("d", mDesign); mItemNBT = aNBT;}
	@Override public void processPacket(INetworkHandler aNetworkHandler) {/**/}
}
