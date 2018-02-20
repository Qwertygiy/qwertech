package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterialStack;

import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import com.kbi.qwertech.api.registry.MobScrapeRegistry;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModLoad_TE_FOUNDATION extends ModLoadBase {
	
	@Override
	public ModData getMod()
	{
		return MD.TE_FOUNDATION;
	}

	@Override
	public void registerMobScrapes()
	{
		try {
			Class Basalz = Class.forName("cofh.thermalfoundation.entity.monster.EntityBasalz");
			MobScrapeRegistry.registerMobKey(Basalz, "Basalz");
		} catch (Exception e) {
			System.out.println("Thermal Foundation was found but Basalzes weren't!");
		}
		try {
			Class Blitz = Class.forName("cofh.thermalfoundation.entity.monster.EntityBlitz");
			MobScrapeRegistry.registerMobKey(Blitz, "Blitz");
		} catch (Exception e) {
			System.out.println("Thermal Foundation was found but Blitzes weren't!");
		}
		try {
			Class Blizz = Class.forName("cofh.thermalfoundation.entity.monster.EntityBlizz");
			MobScrapeRegistry.registerMobKey(Blizz, "Blizz");
		} catch (Exception e) {
			System.out.println("Thermal Foundation was found but Blizzes weren't!");
		}
		MobScrapeRegistry.registerItem("Basalz", OP.dustSmall.mat(MT.Obsidian, 1));
		MobScrapeRegistry.registerItem("Blitz", OP.dustSmall.mat(MT.Niter, 1));
		MobScrapeRegistry.registerItem("Blizz", OP.dust.mat(MT.Snow, 1));
	}

	@Override
	public void registerMobGear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleIron"), new OreDictMaterialStack(MT.Fe, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleGold"), new OreDictMaterialStack(MT.Au, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsGold"), new OreDictMaterialStack(MT.Au, CS.U * 2)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleCopper"), new OreDictMaterialStack(MT.Cu, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetTin"), new OreDictMaterialStack(MT.Sn, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateTin"), new OreDictMaterialStack(MT.Sn, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsTin"), new OreDictMaterialStack(MT.Sn, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsTin"), new OreDictMaterialStack(MT.Sn, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordTin"), new OreDictMaterialStack(MT.Sn, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelTin"), new OreDictMaterialStack(MT.Sn, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeTin"), new OreDictMaterialStack(MT.Sn, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeTin"), new OreDictMaterialStack(MT.Sn, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeTin"), new OreDictMaterialStack(MT.Sn, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsTin"), new OreDictMaterialStack(MT.Sn, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleTin"), new OreDictMaterialStack(MT.Sn, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetSilver"), new OreDictMaterialStack(MT.Sn, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateSilver"), new OreDictMaterialStack(MT.Sn, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsSilver"), new OreDictMaterialStack(MT.Sn, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsSilver"), new OreDictMaterialStack(MT.Sn, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleSilver"), new OreDictMaterialStack(MT.Ag, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetTin"), new OreDictMaterialStack(MT.Pb, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateTin"), new OreDictMaterialStack(MT.Pb, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsTin"), new OreDictMaterialStack(MT.Pb, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsTin"), new OreDictMaterialStack(MT.Pb, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordLead"), new OreDictMaterialStack(MT.Pb, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelLead"), new OreDictMaterialStack(MT.Pb, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeLead"), new OreDictMaterialStack(MT.Pb, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeLead"), new OreDictMaterialStack(MT.Pb, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeLead"), new OreDictMaterialStack(MT.Pb, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsLead"), new OreDictMaterialStack(MT.Pb, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleLead"), new OreDictMaterialStack(MT.Pb, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleNickel"), new OreDictMaterialStack(MT.Ni, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleElectrum"), new OreDictMaterialStack(MT.Electrum, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.plateBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxeBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axeBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoeBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sickleBronze"), new OreDictMaterialStack(MT.Bronze, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.helmetPlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.platePlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.legsPlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "armor.bootsPlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.swordPlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shovelPlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 1)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.pickaxePlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.axePlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.hoePlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.shearsPlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.sicklePlatinum"), new OreDictMaterialStack(MT.Pt, CS.U * 3)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "wrench"), new OreDictMaterialStack(MT.Fe, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "tool.battleWrenchInvar"), new OreDictMaterialStack(MT.Invar, CS.U * 6)));
			
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}
