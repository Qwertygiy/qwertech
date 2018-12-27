package com.kbi.qwertech.loaders.mod;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ModData;
import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

public class ModLoad_TFC extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.TFC;
	}
	
	@Override
	public void addOreDict()
	{
		String mId = this.getMod().mID;
		String[] oreNames = new String[]{"Oak", "Aspen", "Birch", "Chestnut", "DouglasFir", "Hickory", "Maple", "Ash", "Pine", "Sequoia", "Spruce", "Sycamore", "WhiteCedar", "WhiteElm", "Willow", "Kapok", "Acacia"};
		String[] blockNames = new String[]{"planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks", "planks2"};
		int[] meta = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
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