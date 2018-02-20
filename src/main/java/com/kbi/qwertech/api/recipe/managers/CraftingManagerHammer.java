package com.kbi.qwertech.api.recipe.managers;

import gregapi.code.ICondition;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.ST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.recipe.HammerablePrefixRecipe;
import com.kbi.qwertech.api.recipe.HammerableShapedRecipe;
import com.kbi.qwertech.api.recipe.QTArmor;
import com.kbi.qwertech.api.recipe.RepairRecipe;

public class CraftingManagerHammer implements Runnable {

	private static final CraftingManagerHammer instance = new CraftingManagerHammer();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();
    
    public static HashMap<ItemStack, String> replacems = new HashMap();

    /**
     * Returns the static instance of this class
     */
    public static CraftingManagerHammer getInstance()
    {
        /** The static instance of this class */
        return instance;
    }
    
    public static void searchAndReplace()
    {
    	if (QTConfigs.allHammers)
    	{
	    	List oldRecipes = CraftingManager.getInstance().getRecipeList();
	    	System.out.println("Parsing through " + oldRecipes.size() + " recipes to remove hammers...");
	    	int count = 0;
	    	for (int q = 0; q < oldRecipes.size(); q++)
	    	{
	    		IRecipe recipe = (IRecipe)oldRecipes.get(q);
	    		boolean removeIt = false;
	    		if (recipe instanceof ShapelessOreRecipe)
	    		{
	    			List items = ((ShapelessOreRecipe)recipe).getInput();
	    			for (int w = 0; w < items.size() && removeIt == false; w++)
	        		{
	        			Object slot = items.get(w);
	        			if (slot instanceof ItemStack)
	        			{
	        				ItemStack theSlot = (ItemStack)slot;
	        				if (OreDictManager.isItemStackInstanceOf(theSlot, "craftingToolHardHammer"))
	        				{
	        					removeIt = true;
	        					items.remove(w);
	        				}
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
	        			} else if (slot instanceof List && ((List)slot).size() > 0)
	        			{
	        				ItemStack spot = (ItemStack)((List)slot).get(0);
	        				if (spot != null && OreDictManager.isItemStackInstanceOf(spot, "craftingToolHardHammer"))
	        				{
	        					removeIt = true;
	        					items.remove(w);
	        				}
	        			}
	        		}
	    		} else if (recipe instanceof ShapedOreRecipe)
	    		{
	    			Object[] items = ((ShapedOreRecipe)recipe).getInput();
	    			for (int w = 0; w < items.length && removeIt == false; w++)
	        		{
	        			Object slot = items[w];
	        			if (slot instanceof ItemStack)
	        			{
	        				ItemStack theSlot = (ItemStack)slot;
	        				if (OreDictManager.isItemStackInstanceOf(theSlot, "craftingToolHardHammer"))
	        				{
	        					removeIt = true;
	        					items[w] = null;
	        				}
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
	        			} else if (slot instanceof List && ((List)slot).size() > 0)
	        			{
	        				ItemStack spot = (ItemStack)((List)slot).get(0);
	        				if (spot != null && OreDictManager.isItemStackInstanceOf(spot, "craftingToolHardHammer"))
	        				{
	        					removeIt = true;
	        					items[w] = null;
	        				}
	        			}
	        		}
	    		}
	    		if (removeIt)
	    		{
	    			instance.addRecipe(recipe);
	    			oldRecipes.remove(recipe);
	    			count = count + 1;
	    		}
	    	}
	    	System.out.println("Parsing complete, found " + count + " hammer recipes");
    	}
    }

    private CraftingManagerHammer()
    {
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
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateCurved,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plate},		 	"craftingToolBendingCylinder").setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.foil,		  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plate},		 	"craftingToolBendingCylinder").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.ring,		  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stick}, 		"craftingToolBendingCylinderSmall").setVertical());
    	this.addRecipe(new HammerablePrefixRecipe(OP.spring, 	 		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stickLong}, 	"craftingToolBendingCylinder").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.springSmall,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.wireGt01}, 		"craftingToolBendingCylinderSmall").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.springSmall,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stick}, 		"craftingToolBendingCylinderSmall").setHorizontal());
    	this.addRecipe(new HammerablePrefixRecipe(OP.casingSmall,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.plate}));
    	this.addRecipe(new HammerablePrefixRecipe(OP.plateTiny,  		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.nugget}));
    	this.addRecipe(new HammerablePrefixRecipe(OP.stickLong, 		new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), 							new Object[]{OP.stick,			OP.stick}					));

    	this.addRecipe(new HammerableShapedRecipe(OP.rotor,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new String[]{"Y Y", "TXf", "YdY"}, Character.valueOf('Y'), OP.plateCurved, Character.valueOf('X'), OP.ring, Character.valueOf('T'), OP.screw, Character.valueOf('f'), "craftingToolFile", Character.valueOf('d'), "craftingToolScrewdriver"));
    	
    	this.addRecipe(new HammerableShapedRecipe(OP.toolHeadBuzzSaw,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new String[]{"wP ", "P P", "fPx"}, Character.valueOf('P'), OP.plate, Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('f'), "craftingToolFile", Character.valueOf('w'), "craftingToolWrench"));
    	this.addRecipe(new HammerableShapedRecipe(OP.toolHeadBuzzSaw,	new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Compounds.COATED.NOT, TD.Processing.SMITHABLE), new String[]{"wC ", "C C", "fCx"}, Character.valueOf('C'), OP.plateGem, Character.valueOf('x'), "craftingToolWireCutter", Character.valueOf('f'), "craftingToolFile", Character.valueOf('w'), "craftingToolWrench"));
    	
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
    		this.addRecipe(new QTArmor(7,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"D D", "P P", Character.valueOf('D'), OP.plateDouble, Character.valueOf('P'), OP.plate}));
    		this.addRecipe(new QTArmor(6,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"DTD", "D D", "D D", Character.valueOf('D'), OP.plateDouble, Character.valueOf('T'), OP.plateTriple}));
    		this.addRecipe(new QTArmor(5,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"D D", "DTD", "PDP", Character.valueOf('D'), OP.plateDouble, Character.valueOf('P'), OP.plate, Character.valueOf('T'), OP.plateTriple}));
    		this.addRecipe(new QTArmor(4,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"TDT", "D D", Character.valueOf('D'), OP.plateDouble, Character.valueOf('T'), OP.plateTriple}));
    		
    		this.addRecipe(new QTArmor(3,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"L L", "CsC", Character.valueOf('L'), OreDictPrefix.get("link"), Character.valueOf('C'), OreDictPrefix.get("chain"), Character.valueOf('s'), "craftingToolSaw"}));
    		this.addRecipe(new QTArmor(2,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"CLC", "LsL", "L L", Character.valueOf('L'), OreDictPrefix.get("link"), Character.valueOf('C'), OreDictPrefix.get("chain"), Character.valueOf('s'), "craftingToolSaw"}));
    		this.addRecipe(new QTArmor(1,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"LsL", "CLC", "LLL", Character.valueOf('L'), OreDictPrefix.get("link"), Character.valueOf('C'), OreDictPrefix.get("chain"), Character.valueOf('s'), "craftingToolSaw"}));
    		this.addRecipe(new QTArmor(0,				new ICondition.And(TD.Atomic.ANTIMATTER.NOT, TD.Processing.SMITHABLE), new Object[]{"LLL", "LLL", "LsL", Character.valueOf('L'), OreDictPrefix.get("link"), Character.valueOf('s'), "craftingToolSaw"}));
    	
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
