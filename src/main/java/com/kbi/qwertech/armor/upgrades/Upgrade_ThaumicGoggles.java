package com.kbi.qwertech.armor.upgrades;

import com.kbi.qwertech.api.armor.upgrades.IThaumcraftUpgrade;
import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.client.models.ModelArmorGoggles;
import com.kbi.qwertech.loaders.RegisterArmor;
import gregapi.data.LH;
import gregapi.render.IIconContainer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class Upgrade_ThaumicGoggles extends UpgradeBase implements IThaumcraftUpgrade {
    @Override
    public int getVisDiscount(ItemStack var1, EntityPlayer var2, Object var3) {
        return 5;
    }

    @Override
    public boolean showIngamePopups(ItemStack var1, EntityLivingBase var2) {
        return true;
    }

    @Override
    public boolean showNodes(ItemStack var1, EntityLivingBase var2) {
        return true;
    }

    @Override
    public boolean isValidInSlot(int slot)
    {
        return slot == 0;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return (type != null) ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/tcgoggles.png";
    }

    Object model;

    @Override
    public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity, int slot)
    {
        if (slot != 0) return null;
        if (model == null)
        {
            model = new ModelArmorGoggles();
        }
        return (ModelBiped)model;
    }

    @Override
    public int getRenderPasses() {
        return 1;
    }

    @Override
    public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
    {
        aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Reveals underlying magic");
        return aList;
    }

    @Override
    public IIcon getIcon(ItemStack aStack, int aRenderPass)
    {
        return ((IIconContainer) RegisterArmor.iconTitle.get("qwertech:armor/helmet/tcgoggles")).getIcon(aRenderPass);
    }
}
