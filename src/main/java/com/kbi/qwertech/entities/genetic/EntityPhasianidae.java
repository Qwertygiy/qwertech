package com.kbi.qwertech.entities.genetic;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.entities.EntityHelperFunctions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeHooks;

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
        if ((species == -1 || subtype == -1) && !p_i1682_1_.isRemote) {
            species = 0;
            subtype = 0;
            BiomeGenBase theBiome = p_i1682_1_.getBiomeGenForCoords(this.serverPosX, this.serverPosZ);
            List<Species> possible = MobSpeciesRegistry.getSpeciesForBiome(this.getClass(), theBiome);
            if (possible.size() > 0) {
                short chosen = (short)p_i1682_1_.rand.nextInt(possible.size());
                List<Subtype> subtypes = possible.get(chosen).getSubtypesForBiome(theBiome);
                if (subtypes.size() > 0) {
                    species = chosen;
                    subtype = (short)this.rand.nextInt(subtypes.size());
                }
            }
            Species endResult = MobSpeciesRegistry.getSpecies(this.getClass(), species);
            assignRandomStats(p_i1682_1_.rand, endResult, endResult.getSubtype(subtype));
        }
    }

    @Override
    public float getShadowSize()
    {
        return this.height * 0.5F;
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
        setPrimaryColor(primaryColor);
        setSecondaryColor(secondaryColor);
        setSize(data[0]);
        setStrength(data[1]);
        setStamina(data[2]);
        setSmart(data[3]);
        setSnarl(data[4]);
        setMutable(data[5]);
        setFertility(data[6]);
        setMaturity(data[7]);
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
    protected void dropFewItems(boolean isPlayer, int looting) {
        Species spec = MobSpeciesRegistry.getSpecies(this.getClass(), getSpeciesID());
        if (ST.valid(spec.getRare())) {
            ItemStack rare = spec.getRare().copy();
            if (isPlayer && this.rand.nextInt(100) < 3 + looting) {
                ST.drop(this, rare);
            }
        }

        short minSize = spec.getSubtype(getSubtypeID()).getMinSize();
        short maxSize = spec.getSubtype(getSubtypeID()).getMaxSize();
        short range = (short)(maxSize - minSize);
        if (ST.valid(spec.getSecondary())) {
            ItemStack drop = spec.getSecondary().copy();
            drop.stackSize = getSize() < minSize + (range * 0.2) ? 1 : getSize() > maxSize - (range * 0.2) ? 3 : 2;
            drop.stackSize = drop.stackSize * (1 + this.rand.nextInt(looting + 1));
            ST.drop(this, drop);
        }
        if (ST.valid(spec.getMeat())) {
            ItemStack meat = spec.getMeat().copy();
            meat.stackSize = 1;
            ST.drop(this, meat);
        }
    }

    @Override
    public EntityPhasianidae createChild(EntityAgeable p_90011_1_) {
        EntityPhasianidae returnable = null;
        if (EntityHelperFunctions.doesMobFitSpecies((EntityPhasianidae)p_90011_1_, MobSpeciesRegistry.getSpecies(this.getClass(), this.species), 1F) || EntityHelperFunctions.doesMobFitSpecies(this, MobSpeciesRegistry.getSpecies(((IGeneticMob)p_90011_1_).getClass(), ((EntityPhasianidae)p_90011_1_).getSpeciesID()), 1F))
        {
            returnable = new EntityPhasianidae(this.worldObj, this.species, this.subtype);
            EntityHelperFunctions.createOffspring(returnable, this, (IGeneticMob) p_90011_1_);
        }
        return returnable;
    }

    //if stronger than heavy, falls like chicken.
    //otherwise, get a bonus for however much strength they have,
    //and a penalty for however much weight they have
    @Override
    protected void fall(float p_70069_1_) {
        if(getStrength() > getSize()) {
            super.fall(p_70069_1_);
        } else {
            p_70069_1_ = ForgeHooks.onLivingFall(this, p_70069_1_);
            if (p_70069_1_ <= 1) return;
            //super.fall(p_70069_1_);
            PotionEffect potioneffect = this.getActivePotionEffect(Potion.jump);
            float f1 = potioneffect != null ? (float)(potioneffect.getAmplifier() + 1) : 0.0F;
            float f2 = (getStrength() * 0.002F) - (getSize() * 0.001F);
            int i = MathHelper.ceiling_float_int(p_70069_1_ - 3.0F - f1 - f2);

            if (i > 0)
            {
                this.playSound(this.func_146067_o(i), 1.0F, 1.0F);
                this.attackEntityFrom(DamageSource.fall, (float)i);
                int j = MathHelper.floor_double(this.posX);
                int k = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
                int l = MathHelper.floor_double(this.posZ);
                Block block = this.worldObj.getBlock(j, k, l);

                if (block.getMaterial() != Material.air)
                {
                    Block.SoundType soundtype = block.stepSound;
                    this.playSound(soundtype.getStepResourcePath(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
                }
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (short)0);
        this.dataWatcher.addObject(19, (short)0);
        this.dataWatcher.addObject(20, (short)0);
        this.dataWatcher.addObject(21, (short)0);
        this.dataWatcher.addObject(22, (short)0);
        this.dataWatcher.addObject(23, (short)0);
        this.dataWatcher.addObject(24, (short)0);
        this.dataWatcher.addObject(25, (short)0);
        this.dataWatcher.addObject(26, 0);
        this.dataWatcher.addObject(27, 0);
        this.dataWatcher.addObject(28, (short)0);
        this.dataWatcher.addObject(29, (short)0);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return getSize() > 2000;
    }

    @Override
    public short getSpeciesID() {
        return this.dataWatcher.getWatchableObjectShort(18);
    }

    @Override
    public short getSubtypeID() {
        return this.dataWatcher.getWatchableObjectShort(19);
    }

    @Override
    public short getSize() {
        short returnable = this.dataWatcher.getWatchableObjectShort(20);
        if (returnable != data[0]) {
            data[0] = returnable;
            this.setSize((float)(returnable * 0.0001), (float)(returnable * 0.0002));
        }
        return returnable;
    }

    @Override
    public short getStrength() {
        return this.dataWatcher.getWatchableObjectShort(21);
    }

    @Override
    public short getStamina() {
        return this.dataWatcher.getWatchableObjectShort(22);
    }

    @Override
    public short getSmart() {
        return this.dataWatcher.getWatchableObjectShort(23);
    }

    @Override
    public short getSnarl() {
        return this.dataWatcher.getWatchableObjectShort(24);
    }

    @Override
    public short getMutable() {
        return this.dataWatcher.getWatchableObjectShort(25);
    }

    @Override
    public int getPrimaryColor() {
        return this.dataWatcher.getWatchableObjectInt(26);
    }

    @Override
    public int getSecondaryColor() {
        return this.dataWatcher.getWatchableObjectInt(27);
    }

    @Override
    public short getFertility() {
        return this.dataWatcher.getWatchableObjectShort(28);
    }

    @Override
    public short getMaturity() {
        return this.dataWatcher.getWatchableObjectShort(29);
    }

    @Override
    public void setSpeciesID(short species) {
        this.species = species;
        this.dataWatcher.updateObject(18, species);
    }

    @Override
    public void setSubtypeID(short subtype) {
        this.subtype = subtype;
        this.dataWatcher.updateObject(19, subtype);
    }

    @Override
    public void setSize(short size) {
        data[0] = size;
        this.dataWatcher.updateObject(20, size);
        this.setSize((float)(size * 0.0001), (float)(size * 0.0002));
    }

    @Override
    public void setStrength(short strength) {
        data[1] = strength;
        this.dataWatcher.updateObject(21, strength);
    }

    @Override
    public void setStamina(short stamina) {
        data[2] = stamina;
        this.dataWatcher.updateObject(22, stamina);
    }

    @Override
    public void setSmart(short smart) {
        data[3] = smart;
        this.dataWatcher.updateObject(23, smart);
    }

    @Override
    public void setSnarl(short snarl) {
        data[4] = snarl;
        this.dataWatcher.updateObject(24, snarl);
    }

    @Override
    public void setMutable(short mutable) {
        data[5] = mutable;
        this.dataWatcher.updateObject(25, mutable);
    }

    @Override
    public void setPrimaryColor(int color) {
        primaryColor = color;
        this.dataWatcher.updateObject(26, color);
    }

    @Override
    public void setSecondaryColor(int color) {
        secondaryColor = color;
        this.dataWatcher.updateObject(27, color);
    }

    @Override
    public void setFertility(short fertility) {
        data[6] = fertility;
        this.dataWatcher.updateObject(28, fertility);
    }

    @Override
    public void setMaturity(short maturity) {
        data[7] = maturity;
        this.dataWatcher.updateObject(29, maturity);
    }
}
