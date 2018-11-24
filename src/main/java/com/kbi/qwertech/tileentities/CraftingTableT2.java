package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.items.MultiItemTool_QT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.gui.Slot_Holo;
import gregapi.gui.Slot_Normal;
import gregapi.network.INetworkHandler;
import gregapi.network.packets.data.PacketSyncDataByte;
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
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

import java.awt.*;
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
		    if (!this.worldObj.isRemote) {
                this.worldObj.func_147480_a(this.xCoord, this.yCoord, this.zCoord, true);
            }
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

				if (player instanceof EntityPlayer)
                {
                    EntityPlayer playa = (EntityPlayer)player;
                    if (playa.openContainer instanceof GUICommonAdvancedCraftingTable)
                    {
                        playa.inventory.addItemStackToInventory(droppable);
                        droppable = null;
                    }
                }

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
				if (droppable != null && !worldObj.isRemote) {
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

	@Override
	public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {
		return new GUICommonAdvancedCraftingTable2(aPlayer.inventory, this);
	}

	public class GUICommonAdvancedCraftingTable2 extends GUICommonAdvancedCraftingTable
	{
		public GUICommonAdvancedCraftingTable2(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
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
                ct.handleHammer(this.mInventoryPlayer.player, this.mInventoryPlayer.getStackInSlot(getHammer()));
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

	public class GUIClientAdvancedCraftingTable2 extends GUIClientAdvancedCraftingTable
	{

		public GUIClientAdvancedCraftingTable2(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
            super(new GUICommonAdvancedCraftingTable2(aInventoryPlayer, aTileEntity), aTileEntity.mGUITexture);
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

		public void updateCache()
		{
			ItemStack[] stacks = this.mContainer.mInventoryPlayer.mainInventory;
			boolean found = false;
			for (int q = 0; q < stacks.length; q++)
			{
				if (OM.is("craftingToolHardHammer", stacks[q]))
				{
					hammerStack = stacks[q];
					found = true;
					break;
				}
			}
			if (!found)
			{
				hammerStack = null;
			}
		}

		@Override
		protected void mouseClicked(int x, int y, int mB) {
			super.mouseClicked(x, y, mB);
			updateCache();
		}

		@Override
		public void displayHammerable(int p1, int p2)
		{
			if (this.mContainer.mInventoryPlayer.inventoryChanged)
			{
				updateCache();
			}
			if (upCounting)
			{
				counter = counter + 1;
			} else {
				counter = counter - 1;
			}
			if (counter < 0)
			{
				upCounting = true;
			} else if (counter > 60)
			{
				upCounting = false;
			}
			if (this.inventorySlots.getSlot(10).getStack() != null)
            {
                if (hammerStack != null) {
                    ((Slot_Holo)this.mContainer.getSlot(12)).setTooltip("Use Hammer", LH.Chat.GREEN);
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
                    ((Slot_Holo)this.mContainer.getSlot(12)).setTooltip("Hammer Needed", Chat.DRED);
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
			    ((Slot_Holo)this.mContainer.getSlot(12)).setTooltip("No hammer recipe", Chat.DGRAY);
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
