package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.api.recipe.managers.CraftingManager3D;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.CS;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Holo;
import gregapi.gui.Slot_Normal;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

public class CraftingTableT4 extends CraftingTableT3 {
	
	public CraftingTableT4()
	{
		this.mGUITexture = "qwertech:textures/gui/CraftingTableT4.png";
	}
	
	
	@Override
	public String getTileEntityName() {
		// TODO Auto-generated method stub
		return "qt.crafting.tier4";
	}
	
	@Override
	public void generateBoundingBoxes()
	{
		List<AxisAlignedBB> aList = new ArrayList();
		AxisAlignedBB newBox = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
		boolean sideways = false;
		boolean backwards = false;
		double[] dists = new double[]{0.2, 0.4, 0.4, 0.6, 0.6, 0.8};
		int[][] alignments = new int[][]{
				{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26},
				{20, 19, 18, 23, 22, 21, 26, 25, 24, 11, 10, 9, 14, 13, 12, 17, 16, 15, 2, 1, 0, 5, 4, 3, 8, 7, 6},
				{2, 11, 20, 5, 14, 23, 8, 17, 26, 1, 10, 19, 4, 13, 22, 7, 16, 25, 0, 9, 18, 3, 12, 21, 6, 15, 24},
				{18, 9, 0, 21, 12, 3, 24, 15, 6, 19, 10, 1, 22, 13, 4, 25, 16, 7, 20, 11, 2, 23, 14, 5, 26, 17, 8}};
		
		int thechoice = 0;
		int ex;
		int zee;
		{
			for (int e = 0; e < 3; e++)
			{
				for (int q = 0; q < 3; q++)
				{
					for (int w = 0; w < 3; w++)
					{
						if (mFacing == CS.SIDE_X_POS || mFacing == CS.SIDE_X_NEG) {ex = 2 - w; zee = e;} else {ex = w; zee = 2 - e;}
						newBox.setBounds(xCoord + dists[ex * 2], yCoord + 1.05 + (q * 0.2), zCoord + dists[zee * 2], xCoord + dists[(ex * 2) + 1], yCoord + 1.25 + (q * 0.2), zCoord + dists[(zee * 2) + 1]);
						aList.add(newBox.copy());
					}
				}
			}
		}

		switch (mFacing) {
			case CS.SIDE_X_POS:
				thechoice = 2;
				break;
			case CS.SIDE_X_NEG:
				thechoice = 3;
				break;
			case CS.SIDE_Z_POS:
			case CS.SIDE_Y_POS:
				thechoice = 1;
				break;
			case CS.SIDE_Z_NEG:
			case CS.SIDE_Y_NEG:
				thechoice = 0;
				break;
		}
		currentBoxes = new ArrayList(27);
		for (int q = 0; q < aList.size(); q++)
		{
			currentBoxes.add(aList.get(aList.size() - 1 - alignments[thechoice][q]));
		}
	}
	
	@Override
	public void addHammerToolTip(List aList, ItemStack aStack) {
		aList.add(Chat.DGREEN	+ "T4: " + Chat.DGRAY + "3x3x3 grid");
	}
	
	@Override
	public boolean canDrop(int aSlot) {
		if (aSlot < 27) return T;
		return F;
	}
	
	public boolean canDoCraftingOutput() {
		if (!slotHas(27)) return F;
		return T;
	}
	
	public boolean canDoHammerOutput() {
		if (!slotHas(28)) return F;
		return T;
	}
	
	@Override public void setInventorySlotContents(int aSlot, ItemStack aStack)		{if (aSlot >= 0 && aSlot < 27 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContents(aSlot, aStack);}
	@Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack)	{if (aSlot >= 0 && aSlot < 27 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContentsGUI(aSlot, aStack);}
	@Override public ItemStack decrStackSize(int aSlot, int aDecrement)				{if (aSlot >= 0 && aSlot < 27 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSize(aSlot, aDecrement);}
	@Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement)			{if (aSlot >= 0 && aSlot < 27 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSizeGUI(aSlot, aDecrement);}
	
	public ItemStack get3DOutput(World world, ItemStack... aRecipe)
	{
		List<IRecipe> list = CraftingManager3D.getInstance().getRecipeList();
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer var1) {return F;}}, 3, 9);
		for (int i = 0; i < 27 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		
		for (int i = 0; i < list.size(); i++)
		{
			IRecipe check = list.get(i);
			if (check.matches(aCrafting, worldObj))
			{
				return check.getCraftingResult(aCrafting);
			}
		}
		return null;
	}
	
