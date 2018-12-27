package com.kbi.qwertech.blocks;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.tileentities.ChiselableTileEntity;
import gregapi.GT_API_Proxy;
import gregapi.block.IBlockSyncData;
import gregapi.block.behaviors.Drops;
import gregapi.block.prefixblock.PrefixBlock;
import gregapi.block.prefixblock.PrefixBlockItem;
import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.old.Textures;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

public class ChiselableBlock extends PrefixBlock implements IBlockSyncData.IBlockSyncDataAndIDs {

	IIconContainer[] icon = new IIconContainer[32768];
	
	public ChiselableBlock(ModData aMod, String aNameInternal,
			OreDictPrefix aPrefix, Drops aDrops, ITexture aTexture,
			Material aVanillaMaterial, SoundType aSoundType, String aTool,
			float aBaseHardness, float aBaseResistance,
			int aHarvestLevelOffset, int aHarvestLevelMinimum,
			boolean aGravity, boolean aEnderDragonProof,
			OreDictMaterial... aMaterialList) {
		super(aMod, aNameInternal, aPrefix, aDrops, aTexture, aVanillaMaterial,
				aSoundType, aTool, aBaseHardness, aBaseResistance,
				aHarvestLevelOffset, aHarvestLevelMinimum, aGravity,
				aEnderDragonProof, aMaterialList);
		// TODO Auto-generated constructor stub
	}

