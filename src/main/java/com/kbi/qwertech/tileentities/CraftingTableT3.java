package com.kbi.qwertech.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.gui.Slot_Holo;
import gregapi.gui.Slot_Normal;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import java.awt.*;
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

			if (player instanceof EntityPlayer)
			{
				EntityPlayer playa = (EntityPlayer)player;
				if (playa.openContainer instanceof GUICommonAdvancedCraftingTable)
				{
					playa.inventory.addItemStackToInventory(droppable);
					droppable = null;
				}
			}
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
			if (droppable != null && table.exists() && table.mTileEntity instanceof CuttingBoardTileEntity)
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
			if (droppable != null && !this.worldObj.isRemote) {
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

	@Override
	public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {
		return new GUICommonAdvancedCraftingTable3(aPlayer.inventory, this);
	}

	public class GUICommonAdvancedCraftingTable3 extends GUICommonAdvancedCraftingTable
	{
		public GUICommonAdvancedCraftingTable3(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
			super(aInventoryPlayer, aTileEntity);
		}

		public int getHammer()
		{
			for (int q = 0; q < this.mInventoryPlayer.getSizeInventory(); q++)
			{
				ItemStack check = this.mInventoryPlayer.getStackInSlot(q);
				if (OM.is("craftingToolHardHammer", check))
				{
					return q;
				}
			}
			return -1;
		}

		@Override
		public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
			int slot = getHammer();
			if (aSlotIndex == 12 && slot != -1)
			{
				CraftingTableT1 ct = (CraftingTableT1)this.mTileEntity;
				EntityPlayer player = this.mInventoryPlayer.player;
				ItemStack currentlyHolding = player.getCurrentEquippedItem();
				ItemStack ourHammer = this.mInventoryPlayer.getStackInSlot(slot);
				this.mInventoryPlayer.setInventorySlotContents(slot, currentlyHolding);
				this.mInventoryPlayer.setInventorySlotContents(this.mInventoryPlayer.currentItem, ourHammer);
				player.swingItem();
				if (ourHammer.getItem() instanceof MultiItemTool) {
					((MultiItemTool)ourHammer.getItem()).doDamage(ourHammer, UT.Code.units(ct.handleHammer(this.mInventoryPlayer.player, this.mInventoryPlayer.getStackInSlot(getHammer())), 10000, 400, true), this.mInventoryPlayer.player);
				} else if (ourHammer.isItemStackDamageable())
				{
					ourHammer.damageItem(1, this.mInventoryPlayer.player);
				}
				this.mInventoryPlayer.setInventorySlotContents(this.mInventoryPlayer.currentItem, ourHammer);
			}
			return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
		}

		@Override
		public int addSlots(InventoryPlayer aInventoryPlayer) {
			addSlotToContainer(new Slot_Normal(mTileEntity, 0,  80, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, 1,  98, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, 2, 116, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, 3,  80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, 4,  98, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, 5, 116, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, 6,  80, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, 7,  98, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, 8, 116, 53));

			addSlotToContainer(new Slot_Holo(mTileEntity, 9, 152, 35, F, F, 1));
			addSlotToContainer(new Slot_Holo(mTileEntity, 10, 35, 35, F, F, 1));
			addSlotToContainer(new Slot_Holo(mTileEntity, 11, 134, 35, F, F, 1).setTooltip("Dump to Inventory", LH.Chat.WHITE));

			addSlotToContainer(new Slot_Holo(mTileEntity, 12, 34, 55, F, F, 1).setTooltip("Use Hammer", LH.Chat.WHITE));

			return 84;
		}
	}

	public class GUIClientAdvancedCraftingTable3 extends GUIClientAdvancedCraftingTable {
		public GUIClientAdvancedCraftingTable3(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
			super(new GUICommonAdvancedCraftingTable3(aInventoryPlayer, aTileEntity), aTileEntity.mGUITexture);
			updateCache();
		}

		public ItemStack hammerStack;
		public ItemStack defaultHammer = CS.ToolsGT.sMetaTool.getToolWithStats(12, MT.Steel, MT.Wood);
		public int counter = 0;
		public boolean upCounting = true;

		@Override
		public void initGui() {
			super.initGui();
		}

		public void updateCache() {
			ItemStack[] stacks = this.mContainer.mInventoryPlayer.mainInventory;
			boolean found = false;
			for (int q = 0; q < stacks.length; q++) {
				if (OM.is("craftingToolHardHammer", stacks[q])) {
					hammerStack = stacks[q];
					found = true;
					break;
				}
			}
			if (!found) {
				hammerStack = null;
			}
		}

		@Override
		protected void mouseClicked(int x, int y, int mB) {
			super.mouseClicked(x, y, mB);
			updateCache();
		}

		@Override
		public void displayHammerable(int p1, int p2) {
			if (this.mContainer.mInventoryPlayer.inventoryChanged) {
				updateCache();
			}
			if (upCounting) {
				counter = counter + 1;
			} else {
				counter = counter - 1;
			}
			if (counter < 0) {
				upCounting = true;
			} else if (counter > 60) {
				upCounting = false;
			}
			if (this.inventorySlots.getSlot(10).getStack() != null) {
				if (hammerStack != null) {
					((Slot_Holo) this.mContainer.getSlot(12)).setTooltip("Use Hammer", LH.Chat.GREEN);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glColorMask(true, true, true, false);
					this.drawGradientRect(34, 55, 52, 73, new Color(90 + (counter * 2), 120 + (counter * 2), 90 + (counter * 2)).getRGB(), new Color(90 + (counter * 2), 120 + (counter * 2), 90 + (counter * 2)).getRGB());
					this.drawGradientRect(35, 56, 51, 72, new Color(10 + counter * 3, 255, 10 + counter, 150).getRGB(), new Color(3 + (counter * 3), 200, 1 + counter, 255).getRGB());
					GL11.glColorMask(true, true, true, true);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					itemRender.renderItemIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), hammerStack, 35, 56, true);
					//this.drawCenteredString(this.fontRendererObj, "HIT TABLE", 41, 55, 3905827);
					//this.drawCenteredString(this.fontRendererObj, "WITH HAMMER", 41, 66, 3905827);
				} else {
					((Slot_Holo) this.mContainer.getSlot(12)).setTooltip("Hammer Needed", Chat.DRED);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glColorMask(true, true, true, false);
					this.drawGradientRect(34, 55, 52, 73, new Color(90 + (counter * 2), 60 + (counter * 2), 60 + (counter * 2)).getRGB(), new Color(90 + (counter * 2), 60 + (counter * 2), 60 + (counter * 2)).getRGB());
					itemRender.renderItemIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), defaultHammer, 35, 56, true);
					this.drawGradientRect(35, 56, 51, 72, new Color(255, 10 + counter * 3, 10 + counter, 150).getRGB(), new Color(200, 3 + counter * 3, 1 + counter, 255).getRGB());
					GL11.glColorMask(true, true, true, true);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
				}
			} else {
				((Slot_Holo) this.mContainer.getSlot(12)).setTooltip("No hammer recipe", Chat.DGRAY);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glColorMask(true, true, true, false);
				this.drawGradientRect(34, 55, 52, 73, new Color(60 + counter, 60 + counter, 60 + counter).getRGB(), new Color(60 + counter, 60 + counter, 60 + counter).getRGB());
				itemRender.renderItemIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), defaultHammer, 35, 56, true);
				this.drawGradientRect(35, 56, 51, 72, new Color(100 + counter, 100 + counter, 100 + counter, 150).getRGB(), new Color(110 + counter, 110 + counter, 110 + counter, 255).getRGB());
				GL11.glColorMask(true, true, true, true);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
			}
		}
	}

}
