package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;
import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModLoad_BINNIE_TREE extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.BINNIE_TREE;
	}
	
	@Override
	public void addOreDict()
	{
		String mId = this.getMod().mID;
		String[] oreNames = new String[]{"Fir", "Cedar", "Hemlock", "Cypress", "Fig", "Beech", "Alder", "Hazel", "Hornbeam", "Box", "Butternut", "Hickory", "Whitebeam", "Elm", "Apple", "Yew", "Pear", "Hawthorn", "Rowan", "Elder", "Maclura", "Syzgium", "Brazilwood", "Logwood", "Iroko", "Locust", "Eucalyptus", "Purpleheart", "Ash", "Holly", "Olive", "Sweetgum", "Rosewood", "Gingko", "PinkIvory"};
		String[] blockNames = new String[]{"planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks"};
		int[] meta = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
		for (int q = 0; q < oreNames.length; q++)
		{
			Block result = GameRegistry.findBlock(mId, blockNames[q]);
			if (result != null)
			{
				OreDictionary.registerOre("plankWood" + oreNames[q], ST.make(result, 1, meta[q]));
			}
		}
	}

}