package com.kbi.qwertech.armor.upgrades;

import java.util.List;

import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.client.models.ModelArmorSpring;
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class Upgrade_SpringBoots extends UpgradeBase {

	public Upgrade_SpringBoots(OreDictMaterial mat) {
		setMaterial(mat);
	}
	
	@Override
	public boolean isValidInSlot(int slot)
	{
		return slot == 3;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return type == "overlay" ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/spring.png";
	}
	
	Object model;
	
	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity, int slot)
	{
		if (slot != 3) return null;
		if (model == null)
		{
			model = new ModelArmorSpring();
		}
		return (ModelBiped)model;
	}
	
	@Override
	public boolean onFallingDamage(World world, EntityLivingBase entity, ItemStack stack, DamageSource source, float amount, LivingHurtEvent event)
	{
		entity.motionY = Math.min(Math.abs(entity.motionY) + (amount * 0.3), this.getMaterial().mToolQuality);
		UT.Sounds.send(world, "qwertech:armor.upgrade.spring", 0.4F, 1 + CS.RNGSUS.nextFloat(), entity);
		if (amount >= 20) 
		{
			amount = 1000;
			event.ammount = amount;
		} else
		{
			((MultiItemArmor)stack.getItem()).doDamage(stack, (long)(amount * 50));
			event.setResult(Result.DENY);
			event.setCanceled(true);
		}
		return true;
	}
	
	@Override
	public int getRenderPasses() {
		return 1;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass)
	{
		return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/boots/springs")).getIcon(aRenderPass);
	}
	
	@Override
	public short[] getRGBa(ItemStack aStack, int aRenderPass)
	{
		return aRenderPass == 0 ? this.getMaterial().mRGBaSolid : MT.Empty.mRGBaSolid;
	}
	
	@Override
	public void onArmorTick(World world, EntityLivingBase player, ItemStack stack)
	{
		if (!player.isPotionActive(Potion.jump))
		{
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 100, this.getMaterial().mToolQuality, true));
		}
		if (player.onGround)
		{
			if (player.isSprinting())
			{
				if (player instanceof EntityPlayer)
				{
					((EntityPlayer)player).jump();
				} else {
					player.setJumping(true);
				}
				UT.Sounds.send(world, "qwertech:armor.upgrade.spring", 0.4F, 1 + CS.RNGSUS.nextFloat(), player);
				((MultiItemArmor)stack.getItem()).doDamage(stack, CS.RNGSUS.nextInt(10));
			}
		} else if (!player.isInWater()) {
			//work on legs later
		}
	}
	
	@Override
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
	{
		aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Applies Jump Boost");
		return aList;
	}

}
