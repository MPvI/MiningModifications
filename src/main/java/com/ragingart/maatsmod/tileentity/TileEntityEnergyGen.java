package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyGen extends TileEntityMachineMM implements IInventory {

    private ItemStack inventory;
    private int burntime = 0;



    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(!worldObj.isRemote) {
            this.handleBurnTime();
            MachineHelper.transferEnergyToAdjacent(this);
        }
    }

    public void handleBurnTime(){
        int burnenergy = 1;
        int add_burntime = TileEntityFurnace.getItemBurnTime(inventory);
        int get_energy = getEnergyStored(ForgeDirection.UNKNOWN);
        int get_maxenergy = getMaxEnergyStored(ForgeDirection.UNKNOWN);
        if (burntime == 0 && add_burntime != 0 && get_energy + add_burntime <= get_maxenergy) {
            if (decrStackSize(0, 1) != null) {
                burntime = add_burntime / burnenergy;
            }
        }
        if (burntime > 0) {
            energy.modifyEnergyStored(burnenergy);
            burntime--;
        }
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate){
        return 0;
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
        NBTTagCompound inv = new NBTTagCompound();
        if(inventory != null)inventory.writeToNBT(inv);
        cmpd.setTag("Inventory",inv);
        cmpd.setInteger("Burntime",burntime);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        inventory = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory"));
        burntime = cmpd.getInteger("Burntime");
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
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
            inventory = itemStack;
    }

    @Override
    public String getInventoryName() {
        return "EnergyGen";
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
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }


}
