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
import com.kbi.qwertech.client.models.ModelArmorSpring;
import com.kbi.qwertech.client.models.ModelArmorSpurs;
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class Upgrade_Shuriken extends UpgradeBase {

	public Upgrade_Shuriken() {
	}
	
	@Override
	public boolean isValidInSlot(int slot)
	{
		return slot == 3;
	}
	
	@Override
	public int getRenderPasses() {
		return 1;
	}
	
	public Enchantment enchant = Enchantment.thorns;
	
	@Override
	public Enchantment[] getEnchantments(ItemStack stack, OreDictMaterial mat)
	{
		return new Enchantment[]{enchant};
	}
	
	public Upgrade_Shuriken setEnchantment(Enchantment enchantable)
	{
		enchant = enchantable;
		return this;
	}
	
	@Override
	public int[] getEnchantmentLevels(ItemStack stack)
	{
		return new int[]{1};
	}
	
	@Override
	public long getMaxDurabilityMultiplier()
	{
		return 0;
	}
	
	@Override
	public double getWeightAdded()
	{
		return 1;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass)
	{
		return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/boots/spur")).getIcon(0);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return type == "overlay" ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/spur.png";
	}
	
	Object model;
	
	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity, int slot)
	{
		if (slot != 3) return null;
		if (model == null)
		{
			model = new ModelArmorSpurs();
		}
		return (ModelBiped)model;
	}
	
	@Override
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
	{

		aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Spurred");
		return aList;
	}

}
