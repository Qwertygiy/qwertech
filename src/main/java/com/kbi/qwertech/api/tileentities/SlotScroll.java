package com.kbi.qwertech.api.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotScroll extends Slot {

    boolean canInsertItem;
    boolean canStackItem;
    int stackLimit;

    public SlotScroll(IInventory inventory, int index, int x, int y, boolean aCanInsertItem, boolean aCanStackItem, int aMaxStacksize) {
        super(inventory, index, x, y);
        canInsertItem = aCanInsertItem;
        canStackItem = aCanStackItem;
        stackLimit = aMaxStacksize;
    }

    @Override
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
        super.onSlotChange(p_75220_1_, p_75220_2_);
    }

    @Override
    protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {
        super.onCrafting(p_75210_1_, p_75210_2_);
    }

    @Override
    protected void onCrafting(ItemStack p_75208_1_) {
        super.onCrafting(p_75208_1_);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
        super.onPickupFromSlot(p_82870_1_, p_82870_2_);
    }

    @Override
    public boolean isItemValid(ItemStack p_75214_1_) {
        return this.canInsertItem;
    }

    @Override
    public ItemStack getStack() {
        return super.getStack();
    }

    @Override
    public boolean getHasStack() {
        return false;
    }

    @Override
    public void putStack(ItemStack p_75215_1_) {
        if (this.canInsertItem) {
            super.putStack(p_75215_1_);
        }
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
    }

    @Override
    public int getSlotStackLimit() {
        return stackLimit;
    }

    @Override
    public ItemStack decrStackSize(int p_75209_1_) {
        return super.decrStackSize(p_75209_1_);
    }

    @Override
    public boolean isSlotInInventory(IInventory p_75217_1_, int p_75217_2_) {
        return super.isSlotInInventory(p_75217_1_, p_75217_2_);
    }

    @Override
    public boolean canTakeStack(EntityPlayer p_82869_1_) {
        return false;
    }


    @Override
    public int getSlotIndex() {
        return super.getSlotIndex();
    }
}
