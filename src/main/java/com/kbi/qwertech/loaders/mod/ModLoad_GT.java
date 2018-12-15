package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.QTConfigs;
import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.MD;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;

public class ModLoad_GT extends ModLoadBase {

	@Override
	public ModData getMod() {
		return MD.GT;
	}
	
	@Override
	public void tweakRecipes()
	{
		if (QTConfigs.removeVanillaCrafting)
		{
			CR.remout(ST.make(Blocks.crafting_table, 1, 0));
		} else {
			CR.remout(QwerTech.machines.getItem(0));
		}
	}
	
	@Override
	public void addOreDict()
	{
		OreDictionary.registerOre("plankWoodOak", ST.make(Blocks.planks, 1, 0));
		OreDictionary.registerOre("plankWoodSpruce", ST.make(Blocks.planks, 1, 1));
		OreDictionary.registerOre("plankWoodBirch", ST.make(Blocks.planks, 1, 2));
		OreDictionary.registerOre("plankWoodJungle", ST.make(Blocks.planks, 1, 3));
		OreDictionary.registerOre("plankWoodAcacia", ST.make(Blocks.planks, 1, 4));
		OreDictionary.registerOre("plankWoodDarkOak", ST.make(Blocks.planks, 1, 5));
		OreDictionary.registerOre("plankWoodRubber", ST.make(CS.BlocksGT.Planks, 1, 0));
		OreDictionary.registerOre("plankWoodRubber", ST.make(CS.BlocksGT.PlanksFireProof, 1, 0));
		OreDictionary.registerOre("plankWoodMaple", ST.make(CS.BlocksGT.Planks, 1, 1));
		OreDictionary.registerOre("plankWoodMaple", ST.make(CS.BlocksGT.PlanksFireProof, 1, 1));
		OreDictionary.registerOre("plankWoodWillow", ST.make(CS.BlocksGT.Planks, 1, 2));
		OreDictionary.registerOre("plankWoodWillow", ST.make(CS.BlocksGT.PlanksFireProof, 1, 2));
		OreDictionary.registerOre("plankWoodBlueMahoe", ST.make(CS.BlocksGT.Planks, 1, 3));
		OreDictionary.registerOre("plankWoodBlueMahoe", ST.make(CS.BlocksGT.PlanksFireProof, 1, 3));
		OreDictionary.registerOre("plankWoodHazel", ST.make(CS.BlocksGT.Planks, 1, 4));
		OreDictionary.registerOre("plankWoodHazel", ST.make(CS.BlocksGT.PlanksFireProof, 1, 4));
		OreDictionary.registerOre("plankWoodCinnamon", ST.make(CS.BlocksGT.Planks, 1, 5));
		OreDictionary.registerOre("plankWoodCinnamon", ST.make(CS.BlocksGT.PlanksFireProof, 1, 5));
		OreDictionary.registerOre("plankWoodRainbowood", ST.make(CS.BlocksGT.Planks, 1, 7));
		OreDictionary.registerOre("plankWoodRainbowood", ST.make(CS.BlocksGT.PlanksFireProof, 1, 7));
		OreDictionary.registerOre("plankWoodCompressed", ST.make(CS.BlocksGT.Planks, 1, 8));
		OreDictionary.registerOre("plankWoodCompressed", ST.make(CS.BlocksGT.PlanksFireProof, 1, 8));
		OreDictionary.registerOre("plankWoodSealed", ST.make(CS.BlocksGT.Planks, 1, 10));
		OreDictionary.registerOre("plankWoodSealed", ST.make(CS.BlocksGT.PlanksFireProof, 1, 10));
		OreDictionary.registerOre("plankWoodDead", ST.make(CS.BlocksGT.Planks, 1, 12));
		OreDictionary.registerOre("plankWoodDead", ST.make(CS.BlocksGT.PlanksFireProof, 1, 12));
		OreDictionary.registerOre("plankWoodRotten", ST.make(CS.BlocksGT.Planks, 1, 13));
		OreDictionary.registerOre("plankWoodRotten", ST.make(CS.BlocksGT.PlanksFireProof, 1, 13));
		OreDictionary.registerOre("plankWoodMossy", ST.make(CS.BlocksGT.Planks, 1, 14));
		OreDictionary.registerOre("plankWoodMossy", ST.make(CS.BlocksGT.PlanksFireProof, 1, 14));
		OreDictionary.registerOre("plankWoodFrozen", ST.make(CS.BlocksGT.Planks, 1, 15));
		OreDictionary.registerOre("plankWoodFrozen", ST.make(CS.BlocksGT.PlanksFireProof, 1, 15));
	}

}