	@Override
	public ItemStack getCraftingOutput()
	{
		ItemStack mainReturnable = get3DOutput(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8), slot(9), slot(10), slot(11), slot(12), slot(13), slot(14), slot(15), slot(16), slot(17), slot(18), slot(19), slot(20), slot(21), slot(22), slot(23), slot(24), slot(25), slot(26));
		if (mainReturnable == null) 
		{
			if (!slotHas(9) && !slotHas(10) && !slotHas(11) && !slotHas(12) && !slotHas(13) && !slotHas(14) && !slotHas(15) && !slotHas(16) && !slotHas(17) && !slotHas(18) && !slotHas(19) && !slotHas(20) && !slotHas(21) && !slotHas(22) && !slotHas(23) && !slotHas(24) && !slotHas(25) && !slotHas(26))
			{
				mainReturnable = CR.getany(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8));
			}
			if (mainReturnable == null)
			{
				if (!slotHas(0) && !slotHas(1) && !slotHas(2) && !slotHas(3) && !slotHas(4) && !slotHas(5) && !slotHas(9) && !slotHas(10) && !slotHas(11) && !slotHas(12) && !slotHas(13) && !slotHas(14) && !slotHas(18) && !slotHas(19) && !slotHas(20) && !slotHas(21) && !slotHas(22) && !slotHas(23))
				{
					mainReturnable = CR.getany(worldObj, slot(6), slot(7), slot(8), slot(15), slot(16), slot(17), slot(24), slot(25), slot(26));
				}
			}
		}
		if (!slotHas(9) && !slotHas(10) && !slotHas(11) && !slotHas(12) && !slotHas(13) && !slotHas(14) && !slotHas(15) && !slotHas(16) && !slotHas(17) && !slotHas(18) && !slotHas(19) && !slotHas(20) && !slotHas(21) && !slotHas(22) && !slotHas(23) && !slotHas(24) && !slotHas(25) && !slotHas(26))
		{
			ItemStack returnable = getHammeringOutput(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8));
			slot(28, returnable);
		}
		//if (!ST.equal(slot(10), returnable, F)) mUpdatedGrid = T;
		return slot(27, mainReturnable);
		//return slot(9, CR.getany(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8)));
	}
	
	public ItemStack consumeMaterials(EntityPlayer aPlayer, ItemStack aHoldStack, boolean aSubsequentClick) {
		if (!slotHas(27)) return aHoldStack;
		
		if (aHoldStack != null) {
			if (!ST.equal(aHoldStack, slot(27))) {
				if (!aSubsequentClick) {
					UT.Sounds.play(SFX.MC_HMM, 50, 1.0F, 1.0F, getCoords());
				}
				return aHoldStack;
			}
			if (aHoldStack.stackSize + slot(27).stackSize > aHoldStack.getMaxStackSize()) return aHoldStack;
			for (int i = 0; i < 27; i++) if (OM.is("gt:autocrafterinfinite", slot(i))) {
				if (!aSubsequentClick) {
					UT.Sounds.play(SFX.MC_HMM, 50, 1.0F, 1.0F, getCoords());
				}
				return aHoldStack;
			}
		}
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = getCoords();
		
		try {FMLCommonHandler.instance().firePlayerCraftingEvent(aPlayer, ST.copy(slot(27)), new InventoryCrafting(null, 3, 3));} catch(Throwable e) {e.printStackTrace(ERR);}
		
		boolean tOldToolSounds = TOOL_SOUNDS;
		
		for (int i = 0; i < 27; i++) if (slotHas(i)) {
			boolean tNeeds = T;
			TOOL_SOUNDS = isClientSide() && tOldToolSounds && !aSubsequentClick;
			ItemStack tContainer = ST.container(slot(i), F);
			TOOL_SOUNDS = F;
			// Contains itself, so it's an infinite use Container Item anyways.
			if (ST.equal(slot(i), tContainer, F)) continue;
			
			if (tNeeds) for (int j = 0; j < 27; j++) if (j == i) {
				if (ST.equalTools(slot(i), slot(j), F) && slot(j).stackSize > 0) {
					tNeeds = F;
					TOOL_SOUNDS = F;
					ItemStack tContainer2 = ST.container(slot(j), F);
					TOOL_SOUNDS = F;
					// Consume the Item.
					if (tContainer2 == null || (tContainer2.isItemStackDamageable() && tContainer2.getItemDamage() >= tContainer2.getMaxDamage())) {
						decrStackSize(j, 1);
					} else if (slot(j).stackSize == 1) {
						slot(j, tContainer2);
					} else {
						decrStackSize(j, 1);
					}
					break;
				}
			}
		}
		
		if (aHoldStack == null) aHoldStack = ST.copy(slot(27)); else aHoldStack.stackSize += slot(27).stackSize;
		
		aHoldStack.onCrafting(worldObj, aPlayer, slot(27).stackSize);
		
		if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.crafting_table)) aPlayer.addStat(AchievementList.buildWorkBench, 1);
		if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.furnace)) aPlayer.addStat(AchievementList.buildFurnace, 1);
		if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.enchanting_table)) aPlayer.addStat(AchievementList.enchantments, 1);
		if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.bookshelf)) aPlayer.addStat(AchievementList.bookcase, 1);
		if (aHoldStack.getItem() == Items.bread) aPlayer.addStat(AchievementList.makeBread, 1);
		if (aHoldStack.getItem() == Items.cake) aPlayer.addStat(AchievementList.bakeCake, 1);
		if (aHoldStack.getItem() instanceof ItemHoe) aPlayer.addStat(AchievementList.buildHoe, 1);
		if (aHoldStack.getItem() instanceof ItemSword) aPlayer.addStat(AchievementList.buildSword, 1);
		if (aHoldStack.getItem() instanceof ItemPickaxe) {
			aPlayer.addStat(AchievementList.buildPickaxe, 1);
			if (aHoldStack.getItem() != Items.wooden_pickaxe) aPlayer.addStat(AchievementList.buildBetterPickaxe, 1);
		}
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
		
		TOOL_SOUNDS = tOldToolSounds;
		
		return aHoldStack;
	}
	
	public ItemStack consumeMaterialsHammer(EntityPlayer aPlayer, boolean aSubsequentClick) {
		if (!slotHas(28)) return null;
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = getCoords();
		
		ItemStack aHoldStack = slot(28);
		
		try {FMLCommonHandler.instance().firePlayerCraftingEvent(aPlayer, ST.copy(slot(28)), new InventoryCrafting(null, 3, 3));} catch(Throwable e) {e.printStackTrace(ERR);}
		
		boolean tOldToolSounds = TOOL_SOUNDS;
		
		for (int i = 0; i < 9; i++) if (slotHas(i)) {
			boolean tNeeds = T;
			TOOL_SOUNDS = isClientSide() && tOldToolSounds && !aSubsequentClick;
			ItemStack tContainer = ST.container(slot(i), F);
			TOOL_SOUNDS = F;
			// Contains itself, so it's an infinite use Container Item anyways.
			if (ST.equal(slot(i), tContainer, F)) continue;
			
			if (tNeeds) for (int j = 0; j < 9; j++) if (j == i) {
				if (ST.equalTools(slot(i), slot(j), F) && slot(j).stackSize > 0) {
					tNeeds = F;
					TOOL_SOUNDS = F;
					ItemStack tContainer2 = ST.container(slot(j), F);
					TOOL_SOUNDS = F;
					// Consume the Item.
					if (tContainer2 == null || (tContainer2.isItemStackDamageable() && tContainer2.getItemDamage() >= tContainer2.getMaxDamage())) {
						decrStackSize(j, 1);
					} else if (slot(j).stackSize == 1) {
						slot(j, tContainer2);
					} else {
						decrStackSize(j, 1);
					}
					break;
				}
			}
		}
		
		aHoldStack = ST.copy(slot(28));
		
		if (aPlayer != null)
		{
			aHoldStack.onCrafting(worldObj, aPlayer, slot(28).stackSize);
			
			if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.crafting_table)) aPlayer.addStat(AchievementList.buildWorkBench, 1);
			if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.furnace)) aPlayer.addStat(AchievementList.buildFurnace, 1);
			if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.enchanting_table)) aPlayer.addStat(AchievementList.enchantments, 1);
			if (aHoldStack.getItem() == Item.getItemFromBlock(Blocks.bookshelf)) aPlayer.addStat(AchievementList.bookcase, 1);
			if (aHoldStack.getItem() == Items.bread) aPlayer.addStat(AchievementList.makeBread, 1);
			if (aHoldStack.getItem() == Items.cake) aPlayer.addStat(AchievementList.bakeCake, 1);
			if (aHoldStack.getItem() instanceof ItemHoe) aPlayer.addStat(AchievementList.buildHoe, 1);
			if (aHoldStack.getItem() instanceof ItemSword) aPlayer.addStat(AchievementList.buildSword, 1);
			if (aHoldStack.getItem() instanceof ItemPickaxe) {
				aPlayer.addStat(AchievementList.buildPickaxe, 1);
				if (aHoldStack.getItem() != Items.wooden_pickaxe) aPlayer.addStat(AchievementList.buildBetterPickaxe, 1);
			}
		}
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
		
		TOOL_SOUNDS = tOldToolSounds;
		
		return aHoldStack;
	}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new GUIClientAdvancedCraftingTable4(aPlayer.inventory, this);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) 
	{
		return new GUICommonAdvancedCraftingTable4(aPlayer.inventory, this);
	}
	
	public class GUICommonAdvancedCraftingTable4 extends ContainerCommon {
		public GUICommonAdvancedCraftingTable4(InventoryPlayer aInventoryPlayer, CraftingTableT4 aTileEntity) {
			super(aInventoryPlayer, aTileEntity, 0, aTileEntity.getSizeInventoryGUI());
		}
		
		@Override
		public int addSlots(InventoryPlayer aInventoryPlayer) {
			addSlotToContainer(new Slot_Normal(mTileEntity, 0,  21, 41));
			addSlotToContainer(new Slot_Normal(mTileEntity, 1,  51, 41));
			addSlotToContainer(new Slot_Normal(mTileEntity, 2,  83, 41));
			addSlotToContainer(new Slot_Normal(mTileEntity, 3,  21, 69));
			addSlotToContainer(new Slot_Normal(mTileEntity, 4,  51, 69));
			addSlotToContainer(new Slot_Normal(mTileEntity, 5,  83, 69));
			addSlotToContainer(new Slot_Normal(mTileEntity, 6,  21,101));
			addSlotToContainer(new Slot_Normal(mTileEntity, 7,  51,101));
			addSlotToContainer(new Slot_Normal(mTileEntity, 8,  83,101));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 9,  34, 34));
			addSlotToContainer(new Slot_Normal(mTileEntity,10,  64, 34));
			addSlotToContainer(new Slot_Normal(mTileEntity,11,  96, 34));
			addSlotToContainer(new Slot_Normal(mTileEntity,12,  34, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity,13,  64, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity,14,  96, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity,15,  34, 94));
			addSlotToContainer(new Slot_Normal(mTileEntity,16,  64, 94));
			addSlotToContainer(new Slot_Normal(mTileEntity,17,  96, 94));
			
			addSlotToContainer(new Slot_Normal(mTileEntity,18,  46, 28));
			addSlotToContainer(new Slot_Normal(mTileEntity,19,  76, 28));
			addSlotToContainer(new Slot_Normal(mTileEntity,20, 108, 28));
			addSlotToContainer(new Slot_Normal(mTileEntity,21,  46, 56));
			addSlotToContainer(new Slot_Normal(mTileEntity,22,  76, 56));
			addSlotToContainer(new Slot_Normal(mTileEntity,23, 108, 56));
			addSlotToContainer(new Slot_Normal(mTileEntity,24,  46, 88));
			addSlotToContainer(new Slot_Normal(mTileEntity,25,  76, 88));
			addSlotToContainer(new Slot_Normal(mTileEntity,26, 108, 88));
			
			addSlotToContainer(new Slot_Holo(mTileEntity, 27, 152, 27, F, F, 1));
			addSlotToContainer(new Slot_Holo(mTileEntity, 28, 152, 65, F, F, 1));

			addSlotToContainer(new Slot_Holo(mTileEntity, 29, 152, 46, F, F, 1).setTooltip("Dump to Inventory", LH.Chat.WHITE));


			return super.addSlots(aInventoryPlayer);
		}
		
		@Override
		public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
			if (aSlotIndex == 29)
			{
				for (int q = 0; q < 27; q++)
				{
					ItemStack stack = mTileEntity.getStackInSlotGUI(q);
					if (aPlayer.inventory.addItemStackToInventory(stack) || !ST.valid(stack))
					{
						mTileEntity.setInventorySlotContentsGUI(q, null);
					} else {
						//break; why should it if there's stuff later down it can move?
					}
				}
				return null;
			}
			if (aSlotIndex > 28 || aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
			try {
				Slot tSlot = ((Slot)inventorySlots.get(aSlotIndex));
				ItemStack tStack = tSlot.getStack();
				if (tStack != null && tStack.stackSize <= 0) {
					tSlot.putStack(null);
					if (aSlotIndex < 27)
					{
						ItemStack toReturn = super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
						detectAndSendChanges();
						return toReturn;
					}
					return null;
				}
				if (aSlotIndex == 27) {
					ItemStack tCraftedStack = ((CraftingTableT1)mTileEntity).getCraftingOutput();
					if (tCraftedStack != null) {
						if (aShifthold == 1) {
							if (aMouseclick == 0) {
								// SHIFT LEFTCLICK
								for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
									if (aPlayer.inventory.mainInventory[i] == null || tCraftedStack.stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize()) {
										boolean temp = F;
										for (int j = 0; j < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && ((CraftingTableT1)mTileEntity).canDoCraftingOutput(); j++) {
											if (!ST.equal(tStack = ((CraftingTableT1)mTileEntity).getCraftingOutput(), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
												detectAndSendChanges();
												return aPlayer.inventory.getItemStack();
											}
											aPlayer.inventory.mainInventory[i] = (((CraftingTableT1)mTileEntity).consumeMaterials(aPlayer, aPlayer.inventory.mainInventory[i], i != 0 || j != 0));
											temp = T;
										}
										if (temp) return aPlayer.inventory.getItemStack();
									}
								}
								return aPlayer.inventory.getItemStack();
							}
							// SHIFT RIGHTCLICK
							for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
								if (aPlayer.inventory.mainInventory[i] == null || tCraftedStack.stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize()) {
									for (int j = 0; j < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && ((CraftingTableT1)mTileEntity).canDoCraftingOutput(); j++) {
										if (!ST.equal(tStack = ((CraftingTableT1)mTileEntity).getCraftingOutput(), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
											detectAndSendChanges();
											return aPlayer.inventory.getItemStack();
										}
										aPlayer.inventory.mainInventory[i] = (((CraftingTableT1)mTileEntity).consumeMaterials(aPlayer, aPlayer.inventory.mainInventory[i], i != 0 || j != 0));
									}
								}
							}
							return aPlayer.inventory.getItemStack();
						}
						if (aMouseclick == 0) {
							// LEFTCLICK
							if (((CraftingTableT1)mTileEntity).canDoCraftingOutput()) aPlayer.inventory.setItemStack(((CraftingTableT1)mTileEntity).consumeMaterials(aPlayer, aPlayer.inventory.getItemStack(), F));
							detectAndSendChanges();
							return aPlayer.inventory.getItemStack();
						}
						// RIGHTCLICK
						for (int i = 0; i < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && ((CraftingTableT1)mTileEntity).canDoCraftingOutput(); i++) {
							if (!ST.equal(tStack = ((CraftingTableT1)mTileEntity).getCraftingOutput(), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
								detectAndSendChanges();
								return aPlayer.inventory.getItemStack();
							}
							aPlayer.inventory.setItemStack(((CraftingTableT1)mTileEntity).consumeMaterials(aPlayer, aPlayer.inventory.getItemStack(), i != 0));
						}
						detectAndSendChanges();
						return aPlayer.inventory.getItemStack();
					}
					detectAndSendChanges();
					return null;
				}
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
			return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
		}
		
		@Override
		public int getSlotCount() {
			return 27;
		}
		
		@Override
		public int getShiftClickSlotCount() {
			return 0;
		}
		
		@Override
		protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer, int aOffset) {
			for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, (aOffset + i * 18) + 40));
			}
			for (int i = 0; i < 9; i++) {
				addSlotToContainer(new Slot(aInventoryPlayer, i, 8 + i * 18, aOffset + 98));
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public class GUIClientAdvancedCraftingTable4 extends ContainerClient {
		public GUIClientAdvancedCraftingTable4(InventoryPlayer aInventoryPlayer, CraftingTableT4 aTileEntity) {
			super(new GUICommonAdvancedCraftingTable4(aInventoryPlayer, aTileEntity), aTileEntity.mGUITexture);
			this.xSize = 176;
			this.ySize = 206;
		}
		
	}

}
