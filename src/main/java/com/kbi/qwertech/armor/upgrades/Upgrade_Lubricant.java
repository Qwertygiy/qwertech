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
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class Upgrade_Lubricant extends UpgradeBase {

	public Upgrade_Lubricant() {
	}
	
	@Override
	public boolean isValidInSlot(int slot)
	{
		return slot > 1;
	}
	
	@Override
	public int getRenderPasses() {
		return 1;
	}
	
	@Override
	public OreDictMaterial getMaterial()
	{
		return MT.Fe;
	}
	
	@Override
	public double getWeightAdded()
	{
		return -2;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass)
	{
		String[] slots = new String[]{"boots", "leggings", "chestplate", "helmet"};
		String slot = "chestplate";
		for (int q = 0; q < 4; q++)
		{
			IArmorStats stats = ((MultiItemArmor)aStack.getItem()).getArmorStats(aStack);
			if (stats != null && stats.isValidInSlot(q))
			{
				slot = slots[q];
			}
		}
		return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/" + slot + "/lube")).getIcon(aRenderPass);
	}
	
	DamageSource tripped = new DamageSource("trip");
	
	@Override
	public void onArmorTick(World world, EntityLivingBase player, ItemStack stack)
	{
		int tX = MathHelper.floor_double(player.posX), tY = MathHelper.floor_double(player.boundingBox.minY-0.001F), tZ = MathHelper.floor_double(player.posZ);
		Block tBlock = world.getBlock(tX, tY, tZ);
		Material mat = tBlock.getMaterial();
		if (mat == Material.iron || mat == Material.rock || mat == Material.snow || mat == Material.wood || mat == Material.glass)
		{
			double maxSpeed = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
			player.motionX = Math.max(Math.min(player.motionX * 1.5, maxSpeed * 1.1), -maxSpeed * 1.1);
			player.motionZ = Math.max(Math.min(player.motionZ * 1.5, maxSpeed * 1.1), -maxSpeed * 1.1);
			if (CS.RNGSUS.nextInt(2000) == 0 && player.getEquipmentInSlot(1) == stack)
			{
				if (!world.isRemote)
				{
					player.attackEntityFrom(tripped, 4.0F);
					player.setJumping(true);
					if (player instanceof EntityPlayer)
					{
						((EntityPlayer) player).jump();
					}
				}
			}
		}
	}
	
	@Override
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
	{
		aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Slippery");
		return aList;
	}

}
