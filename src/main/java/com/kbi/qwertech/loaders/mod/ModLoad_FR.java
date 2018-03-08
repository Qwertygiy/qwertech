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

public class ModLoad_FR extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.FR;
	}
	
	@Override
	public void addOreDict()
	{
		String mId = this.getMod().mID;
		String[] oreNames = new String[]{"Larch",  "Teak",  "Acacia",  "Lime", "Chestnut",  "Wenge", "Baobab",  "Sequoia",  "Kapok", "Ebony", "Mahogany", "Balsa",  "Willow", "Walnut", "Greenheart", "Cherry", "Mahoe", "Poplar", "Palm", "Papaya", "Pine", "Plum", "Maple", "Citrus", "Giganteum", "Ipe", "Padauk", "Cocobolo", "Zebrawood"};
		String[] blockNames = new String[]{"planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks"};
		int[] meta = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
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
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "bronzeShovel"), new OreDictMaterialStack(MT.Bronze, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "bronzePickaxe"), new OreDictMaterialStack(MT.Bronze, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "wrench"), new OreDictMaterialStack(MT.Bronze, CS.U * 4)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "grafter"), new OreDictMaterialStack(MT.Bronze, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "grafterProven"), new OreDictMaterialStack(MT.StainlessSteel, CS.U)));
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}