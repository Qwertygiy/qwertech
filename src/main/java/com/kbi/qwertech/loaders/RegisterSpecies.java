package com.kbi.qwertech.loaders;

import com.kbi.qwertech.api.data.COLOR;
import com.kbi.qwertech.api.data.QTI;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.entities.genetic.EntityPhasianidae;
import gregapi.data.CS;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraftforge.common.BiomeDictionary;

public class RegisterSpecies {

    public static void begin()
    {
        Species chicken = new Species(EntityPhasianidae.class);
        chicken     .setPrimaryColors("Black",       "White")     .setSecondaryColors("Black",         "White")     .setCommonName("Chicken")           .setLatinName("Gallus gallus domesticus")   .setMinFertility(16000)     .setMaxFertility(Short.MAX_VALUE)   .setMinMaturity(16000)      .setMaxMaturity(Short.MAX_VALUE)    .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(500)   .setMaxSize(4000)   .setMinSmart(1) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(100) .setMaxStamina(16000)   .setMinStrength(100)    .setMaxStrength(8000)   .setMeat(QTI.chickenWholeRaw.get(1))  .setSecondary(ST.make(Items.feather, 1, 0))   ;
        MobSpeciesRegistry.addSpecies(EntityPhasianidae.class, 0, chicken);
        if (CS.CODE_CLIENT)
        {
            chicken.setModel(new net.minecraft.client.model.ModelChicken());
        }
        Subtype mojang = new Subtype(chicken);
        mojang      .setPrimaryColors("White",       "Goldenrod") .setSecondaryColors("Dark Red",      "Red")       .setCommonName("Mojang Chicken")                                                .setMinFertility(16000)     .setMaxFertility(Short.MAX_VALUE)   .setMinMaturity(16000)      .setMaxMaturity(Short.MAX_VALUE)    .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(500)   .setMaxSize(4000)   .setMinSmart(1) .setMaxSmart(10000) .setMinSnarl(0) .setMaxSnarl(20000) .setMinStamina(100) .setMaxStamina(16000)   .setMinStrength(100)    .setMaxStrength(8000)                                                                                                                   .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_default/mojang")  .setLivingSound("mob.chicken.say")  .setHurtSound("mob.chicken.hurt")   .setDeathSound("mob.chicken.hurt")   .addBiome(BiomeDictionary.Type.PLAINS).addBiome(BiomeDictionary.Type.SAVANNA);
        chicken.setSubtype(0, mojang);
        Subtype rir = new Subtype(chicken);
        rir         .setPrimaryColors(COLOR.get("Peru"),       COLOR.make(50, 15, 5)).setSecondaryColors("Crimson", "Tomato").setCommonName("Rhode Island Red")                    .setMinFertility(16000)     .setMaxFertility(32000)             .setMinMaturity(16000)      .setMaxMaturity(32000)              .setMinMutable(10)      .setMaxMutable(1000)    .setMinSize(800)    .setMaxSize(5000)   .setMinSmart(100).setMaxSmart(8000).setMinSnarl(100).setMaxSnarl(10000).setMinStamina(100)  .setMaxStamina(16000)   .setMinStrength(200)    .setMaxStrength(8000)                                                                                                                  .setTexturePath("qwertech:textures/entity/genetic/phasianidae/chicken_crested/rir") .setLivingSound("mob.chicken.say")  .setHurtSound("mob.chicken.hurt")   .setDeathSound("mob.chicken.hurt");
        chicken.setSubtype(1, rir);

        if (CS.CODE_CLIENT)
        {
            rir.setModel(new com.kbi.qwertech.client.models.entity.ModelChickenCrested());
        }
    }
}
