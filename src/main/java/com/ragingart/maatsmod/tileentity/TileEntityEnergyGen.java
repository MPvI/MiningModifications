package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyGen extends TileEntityMachineMM {

    private int burntime = 0;
    private boolean hasWork = false;


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
            hasWork=true;
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
        cmpd.setInteger("Burntime",burntime);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        burntime = cmpd.getInteger("Burntime");
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

}
