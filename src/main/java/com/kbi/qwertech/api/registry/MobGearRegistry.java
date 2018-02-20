package com.kbi.qwertech.api.registry;

import gregapi.data.CS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;

/**
 * The registry of custom items to be given to mobs upon spawning.
 * @author Qwertygiy
 */
public class MobGearRegistry {
	
	private HashMap<String, Class> mobList = new HashMap();
	private HashMap<Class, String> listMob = new HashMap();
	private HashMap<String, List<ItemStack>> listGear = new HashMap();
	private HashMap<String, Float> chances = new HashMap();
	public static MobGearRegistry easy;
	public static MobGearRegistry normal;
	public static MobGearRegistry hard;
	public static MobGearRegistry superHard;
	
	/**
	 * DO NOT CREATE A NEW REGISTRY. Call MobGearRegistry.easy, MobGearRegistry.normal, or MobGearRegistry.hard, or use one of the static helper functions.
	 */
	public MobGearRegistry(int type)
	{
		//create a separate registry for each game difficulty
		switch (type)
		{
			case 0:
				MobGearRegistry.easy = this;
				break;
			case 1:
				MobGearRegistry.normal = this;
				break;
			case 2:
				MobGearRegistry.hard = this;
				break;
			case 3:
				MobGearRegistry.superHard = this;
				break;
			default:
				break;
		}
		
		//register the basic vanilla guys
		this.registerMobKey(EntityZombie.class, "zombie");
		this.registerMobKey(EntitySkeleton.class, "skeleton");
		this.registerMobKey(EntityPigZombie.class, "zombie pigman");
		this.registerMobKey(EntityEnderman.class, "enderman");
	}
	
	/**
	 * Gets the string assigned to this class, if it exists.
	 * @param mob The class of the mob to be registered.
	 * @return The string name of this mob.
	 */
	public <T extends Entity> String getKey(Class<T> mob)
	{
		return listMob.get(mob);
	}
	
	/**
	 * Returns the class assigned to this name, if it exists.
	 * @param key The registered name of the mob.
	 * @return The class of the mob to be registered.
	 */
	public <T extends Entity> Class<T> getMob(String key)
	{
		return mobList.get(key);
	}
	
