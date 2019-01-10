package com.kbi.qwertech.api.data;

import gregapi.data.*;
import gregapi.enchants.Enchantment_Radioactivity;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.TextureSet;
import net.minecraft.enchantment.Enchantment;

import static gregapi.data.TD.Compounds.COATED;
import static gregapi.data.TD.ItemGenerator.*;
import static gregapi.data.TD.Processing.UNRECYCLABLE;
import static gregapi.data.TD.Properties.*;

public class QTMT {
	public static final OreDictMaterial
	ChemicalX = 	OreDictMaterial.createMaterial(13000, "ChemicalX", 		"Chemical X"		).setTextures(TextureSet.SET_PLASMA			).setRGBa(10, 	10, 	15, 	255	).heat(CS.C - 10, 	CS.C + 50										).aspects(TC.PRAECANTIO, 	2, 	TC.PERDITIO, 	1	).put(UNRECYCLABLE, 	LIQUID, 	CONTAINERS_FLUID,						FLAMMABLE, 	EXPLOSIVE									).setOriginalMod(MD.QT	).setMcfg(0, 	MT.Magic, 	CS.U*2								),
	ChemicalY = 	OreDictMaterial.createMaterial(13001, "ChemicalY", 		"Chemical Y"		).setTextures(TextureSet.SET_PLASMA			).setRGBa(200, 	200, 	250, 	255	).heat(CS.C - 10, 	CS.C + 50										).aspects(TC.PRAECANTIO, 	2									).put(UNRECYCLABLE, 	LIQUID,	 	CONTAINERS_FLUID																				).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalX, 	CS.U, 	MT.Magic, 	CS.U, 	MT.C, 	CS.U	),
	ChemicalZ = 	OreDictMaterial.createMaterial(13002, "ChemicalZ", 		"Chemical Z"		).setTextures(TextureSet.SET_LIGNITE		).setRGBa(50, 	50, 	75, 	255	).heat(CS.C + 49, 	CS.C + 50										).aspects(TC.PRAECANTIO, 	2									).put(UNRECYCLABLE,		FLAMMABLE,	GLOWING, 	INGOTS, 	INGOTS_HOT																).setOriginalMod(MD.QT	).setMcfg(0, 	MT.Magic, 	CS.U, 	MT.C, 		CS.U					).addEnchantmentForTools(Enchantment_Radioactivity.INSTANCE, 1).addEnchantmentForArmors(Enchantment_Radioactivity.INSTANCE, 1),
	
	ChemicalY1 = 	OreDictMaterial.createMaterial(13003, "ChemicalY1",		"Chemical Y-1"		).setTextures(TextureSet.SET_FIERY			).setRGBa(120, 	120, 	140, 	255	).setWorking(MT.Ta, CS.U).setSmelting(MT.Ta, 	CS.U).setForging(MT.Ta, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Ta,		CS.U					).qual(MT.Ta),
	ChemicalY2 = 	OreDictMaterial.createMaterial(13004, "ChemicalY2",		"Chemical Y-2"		).setTextures(TextureSet.SET_FIERY			).setRGBa(50, 	50, 	50, 	255	).setWorking(MT.W, CS.U).setSmelting(MT.W, 	CS.U).setForging(MT.W, 		CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.W,		CS.U					).qual(MT.W),
	ChemicalY3 = 	OreDictMaterial.createMaterial(13005, "ChemicalY3",		"Chemical Y-3"		).setTextures(TextureSet.SET_FIERY			).setRGBa(255, 	255, 	200, 	255	).setWorking(MT.Re, CS.U).setSmelting(MT.Re, 	CS.U).setForging(MT.Re,		CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Re,		CS.U					).qual(MT.Re),
	ChemicalY4 = 	OreDictMaterial.createMaterial(13006, "ChemicalY4",		"Chemical Y-4"		).setTextures(TextureSet.SET_FIERY			).setRGBa(50, 	50, 	255, 	255	).setWorking(MT.Os, CS.U).setSmelting(MT.Os, 	CS.U).setForging(MT.Os, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Os,		CS.U					).qual(MT.Os),
	ChemicalY5 = 	OreDictMaterial.createMaterial(13007, "ChemicalY5",		"Chemical Y-5"		).setTextures(TextureSet.SET_FIERY			).setRGBa(240, 	240, 	245, 	255	).setWorking(MT.Ir, CS.U).setSmelting(MT.Ir, 	CS.U).setForging(MT.Ir, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Ir,		CS.U					).qual(MT.Ir),
	ChemicalY6 = 	OreDictMaterial.createMaterial(13008, "ChemicalY6",		"Chemical Y-6"		).setTextures(TextureSet.SET_FIERY			).setRGBa(100, 	180, 	250, 	255	).setWorking(MT.Pt, CS.U).setSmelting(MT.Pt, 	CS.U).setForging(MT.Pt, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Pt,		CS.U					).qual(MT.Pt),
	ChemicalY7 = 	OreDictMaterial.createMaterial(13009, "ChemicalY7",		"Chemical Y-7"		).setTextures(TextureSet.SET_FIERY			).setRGBa(255, 	230, 	80, 	255	).setWorking(MT.Au, CS.U).setSmelting(MT.Au, 	CS.U).setForging(MT.Au, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Au,		CS.U					).qual(MT.Au),
	ChemicalY8 = 	OreDictMaterial.createMaterial(13010, "ChemicalY8",		"Chemical Y-8"		).setTextures(TextureSet.SET_FIERY			).setRGBa(140, 	100, 	140, 	255	).setWorking(MT.Pb, CS.U).setSmelting(MT.Pb, 	CS.U).setForging(MT.Pb, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Pb,		CS.U					).qual(MT.Pb),
	ChemicalY9 = 	OreDictMaterial.createMaterial(13011, "ChemicalY9",		"Chemical Y-9"		).setTextures(TextureSet.SET_FIERY			).setRGBa(50, 	240, 	50, 	255	).setWorking(MT.U_235, CS.U).setSmelting(MT.U_235, CS.U).setForging(MT.U_235, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1									).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.U_235,	CS.U					).qual(MT.U_235).addEnchantmentForTools(Enchantment_Radioactivity.INSTANCE, 1).addEnchantmentForArmors(Enchantment_Radioactivity.INSTANCE, 1),
	ChemicalY10 = 	OreDictMaterial.createMaterial(13012, "ChemicalY10",		"Chemical Y-10"		).setTextures(TextureSet.SET_FIERY			).setRGBa(240, 	50, 	50, 	255	).setWorking(MT.Pu, CS.U).setSmelting(MT.Pu, 	CS.U).setForging(MT.Pu, 	CS.U	).aspects(TC.PRAECANTIO, 	2,	TC.METALLUM,	1	).put(COATED,	G_INGOT_ND																											).setOriginalMod(MD.QT	).setMcfg(0, 	ChemicalY,	CS.U,	MT.Pu,		CS.U					).qual(MT.Pu).addEnchantmentForTools(Enchantment_Radioactivity.INSTANCE, 1).addEnchantmentForArmors(Enchantment_Radioactivity.INSTANCE, 1)
	
	,RedCY11 =		OreDictMaterial.createMaterial(13013, "RedCY11",			"Red CY-11"			).setTextures(TextureSet.SET_FIERY			).setRGBa(200, 	20, 	20, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.Ruby).addEnchantmentForTools(Enchantment.sharpness, 1).addEnchantmentForTools(Enchantment.punch, 1)
	,OrangeCY11 =	OreDictMaterial.createMaterial(13014, "OrangeCY11",		"Orange CY-11"		).setTextures(TextureSet.SET_FIERY			).setRGBa(200, 	100, 	20, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.Topaz)
	,YellowCY11 =	OreDictMaterial.createMaterial(13015, "YellowCY11",		"Yellow CY-11"		).setTextures(TextureSet.SET_FIERY			).setRGBa(200, 	200, 	20, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.YellowSapphire).addEnchantmentForArmors(Enchantment.fireProtection, 1)
	,GreenCY11 =	OreDictMaterial.createMaterial(13016, "GreenCY11",			"Green CY-11"			).setTextures(TextureSet.SET_FIERY			).setRGBa(20, 	200, 	20, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.GreenSapphire).addEnchantmentForTools(Enchantment.unbreaking, 1).addEnchantmentForArmors(Enchantment.protection, 1)
	,BlueCY11 =		OreDictMaterial.createMaterial(13017, "BlueCY11",			"Blue CY-11"			).setTextures(TextureSet.SET_FIERY			).setRGBa(20, 	100, 	200, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.BlueTopaz)
	,PurpleCY11 =	OreDictMaterial.createMaterial(13018, "PurpleCY11",		"Purple CY-11"		).setTextures(TextureSet.SET_FIERY			).setRGBa(200, 	20, 	200, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.Amethyst).addEnchantmentForTools(Enchantment.efficiency, 1)
	,PinkCY11 =		OreDictMaterial.createMaterial(13019, "PinkCY11",			"Pink CY-11"			).setTextures(TextureSet.SET_FIERY			).setRGBa(200, 	100, 	100, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.Ruby)
	,WhiteCY11 =	OreDictMaterial.createMaterial(13020, "WhiteCY11",			"White CY-11"			).setTextures(TextureSet.SET_FIERY			).setRGBa(220, 	220, 	220, 	255	).heat(3000, 3500											).aspects(TC.PRAECANTIO,2,TC.LUCRUM,1,TC.ORDO,1		).put(COATED,	GEMS,	STICKS,		PLATES																							).setOriginalMod(MD.QT																			).qual(MT.Diamond).addEnchantmentForArmors(Enchantment.blastProtection, 1)

	,Undefined =	OreDictMaterial.createMaterial(13030, "Any", 				"Any"					).setTextures(TextureSet.SET_METALLIC		).setRGBa(125, 	125, 	125, 	125).hide(true).qual(MT.Steel).add(TD.ItemGenerator.G_ALL).setAllToTheOutputOf(MT.NULL)
	,CompostRaw =	OreDictMaterial.createMaterial(13031, "CompostRaw", 		"Raw Compost"			).setTextures(TextureSet.SET_LIGNITE		).setRGBa(200, 	150, 	50, 	255)																					.aspects(TC.HERBA, 1, TC.MORTUUS, 1, TC.VICTUS, 1	).put(TD.ItemGenerator.G_DUST																				).setOriginalMod(MD.QT)
	,Compost =		OreDictMaterial.createMaterial(13032, "Compost", 			"Compost"				).setTextures(TextureSet.SET_LIGNITE		).setRGBa(75, 	60, 	55, 	255)																					.aspects(TC.HERBA, 1, TC.MORTUUS, 1, TC.VICTUS, 1	).put(TD.ItemGenerator.G_DUST																				).setOriginalMod(MD.QT)
	;
	
	static {
		ChemicalX.mDescription = new String[] {
				// ========================================================================================================================================================================================================
				  "Chemical X is a mysterious and unstable substance, extracted from certain objects which seem to be imbued with some form of unexplained power."
				, "Originally referred to as Utonium, its nature as an element or a compound has been hotly debated, as it is simply too volatile for thorough examination."
				, "There may be ways to refine it into a more usable form, but attempting to directly harness Chemical X is highly discouraged by most scientists."
				};
		
		ChemicalY.mDescription = new String[] {
				// ========================================================================================================================================================================================================
				  "Chemical Y is a refined form of Chemical X, formed by subjecting that substance to electric currents while in the presence of carbon. It is far less volatile, yet highly reactive."
				, "While it bonds weakly with heavier metals like lead and gold, the true brilliance of Chemical Y is how readily it will bond to practically any crystalline structure."
				, "In doing so, the crystal becomes charged with energy, which is directed based upon the wavelength properties of the crystal, often in very intriguing ways."
				};
		
		ChemicalZ.mDescription = new String[] {
				// ========================================================================================================================================================================================================
				  "Chemical Z is the byproduct of Chemical Y refining. A glowing sludge, it is hazardous to most lifeforms and should be handled with care."
				, "While it is possible to separate the carbon from the compound, the process results in the evaporation of the chemical energy it was bonded with."
				};

		Compost.mDescription = new String[] {
				   "Compost is a rich soil, good for growing many varieties of plants, formed in the somewhat cannibalistic manner of piling up a bunch of dead plants until they rot."
		};

		ChemicalY1.mToolSpeed = ChemicalY1.mToolSpeed * 1.5F;
		ChemicalY2.mToolSpeed = ChemicalY2.mToolSpeed * 1.5F;
		ChemicalY3.mToolSpeed = ChemicalY3.mToolSpeed * 1.5F;
		ChemicalY4.mToolSpeed = ChemicalY4.mToolSpeed * 1.5F;
		ChemicalY5.mToolSpeed = ChemicalY5.mToolSpeed * 1.5F;
		ChemicalY6.mToolSpeed = ChemicalY6.mToolSpeed * 1.5F;
		ChemicalY7.mToolSpeed = ChemicalY7.mToolSpeed * 1.5F;
		ChemicalY8.mToolSpeed = ChemicalY8.mToolSpeed * 1.5F;
		ChemicalY9.mToolSpeed = ChemicalY9.mToolSpeed * 1.5F;
		ChemicalY10.mToolSpeed = ChemicalY10.mToolSpeed * 1.5F;

		MT.TECH.Brick.setRGBa(183, 90, 64, 255);

		OP.blockDust.disableItemGeneration(Compost, CompostRaw);
	}
}
