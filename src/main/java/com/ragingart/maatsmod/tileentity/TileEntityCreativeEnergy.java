package com.ragingart.maatsmod.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.maatsmod.generics.TileEntityMM;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCreativeEnergy extends TileEntityMM  implements IEnergyConnection{

    @Override
    public void updateEntity(){
        if(!worldObj.isRemote) {
            EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this,1,1000,false);
        }
    }

    @Override
    public void writeSpecialNBT(NBTTagCompound cmpd) {
        //NOOP
    }

    @Override
    public void readSpecialNBT(NBTTagCompound cmpd) {
        //NOOP
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return true;
    }
}
