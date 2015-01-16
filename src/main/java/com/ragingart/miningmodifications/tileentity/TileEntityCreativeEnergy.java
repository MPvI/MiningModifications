package com.ragingart.miningmodifications.tileentity;

import cofh.api.energy.IEnergyConnection;
import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.miningmodifications.generics.TileEntityMM;
import net.minecraft.util.EnumFacing;

public class TileEntityCreativeEnergy extends TileEntityMM implements IEnergyConnection{

    public void updateEntity(){
        if(!worldObj.isRemote) {
            for (int i = 0; i < 5; i++) {
                 EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this, i, 10000, false);
            }
        }
    }

    @Override
    public boolean canConnectEnergy(EnumFacing side) {
        return true;
    }
}
