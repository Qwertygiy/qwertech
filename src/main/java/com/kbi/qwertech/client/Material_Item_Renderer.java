package com.kbi.qwertech.client;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;

import java.util.HashMap;

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
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.kbi.qwertech.client.models.ModelBaseTool;
import com.kbi.qwertech.client.models.ModelGear;
import com.kbi.qwertech.client.models.ModelIngot;
import com.kbi.qwertech.client.models.ModelStick;
import com.kbi.qwertech.client.models.ModelStickLong;

public class Material_Item_Renderer implements IItemRenderer {

	protected ModelIngot ingotRenderer;
	protected ModelStick stickRenderer;
	protected ModelStickLong stickLongRenderer;
	protected ModelGear gearRenderer;
	protected HashMap<String, OreDictMaterial> materialMap = new HashMap();
	protected float transparency = 0.5F;
	
	public Material_Item_Renderer()
	{
		ingotRenderer = new ModelIngot();
		stickRenderer = new ModelStick();
		stickLongRenderer = new ModelStickLong();
		gearRenderer = new ModelGear();
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
	
	public String getMaterial(ItemStack item)
	{
		int[] IDs = OreDictionary.getOreIDs(item);
		if (IDs.length > 0)
		{
			return getMaterial(OreDictionary.getOreName(IDs[0]));
		}
		return "";
	}
	
	public String getMaterial(String name)
	{
		String getter = "steel";
		String[] prefixes = {"ingot", "rod", "stickLong", "stick", "gearGt", "rockGt", "scrapGt", "gear"};
		for (int q = 0; q < prefixes.length; q++)
		{
			if (name.startsWith(prefixes[q]))
			{
				getter = name.substring(prefixes[q].length());
				break;
			}
		}
		return getter;
	}
	
	public OreDictMaterial assignMaterial(ItemStack item)
	{
		int[] IDs = OreDictionary.getOreIDs(item);
		if (IDs.length > 0)
		{
			return assignMaterial(OreDictionary.getOreName(IDs[0]));
		}
		return MT.NULL;
	}
	
	public OreDictMaterial assignMaterial(String name)
	{
		OreDictMaterial registrate = OreDictMaterial.get(getMaterial(name));
		materialMap.put(name, registrate);
		return registrate;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		switch (type)
		{
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
			{
				return true;
			}
			default:
			{
				break;
			}
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}
	
	public ResourceLocation getRusted(ItemStack item)
	{
		if (item.hasTagCompound() && item.getTagCompound().hasKey("QT.ToolData") && item.getTagCompound().getCompoundTag("QT.ToolData").getBoolean("Rusted"))
		{
			OreDictMaterial mainMat = materialMap.get(getMaterial(item));
			if (OP.ingot.mat(mainMat, 1) != null)
			{
				return new ResourceLocation("qwertech:textures/items/modeled/rusty.png");
			} else if (OP.gem.mat(mainMat, 1) != null)
			{
				return new ResourceLocation("qwertech:textures/items/modeled/chipped.png");
			} else {
				return new ResourceLocation("qwertech:textures/items/modeled/cracked.png");
			}
		}
		return null;
	}
	
	public void renderModel(ModelBaseTool model, ItemStack item, OreDictMaterial material, Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ResourceLocation texture, int rotatex, int rotatey, int rotatez, float translatex, float translatey, float translatez)
	{
	   	GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
	   	GL11.glEnable(GL11.GL_BLEND);
	   	OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		//GL11.glEnable(GL11.GL_ALPHA);
	   	if (material != MT.NULL)
	   	{
	   		model.setPrimaryColor(material.mRGBaSolid);	    	
	   		model.setSecondaryColor(material.mRGBaSolid);
	   	} else {
	   		model.setPrimaryColor(MT.Wood.mRGBaSolid);
	   		model.setSecondaryColor(MT.Wood.mRGBaSolid);
	   	}
	   	model.setTransparency(1.0F);
	   	GL11.glPushMatrix();
	   	model.render(entity, f, f1, f2, f3, f4, f5, texture, rotatex, rotatey, rotatez, translatex, translatey, translatez);
	   	GL11.glPopMatrix();
	   	if (getRusted(item) != null)
	   	{
	    	GL11.glPushMatrix();
		   	Minecraft.getMinecraft().renderEngine.bindTexture(getRusted(item));
            model.setTransparency(transparency);
		   	model.render(entity, f, f1, f2, f3, f4, f5, rotatex, rotatey, rotatez, translatex, translatey, translatez);
		    GL11.glColor4f(1F, 1F, 1F, 1F);
		   	GL11.glPopMatrix();
	    }
	   	GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		OreDictMaterial itemMat = MT.NULL;
		if (materialMap.containsKey(getMaterial(item)))
		{
			itemMat = materialMap.get(getMaterial(item));
		}
		if (itemMat == null || itemMat == MT.NULL)
		{
			itemMat = assignMaterial(item);
		}
		if (itemMat != null && itemMat != MT.NULL)
		{
			//String ODName = OreDictionary.getOreName(OreDictionary.getOreID(item));
			if (OP.ingot.contains(item))
			{
				switch (type) {
			      case EQUIPPED:
			      {		    	
			    	renderModel(ingotRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/ingot.png"), 15, 0, -155, 0.5F, 0.20F, -0.03F);
			    	break;
			      }
			      case EQUIPPED_FIRST_PERSON:
			      {
			    	renderModel(ingotRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/ingot.png"), 15, 0, -155, 0.5F, 0.20F, -0.03F);
			    	break;
			      }
			      default:
			        break;
				}
			} else if (OP.gearGt.contains(item) || OP.gear.contains(item))
			{
				switch (type) {
			      case EQUIPPED:
			      {		    	
			    	renderModel(gearRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 15, 0, -155, 0.5F, 0.20F, -0.03F);
			    	break;
			      }
			      case EQUIPPED_FIRST_PERSON:
			      {
			    	renderModel(gearRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 15, 0, -155, 0.5F, 0.20F, -0.03F);
			    	break;
			      }
			      default:
			        break;
				}
			} else if (OP.stick.contains(item) || OP.rod.contains(item))
			{
				switch (type) {
			      case EQUIPPED:
			      {		    	
			    	renderModel(stickRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
			    	break;
			      }
			      case EQUIPPED_FIRST_PERSON:
			      {
			    	renderModel(stickRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
			    	break;
			      }
			      default:
			        break;
				}
			} else if (OP.stickLong.contains(item))
			{
				switch (type) {
			      case EQUIPPED:
			      {		    	
			    	renderModel(stickLongRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
			    	break;
			      }
			      case EQUIPPED_FIRST_PERSON:
			      {
			    	renderModel(stickLongRenderer, item, itemMat, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
			    	break;
			      }
			      default:
			        break;
				}
			} else {
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
		} else {
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
		GL11.glPopMatrix();
	}

}
