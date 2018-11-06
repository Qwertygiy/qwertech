package com.kbi.qwertech;

import com.kbi.qwertech.api.data.FOOD;
import com.kbi.qwertech.api.data.QTConfigs;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.recipe.CountertopRecipe;
import com.kbi.qwertech.api.registry.ArmorUpgradeRegistry;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.client.*;
import com.kbi.qwertech.client.blocks.RenderCorrugated;
import com.kbi.qwertech.client.entity.genetic.RenderGeneticEntity;
import com.kbi.qwertech.client.entity.neutral.RenderTurkey;
import com.kbi.qwertech.client.entity.passive.RenderFrog;
import com.kbi.qwertech.client.entity.projectile.*;
import com.kbi.qwertech.client.models.entity.*;
import com.kbi.qwertech.client.tileentity.CountertopRenderer;
import com.kbi.qwertech.client.tileentity.CraftingTable3DRenderer;
import com.kbi.qwertech.client.tileentity.CraftingTableRenderer;
import com.kbi.qwertech.client.tileentity.UpgradeDeskRenderer;
import com.kbi.qwertech.entities.genetic.EntityPhasianidae;
import com.kbi.qwertech.entities.neutral.EntityTurkey;
import com.kbi.qwertech.entities.passive.EntityFrog;
import com.kbi.qwertech.entities.projectile.*;
import com.kbi.qwertech.loaders.RegisterSpecies;
import com.kbi.qwertech.tileentities.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.api.Abstract_Mod;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS;
import gregapi.data.LH;
import gregapi.data.OP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.*;

public final class ClientProxy extends CommonProxy { // NO_UCD (unused code)
	// Insert your Clientside-only implementation of Stuff here
	
