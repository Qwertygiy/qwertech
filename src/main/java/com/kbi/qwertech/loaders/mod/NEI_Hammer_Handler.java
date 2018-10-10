package com.kbi.qwertech.loaders.mod;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.*;
import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTMT;
import com.kbi.qwertech.api.recipe.HammerablePrefixRecipe;
import com.kbi.qwertech.api.recipe.HammerableShapedRecipe;
import com.kbi.qwertech.api.recipe.QTArmor;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.data.MT;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NEI_Hammer_Handler extends ShapedRecipeHandler {

	ShapelessRecipeHandler SRH = new ShapelessRecipeHandler();
	
	public NEI_Hammer_Handler() {
		//this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 13, 36, 18), getOverlayIdentifier(), new Object[0]));
	    if (!NEI_QT_Config.sIsAdded)
	    {
	      System.out.println("Creating QT NEI hammer handler");
	      FMLInterModComms.sendRuntimeMessage(QwerTech.instance, "NEIPlugins", "register-crafting-handler", QwerTech.MODID + "@" + getRecipeName() + "@" + getOverlayIdentifier());
	      GuiCraftingRecipe.craftinghandlers.add(this);
	      GuiUsageRecipe.usagehandlers.add(this);
	    }
	}
	
	@Override
	public TemplateRecipeHandler newInstance() {
    	return new NEI_Hammer_Handler();
    }

	@Override
	public String getRecipeName() {
		// TODO Auto-generated method stub
		return "Hammer Crafting";
	}

	@Override
	public String getGuiTexture()
	{
		return "qwertech:textures/gui/nei/hammer_table.png";
	}
	 
	@Override
	public String getOverlayIdentifier()
	{
		return "crafting";
	}
	
	public class HammerableCachedRecipe extends CachedShapedRecipe
	{

        public HammerableCachedRecipe(int width, int height, Object[] items, ItemStack out) {
            super(width, height, items, out);
        }

        /*public HammerableCachedRecipe(ShapedRecipes recipe) {
            super(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.getRecipeOutput());
        }*/

        /**
         * @param width width of recipe
         * @param height height of recipe
         * @param items  an ItemStack[] or ItemStack[][]
         */
        @Override
        public void setIngredients(int width, int height, Object[] items) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                	Object returnIt = items[y * width + x];
                    if (returnIt == null || (returnIt instanceof List && ((List)returnIt).size() <= 0) || (returnIt instanceof ItemStack[] && ((ItemStack[])returnIt).length <= 0))
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
			if (OM.is("craftingToolHardHammer", ingredient))
			{
				return true;
			}
			for (PositionedStack stack : ingredients)
            {
                for (ItemStack check : stack.items)
                {
                	if (matches(check, ingredient))
					{
						return true;
					}
                }
            }
            return false;
        }
	}
	
	public boolean matches(ItemStack one, ItemStack two)
	{
		if ((one == null && two != null) || (one != null && two == null)) return false;
		if (ST.equal(one, two, false))
    	{
    		return true;
    	} else if (ST.valid(one) && ST.valid(two)) {
    		OreDictItemData data1 = OM.data(one);
    		OreDictItemData data2 = OM.data(two);
    		if (data1 != null && data2 != null && data1.mPrefix == data2.mPrefix && data1.mMaterial != null && data2.mMaterial != null)
			{
				OreDictMaterial mat1 = data1.mMaterial.mMaterial;
				OreDictMaterial mat2 = data2.mMaterial.mMaterial;
    			if (mat1 == mat2 || mat1 == MT.NULL || mat2 == MT.NULL || mat1.mReRegistrations.contains(mat2) || mat2.mReRegistrations.contains(mat1) || mat1 == QTMT.Undefined || mat2 == QTMT.Undefined)
				{
					return true;
				}
			}
            return ST.equal(one, two, true);
    	}
		return false;
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if ((outputId.equals("crafting")) && (getClass() == NEI_Hammer_Handler.class)) {
			List<IRecipe> allrecipes = CraftingManagerHammer.getInstance().getRecipeList();
			for (IRecipe irecipe : allrecipes) {
				CachedRecipe recipe = null;
				if (irecipe instanceof HammerablePrefixRecipe) {
					recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe);
				} else if (irecipe instanceof HammerableShapedRecipe) {
					recipe = hammerShapedRecipe((HammerableShapedRecipe)irecipe);
				} else if (irecipe instanceof ShapedOreRecipe)
				{
					recipe = forgeShapedRecipe((ShapedOreRecipe)irecipe);
				} else if (irecipe instanceof ShapelessOreRecipe)
				{
					recipe = forgeShapelessRecipe((ShapelessOreRecipe)irecipe);
				} else if (irecipe instanceof QTArmor)
				{
					recipe = armorShapedRecipe((QTArmor)irecipe);
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
		boolean doOutput = OM.is("foilCopper", result);
		if (doOutput) System.out.println("Lookingforit");
		List<IRecipe> allrecipes = CraftingManagerHammer.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			if (matches(irecipe.getRecipeOutput(), result)) {
				if (doOutput) System.out.println("Foundit");
				CachedRecipe recipe = null;
				if ((irecipe instanceof HammerablePrefixRecipe)) {
					OreDictItemData mData = OM.anydata(result);
					if (mData != null)
					{
						recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
					} else {
						recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe);
					}
				} else if ((irecipe instanceof HammerableShapedRecipe)) {
					OreDictItemData mData = OM.anydata(result);
					if (mData != null)
					{
						recipe = hammerShapedRecipe((HammerableShapedRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
					} else {
						recipe = hammerShapedRecipe((HammerableShapedRecipe)irecipe);
					}
				} else if ((irecipe) instanceof QTArmor)
				{
					if (result.getItem() instanceof MultiItemArmor)
					{
						recipe = armorShapedRecipe((QTArmor)irecipe, MultiItemArmor.getPrimaryMaterial(result));
					}
				} else if (irecipe instanceof ShapedOreRecipe)
				{
					recipe = forgeShapedRecipe((ShapedOreRecipe)irecipe);
				} else if (irecipe instanceof ShapelessOreRecipe)
				{
					recipe = forgeShapelessRecipe((ShapelessOreRecipe)irecipe);
				}
				if (recipe != null)
				{ 
					arecipes.add(recipe);
				}
			} else if (irecipe.getRecipeOutput() == null)
			{
				if (irecipe instanceof HammerablePrefixRecipe) {
					HammerablePrefixRecipe HPR = (HammerablePrefixRecipe)irecipe;
					if (doOutput) System.out.println("Output broken for " + HPR.outputPrefix + " and " + HPR.primaryMaterial);
					if (matches(HPR.outputPrefix.mat(HPR.primaryMaterial, 1), result)) {
						CachedRecipe recipe = this.shapelessHammerRecipe(HPR, HPR.outputPrefix, HPR.primaryMaterial);
						if (recipe != null) {
							arecipes.add(recipe);
						}
					}
				} else if (irecipe instanceof HammerableShapedRecipe) {
					HammerableShapedRecipe HPR = (HammerableShapedRecipe)irecipe;
					if (doOutput) System.out.println("Output broken for " + HPR.outputPrefix + " and " + HPR.primaryMaterial);
					if (matches(HPR.outputPrefix.mat(HPR.primaryMaterial, 1), result)) {
						CachedRecipe recipe = this.hammerShapedRecipe(HPR, HPR.outputPrefix, HPR.primaryMaterial);
						if (recipe != null) {
							arecipes.add(recipe);
						}
					}
				}
			}
		}
	}
  
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<IRecipe> allrecipes = CraftingManagerHammer.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			CachedRecipe recipe = null;
			if ((irecipe instanceof HammerablePrefixRecipe)) {
				OreDictItemData mData = OM.anydata(ingredient);
				if (mData != null)
				{
					recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
				} else {
					recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe);
				}
			} else if ((irecipe instanceof HammerableShapedRecipe)) {
				OreDictItemData mData = OM.anydata(ingredient);
				if (mData != null)
				{
					recipe = hammerShapedRecipe((HammerableShapedRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
				} else {
					recipe = hammerShapedRecipe((HammerableShapedRecipe)irecipe);
				}
			} else if ((irecipe) instanceof QTArmor)
				{
					OreDictItemData mData = OM.anydata(ingredient);
					if (mData != null)
					{
						recipe = armorShapedRecipe((QTArmor)irecipe, mData.mMaterial.mMaterial);
					} else {
						recipe = armorShapedRecipe((QTArmor)irecipe);
					}
			} else if (irecipe instanceof ShapedOreRecipe)
			{
				recipe = forgeShapedRecipe((ShapedOreRecipe)irecipe);
			} else if (irecipe instanceof ShapelessOreRecipe)
			{
				recipe = forgeShapelessRecipe((ShapelessOreRecipe)irecipe);
			}
			if (recipe != null)
			{
				if (recipe.contains(recipe.getIngredients(), ingredient)) {
					recipe.setIngredientPermutation(recipe.getIngredients(), ingredient);
					arecipes.add(recipe);
				} else if(OM.is("craftingToolHardHammer", ingredient))
				{
					arecipes.add(recipe);
				}
			}
		}
	}
	
	public HammerableCachedRecipe shapelessHammerRecipe(HammerablePrefixRecipe recipe)
	{
		return shapelessHammerRecipe(recipe, null, null);
	}
	
	public HammerableCachedRecipe shapelessHammerRecipe(HammerablePrefixRecipe recipe, OreDictPrefix prefix, OreDictMaterial mat)
	{
		ArrayList<List<ItemStack>> returning = new ArrayList();
		for (int q = 0; q < 9; q++)
		{
			returning.add(null);
		}
		ArrayList<Object> items = new ArrayList(Arrays.asList(recipe.recipePieces));
		for (Object item : items) {
			if (((item instanceof List)) && (((List)item).isEmpty()))
				return null;
		}
		try {
			for (int q = 0; q < items.size(); q++)
			{
				Object check = items.get(q);
				if (check instanceof OreDictItemData)
				{
					OreDictItemData data = (OreDictItemData)check;
					List<ItemStack> adderble = new ArrayList();
					if (mat != null && mat != MT.NULL && data.mPrefix.isTrue(mat))
					{
						if (data.mMaterial.mMaterial == MT.NULL || (data.mMaterial.mMaterial == QTMT.Undefined && mat != QTMT.Undefined))
						{
							adderble.add(data.mPrefix.mat(mat, 1));
						} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)) {
							adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
						} else {
							return null;
						}
					} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial) || data.mMaterial.mMaterial == QTMT.Undefined) {
						adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
					} else {
						return null;
					}
					items.set(q, adderble);
				} else if (check instanceof String)
				{
					List<ItemStack> ores = OreDictionary.getOres((String)check);
					if (!ores.isEmpty()) {
						items.set(q, ores);
					}
				} else if (check instanceof ItemStack)
				{
					ItemStack stacky = (ItemStack)check;
					List<ItemStack> adderble = new ArrayList();
					adderble.add(stacky);
					items.set(q, adderble);
				}
			}
			ItemStack output = recipe.getRecipeOutput();
			OreDictItemData outputData = OM.anydata(output);
			if (outputData != null)
			{
				if ( mat != null && mat != MT.NULL && mat != QTMT.Undefined && outputData.mPrefix.isTrue(mat))
				{
					output = outputData.mPrefix.mat(mat, output.stackSize);
				} else if (!outputData.mPrefix.isTrue(outputData.mMaterial.mMaterial))
				{
					return null;
				}
			}
			//System.out.println("Removed " + removed + " unsuitable materials from NEI recipe");
			returning.set(0, (List)items.get(0));
			if (recipe.isVertical)
			{
				if (recipe.recipePieces.length > 1)
				{
					returning.set(3, (List)items.get(1));
					if (recipe.recipePieces.length == 3)
					{
						returning.set(6, (List)items.get(2));
					}
				}
			} else {
				for (int q = 1; q < items.size(); q++)
				{
					returning.set(q, (List)items.get(q));
				}
			}
			if (UT.Code.containsSomething(returning.toArray()) && output != null)
			{
				return new HammerableCachedRecipe(3, 3, returning.toArray(), output);
			}
		} catch (Exception e) {
			codechicken.nei.NEIClientConfig.logger.error("Error loading recipe: ", e); }
		return null;
	}
	
	public HammerableCachedRecipe hammerShapedRecipe(HammerableShapedRecipe recipe)
	{
		return hammerShapedRecipe(recipe, null, null);
	}
	
	public HammerableCachedRecipe hammerShapedRecipe(HammerableShapedRecipe recipe, OreDictPrefix prefix, OreDictMaterial mat) {
        try {
            Object[] items = recipe.input.clone();
            for (int q = 0; q < items.length; q++)
            {
            	Object item = items[q];
                if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                {
                    return null;
                } else if (item instanceof OreDictItemData)
            	{
            		OreDictItemData data = (OreDictItemData)item;
					List<ItemStack> adderble = new ArrayList();
					if (data.mPrefix.isTrue(mat))
					{
						if (data.mMaterial.mMaterial == MT.NULL || (data.mMaterial.mMaterial == QTMT.Undefined && mat != QTMT.Undefined))
						{
							adderble.add(data.mPrefix.mat(mat, 1));
						} else {
							adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
						}
					} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)) {
						adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
					} else if (mat == null || mat == MT.NULL || mat == QTMT.Undefined) {
						adderble.add(data.mPrefix.mat(QTMT.Undefined, 1));
					} else {
						System.out.println("Could not find results for " + data.mMaterial.mMaterial + " and " + data.mPrefix + " while looking at " + mat);
						System.out.println("Failing recipe was to make " + recipe.outputPrefix);
						return null;
					}
					items[q] = adderble;
            	}
            }
            ItemStack output = recipe.getRecipeOutput();
			OreDictItemData outputData = OM.anydata(output);
			if (outputData != null && mat != null && mat != MT.NULL && mat != QTMT.Undefined && outputData.mPrefix.isTrue(mat))
			{
				output = outputData.mPrefix.mat(mat, output.stackSize);
			}
			if (output == null) return null;
            return new HammerableCachedRecipe(recipe.width, recipe.height, items, output);
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }
	
	public HammerableCachedRecipe armorShapedRecipe(QTArmor recipe)
	{
		return armorShapedRecipe(recipe, MT.NULL);
	}
	
	public HammerableCachedRecipe armorShapedRecipe(QTArmor recipe, OreDictMaterial mat) {
        try {
            Object[] items = recipe.input.clone();
            for (int q = 0; q < items.length; q++)
            {
            	Object item = items[q];
                if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                {
                    return null;
                } else if (item instanceof OreDictItemData)
            	{
            		OreDictItemData data = (OreDictItemData)item;
					List<ItemStack> adderble = new ArrayList();
					if (mat != null && mat != MT.NULL && data.mPrefix.isTrue(mat))
					{
						if (data.mMaterial.mMaterial == MT.NULL || (data.mMaterial.mMaterial == MT.Steel && mat != MT.Steel))
						{
							adderble.add(data.mPrefix.mat(mat, 1));
						} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)) {
							adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
						} else {
							return null;
						}
					} else if (mat == null || mat == MT.NULL){
						adderble.add(data.mPrefix.mat(MT.Steel, 1));
					} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)) {
						adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
					} else {
						return null;
					}
					items[q] = adderble;
            	}
            }
            ItemStack output = recipe.getRecipeOutput();
            OreDictMaterial outputMat = MultiItemArmor.getPrimaryMaterial(output);
            if (outputMat == null || outputMat == MT.NULL)
            {
            	NBTTagCompound tag = UT.NBT.getNBT(output);
            	NBTTagCompound armor = tag.getCompoundTag("QT.ArmorStats");
            	if (mat != MT.NULL && mat != null)
            	{
            		armor.setShort("a", mat.mID);
            		armor.setString("b", mat.toString());
            	}
            	tag.setTag("QT.ArmorStats", armor);
            	UT.NBT.set(output, tag);
            }
            return new HammerableCachedRecipe(recipe.width, recipe.height, items, output);
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }
	
	public CachedShapedRecipe forgeShapedRecipe(ShapedOreRecipe recipe) {
        try {
            int width = ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 4);
            int height = ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 5);

            Object[] items = recipe.getInput();
            for (Object item : items)
                if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                    return null;

            return new CachedShapedRecipe(width, height, items, recipe.getRecipeOutput());
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }
	
	public ShapelessRecipeHandler.CachedShapelessRecipe forgeShapelessRecipe(ShapelessOreRecipe recipe) {
        ArrayList<Object> items = recipe.getInput();

        for (Object item : items)
            if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                return null;

        try {
            return SRH.new CachedShapelessRecipe(items, recipe.getRecipeOutput());
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }

}
