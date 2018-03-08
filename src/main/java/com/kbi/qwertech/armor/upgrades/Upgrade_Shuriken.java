package com.kbi.qwertech.armor.upgrades;

import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.client.models.ModelArmorSpurs;
import com.kbi.qwertech.loaders.RegisterArmor;
import gregapi.data.LH;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

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
		return type.equals("overlay") ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/spur.png";
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
