package com.kbi.qwertech.tileentities;

import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.armor.upgrades.IArmorUpgrade;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.network.packets.PacketInventorySync;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

public class UpgradeDesk extends TileEntityBase09FacingSingle implements IMTE_GetLightOpacity, IMTE_GetSubItems, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddCollisionBoxesToList, IMTE_GetBlocksMovement, IMTE_SetBlockBoundsBasedOnState	 {

	public boolean mUpdated = T;
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass,
			boolean[] aShouldSideBeRendered) {
		setBlockBoundsBasedOnState(aBlock);
		return true;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool()
	{
		return this.box(new float[]{0, 0, 0, 1, 0.25F, 1});
		//return null;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mUpdated) {
				sendDisplays();
				//updateInventory();
				//updateClientData();
				mUpdated = F;
			} else if (aTimer % (200 + (xCoord % 10) + (zCoord % 10)) == 0)
			{
				sendDisplays();
			}
		}
	}
	
	public void sendDisplays()
	{
		for (int q = 0; q < this.invsize(); q++)
		{
			QTI.NW_API.sendToAllPlayersInRange(new PacketInventorySync(slot(q), this.xCoord, this.yCoord, this.zCoord, q), this.worldObj, this.xCoord, this.zCoord);
		}
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return null;
	}

	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 0;
	}

	@Override
	public boolean canDrop(int aSlot) {
		return true;
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
	public String getTileEntityName() {
		return "qt.desk.upgrade";
	}
	
	@Override
	public boolean getOpacity(int aX, int aY, int aZ)
	{
		return false;
	}
	
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem,
			CreativeTabs aTab, List aList, short aID) {
		return SHOW_HIDDEN_MATERIALS || !mMaterial.mHidden;
	}
	
	public OreDictMaterial getMaterial()
	{
		return this.mMaterial;
	}
	
	@Override
	public boolean allowCovers(byte aSide)
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!isServerSide()) return true;
		if (aSide == CS.SIDE_DOWN) return false;
		ItemStack aStack = aPlayer.getHeldItem();
		ItemStack currentOne = this.slot(0);
		mUpdated = true; //force a refresh
		if (aStack != null)
		{
			if (currentOne != null)
			{
				if (currentOne.getItem() instanceof MultiItemArmor)
				{
					IArmorUpgrade upgrade = ArmorUpgradeRegistry.instance.getUpgrade(aStack);
					if (upgrade != null && upgrade.isCompatibleWith(currentOne))
					{
						MultiItemArmor.addUpgrade(currentOne, ArmorUpgradeRegistry.instance.getUpgradeID(aStack));
						aStack.stackSize = aStack.stackSize - 1;
						aPlayer.setCurrentItemOrArmor(0, aStack.stackSize > 0 ? aStack : null);
					} else {
						UT.Sounds.send(CS.SFX.MC_AHA, this);
						UT.Entities.chat(aPlayer, "You cannot place " + LH.Chat.BLINKING_RED + aStack.getDisplayName() + LH.Chat.WHITE + " on " + currentOne.getDisplayName());
					}
				} else {
					UT.Entities.chat(aPlayer, "You cannot upgrade " + LH.Chat.BLINKING_RED + currentOne.getDisplayName());
					//not armor
				}
			} else {
				this.setInventorySlotContents(0, aStack);
				aPlayer.setCurrentItemOrArmor(0, null);
			}
		} else {
			if (currentOne != null)
			{
				this.setInventorySlotContents(0, null);
				aPlayer.setCurrentItemOrArmor(0, currentOne);
			} else {
				return false; //no item in either hand, do nothing
			}
		}
		return true;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		mUpdated = true;
		slot(slot, stack);
	}

	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List aList, Entity aEntity) {
		//aList.clear();
		//System.out.println(aAABB.minX + ", " + aAABB.minY + ", " + aAABB.minZ);
		//aAABB.setBounds(0, 0, 0, 1, 0., 1);
		AxisAlignedBB newBB = this.box(0,0,0, 1, 0.25, 1);
		if (aAABB != null && aAABB.intersectsWith(newBB))
		{
			aList.add(newBB	);
		}
		if (this.mFacing == CS.SIDE_Z_NEG || this.mFacing == CS.SIDE_Z_POS)
		{
			if (slotHas(0) && slot(0).getItem() instanceof ItemArmor)
			{
				newBB = this.box(0, 0, 0.33, 1, 1.25, 0.66);
			} else {
				newBB = this.box(0,0,0.45, 1, 0.75, 0.55);
			}
			if (aAABB != null && aAABB.intersectsWith(newBB))
			{
				aList.add(newBB	);
			}
		} else {
			if (slotHas(0) && slot(0).getItem() instanceof ItemArmor)
			{
				newBB = this.box(0.33, 0, 0, 0.66, 1.25, 1);
			} else {
				newBB = this.box(0.45,0,0, 0.55, 0.75, 1);
			}
			if (aAABB != null && aAABB.intersectsWith(newBB))
			{
				aList.add(newBB	);
			}
		}
		//aAABB.setBounds(0, 0, 0.4, 1, 1, 0.6);
		
		//aList.add(this.box(0,0,0.4,1,1,0.6));
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ)
	{
		return super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide == CS.SIDE_Y_NEG || aSide == CS.SIDE_Y_POS ? CS.SIDE_Z_POS : aSide, aHitX, aHitY, aHitZ);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		return this.box(0, 0, 0, 1, 0.25F, 1);
	}

	@Override
	public boolean getBlocksMovement() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		aBlock.setBlockBounds(0, 0, 0, 1, 0.25F, 1);
	}

}
