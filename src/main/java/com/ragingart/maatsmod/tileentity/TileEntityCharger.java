package com.ragingart.maatsmod.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import com.ragingart.maatsmod.generics.TileEntityMM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCharger extends TileEntityMM implements IEnergyHandler,IInventory {

    private EnergyStorage energy = new EnergyStorage(100000);
    private ItemStack inventory;

    @Override
    public void updateEntity()
    {
            loadContainer();
    }

    public boolean getHasContainer(){
        if(inventory!=null){
            if(inventory.getItem() instanceof IEnergyContainerItem){
                return true;
            }
        }
        return false;
    }

    public void loadContainer(){
        if(!worldObj.isRemote) {
            if (inventory != null && inventory.getItem() instanceof IEnergyContainerItem) {
                IEnergyContainerItem item = (IEnergyContainerItem) inventory.getItem();

                int maxExtract = energy.getEnergyStored();
                int maxInsert = item.getMaxEnergyStored(inventory) - item.getEnergyStored(inventory);
                int maxTransferRate = item.receiveEnergy(inventory, maxInsert, true);
                int transferRate = Math.min(maxExtract, maxTransferRate);

                int transfered = item.receiveEnergy(inventory, energy.extractEnergy(transferRate, false), false);

                if (transfered > 0 && getHasContainer()) {
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 3);
                } else if (getHasContainer()) {
                    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
                }
            } else {
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
            }
        }
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return energy.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return energy.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {

        return energy.getEnergyStored();

    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {

        return energy.getMaxEnergyStored();

    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
      return from==ForgeDirection.DOWN;
    }

    @Override
    public void writeSpecialNBT(NBTTagCompound cmpd) {
        NBTTagCompound inv = new NBTTagCompound();
        if(inventory != null)inventory.writeToNBT(inv);
        cmpd.setTag("Inventory",inv);
        energy.writeToNBT(cmpd);
    }

    @Override
    public void readSpecialNBT(NBTTagCompound cmpd) {
        inventory = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory"));
        energy = energy.readFromNBT(cmpd);
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
