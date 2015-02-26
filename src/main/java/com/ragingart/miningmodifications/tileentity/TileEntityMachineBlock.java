package com.ragingart.miningmodifications.tileentity;

import com.ragingart.miningmodifications.generics.TileEntityMachineMM;

public class TileEntityMachineBlock extends TileEntityMachineMM {

    @Override
    public int[] validPorts() {
        return new int[]{0,1,2,3,4,5};
    }

    @Override
    public boolean isWorkDone() {
        return false;
    }
}
