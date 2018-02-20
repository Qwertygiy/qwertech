package com.kbi.qwertech.loaders.mod;

import gregapi.data.MT;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.util.OM;
import gregapi.util.ST;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.WOOD;
import com.kbi.qwertech.api.recipe.WoodSpecificCrafting;
import cpw.mods.fml.common.event.FMLInterModComms;

public class NEI_Wood_Handler extends ShapedRecipeHandler {

	ShapelessRecipeHandler SRH = new ShapelessRecipeHandler();
	
	public NEI_Wood_Handler() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), getOverlayIdentifier(), new Object[0]));
	    if (!NEI_QT_Config.sIsAdded)
	    {
	      System.out.println("Creating QT NEI wood handler");
	      FMLInterModComms.sendRuntimeMessage(QwerTech.instance, "NEIPlugins", "register-crafting-handler", QwerTech.MODID + "@" + getRecipeName() + "@" + getOverlayIdentifier());
	      GuiCraftingRecipe.craftinghandlers.add(this);
	      GuiUsageRecipe.usagehandlers.add(this);
	    }
	}
	
	@Override
	public TemplateRecipeHandler newInstance() {
    	return new NEI_Wood_Handler();
    }

	@Override
	public String getRecipeName() {
		// TODO Auto-generated method stub
		return "Wood-Specific Crafting";
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
	
	public class WoodCachedRecipe extends CachedShapedRecipe
	{

        public WoodCachedRecipe(int width, int height, Object[] items, ItemStack out) {
            super(width, height, items, out);
        }

        /*public HammerableCachedRecipe(ShapedRecipes recipe) {
            super(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.getRecipeOutput());
        }*/

        /**
         * @param width
         * @param height
         * @param items  an ItemStack[] or ItemStack[][]
         */
        @Override
        public void setIngredients(int width, int height, Object[] items) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                	Object returnIt = items[y * width + x];
                    if (returnIt == null || (returnIt instanceof ItemStack[] && ((ItemStack[])returnIt).length <= 0))
                    {
                        continue;
                    }

                    PositionedStack stack = new PositionedStack(returnIt, 25 + x * 18, 6 + y * 18, false);
                    stack.setMaxSize(1);
                    ingredients.add(stack);
                }
            }
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, ingredients);
        }

        @Override
        public PositionedStack getResult() {
            return result;
        }

        @Override
        public void computeVisuals() {
            for (PositionedStack p : ingredients)
                p.generatePermutations();
        }
		
		@Override
		public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) 
		{
			return contains(ingredients, ingredient, false);
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
                	} else {
                		OreDictItemData data1 = OM.anydata(check);
                		OreDictItemData data2 = OM.anydata(ingredient);
                		if (data1 != null && data2 != null && data1.mPrefix == data2.mPrefix && (data1.mMaterial.mMaterial == data2.mMaterial.mMaterial || data1.mMaterial.mMaterial == MT.NULL || data2.mMaterial.mMaterial == MT.NULL))
                		{
                			return true;
                		}
                	}
                }
            }
            return false;
        }
	}
	
	public boolean matches(ItemStack one, ItemStack two)
	{
		if (ST.equal(one, two, false))
    	{
    		return true;
    	} else {
    		OreDictItemData data1 = OM.anydata(one);
    		OreDictItemData data2 = OM.anydata(two);
    		if (data1 != null && data2 != null && data1.mPrefix == data2.mPrefix && (data1.mMaterial.mMaterial == data2.mMaterial.mMaterial || data1.mMaterial.mMaterial == MT.NULL || data2.mMaterial.mMaterial == MT.NULL || (data1.mMaterial.mMaterial == MT.Steel || data2.mMaterial.mMaterial == MT.Steel)))
    		{
    			return true;
    		}
    	}
		return false;
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if ((outputId.equals("crafting")) && (getClass() == NEI_Wood_Handler.class)) {
			List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
			for (IRecipe irecipe : allrecipes) {
				CachedRecipe recipe = null;
				if (irecipe instanceof WoodSpecificCrafting) {
					recipe = shapedWoodRecipe((WoodSpecificCrafting)irecipe);
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
			if (irecipe instanceof WoodSpecificCrafting)
			{
				if (result.getItem() == irecipe.getRecipeOutput().getItem()) {
					CachedRecipe recipe = null;
					OreDictItemData mData = OM.anydata(result);
					recipe = shapedWoodRecipe((WoodSpecificCrafting)irecipe, result, null);
					if (recipe != null)
					{
						arecipes.add(recipe);
					}
				}
			}
		}
	}
  
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			CachedRecipe recipe = null;
			if ((irecipe instanceof WoodSpecificCrafting)) {
				recipe = shapedWoodRecipe((WoodSpecificCrafting)irecipe, null, ingredient);
			} 
			if (recipe != null)
			{
				if (recipe.contains(recipe.getIngredients(), ingredient)) {
					recipe.setIngredientPermutation(recipe.getIngredients(), ingredient);
					arecipes.add(recipe);
				} }
		}
	}
	
	public WoodCachedRecipe shapedWoodRecipe(WoodSpecificCrafting recipe)
	{
		return shapedWoodRecipe(recipe, null, null);
	}
	
	public WoodCachedRecipe shapedWoodRecipe(WoodSpecificCrafting recipe, ItemStack output, ItemStack input) {
        try {
            Object[] items = recipe.getInput().clone();
            List<ItemStack> inputs = new ArrayList();
            if (input == null)
            {
            	if (output != null)
            	{
            		inputs = (OreDictionary.getOres("plank" + WOOD.woodList[output.getItemDamage()].mNameInternal));
            	} else {
            		inputs = OreDictionary.getOres("plankWood");
            	}
            } else if (OreDictManager.isItemStackInstanceOf(input, "plankWood")){
            	inputs.add(input);
            } else {
            	return null;
            }
            for (int q = 0; q < items.length; q++)
            {
            	Object item = items[q];
                if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                {
                    return null;
                } else if (item instanceof List)
                {
                	items[q] = inputs;
                }
            }
            if (output == null)
            {
            	output = recipe.getRecipeOutput();
            	if (input != null)
            	{
            		int[] ores = OreDictionary.getOreIDs(input);
            		for (int ore : ores)
            		{
            			String result = OreDictionary.getOreName(ore);
            			if (result.startsWith("plankWood") && result != "plankWood")
            			{      
            				if (WOOD.woodMap.get(result.substring(5)) != null)
            				{
            					output.setItemDamage(WOOD.woodMap.get(result.substring(5)));
            				}
            			}
            		}
            	}
            }
            output.stackSize = recipe.getRecipeOutput().stackSize;
            return new WoodCachedRecipe(2, 2, items, output);
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }

}
