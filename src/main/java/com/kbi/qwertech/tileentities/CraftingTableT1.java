package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import com.kbi.qwertech.network.packets.PacketInventorySync;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetLightOpacity;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSubItems;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnBlockClicked;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.CS;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Holo;
import gregapi.gui.Slot_Normal;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.oredict.OreDictManager;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.machines.ITileEntityAnvil;
import gregapi.util.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

public class CraftingTableT1 extends TileEntityBase09FacingSingle implements ITileEntityAnvil, IMTE_GetSubItems, IMTE_OnBlockClicked, IMTE_GetLightOpacity	 {

	public String mGUITexture = "qwertech:textures/gui/CraftingTableT1.png";
	public boolean mUpdatedGrid = T;
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mUpdatedGrid) {
				getCraftingOutput();
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
	
	@Override
	public int getLightOpacity()
	{
		return CS.LIGHT_OPACITY_WATER;
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity)
	{
		return true;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ)
	{
		Block aBlock = aWorld.getBlock(aX, aY + 1, aZ);
		if (aBlock.canBeReplacedByLeaves(aWorld, aX, aY + 1, aZ))
		{
			WD.set(aWorld, aX, aY + 1, aZ, QwerTech.machines.getItem(400));
		}
		return super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
	}
	
	GUICommonAdvancedCraftingTable blargh;
	
	public void sendDisplays()
	{
		for (int q = 0; q < this.invsize(); q++)
		{
			QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(slot(q), this.xCoord, this.yCoord, this.zCoord, q), this.worldObj, this.xCoord, this.zCoord);
		}
	}
	
	@Override public void setInventorySlotContents(int aSlot, ItemStack aStack)		{if (aSlot >= 0 && aSlot < 9 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContents(aSlot, aStack);}
	@Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack)	{if (aSlot >= 0 && aSlot < 9 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContentsGUI(aSlot, aStack);}
	@Override public ItemStack decrStackSize(int aSlot, int aDecrement)				{if (aSlot >= 0 && aSlot < 9 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSize(aSlot, aDecrement);}
	@Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement)			{if (aSlot >= 0 && aSlot < 9 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSizeGUI(aSlot, aDecrement);}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(CS.TOOL_hammer)) {
			if (isClientSide())
			{ 
				if (canDoHammerOutput())
				{
					this.worldObj.spawnParticle("fireworksSpark", this.getX() + 0.6, this.getY() + 1, this.getZ() + 0.6, CS.RANDOM.nextDouble() * 0.1D, 0.1D, CS.RANDOM.nextDouble() * 0.1D);
					this.worldObj.spawnParticle("fireworksSpark", this.getX() + 0.6, this.getY() + 1, this.getZ() + 0.4, CS.RANDOM.nextDouble() * -0.1D, 0.1D, CS.RANDOM.nextDouble() * -0.1D);
				}
				return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
			}
			long returnit = this.handleHammer(aPlayer, aStack);
			this.causeBlockUpdate();
			return returnit;
		}
		if (aSide != CS.SIDE_UP)
		{
			return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		}
		return 0;
	}
	
	public List<AxisAlignedBB> currentBoxes;
	
	@SideOnly(Side.CLIENT)
	public void drawBoundingBox(EntityPlayer entity, AxisAlignedBB[] boxes, float partialTick, RenderGlobal context, int color)
	{
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        float f1 = 0.002F;

        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTick;
        double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTick;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTick;
        for (int q = 0; q < boxes.length; q++)
        {
	        AxisAlignedBB box2 = boxes[q].expand(f1, f1, f1).getOffsetBoundingBox(-d0, -d1, -d2);
	        //System.out.println("Drawing " + box2.minX + "x to " + box2.maxX + "x, " + box2.minY + "y to " + box2.maxY + ", " + box2.minZ + "z to " + box2.maxZ);
	        RenderGlobal.drawOutlinedBoundingBox(box2, color);
        }


        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
	}
	
	@Override
	public boolean addDefaultCollisionBoxToList()
	{
		return true;
	}
	
	public void generateBoundingBoxes()
	{
		List<AxisAlignedBB> aList = new ArrayList();
		AxisAlignedBB newBox = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
		boolean sideways = false;
		double[] dists = new double[]{0.2, 0.4, 0.4, 0.6, 0.6, 0.8};
		if (this.mFacing == CS.SIDE_Y_POS || this.mFacing == CS.SIDE_X_POS || this.mFacing == CS.SIDE_X_NEG)
		{
			sideways = true;
		}
		
		if (this.mFacing == CS.SIDE_Y_NEG || this.mFacing == CS.SIDE_X_NEG || this.mFacing == CS.SIDE_Z_POS)
		{
			dists[0] = 0.6;
			dists[1] = 0.8;
			dists[2] = 0.4;
			dists[3] = 0.6;
			dists[4] = 0.2;
			dists[5] = 0.4;
		}
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[0], yCoord + 1.05, sideways  ? zCoord + dists[0] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[1], yCoord + 1.25, sideways ? zCoord + dists[1] : zCoord + 0.6);
		aList.add(newBox.copy());
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[2], yCoord + 1.05, sideways  ? zCoord + dists[2] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[3], yCoord + 1.25, sideways ? zCoord + dists[3] : zCoord + 0.6);
		aList.add(newBox.copy());
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[4], yCoord + 1.05, sideways  ? zCoord + dists[4] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[5], yCoord + 1.25, sideways ? zCoord + dists[5] : zCoord + 0.6);
		aList.add(newBox.copy());
		
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[0], yCoord + 1.25, sideways  ? zCoord + dists[0] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[1], yCoord + 1.45, sideways ? zCoord + dists[1] : zCoord + 0.6);
		aList.add(newBox.copy());
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[2], yCoord + 1.25, sideways  ? zCoord + dists[2] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[3], yCoord + 1.45, sideways ? zCoord + dists[3] : zCoord + 0.6);
		aList.add(newBox.copy());
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[4], yCoord + 1.25, sideways  ? zCoord + dists[4] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[5], yCoord + 1.45, sideways ? zCoord + dists[5] : zCoord + 0.6);
		aList.add(newBox.copy());
		
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[0], yCoord + 1.45, sideways  ? zCoord + dists[0] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[1], yCoord + 1.65, sideways ? zCoord + dists[1] : zCoord + 0.6);
		aList.add(newBox.copy());
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[2], yCoord + 1.45, sideways  ? zCoord + dists[2] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[3], yCoord + 1.65, sideways ? zCoord + dists[3] : zCoord + 0.6);
		aList.add(newBox.copy());
		newBox.setBounds(sideways ? xCoord + 0.4 : xCoord + dists[4], yCoord + 1.45, sideways  ? zCoord + dists[4] : zCoord + 0.4, sideways ? xCoord + 0.6 : xCoord + dists[5], yCoord + 1.65, sideways ? zCoord + dists[5] : zCoord + 0.6);
		aList.add(newBox.copy());
		currentBoxes = new ArrayList(9);
		for (int q = 0; q < aList.size(); q++)
		{
			currentBoxes.add(aList.get(aList.size() - 1 - q));
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return getCollisionBoundingBoxFromPool();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool()
	{
		return this.box(new float[]{0, 0, 0, 1, 1, 1});
		//return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 2, zCoord);
	}
	
	@Override
	public boolean checkObstruction(EntityPlayer player, byte aSide, float aHitX, float aHitY, float aHitZ)
	{
		return false;
	}
	
	@Override
	public boolean getOpacity(int aX, int aY, int aZ)
	{
		return false;
	}
	
	public int getIntersectedBox(Vec3 startPos, Vec3 endPos, boolean sneaky)
	{
		if (currentBoxes == null || currentBoxes.size() <= 0)
		{
			generateBoundingBoxes();
		}
		
		Vec3 dif = Vec3.createVectorHelper(endPos.xCoord - startPos.xCoord, endPos.yCoord - startPos.yCoord, endPos.zCoord - startPos.zCoord);
		while (Math.abs(dif.xCoord) + Math.abs(dif.yCoord) + Math.abs(dif.zCoord) < 5)
		{
			dif = Vec3.createVectorHelper(dif.xCoord * 2, dif.yCoord * 2, dif.zCoord * 2);
		}
		endPos = endPos.addVector(dif.xCoord, dif.yCoord, dif.zCoord);
		
		if (currentBoxes != null && currentBoxes.size() > 0)
		{
			AxisAlignedBB primaryBox = null;
			Vec3 theCenter = null;
			boolean isSlot = false;
			int returnit = -1;
			for (int q = 0; q < currentBoxes.size(); q++)
			{
				AxisAlignedBB checkBox = currentBoxes.get(q);
				if (checkBox.calculateIntercept(startPos, endPos) != null)
				{
					if (primaryBox != null)
					{
						Vec3 center = Vec3.createVectorHelper(checkBox.maxX - 0.1, checkBox.maxY - 0.1, checkBox.maxZ - 0.1);
						if (startPos.distanceTo(center) < startPos.distanceTo(theCenter))
						{
							if (slotHas(q))
							{
								primaryBox = checkBox;
								theCenter = center;
								isSlot = true;
								returnit = q;
							} else if (!sneaky && isSlot)
							{
								primaryBox = checkBox;
								theCenter = center;
								isSlot = false;
								returnit = q;
							}
						} else if (!isSlot && sneaky)
						{
							primaryBox = checkBox;
							theCenter = center;
							isSlot = true;
							returnit = q;
						}
					} else {
						primaryBox = currentBoxes.get(q);
						theCenter = Vec3.createVectorHelper(primaryBox.maxX - 0.1, primaryBox.maxY - 0.1, primaryBox.maxZ - 0.1);
						isSlot = slotHas(q);
						returnit = q;
					}
				}
			}
			return returnit;
		}
		return -1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean onDrawBlockHighlight2(DrawBlockHighlightEvent event)
	{	
		if (event.player.getHeldItem() != null && OreDictManager.isItemStackInstanceOf(event.player.getHeldItem(), "craftingToolHardHammer"))
		{
			return false;
		}
		Vec3 startPos = event.player.getPosition(event.player.isSneaking() ? 0.75F : 1F);
		Vec3 endPos = event.target.hitVec;
		if (currentBoxes != null && currentBoxes.size() > 0)
		{
			AxisAlignedBB[] allOfThem = new AxisAlignedBB[currentBoxes.size()];
			for (int q = 0; q < currentBoxes.size(); q++)
			{
				allOfThem[q] = currentBoxes.get(q);
			}
			drawBoundingBox(event.player, allOfThem, event.partialTicks, event.context, -1);
		}
		int spot = getIntersectedBox(startPos, endPos, event.player.isSneaking());
		if (spot > -1)
		{
			AxisAlignedBB box = currentBoxes.get(spot);
			drawBoundingBox(event.player, new AxisAlignedBB[]{box}, event.partialTicks, event.context, 39168);
			event.setCanceled(true);
		}
		return true;
	}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List bList, Entity aEntity)
	{
			//aAABB.setBounds(aAABB.minX, aAABB.minY, aAABB.minZ, aAABB.maxX, aAABB.maxY + 1, aAABB.maxZ);
			//bList.add(aAABB);
	}
	
	public long handleHammer(Entity player, ItemStack stack)
	{
		this.mUpdatedGrid = true;
		this.worldObj.func_147480_a(this.xCoord, this.yCoord, this.zCoord, true);
		return 12500;
	}
	
	public IRecipe sLastRecipe = null;
	public boolean isRepair = false;
	
	public ItemStack getHammeringOutput(World world, ItemStack... aRecipe)
	{
		List<IRecipe> list = CraftingManagerHammer.getInstance().getRecipeList();
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer var1) {return F;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		
		for (int i = 0; i < list.size(); i++)
		{
			IRecipe check = list.get(i);
			if (check.matches(aCrafting, worldObj))
			{
				isRepair = check instanceof RepairRecipe;
				return check.getCraftingResult(aCrafting);
			}
		}
		return null;
	}
	
	public ItemStack getCraftingOutput()
	{
		ItemStack mainReturnable = CR.getany(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8));	
		ItemStack returnable = getHammeringOutput(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8));
		
		slot(10, returnable);
		//if (!ST.equal(slot(10), returnable, F)) mUpdatedGrid = T;
		return slot(9, mainReturnable);
		//return slot(9, CR.getany(worldObj, slot(0), slot(1), slot(2), slot(3), slot(4), slot(5), slot(6), slot(7), slot(8)));
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		addHammerToolTip(aList, aStack);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public void addHammerToolTip(List aList, ItemStack aStack)
	{
		aList.add(Chat.CYAN	+ "T1: " + Chat.PINK + "Will break if hammered!");
	}

	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem,
			CreativeTabs aTab, List aList, short aID) {
		return SHOW_HIDDEN_MATERIALS || !mMaterial.mHidden;
	}
	

	public IIconContainer[] primary;
	public IIconContainer[] overlay;
	
	@Override
	public boolean allowCovers(byte side)
	{
		return side >= 2;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass)
	{
		return pass == 0;
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
				this.primary = CraftingTableT1.sColoreds;
				this.overlay = CraftingTableT1.sOverlays;
			} else if(OP.ingot.canGenerateItem(this.mMaterial))
			{
				this.primary = CraftingTableT1.sMetals;
				this.overlay = CraftingTableT1.sOvermetals;
			} else if(OP.gem.canGenerateItem(this.mMaterial))
			{
				this.primary = CraftingTableT1.sGems;
				this.overlay = CraftingTableT1.sOvergems;
			} else {
				this.primary = CraftingTableT1.sRocks;
				this.overlay = CraftingTableT1.sOverrocks;
			}
		}
		return BlockTextureMulti.get(BlockTextureDefault.get(primary[aIndex], mRGBa), BlockTextureDefault.get(overlay[aIndex]));
	}
	
	// Icons
	public static String texture_Dir(){
		return "qwertech:craftingtables/t1/";
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

	@Override
	public boolean canDrop(int aSlot) {
		if (aSlot < 9) return T;
		return F;
	}

	@Override
	public String getTileEntityName() {
		// TODO Auto-generated method stub
		return "qt.crafting.tier1";
	}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new GUIClientAdvancedCraftingTable(aPlayer.inventory, this);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) 
	{
		return new GUICommonAdvancedCraftingTable(aPlayer.inventory, this);
	}
	
	//@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[aNBT.getInteger(CS.NBT_INV_SIZE)];}
	public boolean interpretClick(EntityPlayer aPlayer, boolean isSneaking, boolean isRightclick)
	{
		Vec3 eyeSpot = Vec3.createVectorHelper(aPlayer.posX, aPlayer.posY + (aPlayer.isSneaking() ? aPlayer.getEyeHeight() * 0.9F : aPlayer.getEyeHeight()), aPlayer.posZ);
		Vec3 lookVec = aPlayer.getLookVec();
		Vec3 endsUpAt = eyeSpot.addVector(lookVec.xCoord * 2, lookVec.yCoord * 2, lookVec.zCoord * 2);
		//System.out.println("Trying to go from " + eyeSpot.xCoord + "x " + eyeSpot.yCoord + "y " + eyeSpot.zCoord + "z to " + endsUpAt.xCoord + "x " + endsUpAt.yCoord + "y " + endsUpAt.zCoord + "z");
		int leSlot = getIntersectedBox(eyeSpot, endsUpAt, aPlayer.isSneaking());
		if (leSlot > -1)
		{
			AxisAlignedBB leBox = currentBoxes.get(leSlot);
			ItemStack inHand = aPlayer.getHeldItem();
			ItemStack inTable = slot(leSlot);
			if (isRightclick)
			{
				if (isSneaking)
				{
					setInventorySlotContents(leSlot, inHand);
					QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(inHand, this.xCoord, this.yCoord, this.zCoord, leSlot), this.worldObj, this.xCoord, this.zCoord);
					aPlayer.setCurrentItemOrArmor(0, inTable);
				} else {
					if (inTable == null)
					{
						setInventorySlotContents(leSlot, ST.amount(1, inHand));
						QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(ST.amount(1, inHand), this.xCoord, this.yCoord, this.zCoord, leSlot), this.worldObj, this.xCoord, this.zCoord);
						inHand.stackSize = inHand.stackSize - 1;
						if (inHand.stackSize < 1)
						{
							inHand = null;
						}
						aPlayer.setCurrentItemOrArmor(0, inHand);
					} else if (inHand == null)
					{
						aPlayer.setCurrentItemOrArmor(0, ST.amount(1, inTable));
						inTable.stackSize = inTable.stackSize - 1;
						if (inTable.stackSize < 1)
						{
							inTable = null;
						}
						setInventorySlotContents(leSlot, inTable);
						QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(inTable, this.xCoord, this.yCoord, this.zCoord, leSlot), this.worldObj, this.xCoord, this.zCoord);
					} else if (ST.equal(inHand, inTable) && inTable.stackSize < inTable.getItem().getItemStackLimit(inTable))
					{
						inTable.stackSize = inTable.stackSize + 1;
						inHand.stackSize = inHand.stackSize - 1;
						if (inHand.stackSize < 1)
						{
							inHand = null;
						}
						setInventorySlotContents(leSlot, inTable);
						QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(inTable, this.xCoord, this.yCoord, this.zCoord, leSlot), this.worldObj, this.xCoord, this.zCoord);
						aPlayer.setCurrentItemOrArmor(0, inHand);
					}
				}
			} else {
				if (isSneaking)
				{
					ItemStack choice = getCraftingOutput();
					int stacker = inHand == null ? 0 : inHand.stackSize;
					if (inHand == null || ST.equal(inHand, choice))
					{
						for (int j = 0; getCraftingOutput() != null && j < (getCraftingOutput().getMaxStackSize() / getCraftingOutput().stackSize) - stacker; j++)
						{
							inHand = consumeMaterials(aPlayer, inHand, j != 0);
						}
					}
					aPlayer.setCurrentItemOrArmor(0, inHand);
				} else {
					if (inHand == null)
					{
						ItemStack returnable = consumeMaterials(aPlayer, aPlayer.getHeldItem(), false);
						if (returnable != null)
						{
							aPlayer.setCurrentItemOrArmor(0, returnable);
						}
					} else if (ST.equal(inHand,getCraftingOutput()) && inHand.stackSize + getCraftingOutput().stackSize < inHand.getItem().getItemStackLimit(inHand))
					{
						ItemStack returnable = consumeMaterials(aPlayer, aPlayer.getHeldItem(), false);
						aPlayer.setCurrentItemOrArmor(0, returnable);
					}
				}
			}
			return true;
		} else {
			return isRightclick && openGUI(aPlayer, 0);
		}
	}
	
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!isServerSide()) return true;
		if (!(this.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof CraftingHelper))
		{
			Block aBlock = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
			if (aBlock.canBeReplacedByLeaves(worldObj, xCoord, yCoord + 1, zCoord))
			{
				WD.set(worldObj, xCoord, yCoord + 1, zCoord, QwerTech.machines.getItem(400));
			} else {
				return false;
			}
		}
		//System.out.println(aHitX +", " + aHitY + ", " + aHitZ);
		return interpretClick(aPlayer, aPlayer.isSneaking(), true);
	}
	
	public boolean canDoCraftingOutput() {
		if (!slotHas(9)) return F;
		return T;
	}
	
	public boolean canDoHammerOutput() {
		if (!slotHas(10)) return F;
		return T;
	}
	
	public ItemStack consumeMaterials(EntityPlayer aPlayer, ItemStack aHoldStack, boolean aSubsequentClick) {
		if (!slotHas(9)) return aHoldStack;
		
		if (aHoldStack != null) {
			if (!ST.equal(aHoldStack, slot(9))) {
				if (!aSubsequentClick) {
					UT.Sounds.play(SFX.MC_HMM, 50, 1.0F, 1.0F, getCoords());
				}
				return aHoldStack;
			}
			if (aHoldStack.stackSize + slot(9).stackSize > aHoldStack.getMaxStackSize()) return aHoldStack;
			for (int i = 0; i < 9; i++) if (OM.is("gt:autocrafterinfinite", slot(i))) {
				if (!aSubsequentClick) {
					UT.Sounds.play(SFX.MC_HMM, 50, 1.0F, 1.0F, getCoords());
				}
				return aHoldStack;
			}
		}
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = getCoords();
		
		try {FMLCommonHandler.instance().firePlayerCraftingEvent(aPlayer, ST.copy(slot(9)), new InventoryCrafting(null, 3, 3));} catch(Throwable e) {e.printStackTrace(ERR);}
		
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
		
		if (aHoldStack == null) aHoldStack = ST.copy(slot(9)); else aHoldStack.stackSize += slot(9).stackSize;
		
		aHoldStack.onCrafting(worldObj, aPlayer, slot(9).stackSize);
		
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
		if (!slotHas(10)) return null;
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = getCoords();
		
		ItemStack aHoldStack;
		
		try {FMLCommonHandler.instance().firePlayerCraftingEvent(aPlayer, ST.copy(slot(10)), new InventoryCrafting(null, 3, 3));} catch(Throwable e) {e.printStackTrace(ERR);}
		
		boolean tOldToolSounds = TOOL_SOUNDS;
		
		for (int i = 0; i < 9; i++) if (slotHas(i)) {
			boolean tNeeds = T;
			TOOL_SOUNDS = isClientSide() && tOldToolSounds && !aSubsequentClick;
			ItemStack tContainer = ST.container(slot(i), F);
			TOOL_SOUNDS = F;
			// Contains itself, so it's an infinite use Container Item anyways.
			if (ST.equal(slot(i), tContainer, F)) continue;

			if (isRepair)
			{
				decrStackSize(i, 1);
			}
			
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
		
		aHoldStack = ST.copy(slot(10));
		
		if (aPlayer != null)
		{
			aHoldStack.onCrafting(worldObj, aPlayer, slot(10).stackSize);
			
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

	@Override
	public boolean isAnvil(byte aSide) {
		return true;
	}

	public class GUICommonAdvancedCraftingTable extends ContainerCommon {
		public GUICommonAdvancedCraftingTable(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
			super(aInventoryPlayer, aTileEntity);
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

			return super.addSlots(aInventoryPlayer);
		}
		
		@Override
		public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
			if (aSlotIndex == 11)
			{
				for (int q = 0; q < 9; q++)
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
			if (aSlotIndex > 10 || aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
			try {
				Slot tSlot = ((Slot)inventorySlots.get(aSlotIndex));
				ItemStack tStack = tSlot.getStack();
				if (tStack != null && tStack.stackSize <= 0) {
					tSlot.putStack(null);
					if (aSlotIndex < 9)
					{
						ItemStack toReturn = super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
						detectAndSendChanges();
						return toReturn;
					}
					return null;
				}
				if (aSlotIndex == 9) {
					ItemStack tCraftedStack = ((CraftingTableT1)mTileEntity).getCraftingOutput();
					if (tCraftedStack != null) {
						if (aShifthold == 1) {
							if (aMouseclick == 0) {
								// SHIFT LEFTCLICK
								for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
									if (aPlayer.inventory.mainInventory[i] == null || ST.equal(aPlayer.inventory.mainInventory[i], tCraftedStack, false)) {
										if (aPlayer.inventory.mainInventory[i] == null || tCraftedStack.stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize()) {
											for (int j = 0; j < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && ((CraftingTableT1) mTileEntity).canDoCraftingOutput(); j++) {
												if (!ST.equal(tStack = ((CraftingTableT1) mTileEntity).getCraftingOutput(), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
													detectAndSendChanges();
													return aPlayer.inventory.getItemStack();
												}
												aPlayer.inventory.mainInventory[i] = (((CraftingTableT1) mTileEntity).consumeMaterials(aPlayer, aPlayer.inventory.mainInventory[i], i != 0 || j != 0));
											}
										}
										if (aPlayer.inventory.mainInventory[i].stackSize < aPlayer.inventory.mainInventory[i].getMaxStackSize()) {
											for (int q = i; q < aPlayer.inventory.mainInventory.length; q++) {
												if (aPlayer.inventory.mainInventory[q] == null || (ST.equal(tCraftedStack, aPlayer.inventory.mainInventory[q]) && tCraftedStack.stackSize + aPlayer.inventory.mainInventory[q].stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize() * 2)) {
													if (!ST.equal(tStack = ((CraftingTableT1) mTileEntity).getCraftingOutput(), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
														detectAndSendChanges();
														return aPlayer.inventory.getItemStack();
													}
													ItemStack splittable = (((CraftingTableT1) mTileEntity).consumeMaterials(aPlayer, null, i != 0));
													this.mergeItemStack(splittable, 0, aPlayer.inventory.mainInventory.length, true);
													break;
												}
											}
										}
									}
								}
								return aPlayer.inventory.getItemStack();
							}
							// SHIFT RIGHTCLICK
							for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
								if (aPlayer.inventory.mainInventory[i] == null || tCraftedStack.stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize()) {
									boolean temp = F;
									for (int j = 0; j < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && ((CraftingTableT1)mTileEntity).canDoCraftingOutput(); j++) {
										if (!ST.equal(tStack = ((CraftingTableT1)mTileEntity).getCraftingOutput(), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
											detectAndSendChanges();
											return aPlayer.inventory.getItemStack();
										}
										ItemStack splittable = (((CraftingTableT1)mTileEntity).consumeMaterials(aPlayer, null, i != 0 || j != 0));
										this.mergeItemStack(splittable, 0, aPlayer.inventory.mainInventory.length, true);
										temp = T;
									}
									if (temp) return aPlayer.inventory.getItemStack();
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
			return 9;
		}
		
		@Override
		public int getShiftClickSlotCount() {
			return 0;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public class GUIClientAdvancedCraftingTable extends ContainerClient {
		public GUIClientAdvancedCraftingTable(InventoryPlayer aInventoryPlayer, CraftingTableT1 aTileEntity) {
			super(new GUICommonAdvancedCraftingTable(aInventoryPlayer, aTileEntity), aTileEntity.mGUITexture);
		}
		
		@Override
		public void drawGuiContainerForegroundLayer(int p1, int p2)
		{
			super.drawGuiContainerForegroundLayer(p1, p2);
			this.drawCenteredString(this.fontRendererObj, "Hammer output", 41, 15, 16777215);
			this.displayHammerable(p1,p2);
		}
		
		public void displayHammerable(int p1, int p2)
		{
			if (this.inventorySlots.getSlot(10).getHasStack())
			{
				this.drawCenteredString(this.fontRendererObj, "TABLE IS", 41, 55, 11010048);
				this.drawCenteredString(this.fontRendererObj, "TOO WEAK", 41, 66, 11010048);
			}
		}
		
	}

	@Override
	public void onBlockClicked(EntityPlayer aPlayer) {
		interpretClick(aPlayer, aPlayer.isSneaking(), false);
	}

}
