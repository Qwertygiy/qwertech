package com.kbi.qwertech.entities.genetic;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.entities.GMIs;
import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.entities.EntityHelperFunctions;
import com.kbi.qwertech.entities.ai.EntityAIMoveTowardsSimpleTarget;
import com.kbi.qwertech.entities.ai.EntityAINesting;
import com.kbi.qwertech.loaders.RegisterSpecies;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.kbi.qwertech.loaders.RegisterSpecies.NAME_ENGLISH;

public class EntityPhasianidae extends EntityChicken implements IGeneticMob, GMIs.IHitAggro, GMIs.IAutoAggro, GMIs.IEatStuffOnTheGround {

    private short[] data = new short[8];
    private short species = -1;
    private short subtype = -1;
    private int primaryColor = 0;
    private int secondaryColor = 0;
    private boolean isFertilized = false;
    private IGeneticMob lastMate = null;
    private NBTTagCompound lastMateTag = null;
    private Species theSpecies = MobSpeciesRegistry.getSpecies(this.getClass(), (short)0);
    private Subtype theSubtype = theSpecies.getSubtype((short)0);
    public int angryTime = 0;

    @Override
    public String getCommandSenderName() {
        return hasCustomNameTag() ? getCustomNameTag() : (String)this.getSpecies().getTag(NAME_ENGLISH) + " (" + (String)this.getSubtype().getTag(NAME_ENGLISH) + ")";
    }

