package com.kbi.qwertech.api.recipe;

import gregapi.code.ICondition;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;

public class QTArmor3D extends QTArmor {
	
	public QTArmor3D(long aArmorID, Object[] recipe)
	{
		this(aArmorID, MT.NULL, ICondition.TRUE, recipe);
	}
	
	public QTArmor3D(long aArmorID, ICondition condition, Object[] recipe)
	{
		this(aArmorID, MT.NULL, condition, recipe);
	}
	
	public QTArmor3D(long aArmorID, OreDictMaterial mat, Object[] recipe)
	{
		this(aArmorID, mat, ICondition.TRUE, recipe);
	}

	public QTArmor3D(long aArmorID, OreDictMaterial material,
			ICondition condition, Object[] recipe) {
		super(aArmorID, material, condition, recipe);
		MAX_CRAFT_GRID_HEIGHT = 9;
	}

}
