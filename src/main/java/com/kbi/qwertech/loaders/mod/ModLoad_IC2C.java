package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterialStack;

public class ModLoad_IC2C extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.IC2C;
	}
	
	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolWrench"), new OreDictMaterialStack(MT.Bronze, CS.U * 6)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolCutter"), new OreDictMaterialStack(MT.Fe, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorAllowChestplate"), new OreDictMaterialStack(MT.BlackSteel, CS.U * 6)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorAlloyBoots"), new OreDictMaterialStack(MT.BlackSteel, CS.U * 4)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorAlloyHelmet"), new OreDictMaterialStack(MT.BlackSteel, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorAlloyLeggings"), new OreDictMaterialStack(MT.BlackSteel, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorRubHelmet"), new OreDictMaterialStack(MT.Rubber, CS.U * 4)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorRubChestplate"), new OreDictMaterialStack(MT.Rubber, CS.U * 6)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorRubLeggings"), new OreDictMaterialStack(MT.Rubber, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorRubBoots"), new OreDictMaterialStack(MT.Rubber, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorBronzeHelmet"), new OreDictMaterialStack(MT.Bronze, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorBronzeChestplate"), new OreDictMaterialStack(MT.Bronze, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorBronzeLeggings"), new OreDictMaterialStack(MT.Bronze, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemArmorBronzeBoots"), new OreDictMaterialStack(MT.Bronze, CS.U * 4)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolBronzePickaxe"), new OreDictMaterialStack(MT.Bronze, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolBronzeAxe"), new OreDictMaterialStack(MT.Bronze, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolBronzeShovel"), new OreDictMaterialStack(MT.Bronze, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolBronzeHoe"), new OreDictMaterialStack(MT.Bronze, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(getMod().mID, "itemToolBronzeSword"), new OreDictMaterialStack(MT.Bronze, CS.U * 2)));
			
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
