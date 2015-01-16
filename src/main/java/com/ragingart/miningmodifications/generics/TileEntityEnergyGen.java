package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.util.RFHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityEnergyGen extends TileEntityMachineMM {

    protected boolean hasWork = false;


    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(!worldObj.isRemote) {
            RFHelper.transferEnergyToAdjacent(this, 100);
        }
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate){
        return 0;
    }

    @Override
    public int[] validPorts() {
        return new int[]{0,1,2};
    }

    @Override
    public boolean isWorkDone() {
        return hasWork;
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

}