	public ChiselableBlock(String aModIDOwner, String aModIDTextures,
			String aNameInternal, OreDictPrefix aPrefix, Drops aDrops,
			ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType,
			String aTool, float aBaseHardness, float aBaseResistance,
			int aHarvestLevelOffset, int aHarvestLevelMinimum,
			boolean aGravity, boolean aEnderDragonProof,
			OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aDrops,
				aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness,
				aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum,
				aGravity, aEnderDragonProof, aMaterialList);
		// TODO Auto-generated constructor stub
	}

	public ChiselableBlock(ModData aMod, String aNameInternal,
			OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial,
			Class<? extends PrefixBlockItem> aItemClass, Drops aDrops,
			ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType,
			String aTool, float aBaseHardness, float aBaseResistance,
			int aHarvestLevelOffset, int aHarvestLevelMinimum,
			int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ,
			double aMaxX, double aMaxY, double aMaxZ, boolean aGravity,
			boolean aBeaconBase, boolean aEnderDragonProof,
			boolean aWitherProof, boolean aOpaque, boolean aNormalCube,
			boolean aPlacementChecksTemperature,
			boolean aPlacementChecksAntimatter, boolean aCanBurn,
			boolean aCanExplode, boolean aRenderOverlayInWorld,
			boolean aCanGlow, boolean aCanLight, boolean aSpawnProof,
			OreDictMaterial... aMaterialList) {
		super(aMod, aNameInternal, aPrefix, aHullMaterial, aItemClass, aDrops,
				aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness,
				aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum,
				aHarvestLevelMaximum, aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ,
				aGravity, aBeaconBase, aEnderDragonProof, aWitherProof,
				aOpaque, aNormalCube, aPlacementChecksTemperature,
				aPlacementChecksAntimatter, aCanBurn, aCanExplode,
				aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof,
				aMaterialList);
		// TODO Auto-generated constructor stub
	}

	public ChiselableBlock(String aModIDOwner, String aModIDTextures,
			String aNameInternal, OreDictPrefix aPrefix,
			OreDictMaterialStack aHullMaterial,
			Class<? extends PrefixBlockItem> aItemClass, Drops aDrops,
			ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType,
			String aTool, float aBaseHardness, float aBaseResistance,
			int aHarvestLevelOffset, int aHarvestLevelMinimum,
			int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ,
			double aMaxX, double aMaxY, double aMaxZ, boolean aGravity,
			boolean aBeaconBase, boolean aEnderDragonProof,
			boolean aWitherProof, boolean aOpaque, boolean aNormalCube,
			boolean aPlacementChecksTemperature,
			boolean aPlacementChecksAntimatter, boolean aCanBurn,
			boolean aCanExplode, boolean aRenderOverlayInWorld,
			boolean aCanGlow, boolean aCanLight, boolean aSpawnProof,
			OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix,
				aHullMaterial, aItemClass, aDrops, aTexture, aVanillaMaterial,
				aSoundType, aTool, aBaseHardness, aBaseResistance,
				aHarvestLevelOffset, aHarvestLevelMinimum,
				aHarvestLevelMaximum, aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ,
				aGravity, aBeaconBase, aEnderDragonProof, aWitherProof,
				aOpaque, aNormalCube, aPlacementChecksTemperature,
				aPlacementChecksAntimatter, aCanBurn, aCanExplode,
				aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof,
				aMaterialList);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		if (mRegisterToOreDict) {
			for (short i = 0; i < mMaterialList.length; i++) if (mMaterialList[i] != null && mPrefix.isGeneratingItem(mMaterialList[i])) {
				ItemStack tStack = ST.make(this, 1, i);
				ST.update_(tStack);
				LH.add("oredict." + mPrefix.dat(mMaterialList[i]).toString() + ".name", getLocalName(mPrefix, mMaterialList[i]));
				OreDictManager.INSTANCE.registerOre_(mPrefix, mMaterialList[i], tStack);
			}
		}
		for (int q = 0; q < QwerTech.maxChiselTex; q++)
		{
			icon[q] = new Textures.BlockIcons.CustomIcon("qwertech:chisel/" + q);
		}
	}
	
	public ITexture getTexture(short aMetaData, short aDesign, boolean aRendersInWorld) {
		//if (!mRenderOverlayInWorld && aRendersInWorld) return mTexture;
		if (mPrefix.mIconIndexBlock >= 0) {
			OreDictMaterial aMaterial = getMetaMaterial(aMetaData);
			if (mTexture == null) {
				/*if (aMaterial != null && aMaterial.mTextureSetsBlock != null)
				return BlockTextureDefault.get(aMaterial, mPrefix, mCanGlow && aMaterial.contains(TD.Properties.GLOWING));
				return BlockTextureDefault.get(MT.NULL, mPrefix, F);*/
			}
			//return BlockTextureMulti.get(BlockTextureDefault.get(aMaterial, mPrefix, mCanGlow && aMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(icon[aDesign], CS.CA_WHITE, true, false, false, true));
			return BlockTextureDefault.get(icon[aDesign], aMaterial.mRGBaSolid, false, false, false, true);
		}
		return null;
	}
	
	@Override
	public boolean placeBlock(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		OreDictMaterial aMaterial = getMetaMaterial(aMetaData);
		if (aMaterial != null && (aForcePlacement || ((!mPlacementChecksAntimatter || !aMaterial.contains(TD.Atomic.ANTIMATTER)) && (!mPlacementChecksTemperature || aMaterial.mMeltingPoint > WD.temperature(aWorld, aX, aY, aZ)))) && aWorld.setBlock(aX, aY, aZ, this, UT.Code.bind4(aMaterial.mToolQuality), aCauseBlockUpdates?3:0)) {
			// This darn TileEntity update is ruining World generation Code (infinite Loops when placing TileEntities on Chunk Borders). I'm glad I finally found a way to disable it.
			TileEntity tTileEntity = createTileEntity(aWorld, aX, aY, aZ, aSide, aMetaData, aNBT);
			WD.te(aWorld, aX, aY, aZ, tTileEntity, aCauseBlockUpdates);
			aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 2);
			GT_API_Proxy.SCHEDULED_TILEENTITY_UPDATES.add((ChiselableTileEntity)tTileEntity);
			return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {
		short design = 0;
		NBTTagCompound tag = aStack.getTagCompound();
		if (tag != null && tag.hasKey("d"))
		{
			design = tag.getShort("d");
		}
		return getTexture(ST.meta(aStack), design, F);
	}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {
		return aShouldSideBeRendered[aSide] ? getTexture(getMetaDataValue(aWorld, aX, aY, aZ), getDesign(aWorld, aX, aY, aZ), T) : null;
	}
	
	@Override
	public TileEntity createTileEntity(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT) {
		ChiselableTileEntity rTileEntity = new ChiselableTileEntity();
		if (aNBT != null) 
		{
			rTileEntity.readFromNBT(aNBT);
		} else {
			rTileEntity.mDesign = 0;
		}
		rTileEntity.mMetaData = aMetaData;
		//rTileEntity.mDesign = aNBT == null ? 0 : aNBT.hasKey("d") ? aNBT.getShort("d") : 0;
		return rTileEntity;
	}
	
	
	@Override
	public void onBlockPlacedBy(World aWorld, int aX, int aY, int aZ, EntityLivingBase aPlayer, ItemStack aStack)
	{
		NBTTagCompound nbt = aStack.getTagCompound();
		short design = 0;
		if (nbt != null)
		{
			if (nbt.hasKey("d")) design = nbt.getShort("d");
		}
		setDesign(aWorld, aX, aY, aZ, (short)aStack.getItemDamage(), design);
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		OreDictMaterial aMaterial = getMetaMaterial(aWorld, aX, aY, aZ);

		if (aTool.equals(CS.TOOL_chisel)) {
			ItemStack tChiseledBlock = this.getItemStackFromBlock(aWorld, aX, aY, aZ, aSide);
			if (tChiseledBlock != null && !aWorld.isRemote) {
				System.out.println("We're trying to chisel here!");
				short design = getDesign(aWorld, aX, aY, aZ);
				System.out.println("We found design " + design);
				if (design < QwerTech.maxChiselTex)
				{
					System.out.println("We're using it");
					NBTTagCompound taggy = UT.NBT.make();
					taggy.setShort("d", (short)(design + 1));
					setDesign(aWorld, aX, aY, aZ, getExtendedMetaData(aWorld, aX, aY, aZ), (short)(design + 1));
				} else {
					return super.onToolClick(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
				}
				return 1000;
			}
			return 0;
		}

		return super.onToolClick(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public ItemStack getItemStackFromBlock(IBlockAccess aWorld, int aX, int aY, int aZ, byte aSide) {
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		NBTTagCompound tagger = UT.NBT.make();
		tagger.setShort("d", getDesign(aTileEntity));
		return ST.make(this, 1, getMetaDataValue(aTileEntity), tagger);
	}
	
	public void setDesign(IBlockAccess aWorld, int aX, int aY, int aZ, short aMetaData, short aDesign) {
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		NBTTagCompound nbt = UT.NBT.make();
		if (aTileEntity == null && aWorld instanceof World) 
		{
			aTileEntity = WD.te((World)aWorld, aX, aY, aZ, createTileEntity((World)aWorld, aX, aY, aZ, (byte)6, aMetaData, nbt), F);
		} else {
			aTileEntity.writeToNBT(nbt);
		}
		nbt.setShort("d", aDesign);
		nbt.setShort("m", aMetaData);
		aTileEntity.readFromNBT(nbt);
		((ChiselableTileEntity)aTileEntity).onScheduledUpdate();
		System.out.println("Successful I hope");
	}
	
	public short getDesign(TileEntity aTileEntity) {
		return aTileEntity instanceof ChiselableTileEntity?((ChiselableTileEntity)aTileEntity).mDesign:0;
	}
	
	public short getDesign(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return getDesign(aWorld.getTileEntity(aX, aY, aZ));
	}

	
	/*the old Prefixblock bit*/
	@Override public void receiveDataShort				(IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler) {}
	
	@Override public void receiveData(IBlockAccess aWorld, int aX, int aY, int aZ, INetworkHandler aNetworkHandler, short aID1, short aID2) {
		System.out.println("Recieving packet for " + aID2); 
		setDesign(aWorld, aX, aY, aZ, aID1, aID2);
	}
	@Override
	public void receiveDataByte(IBlockAccess aWorld, int aX, int aY, int aZ, byte aData, INetworkHandler aNetworkHandler, short aID1, short aID2) {}
	@Override
	public void receiveDataShort(IBlockAccess aWorld, int aX, int aY, int aZ, short aData, INetworkHandler aNetworkHandler, short aID1, short aID2) {}
	@Override
	public void receiveDataInteger(IBlockAccess aWorld, int aX, int aY, int aZ, int aData, INetworkHandler aNetworkHandler, short aID1, short aID2) {}
	@Override
	public void receiveDataLong(IBlockAccess aWorld, int aX, int aY, int aZ, long aData, INetworkHandler aNetworkHandler, short aID1, short aID2) {}
	@Override
	public void receiveDataByteArray(IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short aID1, short aID2) {}
}
