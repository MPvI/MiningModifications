package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityMachineMultiBlockMM extends TileEntityMachineMM {

    protected static int mNumberOfParts;
    protected static int[] mOffsets;
    protected static int[][][] mStructure;
    protected boolean completeStructure;

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote && (timer == -1 || timer%40==0)) {
            completeStructure=checkStructure(worldObj,pos.add(-mOffsets[0],-mOffsets[1],-mOffsets[2]));
        }
        super.updateEntity();
    }


    public boolean checkStructure(IBlockAccess world, BlockPos pos){
        int aNumberOfParts=0;
        for (int i = 0; i < mStructure.length; i++) {
            for (int j = 0; j < mStructure[i].length; j++) {
                for (int k = 0; k < mStructure[i][j].length; k++) {
                    Block aBlock = world.getBlockState(pos.add(i,j,k)).getBlock();
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
