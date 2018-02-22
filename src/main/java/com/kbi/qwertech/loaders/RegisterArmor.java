package com.kbi.qwertech.loaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;
import gregapi.data.CS;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.IIconContainer;
import gregapi.util.ST;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.armor.upgrades.IArmorUpgrade;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.armor.ArmorIcon;
import com.kbi.qwertech.armor.BootBase;
import com.kbi.qwertech.armor.BootPlate;
import com.kbi.qwertech.armor.ChestBase;
import com.kbi.qwertech.armor.ChestPlate;
import com.kbi.qwertech.armor.HelmetBase;
import com.kbi.qwertech.armor.HelmetPlate;
import com.kbi.qwertech.armor.PantBase;
import com.kbi.qwertech.armor.PantPlate;
import com.kbi.qwertech.armor.upgrades.Upgrade_Feather;
import com.kbi.qwertech.armor.upgrades.Upgrade_Lubricant;
import com.kbi.qwertech.armor.upgrades.Upgrade_Magnifier;
import com.kbi.qwertech.armor.upgrades.Upgrade_Plate;
import com.kbi.qwertech.armor.upgrades.Upgrade_Shuriken;
import com.kbi.qwertech.armor.upgrades.Upgrade_Slime;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RegisterArmor {
	
	private static MultiItemArmor armor;
	public static HashMap<String, Object> iconTitle = new HashMap();
	private static List<String> types = new ArrayList();
	public static RegisterArmor instance;
	
	public RegisterArmor()
	{
		instance = this;
		MT.Bronze.addEnchantmentForArmors(Enchantment.protection, 1);
		MT.Steel.addEnchantmentForArmors(Enchantment.fireProtection, 1);
		MT.BlackBronze.addEnchantmentForArmors(Enchantment.protection, 2);
		MT.BlueSteel.addEnchantmentForArmors(Enchantment.blastProtection, 3);
		MT.ObsidianSteel.addEnchantmentForArmors(Enchantment.blastProtection, 2);
		MT.RedSteel.addEnchantmentForArmors(Enchantment.projectileProtection, 3);
		MT.BlackSteel.addEnchantmentForArmors(Enchantment.protection, 3);
		MT.BlackSteel.addEnchantmentForArmors(Enchantment.thorns, 1);
		MT.DamascusSteel.addEnchantmentForArmors(Enchantment.featherFalling, 2);
		MT.DamascusSteel.addEnchantmentForArmors(Enchantment.unbreaking, 1);
		MT.TungstenSteel.addEnchantmentForArmors(Enchantment.unbreaking, 2);
		MT.TungstenSteel.addEnchantmentForArmors(Enchantment.fireProtection, 3);
		MT.Ti.addEnchantmentForArmors(Enchantment.thorns, 2);
		MT.Ti.addEnchantmentForArmors(Enchantment.fireProtection, 3);
		MT.TungstenCarbide.addEnchantmentForArmors(Enchantment.fireProtection, 3);
		MT.TungstenCarbide.addEnchantmentForArmors(Enchantment.unbreaking, 2);
		MT.TungstenSintered.addEnchantmentForArmors(Enchantment.fireProtection, 3);
		MT.TungstenSintered.addEnchantmentForArmors(Enchantment.unbreaking, 3);
		MT.Ag.addEnchantmentForArmors(Enchantment.aquaAffinity, 1);
		MT.Ag.addEnchantmentForArmors(Enchantment.respiration, 1);
		MT.Cr.addEnchantmentForArmors(Enchantment.fireProtection, 2);
		MT.SteelGalvanized.addEnchantmentForArmors(Enchantment.fireProtection, 2);
		MT.SteelGalvanized.addEnchantmentForArmors(Enchantment.blastProtection, 1);
		MT.StainlessSteel.addEnchantmentForArmors(Enchantment.fireProtection, 3);
		MT.StainlessSteel.addEnchantmentForArmors(Enchantment.blastProtection, 2);
		MT.Thaumium.addEnchantmentForArmors(Enchantment.aquaAffinity, 2);
		MT.Manasteel.addEnchantmentForArmors(Enchantment.aquaAffinity, 2);
		MT.IronWood.addEnchantmentForArmors(Enchantment.respiration, 1);
		
		addType("chainmail");
		addType("plate");
		addType("springs");
		addType("lube");
		addType("slime");
		addType("curvedPlate");
		addType("monocle");
		addType("spur");
		addType("feather");
		
		try
		{
			registerIcons();
		} catch (Throwable t)
		{
			//Just serverside probably
		}
		
		armor = new MultiItemArmor(MD.QT.mID, "qt.armor");
		QTI.qwerArmor.set(armor);
		armor.addArmor(0, "Chain Helmet", "Rattles worse than bones", new HelmetBase().setMaterialAmount(CS.U * 8), "armorHelmet");
		armor.addArmor(1, "Chainmail Shirt", "Thy mother was a hamster!", new ChestBase().setMaterialAmount(CS.U * 11), "armorChest");
		armor.addArmor(2, "Chainmail Leggings", "Thy father smelt of elderberries!", new PantBase().setMaterialAmount(CS.U * 10), "armorLegs");
		armor.addArmor(3, "Chain Boots", "Linked to the Past", new BootBase().setMaterialAmount(CS.U * 5), "armorBoots");
		armor.addArmor(4, "Plated Helmet", "Always wear your helmet", new HelmetPlate().setMaterialAmount(CS.U * 12), "armorHelmet");
		armor.addArmor(5, "Chestplate", "The classic look", new ChestPlate().setMaterialAmount(CS.U * 15), "armorChest");
		armor.addArmor(6, "Plated Pants", "Not to be confused with pleated pants", new PantPlate().setMaterialAmount(CS.U * 15), "armorLegs");
		armor.addArmor(7, "Plated Boots", "Clanky and clunky", new BootPlate().setMaterialAmount(CS.U * 6), "armorBoots");		
		
		//addUpgrades();
		
		//FMLCommonHandler.instance().bus().register(this);
	}
	
	public void addUpgrades()
	{
		IArmorUpgrade upgrade = new Upgrade_Lubricant();
		upgrade.addUpgradeStack(IL.Bottle_Lubricant.get(1));
		ArmorUpgradeRegistry.instance.addUpgrade(5, upgrade);
		
		upgrade = new Upgrade_Slime();
		upgrade.addUpgradeStack(ST.make(Items.slime_ball, 1, 0));
		ArmorUpgradeRegistry.instance.addUpgrade(6, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.protection);
		upgrade.setMaterial(MT.Bronze);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.Bronze, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(7, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.protection);
		upgrade.setMaterial(MT.Steel);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.Steel, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(8, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.protection);
		upgrade.setMaterial(MT.BlackSteel);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.BlackSteel, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(9, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.blastProtection);
		upgrade.setMaterial(MT.ObsidianSteel);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.ObsidianSteel, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(10, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.blastProtection);
		upgrade.setMaterial(MT.BlueSteel);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.BlueSteel, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(11, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.blastProtection);
		upgrade.setMaterial(MT.SteelGalvanized);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.SteelGalvanized, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(12, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.fireProtection);
		upgrade.setMaterial(MT.StainlessSteel);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.StainlessSteel, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(13, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.fireProtection);
		upgrade.setMaterial(MT.TungstenSteel);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.TungstenSteel, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(14, upgrade);
		
		upgrade = new Upgrade_Plate().setEnchantment(Enchantment.fireProtection);
		upgrade.setMaterial(MT.Ti);
		upgrade.addUpgradeStack(OP.plateCurved.mat(MT.Ti, 1));
		ArmorUpgradeRegistry.instance.addUpgrade(15, upgrade);
		
		upgrade = new Upgrade_Shuriken();
		List<ItemStack> shurikens = OreDictionary.getOres("shurikenAnyIronOrSteel");
		for (ItemStack item : shurikens)
		{
			upgrade.addUpgradeStack(item);
		}
		ArmorUpgradeRegistry.instance.addUpgrade(16, upgrade);
		
		upgrade = new Upgrade_Magnifier();
		upgrade.addUpgradeStack(ST.make(CS.ToolsGT.sMetaTool, 1, 62));
		ArmorUpgradeRegistry.instance.addUpgrade(17, upgrade);
		
		upgrade = new Upgrade_Feather();
		upgrade.addUpgradeStack(ST.make(Items.feather, 1, 0));
		ArmorUpgradeRegistry.instance.addUpgrade(18, upgrade);
	}
	
	private void addType(String type)
	{
		types.add(type);
	}
	
	private void registerIcons()
	{
		for (int q = 0; q < types.size(); q++)
		{
			String type = types.get(q);
			iconTitle.put("qwertech:armor/helmet/" + type, new ArmorIcon("qwertech:armor/helmet/" + type));
			iconTitle.put("qwertech:armor/chestplate/" + type, new ArmorIcon("qwertech:armor/chestplate/" + type));
			iconTitle.put("qwertech:armor/leggings/" + type, new ArmorIcon("qwertech:armor/leggings/" + type));
			iconTitle.put("qwertech:armor/boots/" + type, new ArmorIcon("qwertech:armor/boots/" + type));
		}
		iconTitle.put("weightNone", new ArmorIcon("qwertech:armorui/armor/weightNone"));
		iconTitle.put("weightLittle", new ArmorIcon("qwertech:armorui/armor/weightLittle"));
		iconTitle.put("weightLight", new ArmorIcon("qwertech:armorui/armor/weightLight"));
		iconTitle.put("weightMuch", new ArmorIcon("qwertech:armorui/armor/weightMuch"));
		iconTitle.put("weightSignificant", new ArmorIcon("qwertech:armorui/armor/weightSignificant"));
		iconTitle.put("noShield", new ArmorIcon("qwertech:armorui/armor/noShield"));
		iconTitle.put("woodShield", new ArmorIcon("qwertech:armorui/armor/woodShield"));
		iconTitle.put("bronzeShield", new ArmorIcon("qwertech:armorui/armor/bronzeShield"));
		iconTitle.put("blueMetalShield", new ArmorIcon("qwertech:armorui/armor/blueMetalShield"));
		iconTitle.put("purpleShield", new ArmorIcon("qwertech:armorui/armor/purpleShield"));
		iconTitle.put("brokenWoodShield", new ArmorIcon("qwertech:armorui/armor/brokenWoodShield"));
		iconTitle.put("brokenBronzeShield", new ArmorIcon("qwertech:armorui/armor/brokenBronzeShield"));
		iconTitle.put("brokenBlueMetalShield", new ArmorIcon("qwertech:armorui/armor/brokenBlueMetalShield"));
		iconTitle.put("brokenPurpleShield", new ArmorIcon("qwertech:armorui/armor/brokenPurpleShield"));
	}
	
	public HashMap<EntityLivingBase, Double> entities = new HashMap();
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void setArmorModel(SetArmorModel event)
	{
		if (event.stack != null && event.stack.getItem() instanceof MultiItemArmor)
		{
			/*
			 * Draw our own upgrades, with blackjack and cookers 
			 */
			GL11.glPushMatrix();
			IArmorUpgrade[] upgrades = MultiItemArmor.getUpgrades(event.stack);
			for (int q = 0; q < upgrades.length; q++)
			{
				IArmorUpgrade upgrade = upgrades[q];
				if (upgrade != null)
				{
					ModelBiped drawIt = upgrade.getArmorModel(event.stack, event.entityLiving, 3 - event.slot);
					if (drawIt != null)
					{
						//System.out.println("GOTIT");
						drawIt.onGround = event.entityLiving.getSwingProgress(event.partialRenderTick);
						drawIt.isRiding = event.entity.isRiding();
						drawIt.isChild = event.entityLiving.isChild();
						short[] j = upgrade.getRGBa(event.stack, 0);
						//UPDATE THIS PART LATER D00D
						
						GL11.glPushMatrix();
						
						if (j != null && j.length == 4)
						{
							GL11.glColor4f(j[0], j[1], j[2], j[3]);
						}
						String texture = upgrade.getArmorTexture(event.stack, event.entityLiving, event.slot, null);
						if (texture != null)
						{
							RenderManager.instance.renderEngine.bindTexture(new ResourceLocation(texture));
						}
						drawIt.setLivingAnimations(event.entityLiving, event.entityLiving.limbSwing - event.entityLiving.limbSwingAmount, event.entityLiving.prevLimbSwingAmount + (event.entityLiving.limbSwingAmount - event.entityLiving.prevLimbSwingAmount), 0);
						drawIt.render(event.entityLiving, event.entityLiving.limbSwing - event.entityLiving.limbSwingAmount, event.entityLiving.prevLimbSwingAmount + (event.entityLiving.limbSwingAmount - event.entityLiving.prevLimbSwingAmount), event.entityLiving.ticksExisted, event.entityLiving.rotationYawHead - event.entityLiving.renderYawOffset, event.entityLiving.prevRotationPitch + (event.entityLiving.rotationPitch - event.entityLiving.prevRotationPitch), 0.0625F);
						GL11.glPopMatrix();
					}
				}
			}
			GL11.glPopMatrix();
		}
	}
	
	public void onClickedWearingArmor(PlayerInteractEvent event)
	{
		for (int q = 1; q < 5; q++)
		{
			ItemStack armor = event.entityPlayer.getEquipmentInSlot(q);
			if (armor != null && armor.getItem() instanceof MultiItemArmor)
			{
				((MultiItemArmor)armor.getItem()).onClickedWearing(armor, q, event.world, event.entityPlayer, event.action, event.x, event.y, event.z, event.face, event);
			}
		}
	}
	
	//@SubscribeEvent
	public void updateEntityArmor(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		for (int q = 1; q < 5; q++)
		{
			ItemStack stack = entity.getEquipmentInSlot(q);
			if (stack != null && stack.getItem() instanceof MultiItemArmor)
			{
				((MultiItemArmor)stack.getItem()).onArmorTicked(entity.worldObj, entity, stack);
			}
		}
		if (event.entity.worldObj.getWorldTime() % 100 == 0)
		{
			if (entities.containsKey(entity))
			{
				entities.clear();
			}
			double totalWeight = 0;
			for (int q = 1; q < 5; q++)
			{
				ItemStack stack = entity.getEquipmentInSlot(q);
				if (stack != null && stack.getItem() instanceof MultiItemArmor)
				{
					IArmorStats tStats = ((MultiItemArmor)stack.getItem()).getArmorStats(stack);
					if (tStats != null)
					{
						OreDictMaterial mat = MultiItemArmor.getPrimaryMaterial(stack);
						totalWeight = totalWeight + mat.getWeight(tStats.getMaterialAmount()/500);
					}
				}
			}
			if (totalWeight > 0 && !(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode))
			{
				//System.out.println("Adding weight " + totalWeight);
				entities.put(entity, totalWeight);
			}
		} else {
			if (entities.containsKey(entity))
			{
				double weightToApply = entities.get(entity);
				if (!entity.onGround)
				{
					weightToApply = weightToApply * 0.5;
				}
				double speedEffect = 1;
				double jumpEffect = 0;
				if (weightToApply < 20)
				{
					//do nothing, you're good
				} else if (weightToApply < 30)
				{
					speedEffect = 0.9;
				} else if (weightToApply < 50)
				{
					speedEffect = 0.8;
				} else if (weightToApply < 70)
				{
					speedEffect = 0.7;
					jumpEffect = -0.01;
				} else if (weightToApply < 90)
				{
					speedEffect = 0.5;
					jumpEffect = -0.02;
				} else if (weightToApply < 150) {
					speedEffect = 0.3;
					jumpEffect = -0.03;
				} else {
					speedEffect = 0.1;
					jumpEffect = -0.05;
				}
				if (entity.motionY < 0)
				{
					entity.motionY = entity.motionY + jumpEffect;
				}
				entity.motionX = entity.motionX * speedEffect;
				entity.motionZ = entity.motionZ * speedEffect;
			}
		}
	}
}
