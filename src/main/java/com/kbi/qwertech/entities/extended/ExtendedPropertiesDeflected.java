package com.kbi.qwertech.entities.extended;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPropertiesDeflected implements IExtendedEntityProperties {

	public ExtendedPropertiesDeflected()
	{
		
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setBoolean("isDeflected", true);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		//do nothing lol
	}

	@Override
	public void init(Entity entity, World world) {
		//not required, just need to mark entity as deflected
	}

}
