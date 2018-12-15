package com.kbi.qwertech.api.armor.upgrades;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import gregapi.data.CS;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
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

import java.util.ArrayList;
import java.util.List;

public class UpgradeBase implements IArmorUpgrade {


	@Override
	public boolean renderHelmetOverlay(ItemStack stack, EntityPlayer player,
			ScaledResolution resolution, float partialTicks, boolean hasScreen,
			int mouseX, int mouseY) {
		return false;
	}

	@Override
	public int getBaseQuality() {
		return 0;
	}

	@Override
	public String getBreakingSound() {
		return null;
	}

	/**
	 * This is pretty much unnecessary
	 */
	@Override
	public ItemStack getBrokenItem(ItemStack aStack) {
		return null;
	}

	@Override
	public float getDamageProtection() {
		return 0;
	}

	@Override
	public int[] getEnchantmentLevels(ItemStack aStack) {
		return CS.ZL_INTEGER;
	}

	@Override
	public Enchantment[] getEnchantments(ItemStack aStack,
			OreDictMaterial aMaterial) {
		return CS.ZL_ENCHANTMENT;
	}

	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		//return Textures.ItemIcons.VOID.getIcon(aRenderPass);
		return null;
	}

	@Override
	public String getImpactSound() {
		return null;
	}
	
	public long materialAmount;

	@Override
	public long getMaterialAmount() {
		return materialAmount;
	}

	@Override
	public long getMaxDurabilityMultiplier() {
		return 0;
	}

	@Override
	public int getRenderPasses() {
		return 0;
	}

	@Override
	public short[] getRGBa(ItemStack aStack, int aRenderPass) {
		return MT.Empty.mRGBaSolid;
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

	/**
	 * Unused
	 */
	@Override
	public void onArmorCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		
	}

	@Override
	public void onArmorTick(World world, EntityLivingBase player, ItemStack stack) {

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

	/**
	 * Unused
	 */
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
	public IArmorStats setMaterialAmount(long aMaterialAmount) {
		materialAmount = aMaterialAmount;
		return this;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return null;
	}

	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity,
			int slot) {
		return null;
	}

	@Override
	public void onHeatDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {

	}

	@Override
	public void onFrostDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {

	}

	@Override
	public void onChemDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {

	}

	@Override
	public void onBumbleDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {

	}

	@Override
	public void onSpikeDamage(World world, EntityLivingBase entity,
			ItemStack stack, DamageSource source, float amount,
			LivingHurtEvent event) {

	}

	@Override
	public double getWeightAdded() {
		return 1.0;
	}
	
	public OreDictMaterial theMaterial = MT.Fe;

	@Override
	public OreDictMaterial getMaterial() {
		return theMaterial;
	}

	@Override
	public boolean setMaterial(OreDictMaterial mat) {
		theMaterial = mat;
		return true;
	}

	@Override
	public boolean isCompatibleWith(ItemStack aStack) {
		IArmorStats tStats = ((MultiItemArmor)aStack.getItem()).getArmorStats(aStack);
		if (tStats != null)
		{
			for (int q = 0; q < 4; q++)
			{
				if (tStats.isValidInSlot(q) && this.isValidInSlot(q))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Essentially useless
	 */
	@Override
	public int baseUpgrades() {
		return 0;
	}

	@Override
	public List<String> getAdditionalToolTips(List<String> aList,
			ItemStack aStack) {
		return aList;
	}
	
	private List<ItemStack> upgrades = new ArrayList();

	@Override
	public ItemStack getUpgradeStack(ItemStack aStack) {
		return upgrades.get(0);
	}

	@Override
	public void addUpgradeStack(ItemStack aStack) {
		if (!upgrades.contains(aStack))
		{
			upgrades.add(aStack);
		}
	}

	@Override
	public List<ItemStack> getUpgradeStacks() {
		return upgrades;
	}

	@Override
	public boolean onClickedWearing(ItemStack armorStack, int slot,
			World world, EntityPlayer player, Action action, int x, int y,
			int z, int face, PlayerInteractEvent event) {
		return false;
	}

}
