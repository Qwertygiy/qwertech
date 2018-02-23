package com.kbi.qwertech.api.recipe;

import gregapi.code.ICondition;
import gregapi.data.MT;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTI;

public class QTArmor implements ICraftingRecipeGT, IRecipe
{
  public final ICondition mCondition;
  public final short mArmorID;
  
  public static int MAX_CRAFT_GRID_WIDTH = 3;
  public static int MAX_CRAFT_GRID_HEIGHT = 3;
  
  public final Object[] input;
  public final OreDictMaterial primaryMaterial;
  
  public OreDictMaterial tempPrimary;
  
  public int width = 0;
  public int height = 0;
  private boolean mirrored = true;
  
	public QTArmor(long aArmorID,								ICondition condition,	Object... items					) { this(aArmorID,	MT.NULL, 	condition,		 	items);}
	public QTArmor(long aArmorID,  							 							Object... items					) { this(aArmorID, 	MT.NULL, 	ICondition.TRUE, 	items);}
	public QTArmor(long aArmorID,	OreDictMaterial material, 							Object... items					) { this(aArmorID, 	material, 	ICondition.TRUE, 	items);}
	
	public QTArmor(long aArmorID, 	OreDictMaterial material, 	ICondition condition, 											Object[] recipe) {
	  //super(QwerTech.qwerArmor.getArmorWithStats(UT.Code.bind15(aArmorID), primary, secondary), stringify(recipes));
	  this.mCondition = condition;
	  this.mArmorID = UT.Code.bind15(aArmorID);
	  this.primaryMaterial = material;
	  this.tempPrimary = material;
	  
	  String shape = "";
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
              shape += s;
          }

          height = parts.length;
      }
      else
      {
          while (recipe[idx] instanceof String)
          {
              String s = (String)recipe[idx++];
              shape += s;
              width = s.length();
              height++;
          }
      }

      if (width * height != shape.length())
      {
          String ret = "Invalid armor recipe: ";
          for (Object tmp :  recipe)
          {
              ret += tmp + ", ";
          }
          ret += aArmorID;
          throw new RuntimeException(ret);
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
              String ret = "Invalid armor recipe: ";
              for (Object tmp :  recipe)
              {
                  ret += tmp + ", ";
              }
              ret += aArmorID;
              throw new RuntimeException(ret);
          }
      }

      input = new Object[width * height];
      int x = 0;
      for (char chr : shape.toCharArray())
      {
          input[x++] = itemMap.get(chr);
      }
  }
  
  public static Object[] stringify(Object[] ODs)
  {
	  String ret = "Adding QTArmor3D recipe: ";
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
				  returnable[i] = ((OreDictItemData)ODs[i]).toString();
			  }
			  
		  } else {
			  returnable[i] = ODs[i];
		  }
		  ret += returnable[i] + ", ";
	  }
	  System.out.println(ret);
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
			} else if (p2 == this.tempPrimary || p2.mToThis.contains(this.tempPrimary) || this.tempPrimary.mToThis.contains(p2) || p2.mReRegistrations.contains(this.tempPrimary) || this.tempPrimary.mReRegistrations.contains(p2))
			{
				return true;
			}
			return false;
		}
		return false;
	}
  
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
	                    if (!ST.equal((ItemStack)target, slot))
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
						} else {
							return false;
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
  
  	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
  		return matches(aGrid);
	}
  
  
  	@Override
  	public ItemStack getCraftingResult(InventoryCrafting aGrid)
  	{
  		return ((MultiItemArmor)QTI.qwerArmor.getItem()).getArmorWithStats(this.mArmorID, this.tempPrimary);
  	}
  
	@Override
	public ItemStack getRecipeOutput()
	{
	  return ((MultiItemArmor)QTI.qwerArmor.getItem()).getArmorWithStats(this.mArmorID, this.tempPrimary);
	}
	
	public QTArmor setMirrored(boolean mirror)
    {
        mirrored = mirror;
        return this;
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
		return input.length;
	}
}
