package com.kbi.qwertech.api.recipe.listeners;

import gregapi.code.ICondition;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.F;

public class OreProcessing_NonCrafting
implements IOreDictListenerEvent
{
	private final ICondition mCondition;
	private final RecipeMap mHandler;
	private final Object mInput;
	private final Object mOutput;

	public OreProcessing_NonCrafting(RecipeMap aHandler, Item input, ItemStack output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), output, aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Item input, OreDictPrefix output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), output, aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, ItemStack input, Item output, ICondition aCondition){this(aHandler, input, ST.make(output, 1, 0), aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Item input, Item output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), ST.make(output, 1, 0), aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Block input, ItemStack output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), output, aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Block input, OreDictPrefix output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), output, aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Block input, Item output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), ST.make(output, 1, 0), aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Block input, Block output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), ST.make(output, 1, 0), aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, ItemStack input, Block output, ICondition aCondition){this(aHandler, input, ST.make(output, 1, 0), aCondition);}
	public OreProcessing_NonCrafting(RecipeMap aHandler, Item input, Block output, ICondition aCondition){this(aHandler, ST.make(input, 1, 0), ST.make(output, 1, 0), aCondition);}
	
	public OreProcessing_NonCrafting(RecipeMap aHandler, Object input, Object output, ICondition aCondition)
	{
	  this.mCondition = aCondition;
	  this.mHandler = aHandler;
	  this.mInput = input;
	  this.mOutput = output;
	}
	
	public ItemStack materialize(ItemStack item, OreDictMaterial mat)
	{
		if (item.getItem() instanceof MultiItemTool)
		{
			return ((MultiItemTool)item.getItem()).getToolWithStats(item.getItemDamage(), mat, mat.mHandleMaterial);
		}
		return item;
	}
	
	public void onOreRegistration(IOreDictListenerEvent.OreDictRegistrationContainer aEvent)
	{
		if ((aEvent.mNotAlreadyRegisteredName) && (aEvent.mMaterial.contains(TD.Properties.HAS_TOOL_STATS)) && (this.mCondition.isTrue(aEvent.mMaterial)))
		  {
			Object ourInput = this.mInput;
			Object ourOutput = this.mOutput;
			if (ourInput == null) {ourInput = aEvent.mStack;} else if (ourInput instanceof ItemStack) {ourInput = materialize((ItemStack)this.mInput, aEvent.mMaterial);}
			if (ourOutput == null) {ourOutput = aEvent.mStack;} else if (ourOutput instanceof OreDictPrefix) {ourOutput = ((OreDictPrefix)ourOutput).mat(aEvent.mMaterial, 1);} else if (ourOutput instanceof ItemStack) {ourOutput = materialize((ItemStack)this.mOutput, aEvent.mMaterial);}
			this.mHandler.addRecipe1(F, 16, 32+32*aEvent.mMaterial.mToolQuality, (ItemStack)ourInput, (ItemStack)ourOutput);
		  }
	}
}