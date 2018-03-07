package com.kbi.qwertech;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.data.QTMT;
import com.kbi.qwertech.api.data.WOOD;
import com.kbi.qwertech.api.recipe.AnyQTTool;
import com.kbi.qwertech.api.recipe.WoodSpecificCrafting;
import com.kbi.qwertech.api.recipe.listeners.OreProcessing_NonCrafting;
import com.kbi.qwertech.api.recipe.listeners.OreProcessing_QTTool;
import com.kbi.qwertech.api.recipe.listeners.ShapelessCraftFrom;
import com.kbi.qwertech.api.recipe.managers.CraftingManager3D;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.blocks.BlockCorrugated;
import com.kbi.qwertech.client.QT_GUIHandler;
import com.kbi.qwertech.entities.projectile.EntityShuriken;
import com.kbi.qwertech.items.MultiItemTool_QT;
import com.kbi.qwertech.items.behavior.Dispenser_Shuriken;
import com.kbi.qwertech.items.stats.*;
import com.kbi.qwertech.loaders.*;
import com.kbi.qwertech.loaders.mod.ModLoadBase;
import com.kbi.qwertech.network.packets.PacketInventorySync;
import com.kbi.qwertech.tileentities.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.ItemBlockBase;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ICondition.And;
import gregapi.code.ModData;
import gregapi.config.ConfigCategories;
import gregapi.data.*;
import gregapi.data.CS.ModIDs;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.food.FoodStatDrink;
import gregapi.item.prefixitem.PrefixItem;
import gregapi.network.NetworkHandler;
import gregapi.old.Textures;
import gregapi.oredict.*;
import gregapi.recipes.handlers.RecipeMapHandlerPrefix;
import gregapi.render.IIconContainer;
import gregapi.render.TextureSet;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.loaders.b.Loader_OreProcessing;
import gregtech.tileentity.tools.MultiTileEntityMold;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static gregapi.data.TD.Prefix.*;
import static gregapi.data.TD.Properties.HAS_TOOL_STATS;

@InterfaceList(value = {
		@Interface(iface = "squeek.applecore.api.food.IEdible", modid = ModIDs.APC)
		, @Interface(iface = "ic2.api.item.IItemReactorPlanStorage", modid = ModIDs.IC2C)
		, @Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
		, @Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
		, @Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
		})
@Mod(modid=QwerTech.MODID, name=QwerTech.MODNAME, version=QwerTech.VERSION, dependencies="required-after:gregapi_post; after:gregtech")
public final class QwerTech extends Abstract_Mod {
    public static final String MODID = "qwertech";
    public static final String MODNAME = "QwerTech";
    public static final String VERSION = "${version}";
    public static ModData MOD_DATA = new ModData(MODID, MODNAME);
    public static QwerTech instance;
    
	@SidedProxy(modId = MODID, clientSide = "com.kbi.qwertech.ClientProxy", serverSide = "com.kbi.qwertech.ServerProxy")
    public static CommonProxy PROXY;
	
	// Do not change these 7 Functions. Just keep them this way.
	@Mod.EventHandler public final void onPreLoad			(FMLPreInitializationEvent	aEvent) {onModPreInit(aEvent);}
	@Mod.EventHandler public final void onLoad				(FMLInitializationEvent		aEvent) {onModInit(aEvent);}
	@Mod.EventHandler public final void onPostLoad			(FMLPostInitializationEvent	aEvent) {onModPostInit(aEvent);}
	@Mod.EventHandler public final void onServerStarting	(FMLServerStartingEvent		aEvent) {onModServerStarting(aEvent);}
	@Mod.EventHandler public final void onServerStarted		(FMLServerStartedEvent		aEvent) {onModServerStarted(aEvent);}
	@Mod.EventHandler public final void onServerStopping	(FMLServerStoppingEvent		aEvent) {onModServerStopping(aEvent);}
	@Mod.EventHandler public final void onServerStopped		(FMLServerStoppedEvent		aEvent) {onModServerStopped(aEvent);}
    
	public static HashMap<String, ItemStack> food;
	
	private static MultiItemTool qwerTool;
	private static MultiItemRandom qwerFood;
	public static BlockCorrugated corrugatedBlock;
	//public static ChiselableBlock chiselableBlock;
	public static int knucklesTexID;
	public static int slingshotTexID;
	public static int stringshotTexID;
	public static int javelinHeadTexID;
	public static int stakeTexID;
	public static int batTexID;
	public static int batSpikeTexID;
	public static RegisterAchievements achievementHandler;
	public static int maxChiselTex = 4;
	public static MultiTileEntityRegistry machines;
	public static MultiTileEntityBlock metal;
	public static MultiTileEntityBlock wood;
	public static MultiTileEntityBlock air;

	@Override
	public String getModID() {
		return MODID;
	}

	@Override
	public String getModName() {
		return MODNAME;
	}

	@Override
	public String getModNameForLog() {
		return "Qwertech";
	}

	@Override
	public Abstract_Proxy getProxy() {
		return PROXY;
	}
	
