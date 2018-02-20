package com.kbi.qwertech.client;

import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.kbi.qwertech.client.models.ModelAxe;
import com.kbi.qwertech.client.models.ModelBaseTool;
import com.kbi.qwertech.client.models.ModelBat;
import com.kbi.qwertech.client.models.ModelKnuckles;
import com.kbi.qwertech.client.models.ModelMace;
import com.kbi.qwertech.client.models.ModelMattock;
import com.kbi.qwertech.client.models.ModelPick;
import com.kbi.qwertech.client.models.ModelPokeyStick;
import com.kbi.qwertech.client.models.ModelShovel;
import com.kbi.qwertech.client.models.ModelSlingshot;

public class QT_Tool_Renderer implements IItemRenderer {
	
	protected ModelKnuckles knucklesRenderer;
	protected ModelMace maceRenderer;
	protected ModelSlingshot slingshotRenderer;
	protected ModelPokeyStick stakeRenderer;
	protected ModelBat batRenderer;
	protected ModelMattock mattockRenderer;
	protected ModelAxe axeRenderer;
	protected ModelShovel shovelRenderer;
	protected ModelPick pickaxeRenderer;
	private float transparency = 0.5F;
	
	public QT_Tool_Renderer()
	{
		knucklesRenderer = new ModelKnuckles();
		maceRenderer = new ModelMace();
		slingshotRenderer = new ModelSlingshot();
		stakeRenderer = new ModelPokeyStick();
		batRenderer = new ModelBat();
		mattockRenderer = new ModelMattock();
		axeRenderer = new ModelAxe();
		shovelRenderer = new ModelShovel();
		pickaxeRenderer = new ModelPick();
	}
	
	public ItemStack getLaunchable(EntityPlayer player)
	{
		ItemStack[] inventory = player.inventory.mainInventory;
		ItemStack returnable = null;
		for (int q = 0; q < inventory.length; q++)
		{
			if (inventory[q] != null) {
				if (OP.rockGt.contains(inventory[q]))
				{
					returnable = inventory[q];
					break;
				} else if (OP.chunkGt.contains(inventory[q])) {
					returnable = inventory[q];
					break;
				} else if (OP.foil.contains(inventory[q])) {
					returnable = inventory[q];
					break;
				} else if (OreDictManager.isItemStackInstanceOf(inventory[q], "cropTomato") || (inventory[q]).getItem() == Items.egg || (inventory[q]).getItem() == Items.slime_ball || (inventory[q]).getItem() == IL.Mud_Ball.getItem()) {
					returnable = inventory[q];
					break;
				} else {
					//do nothing, keep going
				}
			}
		}
		return returnable;
	}
	
