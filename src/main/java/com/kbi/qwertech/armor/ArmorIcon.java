package com.kbi.qwertech.armor;

import static gregapi.data.CS.UNCOLOURED;
import gregapi.GT_API;
import gregapi.render.IIconContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class ArmorIcon implements IIconContainer, Runnable {
	protected IIcon mIcon, mOverlay;
	protected String mIconName;
	
	@Override public IIcon getIcon(int aRenderPass) {return aRenderPass==1?mOverlay:mIcon;}
	
	public ArmorIcon(String aIconName) {
		mIconName = aIconName;
		if (GT_API.sItemIconload != null) GT_API.sItemIconload.add(this);
	}
	
	@Override
	public void run() {
		mIcon		= GT_API.sItemIcons.registerIcon(mIconName);
		mOverlay	= GT_API.sItemIcons.registerIcon(mIconName + "_OVERLAY");
	}
	
	@Override
	public ResourceLocation getTextureFile() {
		return TextureMap.locationItemsTexture;
	}
	@Override
	public short[] getIconColor(int aRenderPass) {
		return UNCOLOURED;
	}
	@Override
	public int getIconPasses() {
		return 2;
	}
	@Override
	public void registerIcons(IIconRegister aIconRegister) {
		//
	}

	@Override
	public boolean isUsingColorModulation(int aRenderPass) {
		// TODO Auto-generated method stub
		return aRenderPass == 0;
	}
}