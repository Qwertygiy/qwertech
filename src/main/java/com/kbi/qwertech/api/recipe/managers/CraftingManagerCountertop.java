package com.kbi.qwertech.api.recipe.managers;

import com.kbi.qwertech.api.data.FOOD;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.recipe.CountertopRecipe;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftingManagerCountertop implements Runnable {

    private static final CraftingManagerCountertop instance = new CraftingManagerCountertop();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();
    private HashMap<String, ArrayList<CountertopRecipe>> recipesByItem = new HashMap<String, ArrayList<CountertopRecipe>>();

    private static boolean hasRun = false;

    /**
     * Returns the static instance of this class
     */
    public static CraftingManagerCountertop getInstance()
    {
        /* The static instance of this class */
        return instance;
    }

    public ArrayList<CountertopRecipe> findMatchingRecipes(List<ItemStack> checking, World p_82787_2_)
    {
        System.out.println("Getting recipes");
        //returnable: only includes the things we have not yet made
        ArrayList<CountertopRecipe> returnable = new ArrayList<CountertopRecipe>();
        ArrayList<CountertopRecipe> potentialRecipes = new ArrayList<CountertopRecipe>();
        System.out.println("We found " + checking.size() + " items");
        //if we added a new item through the last recursive check
        boolean newItem = true;
        //how many recursive tries we have had
        int lastRun = 0;

        //as long as we have added a new item to the list and we haven't yet gone five times, check it again
        while (newItem && lastRun++ < 5) {
            System.out.println("doing recursive check #" + lastRun);
            newItem = false;
            for (int j = 0; j < checking.size() + returnable.size() + potentialRecipes.size(); j++) {
                ArrayList<CountertopRecipe> contains;
                if (j < checking.size()) {
                    contains = recipesByItem.get(checking.get(j).getUnlocalizedName());
                } else if (j < checking.size() + returnable.size()) {
                    contains = recipesByItem.get(returnable.get(j - checking.size()).getCraftingResultList(checking).getUnlocalizedName());
                } else {
                    contains = recipesByItem.get(potentialRecipes.get(j - checking.size() - returnable.size()).getCraftingResultList(checking).getUnlocalizedName());
                }
                if (contains == null)
                {
                    contains = new ArrayList<CountertopRecipe>();
                }
                int[] ODN;
                if (j < checking.size()) {
                    ODN = OreDictionary.getOreIDs(checking.get(j));
                } else if (j < checking.size() + returnable.size()) {
                    ODN = OreDictionary.getOreIDs(returnable.get(j - checking.size()).getCraftingResultList(checking));
                } else {
                    ODN = OreDictionary.getOreIDs(potentialRecipes.get(j - checking.size() - returnable.size()).getCraftingResultList(checking));
                }
                for (int w = 0; w < ODN.length; w++)
                {
                    String OD = OreDictionary.getOreName(ODN[w]);
                    ArrayList<CountertopRecipe> temp = recipesByItem.get(OD);
                    if (temp != null && !temp.isEmpty()) {
                        contains.addAll(temp);
                    }
                }

                if (!contains.isEmpty()) {
                    for (int q = 0; q < contains.size(); q++) {
                        CountertopRecipe recipe = contains.get(q);
                        boolean isNew = true;
                        for (CountertopRecipe aReturnable : returnable) {
                            if (aReturnable == recipe) {
                                isNew = false;
                                break;
                            }
                        }

                        if (isNew) {
                            for (CountertopRecipe potentialRecipe : potentialRecipes) {
                                if (potentialRecipe == recipe) {
                                    isNew = false;
                                    break;
                                }
                            }
                            if (recipe.matchesLists(checking, returnable)) {
                                System.out.println("Adding new recipe we have everything for: " + recipe.getRecipeOutput().getDisplayName());
                                newItem = true;
                                returnable.add(recipe);
                            } else {
                                if (isNew)
                                {
                                    newItem = true;
                                    potentialRecipes.add(recipe);
                                }
                                //System.out.println("Adding new recipe we can't make yet");
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("Returning " + returnable.size() + " items");
        for (CountertopRecipe recipe : potentialRecipes)
        {
            returnable.add(recipe);
        }
        return returnable;
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }

    public void addRecipe(ItemStack result, Object... ingredients)
    {
        CountertopRecipe recipe = new CountertopRecipe(result, ingredients);
        this.getRecipeList().add(recipe);
        for (int q = 0; q < ingredients.length; q++)
        {
            String key = "";
            if (ingredients[q] instanceof ItemStack)
            {
                key = ((ItemStack)ingredients[q]).getUnlocalizedName();
            } else if (ingredients[q] instanceof String)
            {
                key = (String)ingredients[q];
            }
            if (recipesByItem.containsKey(key))
            {
                recipesByItem.get(key).add(recipe);
            } else {
                ArrayList<CountertopRecipe> list = new ArrayList<CountertopRecipe>();
                list.add(recipe);
                recipesByItem.put(key, list);
            }
        }
    }

    private CraftingManagerCountertop() {

    }

    @Override
    public void run() {
        FOOD.setDefaultQuantity(ST.make(Items.chicken, 1, 0), FOOD.POUND);
        FOOD.setDefaultQuantity(ST.make(Items.cooked_chicken, 1, 0), FOOD.POUND);
        FOOD.setDefaultQuantity(ST.make(Items.porkchop, 1, 0), FOOD.SLAB);
        FOOD.setDefaultQuantity(ST.make(Items.cooked_porkchop, 1, 0), FOOD.SLAB);
        FOOD.setDefaultQuantity(ST.make(Items.beef, 1, 0), FOOD.SLAB);
        FOOD.setDefaultQuantity(ST.make(Items.cooked_beef, 1, 0), FOOD.SLAB);
        FOOD.setDefaultQuantity(ST.make(Items.bread, 1, 0), FOOD.SERVING);
        FOOD.setDefaultQuantity(ST.make(Items.fish, 1, 0), FOOD.PORTION);
        FOOD.setDefaultQuantity(ST.make(Items.cooked_fished, 1, 0), FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Dough.get(1), FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Baguette_Raw.get(1), 125 + 64);
        FOOD.setDefaultQuantity(IL.Food_Bun_Raw.get(1), FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Bread_Raw.get(1), FOOD.SERVING);
        FOOD.setDefaultQuantity(IL.Food_Dough_Flat.get(1), FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Dough_Sugar.get(1), FOOD.LUMP);
        FOOD.setDefaultQuantity(IL.Food_Dough_Chocolate.get(1), FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_CakeBottom_Raw.get(1), FOOD.SERVING);
        FOOD.setDefaultQuantity(IL.Food_Cookie_Raw.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(OP.dust.mat(MT.Sugar, 1), FOOD.DASH);
        FOOD.setDefaultQuantity(OP.gemChipped.mat(MT.Sugar, 1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(OP.dust.mat(MT.Chocolate, 1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(OP.dust.mat(MT.Cocoa, 1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(IL.Food_Raisins_Green.get(1), 50);
        FOOD.setDefaultQuantity(IL.Food_Raisins_Purple.get(1), 50);
        FOOD.setDefaultQuantity(IL.Food_Raisins_Red.get(1), 50);
        FOOD.setDefaultQuantity(IL.Food_Raisins_White.get(1), 50);
        FOOD.setDefaultQuantity(IL.Food_Raisins_Chocolate.get(1), 60);
        FOOD.setDefaultQuantity(ST.make(Items.cookie, 1, 0), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(ST.make(Items.bowl, 1, 0), 0);
        FOOD.setDefaultQuantity(ST.make(Items.glass_bottle, 1, 0), 0);
        FOOD.setDefaultQuantity("cropLemon", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Lemon_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity("cropTomato", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Tomato_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(IL.Food_MTomato.get(1), FOOD.TON);
        FOOD.setDefaultQuantity("cropOnion", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Onion_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity("cropCucumber", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Cucumber_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity("cropChiliPepper", FOOD.CUPFUL);
        FOOD.setDefaultQuantity("cropGrapes", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Carrot_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(ST.make(Items.carrot, 1, 0), FOOD.PORTION);
        FOOD.setDefaultQuantity("cropBanana", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Banana_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity("cropPomegranate", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Pomeraisins.get(1), 50);
        FOOD.setDefaultQuantity("cropBlueberry", 10);
        FOOD.setDefaultQuantity("cropGooseberry", 10);
        FOOD.setDefaultQuantity("cropCandleberry", 10);
        FOOD.setDefaultQuantity("cropCranberry", 10);
        FOOD.setDefaultQuantity("cropBlackberry", 10);
        FOOD.setDefaultQuantity("cropRaspberry", 10);
        FOOD.setDefaultQuantity("cropStrawberry", 10);
        FOOD.setDefaultQuantity("cropApple", 10);
        FOOD.setDefaultQuantity(IL.Food_Apple_DarkRed_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(IL.Food_Apple_Green_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(IL.Food_Apple_Red_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity(IL.Food_Apple_Yellow_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity("cropPeanut", 10);
        FOOD.setDefaultQuantity("cropHazelnut", 10);
        FOOD.setDefaultQuantity("cropPineapple", FOOD.PORTION);
        FOOD.setDefaultQuantity("cropAnanas", FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Ananas_Sliced.get(1), FOOD.SPOONFUL);
        FOOD.setDefaultQuantity("cropCinnamon", FOOD.LUMP);
        FOOD.setDefaultQuantity(IL.Food_Cheese.get(1), 80);
        FOOD.setDefaultQuantity(IL.Food_Cheese_Sliced.get(1), 20);
        FOOD.setDefaultQuantity(IL.Food_Bun.get(1), FOOD.PORTION);
        FOOD.setDefaultQuantity(IL.Food_Bun_Sliced.get(1), FOOD.LUMP);
        FOOD.setDefaultQuantity(IL.Food_Buns_Sliced.get(1), FOOD.PORTION);


        addRecipe(IL.Food_Baguette_Raw.get(1), IL.Food_Dough.get(1), IL.Food_Dough.get(1), IL.Food_Dough.get(1));
        addRecipe(IL.Food_Bread_Raw.get(1), IL.Food_Dough.get(1), IL.Food_Dough.get(1));
        addRecipe(IL.Food_Bun_Raw.get(1), IL.Food_Dough.get(1));
        addRecipe(IL.Food_Dough_Flat.get(1), IL.Food_Dough.get(1), "craftingToolRollingPin");
        addRecipe(IL.Food_Dough_Sugar.get(2), IL.Food_Dough.get(1), OP.dust.mat(MT.Sugar, 1));
        addRecipe(IL.Food_Dough_Sugar.get(4), IL.Food_Dough.get(1), OP.gemChipped.mat(MT.Sugar, 4));
        addRecipe(IL.Food_Dough_Chocolate.get(2), IL.Food_Dough.get(1), OP.dust.mat(MT.Chocolate, 1));
        addRecipe(IL.Food_Dough_Chocolate.get(1), IL.Food_Dough.get(1), OP.dust.mat(MT.Cocoa, 1));
        addRecipe(IL.Food_CakeBottom_Raw.get(1), IL.Food_Dough_Sugar.get(1), IL.Food_Dough_Sugar.get(1), IL.Food_Dough_Sugar.get(1), IL.Food_Dough_Sugar.get(1));
        addRecipe(IL.Food_Dough_Sugar_Chocolate_Raisins.get(1), IL.Food_Dough_Sugar.get(1), IL.Food_Raisins_Chocolate.get(1));
        addRecipe(IL.Food_Dough_Sugar_Raisins.get(1), IL.Food_Dough_Sugar.get(1), "foodRaisin");
        addRecipe(IL.Food_Cookie_Raw.get(4), IL.Food_Dough_Chocolate.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Lemon_Sliced.get(4), "cropLemon", "craftingToolKnife");
        addRecipe(IL.Food_Tomato_Sliced.get(4), "cropTomato", "craftingToolKnife");
        addRecipe(IL.Food_Onion_Sliced.get(4), "cropOnion", "craftingToolKnife");
        addRecipe(IL.Food_Cucumber_Sliced.get(4), "cropCucumber", "craftingToolKnife");
        addRecipe(IL.Food_Carrot_Sliced.get(4), "cropCarrot", "craftingToolKnife");
        addRecipe(IL.Food_Banana_Sliced.get(4), "cropBanana", "craftingToolKnife");
        addRecipe(IL.Food_Ananas_Sliced.get(4), "cropPineapple", "craftingToolKnife");
        addRecipe(IL.Food_Apple_DarkRed_Sliced.get(4), IL.Food_Apple_DarkRed.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Apple_Red_Sliced.get(4), IL.Food_Apple_Red.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Apple_Green_Sliced.get(4), IL.Food_Apple_Green.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Apple_Yellow_Sliced.get(4), IL.Food_Apple_Yellow.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Cheese_Sliced.get(4), IL.Food_Cheese.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Ham_Slice_Raw.get(4), IL.Food_Ham_Raw.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Ham_Slice_Cooked.get(4), IL.Food_Ham_Cooked.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Cookie_Raisins_Raw.get(4), IL.Food_Dough_Sugar_Raisins.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Cookie_Chocolate_Raisins_Raw.get(4), IL.Food_Dough_Sugar_Chocolate_Raisins.get(1), "craftingToolKnife");
        addRecipe(QTI.doughFlatSauce.get(1), IL.Food_Dough_Flat.get(1), QTI.tomatoSauce.get(1));
        addRecipe(QTI.doughFlatSauce.get(5), IL.Food_Dough_Flat.get(1), IL.Food_Dough_Flat.get(1), IL.Food_Dough_Flat.get(1), IL.Food_Dough_Flat.get(1), IL.Food_Dough_Flat.get(1), QTI.tomatoSauce.get(1));
        addRecipe(IL.Food_Pizza_Cheese_Raw.get(1), QTI.doughFlatSauce.get(1), QTI.mozzarella.get(1), QTI.mozzarella.get(1), QTI.mozzarella.get(1));
        addRecipe(IL.Food_Pizza_Meat_Raw.get(1), QTI.doughFlatSauce.get(1), QTI.mozzarella.get(1), OP.dust.mat(MT.MeatCooked, 1));
        addRecipe(IL.Food_Pizza_Veggie_Raw.get(1), QTI.doughFlatSauce.get(1), IL.Food_Tomato_Sliced.get(1), IL.Food_Onion_Sliced.get(1), IL.Food_Cucumber_Sliced.get(1), QTI.mozzarella.get(1));
        addRecipe(IL.Food_Pizza_Ananas_Raw.get(1), QTI.doughFlatSauce.get(1), IL.Food_Ananas_Sliced.get(1), IL.Food_Ananas_Sliced.get(1), IL.Food_Ham_Slice_Cooked.get(1), QTI.mozzarella.get(1));
        addRecipe(IL.Food_Bun_Sliced.get(2), IL.Food_Bun.get(1), "craftingToolKnife");
        addRecipe(IL.Food_Buns_Sliced.get(1), IL.Food_Bun_Sliced.get(1), IL.Food_Bun_Sliced.get(1));
        addRecipe(IL.Food_Burger_Veggie.get(1), IL.Food_Buns_Sliced.get(1), IL.Food_Tomato_Sliced.get(1), IL.Food_Cucumber_Sliced.get(1), IL.Food_Onion_Sliced.get(1));
        addRecipe(IL.Food_Burger_Cheese.get(1), IL.Food_Buns_Sliced.get(1), IL.Food_Cheese_Sliced.get(1), IL.Food_Cheese_Sliced.get(1), IL.Food_Cheese_Sliced.get(1));
        addRecipe(IL.Food_Burger_Meat.get(1), IL.Food_Buns_Sliced.get(1), OP.ingot.mat(MT.MeatCooked, 1));
        addRecipe(IL.Food_Burger_Chum.get(1), IL.Food_Buns_Sliced.get(1), IL.Food_Chum.get(1));
        addRecipe(IL.Food_Burger_Tofu.get(1), IL.Food_Buns_Sliced.get(1), OP.ingot.mat(MT.Tofu, 1));
        addRecipe(IL.Food_Burger_Fish.get(1), IL.Food_Buns_Sliced.get(1), OP.ingot.mat(MT.FishCooked, 1));
    }
}
