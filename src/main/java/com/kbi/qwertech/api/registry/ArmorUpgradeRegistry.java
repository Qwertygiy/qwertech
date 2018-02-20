package com.kbi.qwertech.api.registry;

import gregapi.util.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.kbi.qwertech.api.armor.upgrades.IArmorUpgrade;

public class ArmorUpgradeRegistry {

	public static ArmorUpgradeRegistry instance;
	
	public static final HashMap<Integer, IArmorUpgrade> upgradeList = new HashMap(Short.MAX_VALUE);
	public static final HashMap<ItemStack, Integer> upgradeItems = new HashMap();
	/**
	 * Registry of all QT armor upgrade items.
	 */
	public ArmorUpgradeRegistry() {
		instance = this;
	}
	
	public boolean addUpgrade(int ID, IArmorUpgrade upgrade)
	{
		if (upgradeList.containsKey(ID)) return false;
		upgradeList.put(ID, upgrade);
		List<ItemStack> stacks = upgrade.getUpgradeStacks();
		for (ItemStack stack : stacks)
		{
			upgradeItems.put(stack, ID);
		}
		return true;
	}
	
	public IArmorUpgrade getUpgrade(int ID)
	{
		if (!upgradeList.containsKey(ID)) return null;
		return upgradeList.get(ID);
	}
	
	public IArmorUpgrade getUpgrade(ItemStack stack)
	{
		for (ItemStack stacker : upgradeItems.keySet())
		{
			if (ST.equal(stack, stacker, true))
			{
				int ID = upgradeItems.get(stacker);
				if (upgradeList.containsKey(ID))
				{
					return upgradeList.get(ID);
				}
			}
		}
		return null;
	}

}
