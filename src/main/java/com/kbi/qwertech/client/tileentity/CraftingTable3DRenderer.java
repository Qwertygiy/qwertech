package com.kbi.qwertech.client.tileentity;

import com.kbi.qwertech.tileentities.CraftingTableT4;
import gregapi.data.CS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class CraftingTable3DRenderer extends CraftingTableRenderer {

	public CraftingTable3DRenderer() {
		super();
	}
	
	public void renderTileEntityAt(CraftingTableT4 tileEntity, double x, double y, double z, float partialTick)
	{
		//System.out.println("Rendering");
		if (mc == null) mc = Minecraft.getMinecraft();
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short dir = tileEntity.getFacing();
		if (dir == CS.SIDE_Y_NEG || dir == CS.SIDE_X_NEG || dir == CS.SIDE_X_POS)
		{
			GL11.glRotatef(90, 0, 1, 0);
		}
		
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		double dist = (Math.abs(x) + Math.abs(y) + Math.abs(z));
		float timeLoop = 0;
		float timeSine = 0;
		World leWorld = tileEntity.getWorld();
		if (leWorld != null)
		{
			timeLoop = (leWorld.getWorldTime() % 360) + partialTick;
			timeSine = (leWorld.getWorldTime() % 180) + partialTick;
		}
		timeSine = timeSine > 90 ? 180 - timeSine : timeSine;
		
		if (dist < 16)
		{
			ItemStack craftResult = tileEntity.slot(27);
			ItemStack hammerResult = tileEntity.slot(28);
			for (int k = 0; k < 3; k++)
			{
				for (int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						ItemStack display = tileEntity.slot((k * 9) + (i * 3) + (j));
						if (display != null)
						{
							if (tileEntity.mFacing == CS.SIDE_Y_NEG || tileEntity.mFacing == CS.SIDE_X_NEG || tileEntity.mFacing == CS.SIDE_Z_NEG) 
							{
								renderItem(display.copy(), 0, 0.5 - (0.2 * (j - 1)), 1.55 - (i * 0.2) - (timeSine/4000), 0.5 + (0.2 * (k - 1)), tileEntity.getWorld(), 1, 1F);
							} else {
								renderItem(display.copy(), 0, 0.5 + (0.2 * (j - 1)), 1.55 - (i * 0.2) - (timeSine/4000), 0.5 - (0.2 * (k - 1)), tileEntity.getWorld(), 1, 1F);
							}
						}
					}
				}
			}
			if (craftResult != null)
			{
		        GL11.glEnable(GL11.GL_BLEND);
		        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
				OpenGlHelper.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
		        //GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA);
		        renderItem(craftResult.copy(), timeLoop, 0.5, 1.5 + (timeSine/2000), 0.5, tileEntity.getWorld(), 4, 0.7F);
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
		        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
		        GL11.glDisable(GL11.GL_BLEND);
			}
			if (hammerResult != null)
			{
				GL11.glPushMatrix();
				GL11.glTranslatef(0.5F, 0.5F, 0.5F);
				GL11.glRotatef(90, 1, 0, 0);
				//GL11.glScalef(1, -1, 1);
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				GL11.glEnable(GL11.GL_BLEND);
		        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		        GL11.glDepthFunc(GL11.GL_LESS);
				OpenGlHelper.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
		        //GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA);
		        renderItem(hammerResult.copy(), 0, 0.5, 0.5, -(timeSine/3600), tileEntity.getWorld(), 4, 0.7F);
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
		        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
		        GL11.glDisable(GL11.GL_BLEND);
		        GL11.glPopMatrix();
			}
		} else if (dist < 32)
		{
			for (int k = 0; k < 3; k++)
			{
				for (int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						ItemStack display = tileEntity.slot((i * 3) + j + k);
						if (display != null)
						{
							GL11.glEnable(GL11.GL_BLEND);
					        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
					        GL11.glDepthFunc(GL11.GL_LESS);
							OpenGlHelper.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
							renderItem(display.copy(), 0, 0.5 + (0.2 * (j - 1)), 1.55 - (i * 0.2) - (timeSine/4000), 0.5 - (0.2 * (k - 1)), tileEntity.getWorld(), 1, (float)(1F - ((dist - 16F)/16F)));
							GL11.glDepthFunc(GL11.GL_LEQUAL);
					        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
					        GL11.glDisable(GL11.GL_BLEND);
						}
					}
				}
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
		renderTileEntityAt((CraftingTableT4)tileEntity, x, y, z, partialTick);
	}

}
