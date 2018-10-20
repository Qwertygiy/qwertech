package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.recipe.RepairRecipe;
import com.kbi.qwertech.api.recipe.managers.CraftingManagerHammer;
import com.kbi.qwertech.api.registry.MobBloodRegistry;
import com.kbi.qwertech.api.registry.MobScrapeRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ModData;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ModLoad_TF extends ModLoadBase {
	
	@Override
	public ModData getMod()
	{
		return MD.TF;
	}

	@Override
	public void registerMobScrapes()
	{
		Class adderble;
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFBlockGoblin");
			MobScrapeRegistry.registerMobKey(adderble, "BlockGoblin");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Block Goblins weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFFireBeetle");
			MobScrapeRegistry.registerMobKey(adderble, "FireBeetle");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Fire Beetles weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFGoblinKnightLower");
			MobScrapeRegistry.registerMobKey(adderble, "GoblinKnight");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Goblin Knights weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFHedgeSpider");
			MobScrapeRegistry.registerMobKey(adderble, "HedgeSpider");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Hedge Spiders weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFHelmetCrab");
			MobScrapeRegistry.registerMobKey(adderble, "HelmetCrab");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Helmet Crabs weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFIceExploder");
			MobScrapeRegistry.registerMobKey(adderble, "IceExploder");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Ice Exploders weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFIceShooter");
			MobScrapeRegistry.registerMobKey(adderble, "IceShooter");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Ice Shooters weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFKingSpider");
			MobScrapeRegistry.registerMobKey(adderble, "KingSpider");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but King Spiders weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFKobold");
			MobScrapeRegistry.registerMobKey(adderble, "Kobold");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Kobolds weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFTowerGhast");
			MobScrapeRegistry.registerMobKey(adderble, "TowerGhast");
			MobBloodRegistry.registerMobKey(adderble, "TowerGhast");
			MobBloodRegistry.removeBlood("TowerGhast");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Tower Ghasts weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFMiniGhast");
			MobScrapeRegistry.registerMobKey(adderble, "MiniGhast");
			MobBloodRegistry.registerMobKey(adderble, "MiniGhast");
			MobBloodRegistry.removeBlood("MiniGhast");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Mini Ghasts weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFMinotaur");
			MobScrapeRegistry.registerMobKey(adderble, "Minotaur");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Minotaurs weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFRedcap");
			MobScrapeRegistry.registerMobKey(adderble, "Redcap");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Redcaps weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFRedcapSapper");
			MobScrapeRegistry.registerMobKey(adderble, "RedcapSapper");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Redcap Sappers weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFSkeletonDruid");
			MobScrapeRegistry.registerMobKey(adderble, "SkeletonDruid");
			MobBloodRegistry.registerMobKey(adderble, "SkeletonDruid");
			MobBloodRegistry.removeBlood("SkeletonDruid");
			try {
				Class TFItems = Class.forName("twilightforest.item.TFItems");
				Object torchberries = new Object();
				torchberries = TFItems.getField("torchberries").get(torchberries);
				MobScrapeRegistry.registerItem("SkeletonDruid", ST.make((Item)torchberries, 1, 0));
			} catch (Exception e) {
				System.out.println("Could not register torchberry drop for Skeleton Druid");
			}
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Skeleton Druids weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFSlimeBeetle");
			MobScrapeRegistry.registerMobKey(adderble, "SlimeBeetle");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Slime Beetles weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFSnowGuardian");
			MobScrapeRegistry.registerMobKey(adderble, "SnowGuardian");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Snow Guardians weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFSwarmSpider");
			MobScrapeRegistry.registerMobKey(adderble, "SwarmSpider");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Swarm Spiders weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFTowerBroodling");
			MobScrapeRegistry.registerMobKey(adderble, "TowerBroodling");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Tower Broodlings weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFTowerTermite");
			MobScrapeRegistry.registerMobKey(adderble, "TowerTermite");
			try {
				Class TFItems = Class.forName("twilightforest.item.TFItems");
				Object borerEssence = new Object();
				borerEssence = TFItems.getField("borerEssence").get(borerEssence);
				MobScrapeRegistry.registerItem("TowerTermite", ST.make((Item)borerEssence, 1, 0));
			} catch (Exception e) {
				System.out.println("Could not register borer essence drop for Tower Termite");
			}
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Tower Termites weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFWinterWolf");
			MobScrapeRegistry.registerMobKey(adderble, "WinterWolf");
			try {
				Class TFItems = Class.forName("twilightforest.item.TFItems");
				Object arcticFur = new Object();
				arcticFur = TFItems.getField("arcticFur").get(arcticFur);
				MobScrapeRegistry.registerItem("WinterWolf", ST.make((Item)arcticFur, 1, 0));
			} catch (Exception e) {
				System.out.println("Could not register arctic fur drop for Winter Wolf");
			}
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Winter Wolves weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFWraith");
			MobScrapeRegistry.registerMobKey(adderble, "Wraith");
			MobBloodRegistry.registerMobKey(adderble, "Wraith");
			MobBloodRegistry.removeBlood("Wraith");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Wraiths weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.EntityTFYeti");
			MobScrapeRegistry.registerMobKey(adderble, "Yeti");
			try {
				Class TFItems = Class.forName("twilightforest.item.TFItems");
				Object arcticFur = new Object();
				arcticFur = TFItems.getField("arcticFur").get(arcticFur);
				MobScrapeRegistry.registerItem("Yeti", ST.make((Item)arcticFur, 1, 0));
			} catch (Exception e) {
				System.out.println("Could not register arctic fur drop for Yeti");
			}
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Yetis weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFBighorn");
			MobScrapeRegistry.registerMobKey(adderble, "Bighorn");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Bighorns weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFBird");
			MobScrapeRegistry.registerMobKey(adderble, "TFBird");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Birds weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFBoar");
			MobScrapeRegistry.registerMobKey(adderble, "TFBoar");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Boars weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFBunny");
			MobScrapeRegistry.registerMobKey(adderble, "TFBunny");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Bunnies weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFDeer");
			MobScrapeRegistry.registerMobKey(adderble, "TFDeer");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Deer weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFPenguin");
			MobScrapeRegistry.registerMobKey(adderble, "TFPenguin");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Penguins weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFQuestRam");
			MobScrapeRegistry.registerMobKey(adderble, "QuestRam");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but the Questing Ram wasn't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFRaven");
			MobScrapeRegistry.registerMobKey(adderble, "TFRaven");
			try {
				Class TFItems = Class.forName("twilightforest.item.TFItems");
				Object feather = new Object();
				feather = TFItems.getField("feather").get(feather);
				MobScrapeRegistry.registerItem("TFRaven", ST.make((Item)feather, 1, 0));
			} catch (Exception e) {
				System.out.println("Could not register feather drop for Raven");
			}
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Ravens weren't!");
		}
		try {
			adderble = Class.forName("twilightforest.entity.passive.EntityTFSquirrel");
			MobScrapeRegistry.registerMobKey(adderble, "TFSquirrel");
		} catch (Exception e) {
			System.out.println("Twilight Forest was found but Squirrels weren't!");
		}
		
		
		MobScrapeRegistry.registerItem("BlockGoblin", OP.scrapGt.mat(MT.Knightmetal, 1));
		MobScrapeRegistry.registerItem("GoblinKnight", OP.scrapGt.mat(MT.Knightmetal, 1));
		MobScrapeRegistry.registerItem("HelmetCrab", OP.scrapGt.mat(MT.Knightmetal, 1));
		MobScrapeRegistry.registerItem("FireBeetle", OP.dustSmall.mat(MT.Gunpowder, 1));
		MobScrapeRegistry.registerItem("HedgeSpider", ST.make(Items.string, 1, 0));
		MobScrapeRegistry.registerItem("KingSpider", ST.make(Items.string, 1, 0));
		MobScrapeRegistry.registerItem("SwarmSpider", ST.make(Items.string, 1, 0));
		MobScrapeRegistry.registerItem("TowerBroodling", ST.make(Items.string, 1, 0));
		MobScrapeRegistry.registerItem("IceExploder", OP.dust.mat(MT.Snow, 1));
		MobScrapeRegistry.registerItem("IceShooter", OP.dust.mat(MT.Snow, 1));
		MobScrapeRegistry.registerItem("SnowGuardian", OP.dust.mat(MT.Snow, 1));
		MobScrapeRegistry.registerItem("Kobold", OP.dustSmall.mat(MT.Wheat, 1));
		MobScrapeRegistry.registerItem("MiniGhast", OP.dustSmall.mat(MT.Gunpowder, 1));
		MobScrapeRegistry.registerItem("TowerGhast", OP.dustSmall.mat(MT.Gunpowder, 1));
		MobScrapeRegistry.registerItem("Minotaur", OP.scrapGt.mat(MT.MeatRaw, 1));
		MobScrapeRegistry.registerItem("Redcap", OP.dustSmall.mat(MT.Coal, 1));
		MobScrapeRegistry.registerItem("RedcapSapper", OP.dustSmall.mat(MT.Coal, 1));
		MobScrapeRegistry.registerItem("SkeletonDruid", ST.make(Items.dye, 1, 15), 1);
		MobScrapeRegistry.registerItem("SlimeBeetle", ST.make(Items.slime_ball, 1, 0));
		MobScrapeRegistry.registerItem("Wraith", OP.dustSmall.mat(MT.Glowstone, 1));
		MobScrapeRegistry.registerItem("Bighorn", ST.make(Items.string, 1, 0), 2);
		MobScrapeRegistry.registerItem("Bighorn", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("TFBird", ST.make(Items.feather, 1, 0), 1);
		MobScrapeRegistry.registerItem("TFBoar", OP.scrapGt.mat(MT.MeatRaw, 1), 10);
		MobScrapeRegistry.registerItem("TFBoar", IL.Food_Bacon_Raw.get(1, ST.make(Items.porkchop, 1, 0)), 1);
		MobScrapeRegistry.registerItem("TFDeer", OP.scrapGt.mat(MT.MeatRaw, 1), 1);
		MobScrapeRegistry.registerItem("TFPenguin", ST.make(Items.feather, 1, 0), 1);
		MobScrapeRegistry.registerItem("QuestRam", ST.make(Blocks.wool, 2, 15), 1);
		MobScrapeRegistry.registerItem("QuestRam", ST.make(Blocks.wool, 2, 3), 1);
		
		if (MD.EtFu.mLoaded) {
			MobScrapeRegistry.registerItem("TFBunny", IL.EtFu_Rabbit_Foot.get(1,(Object[])null), 1);
		}
		
		if (OreDictionary.doesOreNameExist("seedOak"))
		{
			MobScrapeRegistry.registerItem("TFSquirrel", OreDictionary.getOres("seedOak").get(0));
		} else if (OreDictionary.doesOreNameExist("seedAcorn"))
		{
			MobScrapeRegistry.registerItem("TFSquirrel", OreDictionary.getOres("seedAcorn").get(0));
		}
	}

	@Override
	public void addOreDict()
	{
		Block towerWood = GameRegistry.findBlock(this.getMod().mID, "TFTowerStone");
		if (towerWood != null)
		{
			OreDictionary.registerOre("plankWoodTowerwood", ST.make(towerWood, 1, 0));
		}
	}
	
	@Override
	public void tweakRecipes()
	{
		CraftingManagerHammer hammer = CraftingManagerHammer.getInstance();
		try {
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodHelm"), new OreDictMaterialStack(MT.IronWood, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodPlate"), new OreDictMaterialStack(MT.IronWood, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodLegs"), new OreDictMaterialStack(MT.IronWood, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodBoots"), new OreDictMaterialStack(MT.IronWood, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodSword"), new OreDictMaterialStack(MT.IronWood, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodShovel"), new OreDictMaterialStack(MT.IronWood, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodPick"), new OreDictMaterialStack(MT.IronWood, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodAxe"), new OreDictMaterialStack(MT.IronWood, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.ironwoodHoe"), new OreDictMaterialStack(MT.IronWood, CS.U * 2)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.fieryHelm"), new OreDictMaterialStack(MT.FierySteel, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.fieryPlate"), new OreDictMaterialStack(MT.FierySteel, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.fieryLegs"), new OreDictMaterialStack(MT.FierySteel, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.fieryBoots"), new OreDictMaterialStack(MT.FierySteel, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.fierySword"), new OreDictMaterialStack(MT.FierySteel, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.fieryPick"), new OreDictMaterialStack(MT.FierySteel, CS.U * 3)));

			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafHelm"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafPlate"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafLegs"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafBoots"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 4)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafSword"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafShovel"), new OreDictMaterialStack(MT.Steeleaf, CS.U)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafPick"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafAxe"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.steeleafHoe"), new OreDictMaterialStack(MT.Steeleaf, CS.U * 2)));
			
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlyHelm"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 5)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlyPlate"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 8)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlyLegs"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 7)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlyBoots"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 4)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlySword"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 2)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlyPick"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 3)));
			hammer.addRecipe(new RepairRecipe(GameRegistry.findItem(this.getMod().mID, "item.knightlyAxe"), new OreDictMaterialStack(MT.Knightmetal, CS.U * 3)));
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}
