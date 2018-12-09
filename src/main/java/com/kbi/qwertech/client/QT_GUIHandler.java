package com.kbi.qwertech.client;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.armor.ArmorIcon;
import com.kbi.qwertech.client.gui.GuiSplat;
import com.kbi.qwertech.loaders.RegisterArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class QT_GUIHandler {
	public static int[] splats;
	public static short[][] colors;
	public static ResourceLocation backgroundIcons = new ResourceLocation("qwertech:textures/gui/effect/backgrounds.png");
	public static ResourceLocation armorIcons = new ResourceLocation("qwertech:textures/gui/effect/armor.png");
	public static HashMap<String, Float[]> iconUVs = new HashMap<>();
	public static int backgroundWidth = 120;
	public static int backgroundHeight = 60;
	public static int armorWidth = 64;
	public static int armorHeight = 64;

	public QT_GUIHandler()
	{
		QT_GUIHandler.splats = new int[8];
		QT_GUIHandler.colors = new short[][]{{255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}, {255, 255, 255, 255}};
		addIcons();
	}

	private float calculate(ResourceLocation tex, int amount, boolean isY)
	{
		if (tex == armorIcons)
		{
			if (isY)
			{
				return (float)amount/(float)armorHeight;
			} else {
				return (float)amount/(float)armorWidth;
			}
		} else if (tex == backgroundIcons)
		{
			if (isY)
			{
				return (float)amount/(float)backgroundHeight;
			} else {
				return (float)amount/(float)backgroundWidth;
			}
		} else {
			return 0.0F;
		}
	}

	private void addIcon(String string, Integer[] pixels, ResourceLocation texture)
	{
		iconUVs.put(string, new Float[]{calculate(texture, pixels[0], false), calculate(texture, pixels[1], true), calculate(texture, pixels[2], false), calculate(texture, pixels[3], true)});
	}

	private void addIcons()
	{
		addIcon("roundedYellow", new Integer[]{0, 0, 20, 20}, backgroundIcons);
		addIcon("roundedRed", new Integer[]{20, 0, 20, 20}, backgroundIcons);
		addIcon("roundedGreen", new Integer[]{40, 0, 20, 20}, backgroundIcons);
		addIcon("roundedBlue", new Integer[]{60, 0, 20, 20}, backgroundIcons);
		addIcon("roundedBlack", new Integer[]{80, 0, 20, 20}, backgroundIcons);
		addIcon("roundedWhite", new Integer[]{100, 0, 20, 20}, backgroundIcons);
		addIcon("squareYellow", new Integer[]{0, 20, 20, 20}, backgroundIcons);
		addIcon("squareRed", new Integer[]{20, 20, 20, 20}, backgroundIcons);
		addIcon("squareGreen", new Integer[]{40, 20, 20, 20}, backgroundIcons);
		addIcon("squareBlue", new Integer[]{60, 20, 20, 20}, backgroundIcons);
		addIcon("squareBlack", new Integer[]{80, 20, 20, 20}, backgroundIcons);
		addIcon("squareWhite", new Integer[]{100, 20, 20, 20}, backgroundIcons);
		addIcon("circleYellow", new Integer[]{0, 40, 20, 20}, backgroundIcons);
		addIcon("circleRed", new Integer[]{20, 40, 20, 20}, backgroundIcons);
		addIcon("circleGreen", new Integer[]{40, 40, 20, 20}, backgroundIcons);
		addIcon("circleBlue", new Integer[]{60, 40, 20, 20}, backgroundIcons);
		addIcon("circleBlack", new Integer[]{80, 40, 20, 20}, backgroundIcons);
		addIcon("circleWhite", new Integer[]{100, 40, 20, 20}, backgroundIcons);
		addIcon("weightNone", new Integer[]{0, 48, 16, 16}, armorIcons);
		addIcon("weightLight", new Integer[]{0, 32, 16, 16}, armorIcons);
		addIcon("weightLittle", new Integer[]{16, 32, 16, 16}, armorIcons);
		addIcon("weightSignificant", new Integer[]{32, 32, 16, 16}, armorIcons);
		addIcon("weightMuch", new Integer[]{48, 32, 16, 16}, armorIcons);
		addIcon("shieldNone", new Integer[]{16, 48, 16, 16}, armorIcons);
		addIcon("shieldWood", new Integer[]{0, 0, 16, 16}, armorIcons);
		addIcon("shieldBronze", new Integer[]{16, 0, 16, 16}, armorIcons);
		addIcon("shieldIron", new Integer[]{32, 0, 16, 16}, armorIcons);
		addIcon("shieldPurple", new Integer[]{48, 0, 16, 16}, armorIcons);
		addIcon("shieldBrokenWood", new Integer[]{0, 16, 16, 16}, armorIcons);
		addIcon("shieldBrokenBronze", new Integer[]{16, 16, 16, 16}, armorIcons);
		addIcon("shieldBrokenIron", new Integer[]{32, 16, 16, 16}, armorIcons);
		addIcon("shieldBrokenPurple", new Integer[]{48, 16, 16, 16}, armorIcons);
	}

	private Float[] getUV(String name)
	{
		Float[] returnable = iconUVs.get(name);
		if (returnable == null)
		{
			return new Float[]{0F, 0F, 0F, 0F};
		}
		return returnable;
	}

	public void renderIcon(int xpos, int ypos, float uMin, float vMin, float uMax, float vMax, int xsize, int ysize)
	{
		//System.out.println(uMin + " " + vMin + " " + uMax + " " + vMax);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double)(xpos + 0), (double)(ypos + ysize), RenderItem.getInstance().zLevel, uMin, vMax);
		tessellator.addVertexWithUV((double)(xpos + xsize), (double)(ypos + ysize), RenderItem.getInstance().zLevel, uMax, vMax);
		tessellator.addVertexWithUV((double)(xpos + xsize), (double)(ypos + 0), RenderItem.getInstance().zLevel, uMax, vMin);
		tessellator.addVertexWithUV((double)(xpos + 0), (double)(ypos + 0), RenderItem.getInstance().zLevel, uMin, vMin);
		tessellator.draw();
	}

	public void renderIcon(int xpos, int ypos, Float[] UVs, int xsize, int ysize)
	{
		renderIcon(xpos, ypos, UVs[0], UVs[1], UVs[0] + UVs[2], UVs[1] + UVs[3], xsize, ysize);
	}
	
	@SubscribeEvent
	public void onRendertext(RenderGameOverlayEvent.Text event)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		if (QTConfigs.enableArmor && event.type == ElementType.TEXT)
		{
			//event.setCanceled(true);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
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
			int x = (event.resolution.getScaledWidth() / 2) - 89;
			int y = (event.resolution.getScaledHeight()) - 46;

			if (minecraft.thePlayer.isPotionActive(Potion.field_76444_x))
			{
				y = y - 10;
			}
			
			protec = 100 - protec;
			
			GL11.glColor4f(1, 1, 1, 1);

			minecraft.renderEngine.bindTexture(backgroundIcons);
			Float[] startSpots = getUV("roundedYellow");
			renderIcon(x - 2, y - 14, startSpots, 20, 20);
			renderIcon(x + 18, y - 14, startSpots, 20, 20);
			
			String protect = protec < 1? "shieldNone" : protec < 16 ? "shieldWood" : protec < 33 ? "shieldBronze" : protec < 50 ? "shieldIron" : "shieldPurple";
			minecraft.renderEngine.bindTexture(armorIcons);
			//minecraft.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			startSpots = getUV(protect);
			renderIcon(x, y - 12, startSpots, 16, 16);

			String weighty = weight < 1 ? "weightNone" : weight < 20 ? "weightLight" : weight < 40 ? "weightLittle" : weight < 70 ? "weightSignificant" : "weightMuch";
			startSpots = getUV(weighty);
			renderIcon(x + 20, y - 12, startSpots, 16, 16);

			if (minecraft.gameSettings.advancedItemTooltips)
			{
				if (protec > 0) {
					minecraft.fontRenderer.drawString((int) (protec) + "%", x + 18 - (minecraft.fontRenderer.getStringWidth((int) (protec) + "%")), y - 24, 16777215, true);
				}

				if (weight > 0) {
					String drawit = (int) weight + "kg";
					minecraft.fontRenderer.drawString(drawit, x + 44 - (minecraft.fontRenderer.getStringWidth(drawit)), y - 24, 16777215, true);
				}
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
					//System.out.println("Old splat resetting");
					splats[q] = 0;
				} else {
					new GuiSplat(Minecraft.getMinecraft(), splats[q], minecraft.thePlayer.ticksExisted, colors[q]);
				}
			}
		}
    }
	
	public static void addNewSplat(short[] color)
	{
		//System.out.println("Splat adding");
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
