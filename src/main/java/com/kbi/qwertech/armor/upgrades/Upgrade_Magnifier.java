package com.kbi.qwertech.armor.upgrades;

import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.armor.upgrades.UpgradeBase;
import com.kbi.qwertech.client.models.ModelArmorMonocle;
import com.kbi.qwertech.loaders.RegisterArmor;
import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import java.util.List;

import static gregapi.data.CS.F;

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
		return (type != null) ? "qwertech:textures/armor/blank.png" : "qwertech:textures/armor/upgrade/monocle.png";
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
		if (action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && player.isSneaking())
		{
			List<String> tChatReturn = new ArrayListNoNulls<String>();
			long tDamage = IBlockToolable.Util.onToolClick("magnifyingglass", Long.MAX_VALUE, (long)1, player, tChatReturn, player.inventory, player.isSneaking(), armorStack, world, (byte)face, x, y, z, 0.5F, 0.5F, 0.5F);
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
