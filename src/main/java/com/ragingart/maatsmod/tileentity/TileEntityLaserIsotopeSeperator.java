package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMachineMM;

/**
 * Created by MaaT on 18.10.2014.
 */
public class TileEntityLaserIsotopeSeperator extends TileEntityMachineMM {
    @Override
    public int[] validPorts() {
        return new int[0];
    }

    @Override
    public boolean isWorkDone() {
        return false;
    }
}
