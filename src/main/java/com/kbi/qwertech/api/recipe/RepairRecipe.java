package com.kbi.qwertech.api.recipe;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RepairRecipe implements ICraftingRecipeGT, IRecipe
{
	
	Item input;
	OreDictMaterialStack amountNeeded;
	boolean useVanillaDurability;
	
	public RepairRecipe(Item toRepair, OreDictMaterialStack amountMade)
	{
		if (toRepair == null)
		{
			System.out.println("Failed to create repair recipe, Item doesn't exist");
		} else {
			if (toRepair instanceof MultiItemArmor || toRepair instanceof MultiItemTool) {
				input = toRepair;
				useVanillaDurability = false;
			} else if (toRepair.isDamageable()) {
				input = toRepair;
				useVanillaDurability = true;
				if (amountMade != null && amountMade.mAmount > 0 && amountMade.mMaterial != MT.NULL) {
					amountNeeded = amountMade;
				} else {
					System.out.println("RepairRecipe cannot use provided materialstack for " + toRepair.getUnlocalizedName());
				}
			} else {
				System.out.println("RepairRecipe is not compatible with " + toRepair.getUnlocalizedName());
			}
		}
	}

	@Override
	public boolean matches(InventoryCrafting inventory, World p_77569_2_) {
		return returnCraftingResult(inventory, false) != null;
	}
	
	public ItemStack returnCraftingResult(InventoryCrafting inventory, boolean destroy)
	{
		ItemStack toRepair = null;
		int repairSlot = -1;
		int nugs = 0;
		OreDictMaterial plateMat = MT.NULL;
		for (int q = 0; q < inventory.getSizeInventory(); q++)
		{
			ItemStack inSlot = inventory.getStackInSlot(q);
			if (inSlot != null)
			{
				if (inSlot.getItem() == input)
				{
					if (toRepair != null)
					{
						return null;
					} else {
						toRepair = inSlot;
						repairSlot = q;
					}
				} else {
					OreDictItemData tData = OreDictManager.INSTANCE.getItemData(inSlot);
					if (tData != null)
					{
						if (tData.mPrefix == OP.plateTiny)
						{
							if (plateMat != MT.NULL && tData.mMaterial.mMaterial != plateMat)
							{
								return null;
							}
							plateMat = tData.mMaterial.mMaterial;
							nugs = nugs + 2;
						} else if (tData.mPrefix == OP.plate)
						{
							if (plateMat != MT.NULL && tData.mMaterial.mMaterial != plateMat)
							{
								return null;
							}
							plateMat = tData.mMaterial.mMaterial;
							nugs = nugs + 15;
						} else {
							return null;
						}
					} else {
						return null;
					}
				}
			}
		}
		if (toRepair != null && nugs > 0)
		{
			if (useVanillaDurability)
			{
				if (amountNeeded != null)
				{
					if (plateMat != amountNeeded.mMaterial)
					{
						return null;
					}
					int totalDamage = toRepair.getMaxDamage();
					int currentDamage = toRepair.getItemDamage();
					int totalMaterial = (int)amountNeeded.mAmount;
					int addedMaterial = (int)(nugs * (CS.U / 9));
					double reducedDamage = currentDamage - (((double)addedMaterial/(double)totalMaterial) * totalDamage);
					
					//System.out.println("Max damage: " + totalDamage + "| Current damage: " + currentDamage + "| Total material: " + totalMaterial + "| Added material: " + addedMaterial + "|Reduced: " + reducedDamage);
					
					ItemStack returnable = toRepair.copy();
					
					returnable.setItemDamage(reducedDamage < 0 ? (int)reducedDamage : 0);
					//if (destroy) inventory.setInventorySlotContents(repairSlot, null);
					return returnable;
				}
			} else
			{
				if (input instanceof MultiItemArmor)
				{
					if (plateMat != MultiItemArmor.getPrimaryMaterial(toRepair))
					{
						return null;
					}
					IArmorStats tStats = ((MultiItemArmor)input).getArmorStats(toRepair);
					if (tStats != null)
					{
						long totalDamage = MultiItemArmor.getArmorMaxDamage(toRepair);
						long currentDamage = MultiItemArmor.getArmorDamage(toRepair);
						long totalMaterial = tStats.getMaterialAmount();
						long addedMaterial = (nugs * (CS.U9));
						double reducedDamage = currentDamage - (((double)addedMaterial/(double)totalMaterial) * totalDamage);
						//System.out.println("Max damage " + totalDamage + " current damage " + currentDamage + " total material " + totalMaterial + " amount given " + addedMaterial + " via " + nugs + " nugs, and reduced amount is " + reducedDamage);
						//System.out.println("CS.U: " + CS.U + " CS.U * 8: " + CS.U * 8);
						ItemStack returnable = toRepair.copy();
						MultiItemArmor.setArmorDamage(returnable, reducedDamage > 0 ? (long)reducedDamage : 0);
						//if (destroy) inventory.setInventorySlotContents(repairSlot, null);
						return returnable;
					}
				} else if (input instanceof MultiItemTool)
				{
					if (plateMat != MultiItemTool.getPrimaryMaterial(toRepair))
					{
						return null;
					}
					IToolStats tStats = ((MultiItemTool)input).getToolStats(toRepair);
					if (tStats instanceof ToolStats)
					{
						long totalDamage = MultiItemTool.getToolMaxDamage(toRepair);
						long currentDamage = MultiItemTool.getToolDamage(toRepair);
						
						Object totallyMaterial = UT.Reflection.getFieldContent(tStats, "mMaterialAmount", true, false);
						long totalMaterial = CS.U * 5;
						
						if (totallyMaterial instanceof Long)
						{
							totalMaterial = (Long)totallyMaterial;
						}
						
						long addedMaterial = (nugs * (CS.U9));
						double reducedDamage = currentDamage - (((double)addedMaterial/(double)totalMaterial) * totalDamage);
						ItemStack returnable = toRepair.copy();
						MultiItemTool.setToolDamage(returnable, reducedDamage > 0 ? (long)reducedDamage : 0);
						//if (destroy) inventory.setInventorySlotContents(repairSlot, null);
						return returnable;
					}
				}
			}
		}
		return null;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		return returnCraftingResult(inventory, true);
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ST.make(input, 1, CS.W);
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
