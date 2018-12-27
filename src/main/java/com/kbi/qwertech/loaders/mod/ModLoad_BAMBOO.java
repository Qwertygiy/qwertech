package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;
import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModLoad_BAMBOO extends ModLoadBase {
    @Override
    public ModData getMod() {
        return MD.Bamboo;
    }

    @Override
    public void addOreDict() {
        ItemStack sakuraPlank = ST.make(MD.Bamboo, "twoDirDeco", 1, 2);
        if (ST.valid(sakuraPlank))
        {
            OreDictionary.registerOre("plankWoodSakura", sakuraPlank);
        }
    }
}
