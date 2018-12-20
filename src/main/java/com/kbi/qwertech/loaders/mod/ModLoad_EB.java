package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModLoad_EB extends ModLoadBase {
    @Override
    public ModData getMod() {
        return MD.EB;
    }

    @Override
    public void addOreDict() {
        Block tPlank = ST.block(MD.EB, "enhancedbiomes.tile.planksEB");
        if (tPlank != null)
        {
            OreDictionary.registerOre("plankWoodGreatwood", ST.make(tPlank, 1, 0));
            OreDictionary.registerOre("plankWoodThorntree", ST.make(tPlank, 1, 1));
            OreDictionary.registerOre("plankWoodPoplar", ST.make(tPlank, 1, 2));
            OreDictionary.registerOre("plankWoodMangrove", ST.make(tPlank, 1, 3));
            OreDictionary.registerOre("plankWoodFir", ST.make(tPlank, 1, 4));
            OreDictionary.registerOre("plankWoodCypress", ST.make(tPlank, 1, 5));
            OreDictionary.registerOre("plankWoodPine", ST.make(tPlank, 1, 6));
            OreDictionary.registerOre("plankWoodSilverPine", ST.make(tPlank, 1, 7));
            OreDictionary.registerOre("plankWoodAlder", ST.make(tPlank, 1, 8));
            OreDictionary.registerOre("plankWoodEucalyptus", ST.make(tPlank, 1, 9));
            OreDictionary.registerOre("plankWoodAspen", ST.make(tPlank, 1, 10));
            OreDictionary.registerOre("plankWoodBirch", ST.make(tPlank, 1, 11));
            OreDictionary.registerOre("plankWoodBaobab", ST.make(tPlank, 1, 12));
            OreDictionary.registerOre("plankWoodDead", ST.make(tPlank, 1, 13));
            OreDictionary.registerOre("plankWoodCherry", ST.make(tPlank, 1, 14));
            OreDictionary.registerOre("plankWoodKapok", ST.make(tPlank, 1, 15));
        }
    }
}
