package com.kbi.qwertech.api.registry;

import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.fluids.Fluid;

import java.util.HashMap;

public class MobBloodRegistry {
	private static HashMap<String, Class> mobList = new HashMap();
	private static HashMap<Class, String> listMob = new HashMap();
	private static HashMap<String, Fluid> listGear = new HashMap();

	public MobBloodRegistry()
	{
		registerMobKey(EntityBat.class, "bat");
		registerMobKey(EntityChicken.class, "chicken");
		registerMobKey(EntityCow.class, "cow");
		registerMobKey(EntityHorse.class, "horse");
		registerMobKey(EntityMooshroom.class, "mooshroom");
		registerBlood("mooshroom", UT.Fluids.fluid("mushroomsoup"));
		registerMobKey(EntityOcelot.class, "ocelot");
		registerMobKey(EntityPig.class, "pig");
		registerMobKey(EntitySheep.class, "sheep");
		registerMobKey(EntitySquid.class, "squid");
		registerMobKey(EntityVillager.class, "villager");
		registerMobKey(EntityWolf.class, "wolf");
		
		registerMobKey(EntityBlaze.class, "blaze");
		registerBlood("blaze", UT.Fluids.fluid("blaze"));
		registerMobKey(EntityCaveSpider.class, "cave spider");
		registerMobKey(EntityCreeper.class, "creeper");
		registerMobKey(EntityEnderman.class, "enderman");
		registerMobKey(EntityGhast.class, "ghast");
		registerMobKey(EntityIronGolem.class, "iron golem");
		registerBlood("iron golem", UT.Fluids.fluid("lubricant"));
		registerMobKey(EntityMagmaCube.class, "magma cube");
		registerBlood("magma cube", UT.Fluids.fluid("slime"));
		registerMobKey(EntitySilverfish.class, "silverfish");
		registerMobKey(EntitySkeleton.class, "skeleton");
		removeBlood("skeleton");
		registerMobKey(EntitySlime.class, "slime");
		registerBlood("slime", UT.Fluids.fluid("slime"));
		registerMobKey(EntitySnowman.class, "snow golem");
		registerBlood("snow golem", UT.Fluids.fluid("ice"));
		registerMobKey(EntitySpider.class, "spider");
		registerMobKey(EntityWitch.class, "witch");
		registerMobKey(EntityZombie.class, "zombie");
		registerMobKey(EntityPigZombie.class, "zombie pigman");
	}

	/**
	 * Gets the string assigned to this class, if it exists.
	 * @param mob The class of the mob to be registered.
	 * @return The string name of this mob.
	 */
	public static <T extends Entity> String getKey(Class<T> mob)
	{
		return listMob.get(mob);
	}
	
	/**
	 * Returns the class assigned to this name, if it exists.
	 * @param key The registered name of the mob.
	 * @return The class of the mob to be registered.
	 */
	public static <T extends Entity> Class<T> getMob(String key)
	{
		return mobList.get(key);
	}
	
	/**
	 * Register a new entity to receive loot items on scraping.
	 * @param mob The entity class of the mob being registered.
	 * @param key The name of the mob, for easy reference.
	 * @return True if successful.
	 */
	public static <T extends Entity> boolean registerMobKey(Class<T> mob, String key)
	{
		try {
			mobList.put(key, mob);
			listMob.put(mob, key);
			listGear.put(key, UT.Fluids.fluid("blood"));
			return true;
		} catch (Exception e) {
			System.out.println("Unable to add MobBloodRegistry entry " + key + ": " + e);
		}
		return false;
	}

	/**
	 * Registers a new ItemStack to be scraped from the mob.
	 * @param key The registered name of the mob.
	 * @param fluid The fluid to be used as blood.
	 * @return True if successful.
	 */
	public static boolean registerBlood(String key, Fluid fluid)
	{
		listGear.put(key, fluid);
		return true;
	}
	
	/**
	 * Removes an ItemStack from this mob's registry.
	 * @param key The registered name of the mob.
	 * @return True if successful.
	 */
	public static boolean removeBlood(String key)
	{
		Fluid removerble = listGear.get(key);
		if (removerble != null)
		{
			listGear.put(key, null);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the list of registered items for this mob.
	 * @param key The registered name of the mob.
	 * @return An ArrayList of all registered items.
	 */
	public static Fluid getFluid(String key)
	{
		Fluid returnable = listGear.get(key);
		if (returnable != null)
		{
			return returnable;
		} else {
			return UT.Fluids.fluid("blood");
		}
	}

	/**
	 * Gets the list of registered items for this mob.
	 * @param entityClass the class of the mob.
	 * @return An ArrayList of all registered items.
	 */
	public static <T extends Entity> Fluid getFluid(Class<T> entityClass)
	{
		return getFluid(getKey(entityClass));
	}
	
	/**
	 * Checks to see if the mob has been registered yet.
	 * @param key The registered name of the mob.
	 * @return True if found.
	 */
	public static boolean isRegistered(String key)
	{
        return mobList.get(key) != null;
    }
	
	/**
	 * Checks to see if the mob has been registered yet.
	 * @param mob The class of the mob to be registered.
	 * @return True if found.
	 */
	public static <T extends Entity> boolean isRegistered(Class<T> mob)
	{
        return listMob.get(mob) != null;
    }
}