	public void doConfigurations()
	{
		Configuration tMainConfig = new Configuration(new File(CS.DirectoriesGT.CONFIG_GT, "QwerTech.cfg"));
		tMainConfig.load();
		
		QTConfigs.doMobScrapesDrop = tMainConfig.get("entities", "DoMobScrapesDrop", true, "Add bonus loot from certain weapons").setShowInGui(true).getBoolean(true);
		QTConfigs.doMobsUseGear = tMainConfig.get("entities", "DoMobsUseGear", true, "Add more items to be held by naturally-spawning mobs").setShowInGui(true).getBoolean(true);
		QTConfigs.slingshotExplode = tMainConfig.get("entities", "CanThrownRocksExplode", true, "Add the chance for projectiles made from explosive materials to explode on impact").setShowInGui(true).getBoolean(true);
		QTConfigs.slingshotGlass = tMainConfig.get("entities", "CanRocksBreakGlass", true, "Add the ability for thrown rocks and chunks to break glass").setShowInGui(true).getBoolean(true);
		QTConfigs.canScrapePlayers = tMainConfig.get("entities", "CanPlayersBeScraped", true, "Add the chance for players to drop an item when hit by certain weapons").setShowInGui(true).getBoolean(true);
		QTConfigs.addCustomAI = tMainConfig.get("entities", "AddCustomAI", true, "Add custom AI to existing mobs").setShowInGui(true).getBoolean(true);
		QTConfigs.cowsOverheat = tMainConfig.get("entities", "HeatKillsCows", true, "Add temperature limits to cows (deserts, savannahs)").setShowInGui(true).getBoolean(true);
		QTConfigs.addDungeonTools = tMainConfig.get("worldgen", "AddDungeonTools", true, "Add GT and QT tools as rare dungeon items").setShowInGui(true).getBoolean(true);
		QTConfigs.announceFanfare = tMainConfig.get("achievements", "AnnounceFanfare", true, "If achievements annoy you, turn this off").setShowInGui(true).getBoolean(true);
		QTConfigs.removeVanillaCrafting = tMainConfig.get("recipes", "RemoveCrafting", true, "Replace vanilla crafting table with QwerTech crafting tables").setShowInGui(true).getBoolean(true);
		QTConfigs.allHammers = tMainConfig.get("recipes", "AllHammerRecipes", true, "Disable to keep advanced hammer recipes in crafting grids").setShowInGui(true).getBoolean(true);
		QTConfigs.anyHammers = tMainConfig.get("recipes", "PutPlateHammeringBackInCraftingTable", false, "Set to true to disable QwerTech from moving plate-hammering, gem-smashing, and related basic recipes to the crafting anvils").setShowInGui(true).getBoolean(false);

		tMainConfig.save();
		
		Configuration t3DConfig = new Configuration(new File(CS.DirectoriesGT.CONFIG_GT, "3Ditems.cfg"));
		t3DConfig.load();
		
		QTConfigs.add3DGregTools = t3DConfig.get("general", "Add3DGregTools", true, "Add 3D models for default GregTech tools").setShowInGui(true).getBoolean(true);
		QTConfigs.add3DQwerTools = t3DConfig.get("general", "Add3DQwerTools", true, "Add 3D models for new QwerTech tools").setShowInGui(true).getBoolean(true);
		QTConfigs.add3DPrefixes = t3DConfig.get("general", "Add3DPrefixes", true, "Add 3D Models for OreDictPrefix objects (tool heads, ingots, rods, etc.)").setShowInGui(true).getBoolean(true);
		
		t3DConfig.save();
		
		Configuration tCompat = new Configuration(new File(CS.DirectoriesGT.CONFIG_GT, "QTCompat.cfg"));
		tCompat.load();
		
		QTConfigs.overwriteJourneyMap = tCompat.get("journeymap", "OverwriteIcons", true, "Overwrite JourneyMap icons on load").setShowInGui(true).getBoolean(true);
		
		tCompat.save();
		
		Configuration tSections = new Configuration(new File(CS.DirectoriesGT.CONFIG_GT, "QTModules.cfg"));
		tSections.load();
		
		QTConfigs.enableFrogs = tSections.get("entities", "enableFrogs", true, "Allow Frogs to exist").setShowInGui(true).getBoolean(true);
		QTConfigs.enableTurkeys = tSections.get("entities", "enableTurkeys", true, "Allow Turkeys to exist").setShowInGui(true).getBoolean(true);
		
		QTConfigs.enable3DCrafting = tSections.get("crafting", "enable3D", true, "Allow use of the 3x3x3 crafting grid of T4 crafting anvils").setShowInGui(true).getBoolean(true);
		
		QTConfigs.enableTools = tSections.get("tools", "enableTools", true, "Allow the creation of QwerTech tools like maces and mattocks").setShowInGui(true).getBoolean(true);
		
		QTConfigs.enableArmor = tSections.get("armor", "enableArmor", true, "Allow the creation of QwerTech armor").setShowInGui(true).getBoolean(true);
		
		tSections.save();
	}

