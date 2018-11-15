package com.kbi.qwertech.client.models.entity;

import com.kbi.qwertech.api.entities.IGeneticMob;
import com.kbi.qwertech.api.entities.Species;
import com.kbi.qwertech.api.entities.Subtype;
import com.kbi.qwertech.api.registry.MobSpeciesRegistry;
import com.kbi.qwertech.loaders.RegisterSpecies;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GeneticModelHandler extends ModelBase {

    public ModelBase getModel(Species s, Subtype t, boolean isChild)
    {
        ModelBase model = null;
        ModelBase kiddy = null;
        if (t.hasTag(RegisterSpecies.MODEL_PRIMARY))
        {
            model = (ModelBase)t.getTag(RegisterSpecies.MODEL_PRIMARY);
        } else if (s.hasTag(RegisterSpecies.MODEL_PRIMARY))
        {
            model = (ModelBase)s.getTag(RegisterSpecies.MODEL_PRIMARY);
        } else {
            model = backup;
        }
        if (t.hasTag(RegisterSpecies.MODEL_CHILD))
        {
            kiddy = (ModelBase)t.getTag(RegisterSpecies.MODEL_CHILD);
        } else if (s.hasTag(RegisterSpecies.MODEL_CHILD))
        {
            kiddy = (ModelBase)s.getTag(RegisterSpecies.MODEL_CHILD);
        } else {
            kiddy = backup;
        }
        if (isChild && (kiddy != backup))
        {
            return kiddy;
        }
        return model;
    }


    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        IGeneticMob igm = (IGeneticMob)p_78088_1_;
        Species s = MobSpeciesRegistry.getSpecies(igm.getClass(), igm.getSpeciesID());
        Subtype t = s.getSubtype(igm.getSubtypeID());

        float scale = igm.getSize() * 0.001F;

        GL11.glTranslatef(0F, (-p_78088_7_ * 24F) * (scale - 1), 0F);
        GL11.glScalef(scale, scale, scale);

        ModelBase mb;

        if ((t.hasTag(RegisterSpecies.MODEL_CHILD) || s.hasTag(RegisterSpecies.MODEL_CHILD)) && ((EntityLiving)p_78088_1_).isChild())
        {
            mb = getModel(s, t, true);
        } else {
            mb = getModel(s, t, false);
        }
        mb.isChild = ((EntityLiving)p_78088_1_).isChild();

        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation((String)t.getTag(RegisterSpecies.TEXTURE_PRIMARY)));
        int pc = igm.getPrimaryColor();
        GL11.glColor3f((pc >> 16 & 255) / 255.0F, (pc >> 8 & 255) / 255.0F,(pc & 255) / 255.0F);
        mb.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        GL11.glColor3f(1F, 1F, 1F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation((String)t.getTag(RegisterSpecies.TEXTURE_SECONDARY)));
        pc = igm.getSecondaryColor();
        GL11.glColor3f((pc >> 16 & 255) / 255.0F, (pc >> 8 & 255) / 255.0F,(pc & 255) / 255.0F);
        mb.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        GL11.glColor3f(1F, 1F, 1F);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation((String)t.getTag(RegisterSpecies.TEXTURE_TERTIARY)));
        mb.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        IGeneticMob igm = (IGeneticMob)p_78087_7_;
        Species s = MobSpeciesRegistry.getSpecies(igm.getClass(), igm.getSpeciesID());
        Subtype t = s.getSubtype(igm.getSubtypeID());

        ModelBase m = getModel(s, t, false);
        ModelBase k = getModel(s, t, true);

        m.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        k.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    ModelBase backup = new ModelSquid();

    @Override
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        IGeneticMob igm = (IGeneticMob)p_78086_1_;
        Species s = MobSpeciesRegistry.getSpecies(igm.getClass(), igm.getSpeciesID());
        Subtype t = s.getSubtype(igm.getSubtypeID());

        ModelBase model = getModel(s, t, false);
        ModelBase kiddy = getModel(s, t, true);

        model.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
        kiddy.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
    }

}
