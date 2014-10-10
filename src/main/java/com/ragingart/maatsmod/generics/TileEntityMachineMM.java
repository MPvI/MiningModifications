package com.ragingart.maatsmod.generics;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageTileEntityMachineMM;
import com.ragingart.maatsmod.util.CasingHelper;
import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 29.08.2014.
 */

public abstract class TileEntityMachineMM extends TileEntityMM implements IEnergyHandler,ISidedInventory,IInventory {

    protected EnergyStorage energy = new EnergyStorage(100000);
    protected MachineHelper machineHelper = new MachineHelper();
    protected ItemStack inventory;


    protected int timer = -1;
    @Override
    public void updateEntity(){
        if(!worldObj.isRemote && (timer == -1 || timer%10==0)) {
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityMachineMM(this));
            timer=0;
        }
        timer++;
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
        energy.writeToNBT(cmpd);
        machineHelper.writePortsToNBT(cmpd);
        NBTTagCompound inv = new NBTTagCompound();
        if(inventory != null)inventory.writeToNBT(inv);
        cmpd.setTag("Inventory",inv);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        energy = energy.readFromNBT(cmpd);
        machineHelper.getPortsFromNBT(cmpd);
        inventory = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory"));
    }

    /* Custom */
    public MachineHelper getMachineHelper(){
        return machineHelper;
    }

    public void setMachineHelper(MachineHelper aHelper){
        this.machineHelper=aHelper;
    }

    public void setEnergy(int e)
    {
        this.energy.setEnergyStored(e);
    }

    /**
     * Overwrite with acceptable ports in childclass
     * @return valid ports for machine
     */
    public abstract int[] validPorts();

    /**
     * Only determines if item is ready for extraction / machine ready for insertion
     * @return
     */
    public abstract boolean isWorkDone();

    public boolean canAcceptPort(int i){
        for(int p:validPorts()){
            if(p==i){
                return true;
            }
        }
        return false;
    }

    /* IEnergyHandler */

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

    /* IEnergyConnection */

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return machineHelper.hasPort(from.ordinal(), CasingHelper.Port.ENERGY);
    }

    /* ISidedInventory */

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        return itemStack.getItem() instanceof IEnergyContainerItem;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if(machineHelper.hasPort(side,CasingHelper.Port.INPUT) || machineHelper.hasPort(side, CasingHelper.Port.OUTPUT)) {
            return new int[]{0};
        }
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side) {
        if(isWorkDone()) {
            return isItemValidForSlot(slot, item) && machineHelper.hasPort(side, CasingHelper.Port.INPUT);
        }else {
            return false;
        }
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side) {
        if(isWorkDone()) {
            return machineHelper.hasPort(side, CasingHelper.Port.OUTPUT);
        }else{
            return false;
        }
    }


    /* IInventory */

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
        return "";
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
    public void openInventory() {}

    @Override
    public void closeInventory() {}
}