	/**
	 * Set the chance that this mob will spawn with an item.
	 * @param key The registered name of the mob.
	 * @param chance The chance this mob has an item, from 0.0F to 1.0F.
	 * @return True if successful.
	 */
	public boolean setChances(String key, Float chance)
	{
		if (this.mobList.get(key) != null)
		{
			this.chances.put(key, chance);
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the chances this mob will spawn with an item in this difficulty
	 * @param key The registered name of the mob
	 * @param chance The chance to spawn with an item
	 * @param difficulty 0: easy, 1: normal, 2: hard, 3: superhard
	 * @return true if successful
	 */
	public static boolean setChances(String key, Float chance, int difficulty)
	{
		switch (difficulty)
		{
		case 0:
			return MobGearRegistry.easy.setChances(key, chance);
		case 1:
			return MobGearRegistry.normal.setChances(key, chance);
		case 2:
			return MobGearRegistry.hard.setChances(key, chance);
		case 3:
			return MobGearRegistry.superHard.setChances(key, chance);
		default:
			return MobGearRegistry.easy.setChances(key, chance) && MobGearRegistry.normal.setChances(key, chance) && MobGearRegistry.hard.setChances(key, chance);
		}
	}
	
	/**
	 * Gets the chance that this mob will spawn with an item.
	 * @param key The registered name of the mob.
	 * @return A float between 0.0F and 1.0F.
	 */
	public float getChances(String key)
	{
		if (this.chances.containsKey(key))
		{
			return this.chances.get(key);
		}
		return 0.0F;
	}
	
	/**
	 * Gets the chance that this mob will spawn with an item in this difficulty.
	 * @param key The registered name of the mob.
	 * @param difficulty 0 = easy, 1 = normal, 2 = hard.
	 * @return A float between 0.0F and 1.0F.
	 */
	public static float getChances(String key, int difficulty)
	{
		switch (difficulty)
		{
		case 0:
			return MobGearRegistry.easy.getChances(key);
		case 1:
			return MobGearRegistry.normal.getChances(key);
		case 2:
			return MobGearRegistry.hard.getChances(key);
		case 3:
			return MobGearRegistry.superHard.getChances(key);
		default:
			return MobGearRegistry.normal.getChances(key);
		}
	}
	
	/**
	 * Register a new entity to receive custom gear upon spawn.
	 * @param mob The entity class of the mob being registered.
	 * @param key The name of the mob, for easy reference.
	 * @return True if successful.
	 */
	public <T extends Entity> boolean registerMobKey(Class<T> mob, String key)
	{
		try {
			this.mobList.put(key, mob);
			this.listMob.put(mob, key);
			this.listGear.put(key, new ArrayList<ItemStack>());
			return true;
		} catch (Exception e) {
			System.out.println("Unable to add MobGearRegistry entry " + key + ": " + e);
		}
		return false;
	}
	
	/**
	 * Registers a mob in all any or all difficulties for ease of use
	 * @param mob The entity class of the mob being registered.
	 * @param key The name of the mob, for easy reference.
	 * @param difficulty 0: easy, 1: normal, 2: hard, 3: superhard, 4: all
	 * @return True if successful.
	 */
	public static <T extends Entity> boolean registerMobKey(Class<T> mob, String key, int difficulty)
	{
		try {
			switch (difficulty)
			{
			case 0:
				return MobGearRegistry.easy.registerMobKey(mob, key);
			case 1:
				return MobGearRegistry.normal.registerMobKey(mob, key);
			case 2:
				return MobGearRegistry.hard.registerMobKey(mob, key);
			case 3:
				return MobGearRegistry.superHard.registerMobKey(mob, key);
			default:
				return MobGearRegistry.easy.registerMobKey(mob, key) && MobGearRegistry.normal.registerMobKey(mob, key) && MobGearRegistry.hard.registerMobKey(mob, key);
			}
		} catch (Exception e) {
			System.out.println("Unable to add MobGearRegistry entry " + key + ": " + e);
		}
		return false;
	}
	
	/**
	 * Registers a new ItemStack to be held in the mob's hand.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be held.
	 * @return True if successful.
	 */
	public boolean registerTool(String key, ItemStack item)
	{
		return this.registerTool(key, item, 10);
	}
	
	/**
	 * Registers a new ItemStack to be held in the mob's hand.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be held.
	 * @param chances Number of chances it will have to be chosen. Default 10.
	 * @return True if successful.
	 */
	public boolean registerTool(String key, ItemStack item, int chances)
	{
		List<ItemStack> adderble = this.listGear.get(key);
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
	 * Registers an item in any or all difficulties for ease of use
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be held.
	 * @param chances Number of chances it will have to be chosen. Default 10.
	 * @param difficulty 0 = easy, 1 = normal, 2 = hard, 3 = superhard, 4 = all
	 * @return True if successful.
	 */
	public static boolean registerTool(String key, ItemStack item, int chances, int difficulty)
	{
		switch (difficulty)
		{
		case 0:
			return MobGearRegistry.easy.registerTool(key, item, chances);
		case 1:
			return MobGearRegistry.normal.registerTool(key, item, chances);
		case 2:
			return MobGearRegistry.hard.registerTool(key, item, chances);
		case 3:
			return MobGearRegistry.superHard.registerTool(key, item, chances);
		default:
			return MobGearRegistry.easy.registerTool(key, item, chances) && MobGearRegistry.normal.registerTool(key, item, chances) && MobGearRegistry.hard.registerTool(key, item, chances);
		}
	}
	
	/**
	 * Removes an ItemStack from this mob's registry.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be removed.
	 * @return True if successful.
	 */
	public boolean removeTool(String key, ItemStack item)
	{
		List<ItemStack> removerble = this.listGear.get(key);
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
	 * Removes an ItemStack from any or all difficulties for ease of use.
	 * @param key The registered name of the mob.
	 * @param item The ItemStack to be removed.
	 * @param difficulty 0 = easy, 1 = normal, 2 = hard, 3 = superhard, 4 = all
	 * @return True if successful.
	 */
	public static boolean removeTool(String key, ItemStack item, int difficulty)
	{
		switch (difficulty)
		{
		case 0:
			return MobGearRegistry.easy.removeTool(key, item);
		case 1:
			return MobGearRegistry.normal.removeTool(key, item);
		case 2:
			return MobGearRegistry.hard.removeTool(key, item);
		case 3:
			return MobGearRegistry.superHard.removeTool(key, item);
		default:
			return MobGearRegistry.easy.removeTool(key, item) && MobGearRegistry.normal.removeTool(key, item) && MobGearRegistry.hard.removeTool(key, item);
		}
	}
	
	/**
	 * Gets the list of registered items for this mob.
	 * @param key The registered name of the mob.
	 * @return An ArrayList of all registered items.
	 */
	public List<ItemStack> getTools(String key)
	{
		List<ItemStack> returnable = this.listGear.get(key);
		if (returnable != null)
		{
			return returnable;
		} else {
			return new ArrayList();
		}
	}
	
	/**
	 * Returns a random item registered for this mob.
	 * @param key The registered name of the mob.
	 * @return Any random item, or null if none found.
	 */
	public ItemStack getRandomTool(String key)
	{
		List<ItemStack> returner = this.listGear.get(key);
		if (returner != null)
		{
			if (returner.size() > 0)
			{
				return returner.get(CS.RANDOM.nextInt(returner.size()));
			}
		}
		return null;
	}
	
	/**
	 * Checks to see if the mob has been registered yet.
	 * @param key The registered name of the mob.
	 * @return True if found.
	 */
	public boolean isRegistered(String key)
	{
		if (this.mobList.get(key) != null)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if the mob has been registered yet.
	 * @param mob The class of the mob to be registered.
	 * @return True if found.
	 */
	public <T extends Entity> boolean isRegistered(Class<T> mob)
	{
		if (this.listMob.get(mob) != null)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if the mob has been registered in any or all difficulties for ease of use.
	 * @param key The registered name of the mob.
	 * @param difficulty 0 = easy, 1 = normal, 2 = hard, 3 = superhard, 4 = all
	 * @return True if found.
	 */
	public static boolean isRegistered(String key, int difficulty)
	{
		switch (difficulty)
		{
		case 0:
			return MobGearRegistry.easy.isRegistered(key);
		case 1:
			return MobGearRegistry.normal.isRegistered(key);
		case 2:
			return MobGearRegistry.hard.isRegistered(key);
		case 3:
			return MobGearRegistry.superHard.isRegistered(key);
		default:
			return MobGearRegistry.easy.isRegistered(key) && MobGearRegistry.normal.isRegistered(key) && MobGearRegistry.hard.isRegistered(key);
		}
	}
	
	/**
	 * Checks to see if the mob has been registered in any or all difficulties for ease of use.
	 * @param key The class of the mob to be registered.
	 * @param difficulty 0 = easy, 1 = normal, 2 = hard, 3 = all
	 * @return True if found.
	 */
	public static <T extends Entity> boolean isRegistered(Class<T> key, int difficulty)
	{
		switch (difficulty)
		{
		case 0:
			return MobGearRegistry.easy.isRegistered(key);
		case 1:
			return MobGearRegistry.normal.isRegistered(key);
		case 2:
			return MobGearRegistry.hard.isRegistered(key);
		case 3:
			return MobGearRegistry.superHard.isRegistered(key);
		default:
			return MobGearRegistry.easy.isRegistered(key) && MobGearRegistry.normal.isRegistered(key) && MobGearRegistry.hard.isRegistered(key);
		}
	}
}
