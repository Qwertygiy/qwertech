package com.kbi.qwertech.api.recipe;

import gregapi.code.ICondition;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;

public class Prefix3DRecipe extends HammerableShapedRecipe {

	public Prefix3DRecipe(OreDictPrefix output, Object... items) {
		super(output, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, ICondition condition,
			Object... items) {
		super(output, condition, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, OreDictMaterial material,
			Object... items) {
		super(output, material, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, OreDictMaterial material,
			ICondition condition, Object... items) {
		super(output, material, condition, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, int amount,
			ICondition condition, Object... items) {
		super(output, amount, condition, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, int amount, Object... items) {
		super(output, amount, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, int amount,
			OreDictMaterial material, Object... items) {
		super(output, amount, material, items);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

	public Prefix3DRecipe(OreDictPrefix output, int amount,
			OreDictMaterial material, ICondition condition, Object[] recipe) {
		super(output, amount, material, condition, recipe);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

}
