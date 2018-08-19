package com.kbi.qwertech.loaders;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.entities.genetic.EntityPhasianidae;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraftforge.common.BiomeDictionary;

public class RegisterSpecies {

    public static String EGG_ITEM = "EggItem";
    public static String EGG_COLOR = "EggColor";

    public static void begin()
    {
        Species chicken = new Species(EntityPhasianidae.class);
        chicken     .setPrimaryColors("Black",       "White")     .setSecondaryColors("Black",         "White")     .setCommonName("Chicken")           .setLatinName("Gallus gallus domesticus")   .setMinFertility(8000)     .setMaxFertility(Short.MAX_VALUE)   .setMinMaturity(16000)      .setMaxMaturity(Short.MAX_VALUE)    .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(400)   .setMaxSize(3000)   .setMinSmart(1) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(100) .setMaxStamina(16000)   .setMinStrength(100)    .setMaxStrength(8000)   .setMeat(QTI.chickenWholeRaw.get(1))  .setSecondary(ST.make(Items.feather, 1, 0))   ;
        chicken.addTag(EGG_ITEM, QTI.qwerFood.getWithDamage(1, 32)).addTag(EGG_COLOR, COLOR.make(222, 205, 200));
        MobSpeciesRegistry.addSpecies(EntityPhasianidae.class, 0, chicken);
        Subtype mojang = new Subtype(chicken);
        mojang      .setPrimaryColors("White",                  "Goldenrod")                            .setSecondaryColors("Dark Red",         "Red")          .setCommonName("Mojang Chicken")                        .setFertility(8000, 17000, Short.MAX_VALUE)        .setMaturity(16000, 17000, Short.MAX_VALUE)    .setMutable(10, 100, 1000)    .setSize(400, 1000, 2000)   .setSmart(1, 1000, 10000) .setSnarl(0, 10, 20000)      .setStamina(100, 6000, 16000)   .setStrength(100, 1000, 8000)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_default/mojang")  .setLivingSound("mob.chicken.say")  .setHurtSound("mob.chicken.hurt")   .setDeathSound("mob.chicken.hurt")   .addBiome(BiomeDictionary.Type.PLAINS).addBiome(BiomeDictionary.Type.SAVANNA);
        chicken.setSubtype(0, mojang);
        Subtype rir = new Subtype(chicken);
        rir         .setPrimaryColors(COLOR.get("Peru"),        COLOR.make(50, 15, 5))  .setSecondaryColors("Crimson",          "Tomato")       .setCommonName("Rhode Island Red")                      .setFertility(16000, 20000, 32000)             .setMaturity(16000, 17000, 32000)         .setMutable(10, 100, 1000)    .setSize(800, 1100, 2000)   .setSmart(100, 1000, 8000).setSnarl(100, 500, 10000)   .setStamina(100, 6000, 16000)   .setStrength(200, 1500, 8000)                                                                                                                  .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_crested/rir") .setLivingSound("mob.chicken.say")  .setHurtSound("mob.chicken.hurt")   .setDeathSound("mob.chicken.hurt");
        rir.addTag(EGG_COLOR, COLOR.make(205, 160, 140));
        chicken.setSubtype(1, rir);
        Subtype riw = new Subtype(chicken);
        riw         .setPrimaryColors(COLOR.get("White"),       COLOR.make(240, 240, 240)).setSecondaryColors("Crimson",        "Tomato")       .setCommonName("Rhode Island White")                    .setFertility(16000, 20000, 32000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(800, 1100, 2000)   .setSmart(100, 1000, 8000).setSnarl(100, 500, 10000)    .setStamina(100, 6000, 16000) .setStrength(200, 1500, 8000)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_crested/rir").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        riw.addTag(EGG_COLOR, COLOR.make(205, 160, 140)).setPrimaryTexture("qwertech:textures/entity/genetic/phasianidae/chicken_crested/riw_primary.png");
        chicken.setSubtype(2, riw);
        Subtype andal = new Subtype(chicken);
        andal       .setPrimaryColors(COLOR.make(240, 240, 240),       COLOR.make(150, 150, 160)).setSecondaryColors("Salmon",        "Tomato")       .setCommonName("Andalusian")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 200, 1000)      .setSize(600, 1000, 1500)   .setSmart(100, 1000, 8000).setSnarl(200, 6000, 20000)    .setStamina(500, 10000, 16000) .setStrength(100, 1000, 7000)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_crested/andalusian").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(3, andal);
        Subtype leghorn = new Subtype(chicken);
        leghorn       .setPrimaryColors(COLOR.make(240, 240, 240),       COLOR.get("White")).setSecondaryColors("Red",        "Dark Red")       .setCommonName("White Leghorn")                            .setFertility(12000, 17000, 32000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(4, leghorn);
        Subtype darkbrownlh = new Subtype(chicken);
        darkbrownlh       .setPrimaryColors(COLOR.get("Peru"),        COLOR.make(50, 15, 5)).setSecondaryColors(COLOR.make(10, 10, 10), COLOR.make(10, 30, 40))       .setCommonName("Dark Brown Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(5, darkbrownlh);
        Subtype lightbrownlh = new Subtype(chicken);
        lightbrownlh       .setPrimaryColors(COLOR.make(230, 160, 80),        COLOR.make(100, 30, 10)).setSecondaryColors(COLOR.make(10, 10, 10), COLOR.make(10, 30, 40))       .setCommonName("Light Brown Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(6, lightbrownlh);
        Subtype blacklh = new Subtype(chicken);
        blacklh       .setPrimaryColors(COLOR.make(10, 10, 10), COLOR.make(10, 30, 40)).setSecondaryColors(COLOR.make(10, 10, 10), COLOR.make(10, 30, 40))       .setCommonName("Black Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(7, blacklh);
        Subtype bufflh = new Subtype(chicken);
        bufflh       .setPrimaryColors("Gold",       "Goldenrod").setSecondaryColors("Red",        "Dark Red")       .setCommonName("Buff Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(8, bufflh);
        Subtype silverlh = new Subtype(chicken);
        silverlh       .setPrimaryColors(COLOR.get("White"),       COLOR.make(240, 240, 240)).setSecondaryColors(COLOR.make(10, 10, 10), COLOR.make(10, 30, 40))       .setCommonName("Silver Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(9, silverlh);
        Subtype btredlh = new Subtype(chicken);
        btredlh       .setPrimaryColors(COLOR.make(80, 0, 0),       COLOR.make(120, 40, 0)).setSecondaryColors(COLOR.make(10, 10, 10), COLOR.make(10, 30, 40))       .setCommonName("Black-Tailed Red Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh2").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(10, btredlh);
        Subtype redlh = new Subtype(chicken);
        redlh       .setPrimaryColors(COLOR.make(80, 0, 0),       COLOR.make(120, 40, 0)).setSecondaryColors("Red",        "Dark Red")       .setCommonName("Red Leghorn")                            .setFertility(10000, 15000, 30000)              .setMaturity(16000, 17000, 32000)       .setMutable(10, 100, 1000)      .setSize(700, 1000, 1600)   .setSmart(100, 3000, 8000).setSnarl(200, 5000, 20000)    .setStamina(500, 10000, 16000) .setStrength(150, 1200, 7500)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_tailed/lh").setLivingSound("mob.chicken.say") .setHurtSound("mob.chicken.hurt")    .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(11, redlh);
    }
}
