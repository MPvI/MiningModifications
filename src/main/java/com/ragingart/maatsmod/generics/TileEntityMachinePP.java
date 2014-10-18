package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.inter.IMusclePower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by XtraX on 18.10.2014.
 */
public class TileEntityMachinePP extends TileEntityMM implements IMusclePower,IInventory {

    protected ItemStack inventory[] = new ItemStack[2];

    public TileEntityMachinePP(){
        super();
    }


    /*IMusclePower*/

    @Override
    public boolean canAcceptMusclePower(){
        return true;
    }

    @Override
    public void receiveMusclePower(int amount){}

    /* IInventory */

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack){return false;}

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
    }

    @Override
    public String getInventoryName() {
        return worldObj.getBlock(xCoord,yCoord,zCoord).getLocalizedName();
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
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}
}
