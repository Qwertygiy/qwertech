package com.kbi.qwertech.loaders;

import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;

public class RegisterLoot {
	
	public String lootType;
	public static HashMap<String, RegisterLoot> dungeonMap = new HashMap();
	
	public RegisterLoot(String type)
	{
		this.lootType = type;
		dungeonMap.put(type, this);
	}
	
	public RegisterLoot addItem(ItemStack item)
	{
		return this.addItem(item, 1, 1, 1);
	}
	
	public RegisterLoot addItem(ItemStack item, int chances)
	{
		return this.addItem(item, 1, 1, chances);
	}
	
	public RegisterLoot addItem(ItemStack item, int max, int chances)
	{
		return this.addItem(item, 1, max, chances);
	}
	
	public RegisterLoot addItem(ItemStack item, int min, int max, int chances)
	{
		ChestGenHooks.addItem(this.lootType, new WeightedRandomChestContent(item, min, max, chances));
		return this;
	}
	
	public static void init()
	{
		if (!(QTConfigs.addDungeonTools)) return;
		OreDictPrefix star = OreDictPrefix.get("shuriken");
		OreDictMaterial[] mats = new OreDictMaterial[]{MT.Steel, MT.Bronze, MT.BlackSteel, MT.BlackBronze, MT.WroughtIron, MT.Au, MT.Ag};
		MultiItemTool gt = CS.ToolsGT.sMetaTool;
		MultiItemTool qt = (MultiItemTool)QTI.qwerTool.getItem();
		
		RegisterLoot mineshaft = new RegisterLoot("mineshaftCorridor");
		RegisterLoot jungleShooter = new RegisterLoot("pyramidJungleDispenser").addItem(star.mat(MT.Steel, 1), 16, 2).addItem(star.mat(MT.Ag, 1), 16, 1).addItem(star.mat(MT.Pb, 1), 16, 1).addItem(star.mat(MT.Bronze, 1), 16, 2).addItem(star.mat(MT.Ti, 1), 16, 1);
		RegisterLoot jungleChest = new RegisterLoot("pyramidJungleChest");
		RegisterLoot desertChest = new RegisterLoot("pyramidDesertyChest");
		RegisterLoot dungeonChest = new RegisterLoot("dungeonChest");
		RegisterLoot village = new RegisterLoot("villageBlacksmith").addItem(star.mat(MT.Steel, 1), 16, 2).addItem(star.mat(MT.Fe, 1), 16, 2).addItem(star.mat(MT.Bronze, 1), 16, 1).addItem(star.mat(MT.DamascusSteel, 1), 16, 1).addItem(star.mat(MT.BlueSteel, 1), 16, 1).addItem(star.mat(MT.RedSteel, 1), 16, 1);
		RegisterLoot library = new RegisterLoot("strongholdLibrary");
		RegisterLoot strong1 = new RegisterLoot("strongholdCorridor");
		RegisterLoot strong2 = new RegisterLoot("strongholdCrossing");
		
		for (int q = 0; q < mats.length; q++)
		{
			village.addItem(gt.getToolWithStats(2, mats[q], mats[q].mHandleMaterial)).addItem(gt.getToolWithStats(58, mats[q], mats[q].mHandleMaterial)).addItem(gt.getToolWithStats(12, mats[q], mats[q].mHandleMaterial)).addItem(gt.getToolWithStats(36, mats[q], mats[q].mHandleMaterial)).addItem(gt.getToolWithStats(0, mats[q], mats[q].mHandleMaterial)).addItem(qt.getToolWithStats(0, mats[q], mats[q].mHandleMaterial)).addItem(qt.getToolWithStats(6, mats[q], mats[q].mHandleMaterial)).addItem(qt.getToolWithStats(12, MT.Brass, mats[q]));
			ItemStack damagedSturdy = qt.getToolWithStats(18, mats[q], mats[q].mHandleMaterial);
			mineshaft.addItem(damagedSturdy.copy());
			qt.doDamage(damagedSturdy, CS.RANDOM.nextInt((int)MultiItemTool.getToolMaxDamage(damagedSturdy)/2));
			mineshaft.addItem(damagedSturdy);
			strong1.addItem(gt.getToolWithStats(0, mats[q], mats[q].mHandleMaterial)).addItem(gt.getToolWithStats(6, mats[q], mats[q].mHandleMaterial));
		}
	}
	
	public static RegisterLoot get(String key)
	{
		if (dungeonMap.containsKey(key)) return dungeonMap.get(key);
		return new RegisterLoot(key);
	}
}