	@Override
	public void onModPreInit2(FMLPreInitializationEvent aEvent) {
		instance = this;
		
		QTMT.ChemicalX.toString();
		
		this.doConfigurations();
		
		QTI.NW_API = new NetworkHandler(MODID, "QWER", new PacketInventorySync());
		
		OreDictionary.registerOre("ingotCeramic", Items.brick);
		
		RecipeSorter.register("qwertech:tool", AnyQTTool.class, RecipeSorter.Category.SHAPELESS, "after:gregtech:tool");
		RecipeSorter.register("qwertech:wooded", WoodSpecificCrafting.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
		
		new ArmorUpgradeRegistry();
		new RegisterMaterials();
		RegisterItems.run();
		
		new FoodStatDrink(UT.Fluids.create("tomatosauce", "Tomato Sauce", null, 1, 1000L, 300L,  CS.FluidsGT.SIMPLE, CS.FluidsGT.FOOD), "Spaghetti, spaghetti, all over the place", 3, 0.4F, 20.0F, 350.0F, 0.5F, 0, 0, 0, 5, 0, EnumAction.drink, false, false, false, Potion.hunger.id, 100, 1, 20 );
		new FoodStatDrink(UT.Fluids.create("mildsalsa", "Mild Salsa", null, 1, 1000L, 300L, CS.FluidsGT.SIMPLE, CS.FluidsGT.FOOD ), "AKA Chunky Ketchup", 3, 0.4F, 20.0F, 310.0F, 0.3F, 0, 0, 0, 5, 0, EnumAction.drink, false, false, false, Potion.hunger.id, 100, 1, 20 );
		new FoodStatDrink(UT.Fluids.create("salsa", "Medium Salsa", null, 1, 1000L, 300L, CS.FluidsGT.SIMPLE, CS.FluidsGT.FOOD ), "With a little kick", 3, 0.4F, 20.0F, 310.0F, 0.5F, 0, 0, 0, 5, 0, EnumAction.drink, false, false, false, Potion.hunger.id, 100, 1, 20 );
		new FoodStatDrink(UT.Fluids.create("hotsalsa", "Hot Salsa", null, 1, 1000L, 300L, CS.FluidsGT.SIMPLE, CS.FluidsGT.FOOD ), "Vegan fire", 3, 0.4F, 20.0F, 310.0F, 0.7F, 0, 0, 0, 5, 0, EnumAction.drink, false, false, false, Potion.hunger.id, 100, 1, 20 );
		
		corrugatedBlock = new BlockCorrugated(ItemBlockBase.class, "qt.block.corrugated", Material.iron, Block.soundTypeMetal, 16, new IIconContainer[]{new Textures.BlockIcons.CustomIcon("qwertech:wall")});
		LH.add("qt.block.corrugated.0.name", "Corrugated Iron Wall");
		LH.add("qt.block.corrugated.1.name", "Corrugated Aluminium Wall");
		LH.add("qt.block.corrugated.2.name", "Corrugated Gold Wall");
		LH.add("qt.block.corrugated.3.name", "Corrugated Steel Wall");
		LH.add("qt.block.corrugated.4.name", "Corrugated Bronze Wall");
		LH.add("qt.block.corrugated.5.name", "Corrugated Brass Wall");
		LH.add("qt.block.corrugated.6.name", "Corrugated Silver Wall");
		LH.add("qt.block.corrugated.7.name", "Corrugated Stainless Steel Wall");
		LH.add("qt.block.corrugated.8.name", "Corrugated Wrought Iron Wall");
		LH.add("qt.block.corrugated.9.name", "Corrugated Vinyl Wall");
		LH.add("qt.block.corrugated.10.name", "Corrugated Titanium Wall");
		LH.add("qt.block.corrugated.11.name", "Corrugated TungstenSteel Wall");
		LH.add("qt.block.corrugated.12.name", "Corrugated Invar Wall");
		LH.add("qt.block.corrugated.13.name", "Corrugated Tin Alloy Wall");
		LH.add("qt.block.corrugated.14.name", "Corrugated Galvanized Steel Wall");
		LH.add("qt.block.corrugated.15.name", "Corrugated Electrum Wall");
		
		/*final OreDictPrefix chiselBlock = OreDictPrefix.createPrefix("blockCarved");
		chiselBlock.setCategoryName("Chiseled Blocks");
		chiselBlock.setLocalItemName("Chiseled Block of ", "");
		chiselBlock.setCondition(new ICondition.Or.Or(OP.blockIngot, OP.blockSolid, OP.blockGem, OP.blockPlate));
		chiselBlock.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE).setStacksize(64).aspects(TC.LUCRUM, 1, TC.SENSUS, 1);
		chiselBlock.setMaterialStats(gregapi.data.CS.U * 9);*/
		
		//chiselableBlock = new ChiselableBlock(QwerTech.MOD_DATA, "qwertech.blocks.chisel", OP.blockSolid, null, ChiselableBlockItem.class, null, null, Material.iron, Block.soundTypeAnvil, "pickaxe", 1, 1, 0, 0, 3, 0, 0, 0, 1, 1, 1, false, true, false, false, true, true, false, false, true, true, true, true, true, true, OreDictMaterial.MATERIAL_ARRAY);
		//GameRegistry.registerTileEntity(ChiselableTileEntity.class, "qwertech.te.chiselable");
		
		machines = new MultiTileEntityRegistry("qwertech.machines");
		
		metal = MultiTileEntityBlock.getOrCreate(MODID, "iron"		, Material.iron	, Block.soundTypeMetal	, CS.TOOL_pickaxe	, 0, 0, 15, false, false);
		wood = MultiTileEntityBlock.getOrCreate(MODID, 	"wood"		, Material.wood	, Block.soundTypeWood	, CS.TOOL_axe		, 0, 0, 15, false, false);
		air = (MultiTileEntityBlock)MultiTileEntityBlock.getOrCreate(MODID, 	"air", 		Material.plants, Block.soundTypeSnow, 	"", -1, -1, -1, false, false).setTickRandomly(true).setLightOpacity(0);
		
		final OreDictPrefix mattockHead = OreDictPrefix.createPrefix("toolHeadMattock"); // This newly created OreDict Prefix is named "exampleprefix", so an Aluminium Item with this Prefix would be named "exampleprefixAluminium" in the OreDict.
		mattockHead.setCategoryName("Mattock Heads"); // That is what the Creative Tab of it would be named.
		mattockHead.setLocalItemName("", " Mattock Head"); // Generic Items will follow this naming Guideline, so for example "Small Aluminium Example" for an Aluminium Item with that Prefix.
		mattockHead.setCondition(new And(OP.toolHeadHoe, OP.toolHeadAxe)); // The Condition under which Items of this Prefix should generate in general. In this case TRUE to have ALL the Items.
		mattockHead.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE						).setStacksize(16).aspects(TC.INSTRUMENTUM, 2, TC.MESSIS, 1);// Items of this can be recycled for Resources.
		mattockHead.setMaterialStats(gregapi.data.CS.U * 5); // Any Item of this example Prefix has the value of 1 Material Unit (U), this is exactly equal to one Ingot/Dust/Gem.
		
		final OreDictPrefix mattockHeadRaw = OreDictPrefix.createPrefix("toolHeadRawMattock"); // This newly created OreDict Prefix is named "exampleprefix", so an Aluminium Item with this Prefix would be named "exampleprefixAluminium" in the OreDict.
		mattockHeadRaw.setCategoryName("Raw Mattock Heads"); // That is what the Creative Tab of it would be named.
		mattockHeadRaw.setLocalItemName("Raw ", " Mattock Head"); // Generic Items will follow this naming Guideline, so for example "Small Aluminium Example" for an Aluminium Item with that Prefix.
		mattockHeadRaw.setCondition(new And(OP.toolHeadHoe, OP.toolHeadAxe)); // The Condition under which Items of this Prefix should generate in general. In this case TRUE to have ALL the Items.
		mattockHeadRaw.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE, NEEDS_SHARPENING	).setStacksize(16, 8).aspects(TC.INSTRUMENTUM, 2, TC.MESSIS, 1);// Items of this can be recycled for Resources.
		mattockHeadRaw.setMaterialStats(gregapi.data.CS.U * 5); // Any Item of this example Prefix has the value of 1 Material Unit (U), this is exactly equal to one Ingot/Dust/Gem.
		
