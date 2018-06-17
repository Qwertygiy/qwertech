package com.kbi.qwertech.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.data.CS;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

import static gregapi.data.CS.*;

public class CraftingTableT3 extends CraftingTableT1 implements IMultiTileEntity.IMTE_SyncDataByte {
	
	public CraftingTableT3()
	{
		this.mGUITexture = "qwertech:textures/gui/CraftingTableT3.png";
	}
	
	
	@Override
	public String getTileEntityName() {
		// TODO Auto-generated method stub
		return "qt.crafting.tier3";
	}
	
	@Override
	public boolean isSurfaceOpaque2	(byte aSide) 
	{return aSide == CS.SIDE_UP || aSide == CS.SIDE_DOWN;}
	
	@Override
	public void addHammerToolTip(List aList, ItemStack aStack) {
		aList.add(Chat.DBLUE	+ "T3: " + Chat.DGRAY + "Will not be damaged");
	}
	
	public long handleHammer(Entity player, ItemStack stack)
	{
		if (this.canDoHammerOutput())
		{
			ItemStack droppable;
			if (player instanceof EntityPlayer)
			{
				droppable = this.consumeMaterialsHammer((EntityPlayer)player, F);
			} else {
				droppable = this.consumeMaterialsHammer(null, F);
			}
			this.mUpdatedGrid = true;
			//EntityItem dropped = ST.drop(worldObj, getCoords(), droppable);
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
			//dropped.posY = this.yCoord + 4;
			//dropped.motionX = 0;
			//dropped.motionY = 0;
			//dropped.motionZ = 0;
			return 10000;
		}
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered)
	{
		return 5;
	}
	
	@Override
	public boolean shouldSideBeRendered(byte side)
	{
		return true;
	}
	
	@Override
	public boolean allowCovers(byte side)
	{
		return false;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide<2?aSide:aSide==mFacing?2:aSide==OPPOSITES[mFacing]?3:4;
		if (aSide == CS.SIDE_Y_POS && (mFacing == CS.SIDE_Y_POS || mFacing == CS.SIDE_Z_NEG || mFacing == CS.SIDE_Z_POS))
		{
			aIndex = 5;
		}
		if (this.primary == null)
		{
			if (this.mMaterial.mNameInternal.startsWith("Wood"))
			{
				this.primary = CraftingTableT3.sColoreds;
				this.overlay = CraftingTableT3.sOverlays;
			} else if(OP.ingot.canGenerateItem(this.mMaterial))
			{
				this.primary = CraftingTableT3.sMetals;
				this.overlay = CraftingTableT3.sOvermetals;
			} else if(OP.gem.canGenerateItem(this.mMaterial))
			{
				this.primary = CraftingTableT3.sGems;
				this.overlay = CraftingTableT3.sOvergems;
			} else {
				this.primary = CraftingTableT3.sRocks;
				this.overlay = CraftingTableT3.sOverrocks;
			}
		}
		return BlockTextureMulti.get(BlockTextureDefault.get(primary[aIndex], mRGBa), BlockTextureDefault.get(overlay[aIndex]));
	}
	
	public static String texture_Dir()
	{
		return "qwertech:craftingtables/t3/";
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "colored/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "colored/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "colored/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "colored/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "colored/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "colored/top2")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overlay/top")
	}, sMetals[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "metal/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "metal/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "metal/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "metal/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "metal/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "metal/top2")
	}, sOvermetals[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overmetal/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overmetal/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overmetal/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overmetal/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overmetal/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overmetal/top")
	}, sGems[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "gem/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "gem/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "gem/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "gem/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "gem/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "gem/top2")
	}, sOvergems[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overgem/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overgem/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overgem/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overgem/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overgem/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overgem/top")
	}, sRocks[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "stone/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "stone/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "stone/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "stone/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "stone/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "stone/top2")
	}, sOverrocks[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overstone/bottom"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overstone/top"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overstone/front"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overstone/back"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overstone/side"),
		new Textures.BlockIcons.CustomIcon(texture_Dir() + "overstone/top")
	};
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean setBlockBounds2(Block block, int aRenderPass, boolean[] aShouldSideBeRendered)
	{
		if (this.getFacing() == CS.SIDE_Y_POS || this.getFacing() == CS.SIDE_Z_NEG || this.getFacing() == CS.SIDE_Z_POS) 
		{
			switch (aRenderPass)
			{
				case 0:	block.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 2], PX_P[16], PX_P[ 3], PX_P[ 14]); return T;
				case 1:	block.setBlockBounds(PX_P[ 1], PX_P[ 1], PX_P[ 3], PX_P[15], PX_P[ 4], PX_P[ 13]); return T;
				case 2:	block.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 4], PX_P[14], PX_P[ 5], PX_P[ 12]); return T;
				case 3: block.setBlockBounds(PX_P[ 4], PX_P[ 3], PX_P[ 6], PX_P[12], PX_P[ 10], PX_P[ 10]); return T;
				case 4:	block.setBlockBounds(PX_P[ 0], PX_P[ 10], PX_P[ 1], PX_P[16], PX_P[ 16], PX_P[ 15]); return T;
			}
		}
		if (this.getFacing() == CS.SIDE_Y_NEG || this.getFacing() == CS.SIDE_X_NEG || this.getFacing() == CS.SIDE_X_POS) 
		{
			switch (aRenderPass)
			{
				case 0:	block.setBlockBounds(PX_P[ 2], PX_P[ 0], PX_P[ 0], PX_P[14], PX_P[ 3], PX_P[ 16]); return T;
				case 1:	block.setBlockBounds(PX_P[ 3], PX_P[ 1], PX_P[ 1], PX_P[13], PX_P[ 4], PX_P[ 15]); return T;
				case 2:	block.setBlockBounds(PX_P[ 4], PX_P[ 2], PX_P[ 2], PX_P[12], PX_P[ 5], PX_P[ 14]); return T;
				case 3: block.setBlockBounds(PX_P[ 6], PX_P[ 3], PX_P[ 4], PX_P[10], PX_P[ 10], PX_P[ 12]); return T;
				case 4:	block.setBlockBounds(PX_P[ 1], PX_P[ 10], PX_P[ 0], PX_P[15], PX_P[ 16], PX_P[ 16]); return T;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new GUIClientAdvancedCraftingTable3(aPlayer.inventory, this);}

	public class GUIClientAdvancedCraftingTable3 extends GUIClientAdvancedCraftingTable
	{

		public GUIClientAdvancedCraftingTable3(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
			super(aInventoryPlayer, aTileEntity);
		}
		
		@Override
		public void displayHammerable(int p1, int p2)
		{
			if (this.inventorySlots.getSlot(10).getHasStack())
			{
				this.drawCenteredString(this.fontRendererObj, "HIT TABLE", 41, 55, 3905827);
				this.drawCenteredString(this.fontRendererObj, "WITH HAMMER", 41, 66, 3905827);
			}
		}
		
	}

}
