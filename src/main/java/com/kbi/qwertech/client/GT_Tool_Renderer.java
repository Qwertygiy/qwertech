package com.kbi.qwertech.client;

import com.kbi.qwertech.client.models.*;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictMaterial;
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

public class GT_Tool_Renderer implements IItemRenderer {
	
	protected ModelHammer hammerRenderer;
	protected ModelClub clubRenderer;
	protected ModelDoubleAxe doubleAxeRenderer;
	protected ModelCrowbar crowbarRenderer;
	protected ModelWrench wrenchRenderer;
	protected ModelScythe scytheRenderer;
	protected ModelBigKnife bigKnifeRenderer;
	protected ModelKnife knifeRenderer;
	protected ModelScrewdriver screwdriverRenderer;
	protected ModelChisel chiselRenderer;
	private float transparency = 0.5F;
	
	public GT_Tool_Renderer()
	{
		hammerRenderer = new ModelHammer();
		clubRenderer = new ModelClub();
		doubleAxeRenderer = new ModelDoubleAxe();
		crowbarRenderer = new ModelCrowbar();
		wrenchRenderer = new ModelWrench();
		scytheRenderer = new ModelScythe();
		bigKnifeRenderer = new ModelBigKnife();
		knifeRenderer = new ModelKnife();
		screwdriverRenderer = new ModelScrewdriver();
		chiselRenderer = new ModelChisel();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if (item.getItemDamage() == 48 || item.getItemDamage() == 22 || item.getItemDamage() == 34 || item.getItemDamage() == 36 || item.getItemDamage() == 40 || item.getItemDamage() == 16 || item.getItemDamage() == 20 || item.getItemDamage() == 24 || item.getItemDamage() == 12 || item.getItemDamage() == 14 || item.getItemDamage() == 58) {
			switch (type) {
		      case EQUIPPED:
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	  //System.out.println("WE HAVE THE THINGY");
		    	  return true;
		      }
		      default:
		        return false;
		    }
		} else if (type == ItemRenderType.EQUIPPED) {
			//return true; //it doesn't fix zombies :(
			return false;
		} else {
			//System.out.println("WE CHECKED BUT HAVE NO THINGY");
			return false;
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
	
	public ResourceLocation getRusted(ItemStack item)
	{
		if (item.hasTagCompound() && item.getTagCompound().hasKey("QT.ToolData") && item.getTagCompound().getCompoundTag("QT.ToolData").getBoolean("Rusted"))
		{
			OreDictMaterial mainMat = MultiItemTool.getPrimaryMaterial(item);
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
	
	public void renderModel(ModelBaseTool model, ItemStack item, Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ResourceLocation texture, int rotatex, int rotatey, int rotatez, float translatex, float translatey, float translatez)
	{
	   	GL11.glDisable(GL11.GL_ALPHA_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING);
	   	GL11.glEnable(GL11.GL_BLEND);
	   	OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		//GL11.glEnable(GL11.GL_ALPHA);
	   	model.setPrimaryColor(MultiItemTool.getPrimaryMaterial(item, MT.Brass).mRGBaSolid);	    	
	   	model.setSecondaryColor(MultiItemTool.getSecondaryMaterial(item, MT.Wood).mRGBaSolid);
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
        //GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	      
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		switch (item.getItemDamage()) {
			case 12:
			case 14:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(hammerRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/hammer.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(hammerRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/hammer.png"), 15, 0, -45, 0F, 0.4F, -0.1F);
						break;
					}
					default:
						break;
				}
				break;
			case 22:
				switch (type) {
					case EQUIPPED: {
						renderModel(screwdriverRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/screwdriver.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(screwdriverRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/screwdriver.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
						break;
					}
					default:
						break;
				}
				break;
			case 48:
				switch (type) {
					case EQUIPPED: {
						renderModel(chiselRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/chisel.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(chiselRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/chisel.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
						break;
					}
					default:
						break;
				}
				break;
			case 34:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(knifeRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/knife.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(knifeRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/knife.png"), 15, 0, -45, 0F, 0.5F, 0F);
						break;
					}
					default:
						break;
				}
				break;
			case 36:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(bigKnifeRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/bigknife.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(bigKnifeRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/bigknife.png"), 15, 0, -45, 0.0F, 0.5F, 0.0F);
						break;
					}
					default:
						break;
				}
				break;
			case 58:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(doubleAxeRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/doubleaxe.png"), 0, 0, -20, -0.1F, 0.25F, 0.0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(doubleAxeRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/doubleaxe.png"), 15, 0, -45, 0F, 0.5F, 0F);
						break;
					}
					default:
						break;
				}
				break;
			case 20:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(crowbarRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/crowbar.png"), 0, 0, -20, -0.1F, 0.33F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(crowbarRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/crowbar.png"), 15, 0, -45, -0F, 0.5F, 0F);
						break;
					}
					default:
						break;
				}
				break;
			case 40:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(scytheRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/scythe.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(scytheRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/scythe.png"), 15, -45, -45, -0F, 0.5F, 0F);
						break;
					}
					default:
						break;
				}
				break;
			case 16:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(wrenchRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/wrench.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(wrenchRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/wrench.png"), 15, 0, -45, -0F, 0.5F, 0F);
						break;
					}
					default:
						break;
				}
				break;
			case 24:
				switch (type) {
					case EQUIPPED:
						//case EQUIPPED_FIRST_PERSON:
					{
						renderModel(clubRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/club.png"), 0, 0, -20, -0.1F, 0.25F, 0F);
						break;
					}
					case EQUIPPED_FIRST_PERSON: {
						renderModel(clubRenderer, item, (Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/club.png"), 15, 0, -45, -0F, 0.5F, 0F);
						break;
					}
					default:
						break;
				}
				break;
			default:
				for (int i = 0; i < item.getItem().getRenderPasses(item.getItemDamage()); i++) {
					int j = item.getItem().getColorFromItemStack(item, i);
					float f5 = (j >> 16 & 255) / 255.0F;
					float f2 = (j >> 8 & 255) / 255.0F;
					float f3 = (j & 255) / 255.0F;
					GL11.glColor4f(f5, f2, f3, 1.0F);
					renderPlainItem((EntityLivingBase) data[1], item, i, type);
				}
				break;
		}
		GL11.glPopMatrix();
	}

}
