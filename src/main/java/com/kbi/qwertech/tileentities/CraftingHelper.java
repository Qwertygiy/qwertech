package com.kbi.qwertech.tileentities;

import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase04MultiTileEntities;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.render.ITileEntityOnDrawBlockHighlight;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

import java.util.List;

public class CraftingHelper extends TileEntityBase04MultiTileEntities implements IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetPlayerRelativeBlockHardness, IMTE_SetBlockBoundsBasedOnState, IMTE_OnEntityCollidedWithBlock, IMTE_OnToolClick, IMTE_OnBlockClicked, IMTE_RemovedByPlayer, ITileEntityOnDrawBlockHighlight, IMTE_CanEntityDestroy, IMTE_GetBlocksMovement, IMTE_AddCollisionBoxesToList {

	public CraftingHelper() {
		super();
	}
	
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, float original)	
	{
		return 0F;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target)
	{
		CraftingTableT1 table = getTable();
		target.blockY = target.blockY - 1;
		target.sideHit = 1;
		target.hitVec = Vec3.createVectorHelper(xCoord + 0.5, yCoord, zCoord + 0.5);
		return table.getPickBlock(target);
	}
	
	@Override
	public boolean canUpdate()
	{
		return true;
	}
	
	@Override
	public void doBlockUpdate()
	{
		getTable();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool()
	{
		return this.box(new float[]{0, 0, 0, 0, 0, 0});
	}
	
	@Override
	public boolean isSurfaceSolid(byte aSide)
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		CraftingTableT1 table = getTable();
		if (table != null)
		{
			if (table instanceof CraftingTableT4)
			{
				return this.box(new float[]{0.2F, 0.05F, 0.2F, 0.8F, 0.65F, 0.8F});
			} else {
				byte facing = table.mFacing;
				if (facing == CS.SIDE_X_NEG || facing == CS.SIDE_X_POS)
				{
					return this.box(new float[]{0.4F, 0.05F, 0.2F, 0.6F, 0.65F, 0.8F});
				} else {
					return this.box(new float[]{0.2F, 0.05F, 0.4F, 0.8F, 0.65F, 0.6F});
				}
			}
		}
		return getCollisionBoundingBoxFromPool();
	}
	
	public boolean setToAir()
	{
		return worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air , 0, 3);
	}
	
	public CraftingTableT1 getTable()
	{
		DelegatorTileEntity<TileEntity> te = this.getAdjacentTileEntity(CS.SIDE_BOTTOM);
		if (te == null)
		{
			setToAir();
		} else {
			if (te.mTileEntity instanceof CraftingTableT1)
			{
				return ((CraftingTableT1)te.mTileEntity);
			} else {
				setToAir();
			}
		}
		return null;
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity)
	{
		return getTable() != null && getTable().allowInteraction(aEntity);
	}
	
	@Override
	public boolean allowRightclick(Entity aEntity)
	{
		return getTable() != null && getTable().allowRightclick(aEntity);
	}
	
	@Override
	public boolean shouldRenderInPass(int pass)
	{
		return false; //just don't draw it mmkay
	}

	@Override
	public boolean onDrawBlockHighlight2(DrawBlockHighlightEvent aEvent) {
		CraftingTableT1 table = getTable();
		if (table != null)
		{
			table.onDrawBlockHighlight(aEvent);
		}
		aEvent.setCanceled(true);
		return true;
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return getTable() != null ? getTable().onToolClick(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, CS.SIDE_TOP, aHitX, aHitY, aHitZ) : 0;
	}

	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide,
			float aHitX, float aHitY, float aHitZ) {
			return getTable() != null && getTable().onBlockActivated(aPlayer, CS.SIDE_TOP, aHitX, aHitY, aHitZ);
	}

	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		return new ArrayListNoNulls();
	}

	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide,
			boolean[] aShouldSideBeRendered) {
		return null;
	}

	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass,
			boolean[] aShouldSideBeRendered) {
		aBlock.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.6F, 0.8F);
		return true;
	}

	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 0;
	}

	@Override
	public String getTileEntityName() {
		return "air";
	}

	@Override
	public boolean canEntityDestroy(Entity aEntity) {
		return false;
	}

	@Override
	public boolean getBlocksMovement() {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(Entity aEntity) {
		//it does NOTHING
	}

	@Override
	public void addCollisionBoxesToList(AxisAlignedBB aAABB, List aList,
			Entity aEntity) {
		//aList.clear();
	}

	@Override
	public void onBlockClicked(EntityPlayer aPlayer) {
		if (getTable() != null) getTable().onBlockClicked(aPlayer);
	}

	@Override
	public boolean removedByPlayer(World aWorld, EntityPlayer aPlayer,
			boolean aWillHarvest) {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		CraftingTableT1 table = getTable();
		if (table != null)
		{
			if (table instanceof CraftingTableT4)
			{
				aBlock.setBlockBounds(0.2F, 0, 0.2F, 0.8F, 0.6F, 0.8F);
			} else {
				byte facing = table.mFacing;
				if (facing == CS.SIDE_X_NEG || facing == CS.SIDE_X_POS)
				{
					aBlock.setBlockBounds(0.4F, 0, 0.2F, 0.6F, 0.6F, 0.8F);
				} else {
					aBlock.setBlockBounds(0.2F, 0, 0.4F, 0.8F, 0.6F, 0.6F);
				}
			}
		}
	}

}