    @Override
    public boolean interact(EntityPlayer playa) {
        ItemStack stacky = playa.getHeldItem();
        if (ST.invalid(stacky)) return super.interact(playa);
        if (stacky.getItem() instanceof MultiItem)
        {
            ArrayList<IBehavior<MultiItem>> meep = ((MultiItem)stacky.getItem()).mItemBehaviors.get((short)stacky.getItemDamage());
            if (meep == null || meep.size() < 1) return super.interact(playa);
            for (IBehavior beep : meep)
            {
                if (beep instanceof Behavior_Tool)
                {
                    if (((Behavior_Tool)beep).mToolName.equals("magnifyingglass"))
                    {
                        List<String> chats = new ArrayList<String>();
                        chats.add(this.theSubtype.getTag(NAME_ENGLISH) + "(" + this.theSpecies.getTag(RegisterSpecies.NAME_LATIN) + ")");
                        int randomStat = this.rand.nextInt(6);
                        switch (randomStat)
                        {
                            case 0:
                                if (this.getSize() < 700) {
                                    chats.add("It appears to be extremely small.");
                                } else if (this.getSize() < 900) {
                                    chats.add("It appears to be quite small.");
                                } else if (this.getSize() < 1100) {
                                    chats.add("It appears averagely-sized.");
                                } else if (this.getSize() < 1300) {
                                    chats.add("It appears to be on the plump side.");
                                } else if (this.getSize() < 1500) {
                                    chats.add("It appears to be rather large.");
                                } else if (this.getSize() < 1700) {
                                    chats.add("It appears to be a goodly large size.");
                                } else if (this.getSize() < 2000)
                                {
                                    chats.add("It appears to be a very big bird.");
                                } else {
                                    chats.add("It is absolutely humungous.");
                                }
                                break;
                            case 1:
                                if (this.getSnarl() < 1000)
                                {
                                    chats.add("It seems extremely docile.");
                                } else if (this.getSnarl() < 2000)
                                {
                                    chats.add("It seems docile.");
                                } else if (this.getSnarl() < 4000)
                                {
                                    chats.add("It seems to be quite tolerant of you getting in its face.");
                                } else if (this.getSnarl() < 8000)
                                {
                                    chats.add("It seems tolerant, but might not take kindly to being poked.");
                                } else if (this.getSnarl() < 12000)
                                {
                                    chats.add("It doesn't seem comfortable with being touched.");
                                } else if (this.getSnarl() < 16000)
                                {
                                    chats.add("It seems rather defensive.");
                                } else if (this.getSnarl() < 20000)
                                {
                                    chats.add("It seems to be on the aggressive side.");
                                } else if (this.getSnarl() < 25000)
                                {
                                    chats.add("It seems to be quite aggressive.");
                                } else if (this.getSnarl() < 30000)
                                {
                                    chats.add("It seems to be rather hostile.");
                                } else {
                                    chats.add("It seems extremely hostile.");
                                }
                                if (shouldAutoAggro(this, playa))
                                {
                                    chats.add("It doesn't seem to like you.");
                                } else {
                                    float chances = shouldAggroOnHit(this, playa);
                                    if (chances > 0.8)
                                    {
                                        chats.add("You probably shouldn't poke it.");
                                    } else if (chances > 0.5)
                                    {
                                        chats.add("It'll probably get mad if you poke it.");
                                    } else if (chances > 0.1)
                                    {
                                        chats.add("It might get upset if you poke it.");
                                    }
                                }
                                break;
                            case 2:
                                if (this.getMaturity() < 1000)
                                {
                                    chats.add("It's gone a very long time without doing much growing up.");
                                } else if (this.getMaturity() < 2500)
                                {
                                    chats.add("It seems to take quite a while to grow up. Much like you.");
                                } else if (this.getMaturity() < 5000)
                                {
                                    chats.add("It takes a while to grow, just like ideas in your head.");
                                } else if (this.getMaturity() < 10000)
                                {
                                    chats.add("It grows up at an average rate.");
                                } else if (this.getMaturity() < 15000)
                                {
                                    chats.add("It seems to grow rather quickly.");
                                } else if (this.getMaturity() < 20000)
                                {
                                    chats.add("It grows pretty quickly, just like your lawn.");
                                } else if (this.getMaturity() < 25000)
                                {
                                    chats.add("It grows up very fast. Just yesterday it still seemed like a little egg!");
                                } else {
                                    chats.add("What maturity! It seems to grow up faster than an orphan on the streets...");
                                }
                                break;
                            case 3:
                                if (this.getFertility() < 1000)
                                {
                                    chats.add("It seems to go forever without laying. Like you.");
                                } else if (this.getFertility() < 2000)
                                {
                                    chats.add("It seems to take an extremely long time for it to lay eggs.");
                                } else if (this.getFertility() < 5000)
                                {
                                    chats.add("It takes quite a while for it to lay an egg.");
                                } else if (this.getFertility() < 8000)
                                {
                                    chats.add("It seems to take quite a bit of time to get comfy between eggs.");
                                } else if (this.getFertility() < 12000)
                                {
                                    chats.add("It's a bit on the slow side for laying eggs.");
                                } else if (this.getFertility() < 16000)
                                {
                                    chats.add("Not the fastest of egg-layers, it would seem.");
                                } else if (this.getFertility() < 20000)
                                {
                                    chats.add("Looks like it lays eggs fairly often.");
                                } else if (this.getFertility() < 25000)
                                {
                                    chats.add("Seems like it tries to have kids even more often than your mom.");
                                } else if (this.getFertility() < 30000)
                                {
                                    chats.add("You're not quite sure how it manages to lay eggs so quickly.");
                                } else {
                                    chats.add("Bird or egg-making robot in disguise? Who can really be sure?");
                                }
                                break;
                            default:
                                chats.add("It is some kind of chickenish bird.");
                        }
                        UT.Entities.sendchat(playa, chats, false);
                    }
                }
            }
        }
        return super.interact(playa);
    }

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
        this.tasks.addTask(3, new EntityAIMoveTowardsSimpleTarget(this, 1.0D, 16F));
        this.tasks.addTask(1, new EntityAINesting(this, QwerTech.machines.getItem(1770)));