	public void renderSlingshot(ItemStack item, ItemRenderType type, Object... data)
	{
		GL11.glDisable(GL11.GL_ALPHA_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING);
	   	GL11.glEnable(GL11.GL_BLEND);
	   	OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		slingshotRenderer.setHandleMaterial(MultiItemTool.getPrimaryMaterial(item, MT.Brass));
    	float typeData = 0;
    	float itemInUse = 0;
    	if ((EntityPlayer)data[1] != null)
    	{
    		ItemStack launchable = getLaunchable((EntityPlayer)data[1]);
    		EntityPlayer player = (EntityPlayer)data[1];
    		itemInUse = player.getItemInUseCount();
    		if (launchable != null) {
	    		if (OP.chunkGt.contains(launchable))
	    		{
	    			typeData = 0;
	    		} else if (OP.rockGt.contains(launchable))
	    		{
	    			typeData = 1;
	    		} else if (launchable.getItem() == Items.egg)
	    		{
	    			typeData = 3;
	    		} else if (OP.foil.contains(launchable))
	    		{
	    			typeData = 2;
	    		} else if (launchable.getItem() == Items.slime_ball)
	    		{
	    			typeData = 4;
	    		} else if (launchable.getItem() == IL.Mud_Ball.getItem() && launchable.getItemDamage() == IL.Mud_Ball.get(1, (Object[])null).getItemDamage())
	    		{
	    			typeData = 5;
	    		} else if (OreDictManager.isItemStackInstanceOf(launchable, "cropTomato"))
	    		{
	    			typeData = 6;
	    		}
	    		if (typeData <= 2)
		    	{
	    			if (OM.anydata(launchable) != null)
	    			{
	    				slingshotRenderer.setRockMaterial(OM.anydata(launchable).mMaterial.mMaterial);
	    			}
		    	}
    		} else {
    			itemInUse = 0;
    		}
    	}
    	GL11.glPushMatrix();
    	slingshotRenderer.render((Entity)data[1], typeData, itemInUse, 0.0F, 0.0F, 0.0F, 0.0625F);
    	GL11.glPopMatrix();
	   	GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if (OreDictManager.isItemStackInstanceOf(item, "toolKnuckles")) {
			switch (type) {
		      case EQUIPPED:
		      //case EQUIPPED_FIRST_PERSON:
		      {
		    	  //System.out.println("WE HAVE THE THINGY");
		    	  return true;
		      }
		      default:
		        return false;
		    }
		} else if (item.getItemDamage() == 18 || item.getItemDamage() == 16 || item.getItemDamage() == 14 || item.getItemDamage() == 0 || item.getItemDamage() == 12 || item.getItemDamage() == 10 || OreDictManager.isItemStackInstanceOf(item, "toolMace") || OreDictManager.isItemStackInstanceOf(item, "toolSlingshot")) {
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
		} else {
			//System.out.println("WE CHECKED BUT HAVE NO THINGY");
			return false;
		}
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
    	if (model == batRenderer && MultiItemTool.getSecondaryMaterial(item, MT.Empty) == MT.Empty)
    	{
    		model.setSecondaryColor(batRenderer.defaultHandleColor);
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
        //GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	      
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		if (OreDictManager.isItemStackInstanceOf(item, "toolKnuckles")) {
			switch (type) {
		      case EQUIPPED:
		      //case EQUIPPED_FIRST_PERSON:
		      {
		    	renderModel(knucklesRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F, new ResourceLocation("qwertech:textures/items/modeled/knuckles.png"), 0, 0, 0, 0F, 0F, 0F);
		    	break;
		      }
		      default:
		        break;
		    }
		} else if (item.getItemDamage() == 0) {
			switch (type) {
				case EQUIPPED:
				{
					renderModel(mattockRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/doubleaxe.png"), 0, 0, -20, -0.1F, 0.25F, 0.0F);
			    	break;
			    }
			    case EQUIPPED_FIRST_PERSON:
			    { 	
			    	renderModel(mattockRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/doubleaxe.png"), 15, 0, -45, 0F, 0.5F, 0F);
			    	break;
			    }
				default:
					break;
			}
		} else if (item.getItemDamage() == 18) {
			switch (type) {
		      case EQUIPPED:
		      {		    	
		    	renderModel(pickaxeRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/pickaxe.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	renderModel(pickaxeRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/pickaxe.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      default:
		        break;
			}
		} else if (item.getItemDamage() == 16) {
			switch (type) {
		      case EQUIPPED:
		      {		    	
		    	renderModel(shovelRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	renderModel(shovelRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/shovel.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      default:
		        break;
			}
		} else if (item.getItemDamage() == 10) {
			switch (type) {
		      case EQUIPPED:
		      {		    	
		    	renderModel(stakeRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/stake.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	renderModel(stakeRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/stake.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      default:
		        break;
			}
		} else if (item.getItemDamage() == 12) {
			switch (type) {
		      case EQUIPPED:
		      {		    	
		    	renderModel(batRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/bat.png"), 0, 0, -130, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	renderModel(batRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/bat.png"), 15, 0, -155, 0.8F, 0.25F, -0.03F);
		    	break;
		      }
		      default:
		        break;
			}
		} else if (item.getItemDamage() == 14) {
			switch (type) {
		      case EQUIPPED:
		      {
		    	  renderModel(axeRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/doubleaxe.png"), 0, 0, -20, -0.1F, 0.25F, 0.0F);
		    	  break;
			  }
			  case EQUIPPED_FIRST_PERSON:
			  { 	
				  renderModel(axeRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/doubleaxe.png"), 15, 0, -45, 0F, 0.5F, 0F);
				  break;
			  }
		      default:
		        break;
			}
		} else if (OreDictManager.isItemStackInstanceOf(item, "toolMace")) {
			switch (type) {
		      case EQUIPPED:
		      //case EQUIPPED_FIRST_PERSON:
		      {		    	
		    	renderModel(maceRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/mace.png"), 0, 0, 0, 0F, 0F, 0F);
		    	break;
		      }
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	renderModel(maceRenderer, item, (Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, new ResourceLocation("qwertech:textures/items/modeled/mace.png"), 15, 0, -45, 0F, 0.4F, -0.1F);
		    	break;
		      }
		      default:
		        break;
		    }
		} else if (OreDictManager.isItemStackInstanceOf(item, "toolSlingshot")) {
			switch (type) {
		      case EQUIPPED:
		      //case EQUIPPED_FIRST_PERSON:
		      {
		    	
		    	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("qwertech:textures/items/modeled/slingshot.png"));
		    	
		    	float scale = 1.0F;
		    	
		    	GL11.glScalef(scale, scale, scale);
		    	GL11.glTranslatef(0.2F, -0.25F, 0.0F);
		    	GL11.glRotatef(20, 0.0F, 0F, 1F);
		    	
		    	GL11.glColor4f(1F, 1F, 1F, 1F);
		    	renderSlingshot(item, type, data);
		    	break;
		      }
		      case EQUIPPED_FIRST_PERSON:
		      {
		    	
		    	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("qwertech:textures/items/modeled/slingshot.png"));
		    	
		    	float scale = 1.0F;
		    	
		    	GL11.glScalef(scale, scale, scale);
		    	
		    	GL11.glTranslatef(0.0F, 0.5F, -0.1F);
		    	GL11.glRotatef(-45, 0.0F, 0F, 1F);
		    	GL11.glRotatef(15, 1.0F, 0F, 0F);
		    	
		    	GL11.glColor4f(1F, 1F, 1F, 1F);
		    	renderSlingshot(item, type, data);
		    	break;
		      }
		      default:
		        break;
		    }
		}
		GL11.glPopMatrix();
	}

}
