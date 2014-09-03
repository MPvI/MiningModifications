package com.ragingart.maatsmod.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.maatsmod.generics.TileEntityMM;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCreativeEnergy extends TileEntityMM implements IEnergyConnection{

    @Override
    public void updateEntity(){
        if(!worldObj.isRemote) {
            for (int i = 0; i < 5; i++) {
                 EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this, i, 10000, false);
            }
        }
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return true;
    }
}
