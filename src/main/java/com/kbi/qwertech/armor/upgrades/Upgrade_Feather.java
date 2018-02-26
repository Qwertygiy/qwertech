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
import com.kbi.qwertech.client.models.ModelArmorMonocle;
import com.kbi.qwertech.client.models.ModelArmorSpring;
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class Upgrade_Feather extends UpgradeBase {

	public Upgrade_Feather() {
	}
	
	@Override
	public boolean isValidInSlot(int slot)
	{
		return slot == 0;
	}
	
	@Override
	public int getRenderPasses() {
		return 1;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return type == "overlay" ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/featherwhite.png";
	}
	
	Object model;
	
	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity, int slot)
	{
		if (slot != 0) return null;
		if (model == null)
		{
			model = new ModelArmorFeather();
		}
		return (ModelBiped)model;
	}
	
	public short[] color = MT.Empty.mRGBaSolid;
	
	@Override
	public short[] getRGBa(ItemStack tStack, int pass)
	{
		return color;
	}
	
	public void setRGBa(short[] colorable)
	{
		color = colorable;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass)
	{
		return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/helmet/feather")).getIcon(aRenderPass);
	}
	
	@Override
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
	{
		aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Dashing");
		return aList;
	}

}
