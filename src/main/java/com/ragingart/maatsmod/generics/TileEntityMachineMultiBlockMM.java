package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.api.IMultiBlockPart;
import com.ragingart.maatsmod.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

/**
 * Created by MaaT on 22.10.2014.
 */
public class TileEntityMachineMultiBlockMM extends TileEntityMachineMM {

    protected static int[][][] mStructure;
    protected static int mNumberOfParts;



    public boolean checkStructure(IBlockAccess world, int x, int y, int z){
        int aNumberOfParts=0;
        for (int i = 0; i < mStructure.length; i++) {
            for (int j = 0; j < mStructure[i].length; j++) {
                for (int k = 0; k < mStructure[i][j].length; k++) {
                    Block aBlock = world.getBlock(x + i, y + j, z + k);
                    if(aBlock instanceof IMultiBlockPart){
                        if(mStructure[i][j][k]==((IMultiBlockPart) aBlock).getID()){
                            aNumberOfParts++;
                        }
                    }
                }
            }
        }
        LogHelper.info(aNumberOfParts);
        return aNumberOfParts==mNumberOfParts;
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
