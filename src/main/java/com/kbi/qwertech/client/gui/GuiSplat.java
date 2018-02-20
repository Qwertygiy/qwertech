package com.kbi.qwertech.client.gui;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSplat extends Gui {

	ResourceLocation[] splats = new ResourceLocation[]{new ResourceLocation("qwertech:textures/gui/splat1.png"), new ResourceLocation("qwertech:textures/gui/splat2.png"), new ResourceLocation("qwertech:textures/gui/splat3.png"), new ResourceLocation("qwertech:textures/gui/splat4.png")};
	public GuiSplat(Minecraft mc, int tickStart, int currentTick, short[] rgb)
	{
		//System.out.println("Drawing splat");
		float difference = currentTick - tickStart;
		float countdown = 60 - difference;
		float percentage = difference/100.0F;
		//System.out.println("Difference is" + difference);
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        Random adjuster = new Random();
        adjuster.setSeed(tickStart);
        double xsize = (adjuster.nextFloat()/1.33F) + 0.33 + (percentage/3);
        double ysize = (adjuster.nextFloat()/1.33F) + 0.33 + (percentage/3);
        double xpos = (adjuster.nextFloat()/1.33F) - 0.33 - (percentage/6);
        double ypos = (adjuster.nextFloat()/1.33F) - 0.33 - (percentage/6);
        
        double aXs = xsize * width;
        double aYs = ysize * height;
        double aXp = xpos * width;
        double aYp = ypos * height;
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(rgb[0]/255F, rgb[1]/255F, rgb[2]/255F, (Math.max(((rgb[3]/60) * countdown), 1))/255F);
        //GL11.glTranslatef(0.0F, percentage, 0.0F);
        //GL11.glTranslatef(adjuster.nextFloat() - 0.5F, adjuster.nextFloat() - 0.5F, adjuster.nextFloat() - 0.5F);
        //GL11.glScalef(adjuster.nextFloat() + 0.2F, adjuster.nextFloat() + 0.2F, adjuster.nextFloat() + 0.2F);
        
        mc.getTextureManager().bindTexture(splats[adjuster.nextInt(4)]);
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(aXp, aYp + aYs + (percentage * height), -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(aXp + aXs, aYp + aYs + (percentage * height), -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(aXp + aXs, aYp + (percentage * height), -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(aXp, aYp + (percentage * height), -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
	}
}
