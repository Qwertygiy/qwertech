package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ModData;
import gregapi.data.ANY;
import gregapi.data.CS;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterialStack;

public class ModLoad_GC extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.GC;
	}
	
	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "steel_pickaxe"), new OreDictMaterialStack(ANY.Steel, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "steel_axe"), new OreDictMaterialStack(ANY.Steel, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "steel_hoe"), new OreDictMaterialStack(ANY.Steel, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "steel_shovel"), new OreDictMaterialStack(ANY.Steel, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "steel_sword"), new OreDictMaterialStack(ANY.Steel, CS.U * 2)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "helmet"), new OreDictMaterialStack(ANY.Steel, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "chestplate"), new OreDictMaterialStack(ANY.Steel, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "leggings"), new OreDictMaterialStack(ANY.Steel, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "boots"), new OreDictMaterialStack(ANY.Steel, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "standardWrench"), new OreDictMaterialStack(MT.BlackSteel, CS.U * 3)));		
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
