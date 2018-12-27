package com.kbi.qwertech.api.recipe;

import com.kbi.qwertech.api.data.QTMT;
import gregapi.code.ICondition;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static gregapi.data.CS.F;

public class HammerablePrefixRecipe implements ICraftingRecipeGT, IRecipe {

	public final ICondition mCondition;
	  
	public final Object[] recipePieces;
	public final OreDictMaterial primaryMaterial;
	public OreDictMaterial tempPrimary;
	public final OreDictPrefix outputPrefix;
	public final int outputAmount;
	public boolean isVertical = false;
	public boolean isHorizontal = false;
	public int tempWidth;
	public int tempHeight;
	
	public HammerablePrefixRecipe(OreDictPrefix output,													ICondition condition,	OreDictPrefix input, Object... items					) { this(output, 1, 		MT.NULL, 	condition,		 	combineThings(new Object[]{input}, 	MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 				 							 							OreDictPrefix input, Object... items					) { this(output, 1,		 	MT.NULL, 	ICondition.TRUE, 	combineThings(new Object[]{input},	MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 			 		OreDictMaterial material, 							OreDictPrefix input, Object... items					) { this(output, 1, 		material, 	ICondition.TRUE, 	combineThings(new Object[]{input},	material, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 				 	OreDictMaterial material, 	ICondition condition, 	OreDictPrefix input, Object... items					) { this(output, 1, 		material, 	condition, 			combineThings(new Object[]{input},	material, items));}
	
	public HammerablePrefixRecipe(OreDictPrefix output,		int amount,									ICondition condition,	OreDictPrefix input, Object... items					) { this(output, amount, 	MT.NULL, 	condition,		 	combineThings(new Object[]{input}, 	MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount,	 							 							OreDictPrefix input, Object... items					) { this(output, amount, 	MT.NULL, 	ICondition.TRUE, 	combineThings(new Object[]{input},	MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 							OreDictPrefix input, Object... items					) { this(output, amount, 	material, 	ICondition.TRUE, 	combineThings(new Object[]{input},	material, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 	ICondition condition, 	OreDictPrefix input, Object... items					) { this(output, amount, 	material, 	condition, 			combineThings(new Object[]{input},	material, items));}
	  
	public HammerablePrefixRecipe(OreDictPrefix output,													ICondition condition,	Object[] primaryMats, Object... items					) { this(output, 1, 		MT.NULL, 	condition,		 	combineThings(primaryMats, 			MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 					 							 						Object[] primaryMats, Object... items					) { this(output, 1, 		MT.NULL, 	ICondition.TRUE, 	combineThings(primaryMats, 			MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 				 	OreDictMaterial material, 							Object[] primaryMats, Object... items					) { this(output, 1, 		material, 	ICondition.TRUE, 	combineThings(primaryMats, 			material, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 				 	OreDictMaterial material, 	ICondition condition, 	Object[] primaryMats, Object... items					) { this(output, 1, 		material, 	condition, 			combineThings(primaryMats, 			material, items));}
	
	public HammerablePrefixRecipe(OreDictPrefix output,		int amount,									ICondition condition,	Object[] primaryMats, Object... items					) { this(output, amount,	MT.NULL, 	condition,		 	combineThings(primaryMats, 			MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount,	 							 							Object[] primaryMats, Object... items					) { this(output, amount, 	MT.NULL, 	ICondition.TRUE, 	combineThings(primaryMats, 			MT.NULL, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 							Object[] primaryMats, Object... items					) { this(output, amount, 	material, 	ICondition.TRUE, 	combineThings(primaryMats, 			material, items));}
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 	ICondition condition, 	Object[] primaryMats, Object... items					) { this(output, amount, 	material, 	condition, 			combineThings(primaryMats, 			material, items));}
	
	public HammerablePrefixRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 	ICondition condition, 											Object[] recipes) {
		this.mCondition = condition;
		this.primaryMaterial = material;
		this.recipePieces = recipes;
		this.outputPrefix = output;
		this.outputAmount = amount;
	}
	
	public HammerablePrefixRecipe setVertical()
	{
		this.isVertical = true;
		this.isHorizontal = false;
		return this;
	}
	
	public HammerablePrefixRecipe setHorizontal()
	{
		this.isVertical = false;
		this.isHorizontal = true;
		return this;
	}
	
	public static Object[] combineThings(Object[] primaryPrefix, OreDictMaterial primaryMat, Object... extras)
	  {
		  if (primaryPrefix == null) {primaryPrefix = new Object[]{};}
		  if (extras == null) {extras = new Object[]{};}
		  Object[] returnable = new Object[primaryPrefix.length + extras.length];
		  for (int i = 0; i < primaryPrefix.length; i++)
		  {
			  returnable[i] = ((OreDictPrefix)primaryPrefix[i]).dat(primaryMat);
		  }
		  System.arraycopy(extras, 0, returnable, primaryPrefix.length, extras.length);
		  return returnable;
	  }
	
	//first is the one you want, second is the one you have. NULL = wildcard.
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
  				return true;
  			} else
				return p2 == this.tempPrimary || p2.mToThis.contains(this.tempPrimary) || this.tempPrimary.mToThis.contains(p2) || p2.mReRegistrations.contains(this.tempPrimary) || this.tempPrimary.mReRegistrations.contains(p2);
		}
  		return false;
  	}

  	public boolean matches(InventoryCrafting aGrid)
  	{
  		this.tempPrimary = this.primaryMaterial;
  		this.tempHeight = -1;
  		this.tempWidth = -1;
		ArrayList<Object> recipeCheck = new ArrayList<Object>(Arrays.asList(recipePieces));
		if (recipeCheck.size() > aGrid.getSizeInventory()) {return F;}
		ItemStack tStack;
		for (int i = 0; i < aGrid.getSizeInventory(); i++)
		{
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack))
			{
				
				if (this.isHorizontal && this.tempHeight == -1) 
				{	
					this.tempHeight = (int)Math.floor((i)/Math.sqrt(aGrid.getSizeInventory()));
				} else if (this.isHorizontal && (int)Math.floor((i)/Math.sqrt(aGrid.getSizeInventory())) != this.tempHeight)
				{
					return false;
				}
				
				if (this.isVertical && this.tempWidth == -1) 
				{
					this.tempWidth = i % (int)Math.sqrt(aGrid.getSizeInventory());
				} else if (this.isVertical && i % (int)Math.sqrt(aGrid.getSizeInventory()) != this.tempWidth)
				{
					return false;
				}
				
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
						if (tData.toString().equals(next))
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
		return recipeCheck.isEmpty() && this.mCondition.isTrue(this.tempPrimary);
  	}
  
  	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
  		return matches(aGrid);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		// TODO Auto-generated method stub
		return outputPrefix.mat(tempPrimary == null || tempPrimary == MT.NULL ? QTMT.Undefined : tempPrimary, outputAmount);
	}

	@Override
	public int getRecipeSize() {
		return recipePieces.length;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return outputPrefix.mat(tempPrimary == null || tempPrimary == MT.NULL ? QTMT.Undefined : tempPrimary, outputAmount);
	}

	@Override
	public boolean isRemovableByGT() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAutocraftableByGT() {
		// TODO Auto-generated method stub
		return false;
	}

}
