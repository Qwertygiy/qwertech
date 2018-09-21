package com.kbi.qwertech.api.recipe.listeners;

import gregapi.code.ICondition;
import gregapi.data.CS;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.IRecipeMapHandler;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

public class RecipeMap_NonCrafting implements IRecipeMapHandler {
	private final Object mInput;
	private final Object mOutput;
	private final boolean isInputPrefix;
	private final boolean isOutputPrefix;
	private final ICondition mCondition;
	
	protected boolean mAlreadyAddedAllRecipes = F;
	
	public RecipeMap_NonCrafting(Object aInput, Object aOutput, ICondition aCondition)
	{
		this.mInput = aInput;
		this.mOutput = aOutput;
		this.mCondition = aCondition;
		this.isInputPrefix = !(aInput instanceof ItemStack);
		this.isOutputPrefix = !(aOutput instanceof ItemStack);
	}

	@Override
	public boolean addRecipesUsing(RecipeMap aMap, ItemStack aStack,
			OreDictItemData aData) {
		if (isDone()) return F;
		if (isInputPrefix) {
			return aData != null && aData.hasValidMaterialData() && aData.mPrefix == mInput && addRecipeForMaterial(aMap, aData.mMaterial.mMaterial);
		} else {
			return (aStack == mInput && addRecipeForMaterial(aMap, aData.mMaterial.mMaterial));
		}
	}
	

	@Override
	public boolean addRecipesUsing(RecipeMap aMap, Fluid aFluid) {
		return false;
	}

	@Override
	public boolean addRecipesProducing(RecipeMap aMap, ItemStack aStack,
			OreDictItemData aData) {
		if (isDone()) return F;
		if (this.isOutputPrefix)
		{
			return aData != null && aData.hasValidMaterialData() && aData.mPrefix == mOutput && addRecipeForMaterial(aMap, aData.mMaterial.mMaterial);
		} else {
			return (aStack == mOutput && addRecipeForMaterial(aMap, aData.mMaterial.mMaterial));
		}
	}

	@Override
	public boolean addRecipesProducing(RecipeMap aMap, Fluid aFluid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsInput(RecipeMap aMap, ItemStack aStack,
			OreDictItemData aData) {
		if (isDone()) return F;
		return addRecipesUsing(aMap, aStack, aData);
	}

	@Override
	public boolean containsInput(RecipeMap aMap, Fluid aFluid) {
		return false;
	}

	@Override
	public boolean addAllRecipes(RecipeMap aMap) {
		if (isDone()) return F;
		if (this.isInputPrefix)
		{
			for (OreDictMaterial tMaterial : ((OreDictPrefix)mInput).mRegisteredMaterials) addRecipeForMaterial(aMap, tMaterial);
		} else if (this.isOutputPrefix) {
			if (((ItemStack)this.mInput).getItem() instanceof MultiItemTool)
			{
				addRecipeForMaterial(aMap, MultiItemTool.getPrimaryMaterial((ItemStack)this.mInput));
			} else if (OM.data((ItemStack)this.mInput) != null) {
				addRecipeForMaterial(aMap, OM.data((ItemStack)this.mInput).mMaterial.mMaterial);
			} else if (OM.anydata((ItemStack)this.mInput) != null) {
				addRecipeForMaterial(aMap, OM.anydata((ItemStack)this.mInput).mMaterial.mMaterial);
			} else {
				return F;
			}
		} else {
			addRecipeItemsOnly(aMap);
		}
		mAlreadyAddedAllRecipes = T;
		return T;
	}

	@Override
	public boolean isDone() {
		return mAlreadyAddedAllRecipes;
	}

	@Override
	public boolean onAddedToMap(RecipeMap aMap) {
		return true;
	}
	
	public boolean addRecipeItemsOnly(RecipeMap aMap)
	{
		if (ST.invalid((ItemStack)mInput) || (ST.invalid((ItemStack)mOutput)) || this.isInputPrefix || this.isOutputPrefix)
		{
			return F;
		}
		
		ItemStack[] tInputs = new ItemStack[]{(ItemStack)mInput};
		ItemStack[] tOutputs = new ItemStack[]{(ItemStack)mOutput};
		
		return aMap.addRecipeX(F,T,F,F,T, 16, 64, new long[]{1}, tInputs, CS.NF, CS.NF, tOutputs) != null;
	}
	
	public boolean addRecipeForMaterial(RecipeMap aMap, OreDictMaterial aMaterial) {
		if (!(this.isInputPrefix || this.isOutputPrefix) || aMaterial.contains(TD.Properties.INVALID_MATERIAL))
		{
			return addRecipeItemsOnly(aMap);
		}
		
		ItemStack tInput;
		ItemStack tOutput;
		
		if (this.isInputPrefix && !(ST.invalid(((OreDictPrefix)mInput).mat(aMaterial, 1))))
		{
			tInput = ((OreDictPrefix)mInput).mat(aMaterial, 1);
		} else {
			tInput = (ItemStack)mInput;
		}
		if (this.isOutputPrefix && !(ST.invalid(((OreDictPrefix)mInput).mat(aMaterial, 1))))
		{
			tOutput = ((OreDictPrefix)mOutput).mat(aMaterial, 1);
		} else {
			tOutput = (ItemStack)mOutput;
		}
		
		return aMap.addRecipe1(F, 16, 32+32*aMaterial.mToolQuality, tInput, tOutput) != null;
	}

}
