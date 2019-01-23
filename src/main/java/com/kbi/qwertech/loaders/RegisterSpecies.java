package com.kbi.qwertech.loaders;

import com.kbi.qwertech.QwerTech;
import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.entities.Taggable;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.entities.genetic.EntityPhasianidae;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.List;

public class RegisterSpecies {

    /*
        starts with A: attack
        starts with C: color
        starts with D: drop
        starts with L: list
        starts with M: model
        starts with N: name
        starts with S: sound
        starts with T: texture
        starts with V: avoids
     */
    public static final String
            SOUNDS_IDLE = "SI",
            SOUNDS_HURT = "SH",
            SOUNDS_ANGRY = "SA",
            SOUNDS_DEAD = "SD",
            SOUNDS_LOVE = "SL",
            MODEL_PRIMARY = "MP",
            MODEL_CHILD = "MC",
            DROP_PRIMARY = "DP",
            DROP_SECONDARY = "DS",
            DROP_RARE = "DR",
            ITEM_EGG = "DE",
            NAME_LATIN = "NL",
            NAME_ENGLISH = "NE",
            NAME_TRANSLATE = "NT",
            COLOR_PRIMARY_MIN = "C1-",
            COLOR_PRIMARY_MAX = "C1+",
            COLOR_SECONDARY_MIN = "C2-",
            COLOR_SECONDARY_MAX = "C2+",
            COLOR_TERTIARY_MIN = "C3-",
            COLOR_TERTIARY_MAX = "C3+",
            COLOR_EGG = "CE",
            TEXTURE_PRIMARY = "T1",
            TEXTURE_SECONDARY = "T2",
            TEXTURE_TERTIARY = "T3",
            ATTACK_ON_SIGHT = "AOS",
            ATTACK_ON_HIT = "AOH",
            ATTACK_ON_SPECIESHIT = "AOBH",
            ATTACK_ON_PLAYERHIT = "AOPH",
            ATTACK_ON_SPECIESORPLAYERHIT = "AOPBH",
            BREEDING_FOOD = "BF",
            AI_LIST = "LAI",
            LAYS_EGGS = "LEGG",
            AVOIDS_ENTITY = "VE",
            AVOIDS_BLOCK = "VB",
            IGNORES_GROUND_FOOD = "VF",
            NESTING_GROUND = "HNG",
            NEST_ITEM = "DN";

