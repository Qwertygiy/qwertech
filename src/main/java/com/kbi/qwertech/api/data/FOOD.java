package com.kbi.qwertech.api.data;

import gregapi.item.multiitem.MultiItemTool;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;

public class FOOD {

    /*
    * QUANTITY AND QUALITY
    *
    * Quality determines how much of a food's base stats are modified based on how good the ingredients are.
    * A low quality food will be less saturating and may have higher fat or sugar content.
    * A high quality food will be more saturating and may have lower fat or sugar content.
    *
    * Quantity determines how the quality of this food will affect any products made with it.
    * A low quantity ingredient, such as a spice, will not greatly impact a dish's quality.
    * A high quantity ingredient, such as meat in a sandwich, will have a large impact on the quality.
    *
    * Both quantity and quality can be anywhere from 0 to 255.
     */

    /*
    * DEFAULT RANGES
    *
    * Quality 0-31: Very Bad (i.e. rotten)
    * Quality 32-63: Bad (i.e. neglected)
    * Quality 64-95: Average (i.e. default)
    * Quality 96-127: Good (i.e. simple care)
    * Quality 128-159: Great (i.e. well-prepared)
    * Quality 160-191: Fabulous (i.e. much effort)
    * Quality 192-223: Extraordinary (i.e. fine dining)
    * Quality 224-255: Unsurpassed (i.e. top notch extreme)
    *
    * Quantity 0-7: Pinch (i.e. seasoning)
    * Quantity 8-15: Dash (i.e. salt)
    * Quantity 16-31: Spoonful (i.e. flavoring)
    * Quantity 32-47: Lump (i.e. sugar)
    * Quantity 48-63: Cupful (i.e. flour)
    * Quantity 64-99: Portion (i.e. vegetable)
    * Quantity 100-124: Pound (i.e. meat)
    * Quantity 125-174: Serving (i.e. dish)
    * Quantity 175-221: Slab (i.e. raw meat)
    * Quantity 222-255: Ton (i.e. for storage)
     */

    public final static int VERY_BAD = 1,
    BAD = 32,
    AVERAGE = 64,
    GOOD = 96,
    GREAT = 128,
    FABULOUS = 160,
    EXTRAORDINARY = 192,
    UNSURPASSED = 224,
    BEST = 255,
    PINCH = 1,
    DASH = 8,
    SPOONFUL = 16,
    LUMP = 32,
    CUPFUL = 48,
    PORTION = 64,
    POUND = 100,
    SERVING = 125,
    SLAB = 175,
    TON = 222;


    private static HashMap<String, Integer> defaultValues = new HashMap<String, Integer>();

    public static void setDefaultQuantity(ItemStack item, int quantity)
    {
        if (!defaultValues.containsKey(item.getUnlocalizedName())) {
            defaultValues.put(item.getUnlocalizedName(), quantity);
        } else {
            //System.out.println("Default quantity already assigned for " + item.getDisplayName());
        }
    }

    public static void setDefaultQuantity(String name, int quantity)
    {
        if (!defaultValues.containsKey(name)) {
            defaultValues.put(name, quantity);
        } else {
            //System.out.println("Default quantity already assigned for " + name);
        }
    }

    public static int getDefaultQuantity(ItemStack item)
    {
        if (defaultValues.containsKey(item.getUnlocalizedName()))
        {
            return defaultValues.get(item.getUnlocalizedName());
        } else {
            //first, let's make sure we're not checking a knife or something.
            Item baseItem = item.getItem();
            if (baseItem instanceof ItemTool || baseItem instanceof MultiItemTool || baseItem.isItemTool(item) || baseItem.isDamageable() || baseItem.hasContainerItem(item)) return 0;
            //System.out.println("No default quantity found for " + item.getDisplayName() + "; defaulting to 50");
        }
        return 50;
    }

    public static int getQuality(ItemStack stack)
    {
        if (ST.valid(stack))
        {
            NBTTagCompound nbt = UT.NBT.getOrCreate(stack);
            NBTTagCompound stats = nbt.getCompoundTag("qt.food");
            return stats.getByte("qual");
        }
        return 0;
    }

    public static int getQuantity(ItemStack stack)
    {
        if (ST.valid(stack))
        {
            NBTTagCompound nbt = UT.NBT.getOrCreate(stack);
            NBTTagCompound stats = nbt.getCompoundTag("qt.food");
            if (stats.hasKey("quant")) {
                return stats.getByte("quant");
            } else {
                return getDefaultQuantity(stack);
            }
        }
        return 0;
    }

    public static ItemStack setQuantity(ItemStack stack, byte quantity)
    {
        try {
            if (ST.valid(stack))
            {
                NBTTagCompound nbt = UT.NBT.getOrCreate(stack);
                NBTTagCompound stats = nbt.getCompoundTag("qt.food");
                stats.setByte("quant", quantity);
                UT.NBT.set(stack, nbt);
            }
        } catch (Exception e) {

        }
        return stack;
    }

    public static ItemStack setQuality(ItemStack stack, byte quality)
    {
        try {
            if (ST.valid(stack))
            {
                NBTTagCompound nbt = UT.NBT.getOrCreate(stack);
                NBTTagCompound stats = nbt.getCompoundTag("qt.food");
                stats.setByte("qual", quality);
                UT.NBT.set(stack, nbt);
            }
        } catch (Exception e) {

        }
        return stack;
    }

}
