package com.kbi.qwertech.armor;

import gregapi.render.IIconContainer;

import com.kbi.qwertech.loaders.RegisterArmor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class BootPlate extends BootBase {
	
	@Override
	public float getDamageProtection()
	{
		return 0.8F;
	}
	
	@Override
	public long getMaxDurabilityMultiplier() {
		return 125;
	}
	
	@Override
	public IIconContainer getIcon(ItemStack stack)
	{
		return (IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/boots/plate");
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (type != "overlay")
		{
			return "qwertech:textures/armor/plate.png";
		} else {
			return "qwertech:textures/armor/blank.png";
		}
	}

}
