package com.kbi.qwertech.api.recipe.listeners;

import gregapi.code.ICondition;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.IOreDictListenerEvent;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.CR;
import gregapi.util.ST;

public class ShapelessCraftFrom
implements IOreDictListenerEvent
{
	private final ICondition mCondition;
	private final String[][] mRecipes;
	private final String mCategoryName;
	private final OreDictPrefix mSpecialPrefix1;
	private final OreDictPrefix mSpecialPrefix2;
	private final OreDictPrefix mSpecialPrefix3;
	private final Object mSpecialObject1;
	private final Object mSpecialObject2;
	private final long mOutputAmount;
	
	public ShapelessCraftFrom(long aOutputAmount, String aCategoryName, String[][] aRecipes, OreDictPrefix aSpecialPrefix1, OreDictPrefix aSpecialPrefix2, OreDictPrefix aSpecialPrefix3, Object aSpecialObject1, Object aSpecialObject2, ICondition aCondition)
	{
	  this.mSpecialPrefix1 = aSpecialPrefix1;
	  this.mSpecialPrefix2 = aSpecialPrefix2;
	  this.mSpecialPrefix3 = aSpecialPrefix3;
	  this.mSpecialObject1 = aSpecialObject1;
	  this.mSpecialObject2 = aSpecialObject2;
	  this.mOutputAmount = aOutputAmount;
	  this.mRecipes = aRecipes;
	  this.mCondition = aCondition;
	  this.mCategoryName = aCategoryName;
	}
	
	public void onOreRegistration(IOreDictListenerEvent.OreDictRegistrationContainer aEvent)
	{
	  if ((aEvent.mNotAlreadyRegisteredName) && (this.mCondition.isTrue(aEvent.mMaterial)) && ((this.mCategoryName == null) || (CS.ConfigsGT.RECIPES.get(this.mCategoryName, aEvent.mMaterial.mNameInternal, true)))) {
	    for (int i = 0; i < this.mRecipes.length; i++) {
	      if ((this.mRecipes[i] != null) && (this.mRecipes[i].length > 0)) {
			  switch (this.mRecipes[i].length) {
				  case 1:
					  CR.shaped(aEvent.mPrefix.mat(aEvent.mMaterial, this.mOutputAmount, ST.amount(this.mOutputAmount, aEvent.mStack)), CR.DEF_NAC, new Object[]{this.mRecipes[i][0], 'G', OP.gem.dat(aEvent.mMaterial), 'I', aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.ingot.dat(aEvent.mMaterial), 'P', aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.plate.dat(aEvent.mMaterial), 'C', OP.plateGem.dat(aEvent.mMaterial), 'S', OP.stick.dat(aEvent.mMaterial), 'T', OP.screw.dat(aEvent.mMaterial), 'N', OP.nugget.dat(aEvent.mMaterial), 'V', this.mSpecialObject1 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject1, 'W', this.mSpecialObject2 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject2, 'X', (this.mSpecialPrefix1 == null ? OP.plate : this.mSpecialPrefix1).dat(aEvent.mMaterial), 'Y', (this.mSpecialPrefix2 == null ? OP.plate : this.mSpecialPrefix2).dat(aEvent.mMaterial), 'Z', (this.mSpecialPrefix3 == null ? OP.plate : this.mSpecialPrefix3).dat(aEvent.mMaterial), 'H', OP.stick.dat(aEvent.mMaterial.mHandleMaterial)});
					  break;
				  case 2:
					  CR.shaped(aEvent.mPrefix.mat(aEvent.mMaterial, this.mOutputAmount, ST.amount(this.mOutputAmount, aEvent.mStack)), CR.DEF_NAC, new Object[]{this.mRecipes[i][0], this.mRecipes[i][1], 'G', OP.gem.dat(aEvent.mMaterial), 'I', aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.ingot.dat(aEvent.mMaterial), 'P', aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.plate.dat(aEvent.mMaterial), 'C', OP.plateGem.dat(aEvent.mMaterial), 'S', OP.stick.dat(aEvent.mMaterial), 'T', OP.screw.dat(aEvent.mMaterial), 'N', OP.nugget.dat(aEvent.mMaterial), 'V', this.mSpecialObject1 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject1, 'W', this.mSpecialObject2 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject2, 'X', (this.mSpecialPrefix1 == null ? OP.plate : this.mSpecialPrefix1).dat(aEvent.mMaterial), 'Y', (this.mSpecialPrefix2 == null ? OP.plate : this.mSpecialPrefix2).dat(aEvent.mMaterial), 'Z', (this.mSpecialPrefix3 == null ? OP.plate : this.mSpecialPrefix3).dat(aEvent.mMaterial), 'H', OP.stick.dat(aEvent.mMaterial.mHandleMaterial)});
					  break;
				  default:
					  CR.shaped(aEvent.mPrefix.mat(aEvent.mMaterial, this.mOutputAmount, ST.amount(this.mOutputAmount, aEvent.mStack)), CR.DEF_NAC, new Object[]{this.mRecipes[i][0], this.mRecipes[i][1], this.mRecipes[i][2], 'G', OP.gem.dat(aEvent.mMaterial), 'I', aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.ingot.dat(aEvent.mMaterial), 'P', aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.plate.dat(aEvent.mMaterial), 'C', OP.plateGem.dat(aEvent.mMaterial), 'S', OP.stick.dat(aEvent.mMaterial), 'T', OP.screw.dat(aEvent.mMaterial), 'N', OP.nugget.dat(aEvent.mMaterial), 'V', this.mSpecialObject1 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject1, 'W', this.mSpecialObject2 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject2, 'X', (this.mSpecialPrefix1 == null ? OP.plate : this.mSpecialPrefix1).dat(aEvent.mMaterial), 'Y', (this.mSpecialPrefix2 == null ? OP.plate : this.mSpecialPrefix2).dat(aEvent.mMaterial), 'Z', (this.mSpecialPrefix3 == null ? OP.plate : this.mSpecialPrefix3).dat(aEvent.mMaterial), 'H', OP.stick.dat(aEvent.mMaterial.mHandleMaterial)});
					  break;
			  }
	      }
	    }
	  }
	}
}
