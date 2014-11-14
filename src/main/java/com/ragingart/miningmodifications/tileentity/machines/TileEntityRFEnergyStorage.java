package com.ragingart.miningmodifications.tileentity.machines;

import com.ragingart.miningmodifications.generics.TileEntityMachineMM;
import com.ragingart.miningmodifications.util.RFHelper;

public class TileEntityRFEnergyStorage extends TileEntityMachineMM {


    @Override
    public void updateEntity()
    {
        super.updateEntity();
        RFHelper.transferEnergyToAdjacent(this);
    }

    @Override
    public int[] validPorts() {
        return new int[]{0,1};
    }

    @Override
    public boolean isWorkDone() {
        return true;
    }

}