    public static Taggable setLatin(Taggable spec, String name)
    {
        return spec.addTag(NAME_LATIN, name);
    }
    public static Taggable setEnglish(Taggable spec, String name)
    {
        return spec.addTag(NAME_ENGLISH, name);
    }
    public static Taggable setTranslate(Taggable spec, String namekey)
    {
        return spec.addTag(NAME_TRANSLATE, namekey);
    }
    public static Taggable setNames(Taggable spec, String latin, String english, String key)
    {
        return spec.addTag(NAME_LATIN, latin).addTag(NAME_ENGLISH, english).addTag(NAME_TRANSLATE, key);
    }
    public static Taggable setTexturePrimary(Taggable spec, String texture)
    {
        return spec.addTag(TEXTURE_PRIMARY, texture);
    }
    public static Taggable setTextureSecondary(Taggable spec, String texture)
    {
        return spec.addTag(TEXTURE_SECONDARY, texture);
    }
    public static Taggable setTextureTertiary(Taggable spec, String texture)
    {
        return spec.addTag(TEXTURE_TERTIARY, texture);
    }
    public static Taggable setTextures(Taggable spec, String texturePrimary, String textureSecondary, String textureTertiary)
    {
        return spec.addTag(TEXTURE_PRIMARY, texturePrimary).addTag(TEXTURE_SECONDARY, textureSecondary).addTag(TEXTURE_TERTIARY, textureTertiary);
    }
    public static Taggable setTextureDir(Taggable spec, String texDir)
    {
        return spec.addTag(TEXTURE_PRIMARY, texDir + "_primary.png").addTag(TEXTURE_SECONDARY, texDir + "_secondary.png").addTag(TEXTURE_TERTIARY, texDir + "_overlay.png");
    }
    public static Taggable addLivingSound(Taggable spec, String sound, float pitch, float volume)
    {
        if (spec.hasTag(SOUNDS_IDLE))
        {
            List<Object[]> audio = (List<Object[]>)spec.getTag(SOUNDS_IDLE);
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_IDLE, audio); //juuuust to be sure.
        } else {
            List<Object> audio = new ArrayList<Object>();
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_IDLE, audio); //juuuust to be sure.
        }
        return spec;
    }
    public static Taggable addHurtSound(Taggable spec, String sound, float pitch, float volume)
    {
        if (spec.hasTag(SOUNDS_HURT))
        {
            List<Object[]> audio = (List<Object[]>)spec.getTag(SOUNDS_HURT);
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_HURT, audio); //juuuust to be sure.
        } else {
            List<Object> audio = new ArrayList<Object>();
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_HURT, audio); //juuuust to be sure.
        }
        return spec;
    }
    public static Taggable addAngrySound(Taggable spec, String sound, float pitch, float volume)
    {
        if (spec.hasTag(SOUNDS_ANGRY))
        {
            List<Object[]> audio = (List<Object[]>)spec.getTag(SOUNDS_ANGRY);
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_ANGRY, audio); //juuuust to be sure.
        } else {
            List<Object> audio = new ArrayList<Object>();
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_ANGRY, audio); //juuuust to be sure.
        }
        return spec;
    }
    public static Taggable addDeathSound(Taggable spec, String sound, float pitch, float volume)
    {
        if (spec.hasTag(SOUNDS_DEAD))
        {
            List<Object[]> audio = (List<Object[]>)spec.getTag(SOUNDS_DEAD);
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_DEAD, audio); //juuuust to be sure.
        } else {
            List<Object> audio = new ArrayList<Object>();
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_DEAD, audio); //juuuust to be sure.
        }
        return spec;
    }
    public static Taggable addLovingSound(Taggable spec, String sound, float pitch, float volume)
    {
        if (spec.hasTag(SOUNDS_LOVE))
        {
            List<Object[]> audio = (List<Object[]>)spec.getTag(SOUNDS_LOVE);
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_LOVE, audio); //juuuust to be sure.
        } else {
            List<Object> audio = new ArrayList<Object>();
            audio.add(new Object[]{sound, pitch, volume});
            spec.addTag(SOUNDS_LOVE, audio); //juuuust to be sure.
        }
        return spec;
    }

    public static Species addEgg(Species spec, ItemStack eggItem, int eggColor)
    {
        return addEgg(spec, eggItem, eggColor, false);
    }

    public static Species addEgg(Species spec, ItemStack eggItem, int eggColor, boolean laysEgg)
    {
        return spec.addTag(ITEM_EGG, eggItem).addTag(COLOR_EGG, eggColor).addTag(LAYS_EGGS, laysEgg);
    }

    public static Taggable setNesting(Taggable spec)
    {
        return setNesting(spec, true);
    }

    public static Taggable setNesting(Taggable spec, boolean usesNest)
    {
        return setNesting(spec, usesNest, QwerTech.machines.getItem(1770));
    }

    public static Taggable setNesting(Taggable spec, boolean usesNest, ItemStack nestItem)
    {
        return spec.addTag(NESTING_GROUND, usesNest).addTag(NEST_ITEM, nestItem);
    }

    public static Taggable setPrimaryColor(Taggable spec, String color)
    {
        if (COLOR.colorDictionary.containsKey(color))
        {
            int colorValue = COLOR.colorDictionary.get(color);
            int min = Math.max((colorValue >> 16 & 255) - 20, 0) * (int)Math.pow(2, 16);
            min = min + Math.max((colorValue >> 8 & 255) - 20, 0) * (int)Math.pow(2, 8);
            min = min + Math.max((colorValue & 255) - 20, 0);

            int max = Math.min((colorValue >> 16 & 255) + 20, 255) * (int)Math.pow(2, 16);
            max = max + Math.min((colorValue >> 8 & 255) + 20, 255) * (int)Math.pow(2, 8);
            max = max + Math.min((colorValue & 255) + 20, 255);
            setPrimaryColors(spec, min, max);
        } else {
            System.out.println("Could not find color " + color);
        }
        return spec;
    }

    public static Taggable setPrimaryColors(Taggable spec, int color1, int color2)
    {
        return spec.addTag(COLOR_PRIMARY_MIN, color1).addTag(COLOR_PRIMARY_MAX, color2);
    }

    public static Taggable setPrimaryColors(Taggable spec, String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setPrimaryColors(spec, COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        } else {
            System.out.println("Could not find both " + color1 + " and " + color2);
        }
        return spec;
    }

    public static Taggable setSecondaryColor(Taggable spec, String color)
    {
        if (COLOR.colorDictionary.containsKey(color))
        {
            int colorValue = COLOR.colorDictionary.get(color);
            int min = Math.max((colorValue >> 16 & 255) - 20, 0) * (int)Math.pow(2, 16);
            min = min + Math.max((colorValue >> 8 & 255) - 20, 0) * (int)Math.pow(2, 8);
            min = min + Math.max((colorValue & 255) - 20, 0);

            int max = Math.min((colorValue >> 16 & 255) + 20, 255) * (int)Math.pow(2, 16);
            max = max + Math.min((colorValue >> 8 & 255) + 20, 255) * (int)Math.pow(2, 8);
            max = max + Math.min((colorValue & 255) + 20, 255);
            setSecondaryColors(spec, min, max);
        } else {
            System.out.println("Could not find color " + color);
        }
        return spec;
    }

    public static Taggable setSecondaryColors(Taggable spec, int color1, int color2)
    {
        return spec.addTag(COLOR_SECONDARY_MIN, color1).addTag(COLOR_SECONDARY_MAX, color2);
    }

    public static Taggable setSecondaryColors(Taggable spec, String color1, String color2)
    {
        if (COLOR.colorDictionary.containsKey(color1) && COLOR.colorDictionary.containsKey(color2))
        {
            setSecondaryColors(spec, COLOR.colorDictionary.get(color1), COLOR.colorDictionary.get(color2));
        } else {
            System.out.println("Could not find both " + color1 + " and " + color2 + " in the color database");
        }
        return spec;
    }

    @SideOnly(Side.CLIENT)
    public static Taggable setModel(Taggable spec, net.minecraft.client.model.ModelBase modelType)
    {
        return spec.addTag(MODEL_PRIMARY, modelType);
    }

    @SideOnly(Side.CLIENT)
    public static Taggable setChildModel(Taggable spec, net.minecraft.client.model.ModelBase modelType)
    {
        return spec.addTag(MODEL_CHILD, modelType);
    }

    public static Taggable addPrimaryDrop(Taggable spec, ItemStack drop)
    {
        List<ItemStack> stacks;
        if (spec.hasTag(DROP_PRIMARY))
        {
            stacks = (List<ItemStack>)spec.getTag(DROP_PRIMARY);
        } else {
            stacks = new ArrayList<ItemStack>();
        }
        stacks.add(drop);
        return spec.addTag(DROP_PRIMARY, stacks); //just to be safe
    }

    public static Taggable addSecondaryDrop(Taggable spec, ItemStack drop)
    {
        List<ItemStack> stacks;
        if (spec.hasTag(DROP_SECONDARY))
        {
            stacks = (List<ItemStack>)spec.getTag(DROP_SECONDARY);
        } else {
            stacks = new ArrayList<ItemStack>();
        }
        stacks.add(drop);
        return spec.addTag(DROP_SECONDARY, stacks); //just to be safe
    }

    public static Taggable addRareDrop(Taggable spec, ItemStack drop)
    {
        List<ItemStack> stacks;
        if (spec.hasTag(DROP_RARE))
        {
            stacks = (List<ItemStack>)spec.getTag(DROP_RARE);
        } else {
            stacks = new ArrayList<ItemStack>();
        }
        stacks.add(drop);
        return spec.addTag(DROP_RARE, stacks); //just to be safe
    }

    public static Taggable addAI(Taggable spec, Class<? extends EntityAIBase> type, Object... parameters)
    {
        ArrayList<Object[]> AIlist;
        if (!spec.hasTag(AI_LIST))
        {
            AIlist = new ArrayList<Object[]>();
        } else {
            AIlist = (ArrayList<Object[]>)spec.getTag(AI_LIST);
        }
        AIlist.add(new Object[]{type, parameters});
        return spec.addTag(AI_LIST, AIlist);
    }

    public static void begin()
    {
        ItemStack feather = QTI.turkeyFeather.get(1);
        NBTTagCompound tag = UT.NBT.getOrCreate(feather);
        tag.setInteger("itemColor", 0);
        UT.NBT.set(feather, tag);

        Species chicken = new Species(EntityPhasianidae.class);
        setNesting(chicken);
        setPrimaryColors(chicken, "Black", "White"); setSecondaryColors(chicken, "Black", "White"); setNames(chicken, "Gallus gallus domesticus", "Chicken", "qwertech.phasian.chicken"); addEgg(chicken, QTI.qwerFood.getWithDamage(1, 32), COLOR.make(222, 205, 200)); addPrimaryDrop(chicken, QTI.chickenWholeRaw.get(1)); addSecondaryDrop(chicken, feather);
        chicken.setMinFertility(8000)     .setMaxFertility(Short.MAX_VALUE)   .setMinMaturity(16000)      .setMaxMaturity(Short.MAX_VALUE)    .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(400)   .setMaxSize(3000)   .setMinSmart(1) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(100) .setMaxStamina(16000)   .setMinStrength(100)    .setMaxStrength(8000);
        MobSpeciesRegistry.addSpecies(EntityPhasianidae.class, 0, chicken);

        Subtype mojang = new Subtype(chicken);
        setPrimaryColors(mojang, "White","Goldenrod"); setSecondaryColors(mojang, "Dark Red","Red"); setEnglish(mojang, "Mojang Chicken"); setTranslate(mojang, "qwertech.phasian.chicken.mojang"); setTextureDir(mojang, "qwertech:textures/entity/genetic/phasianidae/chicken_default/mojang"); addLivingSound(mojang, "mob.chicken.say", 1, 0.5F); addHurtSound(mojang, "mob.chicken.hurt", 1, 0.5F);
        mojang.setFertility(8000, 17000, Short.MAX_VALUE)        .setMaturity(16000, 17000, Short.MAX_VALUE)    .setMutable(10, 100, 1000)    .setSize(400, 1000, 2000)   .setSmart(1, 1000, 10000) .setSnarl(0, 10, 20000)      .setStamina(100, 6000, 16000)   .setStrength(100, 1000, 8000).addBiome(BiomeDictionary.Type.PLAINS).addBiome(BiomeDictionary.Type.SAVANNA);
        chicken.setSubtype(0, mojang);

        Subtype rir = new Subtype(chicken);
        setPrimaryColors(rir, COLOR.get("Peru"),        COLOR.make(50, 15, 5)); setSecondaryColors(rir, "Crimson","Tomato"); setEnglish(rir,"Rhode Island Red"); setTranslate(rir, "qwertech.phasian.chicken.rir"); setTextureDir(rir,"qwertech:textures/entity/genetic/phasianidae/chicken_crested/rir"); addLivingSound(rir, "mob.chicken.say", 1, 0.5F); addHurtSound(rir, "mob.chicken.hurt", 1, 0.5F);
        rir.setFertility(16000, 20000, 32000)             .setMaturity(16000, 17000, 32000)         .setMutable(10, 100, 1000)    .setSize(800, 1100, 2000)   .setSmart(100, 1000, 8000).setSnarl(100, 500, 10000)   .setStamina(100, 6000, 16000)   .setStrength(200, 1500, 8000);
        rir.addTag(COLOR_EGG, COLOR.make(205, 160, 140));
        chicken.setSubtype(1, rir);

        Subtype riw = new Subtype(chicken);
        setPrimaryColors(riw, COLOR.get("White"),       COLOR.make(240, 240, 240)); setSecondaryColors(riw, "Crimson",        "Tomato"); setEnglish(riw, "Rhode Island White"); setTranslate(riw, "qwertech.phasian.chicken.riw"); setTextureDir(riw, "qwertech:textures/entity/genetic/phasianidae/chicken_crested/rir"); setTexturePrimary(riw, "qwertech:textures/entity/genetic/phasianidae/chicken_crested/riw_primary.png"); addLivingSound(riw, "mob.chicken.say", 1, 0.5F); addHurtSound(riw, "mob.chicken.hurt", 1, 0.5F);
        riw.setFertility(16000, 20000, 32000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(800, 1100, 2000)   .setSmart(100, 1000, 8000).setSnarl(100, 500, 10000)    .setStamina(100, 6000, 16000) .setStrength(200, 1500, 8000);
        riw.addTag(COLOR_EGG, COLOR.make(205, 160, 140));
        chicken.setSubtype(2, riw);

        Subtype andal = new Subtype(chicken);
        setPrimaryColors(andal, COLOR.make(240, 240, 240),       COLOR.make(150, 150, 160)); setSecondaryColors(andal, "Salmon",        "Tomato");       setEnglish(andal, "Andalusian"); setTranslate(andal, "qwertech.phasian.chicken.andal"); setTextureDir(andal, "qwertech:textures/entity/genetic/phasianidae/chicken_crested/andalusian"); addLivingSound(andal, "mob.chicken.say", 1, 0.5F); addHurtSound(andal, "mob.chicken.hurt", 1, 0.5F);
        andal.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 200, 1000)      .setSize(600, 1000, 1500)   .setSmart(100, 1000, 8000).setSnarl(200, 6000, 20000)    .setStamina(500, 10000, 16000) .setStrength(100, 1000, 7000);
        chicken.setSubtype(3, andal);

        Subtype leghorn = new Subtype(chicken);
        setPrimaryColors(leghorn, COLOR.make(240, 240, 240),       COLOR.get("White")); setSecondaryColors(leghorn, "Red",        "Dark Red"); setEnglish(leghorn, "White Leghorn"); setTranslate(leghorn, "qwertech.phasian.chicken.leghornwhite"); setTextureDir(leghorn,"qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh"); addLivingSound(leghorn, "mob.chicken.say", 1, 0.5F); addHurtSound(leghorn, "mob.chicken.hurt", 1, 0.5F);
        leghorn.setFertility(12000, 17000, 32000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        chicken.setSubtype(4, leghorn);

        Subtype darkbrownlh = new Subtype(chicken);
        setPrimaryColors(darkbrownlh, COLOR.get("Peru"),        COLOR.make(50, 15, 5)); setSecondaryColors(darkbrownlh, COLOR.make(10, 10, 10), COLOR.make(10, 30, 40)); setEnglish(darkbrownlh, "Dark Brown Leghorn"); setTranslate(darkbrownlh, "qwertech.phasian.chicken.leghorndbrown"); setTextureDir(darkbrownlh,"qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2"); addLivingSound(darkbrownlh, "mob.chicken.say", 1, 0.5F); addHurtSound(darkbrownlh, "mob.chicken.hurt", 1, 0.5F);
        darkbrownlh.setFertility(12000, 17000, 32000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        chicken.setSubtype(5, darkbrownlh);

        Subtype lightbrownlh = new Subtype(chicken);
        setPrimaryColors(lightbrownlh, COLOR.make(230, 160, 80),        COLOR.make(100, 30, 10)); setSecondaryColors(lightbrownlh, COLOR.make(10, 10, 10), COLOR.make(10, 30, 40)); setEnglish(lightbrownlh, "Light Brown Leghorn"); setTranslate(lightbrownlh, "qwertech.phasian.chicken.leghornlbrown"); setTextureDir(lightbrownlh,"qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2"); addLivingSound(lightbrownlh, "mob.chicken.say", 1, 0.5F); addHurtSound(lightbrownlh, "mob.chicken.hurt", 1, 0.5F);
        lightbrownlh.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        chicken.setSubtype(6, lightbrownlh);

        Subtype blacklh = new Subtype(chicken);
        setPrimaryColors(blacklh, COLOR.make(10, 10, 10), COLOR.make(10, 30, 40)); setSecondaryColors(blacklh, COLOR.make(10, 10, 10), COLOR.make(10, 30, 40));       setEnglish(blacklh, "Black Leghorn");
        blacklh.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        setTextureDir(blacklh, "qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2"); addLivingSound(blacklh, "mob.chicken.say", 1, 0.5F); addHurtSound(blacklh, "mob.chicken.hurt", 1, 0.5F);
        chicken.setSubtype(7, blacklh);

        Subtype bufflh = new Subtype(chicken);
        setPrimaryColors(bufflh, COLOR.make(225, 188, 113),       COLOR.make(219, 93, 5)); setSecondaryColors(bufflh, "Red",        "Dark Red");       setEnglish(bufflh, "Buff Leghorn"); setTranslate(bufflh,"qwertech.phasian.chicken.leghornbuff");
        bufflh.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        setTextureDir(bufflh, "qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh"); addLivingSound(bufflh, "mob.chicken.say", 1, 0.5F); addHurtSound(bufflh, "mob.chicken.hurt", 1, 0.5F);
        chicken.setSubtype(8, bufflh);

        Subtype silverlh = new Subtype(chicken);
        setPrimaryColors(silverlh, COLOR.get("White"),       COLOR.make(240, 240, 240)); setSecondaryColors(silverlh, COLOR.make(10, 10, 10), COLOR.make(10, 30, 40));       setEnglish(silverlh, "Silver Leghorn"); setTranslate(silverlh, "qwertech.phasian.chicken.leghornsilver");
        silverlh.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        setTextureDir(silverlh,"qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2"); addLivingSound(silverlh, "mob.chicken.say", 1, 0.5F); addHurtSound(silverlh, "mob.chicken.hurt", 1, 0.5F);
        chicken.setSubtype(9, silverlh);

        Subtype btredlh = new Subtype(chicken);
        setPrimaryColors(btredlh, COLOR.make(80, 0, 0),       COLOR.make(120, 40, 0)); setSecondaryColors(btredlh, COLOR.make(10, 10, 10), COLOR.make(10, 30, 40));       setEnglish(btredlh, "Black-Tailed Red Leghorn"); setTranslate(btredlh, "qwertech.phasian.chicken.leghornredbt");
        btredlh.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        setTextureDir(btredlh, "qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2");addLivingSound(btredlh, "mob.chicken.say", 1, 0.5F); addHurtSound(btredlh, "mob.chicken.hurt", 1, 0.5F);
        chicken.setSubtype(10, btredlh);

        Subtype redlh = new Subtype(chicken);
        setPrimaryColors(redlh, COLOR.make(80, 0, 0),       COLOR.make(120, 40, 0)); setSecondaryColors(redlh, "Red",        "Dark Red");       setEnglish(redlh,"Red Leghorn"); setTranslate(redlh, "qwertech.phasian.chicken.longhornred");
        redlh.setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500);
        setTextureDir(redlh, "qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh");addLivingSound(redlh, "mob.chicken.say", 1, 0.5F); addHurtSound(redlh, "mob.chicken.hurt", 1, 0.5F);
        chicken.setSubtype(11, redlh);

        Subtype superChick = new Subtype(chicken);
        setSecondaryColors(superChick, COLOR.make(255, 0, 0),    COLOR.make(200, 0, 0)); setPrimaryColors(superChick, COLOR.make(0, 0, 255), COLOR.make(0, 0, 150)); setEnglish(superChick, "Super Chicken"); setTranslate(superChick, "qwertech.phasian.chicken.super");
        superChick.setFertility(31000, 32000, Short.MAX_VALUE).setMaturity(31000, 32000, Short.MAX_VALUE).setMutable(10, 100, 1000).setSize(2800, 2900, 3000).setSmart(9000, 9500, 10000).setSnarl(19000, 19500, 20000).setStamina(15000, 15500, 16000).setStrength(7000, 7500, 8000);
        setTextureDir(superChick, "qwertech:textures/entity/genetic/phasianidae/chicken_caped/super"); addLivingSound(superChick,"qwertech:mob.chicken.super", 1, 0.5F); addHurtSound(superChick, "mob.chicken.hurt", 1, 0.5F);
        superChick.addTag(ATTACK_ON_SIGHT, new Class[]{EntityMob.class}).addTag(ATTACK_ON_SPECIESORPLAYERHIT, new Class[]{Entity.class});
        chicken.setSubtype(12, superChick);

        Species redJunglefowl = new Species(EntityPhasianidae.class);
        setNesting(redJunglefowl);
        setPrimaryColors(redJunglefowl, "Black",       "White"); setSecondaryColors(redJunglefowl, "Black",         "White"); setEnglish(redJunglefowl, "Red Junglefowl");           setLatin(redJunglefowl, "Gallus gallus"); setTranslate(redJunglefowl, "qwertech.phasian.redjungle");
        redJunglefowl.setMinFertility(2000)     .setMaxFertility(20000)   .setMinMaturity(8000)      .setMaxMaturity(20000)    .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(500)   .setMaxSize(2000)   .setMinSmart(100) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(1000) .setMaxStamina(16000)   .setMinStrength(1000)    .setMaxStrength(6000);
        addPrimaryDrop(redJunglefowl, QTI.junglefowlWholeRaw.get(1)); addSecondaryDrop(redJunglefowl, feather); addEgg(redJunglefowl, QTI.junglefowlEgg.get(1), COLOR.make(205, 160, 140));
        MobSpeciesRegistry.addSpecies(EntityPhasianidae.class, 1, redJunglefowl);

        Subtype murghi = new Subtype(redJunglefowl);
        setPrimaryColors(murghi, "Dark Cyan",                  "Sea Green");                            setSecondaryColors(murghi, "Peru",         "Orange");          setEnglish(murghi,"Indian Red Junglefowl"); setTranslate(murghi,"qwertech.phasian.redjungle.indian");
        murghi.setFertility(2000, 8000, 20000)        .setMaturity(8000, 12000, 20000)    .setMutable(10, 100, 1000)    .setSize(500, 1000, 2000)   .setSmart(100, 1000, 10000) .setSnarl(0, 1000, 20000)      .setStamina(1000, 8000, 16000)   .setStrength(1000, 2000, 6000).addBiome(BiomeDictionary.Type.JUNGLE);
        setTextureDir(murghi, "qwertech:textures/entity/genetic/phasianidae/chicken_wild1/murghi"); addLivingSound(murghi, "mob.chicken.say", 1, 0.5F); addHurtSound(murghi, "mob.chicken.hurt", 1, 0.5F);
        redJunglefowl.setSubtype(0, murghi);

        Subtype gallus3 = new Subtype(redJunglefowl);
        setPrimaryColors(gallus3, "Cyan",                  "Dark Slate Gray");                            setSecondaryColors(gallus3, "Orange",         "Goldenrod");          setEnglish(gallus3,"Indochinese Red Junglefowl"); setTranslate(gallus3,"qwertech.phasian.redjungle.indochina");
        gallus3.setFertility(2000, 8000, 20000)        .setMaturity(8000, 12000, 20000)    .setMutable(10, 100, 1000)    .setSize(500, 1000, 2000)   .setSmart(100, 1000, 10000) .setSnarl(0, 1000, 20000)      .setStamina(1000, 8000, 16000)   .setStrength(1000, 2000, 6000).addBiome(BiomeDictionary.Type.JUNGLE);
        setTextureDir(gallus3, "qwertech:textures/entity/genetic/phasianidae/chicken_wild1/murghi"); addLivingSound(gallus3, "mob.chicken.say", 1, 0.5F); addHurtSound(gallus3, "mob.chicken.hurt", 1, 0.5F);
        redJunglefowl.setSubtype(1, gallus3);

        Subtype spadic = new Subtype(redJunglefowl);
        setPrimaryColors(spadic, "Dark Cyan",             "Dark Slate Gray");                            setSecondaryColors(spadic, "Peru",         "Orange");          setEnglish(spadic, "Burmese Red Junglefowl"); setTranslate(spadic, "qwertech.phasian.redjungle.burmese");
        spadic.setFertility(2000, 8000, 20000)        .setMaturity(8000, 12000, 20000)    .setMutable(10, 100, 1000)    .setSize(500, 1000, 2000)   .setSmart(100, 1000, 10000) .setSnarl(0, 1000, 20000)      .setStamina(1000, 8000, 16000)   .setStrength(1000, 2000, 6000).addBiome(BiomeDictionary.Type.JUNGLE);
        setTextureDir(spadic, "qwertech:textures/entity/genetic/phasianidae/chicken_wild1/spadiceus"); addLivingSound(spadic, "mob.chicken.say", 1, 0.5F); addHurtSound(spadic, "mob.chicken.hurt", 1, 0.5F);
        redJunglefowl.setSubtype(2, spadic);

        Subtype yeahboi = new Subtype(redJunglefowl);
        setPrimaryColors(yeahboi, "Dark Cyan",                  "Sea Green");                            setSecondaryColors(yeahboi, "Peru",         "Orange");          setEnglish(yeahboi,"Tarkin Red Junglefowl"); setTranslate(yeahboi, "qwertech.phasian.redjungle.tarkin");
        yeahboi.setFertility(2000, 8000, 20000)        .setMaturity(8000, 12000, 20000)    .setMutable(10, 100, 1000)    .setSize(500, 1200, 2000)   .setSmart(100, 1000, 10000) .setSnarl(0, 1000, 20000)      .setStamina(1000, 7000, 16000)   .setStrength(1000, 1500, 6000).addBiome(BiomeDictionary.Type.JUNGLE);
        setTextureDir(yeahboi, "qwertech:textures/entity/genetic/phasianidae/chicken_wild2/jabouille");  addLivingSound(yeahboi, "mob.chicken.say", 1, 0.5F); addHurtSound(yeahboi, "mob.chicken.hurt", 1, 0.5F);
        redJunglefowl.setSubtype(3, yeahboi);

        Species greyJunglefowl = new Species(EntityPhasianidae.class);
        setNesting(greyJunglefowl);
        setPrimaryColors(greyJunglefowl, "Black",       "White");     setSecondaryColors(greyJunglefowl, "Black",         "White");     setEnglish(greyJunglefowl, "Grey Junglefowl");           setLatin(greyJunglefowl, "Gallus sonneratii"); setTranslate(greyJunglefowl, "qwertech.phasian.greyjungle");
        greyJunglefowl.setMinFertility(2000)     .setMaxFertility(20000)   .setMinMaturity(8000)      .setMaxMaturity(20000)    .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(500)   .setMaxSize(2000)   .setMinSmart(100) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(1000) .setMaxStamina(16000)   .setMinStrength(1000)    .setMaxStrength(6000);
        addPrimaryDrop(greyJunglefowl, QTI.junglefowlWholeRaw.get(1));  addSecondaryDrop(greyJunglefowl, feather); addEgg(greyJunglefowl, QTI.junglefowlEgg.get(1), COLOR.make(205, 160, 140));
        MobSpeciesRegistry.addSpecies(EntityPhasianidae.class, 2, greyJunglefowl);

        Subtype sunnyrat = new Subtype(greyJunglefowl);
        setSecondaryColors(sunnyrat, COLOR.make(255, 255, 255),                  COLOR.make(255, 240, 240));                            setPrimaryColors(sunnyrat, "Dark Cyan",             "Dark Slate Gray");          setEnglish(sunnyrat, "Grey Junglefowl"); setTranslate(sunnyrat,"qwertech.phasian.greyjungle.primary");
        sunnyrat.setFertility(2000, 8000, 20000)        .setMaturity(8000, 12000, 20000)    .setMutable(10, 100, 1000)    .setSize(500, 1200, 2000)   .setSmart(100, 1000, 10000) .setSnarl(0, 1000, 20000)      .setStamina(1000, 7000, 16000)   .setStrength(1000, 1500, 6000).addBiome(BiomeDictionary.Type.JUNGLE);
        setTextureDir(sunnyrat, "qwertech:textures/entity/genetic/phasianidae/chicken_wild2/sonneratii"); addLivingSound(sunnyrat, "mob.chicken.say", 1, 0.5F); addHurtSound(sunnyrat, "mob.chicken.hurt", 1, 0.5F);
        greyJunglefowl.setSubtype(0, sunnyrat);

        Species turkey = new Species(EntityPhasianidae.class);
        setNesting(turkey);
        setPrimaryColors(turkey, "Black",       "White");     setSecondaryColors(turkey, "Black",         "White");     setEnglish(turkey, "Turkey");           setLatin(turkey, "Meleagris gallopavo"); setTranslate(turkey, "qwertech.phasian.turkey");
        turkey.setMinFertility(2000)     .setMaxFertility(20000)   .setMinMaturity(8000)      .setMaxMaturity(20000)    .setMinMutable(10)      .setMaxMutable(1000) .setMinSize(1000)   .setMaxSize(3000)   .setMinSmart(100) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(1000) .setMaxStamina(12000)   .setMinStrength(1000)    .setMaxStrength(8000);
        addPrimaryDrop(turkey, QTI.turkeyWholeRaw.get(1)); addSecondaryDrop(turkey, feather); addEgg(turkey, QTI.turkeyEgg.get(1), COLOR.make(205, 160, 140));
        turkey.addTag(ATTACK_ON_HIT, new Class[]{Entity.class}).addTag(ATTACK_ON_SPECIESHIT, new Class[]{Entity.class}).addTag(BREEDING_FOOD, new Object[]{"cropCorn", "seedCorn", "listAllseed", Items.wheat_seeds});
        MobSpeciesRegistry.addSpecies(EntityPhasianidae.class, 3, turkey);

        Subtype bbb = new Subtype(turkey);
        setPrimaryColors(bbb, COLOR.make(140, 90, 70), COLOR.make(130, 75, 55)); setSecondaryColors(bbb, COLOR.make(24, 32, 56), COLOR.make(30,75, 75)); setEnglish(bbb, "Broad-breasted Bronze"); setTranslate(bbb, "qwertech.phasian.turkey.bbb");
        bbb.setFertility(4000, 8000, 24000).setMaturity(8000, 12000, 20000).setMutable(10, 100, 1000).setSize(1000, 1100, 3000).setSmart(100, 5000, 10000).setSnarl(10, 15000, 20000).setStamina(1000, 3000, 6000).setStrength(1000, 4000, 8000).addBiome(BiomeDictionary.Type.FOREST);
        setTextureDir(bbb, "qwertech:textures/entity/genetic/phasianidae/turkey_domestic/broadbronze"); addLivingSound(bbb, "qwertech:mob.turkey.say", 1, 0.5F); addHurtSound(bbb,"qwertech:mob.turkey.hurt", 1, 0.5F);   addDeathSound(bbb, "qwertech:mob.turkey.death", 1, 0.5F);
        turkey.setSubtype(0, bbb);
    }
}
