package com.kbi.qwertech.api.entities;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.loaders.RegisterSpecies;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subtype implements Taggable{

    private HashMap<String, Object> extraTags = new HashMap<String, Object>();

    public short[] minLimits = new short[8];
    public short[] maxLimits = new short[8];
    public short[] preferred = new short[8];
    public int primaryColorMin;
    public int primaryColorMax;
    public int secondaryColorMin;
    public int secondaryColorMax;

    private String primaryTexture = "";
    private String secondaryTexture = "";
    private String overlayTexture = "";
    private String commonName = "";
    private boolean isNatural = false;
    private final Species assignedSpecies;
    private ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

    public Subtype(Species species)
    {
        assignedSpecies = species;
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
        return (String)this.getTag(RegisterSpecies.NAME_TRANSLATE);
    }


    public Subtype setSize(int min, int pref, int max)
    {
        minLimits[0] = (short)min;
        preferred[0] = (short)pref;
        maxLimits[0] = (short)max;
        return this;
    }

    public Subtype setStrength(int min, int pref, int max)
    {
        minLimits[1] = (short)min;
        preferred[1] = (short)pref;
        maxLimits[1] = (short)max;
        return this;
    }

    public Subtype setStamina(int min, int pref, int max)
    {
        minLimits[2] = (short)min;
        preferred[2] = (short)pref;
        maxLimits[2] = (short)max;
        return this;
    }

    public Subtype setSmart(int min, int pref, int max)
    {
        minLimits[3] = (short)min;
        preferred[3] = (short)pref;
        maxLimits[3] = (short)max;
        return this;
    }

    public Subtype setSnarl(int min, int pref, int max)
    {
        minLimits[4] = (short)min;
        preferred[4] = (short)pref;
        maxLimits[4] = (short)max;
        return this;
    }

    public Subtype setMutable(int min, int pref, int max)
    {
        minLimits[5] = (short)min;
        preferred[5] = (short)pref;
        maxLimits[5] = (short)max;
        return this;
    }

    public Subtype setFertility(int min, int pref, int max)
    {
        minLimits[6] = (short)min;
        preferred[6] = (short)pref;
        maxLimits[6] = (short)max;
        return this;
    }

    public Subtype setMaturity(int min, int pref, int max)
    {
        minLimits[7] = (short)min;
        preferred[7] = (short)pref;
        maxLimits[7] = (short)max;
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

    public short getPrefSize() { return preferred[0]; }
    public short getPrefStrength() { return preferred[1]; }
    public short getPrefStamina() { return preferred[2]; }
    public short getPrefSmart() { return preferred[3]; }
    public short getPrefSnarl() { return preferred[4]; }
    public short getPrefMutable() { return preferred[5]; }
    public short getPrefFertility() { return preferred[6]; }
    public short getPrefMaturity() { return preferred[7]; }

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

    @Override
    public boolean hasTag(String tag)
    {
        return extraTags.containsKey(tag);
    }

    @Override
    public Subtype addTag(String tag, Object obby)
    {
        extraTags.put(tag, obby);
        return this;
    }

    @Override
    public Object getTag(String tag)
    {
        if (extraTags.containsKey(tag))
        {
            return extraTags.get(tag);
        }
        return null;
    }

    @Override
    public List<String> getTags()
    {
        return new ArrayList<String>(extraTags.keySet());
    }

}
