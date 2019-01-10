package com.kbi.qwertech.items.behavior;

import com.kbi.qwertech.api.data.MUSE;
import com.kbi.qwertech.api.data.NOTE;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

import java.util.List;

public class Behavior_Note extends gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault implements IBehavior<MultiItem> {

    public String soundName;
    public NOTE[] song;
    private int progress = 0;

    public Behavior_Note(String sound) {
        soundName = sound;
    }

    @Override
    public ItemStack onItemRightClick(MultiItem aItem, ItemStack stack, World world,
                                      EntityPlayer player) {
        if (world.isRemote) return stack;
        String octave = ".3";
        if (player.isSneaking() || song == null) {
            song = MUSE.getSong(world.rand.nextInt(2000));
            progress = 0;
        }
        float pitchy = song[progress].get() / NOTE.E3.get();
        if (pitchy < 0.6F)
        {
            pitchy = song[progress].get() / NOTE.E2.get();
            octave = ".2";
        } else if (pitchy > 1.9F)
        {
            pitchy = song[progress].get() / NOTE.E4.get();
            octave = ".4";
        }
        UT.Sounds.send(world, soundName + octave, 0.5F, pitchy, (int)player.posX, (int)player.posY, (int)player.posZ);
        progress = progress + 1;
        if (progress >= song.length) progress = 0;

        if (soundName.equals("qwertech:note.kazoo"))
        {
            List<Entity> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(8, 4, 8));
            for (int q = 0; q < entities.size(); q++)
            {
                Entity bob = entities.get(q);
                if (!(bob instanceof EntityPlayer))
                {
                    ((EntityLivingBase)bob).attackEntityFrom(new EntityDamageSource("sound", bob), 0);
                }
            }
        }

        return stack;
    }
}
