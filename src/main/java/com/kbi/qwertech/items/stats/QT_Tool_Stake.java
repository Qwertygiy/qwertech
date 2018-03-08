package com.kbi.qwertech.items.stats;

import com.kbi.qwertech.QwerTech;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IExtendedEntityProperties;

public class QT_Tool_Stake extends ToolStats {

	@Override
	public boolean isMinableBlock(Block arg0, byte arg1) {
		return false;
	}
	
	@Override
	public boolean isWeapon()
	{
		return true;
	}
	
	@Override
	public boolean isMiningTool()
	{
		return false;
	}
	
	@Override
	public float getBaseDamage()
	{
		return 1F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier()
	{
	    return 0.1F;
	}
	
	@Override
	public int getToolDamagePerEntityAttack()
	{
	    return 100;
	}
	
	public boolean isEntityVampire(Entity aEntity)
	{
		if (aEntity.getClass().getName().toLowerCase().contains("vampir"))
		{
			return true;
		}
		if (aEntity.getExtendedProperties("VampirePlayer") != null)
		{
			return true;
		}
		IExtendedEntityProperties WEP = aEntity.getExtendedProperties("WitcheryExtendedPlayer");
		if (WEP != null)
		{
			Object isVampire = UT.Reflection.callMethod(WEP, "isVampire", false, false, true);
			return isVampire instanceof Boolean && (Boolean) isVampire;
		}
		return false;
	}
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		if (aEntity instanceof EntityGolem)
		{
			return aOriginalDamage / 2;
		} else if (isEntityVampire(aEntity))
		{
			return aOriginalDamage * 2.5F;
		}
		
		if (MultiItemTool.getToolDamage(aStack) <= getToolDamagePerEntityAttack())
		{
			return aOriginalDamage * 2;
		}
		
		return aOriginalDamage;
	}
	
		@Override
	  public void onStatsAddedToTool(MultiItemTool aItem, int aID)
	  {
		  //aItem.addItemBehavior(aID, new Behavior_Slingshot("", 50));
	  }
	
		@Override
	  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
	  {
	    return MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(QwerTech.stakeTexID);
	  }
	  
		@Override
	  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
	  {
			return MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
	  }
		
		@Override
		public int getRenderPasses() {
			return 1;
		}
	  
		@Override
	  public String getDeathMessage()
	  {
	    return "[KILLER]'s hand was better than [VICTIM]'s in poker";
	  }
		
		
}