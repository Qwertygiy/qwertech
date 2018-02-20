package com.kbi.qwertech.api.registry;

import gregapi.util.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
		} catch (Exception e){

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
