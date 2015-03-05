package com.ragingart.miningmodifications.tileentity.machines;

import com.ragingart.miningmodifications.generics.TileEntityMachineMM;
import com.ragingart.miningmodifications.util.CasingHelper;

public class TileEntityMachineBlock extends TileEntityMachineMM {

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int[] validPorts() {
        return new int[]{0,1,2,3,4,5};
    }

    @Override
    public boolean isWorkDone() {
        return true;
    }

    public int getMultiBlockMode() {
        if (machineHelper.hasPort(CasingHelper.Port.INPUT) || machineHelper.hasPort(CasingHelper.Port.OUTPUT)) {
            return 0;
        } else if (machineHelper.hasPort(CasingHelper.Port.ENERGY)) {
            return 1;
        } else if (machineHelper.hasPort(CasingHelper.Port.FOUTPUT)) {
            return 2;
        } else {
            return 9;
        }
    }
}
