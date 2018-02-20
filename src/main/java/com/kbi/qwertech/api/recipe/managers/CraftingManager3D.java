package com.kbi.qwertech.api.recipe.managers;

import gregapi.block.MaterialMachines;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ICondition;
import gregapi.data.ANY;
import gregapi.data.CS;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import com.kbi.qwertech.api.recipe.CraftingRecipe3D;
import com.kbi.qwertech.api.recipe.Prefix3DRecipe;
import com.kbi.qwertech.api.recipe.QTArmor3D;

public class CraftingManager3D implements Runnable {

	private static final CraftingManager3D instance = new CraftingManager3D();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();

    /**
     * Returns the static instance of this class
     */
    public static CraftingManager3D getInstance()
    {
        /** The static instance of this class */
        return instance;
    }
    
    private static void machinery(MultiTileEntityRegistry aRegistry)
    {
    	OreDictMaterial aMat;
    	CraftingManager3D t = getInstance();
    	
    	aMat = MT.DATA.Kinetic_T[1];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1512), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Bronze) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1515), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Brass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1518), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Invar) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16001), new Object[] { " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR",Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.pipeSmall.dat(MT.StainlessSteel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20011), new Object[] { " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20021), new Object[] { " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20031), new Object[] { "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(MT.Diamond) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20011), new Object[] { "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20021), new Object[] { "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20031), new Object[] { "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(ANY.Sapphire) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20051), new Object[] { "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('W'), OP.wireFine.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20061), new Object[] { " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(MT.Diamond), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(ANY.Steel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20061), new Object[] { " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(ANY.Sapphire), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(ANY.Steel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20071), new Object[] { " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('P'), OP.plateTriple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20081), new Object[] { " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stickLong.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20101), new Object[] { "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('H'), OP.plateQuintuple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20111), new Object[] { " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20121), new Object[] { " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20131), new Object[] { " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat)}));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20141), new Object[] { " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('R'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plateQuadruple.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20151), new Object[] { " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20181), new Object[] { "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.plate.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Kinetic_T[2];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1522), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Steel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1525), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Cr) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1527), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.IronWood) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1528), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Steeleaf) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1529), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Thaumium) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16002), new Object[] { " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR",Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.pipeMedium.dat(MT.StainlessSteel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20012), new Object[] { " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20022), new Object[] { " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20032), new Object[] { "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(MT.Diamond) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20012), new Object[] { "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20022), new Object[] { "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20032), new Object[] { "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(ANY.Sapphire) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20052), new Object[] { "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('W'), OP.wireFine.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20062), new Object[] { " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(MT.Diamond), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(MT.CobaltBrass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20062), new Object[] { " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(ANY.Sapphire), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(MT.CobaltBrass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20072), new Object[] { " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('P'), OP.plateTriple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20082), new Object[] { " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stickLong.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20102), new Object[] { "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('H'), OP.plateQuintuple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20112), new Object[] { " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20122), new Object[] { " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20132), new Object[] { " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat)}));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20142), new Object[] { " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('R'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plateQuadruple.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20152), new Object[] { " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20182), new Object[] { "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.plateDouble.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Kinetic_T[3];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1530), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Ti) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1531), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.FierySteel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1535), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Al) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1538), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Magnalium) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16003), new Object[] { " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR",Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.pipeLarge.dat(MT.StainlessSteel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20013), new Object[] { " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20023), new Object[] { " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20033), new Object[] { "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(MT.Diamond) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20013), new Object[] { "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20023), new Object[] { "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20033), new Object[] { "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(ANY.Sapphire) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20053), new Object[] { "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('W'), OP.wireFine.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20063), new Object[] { " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(MT.Diamond), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(MT.CobaltBrass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20063), new Object[] { " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(ANY.Sapphire), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(MT.CobaltBrass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20073), new Object[] { " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('P'), OP.plateTriple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20083), new Object[] { " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stickLong.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20103), new Object[] { "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('H'), OP.plateQuintuple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20113), new Object[] { " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20123), new Object[] { " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20133), new Object[] { " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat)}));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20143), new Object[] { " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('R'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plateQuadruple.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20153), new Object[] { " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20183), new Object[] { "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.plateTriple.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Kinetic_T[4];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1540), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.VoidMetal) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1545), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Trinitanium) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(1548), new Object[] { " T ", "LPL", " T ", " P ", "PGP", " P ", " T ", "LPL", " T ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.rotor.dat(MT.Graphene) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(16004), new Object[] { " L ", " MP", "wGR", " M ", "MPM", " M ", " L ", " MP", " GR",Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.pipeHuge.dat(MT.StainlessSteel) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20014), new Object[] { " L ", " P ", " w ", "GPG", "PDP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20024), new Object[] { " L ", " P ", " w ", "DPD", "PSP", " P ", " L ", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(MT.Diamond) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20034), new Object[] { "TLd", " P ", " w ", "DPG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(MT.Diamond) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20014), new Object[] { "DL ", " P ", " w ", "GPG", "P P", " P ", " LD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('D'), OP.plateGem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20024), new Object[] { "DLD", " P ", " w ", " P ", "PSP", " P ", "DLD", " P ", "   ",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('D'), OP.gem.dat(ANY.Sapphire) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20034), new Object[] { "TLd", "DPD", " w ", " PG", "P P", "SP ", " L ", " P ", "   ",Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.plateGemTiny.dat(ANY.Sapphire) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20054), new Object[] { "W W", " P ", "RSR", "xPw", "P P", " P ", "W W", " P ", "RSR",Character.valueOf('w'), "craftingToolWrench", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('W'), OP.wireFine.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20064), new Object[] { " S ", " P ", " L ", "DG ", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(MT.Diamond), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(MT.CobaltBrass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20064), new Object[] { " S ", " P ", " L ", "DGD", "PPP", " P ", " S ", " P ", " L ", Character.valueOf('M'), OP.casingMachineDouble.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat), Character.valueOf('D'), OP.dust.dat(ANY.Sapphire), Character.valueOf('G'), OP.toolHeadBuzzSaw.dat(MT.CobaltBrass) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20074), new Object[] { " P ", "RMR", "wS ", " M ", "M M", " M ", " P ", "RMR", " R ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('P'), OP.plateTriple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20084), new Object[] { " S ", " P ", "wS ", " P ", "PPP", " P ", " G ", " S ", " G ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stickLong.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20104), new Object[] { "   ", " P ", " w ", "HPH", "P P", " P ", "R R", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('H'), OP.plateQuintuple.dat(aMat), Character.valueOf('S'), OP.spring.dat(aMat), Character.valueOf('R'), OP.stick.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20114), new Object[] { " L ", " P ", " w ", "GP ", "P P", "GP ", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20124), new Object[] { " L ", " PG", " w ", " PS", "P P", " PS", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20134), new Object[] { " L ", " PG", " w ", " PG", "P P", " PG", " L ", " PG", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat)}));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20144), new Object[] { " S ", "RPR", "wS ", "SPG", "P P", " P ", " S ", "RPR", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('R'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plateQuadruple.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20154), new Object[] { " G ", "SPS", "wL ", " P ", "P P", " P ", "   ", " P ", " L ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.gearGtSmall.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20184), new Object[] { "P P", " M ", "wS ", " M ", "MMM", "SRS", "P P", " M ", " S ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('R'), OP.rotor.dat(MT.StainlessSteel), Character.valueOf('P'), OP.plateQuadruple.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Electric_T[0];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10040), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(ANY.Cu), Character.valueOf('X'), OP.wireGt04.dat(ANY.Cu), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10050), new Object[] { "WSW", "CPC", " L ", "SPS", "P P", " P ", " S ", " P ", " L ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.cableGt01.dat(ANY.Cu), Character.valueOf('C'), CS.OD_CIRCUITS[1], Character.valueOf('S'), OP.plateGem.dat(ANY.Si) }));
    	
    	aMat = MT.DATA.Electric_T[1];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10001), new Object[] { " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('C'), OP.wireGt01.dat(ANY.Cu) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10021), new Object[] { "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('I'), OP.stickLong.dat(MT.IronMagnetic), Character.valueOf('C'), OP.wireGt01.dat(ANY.Cu) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10031), new Object[] { "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.wireGt01.dat(ANY.Cu) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10041), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(ANY.Cu), Character.valueOf('X'), OP.wireGt04.dat(ANY.Cu), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15011), new Object[] { " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('B'), OP.bolt.dat(ANY.Steel), Character.valueOf('C'), gregapi.data.MT.DATA.CABLES_01[1] }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20091), new Object[] { " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.wireGt01.dat(MT.Pt), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[1] }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20161), new Object[] { " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), IL.PUMPS[1].get(1), Character.valueOf('C'), CS.OD_CIRCUITS[1], Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[1], Character.valueOf('M'), OP.pipeTiny.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Electric_T[2];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10002), new Object[] { " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('C'), OP.wireGt02.dat(MT.Constantan) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10022), new Object[] { "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('I'), OP.stickLong.dat(MT.IronMagnetic), Character.valueOf('C'), OP.wireGt02.dat(MT.Constantan) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10032), new Object[] { "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.wireGt02.dat(ANY.Cu) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10042), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(ANY.Cu), Character.valueOf('X'), OP.wireGt04.dat(ANY.Cu), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15012), new Object[] { " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('B'), OP.bolt.dat(ANY.Steel), Character.valueOf('C'), gregapi.data.MT.DATA.CABLES_01[2] }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20092), new Object[] { " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.wireGt01.dat(MT.Pt), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[2] }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20162), new Object[] { " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), IL.PUMPS[2].get(1), Character.valueOf('C'), CS.OD_CIRCUITS[2], Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[2], Character.valueOf('M'), OP.pipeSmall.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Electric_T[3];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10003), new Object[] { " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('C'), OP.wireGt04.dat(MT.Kanthal) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10023), new Object[] { "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('I'), OP.stickLong.dat(MT.IronMagnetic), Character.valueOf('C'), OP.wireGt04.dat(MT.Kanthal) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10033), new Object[] { "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.wireGt04.dat(MT.AnnealedCopper) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10043), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(MT.AnnealedCopper), Character.valueOf('X'), OP.wireGt04.dat(MT.AnnealedCopper), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15013), new Object[] { " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('B'), OP.bolt.dat(ANY.Steel), Character.valueOf('C'), gregapi.data.MT.DATA.CABLES_01[3] }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20093), new Object[] { " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.wireGt01.dat(MT.Pt), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[3] }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20163), new Object[] { " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), IL.PUMPS[3].get(1), Character.valueOf('C'), CS.OD_CIRCUITS[3], Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[3], Character.valueOf('M'), OP.pipeMedium.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Electric_T[4];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10004), new Object[] { " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('C'), OP.wireGt08.dat(MT.Nichrome) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10024), new Object[] { "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('I'), OP.stickLong.dat(MT.IronMagnetic), Character.valueOf('C'), OP.wireGt08.dat(MT.Nichrome) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10034), new Object[] { "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.wireGt08.dat(MT.AnnealedCopper) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10044), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(MT.AnnealedCopper), Character.valueOf('X'), OP.wireGt04.dat(MT.AnnealedCopper), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15014), new Object[] { " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('B'), OP.bolt.dat(ANY.Steel), Character.valueOf('C'), gregapi.data.MT.DATA.CABLES_01[4] }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20094), new Object[] { " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.wireGt01.dat(MT.Pt), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[4] }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20164), new Object[] { " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), IL.PUMPS[4].get(1), Character.valueOf('C'), CS.OD_CIRCUITS[4], Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[4], Character.valueOf('M'), OP.pipeLarge.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Electric_T[5];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10005), new Object[] { " C ", "TPT", "dC ", "LPL", "P P", " P ", " C ", "TPT", " C ", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('C'), OP.wireGt16.dat(MT.Nichrome) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10025), new Object[] { "TIT", "LPL", " d ", "CPC", "P P", " P ", "   ", " P ", "TG ", Character.valueOf('P'), OP.plateDouble.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('I'), OP.stickLong.dat(MT.IronMagnetic), Character.valueOf('C'), OP.wireGt16.dat(MT.Nichrome) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10035), new Object[] { "CxC", "SPS", "C C", " P ", "P P", " P ", "C C", "SPS", "C C", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.wireGt01.dat(MT.AnnealedCopper) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10045), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(MT.AnnealedCopper), Character.valueOf('X'), OP.wireGt04.dat(MT.AnnealedCopper), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(15015), new Object[] { " L ", " P ", " w ", "BPB", "P P", "CPC", " L ", " P ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('B'), OP.bolt.dat(ANY.Steel), Character.valueOf('C'), gregapi.data.MT.DATA.CABLES_01[5] }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20095), new Object[] { " L ", " P ", "wW ", "SP ", "P P", "SP ", " L ", " P ", " W ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.wireGt01.dat(MT.Pt), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[5] }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20165), new Object[] { " L ", " P ", " M ", "XP ", "PCP", "XPW", " L ", " P ", " M ", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), IL.PUMPS[5].get(1), Character.valueOf('C'), CS.OD_CIRCUITS[5], Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[5], Character.valueOf('M'), OP.pipeHuge.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Electric_T[6];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10046), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(MT.AnnealedCopper), Character.valueOf('X'), OP.wireGt04.dat(MT.AnnealedCopper), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	
    	aMat = MT.DATA.Electric_T[7];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10047), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(MT.AnnealedCopper), Character.valueOf('X'), OP.wireGt04.dat(MT.AnnealedCopper), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    	
    	aMat = MT.DATA.Electric_T[8];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10048), new Object[] { " I ", " P ", "Xx ", "SPS", "P P", "SPS", "WIW", " P ", "W W", Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('W'), OP.wireGt01.dat(MT.AnnealedCopper), Character.valueOf('X'), OP.wireGt04.dat(MT.AnnealedCopper), Character.valueOf('I'), OP.plateDouble.dat(ANY.Fe) }));
    
    	aMat = MT.DATA.Heat_T[1];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20001), new Object[] { " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20171), new Object[] { "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block, Character.valueOf('P'), OP.pipeMedium.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20191), new Object[] { " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('S'), OP.stick.dat(MT.Blaze), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('G'), Blocks.glass, Character.valueOf('P'), OP.pipeTiny.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Heat_T[2];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20002), new Object[] { " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20172), new Object[] { "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block, Character.valueOf('P'), OP.pipeMedium.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20192), new Object[] { " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('S'), OP.stick.dat(MT.Blaze), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('G'), Blocks.glass, Character.valueOf('P'), OP.pipeSmall.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Heat_T[3];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20003), new Object[] { " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20173), new Object[] { "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block, Character.valueOf('P'), OP.pipeMedium.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20193), new Object[] { " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('S'), OP.stick.dat(MT.Blaze), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('G'), Blocks.glass, Character.valueOf('P'), OP.pipeMedium.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.DATA.Heat_T[4];
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20004), new Object[] { " L ", " P ", " w ", " P ", "PPP", " C ", " L ", " P ", "BBB", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('w'), "craftingToolWrench", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20174), new Object[] { "PL ", " M ", " w ", "PM ", "MMM", "BCB", "PL ", " M ", "   ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.plate.dat(aMat), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('B'), Blocks.brick_block, Character.valueOf('P'), OP.pipeMedium.dat(aMat) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(20194), new Object[] { " L ", " M ", "SPS", "GMG", "MMM", " C ", " L ", " M ", " w ", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('S'), OP.stick.dat(MT.Blaze), Character.valueOf('C'), OP.plateDouble.dat(ANY.Cu), Character.valueOf('G'), Blocks.glass, Character.valueOf('P'), OP.pipeLarge.dat(MT.StainlessSteel) }));
    	
    	aMat = MT.TungstenSteel;
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(18100), new Object[] { "TL ", "wPd", " GT", " P ", "P P", " P ", "TL ", " P ", " GT", Character.valueOf('w'), "craftingToolWrench", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('L'), OP.stickLong.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('G'), OP.gearGt.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat) }));
    	
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10060), new Object[] { " P ", "ZMZ", " P ", " M ", "M M", " M ", " P ", "ZMZ", " P ", Character.valueOf('M'), OP.plate.dat(MT.Pt), Character.valueOf('P'), OP.pipeMedium.dat(MT.Pt), Character.valueOf('Z'), OP.plate.dat(MT.Plastic) }));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(10061), new Object[] { " P ", "ZMZ", " P ", " M ", "M M", " M ", " P ", "ZMZ", " P ", Character.valueOf('M'), OP.plate.dat(MT.W), Character.valueOf('P'), OP.pipeMedium.dat(MT.W), Character.valueOf('Z'), OP.plate.dat(MT.Plastic) }));
    }
    
    private static void metalset(MultiTileEntityRegistry aRegistry, OreDictMaterial aMat, int aID) {
    	CraftingManager3D t = getInstance();
    	//aRegistry.getItem(aID);// 			Chest; 				new Object[] { "sPw", "RSR", "PPP", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(500 + aID),  new Object[]{"   ", "SRS", "s w", "WWW", "W W", "WWW", "   ", "SRS", "   ", Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('W'), "plankWood", Character.valueOf('s'), "craftingToolSaw", Character.valueOf('w'), "craftingToolWrench"}));
    	//aRegistry.getItem(500 + aID); // 	Reinforced Chest; 	new Object[] { "sSw", "RCR", "SSS", Character.valueOf('C'), OreDictNames.craftingChest, Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(2000 + aID), new Object[]{" G ", "GPG", " O ", " P ", "PSP", " P ", "   ", " P ", "   ", Character.valueOf('P'), OP.plateTriple.dat(aMat), Character.valueOf('G'), OP.gearGtSmall.dat(aMat), Character.valueOf('O'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat)}));
    	//aRegistry.getItem(2000 + aID);// 	Mechanical Safe;	new Object[] { "PGP", "GOS", "PGP", Character.valueOf('P'), OP.plateQuintuple.dat(aMat), Character.valueOf('G'), OP.gearGtSmall.dat(aMat), Character.valueOf('O'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(3000 + aID), new Object[]{" O ", "GPG", " G ", " P ", "PSP", " P ", "   ", " P ", "   ", Character.valueOf('P'), OP.plateTriple.dat(aMat), Character.valueOf('G'), OP.gearGtSmall.dat(aMat), Character.valueOf('O'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat)}));
    	//aRegistry.getItem(3000 + aID);// 	Locked Safe;		new Object[] { "PGP", "OGS", "PGP", Character.valueOf('P'), OP.plateQuintuple.dat(aMat), Character.valueOf('G'), OP.gearGtSmall.dat(aMat), Character.valueOf('O'), OP.gearGt.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(5000 + aID), new Object[]{" P ", "PCP", " P ", "WWW", "W W", "WWW", " T ", " d ", " T ", Character.valueOf('d'), "craftingToolScrewdriver", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('C'), "craftingWorkBench", Character.valueOf('W'), "plankWood"}));
    	//aRegistry.getItem(5000 + aID);// 	Advanced Crafting;	new Object[] { "PdP", "TWT", "PCP", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('W'), OreDictNames.craftingWorkBench, Character.valueOf('C'), OreDictNames.craftingChest });
 
    	//aRegistry.getItem(5500 + aID);// 	Charged Crafting;	new Object[] { "TCT", "dMx", "WWW", Character.valueOf('M'), aRegistry.getItem(5000 + aID), Character.valueOf('T'), OP.screw.dat(aMat), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_04[3], Character.valueOf('C'), CS.OD_CIRCUITS[3] });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(7300 + aID), new Object[]{" R ", "SPS", " w ", "LPL", "P P", " P ", " R ", "SPS", " M ", Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('L'), "itemLeather", Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('w'), "craftingToolWrench"}));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(7300 + aID), new Object[]{"SRS", "SPS", "PPP", "LPL", "PwP", "PPP", "SRS", "SPS", "PPP", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('L'), "itemLeather", Character.valueOf('R'), OP.ring.dat(aMat)}));
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(7300 + aID), new Object[]{"   ", "SPS", " w ", "LPL", "PCP", " P ", "   ", "SPS", "   ", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID), Character.valueOf('S'), OP.stickLong.dat(aMat), Character.valueOf('L'), "itemLeather"}));
    	//aRegistry.getItem(7300 + aID);// 	Locker;				new Object[] { "SLS", "LCL", "PMP", Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('L'), "itemLeather" });
    	
    	//aRegistry.getItem(7500 + aID);// 	Charging Locker;	new Object[] { "WCW", "WMW", "WCW", Character.valueOf('M'), aRegistry.getItem(7300 + aID), Character.valueOf('W'), gregapi.data.MT.DATA.CABLES_01[3], Character.valueOf('C'), CS.OD_CIRCUITS[3] });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(4000 + aID), new Object[]{"CPC", "PPP", "CPC", "SPS", "PwP", "SPS", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('w'), "craftingToolWrench"}));
    	//aRegistry.getItem(4000 + aID);// 	Compartment Drawer;	new Object[] { "CPC", "PMP", "CPC", Character.valueOf('M'), OP.casingMachine.dat(aMat), Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(6000 + aID), new Object[]{"PCP", "CPC", "PCP", "SPS", "PwP", "SPS", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID), Character.valueOf('S'), OP.stick.dat(aMat), Character.valueOf('w'), "craftingToolWrench"}));
    	//aRegistry.getItem(6000 + aID);// 	Mass Storage;		new Object[] { "PCP", "CMC", "PCP", Character.valueOf('M'), OP.casingMachineQuadruple.dat(aMat), Character.valueOf('P'), OP.plateQuintuple.dat(aMat), Character.valueOf('C'), aRegistry.getItem(aID) });

    	//aRegistry.getItem(7100 + aID);// 	Bookshelf;			new Object[] { "PPP", "sfh", "PPP", Character.valueOf('P'), OP.plate.dat(aMat) });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(8000 + aID), new Object[]{"PPP", " X ", "   ", "P P", "XRX", "   ", "PPP", " X ", "   ", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), OP.plateCurved.dat(aMat), Character.valueOf('R'), OP.ring.dat(aMat)}));
    	//aRegistry.getItem(8000 + aID);// 	Hopper;				new Object[] { "PwP", "XCX", " Xh", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), OP.plateCurved.dat(aMat), Character.valueOf('C'), OreDictNames.craftingChest });
    	t.addRecipe(new CraftingRecipe3D(aRegistry.getItem(8000 + aID), new Object[]{"PPP", " X ", "   ", "PCP", "XRX", "   ", "PPP", " X ", "   ", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), OP.plateCurved.dat(aMat), Character.valueOf('R'), OP.ring.dat(aMat), Character.valueOf('C'), "craftingChest"}));
    	//aRegistry.getItem(8200 + aID);// 	Queue Hopper;		new Object[] { "PCP", "XCX", "wXh", Character.valueOf('P'), OP.plate.dat(aMat), Character.valueOf('X'), OP.plateCurved.dat(aMat), Character.valueOf('C'), OreDictNames.craftingChest });
    }
    
    private static void storages(MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aMetal, MultiTileEntityBlock aMetalChips, MultiTileEntityBlock aMetalWires, MultiTileEntityBlock aMachine, MultiTileEntityBlock aWooden, MultiTileEntityBlock aBush, MultiTileEntityBlock aStone, MultiTileEntityBlock aWool, MultiTileEntityBlock aTNT, MultiTileEntityBlock aHive, MultiTileEntityBlock aUtilMetal, MultiTileEntityBlock aUtilStone, MultiTileEntityBlock aUtilWood, MultiTileEntityBlock aUtilWool, OreDictMaterial aMat, Class aClass) {

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
    	addRecipe(new Prefix3DRecipe(OP.casingMachine,			new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachine), 		new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, Character.valueOf('P'), OP.plate, Character.valueOf('L'), OP.stickLong));
    	addRecipe(new Prefix3DRecipe(OP.casingMachineDouble,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachineDouble), 	new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, Character.valueOf('P'), OP.plateDouble, Character.valueOf('L'), OP.stickLong));
    	addRecipe(new Prefix3DRecipe(OP.casingMachineQuadruple,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachineQuadruple),new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, Character.valueOf('P'), OP.plateQuadruple, Character.valueOf('L'), OP.stickLong));
    	addRecipe(new Prefix3DRecipe(OP.casingMachineDense,		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, OP.casingMachineDense), 	new String[]{" L ", " P ", "   ", " P ", "P P", " P ", " L ", " P ", "   "}, Character.valueOf('P'), OP.plateDense, Character.valueOf('L'), OP.stickLong));
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
		addRecipe(new CraftingRecipe3D(OP.dust.mat(MT.Diamond, 1), new Object[]{"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", Character.valueOf('A'), OP.dust.dat(MT.Ash), Character.valueOf('B'), OP.dust.dat(MT.Basalt), Character.valueOf('C'), OP.dust.dat(MT.CoalCoke), Character.valueOf('D'), OP.dust.dat(MT.DarkAsh), Character.valueOf('E'), OP.dust.dat(MT.EnderPearl), Character.valueOf('F'), OP.dust.dat(MT.Flint), Character.valueOf('G'), OP.dust.dat(MT.Graphite), Character.valueOf('H'), OP.dust.dat(MT.HydratedCoal), Character.valueOf('I'), OP.dust.dat(MT.Fe)}));
		addRecipe(new QTArmor3D(0, TD.Processing.SMITHABLE, new Object[]{"XXX", "X X", "   ", "XXX", "XfX", "   ", "XXX", "XXX", "   ", Character.valueOf('X'), OreDictPrefix.get("link"), Character.valueOf('f'), "craftingToolSaw"}));
		addRecipe(new QTArmor3D(0, TD.Processing.SMITHABLE, new Object[]{"   ", "XXX", "X X", "   ", "XXX", "XfX", "   ", "XXX", "XXX", Character.valueOf('X'), OreDictPrefix.get("link"), Character.valueOf('f'), "craftingToolSaw"}));
		addRecipe(new QTArmor3D(1, TD.Processing.SMITHABLE, new Object[]{"X X", "XXX", "XXX", "X X", "XfX", "X X", "XXX", "XXX", "XXX", Character.valueOf('X'), OreDictPrefix.get("link"), Character.valueOf('f'), "craftingToolSaw"}));
		addRecipe(new QTArmor3D(2, TD.Processing.SMITHABLE, new Object[]{"XXX", "X X", "XfX", "X X", "XXX", "X X", "XXX", "X X", "X X", Character.valueOf('X'), OreDictPrefix.get("link"), Character.valueOf('f'), "craftingToolSaw"}));
		addRecipe(new QTArmor3D(3, TD.Processing.SMITHABLE, new Object[]{"   ", "   ", "XfX", "   ", "X X", " X ", "X X", "X X", "X X", Character.valueOf('X'), OreDictPrefix.get("link"), Character.valueOf('f'), "craftingToolSaw"}));
		
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
    	storages(aRegistry, aMetal, aMetalChips, aMetalWires, aMachine, aWooden, aBush, aStone, aWool, aTNT, aHive, aUtilMetal, aUtilStone, aUtilWood, aUtilWool, MT.NULL, null);
    	machinery(aRegistry);
    	System.out.println("Completed 3x3x3 GT recipes");
	}
}
