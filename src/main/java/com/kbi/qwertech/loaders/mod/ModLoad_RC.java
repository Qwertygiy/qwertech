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

public class ModLoad_RC extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.RC;
	}
	
	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.steel.shears"), new OreDictMaterialStack(MT.Steel, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.steel.sword"), new OreDictMaterialStack(MT.Steel, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.steel.shovel"), new OreDictMaterialStack(MT.Steel, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.steel.pickaxe"), new OreDictMaterialStack(MT.Steel, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.steel.hoe"), new OreDictMaterialStack(MT.Steel, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.steel.axe"), new OreDictMaterialStack(MT.Steel, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "armor.steel.helmet"), new OreDictMaterialStack(MT.Steel, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "armor.steel.plate"), new OreDictMaterialStack(MT.Steel, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "armor.steel.legs"), new OreDictMaterialStack(MT.Steel, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "armor.steel.boots"), new OreDictMaterialStack(MT.Steel, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.crowbar"), new OreDictMaterialStack(ANY.Iron, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "tool.crowbar.reinforced"), new OreDictMaterialStack(ANY.Steel, CS.U * 3)));
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