		final OreDictPrefix maceHead = OreDictPrefix.createPrefix("toolHeadMace");
		maceHead.setCategoryName("Mace Heads");
		maceHead.setLocalItemName("", " Mace Head");
		maceHead.setCondition(new And(HAS_TOOL_STATS, OreDictMaterialCondition.typemin(3)));
		maceHead.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE						).setStacksize(16).aspects(TC.TELUM, 2, TC.PERDITIO, 1);
		maceHead.setMaterialStats(gregapi.data.CS.U * 5);
		
		final OreDictPrefix maceHeadRaw = OreDictPrefix.createPrefix("toolHeadRawMace");
		maceHeadRaw.setCategoryName("Raw Mace Heads");
		maceHeadRaw.setLocalItemName("Raw ", " Mace Head");
		maceHeadRaw.setCondition(new And(HAS_TOOL_STATS, OreDictMaterialCondition.typemin(3)));
		maceHeadRaw.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE, TOOL_HEAD, NEEDS_HANDLE, NEEDS_SHARPENING	).setStacksize(16, 8).aspects(TC.TELUM, 2, TC.PERDITIO, 1);
		maceHeadRaw.setMaterialStats(gregapi.data.CS.U * 5);
		
		final OreDictPrefix shuriken = OreDictPrefix.createPrefix("shuriken");
		shuriken.setCategoryName("Shuriken");
		shuriken.setLocalItemName("", " Shuriken");
		shuriken.setCondition(HAS_TOOL_STATS);
		shuriken.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE).setStacksize(64,16).aspects(TC.TELUM, 2, TC.MOTUS, 1);
		shuriken.setMaterialStats(OP.stick.mAmount * 4);
		
		final OreDictPrefix link = OreDictPrefix.createPrefix("link");
		link.setCategoryName("Links");
		link.setLocalItemName("Link of ", "");
		link.setCondition(OP.ring);
		link.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE).setStacksize(64, 16).aspects(TC.VINCULUM, 1, TC.TUTAMEN, 1);
		link.setMaterialStats(OP.ring.mAmount);
		
		final OreDictPrefix chain = OreDictPrefix.createPrefix("chain");
		chain.setCategoryName("Chains");
		chain.setLocalItemName("Chains of ", "");
		chain.setCondition(OP.ring);
		chain.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE).setStacksize(32, 8).aspects(TC.VINCULUM, 2, TC.MACHINA, 1);
		chain.setMaterialStats(OP.ring.mAmount * 4);
		
		/*final OreDictPrefix stake = OreDictPrefix.createPrefix("stake");
		stake.setCategoryName("Stakes");
		stake.setLocalItemName("", " Stake");
		stake.setCondition(OP.stick);
		stake.add(UNIFICATABLE, BURNABLE, RECYCLABLE, SCANNABLE);
		stake.setMaterialStats(OP.stick.mAmount);*/
		
		
		new PrefixItem(MOD_DATA, "qwertech.tools.mattock", mattockHead);
		new PrefixItem(MOD_DATA, "qwertech.tools.mattockRaw", mattockHeadRaw);
		new PrefixItem(MOD_DATA, "qwertech.tools.mace", maceHead);
		new PrefixItem(MOD_DATA, "qwertech.tools.maceRaw", maceHeadRaw);
		new PrefixItem(MOD_DATA, "qwertech.armor.link", link);
		new PrefixItem(MOD_DATA, "qwertech.armor.chain", chain);
		new PrefixItem(MOD_DATA, "qwertech.tools.shuriken", shuriken) {
			@Override
			public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
			{
				OreDictItemData data = OreDictManager.INSTANCE.getItemData(item);
				UT.Sounds.send(world, "qwertech:metal.slide", 0.5F, (world.rand.nextInt(5) + 8)/10F, (int)player.posX, (int)player.posY, (int)player.posZ);
				if (!world.isRemote)
				{
					EntityShuriken es = new EntityShuriken(world, player, 4F/3F, data.mMaterial.mMaterial);
					world.spawnEntityInWorld(es);
					if (!UT.Entities.hasInfiniteItems(player))
					{
						item.stackSize = item.stackSize - 1;
					}
				}
				return item;
			}
			
			@Override
			public void run()
			{
				super.run();
				BlockDispenser.dispenseBehaviorRegistry.putObject(this, new Dispenser_Shuriken());
			}
			
			@Override 
			public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) 
			{
				aList.add("Ken I toss this throwing star?");
				OreDictMaterial mat = OreDictManager.INSTANCE.getItemData(aStack).mMaterial.mMaterial;
				float tCombat = 2 + ((mat.mToolQuality)/2F);
				aList.add(LH.Chat.WHITE + "Attack Damage: " + LH.Chat.BLUE + "+" + tCombat + LH.Chat.RED + " (= " + ((tCombat+1)/2) + " Hearts)" + LH.Chat.GRAY);
				if (mat == MT.Ag && aPlayer.getDisplayName().toLowerCase().startsWith("bear989"))
				{
					aList.add(LH.Chat.BLINKING_RED + "Be careful with this, Mr. Bear!" + LH.Chat.GRAY);
				} else if (mat == MT.Pb && aPlayer.getDisplayName().equalsIgnoreCase("crazyj1984"))
				{
					aList.add(LH.Chat.BLINKING_RED + "Careful not to cut yourself on this one, lass!"+ LH.Chat.GRAY);
				} else if ((mat == MT.Diamond || mat == MT.Diamantine) && aPlayer.getDisplayName().equalsIgnoreCase("shadowkn1ght18") || aPlayer.getDisplayName().equalsIgnoreCase("netmc"))
				{
					aList.add(LH.Chat.BLINKING_RED + "I wouldn't throw this straight up if I were you..."+ LH.Chat.GRAY);
				} else if (mat == MT.Ti && aPlayer.getDisplayName().equalsIgnoreCase("gregoriust") || aPlayer.getDisplayName().equalsIgnoreCase("speiger"))
				{
					aList.add(LH.Chat.BLINKING_RED + "How is this like a NullPointerException? You'll get mad if it's thrown at you."+ LH.Chat.GRAY);
				} else if (mat == MT.Pt && aPlayer.getDisplayName().equalsIgnoreCase("qwertygiy") || aPlayer.getDisplayName().equalsIgnoreCase("ilrith"))
				{
					aList.add(LH.Chat.BLINKING_RED + "But it's so shiny!"+ LH.Chat.GRAY);
				} else if (mat == MT.Al && aPlayer.getDisplayName().equalsIgnoreCase("andyafw"))
				{
					aList.add(LH.Chat.BLINKING_RED + "Might want to return this one to Wal-Mart.");
				}
			}
		};
		shuriken.addListener(new Loader_OreProcessing.OreProcessing_CraftFrom(4L, null, new String[][] { { "X X", " f ", "X X" }, {" X ", "XfX", " X "} }, OP.stick, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
		//new PrefixBlock(QwerTech.MODID, QwerTech.MODID, "qwertech.blocks.stake", stake, null, null, null, null, net.minecraft.block.material.Material.wood, net.minecraft.block.Block.soundTypeLadder, null, 1.5F, 4.5F,   0,   0, 999, 0, 0, 0, 1, 1, 1, false, false, false, false, false, false, true, true, true, true, false, true, true, true, gregapi.oredict.OreDictMaterial.MATERIAL_ARRAY);
		
		if (QTConfigs.enableTools)
		{
			knucklesTexID = TextureSet.addToAll(QwerTech.MODID, true, "knuckles");
			slingshotTexID = TextureSet.addToAll(QwerTech.MODID, true, "slingshot");
			stringshotTexID = TextureSet.addToAll(QwerTech.MODID, true, "slingstring");
			javelinHeadTexID = TextureSet.addToAll(QwerTech.MODID, true, "javelinHead");
			stakeTexID = TextureSet.addToAll(QwerTech.MODID, true, "stake");
			batTexID = TextureSet.addToAll(QwerTech.MODID, true, "bat");
			batSpikeTexID = TextureSet.addToAll(QwerTech.MODID, true, "batSpike");
			
			qwerTool = new MultiItemTool_QT(MODID, "qwertech.tools");
			QTI.qwerTool.set(qwerTool);
			qwerTool.setFull3D();
			qwerTool.addTool(0, "Mattock", "Tills soil and chops logs", new QT_Tool_Mattock().setMaterialAmount(mattockHead.mAmount), "craftingToolAxe", "craftingToolHoe", TC.stack(TC.INSTRUMENTUM, 2L), TC.stack(TC.METO, 2L), TC.stack(TC.ARBOR, 2L), "toolMattock" );
			mattockHead.addListener(new OreProcessing_QTTool(0, ConfigCategories.Recipes.gregtechtools + "." + "Mattock", true, false, 0L, 0L, null, new String[][]{{ "PPI", "PIh", "fH " }}, new String[][] { { "PPI", "PIh", "f  " } }, null, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			GameRegistry.addRecipe(new AnyQTTool(0L, mattockHead, true));
			qwerTool.addTool(2, "Slingshot", "Rock and roll", new QT_Tool_Slingshot().setMaterialAmount(CS.U3 + (CS.U / 2)),  TC.stack(TC.INSTRUMENTUM, 2L), TC.stack(TC.TELUM, 2L), TC.stack(TC.TERRA, 2L), "toolSlingshot");
			mattockHead.addListener(new OreProcessing_QTTool(2, ConfigCategories.Recipes.gregtechtools + "." + "Slingshot", true, false, 0L, 0L, null, new String[][]{{"XXX", "SfS", " S "}}, null, new ItemStack(Items.string), null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			qwerTool.addTool(4,  "Knuckles", "Hit it!", new QT_Tool_Knuckles().setMaterialAmount(OP.ring.mAmount * 4), TC.stack(TC.TELUM, 1), TC.stack(TC.PERDITIO, 1), "toolKnuckles");
			mattockHead.addListener(new OreProcessing_QTTool(4, ConfigCategories.Recipes.gregtechtools + "." + "Knuckles", false, false, 0L, 0L, MT.Empty, new String[][]{{"OOO", "Oh ", "   "}}, null, null, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			qwerTool.addTool(6, "Mace", "Club with teeth", new QT_Tool_Mace().setMaterialAmount(maceHead.mAmount), TC.stack(TC.TELUM, 3), TC.stack(TC.PERDITIO, 2), "toolMace");
			maceHead.addListener(new OreProcessing_QTTool(6, ConfigCategories.Recipes.gregtechtools + "." + "Mace", true, false, 0L, 0L, null, null, new String[][]{{"DID", "III", "DhD"}, {"DGD", "GGG", "DhD"}}, null, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			GameRegistry.addRecipe(new AnyQTTool(6L, maceHead, true));
			qwerTool.addTool(8, "Spear", "Stabby McStabface", new QT_Tool_Javelin().setMaterialAmount(OP.stickLong.mAmount + OP.toolHeadArrow.mAmount), TC.stack(TC.TELUM, 2), TC.stack(TC.MOTUS, 1), "toolSpear");
			GameRegistry.addRecipe(new AnyQTTool(8L, OP.toolHeadArrow, OP.stickLong, false));
			qwerTool.addTool(10, "Stake", "GIT ME MY POKIN' STICK, MARTHA", new QT_Tool_Stake().setMaterialAmount(OP.stick.mAmount), TC.stack(TC.TELUM, 2), TC.stack(TC.MOTUS, 1), "toolSharpStick");
			OP.stick.addListener(new OreProcessing_QTTool(10, ConfigCategories.Recipes.gregtechtools + "." + "Stake", true, false, 0L, 0L, null, new String[][]{{"kS"}, {"Sk"}}, null, null, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			OP.stick.addListener(new OreProcessing_NonCrafting(RM.Sharpening, (ItemStack)null, qwerTool.getToolWithStats(10, MT.Empty, MT.Empty), TD.Atomic.ANTIMATTER.NOT));
			
			qwerTool.addTool(12, "Bat", "Underwood Light Switch", new QT_Tool_Bat().setMaterialAmount(OP.stick.mAmount), TC.stack(TC.TELUM, 2), "toolBat");
			OP.stickLong.addListener(new OreProcessing_QTTool(12, ConfigCategories.Recipes.gregtechtools + "." + "Bat", false, false, 0L, 0L, MT.Empty, new String[][]{{"yL"}, {"Ly"}}, null, null, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			
			qwerTool.addTool(14, "Sturdy Axe", "Fells whole trees in a single chop", new QT_Tool_SturdyAxe().setMaterialAmount(OP.toolHeadAxe.mAmount), "craftingToolAxe", TC.stack(TC.INSTRUMENTUM, 2), TC.stack(TC.ARBOR, 1), TC.stack(TC.MACHINA, 1), "axe");
			
			qwerTool.addTool(16, "Sturdy Shovel", "Diggy diggy hole", new QT_Tool_SturdyShovel().setMaterialAmount(OP.toolHeadShovel.mAmount), "craftingToolShovel", TC.stack(TC.INSTRUMENTUM, 2), TC.stack(TC.TERRA, 1), "shovel");

			qwerTool.addTool(18, "Sturdy Pickaxe", "This is boring", new QT_Tool_SturdyPickaxe().setMaterialAmount(OP.toolHeadPickaxe.mAmount), "craftingToolPickaxe", TC.stack(TC.INSTRUMENTUM, 2), TC.stack(TC.PERDITIO, 1), "pickaxe");
			
			//mattockHeadRaw.addListener(new Loader_OreProcessing.OreProcessing_Sharpening(mattockHead, 1L, true, TD.Atomic.ANTIMATTER.NOT));
			//maceHeadRaw.addListener(new Loader_OreProcessing.OreProcessing_Sharpening(maceHead, 1L, true, TD.Atomic.ANTIMATTER.NOT));
			RM.Sharpening.add(new RecipeMapHandlerPrefix(mattockHeadRaw, 1, CS.NF, 16, 0, 64, CS.NF, mattockHead, 1, CS.NI, CS.NI, CS.T, CS.T, CS.F, TD.Atomic.ANTIMATTER.NOT));
			RM.Sharpening.add(new RecipeMapHandlerPrefix(maceHeadRaw, 1, CS.NF, 16, 0, 64, CS.NF, maceHead, 1, CS.NI, CS.NI, CS.T, CS.T, CS.F, TD.Atomic.ANTIMATTER.NOT));
			RM.RollingMill.add(new RecipeMapHandlerPrefix(OP.ring, 1, CS.NF, 16, 0, 64, CS.NF, link, 1, CS.NI, CS.NI, CS.T, CS.T, CS.F, TD.Atomic.ANTIMATTER.NOT));
			RM.RollBender.add(new RecipeMapHandlerPrefix(OP.stick, 4, CS.NF, 16, 0, 64, CS.NF, chain, 1, CS.NI, CS.NI, CS.T, CS.T, CS.F, TD.Atomic.ANTIMATTER.NOT));
			
			mattockHead.addListener(new ShapelessCraftFrom( 1, null, new String[][] {{"X ", " f"}}, mattockHeadRaw, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
			maceHead.addListener(new ShapelessCraftFrom( 1, null, new String[][] {{"X ", " f"}}, maceHeadRaw, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
		}
		
		chain.addListener(new ShapelessCraftFrom(1, null, new String[][]{{"X X", " f ", "X X"}, {" X ", "XfX", " X "}}, link, null, null, null, null, TD.Atomic.ANTIMATTER.NOT));
		
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[1] | CS.B[2] | CS.B[3] | CS.B[4] | CS.B[7] | CS.B[8] | CS.B[12] | CS.B[13] | CS.B[18] | CS.B[19] | CS.B[23] | CS.B[24]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[0] | CS.B[1] | CS.B[2] | CS.B[3] | CS.B[6] | CS.B[7] | CS.B[11] | CS.B[12] | CS.B[17] | CS.B[18] | CS.B[22] | CS.B[23]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[0] | CS.B[5] | CS.B[6] | CS.B[7] | CS.B[10] | CS.B[11] | CS.B[12] | CS.B[3] | CS.B[4] | CS.B[15] | CS.B[8] | CS.B[9]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[5] | CS.B[10] | CS.B[11] | CS.B[12] | CS.B[15] | CS.B[16] | CS.B[17] | CS.B[8] | CS.B[9] | CS.B[20] | CS.B[13] | CS.B[14]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[10] | CS.B[11] | CS.B[4] | CS.B[15] | CS.B[16] | CS.B[7] | CS.B[8] | CS.B[9] | CS.B[12] | CS.B[13] | CS.B[14] | CS.B[19]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[15] | CS.B[16] | CS.B[9] | CS.B[20] | CS.B[21] | CS.B[12] | CS.B[13] | CS.B[14] | CS.B[17] | CS.B[18] | CS.B[19] | CS.B[24]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[2] | CS.B[3] | CS.B[7] | CS.B[8] | CS.B[11] | CS.B[12] | CS.B[16] | CS.B[17] | CS.B[20] | CS.B[21] | CS.B[22] | CS.B[23]), mattockHeadRaw);
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[3] | CS.B[4] | CS.B[8] | CS.B[9] | CS.B[12] | CS.B[13] | CS.B[17] | CS.B[18] | CS.B[21] | CS.B[22] | CS.B[23] | CS.B[24]), mattockHeadRaw);
		
		MultiTileEntityMold.MOLD_RECIPES.put(Integer.valueOf(CS.B[0] | CS.B[4] | CS.B[6] | CS.B[7] | CS.B[8] | CS.B[11] | CS.B[12] | CS.B[13] | CS.B[16] | CS.B[17] | CS.B[18] | CS.B[20] | CS.B[24]), maceHeadRaw);
		
		if (QTConfigs.enableArmor)
		{
			MinecraftForge.EVENT_BUS.register(new RegisterArmor());
		}
	}

	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {
		achievementHandler = new RegisterAchievements();
    	//MinecraftForge.EVENT_BUS.register(achievementHandler);
        //FMLCommonHandler.instance().bus().register(this);
        
        //MinecraftForge.EVENT_BUS.register(new RegisterMobs());
        new RegisterMobs();
        MinecraftForge.EVENT_BUS.register(new QT_GUIHandler());

        RegisterMaterials.instance.registerRecipes();
        
        RegisterLoot.init();
        
        ModLoadBase.runInit();
        
        CR.shaped(qwerTool.getToolWithStats(2, MT.Wood, MT.Wood), CR.DEF, "AAA", "BfB", " B ", 'A', ST.make(Items.string, 1, 0), 'B', OP.stick.mat(ANY.Wood, 1));
        CR.shaped(qwerTool.getToolWithStats(2, MT.Bone, MT.Bone), CR.DEF, "AAA", "BfB", " B ", 'A', ST.make(Items.string, 1, 0), 'B', ST.make(Items.bone, 1, 0));
        CR.shaped(qwerTool.getToolWithStats(2, MT.Rubber, MT.Rubber), CR.DEF, "AAA", "BfB", " B ", 'A', ST.make(Items.string, 1, 0), 'B', OP.stick.mat(MT.Rubber, 1));
        CR.shaped(qwerTool.getToolWithStats(2, MT.Blaze, MT.Blaze), CR.DEF, "AAA", "BfB", " B ", 'A', ST.make(Items.string, 1, 0), 'B', ST.make(Items.blaze_rod, 1, 0));
        
        if (OreDictionary.doesOreNameExist("cropOnion") && OreDictionary.doesOreNameExist("cropGarlic") && OreDictionary.doesOreNameExist("cropSpiceleaf")) {
	        RM.Mixer.addRecipe2(true, 16L, 16L, OreDictionary.getOres("cropGarlic").get(0), OreDictionary.getOres("cropOnion").get(0), UT.Fluids.make("binnie.juicetomato", 500), UT.Fluids.make("mildsalsa", 500), (ItemStack[])null);
	        RM.Mixer.addRecipe2(true, 16L, 16L, OreDictionary.getOres("cropBellpepper").get(0), OreDictionary.getOres("cropSpiceleaf").get(0), UT.Fluids.make("mildsalsa", 500), UT.Fluids.make("salsa", 500), (ItemStack[])null);
	        RM.Mixer.addRecipe2(true, 16L, 16L, OreDictionary.getOres("cropChilipepper").get(0), OreDictionary.getOres("cropChilipepper").get(0), UT.Fluids.make("salsa", 500), UT.Fluids.make("hotsalsa", 500), (ItemStack[])null);
        }
        
        GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadScrewdriver, OP.toolHeadScrewdriver}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadScrewdriver, OP.toolHeadFile}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadFile, 		OP.toolHeadFile}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadScrewdriver, OP.toolHeadChisel}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadFile, 		OP.toolHeadChisel}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadChisel, 		OP.toolHeadChisel}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.screw, 				OP.toolHeadScrewdriver}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.screw, 				OP.toolHeadFile}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.screw, 				OP.toolHeadChisel}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.screw, 				OP.screw}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadArrow, 		OP.toolHeadScrewdriver}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadArrow, 		OP.toolHeadFile}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadArrow, 		OP.toolHeadChisel}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadArrow, 		OP.screw}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
		GameRegistry.addRecipe(new AnyQTTool(12L, new And(OP.stickLong, TD.Atomic.ANTIMATTER.NOT), false, (Object[])null, new Object[]{OP.toolHeadArrow, 		OP.toolHeadArrow}, qwerTool.getToolWithStats(12, MT.NULL, MT.Empty)));
        
		GameRegistry.addRecipe(new AnyQTTool(14L, new And(TD.Atomic.ANTIMATTER.NOT), true, new Object[]{OP.toolHeadAxe, OP.plate}, new Object[]{OP.stickLong}));
		
		GameRegistry.addRecipe(new AnyQTTool(16L, new And(TD.Atomic.ANTIMATTER.NOT), true, new Object[]{OP.toolHeadShovel, OP.plate}, new Object[]{OP.stickLong}));
		
		GameRegistry.addRecipe(new AnyQTTool(18L, new And(TD.Atomic.ANTIMATTER.NOT), true, new Object[]{OP.toolHeadPickaxe, OP.plate}, new Object[]{OP.stickLong}));
		
		OreDictMaterial[] wallmats = new OreDictMaterial[]{MT.Fe, MT.Al, MT.Au, MT.Steel, ANY._Bronze, MT.Brass, MT.Ag, MT.StainlessSteel, MT.WroughtIron, MT.Plastic, MT.Ti, MT.TungstenSteel, MT.Invar, MT.TinAlloy, MT.SteelGalvanized, MT.Electrum};
		for (int q = 0; q < 16; q++)
		{
			//CR.shaped(ST.make(corrugatedBlock, 1, q), CR.DEF, "hPz", Character.valueOf('P'), OP.plateDouble.mat(wallmats[q], 1));
			CraftingManagerHammer.getInstance().addRecipe(new ShapedOreRecipe(ST.make(corrugatedBlock, 1,q), new Object[]{"P", "z", Character.valueOf('P'), OP.plateDouble.dat(wallmats[q]).toString(), Character.valueOf('z'), "craftingToolBendingCylinder"}));
			RM.RollFormer.addRecipe1(true, 16, 768, OP.plateDouble.mat(wallmats[q], 1), ST.make(corrugatedBlock,  1, q));
			RM.RollingMill.addRecipe1(true, 16, 768, ST.make(corrugatedBlock, 1, q), OP.plateDouble.mat(wallmats[q], 1));
		}
		
		for (int q = 1; q < WOOD.woodList.length; q++)
		{
			OreDictMaterial woodType = WOOD.woodList[q];
			if (woodType != null && OreDictionary.getOres("plank" + woodType.mNameInternal).size() > 0)
			{
				machines.add(woodType.mNameLocal + " Crafting Table", "Crafting Tables", q, 0, CraftingTableT1.class, 0, 16, wood, UT.NBT.make(null, CS.NBT_MATERIAL, woodType, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:wood", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(woodType.fRGBaSolid)));
			}
		}
		machines.add("Wooden Crafting Table", "Crafting Tables", 0, 0, CraftingTableT1.class, 0, 16, wood, UT.NBT.make(null, CS.NBT_MATERIAL, MT.Wood, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:wood", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(MT.Wood.fRGBaSolid)));
		
		GameRegistry.addRecipe(new WoodSpecificCrafting(machines.getItem(0), "PP", "PP", 'P', "plankWood"));
		
		OreDictMaterial[] tier1 = new OreDictMaterial[]{MT.Plastic, MT.Bi, MT.Cu, MT.Au, MT.Pb, MT.TinAlloy};
		for (int q = 0; q < tier1.length; q++)
		{
			OreDictMaterial mat = tier1[q];
			machines.add(mat.mNameLocal + " Crafting Table", "Crafting Tables", 257 + q, 0, CraftingTableT1.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", "CCC", "CCC", 'C', OP.chunkGt.dat(mat));
		}
		
		machines.add("Sandstone Crafting Table", "Crafting Tables", 256, 0, CraftingTableT1.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, MT.Sand, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(MT.Sand.fRGBaSolid)), "CCC", "CCC", "CCC", 'C', ST.make(Blocks.sandstone, 1, CS.W));
		
		OreDictMaterial[] tier2i = new OreDictMaterial[]{MT.Brass, MT.Ag, MT.Al, MT.Constantan, MT.AluminiumBrass, MT.Ni, MT.Ge};
		OreDictMaterial[] tier2g = new OreDictMaterial[]{MT.Almandine, MT.Topaz, MT.Alexandrite, MT.Spinel, MT.Opal, MT.Maxixe, MT.Pyrope, MT.Goshenite};
		OreDictMaterial[] tier2r = new OreDictMaterial[]{MT.Stone, MT.Obsidian, MT.Marble, MT.Limestone, MT.Diorite, MT.Andesite, MT.Netherrack, MT.Endstone};
		
		for (int q = 0; q < tier2i.length; q++)
		{
			OreDictMaterial mat = tier2i[q];
			machines.add(mat.mNameLocal + " Crafting Table", "Crafting Tables", 270 + q, 0, CraftingTableT2.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", "CCC", "CCC", 'C', OP.chunkGt.dat(mat));
		}
		for (int q = 0; q < tier2g.length; q++)
		{
			OreDictMaterial mat = tier2g[q];
			machines.add(mat.mNameLocal + " Crafting Table", "Crafting Tables", 280 + q, 0, CraftingTableT2.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:gem", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", "CCC", "CCC", 'C', OP.gemFlawed.dat(mat));
		}
		for (int q = 0; q < tier2r.length; q++)
		{
			OreDictMaterial mat = tier2r[q];
			machines.add(mat.mNameLocal + " Crafting Table", "Crafting Tables", 290 + q, 0, CraftingTableT2.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:rock", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", "CCC", "CCC", 'C', OP.rockGt.dat(mat));
		}
		
		OreDictMaterial[] tier3i = new OreDictMaterial[]{MT.Bronze, MT.WroughtIron, MT.Electrum, MT.Alumite, MT.Co, MT.CobaltBrass, MT.Pt, MT.Cr, MT.Os, MT.MeteoricIron};
		OreDictMaterial[] tier3g = new OreDictMaterial[]{MT.Ruby, MT.OrangeSapphire, MT.Amber, MT.Emerald, MT.Diamond, MT.BlueSapphire, MT.Amethyst};
		OreDictMaterial[] tier3r = new OreDictMaterial[]{MT.GraniteBlack, MT.GraniteRed, MT.Basalt};
		
		for (int q = 0; q < tier3i.length; q++)
		{
			OreDictMaterial mat = tier3i[q];
			machines.add(mat.mNameLocal + " Crafting Anvil", "Crafting Tables", 300 + q, 0, CraftingTableT3.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", " C ", "CCC", 'C', OP.chunkGt.dat(mat));
			CR.shapeless(ST.make(Blocks.crafting_table, 1, 0), CR.DEF, new Object[]{machines.getItem(300 + q)});
		}
		for (int q = 0; q < tier3g.length; q++)
		{
			OreDictMaterial mat = tier3g[q];
			machines.add(mat.mNameLocal + " Crafting Anvil", "Crafting Tables", 310 + q, 0, CraftingTableT3.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:gem", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", " C ", "CCC", 'C', OP.gemFlawed.dat(mat));
			CR.shapeless(ST.make(Blocks.crafting_table, 1, 0), CR.DEF, new Object[]{machines.getItem(310 + q)});
		}
		for (int q = 0; q < tier3r.length; q++)
		{
			OreDictMaterial mat = tier3r[q];
			machines.add(mat.mNameLocal + " Crafting Anvil", "Crafting Tables", 320 + q, 0, CraftingTableT3.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 11, CS.NBT_TEXTURE, "qwertech:rock", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", " C ", "CCC", 'C', OP.rockGt.dat(mat));
			CR.shapeless(ST.make(Blocks.crafting_table, 1, 0), CR.DEF, new Object[]{machines.getItem(320 + q)});
		}
		
		Class<? extends TileEntity> toUse = CraftingTableT4.class;
		if (!QTConfigs.enable3DCrafting)
		{
			toUse = CraftingTableT3.class;
		}
		OreDictMaterial[] tier4i = new OreDictMaterial[]{MT.Steel, MT.Invar, MT.SteelGalvanized, MT.MeteoricSteel, MT.Thaumium, MT.Manasteel, MT.Knightmetal, MT.ObsidianSteel, MT.Manyullyn, MT.FakeOsmium};
		for (int q = 0; q < tier4i.length; q++)
		{
			OreDictMaterial mat = tier4i[q];
			machines.add(mat.mNameLocal + " Crafting Anvil", "Crafting Tables", 330 + q, 0, toUse, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 29, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", " C ", "CCC", 'C', OP.chunkGt.dat(mat));
			CR.shapeless(ST.make(Blocks.crafting_table, 1, 0), CR.DEF, new Object[]{machines.getItem(330 + q)});
		}
		
		OreDictMaterial[] tier5i = new OreDictMaterial[]{MT.StainlessSteel, MT.TungstenSteel, MT.Ti, MT.Ad, MT.Bedrock, MT.Desh, MT.TungstenCarbide, MT.DuraniumAlloy, MT.W, MT.TritaniumAlloy};
		for (int q = 0; q < tier5i.length; q++)
		{
			OreDictMaterial mat = tier5i[q];
			machines.add(mat.mNameLocal + " Crafting Anvil", "Crafting Tables", 340 + q, 0, toUse, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 29, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "CCC", " C ", "CCC", 'C', OP.chunkGt.dat(mat));
		}
		
		machines.add("Crafting Helper", "", 400, 0, CraftingHelper.class, 15, 0, air, UT.NBT.make());
		
		OreDictMaterial[] upgradeDeskMats = new OreDictMaterial[] {MT.Bronze, MT.Co, MT.Au, MT.Obsidian, MT.Plastic, MT.Ag};
		for (int q = 0; q < upgradeDeskMats.length; q++)
		{
			OreDictMaterial mat = upgradeDeskMats[q];
			machines.add(mat.mNameLocal + " Upgrade Desk", "Upgrade Desks", 401 + q, 0, UpgradeDesk.class, 0, 16, metal, UT.NBT.make(null, CS.NBT_MATERIAL, mat, CS.NBT_INV_SIZE, 1, CS.NBT_TEXTURE, "qwertech:metal", CS.NBT_HARDNESS, 3.0F, CS.NBT_RESISTANCE, 3.0F, CS.NBT_COLOR, UT.Code.getRGBInt(mat.fRGBaSolid)), "RfR", "RSR", "CCC", 'C', OP.plate.dat(mat), 'R', OP.stick.dat(ANY.Steel), 'S', OP.springSmall.dat(ANY.Steel));
		}
	}

	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		RegisterArmor.instance.addUpgrades();
		
		ModLoadBase.runPostInit();
		CraftingManagerHammer.replacems.put(ST.make(Items.feather, 1, 0), "itemFeather");
		//CS.GT.mAfterPostInit.add(CraftingManagerHammer.getInstance());
		//CS.GT.mAfterPostInit.add(CraftingManager3D.getInstance());
	}

	@Mod.EventHandler
	public void onModsLoaded(FMLLoadCompleteEvent aEvent)
	{
		CraftingManagerHammer.getInstance().run();
		CraftingManager3D.getInstance().run();
	}

	@Override
	public void onModServerStarting2(FMLServerStartingEvent aEvent) {
		
	}

	@Override
	public void onModServerStarted2(FMLServerStartedEvent aEvent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onModServerStopping2(FMLServerStoppingEvent aEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onModServerStopped2(FMLServerStoppedEvent aEvent) {
		// TODO Auto-generated method stub
		
	}
}
