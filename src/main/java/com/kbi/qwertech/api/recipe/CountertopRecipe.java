package com.kbi.qwertech.api.recipe;

import com.kbi.qwertech.api.data.FOOD;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountertopRecipe extends ShapelessOreRecipe implements IListRecipe {

    public CountertopRecipe(ItemStack result, Object... recipe) {
        super(result, recipe);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1) {
        List<ItemStack> list = new ArrayList<ItemStack>();
        for (int x = 0; x < var1.getSizeInventory(); x++)
        {
            ItemStack stack = var1.getStackInSlot(x);
            if (ST.valid(stack))
            {
                for (int q = 0; q < stack.stackSize; q++)
                {
                    list.add(stack);
                }
            }
        }
        return getCraftingResultList(list);
    }

    private ArrayList<ItemStack> tempResults = new ArrayList<ItemStack>();
    private Integer[] matchingOne = new Integer[32];
    private Integer[] matchingTwo = new Integer[64];

    @Override
    public boolean matches(InventoryCrafting var1, World world) {
        List<ItemStack> list = new ArrayList<ItemStack>();
        for (int x = 0; x < var1.getSizeInventory(); x++)
        {
            ItemStack stack = var1.getStackInSlot(x);
            if (ST.valid(stack))
            {
                for (int q = 0; q < stack.stackSize; q++)
                {
                    list.add(stack);
                }
            }
        }
        return matchesList(list);
    }

    @Override
    public ItemStack getCraftingResultList(List<ItemStack> input) {
        int[] quality = new int[this.getInput().size()];
        ArrayList<ItemStack> pruned = new ArrayList<ItemStack>(this.getInput().size());
        ArrayList<ItemStack> results = (ArrayList<ItemStack>)tempResults.clone();
        for (int q = 0; q < this.getInput().size(); q++)
        {
            Object examine = this.getInput().get(q);
            if (examine instanceof ItemStack)
            {
                pruned.add((ItemStack)examine);
            } else if (examine instanceof ArrayList && ((ArrayList)examine).size() > 0)
            {
                pruned.add(((ArrayList<ItemStack>)examine).get(0));
            } else {
                return this.getRecipeOutput().copy(); //this means we have an OreDict entry which has no match
            }
            for (int w = 0; w < results.size(); w++)
            {
                ItemStack check = results.get(w);
                if (ST.equal(pruned.get(q), check, true))
                {
                    int qual = FOOD.getQuality(check);
                    if (qual > quality[q])
                    {
                        pruned.set(q, check);
                        quality[q] = qual;
                        results.remove(w);
                        w = w - 1;
                    }
                }
            }
        }

        int totalquality = 0;
        int totalquantity = 0;
        for (int e = 0; e < pruned.size(); e++)
        {
            int quantity = FOOD.getQuantity(pruned.get(e));
            totalquality = totalquality + (quality[e] * quantity);
            totalquantity = totalquantity + quantity;
        }
        totalquality = (int)Math.floor((double)totalquality / (double)totalquantity);
        return FOOD.setQuality(this.getRecipeOutput().copy(), (byte)Math.min(255, totalquality));
        //return super.getCraftingResult(var1);
    }

    @Override
    public ItemStack getRecipeOutput() {
        ItemStack returnable = super.getRecipeOutput();
        if (returnable.stackSize <= 0)
        {
            returnable.stackSize = 1;
        }
        return returnable;
    }

    public ArrayList<ItemStack> copyList(List<ItemStack> toCopy)
    {
        ArrayList<ItemStack> returnable = new ArrayList<ItemStack>(0);
        for (ItemStack is : toCopy)
        {
            if (is != null) {
                returnable.add(is.copy());
            } else {
                returnable.add(null);
            }
        }
        return returnable;
    }

    public boolean matchesLists(List<ItemStack> input, List<CountertopRecipe> secondary) {
        return matchesListsInternal(copyList(input), secondary);
    }

    private boolean matchesListsInternal(List<ItemStack> input, List<CountertopRecipe> secondary) {
        ArrayList<Object> required = new ArrayList<Object>(this.getInput());
        List<ItemStack> tempInput = input;
        matchingOne = UT.Code.fill(0, new Integer[32]);
        matchingTwo = UT.Code.fill(0, new Integer[64]);
        tempResults = new ArrayList<ItemStack>();

        for (int x = 0; x < tempInput.size(); x++)
        {
            ItemStack slot = tempInput.get(x);
        //Iterator<ItemStack> irq = input.iterator();
        //while (irq.hasNext())
        //{
            //ItemStack slot = irq.next();

            if (ST.valid(slot))
            {
                //Iterator<Object> req = required.iterator();

                //while (req.hasNext())
                //{
                for (int k = 0; k < required.size(); k++)
                {
                    boolean match = false;

                    Object next = required.get(k);

                    //Object next = req.next();

                    if (next instanceof ItemStack)
                    {
                        match = OreDictionary.itemMatches((ItemStack)next, slot, false);
                    }
                    else if (next instanceof ArrayList)
                    {
                        Iterator<ItemStack> itr = ((ArrayList<ItemStack>)next).iterator();
                        while (itr.hasNext() && !match)
                        {
                            match = OreDictionary.itemMatches(itr.next(), slot, false);
                        }
                    }

                    if (match)
                    {
                        try {
                            matchingOne[x] = matchingOne[x] + 1;
                            if(slot.stackSize > 1)
                            {
                                slot.stackSize = slot.stackSize - 1;
                                tempInput.set(x, slot);
                                tempResults.add(slot);
                            } else {
                                tempInput.set(x, null);
                                tempResults.add(slot);
                                slot = null;
                            }
                            required.remove(next);
                            k = k - 1;
                        } catch (Throwable t)
                        {
                            System.out.println("ERROR");
                            t.printStackTrace();
                        }
                    }
                }
            }
        }

        //don't check our secondary if we don't have to
        if (required.isEmpty()) return true;
        if (required.size() < input.size()) //System.out.println("Still checking " + required.size() + " items for " + getRecipeOutput().getDisplayName());
        for (int x = 0; x < secondary.size(); x++)
        {
            CountertopRecipe intermediate = secondary.get(x);
            ItemStack slot = intermediate.getCraftingResultList(tempInput);

            if (ST.valid(slot))
            {
                //Iterator<Object> req = required.iterator();

                //while (req.hasNext())
                //{
                for (int k = 0; k < required.size(); k++)
                {
                    boolean match = false;

                    Object next = required.get(k);

                    //Object next = req.next();

                    if (next instanceof ItemStack)
                    {
                        match = OreDictionary.itemMatches((ItemStack)next, slot, false);
                    }
                    else if (next instanceof ArrayList)
                    {
                        Iterator<ItemStack> itr = ((ArrayList<ItemStack>)next).iterator();
                        while (itr.hasNext() && !match)
                        {
                            match = OreDictionary.itemMatches(itr.next(), slot, false);
                        }
                    }

                    if (match)
                    {
                        //System.out.println("Found matching possible recipe! Now comparing inputs");
                        ArrayList<ItemStack> backup = new ArrayList<ItemStack>(tempInput);
                        ArrayList<Object> results = new ArrayList<Object>(intermediate.getInputList());
                        if (intermediate.matchesListsInternal(tempInput, secondary))
                        {
                            required.remove(next);
                            k = k - 1;
                            tempResults.add(slot);
                            matchingTwo[x] = matchingTwo[x] + 1;
                        } else {
                            tempInput = backup;
                        }
                    }
                }
            }
        }
        return required.isEmpty();
    }

    @Override
    public boolean matchesList(List<ItemStack> input) {
        ArrayList<Object> required = new ArrayList<Object>(this.getInput());
        tempResults = new ArrayList<ItemStack>();

        for (int x = 0; x < input.size(); x++)
        {
            ItemStack slot = input.get(x);

            if (slot != null)
            {
                boolean inRecipe = false;
                Iterator<Object> req = required.iterator();

                while (req.hasNext())
                {
                    boolean match = false;

                    Object next = req.next();

                    if (next instanceof ItemStack)
                    {
                        match = OreDictionary.itemMatches((ItemStack)next, slot, false);
                    }
                    else if (next instanceof ArrayList)
                    {
                        Iterator<ItemStack> itr = ((ArrayList<ItemStack>)next).iterator();
                        while (itr.hasNext() && !match)
                        {
                            match = OreDictionary.itemMatches(itr.next(), slot, false);
                        }
                    }

                    if (match)
                    {
                        inRecipe = true;
                        required.remove(next);
                        tempResults.add(slot);
                        break;
                    }
                }
            }
        }

        return required.isEmpty();
    }

    @Override
    public List<Object> getInputList() {
        return this.getInput();
    }
    public Integer[] getInputSlotsUsed() {return this.matchingOne;}
    public Integer[] getRecipeSlotsUsed() {return this.matchingTwo;}
}
