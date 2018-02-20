package com.kbi.qwertech.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class ModelBaseTool extends ModelBase {
	public short[] handleColor;
    public short[] bladeColor;
    public float transparency = 1.0F;
    
    public void setPrimaryColor(short[] rgb)
    {
    	this.bladeColor = rgb;
    }
    
    public void setSecondaryColor(short[] rgb)
    {
    	this.handleColor = rgb;
    }
    
    public void applyColorPrimary(float transparency)
    {
    	float red = (this.bladeColor[0]) / 255.0F;
     	float green = (this.bladeColor[1]) / 255.0F;
     	float blue = (this.bladeColor[2]) / 255.0F;
     	GL11.glColor4f(red, green, blue, transparency);
    }
    
    public void applyColorSecondary(float transparency)
    {
    	float red = (this.handleColor[0]) / 255.0F;
     	float green = (this.handleColor[1]) / 255.0F;
     	float blue = (this.handleColor[2]) / 255.0F;
     	GL11.glColor4f(red, green, blue, transparency);
    }
    
    public void applyColorPrimary()
    {
    	this.applyColorPrimary(this.transparency);
    }
    
    public void applyColorSecondary()
    {
    	this.applyColorSecondary(this.transparency);
    }
    
    public void setTransparency(float trans)
    {
    	this.transparency = trans;
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
    {
    	
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int rotatex, int rotatey, int rotatez)
    {
    	this.render(entity, f, f1, f2, f3, f4, f5, rotatex, rotatey, rotatez, 0, 0, 0);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ResourceLocation texture, int rotatex, int rotatey, int rotatez)
    {
    		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    		this.render(entity, f, f1, f2, f3, f4, f5, rotatex, rotatey, rotatez, 0, 0, 0);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ResourceLocation texture, int rotatex, int rotatey, int rotatez, float translatex, float translatey, float translatez)
    {
    		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    		this.render(entity, f, f1, f2, f3, f4, f5, rotatex, rotatey, rotatez, translatex, translatey, translatez);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int rotatex, int rotatey, int rotatez, float translatex, float translatey, float translatez)
    {
    	GL11.glTranslatef(translatex, translatey, translatez);
    	GL11.glRotatef(rotatex, 1.0F, 0.0F, 0.0F);
    	GL11.glRotatef(rotatey, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(rotatez, 0.0F, 0.0F, 1.0F);
    	
    	this.render(entity, f, f1, f2, f3, f4, f5);
    }
}
