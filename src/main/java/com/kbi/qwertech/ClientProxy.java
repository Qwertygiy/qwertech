package com.kbi.qwertech;

import gregapi.api.Abstract_Mod;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.OP;

import java.util.Iterator;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.client.GT_Tool_Renderer;
import com.kbi.qwertech.client.Material_Item_Renderer;
import com.kbi.qwertech.client.QT_Armor_Renderer;
import com.kbi.qwertech.client.QT_Food_Renderer;
import com.kbi.qwertech.client.QT_Tool_Renderer;
import com.kbi.qwertech.client.blocks.RenderCorrugated;
import com.kbi.qwertech.client.entity.neutral.RenderTurkey;
import com.kbi.qwertech.client.entity.passive.RenderFrog;
import com.kbi.qwertech.client.entity.projectile.RenderEntityBall;
import com.kbi.qwertech.client.entity.projectile.RenderEntityEgg;
import com.kbi.qwertech.client.entity.projectile.RenderEntityFoil;
import com.kbi.qwertech.client.entity.projectile.RenderEntityRock;
import com.kbi.qwertech.client.entity.projectile.RenderEntityShuriken;
import com.kbi.qwertech.client.models.ModelFrog;
import com.kbi.qwertech.client.models.ModelTurkey;
import com.kbi.qwertech.client.tileentity.CraftingTable3DRenderer;
import com.kbi.qwertech.client.tileentity.CraftingTableRenderer;
import com.kbi.qwertech.entities.neutral.EntityTurkey;
import com.kbi.qwertech.entities.passive.EntityFrog;
import com.kbi.qwertech.entities.projectile.EntityBall;
import com.kbi.qwertech.entities.projectile.EntityEgg;
import com.kbi.qwertech.entities.projectile.EntityFoil;
import com.kbi.qwertech.entities.projectile.EntityRock;
import com.kbi.qwertech.entities.projectile.EntityShuriken;
import com.kbi.qwertech.tileentities.CraftingTableT1;
import com.kbi.qwertech.tileentities.CraftingTableT2;
import com.kbi.qwertech.tileentities.CraftingTableT3;
import com.kbi.qwertech.tileentities.CraftingTableT4;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Max Mustermann
 * 
 * An example implementation for a Clientside Proxy using my System.
 */
public final class ClientProxy extends CommonProxy { // NO_UCD (unused code)
	// Insert your Clientside-only implementation of Stuff here
	
	@Override
	public void onProxyAfterInit			(Abstract_Mod aMod, FMLInitializationEvent		aEvent)
	{
		Material_Item_Renderer itemRenderer = new Material_Item_Renderer();
		if (QTConfigs.add3DQwerTools)
		{
			MinecraftForgeClient.registerItemRenderer(QTI.qwerTool.getItem(), new QT_Tool_Renderer());
			MinecraftForgeClient.registerItemRenderer(QTI.qwerFood.getItem(), new QT_Food_Renderer());
		}
		if (QTConfigs.enableArmor)
		{
			MinecraftForgeClient.registerItemRenderer(QTI.qwerArmor.getItem(), new QT_Armor_Renderer());
		}
		if (QTConfigs.add3DGregTools)
		{
			MinecraftForgeClient.registerItemRenderer(CS.ToolsGT.sMetaTool, new GT_Tool_Renderer());
		}
		if (QTConfigs.add3DPrefixes)
		{
			Iterator<ItemStackContainer> ingot = OP.ingot.mRegisteredItems.iterator();
			while (ingot.hasNext())
			{
				MinecraftForgeClient.registerItemRenderer(ingot.next().mItem, itemRenderer);
			}
			Iterator<ItemStackContainer> stick = OP.stick.mRegisteredItems.iterator();
			while (stick.hasNext())
			{
				MinecraftForgeClient.registerItemRenderer(stick.next().mItem, itemRenderer);
			}
			Iterator<ItemStackContainer> gear = OP.gear.mRegisteredItems.iterator();
			while (gear.hasNext())
			{
				MinecraftForgeClient.registerItemRenderer(gear.next().mItem, itemRenderer);
			}
			/*Iterator<ItemStackContainer> stickLong = OP.stickLong.mRegisteredItems.iterator();
			while (stickLong.hasNext())
			{
				MinecraftForgeClient.registerItemRenderer(stickLong.next().mItem, itemRenderer);
			}*/
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFoil.class,
				new RenderEntityFoil());
		RenderingRegistry.registerEntityRenderingHandler(EntityBall.class,
				new RenderEntityBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityRock.class, 
			      new RenderEntityRock());
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class,
				new RenderEntityShuriken());
		RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, new RenderEntityEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityTurkey.class, new RenderTurkey(new ModelTurkey(), 1));
		//RenderingRegistry.registerEntityRenderingHandler(EntityZombie.class,
				//new RenderZombieFix());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class, new RenderFrog(new ModelFrog(), 1));
		
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT1.class, new CraftingTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT2.class, new CraftingTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT3.class, new CraftingTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT4.class, new CraftingTable3DRenderer());
		
		wallRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(wallRenderID, new RenderCorrugated());
		System.out.println("REGISTEREDRENDERER");
	}
	
	@Override
	public void onProxyAfterPostInit		(Abstract_Mod aMod, FMLPostInitializationEvent	aEvent) {
		MinecraftForge.EVENT_BUS.register(this);
		//this allows it to read game events for other classes without having to be added unnecessarily to all the game building events.
	}
	
	 @SubscribeEvent
	    public void onTooltip(ItemTooltipEvent event)
	    {
	    	if (ArmorUpgradeRegistry.instance.getUpgrade(event.itemStack) != null)
	    	{
	    		event.toolTip.add(LH.Chat.GOLD + "Can be used to upgrade armor");
	    	}
	    }
}