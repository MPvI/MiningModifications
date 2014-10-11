package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.ref.Fluids;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityWaterTurbine extends TileEntityEnergyGen {


    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(!worldObj.isRemote){
            if(tank.getFluidAmount() != 0 && tank.getFluid().getFluid().getID() == Fluids.ID.HIGHHELDWATER.ordinal()){
                energy.modifyEnergyStored(tank.drain(100, true).amount);
            }
        }
    }


    @Override
    public int[] validPorts() {
        return new int[]{0,1,4,5};
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
