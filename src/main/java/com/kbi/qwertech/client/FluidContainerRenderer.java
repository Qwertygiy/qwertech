package com.kbi.qwertech.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class FluidContainerRenderer implements IItemRenderer {

    public FluidContainerRenderer()
    {
        System.out.println("Registered fluid container");
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type.equals(ItemRenderType.ENTITY);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        //System.out.println("AAAAA");
        RenderShortcuts.renderItemFluid(type, item, data);
    }
}
