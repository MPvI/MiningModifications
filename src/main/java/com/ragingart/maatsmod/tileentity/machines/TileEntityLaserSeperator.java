package com.ragingart.maatsmod.tileentity.machines;

import com.ragingart.maatsmod.generics.TileEntityMachineMultiBlockMM;

/**
 * Created by MaaT on 18.10.2014.
 */
public class TileEntityLaserSeperator extends TileEntityMachineMultiBlockMM{

    protected static int[][] mBase=new int[][]{{0,0,2,0,0},{2,3,1,3,2},{0,0,2,0,0}}; // 7 Parts;
    protected static int[][] aMachinePart = new int[][]{{0,0,0,0,0},{0,0,2,0,0},{0,0,0,0,0}}; // 1 Part
    protected static int[][] aConnectionPart = new int[][]{{0,0,0,0,0},{0,3,3,3,0},{0,0,0,0,0}}; // 3 Parts

    static{
        mStructure = new int[][][]{aMachinePart,aConnectionPart,mBase,aConnectionPart,aMachinePart};
        mNumberOfParts = 15;
    }



    @Override
    public int[] validPorts() {
        return new int[0];
    }

    @Override
    public boolean isWorkDone() {
        return false;
    }

}
