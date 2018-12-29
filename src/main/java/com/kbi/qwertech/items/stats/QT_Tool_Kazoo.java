package com.kbi.qwertech.items.stats;

import com.kbi.qwertech.items.behavior.Behavior_Note;
import com.kbi.qwertech.items.behavior.Behavior_Scrape;
import com.kbi.qwertech.items.behavior.Behavior_Swing;
import com.kbi.qwertech.loaders.RegisterArmor;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import static gregapi.data.CS.UNCOLOURED;

public class QT_Tool_Kazoo extends ToolStats {

    @Override
    public String getMiningSound() {
        return "qwertech:note.kazoo";
    }

    @Override
    public String getCraftingSound() {
        return "qwertech:note.kazoo";
    }

    @Override
    public String getEntityHitSound() {
        return "qwertech:note.kazoo";
    }

    @Override
    public String getBreakingSound() {
        return "qwertech:note.kazoo";
    }

    @Override
    public boolean isMinableBlock(Block aBlock, byte aMetaData) {
        return false;
    }

    @Override
    public boolean isWeapon()
    {
        return true;
    }

    @Override
    public boolean isMiningTool()
    {
        return false;
    }

    @Override
    public float getBaseDamage()
    {
        return 0.5F;
    }

    @Override
    public float getMaxDurabilityMultiplier()
    {
        return 0.1F;
    }

    @Override
    public int getRenderPasses() {
        return 2;
    }

    @Override
    public IIcon getIcon(ItemStack aStack, int aRenderPass) {
        return ((IIconContainer)RegisterArmor.iconTitle.get("qwertech:kazoo")).getIcon(aRenderPass);
    }

    @Override
    public short[] getRGBa(ItemStack aStack, int aRenderPass) {
        return aRenderPass == 0 ? MultiItemTool.getPrimaryMaterial(aStack).mRGBaSolid : UNCOLOURED;
    }

    @Override
    public String getDeathMessage()
    {
        return "[VICTIM]'s ears exploded after a [KILLER] concert";
    }

    @Override
    public void onStatsAddedToTool(MultiItemTool aItem, int aID)
    {
        aItem.addItemBehavior(aID, new Behavior_Note("qwertech:note.kazoo"));
    }
}
