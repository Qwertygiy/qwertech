package com.kbi.qwertech.armor.upgrades;

import java.util.List;

import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.client.models.ModelArmorFeather;
import com.kbi.qwertech.client.models.ModelArmorPlates;
import com.kbi.qwertech.client.models.ModelArmorSpring;
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class Upgrade_Plate extends UpgradeBase {

	public Upgrade_Plate() {
	}
	
	@Override
	public boolean isValidInSlot(int slot)
	{
		return true;
	}
	
	@Override
	public int getRenderPasses() {
		return 1;
	}
	
	public Enchantment enchant = Enchantment.protection;
	
	@Override
	public Enchantment[] getEnchantments(ItemStack stack, OreDictMaterial mat)
	{
		return new Enchantment[]{enchant};
	}
	
	public Upgrade_Plate setEnchantment(Enchantment enchantable)
	{
		enchant = enchantable;
		return this;
	}
	
	@Override
	public int[] getEnchantmentLevels(ItemStack stack)
	{
		return new int[]{this.getMaterial().mToolQuality};
	}
	
	@Override
	public long getMaxDurabilityMultiplier()
	{
		return 10;
	}
	
	@Override
	public double getWeightAdded()
	{
		return 1;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass)
	{
		String[] slots = new String[]{"boots", "leggings", "chestplate", "helmet"};
		String slot = "chestplate";
		for (int q = 0; q < 4; q++)
		{
			IArmorStats stats = ((MultiItemArmor)aStack.getItem()).getArmorStats(aStack);
			if (stats != null && stats.isValidInSlot(3 - q))
			{
				slot = slots[q];
			}
		}
		return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/" + slot + "/curvedPlate")).getIcon(aRenderPass);
	}
	
	@Override
	public short[] getRGBa(ItemStack aStack, int aRenderPass) {
		return this.getMaterial().mRGBaSolid;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return type == "overlay" ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/plate.png";
	}
	
	Object model;
	
	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity, int slot)
	{
		if (model == null)
		{
			model = new ModelArmorPlates();
		}
		ModelArmorPlates m = (ModelArmorPlates)model;
		
		m.bipedBodyPlate.isHidden = slot != 1;
		m.bipedHeadPlate.isHidden = slot != 0;
		m.bipedLeftBootPlate.isHidden = slot != 3;
		m.bipedRightBootPlate.isHidden = slot != 3;
		m.bipedLegPlate.isHidden = slot != 2;
		
		return (ModelBiped)model;
	}
	
	@Override
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
	{
		switch(this.getMaterial().mToolQuality)
		{
			case 0:
				aList.add(LH.Chat.ITALIC + LH.Chat.DGRAY + "Reinforced");
				break;
			case 1:
				aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Reinforced");
				break;
			case 2:
				aList.add(LH.Chat.ITALIC + LH.Chat.PURPLE + "Reinforced");
				break;
			default:
				aList.add(LH.Chat.ITALIC + LH.Chat.DBLUE + "Reinforced");
				break;
		} 
		return aList;
	}

}
