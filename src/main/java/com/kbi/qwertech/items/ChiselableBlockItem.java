package com.kbi.qwertech.items;

import gregapi.block.prefixblock.PrefixBlockItem;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

public class ChiselableBlockItem extends PrefixBlockItem {
	ArrayList names = new ArrayList();

	public ChiselableBlockItem(Block aBlock) {
		super(aBlock);
		names.add("Smooth");
		names.add("Horizontal Medium Bricks");
		names.add("Vertical Medium Bricks");
		names.add("Horizontal Small Bricks");
		names.add("Vertical Small Bricks");
	}
	
	@Override
	public void getSubItems(Item var1, CreativeTabs aCreativeTab, List aList) {
		if ((SHOW_HIDDEN_PREFIXES || !mBlock.mPrefix.contains(TD.Creative.HIDDEN)) && (SHOW_ORE_BLOCK_PREFIXES || !mBlock.mPrefix.contains(TD.Prefix.ORE))) for (int i = 0; i < mBlock.mMaterialList.length; i++) if (mBlock.mPrefix.isGeneratingItem(mBlock.mMaterialList[i])) if (SHOW_HIDDEN_MATERIALS || !mBlock.mMaterialList[i].mHidden) {
			NBTTagCompound tag = UT.NBT.make();
			for (int q = 0; q < names.size(); q++)
			{
				tag.setShort("d", (short)q);
				ItemStack tStack = ST.make(this, 1, i, (NBTTagCompound)tag.copy());
				updateItemStack(tStack);
				aList.add(tStack);
			}
		}
		if (aList.isEmpty()) ST.hide(this);
	}
	
	@Override
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		super.addInformation(aStack, aPlayer, aList, aF3_H);
		NBTTagCompound tag = aStack.getTagCompound();
		if (tag != null && tag.hasKey("d"))
		{
			int design = tag.getShort("d");
			if (names.get(design) != null)
			{
				aList.add(LH.Chat.ITALIC + names.get(design));
			}
		} else {
			aList.add(LH.Chat.ITALIC + names.get(0));
		}
	}

}
