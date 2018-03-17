package com.kbi.qwertech.api.armor.upgrades;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IThaumcraftUpgrade {
    /**
     * If the upgrade should provide a vis discount like certain ThaumCraft armor.
     * @param var1 The armor stack.
     * @param var2 The player.
     * @param var3 The aspect that is being checked for a discount.
     * @return The percent(?) discounted.
     */
    int getVisDiscount(ItemStack var1, EntityPlayer var2, Object var3);

    /**
     * Whether the upgrade should unveil the aspects that make up in-world items.
     * @param var1 The armor stack.
     * @param var2 The player wearing the armor.
     * @return True or false.
     */
    boolean showIngamePopups(ItemStack var1, EntityLivingBase var2);

    /**
     * Whether the upgrade should make nodes much more visible to the wearer.
     * @param var1 The armor stack.
     * @param var2 The player wearing the armor.
     * @return True or false.
     */
    boolean showNodes(ItemStack var1, EntityLivingBase var2);
}
