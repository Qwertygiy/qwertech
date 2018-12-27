package com.kbi.qwertech.api.entities;

import com.kbi.qwertech.loaders.RegisterSpecies;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Species implements Taggable {
    public Subtype[] subtypes = new Subtype[Short.MAX_VALUE];
    public HashMap<BiomeGenBase, List<Subtype>> spawnMap = new HashMap<BiomeGenBase, List<Subtype>>();

    private HashMap<String, Object> extraTags = new HashMap<String, Object>();

    public short[] minLimits = new short[8];
    public short[] maxLimits = new short[8];
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
    public String toString() {
        return (String)this.getTag(RegisterSpecies.NAME_TRANSLATE);
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

    @Override
    public boolean hasTag(String tag)
    {
        return extraTags.containsKey(tag);
    }

    @Override
    public Species addTag(String tag, Object obby)
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
