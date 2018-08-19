package com.kbi.qwertech.api.entities;

import com.kbi.qwertech.api.data.COLOR;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.HashMap;
import java.util.List;

public class Species {
    public Subtype[] subtypes = new Subtype[Short.MAX_VALUE];
    public HashMap<BiomeGenBase, List<Subtype>> spawnMap = new HashMap<BiomeGenBase, List<Subtype>>();

    private HashMap<String, Object> extraTags = new HashMap<String, Object>();

    public short[] minLimits = new short[8];
    public short[] maxLimits = new short[8];
    private ItemStack meat;
    private ItemStack secondary;
    private ItemStack rare;
    private String latinName;
    private String commonName;
    public int primaryColorMin;
    public int primaryColorMax;
    public int secondaryColorMin;
    public int secondaryColorMax;
    private Class<? extends IGeneticMob> mobType;

    public Species(Class<? extends IGeneticMob> mob)
    {
        mobType = mob;
    }

    public Class<? extends IGeneticMob> getMobType()
    {
        return mobType;
    }

    public List<Subtype> getSubtypesForBiome(BiomeGenBase biome)
    {
        return spawnMap.get(biome);
    }

    @Override
    public String toString()
    {
        return getCommonName() + " (" + getLatinName() + ")";
    }

    public String getLatinName()
    {
        return latinName;
    }

    public Species setLatinName(String name)
    {
        latinName = name;
        return this;
    }

    public String getCommonName()
    {
        return commonName;
    }

    public Species setCommonName(String name)
    {
        commonName = name;
        return this;
    }

    public Species setSubtype(int ID, Subtype type)
    {
        if (subtypes[ID] == null) {
            subtypes[ID] = type;
        } else {
            System.out.println("WARNING: ID " + ID + " ALREADY TAKEN, COULD NOT ADD SUBTYPE " + type);
        }
        return this;
    }

    public Subtype getSubtype(short ID)
    {
        if (subtypes[ID] != null)
        {
            return subtypes[ID];
        } else {
            return subtypes[0];
        }
    }

    public ItemStack getMeat()
    {
        return meat;
    }

    public Species setMeat(ItemStack meatType)
    {
        meat = meatType;
        return this;
    }

    public ItemStack getSecondary()
    {
        return secondary;
    }

    public Species setSecondary(ItemStack secondaryType)
    {
        secondary = secondaryType;
        return this;
    }

    public ItemStack getRare()
    {
        return rare;
    }

    public Species setRare(ItemStack rareType)
    {
        rare = rareType;
        return this;
    }

    private Object model;

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBase getModel()
    {
        return (net.minecraft.client.model.ModelBase)model;
    }

    @SideOnly(Side.CLIENT)
    public Species setModel(net.minecraft.client.model.ModelBase modelType)
    {
        model = modelType;
        return this;
    }

    public Species setPrimaryColor(String color)
    {
        if (COLOR.colorDictionary.containsKey(color))
        {
            int colorValue = COLOR.colorDictionary.get(color);
            int min = Math.max((colorValue >> 16 & 255) - 20, 0) * (int)Math.pow(2, 16);
            min = min + Math.max((colorValue >> 8 & 255) - 20, 0) * (int)Math.pow(2, 8);
            min = min + Math.max((colorValue & 255) - 20, 0);

            int max = Math.min((colorValue >> 16 & 255) + 20, 255) * (int)Math.pow(2, 16);
            max = max + Math.min((colorValue >> 8 & 255) + 20, 255) * (int)Math.pow(2, 8);
            max = max + Math.min((colorValue & 255) + 20, 255);
            setPrimaryColors(min, max);
        } else {
            System.out.println("Could not find color " + color);
        }
        return this;
    }

    public Species setPrimaryColors(int color1, int color2)
    {
        primaryColorMin = color1;
        primaryColorMax = color2;
        return this;
    }

    public Species setPrimaryColors(String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setPrimaryColors(COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        } else {
            System.out.println("Could not find both colors " + color1 + " and " + color2);
        }
        return this;
    }

