package com.ragingart.miningmodifications.tileentity.machines;


import com.ragingart.miningmodifications.generics.TileEntityMachineMultiBlockMM;

public class TileEntityLaserSeparator extends TileEntityMachineMultiBlockMM {


    static{
        int[][] mBase=new int[][]{{0,0,0,0,0},{2,3,1,3,2},{0,0,2,0,0}}; // 6 Parts;
        int[][] aMachinePart = new int[][]{{0,0,0,0,0},{0,0,2,0,0},{0,0,0,0,0}}; // 1 Part
        int[][] aConnectionPart = new int[][]{{0,0,0,0,0},{0,3,3,3,0},{0,0,0,0,0}}; // 3 Parts

        mStructure = new int[][][]{aMachinePart,aConnectionPart,mBase,aConnectionPart,aMachinePart};
        mOffsets = new int[]{-2,-1,-2};
        mNumberOfParts = 14;
    }

    // 2x SiO -> Si + SiO2
    // 1x Si + O2 -> SiO2
    // 4x H20 + O2 -> 2x H2O2 + 2 coolingH2O

    @Override
    public int[] validPorts() {
        return new int[0];
    }

    @Override
    public boolean isWorkDone() {
        return false;
    }

}
