package com.kbi.qwertech.api.registry;

import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobBreedRegistry {
	
	private static HashMap<Item, List<Object>> hash = new HashMap();
	
	public static boolean addItemStack(Item original, ItemStack addition)
	{
		return addObject(original, addition);
	}
	
	public static boolean addItem(Item original, Item addition)
	{
		return addObject(original, ST.make(addition, 1, 0));
	}
	
	public static boolean addOreDict(Item original, String addition)
	{
		return addObject(original, addition);
	}
	
	private static boolean addObject(Item original, Object addition)
	{
		try
		{
			List addingTo = hash.get(original);
		    if (addingTo == null)
		    {
		        addingTo = new ArrayList();
		        hash.put(original, addingTo);
		    }
		    addingTo.add(addition);
			return true;
		} catch (Exception ignored){

		}
		return false;
	}
	
	public static List<Object> getItemReplacements(Item original)
	{
		return hash.get(original);
	}
	
	public static HashMap<Item, List<Object>> getMap()
	{
		return hash;
	}

	public static boolean isBreedingItem(EntityAnimal entity, ItemStack stack)
	{
		if (entity.isBreedingItem(stack)) return true;
		ItemStack[] defaults = new ItemStack[]{ST.make(Items.wheat, 1, 0), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.carrot, 1, 0), ST.make(Items.fish, 1, 0), ST.make(Items.porkchop, 1, 0)};
		for (ItemStack stackle : defaults)
		{
			if (entity.isBreedingItem(stackle))
			{
				List<Object> replaces = getItemReplacements(stackle.getItem());
				for (Object q : replaces)
				{
					if (q instanceof ItemStack)
					{
						if (ST.equal(stack, (ItemStack)q))
						{
							return true;
						}
					} else if (q instanceof String)
					{
						if (OM.is(q, stack))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
