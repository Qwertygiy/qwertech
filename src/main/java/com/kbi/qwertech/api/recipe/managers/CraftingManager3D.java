package com.kbi.qwertech.api.recipe.managers;

import com.kbi.qwertech.api.recipe.CraftingRecipe3D;
import com.kbi.qwertech.api.recipe.Prefix3DRecipe;
import com.kbi.qwertech.api.recipe.QTArmor3D;
import gregapi.block.MaterialMachines;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ICondition;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CraftingManager3D implements Runnable {

	private static final CraftingManager3D instance = new CraftingManager3D();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();

    /**
     * Returns the static instance of this class
     */
    public static CraftingManager3D getInstance()
    {
        /* The static instance of this class */
        return instance;
    }
    
    private static void machinery(MultiTileEntityRegistry aRegistry)
    {
    	OreDictMaterial aMat;
    	CraftingManager3D t = getInstance();
    	
    	aMat = MT.DATA.Kinetic_T[1];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1512), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Bronze)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1515), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Brass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1518), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Invar)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16001), " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'M', OP.casingMachineDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.pipeSmall.dat(MT.StainlessSteel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20011), " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20021), " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20031), "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(MT.Diamond)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20011), "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20021), "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20031), "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(ANY.Sapphire)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20051), "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR", 'w', "craftingToolWrench", 'x', "craftingToolWireCutter", 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'W', OP.wireFine.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20061), " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(MT.Diamond), 'G', OP.toolHeadBuzzSaw.dat(ANY.Steel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20061), " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(ANY.Sapphire), 'G', OP.toolHeadBuzzSaw.dat(ANY.Steel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20071), " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'P', OP.plateTriple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20081), " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.stickLong.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20101), "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", 'w', "craftingToolWrench", 'P', OP.plateDouble.dat(aMat), 'H', OP.plateQuintuple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20111), " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20121), " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20131), " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20141), " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", 'w', "craftingToolWrench", 'R', OP.stick.dat(aMat), 'P', OP.plateQuadruple.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20151), " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20181), "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", 'w', "craftingToolWrench", 'M', OP.plate.dat(aMat), 'S', OP.stick.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.plate.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Kinetic_T[2];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1522), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Steel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1525), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Cr)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1527), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.IronWood)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1528), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Steeleaf)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1529), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Thaumium)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16002), " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'M', OP.casingMachineDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.pipeMedium.dat(MT.StainlessSteel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20012), " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20022), " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20032), "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(MT.Diamond)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20012), "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20022), "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20032), "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(ANY.Sapphire)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20052), "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR", 'w', "craftingToolWrench", 'x', "craftingToolWireCutter", 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'W', OP.wireFine.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20062), " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(MT.Diamond), 'G', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20062), " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(ANY.Sapphire), 'G', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20072), " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'P', OP.plateTriple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20082), " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.stickLong.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20102), "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", 'w', "craftingToolWrench", 'P', OP.plateDouble.dat(aMat), 'H', OP.plateQuintuple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20112), " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20122), " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20132), " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20142), " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", 'w', "craftingToolWrench", 'R', OP.stick.dat(aMat), 'P', OP.plateQuadruple.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20152), " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20182), "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", 'w', "craftingToolWrench", 'M', OP.plate.dat(aMat), 'S', OP.stick.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.plateDouble.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Kinetic_T[3];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1530), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Ti)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1531), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.FierySteel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1535), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Al)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1538), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Magnalium)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16003), " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'M', OP.casingMachineDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.pipeLarge.dat(MT.StainlessSteel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20013), " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20023), " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20033), "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(MT.Diamond)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20013), "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20023), "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20033), "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(ANY.Sapphire)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20053), "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR", 'w', "craftingToolWrench", 'x', "craftingToolWireCutter", 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'W', OP.wireFine.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20063), " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(MT.Diamond), 'G', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20063), " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(ANY.Sapphire), 'G', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20073), " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'P', OP.plateTriple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20083), " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.stickLong.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20103), "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", 'w', "craftingToolWrench", 'P', OP.plateDouble.dat(aMat), 'H', OP.plateQuintuple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20113), " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20123), " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20133), " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20143), " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", 'w', "craftingToolWrench", 'R', OP.stick.dat(aMat), 'P', OP.plateQuadruple.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20153), " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20183), "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", 'w', "craftingToolWrench", 'M', OP.plate.dat(aMat), 'S', OP.stick.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.plateTriple.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Kinetic_T[4];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1540), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.VoidMetal)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1545), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Trinitanium)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1548), " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.rotor.dat(MT.Graphene)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16004), " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'M', OP.casingMachineDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.pipeHuge.dat(MT.StainlessSteel)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20014), " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20024), " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(MT.Diamond)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20034), "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(MT.Diamond)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20014), "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'D', OP.plateGem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20024), "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'D', OP.gem.dat(ANY.Sapphire)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20034), "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ", 'd', "craftingToolScrewdriver", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.plateGemTiny.dat(ANY.Sapphire)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20054), "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR", 'w', "craftingToolWrench", 'x', "craftingToolWireCutter", 'P', OP.plateDouble.dat(aMat), 'S', OP.spring.dat(aMat), 'W', OP.wireFine.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20064), " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(MT.Diamond), 'G', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20064), " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", 'M', OP.casingMachineDouble.dat(aMat), 'S', OP.gearGtSmall.dat(aMat), 'D', OP.dust.dat(ANY.Sapphire), 'G', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20074), " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'P', OP.plateTriple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20084), " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", 'w', "craftingToolWrench", 'M', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.stickLong.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20104), "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", 'w', "craftingToolWrench", 'P', OP.plateDouble.dat(aMat), 'H', OP.plateQuintuple.dat(aMat), 'S', OP.spring.dat(aMat), 'R', OP.stick.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20114), " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20124), " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20134), " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20144), " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", 'w', "craftingToolWrench", 'R', OP.stick.dat(aMat), 'P', OP.plateQuadruple.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20154), " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'G', OP.gearGt.dat(aMat), 'S', OP.gearGtSmall.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20184), "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", 'w', "craftingToolWrench", 'M', OP.plate.dat(aMat), 'S', OP.stick.dat(aMat), 'R', OP.rotor.dat(MT.StainlessSteel), 'P', OP.plateQuadruple.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Electric_T[0];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10040), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(ANY.Cu), 'X', OP.wireGt04.dat(ANY.Cu), 'I', OP.plateDouble.dat(ANY.Fe)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10050), "WSW", "CPC", " L ", "SPS", "P P", " P ", " S ", " P ", " L ", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.cableGt01.dat(ANY.Cu), 'C', CS.OD_CIRCUITS[1], 'S', OP.plateGem.dat(ANY.Si)));
    	
    	aMat = MT.DATA.Electric_T[1];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10001), " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", 'd', "craftingToolScrewdriver", 'P', OP.plateDouble.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'C', OP.wireGt01.dat(ANY.Cu)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10021), "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'I', OP.stickLong.dat(MT.IronMagnetic), 'C', OP.wireGt01.dat(ANY.Cu)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10031), "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", 'S', OP.stick.dat(aMat), 'x', "craftingToolWireCutter", 'P', OP.plate.dat(aMat), 'C', OP.wireGt01.dat(ANY.Cu)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10041), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(ANY.Cu), 'X', OP.wireGt04.dat(ANY.Cu), 'I', OP.plateDouble.dat(ANY.Fe)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15011), " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'B', OP.bolt.dat(ANY.Steel), 'C', MT.DATA.CABLES_01[1]));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20091), " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'S', OP.wireGt01.dat(MT.Pt), 'W', MT.DATA.CABLES_01[1]));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20161), " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'X', IL.PUMPS[1].get(1), 'C', CS.OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'M', OP.pipeTiny.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Electric_T[2];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10002), " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", 'd', "craftingToolScrewdriver", 'P', OP.plateDouble.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'C', OP.wireGt02.dat(MT.Constantan)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10022), "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'I', OP.stickLong.dat(MT.IronMagnetic), 'C', OP.wireGt02.dat(MT.Constantan)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10032), "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", 'S', OP.stick.dat(aMat), 'x', "craftingToolWireCutter", 'P', OP.plate.dat(aMat), 'C', OP.wireGt02.dat(ANY.Cu)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10042), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(ANY.Cu), 'X', OP.wireGt04.dat(ANY.Cu), 'I', OP.plateDouble.dat(ANY.Fe)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15012), " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'B', OP.bolt.dat(ANY.Steel), 'C', MT.DATA.CABLES_01[2]));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20092), " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'S', OP.wireGt01.dat(MT.Pt), 'W', MT.DATA.CABLES_01[2]));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20162), " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'X', IL.PUMPS[2].get(1), 'C', CS.OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[2], 'M', OP.pipeSmall.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Electric_T[3];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10003), " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", 'd', "craftingToolScrewdriver", 'P', OP.plateDouble.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'C', OP.wireGt04.dat(MT.Kanthal)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10023), "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'I', OP.stickLong.dat(MT.IronMagnetic), 'C', OP.wireGt04.dat(MT.Kanthal)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10033), "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", 'S', OP.stick.dat(aMat), 'x', "craftingToolWireCutter", 'P', OP.plate.dat(aMat), 'C', OP.wireGt04.dat(MT.AnnealedCopper)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10043), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(MT.AnnealedCopper), 'X', OP.wireGt04.dat(MT.AnnealedCopper), 'I', OP.plateDouble.dat(ANY.Fe)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15013), " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'B', OP.bolt.dat(ANY.Steel), 'C', MT.DATA.CABLES_01[3]));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20093), " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'S', OP.wireGt01.dat(MT.Pt), 'W', MT.DATA.CABLES_01[3]));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20163), " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'X', IL.PUMPS[3].get(1), 'C', CS.OD_CIRCUITS[3], 'W', MT.DATA.CABLES_01[3], 'M', OP.pipeMedium.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Electric_T[4];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10004), " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", 'd', "craftingToolScrewdriver", 'P', OP.plateDouble.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'C', OP.wireGt08.dat(MT.Nichrome)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10024), "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'I', OP.stickLong.dat(MT.IronMagnetic), 'C', OP.wireGt08.dat(MT.Nichrome)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10034), "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", 'S', OP.stick.dat(aMat), 'x', "craftingToolWireCutter", 'P', OP.plate.dat(aMat), 'C', OP.wireGt08.dat(MT.AnnealedCopper)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10044), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(MT.AnnealedCopper), 'X', OP.wireGt04.dat(MT.AnnealedCopper), 'I', OP.plateDouble.dat(ANY.Fe)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15014), " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'B', OP.bolt.dat(ANY.Steel), 'C', MT.DATA.CABLES_01[4]));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20094), " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'S', OP.wireGt01.dat(MT.Pt), 'W', MT.DATA.CABLES_01[4]));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20164), " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'X', IL.PUMPS[4].get(1), 'C', CS.OD_CIRCUITS[4], 'W', MT.DATA.CABLES_01[4], 'M', OP.pipeLarge.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Electric_T[5];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10005), " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", 'd', "craftingToolScrewdriver", 'P', OP.plateDouble.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'C', OP.wireGt16.dat(MT.Nichrome)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10025), "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", 'P', OP.plateDouble.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.screw.dat(aMat), 'L', OP.stickLong.dat(aMat), 'I', OP.stickLong.dat(MT.IronMagnetic), 'C', OP.wireGt16.dat(MT.Nichrome)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10035), "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", 'S', OP.stick.dat(aMat), 'x', "craftingToolWireCutter", 'P', OP.plate.dat(aMat), 'C', OP.wireGt01.dat(MT.AnnealedCopper)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10045), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(MT.AnnealedCopper), 'X', OP.wireGt04.dat(MT.AnnealedCopper), 'I', OP.plateDouble.dat(ANY.Fe)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15015), " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'B', OP.bolt.dat(ANY.Steel), 'C', MT.DATA.CABLES_01[5]));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20095), " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'S', OP.wireGt01.dat(MT.Pt), 'W', MT.DATA.CABLES_01[5]));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20165), " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'X', IL.PUMPS[5].get(1), 'C', CS.OD_CIRCUITS[5], 'W', MT.DATA.CABLES_01[5], 'M', OP.pipeHuge.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Electric_T[6];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10046), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(MT.AnnealedCopper), 'X', OP.wireGt04.dat(MT.AnnealedCopper), 'I', OP.plateDouble.dat(ANY.Fe)));
    	
    	aMat = MT.DATA.Electric_T[7];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10047), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(MT.AnnealedCopper), 'X', OP.wireGt04.dat(MT.AnnealedCopper), 'I', OP.plateDouble.dat(ANY.Fe)));
    	
    	aMat = MT.DATA.Electric_T[8];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10048), " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", 'x', "craftingToolWireCutter", 'S', OP.stick.dat(aMat), 'P', OP.plate.dat(aMat), 'W', OP.wireGt01.dat(MT.AnnealedCopper), 'X', OP.wireGt04.dat(MT.AnnealedCopper), 'I', OP.plateDouble.dat(ANY.Fe)));
    
    	aMat = MT.DATA.Heat_T[1];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20001), " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'P', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20171), "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block, 'P', OP.pipeMedium.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20191), " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.casingMachine.dat(aMat), 'S', OP.stick.dat(MT.Blaze), 'C', OP.plateDouble.dat(ANY.Cu), 'G', Blocks.glass, 'P', OP.pipeTiny.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Heat_T[2];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20002), " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'P', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20172), "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block, 'P', OP.pipeMedium.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20192), " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.casingMachine.dat(aMat), 'S', OP.stick.dat(MT.Blaze), 'C', OP.plateDouble.dat(ANY.Cu), 'G', Blocks.glass, 'P', OP.pipeSmall.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Heat_T[3];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20003), " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'P', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20173), "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block, 'P', OP.pipeMedium.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20193), " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.casingMachine.dat(aMat), 'S', OP.stick.dat(MT.Blaze), 'C', OP.plateDouble.dat(ANY.Cu), 'G', Blocks.glass, 'P', OP.pipeMedium.dat(MT.StainlessSteel)));
    	
    	aMat = MT.DATA.Heat_T[4];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20004), " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", 'L', OP.stickLong.dat(aMat), 'w', "craftingToolWrench", 'P', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20174), "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.plate.dat(aMat), 'C', OP.plateDouble.dat(ANY.Cu), 'B', Blocks.brick_block, 'P', OP.pipeMedium.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20194), " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", 'w', "craftingToolWrench", 'L', OP.stickLong.dat(aMat), 'M', OP.casingMachine.dat(aMat), 'S', OP.stick.dat(MT.Blaze), 'C', OP.plateDouble.dat(ANY.Cu), 'G', Blocks.glass, 'P', OP.pipeLarge.dat(MT.StainlessSteel)));
    	
    	aMat = MT.TungstenSteel;
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(18100), "TL ", "wPd", " GT", " P ", "P P", " P ", "TL ", " P ", " GT", 'w', "craftingToolWrench", 'd', "craftingToolScrewdriver", 'L', OP.stickLong.dat(aMat), 'P', OP.plate.dat(aMat), 'G', OP.gearGt.dat(aMat), 'T', OP.screw.dat(aMat)));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10060), " P ", "ZMZ", " P ", " M ", "M M", " M ", " P ", "ZMZ", " P ", 'M', OP.plate.dat(MT.Pt), 'P', OP.pipeMedium.dat(MT.Pt), 'Z', OP.plate.dat(MT.Plastic)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10061), " P ", "ZMZ", " P ", " M ", "M M", " M ", " P ", "ZMZ", " P ", 'M', OP.plate.dat(MT.W), 'P', OP.pipeMedium.dat(MT.W), 'Z', OP.plate.dat(MT.Plastic)));
    }
    
    private static void metalset(MultiTileEntityRegistry aRegistry, OreDictMaterial aMat, int aID) {
    	CraftingManager3D t = getInstance();
    	//aRegistry.getItem(aID);// 			Chest; 				new Object[] { "sPw", "RSR", "PPP", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(500 + aID), "   ", "SRS", "s w", "WWW", "W W", "WWW", "   ", "SRS", "   ", 'S', OP.stick.dat(aMat), 'R', OP.ring.dat(aMat), 'W', "plankWood", 's', "craftingToolSaw", 'w', "craftingToolWrench"));
    	//aRegistry.getItem(500 + aID); // 	Reinforced Chest; 	new Object[] { "sSw", "RCR", "SSS", Character.valueOf('C'), OreDictNames.craftingChest, Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(2000 + aID), " G ", "GPG", " O ", " P ", "PSP", " P ", "   ", " P ", "   ", 'P', OP.plateTriple.dat(aMat), 'G', OP.gearGtSmall.dat(aMat), 'O', OP.gearGt.dat(aMat), 'S', OP.stick.dat(aMat)));
    	//aRegistry.getItem(2000 + aID);// 	Mechanical Safe;	new Object[] { "PGP", "GOS", "PGP", Character.valueOf('P'), OP.plateQuintuple.dat(aMat), Character.valueOf('G'), OP.gearGtSmall.dat(aMat), Character.valueOf('O'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(3000 + aID), " O ", "GPG", " G ", " P ", "PSP", " P ", "   ", " P ", "   ", 'P', OP.plateTriple.dat(aMat), 'G', OP.gearGtSmall.dat(aMat), 'O', OP.gearGt.dat(aMat), 'S', OP.stick.dat(aMat)));
    	//aRegistry.getItem(3000 + aID);// 	Locked Safe;		new Object[] { "PGP", "OGS", "PGP", Character.valueOf('P'), OP.plateQuintuple.dat(aMat), Character.valueOf('G'), OP.gearGtSmall.dat(aMat), Character.valueOf('O'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(5000 + aID), " P ", "PCP", " P ", "WWW", "W W", "WWW", " T ", " d ", " T ", 'd', "craftingToolScrewdriver", 'P', OP.plate.dat(aMat), 'T', OP.screw.dat(aMat), 'C', "craftingWorkBench", 'W', "plankWood"));
    	//aRegistry.getItem(5000 + aID);// 	Advanced Crafting;	new Object[] { "PdP", "TWT", "PCP", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('W'), OreDictNames.craftingWorkBench, Character.valueOf('C'), OreDictNames.craftingChest });
 
    	//aRegistry.getItem(5500 + aID);// 	Charged Crafting;	new Object[] { "TCT", "dMx", "WWW", Character.valueOf('M'), aRegistry.getItem(5000 + aID), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_04[3], Character.valueOf('C'), CS.OD_CIRCUITS[3] });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(7300 + aID), " R ", "SPS", " w ", "LPL", "P P", " P ", " R ", "SPS", " M ", 'M', OP.casingMachine.dat(aMat), 'P', OP.plate.dat(aMat), 'S', OP.stick.dat(aMat), 'L', "itemLeather", 'R', OP.ring.dat(aMat), 'w', "craftingToolWrench"));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(7300 + aID), "SRS", "SPS", "PPP", "LPL", "PwP", "PPP", "SRS", "SPS", "PPP", 'P', OP.plate.dat(aMat), 'S', OP.stick.dat(aMat), 'L', "itemLeather", 'R', OP.ring.dat(aMat)));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(7300 + aID), "   ", "SPS", " w ", "LPL", "PCP", " P ", "   ", "SPS", "   ", 'P', OP.plate.dat(aMat), 'C', aRegistry.getItem(aID), 'S', OP.stickLong.dat(aMat), 'L', "itemLeather"));
    	//aRegistry.getItem(7300 + aID);// 	Locker;				new Object[] { "SLS", "LCL", "PMP", Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('L'), "itemLeather" });
    	
    	//aRegistry.getItem(7500 + aID);// 	Charging Locker;	new Object[] { "WCW", "WMW", "WCW", Character.valueOf('M'), aRegistry.getItem(7300 + aID), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[3], Character.valueOf('C'), CS.OD_CIRCUITS[3] });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(4000 + aID), "CPC", "PPP", "CPC", "SPS", "PwP", "SPS", 'P', OP.plate.dat(aMat), 'C', aRegistry.getItem(aID), 'S', OP.stick.dat(aMat), 'w', "craftingToolWrench"));
    	//aRegistry.getItem(4000 + aID);// 	Compartment Drawer;	new Object[] { "CPC", "PMP", "CPC", Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(6000 + aID), "PCP", "CPC", "PCP", "SPS", "PwP", "SPS", 'P', OP.plate.dat(aMat), 'C', aRegistry.getItem(aID), 'S', OP.stick.dat(aMat), 'w', "craftingToolWrench"));
    	//aRegistry.getItem(6000 + aID);// 	Mass Storage;		new Object[] { "PCP", "CMC", "PCP", Character.valueOf('M'), OP.casingMachineQuadruple.dat(aMat), Character.valueOf('P'), OP.plateQuintuple.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID) });

    	//aRegistry.getItem(7100 + aID);// 	Bookshelf;			new Object[] { "PPP", "sfh", "PPP", Character.valueOf('P'), OP.plate.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(8000 + aID), "PPP", " X ", "   ", "P P", "XRX", "   ", "PPP", " X ", "   ", 'P', OP.plate.dat(aMat), 'X', OP.plateCurved.dat(aMat), 'R', OP.ring.dat(aMat)));
    	//aRegistry.getItem(8000 + aID);// 	Hopper;				new Object[] { "PwP", "XCX", " Xh", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), OP.plateCurved.dat(aMat), Character.valueOf('C'), OreDictNames.craftingChest });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(8000 + aID), "PPP", " X ", "   ", "PCP", "XRX", "   ", "PPP", " X ", "   ", 'P', OP.plate.dat(aMat), 'X', OP.plateCurved.dat(aMat), 'R', OP.ring.dat(aMat), 'C', "craftingChest"));
    	//aRegistry.getItem(8200 + aID);// 	Queue Hopper;		new Object[] { "PCP", "XCX", "wXh", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), OP.plateCurved.dat(aMat), Character.valueOf('C'), OreDictNames.craftingChest });
    }
    
    private static void storages(MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aMetal, MultiTileEntityBlock aMetalChips, MultiTileEntityBlock aMetalWires, MultiTileEntityBlock aMachine, MultiTileEntityBlock aWooden, MultiTileEntityBlock aBush, MultiTileEntityBlock aStone, MultiTileEntityBlock aWool, MultiTileEntityBlock aTNT, MultiTileEntityBlock aHive, MultiTileEntityBlock aUtilMetal, MultiTileEntityBlock aUtilStone, MultiTileEntityBlock aUtilWood, MultiTileEntityBlock aUtilWool) {

   		metalset(aRegistry, MT.Pb, 					0);
   		metalset(aRegistry, MT.Bi, 					16);
   		metalset(aRegistry, MT.Ni, 					22);
   		metalset(aRegistry, MT.Bronze, 				9);
   		metalset(aRegistry, MT.FakeOsmium, 			37);
   		metalset(aRegistry, MT.Al, 					1);
   		metalset(aRegistry, MT.Brass, 				8);
   		metalset(aRegistry, MT.TinAlloy, 			5);
   		metalset(aRegistry, MT.Co, 					21);
   		metalset(aRegistry, MT.Ardite, 				38);
   		metalset(aRegistry, MT.Ge, 					23);
   		metalset(aRegistry, MT.Invar, 				6);
   		metalset(aRegistry, MT.Steel, 				10);
   		metalset(aRegistry, MT.HSLA, 				18);
   		metalset(aRegistry, MT.Au, 					2);
   		metalset(aRegistry, MT.Ag, 					3);
   		metalset(aRegistry, MT.Manyullyn, 			39);
   		metalset(aRegistry, MT.Knightmetal, 		25);
   		metalset(aRegistry, MT.SteelGalvanized, 	19);
   		metalset(aRegistry, MT.Meteorite, 			43);
   		metalset(aRegistry, MT.MeteoricSteel, 		24);
   		metalset(aRegistry, MT.GildedIron, 			20);
   		metalset(aRegistry, MT.Electrum, 			7);
   		metalset(aRegistry, MT.StainlessSteel, 		11);
   		metalset(aRegistry,	MT.Thaumium, 			27);
   		metalset(aRegistry, MT.Manasteel, 			40);
   		metalset(aRegistry, MT.Ti, 					12);
   		metalset(aRegistry, MT.Cr, 					13);
   		metalset(aRegistry, MT.Pt, 					4);
   		metalset(aRegistry, MT.Desh, 				30);
   		metalset(aRegistry, MT.Terrasteel, 			42);
   		metalset(aRegistry, MT.TungstenSteel, 		14);
   		metalset(aRegistry, MT.TungstenCarbide, 	17);
   		metalset(aRegistry, MT.DuraniumAlloy, 		31);
   		metalset(aRegistry, MT.Draconium, 			35);
   		metalset(aRegistry, ANY.W, 					26);
    	metalset(aRegistry, MT.Ir, 					15);
    	metalset(aRegistry, MT.Os, 					29);
    	metalset(aRegistry, MT.VoidMetal, 			28);
    	metalset(aRegistry, MT.ElvenElementium, 	41);
    	metalset(aRegistry, MT.TritaniumAlloy, 		32);
    	metalset(aRegistry, MT.Ad, 					33);
    	metalset(aRegistry, MT.Bedrock_HSLA_Alloy, 	34);
    	metalset(aRegistry, MT.DraconiumAwakened, 	36);
    }

    private CraftingManager3D()
    {
    	addRecipe(new Prefix3DRecipe(OP.casingMachine,			new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachine), 		new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, 'P', OP.plate, 'L', OP.stickLong));
    	addRecipe(new Prefix3DRecipe(OP.casingMachineDouble,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachineDouble), 	new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, 'P', OP.plateDouble, 'L', OP.stickLong));
    	addRecipe(new Prefix3DRecipe(OP.casingMachineQuadruple,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachineQuadruple),new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, 'P', OP.plateQuadruple, 'L', OP.stickLong));
    	addRecipe(new Prefix3DRecipe(OP.casingMachineDense,		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachineDense), 	new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, 'P', OP.plateDense, 'L', OP.stickLong));
    }
    
    public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World p_82787_2_)
    {
        int j;
        {
            for (j = 0; j < this.recipes.size(); ++j)
            {
                IRecipe irecipe = (IRecipe)this.recipes.get(j);

                if (irecipe.matches(p_82787_1_, p_82787_2_))
                {
                    return irecipe.getCraftingResult(p_82787_1_);
                }
            }

            return null;
        }
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
    
    public void addRecipe(IRecipe recipe)
    {
    	this.getRecipeList().add(recipe);
    }

	@Override
	public void run() {
		addRecipe(new CraftingRecipe3D(OP.dust.mat(MT.Diamond, 1), "AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", 'A', OP.dust.dat(MT.Ash), 'B', OP.dust.dat(MT.Basalt), 'C', OP.dust.dat(MT.CoalCoke), 'D', OP.dust.dat(MT.DarkAsh), 'E', OP.dust.dat(MT.EnderPearl), 'F', OP.dust.dat(MT.Flint), 'G', OP.dust.dat(MT.Graphite), 'H', OP.dust.dat(MT.HydratedCoal), 'I', OP.dust.dat(MT.Fe)));
		addRecipe(new QTArmor3D(0, TD.Processing.SMITHABLE, new Object[]{"XXX", "X X", "   ", "XXX", "XfX", "   ", "XXX", "XXX", "   ", 'X', OreDictPrefix.get("link"), 'f', "craftingToolSaw"}));
		addRecipe(new QTArmor3D(0, TD.Processing.SMITHABLE, new Object[]{"   ", "XXX", "X X", "   ", "XXX", "XfX", "   ", "XXX", "XXX", 'X', OreDictPrefix.get("link"), 'f', "craftingToolSaw"}));
		addRecipe(new QTArmor3D(1, TD.Processing.SMITHABLE, new Object[]{"X X", "XXX", "XXX", "X X", "XfX", "X X", "XXX", "XXX", "XXX", 'X', OreDictPrefix.get("link"), 'f', "craftingToolSaw"}));
		addRecipe(new QTArmor3D(2, TD.Processing.SMITHABLE, new Object[]{"XXX", "X X", "XfX", "X X", "XXX", "X X", "XXX", "X X", "X X", 'X', OreDictPrefix.get("link"), 'f', "craftingToolSaw"}));
		addRecipe(new QTArmor3D(3, TD.Processing.SMITHABLE, new Object[]{"   ", "   ", "XfX", "   ", "X X", " X ", "X X", "X X", "X X", 'X', OreDictPrefix.get("link"), 'f', "craftingToolSaw"}));
		
		MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
    	String GTmID = MD.GT.mID;
    	
    	MultiTileEntityBlock aMetal = MultiTileEntityBlock.getOrCreate(GTmID, "iron", Material.iron, Block.soundTypeMetal, "pickaxe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aMetalChips = MultiTileEntityBlock.getOrCreate(GTmID, "iron", Material.iron, Block.soundTypeMetal, "shovel", 0, 0, 15, false, false);
    	MultiTileEntityBlock aMetalWires = MultiTileEntityBlock.getOrCreate(GTmID, "machine", MaterialMachines.instance, Block.soundTypeMetal, "cutter", 0, 0, 15, false, false);
    	MultiTileEntityBlock aMachine = MultiTileEntityBlock.getOrCreate(GTmID, "machine", MaterialMachines.instance, Block.soundTypeMetal, "wrench", 0, 0, 15, false, false);
    	MultiTileEntityBlock aWooden = MultiTileEntityBlock.getOrCreate(GTmID, "wood", Material.wood, Block.soundTypeWood, "axe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aBush = MultiTileEntityBlock.getOrCreate(GTmID, "leaves", Material.leaves, Block.soundTypeGrass, "axe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aStone = MultiTileEntityBlock.getOrCreate(GTmID, "rock", Material.rock, Block.soundTypeStone, "pickaxe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aWool = MultiTileEntityBlock.getOrCreate(GTmID, "cloth", Material.cloth, Block.soundTypeCloth, "shears", 0, 0, 15, false, false);
    	MultiTileEntityBlock aTNT = MultiTileEntityBlock.getOrCreate(GTmID, "tnt", Material.tnt, Block.soundTypeGrass, "pickaxe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aUtilMetal = MultiTileEntityBlock.getOrCreate(GTmID, "redstoneLight", Material.redstoneLight, Block.soundTypeMetal, "pickaxe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aUtilStone = MultiTileEntityBlock.getOrCreate(GTmID, "redstoneLight", Material.redstoneLight, Block.soundTypeStone, "pickaxe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aUtilWood = MultiTileEntityBlock.getOrCreate(GTmID, "redstoneLight", Material.redstoneLight, Block.soundTypeWood, "axe", 0, 0, 15, false, false);
    	MultiTileEntityBlock aUtilWool = MultiTileEntityBlock.getOrCreate(GTmID, "redstoneLight", Material.redstoneLight, Block.soundTypeCloth, "shears", 0, 0, 15, false, false);
    	MultiTileEntityBlock aHive = MultiTileEntityBlock.getOrCreate(GTmID, "rock", gregapi.block.MaterialScoopable.instance, Block.soundTypeWood, "scoop", 0, 0, 15, false, false);
    		
    	System.out.println("Beginning 3x3x3 GT recipes");
    	storages(aRegistry, aMetal, aMetalChips, aMetalWires, aMachine, aWooden, aBush, aStone, aWool, aTNT, aHive, aUtilMetal, aUtilStone, aUtilWood, aUtilWool);
    	machinery(aRegistry);
    	System.out.println("Completed 3x3x3 GT recipes");
	}
}
