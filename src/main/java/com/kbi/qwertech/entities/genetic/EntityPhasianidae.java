package com.kbi.qwertech.entities.genetic;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.entities.GMIs;
import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.entities.EntityHelperFunctions;
import com.kbi.qwertech.loaders.RegisterSpecies;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeHooks;

import java.util.List;
import java.util.Random;

public class EntityPhasianidae extends EntityChicken implements IGeneticMob, GMIs.IEggLayer, GMIs.IHitAggro, GMIs.IAutoAggro, GMIs.IEatStuffOnTheGround {

    private short[] data = new short[8];
    private short species = -1;
    private short subtype = -1;
    private int primaryColor = 0;
    private int secondaryColor = 0;
    private boolean isFertilized = false;
    private NBTTagCompound lastMate = null;
    private Species theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), (short)0);
    private Subtype theSubtype = theSpecies.getSubtype((short)0);
    public int angryTime = 0;

    public EntityPhasianidae(World p_i1682_1_) {
        super(p_i1682_1_);
        for (Object aiBase : this.tasks.taskEntries)
        {
            if (aiBase instanceof EntityAIPanic || aiBase instanceof EntityAIRunAroundLikeCrazy)
            {
                this.tasks.removeTask((EntityAIBase)aiBase);
            }
        }
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, true));
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
            theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), species);
            theSubtype = theSpecies.getSubtype(subtype);
            assignRandomStats(p_i1682_1_.rand, theSpecies, theSubtype);
            this.timeUntilNextEgg = (int)Math.floor(this.rand.nextInt(Short.MAX_VALUE - this.getFertility() + 1) * 0.1) + 1000;
        }
    }

    public void findTarget()
    {
        AxisAlignedBB aabb = this.getBoundingBox();
        if (aabb == null)
        {
            aabb = AxisAlignedBB.getBoundingBox(this.posX - 1, this.posY - 1, this.posZ - 1, this.posX + 1, this.posY + 1, this.posZ + 1);
        }
        AxisAlignedBB aabbcc = aabb.expand(10, 2, 10);
        List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, aabbcc);
        float topFood = 0F;
        EntityItem theFood = null;
        for (Object entity : entities)
        {
            if (entity instanceof EntityItem)
            {
                float foodValue = this.shouldEatOffTheGround(this, (EntityItem)entity);
                if (foodValue > topFood)
                {
                    topFood = foodValue;
                    theFood = (EntityItem)entity;
                }
            } else if (entity instanceof EntityLiving){
                if (shouldAutoAggro(this, (EntityLiving)entity))
                {
                    System.out.println("We should attack it");
                    this.setAttackTarget((EntityLiving)entity);
                    this.angryTime = 10000;
                    return;
                }
            }
        }
        if (theFood != null)
        {
            this.setTarget(theFood);
            if (this.getDistanceToEntity(theFood) < 0.1F)
            {
                if (this.getGrowingAge() == 0)
                {
                    this.func_146082_f(null);
                } else {
                    this.setGrowingAge(1000);
                }
                theFood.setDead();
            }
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.angryTime > 0)
        {
            --angryTime;
            if (this.angryTime < 1 || this.getAttackTarget() == null || this.getAttackTarget().isDead)
            {
                angryTime = 0;
                this.setAttackTarget(null);
            }
        }
        if (this.getAttackTarget() == null && this.worldObj.getTotalWorldTime() % 40 == 0)
        {
            findTarget();
        }
        if (!this.worldObj.isRemote && !this.isChild() && this.canLayEgg(this) && --this.timeUntilNextEgg <= 0)
        {
            this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            ItemStack egg = this.getEggItem(this);
            NBTTagCompound nbt = UT.NBT.getOrCreate(egg);
            if (this.isFertilized) {
                this.isFertilized = false;
                EntityPhasianidae returnable = new EntityPhasianidae(this.worldObj, this.species, this.subtype);
                EntityPhasianidae fakeParent = new EntityPhasianidae(this.worldObj, this.species, this.subtype);
                fakeParent.readEntityFromNBT(lastMate);
                EntityHelperFunctions.createOffspring(returnable, this, fakeParent);
                returnable.writeEntityToNBT(nbt);
                nbt.setLong("Timer", this.worldObj.getTotalWorldTime() + (Short.MAX_VALUE - this.getMaturity()));
                egg.setTagCompound(nbt);
                returnable.setDead();
                fakeParent.setDead();
            }
            ST.drop(this, egg);
            this.timeUntilNextEgg = (int)Math.floor(this.rand.nextInt(Short.MAX_VALUE - this.getFertility() + 1) * 0.1) + 1000;
        }
    }

    @Override
    public boolean func_152116_bZ()
    {
        return true;
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
        theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), species);
        theSubtype = theSpecies.getSubtype(aSubtype);
        assignRandomStats(world.rand, theSpecies, theSubtype);
    }

    /**
     * assignRandomStats
     * Assigns random stats to each of the mob's genes, where the formula for each gene is
     * take the preferred gene value, then get a random amount that would remain between
     * the maximum and minimum gene values, then multiply that random amount by a random
     * percentage of itself three times, then add that amount to the preferred gene value.
     * This means that the average variation will be 12.5% from the preferred value to
     * the median of the minimum and maximum values.
     * @param rand the random instance to use
     * @param theSpecies the species to assign to
     * @param theSubtype the subtype to assign to
     */
    public void assignRandomStats(Random rand, Species theSpecies, Subtype theSubtype)
    {
        data[0] = (short)(rand.nextInt(theSubtype.getMaxSize() - theSubtype.getMinSize()) + theSubtype.getMinSize());
        data[0] = (short)(theSubtype.getPrefSize() + (Math.round((data[0] - theSubtype.getPrefSize()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[1] = (short)(rand.nextInt(theSubtype.getMaxStrength() - theSubtype.getMinStrength()) + theSubtype.getMinStrength());
        data[1] = (short)(theSubtype.getPrefStrength() + (Math.round((data[1] - theSubtype.getPrefStrength()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[2] = (short)(rand.nextInt(theSubtype.getMaxStamina() - theSubtype.getMinStamina()) + theSubtype.getMinStamina());
        data[2] = (short)(theSubtype.getPrefStamina() + (Math.round((data[2] - theSubtype.getPrefStamina()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[3] = (short)(rand.nextInt(theSubtype.getMaxSmart() - theSubtype.getMinSmart()) + theSubtype.getMinSmart());
        data[3] = (short)(theSubtype.getPrefSmart() + (Math.round((data[3] - theSubtype.getPrefSmart()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[4] = (short)(rand.nextInt(theSubtype.getMaxSnarl() - theSubtype.getMinSnarl()) + theSubtype.getMinSnarl());
        data[4] = (short)(theSubtype.getPrefSnarl() + (Math.round((data[4] - theSubtype.getPrefSnarl()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[5] = (short)(rand.nextInt(theSubtype.getMaxMutable() - theSubtype.getMinMutable()) + theSubtype.getMinMutable());
        data[5] = (short)(theSubtype.getPrefMutable() + (Math.round((data[5] - theSubtype.getPrefMutable()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[6] = (short)(rand.nextInt(theSubtype.getMaxFertility() - theSubtype.getMinFertility()) + theSubtype.getMinFertility());
        data[6] = (short)(theSubtype.getPrefFertility() + (Math.round((data[6] - theSubtype.getPrefFertility()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
        data[7] = (short)(rand.nextInt(theSubtype.getMaxMaturity() - theSubtype.getMinMaturity()) + theSubtype.getMinMaturity());
        data[7] = (short)(theSubtype.getPrefMaturity() + (Math.round((data[7] - theSubtype.getPrefMaturity()) * rand.nextFloat() * rand.nextFloat() * rand.nextFloat())));
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
        tag.setBoolean("fertilized", isFertilized);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("QTgenes")) {
            NBTTagCompound genetics = tag.getCompoundTag("QTgenes");
            setSpeciesID(genetics.getShort("species"));
            setSubtypeID(genetics.getShort("subtype"));
            if (genetics.hasKey("size") && genetics.hasKey("strength")) {
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
            } else {
                theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), getSpeciesID());
                theSubtype = theSpecies.getSubtype(getSubtypeID());
                assignRandomStats(this.rand, theSpecies, theSubtype);
            }
        }
        if (tag.hasKey("fertilized"))
        {
            isFertilized = tag.getBoolean("fertilized");
        }
    }

    @Override
    protected String getLivingSound() {
        return theSubtype.getLivingSound();
    }

    @Override
    protected String getHurtSound() {
        return theSubtype.getHurtSound();
    }

    @Override
    protected String getDeathSound() {
        return theSubtype.getDeathSound();
    }

    @Override
    protected Item getDropItem() {
        return theSpecies.getMeat().getItem();
    }

    @Override
    protected void dropFewItems(boolean isPlayer, int looting) {
        if (ST.valid(theSpecies.getRare())) {
            ItemStack rare = theSpecies.getRare().copy();
            if (isPlayer && this.rand.nextInt(100) < 3 + looting) {
                ST.drop(this, rare);
            }
        }

        short minSize = theSubtype.getMinSize();
        short maxSize = theSubtype.getMaxSize();
        short range = (short)(maxSize - minSize);
        if (ST.valid(theSpecies.getSecondary())) {
            ItemStack drop = theSpecies.getSecondary().copy();
            drop.stackSize = getSize() < minSize + (range * 0.2) ? 1 : getSize() > maxSize - (range * 0.2) ? 3 : 2;
            drop.stackSize = drop.stackSize * (1 + this.rand.nextInt(looting + 1));
            ST.drop(this, drop);
        }
        if (ST.valid(theSpecies.getMeat())) {
            ItemStack meat = theSpecies.getMeat().copy();
            meat.stackSize = 1;
            ST.drop(this, meat);
        }
    }

    @Override
    public EntityPhasianidae createChild(EntityAgeable other) {
        /*EntityPhasianidae returnable = null;
        if (EntityHelperFunctions.doesMobFitSpecies((EntityPhasianidae)p_90011_1_, MobSpeciesRegistry.getSpecies(this.getClass(), this.species), 1F) || EntityHelperFunctions.doesMobFitSpecies(this, MobSpeciesRegistry.getSpecies(((IGeneticMob)p_90011_1_).getClass(), ((EntityPhasianidae)p_90011_1_).getSpeciesID()), 1F))
        {
            returnable = new EntityPhasianidae(this.worldObj, this.species, this.subtype);
            EntityHelperFunctions.createOffspring(returnable, this, (IGeneticMob) p_90011_1_);
        }*/
        NBTTagCompound tag = new NBTTagCompound();
        other.writeToNBT(tag);
        this.lastMate = tag;
        this.isFertilized = true;
        this.setGrowingAge(6000);
        other.setGrowingAge(6000);
        this.resetInLove();
        this.entityToAttack = null;
        other.setAttackTarget(null);
        ((EntityAnimal)other).resetInLove();
        return null;
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
            if (p_70069_1_ <= 2) return;
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
            this.setSize((float)(returnable * 0.0004), (float)(returnable * 0.0006));
            updateHealthAndSpeed();
        }
        return returnable;
    }

    @Override
    public short getStrength() {
        short returnable = this.dataWatcher.getWatchableObjectShort(21);
        return returnable;
    }

    @Override
    public short getStamina() {
        short returnable = this.dataWatcher.getWatchableObjectShort(22);
        return returnable;
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
        this.dataWatcher.updateObject(18, species);
        this.species = species;
        this.theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), species);
    }

    @Override
    public void setSubtypeID(short subtype) {
        this.dataWatcher.updateObject(19, subtype);
        this.subtype = subtype;
        this.theSubtype = theSpecies.getSubtype(subtype);
    }

    @Override
    public void setSize(short size) {
        this.dataWatcher.updateObject(20, size);
        data[0] = size;
        this.setSize((float)(size * 0.0004), (float)(size * 0.0006));
        updateHealthAndSpeed();
    }

    @Override
    public void setStrength(short strength) {
        this.dataWatcher.updateObject(21, strength);
        data[1] = strength;
        updateHealthAndSpeed();
    }

    @Override
    public void setStamina(short stamina) {
        this.dataWatcher.updateObject(22, stamina);
        data[2] = stamina;
        updateHealthAndSpeed();
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

    @Override
    public boolean canLayEgg(IGeneticMob geneticMob) {
        return true;
    }

    @Override
    public ItemStack getEggItem(IGeneticMob geneticMob) {
        ItemStack toReturn = QTI.qwerFood.getWithDamage(1, 32);
        if (theSubtype.hasTag(RegisterSpecies.EGG_ITEM))
        {
            toReturn = ((ItemStack)theSubtype.getTag(RegisterSpecies.EGG_ITEM)).copy();
        } else if (theSpecies.hasTag(RegisterSpecies.EGG_ITEM))
        {
            toReturn = ((ItemStack)theSpecies.getTag(RegisterSpecies.EGG_ITEM)).copy();
        }
        NBTTagCompound tag = UT.NBT.getOrCreate(toReturn);
        if (theSubtype.hasTag(RegisterSpecies.EGG_COLOR))
        {
            tag.setInteger("itemColor", (Integer)theSubtype.getTag(RegisterSpecies.EGG_COLOR));
        } else if (theSpecies.hasTag(RegisterSpecies.EGG_COLOR))
        {
            tag.setInteger("itemColor", (Integer)theSpecies.getTag(RegisterSpecies.EGG_COLOR));
        }
        toReturn.setTagCompound(tag);
        return toReturn;
    }

    @Override
    public boolean willEggHatch(IGeneticMob geneticMob) {
        return isFertilized;
    }

    @Override
    public float shouldAggroOnHit(IGeneticMob geneticMob, EntityLiving attacker) {
        if (getSnarl() < 1000)
        {
            return 0.0F;
        } else if (getSnarl() > 25000)
        {
            return 1.0F;
        }
        Object specTag = theSpecies.getTag(RegisterSpecies.HOSTILEON_HIT);
        Object subTag = theSubtype.getTag(RegisterSpecies.HOSTILEON_HIT);
        if (subTag != null)
        {
            Class[] subs = (Class[])subTag;
            for (Class classy : subs)
            {
                if (classy.isInstance(attacker))
                {
                    return 1.0F;
                }
            }
        }
        if (specTag != null)
        {
            Class[] specs = (Class[])specTag;
            for (Class classy : specs)
            {
                if (classy.isInstance(attacker))
                {
                    return 1.0F;
                }
            }
        }
        return (getSnarl() / 32000F) - 0.5F;
    }

    @Override
    public int aggroHitTimer(IGeneticMob geneticMob, EntityLiving attacker) {
        return (int)Math.floor(getSnarl() * 0.2);
    }

    @Override
    public boolean shouldAutoAggro(IGeneticMob geneticMob, EntityLiving otherEntity) {
        if (getSnarl() < 1000)
        {
            return false;
        } else if (getSnarl() > 30000)
        {
            return true;
        }
        Object specTag = theSpecies.getTag(RegisterSpecies.HOSTILEON_SIGHT);
        Object subTag = theSubtype.getTag(RegisterSpecies.HOSTILEON_SIGHT);
        if (subTag != null)
        {
            Class[] subs = (Class[])subTag;
            for (Class classy : subs)
            {
                if (classy.isInstance(otherEntity))
                {
                    return true;
                }
            }
        }
        if (specTag != null)
        {
            Class[] specs = (Class[])specTag;
            for (Class classy : specs)
            {
                if (classy.isInstance(otherEntity))
                {
                    return true;
                }
            }
        }
        return this.rand.nextInt(getSnarl()) > 16000;
    }

    @Override
    public float shouldEatOffTheGround(IGeneticMob geneticMob, EntityItem itemEntity) {
        ItemStack IS = itemEntity.getEntityItem();
        if (isBreedingItem(IS))
        {
            if (this.getGrowingAge() == 0)
            {
                return 10F - this.getDistanceToEntity(itemEntity);
            } else {
                return 1F - this.getDistanceToEntity(itemEntity);
            }
        }
        return 0;
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float damage) {
        if (ds.getSourceOfDamage() instanceof EntityLiving)
        {
            EntityLiving source = (EntityLiving)ds.getSourceOfDamage();
            if (this.shouldAggroOnHit(this, source) >= this.rand.nextFloat())
            {
                this.setAttackTarget(source);
                this.angryTime = this.aggroHitTimer(this, source);
            }
        }
        return super.attackEntityFrom(ds, damage);
    }

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        int i = Math.round(getStrength() * 0.001F);
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    public void updateHealthAndSpeed()
    {
        //System.out.println("Our data is: " + data[0] + ", " + data[1] + ", " + data[2]);
        double health = (getSize() + (getStrength() * 0.5)) * 0.005;
        double speed = (getStrength() - (getSize() * 0.5) + (getStamina() * 0.5)) * 0.0001;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(Math.max(0.1D, Math.min(speed, 0.5D)));
        System.out.println("With a size of " + getSize() + ", strength of " + getStrength() + ", and stamina of " + getStamina() + ", Health is now " + health + " and we adjusted speed from " + speed + " to " + this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue());
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }
}
