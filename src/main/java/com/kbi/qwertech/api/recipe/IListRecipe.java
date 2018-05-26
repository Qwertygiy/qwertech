package com.kbi.qwertech.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import java.util.List;

public interface IListRecipe extends IRecipe {
    ItemStack getCraftingResultList(List<ItemStack> input);

    boolean matchesList(List<ItemStack> input);

    List<Object> getInputList();
}
