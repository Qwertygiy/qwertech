package com.kbi.qwertech.armor.upgrades;

import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.client.models.ModelArmorFeather;
import com.kbi.qwertech.loaders.RegisterArmor;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.render.IIconContainer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

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
		return type != null ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/featherwhite.png";
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
