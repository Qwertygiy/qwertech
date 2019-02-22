package com.kbi.qwertech.loaders;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.entities.neutral.EntityTurkey;
import com.kbi.qwertech.entities.projectile.EntityBall;
import com.kbi.qwertech.entities.projectile.EntityFoil;
import com.kbi.qwertech.entities.projectile.EntityRock;
import com.kbi.qwertech.items.behavior.Behavior_Slingshot;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.*;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictPrefix;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.tileentity.tools.MultiTileEntityMold;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterAchievements {
	
	//public static AchievementPage Achievements;
    public HashMap<String, Achievement> achievementList;
    public HashMap<String, Achievement> achievementAges;
    public HashMap<String, Integer> achievementXP;
    public MultiTileEntityRegistry aRegistry;
    public MultiTileEntityRegistry qRegistry;
    
    private MultiItemTool qwerTool;
    
    public RegisterAchievements()
    {
    	this.achievementList = new HashMap();
    	this.achievementAges = new HashMap();
    	this.achievementXP = new HashMap();
    	this.aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
    	this.qRegistry = MultiTileEntityRegistry.getRegistry("qwertech.machines");
    	
    	ItemStack rock = OP.rockGt.mat(MT.Stone, 1);
    	ItemStack crushed = OP.crushed.mat(MT.Cu, 1);
    	ItemStack plate = OP.plate.mat(MT.Steel, 1);
    	ItemStack stonePick = CS.ToolsGT.sMetaTool.getToolWithStats(CS.ToolsGT.PICKAXE, 1, MT.Stone, MT.Wood);
    	ItemStack marbleHammer = CS.ToolsGT.sMetaTool.getToolWithStats(CS.ToolsGT.HARDHAMMER, 1, MT.Marble, MT.Wood);
    	ItemStack file = CS.ToolsGT.sMetaTool.getToolWithStats(CS.ToolsGT.FILE, 1, MT.Pb, MT.Wood);
    	ItemStack wrench = CS.ToolsGT.sMetaTool.getToolWithStats(CS.ToolsGT.WRENCH, 1, MT.Bronze, MT.Bronze);
    	ItemStack chisel = CS.ToolsGT.sMetaTool.getToolWithStats(CS.ToolsGT.CHISEL, 1, MT.Fe, MT.Wood);
    	ItemStack hamburger = IL.Food_Burger_Meat.get(1, (Object)null);
    	ItemStack dryGrass = IL.Grass_Dry.get(1, (Object)null);
    	ItemStack fireStarter = IL.Tool_Fire_Starter.get(1, (Object)null);
    	ItemStack mold = aRegistry.getItem(1055);
    	ItemStack plateMold = QTI.moldPlate.get(1);
    	ItemStack ingotMold = QTI.moldIngot.get(1);
    	ItemStack stickMold = QTI.moldRod.get(1);
    	ItemStack longRodMold = QTI.moldLongRod.get(1);
    	ItemStack chunkMold = QTI.moldChunk.get(1);
    	ItemStack pickaxeMold = QTI.moldPickaxe.get(1);
    	ItemStack tinyPlateMold = QTI.moldTinyPlate.get(1);
    	ItemStack arrowMold = QTI.moldArrow.get(1);
    	ItemStack axeMold = QTI.moldAxe.get(1);
    	ItemStack boltMold = QTI.moldBolt.get(1);
    	ItemStack chiselMold = QTI.moldChisel.get(1);
    	ItemStack doubleAxeMold = QTI.moldDoubleAxe.get(1);
    	ItemStack fileMold = QTI.moldFile.get(1);
    	ItemStack gearMold = QTI.moldGear.get(1);
    	ItemStack hammerMold = QTI.moldHammer.get(1);
    	ItemStack hoeMold = QTI.moldHoe.get(1);
    	ItemStack itemCasingMold = QTI.moldItemCasing.get(1);
    	ItemStack plowMold = QTI.moldPlow.get(1);
    	ItemStack ringMold = QTI.moldRing.get(1);
    	ItemStack sawMold = QTI.moldSaw.get(1);
    	ItemStack screwdriverMold = QTI.moldScrewdriver.get(1);
    	ItemStack senseMold = QTI.moldSense.get(1);
    	ItemStack shovelMold = QTI.moldShovel.get(1);
    	ItemStack smallGearMold = QTI.moldSmallGear.get(1);
    	ItemStack swordMold = QTI.moldSword.get(1);
    	ItemStack spadeMold = QTI.moldSpade.get(1);
    	ItemStack uSpadeMold = QTI.moldUniversalSpade.get(1);
    	ItemStack mattockMold = QTI.moldMattock.get(1);
    	ItemStack maceMold = QTI.moldMace.get(1);
    	ItemStack burningBox = aRegistry.getItem(1103);
    	qwerTool = (MultiItemTool)QTI.qwerTool.getItem();
    	ItemStack knuckles = qwerTool.getToolWithStats(4, MT.Brass, MT.Brass);
    	ItemStack bronzeGear = OP.gearGt.mat(MT.Bronze, 1);
    	ItemStack boiler = aRegistry.getItem(1202);
    	ItemStack turbine = aRegistry.getItem(1512);
    	ItemStack slingshot = qwerTool.getToolWithStats(2, MT.Ag, MT.Ag);
    	ItemStack mace = qwerTool.getToolWithStats(6, MT.Amethyst, MT.Wood);
    	ItemStack doubleAxe = CS.ToolsGT.sMetaTool.getToolWithStats(58, MT.Invar, MT.Wood);
    	ItemStack bat = qwerTool.getToolWithStats(12, MT.Fe, MT.Empty);
    	ItemStack mudBall = IL.Mud_Ball.get(1, (Object[])null);
    	ItemStack rollMill = aRegistry.getItem(20111);
    	ItemStack lathe = aRegistry.getItem(20041);
    	ItemStack rollBender = aRegistry.getItem(20121);
    	ItemStack crusher = aRegistry.getItem(20021);
    	ItemStack shredder = aRegistry.getItem(20011);
    	ItemStack squeezer = aRegistry.getItem(20071);
    	ItemStack compressor = aRegistry.getItem(20101);
    	ItemStack shuriken = OreDictPrefix.get("shuriken").mat(MT.Fe, 1);
    	ItemStack batOnBat = QTI.batEmblem.get(1);
    	ItemStack splashPot = ST.make(Items.potionitem, 1, 16457);
    	ItemStack turkeyMeat = ST.make(QTI.qwerFood.getItem(), 1, 8);
    	ItemStack stoneCraft = qRegistry.getItem(290);
    	ItemStack bronzeCraft = qRegistry.getItem(300);
    	
    	achievementAges.put("stoneAge", registerAchievement("stoneAge", 5, -6, new ItemStack(Blocks.furnace), null, true, 0));
    	achievementAges.put("rockstart", registerAchievement("rockyStart", 5, -4, rock, achievementAges.get("stoneAge"), false, 5));
        achievementAges.put("pickUpFlint", registerAchievement("pickUpFlint", 7, -4, new ItemStack(Items.flint), achievementAges.get("rockstart"), false, 5));
        achievementAges.put("craftHammer", registerAchievement("craftHammer", 3, -4, marbleHammer, achievementAges.get("rockstart"), false, 15));
        achievementAges.put("dryGrass", registerAchievement("dryGrass", 7, -6, dryGrass, achievementAges.get("pickUpFlint"), false, 15));
        achievementAges.put("fireStarter", registerAchievement("fireStarter", 9, -6, fireStarter, achievementAges.get("dryGrass"), false, 10));
        achievementAges.put("crushOre", registerAchievement("crushOre", 1, -4, crushed, achievementAges.get("craftHammer"), false, 15));
        achievementAges.put("stoneCraft", registerAchievement("stoneCraft", 3, -6, stoneCraft, achievementAges.get("craftHammer"), false, 25));
        achievementAges.put("hammerPlate", registerAchievement("hammerPlate", 1, -6, plate, achievementAges.get("stoneCraft"), false, 25));
        achievementAges.put("craftFile", registerAchievement("craftFile", -1, -4, file, achievementAges.get("hammerPlate"), false, 15));
        achievementAges.put("useMace", registerAchievement("useMace", -3, -4, mace, achievementAges.get("craftFile"), false, 25));
        achievementAges.put("craftWrench", registerAchievement("craftWrench", -1, -6, wrench, achievementAges.get("hammerPlate"), false, 15));
        achievementAges.put("craftChisel", registerAchievement("craftChisel", -1, -2, chisel, achievementAges.get("craftFile"), false, 25));
        achievementAges.put("craftMold", registerAchievement("craftMold", 1, -2, mold, achievementAges.get("craftChisel"), false, 25));
        achievementAges.put("moldPlate", registerAchievement("moldPlate", 2, -3, plateMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldIngot", registerAchievement("moldIngot", 3, -3, ingotMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldRod", registerAchievement("moldRod", 4, -3, stickMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldLongRod", registerAchievement("moldLongRod", 5, -3, longRodMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldChunk", registerAchievement("moldChunk", 6, -3, chunkMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldRing", registerAchievement("moldRing", 7, -3, ringMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldItemCasing", registerAchievement("moldItemCasing", 8, -3, itemCasingMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldGear", registerAchievement("moldGear", 9, -3, gearMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldSmallGear", registerAchievement("moldSmallGear", 10, -3, smallGearMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldBolt", registerAchievement("moldBolt", 11, -3, boltMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldTinyPlate", registerAchievement("moldTinyPlate", 12, -3, tinyPlateMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldArrow", registerAchievement("moldArrow", 13, -3, arrowMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldSpade", registerAchievement("moldSpade", 14, -3, spadeMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldPickaxe", registerAchievement("moldPickaxe", 2, -2, pickaxeMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldShovel", registerAchievement("moldShovel", 3, -2, shovelMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldAxe", registerAchievement("moldAxe", 4, -2, axeMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldSword", registerAchievement("moldSword", 5, -2, swordMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldHammer", registerAchievement("moldHammer", 7, -2, hammerMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldHoe", registerAchievement("moldHoe", 6, -2, hoeMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldFile", registerAchievement("moldFile", 8, -2, fileMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldChisel", registerAchievement("moldChisel", 9, -2, chiselMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldScrewdriver", registerAchievement("moldScrewdriver", 10, -2, screwdriverMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldSaw", registerAchievement("moldSaw", 11, -2, sawMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldSense", registerAchievement("moldSense", 12, -2, senseMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldPlow", registerAchievement("moldPlow", 13, -2, plowMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldDoubleAxe", registerAchievement("moldDoubleAxe", 14, -2, doubleAxeMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldUniversalSpade", registerAchievement("moldUniversalSpade", 15, -3, uSpadeMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldMattock", registerAchievement("moldMattock", 15, -2, mattockMold, achievementAges.get("craftMold"), false, 10));
        achievementAges.put("moldMace", registerAchievement("moldMace", 16, -3, maceMold, achievementAges.get("craftMold"), false, 10));
        
        achievementAges.put("bronzeAge", registerAchievement("bronzeAge", 5, -1, bronzeGear, achievementAges.get("craftMold"), true, 25));
        achievementAges.put("bronzeCraft", registerAchievement("bronzeCraft", 8, -1, bronzeCraft, achievementAges.get("bronzeAge"), true, 25));
        achievementAges.put("craftBoiler", registerAchievement("craftBoiler", 3, 0, boiler, achievementAges.get("bronzeAge"), false, 20));
        achievementAges.put("craftTurbine", registerAchievement("craftTurbine", 7, 0, turbine, achievementAges.get("bronzeAge"), false, 20));
        achievementAges.put("craftRollingMill", registerAchievement("craftRollingMill", 6, 1, rollMill, achievementAges.get("bronzeAge"), false, 15));
        achievementAges.put("craftLathe", registerAchievement("craftLathe", 4, 1, lathe, achievementAges.get("bronzeAge"), false, 15));
        achievementAges.put("craftRollBender", registerAchievement("craftRollBender", 8, 1, rollBender, achievementAges.get("bronzeAge"), false, 15));
        achievementAges.put("craftCrusher", registerAchievement("craftCrusher", 2, 1, crusher, achievementAges.get("bronzeAge"), false, 20));
        achievementAges.put("craftCompressor", registerAchievement("craftCompressor", 3, 2, compressor, achievementAges.get("bronzeAge"), false, 20));
        achievementAges.put("craftSqueezer", registerAchievement("craftSqueezer", 5, 2, squeezer, achievementAges.get("bronzeAge"), false, 20));
        achievementAges.put("craftShredder", registerAchievement("craftShredder", 7, 2, shredder, achievementAges.get("bronzeAge"), false, 20));
        
        achievementList.put("stoneWeapon", registerAchievement("stoneWeapon", 0, -6, stonePick, achievementAges.get("rockstart"), false, 50));
        achievementList.put("cookCow", registerAchievement("cookCow", 0, -5, hamburger, achievementAges.get("pickUpFlint"), false, 25));
        achievementList.put("explodeOverheat", registerAchievement("explodeOverheat", 0, -4, burningBox, achievementAges.get("craftChisel"), false, 50));
        achievementList.put("knuckleDuster", registerAchievement("knuckleDuster", 0, -3, knuckles, achievementAges.get("hammerPlate"), false, 20));
    	achievementList.put("brokenWindow", registerAchievement("brokenWindow", 0, -2, slingshot, achievementAges.get("rockstart"), false, 25));
        achievementList.put("rustyBreak", registerAchievement("rustyBreak", 1, -6, doubleAxe, AchievementList.killEnemy, false, 20));
        achievementList.put("batterUp", registerAchievement("batterUp", 1, -5, bat, achievementAges.get("craftChisel"), false, 15));
        achievementList.put("mudSplat", registerAchievement("mudSplat", 1, -4, mudBall, achievementAges.get("rockstart"), false, 10));
        achievementList.put("ninjaStrike", registerAchievement("ninjaStrike", 1, -3, shuriken, achievementAges.get("craftFile"), false, 50));
        achievementList.put("kingBat", registerAchievement("kingBat", 1, -2, batOnBat, achievementList.get("batterUp"), false, 50));
    	achievementList.put("deflectKill", registerAchievement("deflectKill", 2, -6, splashPot, achievementList.get("batterUp"), false, 33));
        achievementList.put("peckish", registerAchievement("peckish", 2, -5, turkeyMeat, achievementAges.get("rockstart"), false, 25));
    	
        AchievementPage.registerAchievementPage(new AchievementPage("QwerTech Progression", this.achievementAges.values().toArray(new Achievement[this.achievementAges.size()])));
        AchievementPage.registerAchievementPage(new AchievementPage("QwerTech Challenges", this.achievementList.values().toArray(new Achievement[this.achievementList.size()]))); 
        
        registerRewards();
        FMLCommonHandler.instance().bus().register(this);
    }
    
    public void registerRewards()
    {
    	achievementXP.put(AchievementList.openInventory.statId, 0);
    	achievementXP.put(AchievementList.mineWood.statId, 5);
    	achievementXP.put(AchievementList.buildWorkBench.statId, 5);
    	achievementXP.put(AchievementList.buildPickaxe.statId, 10);
    	achievementXP.put(AchievementList.buildFurnace.statId, 15);
    	achievementXP.put(AchievementList.buildSword.statId, 10);
    	achievementXP.put(AchievementList.buildHoe.statId, 10);
    	achievementXP.put(AchievementList.acquireIron.statId, 25); //it's a lot harder with GregTech!
    	achievementXP.put(AchievementList.cookFish.statId, 15);
    	achievementXP.put(AchievementList.killEnemy.statId, 10);
    	achievementXP.put(AchievementList.killCow.statId, 10);
    	achievementXP.put(AchievementList.buildBetterPickaxe.statId, 25);
    	achievementXP.put(AchievementList.makeBread.statId, 10);
    	achievementXP.put(AchievementList.bakeCake.statId, 25);
    	achievementXP.put(AchievementList.diamonds.statId, 50);
    	achievementXP.put(AchievementList.portal.statId, 25);
    	achievementXP.put(AchievementList.blazeRod.statId, 20);
    	achievementXP.put(AchievementList.ghast.statId, 100);
    	achievementXP.put(AchievementList.bookcase.statId, 50);
    	achievementXP.put(AchievementList.enchantments.statId, 25);
    	achievementXP.put(AchievementList.flyPig.statId, 50);
    	achievementXP.put(AchievementList.onARail.statId, 100);
    	achievementXP.put(AchievementList.overkill.statId, 50);
    	achievementXP.put(AchievementList.potion.statId, 25);
    	achievementXP.put(AchievementList.snipeSkeleton.statId, 100);
    	achievementXP.put(AchievementList.theEnd.statId, 100);
    	achievementXP.put(AchievementList.theEnd2.statId, 500);
    	achievementXP.put("achievement.breedCow", 20);
    	achievementXP.put("achievement.spawnWither", 50);
    	achievementXP.put("achievement.killWither", 100);
    	achievementXP.put("achievement.fullBeacon", 200);
    	achievementXP.put("achievement.exploreAllBiomes", 200);
    	
    	achievementXP.put("achievement.ic2.acquireResin", 15);
    	achievementXP.put("achievement.ic2.acquireRefinedIron", 15);
    	achievementXP.put("achievement.ic2.dieFromOwnNuke", 100); //not like you'll be using it ;D
    	//I don't know if any other IC2 achievements are really acquirable with GregTech
    	
    	System.out.println("There are " + achievementXP.size() + " achievement XP entries");
    }
	
	public Achievement registerAchievement(String textId, int x, int y, ItemStack icon, Achievement requirement, boolean special)
    {
      Achievement achievement = new Achievement("qwertech." + textId, textId, x, y, icon, requirement);
      if (special) {
        achievement.setSpecial();
      }
      achievement.registerStat();
      //this.achievementList.put(textId, achievement);
      return achievement;
    }
	
	public Achievement registerAchievement(String textId, int x, int y, ItemStack icon, Achievement requirement, boolean special, int XP)
	{
		Achievement achievement = registerAchievement(textId, x, y, icon, requirement, special);
		this.achievementXP.put(achievement.statId, XP);
		return achievement;
	}
    
    public void issueAchievement(EntityPlayer entityplayer, String textId)
    {
      if (this.achievementList.containsKey(textId)) {
        entityplayer.triggerAchievement(this.achievementList.get(textId));
      } else if (this.achievementAges.containsKey(textId)) {
        entityplayer.triggerAchievement(this.achievementAges.get(textId));
      }
    }
    
    //@SubscribeEvent
    public void onAchieved(AchievementEvent event)
    {
    	EntityPlayerMP ep = (EntityPlayerMP)event.entityPlayer;
    	if ((!ep.func_147099_x().hasAchievementUnlocked(event.achievement)) && ((event.achievement.parentAchievement == null) || (ep.func_147099_x().hasAchievementUnlocked(event.achievement.parentAchievement))))
    	{
    		if (QTConfigs.announceFanfare) 
    		{
    			if (event.achievement == AchievementList.openInventory || event.achievement == AchievementList.mineWood || event.achievement == AchievementList.buildWorkBench || event.achievement == AchievementList.buildPickaxe || event.achievement == achievementAges.get("stoneAge") || event.achievement == achievementAges.get("rockstart"))
    			{} else
    			UT.Sounds.send("qwertech:achievement.normal", 0.3F, 1F, event.entityPlayer);
    		}
	    	if (event.achievement == AchievementList.openInventory)
	    	{
	    		issueAchievement(event.entityPlayer, "stoneAge");
	    	}
	    	if (this.achievementXP.containsKey(event.achievement.statId))
	    	{
	    		event.entityPlayer.addExperience(this.achievementXP.get(event.achievement.statId));
	    	} else {
	    		if (event.achievement.getSpecial())
	    		{
	    			event.entityPlayer.addExperience(50);
	    		} else {
	    			event.entityPlayer.addExperience(15);
	    		}
	    	}
    	} else if(event.achievement == AchievementList.openInventory) {
    		issueAchievement(event.entityPlayer, "stoneAge");
    	}
    }
    
    //@SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event)
    {
      ItemStack stacker = event.item.getEntityItem();
      if (OP.rockGt.contains(stacker)) {
    	  issueAchievement(event.entityPlayer, "stoneAge");
    	  issueAchievement(event.entityPlayer, "rockstart");
      } else if (stacker.getItem() == Items.flint) {
    	  //issueAchievement(event.entityPlayer, "rockstart");
    	  issueAchievement(event.entityPlayer, "pickUpFlint");
      } else if (ItemStack.areItemStacksEqual(stacker, IL.Bale_Dry.get(1, (Object)null))) {
    	  issueAchievement(event.entityPlayer, "dryGrass");
      }
    }
    
    /* Required to grant achievements when right-clicking rocks or sticks to pick them up.
     * Since they go direct to inventory instead of dropping as an item.
     */
    //@SubscribeEvent
    public void onRightClick(PlayerInteractEvent event)
    {
    	if (event.action == Action.RIGHT_CLICK_BLOCK) {
	    	Block og = event.world.getBlock(event.x, event.y, event.z);
	    	Class bc = og.getClass();
	    	//System.out.println("Interacted with " + bc.getName());
	    	if (bc.getName().equals("gregapi.block.multitileentity.MultiTileEntityBlock")) {
	    		//System.out.println("We have a GT block!");
	    		MultiTileEntityBlock GTB = (MultiTileEntityBlock)og;
	    		ArrayList<ItemStack> rawdrop = GTB.getDrops(event.world, event.x, event.y, event.z, 0, 0);
	    		ItemStack[] drops = rawdrop.toArray(new ItemStack[rawdrop.size()]);
	    		if (OP.rockGt.contains(drops))
	    		{
	    			issueAchievement(event.entityPlayer, "stoneAge");
	    			issueAchievement(event.entityPlayer, "rockstart");
	    		} else {
	    			for (int q = 0; q < rawdrop.size(); q++) {
	    				if (ItemStack.areItemStacksEqual(drops[q], new ItemStack(Items.flint))) {
	    					issueAchievement(event.entityPlayer, "stoneAge");
	    					issueAchievement(event.entityPlayer, "rockstart");
	    	    			issueAchievement(event.entityPlayer, "pickUpFlint");
	    				}
	    			}
	    		}
	    		if (event.world.getTileEntity(event.x, event.y, event.z) != null) {
	    			Class TE = event.world.getTileEntity(event.x, event.y, event.z).getClass();
	    			if (TE.getName().equals("gregtech.tileentity.tools.MultiTileEntityMold")) {
	    				TileEntityBase07Paintable moldTE = (MultiTileEntityMold)event.world.getTileEntity(event.x, event.y, event.z);
	    				if (moldTE.slot(0) != null && moldTE.canExtractItem2(0, moldTE.slot(0), (byte)0) && event.entityPlayer.getHeldItem() == null)
	    				{
	    					//System.out.println("Picking up item from mold: " + moldTE.slot(0));
	    					if (OP.plate.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldPlate");
	    					} else if (OP.ingot.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldIngot");
	    					} else if (OP.stick.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldRod");
	    					} else if (OP.stickLong.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldLongRod");
	    					} else if (OP.chunkGt.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldChunk");
	    					} else if (OP.toolHeadRawPickaxe.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldPickaxe");
	    					} else if (OP.plateTiny.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldTinyPlate");
	    					} else if (OP.arrow.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldArrow");
	    					} else if (OP.toolHeadRawAxe.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldAxe");
	    					} else if (OP.bolt.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldBolt");
	    					} else if (OP.toolHeadRawChisel.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldChisel");
	    					} else if (OP.toolHeadRawAxeDouble.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldDoubleAxe");
	    					} else if (OP.toolHeadFile.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldFile");
	    					} else if (OP.gearGt.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldGear");
	    					} else if (OP.toolHeadHammer.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldHammer");
	    					} else if (OP.toolHeadRawHoe.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldHoe");
	    					} else if (OP.casingSmall.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldItemCasing");
	    					} else if (OP.toolHeadRawPlow.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldPlow");
	    					} else if (OP.toolHeadRawSaw.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldSaw");
	    					} else if (OP.toolHeadScrewdriver.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldScrewdriver");
	    					} else if (OP.toolHeadRawSense.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldSense");
	    					} else if (OP.toolHeadRawShovel.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldShovel");
	    					} else if (OP.gearGtSmall.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldSmallGear");
	    					} else if (OP.toolHeadRawSword.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldSword");
	    					} else if (OP.toolHeadRawSpade.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldSpade");
	    					} else if (OP.toolHeadRawUniversalSpade.contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldUniversalSpade");
	    					} else if (OreDictPrefix.get("toolHeadMattock").contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldMattock");
	    					} else if (OreDictPrefix.get("toolHeadMace").contains(moldTE.slot(0))) {
	    						issueAchievement(event.entityPlayer, "moldMace");
	    					}
	    				}
	    			}
	    		}
	    	}
    	}
    }
    
    //@SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event)
    {
    	if (event.source.getSourceOfDamage() instanceof EntityPlayer)
    	{
    		Entity project = event.source.getEntity();
    		if (project != null)
    		{
    			if (project.getExtendedProperties("isDeflected") != null)
    			{
    				issueAchievement((EntityPlayer)event.source.getSourceOfDamage(), "deflectKill");
    			}
    		}
    	} else if (event.entity instanceof EntityPlayer)
    	{
    		if (event.source.getSourceOfDamage() instanceof EntityTurkey)
    		{
    			issueAchievement((EntityPlayer)event.entity, "peckish");
    		}
    	}
    }
    
    //@SubscribeEvent
    public void onEntityHurt(AttackEntityEvent event)
    {
    	Entity hurt = event.target;
    	ItemStack tool = event.entityPlayer.getCurrentEquippedItem();
    	if (tool != null)
    	{
	    	if (hurt instanceof EntitySkeleton)			
	    	{
	    		//System.out.println("We stabbed a skelly!");
	    		if (MultiItemTool.getPrimaryMaterial(tool) == MT.Stone)
	    		{
	    			issueAchievement(event.entityPlayer, "stoneWeapon");
	    		}
	    	} else if (hurt instanceof EntityCow)
	    	{
	    		if (MultiItemTool.getPrimaryMaterial(tool) == MT.Flint)
	    		{
	    			issueAchievement(event.entityPlayer, "cookCow");
	    		}
	    	}
    	}
    }
    
    //@SubscribeEvent
    public void onCrafted(PlayerEvent.ItemCraftedEvent event)
    {
    	ItemStack result = event.crafting;
    	if (result != null)
    	{
    		//System.out.println("Just crafted a " + result);
    		if (OreDictManager.isItemStackInstanceOf(result, "craftingToolHardHammer"))
    		{
    			//System.out.println("It's a hammah!");
    			issueAchievement(event.player, "craftHammer");
    		} else if (OreDictManager.isItemStackInstanceOf(result, "craftingToolFile"))
    		{
    			//System.out.println("IT'S A GOSH DARN FILE SO ACHIEVE IT ALREADY AAAAAAAAAAAAA");
    			issueAchievement(event.player, "craftFile");
    		} else if (OreDictManager.isItemStackInstanceOf(result, "craftingToolWrench"))
    		{
    			issueAchievement(event.player, "craftWrench");
    		} else if (OreDictManager.isItemStackInstanceOf(result, "craftingToolChisel"))
    		{
    			issueAchievement(event.player, "craftChisel");
    		} else if (OP.plate.contains(result)) {
    			OreDictItemData tData = OreDictManager.INSTANCE.getItemData(result);
    			if (OP.ingot.isTrue(tData.mMaterial.mMaterial)) {
    				issueAchievement(event.player, "hammerPlate");
    			}
    		} else if (ItemStack.areItemStacksEqual(result, IL.Tool_Fire_Starter.get(1, (Object)null))) {
    			issueAchievement(event.player, "fireStarter");
    		} else if (aRegistry.getNewTileEntity(result) != null) {
    			TileEntity mte = aRegistry.getNewTileEntity(result);
    			String MachineType = mte.getClass().getName();
    			//System.out.println("Crafted a" + MachineType);
    			if (MachineType.equals("gregtech.tileentity.tools.MultiTileEntityMold")) {
    				issueAchievement(event.player, "craftMold");
    			} else if(MachineType.equals("gregtech.tileentity.energy.MultiTileEntityBoilerTank")) {
    				issueAchievement(event.player, "bronzeAge");
    				issueAchievement(event.player, "craftBoiler");
    			} else if(MachineType.equals("gregtech.tileentity.energy.MultiTileEntityTurbineSteam")) {
    				issueAchievement(event.player, "bronzeAge");
    				issueAchievement(event.player, "craftTurbine");
    			} else if (MachineType.toLowerCase().contains(("crafting"))) {
    				event.player.triggerAchievement(AchievementList.buildWorkBench);
    				if (MachineType.contains("2"))
    				{
    					issueAchievement(event.player, "stoneCraft");
    				} else if (MachineType.contains("3"))
    				{
    					issueAchievement(event.player, "stoneCraft");
    					issueAchievement(event.player, "bronzeCraft");
    				}
    			} else if(MachineType.equals("gregapi.tileentity.machines.MultiTileEntityBasicMachine")) {
    				if (result.getDisplayName().contains("Lathe"))
    				{
    					issueAchievement(event.player, "craftLathe");
    				} else if (result.getDisplayName().contains("Rolling Mill"))
    				{
    					issueAchievement(event.player, "craftRollingMill");
    				} else if (result.getDisplayName().contains("Shredder"))
    				{
    					issueAchievement(event.player, "craftShredder");
    				} else if (result.getDisplayName().contains("Crusher"))
    				{
    					issueAchievement(event.player, "craftCrusher");
    				} else if (result.getDisplayName().contains("Squeezer"))
    				{
    					issueAchievement(event.player, "craftSqueezer");
    				} else if (result.getDisplayName().contains("Compressor"))
    				{
    					issueAchievement(event.player, "craftCompressor");
    				} else if (result.getDisplayName().contains("Roll Bender"))
    				{
    					issueAchievement(event.player, "craftRollBender");
    				}
    				
    			}
    		} else if (qRegistry.getNewTileEntity(result) != null) {
				TileEntity mte = qRegistry.getNewTileEntity(result);
				String MachineType = mte.getClass().getName();
				//System.out.println("Crafted a" + MachineType);
				if (MachineType.toLowerCase().contains(("crafting"))) {
					event.player.triggerAchievement(AchievementList.buildWorkBench);
					if (MachineType.contains("2"))
					{
						issueAchievement(event.player, "stoneCraft");
					} else if (MachineType.contains("3"))
					{
						issueAchievement(event.player, "stoneCraft");
						issueAchievement(event.player, "bronzeCraft");
					}
				}
			}
    	}
    }
    
    //@SubscribeEvent
    public void onSmelted(PlayerEvent.ItemSmeltedEvent event)
    {
    	if (event.smelting.getItem() == Items.bread || event.smelting.equals(IL.Food_Bread.get(1, Items.bread)))
    	{
    		event.player.triggerAchievement(AchievementList.makeBread);
    	}
    }
    
    //@SubscribeEvent
    public void onBlockBreak(BlockEvent.HarvestDropsEvent event)
    {
    	if (event.harvester != null)
    	{
    		//is broken by person
    		ItemStack tool = event.harvester.getCurrentEquippedItem();
    		if (tool != null)
    		{
    			//is broken with a tool
    			if (CS.ToolsGT.contains(CS.TOOL_hammer, tool))
        		{
    				//are holding hammer
    				ItemStack[] drops = event.drops.toArray(new ItemStack[event.drops.size()]);
    				if (OP.ore.contains(drops) || OP.crushed.contains(drops))
    				{
    					issueAchievement(event.harvester, "crushOre");
    				}
        		} else if (OreDictManager.isItemStackInstanceOf(tool, "toolKnuckles")) {
        			ItemStack[] drops = event.drops.toArray(new ItemStack[event.drops.size()]);
    				if (OP.dust.contains(drops) || OP.blockDust.contains(drops))
    				{
    					issueAchievement(event.harvester, "knuckleDuster");
    				}
        		}
    		}
    	}
    }
    
    //@SubscribeEvent
    public void onExploded(ExplosionEvent.Detonate event)
    {
    	TileEntity source = event.world.getTileEntity((int)event.explosion.explosionX, (int)event.explosion.explosionY - 1, (int)event.explosion.explosionZ);
    	if (source != null)
    	{
    		//System.out.println("Found a TileEntity at the base of the explosion");
    		if (source.getClass().getName().contains("MultiTileEntityGenerator"))
    		{
    			//System.out.println("It's on a Generator");
    			List<Entity> entities = event.getAffectedEntities();
    			for (int q = 0; q < entities.size(); q++) {
    				//System.out.println("It's affecting a " + entities.get(q).getEntityId());
    				if (entities.get(q) instanceof EntityPlayer)
    				{
    					//System.out.println("IT'S A PLAYER");
    					issueAchievement((EntityPlayerMP)entities.get(q), "explodeOverheat");
    				}
    			}
    		} else {
    			//System.out.println("It was just a " + source.getClass().getName());
    		}
    	}
    }
    
    //@SubscribeEvent
    public void onBroken(PlayerDestroyItemEvent event)
    {
    	if (event.original.hasTagCompound() && event.original.getTagCompound().hasKey("QT.ToolData"))
    	{
    		if (event.original.getTagCompound().getCompoundTag("QT.ToolData").getBoolean("Rusted"))
    		{
    			issueAchievement(event.entityPlayer, "rustyBreak");
    		}
    	}
    }
    
    //@SubscribeEvent
    public ItemStack onItemFinish(PlayerUseItemEvent.Stop event)
    {
    	if (OreDictManager.isItemStackInstanceOf(event.item, "toolSlingshot") && (72000 - event.duration) > 10) { //useless for now
    		Behavior_Slingshot BS = (Behavior_Slingshot)qwerTool.mItemBehaviors.get((short)event.item.getItemDamage()).get(0);
    		//System.out.println("Slingshot ended at duration" + event.duration);
			ItemStack ammo = null;
			short type = 0;
			int slot = -1;
			ItemStack[] inventory = event.entityPlayer.inventory.mainInventory;
			for (int q = 0; q < inventory.length; q++)
			{
				if (inventory[q] != null) {
					if (OP.rockGt.contains(inventory[q]))
					{
						ammo = inventory[q];
						slot = q;
						type = 0;
						break;
					} else if (OP.chunkGt.contains(inventory[q])) {
						ammo = inventory[q];
						slot = q;
						type = 1;
						break;
					} else if (OP.foil.contains(inventory[q])) {
						ammo = inventory[q];
						slot = q;
						type = 2;
						break;
					} else if ((inventory[q]).getItem() == Items.egg) {
						ammo = inventory[q];
						slot = q;
						type = 3;
						break;
					} else if ((inventory[q]).getItem() == Items.slime_ball) {
						ammo = inventory[q];
						slot = q;
						type = 4;
						break;
					} else if ((inventory[q]).getItem() == IL.Mud_Ball.getItem() && (inventory[q]).getItemDamage() == IL.Mud_Ball.get(1, (Object[])null).getItemDamage()) {
						ammo = inventory[q];
						slot = q;
						type = 5;
						break;
					} else if (OreDictManager.isItemStackInstanceOf(inventory[q], "cropTomato"))
					{
						ammo = inventory[q];
						slot = q;
						type = 6;
					} else {
						//do nothing, keep going
					}
				}
			}
			if (ammo != null)
			{
				if ((BS.mDamage > 0L) && (!UT.Entities.hasInfiniteItems(event.entityPlayer))) {
					((MultiItemTool)event.item.getItem()).doDamage(event.item, UT.Code.units(BS.mDamage, 10000L, BS.mDamage, true), event.entityPlayer);
					ammo.stackSize = ammo.stackSize - 1;
					if (ammo.stackSize < 1) {
						event.entityPlayer.inventory.setInventorySlotContents(slot, null);
					} else {
						event.entityPlayer.inventory.setInventorySlotContents(slot, ammo);
					}
				}
				if (BS.mSoundName != null) {
					UT.Sounds.send(event.entityPlayer.worldObj, BS.mSoundName, 1.0F, 1.0F, (int)event.entityPlayer.posX, (int)event.entityPlayer.posY, (int)event.entityPlayer.posZ);
				}
				if (!(event.entityPlayer.worldObj.isRemote)) {
					if (type == (short)3) {
						event.entityPlayer.worldObj.spawnEntityInWorld(new EntityEgg(event.entityPlayer.worldObj, event.entityPlayer));
					} else if (type >= (short)4 && type <= (short)6) {
						EntityBall entityBall = new EntityBall(event.entityPlayer.worldObj, event.entityPlayer, Math.min(50, 72000 - event.duration)/20, MT.Empty, type);
						if (MultiItemTool.getPrimaryMaterial(event.item).contains(TD.Properties.BURNING))
						{
							entityBall.setFire(20);
						}
						event.entityPlayer.worldObj.spawnEntityInWorld(entityBall);
					} else if (type == (short)2) {
						EntityFoil entityFoil = new EntityFoil(event.entityPlayer.worldObj, event.entityPlayer, Math.min(50, 72000 - event.duration)/20, OM.anydata(ammo).mMaterial.mMaterial, type);
						if (MultiItemTool.getPrimaryMaterial(event.item).contains(TD.Properties.BURNING))
						{
							entityFoil.setFire(20);
						}
						event.entityPlayer.worldObj.spawnEntityInWorld(entityFoil);
					} else if (type == (short)1 || type == (short)0) {
						EntityRock entityRock = new EntityRock(event.entityPlayer.worldObj, event.entityPlayer, Math.min(50, 72000 - event.duration)/25, OM.anydata(ammo).mMaterial.mMaterial, type);
						if (MultiItemTool.getPrimaryMaterial(event.item).contains(TD.Properties.BURNING))
						{
							entityRock.setFire(20);
						}
						event.entityPlayer.worldObj.spawnEntityInWorld(entityRock);
					}
				}
			}
		}
    	return event.item;
    }
    
}
