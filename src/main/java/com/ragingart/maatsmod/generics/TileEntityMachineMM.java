package com.ragingart.maatsmod.generics;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 29.08.2014.
 */

public class TileEntityMachineMM extends TileEntityMM implements IEnergyHandler{

    protected EnergyStorage energy = new EnergyStorage(100000);

    @Override
    public void writeSpecialNBT(NBTTagCompound cmpd) {
        energy.writeToNBT(cmpd);
    }

    @Override
    public void readSpecialNBT(NBTTagCompound cmpd) {
        energy = energy.readFromNBT(cmpd);
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
        return from==ForgeDirection.DOWN;
    }
}
