package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

/**
 * Created by MaaT on 22.10.2014.
 */
public class TileEntityMachineMultiBlockMM extends TileEntityMachineMM {

    protected static int mNumberOfParts;
    protected static int[] mOffsets;
    protected static int[][][] mStructure;
    protected boolean completeStructure;

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote && (timer == -1 || timer%40==0)) {
            completeStructure=checkStructure(worldObj,xCoord-mOffsets[0],yCoord-mOffsets[1],zCoord-mOffsets[2]);
        }
        super.updateEntity();
    }


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
