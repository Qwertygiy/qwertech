package com.kbi.qwertech.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.kbi.qwertech.client.models.ModelBaseTool;
import com.kbi.qwertech.client.models.ModelClub;
import com.kbi.qwertech.client.models.ModelEgg;

public class QT_Food_Renderer implements IItemRenderer {
	
	protected ModelClub clubRenderer;
	protected ModelEgg modelEgg;
	private float transparency = 0.5F;
	
	public QT_Food_Renderer()
	{
		clubRenderer = new ModelClub();
		modelEgg = new ModelEgg();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
			case EQUIPPED:
		    case EQUIPPED_FIRST_PERSON:
		    {
		    	return true;
		    }
		    default:
		    {
		    	//if (item.getItemDamage() == 13 && type == type.ENTITY) return true;
		    	return false;
		    }
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void renderPlainItem(EntityLivingBase p_78443_1_, ItemStack p_78443_2_, int p_78443_3_, ItemRenderType type)
	{
		GL11.glPushMatrix();
		IIcon iicon = p_78443_1_.getItemIcon(p_78443_2_, p_78443_3_);

        if (iicon == null)
        {
            GL11.glPopMatrix();
            return;
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(p_78443_2_.getItemSpriteNumber()));
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
        ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);

        if (p_78443_2_.hasEffect(p_78443_3_))
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(768, 1, 1, 0);
            float f7 = 0.76F;
            GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float f8 = 0.125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getResourceLocation(p_78443_2_.getItemSpriteNumber()));
        TextureUtil.func_147945_b();
        GL11.glPopMatrix();
	}
	
	public void renderModel(short[] primary, short[] secondary, ModelBaseTool model, ItemStack item, Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ResourceLocation texture, int rotatex, int rotatey, int rotatez, float translatex, float translatey, float translatez)
	{
	   	GL11.glDisable(GL11.GL_ALPHA_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING);
	   	GL11.glEnable(GL11.GL_BLEND);
	   	OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		//GL11.glEnable(GL11.GL_ALPHA);
	   	model.setPrimaryColor(primary);	    	
	   	model.setSecondaryColor(secondary);
    	model.setTransparency(1.0F);
	   	GL11.glPushMatrix();
	   	model.render(entity, f, f1, f2, f3, f4, f5, texture, rotatex, rotatey, rotatez, translatex, translatey, translatez);
	   	GL11.glPopMatrix();
	   	GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	      
	}
	
	public void renderType(short[] primary, short[] secondary, String type, ItemStack item, Entity entity, boolean isFP)
	{
		if (type == "club")
		{
			if (!isFP)
			{
				renderModel(primary, secondary, clubRenderer, item, entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/club.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
			} else {
				GL11.glScalef(0.8F, 0.8F, 0.8F);
				renderModel(primary, secondary, clubRenderer, item, entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/club.png"), 15, 0, -45, -0F, 0.5F, 0F);
			}
		} else if (type == "egg")
		{
			if (!isFP)
			{
				renderModel(primary, secondary, modelEgg, item, entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/entity/egg.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
			} else {
				renderModel(primary, secondary, modelEgg, item, entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/entity/egg.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
			}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		boolean isFirst = false;
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			isFirst = true;
		}
		short[] primary = new short[]{0, 0, 0, 0};
		short[] secondary = new short[]{0, 0, 0, 0};
		switch(item.getItemDamage())
		{
			case 7:
				primary = new short[]{230, 180, 160, 255};
				secondary = new short[]{255, 240, 240, 255};
			    renderType(primary, secondary, "club", item, (Entity)data[1], isFirst);
				break;
			case 8:
				primary = new short[]{190, 130, 100, 255};
				secondary = new short[]{255, 240, 240, 255};
			    renderType(primary, secondary, "club", item, (Entity)data[1], isFirst);
				break;
			case 16:
				primary = new short[]{240, 190, 170, 255};
				secondary = new short[]{255, 240, 240, 255};
			    renderType(primary, secondary, "club", item, (Entity)data[1], isFirst);
				break;
			case 17:
				primary = new short[]{210, 140, 100, 255};
				secondary = new short[]{255, 240, 240, 255};
			    renderType(primary, secondary, "club", item, (Entity)data[1], isFirst);
				break;
			case 18:
				primary = new short[]{220, 200, 150, 255};
				secondary = new short[]{255, 240, 240, 255};
			    renderType(primary, secondary, "club", item, (Entity)data[1], isFirst);
				break;
			case 19:
			{
				primary = new short[]{220, 200, 150, 255};
				secondary = new short[]{255, 240, 240, 255};
			    renderType(primary, secondary, "club", item, (Entity)data[1], isFirst);
				break;
			}
			case 13:
			{
				primary = new short[]{250, 220, 220, 255};
				secondary = new short[]{250, 220, 220, 255};
			    renderType(primary, secondary, "egg", item, (Entity)data[1], isFirst);
				break;
			}
			case 14:
			{
				primary = new short[]{250, 220, 220, 255};
				secondary = new short[]{250, 220, 220, 255};
			    renderType(primary, secondary, "egg", item, (Entity)data[1], isFirst);
				break;
			}
			default:
			{
				for (int i = 0; i < item.getItem().getRenderPasses(item.getItemDamage()); i++)
	            {
	                int j = item.getItem().getColorFromItemStack(item, i);
	                float f5 = (j >> 16 & 255) / 255.0F;
	                float f2 = (j >> 8 & 255) / 255.0F;
	                float f3 = (j & 255) / 255.0F;
	                GL11.glColor4f(f5, f2, f3, 1.0F);
	                renderPlainItem((EntityLivingBase)data[1], item, i, type);
	            }
			}
		}
		GL11.glPopMatrix();
	}

}
