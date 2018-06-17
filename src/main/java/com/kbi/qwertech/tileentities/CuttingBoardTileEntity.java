package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.recipe.CountertopRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerCountertop;
import com.kbi.qwertech.api.tileentities.InventoryScroll;
import com.kbi.qwertech.api.tileentities.SlotScroll;
import com.kbi.qwertech.network.packets.PacketInventorySync;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.metatype.BlockStones;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Holo;
import gregapi.gui.Slot_Normal;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

import static gregapi.data.CS.*;

public class CuttingBoardTileEntity extends TileEntityBase09FacingSingle implements IMultiTileEntity.IMTE_GetSubItems, IMultiTileEntity.IMTE_OnBlockClicked, IMultiTileEntity.IMTE_GetLightOpacity {

    public String mGUITexture = "qwertech:textures/gui/countertopSmall.png";
    public boolean mUpdatedGrid = T;

    @Override
    public void onTick2(long aTimer, boolean aIsServerSide) {
        super.onTick2(aTimer, aIsServerSide);
        if (aIsServerSide) {
            if (mUpdatedGrid) {
                sendDisplays();
                //updateInventory();
                //updateClientData();
                mUpdatedGrid = F;
            } else if (aTimer % (200 + (xCoord % 10) + (zCoord % 10)) == 0)
            {
                sendDisplays();
            }
        }
    }

    public int mTexture = -1;
    public int mMetafy = 0;

    @Override
    public void readFromNBT2(NBTTagCompound aNBT) {
        super.readFromNBT2(aNBT);
        if (aNBT.hasKey(NBT_TEXTURE)) {
            mTexture = aNBT.getInteger(NBT_TEXTURE);
            mMetafy = aNBT.getInteger("qt.metatex");
        }
    }

