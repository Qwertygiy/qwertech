package com.kbi.qwertech.loaders;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.registry.MobBloodRegistry;
import com.kbi.qwertech.api.registry.MobBreedRegistry;
import com.kbi.qwertech.api.registry.MobGearRegistry;
import com.kbi.qwertech.api.registry.MobScrapeRegistry;
import com.kbi.qwertech.entities.ai.*;
import com.kbi.qwertech.entities.genetic.EntityPhasianidae;
import com.kbi.qwertech.entities.neutral.EntityTurkey;
import com.kbi.qwertech.entities.passive.EntityFrog;
import com.kbi.qwertech.entities.projectile.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import gregapi.data.*;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterMobs {
	
	static MultiItemTool GT_Tool = CS.ToolsGT.sMetaTool;
	static MultiItemTool QT_Tool;
	static ArrayList<OreDictMaterial> metals = new ArrayList();
	static ArrayList<OreDictMaterial> rocks = new ArrayList();
	private static Random rand = new Random();
	
	public static RegisterMobs instance;

	/**
	 * initiate and implement the mob registries
	 */
	public RegisterMobs()
	{
		instance = this;
		if (QTConfigs.enableTools)
		{
			QT_Tool = (MultiItemTool)QTI.qwerTool.getItem();
		}
		new MobGearRegistry(0);
		new MobGearRegistry(1);
		new MobGearRegistry(2);
		new MobGearRegistry(3);
		
		new MobScrapeRegistry();
		new MobBloodRegistry();
		
		addMobScrapes();
		
		registerLures();
		
		addToolMaterials();
		addZombieTools();
		addSkeletonTools();
		registerEntities();
		
		FMLCommonHandler.instance().bus().register(this);
	}
	
	/**
	 * register QwerTech's entities
	 */
	private static void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityRock.class, "thrownRock", 1, QwerTech.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityFoil.class, "thrownFoil", 2, QwerTech.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityBall.class, "thrownBall", 3, QwerTech.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityShuriken.class, "thrownShuriken", 4, QwerTech.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityEgg.class, "thrownEgg", 6, QwerTech.instance, 64, 10, true);
		
		if (QTConfigs.enableTurkeys)
		{
			EntityRegistry.registerModEntity(EntityTurkey.class, "turkey", 5, QwerTech.instance, 80, 3, true);
			EntityRegistry.addSpawn(EntityTurkey.class, 10, 4, 4, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
		}
		
		if (QTConfigs.enableFrogs)
		{
			EntityRegistry.registerModEntity(EntityFrog.class, "frog", 7, QwerTech.instance, 80, 3, true);
			EntityRegistry.addSpawn(EntityFrog.class, 10, 2, 6, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP));
			EntityRegistry.addSpawn(EntityFrog.class, 10, 2, 4, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.WET));
			EntityRegistry.addSpawn(EntityFrog.class, 10, 2, 4, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.LUSH));
		}

		/*begin genetic mobs*/
		EntityRegistry.registerModEntity(EntityPhasianidae.class, "phasianidae", 10, QwerTech.instance, 120, 3, true);
		EntityRegistry.addSpawn(EntityPhasianidae.class, 12, 2, 6, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE));
		EntityRegistry.addSpawn(EntityPhasianidae.class, 12, 2, 6, EnumCreatureType.creature, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
		RegisterSpecies.begin();
	}
	
	private static void registerLures()
	{
		MobBreedRegistry.addOreDict(Items.wheat, "cropWheat");
		MobBreedRegistry.addOreDict(Items.wheat, "itemGrass");
		MobBreedRegistry.addOreDict(Items.wheat, "itemGrassDry");
		MobBreedRegistry.addOreDict(Items.wheat, "itemGrassMoldy");
		MobBreedRegistry.addOreDict(Items.wheat, "itemGrassRotten");
		MobBreedRegistry.addOreDict(Items.wheat, "cropBarley");
		MobBreedRegistry.addOreDict(Items.wheat, "cropRye");
		MobBreedRegistry.addOreDict(Items.wheat, "cropOats");
		MobBreedRegistry.addOreDict(Items.wheat, "cropRice");
		MobBreedRegistry.addOreDict(Items.wheat,  "listAllgrain");
		MobBreedRegistry.addOreDict(Items.wheat_seeds, "listAllseed");
		MobBreedRegistry.addOreDict(Items.fish, "listAllfishraw");
		MobBreedRegistry.addOreDict(Items.fish, "listAllfishcooked");
		MobBreedRegistry.addOreDict(Items.carrot, "cropCarrot");
		MobBreedRegistry.addOreDict(Items.carrot, "cropParsnip");
		MobBreedRegistry.addOreDict(Items.porkchop, "listAllmeatraw");
		MobBreedRegistry.addOreDict(Items.porkchop, "listAllmeatcooked");
		MobBreedRegistry.addItemStack(Items.fish, IL.Food_Can_Empty.getWildcard(1, Items.fish));
	}
	
	protected static void enchantEquipment(EntityLiving entity)
    {
        float f = entity.worldObj.func_147462_b(entity.posX, entity.posY, entity.posZ);

        if (entity.getHeldItem() != null && rand.nextFloat() < (0.25F * f))
        {
            EnchantmentHelper.addRandomEnchantment(rand, entity.getHeldItem(), (int)(5.0F + f * rand.nextInt(18)));
        }

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = entity.func_130225_q(i);

            if (itemstack != null && rand.nextFloat() < 0.5F * f)
            {
                EnchantmentHelper.addRandomEnchantment(rand, itemstack, (int)(5.0F + f * rand.nextInt(18)));
            }
        }
    }
	
	/**
	 * give us a random rock or metal to use
	 * @param item the itemstack to check
	 * @param MD the damage of the itemstack for some reason
	 * @return primary, secondary
	 */
	private static OreDictMaterial[] getMaterials(ItemStack item, int MD)
	{
		OreDictMaterial primary;
		OreDictMaterial secondary = MT.Wood;
		switch(MD)
		{
		case 12:
		case 24:
			if (new Random().nextBoolean())
			{
				primary = rocks.get(new Random().nextInt(rocks.size()));
				secondary = MT.Bone;
			} else {
				primary = metals.get(new Random().nextInt(metals.size()));
			}
			break;
		default:
			primary = metals.get(new Random().nextInt(metals.size()));
		}
		return new OreDictMaterial[]{primary, secondary};
	}
	
	/**
	 * register certain materials to be zombie-held
	 */
	private static void addToolMaterials()
	{
		for (int q = 0; q < 10; q++)
		{
			metals.add(MT.Fe);
			metals.add(MT.Bronze);
			metals.add(MT.Steel);
			metals.add(MT.Pb);
			metals.add(MT.Ag);
			metals.add(MT.Invar);
			metals.add(MT.BismuthBronze);
			metals.add(MT.CertusQuartz);
			metals.add(MT.Obsidian);
			if (MD.MET.mLoaded)
			{
				metals.add(MT.DeepIron);
				metals.add(MT.ShadowIron);
				metals.add(MT.Hepatizon);
			}
			if (MD.EIO.mLoaded || MD.TG.mLoaded)
			{
				metals.add(MT.ObsidianSteel);
			}
			if (MD.Mek.mLoaded)
			{
				metals.add(MT.FakeOsmium);
			}
			if (MD.TF.mLoaded)
			{
				metals.add(MT.IronWood);
			}
			
			rocks.add(MT.Stone);
			rocks.add(MT.Stone);
			rocks.add(MT.Stone);
			rocks.add(MT.Stone);
			rocks.add(MT.Diorite);
			rocks.add(MT.GraniteBlack);
			rocks.add(MT.GraniteRed);
			rocks.add(MT.Limestone);
			rocks.add(MT.Andesite);
			rocks.add(MT.Basalt);
			rocks.add(MT.Marble);
			rocks.add(MT.Netherrack);
		}
		metals.add(MT.Au);
		metals.add(MT.DamascusSteel);
		metals.add(MT.Ruby);
		metals.add(MT.Sapphire);
		metals.add(MT.Emerald);
		metals.add(MT.GreenSapphire);
		metals.add(MT.Craponite);
		metals.add(MT.Al);
		metals.add(MT.TungstenSteel);
		metals.add(MT.BlackSteel);
		metals.add(MT.BlackBronze);
		metals.add(MT.RedSteel);
		metals.add(MT.BlueSteel);
		metals.add(MT.Cu);
		metals.add(MT.Cheese);
		metals.add(MT.MeatRaw);
		metals.add(MT.Chocolate);
		metals.add(MT.Butter);
		if (MD.TC.mLoaded)
		{
			metals.add(MT.Thaumium);
			metals.add(MT.Amber);
		}
		if (MD.TF.mLoaded)
		{
			metals.add(MT.Knightmetal);
		}
		if (MD.BOTA.mLoaded)
		{
			metals.add(MT.Manasteel);
		}
		if (MD.TiC.mLoaded)
		{
			metals.add(MT.Manyullyn);
		}
		if (MD.DE.mLoaded)
		{
			metals.add(MT.Draconium);
		}
		if (MD.GC_GALAXYSPACE.mLoaded)
		{
			metals.add(MT.Ad);
			rocks.add(MT.MarsRock);
			rocks.add(MT.MoonRock);
			rocks.add(MT.Endstone);
			rocks.add(MT.SpaceRock);
		}
		if (MD.MET.mLoaded)
		{
			metals.add(MT.Sanguinite);
			metals.add(MT.Orichalcum);
			metals.add(MT.Mithril);
			metals.add(MT.Ad);
			metals.add(MT.Atlarus);
		}
	}
	
	private static void addMobScrapes()
	{
		MobScrapeRegistry.registerItem("zombie", OP.scrapGt.mat(MT.MeatRotten, 1), 10);
		MobScrapeRegistry.registerItem("zombie", ST.make(Items.rotten_flesh, 1, 0), 1);
		MobScrapeRegistry.registerItem("zombie", OP.nugget.mat(MT.MeatRotten, 1), 1);
		MobScrapeRegistry.registerItem("skeleton", ST.make(Items.dye, 1, 15), 10);
		MobScrapeRegistry.registerItem("skeleton", OP.scrapGt.mat(MT.Bone, 1), 1);
		MobScrapeRegistry.registerItem("zombie pigman", OP.scrapGt.mat(MT.MeatRotten, 1), 10);
		MobScrapeRegistry.registerItem("zombie pigman", OP.nugget.mat(MT.MeatRotten, 1), 1);
		MobScrapeRegistry.registerItem("zombie pigman", ST.make(Items.gold_nugget, 1, 0), 1);
		MobScrapeRegistry.registerItem("blaze", ST.make(Items.blaze_powder, 1, 0), 10);
		MobScrapeRegistry.registerItem("blaze", OP.dustTiny.mat(MT.S, 1), 1);
		MobScrapeRegistry.registerItem("blaze", OP.dustTiny.mat(MT.S, 2), 1);
		MobScrapeRegistry.registerItem("chicken", ST.make(Items.feather, 1, 0), 10);
		MobScrapeRegistry.registerItem("chicken", ST.make(Items.feather,  2, 0), 3);
		MobScrapeRegistry.registerItem("chicken", OP.scrapGt.mat(MT.MeatRaw, 1), 2);
		MobScrapeRegistry.registerItem("cow", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("pig", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("horse", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("sheep", ST.make(Items.string, 1, 0), 2);
		MobScrapeRegistry.registerItem("sheep", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("mooshroom", ST.make(Blocks.red_mushroom, 1, 0), 1);
		MobScrapeRegistry.registerItem("mooshroom", ST.make(Blocks.brown_mushroom, 1, 0), 1);
		MobScrapeRegistry.registerItem("mooshroom", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("iron golem", OP.scrapGt.mat(MT.Fe, 1), 10);
		MobScrapeRegistry.registerItem("iron golem", OP.chunkGt.mat(MT.Fe, 1), 2);
		MobScrapeRegistry.registerItem("iron golem", OP.dustTiny.mat(MT.Fe, 2), 2);
		MobScrapeRegistry.registerItem("iron golem", ST.make(Blocks.vine, 1, 0), 1);
		MobScrapeRegistry.registerItem("snow golem", ST.make(Items.snowball, 1, 0), 2);
		MobScrapeRegistry.registerItem("snow golem", ST.make(Items.pumpkin_seeds, 1, 0), 1);
		MobScrapeRegistry.registerItem("enderman", OP.dustTiny.mat(MT.EnderPearl, 1), 10);
		MobScrapeRegistry.registerItem("enderman", OP.chunkGt.mat(MT.EnderPearl, 1), 2);
		MobScrapeRegistry.registerItem("enderman", OP.dustSmall.mat(MT.Endstone, 1), 1);
		MobScrapeRegistry.registerItem("spider", ST.make(Items.string, 1, 0));
		MobScrapeRegistry.registerItem("cave spider", ST.make(Items.string, 1, 0));
		MobScrapeRegistry.registerItem("creeper", OP.dustSmall.mat(MT.Gunpowder, 1));
		MobScrapeRegistry.registerItem("ghast", OP.dustSmall.mat(MT.Gunpowder, 1));
	}
	
	/**
	 * go through and add chances and tools
	 */
	private static void addZombiePigmanTools()
	{
		MobGearRegistry.easy.setChances("zombie pigman", 0.75F); //3 in 4
		MobGearRegistry.normal.setChances("zombie pigman", 1F); //all
		MobGearRegistry.hard.setChances("zombie pigman", 1F); //all
		MobGearRegistry.superHard.setChances("zombie pigman", 1F); //all
		
		MobGearRegistry.registerTool("zombie pigman", new ItemStack(Items.golden_sword), 50, 4);
		
		MobGearRegistry.registerTool("zombie pigman", GT_Tool.make(36), 1,  1); //butchery knife
		
		MobGearRegistry.registerTool("zombie pigman", GT_Tool.make(36), 5,  2); //butchery knife
	}
	
	/**
	 * go through and add chances and tools
	 */
	private static void addSkeletonTools()
	{
		MobGearRegistry.easy.setChances("skeleton", 0.75F); //3 in 4
		MobGearRegistry.normal.setChances("skeleton", 1F); //all
		MobGearRegistry.hard.setChances("skeleton", 1F); //all
		MobGearRegistry.superHard.setChances("skeleton", 1F); //all
		
		MobGearRegistry.registerTool("skeleton", new ItemStack(Items.bow), 50, 4); //bow
		MobGearRegistry.registerTool("skeleton", GT_Tool.make(40), 4, 4); //scythe/sense
		
		if (QTConfigs.enableTools)
		{
			MobGearRegistry.registerTool("skeleton", QT_Tool.make(6), 1, 2); //mace
		}
		ItemStack powerBow = new ItemStack(Items.bow);
		powerBow.addEnchantment(Enchantment.power, 2);
		
		MobGearRegistry.registerTool("skeleton", powerBow, 10, 3);
		MobGearRegistry.registerTool("skeleton", GT_Tool.getToolWithStats(58, MT.BlueSteel, MT.BlueSteel.mHandleMaterial), 5, 3);
		MobGearRegistry.registerTool("skeleton", GT_Tool.getToolWithStats(40, MT.Adamantite, MT.Adamantite.mHandleMaterial), 1, 3);
	}
	
	/**
	 * go through and add chances and tools
	 */
	private static void addZombieTools()
	{
		MobGearRegistry.easy.setChances("zombie", 0.05F); //1 in 20
		MobGearRegistry.normal.setChances("zombie", 0.075F); //1 in 13
		MobGearRegistry.hard.setChances("zombie", 0.125F); //1 in 8
		MobGearRegistry.superHard.setChances("zombie", 0.2F); //1 in 4
		
		MobGearRegistry.registerTool("zombie", GT_Tool.make(24), 10, 4); //club
		MobGearRegistry.registerTool("zombie", GT_Tool.make(12), 10, 4); //hammer
		MobGearRegistry.registerTool("zombie", GT_Tool.make(40), 10, 4); //scythe/sense
		MobGearRegistry.registerTool("zombie", GT_Tool.make(34), 2,  4); //knife
		
		MobGearRegistry.registerTool("zombie", new ItemStack(Items.bone), 1, 4);
		MobGearRegistry.registerTool("zombie", new ItemStack(Items.brick), 1, 4);
		MobGearRegistry.registerTool("zombie", new ItemStack(Blocks.dirt), 1, 4);
		MobGearRegistry.registerTool("zombie", new ItemStack(Items.compass), 1, 4);
		
		if (QTConfigs.enableTools)
		{
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(14, MT.Stone, MT.Wood), 1, 4); //stone axe
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(14, MT.Bronze, MT.Wood), 1, 4); //bronze axe
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(14, MT.Fe, MT.Wood), 1, 4); //iron axe
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(10, MT.Wood, MT.Wood), 2, 4); //wood stake
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(10, MT.Bone, MT.Bone), 2, 4); //bone stake
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(12, MT.Wood, MT.Empty), 2, 4); //wood bat
			MobGearRegistry.registerTool("zombie", QT_Tool.make(14), 10, 1); //axe
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(10, MT.Fe, MT.Fe), 2, 1); //iron stake
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(12, MT.Fe, MT.Empty), 2, 1); //iron bat
			
			MobGearRegistry.registerTool("zombie", QT_Tool.make(14), 10, 2); //axe
			MobGearRegistry.registerTool("zombie", QT_Tool.make(6),  10, 2); //mace
			MobGearRegistry.registerTool("zombie", QT_Tool.make(4),  5,  2); //knuckles
		
			MobGearRegistry.registerTool("zombie", QT_Tool.make(10), 3,  2); //stake
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(12, MT.Steel, MT.Empty), 5, 2); //steel bat
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(12, MT.Steel, MT.Steel), 2, 2); //steel spiked bat
			
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(6, MT.TungstenSteel, MT.TungstenSteel.mHandleMaterial), 5, 3);
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(6, MT.BlueSteel, MT.BlueSteel.mHandleMaterial), 5, 3);
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(6, MT.DamascusSteel, MT.DamascusSteel.mHandleMaterial), 5, 3);
			MobGearRegistry.registerTool("zombie", QT_Tool.make(12), 10, 3); //any spiky bat
			
			MobGearRegistry.registerTool("zombie", QT_Tool.getToolWithStats(12, MT.Wood, MT.Empty), 5, 0); //wood bat
		}
		MobGearRegistry.registerTool("zombie", GT_Tool.make(40), 10, 0); //scythe/sense
		MobGearRegistry.registerTool("zombie", GT_Tool.make(16), 8,  0); //wrench
	
		MobGearRegistry.registerTool("zombie", GT_Tool.make(58), 6,  1); //doubleaxe
		MobGearRegistry.registerTool("zombie", GT_Tool.make(36), 6,  1); //butchery knife
		MobGearRegistry.registerTool("zombie", GT_Tool.make(16), 6,  1); //wrench
		MobGearRegistry.registerTool("zombie", GT_Tool.make(20), 6,  1); //crowbar
		
		MobGearRegistry.registerTool("zombie", GT_Tool.make(58), 10, 2); //doubleaxe
		MobGearRegistry.registerTool("zombie", GT_Tool.make(36), 10, 2); //butchery knife
		
		MobGearRegistry.registerTool("zombie", GT_Tool.make(16), 5,  2); //wrench
		MobGearRegistry.registerTool("zombie", GT_Tool.make(20), 10, 2); //crowbar
		MobGearRegistry.registerTool("zombie", GT_Tool.make(24), 10, 2); //club
		MobGearRegistry.registerTool("zombie", GT_Tool.make(34), 3,  2); //knife
		
		MobGearRegistry.registerTool("zombie", GT_Tool.getToolWithStats(58, MT.TungstenSteel, MT.TungstenSteel.mHandleMaterial), 5, 3);
		MobGearRegistry.registerTool("zombie", GT_Tool.getToolWithStats(58, MT.BlueSteel, MT.BlueSteel.mHandleMaterial), 5, 3);
		MobGearRegistry.registerTool("zombie", GT_Tool.getToolWithStats(58, MT.DamascusSteel, MT.DamascusSteel.mHandleMaterial), 5, 3);
		MobGearRegistry.registerTool("zombie", GT_Tool.getToolWithStats(36, MT.TungstenSteel, MT.TungstenSteel.mHandleMaterial), 5, 3);
		MobGearRegistry.registerTool("zombie", GT_Tool.getToolWithStats(36, MT.BlueSteel, MT.BlueSteel.mHandleMaterial), 5, 3);
		MobGearRegistry.registerTool("zombie", GT_Tool.getToolWithStats(36, MT.DamascusSteel, MT.DamascusSteel.mHandleMaterial), 5, 3);
	}
	
	public static boolean addAI(EntityLiving mob, EntityAIBase ai, int importance)
	{
		return addAI(mob, ai, importance, false, true);
	}
	
	public static boolean addAI(EntityLiving mob, EntityAIBase ai, int importance, boolean isTargeting)
	{
		return addAI(mob, ai, importance, isTargeting, true);
	}
	
	public static boolean addAI(EntityLiving mob, EntityAIBase ai, int importance, boolean isTargeting, boolean checkExists)
	{
		if (checkExists && mob.tasks.taskEntries.size() == 0 && mob.targetTasks.taskEntries.size() == 0)
		{
			return false;
		}
		if (isTargeting)
		{
			mob.targetTasks.addTask(importance, ai);
		} else {
			mob.tasks.addTask(importance, ai);
		}
		return true;
	}
	
	//@SubscribeEvent
	public void onInteracted(EntityInteractEvent event)
	{
		ItemStack bottle = event.entityPlayer.getHeldItem();
		ItemStack newBottle = null;
		if (bottle != null && Item.getIdFromItem(bottle.getItem()) == Item.getIdFromItem(Items.glass_bottle))
		{
			Class entityClass = event.target.getClass();
			if (entityClass.equals(EntityChicken.class))
			{
				newBottle = QTI.jarChicken.get(1);
			} else if (entityClass.equals(EntitySlime.class) && ((EntitySlime)event.target).getSlimeSize() == 1)
			{
				newBottle = QTI.jarSlime.get(1);
			} else if (entityClass.equals(EntityBat.class))
			{
				newBottle = QTI.jarBat.get(1);
			} else if (entityClass.equals(EntityMagmaCube.class) && ((EntityMagmaCube)event.target).getSlimeSize() == 1)
			{
				newBottle = QTI.jarMagmaCube.get(1);
			} else if (entityClass.equals(EntitySilverfish.class))
			{
				newBottle = QTI.jarSilverfish.get(1);
			} else if (entityClass.equals(EntityCaveSpider.class))
			{
				newBottle = QTI.jarCaveSpider.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.passive.EntityTFBunny"))
			{
				newBottle = QTI.jarTFBunny.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.passive.EntityTFBird"))
			{
				newBottle = QTI.jarTFBird.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.EntityTFSwarmSpider"))
			{
				newBottle = QTI.jarTFSpider.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.passive.EntityTFSquirrel"))
			{
				newBottle = QTI.jarTFSquirrel.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.passive.EntityTFRaven"))
			{
				newBottle = QTI.jarTFRaven.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.passive.EntityTFTinyBird"))
			{
				newBottle = QTI.jarTFTinyBird.get(1);
			} else if (entityClass.getName().equals("twilightforest.entity.EntityTFMazeSlime") && ((EntitySlime)event.target).getSlimeSize() == 1)
			{
				newBottle = QTI.jarTFMazeSlime.get(1);
			}
			if (newBottle != null)
			{
				NBTTagCompound taggy = UT.NBT.make();
				event.target.writeToNBT(taggy);
				UT.NBT.set(newBottle, taggy);
				bottle.stackSize = bottle.stackSize - 1;
				if (bottle.stackSize < 1) event.entityPlayer.destroyCurrentEquippedItem();
				event.entityPlayer.inventory.addItemStackToInventory(newBottle);
				event.target.setDead();
				event.setCanceled(true);
			}
		}
	}
	
	//@SubscribeEvent
	public void onDrop(LivingDropsEvent event)
	{
		for (int q = 0; q < event.drops.size(); q++)
		{
			EntityItem drop = event.drops.get(q);
			
			//replace food drops
			ItemStack is = drop.getEntityItem();
			if (is.getItem() == Items.chicken)
			{
				drop.setEntityItemStack(ST.make(QTI.qwerFood.getItem(), 1, 27));
			} else if (is.getItem() == Items.cooked_chicken)
			{
				drop.setEntityItemStack(ST.make(QTI.qwerFood.getItem(), 1, 28));
			}
			is = drop.getEntityItem();
			
			//check if butchered
			if (event.source.getEntity() instanceof EntityLivingBase)
			{
				if (ST.equal(((EntityLivingBase)event.source.getEntity()).getHeldItem(), CS.ToolsGT.sMetaTool.getToolWithStats(36, MT.NULL, MT.NULL), true))
				{
					if (is.getItem() == QTI.qwerFood.getItem())
					{
						switch (is.getItemDamage())
						{
							case 23:
							{
								drop.setEntityItemStack(ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2 + event.lootingLevel), 5));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 11)));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 7)));
								break;
							}
							case 24:
							{
								drop.setEntityItemStack(ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2 + event.lootingLevel), 6));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 12)));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 8)));
								break;
							}
							case 27:
							{
								drop.setEntityItemStack(ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2 + event.lootingLevel), 25));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 9)));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 16)));
								break;
							}
							case 28:
							{
								drop.setEntityItemStack(ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2 + event.lootingLevel), 26));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 10)));
								event.drops.add(new EntityItem(drop.worldObj, drop.posX, drop.posY, drop.posZ, ST.make(QTI.qwerFood.getItem(), 1 + CS.RANDOM.nextInt(2), 17)));
								break;
							}
							default:
								break;
						}
					}
				}
			}
		}
	}
	
	//@SubscribeEvent
	public void specialSpawn(LivingSpawnEvent.SpecialSpawn event)
	{
		this.onMobSpawn(event);
	}
	
	//@SubscribeEvent
	public void checkSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		this.onMobSpawn(event);
	}
	
	//@SubscribeEvent
	public void onAdded(EntityJoinWorldEvent event)
	{
		if (event.entity.getClass() == EntityChicken.class && QTConfigs.enableChickens)
		{
			if (((EntityChicken)event.entity).isChild())
			{
				EntityPhasianidae EP = new EntityPhasianidae(event.world, (short)0, (short)0);
				EP.copyLocationAndAnglesFrom(event.entity);
				EP.setGrowingAge(((EntityChicken)event.entity).getGrowingAge());
				event.entity.setDead();
				event.world.spawnEntityInWorld(EP);
			}
		}
		if (event.entity instanceof EntityLiving && QTConfigs.doMobsUseGear)
		{
			if (((EntityLiving)event.entity).getHeldItem() != null)
			{
				if (event.entity instanceof EntityZombie)
				{
					if (((EntityLiving)event.entity).getHeldItem().getItem().equals(Items.iron_shovel) || ((EntityLiving)event.entity).getHeldItem().getItem().equals(Items.iron_sword))
					{
						//System.out.println("Replacing vanilla zombie item");
						onMobSpawn(new LivingSpawnEvent((EntityLiving)event.entity, event.world, (float)event.entity.posX, (float)event.entity.posY, (float)event.entity.posZ));
					}
				}
			}
		}
		
		if (QTConfigs.addCustomAI)
		{
			if (event.entity instanceof EntityAnimal)
			{
				EntityAnimal ec = (EntityAnimal)event.entity;
				List<EntityAITasks.EntityAITaskEntry> tasks = ec.tasks.taskEntries;
				for (int q = 0; q < tasks.size(); q++)
				{
					EntityAIBase action = tasks.get(q).action;
					if (action instanceof EntityAITempt)
					{
						boolean shy = false;
						if (ec instanceof EntityOcelot) shy = true;
						addAI((EntityLiving)event.entity, new EntityAITemptAdvanced(ec, 1D, shy), tasks.get(q).priority, false, false);
						ec.tasks.removeTask(action);
					}
				}
			}
			if (event.entity instanceof EntityZombie)
			{
				addAI((EntityLiving)event.entity, new EntityAIUseCompass((EntityCreature)event.entity, false, false), 1, true, false);
				addAI((EntityLiving)event.entity, new EntityAISwing((EntityCreature)event.entity), 5, false, false);
			} else if (event.entity instanceof EntitySkeleton)
			{
				addAI((EntityLiving)event.entity, new EntityAISwing((EntityCreature)event.entity), 5, false, false);
			} else if (event.entity instanceof EntityPigZombie)
			{
				addAI((EntityLiving)event.entity, new EntityAISwing((EntityCreature)event.entity), 5, false, false);
			} else if (event.entity instanceof EntityCow && QTConfigs.cowsOverheat)
			{
				addAI((EntityLiving)event.entity, new EntityAITemperatureLimit((EntityLivingBase)event.entity, 1.3F, 1), 10, false, true);
			} else if (event.entity instanceof EntityBlaze || event.entity instanceof EntityMagmaCube)
			{
				addAI((EntityLiving)event.entity, new EntityAITemperatureLimit((EntityLivingBase)event.entity, 0.2F, -1).setDamage(2), 10, false, true);
			} else if (event.entity instanceof EntitySnowman)
			{
				addAI((EntityLiving)event.entity, new EntityAITemperatureLimit((EntityLivingBase)event.entity, 0.5F, 1), 10, false, true);
			} else if (event.entity instanceof EntitySlime)
			{
				addAI((EntityLiving)event.entity, new EntityAIHydrationLimit((EntityLivingBase)event.entity, 0.2F, -1), 10, false, true);
			}
		}
	}
	
    public void onMobSpawn(LivingSpawnEvent event)
    {
		
		float localDif = event.world.func_147462_b(event.x, event.y, event.z);
		//System.out.println("The local difficulty at " + event.x + ", " + event.y + ", " + event.z + " is " + localDif);
		
		MobGearRegistry gearRegistry;
		
		if (localDif <= 0.25)
		{
			gearRegistry = MobGearRegistry.easy;
		} else if (localDif <= 0.5) {
			gearRegistry = MobGearRegistry.normal;
		} else if (localDif <= 1) {
			gearRegistry = MobGearRegistry.hard;
		} else {
			gearRegistry = MobGearRegistry.superHard;
		}
		
    	if (QTConfigs.doMobsUseGear && gearRegistry.isRegistered(event.entity.getClass()))
    	{
    		//System.out.println("Registered a mob spawn for " + event.entity.getClass().getName());
    		String key = gearRegistry.getKey(event.entity.getClass());
    		if (new Random().nextFloat() < gearRegistry.getChances(key))
    		{
    			//System.out.println("Giving a mob a tool!");
    			ItemStack item = gearRegistry.getRandomTool(key);
    			if (item != null)
    			{
    				//System.out.println("The tool is real! It's a " + item.getDisplayName());
	    			if (item.getItem() instanceof MultiItemTool)
	    			{
	    				if (MultiItemTool.getPrimaryMaterial(item) == MT.NULL || MultiItemTool.getPrimaryMaterial(item) == MT.Empty)
	    				{
	    					OreDictMaterial[] mats = getMaterials(item, item.getItemDamage());
	    					item = ((MultiItemTool)item.getItem()).getToolWithStats(item.getItemDamage(), mats[0], mats[1]);
	    				}
	    				((MultiItemTool)item.getItem()).doDamage(item, MultiItemTool.getToolMaxDamage(item) - 1);
	    				NBTTagCompound nbt = UT.NBT.getOrCreate(item);
	    				NBTTagCompound rusty = UT.NBT.make();
	    				rusty.setBoolean("Rusted", true);
	    				nbt.setTag("QT.ToolData", rusty);
	    				UT.NBT.set(item, nbt);
	    				((EntityLiving)event.entity).setEquipmentDropChance(0, 0.5F);
	    			}
	    			event.entity.setCurrentItemOrArmor(0, item);
	    			enchantEquipment((EntityLiving)event.entity);
    			}
    		} else {
    			event.entity.setCurrentItemOrArmor(0, null);
    		}
    	} else {
    		//System.out.println("Could not register a mob spawn for " + event.entity.getClass().getName());
    	}
    	//event.setResult(Result.DEFAULT);
    }
    
    //@SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event)
    {
    	for (int q = 1; q < 5; q++)
    	{
    		ItemStack armorPiece = ((EntityLivingBase)event.entity).getEquipmentInSlot(q);
    		if (armorPiece != null && armorPiece.getItem() instanceof MultiItemArmor)
    		{
    			((MultiItemArmor)armorPiece.getItem()).onArmorHit(event.entity.worldObj, (EntityLivingBase)event.entity, armorPiece, q, event.source, event.ammount, event);
    		}
    	}
    }
}
