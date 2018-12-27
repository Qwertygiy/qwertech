package com.kbi.qwertech.entities.ai;

import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.entities.EntityHelperFunctions;
import com.kbi.qwertech.loaders.RegisterSpecies;
import gregapi.util.UT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;

public class EntityAILayEgg extends EntityAIBase {

    private boolean isFertilized;
    private ItemStack eggStack;
    private EntityLivingBase ourEntity;
    private int minEggTimer;
    private int maxEggTimer;
    private int eggTimer;

    private void setEggData()
    {
        Species species = ((IGeneticMob)ourEntity).getSpecies();
        Subtype subtype = ((IGeneticMob)ourEntity).getSubtype();
        ItemStack toReturn = QTI.qwerFood.getWithDamage(1, 32);
        if (subtype.hasTag(RegisterSpecies.ITEM_EGG))
        {
            toReturn = ((ItemStack)subtype.getTag(RegisterSpecies.ITEM_EGG)).copy();
        } else if (species.hasTag(RegisterSpecies.ITEM_EGG))
        {
            toReturn = ((ItemStack)species.getTag(RegisterSpecies.ITEM_EGG)).copy();
        }
        NBTTagCompound tag = UT.NBT.getOrCreate(toReturn);
        if (subtype.hasTag(RegisterSpecies.COLOR_EGG))
        {
            tag.setInteger("itemColor", (Integer)subtype.getTag(RegisterSpecies.COLOR_EGG));
        } else if (species.hasTag(RegisterSpecies.COLOR_EGG))
        {
            tag.setInteger("itemColor", (Integer)species.getTag(RegisterSpecies.COLOR_EGG));
        }
        toReturn.setTagCompound(tag);
        eggStack = toReturn;
    }

    public EntityAILayEgg(EntityLivingBase entity, int minTime, int maxTime)
    {
        ourEntity = entity;
        minEggTimer = minTime;
        maxEggTimer = maxTime;
        eggTimer = (int)Math.floor((maxEggTimer - ourEntity.getRNG().nextInt(((IGeneticMob)ourEntity).getFertility() + 1)) * 0.25) + minEggTimer;
        setEggData();
        setMutexBits(5);
    }


    @Override
    public void startExecuting() {
        //System.out.println("STARTIN'");
        ourEntity.playSound("mob.chicken.plop", 1.0F, (ourEntity.getRNG().nextFloat() - ourEntity.getRNG().nextFloat()) * 0.2F + 1.0F);
        ItemStack egg = eggStack.copy();
        NBTTagCompound nbt = UT.NBT.getOrCreate(egg);
        if (((IGeneticMob)ourEntity).isFertilized()) {
            ((IGeneticMob)ourEntity).setFertilized(false);
            EntityLivingBase returnable;
            try {
                Constructor constructor = ourEntity.getClass().getConstructor(World.class);
                returnable = (EntityLivingBase)constructor.newInstance(ourEntity.worldObj);
                ((IGeneticMob)returnable).setSpeciesID(((IGeneticMob)ourEntity).getSpeciesID());
                ((IGeneticMob)returnable).setSubtypeID(((IGeneticMob)ourEntity).getSubtypeID());
                EntityLivingBase fakeParent = (EntityLivingBase)constructor.newInstance(ourEntity.worldObj);
                fakeParent.readEntityFromNBT(((IGeneticMob)ourEntity).getMateNBT());
                EntityHelperFunctions.createOffspring((IGeneticMob)returnable, (IGeneticMob)ourEntity, (IGeneticMob)fakeParent);
                returnable.writeEntityToNBT(nbt);
                fakeParent.setDead();
                returnable.setDead();
            } catch (Exception e) {
                System.out.println("Error in resolving mate:");
                e.printStackTrace();
                ourEntity.writeEntityToNBT(nbt);
            }
            nbt.setLong("Timer", ourEntity.worldObj.getTotalWorldTime() + (Short.MAX_VALUE - ((IGeneticMob)ourEntity).getMaturity()));
            egg.setTagCompound(nbt);
        }
        //System.out.println("Should be dropping " + egg.getUnlocalizedName() + " at " + ourEntity.posX + "x, " + ourEntity.posY + "y, " + ourEntity.posZ + "z");
        egg.getUnlocalizedName(); //for some reason this makes a difference
        ourEntity.entityDropItem(egg, 0.1F);
        eggTimer = (int)Math.floor((maxEggTimer - ourEntity.getRNG().nextInt(((IGeneticMob)ourEntity).getFertility() + 1)) * 0.25) + minEggTimer;
    }

    @Override
    public boolean continueExecuting() {
        return false;
    }

    @Override
    public boolean shouldExecute() {
        //System.out.println("Checking if we should execute at " + eggTimer);
        if (eggTimer > 0) {
            eggTimer--;
            return false;
        }
        //System.out.println("Should lay egg now");
        return true;
    }
}