    public void sendDisplays()
    {
        for (int q = 0; q < this.invsize(); q++)
        {
            QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(slot(q), this.xCoord, this.yCoord, this.zCoord, q), this.worldObj, this.xCoord, this.zCoord);
        }
    }

    @Override public void setInventorySlotContents(int aSlot, ItemStack aStack)		{if (aSlot >= 0 && aSlot < 8 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContents(aSlot, aStack);}
    @Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack)	{if (aSlot >= 0 && aSlot < 8 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContentsGUI(aSlot, aStack);}
    @Override public ItemStack decrStackSize(int aSlot, int aDecrement)				{if (aSlot >= 0 && aSlot < 8 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSize(aSlot, aDecrement);}
    @Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement)			{if (aSlot >= 0 && aSlot < 8 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSizeGUI(aSlot, aDecrement);}


    @Override
    public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
        aList.add(LH.Chat.GRAY + "Prepares meals");
        aList.add(LH.Chat.GRAY + "Holds up to " + LH.Chat.BOLD + "8" + LH.Chat.GRAY + " items");
        super.addToolTips(aList, aStack, aF3_H);
    }

    @Override
    public void onBlockClicked(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
        if (aSide > 1 && openGUI(aPlayer, 0)) return true;
        int aSlot = -1;
        short sided = this.getFacing();
        System.out.println("Facing " + sided);
        switch (sided)
        {
            case CS.SIDE_Z_NEG:
            {
                aHitX = 1 - aHitX;
                break;
            }
            case CS.SIDE_X_POS:
            {
                aHitZ = 1 - aHitZ;
            }
            case CS.SIDE_X_NEG:
            {
                float aTemp = aHitX;
                aHitX = aHitZ;
                aHitZ = aTemp;
                break;
            }
            case CS.SIDE_Z_POS:
            {
                break; //default
            }
        }
        if (aHitX < 0.33)
        {
            if (aHitZ > 0.66)
            {
                aSlot = 0;
            } else if (aHitZ < 0.33)
            {
                aSlot = 2;
            } else {
                aSlot = 1;
            }
        } else if (aHitX > 0.66)
        {
            if (aHitZ > 0.66)
            {
                aSlot = 5;
            } else if (aHitZ < 0.33)
            {
                aSlot = 7;
            } else {
                aSlot = 6;
            }
        } else {
            if (aHitZ > 0.66)
            {
                aSlot = 3;
            } else if (aHitZ < 0.33)
            {
                aSlot = 4;
            }
        }
        if (aSlot > -1)
        {
            ItemStack item = getStackInSlot(aSlot);
            ItemStack held = aPlayer.getHeldItem();
            if (held != null && ST.equal(item, held))
            {
                while (item.stackSize < item.getMaxStackSize() && held.stackSize > 0)
                {
                    item.stackSize = item.stackSize + 1;
                    held.stackSize = held.stackSize - 1;
                }
                if (held.stackSize < 1) {
                    aPlayer.setCurrentItemOrArmor(0, null);
                }
                QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(item, this.xCoord, this.yCoord, this.zCoord, aSlot), this.worldObj, this.xCoord, this.zCoord);
                return true;
            } else if (held == null && item != null)
            {
                aPlayer.setCurrentItemOrArmor(0, item);
                setInventorySlotContents(aSlot, null);
                QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(null, this.xCoord, this.yCoord, this.zCoord, aSlot), this.worldObj, this.xCoord, this.zCoord);
                return true;
            } else if (held != null && item == null)
            {
                aPlayer.setCurrentItemOrArmor(0, null);
                setInventorySlotContents(aSlot, held);
                QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(held, this.xCoord, this.yCoord, this.zCoord, aSlot), this.worldObj, this.xCoord, this.zCoord);
                return true;
            }
        }
        if (aPlayer.getHeldItem() == null && openGUI(aPlayer, 0)) return true;
        return false;
    }

    @Override
    public boolean allowCovers(byte side)
    {
        return false;
    }

    @Override
    public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem,
                               CreativeTabs aTab, List aList, short aID) {
        return SHOW_HIDDEN_MATERIALS || !mMaterial.mHidden;
    }

    @Override
    public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        if (mMaterial.contains(TD.Properties.STONE)) {
            return BlockTextureDefault.get(((BlockStones)BlocksGT.stones[mTexture]).mIcons[mMetafy], UT.Code.getRGBInt(255, 255, 255));
        } else {
            IIconContainer returnable = aRenderPass == 0 ? icons1[aSide < 2 ? 0 : 1] : icons2[aSide < 2 ? 0 : 1];
            return BlockTextureDefault.get(returnable, mRGBa);
        }
    }

    // Icons
    public static String texture_Dir(){
        return "qwertech:cooking/";
    }

    public static IIconContainer icons1[] = new IIconContainer[] {
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "cutboard"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "cutside")
    };
    public static IIconContainer icons2[] = new IIconContainer[] {
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "woodbottom"),
            new Textures.BlockIcons.CustomIcon(texture_Dir() + "woodsides")
    };

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered)
    {
        return 2;
    }


    @Override
    public boolean canDrop(int i) {
        return i < 8;
    }

    @Override
    public String getTileEntityName() {
        return "qt.cooking.simple";
    }

    @SideOnly(Side.CLIENT)
    @Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new GUIClientCuttingBoard(aPlayer.inventory, this);}
    @Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer)
    {
        return new GUICommonCuttingBoard(aPlayer.inventory, this);
    }

    @Override
    public boolean isSurfaceOpaque2	(byte aSide)
    {return aSide == CS.SIDE_UP || aSide == CS.SIDE_DOWN;}

    @SideOnly(Side.CLIENT)
    @Override
    public boolean setBlockBounds2(Block block, int aRenderPass, boolean[] aShouldSideBeRendered) {
        short xneg = 1, xpos = 15, zneg = 1, zpos = 15;
        if (this.getOpacityAtSide(CS.SIDE_X_NEG) || (this.getAdjacentTileEntity(CS.SIDE_X_NEG).exists() && this.getAdjacentTileEntity(CS.SIDE_X_NEG).mTileEntity.getClass() == this.getClass()))
        {
            xneg = 0;
        }
        if (this.getOpacityAtSide(CS.SIDE_X_POS) || (this.getAdjacentTileEntity(CS.SIDE_X_POS).exists() && this.getAdjacentTileEntity(CS.SIDE_X_POS).mTileEntity.getClass() == this.getClass()))
        {
            xpos = 16;
        }
        if (this.getOpacityAtSide(CS.SIDE_Z_NEG) || (this.getAdjacentTileEntity(CS.SIDE_Z_NEG).exists() && this.getAdjacentTileEntity(CS.SIDE_Z_NEG).mTileEntity.getClass() == this.getClass()))
        {
            zneg = 0;
        }
        if (this.getOpacityAtSide(CS.SIDE_Z_POS) || (this.getAdjacentTileEntity(CS.SIDE_Z_POS).exists() && this.getAdjacentTileEntity(CS.SIDE_Z_POS).mTileEntity.getClass() == this.getClass()))
        {
            zpos = 16;
        }
        switch (aRenderPass) {
            case 0:
                block.setBlockBounds(PX_P[0], PX_P[10], PX_P[0], PX_P[16], PX_P[16], PX_P[16]);
                break;
            case 1:
                block.setBlockBounds(PX_P[xneg], PX_P[0], PX_P[zneg], PX_P[xpos], PX_P[10], PX_P[zpos]);
                break;
        }
        return true;
    }

    public class GUICommonCuttingBoard extends ContainerCommon {

        public InventoryScroll craftResults;
        public ArrayList<CountertopRecipe> currentRecipes = new ArrayList<CountertopRecipe>();

        public GUICommonCuttingBoard(InventoryPlayer aInventoryPlayer, CuttingBoardTileEntity aTileEntity) {
            super(aInventoryPlayer, aTileEntity);
            onCraftMatrixChanged(aTileEntity);
        }



        @Override
        public void onContainerClosed(EntityPlayer p_75134_1_)
        {
            super.onContainerClosed(p_75134_1_);
        }

        @Override
        public int addSlots(InventoryPlayer aInventoryPlayer) {
            if (craftResults == null) {
                craftResults = new InventoryScroll(this, 5, 3);
            }
            /*
            addSlotToContainer(new Slot(craftMatrix,  0,  8, 14));
            addSlotToContainer(new Slot(craftMatrix,  1, 26, 14));
            addSlotToContainer(new Slot(craftMatrix, 2, 44, 14));
            addSlotToContainer(new Slot(craftMatrix, 3, 62, 14));
            addSlotToContainer(new Slot(craftMatrix, 4,  8, 32));
            addSlotToContainer(new Slot(craftMatrix, 5, 26, 32));
            addSlotToContainer(new Slot(craftMatrix, 6, 44, 32));
            addSlotToContainer(new Slot(craftMatrix, 7, 62, 32));
            addSlotToContainer(new Slot(craftMatrix, 8,  8, 50));
            addSlotToContainer(new Slot(craftMatrix, 9, 26, 50));
            addSlotToContainer(new Slot(craftMatrix, 10, 44, 50));
            addSlotToContainer(new Slot(craftMatrix, 11, 62, 50));
            addSlotToContainer(new Slot(craftMatrix, 12,  8, 68));
            addSlotToContainer(new Slot(craftMatrix, 13, 26, 68));
            addSlotToContainer(new Slot(craftMatrix, 14, 44, 68));
            addSlotToContainer(new Slot(craftMatrix, 15, 62, 68));
            */
            addSlotToContainer(new Slot_Normal(mTileEntity, 0, 10, 29));
            addSlotToContainer(new Slot_Normal(mTileEntity, 1, 32, 29));
            addSlotToContainer(new Slot_Normal(mTileEntity, 2, 54, 29));
            addSlotToContainer(new Slot_Normal(mTileEntity, 3, 10, 51));
            addSlotToContainer(new Slot_Normal(mTileEntity, 4, 54, 51));
            addSlotToContainer(new Slot_Normal(mTileEntity, 5, 10, 73));
            addSlotToContainer(new Slot_Normal(mTileEntity, 6, 32, 73));
            addSlotToContainer(new Slot_Normal(mTileEntity, 7, 54, 73));

            addSlotToContainer(new SlotScroll(craftResults, 0, 116, 14, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 1, 134, 14, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 2, 152, 14, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 3, 116, 32, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 4, 134, 32, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 5, 152, 32, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 6, 116, 50, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 7, 134, 50, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 8, 152, 50, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 9, 116, 68, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 10, 134, 68, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 11, 152, 68, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 12, 116, 86, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 13, 134, 86, F, F, 64));
            addSlotToContainer(new SlotScroll(craftResults, 14, 152, 86, F, F, 64));

            addSlotToContainer(new Slot_Holo(mTileEntity, 8, 31, 50, F, F, 1).setTooltip("Dump to Inventory", LH.Chat.WHITE));

            return super.addSlots(aInventoryPlayer);
        }

        public ItemStack consume(CountertopRecipe recipe)
        {
            List<ItemStack> subby = this.inventoryItemStacks.subList(0, 8);
            if (!((CuttingBoardTileEntity)mTileEntity).isServerSide() || !recipe.matchesLists(subby, currentRecipes)) {
                return null;
            }
            ItemStack toReturn = recipe.getCraftingResultList(subby);
            System.out.println("Returnable is " + toReturn);
            Integer[] slots = recipe.getRecipeSlotsUsed();
            ArrayList<CountertopRecipe> recipes = new ArrayList<CountertopRecipe>();
            if (slots != null && slots.length > 0) {
                for (int w = 0; w < slots.length; w++) {
                    if (slots[w] > 0) {
                        for (int e = 0; e < slots[w]; e++)
                        {
                            recipes.add(currentRecipes.get(w));
                        }
                    }
                }
                for (int q = 0; q < recipes.size(); q++) {
                    CountertopRecipe intermediate = recipes.get(q);
                    ItemStack product = consume(intermediate);
                    for (int o = 0; o < 8; o++)
                    {
                        Slot slotty = (Slot)this.inventorySlots.get(o);
                        if (slotty.getHasStack())
                        {
                            if (ST.equal(slotty.getStack(), product) && slotty.getStack().stackSize + product.stackSize <= product.getMaxStackSize())
                            {
                                product.stackSize = product.stackSize + slotty.getStack().stackSize;
                                slotty.putStack(product);
                                detectAndSendChanges();
                                break;
                            }
                        } else {
                            slotty.putStack(product);
                            detectAndSendChanges();
                            break;
                        }
                    }
                }
            }
            if (recipe.matchesLists(this.inventoryItemStacks.subList(0, 8), currentRecipes)) {
                slots = recipe.getInputSlotsUsed();
                for (int e = 0; e < slots.length; e++) {
                    if (slots[e] > 0) {
                        ItemStack stackery = (ItemStack)this.inventoryItemStacks.get(e);
                        if (stackery.getItem() instanceof MultiItemTool)
                        {
                            ((MultiItemTool)stackery.getItem()).doDamage(stackery, ((MultiItemTool)stackery.getItem()).getToolStats(stackery).getToolDamagePerContainerCraft());
                            ((Slot) inventorySlots.get(e)).putStack(stackery);
                        } else if (stackery.getItem().isDamageable())
                        {
                            stackery.setItemDamage(stackery.getItemDamage() + 1);
                            ((Slot) inventorySlots.get(e)).putStack(stackery);
                        } else {
                            ((Slot) inventorySlots.get(e)).decrStackSize(slots[e]);
                        }
                    }
                }
                return toReturn;
            }
            return null;
        }

        @Override
        public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
            //System.out.println("Click made! It's slot " + aSlotIndex);
            if (aSlotIndex == 23)
            {
                for (int q = 0; q < 8; q++)
                {
                    ItemStack stack = mTileEntity.getStackInSlotGUI(q);
                    if (aPlayer.inventory.addItemStackToInventory(stack) || !ST.valid(stack))
                    {
                        mTileEntity.setInventorySlotContentsGUI(q, null);
                    } else {
                        //break;
                    }
                }
                return null;
            }
            if (aSlotIndex > 22 || aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
            //System.out.println("It's one to do stuff with");
            Slot tSlot = ((Slot) inventorySlots.get(aSlotIndex));
            ItemStack tStack = tSlot.getStack();
            if (tStack != null)
            {
                if (aSlotIndex > 7)
                {
                    CountertopRecipe chosenRecipe = currentRecipes.get(craftResults.starting + aSlotIndex - 8);
                    ItemStack toReturn = consume(chosenRecipe);
                    if (toReturn == null) return null;
                    System.out.println("It's a recipe that made " + toReturn.getDisplayName() + " times " + toReturn.stackSize);
                    for (int q = 0; q < 8; q++)
                    {
                        Slot aSlot = ((Slot) inventorySlots.get(q));
                        if (!aSlot.getHasStack())
                        {
                            aSlot.putStack(toReturn);
                            toReturn = null;
                            break;
                        } else
                        {
                            ItemStack exisStack = aSlot.getStack();
                            if (ST.equal(exisStack, toReturn) && exisStack.stackSize + toReturn.stackSize <= exisStack.getMaxStackSize())
                            {
                                exisStack.stackSize = exisStack.stackSize + toReturn.stackSize;
                                aSlot.putStack(exisStack);
                                toReturn = null;
                                break;
                            }
                        }
                    }
                    if (toReturn != null && ((CuttingBoardTileEntity)this.mTileEntity).isServerSide()) {
                        aPlayer.entityDropItem(toReturn, 0);
                    }

                    detectAndSendChanges();
                    return toReturn;
                } else
                {
                    if (tStack.stackSize <= 0) {
                        tSlot.putStack(null);
                        ItemStack toReturn = super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
                        //if (aSlotIndex < 8) {
                        mTileEntity.setInventorySlotContentsGUI(aSlotIndex, toReturn);
                        //}
                        detectAndSendChanges();
                        return toReturn;
                    }
                }
            }
            ItemStack returner = super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
            onCraftMatrixChanged((CuttingBoardTileEntity)mTileEntity);
            //detectAndSendChanges();
            return returner;
        }

        @Override
        public void detectAndSendChanges() {
            super.detectAndSendChanges();
        }

        @Override
        public void onCraftMatrixChanged(IInventory par1IInventory)
        {
            List<ItemStack> give = new ArrayList<ItemStack>();
            for (int q = 0; q < par1IInventory.getSizeInventory(); q++)
            {
                ItemStack stack = par1IInventory.getStackInSlot(q);
                if (ST.valid(stack))
                {
                    give.add(stack);
                }
            }
            currentRecipes = CraftingManagerCountertop.getInstance().findMatchingRecipes(give, worldObj);
            ArrayList<ItemStack> results = new ArrayList<ItemStack>();
            for (CountertopRecipe recipe: currentRecipes) {
                results.add(recipe.getCraftingResultList(give));
            }
            craftResults.setList(results);
            super.onCraftMatrixChanged(par1IInventory);
        }

        @Override
        public int getSlotCount() {
            return 23;
        }

        @Override
        public int getShiftClickSlotCount() {
            return 8;
        }

        @Override
        public int getShiftClickStartIndex() {
            return 0;
        }

        @Override
        public int getStartIndex() {
            return 0;
        }

        @Override
        protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer, int aOffset) {
            for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, (aOffset + i * 18) + 32));
            }
            for (int i = 0; i < 9; i++) {
                addSlotToContainer(new Slot(aInventoryPlayer, i, 8 + i * 18, aOffset + 90));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public class GUIClientCuttingBoard extends ContainerClient {

        private int upButtonCounter = 0;
        private int downButtonCounter = 0;
        private boolean[] isAvailable = new boolean[15];
        private ItemStack[] cached = new ItemStack[8];
        public GUIClientCuttingBoard(InventoryPlayer aInventoryPlayer, CuttingBoardTileEntity aTileEntity) {
            super(new GUICommonCuttingBoard(aInventoryPlayer, aTileEntity), aTileEntity.mGUITexture);
            this.xSize = 176;
            this.ySize = 198;
        }

        @Override
        protected void mouseClicked(int x, int y, int mB) {
            super.mouseClicked(x, y, mB);
            //System.out.println("Mouse clicked at " + x + ", " + y);
            if (x > getLeft() + 101 && x < getLeft() + 113)
            {
                if (y > getTop() + 14 && y < getTop() + 21)
                {
                    upButtonCounter = 5;
                    ((GUICommonCuttingBoard)mContainer).craftResults.scrollDown(1);
                } else if (y > getTop() + 95 && y < getTop() + 102)
                {
                    downButtonCounter = 5;
                    ((GUICommonCuttingBoard)mContainer).craftResults.scrollUp(1);
                }
            }
            updateNotifications();
        }

        public void updateNotifications()
        {
            //the can-we-do-it icons
            for (int w = 0; w < 15; w++)
            {
                try {
                    GUICommonCuttingBoard GUI = ((GUICommonCuttingBoard) this.inventorySlots);
                    if (GUI.currentRecipes.size() > w) {
                        CountertopRecipe recipe = GUI.currentRecipes.get(w + GUI.craftResults.starting);
                        if (recipe != null) {
                            if (recipe.matchesLists(GUI.inventoryItemStacks.subList(0, 8), GUI.currentRecipes)) {
                                isAvailable[w] = true;
                            } else {
                                isAvailable[w] = false;
                            }
                        } else {
                            isAvailable[w] = false;
                        }
                    }
                } catch (Exception e) {

                }
            }
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            super.drawGuiContainerBackgroundLayer(par1, par2, par3);
            GUICommonCuttingBoard GUI = ((GUICommonCuttingBoard) this.inventorySlots);
            if (!Arrays.equals(((CuttingBoardTileEntity)GUI.mTileEntity).getInventory(), cached))
            {
                updateNotifications();
                cached = ((CuttingBoardTileEntity)GUI.mTileEntity).getInventory().clone();
            }
        }



        @Override
        public void drawScreen(int aX, int aY, float par3) {
            super.drawScreen(aX, aY, par3);
            this.zLevel = 0.0F;
        }

        @Override
        protected void renderToolTip(ItemStack stack, int x, int y) {
            System.out.println(x + "; " + y);
            GUICommonCuttingBoard CB = (GUICommonCuttingBoard)this.mContainer;
            if (x > this.getLeft() + 100 && y < this.getTop() + 100)
            {
                //List list = stack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

                List list = new ArrayList();
                list.add(stack.getRarity().rarityColor + stack.getDisplayName());

                CountertopRecipe recipe = CB.currentRecipes.get(CB.craftResults.starting);
                List<Object> input = recipe.getInputList();
                HashMap<String, Integer> amounts = new HashMap<String, Integer>();
                for (int q = 0; q < input.size(); q++)
                {
                    Object ob = input.get(q);
                    if (ob instanceof ItemStack)
                    {
                        amounts.put(((ItemStack)ob).getDisplayName(), amounts.get(((ItemStack)ob).getDisplayName()) + ((ItemStack)ob).stackSize);
                    } else if (ob instanceof ArrayList && ((ArrayList)ob).size() > 0)
                    {
                        ItemStack IS = ((ArrayList<ItemStack>)ob).get(0);
                        amounts.put(IS.getDisplayName(), amounts.get(IS.getDisplayName()) + IS.stackSize);
                    } else if (ob instanceof String)
                    {
                        amounts.put((String)ob, amounts.get((String)ob + 1));
                    }
                }
                Iterator iterable = amounts.entrySet().iterator();
                while (iterable.hasNext())
                {
                    Map.Entry entry = (Map.Entry)iterable.next();
                    list.add((String)entry.getKey() + ": " + (Integer)entry.getValue());
                }

                FontRenderer font = stack.getItem().getFontRenderer(stack);
                //drawHoveringText(list, x, y, (font == null ? fontRendererObj : font));
            } else {
                super.renderToolTip(stack, x, y);
            }
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int par1, int par2) {
            super.drawGuiContainerForegroundLayer(par1, par2);
            this.mc.getTextureManager().bindTexture(this.mBackground);

            //the scrollbar
            drawTexturedModalRect(101, 21 + Math.round(41 * ((GUICommonCuttingBoard)mContainer).craftResults.getScroll()), 176, 0,12, 15);

            //the topbutton
            if (upButtonCounter > 0)
            {
                upButtonCounter--;
                drawTexturedModalRect(101, 14, 176, 29, 12, 7);
            } else {
                drawTexturedModalRect(101, 14, 176, 15, 12, 7);
            }

            //the bottombutton
            if (downButtonCounter > 0)
            {
                downButtonCounter--;
                drawTexturedModalRect(101, 95, 176, 36, 12, 7);
            } else {
                drawTexturedModalRect(101, 95, 176, 22, 12, 7);
            }

            this.mc.getTextureManager().bindTexture(this.mBackground);
            this.zLevel = 201.0F;
            for (int x = 0; x < 8; x++)
            {
                Slot slot = (Slot)this.inventorySlots.inventorySlots.get(8 + x);
                if (this.inventorySlots.inventoryItemStacks.get(8 + x) != null) {
                    if (isAvailable[x]) {
                        drawTexturedModalRect(slot.xDisplayPosition, slot.yDisplayPosition, 188, 0, 16, 16);
                    } else {
                        drawTexturedModalRect(slot.xDisplayPosition, slot.yDisplayPosition, 188, 16, 16, 16);
                    }
                }
            }
            this.zLevel = 0.0F;
        }
    }
}