    public Species setSecondaryColor(String color)
    {
        if (COLOR.colorDictionary.containsKey(color))
        {
            int colorValue = COLOR.colorDictionary.get(color);
            int min = Math.max((colorValue >> 16 & 255) - 20, 0) * (int)Math.pow(2, 16);
            min = min + Math.max((colorValue >> 8 & 255) - 20, 0) * (int)Math.pow(2, 8);
            min = min + Math.max((colorValue & 255) - 20, 0);

            int max = Math.min((colorValue >> 16 & 255) + 20, 255) * (int)Math.pow(2, 16);
            max = max + Math.min((colorValue >> 8 & 255) + 20, 255) * (int)Math.pow(2, 8);
            max = max + Math.min((colorValue & 255) + 20, 255);
            setSecondaryColors(min, max);
        } else {
            System.out.println("Could not find color " + color);
        }
        return this;
    }

    public Species setSecondaryColors(int color1, int color2)
    {
        secondaryColorMin = color1;
        secondaryColorMax = color2;
        return this;
    }

    public Species setSecondaryColors(String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setSecondaryColors(COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        } else {
            System.out.println("Could not find both " + color1 + " and " + color2 + " in the color database");
        }
        return this;
    }

    public short getMinSize()
    {
        return minLimits[0];
    }

    public short getMinStrength()
    {
        return minLimits[1];
    }

    public short getMinStamina()
    {
        return minLimits[2];
    }

    public short getMinSmart()
    {
        return minLimits[3];
    }

    public short getMinSnarl()
    {
        return minLimits[4];
    }

    public short getMinMutable()
    {
        return minLimits[5];
    }

    public short getMinFertility()
    {
        return minLimits[6];
    }

    public short getMinMaturity()
    {
        return minLimits[7];
    }
    
    public Species setMinSize(int size)
    {
        minLimits[0] = (short)size;
        return this;
    }

    public Species setMinStrength(int strength)
    {
        minLimits[1] = (short)strength;
        return this;
    }

    public Species setMinStamina(int stamina)
    {
        minLimits[2] = (short)stamina;
        return this;
    }

    public Species setMinSmart(int smart)
    {
        minLimits[3] = (short)smart;
        return this;
    }

    public Species setMinSnarl(int snarl)
    {
        minLimits[4] = (short)snarl;
        return this;
    }

    public Species setMinMutable(int mutable)
    {
        minLimits[5] = (short)mutable;
        return this;
    }

    public Species setMinFertility(int fertility)
    {
        minLimits[6] = (short)fertility;
        return this;
    }

    public Species setMinMaturity(int maturity)
    {
        minLimits[7] = (short)maturity;
        return this;
    }

    public short getMaxSize()
    {
        return maxLimits[0];
    }

    public short getMaxStrength()
    {
        return maxLimits[1];
    }

    public short getMaxStamina()
    {
        return maxLimits[2];
    }

    public short getMaxSmart()
    {
        return maxLimits[3];
    }

    public short getMaxSnarl()
    {
        return maxLimits[4];
    }

    public short getMaxMutable()
    {
        return maxLimits[5];
    }

    public short getMaxFertility()
    {
        return maxLimits[6];
    }

    public short getMaxMaturity()
    {
        return maxLimits[7];
    }

    public Species setMaxSize(int size)
    {
        maxLimits[0] = (short)size;
        return this;
    }

    public Species setMaxStrength(int strength)
    {
        maxLimits[1] = (short)strength;
        return this;
    }

    public Species setMaxStamina(int stamina)
    {
        maxLimits[2] = (short)stamina;
        return this;
    }

    public Species setMaxSmart(int smart)
    {
        maxLimits[3] = (short)smart;
        return this;
    }

    public Species setMaxSnarl(int snarl)
    {
        maxLimits[4] = (short)snarl;
        return this;
    }

    public Species setMaxMutable(int mutable)
    {
        maxLimits[5] = (short)mutable;
        return this;
    }

    public Species setMaxFertility(int fertility)
    {
        maxLimits[6] = (short)fertility;
        return this;
    }

    public Species setMaxMaturity(int maturity)
    {
        maxLimits[7] = (short)maturity;
        return this;
    }

    public int getPrimaryColorMin()
    {
        return primaryColorMin;
    }

    public int getPrimaryColorMax()
    {
        return primaryColorMax;
    }

    public int getSecondaryColorMin()
    {
        return secondaryColorMin;
    }

    public int getSecondaryColorMax()
    {
        return secondaryColorMax;
    }

    public boolean hasTag(String tag)
    {
        return extraTags.containsKey(tag);
    }

    public Species addTag(String tag, Object obby)
    {
        extraTags.put(tag, obby);
        return this;
    }

    public Object getTag(String tag)
    {
        if (extraTags.containsKey(tag))
        {
            return extraTags.get(tag);
        }
        return null;
    }
}
