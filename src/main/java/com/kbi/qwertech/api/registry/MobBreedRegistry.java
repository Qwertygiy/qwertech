package com.kbi.qwertech.api.registry;

import gregapi.util.ST;
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
}