	@Override
	public void onProxyAfterInit			(Abstract_Mod aMod, FMLInitializationEvent		aEvent)
	{
		Material_Item_Renderer itemRenderer = new Material_Item_Renderer();
		MinecraftForgeClient.registerItemRenderer(QTI.syringe.getItem(), new FluidContainerRenderer());
		MinecraftForgeClient.registerItemRenderer(QTI.buckets.getItem(), new FluidContainerRenderer());
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
		MinecraftForgeClient.registerItemRenderer(MultiTileEntityRegistry.getRegistry("qwertech.machines").getItem(401).getItem(), new QT_Machine_Renderer());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFoil.class,
				new RenderEntityFoil());
		RenderingRegistry.registerEntityRenderingHandler(EntityBall.class,
				new RenderEntityBall());
		RenderingRegistry.registerEntityRenderingHandler(EntityRock.class, 
			      new RenderEntityRock());
		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class,
				new RenderEntityShuriken());
		RenderingRegistry.registerEntityRenderingHandler(EntityEgg.class, new RenderEntityEgg());
		RenderingRegistry.registerEntityRenderingHandler(EntityTurkey.class, new RenderTurkey(new ModelTurkey(), 0.8F));
		//RenderingRegistry.registerEntityRenderingHandler(EntityZombie.class,
				//new RenderZombieFix());
		RenderingRegistry.registerEntityRenderingHandler(EntityPhasianidae.class, new RenderGeneticEntity(new GeneticModelHandler(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class, new RenderFrog(new ModelFrog(), 0.5F));
		
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT1.class, new CraftingTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT2.class, new CraftingTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT3.class, new CraftingTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(CraftingTableT4.class, new CraftingTable3DRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(UpgradeDesk.class, new UpgradeDeskRenderer());

		ClientRegistry.bindTileEntitySpecialRenderer(CuttingBoardTileEntity.class, new CountertopRenderer());
		
		wallRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(wallRenderID, new RenderCorrugated());
		registerModels();
		System.out.println("REGISTEREDRENDERER");
	}

	public void registerModel(Class<? extends IGeneticMob> classy, int species, int subtype, ModelBase model, boolean isChild)
	{
		if (!isChild)
		{
			Species[] speciesList = MobSpeciesRegistry.getSpeciesList(classy);
			if (subtype > -1)
			{
				RegisterSpecies.setModel(speciesList[species].getSubtype((short)subtype), model);
			} else {
				RegisterSpecies.setModel(speciesList[species], model);
			}
		} else {
			Species[] speciesList = MobSpeciesRegistry.getSpeciesList(classy);
			if (subtype > -1)
			{
				RegisterSpecies.setChildModel(speciesList[species].getSubtype((short)subtype), model);
			} else {
				RegisterSpecies.setChildModel(speciesList[species], model);
			}
		}
	}

	public void registerModel(Class<? extends IGeneticMob> classy, int species, int subtype, ModelBase model)
	{
		registerModel(classy, species, subtype, model, false);
	}

	public void registerModels()
	{
		registerModel(EntityPhasianidae.class, 0, -1, new ModelChicken());
		registerModel(EntityPhasianidae.class, 0, 1, new ModelChickenCrested());
		registerModel(EntityPhasianidae.class, 0, 2, new ModelChickenCrested());
		registerModel(EntityPhasianidae.class, 0, 3, new ModelChickenCrested());
		for (int q = 4; q < 12; q++) {
			registerModel(EntityPhasianidae.class, 0, q, new ModelChickenTailed());
		}
		registerModel(EntityPhasianidae.class, 0, 12, new ModelSuperChicken());

		//red junglefowl
		registerModel(EntityPhasianidae.class, 1, -1, new ModelWildChicken());
		registerModel(EntityPhasianidae.class, 1, 3, new ModelGallusGallusJabouille());
		//grey junglefowl
		registerModel(EntityPhasianidae.class, 2, -1, new ModelGallusGallusJabouille());

		//turkey
		registerModel(EntityPhasianidae.class, 3, -1, new ModelTurkeyFat());
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

	    	if (event.itemStack.hasTagCompound())
			{
				NBTTagCompound tag = event.itemStack.stackTagCompound;
				if (tag.hasKey("qt.food"))
				{
					event.toolTip.add("Quality: " + FOOD.getQuality(event.itemStack));
					event.toolTip.add("Quantity: " + FOOD.getQuantity(event.itemStack));
				}
			}

	    	if (event.entityPlayer.openContainer instanceof CuttingBoardTileEntity.GUICommonCuttingBoard)
			{
				CuttingBoardTileEntity.GUICommonCuttingBoard CB = (CuttingBoardTileEntity.GUICommonCuttingBoard)event.entityPlayer.openContainer;

				for (int w = 0; w < 15; w++)
				{
					ItemStack stack = CB.craftResults.getStackInSlot(w);
					if (stack == event.itemStack)
					{
						CountertopRecipe recipe = CB.currentRecipes.get(CB.craftResults.starting + w);
						List<Object> input = recipe.getInputList();
						HashMap<String, Integer> amounts = new HashMap<String, Integer>();
						for (int q = 0; q < input.size(); q++)
						{
							Object ob = input.get(q);
							if (ob instanceof ItemStack)
							{
								if (amounts.containsKey(((ItemStack)ob).getDisplayName())) {
									amounts.put(((ItemStack) ob).getDisplayName(), amounts.get(((ItemStack) ob).getDisplayName()) + ((ItemStack) ob).stackSize);
								} else {
									amounts.put(((ItemStack) ob).getDisplayName(), ((ItemStack) ob).stackSize);
								}
							} else if (ob instanceof ArrayList && ((ArrayList)ob).size() > 0)
							{
								ItemStack IS = ((ArrayList<ItemStack>)ob).get(0);
								if (amounts.containsKey(IS.getDisplayName())) {
									amounts.put(IS.getDisplayName(), amounts.get(IS.getDisplayName()) + IS.stackSize);
								} else {
									amounts.put(IS.getDisplayName(), IS.stackSize);
								}
							} else if (ob instanceof String)
							{
								if (amounts.containsKey((String)ob)) {
									amounts.put((String) ob, amounts.get((String) ob + 1));
								} else {
									amounts.put((String)ob, 1);
								}
							}
						}
						Iterator iterable = amounts.entrySet().iterator();
						while (iterable.hasNext())
						{
							Map.Entry entry = (Map.Entry)iterable.next();
							event.toolTip.add(LH.Chat.CYAN + (String)entry.getKey() + ": " + (Integer)entry.getValue());
						}

					}
				}
			}
	    }
}