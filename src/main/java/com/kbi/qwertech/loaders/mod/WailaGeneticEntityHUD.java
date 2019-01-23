package com.kbi.qwertech.loaders.mod;

import com.kbi.qwertech.api.entities.IGeneticMob;
import cpw.mods.fml.common.Optional;
import gregapi.data.CS;
import gregapi.data.LH;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

@Optional.InterfaceList(value = {
        @Optional.Interface(iface = "mcp.mobius.waila.api.IWailaEntityProvider", modid = CS.ModIDs.WAILA)
})
public class WailaGeneticEntityHUD implements mcp.mobius.waila.api.IWailaEntityProvider {

    String b = LH.Chat.ITALIC;
    String r = LH.Chat.RESET_TOOLTIP;
    public Entity getWailaOverride(mcp.mobius.waila.api.IWailaEntityAccessor accessor, mcp.mobius.waila.api.IWailaConfigHandler config) {
        return null;
    }


    public List<String> getWailaHead(Entity entity, List<String> currenttip, mcp.mobius.waila.api.IWailaEntityAccessor accessor, mcp.mobius.waila.api.IWailaConfigHandler config) {
        return currenttip;
    }


    public List<String> getWailaBody(Entity entity, List<String> currenttip, mcp.mobius.waila.api.IWailaEntityAccessor accessor, mcp.mobius.waila.api.IWailaConfigHandler config) {
        if (entity instanceof IGeneticMob)
        {
            IGeneticMob igm = (IGeneticMob)entity;
            String[] s = new String[]{
                    "" + igm.getSmart(),
                    "" + igm.getSize(),
                    "" + igm.getStamina(),
                    "" + igm.getSnarl(),
                    "" + igm.getMaturity(),
                    "" + igm.getFertility(),
                    "" + igm.getStrength(),
                    "" + igm.getMutable()
            };
            for (int q = 0; q < s.length; q++)
            {
                s[q] = (s[q].length() == 1 ? "    " : (s[q].length() == 2 ? "   " : (s[q].length() == 3 ? "  " : (s[q].length() == 4 ? " " : "")))) + s[q];
            }
            currenttip.add(LH.Chat.RED      + "Smart:   " + r + s[0] + LH.Chat.DCYAN    + " | Maturity:  " + r + s[4]);
            currenttip.add(LH.Chat.ORANGE   + "Size:     " + r + s[1] + LH.Chat.BLUE    + " | Fertility:  " + r + s[5]);
            currenttip.add(LH.Chat.YELLOW   + "Stamina: " + r + s[2] + LH.Chat.PURPLE   + " | Strength: " + r + s[6]);
            currenttip.add(LH.Chat.GREEN    + "Snarl:   " + r + s[3] + LH.Chat.PINK     + " | Mutable:    " + r + s[7]);
        }
        return currenttip;
    }


    public List<String> getWailaTail(Entity entity, List<String> currenttip, mcp.mobius.waila.api.IWailaEntityAccessor accessor, mcp.mobius.waila.api.IWailaConfigHandler config) {
        return currenttip;
    }

    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
        return tag;
    }
}
