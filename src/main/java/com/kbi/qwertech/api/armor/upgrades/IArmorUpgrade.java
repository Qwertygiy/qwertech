package com.kbi.qwertech.api.armor.upgrades;

import java.util.List;

import gregapi.oredict.OreDictMaterial;

import com.kbi.qwertech.api.armor.IArmorStats;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IArmorUpgrade extends IArmorStats {
	
	/**
	 * The multiplier of this item's material-amount to calculate weight. Default is 1.0.
	 *
	 */
	public double getWeightAdded();
	
	/**
	 * The main material of this upgrade, used for weight calculations.
	 */
	public OreDictMaterial getMaterial();
	
	/**
	 * Sets the main material of this upgrade, used for weight calculations.
	 */
	public boolean setMaterial(OreDictMaterial mat);
	
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack);
	
	/**
	 * Whether or not this upgrade is compatible with this piece of armor
	 * @param aStack The MultiItemArmor itemstack being checked.
	 * @return True if this upgrade can be applied.
	 */
	public boolean isCompatibleWith(ItemStack aStack);
	
	/**
	 * Gets the itemstack representing this upgrade that will be visible/removable from this specific armor.
	 * @param aStack The MultiItemArmor itemstack being checked.
	 * @return The upgrade ItemStack.
	 */
	public ItemStack getUpgradeStack(ItemStack aStack);
	
	/**
	 * Adds an itemstack to represent this upgrade for crafting. NO ITEMSTACK SHOULD REPRESENT MORE THAN A SINGLE UPGRADE.
	 * @param aStack The itemstack to be used as an upgrade item.
	 */
	public void addUpgradeStack(ItemStack aStack);
	
	/**
	 * Returns all itemstacks that represent this upgrade.
	 * @return
	 */
	public List<ItemStack> getUpgradeStacks();
}