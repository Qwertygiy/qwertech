package com.kbi.qwertech.loaders.mod;

import gregapi.code.ModData;

import java.util.ArrayList;

public abstract class ModLoadBase {

	public static ArrayList<ModLoadBase> registeredLoaders;
	
	
	public ModLoadBase()
	{
		if (this.getMod().mLoaded)
		{
			ModLoadBase.registeredLoaders.add(this);
		} else {
			System.out.println("Not loading compat for missing mod " + this.getMod().mName);
		}
	}
	
	/**
	 * MUST OVERRIDE THIS. VERY IMPORTANT.
	 * @return the mod ID that must be loaded in order for these things to be added
	 */
	public abstract ModData getMod();
	
	public static void runInit()
	{
		registeredLoaders = new ArrayList();
		new ModLoad_ARS();
		new ModLoad_BINNIE_TREE();
		new ModLoad_BoP();
		new ModLoad_BOTA();
		new ModLoad_EBXL();
		new ModLoad_EiO();
		new ModLoad_FR();
		new ModLoad_GC();
		new ModLoad_GT();
		new ModLoad_IC2C();
		new ModLoad_JourneyMap();
		new ModLoad_MoCr();
		new ModLoad_NAT();
		new ModLoad_RC();
		new ModLoad_TC();
		new ModLoad_TE_FOUNDATION();
		new ModLoad_TF();
		new ModLoad_TFC();
		new ModLoad_WTCH();
		for (int q = 0; q < registeredLoaders.size(); q++)
		{
			ModLoadBase thisMLB = registeredLoaders.get(q);
			thisMLB.registerMobGear();
			thisMLB.registerMobScrapes();
			thisMLB.addOreDict();
		}
	}
	
	public static void runPostInit()
	{
		for (int q = 0; q < registeredLoaders.size(); q++)
		{
			ModLoadBase thisMLB = registeredLoaders.get(q);
			thisMLB.tweakRecipes();
		}
	}
	
	/**
	 * Override this to use MobGearRegistry
	 */
	public void registerMobGear() {}

    /**
	 * Override this to use MobScrapeRegistry
	 */
	public void registerMobScrapes() {}

    /**
	 * Override this to remove or replace recipes
	 */
	public void tweakRecipes() {}

    /**
	 * Override this to add OreDictionary names, prefixes, or materials to items.
	 */
	public void addOreDict() {}
}
