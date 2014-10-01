package com.ragingart.maatsmod.generics;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageTileEntityMachineMM;
import com.ragingart.maatsmod.util.CasingHelper;
import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 29.08.2014.
 */

public abstract class TileEntityMachineMM extends TileEntityMM implements IEnergyHandler,ISidedInventory {

    protected EnergyStorage energy = new EnergyStorage(100000);
    protected MachineHelper machineHelper = new MachineHelper();

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
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        energy = energy.readFromNBT(cmpd);
        machineHelper.getPortsFromNBT(cmpd);
    }

    public MachineHelper getMachineHelper(){
        return machineHelper;
    }

    public void setMachineHelper(MachineHelper aHelper){
        this.machineHelper=aHelper;
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
        return isItemValidForSlot(slot,item)&&machineHelper.hasPort(side,CasingHelper.Port.INPUT);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side) {
        return machineHelper.hasPort(side,CasingHelper.Port.OUTPUT);
    }
}
