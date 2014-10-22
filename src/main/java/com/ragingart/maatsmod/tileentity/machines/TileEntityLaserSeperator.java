package com.ragingart.maatsmod.tileentity.machines;

import com.ragingart.maatsmod.generics.TileEntityMachineMultiBlockMM;

/**
 * Created by MaaT on 18.10.2014.
 */
public class TileEntityLaserSeperator extends TileEntityMachineMultiBlockMM{



    static{
        int[][] mBase=new int[][]{{0,0,2,0,0},{2,3,1,3,2},{0,0,2,0,0}}; // 7 Parts;
        int[][] aMachinePart = new int[][]{{0,0,0,0,0},{0,0,2,0,0},{0,0,0,0,0}}; // 1 Part
        int[][] aConnectionPart = new int[][]{{0,0,0,0,0},{0,3,3,3,0},{0,0,0,0,0}}; // 3 Parts

        mStructure = new int[][][]{aMachinePart,aConnectionPart,mBase,aConnectionPart,aMachinePart};
        mOffsets = new int[]{-2,-1,-2};
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
