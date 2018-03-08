package com.kbi.qwertech.items.stats;

import com.kbi.qwertech.QwerTech;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class QT_Tool_Javelin extends ToolStats {

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
		  //aItem.addItemBehavior(aID, new Behavior_Slingshot("", 50));
	  }
	
	@Override
	  public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack)
	  {
	    return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(QwerTech.javelinHeadTexID) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stickLong.mIconIndexItem);
	  }
	  
		@Override
	  public short[] getRGBa(boolean aIsToolHead, ItemStack aStack)
	  {
			return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	  }
	  
		@Override
	  public String getDeathMessage()
	  {
	    return "[KILLER] defeated [VICTIM] in poker";
	  }
		
		
}