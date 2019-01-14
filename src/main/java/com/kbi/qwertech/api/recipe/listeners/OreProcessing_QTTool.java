package com.kbi.qwertech.api.recipe.listeners;

import com.kbi.qwertech.api.data.QTI;
import gregapi.code.ICondition;
import gregapi.code.IItemContainer;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

public class OreProcessing_QTTool
implements IOreDictListenerEvent
{
	private final ICondition mCondition;
	private final String[][] mToolRecipes;
	private final String[][] mToolHeadRecipes;
	private final String mCategoryName;
	private final Object mSpecialObjectV;
	private final Object mSpecialObjectW;
	private final Object mSpecialObjectX;
	private final Object mSpecialObjectY;
	private final Object mSpecialObjectZ;
	private final int mToolID;
	private final long mCapacity;
	private final long mVoltage;
	private final boolean mUseNormalHandle;
	private final boolean mDismantleable;
	private final OreDictMaterial mHandleOverride;
	private Object[] recObj;
	private HashMap<Character, Object> letterData = new HashMap();
	
	public void createHashMap(OreDictMaterial eventMaterial)
	{
		letterData.put('B', OP.plateGem.dat(eventMaterial));
		letterData.put('C', OP.plateCurved.dat(eventMaterial));
		letterData.put('D', OP.toolHeadArrow.dat(eventMaterial));
		letterData.put('G', OP.gem.dat(eventMaterial));
		letterData.put('H', OP.stick.dat(eventMaterial.mHandleMaterial));
		letterData.put('I', eventMaterial == MT.Wood ? OP.plank.dat(eventMaterial) : OP.ingot.dat(eventMaterial));
		letterData.put('J', OP.stickLong.dat(eventMaterial.mHandleMaterial));
		letterData.put('L', OP.stickLong.dat(eventMaterial));
		letterData.put('N', OP.nugget.dat(eventMaterial));
		letterData.put('O', OP.ring.dat(eventMaterial));
		letterData.put('P', eventMaterial == MT.Wood ? OP.plank.dat(eventMaterial) : OP.plate.dat(eventMaterial));
		letterData.put('R', eventMaterial == MT.Stone ? ST.make(Blocks.stone, 1L, 32767L) : OP.stone.dat(eventMaterial));
		letterData.put('S', OP.stick.dat(eventMaterial));
		letterData.put('T', OP.screw.dat(eventMaterial));
		letterData.put('V', mSpecialObjectV == null ? OP.plate.dat(eventMaterial) : mSpecialObjectV);
		letterData.put('W', mSpecialObjectW == null ? OP.plate.dat(eventMaterial) : mSpecialObjectW);
		letterData.put('X', mSpecialObjectX == null ? OP.plate.dat(eventMaterial) : mSpecialObjectX);
		letterData.put('Y', mSpecialObjectY == null ? OP.plate.dat(eventMaterial) : mSpecialObjectY);
		letterData.put('Z', mSpecialObjectZ == null ? OP.plate.dat(eventMaterial) : mSpecialObjectZ);
	}
	
	public void replaceData(Object[] recipeList, OreDictMaterial material)
	{
		OreDictMaterial handle = this.mUseNormalHandle ? this.mHandleOverride : material.mHandleMaterial;
		for (int q = 0; q < recipeList.length; q++)
		{
			if (recipeList[q] instanceof ItemStack && ((ItemStack)recipeList[q]).getItem() instanceof MultiItemTool)
			{
				ItemStack tool = (ItemStack)recipeList[q];
				OreDictMaterial primary = MultiItemTool.getPrimaryMaterial(tool);
				OreDictMaterial secondary = MultiItemTool.getSecondaryMaterial(tool);
				if (primary == MT.Empty) {
					primary = material;
				} else if (primary == MT.NULL) {
					primary = handle;
				}
				if (secondary == MT.Empty) {
					secondary = material;
				} else if (secondary == MT.NULL) {
					secondary = handle;
				}
				recipeList[q] = ((MultiItemTool)tool.getItem()).getToolWithStats(tool.getItemDamage(), primary, secondary);
			} else if (recipeList[q] instanceof OreDictItemData)
			{
				if (((OreDictItemData)recipeList[q]).mMaterial.mMaterial == MT.Empty)
				{
					recipeList[q] = ((OreDictItemData)recipeList[q]).mPrefix.dat(material);
				} else if (((OreDictItemData)recipeList[q]).mMaterial.mMaterial == MT.NULL)
				{
					recipeList[q] = ((OreDictItemData)recipeList[q]).mPrefix.dat(handle);
				}
			} else if (recipeList[q] instanceof OreDictPrefix)
			{
				recipeList[q] = ((OreDictPrefix)recipeList[q]).dat(material);
			}
		}
	}
	
	public OreProcessing_QTTool(int aToolID, String aCategoryName, String[][] aToolRecipes, String[][] aToolHeadRecipes, HashMap<Character, Object> characterMap, ICondition aCondition)
	{
		this.mSpecialObjectV = null;
		this.mSpecialObjectW = null;
		this.mSpecialObjectX = null;
		this.mSpecialObjectY = null;
		this.mSpecialObjectZ = null;
		this.mToolHeadRecipes = aToolHeadRecipes;
		this.mToolRecipes = aToolRecipes;
		this.mCondition = aCondition;
		this.mToolID = aToolID;
		this.mCategoryName = aCategoryName;
		this.mHandleOverride = null;
		this.mUseNormalHandle = true;
		this.mDismantleable = false;
		this.mCapacity = 0;
		this.mVoltage = 0;
		if (characterMap != null)
		{
			this.letterData = characterMap;
		}
	}
	
	public OreProcessing_QTTool(int aToolID, String aCategoryName, String[][] aToolRecipes, String[][] aToolHeadRecipes, ICondition aCondition)
	{
		this.mSpecialObjectV = null;
		this.mSpecialObjectW = null;
		this.mSpecialObjectX = null;
		this.mSpecialObjectY = null;
		this.mSpecialObjectZ = null;
		this.mToolHeadRecipes = aToolHeadRecipes;
		this.mToolRecipes = aToolRecipes;
		this.mCondition = aCondition;
		this.mToolID = aToolID;
		this.mCategoryName = aCategoryName;
		this.mHandleOverride = null;
		this.mUseNormalHandle = true;
		this.mDismantleable = false;
		this.mCapacity = 0;
		this.mVoltage = 0;
	}
	
	public OreProcessing_QTTool(int aToolID, String aCategoryName, boolean aUseNormalHandle, boolean aDismantleable, long aCapacity, long aVoltage, OreDictMaterial aHandleOverride, String[][] aToolRecipes, String[][] aToolHeadRecipes, ICondition aCondition)
	{
		this.mSpecialObjectV = null;
		this.mSpecialObjectW = null;
		this.mSpecialObjectX = null;
		this.mSpecialObjectY = null;
		this.mSpecialObjectZ = null;
		this.mToolHeadRecipes = aToolHeadRecipes;
		this.mToolRecipes = aToolRecipes;
		this.mCondition = aCondition;
		this.mToolID = aToolID;
		this.mCategoryName = aCategoryName;
		this.mHandleOverride = aHandleOverride;
		this.mUseNormalHandle = aUseNormalHandle;
		this.mDismantleable = aDismantleable;
		this.mCapacity = aCapacity;
		this.mVoltage = aVoltage;
	}
	
	public OreProcessing_QTTool(int aToolID, String aCategoryName, boolean aUseNormalHandle, boolean aDismantleable, long aCapacity, long aVoltage, OreDictMaterial aHandleOverride, String[][] aToolRecipes, String[][] aToolHeadRecipes, Object aSpecialObjectX, Object aSpecialObjectY, Object aSpecialobjectZ, Object aSpecialObjectV, Object aSpecialObjectW, ICondition aCondition)
	{
	  this.mSpecialObjectV = aSpecialObjectV;
	  this.mSpecialObjectW = aSpecialObjectW;
	  this.mSpecialObjectX = aSpecialObjectX;
	  this.mSpecialObjectY = aSpecialObjectY;
	  this.mSpecialObjectZ = aSpecialobjectZ;
	  this.mToolHeadRecipes = aToolHeadRecipes;
	  this.mToolRecipes = aToolRecipes;
	  this.mCondition = aCondition;
	  this.mToolID = aToolID;
	  this.mCategoryName = aCategoryName;
	  this.mHandleOverride = aHandleOverride;
	  this.mUseNormalHandle = aUseNormalHandle;
	  this.mDismantleable = aDismantleable;
	  this.mCapacity = aCapacity;
	  this.mVoltage = aVoltage;
	}
	
	public void onOreRegistration(IOreDictListenerEvent.OreDictRegistrationContainer aEvent)
	{
		if ((aEvent.mNotAlreadyRegisteredName) && (aEvent.mMaterial.contains(TD.Properties.HAS_TOOL_STATS)) && (this.mCondition.isTrue(aEvent.mMaterial)))
		{
			long tCapacity = this.mCapacity;
			if (tCapacity < 0L)
			{
				tCapacity = 0L;
				if ((this.mSpecialObjectV instanceof IItemContainer))
				{
					ItemStack tBattery = ((IItemContainer)this.mSpecialObjectV).get(1L);
					if ((tBattery != null) && ((tBattery.getItem() instanceof IItemEnergy))) {
						tCapacity += ((IItemEnergy)tBattery.getItem()).getEnergyCapacity(TD.Energy.EU, tBattery);
					}
				}
				else if (((this.mSpecialObjectV instanceof ItemStack)) && 
						((((ItemStack)this.mSpecialObjectV).getItem() instanceof IItemEnergy)))
				{
					tCapacity += ((IItemEnergy)((ItemStack)this.mSpecialObjectV).getItem()).getEnergyCapacity(TD.Energy.EU, (ItemStack)this.mSpecialObjectV);
				}
				if ((this.mSpecialObjectW instanceof IItemContainer))
				{
					ItemStack tBattery = ((IItemContainer)this.mSpecialObjectW).get(1L);
					if ((tBattery != null) && ((tBattery.getItem() instanceof IItemEnergy))) {
						tCapacity += ((IItemEnergy)tBattery.getItem()).getEnergyCapacity(TD.Energy.EU, tBattery);
					}
				}
				else if (((this.mSpecialObjectW instanceof ItemStack)) && 
						((((ItemStack)this.mSpecialObjectW).getItem() instanceof IItemEnergy)))
				{
					tCapacity += ((IItemEnergy)((ItemStack)this.mSpecialObjectW).getItem()).getEnergyCapacity(TD.Energy.EU, (ItemStack)this.mSpecialObjectW);
				}
			}
			ItemStack tTool = ((MultiItemTool)QTI.qwerTool.getItem()).getToolWithStats(this.mToolID, 1, aEvent.mMaterial, this.mHandleOverride == null ? aEvent.mMaterial : this.mUseNormalHandle ? aEvent.mMaterial.mHandleMaterial : this.mHandleOverride, tCapacity, this.mVoltage);ItemStack tStack = aEvent.mPrefix.mat(aEvent.mMaterial, 1L);
			if (tTool != null)
			{
				createHashMap(aEvent.mMaterial);
				
				recObj = new Object[(letterData.size() * 2)];
				for (int q = 0; q < letterData.size(); q++)
				{
					Character c = (Character)letterData.keySet().toArray()[q];
					recObj[(q * 2)] = c;
					recObj[(q * 2) + 1] = letterData.get(c);
				}
				
				replaceData(recObj, aEvent.mMaterial);
	    	
				if ((this.mToolRecipes != null) && (this.mToolRecipes.length > 0)) {
					for (int i = 0; i < this.mToolRecipes.length; i++) {
						if ((this.mToolRecipes[i] != null) && (this.mToolRecipes[i].length > 0) && ((this.mCategoryName == null) || (CS.ConfigsGT.RECIPES.get(this.mCategoryName + ".toolrecipes." + i, aEvent.mMaterial.mNameInternal, true)))) {
							Object[] recipeStrings = new Object[this.mToolRecipes[i].length];
							System.arraycopy(this.mToolRecipes[i], 0, recipeStrings, 0, this.mToolRecipes[i].length);
							Object[] result = Arrays.copyOf(recipeStrings, recObj.length + recipeStrings.length);
							System.arraycopy(recObj, 0, result, recipeStrings.length, recObj.length);
							CR.shaped(tTool, CR.DEF_NCC | (this.mDismantleable ? CR.DISMANTLE : 0L), result);
						}
					}
				}
				if ((this.mToolHeadRecipes != null) && (this.mToolHeadRecipes.length > 0)) {
					for (int i = 0; i < this.mToolHeadRecipes.length; i++) {
						if ((this.mToolHeadRecipes[i] != null) && (this.mToolHeadRecipes[i].length > 0) && ((this.mCategoryName == null) || (CS.ConfigsGT.RECIPES.get(this.mCategoryName + ".toolheadrecipe." + i, aEvent.mMaterial.mNameInternal, true)))) {
							Object[] recipeStrings = new Object[this.mToolHeadRecipes[i].length];
							System.arraycopy(this.mToolHeadRecipes[i], 0, recipeStrings, 0, this.mToolHeadRecipes[i].length);
							Object[] result = Arrays.copyOf(recipeStrings, recObj.length + recipeStrings.length);
							System.arraycopy(recObj, 0, result, recipeStrings.length, recObj.length);
							CR.shaped(tStack, CR.DEF_NCC | (this.mDismantleable ? CR.DISMANTLE : 0L), result);
						}
					}
				}
			}
		}
	}
}
