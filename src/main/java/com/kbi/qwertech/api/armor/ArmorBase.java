package com.kbi.qwertech.api.armor;

import gregapi.data.CS;
import gregapi.data.CS.*;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import static gregapi.data.CS.*;

/**
 * ArmorBase
 * @author Qwertygiy
 *
 * A basic, default implementation of IArmorStats to build off of.
 */
public class ArmorBase implements IArmorStats {

	private long mMaterialAmount = 0;

	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity,
			int slot) {
		return null;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "qwertech:textures/armor/plate.png";
	}
	
	@Override
	public int getBaseQuality()
	{
		return 0;
	}
	
	@Override
	public String getBreakingSound() {
		return CS.SFX.MC_BREAK;
	}
	
	@Override
	public ItemStack getBrokenItem(ItemStack aStack) {
		return mMaterialAmount < U4 ? null : OP.scrapGt.mat(MultiItemArmor.getPrimaryMaterial(aStack, MT.NULL), 1+RNGSUS.nextInt(1+(int)(4*mMaterialAmount/U)));
	}
	
	@Override
	public float getDamageProtection()
	{
		return 0.9F;
	}

	@Override
	public int[] getEnchantmentLevels(ItemStack aStack) {
		// TODO Auto-generated method stub
		return CS.ZL_INTEGER;
	}

	@Override
	public Enchantment[] getEnchantments(ItemStack aStack,
			OreDictMaterial aMaterial) {
		// TODO Auto-generated method stub
		return ZL_ENCHANTMENT;
	}
	
	public IIconContainer getIcon(ItemStack aStack) {
		return Textures.ItemIcons.VOID;
	}

	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		switch(aRenderPass) {
		case 0: return getIcon(aStack).getIcon(0);
		case 1: return getIcon(aStack).getIcon(1);
		}
		return null;
	}

	@Override
	public String getImpactSound() {
		return SFX.MC_DIG_ROCK;
	}

	@Override
	public long getMaterialAmount()
	{
		return mMaterialAmount;
	}

	@Override
	public long getMaxDurabilityMultiplier() {
		return 100;
	}
	
	@Override
	public int getRenderPasses() {
		return 2;
	}

	@Override
	public short[] getRGBa(ItemStack aStack, int aRenderPass) {
		switch(aRenderPass) {
			case 0: return MultiItemArmor.getPrimaryMaterial(aStack).mRGBaSolid;
			case 1: return UNCOLOURED;
		}
		return UNCOLOURED;
	}

	@Override
	public boolean isValidInSlot(int armorType) {
		return false;
	}

	@Override
	public boolean onAnimalDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onAnyDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public void onArmorCrafted(ItemStack aStack, EntityPlayer aPlayer) {
	}

	@Override
	public void onArmorTick(World world, EntityLivingBase player, ItemStack stack) {
	}

	@Override
	public void onBumbleDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
	}

	@Override
	public void onChemDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
	}

	@Override
	public boolean onDrowningDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onExplosionDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onFallingDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onFireDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public void onFrostDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
	}

	@Override
	public void onHeatDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
	}

	@Override
	public boolean onHostileDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onHungerDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onLavaDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onMagicDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onPlayerDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean onProjectileDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public void onSpikeDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
	}

	@Override
	public void onStatsAddedToArmor(MultiItemArmor armor, int ID) {

	}

	@Override
	public boolean onWitherDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {
		return false;
	}

	@Override
	public boolean renderHelmetOverlay(ItemStack stack, EntityPlayer player,
			ScaledResolution resolution, float partialTicks, boolean hasScreen,
			int mouseX, int mouseY) {
		return false;
	}

	@Override
	public ArmorBase setMaterialAmount(long aMaterialAmount) {
		mMaterialAmount = aMaterialAmount;
		return this;
	}

	@Override
	public int baseUpgrades() {
		return 0;
	}

	@Override
	public boolean onClickedWearing(ItemStack armorStack, int slot,
			World world, EntityPlayer player, Action action, int x, int y,
			int z, int face, PlayerInteractEvent event) {
		return false;
	}

}
