package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.tileentity.machines.TileEntityMachineBlock;
import com.ragingart.miningmodifications.util.CasingHelper;
import net.minecraft.world.World;

public abstract class TileEntityMachineMultiBlockMM extends TileEntityMachineMM {

    protected static int mNumberOfParts;
    protected static int[] mOffsets;
    protected static int[][][] mStructure;


    public abstract boolean checkStructure(World world, int x, int y, int z);

    @Override
    public int[] validPorts() {
        return new int[0];
    }

    @Override
    public boolean isWorkDone() {
        return false;
    }

    public class MultiBlockPort{
        public CasingHelper.Port mPort;
        public int x,y,z;

        public MultiBlockPort(CasingHelper.Port p,int x, int y, int z){
            this.mPort=p;
            this.x=x;
            this.y=y;
            this.z=z;
        }

        public TileEntityMachineBlock getTile(World world){
            return (TileEntityMachineBlock) world.getTileEntity(this.x,this.y,this.z);
        }

    }
}
