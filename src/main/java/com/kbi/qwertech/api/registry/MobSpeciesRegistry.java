package com.kbi.qwertech.api.registry;

import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobSpeciesRegistry {
    private static HashMap<Class<? extends IGeneticMob>, Species[]> hash = new HashMap<Class<? extends IGeneticMob>, Species[]>();
    private static HashMap<Class<? extends IGeneticMob>, HashMap<BiomeGenBase, List<Species>>> biomeHash = new HashMap<Class<? extends IGeneticMob>, HashMap<BiomeGenBase, List<Species>>>();

    public static Species[] getSpeciesList(Class<? extends IGeneticMob> mobType)
    {
        if (hash.containsKey(mobType))
        {
            return hash.get(mobType);
        } else {
            Species[] returnable = new Species[Short.MAX_VALUE];
            hash.put(mobType, returnable);
            return returnable;
        }
    }

    public static List<Species> getSpeciesForBiome(Class<? extends IGeneticMob> mobType, BiomeGenBase biome)
    {
        if (biomeHash.containsKey(mobType))
        {
            HashMap<BiomeGenBase, List<Species>> hm = biomeHash.get(mobType);
            if (hm.containsKey(biome))
            {
                return hm.get(biome);
            }
        }
        return new ArrayList<Species>();
    }

    public static boolean addBiomeForSpecies(Class<? extends IGeneticMob> mobType, Species speciesType, BiomeGenBase biome)
    {
        if (!biomeHash.containsKey(mobType))
        {
            biomeHash.put(mobType, new HashMap<BiomeGenBase, List<Species>>());
        }
        HashMap<BiomeGenBase, List<Species>> hm = biomeHash.get(mobType);
        if (hm.containsKey(biome))
        {
            hm.get(biome).add(speciesType);
            return true;
        } else {
            List<Species> speci = new ArrayList<Species>();
            speci.add(speciesType);
            hm.put(biome, speci);
            return true;
        }
    }

    public static Species getSpecies(Class<? extends IGeneticMob> mobType, short ID)
    {
        Species[] checkList = getSpeciesList(mobType);
        if (checkList[ID] != null)
        {
            return checkList[ID];
        } else {
            return checkList[0];
        }
    }

    public static boolean addSpecies(Class<? extends IGeneticMob> mobType, short ID, Species species)
    {
        Species[] toAdd = getSpeciesList(mobType);
        if (toAdd[ID] == null)
        {
            toAdd[ID] = species;
            return true;
        } else {
            System.out.println("WARNING: ID " + ID + " ALREADY TAKEN, COULD NOT ADD SPECIES " + species);
            return false;
        }
    }
}
