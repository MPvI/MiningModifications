package com.ragingart.maatsmod.tileentity;

import cofh.api.energy.IEnergyHandler;
import com.ragingart.maatsmod.generics.TileEntityMM;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCharger extends TileEntityMM implements IEnergyHandler,IInventory {

    private int capacity = 100000;
    private int stored = 0;
    private ItemStack inventory;

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if(stored + maxReceive <= capacity){
            return maxReceive;
        }else{
            return capacity-stored;
        }
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        if(stored >= maxExtract){
            return maxExtract;
        }
        else {
            return stored;
        }
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return stored;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return capacity;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
      return from==ForgeDirection.DOWN ? true : false;
    }

    @Override
    public void writeSpecialNBT(NBTTagCompound cmpd) {
        cmpd.setInteger(Names.NBT.ENERGY_STORED, stored);
        cmpd.setTag("Inventory",inventory.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readSpecialNBT(NBTTagCompound cmpd) {
        if(cmpd.hasKey(Names.NBT.ENERGY_STORED)){
            stored=cmpd.getInteger(Names.NBT.ENERGY_STORED);
        }
        inventory = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory"));
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return inventory;
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
    public void setInventorySlotContents(int p_70299_1_, ItemStack itemStack) {
        inventory=itemStack;
    }

    @Override
    public String getInventoryName() {
        return "Charger";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
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
        return true;
    }
}
