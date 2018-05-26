package com.kbi.qwertech.api.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class InventoryScroll implements IInventory {

    public ArrayList<ItemStack> contents;
    public int starting = 0;
    public Container eventHandler;

    private int height;
    private int width;

    public InventoryScroll(Container one)
    {
        this(one, 8);
    }

    public InventoryScroll(Container one, int size)
    {
        this(one, size, 2);
    }

    public InventoryScroll(Container one, int size, int wide)
    {
        contents = new ArrayList<ItemStack>(size);
        eventHandler = one;
        height = size;
        width = wide;
    }

    public boolean setList(ArrayList<ItemStack> newContents)
    {
        try {
            contents = newContents;
            if (contents.size() <= height)
            {
                starting = 0;
            }
            //this.eventHandler.onCraftMatrixChanged(this);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public int scrollUp(int amount)
    {
        starting = Math.max(0, Math.min(contents.size() - height, starting + (amount * width)));
        starting = (int)Math.ceil((float)starting / (float)width) * width;
        return starting;
    }

    public int scrollDown(int amount)
    {
        starting = Math.max(0, starting - (amount * width));
        return starting;
    }

    public int scroll(float percentage)
    {
        int amount = Math.round(contents.size() * percentage);
        amount = Math.round(amount / width) * width;
        amount = Math.max(0, Math.min(contents.size() - this.getSizeInventory(), amount));
        return amount;
    }

    public float getScroll()
    {
        if (contents.size() <= this.getSizeInventory()) return 0;
        float returnable = (float)starting / ( (float)contents.size() - (float)getSizeInventory() );
        if (returnable > 1) return 1;
        if (returnable < 0) return 0;
        return returnable;
    }

    @Override
    public int getSizeInventory() {
        return height;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return contents.size() > starting + p_70301_1_ ? contents.get(starting + p_70301_1_) : null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        this.eventHandler.onCraftMatrixChanged(this);
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        int adjusted = starting + p_70299_1_;
        if (adjusted < contents.size())
        {
            contents.set(adjusted, p_70299_2_);
        } else {
            contents.add(p_70299_2_);
        }
        this.eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public String getInventoryName() {
        return "qt.gui.scroll";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}
