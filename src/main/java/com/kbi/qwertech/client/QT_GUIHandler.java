package com.kbi.qwertech.client;

import gregapi.oredict.OreDictMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.armor.ArmorIcon;
import com.kbi.qwertech.client.gui.GuiSplat;
import com.kbi.qwertech.loaders.RegisterArmor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class QT_GUIHandler {
	public static int[] splats;
	public static short[][] colors;
	
	public QT_GUIHandler()
	{
		QT_GUIHandler.splats = new int[8];
		QT_GUIHandler.colors = new short[][]{{255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}};
	}
	
	@SubscribeEvent
	public void onRendertext(RenderGameOverlayEvent.Text event)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		if (QTConfigs.enableArmor && event.type == ElementType.TEXT)
		{
			//event.setCanceled(true);
			double weight = 0;
			double protec = 100;
			for (int i = 0; i < 4; i++)
			{
				ItemStack armorSlot = minecraft.thePlayer.getCurrentArmor(i);
				if (armorSlot != null)
				{
					if (armorSlot.getItem() instanceof MultiItemArmor)
					{
						IArmorStats tStats = ((MultiItemArmor)armorSlot.getItem()).getArmorStats(armorSlot);
						if (tStats != null)
						{
							OreDictMaterial mat = MultiItemArmor.getPrimaryMaterial(armorSlot);
							weight = weight + mat.getWeight(tStats.getMaterialAmount()/500);
							protec = protec * tStats.getDamageProtection();
						}
					} else if (armorSlot.getItem() instanceof ItemArmor)
					{
						ItemArmor armor = (ItemArmor)armorSlot.getItem();
						int reduction = armor.damageReduceAmount * 4;
						protec = protec - (reduction);
						weight = weight + (reduction * 0.66);
					}
				}
			}
			int x = (event.resolution.getScaledWidth() / 2) - 91;
			int y = (event.resolution.getScaledHeight()) - 49;
			if (protec < 100)
			{
				protec = 100 - protec;
				minecraft.fontRenderer.drawString((int)(protec) + "%", x, y - 16, protec > 66 ? 60928 : protec > 50 ? 52224 : protec > 33 ? 13421568 : protec > 25 ? 13408512 : protec > 15 ? 10027008 : 13369344, true);
			}
			
			if (weight > 0)
			{
				String drawit = (int)weight + "kg";
				minecraft.fontRenderer.drawString(drawit, x + 20, y - 16, weight < 20 ? 60928 : weight < 30 ? 52224 : weight < 50 ? 13421568 : weight < 70 ? 13408512 : weight < 90 ? 13369344 : 15597568, true);
			}
			
			GL11.glColor4f(1, 1, 1, 1);
			
			String protect = protec < 1 || protec > 99 ? "noShield" : protec < 16 ? "woodShield" : protec < 33 ? "bronzeShield" : protec < 50 ? "blueMetalShield" : "purpleShield";
			minecraft.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			RenderItem.getInstance().renderIcon(x, y - 8, ((ArmorIcon)RegisterArmor.iconTitle.get(protect)).getIcon(0), 16, 16);

			String weighty = weight < 1 ? "weightNone" : weight < 20 ? "weightLight" : weight < 40 ? "weightLittle" : weight < 70 ? "weightSignificant" : "weightMuch";
			RenderItem.getInstance().renderIcon(x + 20, y - 8, ((ArmorIcon)RegisterArmor.iconTitle.get(weighty)).getIcon(0), 16, 16);
		}
	}
	
	@SubscribeEvent
	public void onRendergui(RenderGameOverlayEvent.Pre event)
	{
		if (QTConfigs.enableArmor && event.type == ElementType.ARMOR)
		{
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {	
		Minecraft minecraft = Minecraft.getMinecraft();
		if (event.type != ElementType.EXPERIENCE) return;
		for (int q = 0; q < 8; q++)
		{
			if (splats[q] != 0)
			{
				if (minecraft.thePlayer.ticksExisted > splats[q] + 60)
				{
					System.out.println("Old splat resetting");
					splats[q] = 0;
				} else {
					new GuiSplat(Minecraft.getMinecraft(), splats[q], minecraft.thePlayer.ticksExisted, colors[q]);
				}
			}
		}
    }
	
	public static void addNewSplat(short[] color)
	{
		System.out.println("Splat adding");
		int minimum = splats[0];
		int pos = 0;
		for (int q = 0; q < 8; q++)
		{
			if (splats[q] < minimum)
			{
				minimum = splats[q];
				pos = q;
			}
		}
		splats[pos] = Minecraft.getMinecraft().thePlayer.ticksExisted;
		colors[pos] = color;
	}
}
