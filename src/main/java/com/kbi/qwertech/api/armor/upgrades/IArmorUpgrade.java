package com.kbi.qwertech.api.armor.upgrades;

import com.kbi.qwertech.api.armor.IArmorStats;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IArmorUpgrade extends IArmorStats {
	
	/**
	 * The multiplier of this item's material-amount to calculate weight. Default is 1.0.
	 *
	 */
    double getWeightAdded();
	
	/**
	 * The main material of this upgrade, used for weight calculations.
	 */
    OreDictMaterial getMaterial();
	
	/**
	 * Sets the main material of this upgrade, used for weight calculations.
	 */
    boolean setMaterial(OreDictMaterial mat);
	
	List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack);
	
	/**
	 * Whether or not this upgrade is compatible with this piece of armor
	 * @param aStack The MultiItemArmor itemstack being checked.
	 * @return True if this upgrade can be applied.
	 */
    boolean isCompatibleWith(ItemStack aStack);
	
	/**
	 * Gets the itemstack representing this upgrade that will be visible/removable from this specific armor.
	 * @param aStack The MultiItemArmor itemstack being checked.
	 * @return The upgrade ItemStack.
	 */
    ItemStack getUpgradeStack(ItemStack aStack);
	
	/**
	 * Adds an itemstack to represent this upgrade for crafting. NO ITEMSTACK SHOULD REPRESENT MORE THAN A SINGLE UPGRADE.
	 * @param aStack The itemstack to be used as an upgrade item.
	 */
    void addUpgradeStack(ItemStack aStack);
	
	/**
	 * Returns all itemstacks that represent this upgrade.
	 * @return
	 */
    List<ItemStack> getUpgradeStacks();
}