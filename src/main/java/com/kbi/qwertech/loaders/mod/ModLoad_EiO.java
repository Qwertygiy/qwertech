package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterialStack;

public class ModLoad_EiO extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.EIO;
	}
	
	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemYetaWrench"), new OreDictMaterialStack(MT.ElectricalSteel, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_helmet"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_chestplate"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_leggings"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_boots"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 4)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_sword"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_pickaxe"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_axe"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "item.darkSteel_shears"), new OreDictMaterialStack(MT.ObsidianSteel, CS.U * 2)));
			
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
