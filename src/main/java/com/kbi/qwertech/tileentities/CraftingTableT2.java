package com.kbi.qwertech.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.network.INetworkHandler;
import gregapi.network.packets.data.PacketSyncDataByte;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

import static gregapi.data.CS.F;

public class CraftingTableT2 extends CraftingTableT1 implements IMultiTileEntity.IMTE_SyncDataByte {
	
	public byte damage = 0;
	
	public CraftingTableT2()
	{
		this.mGUITexture = "qwertech:textures/gui/CraftingTableT2.png";
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		/*if (aIsServerSide) {
			if (this.getRandomNumber(20) == 0)
			{
				getNetworkHandler().sendToAllPlayersInRange(new PacketSyncDataByte(getCoords(), damage), worldObj, getCoords());
			}
		}*/
	}
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("damage")) damage = aNBT.getByte("damage");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, "damage", damage);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		super.writeItemNBT2(aNBT);
		UT.NBT.setNumber(aNBT, "damage", damage);
		return aNBT;
	}
	
	@Override
	public boolean receiveDataByte(byte aData, INetworkHandler aNetworkHandler)
	{
		this.damage = aData;
		return true;
	}
	
	@Override
	public String getTileEntityName() {
		// TODO Auto-generated method stub
		return "qt.crafting.tier2";
	}
	
	@Override
	public void addHammerToolTip(List aList, ItemStack aStack) {
		aList.add(Chat.BLUE	+ "T2: " + Chat.DRED + "Will be damaged by hammers");
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureMulti.get(super.getTexture2(aBlock, aRenderPass, aSide, aShouldSideBeRendered), BlockTextureDefault.get(cracks[damage], mRGBa));
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = new ArrayListNoNulls<ItemStack>();
		if (this.damage < 15)
		{
			rList = super.getDrops(aFortune, aSilkTouch);
			ItemStack hrm = rList.get(0);
			if (hrm != null)
			{
				NBTTagCompound setter = UT.NBT.make();
				this.writeItemNBT(setter);
				UT.NBT.set(hrm, setter);
				rList.set(0, hrm);
			}
		} else {
			rList.add(OP.dust.mat(this.mMaterial, 2));
			if (aFortune > 0) {
				int flipflop = this.getRandomNumber(2);
				if (flipflop == 0)
				{
					rList.add(OP.dustTiny.mat(this.mMaterial, 1 + this.getRandomNumber(aFortune)));
				} else {
					rList.add(OP.dustSmall.mat(this.mMaterial, 1 + this.getRandomNumber(aFortune)));
				}
			}
		}
		return rList;
	}
	
	public IIconContainer[] cracks = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/top"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_0"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_0"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_1"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_2"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_3"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_4"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_5"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_6"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_6"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_7"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_7"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_8"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_8"),
		new Textures.BlockIcons.CustomIcon("minecraft:destroy_stage_9"),
	};
	
	public long handleHammer(Entity player, ItemStack stack)
	{
		if (damage >= 15)
		{
			this.worldObj.func_147480_a(this.xCoord, this.yCoord, this.zCoord, true);
			return 12500;
		} else {
			if (this.slotHas(10))
			{
				damage += 1;
				ItemStack droppable;
				if (player instanceof EntityPlayer)
				{
					droppable = this.consumeMaterialsHammer((EntityPlayer)player, F);
				} else {
					droppable = this.consumeMaterialsHammer(null, F);
				}
				this.mUpdatedGrid = true;
				/*EntityItem dropped = ST.drop(worldObj, getCoords(), droppable);
				dropped.posY = this.yCoord + 1.1;
				dropped.motionX = 0;
				dropped.motionY = 0;
				dropped.motionZ = 0;*/
				DelegatorTileEntity<TileEntity> table = this.getAdjacentTileEntity(CS.SIDE_RIGHT);
				if (!table.exists() || !(table.mTileEntity instanceof CuttingBoardTileEntity))
				{
					table = this.getAdjacentTileEntity(CS.SIDE_LEFT);
					if (!table.exists() || !(table.mTileEntity instanceof CuttingBoardTileEntity))
					{
						table = this.getAdjacentTileEntity(CS.SIDE_BACK);
						if (!table.exists() || !(table.mTileEntity instanceof CuttingBoardTileEntity))
						{
							table = this.getAdjacentTileEntity(CS.SIDE_FRONT);
						}
					}
				}
				if (table.exists() && table.mTileEntity instanceof CuttingBoardTileEntity)
				{
					for (int q = 0; q < 8; q++)
					{
						ItemStack stacky = ((CuttingBoardTileEntity) table.mTileEntity).getStackInSlot(q);
						if (!ST.valid(stacky))
						{
							((CuttingBoardTileEntity) table.mTileEntity).setInventorySlotContents(q, droppable);
							droppable = null;
							break;
						} else if (ST.equal(stacky, droppable) && stacky.stackSize + droppable.stackSize < droppable.getMaxStackSize())
						{
							stacky.stackSize = stacky.stackSize + droppable.stackSize;
							((CuttingBoardTileEntity) table.mTileEntity).setInventorySlotContents(q, stacky);
							droppable = null;
							break;
						}
					}
				}
				if (droppable != null) {
					worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, droppable));
				}
				getNetworkHandler().sendToAllPlayersInRange(new PacketSyncDataByte(getCoords(), damage), worldObj, getCoords());
				return 10000;
			}
		}
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new GUIClientAdvancedCraftingTable2(aPlayer.inventory, this);}

	public class GUIClientAdvancedCraftingTable2 extends GUIClientAdvancedCraftingTable
	{

		public GUIClientAdvancedCraftingTable2(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
			super(aInventoryPlayer, aTileEntity);
		}
		
		@Override
		public void displayHammerable(int p1, int p2)
		{
			if (this.mContainer.getSlot(10).getHasStack())
			{
				this.drawCenteredString(this.fontRendererObj, "HIT TABLE", 41, 55, 3905827);
				this.drawCenteredString(this.fontRendererObj, "WITH HAMMER", 41, 66, 3905827);
			}
		}
		
	}

}
