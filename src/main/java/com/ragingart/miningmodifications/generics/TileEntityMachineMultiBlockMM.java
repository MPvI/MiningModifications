package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.tileentity.TileEntityMachineBlock;
import com.ragingart.miningmodifications.util.CasingHelper;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

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
        int nEnergyPorts=0;
        int nItemInputs=0;
        int nItemOutputs=0;
        int nFluidOutputs=0;

        for (int i = 0; i < mStructure.length; i++) {
            for (int j = 0; j < mStructure[i].length; j++) {
                for (int k = 0; k < mStructure[i][j].length; k++) {
                    Block aBlock = world.getBlock(x + i, y + j, z + k);
                    if(aBlock instanceof IMultiBlockPart){
                        int id = ((IMultiBlockPart) aBlock).getID();
                        if(mStructure[i][j][k]==id){
                            if(id==2){
                                TileEntity aTile = world.getTileEntity(x+i,y+j,z+k);
                                if(aTile instanceof TileEntityMachineBlock){
                                    if(((TileEntityMachineBlock) aTile).getMachineHelper().hasPort(CasingHelper.Port.ENERGY)){
                                        nEnergyPorts++;
                                    }
                                    else if(((TileEntityMachineBlock) aTile).getMachineHelper().hasPort(CasingHelper.Port.INPUT)){
                                        nItemInputs++;
                                    }
                                    else if(((TileEntityMachineBlock) aTile).getMachineHelper().hasPort(CasingHelper.Port.OUTPUT)){
                                        nItemOutputs++;
                                    }
                                    else if(((TileEntityMachineBlock) aTile).getMachineHelper().hasPort(CasingHelper.Port.FOUTPUT)){
                                        nFluidOutputs++;
                                    }
                                    aNumberOfParts++;
                                }
                            }else {
                                aNumberOfParts++;
                            }
                        }
                    }
                }
            }
        }
        return aNumberOfParts==mNumberOfParts && nItemOutputs == 2 && nEnergyPorts == 1 && nFluidOutputs == 1 && nItemInputs == 1;
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
