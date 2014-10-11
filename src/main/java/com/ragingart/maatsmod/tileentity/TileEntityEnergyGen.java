package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.util.RFHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityEnergyGen extends TileEntityMachineMM {

    protected boolean hasWork = false;


    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(!worldObj.isRemote) {
            RFHelper.transferEnergyToAdjacent(this);
        }
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate){
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
