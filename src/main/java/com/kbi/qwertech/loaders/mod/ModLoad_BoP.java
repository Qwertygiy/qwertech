package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;
import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModLoad_BoP extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.BoP;
	}
	
	@Override
	public void addOreDict()
	{
		String mId = this.getMod().mID;
		String[] oreNames = new String[]{"SacredOak", "Cherry", "Dark", "Fir", "Ethereal", "Magic", "Mangrove", "Palm", "Redwood", "Willow", "Pine", "Jacaranda", "Mahogany"};
		String[] blockNames = new String[]{"planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks"};
		int[] meta = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 14};
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
