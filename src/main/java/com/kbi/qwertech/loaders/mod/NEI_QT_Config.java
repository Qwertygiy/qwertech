package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.tileentities.CraftingTableT1.GUIClientAdvancedCraftingTable;
import com.kbi.qwertech.tileentities.CraftingTableT2.GUIClientAdvancedCraftingTable2;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.NEI_RecipeMap;
import gregapi.data.MD;
import gregapi.data.RM;

import static gregapi.data.CS.CODE_CLIENT;
import static gregapi.data.CS.GAPI;

@InterfaceList(value = {
		@Interface(iface = "codechicken.nei.api.IConfigureNEI", modid = "NotEnoughItems")
		})
public class NEI_QT_Config implements codechicken.nei.api.IConfigureNEI
{
	public static boolean sIsAdded = true;
	
	@Override
	public void loadConfig()
	{
	  sIsAdded = false;
	  new NEI_Tool_Handler();
	  new NEI_Hammer_Handler();
	  new NEI_Wood_Handler();
	  new NEI_3D_Handler();
	  NEI_RecipeMap chisel = new NEI_RecipeMap(RM.Chisel);
	  FMLInterModComms.sendRuntimeMessage(GAPI, "NEIPlugins", "register-crafting-handler", MD.GAPI.mID+"@"+chisel.getRecipeName()+"@"+chisel.getOverlayIdentifier());
	  codechicken.nei.recipe.GuiCraftingRecipe.craftinghandlers.add(chisel);
	  codechicken.nei.recipe.GuiUsageRecipe.usagehandlers.add(chisel);
	  
	  if (CODE_CLIENT) {
      	codechicken.nei.api.API.registerGuiOverlay(GUIClientAdvancedCraftingTable.class, "crafting", 80, 17);
      	codechicken.nei.api.API.registerGuiOverlayHandler(GUIClientAdvancedCraftingTable.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 11), "crafting");
      	codechicken.nei.api.API.registerGuiOverlay(GUIClientAdvancedCraftingTable2.class, "crafting", 80, 17);
      	codechicken.nei.api.API.registerGuiOverlayHandler(GUIClientAdvancedCraftingTable2.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 11), "crafting");
      	//codechicken.nei.api.API.registerGuiOverlay(GUIClientAdvancedCraftingTable3.class, "crafting", 80, 17);
      	//codechicken.nei.api.API.registerGuiOverlayHandler(GUIClientAdvancedCraftingTable3.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 11), "crafting");
      }
	  sIsAdded = true;
	}
	
	@Override
	public String getName()
	{
	  return QwerTech.MODNAME + " NEI Plugin";
	}
	
	@Override
	public String getVersion()
	{
	  return "1.1.0";
	}
}