package com.kbi.qwertech.loaders;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.data.QTMT;
import gregapi.code.ICondition;
import gregapi.data.*;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.IconsGT;
import gregapi.recipes.handlers.RecipeMapHandlerMaterial;
import gregapi.render.TextureSet;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import java.util.Set;

public class RegisterMaterials {
	
	public static RegisterMaterials instance;

	public RegisterMaterials()
	{
		instance = this;
		registerFluids();
	}
	
	public void registerFluids()
	{
		//Fluid aa = UT.Fluids.createLiquid(QTMT.ChemicalX, (IIconContainer) TextureSet.SET_FLUID[0].mList.get(IconsGT.INDEX_BLOCK_MOLTEN),
				//(Set)FluidsGT.LIQUID).setTemperature(300);
		Fluid aa = UT.Fluids.create(QTMT.ChemicalX.mNameInternal.toLowerCase(), TextureSet.SET_FLUID[0].mList.get(IconsGT.INDEX_BLOCK_MOLTEN), QTMT.ChemicalX.mNameLocal, QTMT.ChemicalX, QTMT.ChemicalX.mRGBaLiquid, 1, 144L, 1L, null, null, 0, (Set)FluidsGT.SIMPLE);
        Fluid bb = UT.Fluids.create(QTMT.ChemicalY.mNameInternal.toLowerCase(), TextureSet.SET_FLUID[0].mList.get(IconsGT.INDEX_BLOCK_MOLTEN), QTMT.ChemicalY.mNameLocal, QTMT.ChemicalY, QTMT.ChemicalY.mRGBaLiquid, 1, 144L, 1L, null, null, 0, (Set)FluidsGT.SIMPLE);
    /*
		Fluid bb = UT.Fluids.createLiquid(QTMT.ChemicalY, (IIconContainer) TextureSet.SET_FLUID[0].mList.get(IconsGT.INDEX_BLOCK_MOLTEN),
				(Set)FluidsGT.LIQUID).setTemperature(300);
		QTMT.ChemicalX.liquid(UT.Fluids.make(aa, 144), CS.U);
		QTMT.ChemicalY.liquid(UT.Fluids.make(bb, 144), CS.U);*/
	}
	public void registerRecipes()
	{
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.nether_star, 1, 0), UT.Fluids.make("potion.mineralwater", 1440), QTMT.ChemicalX.liquid(CS.U * 10, true), OP.dust.mat(MT.NetherQuartz, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.nether_wart, 1, 0), UT.Fluids.make("potion.mineralwater", 144), QTMT.ChemicalX.liquid(CS.U, true), IL.Remains_Plant.get(1, OP.dustTiny.mat(MT.C, 1)));
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.ender_pearl, 1, 0), UT.Fluids.make("potion.mineralwater", 144*6), QTMT.ChemicalX.liquid(CS.U * 6, true), OP.dust.mat(MT.Be, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.ender_eye, 1, 0), UT.Fluids.make("potion.mineralwater", 144*6), QTMT.ChemicalX.liquid(CS.U * 6, true), OP.dust.mat(MT.Be, 1), OP.dustSmall.mat(MT.S, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.blaze_rod, 1, 0), UT.Fluids.make("potion.mineralwater", 144*3), QTMT.ChemicalX.liquid(CS.U * 3, true), OP.dust.mat(MT.S, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.blaze_powder, 1, 0), UT.Fluids.make("potion.mineralwater", 144), QTMT.ChemicalX.liquid(CS.U, true), OP.dust.mat(MT.Be, 1), OP.dustSmall.mat(MT.S, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.Vinteum, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dustSmall.mat(MT.Ash, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.Thaumium, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Fe, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.Manasteel, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Steel, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.VinteumPurified, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), OP.dustTiny.mat(MT.Ash, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.EnderAmethyst, 1), UT.Fluids.make("potion.mineralwater", 144), QTMT.ChemicalX.liquid(CS.U * 4, true), OP.dust.mat(MT.Amethyst, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.InfusedAir, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), UT.Fluids.make("air", 1000));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.InfusedWater, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), UT.Fluids.make("water", 1000));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.InfusedFire, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), UT.Fluids.make("lava", 100));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.InfusedEarth, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), OP.dust.mat(MT.Stone, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.InfusedEntropy, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 6, true), (ItemStack)null);
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.InfusedOrder, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), QTMT.ChemicalY.liquid(CS.U * 2, true));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.gem.mat(MT.ManaQuartz, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.NetherQuartz, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.Knightmetal, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Fe, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.FierySteel, 1), UT.Fluids.make("potion.mineralwater", 144),QTMT.ChemicalX.liquid(CS.U, true), OP.dust.mat(MT.Steel, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.AstralSilver, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Ag, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.Midasium, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Au, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.Mithril, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Pt, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.DarkThaumium, 1), UT.Fluids.make("potion.mineralwater", 144*3), QTMT.ChemicalX.liquid(CS.U * 3, true), OP.dust.mat(MT.DarkIron, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.VoidMetal, 1), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), OP.dustSmall.mat(MT.W, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.PulsatingIron, 1), UT.Fluids.make("potion.mineralwater", 144*6), QTMT.ChemicalX.liquid(CS.U * 6, true), OP.dust.mat(MT.Fe, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, OP.ingot.mat(MT.Orichalcum, 1), UT.Fluids.make("potion.mineralwater", 144*2), QTMT.ChemicalX.liquid(CS.U * 2, true), OP.dust.mat(MT.Cu, 1));
		RM.Electrolyzer.addRecipe1(true, 32, 500, ST.make(Items.enchanted_book, 1, CS.W), UT.Fluids.make("potion.mineralwater", 144*4), QTMT.ChemicalX.liquid(CS.U * 4, true), OP.dust.mat(MT.Paper, 3));
		
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dust.mat(MT.Ash, 1), QTMT.ChemicalX.liquid(CS.U, true), QTMT.ChemicalY.liquid(CS.U2, true), OP.ingot.mat(QTMT.ChemicalZ, 1));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dust.mat(MT.DarkAsh, 1), QTMT.ChemicalX.liquid(CS.U * 2, true), QTMT.ChemicalY.liquid(CS.U, true), OP.ingot.mat(QTMT.ChemicalZ, 2));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dust.mat(MT.Graphite, 1), QTMT.ChemicalX.liquid(CS.U * 2, true), QTMT.ChemicalY.liquid(CS.U, true), OP.ingot.mat(QTMT.ChemicalZ, 2));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dust.mat(MT.Diamond, 1), QTMT.ChemicalX.liquid(CS.U * 4, true), QTMT.ChemicalY.liquid(CS.U * 2, true), OP.ingot.mat(QTMT.ChemicalZ, 4));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dust.mat(MT.C, 1), QTMT.ChemicalX.liquid(CS.U * 2, true), QTMT.ChemicalY.liquid(CS.U, true), OP.ingot.mat(QTMT.ChemicalZ, 2));
		
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dustSmall.mat(MT.Ash, 1), QTMT.ChemicalX.liquid(CS.U4, true), QTMT.ChemicalY.liquid(CS.U8, true), OP.chunk.mat(QTMT.ChemicalZ, 1));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dustSmall.mat(MT.DarkAsh, 1), QTMT.ChemicalX.liquid(CS.U2, true), QTMT.ChemicalY.liquid(CS.U4, true), OP.chunk.mat(QTMT.ChemicalZ, 2));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dustSmall.mat(MT.Graphite, 1), QTMT.ChemicalX.liquid(CS.U2, true), QTMT.ChemicalY.liquid(CS.U4, true), OP.chunk.mat(QTMT.ChemicalZ, 2));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dustSmall.mat(MT.Diamond, 1), QTMT.ChemicalX.liquid(CS.U, true), QTMT.ChemicalY.liquid(CS.U2, true), OP.chunk.mat(QTMT.ChemicalZ, 4));
		RM.Electrolyzer.addRecipe1(true, 24, 400, OP.dustSmall.mat(MT.C, 1), QTMT.ChemicalX.liquid(CS.U2, true), QTMT.ChemicalY.liquid(CS.U4, true), OP.chunk.mat(QTMT.ChemicalZ, 2));
		
		RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Ta, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY1, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.W, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY2, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Re, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY3, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Os, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY4, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Ir, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY5, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Pt, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY6, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Au, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY7, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Pb, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY8, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.U_235, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY9, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Pu, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.ChemicalY10, null, true, ICondition.TRUE));

        //gems
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Ruby, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Redstone, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Almandine, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.InfusedFire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Se, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.BalasRuby, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Jasper, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Bixbite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.RedCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Topaz, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.OrangeCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.OrangeSapphire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.OrangeCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Grossular, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.OrangeCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Amber, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.OrangeCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.YellowSapphire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.S, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Force, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.InfusedAir, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.P, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Phosphorus, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Heliodor, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Andradite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Emerald, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.GreenSapphire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.InfusedEarth, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.F, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Olivine, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Jade, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Spinel, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Monazite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Uvarovite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.GreenCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Sapphire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.BlueSapphire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.BlueTopaz, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Vinteum, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.VinteumPurified, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.InfusedWater, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.CertusQuartz, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.ChargedCertusQuartz, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Na, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.BlueCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Apatite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Tanzanite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Opal, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Dioptase, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Maxixe, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.YellowCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Amethyst, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PurpleCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.PurpleSapphire, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PurpleCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Fluix, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PurpleCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.InfusedVis, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PurpleCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.LavenderQuartz, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PurpleCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Pyrope, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PurpleCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Craponite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PinkCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.EnderAmethyst, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PinkCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Niter, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PinkCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Morganite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PinkCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Spessartine, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.PinkCY11, null, true, ICondition.TRUE));

        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Diamond, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Goshenite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.NetherQuartz, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.K, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Ca, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Zr, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.I, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Glass, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Dilithium, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Aquamarine, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));
        RM.Mixer.add(new RecipeMapHandlerMaterial(MT.Alexandrite, QTMT.ChemicalY.liquid(CS.U, true), 16, 16, null, QTMT.WhiteCY11, null, true, ICondition.TRUE));

        //RM.Mixer.addRecipeX(true, 16, 16, new ItemStack[]{ST.make(Items.sugar, 1, 0), IL.Food_Cinnamon.get(1), OP.dust.mat(MT.Au, 1)}, QTMT.ChemicalX.liquid(CS.U, true), UT.Fluids.make(FL.Soda, 100L), ST.make(Blocks.red_flower, 1, 0), IL.Food_Butter.get(1));



        ItemStack[] randoms = new ItemStack[]{ST.make(Items.bread, 1, 0),
            ST.make(Items.feather, 1, 0), ST.make(Items.slime_ball, 1, 0), ST.make(Items.apple, 1, 0),
                ST.make(Items.fish, 1, 0), ST.make(Items.flint, 1, 0), ST.make(Items.bone, 1, 0),
                ST.make(Items.egg, 1, 0), ST.make(Items.blaze_powder, 1, 0), ST.make(Items.brick, 1, 0),
                ST.make(Items.potato, 1, 0), ST.make(Items.poisonous_potato, 1, 0), ST.make(Items.gold_nugget, 1, 0),
                ST.make(Items.snowball, 1, 0), ST.make(Items.rotten_flesh, 1, 0), ST.make(Items.stick, 1, 0),
                ST.make(Items.sugar, 1, 0), ST.make(Items.string, 1, 0), ST.make(Items.bowl, 1, 0), ST.make(Items.coal, 1, 1),
                ST.make(Items.gunpowder, 1, 0), ST.make(Items.leather, 1, 0), ST.make(Items.melon, 1, 0),
                ST.make(Items.paper, 1, 0), ST.make(Items.redstone, 1, 0), ST.make(Items.spider_eye, 1, 0),
                IL.Mud_Ball.get(1), IL.Food_Ananas_Sliced.get(1), IL.Food_Burger_Meat.get(1),
                IL.Food_Cucumber_Sliced.get(1), IL.Food_Apple_DarkRed_Sliced.get(1),
                IL.Bale_Oats.get(1), IL.Bale_Moldy.get(1),
                IL.Bottle_Beer.get(1), IL.Bottle_Milk.get(1),
                IL.Comb_Honey.get(1), IL.Comb_Sandy.get(1), IL.Comb_Magic.get(1),
                IL.Crate.get(1), OP.rockGt.mat(MT.Diorite, 1), OP.rockGt.mat(MT.Andesite, 1),
                IL.Food_Can_Meat_1.get(1), IL.Food_Can_Rotten_1.get(1),
                IL.Key_Brass.get(1), IL.Key_Copper.get(1), IL.Key_Lead.get(1),
                ST.make(Blocks.planks, 1, 0), ST.make(Blocks.planks, 1, 1), ST.make(Blocks.planks, 1, 2),
                ST.make(Blocks.planks, 1, 3), ST.make(Blocks.planks, 1, 4), ST.make(Blocks.planks, 1, 5),
                IL.Remains_Plant.get(1), IL.Resin.get(1), OP.rockGt.mat(MT.Granite, 1),
                IL.Wooden_Bucket_Copper.get(1), ST.make(Blocks.glass, 1, 0),
                ST.make(Blocks.brown_mushroom, 1, 0), ST.make(Blocks.red_mushroom, 1, 0),
                ST.make(Blocks.wool, 1, 0), ST.make(Blocks.web, 1, 0),
                ST.make(Blocks.cobblestone, 1, 0), ST.make(Blocks.dirt, 1, 0),
                ST.make(Blocks.mossy_cobblestone, 1, 0), ST.make(Blocks.stone, 1, 0),
                ST.make(Blocks.sand, 1, 0), ST.make(Blocks.sandstone, 1, 0),
                ST.make(Blocks.cactus, 1, 0), ST.make(Blocks.gravel, 1, 0),
                ST.make(Blocks.hardened_clay, 1, 0), ST.make(Blocks.wool, 1, 1), ST.make(Blocks.wool, 1, 2),
                ST.make(Blocks.wool, 1, 3), ST.make(Blocks.wool, 1, 4),
                ST.make(Blocks.stained_glass, 1, 5), ST.make(Blocks.stained_glass, 1, 6), ST.make(Blocks.stained_glass, 1, 7),
                ST.make(Blocks.stained_hardened_clay, 1, 8), ST.make(Blocks.stained_hardened_clay, 1, 9),
                ST.make(Blocks.stained_hardened_clay, 1, 10), ST.make(Blocks.stained_hardened_clay, 1, 11),
                ST.make(Blocks.carpet, 1, 12), ST.make(Blocks.carpet, 1, 13), ST.make(Blocks.carpet, 1, 14),
                ST.make(Blocks.wool, 1, 15), ST.make(Blocks.netherrack, 1, 0), ST.make(Blocks.soul_sand, 1, 0),
                ST.make(Blocks.lever, 1, 0), ST.make(Blocks.stone_button, 1, 0), ST.make(Blocks.wooden_pressure_plate, 1, 0),
                QTI.turkeyFeather.get(1), QTI.frogEggs.get(1), QTI.batEmblem.get(1),
                OP.nugget.mat(MT.Fe, 1), OP.nugget.mat(MT.Cu, 1), OP.nugget.mat(MT.Ag, 1),
                OP.nugget.mat(MT.Pb, 1), OP.nugget.mat(MT.Sn, 1), OP.nugget.mat(MT.Bi, 1),
                OP.dustSmall.mat(MT.Ash, 1), OP.dustSmall.mat(MT.DarkAsh, 1), OP.dustSmall.mat(MT.Redstone, 1),
                OP.dust.mat(MT.Stone, 1), OP.dust.mat(MT.Marble, 1), OP.dust.mat(MT.Limestone, 1),
                OP.dust.mat(MT.Diorite, 1), OP.dust.mat(MT.Granite, 1), OP.dust.mat(MT.Netherrack, 1),
                OP.dust.mat(MT.Sodalite, 1), OP.dustSmall.mat(MT.Bixbite, 1), OP.dustSmall.mat(MT.BalasRuby, 1),
                OP.dust.mat(MT.Pyrite, 1), OP.dustSmall.mat(MT.Au, 1), OP.dust.mat(MT.S, 1),
                OP.crushedTiny.mat(MT.OREMATS.Bauxite, 1), OP.crushedTiny.mat(MT.OREMATS.BrownLimonite, 1),
                OP.crushedTiny.mat(MT.Coal, 1), OP.crushedTiny.mat(MT.OREMATS.Cassiterite, 1),
                OP.crushedTiny.mat(MT.OREMATS.Chalcopyrite, 1), OP.crushedTiny.mat(MT.OREMATS.Hematite, 1),
                OP.crushedTiny.mat(MT.OREMATS.Galena, 1)
        };

        if (QTConfigs.chemicalXRandom) {
            for (int q = 0; q < randoms.length; q++) {
                if (randoms[q] == null)
                {
                    System.out.println("We could not find object #" + q);
                }
                RM.Mixer.addRecipe1(true, 16, 16, randoms[q], QTMT.ChemicalX.liquid(CS.U, true), null, randoms[CS.RANDOM.nextInt(randoms.length)]);
            }
        }
    }
}
