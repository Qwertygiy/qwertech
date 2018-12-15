package com.kbi.qwertech.client.entity.projectile;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.client.models.ModelFoil;
import com.kbi.qwertech.entities.projectile.EntityFoil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderEntityFoil extends RenderEntity {
	
	protected ModelFoil renderer;
	
	public RenderEntityFoil()
	{
		renderer = new ModelFoil();
	}
	
	public void renderFoil(EntityFoil entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		GL11.glPushMatrix();
    	
    	this.bindTexture(new ResourceLocation("qwertech:textures/entity/rock/base.png"));
    	
    	float scale = 1.0F;
    	
    	GL11.glScalef(scale, scale, scale);
    	GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
    	 GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
    	//GL11.glRotatef(-90, 1.0F, 0F, 0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
    	short[] l = entity.getMaterial().mRGBaSolid;
    	//System.out.println("Render Material is " + entity.getMaterial().mNameLocal);
        float f5 = (l[0]) / 255.0F;
        float f6 = (l[1]) / 255.0F;
        float f7 = (l[2]) / 255.0F;
        GL11.glColor3f(f5, f6, f7);
    	renderer.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
    	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    	GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		renderFoil((EntityFoil)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	@Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return new ResourceLocation(QwerTech.MODID+":textures/entity/rock/base.png");
    }
	
}
