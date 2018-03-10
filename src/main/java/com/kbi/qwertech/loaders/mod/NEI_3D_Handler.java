package com.kbi.qwertech.loaders.mod;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import codechicken.nei.recipe.*;
import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTMT;
import com.kbi.qwertech.api.recipe.CraftingRecipe3D;
import com.kbi.qwertech.api.recipe.HammerablePrefixRecipe;
import com.kbi.qwertech.api.recipe.Prefix3DRecipe;
import com.kbi.qwertech.api.recipe.QTArmor3D;
import com.kbi.qwertech.api.recipe.managers.CraftingManager3D;
import com.kbi.qwertech.client.tileentity.CraftingTable3DRenderer;
import com.kbi.qwertech.tileentities.CraftingTableT4;
import com.kbi.qwertech.tileentities.CraftingTableT4.GUIClientAdvancedCraftingTable4;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.data.MT;
import gregapi.gui.ContainerClient;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

public class NEI_3D_Handler extends ShapedRecipeHandler {

	ShapelessRecipeHandler SRH = new ShapelessRecipeHandler();
	
	public NEI_3D_Handler() {
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(145, 90, 20, 23), getOverlayIdentifier()));
	    if (!NEI_QT_Config.sIsAdded)
	    {
	      System.out.println("Creating QT NEI 3D handler");
	      FMLInterModComms.sendRuntimeMessage(QwerTech.instance, "NEIPlugins", "register-crafting-handler", QwerTech.MODID + "@" + getRecipeName() + "@" + getOverlayIdentifier());
	      GuiCraftingRecipe.craftinghandlers.add(this);
	      GuiUsageRecipe.usagehandlers.add(this);
	    }
	}
	
	@Override
	public TemplateRecipeHandler newInstance() {
    	return new NEI_3D_Handler();
    }

	@Override
	public String getRecipeName() {
		// TODO Auto-generated method stub
		return "3x3x3 Crafting";
	}
	
	@Override
	public int recipiesPerPage()
	{
		return 1;
	}
	
	public CraftingTable3DRenderer drawer = new CraftingTable3DRenderer();
	
	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1, 1, 1, 1);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(-3, 0, 2, 3, 173, 120);
    }
	
	@Override
	public Class getGuiClass()
	{
		return CraftingTableT4.GUICommonAdvancedCraftingTable4.class;
	}

	@Override
	public String getGuiTexture()
	{
		return "qwertech:textures/gui/CraftingTableT4.png";
	}
	 
	@Override
	public String getOverlayIdentifier()
	{
		return "crafting3d";
	}
														    // 39, 67, 99
	public static int[] xs = new int[]{41, 71, 103, 29, 59, 91, 16, 46, 78};
    public static int[] ys = new int[]{25, 53, 85, 32, 60, 92, 39, 67, 99};
	
    static {
	    GuiContainerManager.addInputHandler(new QT_RectHandler());
	}
    
    public static class QT_RectHandler implements IContainerInputHandler {
        @Override
        public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) {
            if (canHandle(gui)) {
                if (button == 0) return transferRect(gui, F);
                if (button == 1) return transferRect(gui, T);
            }
            return F;
        }
        
        @Override
        public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyCode) {
        	return F;
        }
        
		public boolean canHandle(GuiContainer gui) {
            return gui instanceof GUIClientAdvancedCraftingTable4;
        }
        
        private boolean transferRect(GuiContainer gui, boolean usage) {
            return canHandle(gui) && new Rectangle(145, 90, 20, 23).contains(new Point(GuiDraw.getMousePosition().x - ((ContainerClient)gui).getLeft() - RecipeInfo.getGuiOffset(gui)[0], GuiDraw.getMousePosition().y - ((ContainerClient)gui).getTop() - RecipeInfo.getGuiOffset(gui)[1])) && (usage ? GuiUsageRecipe.openRecipeGui("crafting3d") : GuiCraftingRecipe.openRecipeGui("crafting3d"));
        }

		@Override
		public boolean keyTyped(GuiContainer gui, char keyChar, int keyCode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseClicked(GuiContainer gui, int mousex, int mousey,
				int button) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseUp(GuiContainer gui, int mousex, int mousey,
				int button) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey,
				int scrolled) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onMouseScrolled(GuiContainer gui, int mousex, int mousey,
				int scrolled) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMouseDragged(GuiContainer gui, int mousex, int mousey,
				int button, long heldTime) {
			// TODO Auto-generated method stub
			
		}
    }
    
	public class Cached3DRecipe extends CachedShapedRecipe
	{

        public Cached3DRecipe(int width, int height, Object[] items, ItemStack out) {
            super(width, height, items, out);
        }

        /*public Cached3DRecipe(ShapedRecipes recipe) {
            super(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.getRecipeOutput());
        }*/

        /**
         * @param width width of recipe
         * @param height heihgt of recipe
         * @param items  an ItemStack[] or ItemStack[][]
         */
        @Override
        public void setIngredients(int width, int height, Object[] items) {
            for (int d = 0; d < 3; d++) {
	        	for (int x = 0; x < width; x++) {
	                for (int y = 0; y < 3; y++) {
	                	int w = 2 - d;
	                	int size = (w * 9) + (y * width) + x;
	                	if (size >= items.length) continue;
	                	Object returnIt = items[size];
	                	if (returnIt == null || (returnIt instanceof List && ((List)returnIt).size() <= 0) || (returnIt instanceof ItemStack[] && ((ItemStack[])returnIt).length <= 0))
	                    {
	                        continue;
	                    }
	                    PositionedStack stack = new PositionedStack(returnIt, xs[x + (d * 3)], ys[y + (d * 3)], false);
	                    stack.setMaxSize(1);
	                    ingredients.add(stack);
	                }
	            }
            }
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, ingredients);
        }

        @Override
        public PositionedStack getResult() {
        	result.relx = 147;
        	result.rely = 24;
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
			OreDictItemData data1 = OM.anydata(one);
			OreDictItemData data2 = OM.anydata(two);
			if (data1 != null && data2 != null && data1.mPrefix == data2.mPrefix && data2.hasValidPrefixMaterialData() && data1.hasValidPrefixMaterialData())
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
		if ((outputId.equals("crafting3d")) && (getClass() == NEI_3D_Handler.class)) {
			List<IRecipe> allrecipes = CraftingManager3D.getInstance().getRecipeList();
			for (IRecipe irecipe : allrecipes) {
				CachedRecipe recipe = null;
				if (irecipe instanceof HammerablePrefixRecipe) {
					recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe);
				} else if (irecipe instanceof Prefix3DRecipe) {
					recipe = hammerShapedRecipe((Prefix3DRecipe)irecipe);
				} else if (irecipe instanceof QTArmor3D) {
					recipe = armorShapedRecipe((QTArmor3D)irecipe);
				} else if (irecipe instanceof CraftingRecipe3D)
				{
					recipe = forgeShapedRecipe((CraftingRecipe3D)irecipe);
				} else if (irecipe instanceof ShapelessOreRecipe)
				{
					recipe = forgeShapelessRecipe((ShapelessOreRecipe)irecipe);
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
		List<IRecipe> allrecipes = CraftingManager3D.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			if (matches(irecipe.getRecipeOutput(), result)) {
				CachedRecipe recipe = null;
				if ((irecipe instanceof HammerablePrefixRecipe)) {
					OreDictItemData mData = OM.anydata(result);
					if (mData != null)
					{
						recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
					} else {
						recipe = shapelessHammerRecipe((HammerablePrefixRecipe)irecipe);
					}
				} else if ((irecipe instanceof Prefix3DRecipe)) {
					OreDictItemData mData = OM.anydata(result);
					if (mData != null)
					{
						recipe = hammerShapedRecipe((Prefix3DRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
					} else {
						recipe = hammerShapedRecipe((Prefix3DRecipe)irecipe);
					}
				} else if (irecipe instanceof QTArmor3D) {
					if (result.getItem() instanceof MultiItemArmor)
					{
						recipe = armorShapedRecipe((QTArmor3D)irecipe, MultiItemArmor.getPrimaryMaterial(result));
					}
				} else if (irecipe instanceof CraftingRecipe3D)
				{
					recipe = forgeShapedRecipe((CraftingRecipe3D)irecipe);
				} else if (irecipe instanceof ShapelessOreRecipe)
				{
					recipe = forgeShapelessRecipe((ShapelessOreRecipe)irecipe);
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
		List<IRecipe> allrecipes = CraftingManager3D.getInstance().getRecipeList();
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
			} else if ((irecipe instanceof Prefix3DRecipe)) {
				OreDictItemData mData = OM.anydata(ingredient);
				if (mData != null)
				{
					recipe = hammerShapedRecipe((Prefix3DRecipe)irecipe, mData.mPrefix, mData.mMaterial.mMaterial);
				} else {
					recipe = hammerShapedRecipe((Prefix3DRecipe)irecipe);
				}
			} else if (irecipe instanceof QTArmor3D) {
				OreDictItemData mData = OM.anydata(ingredient);
				if (mData != null)
				{
					recipe = armorShapedRecipe((QTArmor3D)irecipe, mData.mMaterial.mMaterial);
				} else {
					recipe = armorShapedRecipe((QTArmor3D)irecipe);
				}
			} else if (irecipe instanceof CraftingRecipe3D)
			{
				recipe = forgeShapedRecipe((CraftingRecipe3D)irecipe);
			} else if (irecipe instanceof ShapelessOreRecipe)
			{
				recipe = forgeShapelessRecipe((ShapelessOreRecipe)irecipe);
			}
			if (recipe != null)
			{
				if (recipe.contains(recipe.getIngredients(), ingredient)) {
					recipe.setIngredientPermutation(recipe.getIngredients(), ingredient);
					arecipes.add(recipe);
				} }
		}
	}
	
	public Cached3DRecipe shapelessHammerRecipe(HammerablePrefixRecipe recipe)
	{
		return shapelessHammerRecipe(recipe, null, null);
	}
	
	public Cached3DRecipe shapelessHammerRecipe(HammerablePrefixRecipe recipe, OreDictPrefix prefix, OreDictMaterial mat)
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
						} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)){
							adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
						} else {
							return null;
						}
					} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)){
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
			if (outputData != null && mat != null && mat != MT.NULL && mat != QTMT.Undefined && outputData.mPrefix.isTrue(mat))
			{
				output = outputData.mPrefix.mat(mat, output.stackSize);
			}
			if (output == null) return null;
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
			if (UT.Code.containsSomething(returning.toArray()))
			{
				return new Cached3DRecipe(3, 3, returning.toArray(), output);
			}
		} catch (Exception e) {
			codechicken.nei.NEIClientConfig.logger.error("Error loading recipe: ", e); }
		return null;
	}
	
	public Cached3DRecipe hammerShapedRecipe(Prefix3DRecipe recipe)
	{
		return hammerShapedRecipe(recipe, null, null);
	}
	
	public Cached3DRecipe hammerShapedRecipe(Prefix3DRecipe recipe, OreDictPrefix prefix, OreDictMaterial mat) {
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
						if (data.mMaterial.mMaterial == MT.NULL || (data.mMaterial.mMaterial == QTMT.Undefined && mat != QTMT.Undefined))
						{
							adderble.add(data.mPrefix.mat(mat, 1));
						} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)){
							adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
						} else {
							return null;
						}
					} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)){
						adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
					} else {
						return null;
					}
					items[q] = adderble;
            	}
            }
            ItemStack output = recipe.getRecipeOutput();
			OreDictItemData outputData = OM.anydata(output);
			if (outputData != null)
			{
				if (mat != null && mat != MT.NULL && mat != QTMT.Undefined && outputData.mPrefix.isTrue(mat))
				{
					output = outputData.mPrefix.mat(mat, output.stackSize);
				} else if (!outputData.mPrefix.isTrue(outputData.mMaterial.mMaterial))
				{
					return null;
				}
			}
			if (output == null) return null;
            return new Cached3DRecipe(3, 3, items, output);
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            NEIClientConfig.logger.error(recipe.outputPrefix.mNameLocal + recipe.tempPrimary.mNameLocal);
            return null;
        }
    }
	
	public Cached3DRecipe armorShapedRecipe(QTArmor3D recipe)
	{
		return armorShapedRecipe(recipe, MT.NULL);
	}
	
	public Cached3DRecipe armorShapedRecipe(QTArmor3D recipe, OreDictMaterial mat) {
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
						if (data.mMaterial.mMaterial == MT.NULL || (data.mMaterial.mMaterial == QTMT.Undefined && mat != QTMT.Undefined))
						{
							adderble.add(data.mPrefix.mat(mat, 1));
						} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)){
							adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
						} else {
							return null;
						}
					} else if (mat == null || mat == MT.NULL || mat == QTMT.Undefined){
						adderble.add(data.mPrefix.mat(MT.Steel, 1));
					} else if (data.mPrefix.isTrue(data.mMaterial.mMaterial)){
						adderble.add(data.mPrefix.mat(data.mMaterial.mMaterial, 1));
					} else {
						return null;
					}
					items[q] = adderble;
            	}
            }
            ItemStack output = recipe.getRecipeOutput();
            OreDictMaterial outputMat = MultiItemArmor.getPrimaryMaterial(output);
            if (outputMat == MT.NULL)
            {
            	NBTTagCompound tag = UT.NBT.getNBT(output);
            	NBTTagCompound armor = tag.getCompoundTag("QT.ArmorStats");
            	if (mat != MT.NULL && mat != null)
            	{
            		armor.setShort("a", mat.mID);
            		armor.setString("b", mat.toString());
            	}
            	tag.setTag("QT.ArmorStats", armor);
            	output.setTagCompound(tag);
            }
			if (output == null) return null;
            return new Cached3DRecipe(3, 3, items, output);
        } catch (Exception e) {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }
	
	public Cached3DRecipe forgeShapedRecipe(CraftingRecipe3D recipe) {
        try {
            int width = 3;
            int height = 9;

            Object[] items = recipe.getInput();
            for (Object item : items)
                if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                    return null;

            return new Cached3DRecipe(width, height, items, recipe.getRecipeOutput());
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
