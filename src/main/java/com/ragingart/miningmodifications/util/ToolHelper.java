package com.ragingart.miningmodifications.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class ToolHelper {
    public static HFE[] getHarvestField(BlockPos pos,EnumFacing side,int r){
        int d = (2*r+1);
        HFE[] result = new HFE[d*d];

        for (int i = 0; i < result.length; i++) {
            result[i]=new HFE();
        }

        return init(pos.getX(),pos.getY(),pos.getZ(),result,r,side.ordinal());
    }


    private static HFE[] init(int x,int y,int z,HFE[] result,int r,int side){
        for (int i = -r; i <= r; i++) {
            for (int j = -r; j <= r; j++) {
                switch (side){
                    case 0:
                    case 1:
                        result[f(i,j,r)].mPos=new BlockPos(x+i,y,z+j);
                        break;
                    case 2:
                    case 3:
                        result[f(i,j,r)].mPos=new BlockPos(x+i,y+j,z);
                        break;
                    case 4:
                    case 5:
                        result[f(i,j,r)].mPos=new BlockPos(x,y+i,z+j);
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }

    private static int f(int a,int b,int r){
        return (a+r)*(2*r+1)+b+r;
    }

    public static class HFE {
        public BlockPos mPos;
        public Block mBlock;

        public HFE(){
            mPos = new BlockPos(0,0,0);
            mBlock=Blocks.air;
        }
    }
}

   /*
    sides
    0 bot
    1 top
    2 right
    3 left
    4 back
    5 front
     */