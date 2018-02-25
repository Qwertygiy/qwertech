package com.kbi.qwertech.client.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.kbi.qwertech.api.armor.IArmorStats;
import com.kbi.qwertech.api.armor.MultiItemArmor;
import com.kbi.qwertech.client.models.ModelUpgradeStation;
import com.kbi.qwertech.tileentities.CraftingTableT1;
import com.kbi.qwertech.tileentities.UpgradeDesk;

import gregapi.data.CS;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class UpgradeDeskRenderer extends TileEntitySpecialRenderer {
	
	public ModelUpgradeStation model;
	public RenderPlayer RP;
	public ResourceLocation resource = new ResourceLocation("qwertech:textures/blocks/modeled/upgradestand.png");
	public static UpgradeDeskRenderer instance;
	
	public UpgradeDeskRenderer()
	{
		model = new ModelUpgradeStation();
		RP = new RenderPlayer();
		instance = this;
	}
	
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
        GL11.glPopMatrix();
	}

	public void renderTileEntityAt(UpgradeDesk tileEntity, double x, double y, double z, float partialTick)
	{
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)x, (float)y + 2, (float)z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        
        short dir = tileEntity.getFacing();
        if (dir == CS.SIDE_Z_NEG)
        {
        	GL11.glRotatef(180, 0, 1, 0);
        } else if (dir == CS.SIDE_X_NEG)
        {
        	GL11.glRotatef(90, 0, 1, 0);
        } else if (dir == CS.SIDE_X_POS)
        {
        	GL11.glRotatef(-90, 0, 1, 0);
        }
        
		Minecraft.getMinecraft().renderEngine.bindTexture(resource);
		model.setPrimaryColor(tileEntity.getMaterial().mRGBaSolid);
		model.render(null, 0F, 0F, 0F, 0F, 0F, 0.0625F, 0, 0, 0);

		GL11.glScalef(1, -1, -1);
		
		if (tileEntity.slotHas(0))
		{
			ItemStack aStack = tileEntity.slot(0);
			Item aItem = aStack.getItem();
			if (aItem instanceof MultiItemArmor)
			{
				IArmorStats tStats = ((MultiItemArmor) aItem).getArmorStats(aStack);
				if (tStats != null)
				{
					int isIt = -1;
					for (int q = 0; q < 4 && isIt == -1; q++)
					{
						if (tStats.isValidInSlot(q))
						{
							isIt = q;
						}
					}
					//need to set up fake player. am tired.
					//net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel event = new net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel(Minecraft.getMinecraft().thePlayer, RP, 3 - isIt, 0F, aStack);
					//net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
				}
			} else {
				renderItem(aStack, 0, 0, -0.66, 0.033, tileEntity.getWorld(), 3, 1);
			}
		}
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
		this.renderTileEntityAt((UpgradeDesk)tileEntity, x, y, z, partialTick);
	}

}
