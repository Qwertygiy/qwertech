package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.ST;

public class ModLoad_RFTOOLS extends ModLoadBase {
    @Override
    public ModData getMod() {
        return new ModData("rftools", "RFTools");
    }

    @Override
    public void tweakRecipes() {
        CR.remout(ST.make(this.getMod(), "machineFrame", 1));
        CR.remout(ST.make(this.getMod(), "machineBase", 1));
        CR.shaped(ST.make(this.getMod(), "machineFrame", 1), new Object[]{" 1 ", "232", " 1 ", '1', OP.wireFine.mat(MT.RedAlloy, 1), '2', OP.foil.mat(MT.Au, 1), '3', OP.casingMachine.mat(MT.Fe, 1)});
        CR.shaped(ST.make(this.getMod(), "machineBase", 1), new Object[]{"121", "333", '1', OP.wireFine.mat(MT.RedAlloy, 1), '2', OP.foil.mat(MT.Au, 1), '3', OP.plateDouble.mat(MT.Fe, 1)});
    }
}
