package com.kbi.qwertech.client.tileentity;

import com.kbi.qwertech.tileentities.CraftingTableT1;
import gregapi.data.CS;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class CraftingTableRenderer extends TileEntitySpecialRenderer {

	public CraftingTableRenderer() {
		RB = new RenderBlocks();
		RB.useInventoryTint = false;
	}
	
	public static Minecraft mc;
	public RenderBlocks RB;
	
	public void renderItemIn2D(Tessellator tess, float maxU, float minV, float minU, float maxV, int width, int height, float scale)
    {	
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        tess.addVertexWithUV(0.0D, 0.0D, 0.0D, maxU, maxV);
        tess.addVertexWithUV(1.0D, 0.0D, 0.0D, minU, maxV);
        tess.addVertexWithUV(1.0D, 1.0D, 0.0D, minU, minV);
        tess.addVertexWithUV(0.0D, 1.0D, 0.0D, maxU, minV);
        tess.draw();
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        tess.addVertexWithUV(0.0D, 1.0D, 0.0F - scale, maxU, minV);
        tess.addVertexWithUV(1.0D, 1.0D, 0.0F - scale, minU, minV);
        tess.addVertexWithUV(1.0D, 0.0D, 0.0F - scale, minU, maxV);
        tess.addVertexWithUV(0.0D, 0.0D, 0.0F - scale, maxU, maxV);
        tess.draw();
    }
	
	public void renderItem(ItemStack item, float rotation, double x, double y, double z, World world, float scale, float transparency)
	{   
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glScalef(scale, scale, scale);
        
        Item leItem = item.getItem();
        
        if (leItem instanceof ItemBlock)
        {
        	GL11.glScalef(0.2F, 0.2F, 0.2F);
        } else {
        	GL11.glScalef(0.2F, 0.2F, 0.2F);
        }
        	
        if (leItem instanceof ItemBlock)
        {
        	Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(item.getItemSpriteNumber()));
	        TextureUtil.func_152777_a(false, false, 1.0F);
        	GL11.glPushMatrix();
        	GL11.glRotatef(90, 0, 1, 0);
        	GL11.glRotatef(rotation, 0, 1, 0);
        	GL11.glColor4f(1, 1, 1, transparency);
        	RB.useInventoryTint = false;
        	GL11.glPushMatrix();
        	RB.renderBlockAsItem(Block.getBlockFromItem(leItem), item.getItemDamage(), 1.0F);
        	GL11.glPopMatrix();
        	GL11.glPopMatrix();
        } else
        {
	        int passes = leItem.requiresMultipleRenderPasses() ? leItem.getRenderPasses(item.getItemDamage()) : 1;
	        for (int q = 0; q < passes; q++)
	        {
	        	GL11.glPushMatrix();
		        int l = leItem.getColorFromItemStack(item, q);
		        float r = (l >> 16 & 255) / 255.0F;
		        float g = (l >> 8 & 255) / 255.0F;
		        float b = (l & 255) / 255.0F;
		        GL11.glColor4f(r, g, b, transparency);
		        
				IIcon iicon = leItem.getIcon(item, q);
		
		        if (iicon == null)
		        {
		            return;
		        }
		        
		        //GL11.glScalef(-1, 1, 1);
		        GL11.glRotatef(rotation, 0, 1, 0);
		        GL11.glTranslatef(-0.5F, -0.5F, 0F);
		        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(item.getItemSpriteNumber()));
		        TextureUtil.func_152777_a(false, false, 1.0F);
		        Tessellator tessellator = Tessellator.instance;
		        float f = iicon.getMinU();
		        float f1 = iicon.getMaxU();
		        float f2 = iicon.getMinV();
		        float f3 = iicon.getMaxV();
		        float f4 = 0.0F;
		        float f5 = 0.3F;
		        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		        float f6 = 1.5F;
		        if (transparency < 1)
		        {
		        	renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0F);
		        } else {
		        	ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.1F);
		        }
		        GL11.glPopMatrix();
		        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(item.getItemSpriteNumber()));
		        TextureUtil.func_147945_b();
	        }
        }
        GL11.glPopMatrix();
	}
	
	public void renderTileEntityAt(CraftingTableT1 tileEntity, double x, double y, double z, float partialTick)
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
		float timeLoop = (tileEntity.getWorld().getWorldTime() % 360) + partialTick;
		float timeSine = (tileEntity.getWorld().getWorldTime() % 180) + partialTick;
		timeSine = timeSine > 90 ? 180 - timeSine : timeSine;
		
		if (dist < 16)
		{
			ItemStack craftResult = tileEntity.slot(9);
			ItemStack hammerResult = tileEntity.slot(10);
			
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					ItemStack display = tileEntity.slot((i * 3) + j);
					if (display != null)
					{
						if (tileEntity.mFacing == CS.SIDE_Y_NEG || tileEntity.mFacing == CS.SIDE_X_NEG || tileEntity.mFacing == CS.SIDE_Z_NEG) 
						{
							renderItem(display.copy(), 0, 0.5 - (0.2 * (j - 1)), 1.55 - (i * 0.2) - (timeSine/4000), 0.51, tileEntity.getWorld(), 1, 1F);
						} else {
							renderItem(display.copy(), 0, 0.5 + (0.2 * (j - 1)), 1.55 - (i * 0.2) - (timeSine/4000), 0.51, tileEntity.getWorld(), 1, 1F);
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
				if (hammerResult.getItem() instanceof ItemBlock)
				{
					GL11.glTranslatef(0.2F, 1F, 0.1F);
					GL11.glScalef(1, 0.01F, 1);
					GL11.glPushMatrix();
					GL11.glTranslatef(0.5F, 0.005F, 0.5F);
					GL11.glRotatef(45, 1, 1, 0);
					//GL11.glScalef(1, -1, 1);
					GL11.glTranslatef(-0.5F, -0.005F, -0.5F);
				} else {
					GL11.glPushMatrix();
					GL11.glTranslatef(0.5F, 0.5F, 0.5F);
					GL11.glRotatef(90, 1, 0, 0);
					//GL11.glScalef(1, -1, 1);
					GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				}
				GL11.glEnable(GL11.GL_BLEND);
		        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		        GL11.glDepthFunc(GL11.GL_LESS);
				OpenGlHelper.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
		        //GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA);
		        renderItem(hammerResult.copy(), 0, 0.5, 0.5, -(timeSine/3600), tileEntity.getWorld(), 3, 0.8F);
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
		        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
		        GL11.glDisable(GL11.GL_BLEND);
		        GL11.glPopMatrix();
		        GL11.glPopMatrix();
			}
		} else if (dist < 32)
		{
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					ItemStack display = tileEntity.slot((i * 3) + j);
					if (display != null)
					{
						GL11.glEnable(GL11.GL_BLEND);
				        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
				        GL11.glDepthFunc(GL11.GL_LESS);
						OpenGlHelper.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
						renderItem(display.copy(), 0, 0.5 + (0.2 * (j - 1)), 1.55 - (i * 0.2) - (timeSine/4000), 0.51, tileEntity.getWorld(), 1, (float)(1F - ((dist - 16F)/16F)));
						GL11.glDepthFunc(GL11.GL_LEQUAL);
				        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
				        GL11.glDisable(GL11.GL_BLEND);
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
		renderTileEntityAt((CraftingTableT1)tileEntity, x, y, z, partialTick);
	}

}
