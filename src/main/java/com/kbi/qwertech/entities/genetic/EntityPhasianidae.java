package com.kbi.qwertech.entities.genetic;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.List;
import java.util.Random;

public class EntityPhasianidae extends EntityChicken implements IGeneticMob {

    private short[] data = new short[8];
    private short species = -1;
    private short subtype = -1;
    private int primaryColor = 0;
    private int secondaryColor = 0;

    public EntityPhasianidae(World p_i1682_1_) {
        super(p_i1682_1_);
        if (species == -1 || subtype == -1) {
            species = 0;
            subtype = 0;
            BiomeGenBase theBiome = p_i1682_1_.getBiomeGenForCoords(this.serverPosX, this.serverPosZ);
            List<Species> possible = MobSpeciesRegistry.getSpeciesForBiome(this.getClass(), theBiome);
            if (possible.size() > 0) {
                Species chosen = possible.get(p_i1682_1_.rand.nextInt(possible.size()));
                List<Subtype> subtypes = chosen.getSubtypesForBiome(theBiome);
                if (subtypes.size() > 0) {
                    assignRandomStats(p_i1682_1_.rand, chosen, subtypes.get(p_i1682_1_.rand.nextInt(subtypes.size())));
                }
            }
        }
    }

    public EntityPhasianidae(World world, short aSpecies, short aSubtype)
    {
        super(world);
        species = aSpecies;
        subtype = aSubtype;
        Species theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), species);
        Subtype theSubtype = theSpecies.getSubtype(aSubtype);
        assignRandomStats(world.rand, theSpecies, theSubtype);
    }

    public void assignRandomStats(Random rand, Species theSpecies, Subtype theSubtype)
    {
        data[0] = (short)(rand.nextInt(theSubtype.getMaxSize() - theSubtype.getMinSize()) + theSubtype.getMinSize());
        data[1] = (short)(rand.nextInt(theSubtype.getMaxStrength() - theSubtype.getMinStrength()) + theSubtype.getMinStrength());
        data[2] = (short)(rand.nextInt(theSubtype.getMaxStamina() - theSubtype.getMinStamina()) + theSubtype.getMinStamina());
        data[3] = (short)(rand.nextInt(theSubtype.getMaxSmart() - theSubtype.getMinSmart()) + theSubtype.getMinSmart());
        data[4] = (short)(rand.nextInt(theSubtype.getMaxSnarl() - theSubtype.getMinSnarl()) + theSubtype.getMinSnarl());
        data[5] = (short)(rand.nextInt(theSubtype.getMaxMutable() - theSubtype.getMinMutable()) + theSubtype.getMinMutable());
        data[6] = (short)(rand.nextInt(theSubtype.getMaxFertility() - theSubtype.getMinFertility()) + theSubtype.getMinFertility());
        data[7] = (short)(rand.nextInt(theSubtype.getMaxMaturity() - theSubtype.getMinMaturity()) + theSubtype.getMinMaturity());
        primaryColor = COLOR.getRandom(theSubtype.getMinPrimaryColor(), theSubtype.getMaxPrimaryColor());
        secondaryColor = COLOR.getRandom(theSubtype.getMinSecondaryColor(), theSubtype.getMaxSecondaryColor());

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        NBTTagCompound genetics = new NBTTagCompound();
        genetics.setShort("species", getSpeciesID());
        genetics.setShort("subtype", getSubtypeID());
        genetics.setShort("size", getSize());
        genetics.setShort("strength", getStrength());
        genetics.setShort("stamina", getStamina());
        genetics.setShort("smart", getSmart());
        genetics.setShort("snarl", getSnarl());
        genetics.setShort("mutable", getMutable());
        genetics.setShort("fertility", getFertility());
        genetics.setShort("maturity", getMaturity());
        genetics.setInteger("color1", getPrimaryColor());
        genetics.setInteger("color2", getSecondaryColor());
        tag.setTag("QTgenes", genetics);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("QTgenes")) {
            NBTTagCompound genetics = tag.getCompoundTag("QTgenes");
            setSpeciesID(genetics.getShort("species"));
            setSubtypeID(genetics.getShort("subtype"));
            setSize(genetics.getShort("size"));
            setStrength(genetics.getShort("strength"));
            setStamina(genetics.getShort("stamina"));
            setSmart(genetics.getShort("smart"));
            setSnarl(genetics.getShort("snarl"));
            setMutable(genetics.getShort("mutable"));
            setFertility(genetics.getShort("fertility"));
            setMaturity(genetics.getShort("maturity"));
            setPrimaryColor(genetics.getInteger("color1"));
            setSecondaryColor(genetics.getInteger("color2"));
        }
    }

    @Override
    protected String getLivingSound() {
        return MobSpeciesRegistry.getSpecies(this.getClass(), getSpeciesID()).getSubtype(getSubtypeID()).getLivingSound();
    }

    @Override
    protected String getHurtSound() {
        return MobSpeciesRegistry.getSpecies(this.getClass(), getSpeciesID()).getSubtype(getSubtypeID()).getHurtSound();
    }

    @Override
    protected String getDeathSound() {
        return MobSpeciesRegistry.getSpecies(this.getClass(), getSpeciesID()).getSubtype(getSubtypeID()).getDeathSound();
    }

    @Override
    protected Item getDropItem() {
        return MobSpeciesRegistry.getSpecies(this.getClass(), getSpeciesID()).getMeat().getItem();
    }

    @Override
    public EntityPhasianidae createChild(EntityAgeable p_90011_1_) {
        return new EntityPhasianidae(this.worldObj, this.species, this.subtype);
    }

    @Override
    public short getSpeciesID() {
        return species;
    }

    @Override
    public short getSubtypeID() {
        return subtype;
    }

    @Override
    public short getSize() {
        return data[0];
    }

    @Override
    public short getStrength() {
        return data[1];
    }

    @Override
    public short getStamina() {
        return data[2];
    }

    @Override
    public short getSmart() {
        return data[3];
    }

    @Override
    public short getSnarl() {
        return data[4];
    }

    @Override
    public short getMutable() {
        return data[5];
    }

    @Override
    public int getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public int getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public short getFertility() {
        return data[6];
    }

    @Override
    public short getMaturity() {
        return data[7];
    }

    @Override
    public void setSpeciesID(short species) {
        this.species = species;
    }

    @Override
    public void setSubtypeID(short subtype) {
        this.subtype = subtype;
    }

    @Override
    public void setSize(short size) {
        data[0] = size;
    }

    @Override
    public void setStrength(short strength) {
        data[1] = strength;
    }

    @Override
    public void setStamina(short stamina) {
        data[2] = stamina;
    }

    @Override
    public void setSmart(short smart) {
        data[3] = smart;
    }

    @Override
    public void setSnarl(short snarl) {
        data[4] = snarl;
    }

    @Override
    public void setMutable(short mutable) {
        data[5] = mutable;
    }

    @Override
    public void setPrimaryColor(int color) {
        primaryColor = color;
    }

    @Override
    public void setSecondaryColor(int color) {
        secondaryColor = color;
    }

    @Override
    public void setFertility(short fertility) {
        data[6] = fertility;
    }

    @Override
    public void setMaturity(short maturity) {
        data[7] = maturity;
    }
}
