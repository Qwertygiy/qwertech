package com.kbi.qwertech.loaders.mod;

import gregapi.code.ICondition;
import gregapi.data.ANY;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.OM;
import gregapi.util.ST;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.recipe.AnyQTTool;

import cpw.mods.fml.common.event.FMLInterModComms;

public class NEI_Tool_Handler extends ShapelessRecipeHandler {

	public NEI_Tool_Handler() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), getOverlayIdentifier(), new Object[0]));
	    if (!NEI_QT_Config.sIsAdded)
	    {
	      System.out.println("Creating QT NEI handler");
	      FMLInterModComms.sendRuntimeMessage(QwerTech.instance, "NEIPlugins", "register-crafting-handler", QwerTech.MODID + "@" + getRecipeName() + "@" + getOverlayIdentifier());
	      GuiCraftingRecipe.craftinghandlers.add(this);
	      GuiUsageRecipe.usagehandlers.add(this);
	    }
	}
	
	@Override
	public TemplateRecipeHandler newInstance() {
    	return new NEI_Tool_Handler();
    }

	@Override
	public String getRecipeName() {
		// TODO Auto-generated method stub
		return "Tool Crafting";
	}

	@Override
	public String getGuiTexture()
	{
		return "textures/gui/container/crafting_table.png";
	}
	 
	@Override
	public String getOverlayIdentifier()
	{
		return "crafting";
	}
	
	public class ToolCraftingCachedRecipe extends CachedShapelessRecipe
	{
		ICondition matCondition = ICondition.TRUE;
		
		public ToolCraftingCachedRecipe(ItemStack output) {
			super(output);
		}
 
		public ToolCraftingCachedRecipe(Object[] input, ItemStack output) {
			super(input, output);
		}
		
		public ToolCraftingCachedRecipe(Object[] input, ItemStack output, ICondition condition)
		{
			super(input, output);
			matCondition = condition;
		}
		
		public boolean isConditionTrue(ItemStack toCheck)
		{
			OreDictItemData tDt = OM.data(toCheck);
			if (tDt == null) tDt = OM.anydata(toCheck); //not sure this would do anything (shrug)
			if (tDt == null) return true;
			if (matCondition.isTrue(tDt.mMaterial.mMaterial) && matCondition.isTrue(tDt.mPrefix))
			{
				return true;
			}
			return false;
		}
		
		int lasCheck = -1;
		
		@Override
		public List<PositionedStack> getCycledIngredients(int cycle, List<PositionedStack> ingredients)
		{
			for (int itemIndex = 0; itemIndex < ingredients.size(); itemIndex++) {
				PositionedStack stack = ingredients.get(itemIndex);
				int cycleSpot = cycle % stack.items.length;
				if (cycleSpot == lasCheck)
				{
					//we have recieved an infinite loop, this recipe should not have been displayed, error elsewhere
					return ingredients;
				}
				if (lasCheck == -1) lasCheck = cycleSpot;
				if (ST.equal(ST.make(Items.apple, 1, 20), stack.items[cycleSpot]))
				{
					cycle = cycle + 1;
					return getCycledIngredients(cycle, ingredients);
				} else {
					setRenderPermutation(ingredients.get(itemIndex), cycle);
				}
			}
			lasCheck = -1;
			return ingredients;
		}
		
		public void setRenderPermutation(PositionedStack stack, long cycle) {
			stack.setPermutationToRender((int)cycle % stack.items.length);
		}
		
		@Override
		public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) 
		{
			return contains(ingredients, ingredient, true);
		}
        
		public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient, boolean matchNBT)
		{
			for (PositionedStack stack : ingredients)
            {
                for (ItemStack check : stack.items)
                {
                	if (ST.equal(check, ingredient, matchNBT))
                	{
                		return true;
                	}
                }
            }
            return false;
        }
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if ((outputId.equals("crafting")) && (getClass() == NEI_Tool_Handler.class)) {
			List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
			for (IRecipe irecipe : allrecipes) {
				ToolCraftingCachedRecipe recipe = null;
				if ((irecipe instanceof AnyQTTool)) {
					recipe = shapelessQTRecipe((AnyQTTool)irecipe);
				}
				if (recipe != null)
				{
					arecipes.add(recipe); 
				}
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
 
	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			if (codechicken.nei.NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result)) {
				ToolCraftingCachedRecipe recipe = null;
				if ((irecipe instanceof AnyQTTool)) {
					recipe = shapelessQTRecipe((AnyQTTool)irecipe);
				}
				if (recipe != null)
				{ 
					arecipes.add(recipe);
				}
			}
		}
	}
  
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			ToolCraftingCachedRecipe recipe = null;
			if ((irecipe instanceof AnyQTTool)) {
				OreDictItemData mData = OM.anydata(ingredient);
				if (mData != null)
				{
					recipe = shapelessQTRecipe((AnyQTTool)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
				} else {
					recipe = shapelessQTRecipe((AnyQTTool)irecipe);
				}
			}
			if (recipe != null)
			{
				if (recipe.contains(recipe.ingredients, ingredient) && recipe.isConditionTrue(ingredient)) {
					recipe.setIngredientPermutation(recipe.ingredients, ingredient);
					arecipes.add(recipe);
				} }
		}
	}
	
	public ToolCraftingCachedRecipe shapelessQTRecipe(AnyQTTool recipe)
	{
		return shapelessQTRecipe(recipe, null, null);
	}
	
	public ToolCraftingCachedRecipe shapelessQTRecipe(AnyQTTool recipe, OreDictPrefix prefix, OreDictMaterial mat)
	{
		ArrayList<Object> items = new ArrayList();
		items.addAll(Arrays.asList(recipe.recipePieces));    
		for (Object item : items) {
			if (((item instanceof List)) && (((List)item).isEmpty()))
				return null;
		}
		try {
			OreDictMaterial[] matlist = OreDictMaterial.MATERIAL_ARRAY;
			ArrayList<OreDictMaterial> primary = new ArrayList();
			ArrayList<OreDictMaterial> secondary = new ArrayList();
			for (int w = 0; w < matlist.length; w++)
			{
				if (matlist[w] != null && recipe.mCondition.isTrue(matlist[w]))
				{
					primary.add(matlist[w]);
					if (matlist[w].mHandleMaterial != null)
					{
						secondary.add(matlist[w].mHandleMaterial);
					} else {
						secondary.add(MT.Wood);
					}
				}
			}
			List<Integer> removems = new ArrayList();
			List<Integer> reversems = new ArrayList();
			for (int q = 0; q < items.size(); q++)
			{
				Object check = items.get(q);
				if (check instanceof OreDictItemData)
				{
					OreDictItemData data = (OreDictItemData)check;
					List<ItemStack> adderble = new ArrayList();
					if (data.mMaterial.mMaterial == MT.NULL)
					{
						if (data.mPrefix == prefix)
						{
							primary = new ArrayList();
							primary.add(mat);
							adderble.add(prefix.mat(mat, 1));
						} else {
							for (int e = 0; e < primary.size(); e++)
							{
								OreDictMaterial thisMat = primary.get(e);
								if (data.mPrefix.isTrue(thisMat))
								{
									adderble.add(data.mPrefix.mat(thisMat, 1));
								} else {
									adderble.add(ST.make(Items.apple, 1, 20));
									if (!removems.contains(e)) removems.add(e);
								}
							}
						}
					} else if (data.mMaterial.mMaterial == MT.Empty)
					{
						if (data.mPrefix == prefix)
						{
							primary = new ArrayList();
							primary.add(mat);
							adderble.add(prefix.mat(mat, 1));
						} else {
							reversems.add(q);
							for (int e = 0; e < primary.size(); e++)
							{
								OreDictMaterial thisMat = primary.get(e);
								if (data.mPrefix.isTrue(thisMat))
								{
									adderble.add(data.mPrefix.mat(thisMat, 1));
								} else {
									adderble.add(ST.make(Items.apple, 1, 20));
									if (!removems.contains(e)) removems.add(e);
								}
							}
						}
					} else if (data.mMaterial.mMaterial == MT.Butter)
					{
						if (data.mPrefix == prefix)
						{
							secondary = new ArrayList();
							secondary.add(mat);
							adderble.add(prefix.mat(mat, 1));
						} else {
							for (int e = 0; e < secondary.size(); e++)
							{
								OreDictMaterial thisMat = secondary.get(e);
								if (data.mPrefix.isTrue(thisMat))
								{
									if (thisMat == MT.Plastic || thisMat == ANY.WoodPlastic)
									{
										if (CS.RANDOM.nextInt(3) != 0)
										{
											adderble.add(data.mPrefix.mat(MT.Wood, 1));
										} else {
											adderble.add(data.mPrefix.mat(MT.Plastic, 1));
										}
									} else {
										adderble.add(data.mPrefix.mat(thisMat, 1));
									}
								} else {
									adderble.add(ST.make(Items.apple, 1, 20));
									if (!removems.contains(e)) removems.add(e);
								}
							}	
						}
					}
					items.set(q, adderble);
				} else if (check instanceof String)
				{
					items.set(q, OreDictionary.getOres((String)check));
				} else if (check instanceof ItemStack)
				{
					ItemStack stacky = (ItemStack)check;
					if (stacky.getItem() instanceof MultiItemTool)
					{
						List<ItemStack> adderble = new ArrayList();
						MultiItemTool tool = (MultiItemTool)stacky.getItem();
						OreDictMaterial prime = MultiItemTool.getPrimaryMaterial(stacky);
						OreDictMaterial nex = MultiItemTool.getSecondaryMaterial(stacky);
						int dam = stacky.getItemDamage();
						if (prime == MT.NULL)
						{
							for (int g = 0; g < primary.size(); g++)
							{
								if (nex == MT.Butter)
								{
									adderble.add(tool.getToolWithStats(dam, primary.get(g), secondary.get(g)));
								} else if (nex == MT.NULL)
								{
									adderble.add(tool.getToolWithStats(dam, primary.get(g), primary.get(g)));
								} else if (nex == MT.Empty)
								{
									adderble.add(tool.getToolWithStats(dam, primary.get(g), MT.Empty));
								} else {
									adderble.add(tool.getToolWithStats(dam, primary.get(g), nex));
								}
							}
						} else if (prime == MT.Butter)
						{
							for (int g = 0; g < primary.size(); g++)
							{
								if (nex == MT.Butter)
								{
									adderble.add(tool.getToolWithStats(dam, secondary.get(g), secondary.get(g)));
								} else if (nex == MT.NULL)
								{
									adderble.add(tool.getToolWithStats(dam, secondary.get(g), primary.get(g)));
								} else if (nex == MT.Empty)
								{
									adderble.add(tool.getToolWithStats(dam, secondary.get(g), MT.Empty));
								} else {
									adderble.add(tool.getToolWithStats(dam, secondary.get(g), nex));
								}
							}
						} else if (prime == MT.Empty)
						{
							for (int g = 0; g < primary.size(); g++)
							{
								if (nex == MT.Butter)
								{
									adderble.add(tool.getToolWithStats(dam, primary.get(removems.contains(primary.size() - g - 1) ? g : primary.size() - g - 1), secondary.get(removems.contains(primary.size() - g - 1) ? g : primary.size() - g - 1)));
								} else if (nex == MT.NULL)
								{
									adderble.add(tool.getToolWithStats(dam, primary.get(removems.contains(primary.size() - g - 1) ? g : primary.size() - g - 1), primary.get(g)));
								} else if (nex == MT.Empty)
								{
									adderble.add(tool.getToolWithStats(dam, primary.get(removems.contains(primary.size() - g - 1) ? g : primary.size() - g - 1), MT.Empty));
								} else {
									adderble.add(tool.getToolWithStats(dam, primary.get(removems.contains(primary.size() - g - 1) ? g : primary.size() - g - 1), nex));
								}
							}
						} else {
							for (int g = 0; g < primary.size(); g++)
							{
								if (nex == MT.Butter)
								{
									adderble.add(tool.getToolWithStats(dam, prime, secondary.get(g)));
								} else if (nex == MT.NULL)
								{
									adderble.add(tool.getToolWithStats(dam, prime, primary.get(g)));
								} else if (nex == MT.Empty)
								{
									adderble.add(tool.getToolWithStats(dam, prime, MT.Empty));
								} else {
									adderble.add(tool.getToolWithStats(dam, prime, nex));
								}
							}
						}
						items.set(q, adderble);
					}
				}
			}
			Collections.sort(removems);
			
			for (int t = 0; t < items.size(); t++)
			{
				int removed = 0;
				for (int r = 0; r < removems.size(); r++)
				{
					int removable = removems.get(r);
					Object recipeBit = items.get(t);
					if (recipeBit instanceof List && ((List)recipeBit).size() > 1)
					{
						((List)recipeBit).remove(removable - removed);
					}
					removed = removed + 1;
				}
				if (reversems.contains(t))
				{
					Collections.reverse((List)items.get(t));
				}
				if (items.get(t) == null || (items.get(t) instanceof List && ((List)items.get(t)).size() <= 1))
				{
					//System.out.println("We hve filed to lod" + t);
					return null;
				}
			}
			//System.out.println("Removed " + removed + " unsuitable materials from NEI recipe");
			return new ToolCraftingCachedRecipe(items.toArray(), recipe.getRecipeOutput(), recipe.mCondition);
		} catch (Exception e) {
			codechicken.nei.NEIClientConfig.logger.error("Error loading recipe: ", e); }
		return null;
	}

}