        if ((species == -1 || subtype == -1) && !p_i1682_1_.isRemote) {
            species = 0;
            subtype = 0;
            BiomeGenBase theBiome = p_i1682_1_.getBiomeGenForCoords((int)Math.floor(this.posX), (int)Math.floor(this.posZ));
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
            setSpeciesID(species);
            setSubtypeID(subtype);
            assignRandomStats(p_i1682_1_.rand, theSpecies, theSubtype);
            EntityHelperFunctions.assignAI(this, this);
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
            } else if (entity instanceof EntityLivingBase){
                if (shouldAutoAggro(this, (EntityLivingBase)entity))
                {
                    //System.out.println("We should attack " + entity + " because we're " + getSnarl());
                    this.setAttackTarget((EntityLivingBase)entity);
                    this.angryTime = 10000;
                    return;
                }
            }
        }
        if (theFood != null && this.getGrowingAge() < 1000 && !this.isInLove())
        {
            //System.out.println("Going after food");
            this.setTarget(theFood);
            if (this.getDistanceToEntity(theFood) < 1F)
            {
                int age = this.getGrowingAge();
                if (age == 0)
                {
                    this.func_146082_f(null);
                } else if (age < 0) {
                    this.setGrowingAge(age + Math.min(1000, age * -1/10));
                }
                if (theFood.getEntityItem().stackSize > 1)
                {
                    ItemStack stacky = theFood.getEntityItem();
                    stacky.stackSize = stacky.stackSize - 1;
                    theFood.setEntityItemStack(stacky);
                } else {
                    theFood.setDead();
                }
                this.setTarget(null);
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
        this(world);
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
        primaryColor = COLOR.getRandom((Integer)theSubtype.getTag(RegisterSpecies.COLOR_PRIMARY_MIN), (Integer)theSubtype.getTag(RegisterSpecies.COLOR_PRIMARY_MAX));
        secondaryColor = COLOR.getRandom((Integer)theSubtype.getTag(RegisterSpecies.COLOR_SECONDARY_MIN), (Integer)theSubtype.getTag(RegisterSpecies.COLOR_SECONDARY_MAX));
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
        ArrayList<Object[]> sounds = null;
        if (theSubtype.hasTag(RegisterSpecies.SOUNDS_IDLE))
        {
            sounds = (ArrayList<Object[]>)theSubtype.getTag(RegisterSpecies.SOUNDS_IDLE);
        } else if (theSpecies.hasTag(RegisterSpecies.SOUNDS_IDLE))
        {
            sounds = (ArrayList<Object[]>)theSpecies.getTag(RegisterSpecies.SOUNDS_IDLE);
        }
        if (sounds != null && !sounds.isEmpty())
        {
            return ((String)sounds.get(rand.nextInt(sounds.size()))[0]);
        }
        return "";
    }

    @Override
    protected String getHurtSound() {
        ArrayList<Object[]> sounds = null;
        if (theSubtype.hasTag(RegisterSpecies.SOUNDS_HURT))
        {
            sounds = (ArrayList<Object[]>)theSubtype.getTag(RegisterSpecies.SOUNDS_HURT);
        } else if (theSpecies.hasTag(RegisterSpecies.SOUNDS_HURT))
        {
            sounds = (ArrayList<Object[]>)theSpecies.getTag(RegisterSpecies.SOUNDS_HURT);
        }
        if (sounds != null && !sounds.isEmpty())
        {
            return ((String)sounds.get(rand.nextInt(sounds.size()))[0]);
        }
        return "";
    }

    @Override
    protected String getDeathSound() {
        ArrayList<Object[]> sounds = null;
        if (theSubtype.hasTag(RegisterSpecies.SOUNDS_DEAD))
        {
            sounds = (ArrayList<Object[]>)theSubtype.getTag(RegisterSpecies.SOUNDS_DEAD);
        } else if (theSpecies.hasTag(RegisterSpecies.SOUNDS_DEAD))
        {
            sounds = (ArrayList<Object[]>)theSpecies.getTag(RegisterSpecies.SOUNDS_DEAD);
        } else if (theSubtype.hasTag(RegisterSpecies.SOUNDS_HURT))
        {
            sounds = (ArrayList<Object[]>)theSubtype.getTag(RegisterSpecies.SOUNDS_HURT);
        } else if (theSpecies.hasTag(RegisterSpecies.SOUNDS_HURT))
        {
            sounds = (ArrayList<Object[]>)theSpecies.getTag(RegisterSpecies.SOUNDS_HURT);
        }
        if (sounds != null && !sounds.isEmpty())
        {
            return ((String)sounds.get(rand.nextInt(sounds.size()))[0]);
        }
        return "";
    }

    @Override
    protected Item getDropItem() {
        if (theSpecies.hasTag(RegisterSpecies.DROP_PRIMARY))
        {
            return ((ArrayList<ItemStack>)theSpecies.getTag(RegisterSpecies.DROP_PRIMARY)).get(0).getItem();
        }
        return null;
    }

    @Override
    protected void dropFewItems(boolean isPlayer, int looting) {
        ArrayList<ItemStack> rares = (ArrayList<ItemStack>)theSpecies.getTag(RegisterSpecies.DROP_RARE);
        if (rares != null && !rares.isEmpty()) {
            ItemStack rare = rares.get(rand.nextInt(rares.size())).copy();
            if (isPlayer && this.rand.nextInt(100) < 3 + looting) {
                NBTTagCompound nbt = UT.NBT.getOrCreate(rare);
                if (nbt.hasKey("itemColor"))
                {
                    nbt.setInteger("itemColor", primaryColor);
                    UT.NBT.set(rare, nbt);
                }

                ST.drop(this, rare);
            }
        }

        short minSize = theSubtype.getMinSize();
        short maxSize = theSubtype.getMaxSize();
        short range = (short)(maxSize - minSize);
        ArrayList<ItemStack> secondaries = (ArrayList<ItemStack>)theSpecies.getTag(RegisterSpecies.DROP_SECONDARY);
        if (secondaries != null && !secondaries.isEmpty()) {
            ItemStack drop = secondaries.get(rand.nextInt(secondaries.size())).copy();
            drop.stackSize = getSize() < minSize + (range * 0.2) ? 1 : getSize() > maxSize - (range * 0.2) ? 3 : 2;
            drop.stackSize = drop.stackSize * (1 + this.rand.nextInt(looting + 1));

            NBTTagCompound nbt = UT.NBT.getOrCreate(drop);
            if (nbt.hasKey("itemColor"))
            {
                nbt.setInteger("itemColor", primaryColor);
                UT.NBT.set(drop, nbt);
            }

            ST.drop(this, drop);
        }
        ArrayList<ItemStack> primaries = (ArrayList<ItemStack>)theSpecies.getTag(RegisterSpecies.DROP_PRIMARY);
        if (primaries != null && !primaries.isEmpty()) {
            ItemStack meat = primaries.get(rand.nextInt(primaries.size())).copy();
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
        this.setMate((IGeneticMob)other);
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
        this.height = (float)(size * 0.0006);
        this.width = (float)(size * 0.0004);
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
    public void setMate(IGeneticMob mate) {
        lastMate = mate;
        lastMateTag = new NBTTagCompound();
        ((Entity)mate).writeToNBT(lastMateTag);
    }

    @Override
    public IGeneticMob getMate() {
        return lastMate;
    }

    @Override
    public NBTTagCompound getMateNBT() {
        return lastMateTag;
    }

    @Override
    public float shouldAggroOnHit(IGeneticMob geneticMob, EntityLivingBase attacker) {
        if (getSnarl() < 1000)
        {
            return 0.0F;
        } else if (getSnarl() > 25000)
        {
            return 1.0F;
        }
        Object specTag = theSpecies.getTag(RegisterSpecies.ATTACK_ON_HIT);
        Object subTag = theSubtype.getTag(RegisterSpecies.ATTACK_ON_HIT);
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
    public int aggroHitTimer(IGeneticMob geneticMob, EntityLivingBase attacker) {
        return (int)Math.floor(getSnarl() * 0.2);
    }

    @Override
    public boolean shouldAutoAggro(IGeneticMob geneticMob, EntityLivingBase otherEntity) {
        if (getSnarl() < 1000)
        {
            return false;
        } else if (getSnarl() > 30000)
        {
            return true;
        }
        Object specTag = theSpecies.getTag(RegisterSpecies.ATTACK_ON_SIGHT);
        Object subTag = theSubtype.getTag(RegisterSpecies.ATTACK_ON_SIGHT);
        if (subTag != null)
        {
            Class[] subs = (Class[])subTag;
            for (Class classy : subs)
            {
                if (classy.isInstance(otherEntity))
                {
                    return this.rand.nextInt(getSnarl()) > 16000;
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
                    return this.rand.nextInt(getSnarl()) > 16000;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (theSpecies.hasTag(RegisterSpecies.BREEDING_FOOD))
        {
            Object[] breedingItems = (Object[])theSpecies.getTag(RegisterSpecies.BREEDING_FOOD);
            for (Object obbie : breedingItems)
            {
                if (obbie instanceof ItemStack)
                {
                    if (ST.equal(stack, (ItemStack)obbie))
                    {
                        return true;
                    }
                } else if(obbie instanceof Item)
                {
                    if (stack.getItem().equals(obbie))
                    {
                        return true;
                    }
                } else if (obbie instanceof String)
                {
                    if (OM.is(obbie, stack))
                    {
                        return true;
                    }
                }
            }
        }
        if (theSubtype.hasTag(RegisterSpecies.BREEDING_FOOD))
        {
            Object[] breedingItems = (Object[])theSubtype.getTag(RegisterSpecies.BREEDING_FOOD);
            for (Object obbie : breedingItems)
            {
                if (obbie instanceof ItemStack)
                {
                    if (ST.equal(stack, (ItemStack)obbie))
                    {
                        return true;
                    }
                } else if(obbie instanceof Item)
                {
                    if (stack.getItem().equals(obbie))
                    {
                        return true;
                    }
                } else if (obbie instanceof String)
                {
                    if (OM.is(obbie, stack))
                    {
                        return true;
                    }
                }
            }
        }
        return !theSpecies.hasTag(RegisterSpecies.BREEDING_FOOD) && !theSubtype.hasTag(RegisterSpecies.BREEDING_FOOD) && (OM.is("listAllseed", stack) || OP.seed.contains(stack));
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
        if (ds.getSourceOfDamage() instanceof EntityLivingBase)
        {
            EntityLivingBase source = (EntityLivingBase)ds.getSourceOfDamage();
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
        double speed = 0.19 + ((getStrength() + getStamina() - getSize()) * 0.00001);
        float currentHealth = this.getHealth() / this.getMaxHealth();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
        this.setHealth((float)health * currentHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(Math.max(0.05D, Math.min(speed, 0.5D)));
        this.setAIMoveSpeed((float)Math.max(0.05D, Math.min(speed, 0.5D)));
        //System.out.println("With a size of " + getSize() + ", strength of " + getStrength() + ", and stamina of " + getStamina() + ", Health is now " + health + " and we adjusted speed from " + speed + " to " + this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue());
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
    }

    @Override
    public float getEyeHeight() {
        return super.getEyeHeight();
    }

    @Override
    public boolean isFertilized() {
        return isFertilized;
    }

    @Override
    public void setFertilized(boolean fertile) {
        isFertilized = fertile;
    }

    @Override
    public Species getSpecies() {
        return theSpecies;
    }

    @Override
    public Subtype getSubtype() {
        return theSubtype;
    }
}
