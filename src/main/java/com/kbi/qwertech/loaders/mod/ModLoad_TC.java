package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

public class ModLoad_TC extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.TC;
	}
	
	@Override
	public void addOreDict()
	{
		String mId = this.getMod().mID;
		String[] oreNames = new String[]{"Greatwood", "Silverwood"};
		String[] blockNames = new String[]{"blockWoodenDevice", "blockWoodenDevice"};
		int[] meta = new int[]{6, 7};
		for (int q = 0; q < oreNames.length; q++)
		{
			Block result = GameRegistry.findBlock(mId, blockNames[q]);
			if (result != null)
			{
				OreDictionary.registerOre("plankWood" + oreNames[q], ST.make(result, 1, meta[q]));
			}
		}
	}
	
	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemHoeThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemPickThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemSwordThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemShovelThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemAxeThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemHelmetThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemChestplateThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemLeggingsThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemBootsThaumium"), new OreDictMaterialStack(MT.Thaumium, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemHoeVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemPickVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemSwordVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemShovelVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemAxeVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemHelmetVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemChestplateVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemLeggingsVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "ItemBootsVoid"), new OreDictMaterialStack(MT.VoidMetal, CS.U * 4)));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}