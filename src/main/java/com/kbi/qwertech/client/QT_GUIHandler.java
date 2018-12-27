package com.kbi.qwertech.client;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.client.gui.GuiSplat;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
	public static ResourceLocation potionIcons = new ResourceLocation("textures/gui/container/inventory.png");
	public static HashMap<String, Float[]> iconUVs = new HashMap<>();
	public static int backgroundWidth = 120;
	public static int backgroundHeight = 60;
	public static int armorWidth = 128;
	public static int armorHeight = 128;
    int renderDown = 2;
    int renderRight = 0;
    int perRow = 0;
    boolean isHorizontal = true;

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
		addIcon("burning", new Integer[]{64, 0, 16, 16}, armorIcons);
		addIcon("sneaking", new Integer[]{80, 0, 16, 16}, armorIcons);
		addIcon("blocking", new Integer[]{96, 0, 16, 16},armorIcons);
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

	public void renderIcon(int x, int y, int order, int amount, Float[] UVs, int xsize, int ysize)
	{
	    int xpos = renderRight == 0 ? x : renderRight == 1 ? x - 10 : x - 20;
	    int ypos = renderDown == 0 ? y : renderDown == 1 ? y - 10 : y - 20;
	    if (isHorizontal) {
            xpos = renderRight == 0 ? xpos + (order * 20) : renderRight == 1 ? (xpos - ((amount - 1) * 10) + (order * 20)) : xpos - (order * 20);
        } else {
	        ypos = renderDown == 0 ? ypos + (order * 20) : renderDown == 1 ? (ypos - ((amount - 1) * 10) + (order * 20)) : ypos - (order * 20);
        }

        if (perRow > 1)
        {
            if (order >= perRow)
            {
                int times = (int)Math.floor(((double)order + 1)/(double)perRow);
                if (isHorizontal)
                {
                    ypos = ypos + renderDown != 2 ? 20 * times : -20 * times;
                } else {
                    xpos = xpos + renderRight != 2 ? 20 * times : -20 * times;
                }
            }
        }

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
			int x;
			int y;
			boolean heartcheck = false;
			int extras = 0;

			Object[] potions = minecraft.thePlayer.getActivePotionEffects().toArray();

			if (minecraft.thePlayer.isBurning())
            {
                extras = extras + 1;
            }
            if (minecraft.thePlayer.isBlocking())
            {
                extras = extras + 1;
            }
            if (minecraft.thePlayer.isSneaking())
            {
                extras = extras + 1;
            }

			if (QTConfigs.effectUseDefault)
            {
                switch (QTConfigs.effectDefaultInUse)
                {
                    case 5:
                        x = (event.resolution.getScaledWidth()) - 2;
                        y = 2;
                        renderDown = 2;
                        renderRight = 2;
                        perRow = 0;
                        isHorizontal = true;
                        heartcheck = true;
                        break;
                    case 4:
                        x = (event.resolution.getScaledWidth() / 2);
                        y = 2;
                        renderDown = 2;
                        renderRight = 1;
                        perRow = 0;
                        isHorizontal = true;
                        heartcheck = true;
                        break;
                    case 3:
                        x = 2;
                        y = 2;
                        renderDown = 0;
                        renderRight = 0;
                        perRow = 0;
                        isHorizontal = true;
                        heartcheck = true;
                        break;
                    case 2:
                        x = (event.resolution.getScaledWidth() / 2) + 91;
                        y = (event.resolution.getScaledHeight()) - 40;
                        renderDown = 2;
                        renderRight = 2;
                        perRow = 0;
                        isHorizontal = true;
                        heartcheck = true;
                        break;
                    case 1:
                        x = (event.resolution.getScaledWidth() / 2);
                        y = (event.resolution.getScaledHeight()) - 40;
                        renderDown = 2;
                        renderRight = 1;
                        perRow = 0;
                        isHorizontal = true;
                        heartcheck = true;
                        break;
                    case 0:
                    default:
                        x = (event.resolution.getScaledWidth() / 2) - 91;
                        y = (event.resolution.getScaledHeight()) - 40;
                        renderDown = 2;
                        renderRight = 0;
                        perRow = 0;
                        isHorizontal = true;
                        heartcheck = true;
                        break;
                }
            } else {
			    renderDown = QTConfigs.effectCenterY;
			    renderRight = QTConfigs.effectCenterX;
			    perRow = QTConfigs.effectRowLimit;
			    isHorizontal = QTConfigs.effectHorizontal;
			    switch (QTConfigs.effectAnchorX)
                {
                    case 0:
                        x = QTConfigs.effectOffsetX;
                        break;
                    case 1:
                        x = (event.resolution.getScaledWidth() / 2) + QTConfigs.effectOffsetX;
                        break;
                    case 2:
                    default:
                        x = event.resolution.getScaledWidth() + QTConfigs.effectOffsetX;
                        break;
                }
                switch (QTConfigs.effectAnchorY)
                {
                    case 0:
                        y = QTConfigs.effectOffsetY;
                        break;
                    case 1:
                        y = (event.resolution.getScaledHeight() / 2) + QTConfigs.effectOffsetY;
                        break;
                    case 2:
                    default:
                        y = event.resolution.getScaledHeight() + QTConfigs.effectOffsetY;
                        heartcheck = true;
                        break;
                }
            }

			if (heartcheck && (minecraft.thePlayer.isPotionActive(Potion.field_76444_x) || minecraft.thePlayer.getAir() < 300))
			{
				y = y - 10;
			}
			
			protec = 100 - protec;
			
			GL11.glColor4f(1, 1, 1, 1);

			if (QTConfigs.effectBackgroundType != -1) {
			    String type = "rounded";
			    switch(QTConfigs.effectBackgroundType)
                {
                    case 1:
                        type = "square";
                        break;
                    case 2:
                        type = "circle";
                        break;
                }
                minecraft.renderEngine.bindTexture(backgroundIcons);
                Float[] bgSpots = getUV(type + "Yellow");
                renderIcon(x, y, 0, 2 + potions.length + extras, bgSpots, 20, 20);
                renderIcon(x, y, 1, 2 + potions.length + extras, bgSpots, 20, 20);

                for (int q = 0; q < potions.length; q++)
                {
                    if (potions[q] instanceof PotionEffect) //it should always be, but we all know how things can be...
                    {
                        PotionEffect lePotEf = (PotionEffect) potions[q];
                        Potion lePot = Potion.potionTypes[lePotEf.getPotionID()];
                        if (lePot.isBadEffect())
                        {
                            bgSpots = getUV(type + "Red");
                        } else {
                            bgSpots = getUV(type + "Green");
                        }
                        renderIcon(x, y, 2 + q, 2 + potions.length + extras, bgSpots, 20, 20);
                    }
                }
                if (minecraft.thePlayer.isBurning())
                {
                    bgSpots = getUV(type + "Red");
                    renderIcon(x, y, 2 + potions.length, 2 + potions.length + extras, bgSpots, 20, 20);
                }
                if (minecraft.thePlayer.isBlocking())
                {
                    bgSpots = getUV(type + "White");
                    renderIcon(x, y, 2 + potions.length + (minecraft.thePlayer.isBurning() ? 1 : 0), 2 + potions.length + extras, bgSpots, 20, 20);
                }
                if (minecraft.thePlayer.isSneaking())
                {
                    bgSpots = getUV(type + "White");
                    renderIcon(x, y, 2 + potions.length + (minecraft.thePlayer.isBurning() ? 1 : 0) + (minecraft.thePlayer.isBlocking() ? 1 : 0), 2 + potions.length + extras, bgSpots, 20, 20);
                }
            }
			
			String protect = protec < 1? "shieldNone" : protec < 16 ? "shieldWood" : protec < 33 ? "shieldBronze" : protec < 50 ? "shieldIron" : "shieldPurple";
			minecraft.renderEngine.bindTexture(armorIcons);
			//minecraft.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			Float[] startSpots = getUV(protect);
			renderIcon(x + 2, y + 2, 0, 2 + potions.length + extras, startSpots, 16, 16);

			String weighty = weight < 1 ? "weightNone" : weight < 20 ? "weightLight" : weight < 40 ? "weightLittle" : weight < 70 ? "weightSignificant" : "weightMuch";
			startSpots = getUV(weighty);
			renderIcon(x + 2, y + 2, 1, 2 + potions.length + extras, startSpots, 16, 16);

            if (minecraft.thePlayer.isBurning())
            {
                startSpots = getUV("burning");
                renderIcon(x, y, 2 + potions.length, 2 + potions.length + extras, startSpots, 16, 16);
            }
            if (minecraft.thePlayer.isBlocking())
            {
                startSpots = getUV("blocking");
                renderIcon(x, y, 2 + potions.length + (minecraft.thePlayer.isBurning() ? 1 : 0), 2 + potions.length + extras, startSpots, 16, 16);
            }
            if (minecraft.thePlayer.isSneaking())
            {
                startSpots = getUV("sneaking");
                renderIcon(x, y, 2 + potions.length + (minecraft.thePlayer.isBurning() ? 1 : 0) + (minecraft.thePlayer.isBlocking() ? 1 : 0), 2 + potions.length + extras, startSpots, 16, 16);
            }

			for (int q = 0; q < potions.length; q++)
            {
                if (potions[q] instanceof PotionEffect) //it should always be, but we all know how things can be...
                {
                    PotionEffect lePotEf = (PotionEffect)potions[q];
                    Potion lePot = Potion.potionTypes[lePotEf.getPotionID()];
                    if (lePot.hasStatusIcon())
                    {
                        minecraft.getTextureManager().bindTexture(potionIcons);
                        int l = lePot.getStatusIconIndex();
                        int startX = l % 8 * 18;
                        int startY = l / 8 * 18;
                        Float[] borders = new Float[]{startX * 0.00390625F, (198 + startY) * 0.00390625F, (18) * 0.00390625F, (18) * 0.00390625F};
                        renderIcon(x + 1, y + 1, 2 + q, 2 + extras + potions.length, borders, 18, 18);
                    }
                }
            }

			if (minecraft.gameSettings.advancedItemTooltips)
			{
				if (protec > 0) {
					minecraft.fontRenderer.drawString((int) (protec) + "%", x + 18 - (minecraft.fontRenderer.getStringWidth((int) (protec) + "%")), y - 28, 16777215, true);
				}

				if (weight > 0) {
					String drawit = (int) weight + "kg";
					minecraft.fontRenderer.drawString(drawit, x + 44 - (minecraft.fontRenderer.getStringWidth(drawit)), y - 28, 16777215, true);
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
