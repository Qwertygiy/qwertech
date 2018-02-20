package com.kbi.qwertech.armor.upgrades;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
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
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.client.models.ModelArmorMonocle;
import com.kbi.qwertech.client.models.ModelArmorSpring;
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class Upgrade_Magnifier extends UpgradeBase {

	public Upgrade_Magnifier() {
	}
	
	@Override
	public boolean isValidInSlot(int slot)
	{
		return slot == 0;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return type == "overlay" ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/monocle.png";
	}
	
	Object model;
	
	@Override
	public ModelBiped getArmorModel(ItemStack stack, EntityLivingBase entity, int slot)
	{
		if (slot != 0) return null;
		if (model == null)
		{
			model = new ModelArmorMonocle();
		}
		return (ModelBiped)model;
	}
	
	@Override
	public int getRenderPasses() {
		return 1;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass)
	{
		return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:armor/helmet/monocle")).getIcon(aRenderPass);
	}
	
	@Override
	public short[] getRGBa(ItemStack aStack, int aRenderPass)
	{
		return aRenderPass == 0 ? this.getMaterial().mRGBaSolid : MT.Empty.mRGBaSolid;
	}
	
	@Override
	public List<String> getAdditionalToolTips(List<String> aList, ItemStack aStack)
	{
		aList.add(LH.Chat.ITALIC + LH.Chat.GRAY + "Quite elementary");
		return aList;
	}
	
	@Override
	public boolean onClickedWearing(ItemStack armorStack, int slot,
			World world, EntityPlayer player, Action action, int x, int y,
			int z, int face, PlayerInteractEvent event) {
		if (action == action.RIGHT_CLICK_BLOCK)
		{
			List<String> tChatReturn = new ArrayListNoNulls<String>();
			long tDamage = IBlockToolable.Util.onToolClick("magnifyingglass", Long.MAX_VALUE, (long)1, player, tChatReturn, player.inventory, player.isSneaking(), armorStack, world, (byte)face, x, y, z, 0.0F, 0.0F, 0.0F);
			UT.Entities.sendchat(player, tChatReturn, F);
			if (tDamage > 0) {
	    		if (!UT.Entities.hasInfiniteItems(player)) 
	    		{
	    			((MultiItemArmor)armorStack.getItem()).doDamage(armorStack, 1, player);
	    		}
	    	}
		}
		return false;
	}

}
