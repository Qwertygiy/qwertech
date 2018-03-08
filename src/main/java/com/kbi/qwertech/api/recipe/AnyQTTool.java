package com.kbi.qwertech.api.recipe;

import com.kbi.qwertech.api.data.QTI;
import gregapi.code.ICondition;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static gregapi.data.CS.F;

public class AnyQTTool implements ICraftingRecipeGT, IRecipe
{
  public final ICondition mCondition;
  public final short mToolID;
  
  public final Object[] recipePieces;
  public final OreDictMaterial primaryMaterial;
  public final OreDictMaterial secondaryMaterial;
  public final boolean usesADefaultHandle;
  
  public OreDictMaterial tempPrimary;
  public OreDictMaterial tempSecondary;
  
  public AnyQTTool(long aToolID, 																				OreDictPrefix aToolHead,								boolean useMaterialHandle										){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 					ICondition.TRUE, 	useMaterialHandle,	aToolHead.dat(MT.NULL),											OP.stick.dat(useMaterialHandle ? MT.Butter : MT.Empty)					);}
  public AnyQTTool(long aToolID, 																				OreDictPrefix aToolHead, 	OreDictPrefix aToolRod,		boolean useMaterialHandle										){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 					ICondition.TRUE, 	useMaterialHandle,	aToolHead.dat(MT.NULL), 										aToolRod.dat(useMaterialHandle ? MT.Butter : MT.Empty)					);}
  public AnyQTTool(long aToolID, 														ICondition aCondition, 	OreDictPrefix aToolHead,								boolean useMaterialHandle										){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 					aCondition, 		useMaterialHandle,	aToolHead.dat(MT.NULL),											OP.stick.dat(useMaterialHandle ? MT.Butter : MT.Empty)					);}
  public AnyQTTool(long aToolID, 														ICondition aCondition, 	OreDictPrefix aToolHead, 	OreDictPrefix aToolRod,		boolean useMaterialHandle										){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 					aCondition, 		useMaterialHandle,	aToolHead.dat(MT.NULL), 										aToolRod.dat(useMaterialHandle ? MT.Butter : MT.Empty)					);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 							 							OreDictPrefix aToolHead, 								boolean useMaterialHandle										){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	ICondition.TRUE, 	useMaterialHandle,	aToolHead.dat(primary), 										OP.stick.dat(useMaterialHandle ? primary.mHandleMaterial : MT.Empty)	);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 								ICondition aCondition,	OreDictPrefix aToolHead, 								boolean useMaterialHandle										){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	aCondition, 		useMaterialHandle,	aToolHead.dat(primary), 										OP.stick.dat(useMaterialHandle ? primary.mHandleMaterial : MT.Empty)	);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 							 							OreDictPrefix aToolHead, 	OreDictPrefix aToolRod,		boolean useMaterialHandle										){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	ICondition.TRUE, 	useMaterialHandle,	aToolHead.dat(primary), 										aToolRod.dat(useMaterialHandle ? primary.mHandleMaterial : MT.Empty)	);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 								ICondition aCondition,	OreDictPrefix aToolHead, 	OreDictPrefix aToolRod,		boolean useMaterialHandle										){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	aCondition, 		useMaterialHandle,	aToolHead.dat(primary), 										aToolRod.dat(useMaterialHandle ? primary.mHandleMaterial : MT.Empty)	);}
  public AnyQTTool(long aToolID, 						 	OreDictMaterial secondary, 							OreDictPrefix aToolHead, 	OreDictPrefix aToolRod																		){	this(aToolID, 	MT.NULL, 	secondary, 													ICondition.TRUE, 	F,					aToolHead.dat(MT.NULL), 										aToolRod.dat(secondary)													);}
  public AnyQTTool(long aToolID, 						 	OreDictMaterial secondary, 	ICondition aCondition,	OreDictPrefix aToolHead, 	OreDictPrefix aToolRod																		){	this(aToolID, 	MT.NULL, 	secondary, 													aCondition, 		F,					aToolHead.dat(MT.NULL), 										aToolRod.dat(secondary)													);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 							OreDictPrefix aToolHead, 	OreDictPrefix aToolRod																		){	this(aToolID, 	primary, 	secondary, 													ICondition.TRUE, 	F,					aToolHead.dat(primary), 										aToolRod.dat(secondary)													);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 	ICondition aCondition,	OreDictPrefix aToolHead, 	OreDictPrefix aToolRod																		){	this(aToolID, 	primary, 	secondary, 													aCondition, 		F,					aToolHead.dat(primary), 										aToolRod.dat(secondary)													);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 																					Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	primary, 	secondary, 													ICondition.TRUE, 	F,					combineThings(primaryMats, secondaryMats, primary, secondary)																			);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 																					Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	primary, 	secondary, 													ICondition.TRUE, 	F,					combineThings(primaryMats, secondaryMats, primary, secondary, items)																	);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 	ICondition aCondition, 															Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	primary, 	secondary, 													aCondition, 		F,					combineThings(primaryMats, secondaryMats, primary, secondary)																			);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 	ICondition aCondition, 															Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	primary, 	secondary, 													aCondition, 		F,					combineThings(primaryMats, secondaryMats, primary, secondary, items)																	);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 							 														boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	ICondition.TRUE, 	useMaterialHandle, 	combineThings(primaryMats, secondaryMats, primary, useMaterialHandle ? primary.mHandleMaterial : MT.Empty)								);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 							 														boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	ICondition.TRUE, 	useMaterialHandle, 	combineThings(primaryMats, secondaryMats, primary, useMaterialHandle ? primary.mHandleMaterial : MT.Empty, items)						);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 							 	ICondition aCondition, 								boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	aCondition, 		useMaterialHandle, 	combineThings(primaryMats, secondaryMats, primary, useMaterialHandle ? primary.mHandleMaterial : MT.Empty)								);}
  public AnyQTTool(long aToolID, OreDictMaterial primary, 							 	ICondition aCondition, 								boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	primary, 	useMaterialHandle ? primary.mHandleMaterial : MT.Empty, 	aCondition, 		useMaterialHandle, 	combineThings(primaryMats, secondaryMats, primary, useMaterialHandle ? primary.mHandleMaterial : MT.Empty, items)						);}
  
  public AnyQTTool(long aToolID, 						 	OreDictMaterial secondary, 																					Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	MT.NULL, 	secondary, 													ICondition.TRUE, 	F,					combineThings(primaryMats, secondaryMats, MT.NULL, secondary)																			);}
  public AnyQTTool(long aToolID, 						 	OreDictMaterial secondary, 																					Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	MT.NULL, 	secondary, 													ICondition.TRUE, 	F,					combineThings(primaryMats, secondaryMats, MT.NULL, secondary, items)																	);}
  public AnyQTTool(long aToolID, 						 	OreDictMaterial secondary, 	ICondition aCondition, 															Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	MT.NULL, 	secondary, 													aCondition, 		F,					combineThings(primaryMats, secondaryMats, MT.NULL, secondary)																			);}
  public AnyQTTool(long aToolID, 						 	OreDictMaterial secondary, 	ICondition aCondition, 															Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	MT.NULL, 	secondary, 													aCondition, 		F,					combineThings(primaryMats, secondaryMats, MT.NULL, secondary, items)																	);}
  public AnyQTTool(long aToolID, 						 							 														boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 	ICondition.TRUE, 					useMaterialHandle, 	combineThings(primaryMats, secondaryMats, MT.NULL, useMaterialHandle ? MT.Butter : MT.Empty)											);}
  public AnyQTTool(long aToolID, 						 							 														boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 	ICondition.TRUE, 					useMaterialHandle, 	combineThings(primaryMats, secondaryMats, MT.NULL, useMaterialHandle ? MT.Butter : MT.Empty, items)										);}
  public AnyQTTool(long aToolID, 						 							 	ICondition aCondition, 								boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats					){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 	aCondition, 						useMaterialHandle, 	combineThings(primaryMats, secondaryMats, MT.NULL, useMaterialHandle ? MT.Butter : MT.Empty)											);}
  public AnyQTTool(long aToolID, 						 							 	ICondition aCondition, 								boolean useMaterialHandle,	Object[] primaryMats, 	Object[] secondaryMats, Object... items	){	this(aToolID, 	MT.NULL, 	useMaterialHandle ? MT.Butter : MT.Empty, 	aCondition, 						useMaterialHandle, 	combineThings(primaryMats, secondaryMats, MT.NULL, useMaterialHandle ? MT.Butter : MT.Empty, items)										);}
  
  public AnyQTTool(long aToolID, OreDictMaterial primary, 	OreDictMaterial secondary, 	ICondition aCondition, 	boolean usesDefaultHandle, Object... recipes)
  {
	  //super(QwerTech.qwerTool.getToolWithStats(UT.Code.bind15(aToolID), primary, secondary), stringify(recipes));
	  this.recipePieces = recipes;
	  this.mCondition = aCondition;
	  this.mToolID = UT.Code.bind15(aToolID);
	  this.primaryMaterial = primary;
	  this.secondaryMaterial = secondary;
	  this.tempPrimary = primary;
	  this.tempSecondary = secondary;
	  this.usesADefaultHandle = usesDefaultHandle;
  }
  
  public static Object[] combineThings(Object[] primaryPrefix, Object[] secondaryPrefix, OreDictMaterial primaryMat, OreDictMaterial secondaryMat, Object... extras)
  {
	  if (primaryPrefix == null) {primaryPrefix = new Object[]{};}
	  if (secondaryPrefix == null) {secondaryPrefix = new Object[]{};}
	  if (extras == null) {extras = new Object[]{};}
	  Object[] returnable = new Object[primaryPrefix.length + secondaryPrefix.length + extras.length];
	  for (int i = 0; i < primaryPrefix.length; i++)
	  {
		  returnable[i] = ((OreDictPrefix)primaryPrefix[i]).dat(primaryMat);
	  }
	  for (int o = 0; o < secondaryPrefix.length; o++)
	  {
		  returnable[primaryPrefix.length + o] = ((OreDictPrefix)secondaryPrefix[o]).dat(secondaryMat);
	  }
	  for (int p = 0; p < extras.length; p++)
	  {
		  returnable[primaryPrefix.length + secondaryPrefix.length + p] = extras[p];
	  }
	  return returnable;
  }
  
  public static Object[] stringify(Object[] ODs)
  {
	  StringBuilder ret = new StringBuilder("Adding AnyQTTool recipe: ");
	  Object[] returnable = new Object[ODs.length];
	  for (int i = 0; i < ODs.length; i++)
	  {
		  if (ODs[i] instanceof OreDictItemData)
		  {
			  OreDictMaterial lemat = ((OreDictItemData)ODs[i]).mMaterial.mMaterial;
			  if (lemat == MT.NULL || lemat == MT.Empty || lemat == MT.Butter)
			  {
				  ItemStack lestack = new ItemStack(((OreDictItemData)ODs[i]).mPrefix.mRegisteredPrefixItems.get(0));
				  lestack.setItemDamage(OreDictionary.WILDCARD_VALUE);
				  returnable[i] = lestack;
			  } else {
				  returnable[i] = ODs[i].toString();
			  }
			  
		  } else {
			  returnable[i] = ODs[i];
		  }
		  ret.append(returnable[i]).append(", ");
	  }
	  System.out.println(ret);
	  return returnable;
  }
  
  	//first is the one you want, second is the one you have. NULL = primary, EMPTY = any secondary, BUTTER = primary's handle.
  	public boolean compareMats(OreDictMaterial p, OreDictMaterial p2)
  	{
  		if (p == p2 || p.mToThis.contains(p2) || p2.mToThis.contains(p) || p.mReRegistrations.contains(p2) || p2.mReRegistrations.contains(p))
  		{
  			return true; //we have a match!
  		} else if (p == MT.NULL) //we're looking for primary!
  		{
  			if (this.tempPrimary == MT.NULL)
  			{
  				this.tempPrimary = p2;
  				if (this.tempSecondary == MT.Butter)
  				{
  					this.tempSecondary = p2.mHandleMaterial;
  				} else
					return this.tempSecondary == p2.mHandleMaterial || this.tempSecondary.mToThis.contains(p2.mHandleMaterial) || p2.mHandleMaterial.mToThis.contains(this.tempSecondary) || p2.mHandleMaterial.mReRegistrations.contains(this.tempSecondary) || this.tempSecondary.mReRegistrations.contains(p2.mHandleMaterial);
  				return true;
  			} else
				return p2 == this.tempPrimary || p2.mToThis.contains(this.tempPrimary) || this.tempPrimary.mToThis.contains(p2) || p2.mReRegistrations.contains(this.tempPrimary) || this.tempPrimary.mReRegistrations.contains(p2);
		} else if (p == MT.Empty) //we're looking for any secondary!
  		{
  			if (this.tempSecondary == MT.Empty)
  			{
  				this.tempSecondary = p2;
  				return true;
  			} else
				return p2 == this.tempSecondary || p2.mToThis.contains(this.tempSecondary) || this.tempSecondary.mToThis.contains(p2) || p2.mReRegistrations.contains(this.tempSecondary) || this.tempSecondary.mReRegistrations.contains(p2);
		} else if (p == MT.Butter)
  		{
  			if (this.tempSecondary == MT.Butter)
  			{
  				this.tempSecondary = p2;
  				return true;
  			} else if (p2 == this.tempSecondary)
  			{
  				return true;
  			} else if (p2.mToThis.contains(this.tempSecondary) || this.tempSecondary.mToThis.contains(p2) || p2.mReRegistrations.contains(this.tempSecondary) || this.tempSecondary.mReRegistrations.contains(p2))
  			{
  				this.tempSecondary = p2;
  				return true;
  			}
  		}
  		return false;
  	}
  
  	public boolean matches(InventoryCrafting aGrid)
  	{
  		this.tempPrimary = this.primaryMaterial;
  		this.tempSecondary = this.secondaryMaterial;
		ArrayList<Object> recipeCheck = new ArrayList<Object>(Arrays.asList(recipePieces));
		if (recipeCheck.size() > aGrid.getSizeInventory()) {return F;}
		ItemStack tStack;
		for (int i = 0; i < aGrid.getSizeInventory(); i++)
		{
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack))
			{
				boolean inRecipe = false;
                Iterator<Object> req = recipeCheck.iterator();

                while (req.hasNext())
                {
                    boolean match = false;

                    Object next = req.next();
					OreDictItemData tData = OM.data(tStack);
					if (next instanceof ItemStack)
					{
						//System.out.println("We have an ItemStack");
						ItemStack oStack = (ItemStack)next;
						if (tStack.isItemEqual(oStack) && tStack.getItemDamage() == oStack.getItemDamage() && oStack.getItem() instanceof MultiItemTool)
						{
							//System.out.println("We have a matching MultiItemTool");
							MultiItemTool oMIT = (MultiItemTool)oStack.getItem();
							OreDictMaterial p = MultiItemTool.getPrimaryMaterial(oStack);
							OreDictMaterial s = MultiItemTool.getSecondaryMaterial(oStack);
							OreDictMaterial p2 = MultiItemTool.getPrimaryMaterial(tStack);
							OreDictMaterial s2 = MultiItemTool.getSecondaryMaterial(tStack);
							match = compareMats(p, p2) && compareMats(s, s2);
						} else if (OreDictionary.itemMatches(oStack, tStack, false))
						{
							match = true;
						}
					} else if (next instanceof String && OreDictManager.isItemStackInstanceOf(tStack, next))
					{
						match = true;
					} else if (next instanceof OreDictItemData && tData != null)
					{
						if (((OreDictItemData)next).mPrefix == tData.mPrefix)
						{
							OreDictItemData nex = (OreDictItemData)next;
							match = compareMats(nex.mMaterial.mMaterial, tData.mMaterial.mMaterial);
						}
					} else if (next instanceof String && tData != null) {
						if (tData.toString().equals((String) next))
						{
							match = true;
						}
					}
					
					if (match)
                    {
                        inRecipe = true;
                        recipeCheck.remove(next);
                        break;
                    }
				}
                
                if (!inRecipe) {return F;}
			}
		}
		//System.out.println("We found all but " + recipeCheck.size() + " items");
		return recipeCheck.isEmpty() && this.mCondition.isTrue(this.tempPrimary) && (this.mCondition.isTrue(this.tempSecondary) || this.usesADefaultHandle);
  	}
  
  	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
  		return matches(aGrid);
	}
  
  
  	@Override
  	public ItemStack getCraftingResult(InventoryCrafting aGrid)
  	{
  		return ((MultiItemTool)QTI.qwerTool.getItem()).getToolWithStats(this.mToolID, this.tempPrimary, this.tempSecondary);
  	}
  
	@Override
	public ItemStack getRecipeOutput()
	{
	  return ((MultiItemTool)QTI.qwerTool.getItem()).getToolWithStats(this.mToolID, this.tempPrimary == MT.NULL ? MT.Steel : this.tempPrimary, this.tempSecondary == MT.Butter || this.tempSecondary == MT.Empty || this.tempSecondary == MT.NULL ? MT.Wood : this.tempSecondary);
	}

	@Override
	public boolean isRemovableByGT() {
		return false;
	}
	
	@Override
	public boolean isAutocraftableByGT() {
		return true;
	}
	
	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return recipePieces.length;
	}
}
