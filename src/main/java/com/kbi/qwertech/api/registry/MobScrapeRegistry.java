package com.kbi.qwertech.api.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MobScrapeRegistry {
	private static HashMap<String, Class> mobList = new HashMap();
	private static HashMap<Class, String> listMob = new HashMap();
	private static HashMap<String, List<ItemStack>> listGear = new HashMap();
	
	public MobScrapeRegistry()
	{
		registerMobKey(EntityBat.class, "bat");
		registerMobKey(EntityChicken.class, "chicken");
		registerMobKey(EntityCow.class, "cow");
		registerMobKey(EntityHorse.class, "horse");
		registerMobKey(EntityMooshroom.class, "mooshroom");
		registerMobKey(EntityOcelot.class, "ocelot");
		registerMobKey(EntityPig.class, "pig");
		registerMobKey(EntitySheep.class, "sheep");
		registerMobKey(EntitySquid.class, "squid");
		registerMobKey(EntityVillager.class, "villager");
		registerMobKey(EntityWolf.class, "wolf");
		
		registerMobKey(EntityBlaze.class, "blaze");
		registerMobKey(EntityCaveSpider.class, "cave spider");
		registerMobKey(EntityCreeper.class, "creeper");
		registerMobKey(EntityEnderman.class, "enderman");
		registerMobKey(EntityGhast.class, "ghast");
		registerMobKey(EntityIronGolem.class, "iron golem");
		registerMobKey(EntityMagmaCube.class, "magma cube");
		registerMobKey(EntitySilverfish.class, "silverfish");
		registerMobKey(EntitySkeleton.class, "skeleton");
		registerMobKey(EntitySlime.class, "slime");
		registerMobKey(EntitySnowman.class, "snow golem");
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
			listGear.put(key, new ArrayList<ItemStack>());
			return true;
		} catch (Exception e) {
			System.out.println("Unable to add MobScrapeRegistry entry " + key + ": " + e);
		}
		return false;
	}
	
	/**
	 * Registers a new ItemStack to be scraped from the mob.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be held.
	 * @return True if successful.
	 */
	public static boolean registerItem(String key, ItemStack item)
	{
		return registerItem(key, item, 10);
	}
	
	/**
	 * Registers a new ItemStack to be scraped from the mob.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be held.
	 * @param chances Number of chances it will have to be chosen. Default 10.
	 * @return True if successful.
	 */
	public static boolean registerItem(String key, ItemStack item, int chances)
	{
		List<ItemStack> adderble = listGear.get(key);
		if (adderble != null)
		{
			for (int q = 0; q < chances; q++)
			{
				adderble.add(item);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an ItemStack from this mob's registry.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be removed.
	 * @return True if successful.
	 */
	public static boolean removeItem(String key, ItemStack item)
	{
		List<ItemStack> removerble = listGear.get(key);
		if (removerble != null)
		{
			if (removerble.contains(item))
			{
				List<ItemStack> collection = new ArrayList();
				collection.add(item);
				removerble.removeAll(collection);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the list of registered items for this mob.
	 * @param key The registered name of the mob.
	 * @return An ArrayList of all registered items.
	 */
	public List<ItemStack> getItems(String key)
	{
		List<ItemStack> returnable = listGear.get(key);
		if (returnable != null)
		{
			return returnable;
		} else {
			return new ArrayList<ItemStack>();
		}
	}
	
	/**
	 * Returns a random item registered for this mob.
	 * @param key The registered name of the mob.
	 * @return Any random item, or null if none found.
	 */
	public static ItemStack getRandomItem(String key)
	{
		List<ItemStack> returnable = listGear.get(key);
		if (returnable != null)
		{
			if (returnable.size() > 0)
			{
				return returnable.get(new Random().nextInt(returnable.size()));
			}
		}
		return null;
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
