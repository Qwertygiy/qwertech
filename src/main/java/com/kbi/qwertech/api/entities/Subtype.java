package com.kbi.qwertech.api.entities;

import com.kbi.qwertech.api.data.COLOR;

public class Subtype {
    public short[] minLimits = new short[8];
    public short[] maxLimits = new short[8];
    public String[] sounds = new String[5];
    public int primaryColorMin;
    public int primaryColorMax;
    public int secondaryColorMin;
    public int secondaryColorMax;

    private String primaryTexture;
    private String secondaryTexture;
    private String commonName;

    @Override
    public String toString() {
        return getCommonName();
    }

    public String getCommonName()
    {
        return commonName;
    }

    public Subtype setCommonName(String name)
    {
        commonName = name;
        return this;
    }

    public String getLivingSound()
    {
        return sounds[0];
    }

    public Subtype setLivingSound(String sound)
    {
        sounds[0] = sound;
        return this;
    }

    public String getHurtSound()
    {
        return sounds[1];
    }

    public Subtype setHurtSound(String sound)
    {
        sounds[1] = sound;
        return this;
    }

    public String getDeathSound()
    {
        return sounds[2];
    }

    public Subtype setDeathSound(String sound)
    {
        sounds[2] = sound;
        return this;
    }

    public String getAngrySound()
    {
        return sounds[3];
    }

    public Subtype setAngrySound(String sound)
    {
        sounds[3] = sound;
        return this;
    }

    public String getHungrySound()
    {
        return sounds[4];
    }

    public Subtype setHungrySound(String sound)
    {
        sounds[4] = sound;
        return this;
    }

    public Subtype setPrimaryColor(String color)
    {
        if (COLOR.colorDictionary.containsKey(color))
        {
            int colorValue = COLOR.colorDictionary.get(color);
            int min = Math.max((colorValue >> 16 & 255) - 20, 0) << 16;
            min = min + Math.max((colorValue >> 8 & 255) - 20, 0) << 8;
            min = min + Math.max((colorValue & 255) - 20, 0);

            int max = Math.min((colorValue >> 16 & 255) + 20, 255) << 16;
            max = max + Math.min((colorValue >> 8 & 255) + 20, 255) << 8;
            max = max + Math.min((colorValue & 255) + 20, 255);
            setPrimaryColors(min, max);
        }
        return this;
    }

    public Subtype setPrimaryColors(int color1, int color2)
    {
        primaryColorMin = COLOR.getMin(color1, color2);
        primaryColorMax = COLOR.getMax(color1, color2);
        return this;
    }

    public Subtype setPrimaryColors(String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setPrimaryColors(COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        }
        return this;
    }

    public Subtype setSecondaryColor(String color)
    {
        if (COLOR.colorDictionary.containsKey(color))
        {
            int colorValue = COLOR.colorDictionary.get(color);
            int min = Math.max((colorValue >> 16 & 255) - 20, 0) << 16;
            min = min + Math.max((colorValue >> 8 & 255) - 20, 0) << 8;
            min = min + Math.max((colorValue & 255) - 20, 0);

            int max = Math.min((colorValue >> 16 & 255) + 20, 255) << 16;
            max = max + Math.min((colorValue >> 8 & 255) + 20, 255) << 8;
            max = max + Math.min((colorValue & 255) + 20, 255);
            setSecondaryColors(min, max);
        }
        return this;
    }

    public Subtype setSecondaryColors(int color1, int color2)
    {
        secondaryColorMin = COLOR.getMin(color1, color2);
        secondaryColorMax = COLOR.getMax(color1, color2);
        return this;
    }

    public Subtype setSecondaryColors(String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setSecondaryColors(COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        }
        return this;
    }

    public String getPrimaryTexture()
    {
        return primaryTexture;
    }

    public Subtype setPrimaryTexture(String pT)
    {
        primaryTexture = pT;
        return this;
    }

    public String getSecondaryTexture()
    {
        return secondaryTexture;
    }

    public Subtype setSecondaryTexture(String sT)
    {
        secondaryTexture = sT;
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

    public short getMinPrimaryColor()
    {
        return minLimits[8];
    }

    public short getMinSecondaryColor()
    {
        return minLimits[9];
    }

    public Subtype setMinSize(short size)
    {
        minLimits[0] = size;
        return this;
    }

    public Subtype setMinStrength(short strength)
    {
        minLimits[1] = strength;
        return this;
    }

    public Subtype setMinStamina(short stamina)
    {
        minLimits[2] = stamina;
        return this;
    }

    public Subtype setMinSmart(short smart)
    {
        minLimits[3] = smart;
        return this;
    }

    public Subtype setMinSnarl(short snarl)
    {
        minLimits[4] = snarl;
        return this;
    }

    public Subtype setMinMutable(short mutable)
    {
        minLimits[5] = mutable;
        return this;
    }

    public Subtype setMinFertility(short fertility)
    {
        minLimits[6] = fertility;
        return this;
    }

    public Subtype setMinMaturity(short maturity)
    {
        minLimits[7] = maturity;
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

    public short getMaxPrimaryColor()
    {
        return maxLimits[8];
    }

    public short getMaxSecondaryColor()
    {
        return maxLimits[9];
    }

    public Subtype setMaxSize(short size)
    {
        maxLimits[0] = size;
        return this;
    }

    public Subtype setMaxStrength(short strength)
    {
        maxLimits[1] = strength;
        return this;
    }

    public Subtype setMaxStamina(short stamina)
    {
        maxLimits[2] = stamina;
        return this;
    }

    public Subtype setMaxSmart(short smart)
    {
        maxLimits[3] = smart;
        return this;
    }

    public Subtype setMaxSnarl(short snarl)
    {
        maxLimits[4] = snarl;
        return this;
    }

    public Subtype setMaxMutable(short mutable)
    {
        maxLimits[5] = mutable;
        return this;
    }

    public Subtype setMaxFertility(short fertility)
    {
        maxLimits[6] = fertility;
        return this;
    }

    public Subtype setMaxMaturity(short maturity)
    {
        maxLimits[7] = maturity;
        return this;
    }
}
