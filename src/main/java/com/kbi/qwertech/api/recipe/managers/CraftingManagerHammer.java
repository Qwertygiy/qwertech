package com.kbi.qwertech.api.recipe.managers;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.recipe.HammerablePrefixRecipe;
import com.kbi.qwertech.api.recipe.HammerableShapedRecipe;
import com.kbi.qwertech.api.recipe.QTArmor;
import com.kbi.qwertech.api.recipe.RepairRecipe;
import gregapi.code.ICondition;
import gregapi.config.ConfigCategories;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.ST;
import gregtech.loaders.b.Loader_OreProcessing;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftingManagerHammer implements Runnable {

	private static final CraftingManagerHammer instance = new CraftingManagerHammer();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();
    
    public static HashMap<ItemStack, String> replacems = new HashMap<ItemStack, String>();

    private static boolean hasRun = false;

    /**
     * Returns the static instance of this class
     */
    public static CraftingManagerHammer getInstance()
    {
        /* The static instance of this class */
        return instance;
    }

    public static boolean replaceItems(IRecipe recipe, ArrayList items)
	{
		boolean removeIt = false;
		for (int w = 0; w < items.size(); w++)
		{
			Object slot = items.get(w);
			if (slot instanceof ItemStack)
			{
				ItemStack theSlot = (ItemStack)slot;
				if (!(recipe instanceof ICraftingRecipeGT))
				{
					for (ItemStack key : replacems.keySet())
					{
						if (ST.equal(key, theSlot))
						{
							items.set(w, OreDictionary.getOres(replacems.get(key)));
						}
					}
				}
				if (OreDictManager.isItemStackInstanceOf(theSlot, "craftingToolHardHammer"))
				{
					items.remove(w);
					removeIt = true;
					w = w - 1;
					break;
				}
			} else if (slot instanceof List && ((List)slot).size() > 0)
			{
				for (int q = 0; q < ((List)slot).size(); q++) {
					ItemStack spot = (ItemStack) ((List) slot).get(q);
					if (spot != null && OreDictManager.isItemStackInstanceOf(spot, "craftingToolHardHammer")) {
						items.remove(w);
						removeIt = true;
						w = w - 1;
						break;
					}
				}
			}
		}
		return removeIt;
	}

	public static boolean replaceItems(IRecipe recipe, Object[] items)
	{
		boolean removeIt = false;
		for (int w = 0; w < items.length; w++)
		{
			Object slot = items[w];
			if (slot instanceof ItemStack)
			{
				ItemStack theSlot = (ItemStack)slot;
				if (!(recipe instanceof ICraftingRecipeGT))
				{
					for (ItemStack key : replacems.keySet())
					{
						if (ST.equal(key, theSlot))
						{
							items[w] = OreDictionary.getOres(replacems.get(key));
						}
					}
				}
				if (OreDictManager.isItemStackInstanceOf(theSlot, "craftingToolHardHammer"))
				{
					items[w] = null;
					removeIt = true;
				}
			} else if (slot instanceof List)
			{

				for (int q = 0; q < ((List)slot).size(); q++) {
					ItemStack spot = (ItemStack) ((List) slot).get(q);
					if (OreDictManager.isItemStackInstanceOf(spot, "craftingToolHardHammer")) {
						items[w] = null;
						removeIt = true;
						break;
					}
				}
			}
		}
		return removeIt;
	}
    
    public static void searchAndReplace()
    {
    	if (QTConfigs.allHammers && !hasRun)
    	{
			List oldRecipes = CraftingManager.getInstance().getRecipeList();
			System.out.println("Parsing through " + oldRecipes.size() + " recipes to remove hammers...");
	    	int count = 0;
	    	for (int q = 0; q < oldRecipes.size(); q++)
	    	{
	    		try {
					boolean removelIt = false;
					IRecipe recipe = (IRecipe)oldRecipes.get(q);
					if (recipe instanceof ShapelessOreRecipe) {
						ArrayList items = ((ShapelessOreRecipe) recipe).getInput();
						removelIt = replaceItems(recipe, items);
					} else if (recipe instanceof ShapedOreRecipe) {
						Object[] items = ((ShapedOreRecipe) recipe).getInput();
						removelIt = replaceItems(recipe, items);
					} else if (recipe instanceof ShapedRecipes) {
						ItemStack[] items = ((ShapedRecipes) recipe).recipeItems;
						removelIt = replaceItems(recipe, items);
					} else if (recipe instanceof ShapelessRecipes) {
						//immutable :/
					}
					if (removelIt) {
						instance.addRecipe(recipe);
						oldRecipes.remove(recipe);
						count = count + 1;
						q = q - 1;
					}
				} catch (Throwable t) {
	    			System.out.println("Exception found!");
	    			t.printStackTrace();
				}
	    	}
	    	System.out.println("Parsing complete, found " + count + " hammer recipes");
    	} else {
			if (QTConfigs.anyHammers) {
				String tCategory = ConfigCategories.Recipes.gregtechrecipes + ".";
				OP.ingotDouble.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2ingotDouble", new String[][]{{"h", "I", "I"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.ingotTriple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2ingotTriple", new String[][]{{"h", "I", "X"}}, OP.ingotDouble, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.ingotQuadruple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2ingotQuadruple", new String[][]{{"h", "I", "X"}}, OP.ingotTriple, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.ingotQuintuple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2ingotQuintuple", new String[][]{{"h", "I", "X"}}, OP.ingotQuadruple, null, null,null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateTiny.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "chunkGt2plateTiny", new String[][]{{"h", "X"}}, OP.chunkGt, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plate.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "plateCurved2plate", new String[][]{{"h", "X"}}, OP.plateCurved, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.plate.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2plate", new String[][]{{"h", "X"}}, OP.ingotDouble, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateDouble.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2plateDouble", new String[][]{{"h", "X"}}, OP.ingotTriple, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateTriple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2plateTriple", new String[][]{{"h", "X"}}, OP.ingotQuadruple, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateQuadruple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2plateQuadruple", new String[][]{{"h", "X"}}, OP.ingotQuintuple, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateDouble.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "ingots2plateDouble", new String[][]{{"h", "P", "P"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateTriple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "plates2plateTriple", new String[][]{{"h", "P", "X"}}, OP.plateDouble, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateQuadruple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "plates2plateQuadruple", new String[][]{{"h", "P", "X"}}, OP.plateTriple, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateQuintuple.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "plates2plateQuintuple", new String[][]{{"h", "P", "X"}}, OP.plateQuadruple, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.plateCurved.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "plate2plateCurved", new String[][]{{"h", "P", "z"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.ring.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "stick2ring", new String[][]{{"hS", " o"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.spring.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "stick2spring", new String[][]{{"zXh"}}, OP.stickLong, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.springSmall.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "wire2springSmall", new String[][]{{"oXh"}}, OP.wireGt01, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.springSmall.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "stick2springSmall", new String[][]{{"oSh"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.foil.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(2L, tCategory + "plate2foil", new String[][]{{"hPz"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE)));
				OP.casingSmall.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "plate2casingSmall", new String[][]{{"h P"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.stickLong.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "sticks2stickLong", new String[][]{{"ShS"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.rotor.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "rotor", new String[][]{{"YhY", "TXf", "YdY"}}, OP.ring, OP.plateCurved, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE)));
				OP.toolHeadBuzzSaw.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "toolHeadBuzzSaw", new String[][]{{"wPh", "P P", "fPx"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT)));
				OP.toolHeadBuzzSaw.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(1L, tCategory + "toolHeadBuzzSaw", new String[][]{{"wCh", "C C", "fCx"}}, null, null, null, null, null, new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT)));
			}
		}
    }

    private CraftingManagerHammer() {
	}

	public void addHammerRecipes() {
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotDouble,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingot, 			OP.ingot}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotTriple,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingot, 			OP.ingot, 		OP.ingot}	).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotTriple,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotDouble, 	OP.ingot}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuadruple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotTriple, 	OP.ingot}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuadruple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotDouble, 	OP.ingot, 		OP.ingot}	).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuadruple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotDouble, 	OP.ingotDouble}				).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotTriple, 	OP.ingotDouble}				).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotTriple, 	OP.ingot, 		OP.ingot}	).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotQuadruple, OP.ingot}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ingotQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotDouble, 	OP.ingotDouble, OP.ingot}	).setVertical());
    	
    	this.addRecipe(new HammerablePrefixRecipe(OP.plate,  			new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotDouble}								));
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateDouble,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotTriple}								));
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateTriple,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotQuadruple}								));
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuadruple,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.ingotQuintuple}								));
    	
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateDouble,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plate, 			OP.plate}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateTriple,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plate, 			OP.plate, 		OP.plate}	).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateTriple,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateDouble, 	OP.plate}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuadruple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateTriple, 	OP.plate}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuadruple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateDouble, 	OP.plate, OP.plate}			).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuadruple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateDouble, 	OP.plateDouble}				).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateTriple, 	OP.plateDouble}				).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateTriple, 	OP.plate, OP.plate}			).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateQuadruple, OP.plate}					).setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateQuintuple,  	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new Object[]{OP.plateDouble, 	OP.plateDouble, OP.plate}	).setVertical());
    	
    	this.addRecipe(new HammerablePrefixRecipe(OP.plate,		  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plateCurved}));
    	//this.addRecipe(new HammerablePrefixRecipe(OP.foil,		  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plate},		 	"craftingToolBendingCylinder").setHorizontal());
		this.addRecipe(new HammerablePrefixRecipe(OP.plateCurved,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plate},		 	"craftingToolBendingCylinder").setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ring,		  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stick}, 		"craftingToolBendingCylinderSmall").setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.spring, 	 		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stickLong}, 	"craftingToolBendingCylinder").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.springSmall,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.wireGt01}, 		"craftingToolBendingCylinderSmall").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.springSmall,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stick}, 		"craftingToolBendingCylinderSmall").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.casingSmall,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plate}));
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateTiny,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.nugget}));
    	this.addRecipe(new HammerablePrefixRecipe(OP.stickLong, 		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stick,			OP.stick}					));

    	this.addRecipe(new HammerableShapedRecipe(OP.foil,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE),							new String[]{"Pb"}, 'P', OP.plate, 'b', "craftingToolBendingCylinder"));
		this.addRecipe(new HammerableShapedRecipe(OP.foil,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE),							new String[]{"bP"}, 'P', OP.plate, 'b', "craftingToolBendingCylinder"));
    	this.addRecipe(new HammerableShapedRecipe(OP.rotor,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new String[]{"Y Y", "TXf", "YdY"}, 'Y', OP.plateCurved, 'X', OP.ring, 'T', OP.screw, 'f', "craftingToolFile", 'd', "craftingToolScrewdriver"));
    	
    	this.addRecipe(new HammerableShapedRecipe(OP.toolHeadBuzzSaw,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new String[]{"wP ", "P P", "fPx"}, 'P', OP.plate, 'x', "craftingToolWireCutter", 'f', "craftingToolFile", 'w', "craftingToolWrench"));
    	this.addRecipe(new HammerableShapedRecipe(OP.toolHeadBuzzSaw,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new String[]{"wC ", "C C", "fCx"}, 'C', OP.plateGem, 'x', "craftingToolWireCutter", 'f', "craftingToolFile", 'w', "craftingToolWrench"));
    	
    	this.addRecipe(new HammerablePrefixRecipe(OP.gemExquisite, 2,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT), 													new Object[]{OP.gemLegendary}					));
    	this.addRecipe(new HammerablePrefixRecipe(OP.gemFlawless, 2,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT), 													new Object[]{OP.gemExquisite}					));
    	this.addRecipe(new HammerablePrefixRecipe(OP.gem,			 2,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT), 													new Object[]{OP.gemFlawless}					));
    	this.addRecipe(new HammerablePrefixRecipe(OP.gemFlawed,	 	2,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT), 													new Object[]{OP.gem}							));
    	this.addRecipe(new HammerablePrefixRecipe(OP.gemChipped, 	2,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT), 													new Object[]{OP.gemFlawed}						));
    	this.addRecipe(new HammerablePrefixRecipe(OP.dustSmall, 		new ICondition.And(TD.Atomic.ANTIMATTER.NOT), 													new Object[]{OP.gemChipped}						));
    	
    	this.addRecipe(new HammerablePrefixRecipe(OreDictPrefix.get("link"),new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE),						new Object[]{OP.ring}							));
    	this.addRecipe(new HammerablePrefixRecipe(OreDictPrefix.get("chain"),new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 					new Object[]{OP.stick, OP.stick, OP.stick, OP.stick}, "craftingToolBendingCylinderSmall"));
    	
    	if (QTConfigs.enableArmor)
    	{
    		this.addRecipe(new QTArmor(7,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "D D", "P P", 'D', OP.plateDouble, 'P', OP.plate));
    		this.addRecipe(new QTArmor(6,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "DTD", "D D", "D D", 'D', OP.plateDouble, 'T', OP.plateTriple));
    		this.addRecipe(new QTArmor(5,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "D D", "DTD", "PDP", 'D', OP.plateDouble, 'P', OP.plate, 'T', OP.plateTriple));
    		this.addRecipe(new QTArmor(4,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "TDT", "D D", 'D', OP.plateDouble, 'T', OP.plateTriple));
    		
    		this.addRecipe(new QTArmor(3,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "L L", "CsC", 'L', OreDictPrefix.get("link"), 'C', OreDictPrefix.get("chain"), 's', "craftingToolSaw"));
    		this.addRecipe(new QTArmor(2,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "CLC", "LsL", "L L", 'L', OreDictPrefix.get("link"), 'C', OreDictPrefix.get("chain"), 's', "craftingToolSaw"));
    		this.addRecipe(new QTArmor(1,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "LsL", "CLC", "LLL", 'L', OreDictPrefix.get("link"), 'C', OreDictPrefix.get("chain"), 's', "craftingToolSaw"));
    		this.addRecipe(new QTArmor(0,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), "LLL", "LLL", "LsL", 'L', OreDictPrefix.get("link"), 's', "craftingToolSaw"));
    	
    		this.addRecipe(new RepairRecipe(QTI.qwerArmor.getItem(), null));
    	}
    	
    	if (QTConfigs.enableTools)
    	{
    		this.addRecipe(new RepairRecipe(QTI.qwerTool.getItem(), null));
    	}
    	
    	this.addRecipe(new RepairRecipe(CS.ToolsGT.sMetaTool, null));
    	this.addRecipe(new RepairRecipe(Items.iron_axe, new OreDictMaterialStack(MT.Fe, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.iron_hoe, new OreDictMaterialStack(MT.Fe, CS.U * 2)));
    	this.addRecipe(new RepairRecipe(Items.iron_horse_armor, new OreDictMaterialStack(MT.Fe, CS.U * 20)));
    	this.addRecipe(new RepairRecipe(Items.iron_pickaxe, new OreDictMaterialStack(MT.Fe, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.iron_shovel, new OreDictMaterialStack(MT.Fe, CS.U)));
    	this.addRecipe(new RepairRecipe(Items.iron_sword, new OreDictMaterialStack(MT.Fe, CS.U * 2)));
    	this.addRecipe(new RepairRecipe(Items.iron_boots, new OreDictMaterialStack(MT.Fe, CS.U * 4)));
    	this.addRecipe(new RepairRecipe(Items.iron_chestplate, new OreDictMaterialStack(MT.Fe, CS.U * 8)));
    	this.addRecipe(new RepairRecipe(Items.iron_helmet, new OreDictMaterialStack(MT.Fe, CS.U * 5)));
    	this.addRecipe(new RepairRecipe(Items.iron_leggings, new OreDictMaterialStack(MT.Fe, CS.U * 7)));
    	this.addRecipe(new RepairRecipe(Items.golden_axe, new OreDictMaterialStack(MT.Au, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.golden_hoe, new OreDictMaterialStack(MT.Au, CS.U * 2)));
    	this.addRecipe(new RepairRecipe(Items.golden_pickaxe, new OreDictMaterialStack(MT.Au, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.golden_shovel, new OreDictMaterialStack(MT.Au, CS.U)));
    	this.addRecipe(new RepairRecipe(Items.golden_sword, new OreDictMaterialStack(MT.Au, CS.U * 2)));
    	this.addRecipe(new RepairRecipe(Items.golden_boots, new OreDictMaterialStack(MT.Au, CS.U * 4)));
    	this.addRecipe(new RepairRecipe(Items.golden_chestplate, new OreDictMaterialStack(MT.Au, CS.U * 8)));
    	this.addRecipe(new RepairRecipe(Items.golden_helmet, new OreDictMaterialStack(MT.Au, CS.U * 5)));
    	this.addRecipe(new RepairRecipe(Items.golden_leggings, new OreDictMaterialStack(MT.Au, CS.U * 7)));
    	this.addRecipe(new RepairRecipe(Items.golden_horse_armor, new OreDictMaterialStack(MT.Au, CS.U * 20)));
    	this.addRecipe(new RepairRecipe(Items.flint_and_steel, new OreDictMaterialStack(MT.Steel, CS.U / 9)));
    	this.addRecipe(new RepairRecipe(Items.wooden_axe, new OreDictMaterialStack(MT.Wood, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.wooden_hoe, new OreDictMaterialStack(MT.Wood, CS.U * 2)));
    	this.addRecipe(new RepairRecipe(Items.wooden_pickaxe, new OreDictMaterialStack(MT.Wood, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.wooden_shovel, new OreDictMaterialStack(MT.Wood, CS.U)));
    	this.addRecipe(new RepairRecipe(Items.wooden_sword, new OreDictMaterialStack(MT.Wood, CS.U * 2)));
    	this.addRecipe(new RepairRecipe(Items.chainmail_boots, new OreDictMaterialStack(MT.Fe, CS.U * 3)));
    	this.addRecipe(new RepairRecipe(Items.chainmail_chestplate, new OreDictMaterialStack(MT.Fe, CS.U * 7)));
    	this.addRecipe(new RepairRecipe(Items.chainmail_helmet, new OreDictMaterialStack(MT.Fe, CS.U * 4)));
    	this.addRecipe(new RepairRecipe(Items.chainmail_leggings, new OreDictMaterialStack(MT.Fe, CS.U * 6)));
    	this.addRecipe(new RepairRecipe(Items.shears, new OreDictMaterialStack(MT.Fe, CS.U * 2)));
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
		searchAndReplace();
	}
}
