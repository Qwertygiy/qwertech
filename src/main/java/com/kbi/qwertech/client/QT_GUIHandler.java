package com.kbi.qwertech.client;

import gregapi.oredict.OreDictMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.client.gui.GuiSplat;

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
				minecraft.fontRenderer.drawString("Armor: " + (int)(protec) + "%", x, y, protec > 66 ? 60928 : protec > 50 ? 52224 : protec > 33 ? 13421568 : protec > 25 ? 13408512 : protec > 15 ? 10027008 : 13369344, true);
			}
			if (weight > 0)
			{
				String drawit = "Weight: " + (int)weight + "kg";
				x = (event.resolution.getScaledWidth() / 2) + 91 - minecraft.fontRenderer.getStringWidth(drawit);
				minecraft.fontRenderer.drawString(drawit, x, y, weight < 20 ? 60928 : weight < 30 ? 52224 : weight < 50 ? 13421568 : weight < 70 ? 13408512 : weight < 90 ? 13369344 : 15597568, true);
			}
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
