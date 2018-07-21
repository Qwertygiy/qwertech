package com.kbi.qwertech.api.registry;

import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;

import java.util.HashMap;

public class MobSpeciesRegistry {
    private static HashMap<IGeneticMob, Species[]> hash = new HashMap<IGeneticMob, Species[]>();

    public static Species[] getSpeciesList(IGeneticMob mobType)
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

    public static Species getSpecies(IGeneticMob mobType, short ID)
    {
        Species[] checkList = getSpeciesList(mobType);
        if (checkList[ID] != null)
        {
            return checkList[ID];
        } else {
            return checkList[0];
        }
    }

    public static boolean addSpecies(IGeneticMob mobType, short ID, Species species)
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
