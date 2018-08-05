package com.kbi.qwertech.api.entities;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.List;

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
    private String overlayTexture;
    private String commonName;
    private boolean isNatural;
    private final Species assignedSpecies;
    private ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

    public Subtype(Species species)
    {
        assignedSpecies = species;
    }

    private Object model;

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBase getModel()
    {
        if (model != null) {
            return (net.minecraft.client.model.ModelBase) model;
        }
        return assignedSpecies.getModel();
    }

    @SideOnly(Side.CLIENT)
    public Subtype setModel(net.minecraft.client.model.ModelBase modelType)
    {
        model = modelType;
        return this;
    }

    public Subtype addBiome(BiomeGenBase biome)
    {
        if (!biomes.contains(biome))
        {
            biomes.add(biome);
            List<Subtype> thisBiome = assignedSpecies.spawnMap.get(biome);
            if (thisBiome == null || thisBiome.size() < 1)
            {
                thisBiome = new ArrayList<Subtype>();
            }
            thisBiome.add(this);
            assignedSpecies.spawnMap.put(biome, thisBiome);
            MobSpeciesRegistry.addBiomeForSpecies(assignedSpecies.getMobType(), assignedSpecies, biome);
        }
        return this;
    }

    public Subtype addBiome(BiomeDictionary.Type biomeType)
    {
        BiomeGenBase[] results = BiomeDictionary.getBiomesForType(biomeType);
        for (int q = 0; q < results.length; q++)
        {
            if (!biomes.contains(results[q]))
            {
                biomes.add(results[q]);
                List<Subtype> thisBiome = assignedSpecies.spawnMap.get(results[q]);
                if (thisBiome == null || thisBiome.size() < 1)
                {
                    thisBiome = new ArrayList<Subtype>();
                }
                thisBiome.add(this);
                assignedSpecies.spawnMap.put(results[q], thisBiome);
                MobSpeciesRegistry.addBiomeForSpecies(assignedSpecies.getMobType(), assignedSpecies, results[q]);
            }
        }
        return this;
    }

    public Subtype removeBiome(BiomeGenBase biome)
    {
        if (biomes.contains(biome))
        {
            biomes.remove(biome);
            List<Subtype> thisBiome = assignedSpecies.spawnMap.get(biome);
            if (thisBiome.contains(this))
            {
                thisBiome.remove(this);
            }
            assignedSpecies.spawnMap.put(biome, thisBiome);
        }
        return this;
    }

    public boolean getCanSpawn(BiomeGenBase biome)
    {
        return getNatural() && biomes.contains(biome);
    }

    public Subtype setNatural(boolean isNatural)
    {
        this.isNatural = isNatural;
        return this;
    }

    public boolean getNatural()
    {
        return isNatural;
    }

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

    public Subtype setPrimaryColors(int color1, int color2)
    {
        primaryColorMin = color1;
        primaryColorMax = color2;
        return this;
    }

    public Subtype setPrimaryColors(String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setPrimaryColors(COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        } else {
            System.out.println("Could not find both " + color1 + " and " + color2);
        }
        return this;
    }

    public Subtype setSecondaryColor(String color)
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

    public Subtype setSecondaryColors(int color1, int color2)
    {
        secondaryColorMin = color1;
        secondaryColorMax = color2;
        return this;
    }

    public Subtype setSecondaryColors(String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setSecondaryColors(COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        } else {
            System.out.println("Could not find both " + color1 + " and " + color2 + " in the color database");
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

    public String getOverlayTexture() { return overlayTexture;}

    public Subtype setOverlayTexture(String oT)
    {
        overlayTexture = oT;
        return this;
    }

    public Subtype setTexturePath(String tP)
    {
        primaryTexture = tP + "_primary.png";
        secondaryTexture = tP + "_secondary.png";
        overlayTexture = tP + "_overlay.png";
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

    public int getMinPrimaryColor()
    {
        return primaryColorMin;
    }

    public int getMinSecondaryColor()
    {
        return secondaryColorMin;
    }

    public Subtype setMinSize(int size)
    {
        minLimits[0] = (short)size;
        return this;
    }

    public Subtype setMinStrength(int strength)
    {
        minLimits[1] = (short)strength;
        return this;
    }

    public Subtype setMinStamina(int stamina)
    {
        minLimits[2] = (short)stamina;
        return this;
    }

    public Subtype setMinSmart(int smart)
    {
        minLimits[3] = (short)smart;
        return this;
    }

    public Subtype setMinSnarl(int snarl)
    {
        minLimits[4] = (short)snarl;
        return this;
    }

    public Subtype setMinMutable(int mutable)
    {
        minLimits[5] = (short)mutable;
        return this;
    }

    public Subtype setMinFertility(int fertility)
    {
        minLimits[6] = (short)fertility;
        return this;
    }

    public Subtype setMinMaturity(int maturity)
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

    public int getMaxPrimaryColor()
    {
        return primaryColorMax;
    }

    public int getMaxSecondaryColor()
    {
        return secondaryColorMax;
    }

    public Subtype setMaxSize(int size)
    {
        maxLimits[0] = (short)size;
        return this;
    }

    public Subtype setMaxStrength(int strength)
    {
        maxLimits[1] = (short)strength;
        return this;
    }

    public Subtype setMaxStamina(int stamina)
    {
        maxLimits[2] = (short)stamina;
        return this;
    }

    public Subtype setMaxSmart(int smart)
    {
        maxLimits[3] = (short)smart;
        return this;
    }

    public Subtype setMaxSnarl(int snarl)
    {
        maxLimits[4] = (short)snarl;
        return this;
    }

    public Subtype setMaxMutable(int mutable)
    {
        maxLimits[5] = (short)mutable;
        return this;
    }

    public Subtype setMaxFertility(int fertility)
    {
        maxLimits[6] = (short)fertility;
        return this;
    }

    public Subtype setMaxMaturity(int maturity)
    {
        maxLimits[7] = (short)maturity;
        return this;
    }
}
