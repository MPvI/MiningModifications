package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.inter.IMusclePower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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

    @Override
    public void writeToNBT(NBTTagCompound cmpd){
        super.writeToNBT(cmpd);
        NBTTagCompound inv = new NBTTagCompound();
        if(inventory[0] != null)inventory[0].writeToNBT(inv);
        cmpd.setTag("Inventory0",inv);
        NBTTagCompound inv2 = new NBTTagCompound();
        if(inventory[1] != null)inventory[1].writeToNBT(inv2);
        cmpd.setTag("Inventory1",inv2);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd){
        super.readFromNBT(cmpd);
        inventory[0] = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory0"));
        inventory[1] = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory1"));
    }
}
