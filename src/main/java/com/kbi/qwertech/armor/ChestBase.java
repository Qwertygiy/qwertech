package com.kbi.qwertech.armor;

import com.kbi.qwertech.api.armor.ArmorBase;
import com.kbi.qwertech.loaders.RegisterArmor;
import gregapi.render.IIconContainer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ChestBase extends ArmorBase {
	
	public ChestBase()
	{
		super();
	}
	
	@Override
	public int getBaseQuality()
	{
		return 1;
	}
	
	@Override
	public IIconContainer getIcon(ItemStack stack)
	{
		return (IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/chestplate/chainmail");
	}
	
	@Override
	public float getDamageProtection()
	{
		return 0.8F;
	}
	
	@Override
	public boolean isValidInSlot(int armorSlot)
	{
		return armorSlot == 1;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (!type.equals("overlay"))
		{
			return "qwertech:textures/armor/chainmail.png";
		} else {
			return "qwertech:textures/armor/blank.png";
		}
	}
	
}
