package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.data.WOOD;

import gregapi.code.ModData;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModLoad_NAT extends ModLoadBase {

	@Override
	public ModData getMod() {
		return WOOD.NAT;
	}
	
	@Override
	public void addOreDict()
	{
		String mId = this.getMod().mID;
		String[] oreNames = new String[]{"Eucalyptus", "Sakura", "Ghostwood", "Redwood", "Bloodwood", "Hopseed", "Maple", "Silverbell", "Purpleheart", "Tiger", "Willow", "Dark", "Fusewood"};
		String[] blockNames = new String[]{"natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks", "natura.planks"};
		int[] meta = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
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