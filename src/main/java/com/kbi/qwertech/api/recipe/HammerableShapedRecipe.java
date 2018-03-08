package com.kbi.qwertech.api.recipe;

import gregapi.code.ICondition;
import gregapi.data.MT;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.OM;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HammerableShapedRecipe implements IRecipe, ICraftingRecipeGT {

	 //Added in for future ease of change, but hard coded for now.
    public static int MAX_CRAFT_GRID_WIDTH = 3;
    public static int MAX_CRAFT_GRID_HEIGHT = 3;
    
    public final ICondition mCondition;
	  
	public Object[] input;
	public final OreDictMaterial primaryMaterial;
	public OreDictMaterial tempPrimary;
	public final OreDictPrefix outputPrefix;
	public final int outputAmount;
    
    public int width = 0;
    public int height = 0;
    private boolean mirrored = true;
	  
	public HammerableShapedRecipe(OreDictPrefix output, 					 							 						Object... items					) { this(output, 1, 		MT.NULL, 	ICondition.TRUE, 	items);}
	public HammerableShapedRecipe(OreDictPrefix output, 					 							ICondition condition,	Object... items					) { this(output, 1, 		MT.NULL, 	condition,		 	items);}
	public HammerableShapedRecipe(OreDictPrefix output, 				 	OreDictMaterial material, 							Object... items					) { this(output, 1, 		material, 	ICondition.TRUE, 	items);}
	public HammerableShapedRecipe(OreDictPrefix output, 				 	OreDictMaterial material, 	ICondition condition, 	Object... items					) { this(output, 1, 		material, 	condition, 			items);}
	
	public HammerableShapedRecipe(OreDictPrefix output,		int amount,									ICondition condition,	Object... items					) { this(output, amount,	MT.NULL, 	condition,		 	items);}
	public HammerableShapedRecipe(OreDictPrefix output, 	int amount,	 							 							Object... items					) { this(output, amount, 	MT.NULL, 	ICondition.TRUE, 	items);}
	public HammerableShapedRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 							Object... items					) { this(output, amount, 	material, 	ICondition.TRUE, 	items);}
	
	public HammerableShapedRecipe(OreDictPrefix output, 	int amount, 	OreDictMaterial material, 	ICondition condition, 											Object[] recipe) {
		this.mCondition = condition;
		this.primaryMaterial = material;
		this.input = recipe;
		this.outputPrefix = output;
		this.outputAmount = amount;

        StringBuilder shape = new StringBuilder();
        int idx = 0;

        if (recipe[idx] instanceof Boolean)
        {
            mirrored = (Boolean)recipe[idx];
            if (recipe[idx+1] instanceof Object[])
            {
                recipe = (Object[])recipe[idx+1];
            }
            else
            {
                idx = 1;
            }
        }

        if (recipe[idx] instanceof String[])
        {
            String[] parts = ((String[])recipe[idx++]);

            for (String s : parts)
            {
                width = s.length();
                shape.append(s);
            }

            height = parts.length;
        }
        else
        {
            while (recipe[idx] instanceof String)
            {
                String s = (String)recipe[idx++];
                shape.append(s);
                width = s.length();
                height++;
            }
        }

        if (width * height != shape.length())
        {
            StringBuilder ret = new StringBuilder("Invalid shaped ore recipe: ");
            for (Object tmp :  recipe)
            {
                ret.append(tmp).append(", ");
            }
            ret.append(output);
            throw new RuntimeException(ret.toString());
        }

        HashMap<Character, Object> itemMap = new HashMap<Character, Object>();

        for (; idx < recipe.length; idx += 2)
        {
            Character chr = (Character)recipe[idx];
            Object in = recipe[idx + 1];

            if (in instanceof ItemStack)
            {
                itemMap.put(chr, ((ItemStack)in).copy());
            }
            else if (in instanceof Item)
            {
                itemMap.put(chr, new ItemStack((Item)in));
            }
            else if (in instanceof Block)
            {
                itemMap.put(chr, new ItemStack((Block)in, 1, OreDictionary.WILDCARD_VALUE));
            }
            else if (in instanceof String)
            {
                itemMap.put(chr, OreDictionary.getOres((String)in));
            } else if (in instanceof OreDictItemData)
            {
            	itemMap.put(chr, in);
            } else if (in instanceof OreDictPrefix)
            {
            	itemMap.put(chr, ((OreDictPrefix)in).dat(primaryMaterial));
            }
            else
            {
                StringBuilder ret = new StringBuilder("Invalid shaped ore recipe: ");
                for (Object tmp :  recipe)
                {
                    ret.append(tmp).append(", ");
                }
                ret.append(output);
                throw new RuntimeException(ret.toString());
            }
        }

        input = new Object[width * height];
        int x = 0;
        for (char chr : shape.toString().toCharArray())
        {
            input[x++] = itemMap.get(chr);
        }
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

    /**
     * Returns an Item that is the result of this recipe
     */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		// TODO Auto-generated method stub
		return outputPrefix.mat(tempPrimary == null || tempPrimary == MT.NULL ? MT.Steel : tempPrimary, outputAmount);
	}

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize(){ return input.length; }

    @Override
    public ItemStack getRecipeOutput(){
    	OreDictMaterial outputter = tempPrimary == MT.NULL ? MT.Steel : tempPrimary;
    	if (outputPrefix.isTrue(outputter))
    	{
    		return outputPrefix.mat(outputter, outputAmount); 
    	}
    	return outputPrefix.mat(MT.Steel, outputAmount);
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    
    public boolean matches(InventoryCrafting inv)
    {
    	this.tempPrimary = this.primaryMaterial;
    	for (int x = 0; x <= MAX_CRAFT_GRID_WIDTH - width; x++)
        {
            for (int y = 0; y <= MAX_CRAFT_GRID_HEIGHT - height; ++y)
            {
                if (checkMatch(inv, x, y, false))
                {
                    return this.mCondition.isTrue(this.tempPrimary);
                }

                if (mirrored && checkMatch(inv, x, y, true))
                {
                    return this.mCondition.isTrue(this.tempPrimary);
                }
            }
        }

        return false;
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {
        return matches(inv);
    }

    @SuppressWarnings("unchecked")
    private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror)
    {
    	try{
	        for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++)
	        {
	            for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++)
	            {
	                int subX = x - startX;
	                int subY = y - startY;
	                Object target = null;
	
	                if (subX >= 0 && subY >= 0 && subX < width && subY < height)
	                {
	                    if (mirror)
	                    {
	                        target = input[width - subX - 1 + subY * width];
	                    }
	                    else
	                    {
	                        target = input[subX + subY * width];
	                    }
	                }
	
	                ItemStack slot = inv.getStackInRowAndColumn(x, y);
	                if ((slot == null && target != null) || (slot != null && target == null)) return false;
	
	                if (target instanceof ItemStack)
	                {
	                    if (!OreDictionary.itemMatches((ItemStack)target, slot, false))
	                    {
	                        return false;
	                    }
	                }
	                else if (target instanceof ArrayList)
	                {
	                    boolean matched = false;
	
	                    Iterator<ItemStack> itr = ((ArrayList<ItemStack>)target).iterator();
	                    while (itr.hasNext() && !matched)
	                    {
	                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
	                    }
	
	                    if (!matched)
	                    {
	                        return false;
	                    }
	                } else if (target instanceof OreDictItemData)
	                {
	                	OreDictItemData tData = OM.data(slot);
	                	OreDictItemData oData = (OreDictItemData)target;
	                	if (tData != null && oData.mPrefix == tData.mPrefix)
						{
							if (!compareMats(oData.mMaterial.mMaterial, tData.mMaterial.mMaterial))
							{
								return false;
							}
						}
	                }
	            }
	        }
	
	        return true;
    	} catch (Throwable e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    public HammerableShapedRecipe setMirrored(boolean mirror)
    {
        mirrored = mirror;
        return this;
    }

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     * @return The recipes input vales.
     */
    public Object[] getInput()
    {
        return this.input;
    }
    
	@Override
	public boolean isRemovableByGT() {
		return false;
	}
	@Override
	public boolean isAutocraftableByGT() {
		return false;
	}

}
