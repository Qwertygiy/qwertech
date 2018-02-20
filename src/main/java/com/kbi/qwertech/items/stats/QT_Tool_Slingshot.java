package com.kbi.qwertech.items.stats;

import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.items.behavior.Behavior_Slingshot;

public class QT_Tool_Slingshot extends ToolStats {

	@Override
	public boolean isMinableBlock(Block arg0, byte arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isRangedWeapon()
	{
		return true;
	}
	
	@Override
	  public void onStatsAddedToTool(MultiItemTool aItem, int aID)
	  {
		  aItem.addItemBehavior(aID, new Behavior_Slingshot("random.bow", 50));
	  }
	
	@Override
	  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
	  {
	    return aIsToolHead ? (IIconContainer)MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(QwerTech.slingshotTexID) : (IIconContainer)MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(QwerTech.stringshotTexID);
	  }
	  
		@Override
	  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
	  {
	    return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : new short[] {255, 255, 255, 255};
	  }
	  
		@Override
	  public String getDeathMessage()
	  {
	    return "[VICTIM] was strangled by [KILLER]";
	  }
		
		
}
