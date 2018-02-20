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
	        if (this.mRecipes[i].length == 1) {
	          CR.shaped(aEvent.mPrefix.mat(aEvent.mMaterial, this.mOutputAmount, ST.amount(this.mOutputAmount, new Object[] { aEvent.mStack })), CR.DEF_NAC, new Object[] { this.mRecipes[i][0], Character.valueOf('G'), OP.gem.dat(aEvent.mMaterial), Character.valueOf('I'), aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.ingot.dat(aEvent.mMaterial), Character.valueOf('P'), aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.plate.dat(aEvent.mMaterial), Character.valueOf('C'), OP.plateGem.dat(aEvent.mMaterial), Character.valueOf('S'), OP.stick.dat(aEvent.mMaterial), Character.valueOf('T'), OP.screw.dat(aEvent.mMaterial), Character.valueOf('N'), OP.nugget.dat(aEvent.mMaterial), Character.valueOf('V'), this.mSpecialObject1 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject1, Character.valueOf('W'), this.mSpecialObject2 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject2, Character.valueOf('X'), (this.mSpecialPrefix1 == null ? OP.plate : this.mSpecialPrefix1).dat(aEvent.mMaterial), Character.valueOf('Y'), (this.mSpecialPrefix2 == null ? OP.plate : this.mSpecialPrefix2).dat(aEvent.mMaterial), Character.valueOf('Z'), (this.mSpecialPrefix3 == null ? OP.plate : this.mSpecialPrefix3).dat(aEvent.mMaterial), Character.valueOf('H'), OP.stick.dat(aEvent.mMaterial.mHandleMaterial) });
	        } else if (this.mRecipes[i].length == 2) {
	          CR.shaped(aEvent.mPrefix.mat(aEvent.mMaterial, this.mOutputAmount, ST.amount(this.mOutputAmount, new Object[] { aEvent.mStack })), CR.DEF_NAC, new Object[] { this.mRecipes[i][0], this.mRecipes[i][1], Character.valueOf('G'), OP.gem.dat(aEvent.mMaterial), Character.valueOf('I'), aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.ingot.dat(aEvent.mMaterial), Character.valueOf('P'), aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.plate.dat(aEvent.mMaterial), Character.valueOf('C'), OP.plateGem.dat(aEvent.mMaterial), Character.valueOf('S'), OP.stick.dat(aEvent.mMaterial), Character.valueOf('T'), OP.screw.dat(aEvent.mMaterial), Character.valueOf('N'), OP.nugget.dat(aEvent.mMaterial), Character.valueOf('V'), this.mSpecialObject1 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject1, Character.valueOf('W'), this.mSpecialObject2 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject2, Character.valueOf('X'), (this.mSpecialPrefix1 == null ? OP.plate : this.mSpecialPrefix1).dat(aEvent.mMaterial), Character.valueOf('Y'), (this.mSpecialPrefix2 == null ? OP.plate : this.mSpecialPrefix2).dat(aEvent.mMaterial), Character.valueOf('Z'), (this.mSpecialPrefix3 == null ? OP.plate : this.mSpecialPrefix3).dat(aEvent.mMaterial), Character.valueOf('H'), OP.stick.dat(aEvent.mMaterial.mHandleMaterial) });
	        } else {
	          CR.shaped(aEvent.mPrefix.mat(aEvent.mMaterial, this.mOutputAmount, ST.amount(this.mOutputAmount, new Object[] { aEvent.mStack })), CR.DEF_NAC, new Object[] { this.mRecipes[i][0], this.mRecipes[i][1], this.mRecipes[i][2], Character.valueOf('G'), OP.gem.dat(aEvent.mMaterial), Character.valueOf('I'), aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.ingot.dat(aEvent.mMaterial), Character.valueOf('P'), aEvent.mMaterial == MT.Wood ? OP.plank.dat(aEvent.mMaterial) : OP.plate.dat(aEvent.mMaterial), Character.valueOf('C'), OP.plateGem.dat(aEvent.mMaterial), Character.valueOf('S'), OP.stick.dat(aEvent.mMaterial), Character.valueOf('T'), OP.screw.dat(aEvent.mMaterial), Character.valueOf('N'), OP.nugget.dat(aEvent.mMaterial), Character.valueOf('V'), this.mSpecialObject1 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject1, Character.valueOf('W'), this.mSpecialObject2 == null ? OP.plate.dat(aEvent.mMaterial) : this.mSpecialObject2, Character.valueOf('X'), (this.mSpecialPrefix1 == null ? OP.plate : this.mSpecialPrefix1).dat(aEvent.mMaterial), Character.valueOf('Y'), (this.mSpecialPrefix2 == null ? OP.plate : this.mSpecialPrefix2).dat(aEvent.mMaterial), Character.valueOf('Z'), (this.mSpecialPrefix3 == null ? OP.plate : this.mSpecialPrefix3).dat(aEvent.mMaterial), Character.valueOf('H'), OP.stick.dat(aEvent.mMaterial.mHandleMaterial) });
	        }
	      }
	    }
	  }
	}
}